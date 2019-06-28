package com.github.h4ste.collect;

public interface IntIdentifier<K> extends IntIdentityMapping<K> {
  /**
   * Adds an item to the identifier if and only if it has not already identified by this identifier
   * @param item item to add
   * @return id
   */
  int addItem(K item);

  /**
   * Retrieves the id for an item, or assigns a new id to the item and returns
   * that (thread-safe)
   * @param item item to identify
   * @return id
   */
  int getOrAddItem(K item);

  /**
   * Sets the item at a given id (thread-safe)
   * @param id identifier to update
   * @param item item to be assigned
   */
  void setItemAtId(int id, K item);
}
