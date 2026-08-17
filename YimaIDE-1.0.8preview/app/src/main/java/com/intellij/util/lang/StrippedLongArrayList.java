package com.intellij.util.lang;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class StrippedLongArrayList {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long[] DEFAULT_EMPTY_ARRAY = new long[0];
    private transient long[] a = DEFAULT_EMPTY_ARRAY;
    private int size;

    private static long[] forceCapacity(long[] jArr, int i, int i2) {
        long[] jArr2 = new long[i];
        System.arraycopy(jArr, 0, jArr2, 0, i2);
        return jArr2;
    }

    private void grow(int i) {
        long[] jArr = this.a;
        if (i <= jArr.length) {
            return;
        }
        if (jArr != DEFAULT_EMPTY_ARRAY) {
            i = (int) Math.max(Math.min(((long) jArr.length) + ((long) (jArr.length >> 1)), 2147483639L), i);
        } else if (i < 10) {
            i = 10;
        }
        this.a = forceCapacity(this.a, i, this.size);
    }

    public boolean add(long j) {
        grow(this.size + 1);
        long[] jArr = this.a;
        int i = this.size;
        this.size = i + 1;
        jArr[i] = j;
        return true;
    }

    public long[] elements() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StrippedLongArrayList)) {
            return false;
        }
        int size = size();
        StrippedLongArrayList strippedLongArrayList = (StrippedLongArrayList) obj;
        if (size != strippedLongArrayList.size()) {
            return false;
        }
        long[] jArr = this.a;
        long[] jArr2 = strippedLongArrayList.a;
        if (jArr == jArr2) {
            return true;
        }
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return true;
            }
            if (jArr[i] != jArr2[i]) {
                return false;
            }
            size = i;
        }
    }

    public int size() {
        return this.size;
    }
}
