package org.jcodings.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IntHash<V> extends Hash<V> {
    public IntHash() {
    }

    public V delete(int i) {
        int iHashValue = Hash.hashValue(i);
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        IntHashEntry intHashEntry = (IntHashEntry) hashEntryArr[iBucketIndex];
        if (intHashEntry == null) {
            return null;
        }
        if (intHashEntry.hash == iHashValue) {
            hashEntryArr[iBucketIndex] = intHashEntry.next;
            this.size--;
            intHashEntry.remove();
            return intHashEntry.value;
        }
        while (true) {
            Hash.HashEntry<V> hashEntry = intHashEntry.next;
            if (hashEntry == null) {
                return null;
            }
            if (hashEntry.hash == iHashValue && intHashEntry == Integer.valueOf(i)) {
                intHashEntry.next = intHashEntry.next.next;
                this.size--;
                hashEntry.remove();
                return hashEntry.value;
            }
            intHashEntry = (IntHashEntry) intHashEntry.next;
        }
    }

    public V get(int i) {
        int iHashValue = Hash.hashValue(i);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        Hash.HashEntry<V> hashEntry = hashEntryArr[Hash.bucketIndex(iHashValue, hashEntryArr.length)];
        while (true) {
            IntHashEntry intHashEntry = (IntHashEntry) hashEntry;
            if (intHashEntry == null) {
                return null;
            }
            if (intHashEntry.hash == iHashValue) {
                return intHashEntry.value;
            }
            hashEntry = intHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public void init() {
        this.head = new IntHashEntry();
    }

    public V put(int i, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(i);
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V> hashEntry = this.table[iBucketIndex];
        while (true) {
            IntHashEntry intHashEntry = (IntHashEntry) hashEntry;
            if (intHashEntry == null) {
                Hash.HashEntry<V>[] hashEntryArr = this.table;
                hashEntryArr[iBucketIndex] = new IntHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, this.head);
                this.size++;
                return null;
            }
            if (intHashEntry.hash == iHashValue) {
                intHashEntry.value = v;
                return v;
            }
            hashEntry = intHashEntry.next;
        }
    }

    public void putDirect(int i, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(i);
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        hashEntryArr[iBucketIndex] = new IntHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, this.head);
        this.size++;
    }

    public static final class IntHashEntry<V> extends Hash.HashEntry<V> {
        public IntHashEntry(int i, Hash.HashEntry<V> hashEntry, V v, Hash.HashEntry<V> hashEntry2) {
            super(i, hashEntry, v, hashEntry2);
        }

        public IntHashEntry() {
        }
    }

    public IntHash(int i) {
        super(i);
    }
}
