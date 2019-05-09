package com.github.h4ste.good.util;

public class IdNotFoundException  extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public IdNotFoundException() {
  }

  public IdNotFoundException(String s) {
    super(s);
  }

  public IdNotFoundException(int id) {
    super("Id not found: " + id);
  }
}