package org.jcodings.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class BytesHash<V> extends Hash<V> {
    public BytesHash() {
    }

    public static int hashCode(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            int i4 = ((i3 << 16) + (i3 << 6)) - i3;
            i3 = bArr[i] + i4;
            i++;
        }
        return i3 + (i3 >> 5);
    }

    public V delete(byte[] bArr, int i, int i2) {
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        BytesHashEntry bytesHashEntry = (BytesHashEntry) this.table[iBucketIndex];
        if (bytesHashEntry == null) {
            return null;
        }
        if (bytesHashEntry.hash == iHashValue && bytesHashEntry.equals(bArr, i, i2)) {
            this.table[iBucketIndex] = bytesHashEntry.next;
            this.size--;
            bytesHashEntry.remove();
            return bytesHashEntry.value;
        }
        while (true) {
            Hash.HashEntry<V> hashEntry = bytesHashEntry.next;
            if (hashEntry == null) {
                return null;
            }
            if (hashEntry.hash == iHashValue && bytesHashEntry.equals(bArr, i, i2)) {
                bytesHashEntry.next = bytesHashEntry.next.next;
                this.size--;
                hashEntry.remove();
                return hashEntry.value;
            }
            bytesHashEntry = (BytesHashEntry) bytesHashEntry.next;
        }
    }

    public V get(byte[] bArr, int i, int i2) {
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        Hash.HashEntry<V> hashEntry = hashEntryArr[Hash.bucketIndex(iHashValue, hashEntryArr.length)];
        while (true) {
            BytesHashEntry bytesHashEntry = (BytesHashEntry) hashEntry;
            if (bytesHashEntry == null) {
                return null;
            }
            if (bytesHashEntry.hash == iHashValue && bytesHashEntry.equals(bArr, i, i2)) {
                return bytesHashEntry.value;
            }
            hashEntry = bytesHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public void init() {
        this.head = new BytesHashEntry();
    }

    public V put(byte[] bArr, int i, int i2, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V> hashEntry = this.table[iBucketIndex];
        while (true) {
            BytesHashEntry bytesHashEntry = (BytesHashEntry) hashEntry;
            if (bytesHashEntry == null) {
                Hash.HashEntry<V>[] hashEntryArr = this.table;
                hashEntryArr[iBucketIndex] = new BytesHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, bArr, i, i2, this.head);
                this.size++;
                return null;
            }
            if (bytesHashEntry.hash == iHashValue && bytesHashEntry.equals(bArr, i, i2)) {
                bytesHashEntry.value = v;
                return v;
            }
            hashEntry = bytesHashEntry.next;
        }
    }

    public void putDirect(byte[] bArr, int i, int i2, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        hashEntryArr[iBucketIndex] = new BytesHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, bArr, i, i2, this.head);
        this.size++;
    }

    public BytesHash(int i) {
        super(i);
    }

    public static final class BytesHashEntry<V> extends Hash.HashEntry<V> {
        public final byte[] bytes;
        public final int end;
        public final int p;

        public BytesHashEntry() {
            this.bytes = null;
            this.end = 0;
            this.p = 0;
        }

        public boolean equals(byte[] bArr, int i, int i2) {
            int i3 = this.end;
            int i4 = this.p;
            if (i3 - i4 != i2 - i) {
                return false;
            }
            if (this.bytes == bArr) {
                return true;
            }
            while (i4 < this.end) {
                int i5 = i4 + 1;
                byte b = this.bytes[i4];
                int i6 = i + 1;
                if (b != bArr[i]) {
                    return false;
                }
                i = i6;
                i4 = i5;
            }
            return true;
        }

        public BytesHashEntry(int i, Hash.HashEntry<V> hashEntry, V v, byte[] bArr, int i2, int i3, Hash.HashEntry<V> hashEntry2) {
            super(i, hashEntry, v, hashEntry2);
            this.bytes = bArr;
            this.p = i2;
            this.end = i3;
        }
    }

    public V get(byte[] bArr) {
        return get(bArr, 0, bArr.length);
    }

    public void putDirect(byte[] bArr, V v) {
        putDirect(bArr, 0, bArr.length, v);
    }

    public V put(byte[] bArr, V v) {
        return put(bArr, 0, bArr.length, v);
    }

    public V delete(byte[] bArr) {
        return delete(bArr, 0, bArr.length);
    }
}
