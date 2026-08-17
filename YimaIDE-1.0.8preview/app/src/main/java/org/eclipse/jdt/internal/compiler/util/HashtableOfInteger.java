package org.eclipse.jdt.internal.compiler.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfInteger {
    public int elementSize;
    public Integer[] keyTable;
    int threshold;
    public Object[] valueTable;

    public HashtableOfInteger(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new Integer[i2];
        this.valueTable = new Object[i2];
    }

    private void rehash() {
        HashtableOfInteger hashtableOfInteger = new HashtableOfInteger(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfInteger.keyTable;
                this.valueTable = hashtableOfInteger.valueTable;
                this.threshold = hashtableOfInteger.threshold;
                return;
            } else {
                Integer num = this.keyTable[length];
                if (num != null) {
                    hashtableOfInteger.putUnsafely(num.intValue(), this.valueTable[length]);
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
                this.valueTable[length] = null;
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        HashtableOfInteger hashtableOfInteger = (HashtableOfInteger) super.clone();
        hashtableOfInteger.elementSize = this.elementSize;
        hashtableOfInteger.threshold = this.threshold;
        int length = this.keyTable.length;
        Integer[] numArr = new Integer[length];
        hashtableOfInteger.keyTable = numArr;
        System.arraycopy(this.keyTable, 0, numArr, 0, length);
        int length2 = this.valueTable.length;
        Object[] objArr = new Object[length2];
        hashtableOfInteger.valueTable = objArr;
        System.arraycopy(this.valueTable, 0, objArr, 0, length2);
        return hashtableOfInteger;
    }

    public boolean containsKey(int i) {
        Integer numValueOf = Integer.valueOf(i);
        int length = this.keyTable.length;
        int iHashCode = numValueOf.hashCode() % length;
        while (true) {
            Integer num = this.keyTable[iHashCode];
            if (num == null) {
                return false;
            }
            if (num.equals(numValueOf)) {
                return true;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public Object get(int i) {
        Integer numValueOf = Integer.valueOf(i);
        int length = this.keyTable.length;
        int iHashCode = numValueOf.hashCode() % length;
        while (true) {
            Integer num = this.keyTable[iHashCode];
            if (num == null) {
                return null;
            }
            if (num.equals(numValueOf)) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public Object put(int i, Object obj) {
        Integer numValueOf = Integer.valueOf(i);
        int length = this.keyTable.length;
        int iHashCode = numValueOf.hashCode() % length;
        while (true) {
            Integer[] numArr = this.keyTable;
            Integer num = numArr[iHashCode];
            if (num == null) {
                numArr[iHashCode] = numValueOf;
                this.valueTable[iHashCode] = obj;
                int i2 = this.elementSize + 1;
                this.elementSize = i2;
                if (i2 > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (num.equals(numValueOf)) {
                this.valueTable[iHashCode] = obj;
                return obj;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void putUnsafely(int i, Object obj) {
        Integer[] numArr;
        Integer numValueOf = Integer.valueOf(i);
        int length = this.keyTable.length;
        int iHashCode = numValueOf.hashCode() % length;
        while (true) {
            numArr = this.keyTable;
            if (numArr[iHashCode] == null) {
                break;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
        numArr[iHashCode] = numValueOf;
        this.valueTable[iHashCode] = obj;
        int i2 = this.elementSize + 1;
        this.elementSize = i2;
        if (i2 > this.threshold) {
            rehash();
        }
    }

    public Object removeKey(int i) {
        Integer numValueOf = Integer.valueOf(i);
        int length = this.keyTable.length;
        int iHashCode = numValueOf.hashCode() % length;
        while (true) {
            Integer num = this.keyTable[iHashCode];
            if (num == null) {
                return null;
            }
            if (num.equals(numValueOf)) {
                Object[] objArr = this.valueTable;
                Object obj = objArr[iHashCode];
                this.elementSize--;
                this.keyTable[iHashCode] = null;
                objArr[iHashCode] = null;
                rehash();
                return obj;
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
            Object obj = this.valueTable[i];
            if (obj != null) {
                str = str + String.valueOf(this.keyTable[i]) + " -> " + obj.toString() + "\n";
            }
        }
        return str;
    }

    public HashtableOfInteger() {
        this(13);
    }
}
