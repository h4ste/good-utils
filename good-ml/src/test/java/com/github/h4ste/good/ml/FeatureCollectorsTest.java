package com.github.h4ste.good.ml;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class FeatureCollectorsTest {

  @DataProvider(name = "simple")
  public Object[][] createData() {
    return new Object[][]{
        {"list", Arrays.asList(3, 2, 2, 3, 1, 1)},
        {"set", Stream.of("A", "B", "C").collect(Collectors.toSet())},
        {"singleton#null", Collections.singleton(null)},
        {"empty", Collections.emptySortedSet()}
    };
  }


  @Test(dataProvider = "simple")
  public <K> void testToListMultiFeature(String key, Collection<K> values) {
    MultiFeature<K> collected = values.stream().collect(FeatureCollectors.toListMultiFeature(key));
    MultiFeature<K> created = Feature.fromObjects(key, new ArrayList<>(values));
    assertEquals(collected, created);
  }

  @Test(dataProvider = "simple")
  public <K> void testToSetMultiFeature(String key, Collection<K> values) {
    MultiFeature<K> collected = values.stream().collect(FeatureCollectors.toSetMultiFeature(key));
    MultiFeature<K> created = Feature.fromObjects(key, new HashSet<>(values));
    assertEquals(collected, created);
  }

  private <K, C extends Collection<K>> void testToTypedMultiFeature(String key,
                                                                    C values,
                                                                    Supplier<C> constructor,
                                                                    Function<Collection<K>, C> copyConstructor) {
    MultiFeature<K> collected = values.stream().collect(FeatureCollectors.toTypedMultiFeature(key,
        constructor));
    TypedMultiFeature<K, C> created = new TypedMultiFeature<>(key, copyConstructor.apply(values));
    assertEquals(collected, created);
  }


  @Test(dataProvider = "simple")
  public <K> void testToTypedMultiFeatureWithLinkedHashSet(String key, Collection<K> values) {
    testToTypedMultiFeature(key, values, LinkedHashSet::new, LinkedHashSet::new);
  }

  @Test(dataProvider = "simple")
  public <K> void testToTypedMultiFeatureWithLinkedList(String key, Collection<K> values) {
    testToTypedMultiFeature(key, values, LinkedList::new, LinkedList::new);
  }

  @Test(dataProvider = "simple")
  public <K> void testToTypedMultiFeatureWithHashSet(String key, Collection<K> values) {
    testToTypedMultiFeature(key, values, HashSet::new, HashSet::new);
  }
}