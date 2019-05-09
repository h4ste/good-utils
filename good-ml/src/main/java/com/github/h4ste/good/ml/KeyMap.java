package com.github.h4ste.good.ml;

public interface KeyMap<K extends Keyed> {
  K get(String key);
}
