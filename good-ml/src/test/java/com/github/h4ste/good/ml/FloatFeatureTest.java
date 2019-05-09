package com.github.h4ste.good.ml;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FloatFeatureTest {
  private FloatFeature f;

  @BeforeClass
  public void setUp() {
    this.f = Feature.fromFloat("5", 5f);
  }

  @Test
  public void testGetFloat() {
    assertEquals(f.getFloat(), 5f);
  }

  @Test
  public void testGetDouble() {
    assertEquals(f.getDouble(), 5d);
  }

  @Test
  public void testGetShort() {
    assertEquals(f.getShort(), (short) 5);
  }

  @Test
  public void testGetInt() {
    assertEquals(f.getInt(), 5);
  }

  @Test
  public void testGetLong() {
    assertEquals(f.getLong(), 5L);
  }

  @Test
  public void testGetValue() {
    assertEquals(f.getValue(), 5f);
  }

  @Test
  public void testGetKey() {
    assertEquals(f.getKey(), "5");
  }
}