package com.fasterxml.aalto.in;

import com.fasterxml.aalto.util.NameTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CharBasedPNameTable extends NameTable {
    protected Bucket[] _buckets;
    protected boolean _dirty = true;
    protected int _indexMask;
    protected int _size;
    protected int _sizeThreshold;
    protected PNameC[] _symbols;

    public static final class Bucket {
        private final Bucket mNext;
        private final PNameC mSymbol;

        public Bucket(PNameC pNameC, Bucket bucket) {
            this.mSymbol = pNameC;
            this.mNext = bucket;
        }

        public PNameC find(char[] cArr, int i, int i2, int i3) {
            do {
                PNameC pNameC = this.mSymbol;
                if (pNameC.equalsPName(cArr, i, i2, i3)) {
                    return pNameC;
                }
                this = this.getNext();
            } while (this != null);
            return null;
        }

        public Bucket getNext() {
            return this.mNext;
        }

        public PNameC getSymbol() {
            return this.mSymbol;
        }
    }

    public CharBasedPNameTable(int i) {
        if (i < 1) {
            qf1.a("Can not use negative/zero initial size: ", i);
            throw null;
        }
        int i2 = 16;
        while (i2 < i) {
            i2 += i2;
        }
        this._symbols = new PNameC[i2];
        this._buckets = new Bucket[i2 >> 1];
        this._indexMask = i2 - 1;
        this._size = 0;
        this._sizeThreshold = ((i2 * 3) + 3) >> 2;
    }

    private void copyArrays() {
        PNameC[] pNameCArr = this._symbols;
        int length = pNameCArr.length;
        PNameC[] pNameCArr2 = new PNameC[length];
        this._symbols = pNameCArr2;
        System.arraycopy(pNameCArr, 0, pNameCArr2, 0, length);
        Bucket[] bucketArr = this._buckets;
        int length2 = bucketArr.length;
        Bucket[] bucketArr2 = new Bucket[length2];
        this._buckets = bucketArr2;
        System.arraycopy(bucketArr, 0, bucketArr2, 0, length2);
        this._dirty = true;
    }

    private void rehash() {
        PNameC[] pNameCArr = this._symbols;
        int length = pNameCArr.length;
        int i = length + length;
        Bucket[] bucketArr = this._buckets;
        this._symbols = new PNameC[i];
        this._buckets = new Bucket[i >> 1];
        this._indexMask = i - 1;
        int i2 = this._sizeThreshold;
        this._sizeThreshold = i2 + i2;
        int i3 = 0;
        for (PNameC pNameC : pNameCArr) {
            if (pNameC != null) {
                i3++;
                int customHash = pNameC.getCustomHash() & this._indexMask;
                PNameC[] pNameCArr2 = this._symbols;
                if (pNameCArr2[customHash] == null) {
                    pNameCArr2[customHash] = pNameC;
                } else {
                    int i4 = customHash >> 1;
                    Bucket[] bucketArr2 = this._buckets;
                    bucketArr2[i4] = new Bucket(pNameC, bucketArr2[i4]);
                }
            }
        }
        int i5 = length >> 1;
        for (int i6 = 0; i6 < i5; i6++) {
            for (Bucket next = bucketArr[i6]; next != null; next = next.getNext()) {
                i3++;
                PNameC symbol = next.getSymbol();
                int customHash2 = symbol.getCustomHash() & this._indexMask;
                PNameC[] pNameCArr3 = this._symbols;
                if (pNameCArr3[customHash2] == null) {
                    pNameCArr3[customHash2] = symbol;
                } else {
                    int i7 = customHash2 >> 1;
                    Bucket[] bucketArr3 = this._buckets;
                    bucketArr3[i7] = new Bucket(symbol, bucketArr3[i7]);
                }
            }
        }
        if (i3 == this._size) {
            return;
        }
        throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + i3 + ".");
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0017 A[PHI: r5
      0x0017: PHI (r5v7 int) = (r5v2 int), (r5v4 int) binds: [B:3:0x0015, B:8:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    public PNameC addSymbol(char[] cArr, int i, int i2, int i3) {
        boolean z;
        PNameC pNameCConstruct = PNameC.construct(new String(cArr, i, i2).intern(), i3);
        int i4 = this._indexMask & i3;
        if (this._symbols[i4] == null) {
            z = true;
        } else {
            z = false;
            if (this._size >= this._sizeThreshold) {
                rehash();
                i4 = this._indexMask & i3;
                if (this._symbols[i4] == null) {
                    z = true;
                }
            }
        }
        if (!this._dirty) {
            copyArrays();
        }
        this._size++;
        if (z) {
            this._symbols[i4] = pNameCConstruct;
            return pNameCConstruct;
        }
        int i5 = i4 >> 1;
        Bucket[] bucketArr = this._buckets;
        bucketArr[i5] = new Bucket(pNameCConstruct, bucketArr[i5]);
        return pNameCConstruct;
    }

    public PNameC findSymbol(char[] cArr, int i, int i2, int i3) {
        PNameC pNameCFind;
        int i4 = this._indexMask & i3;
        PNameC pNameC = this._symbols[i4];
        if (pNameC == null) {
            return null;
        }
        if (pNameC.equalsPName(cArr, i, i2, i3)) {
            return pNameC;
        }
        Bucket bucket = this._buckets[i4 >> 1];
        if (bucket == null || (pNameCFind = bucket.find(cArr, i, i2, i3)) == null) {
            return null;
        }
        return pNameCFind;
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public synchronized void mergeFromChild(CharBasedPNameTable charBasedPNameTable) {
        if (charBasedPNameTable.size() <= size()) {
            return;
        }
        this._symbols = charBasedPNameTable._symbols;
        this._buckets = charBasedPNameTable._buckets;
        this._size = charBasedPNameTable._size;
        this._sizeThreshold = charBasedPNameTable._sizeThreshold;
        this._indexMask = charBasedPNameTable._indexMask;
        this._dirty = false;
        charBasedPNameTable._dirty = false;
    }

    public int size() {
        return this._size;
    }

    public CharBasedPNameTable(CharBasedPNameTable charBasedPNameTable) {
        this._symbols = charBasedPNameTable._symbols;
        this._buckets = charBasedPNameTable._buckets;
        this._size = charBasedPNameTable._size;
        this._sizeThreshold = charBasedPNameTable._sizeThreshold;
        this._indexMask = charBasedPNameTable._indexMask;
    }
}
