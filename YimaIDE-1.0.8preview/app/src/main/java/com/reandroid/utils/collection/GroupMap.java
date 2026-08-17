package com.reandroid.utils.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GroupMap<K, V> {
    private final int initialSize;
    private Map<K, Object> itemsMap;

    public static class Groups<T> extends ArrayCollection<T> {
        public void addIfAbsent(T t) {
            if (containsExact(t)) {
                return;
            }
            add(t);
        }
    }

    public GroupMap(int i) {
        this.initialSize = i;
        this.itemsMap = i > 0 ? new HashMap(i) : new HashMap();
    }

    private void initializeEmpty(int i) {
        if (this.itemsMap.size() != 0) {
            return;
        }
        int i2 = this.initialSize;
        if (i < i2) {
            i = i2;
        }
        this.itemsMap = i > 0 ? new HashMap(i) : new HashMap();
    }

    public void clear() {
        Map<K, Object> map = this.itemsMap;
        if (map.size() == 0) {
            return;
        }
        map.clear();
        this.itemsMap = this.initialSize > 0 ? new HashMap(this.initialSize) : new HashMap();
    }

    public boolean contains(K k) {
        return this.itemsMap.containsKey(k);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V get(K k) {
        V v = (V) this.itemsMap.get(k);
        if (v == 0) {
            return null;
        }
        return v instanceof Groups ? (V) ((Groups) v).getFirst() : v;
    }

    public Iterator<V> getAll(K k) {
        Object obj = this.itemsMap.get(k);
        if (obj == null) {
            return EmptyIterator.of();
        }
        return obj instanceof Groups ? (Iterator<V>) ((Groups) obj).iterator() : SingleIterator.of(obj);
    }

    public void put(K k, V v) {
        Groups groups;
        if (k == null || v == null) {
            return;
        }
        Map<K, Object> map = this.itemsMap;
        Object obj = map.get(k);
        if (obj == null) {
            map.put(k, v);
            return;
        }
        if (obj instanceof Groups) {
            groups = (Groups) obj;
        } else {
            Groups groups2 = new Groups();
            groups2.add(obj);
            map.remove(k);
            groups = groups2;
        }
        groups.addIfAbsent(v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void putAll(Collection<? extends V> collection, Function<? super V, K> function) {
        if (collection.isEmpty()) {
            return;
        }
        initializeEmpty(collection.size());
        for (V v : collection) {
            put(function.apply(v), v);
        }
    }

    public int size() {
        return this.itemsMap.size();
    }

    public String toString() {
        return "size = " + size();
    }

    public void updateKey(K k, K k2) {
        Map<K, Object> map;
        Object objRemove;
        if (k == null || k2 == null || k == k2 || (objRemove = (map = this.itemsMap).remove(k)) == null) {
            return;
        }
        map.put(k2, objRemove);
    }

    public GroupMap() {
        this(0);
    }
}
