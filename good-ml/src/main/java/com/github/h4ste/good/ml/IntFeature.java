package com.github.h4ste.good.ml;

import java.util.Objects;

public final class IntFeature extends AbstractKeyed implements NumericFeature, Feature<Integer> {
  private final int value;

  IntFeature(String key, int value) {
    super(key);
    this.value = value;
  }

  @Override
  public float getFloat() {
    return (float) value;
  }

  @Override
  public double getDouble() {
    return (double) value;
  }

  @Override
  public int getInt() {
    return value;
  }

  @Override
  public Integer getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    IntFeature that = (IntFeature) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  @Override
  public String toString() {
    return "IntFeature[" + key + " = " + value + ']';
  }
}
