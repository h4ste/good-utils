package com.github.h4ste.good.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;

import java.io.Serializable;
import java.util.Set;

public abstract class AbstractIntIdentityMapping<K> implements Serializable, IntIdentityMapping<K> {
  // Stores the mapping from items to IDs
  protected final Object2IntMap<K> items2id;

  protected AbstractIntIdentityMapping(Object2IntMap<K> items2id) {
    this.items2id = items2id;
  }

  /** {@inheritDoc} */
  @Override
  public int getId(K item) {
    return items2id.getInt(item);
  }

  /** {@inheritDoc */
  @Override
  public Set<K> getItems() {
    return items2id.keySet();
  }

  /** {@inheritDoc} */
  @Override
  public void forEach(EntryConsumer<K> consumer) {
    Object2IntMaps.fastForEach(this.items2id, consumer);
  }
}
