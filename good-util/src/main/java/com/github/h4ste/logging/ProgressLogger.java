package com.github.h4ste.logging;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public final class ProgressLogger implements AutoCloseable {

  private long startTime;
  private final String description;
  private final long updateInterval;
  private final Logger log;
  private final DecimalFormat df = new DecimalFormat("#,##0.0#");
  private final ReentrantLock lock = new ReentrantLock();
  private long max;
  private long last = 0L;
  private long lastPrint = 0L;

  private final AtomicBoolean started = new AtomicBoolean(false);

  private ProgressLogger(String description, long max, long updateInterval, TimeUnit updateUnit) {
    this.updateInterval = updateUnit.toMillis(updateInterval);
    this.startTime = System.currentTimeMillis();
    this.max = max;
    this.description = description;
    String clazz = getCallerClassName();
    if (clazz == null) {
      log = LogManager.getLogger(ProgressLogger.class);
    } else {
      log = LogManager.getLogger(clazz);
    }
  }

  @SuppressWarnings("UnusedReturnValue")
  public ProgressLogger maybeStart() {
    // compareAndSet is an atomic operation, so this ensures that the startTime will only be
    // updated the first time maybeStart() is called
    if (started.compareAndSet(false, true)) {
      this.startTime = System.currentTimeMillis();
    }
    return this;
  }

  /**
   * Gets the name of the class that called the method that called this method. Taken from:
   * http://stackoverflow.com/questions/11306811/how-to-get-the-caller-class-in-java
   *
   * @return Class name
   */
  private static String getCallerClassName() {
    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
    for (int i = 1; i < stElements.length; i++) {
      StackTraceElement ste = stElements[i];
      if (!ste.getClassName().equals(ProgressLogger.class.getName())
          && ste.getClassName().indexOf("java.lang.Thread") != 0) {
        return ste.getClassName();
      }
    }
    return null;
  }

  public static ProgressLogger fixedSize(String description, long max, long updateInterval,
      TimeUnit updateUnit) {
    return new ProgressLogger(description, max, updateInterval, updateUnit);
  }

  public static ProgressLogger indeterminateSize(String description, long updateInterval,
      TimeUnit updateUnit) {
    return new ProgressLogger(description, -1, updateInterval, updateUnit);
  }

  private void updateStatus(long current, Level level, String status, Object... args) {
    lock.lock();
    try {
      final long currentTime = System.currentTimeMillis();
      if (currentTime - lastPrint > updateInterval) {
        lastPrint = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        sb.append('[').append(description).append(']').append(' ').append(status);
        sb.append(String.format(" [%,d", current));
        if (max > 0) {
          sb.append(String.format(" %05.2f%%", current * 100.0 / max));
        }
        if (current > 0) {
          final long totalTime = lastPrint - startTime;
          final double avgTime = totalTime / (double) current;
//          final long totSq = totalTime * totalTime;
          sb.append("][TOT: ")
              .append(Time.formatElapsed(totalTime))
              .append("][AVG: ")
              .append(Time.formatElapsed((long) avgTime))
              .append("][RATE: ")
              .append(df.format(1000.0 / avgTime)).append("/s");
          if (max > 0 && current > 1) {
            sb.append("][ETA: ")
                .append(Time.formatElapsed((long) avgTime * (max - current)));
          }
        }
        sb.append(']');
        switch (level) {
          case TRACE:
            log.trace(sb.toString(), args);
            break;
          case DEBUG:
            log.debug(sb.toString(), args);
            break;
          case INFO:
            log.info(sb.toString(), args);
            break;
          case WARN:
            log.warn(sb.toString(), args);
            break;
          case ERROR:
            log.error(sb.toString(), args);
            break;
        }
      }
      last = current;
    } finally {
      lock.unlock();
    }
  }

  public void debug(String status, Object... args) {
    updateStatus(last, Level.DEBUG, status, args);
  }

  public void error(String status, Object... args) {
    updateStatus(last, Level.ERROR, status, args);
  }

  public void info(String status, Object... args) {
    updateStatus(last, Level.INFO, status, args);
  }

  public void trace(String status, Object... args) {
    updateStatus(last, Level.TRACE, status, args);
  }

  public void warn(String status, Object... args) {
    updateStatus(last, Level.WARN, status, args);
  }

  public void update(String status, Object... args) {
    assert max == -1 || last < max;
    update(last + 1, status, args);
  }

  public void update(int n, String status, Object... args) {
    assert max == -1 || last < max;
    update(last + n, status, args);
  }

  public void skip(int n, String status, Object... args) {
    if (max > 0) {
      max -= n;
    }
    update(last, status, args);
  }

  public void skip(String status, Object... args) {
    if (max > 0) {
      max--;
    }
    update(last, status, args);
  }

  private void update(long current, String status, Object... args) {
    updateStatus(current, Level.INFO, status, args);
  }

  public void error(long current, String status, Object... args) {
    updateStatus(current, Level.ERROR, status, args);
  }

  public void info(long current, String status, Object... args) {
    updateStatus(current, Level.INFO, status, args);
  }

  public void trace(long current, String status, Object... args) {
    updateStatus(current, Level.TRACE, status, args);
  }

  public void warn(long current, String status, Object... args) {
    updateStatus(current, Level.WARN, status, args);
  }

  public void close() {
    lock.lock();
    try {
      final String elapsed = Time.formatElapsed(System.currentTimeMillis() - startTime);
      if (last > 0) {
        if (last == max || max < 0L) {
          log.info("[{}] completed after {} iterations [{}]",
              description, last, elapsed);
        } else if (max > 0L) {
          log.warn("[{}] failed after {} iterations (of {}) [{}]", description,
              last, max, elapsed);
        } else {
          log.warn("[{}] failed after {} iterations [{}]", description,
              last, elapsed);
        }
      } else {
        log.info("[{}] completed after skipping all iterations!", description);
      }
    } finally {
      lock.unlock();
    }
  }

  public enum Level {
    TRACE, DEBUG, INFO, WARN, ERROR
  }
}

