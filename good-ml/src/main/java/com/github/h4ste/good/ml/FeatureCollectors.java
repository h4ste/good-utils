package com.github.h4ste.good.ml;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class FeatureCollectors {
  private FeatureCollectors() {
  }

  static <K> Collector<K, ?, MultiFeature<K>> toListMultiFeature(String key) {
    return Collectors.collectingAndThen(Collectors.toList(),
        list -> new TypedMultiFeature<>(key, list));
  }

  static <K> Collector<K, ?, MultiFeature<K>> toSetMultiFeature(String key) {
    return Collectors.collectingAndThen(Collectors.toSet(),
        set -> new TypedMultiFeature<>(key, set));
  }

  static <K, C extends Collection<K>> Collector<K, ?, TypedMultiFeature<K, C>> toTypedMultiFeature
      (String key, Supplier<C> constructor) {
    return Collectors.collectingAndThen(Collectors.toCollection(constructor),
        collection -> new TypedMultiFeature<>(key, collection));
  }
}
