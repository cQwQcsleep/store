package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.core.compiler.CharOperation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfIntValues implements Cloneable {
    public static final int NO_VALUE = Integer.MIN_VALUE;
    public int elementSize;
    public char[][] keyTable;
    int threshold;
    public int[] valueTable;

    public HashtableOfIntValues(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new char[i2][];
        this.valueTable = new int[i2];
    }

    private void rehash() {
        HashtableOfIntValues hashtableOfIntValues = new HashtableOfIntValues(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfIntValues.keyTable;
                this.valueTable = hashtableOfIntValues.valueTable;
                this.threshold = hashtableOfIntValues.threshold;
                return;
            } else {
                char[] cArr = this.keyTable[length];
                if (cArr != null) {
                    hashtableOfIntValues.put(cArr, this.valueTable[length]);
                }
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        HashtableOfIntValues hashtableOfIntValues = (HashtableOfIntValues) super.clone();
        hashtableOfIntValues.elementSize = this.elementSize;
        hashtableOfIntValues.threshold = this.threshold;
        int length = this.keyTable.length;
        char[][] cArr = new char[length][];
        hashtableOfIntValues.keyTable = cArr;
        System.arraycopy(this.keyTable, 0, cArr, 0, length);
        int length2 = this.valueTable.length;
        int[] iArr = new int[length2];
        hashtableOfIntValues.valueTable = iArr;
        System.arraycopy(this.valueTable, 0, iArr, 0, length2);
        return hashtableOfIntValues;
    }

    public boolean containsKey(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        int length2 = cArr.length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return false;
            }
            if (cArr2.length == length2 && CharOperation.equals(cArr2, cArr)) {
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
        int length2 = cArr.length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return Integer.MIN_VALUE;
            }
            if (cArr2.length == length2 && CharOperation.equals(cArr2, cArr)) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int put(char[] cArr, int i) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        int length2 = cArr.length;
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
            if (cArr3.length == length2 && CharOperation.equals(cArr3, cArr)) {
                this.valueTable[iHashCode] = i;
                return i;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int removeKey(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        int length2 = cArr.length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return Integer.MIN_VALUE;
            }
            if (cArr2.length == length2 && CharOperation.equals(cArr2, cArr)) {
                int[] iArr = this.valueTable;
                int i = iArr[iHashCode];
                this.elementSize--;
                this.keyTable[iHashCode] = null;
                iArr[iHashCode] = Integer.MIN_VALUE;
                rehash();
                return i;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int size() {
        return this.elementSize;
    }

    public String toString() {
        int length = this.valueTable.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            char[] cArr = this.keyTable[i];
            if (cArr != null) {
                str = str + new String(cArr) + " -> " + this.valueTable[i] + "\n";
            }
        }
        return str;
    }

    public HashtableOfIntValues() {
        this(13);
    }
}
