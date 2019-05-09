package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.ints.IntArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

public class IntMultiFeatureTest {

  @DataProvider(name = "simple")
  public Object[][] createData() {
    return new Object[][]{
        {Feature.fromInts("a", 1, 2, 3), "a", new int[]{1, 2, 3}},
        {Feature.fromIntStream("b", IntStream.of(4, 5, 6)), "b", new int[]{4, 5, 6}},
        {IntMultiFeature.fromIntCollection("c", new IntArrayList(new int[]{7, 8, 9})), "c", new int[]{7, 8, 9}}
    };
  }

  @Test(dataProvider = "simple")
  public void testGetValue(IntMultiFeature f, String key, int[] value) {
    assertEquals(f.getValue(), value);
  }

  @Test(dataProvider = "simple")
  public void testFlatten(IntMultiFeature f, String key, int[] value) {
    assertEquals(f.flatten().iterator(),
        Arrays.stream(value).mapToObj(v -> Feature.fromInt(key, v)).iterator());
  }

  @Test(dataProvider = "simple")
  public void testGetValues(IntMultiFeature f, String key, int[] value) {
    assertEquals(f.getValues(), IntArrayList.wrap(value));
  }

  @Test(dataProvider = "simple")
  public void testIntValues(IntMultiFeature f, String key, int[] value) {
    assertEquals(f.intValues().iterator(),
        IntArrayList.wrap(value).iterator());
  }

  @Test(dataProvider = "simple")
  public void testGetKey(IntMultiFeature f, String key, int[] value) {
    assertEquals(f.getKey(), key);
  }
}