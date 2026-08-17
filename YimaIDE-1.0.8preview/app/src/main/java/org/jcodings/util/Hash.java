package org.jcodings.util;

import java.util.Iterator;
import org.jcodings.exception.InternalException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class Hash<V> implements Iterable<V> {
    private static final int HASH_SIGN_BIT_MASK = Integer.MAX_VALUE;
    private static final int INITIAL_CAPACITY;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final int MIN_CAPA = 8;
    private static final int[] PRIMES;
    protected HashEntry<V> head;
    protected int size;
    protected HashEntry<V>[] table;

    public class HashEntryIterator implements Iterator<HashEntry<V>>, Iterable<HashEntry<V>> {
        HashEntry<V> next;

        public HashEntryIterator() {
            this.next = Hash.this.head.after;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != Hash.this.head;
        }

        @Override // java.lang.Iterable
        public Iterator<HashEntry<V>> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public HashEntry<V> next() {
            HashEntry<V> hashEntry = this.next;
            this.next = hashEntry.after;
            return hashEntry;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new InternalException("not supported operation exception");
        }
    }

    public class HashIterator implements Iterator<V> {
        HashEntry<V> next;

        public HashIterator() {
            this.next = Hash.this.head.after;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != Hash.this.head;
        }

        @Override // java.util.Iterator
        public V next() {
            HashEntry<V> hashEntry = this.next;
            this.next = hashEntry.after;
            return hashEntry.value;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new InternalException("not supported operation exception");
        }
    }

    static {
        int[] iArr = {11, 19, 37, 67, 131, 283, 521, 1033, 2053, 4099, 8219, 16427, 32771, 65581, 131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259, 33554467, 67108879, 134217757, 268435459, 536870923, 1073741909, 0};
        PRIMES = iArr;
        INITIAL_CAPACITY = iArr[0];
    }

    public Hash(int i) {
        int i2 = 0;
        int i3 = 8;
        while (true) {
            int[] iArr = PRIMES;
            if (i2 >= iArr.length) {
                throw new InternalException("run out of polynomials");
            }
            if (i3 > i) {
                this.table = new HashEntry[iArr[i2]];
                init();
                return;
            } else {
                i2++;
                i3 <<= 1;
            }
        }
    }

    public static int bucketIndex(int i, int i2) {
        return i % i2;
    }

    public static int hashValue(int i) {
        return i & Integer.MAX_VALUE;
    }

    public final void checkResize() {
        int i = this.size;
        HashEntry<V>[] hashEntryArr = this.table;
        if (i != hashEntryArr.length) {
            return;
        }
        int length = hashEntryArr.length + 1;
        int i2 = 0;
        int i3 = 8;
        while (true) {
            int[] iArr = PRIMES;
            if (i2 >= iArr.length) {
                return;
            }
            if (i3 > length) {
                resize(iArr[i2]);
                return;
            } else {
                i2++;
                i3 <<= 1;
            }
        }
    }

    public Hash<V>.HashEntryIterator entryIterator() {
        return new HashEntryIterator();
    }

    public abstract void init();

    @Override // java.lang.Iterable
    public Iterator<V> iterator() {
        return new HashIterator();
    }

    public final void resize(int i) {
        HashEntry<V>[] hashEntryArr = this.table;
        HashEntry<V>[] hashEntryArr2 = new HashEntry[i];
        for (int i2 = 0; i2 < hashEntryArr.length; i2++) {
            HashEntry<V> hashEntry = hashEntryArr[i2];
            hashEntryArr[i2] = null;
            while (hashEntry != null) {
                HashEntry<V> hashEntry2 = hashEntry.next;
                int iBucketIndex = bucketIndex(hashEntry.hash, i);
                hashEntry.next = hashEntryArr2[iBucketIndex];
                hashEntryArr2[iBucketIndex] = hashEntry;
                hashEntry = hashEntry2;
            }
        }
        this.table = hashEntryArr2;
    }

    public final int size() {
        return this.size;
    }

    public static class HashEntry<V> {
        protected HashEntry<V> after;
        protected HashEntry<V> before;
        final int hash;
        protected HashEntry<V> next;
        public V value;

        public HashEntry(int i, HashEntry<V> hashEntry, V v, HashEntry<V> hashEntry2) {
            this.hash = i;
            this.next = hashEntry;
            this.value = v;
            this.after = hashEntry2;
            HashEntry<V> hashEntry3 = hashEntry2.before;
            this.before = hashEntry3;
            hashEntry3.after = this;
            this.after.before = this;
        }

        public int getHash() {
            return this.hash;
        }

        public void remove() {
            HashEntry<V> hashEntry = this.before;
            hashEntry.after = this.after;
            this.after.before = hashEntry;
        }

        public HashEntry() {
            this.hash = 0;
            this.after = this;
            this.before = this;
        }
    }

    public Hash() {
        this.table = new HashEntry[INITIAL_CAPACITY];
        init();
    }
}
