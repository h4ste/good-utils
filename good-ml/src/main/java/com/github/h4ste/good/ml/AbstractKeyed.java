package com.github.h4ste.good.ml;

import java.util.Objects;

abstract class AbstractKeyed implements Keyed {
  protected final String key;

  protected AbstractKeyed(String key) {
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractKeyed that = (AbstractKeyed) o;
    return Objects.equals(key, that.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "Keyed[\'" + key + "\']";
  }
}
