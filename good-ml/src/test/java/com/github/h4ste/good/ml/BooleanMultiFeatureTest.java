package com.github.h4ste.good.ml;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class BooleanMultiFeatureTest {

  @DataProvider(name = "simple")
  public Object[][] createData() {
    return new Object[][]{
        {Feature.fromBooleans("a", false, false, false),
            "a",
            new boolean[]{false, false, false}},
        {Feature.fromBooleanStream("b", Stream.of(false, true, false)),
            "b",
            new boolean[]{false, true, false}},
        {BooleanMultiFeature.fromBooleanCollection("c",
            new BooleanArrayList(new boolean[]{false, true, true})),
            "c",
            new boolean[]{false, true, true}},
    };
  }

  @Test(dataProvider = "simple")
  public void testGetValue(BooleanMultiFeature f, String key, boolean[] value) {
    assertEquals(f.getValue(), value);
  }

  @Test(dataProvider = "simple")
  public void testFlatten(BooleanMultiFeature f, String key, boolean[] value) {
    assertEquals(
        f.flatten().iterator(),
        BooleanArrayList.wrap(value).stream().map(v -> Feature.fromBoolean(key, v)).iterator());
  }

  @Test(dataProvider = "simple")
  public void testGetValues(BooleanMultiFeature f, String key, boolean[] value) {
    assertEquals(f.getValues(), BooleanArrayList.wrap(value));
  }

  @Test(dataProvider = "simple")
  public void testGetKey(BooleanMultiFeature f, String key, boolean[] value) {
    assertEquals(f.getKey(), key);
  }
}