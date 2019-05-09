package com.github.h4ste.good.ml;

import java.util.Objects;

public final class FloatFeature extends AbstractKeyed implements NumericFeature, Feature<Float> {
  private final float value;

  FloatFeature(String key, float value) {
    super(key);
    this.value = value;
  }

  @Override
  public float getFloat() {
    return value;
  }

  @Override
  public double getDouble() {
    return (double) value;
  }

  @Override
  public int getInt() {
    return (int) value;
  }

  @Override
  public Float getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    FloatFeature that = (FloatFeature) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  @Override
  public String toString() {
    return "FloatFeature[" + key + " = " + value + ']';
  }
}
