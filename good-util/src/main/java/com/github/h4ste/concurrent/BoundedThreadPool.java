package com.github.h4ste.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by travis on 6/8/15.
 */
@SuppressWarnings("unused")
public abstract class BoundedThreadPool {

  private BoundedThreadPool() {
  }

  public static ExecutorService create(final int size) {
    final BlockingQueue<Runnable> linkedBlockingDeque = new ArrayBlockingQueue<>(size);
    final int N = Runtime.getRuntime().availableProcessors();
    return new ThreadPoolExecutor(N, N, 0,
        TimeUnit.MILLISECONDS, linkedBlockingDeque,
        new BlockPolicy());
  }

  public static ExecutorService create(final int size, final String name) {
    final BlockingQueue<Runnable> linkedBlockingDeque = new ArrayBlockingQueue<>(size);
    final int N = Runtime.getRuntime().availableProcessors();
    return new ThreadPoolExecutor(N, N, 0,
        TimeUnit.MILLISECONDS, linkedBlockingDeque,
        new NamedThreadFactory(name),
        new BlockPolicy());
  }

  public static ExecutorService create(final int N, final int size, final String name) {
    final BlockingQueue<Runnable> linkedBlockingDeque = new ArrayBlockingQueue<>(size);
    return new ThreadPoolExecutor(N, N, 0,
        TimeUnit.MILLISECONDS, linkedBlockingDeque,
        new NamedThreadFactory(name),
        new BlockPolicy());
  }

  // test
}
