package org.eclipse.jdt.internal.compiler.codegen;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IntegerCache {
    int elementSize;
    public int[] keyTable;
    int threshold;
    public int[] valueTable;

    public IntegerCache(int i) {
        this.elementSize = 0;
        this.threshold = (int) (((double) i) * 0.66d);
        this.keyTable = new int[i];
        this.valueTable = new int[i];
    }

    private void rehash() {
        IntegerCache integerCache = new IntegerCache(this.keyTable.length * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = integerCache.keyTable;
                this.valueTable = integerCache.valueTable;
                this.threshold = integerCache.threshold;
                return;
            } else {
                int i = this.keyTable[length];
                int i2 = this.valueTable[length];
                if (i != 0 || (i == 0 && i2 != 0)) {
                    integerCache.put(i, i2);
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

    public boolean containsKey(int i) {
        int iHash = hash(i);
        int length = this.keyTable.length;
        while (true) {
            int i2 = this.keyTable[iHash];
            if (i2 == 0 && (i2 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (i2 == i) {
                return true;
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        return false;
    }

    public int hash(int i) {
        return (i & TypeIds.NoId) % this.keyTable.length;
    }

    public int put(int i, int i2) {
        int[] iArr;
        int iHash = hash(i);
        int length = this.keyTable.length;
        while (true) {
            iArr = this.keyTable;
            int i3 = iArr[iHash];
            if (i3 == 0 && (i3 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (i3 == i) {
                this.valueTable[iHash] = i2;
                return i2;
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        iArr[iHash] = i;
        this.valueTable[iHash] = i2;
        int i4 = this.elementSize + 1;
        this.elementSize = i4;
        if (i4 > this.threshold) {
            rehash();
        }
        return i2;
    }

    public int putIfAbsent(int i, int i2) {
        int[] iArr;
        int iHash = hash(i);
        int length = this.keyTable.length;
        while (true) {
            iArr = this.keyTable;
            int i3 = iArr[iHash];
            if (i3 == 0 && (i3 != 0 || this.valueTable[iHash] == 0)) {
                break;
            }
            if (i3 == i) {
                return this.valueTable[iHash];
            }
            iHash++;
            if (iHash == length) {
                iHash = 0;
            }
        }
        iArr[iHash] = i;
        this.valueTable[iHash] = i2;
        int i4 = this.elementSize + 1;
        this.elementSize = i4;
        if (i4 > this.threshold) {
            rehash();
        }
        return -i2;
    }

    public int size() {
        return this.elementSize;
    }

    public String toString() {
        int size = size();
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            int i2 = this.keyTable[i];
            if (i2 != 0 || (i2 == 0 && this.valueTable[i] != 0)) {
                sb.append(i2);
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

    public IntegerCache() {
        this(13);
    }
}
