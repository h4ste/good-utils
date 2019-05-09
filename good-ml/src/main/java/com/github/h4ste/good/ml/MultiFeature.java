package com.github.h4ste.good.ml;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public interface MultiFeature<V> extends Keyed {
  Collection<V> getValues();

  default Stream<V> values() {
    return getValues().stream();
  }

  default Stream<? extends Feature<V>> flatten() {
    return this.flattenAs(TypedFeature::new);
  }

  default <K extends Feature<V>> Stream<K> flatMapToFeature(BiFunction<String, ? super V, ? extends Stream<? extends K>> function) {
    return values().flatMap(value -> function.apply(this.getKey(), value));
  }

  default <K extends Feature<V>> Stream<K> flattenAs(BiFunction<String, ? super V, ? extends K> function) {
    return values().map(value -> function.apply(this.getKey(), value));
  }
}
