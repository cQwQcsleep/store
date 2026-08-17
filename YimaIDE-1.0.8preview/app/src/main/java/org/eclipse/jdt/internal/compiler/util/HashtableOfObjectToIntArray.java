package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfObjectToIntArray implements Cloneable {
    public int elementSize;
    public Object[] keyTable;
    int threshold;
    public int[][] valueTable;

    public HashtableOfObjectToIntArray(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new Object[i2];
        this.valueTable = new int[i2][];
    }

    private void rehash() {
        HashtableOfObjectToIntArray hashtableOfObjectToIntArray = new HashtableOfObjectToIntArray(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfObjectToIntArray.keyTable;
                this.valueTable = hashtableOfObjectToIntArray.valueTable;
                this.threshold = hashtableOfObjectToIntArray.threshold;
                return;
            } else {
                Object obj = this.keyTable[length];
                if (obj != null) {
                    hashtableOfObjectToIntArray.put(obj, this.valueTable[length]);
                }
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        HashtableOfObjectToIntArray hashtableOfObjectToIntArray = (HashtableOfObjectToIntArray) super.clone();
        hashtableOfObjectToIntArray.elementSize = this.elementSize;
        hashtableOfObjectToIntArray.threshold = this.threshold;
        int length = this.keyTable.length;
        Object[] objArr = new Object[length];
        hashtableOfObjectToIntArray.keyTable = objArr;
        System.arraycopy(this.keyTable, 0, objArr, 0, length);
        int length2 = this.valueTable.length;
        int[][] iArr = new int[length2][];
        hashtableOfObjectToIntArray.valueTable = iArr;
        System.arraycopy(this.valueTable, 0, iArr, 0, length2);
        return hashtableOfObjectToIntArray;
    }

    public boolean containsKey(Object obj) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int[] get(Object obj) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return null;
            }
            if (obj2.equals(obj)) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void keysToArray(Object[] objArr) {
        int length = this.keyTable.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = this.keyTable[i2];
            if (obj != null) {
                objArr[i] = obj;
                i++;
            }
        }
    }

    public int[] put(Object obj, int[] iArr) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object[] objArr = this.keyTable;
            Object obj2 = objArr[iHashCode];
            if (obj2 == null) {
                objArr[iHashCode] = obj;
                this.valueTable[iHashCode] = iArr;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return iArr;
            }
            if (obj2.equals(obj)) {
                this.valueTable[iHashCode] = iArr;
                return iArr;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int[] removeKey(Object obj) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return null;
            }
            if (obj2.equals(obj)) {
                int[] iArr = this.valueTable[iHashCode];
                this.elementSize--;
                this.keyTable[iHashCode] = null;
                rehash();
                return iArr;
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
        StringBuilder sb = new StringBuilder();
        int length = this.keyTable.length;
        for (int i = 0; i < length; i++) {
            Object obj = this.keyTable[i];
            if (obj != null) {
                sb.append(obj);
                sb.append(" -> [");
                int[] iArr = this.valueTable[i];
                if (iArr != null) {
                    int length2 = iArr.length;
                    for (int i2 = 0; i2 < length2; i2++) {
                        if (i2 > 0) {
                            sb.append(',');
                        }
                        sb.append(iArr[i2]);
                    }
                }
                sb.append("]\n");
            }
        }
        return String.valueOf(sb);
    }

    public HashtableOfObjectToIntArray() {
        this(13);
    }
}
