package org.jcodings.util;

import org.jcodings.ascii.AsciiTables;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CaseInsensitiveBytesHash<V> extends Hash<V> {

    public class CaseInsensitiveBytesHashEntryIterator extends Hash<V>.HashEntryIterator {
        public CaseInsensitiveBytesHashEntryIterator() {
            super();
        }

        @Override // org.jcodings.util.Hash.HashEntryIterator, java.util.Iterator
        public CaseInsensitiveBytesHashEntry<V> next() {
            return (CaseInsensitiveBytesHashEntry) super.next();
        }
    }

    public CaseInsensitiveBytesHash() {
    }

    public static boolean caseInsensitiveEquals(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        if (i4 - i3 != i2 - i) {
            return false;
        }
        if (bArr2 == bArr) {
            return true;
        }
        while (i3 < i4) {
            byte[] bArr3 = AsciiTables.ToLowerCaseTable;
            int i5 = i3 + 1;
            int i6 = i + 1;
            if (bArr3[bArr2[i3] & 255] != bArr3[bArr[i] & 255]) {
                return false;
            }
            i3 = i5;
            i = i6;
        }
        return true;
    }

    public static int hashCode(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            i3 = (((i3 << 16) + (i3 << 6)) - i3) + AsciiTables.ToLowerCaseTable[bArr[i] & 255];
            i++;
        }
        return i3 + (i3 >> 5);
    }

    public V delete(byte[] bArr, int i, int i2) {
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        CaseInsensitiveBytesHashEntry caseInsensitiveBytesHashEntry = (CaseInsensitiveBytesHashEntry) this.table[iBucketIndex];
        if (caseInsensitiveBytesHashEntry == null) {
            return null;
        }
        if (caseInsensitiveBytesHashEntry.hash == iHashValue && caseInsensitiveBytesHashEntry.equals(bArr, i, i2)) {
            this.table[iBucketIndex] = caseInsensitiveBytesHashEntry.next;
            this.size--;
            caseInsensitiveBytesHashEntry.remove();
            return caseInsensitiveBytesHashEntry.value;
        }
        while (true) {
            Hash.HashEntry<V> hashEntry = caseInsensitiveBytesHashEntry.next;
            if (hashEntry == null) {
                return null;
            }
            if (hashEntry.hash == iHashValue && caseInsensitiveBytesHashEntry.equals(bArr, i, i2)) {
                caseInsensitiveBytesHashEntry.next = caseInsensitiveBytesHashEntry.next.next;
                this.size--;
                hashEntry.remove();
                return hashEntry.value;
            }
            caseInsensitiveBytesHashEntry = (CaseInsensitiveBytesHashEntry) caseInsensitiveBytesHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public CaseInsensitiveBytesHash<V>.CaseInsensitiveBytesHashEntryIterator entryIterator() {
        return new CaseInsensitiveBytesHashEntryIterator();
    }

    public V get(byte[] bArr, int i, int i2) {
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        Hash.HashEntry<V> hashEntry = hashEntryArr[Hash.bucketIndex(iHashValue, hashEntryArr.length)];
        while (true) {
            CaseInsensitiveBytesHashEntry caseInsensitiveBytesHashEntry = (CaseInsensitiveBytesHashEntry) hashEntry;
            if (caseInsensitiveBytesHashEntry == null) {
                return null;
            }
            if (caseInsensitiveBytesHashEntry.hash == iHashValue && caseInsensitiveBytesHashEntry.equals(bArr, i, i2)) {
                return caseInsensitiveBytesHashEntry.value;
            }
            hashEntry = caseInsensitiveBytesHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public void init() {
        this.head = new CaseInsensitiveBytesHashEntry();
    }

    public V put(byte[] bArr, int i, int i2, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V> hashEntry = this.table[iBucketIndex];
        while (true) {
            CaseInsensitiveBytesHashEntry caseInsensitiveBytesHashEntry = (CaseInsensitiveBytesHashEntry) hashEntry;
            if (caseInsensitiveBytesHashEntry == null) {
                Hash.HashEntry<V>[] hashEntryArr = this.table;
                hashEntryArr[iBucketIndex] = new CaseInsensitiveBytesHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, bArr, i, i2, this.head);
                this.size++;
                return null;
            }
            if (caseInsensitiveBytesHashEntry.hash == iHashValue && caseInsensitiveBytesHashEntry.equals(bArr, i, i2)) {
                caseInsensitiveBytesHashEntry.value = v;
                return v;
            }
            hashEntry = caseInsensitiveBytesHashEntry.next;
        }
    }

    public void putDirect(byte[] bArr, int i, int i2, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(hashCode(bArr, i, i2));
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        hashEntryArr[iBucketIndex] = new CaseInsensitiveBytesHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, bArr, i, i2, this.head);
        this.size++;
    }

    public CaseInsensitiveBytesHash(int i) {
        super(i);
    }

    public static final class CaseInsensitiveBytesHashEntry<V> extends Hash.HashEntry<V> {
        public final byte[] bytes;
        public final int end;
        public final int p;

        public CaseInsensitiveBytesHashEntry() {
            this.bytes = null;
            this.end = 0;
            this.p = 0;
        }

        public boolean equals(byte[] bArr, int i, int i2) {
            return CaseInsensitiveBytesHash.caseInsensitiveEquals(this.bytes, this.p, this.end, bArr, i, i2);
        }

        public CaseInsensitiveBytesHashEntry(int i, Hash.HashEntry<V> hashEntry, V v, byte[] bArr, int i2, int i3, Hash.HashEntry<V> hashEntry2) {
            super(i, hashEntry, v, hashEntry2);
            this.bytes = bArr;
            this.p = i2;
            this.end = i3;
        }
    }

    public static boolean caseInsensitiveEquals(byte[] bArr, byte[] bArr2) {
        return caseInsensitiveEquals(bArr, 0, bArr.length, bArr2, 0, bArr2.length);
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
