package com.github.h4ste.logging;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/* package-private */ final class Time {

  private Time() {
  }

  // See: https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
  /* package-private */ static String formatDuration(Duration duration) {
    return duration.toString()
        .substring(2)
        .replaceAll("(\\d[HMS])(?!$)", "$1 ")
        .toLowerCase();
  }

  /* package-private */ static String formatDuration(long amount, ChronoUnit unit) {
    return formatDuration(Duration.of(amount, unit));
  }

  /**
   * @return a human-readable formatted string for the given amount of nanoseconds
   */
  /* package-private */ static String formatElapsedNanos(long nanos) {
    return formatDuration(nanos, ChronoUnit.NANOS);
  }


  /**
   * @return a human-readable formatted string for the given amount of milliseconds
   */
  /* package-private */ static String formatElapsed(long millis) {
    return formatDuration(millis, ChronoUnit.MILLIS);
  }
}

