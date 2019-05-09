package com.github.h4ste.good.ml;

import java.util.Objects;

public final class BooleanFeature extends AbstractKeyed implements NumericFeature, Feature<Boolean> {
  boolean value;

  BooleanFeature(String key, boolean value) {
    super(key);
    this.value = value;
  }

  @Override
  public float getFloat() {
    return value ? 1f : 0f;
  }

  @Override
  public double getDouble() {
    return value ? 1d : 0d;
  }

  @Override
  public int getInt() {
    return value ? 1 : 0;
  }

  @Override
  public Boolean getValue() {
    return value;
  }

  public boolean getBoolean() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    BooleanFeature that = (BooleanFeature) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  @Override
  public String toString() {
    return "BooleanFeature[" + key + " = " + value + ']';
  }
}
