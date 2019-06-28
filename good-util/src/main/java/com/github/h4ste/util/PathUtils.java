package com.github.h4ste.util;

import java.nio.file.Path;

public final class PathUtils {

  private PathUtils() {
    throw new UnsupportedOperationException("Cannot instantiate utility class");
  }

  public static Path removeExtension(Path path) {
    final Path parent = path.getParent();
    final String filename = path.getFileName().toString();
    final int extIndex = filename.lastIndexOf('.');
    assert extIndex > -1;
    final String newFilename = filename.substring(0, extIndex);
    return parent.resolve(newFilename);
  }

  public static Path changeExtension(Path path,
      @SuppressWarnings("SameParameterValue") String extension) {
    final Path parent = path.getParent();
    final String filename = path.getFileName().toString();
    final int extIndex = filename.lastIndexOf('.');
    assert extIndex > -1;
    final String newFilename = filename.substring(0, extIndex) + extension;
    return parent.resolve(newFilename);
  }

  public static Path changeOrAddExtension(Path path,
      @SuppressWarnings("SameParameterValue") String extension) {
    final Path parent = path.getParent();
    final String filename = path.getFileName().toString();
    final int extIndex = filename.lastIndexOf('.');
    final String newFilename;
    if (extIndex == -1) {
      newFilename = filename + extension;
    } else {
      newFilename = filename.substring(0, extIndex) + extension;
    }
    return parent.resolve(newFilename);
  }
}
