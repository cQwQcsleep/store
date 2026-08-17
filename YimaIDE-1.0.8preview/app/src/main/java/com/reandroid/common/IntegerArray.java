package com.reandroid.common;

import com.intellij.psi.PsiKeyword;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface IntegerArray {
    public static final int[] EMPTY = new int[0];

    static boolean equals(IntegerArray integerArray, IntegerArray integerArray2) {
        if (integerArray == integerArray2) {
            return true;
        }
        if (isEmpty(integerArray)) {
            return isEmpty(integerArray2);
        }
        if (isEmpty(integerArray2)) {
            return isEmpty(integerArray);
        }
        int size = integerArray.size();
        if (size != integerArray2.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (integerArray.get(i) != integerArray2.get(i)) {
                return false;
            }
        }
        return true;
    }

    static int hashCode(IntegerArray integerArray) {
        if (isEmpty(integerArray)) {
            return 0;
        }
        int size = integerArray.size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i += integerArray.get(i2) * 31;
        }
        return i;
    }

    static boolean isEmpty(IntegerArray integerArray) {
        return integerArray == null || integerArray.size() == 0;
    }

    static IntegerArray subArray(IntegerArray integerArray, final int i, final int i2) {
        if (isEmpty(integerArray)) {
            return integerArray;
        }
        return (i == 0 && i2 == integerArray.size()) ? integerArray : new IntegerArray() { // from class: com.reandroid.common.IntegerArray.1
            @Override // com.reandroid.common.IntegerArray
            public int get(int i3) {
                return IntegerArray.this.get(i + i3);
            }

            @Override // com.reandroid.common.IntegerArray
            public void put(int i3, int i4) {
            }

            @Override // com.reandroid.common.IntegerArray
            public void setSize(int i3) {
            }

            @Override // com.reandroid.common.IntegerArray
            public int size() {
                return i2;
            }

            public String toString() {
                return IntegerArray.toString(this);
            }
        };
    }

    static int[] toArray(IntegerArray integerArray) {
        if (isEmpty(integerArray)) {
            return EMPTY;
        }
        int size = integerArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = integerArray.get(i);
        }
        return iArr;
    }

    static String toString(IntegerArray integerArray, boolean z, int i, int i2) {
        if (integerArray == null) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder("size=");
        int size = integerArray.size() - i;
        sb.append(size);
        if (i > 0) {
            sb.append(", start=");
            sb.append(i);
        }
        if (size <= i2) {
            i2 = size;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == 0) {
                sb.append(" [");
            } else {
                sb.append(", ");
            }
            int i4 = integerArray.get(i3);
            if (z) {
                sb.append(HexUtil.toHex(i4, 2));
            } else {
                sb.append(i4);
            }
        }
        if (size > i2) {
            sb.append(", +");
            sb.append(size - i2);
            sb.append(" more");
        }
        if (size > 0) {
            sb.append(']');
        }
        return sb.toString();
    }

    int get(int i);

    void put(int i, int i2);

    void setSize(int i);

    int size();

    static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length == 0;
    }

    static String toString(IntegerArray integerArray, boolean z) {
        return toString(integerArray, z, 0, 10);
    }

    static String toString(IntegerArray integerArray, int i) {
        return toString(integerArray, false, i, 10);
    }

    static String toString(IntegerArray integerArray, boolean z, int i) {
        return toString(integerArray, z, i, 10);
    }

    static String toString(IntegerArray integerArray) {
        return toString(integerArray, false, 0, 10);
    }
}
