package com.intellij.util.containers.hash;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class LongLinkedHashMap<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean accessOrder;
    private Entry<V> back;
    private int capacity;
    private final float loadFactor;
    private int size;
    private Entry<V>[] table;
    private Entry<V> top;

    public static final class Entry<V> {
        private Entry<V> hashNext;
        private final long key;
        private final int keyHash;
        private Entry<V> next;
        private Entry<V> previous;
        private V value;

        public Entry(long j, V v, int i) {
            this.key = j;
            this.keyHash = i;
            this.value = v;
        }

        public long getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    public final class EntrySet extends AbstractSet<Entry<V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LongLinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object obj2 = LongLinkedHashMap.this.get(entry.getKey());
            return obj2 != null && obj2.equals(entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Entry<V>> iterator() {
            return new LinkedHashIterator() { // from class: com.intellij.util.containers.hash.LongLinkedHashMap.EntrySet.1
                {
                    LongLinkedHashMap longLinkedHashMap = LongLinkedHashMap.this;
                }

                @Override // java.util.Iterator
                public Entry<V> next() {
                    return nextEntry();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return (obj instanceof Entry) && LongLinkedHashMap.this.remove(((Entry) obj).getKey()) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LongLinkedHashMap.this.size;
        }
    }

    public abstract class LinkedHashIterator<T> implements Iterator<T> {
        private Entry<V> e;
        private Entry<V> last;

        private LinkedHashIterator() {
            this.e = LongLinkedHashMap.this.back;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.e != null;
        }

        public Entry<V> nextEntry() {
            Entry<V> entry = this.e;
            this.last = entry;
            this.e = ((Entry) entry).previous;
            return entry;
        }

        @Override // java.util.Iterator
        public void remove() {
            Entry<V> entry = this.last;
            if (entry == null) {
                g33.a();
            } else {
                LongLinkedHashMap.this.remove(((Entry) entry).key);
                this.last = null;
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/intellij/util/containers/hash/LongLinkedHashMap", "put"));
    }

    public LongLinkedHashMap(int i, float f, boolean z) {
        this.loadFactor = f;
        clear(i);
        this.accessOrder = z;
    }

    private static int hashCode(long j) {
        return Long.hashCode(j) & Integer.MAX_VALUE;
    }

    private void init(int i) {
        this.table = new Entry[HashCommon.arraySize(i, this.loadFactor)];
        this.back = null;
        this.top = null;
        this.capacity = i;
    }

    private void moveToTop(Entry<V> entry) {
        Entry<V> entry2;
        if (this.accessOrder && (entry2 = this.top) != entry) {
            Entry<V> entry3 = ((Entry) entry).previous;
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
        Entry<V>[] entryArr = new Entry[HashCommon.arraySize(i, this.loadFactor)];
        this.table = entryArr;
        this.capacity = i;
        int length = entryArr.length;
        for (Entry<V> entry = this.back; entry != null; entry = ((Entry) entry).previous) {
            int i2 = ((Entry) entry).keyHash % length;
            ((Entry) entry).hashNext = entryArr[i2];
            entryArr[i2] = entry;
        }
    }

    private void unlink(Entry<V> entry) {
        Entry<V> entry2 = ((Entry) entry).previous;
        Entry<V> entry3 = ((Entry) entry).next;
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

    public void clear() {
        if (isEmpty()) {
            return;
        }
        clear(0);
    }

    public void doRemoveEldestEntry() {
        remove(((Entry) this.back).key);
    }

    public Set<Entry<V>> entrySet() {
        return new EntrySet();
    }

    public V get(long j) {
        Entry<V>[] entryArr = this.table;
        int iHashCode = hashCode(j);
        for (Entry<V> entry = entryArr[iHashCode % entryArr.length]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashCode && ((Entry) entry).key == j) {
                moveToTop(entry);
                return (V) ((Entry) entry).value;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V put(long j, V v) {
        if (v == null) {
            $$$reportNull$$$0(0);
        }
        Entry<V>[] entryArr = this.table;
        int iHashCode = hashCode(j);
        int length = iHashCode % entryArr.length;
        for (Entry<V> entry = entryArr[length]; entry != null; entry = ((Entry) entry).hashNext) {
            if (((Entry) entry).keyHash == iHashCode && ((Entry) entry).key == j) {
                moveToTop(entry);
                return entry.setValue(v);
            }
        }
        Entry<V> entry2 = new Entry<>(j, v, iHashCode);
        ((Entry) entry2).hashNext = entryArr[length];
        entryArr[length] = entry2;
        Entry<V> entry3 = this.top;
        ((Entry) entry2).next = entry3;
        if (entry3 != null) {
            ((Entry) entry3).previous = entry2;
        } else {
            this.back = entry2;
        }
        this.top = entry2;
        this.size++;
        if (removeEldestEntry(this.back)) {
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

    public V remove(long j) {
        Entry<V>[] entryArr = this.table;
        int iHashCode = hashCode(j);
        int length = iHashCode % entryArr.length;
        Entry<V> entry = entryArr[length];
        if (entry == null) {
            return null;
        }
        if (((Entry) entry).keyHash != iHashCode || ((Entry) entry).key != j) {
            while (true) {
                Entry<V> entry2 = ((Entry) entry).hashNext;
                if (entry2 != null) {
                    if (((Entry) entry2).keyHash == iHashCode && ((Entry) entry2).key == j) {
                        ((Entry) entry).hashNext = ((Entry) entry2).hashNext;
                        entry = entry2;
                        break;
                    }
                    entry = entry2;
                } else {
                    return null;
                }
            }
        } else {
            entryArr[length] = ((Entry) entry).hashNext;
        }
        unlink(entry);
        this.size--;
        return (V) ((Entry) entry).value;
    }

    public boolean removeEldestEntry(Entry<V> entry) {
        return false;
    }

    public int size() {
        return this.size;
    }

    private void clear(int i) {
        if (i < 5) {
            i = 5;
        }
        init(i);
        this.size = 0;
    }
}
