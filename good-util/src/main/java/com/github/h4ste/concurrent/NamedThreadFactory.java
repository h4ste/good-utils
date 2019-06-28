package com.github.h4ste.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* package-private */ class NamedThreadFactory implements ThreadFactory {

  private final ThreadGroup group;
  private final AtomicInteger threadNumber = new AtomicInteger(1);
  private final String namePrefix;

  /* package-private */ NamedThreadFactory(String prefix) {
    final SecurityManager s = System.getSecurityManager();
    group = (s != null) ? s.getThreadGroup() :
        Thread.currentThread().getThreadGroup();
    namePrefix = prefix + '-';
  }

  public Thread newThread(Runnable r) {
    Thread t = new Thread(group, r,
        namePrefix + threadNumber.getAndIncrement(),
        0);
    if (t.isDaemon()) {
      t.setDaemon(false);
    }
    if (t.getPriority() != Thread.NORM_PRIORITY) {
      t.setPriority(Thread.NORM_PRIORITY);
    }
    return t;
  }
}
