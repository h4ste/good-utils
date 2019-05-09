package com.github.h4ste.good.ml;

import java.util.Collection;
import java.util.stream.Stream;

public class TypedMultiFeature<V, C extends Collection<V>>
    extends TypedFeature<C> implements MultiFeature<V> {

  public TypedMultiFeature(String key, C values) {
    super(key, values);
  }

  @Override
  public Collection<V> getValues() {
    return value;
  }

  @Override
  public Stream<V> values() {
    return value.stream();
  }

  @Override
  public Stream<Feature<V>> flatten() {
    return flattenAs(TypedFeature::new);
  }

  @Override
  public String toString() {
    return "MultiFeature[" +
        key + " = " +
        value + ']';
  }
}
