package com.intellij.util.lang;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class StrippedLongSet {
    private transient boolean containsNull;
    public transient long[] keys;
    private final float loadFactor;
    private transient int mask;
    private transient int maxFill;
    private transient int n;
    private int size;

    public final class SetIterator {
        int c;
        boolean mustReturnNull;
        int pos;

        public SetIterator() {
            this.pos = StrippedLongSet.this.n;
            this.c = StrippedLongSet.this.size;
            this.mustReturnNull = StrippedLongSet.this.containsNull;
        }

        public boolean hasNext() {
            return this.c != 0;
        }

        public long nextLong() {
            long j;
            if (!hasNext()) {
                z0e.a();
                return 0L;
            }
            this.c--;
            if (this.mustReturnNull) {
                this.mustReturnNull = false;
                StrippedLongSet strippedLongSet = StrippedLongSet.this;
                return strippedLongSet.keys[strippedLongSet.n];
            }
            long[] jArr = StrippedLongSet.this.keys;
            do {
                int i = this.pos - 1;
                this.pos = i;
                j = jArr[i];
            } while (j == 0);
            return j;
        }
    }

    public StrippedLongSet(int i, float f) {
        if (f <= 0.0f || f > 1.0f) {
            w01.a("Load factor must be greater than 0 and smaller than or equal to 1");
            throw null;
        }
        if (i < 0) {
            w01.a("The expected number of elements must be non-negative");
            throw null;
        }
        this.loadFactor = f;
        int iArraySize = Hash.arraySize(i, f);
        this.n = iArraySize;
        this.mask = iArraySize - 1;
        this.maxFill = Hash.maxFill(iArraySize, f);
        this.keys = new long[this.n + 1];
    }

    private int realSize() {
        boolean z = this.containsNull;
        int i = this.size;
        return z ? i - 1 : i;
    }

    private void rehash(int i) {
        long j;
        long[] jArr = this.keys;
        int i2 = i - 1;
        long[] jArr2 = new long[i + 1];
        int i3 = this.n;
        int iRealSize = realSize();
        while (true) {
            int i4 = iRealSize - 1;
            if (iRealSize == 0) {
                this.n = i;
                this.mask = i2;
                this.maxFill = Hash.maxFill(i, this.loadFactor);
                this.keys = jArr2;
                return;
            }
            do {
                i3--;
                j = jArr[i3];
            } while (j == 0);
            int i5 = ((int) j) & i2;
            if (jArr2[i5] != 0) {
                do {
                    i5 = (i5 + 1) & i2;
                } while (jArr2[i5] != 0);
            }
            jArr2[i5] = jArr[i3];
            iRealSize = i4;
        }
    }

    public boolean add(long j) {
        long j2;
        if (j != 0) {
            long[] jArr = this.keys;
            int i = ((int) j) & this.mask;
            long j3 = jArr[i];
            if (j3 != 0) {
                if (j3 == j) {
                    return false;
                }
                do {
                    i = (i + 1) & this.mask;
                    j2 = jArr[i];
                    if (j2 != 0) {
                    }
                } while (j2 != j);
                return false;
            }
            jArr[i] = j;
        } else {
            if (this.containsNull) {
                return false;
            }
            this.containsNull = true;
        }
        int i2 = this.size;
        this.size = i2 + 1;
        if (i2 >= this.maxFill) {
            rehash(Hash.arraySize(i2 + 2, this.loadFactor));
        }
        return true;
    }

    public boolean hasNull() {
        return this.containsNull;
    }

    public SetIterator iterator() {
        return new SetIterator();
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        long[] jArr = new long[this.size];
        SetIterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.nextLong();
            i++;
        }
        return jArr;
    }

    public StrippedLongSet() {
        this(16, 0.5f);
    }
}
