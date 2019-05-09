package com.github.h4ste.good.util;

import it.unimi.dsi.fastutil.objects.Object2IntAVLTreeMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * Assigns indexes to items, starting from zero.
 */
public class ContiguousIntIdentityMapping<T> extends AbstractIntIdentityMapping<T> {
  private static final long serialVersionUID = 0L;

  // Stores the mapping from IDs to items
  protected final T[] ids2item;

  protected ContiguousIntIdentityMapping(T[] ids2item, Object2IntMap<T> items2id) {
    super(Object2IntMaps.unmodifiable(items2id));
    // Defensive copy
    this.ids2item = Arrays.copyOf(ids2item, ids2item.length);
  }

  /** Create a new mapping of items to IDs.  The index of each item in the list
   * will be its ID.
   * @param items
   */
  public static <T> ContiguousIntIdentityMapping<T> fromList(List<T> items) {
    final Object2IntMap<T> items2id = new Object2IntAVLTreeMap<>();
    // We are confident that only T' will be stored in our array, so we choose not to worry about
    // generic array creation warnings
    @SuppressWarnings("unchecked")
    final T[] ids2item = (T[]) new Object[ items.size() ];
    for (int id = 0; id < items.size(); id++) {
      items2id.put(items.get(id), id);
    }
    return new ContiguousIntIdentityMapping<>(ids2item, items2id);
  }

  public static ContiguousIntIdentityMapping<String> fromFile(final Path path) throws IOException {
    return fromFile(path, Function.identity());
  }

  public static <K> ContiguousIntIdentityMapping<K> fromFile(final Path path,
                                                             final Function<String, K> converter)
      throws IOException {
    final ObjectArrayList<K> ids2items = new ObjectArrayList<>();
    final Object2IntMap<K> items2ids = new Object2IntAVLTreeMap<>();
    try {
      if (Files.isReadable(path)) {
        Files.lines(path).forEachOrdered(line -> {
          try {
            // Skip blank lines
            if (line.isEmpty()) {
              return;
            }

            int delimStart = -1;
            int delimEnd = -1;
            for (int i = 0; i < line.length(); i++) {
              // Look for first whitespace character as delimiter
              if (Character.isWhitespace(line.codePointAt(i)) && delimStart == -1) {
                delimStart = i;
              }
              // Stop when we hit a non-whitespace character after finding a whitespace character
              else if (delimStart != -1) {
                // Keep track of the end of consecutive whitespace
                delimEnd = i;
              }
            }

            if (delimStart < 0 || delimEnd == line.length() - 1) {
              throw new IOException("Malformed delimiter");
            }

            final int id = Integer.parseUnsignedInt(line.substring(0, delimStart));
            if (id != ids2items.size()) {
              throw new IOException("Id '" + id + "' is not contiguous");
            }

            final K value = converter.apply(line.substring(delimEnd));
            ids2items.add(value);
            items2ids.put(value, id);
          } catch (IOException e) {
            throw new UncheckedIOException(e);
          }
        });
      }
    } catch (UncheckedIOException e) {
      throw e.getCause();
    } catch (NumberFormatException e) {
      throw new IOException("Failed to parse id", e);
    }

    // We are confident that only K will be stored in our array, so we choose not to worry about
    // generic array creation warnings
    @SuppressWarnings("unchecked")
    K[] ids2itemsArray = (K[]) ids2items.toArray();
    return new ContiguousIntIdentityMapping<>(ids2itemsArray, items2ids);
  }

  /** {@inheritDoc} */
  @Override
  public T getItem(int id) {
    if (id < 0 || id >=  ids2item.length) {
      throw new IdNotFoundException(id);
    }
    return ids2item[id];
  }
}