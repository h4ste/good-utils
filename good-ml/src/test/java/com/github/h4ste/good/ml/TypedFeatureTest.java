package com.github.h4ste.good.ml;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TypedFeatureTest {
  private Feature<String> s;
  private Feature<?> n;

  @BeforeClass
  public void setUp() {
    s = Feature.fromObject("name", "string");
    n = Feature.fromObject("name", null);
  }


  @Test
  public void testGetValue() {
    assertEquals(s.getValue(), "string");
    assertNull(n.getValue());

  }

  @Test
  public void testGetKey() {
    assertEquals(s.getKey(), "name");
    assertEquals(n.getKey(), "name");
  }
}