package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;

import java.util.Arrays;
import java.util.stream.Stream;

public class BooleanMultiFeature extends AbstractKeyed implements MultiFeature<Boolean>, Feature<boolean[]> {
  private final boolean values[];

  BooleanMultiFeature(String key, boolean[] values) {
    super(key);
    this.values = new boolean[ values.length ];
    System.arraycopy(values, 0, this.values, 0, values.length);
  }

  static BooleanMultiFeature fromBooleanCollection(String key, BooleanCollection collection) {
    return new BooleanMultiFeature(key, collection.toBooleanArray());
  }

  @Override
  public boolean[] getValue() {
    final boolean[] copy = new boolean[ values.length ];
    System.arraycopy(values, 0, copy, 0, values.length);
    return copy;
  }

  @Override
  public Stream<BooleanFeature> flatten() {
    return this.flattenAs(BooleanFeature::new);
  }

  @Override
  public BooleanCollection getValues() {
    return new BooleanArrayList(values);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    BooleanMultiFeature that = (BooleanMultiFeature) o;
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
    return "BooleanMultiFeature[" + key + " = " + Arrays.toString(values) + ']';
  }
}
