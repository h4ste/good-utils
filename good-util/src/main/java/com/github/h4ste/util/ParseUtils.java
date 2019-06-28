package com.github.h4ste.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

public final class ParseUtils {

  private ParseUtils() {
    throw new UnsupportedOperationException("Cannot instantiate utility class");
  }

  public static int parsePositiveInt(String source, String message) throws IOException {
    try {
      int value = Integer.parseInt(source);
      if (value < 0) {
        throw new NumberFormatException("negative value '" + value
            + "' for input \"" + source + '\"');
      }
      return value;
    } catch (NumberFormatException nfe) {
      throw new IOException(message, nfe);
    }
  }
}
