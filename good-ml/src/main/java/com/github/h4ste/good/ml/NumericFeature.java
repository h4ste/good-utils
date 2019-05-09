package com.github.h4ste.good.ml;

public interface NumericFeature {
  float getFloat();
  double getDouble();

//  byte getByte();
//  char getChar();

  default short getShort() {
    return (short) getInt();
  }

  int getInt();

  default long getLong() {
    return (long) getInt();
  }
}
