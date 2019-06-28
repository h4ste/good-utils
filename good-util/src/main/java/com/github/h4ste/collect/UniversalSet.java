package com.github.h4ste.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class UniversalSet<T> implements Set<T> {

  @Override
  public int size() {
    return Integer.MAX_VALUE;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return true;
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean add(T t) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }
}
