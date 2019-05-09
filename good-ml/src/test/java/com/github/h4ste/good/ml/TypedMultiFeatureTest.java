package com.github.h4ste.good.ml;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class TypedMultiFeatureTest {

  @DataProvider(name = "simple")
  public Object[][] createData() {
    return new Object[][]{
        {Feature.fromObjects("fromObjects#varargs", "a", "b", "c"),
            "fromObjects#varargs",
            Arrays.asList("a", "b", "c")},
        {Feature.fromObjects("fromObjects#Collection", Arrays.asList("d", "e", "f")),
            "fromObjects#Collection",
            Arrays.asList("d", "e", "f")},
        {Feature.fromObjectStream("fromObjectStream", Stream.of("g", "h", "i")),
            "fromObjectStream",
            Arrays.asList("g", "h", "i")},
        {new TypedMultiFeature<>("constructor#List",
            Arrays.asList("j", "k", "l", null)),
            "constructor#List",
            Arrays.asList("j", "k", "l", null)},
        {new TypedMultiFeature<>("constructor#Set",
            Stream.of("j", "k", "l", null, "l", "k").collect(Collectors.toSet())),
            "constructor#Set",
            Stream.of("j", "k", "l", null, "l", "k").collect(Collectors.toSet())},
    };
  }

  @Test(dataProvider = "simple")
  public void testGetValue(TypedMultiFeature<String, ?> f, String key, Collection<String> values) {
    assertEquals(f.getValue(), values);
  }

  @Test(dataProvider = "simple")
  public void testFlatten(MultiFeature<String> f, String key, Collection<String> values) {
    assertEquals(
        f.flatten().iterator(),
        values.stream().map(v -> Feature.fromObject(key, v)).iterator());
  }

  @Test(dataProvider = "simple")
  public void testGetValues(MultiFeature<String> f, String key, Collection<String> values) {
    assertEquals(f.getValues(), values);
  }

  @Test(dataProvider = "simple")
  public void testGetKey(MultiFeature<String> f, String key, Collection<String> values) {
    assertEquals(f.getKey(), key);
  }
}