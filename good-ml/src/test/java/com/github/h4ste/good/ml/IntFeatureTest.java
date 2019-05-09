package com.github.h4ste.good.ml;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class IntFeatureTest {
  private IntFeature f;

  @BeforeClass
  public void setUp() {
    this.f = Feature.fromInt("5", 5);
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
    assertSame(f.getValue(), 5);
  }

  @Test
  public void testGetKey() {
    assertEquals(f.getKey(), "5");
  }
}