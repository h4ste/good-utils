package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntCollection;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntMultiFeature extends AbstractKeyed implements MultiFeature<Integer>, Feature<int[]> {
  private final int values[];

  IntMultiFeature(String key, int[] values) {
    super(key);
    this.values = new int[ values.length ];
    System.arraycopy(values, 0, this.values, 0, values.length);
  }

  static IntMultiFeature fromIntCollection(String key, IntCollection collection) {
    return new IntMultiFeature(key, collection.toIntArray());
  }

  @Override
  public int[] getValue() {
    final int[] copy = new int[ values.length ];
    System.arraycopy(values, 0, copy, 0, values.length);
    return copy;
  }

  @Override
  public Stream<IntFeature> flatten() {
    return this.flattenAs(IntFeature::new);
  }

  @Override
  public IntCollection getValues() {
    return new IntArrayList(values);
  }

  public IntStream intValues() {
    return Arrays.stream(values);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    IntMultiFeature that = (IntMultiFeature) o;
    return Arrays.equals(values, that.values);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode(values);
    return result;
  }

  @Override
  public String toString() {
    return "IntMultiFeature[" + key + " = " + Arrays.toString(values) + ']';
  }
}
