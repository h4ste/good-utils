package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class FloatMultiFeatureTest {

  @DataProvider(name = "simple")
  public Object[][] createData() {
    return new Object[][]{
        {Feature.fromFloats("a", 1f, 2f, 3f),
            "a",
            new float[]{1f, 2f, 3f}},
        {Feature.fromFloatStream("b", Stream.of(4f, 5f, 6f)),
            "b",
            new float[]{4f, 5f, 6f}},
        {FloatMultiFeature.fromFloatCollection("c",
            new FloatArrayList(new float[]{7f, 8f, 9f})),
            "c",
            new float[]{7f, 8f, 9f}}
    };
  }

  @Test(dataProvider = "simple")
  public void testGetValue(FloatMultiFeature f, String key, float[] value) {
    assertEquals(f.getValue(), value);
  }

  @Test(dataProvider = "simple")
  public void testFlatten(FloatMultiFeature f, String key, float[] value) {
    assertEquals(f.flatten().iterator(),
        FloatArrayList.wrap(value).stream().map(v -> Feature.fromFloat(key, v)).iterator());
  }

  @Test(dataProvider = "simple")
  public void testGetValues(FloatMultiFeature f, String key, float[] value) {
    assertEquals(f.getValues(), FloatArrayList.wrap(value));
  }

  @Test(dataProvider = "simple")
  public void testGetKey(FloatMultiFeature f, String key, float[] value) {
    assertEquals(f.getKey(), key);
  }
}