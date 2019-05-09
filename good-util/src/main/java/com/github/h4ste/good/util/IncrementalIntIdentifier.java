package com.github.h4ste.good.util;

import it.unimi.dsi.fastutil.objects.Object2IntAVLTreeMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Assigns indexes to items, starting from zero.
 */
public class IncrementalIntIdentifier<K> extends AbstractIntIdentityMapping<K> implements IntIdentifier<K> {
  private static final long serialVersionUID = 0L;

  // Efficient thread-safety
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock read  = readWriteLock.readLock();
  private final Lock write = readWriteLock.writeLock();

  // Stores the mapping from IDs to items
  private final List<K> ids2items;

  protected IncrementalIntIdentifier(List<K> ids2items, Object2IntMap<K> items2ids) {
    super(items2ids);
    this.ids2items = ids2items;
  }

  protected IncrementalIntIdentifier() {
    this(new ObjectArrayList<>(), new Object2IntAVLTreeMap<>());
  }

  /** Creates a new mutable int identifier and initial a list of items.
   * The index of each item in the list will be its ID.
   * @param items List of items to identify
   * @return new mutable incremental IntIdentityMapping populated by given items
   */
  public static <T> IncrementalIntIdentifier<T> fromList(List<T> items) {
    final Object2IntMap<T> items2id = new Object2IntAVLTreeMap<>();
    final ObjectList<T> ids2items = new ObjectArrayList<>(items2id.size());
    for (int id = 0; id < items.size(); id++) {
      items2id.put(items.get(id), id);
      ids2items.set(id, items.get(id));
    }

    return new IncrementalIntIdentifier<T>(ids2items, items2id);
  }

  @Override
  public void forEach(EntryConsumer<K> consumer) {
    for (int i = 0, length = ids2items.size(); i < length; i++) {
      consumer.accept(this.ids2items.get(i), i);
    }
  }

  /**
   * Create a new, empty mutable incremental IntIdentityMapping
   * @param <T> Type of items to identify
   * @return new, empty mutable incremental IntIdentityMapping
   */
  public static <T> IncrementalIntIdentifier<T> create() {
    return new IncrementalIntIdentifier<>();
  }

  public ContiguousIntIdentityMapping<K> freeze() {
    // We are confident that only T' will be stored in our array, so we choose not to worry about
    // generic array creation warnings
    @SuppressWarnings("unchecked")
    final K[] ids2item = (K[]) new Object[ this.ids2items.size() ];
    for (int id = 0; id < ids2items.size(); id++) {
      ids2item[id] = this.ids2items.get(id);
    }
    return new ContiguousIntIdentityMapping<>(ids2item, items2id);
  }

  /** {@inheritDoc} */
  @Override
  public void setItemAtId(int id, K item) {
    read.lock();
    try {
      if (id < 0 || id >= items2id.size()) {
        throw new IdNotFoundException(id);
      }
    } finally {
      // Must release read lock before acquiring write lock
      read.unlock();
    }
    write.lock();
    try {
      // Recheck condition because the list may have changed
      if (id >= items2id.size()) {
        throw new IdNotFoundException(id);
      }
      items2id.put(item, id);
      ids2items.set(id, item);
    } finally {
      write.unlock();
    }
  }

  /** {@inheritDoc} */
  @Override
  public K getItem(int id) {
    read.lock();
    try {
      if (id < 0 || id >= items2id.size()) {
        throw new IdNotFoundException(id);
      }
      return ids2items.get(id);
    } finally {
      read.unlock();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getId(K item) {
    read.lock();
    try {
      return super.getId(item);
    } finally {
      read.unlock();
    }
  }


  /** {@inheritDoc} */
  @Override
  public int getOrAddItem(K item) {
    read.lock();
    try {
      if (items2id.containsKey(item)) {
        return items2id.getInt(item);
      }
    } finally {
      read.unlock();
    }
    return addItem(item);
  }

  /** {@inheritDoc} */
  @Override
  public int addItem(K item) {
    write.lock();
    try {
      final int id = ids2items.size();
      items2id.put(item, id);
      ids2items.add(item);
      return id;
    } finally {
      write.unlock();
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<K> getItems() {
    read.lock();
    try {
      return items2id.keySet();
    } finally {
      read.unlock();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    read.lock();
    try {
      return ids2items.size();
    } finally {
      read.unlock();
    }
  }
}