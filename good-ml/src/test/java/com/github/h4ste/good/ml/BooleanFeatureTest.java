package com.github.h4ste.good.ml;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

public class BooleanFeatureTest {
  private BooleanFeature t;
  private BooleanFeature f;

  @BeforeClass
  public void setUp() {
    this.t = Feature.fromBoolean("true", true);
    this.f = Feature.fromBoolean("false", false);
  }

  @Test
  public void testGetFloat() {
    assertEquals(t.getFloat(), 1f);
    assertEquals(f.getFloat(), 0f);
  }

  @Test
  public void testGetDouble() {
    assertEquals(t.getDouble(), 1d);
    assertEquals(f.getDouble(), 0d);
  }

  @Test
  public void testGetShort() {
    assertEquals(t.getShort(), (short) 1);
    assertEquals(f.getShort(), (short) 0);
  }

  @Test
  public void testGetInt() {
    assertEquals(t.getInt(), 1);
    assertEquals(f.getInt(), 0);
  }

  @Test
  public void testGetLong() {
    assertEquals(t.getLong(), 1L);
    assertEquals(f.getLong(), 0L);
  }

  @Test
  public void testGetValue() {
    assertSame(t.getValue(), Boolean.TRUE);
    assertSame(f.getValue(), Boolean.FALSE);
  }

  @Test
  public void testGetBoolean() {
    assertTrue(t.getBoolean());
    assertFalse(f.getBoolean());
  }

  @Test
  public void testGetKey() {
    assertEquals(t.getKey(), "true");
    assertEquals(f.getKey(), "false");
  }
}