package org.eclipse.jdt.internal.compiler.codegen;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class LongCache {
    int elementSize;
    public long[] keyTable;
    int threshold;
    public int[] valueTable;

    public LongCache(int i) {
        this.elementSize = 0;
        this.threshold = (int) (((double) i) * 0.66d);
        this.keyTable = new long[i];
        this.valueTable = new int[i];
    }

    private void rehash() {
        LongCache longCache = new LongCache(this.keyTable.length * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = longCache.keyTable;
                this.valueTable = longCache.valueTable;
                this.threshold = longCache.threshold;
                return;
            } else {
                long j = this.keyTable[length];
                int i = this.valueTable[length];
                if (j != 0 || (j == 0 && i != 0)) {
                    longCache.put(j, i);
                }
            }
        }
    }

    public void clear() {
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.elementSize = 0;
                return;
            } else {
                this.keyTable[length] = 0;
                this.valueTable[length] = 0;
            }
        }
    }

    public boolean containsKey(long j) {
        int iHash = hash(j);
        int length = this.keyTable.length;
        while (true) {
            long j2 = this.keyTable[iHash];
            if (j2 == 0 && (j2 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (j2 == j) {
                return true;
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        return false;
    }

    public int hash(long j) {
        return (((int) j) & TypeIds.NoId) % this.keyTable.length;
    }

    public int put(long j, int i) {
        long[] jArr;
        int iHash = hash(j);
        int length = this.keyTable.length;
        while (true) {
            jArr = this.keyTable;
            long j2 = jArr[iHash];
            if (j2 == 0 && (j2 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (j2 == j) {
                this.valueTable[iHash] = i;
                return i;
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        jArr[iHash] = j;
        this.valueTable[iHash] = i;
        int i2 = this.elementSize + 1;
        this.elementSize = i2;
        if (i2 > this.threshold) {
            rehash();
        }
        return i;
    }

    public int putIfAbsent(long j, int i) {
        long[] jArr;
        int iHash = hash(j);
        int length = this.keyTable.length;
        while (true) {
            jArr = this.keyTable;
            long j2 = jArr[iHash];
            if (j2 == 0 && (j2 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (j2 == j) {
                return this.valueTable[iHash];
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        jArr[iHash] = j;
        this.valueTable[iHash] = i;
        int i2 = this.elementSize + 1;
        this.elementSize = i2;
        if (i2 > this.threshold) {
            rehash();
        }
        return -i;
    }

    public int size() {
        return this.elementSize;
    }

    public String toString() {
        int size = size();
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            long j = this.keyTable[i];
            if (j != 0 || (j == 0 && this.valueTable[i] != 0)) {
                sb.append(j);
                sb.append("->");
                sb.append(this.valueTable[i]);
            }
            if (i < size) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public LongCache() {
        this(13);
    }
}
