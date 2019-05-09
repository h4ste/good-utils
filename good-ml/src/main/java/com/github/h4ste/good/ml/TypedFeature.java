package com.github.h4ste.good.ml;

import java.util.Objects;

public class TypedFeature<V> extends AbstractKeyed implements Feature<V> {
  protected final V value;

  TypedFeature(String key, V value) {
    super(key);
    this.value = value;
  }

  @Override
  public V getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    TypedFeature<?> that = (TypedFeature<?>) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), value);
  }

  @Override
  public String toString() {
    return "Feature[" +
        key + " = " +
        value + ']';
  }
}
