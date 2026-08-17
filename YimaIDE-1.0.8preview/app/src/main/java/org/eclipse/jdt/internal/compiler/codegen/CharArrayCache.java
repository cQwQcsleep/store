package org.eclipse.jdt.internal.compiler.codegen;

import org.eclipse.jdt.core.compiler.CharOperation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class CharArrayCache {
    int elementSize;
    public char[][] keyTable;
    int threshold;
    public int[] valueTable;

    public CharArrayCache(int i) {
        this.elementSize = 0;
        this.threshold = (i * 2) / 3;
        this.keyTable = new char[i][];
        this.valueTable = new int[i];
    }

    private int put(char[] cArr, int i) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[][] cArr2 = this.keyTable;
            char[] cArr3 = cArr2[iHashCode];
            if (cArr3 == null) {
                cArr2[iHashCode] = cArr;
                this.valueTable[iHashCode] = i;
                int i2 = this.elementSize + 1;
                this.elementSize = i2;
                if (i2 > this.threshold) {
                    rehash();
                }
                return i;
            }
            if (CharOperation.equals(cArr3, cArr)) {
                this.valueTable[iHashCode] = i;
                return i;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    private void rehash() {
        CharArrayCache charArrayCache = new CharArrayCache(this.keyTable.length * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = charArrayCache.keyTable;
                this.valueTable = charArrayCache.valueTable;
                this.threshold = charArrayCache.threshold;
                return;
            } else {
                char[] cArr = this.keyTable[length];
                if (cArr != null) {
                    charArrayCache.put(cArr, this.valueTable[length]);
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
                this.keyTable[length] = null;
                this.valueTable[length] = 0;
            }
        }
    }

    public boolean containsKey(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return false;
            }
            if (CharOperation.equals(cArr2, cArr)) {
                return true;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int get(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return -1;
            }
            if (CharOperation.equals(cArr2, cArr)) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int putIfAbsent(char[] cArr, int i) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[][] cArr2 = this.keyTable;
            char[] cArr3 = cArr2[iHashCode];
            if (cArr3 == null) {
                cArr2[iHashCode] = cArr;
                this.valueTable[iHashCode] = i;
                int i2 = this.elementSize + 1;
                this.elementSize = i2;
                if (i2 > this.threshold) {
                    rehash();
                }
                return -i;
            }
            if (CharOperation.equals(cArr3, cArr)) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void remove(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return;
            }
            if (CharOperation.equals(cArr2, cArr)) {
                this.valueTable[iHashCode] = 0;
                this.keyTable[iHashCode] = null;
                return;
            } else {
                iHashCode++;
                if (iHashCode == length) {
                    iHashCode = 0;
                }
            }
        }
    }

    public char[] returnKeyFor(int i) {
        int length = this.keyTable.length;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return null;
            }
            if (this.valueTable[i2] == i) {
                return this.keyTable[i2];
            }
            length = i2;
        }
    }

    public int size() {
        return this.elementSize;
    }

    public String toString() {
        int size = size();
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            char[] cArr = this.keyTable[i];
            if (cArr != null) {
                sb.append(cArr);
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

    public CharArrayCache() {
        this(9);
    }
}
