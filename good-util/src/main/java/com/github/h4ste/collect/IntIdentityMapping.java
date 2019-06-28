package com.github.h4ste.collect;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

public interface IntIdentityMapping<K> {

  @FunctionalInterface
  interface EntryConsumer<K> extends ObjIntConsumer<K>, Consumer<Map.Entry<K, Integer>> {
    @Override
    default void accept(Map.Entry<K, Integer> entry) {
      accept(entry.getKey(), entry.getValue());
    }
  }

  int getId(K item);
  K getItem(int id);
  Set<K> getItems();
  void forEach(final EntryConsumer<K> consumer);

  /**
   * Get the number of items currently being tracked by this identifier.  This
   * can change over time.
   */
  default int size() {
    return getItems().size();
  }

  default Optional<K> getItemOptionally(int id) {
    try {
      return Optional.of(getItem(id));
    } catch (IdNotFoundException e) {
      return Optional.empty();
    }
  }

  default void toFile(final Path path) throws IOException {
    try (final BufferedWriter writer = Files.newBufferedWriter(path)) {
      try {
        this.forEach((item, id) -> {
          try {
            writer.append(Integer.toString(id))
                .append('\t')
                .append(Objects.toString(item));
            writer.newLine();
          } catch (IOException e) {
            throw new UncheckedIOException(e);
          }
        });
      } catch (UncheckedIOException e) {
        throw e.getCause();
      }
    }
  }
}
