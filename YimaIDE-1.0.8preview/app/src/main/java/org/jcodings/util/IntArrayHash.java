package org.jcodings.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class IntArrayHash<V> extends Hash<V> {
    public IntArrayHash() {
    }

    private int hashCode(int[] iArr) {
        int i;
        int i2;
        int length = iArr.length;
        if (length == 1) {
            return iArr[0];
        }
        if (length == 2) {
            i = iArr[0];
            i2 = iArr[1];
        } else if (length == 3) {
            i = iArr[0] + iArr[1];
            i2 = iArr[2];
        } else {
            if (length != 4) {
                int i3 = 0;
                for (int i4 : iArr) {
                    i3 += i4;
                }
                return i3;
            }
            i = iArr[0] + iArr[1] + iArr[2];
            i2 = iArr[3];
        }
        return i + i2;
    }

    public V delete(int... iArr) {
        int iHashValue = Hash.hashValue(hashCode(iArr));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        IntArrayHashEntry intArrayHashEntry = (IntArrayHashEntry) this.table[iBucketIndex];
        if (intArrayHashEntry == null) {
            return null;
        }
        if (intArrayHashEntry.hash == iHashValue && intArrayHashEntry.equals(iArr)) {
            this.table[iBucketIndex] = intArrayHashEntry.next;
            this.size--;
            intArrayHashEntry.remove();
            return intArrayHashEntry.value;
        }
        while (true) {
            Hash.HashEntry<V> hashEntry = intArrayHashEntry.next;
            if (hashEntry == null) {
                return null;
            }
            if (hashEntry.hash == iHashValue && intArrayHashEntry.equals(iArr)) {
                intArrayHashEntry.next = intArrayHashEntry.next.next;
                this.size--;
                hashEntry.remove();
                return hashEntry.value;
            }
            intArrayHashEntry = (IntArrayHashEntry) intArrayHashEntry.next;
        }
    }

    public V get(int... iArr) {
        int iHashValue = Hash.hashValue(hashCode(iArr));
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        Hash.HashEntry<V> hashEntry = hashEntryArr[Hash.bucketIndex(iHashValue, hashEntryArr.length)];
        while (true) {
            IntArrayHashEntry intArrayHashEntry = (IntArrayHashEntry) hashEntry;
            if (intArrayHashEntry == null) {
                return null;
            }
            if (intArrayHashEntry.hash == iHashValue && intArrayHashEntry.equals(iArr)) {
                return intArrayHashEntry.value;
            }
            hashEntry = intArrayHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public void init() {
        this.head = new IntArrayHashEntry();
    }

    public V put(int[] iArr, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(iArr));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V> hashEntry = this.table[iBucketIndex];
        while (true) {
            IntArrayHashEntry intArrayHashEntry = (IntArrayHashEntry) hashEntry;
            if (intArrayHashEntry == null) {
                Hash.HashEntry<V>[] hashEntryArr = this.table;
                hashEntryArr[iBucketIndex] = new IntArrayHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, iArr, this.head);
                this.size++;
                return null;
            }
            if (intArrayHashEntry.hash == iHashValue && intArrayHashEntry.equals(iArr)) {
                intArrayHashEntry.value = v;
                return v;
            }
            hashEntry = intArrayHashEntry.next;
        }
    }

    public void putDirect(int[] iArr, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(iArr));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        hashEntryArr[iBucketIndex] = new IntArrayHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, iArr, this.head);
        this.size++;
    }

    public IntArrayHash(int i) {
        super(i);
    }

    public static final class IntArrayHashEntry<V> extends Hash.HashEntry<V> {
        public final int[] key;

        public IntArrayHashEntry() {
            this.key = null;
        }

        public boolean equals(int[] iArr) {
            int[] iArr2 = this.key;
            if (iArr2 == iArr) {
                return true;
            }
            if (iArr2.length != iArr.length) {
                return false;
            }
            int length = iArr.length;
            if (length == 1) {
                return iArr2[0] == iArr[0];
            }
            if (length == 2) {
                return iArr2[0] == iArr[0] && iArr2[1] == iArr[1];
            }
            if (length == 3) {
                return iArr2[0] == iArr[0] && iArr2[1] == iArr[1] && iArr2[2] == iArr[2];
            }
            if (length == 4) {
                return iArr2[0] == iArr[0] && iArr2[1] == iArr[1] && iArr2[2] == iArr[2] && iArr2[3] == iArr[3];
            }
            for (int i = 0; i < iArr.length; i++) {
                if (this.key[i] != iArr[i]) {
                    return false;
                }
            }
            return true;
        }

        public IntArrayHashEntry(int i, Hash.HashEntry<V> hashEntry, V v, int[] iArr, Hash.HashEntry<V> hashEntry2) {
            super(i, hashEntry, v, hashEntry2);
            this.key = iArr;
        }
    }
}
