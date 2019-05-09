package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatCollection;

import java.util.Arrays;
import java.util.stream.Stream;

public final class FloatMultiFeature extends AbstractKeyed implements MultiFeature<Float>, Feature<float[]> {
  private final float values[];

  FloatMultiFeature(String key, float[] values) {
    super(key);
    this.values = new float[ values.length ];
    System.arraycopy(values, 0, this.values, 0, values.length);
  }

  static FloatMultiFeature fromFloatCollection(String key, FloatCollection collection) {
    return new FloatMultiFeature(key, collection.toFloatArray());
  }

  @Override
  public float[] getValue() {
    final float[] copy = new float[ values.length ];
    System.arraycopy(values, 0, copy, 0, values.length);
    return copy;
  }

  @Override
  public Stream<FloatFeature> flatten() {
    return this.flattenAs(FloatFeature::new);
  }

  @Override
  public FloatCollection getValues() {
    return new FloatArrayList(values);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    FloatMultiFeature that = (FloatMultiFeature) o;
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
    return "FloatMultiFeature[" + key + " = " + Arrays.toString(values) + ']';
  }
}
