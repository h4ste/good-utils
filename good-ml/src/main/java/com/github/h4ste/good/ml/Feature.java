package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface Feature<V> extends Keyed {
  V getValue();

  static <K> Feature<K> fromObject(String key, K value) {
    return new TypedFeature<>(key, value);
  }

  static <K> MultiFeature<K> fromObjects(String key, K... value) {
    return fromObjects(key, Arrays.asList(value));
  }

  static <K> MultiFeature<K> fromObjects(String key, Collection<K> value) {
    return new TypedMultiFeature<>(key, value);
  }

  static <K> MultiFeature<K> fromObjectStream(String key, Stream<K> value) {
    return value.collect(FeatureCollectors.toListMultiFeature(key));
  }

  static IntFeature fromInt(String key, int value) {
    return new IntFeature(key, value);
  }

  static IntMultiFeature fromInts(String key, int... values) {
    return new IntMultiFeature(key, values);
  }

  static IntMultiFeature fromIntStream(String key, IntStream values) {
    return IntMultiFeature.fromIntCollection(
        key,
        values.collect(IntArrayList::new,
            (list, value)  -> list.add(value),
            IntList::addAll));
  }

  static FloatFeature fromFloat(String key, float value) {
    return new FloatFeature(key, value);
  }

  static FloatMultiFeature fromFloats(String key, float... values) {
    return new FloatMultiFeature(key, values);
  }

  static FloatMultiFeature fromFloatStream(String key, Stream<Float> values) {
    return FloatMultiFeature.fromFloatCollection(
        key,
        values.collect(
            FloatArrayList::new,
            (list, value) -> list.add(value.floatValue()),
            FloatArrayList::addAll));
  }

  static BooleanFeature fromBoolean(String key, boolean value) {
    return new BooleanFeature(key, value);
  }

  static BooleanMultiFeature fromBooleans(String key, boolean... values) {
    return new BooleanMultiFeature(key, values);
  }

  static BooleanMultiFeature fromBooleanStream(String key, Stream<Boolean> values) {
    return BooleanMultiFeature.fromBooleanCollection(
        key,
        values.collect(
            BooleanArrayList::new,
            (list, value) -> list.add(value.booleanValue()),
            BooleanList::addAll));
  }
}
