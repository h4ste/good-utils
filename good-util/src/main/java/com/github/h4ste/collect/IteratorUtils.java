package com.github.h4ste.collect;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public final class IteratorUtils {

  private IteratorUtils() {
    throw new UnsupportedOperationException("Cannot instantiate utility class");
  }

  public static <T> T nextOrIOException(Iterator<? extends T> source, String message) throws IOException {
    if (Objects.requireNonNull(source).hasNext()) {
      return source.next();
    } else {
      throw new IOException(message);
    }
  }

  public static <T> T nextOrNull(Iterator<? extends T> source) {
    if (Objects.requireNonNull(source).hasNext()) {
      return source.next();
    } else {
      return null;
    }
  }

}
