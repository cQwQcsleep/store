package com.intellij.util.containers.hash;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
public class LinkedHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean accessOrder;
    private Entry<K, V> back;
    private int capacity;
    private final EqualityPolicy<? super K> hashingStrategy;
    private final float loadFactor;
    private int size;
    private Entry<K, V>[] table;
    private Entry<K, V> top;

    public static final class Entry<K, V> implements Map.Entry<K, V> {
        private Entry<K, V> hashNext;
        private final K key;
        private final int keyHash;
        private Entry<K, V> next;
        private Entry<K, V> previous;
        private V value;

        public Entry(K k, V v, int i) {
            this.key = k;
            this.keyHash = i;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = LinkedHashMap.this.get(entry.getKey());
            return obj2 != null && obj2.equals(entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.hash.LinkedHashMap.EntrySet.1
                {
                    LinkedHashMap linkedHashMap = LinkedHashMap.this;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    return nextEntry();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashMap.this.remove(((Map.Entry) obj).getKey()) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashMap.this.size;
        }
    }

    public final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.hash.LinkedHashMap.KeySet.1
                {
                    LinkedHashMap linkedHashMap = LinkedHashMap.this;
                }

                @Override // java.util.Iterator
                public K next() {
                    return (K) ((Entry) nextEntry()).key;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashMap.this.size;
        }
    }

    public abstract class LinkedHashIterator<T> implements Iterator<T> {
        private Entry<K, V> e;
        private Entry<K, V> last;

        private LinkedHashIterator() {
            this.e = LinkedHashMap.this.back;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.e != null;
        }

        public Entry<K, V> nextEntry() {
            Entry<K, V> entry = this.e;
            this.last = entry;
            this.e = ((Entry) entry).previous;
            return entry;
        }

        @Override // java.util.Iterator
        public void remove() {
            Entry<K, V> entry = this.last;
            if (entry == null) {
                g33.a();
            } else {
                LinkedHashMap.this.remove(((Entry) entry).key);
                this.last = null;
            }
        }
    }

    public final class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return LinkedHashMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.hash.LinkedHashMap.Values.1
                {
                    LinkedHashMap linkedHashMap = LinkedHashMap.this;
                }

                @Override // java.util.Iterator
                public V next() {
                    return (V) ((Entry) nextEntry()).value;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return LinkedHashMap.this.size;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 2) {
            objArr[0] = "hashingStrategy";
        } else {
            objArr[0] = "value";
        }
        objArr[1] = "com/intellij/util/containers/hash/LinkedHashMap";
        if (i != 2) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "put";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public LinkedHashMap(int i, float f, EqualityPolicy<? super K> equalityPolicy, boolean z) {
        if (equalityPolicy == null) {
            $$$reportNull$$$0(1);
        }
        this.loadFactor = f;
        this.hashingStrategy = equalityPolicy;
        this.accessOrder = z;
        clear(i);
    }

    private int hashKey(K k) {
        if (k == null) {
            return 0;
        }
        return this.hashingStrategy.getHashCode(k) & Integer.MAX_VALUE;
    }

    private void init(int i) {
        this.table = new Entry[HashUtil.adjustTableSize((int) (i / this.loadFactor))];
        this.back = null;
        this.top = null;
        this.capacity = i;
    }

    private void moveToTop(Entry<K, V> entry) {
        Entry<K, V> entry2;
        if (this.accessOrder && (entry2 = this.top) != entry) {
            Entry<K, V> entry3 = ((Entry) entry).previous;
            Entry entry4 = ((Entry) entry).next;
            ((Entry) entry3).next = entry4;
            if (entry4 != null) {
                entry4.previous = entry3;
            } else {
                this.back = entry3;
            }
            ((Entry) entry2).previous = entry;
            ((Entry) entry).next = entry2;
            ((Entry) entry).previous = null;
            this.top = entry;
        }
    }

    private void rehash(int i) {
        Entry<K, V>[] entryArr = new Entry[HashUtil.adjustTableSize((int) (i / this.loadFactor))];
        this.table = entryArr;
        this.capacity = i;
        int length = entryArr.length;
        for (Entry<K, V> entry = this.back; entry != null; entry = ((Entry) entry).previous) {
            int i2 = ((Entry) entry).keyHash % length;
            ((Entry) entry).hashNext = entryArr[i2];
            entryArr[i2] = entry;
        }
    }

    private void unlink(Entry<K, V> entry) {
        Entry<K, V> entry2 = ((Entry) entry).previous;
        Entry<K, V> entry3 = ((Entry) entry).next;
        if (entry2 != null) {
            ((Entry) entry2).next = entry3;
        } else {
            this.top = entry3;
        }
        if (entry3 != null) {
            ((Entry) entry3).previous = entry2;
        } else {
            this.back = entry2;
        }
        ((Entry) entry).previous = null;
        ((Entry) entry).next = null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (isEmpty()) {
            return;
        }
        clear(0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public void doRemoveEldestEntry() {
        remove(((Entry) this.back).key);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Object obj2;
        Entry<K, V>[] entryArr = this.table;
        int iHashKey = hashKey(obj);
        for (Entry<K, V> entry = entryArr[iHashKey % entryArr.length]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashKey && ((obj2 = ((Entry) entry).key) == obj || this.hashingStrategy.isEqual(obj2, obj))) {
                moveToTop(entry);
                return (V) ((Entry) entry).value;
            }
        }
        return null;
    }

    public K getFirstKey() {
        Entry<K, V> entry = this.back;
        if (entry != null) {
            return (K) ((Entry) entry).key;
        }
        return null;
    }

    public K getLastKey() {
        Entry<K, V> entry = this.top;
        if (entry != null) {
            return (K) ((Entry) entry).key;
        }
        return null;
    }

    public V getLastValue() {
        Entry<K, V> entry = this.top;
        if (entry != null) {
            return (V) ((Entry) entry).value;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        return new KeySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Object obj;
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        Entry<K, V>[] entryArr = this.table;
        int iHashKey = hashKey(k);
        int length = iHashKey % entryArr.length;
        for (Entry<K, V> entry = entryArr[length]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashKey && ((obj = ((Entry) entry).key) == k || this.hashingStrategy.isEqual(obj, k))) {
                moveToTop(entry);
                return entry.setValue(v);
            }
        }
        Entry<K, V> entry2 = new Entry<>(k, v, iHashKey);
        ((Entry) entry2).hashNext = entryArr[length];
        entryArr[length] = entry2;
        Entry<K, V> entry3 = this.top;
        ((Entry) entry2).next = entry3;
        if (entry3 != null) {
            ((Entry) entry3).previous = entry2;
        } else {
            this.back = entry2;
        }
        this.top = entry2;
        this.size++;
        Entry<K, V> entry4 = this.back;
        if (removeEldestEntry(entry4, ((Entry) entry4).key, ((Entry) this.back).value)) {
            doRemoveEldestEntry();
            return null;
        }
        int i = this.size;
        int i2 = this.capacity;
        if (i <= i2) {
            return null;
        }
        rehash((int) (i2 * 1.618034f));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Entry<K, V> entry;
        Object obj2;
        Object obj3;
        Entry<K, V>[] entryArr = this.table;
        int iHashKey = hashKey(obj);
        int length = iHashKey % entryArr.length;
        Entry<K, V> entry2 = entryArr[length];
        if (entry2 == null) {
            return null;
        }
        if (((Entry) entry2).keyHash == iHashKey && ((obj3 = ((Entry) entry2).key) == obj || this.hashingStrategy.isEqual(obj3, obj))) {
            entryArr[length] = ((Entry) entry2).hashNext;
        } else {
            while (true) {
                entry = ((Entry) entry2).hashNext;
                if (entry != null) {
                    if (((Entry) entry).keyHash == iHashKey && ((obj2 = ((Entry) entry).key) == obj || this.hashingStrategy.isEqual(obj2, obj))) {
                        break;
                    }
                    entry2 = entry;
                } else {
                    return null;
                }
            }
            ((Entry) entry2).hashNext = ((Entry) entry).hashNext;
            entry2 = entry;
        }
        unlink(entry2);
        this.size--;
        return (V) ((Entry) entry2).value;
    }

    public boolean removeEldestEntry(Map.Entry<K, V> entry, K k, V v) {
        return removeEldestEntry(entry);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        return new Values();
    }

    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return false;
    }

    private void clear(int i) {
        if (i < 5) {
            i = 5;
        }
        init(i);
        this.size = 0;
    }

    public LinkedHashMap(int i, boolean z) {
        this(i, 1.0f, EqualityPolicy.CANONICAL, z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LinkedHashMap(EqualityPolicy<? super K> equalityPolicy) {
        this(0, 1.0f, equalityPolicy, false);
        if (equalityPolicy == null) {
            $$$reportNull$$$0(0);
        }
    }

    public LinkedHashMap() {
        this(0, 1.0f, EqualityPolicy.CANONICAL, false);
    }
}
