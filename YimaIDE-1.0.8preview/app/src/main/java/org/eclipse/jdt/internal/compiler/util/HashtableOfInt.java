package org.eclipse.jdt.internal.compiler.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfInt {
    public int elementSize;
    public int[] keyTable;
    int threshold;
    public Object[] valueTable;

    public HashtableOfInt(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new int[i2];
        this.valueTable = new Object[i2];
    }

    private void rehash() {
        HashtableOfInt hashtableOfInt = new HashtableOfInt(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfInt.keyTable;
                this.valueTable = hashtableOfInt.valueTable;
                this.threshold = hashtableOfInt.threshold;
                return;
            } else {
                int i = this.keyTable[length];
                if (i != 0) {
                    hashtableOfInt.put(i, this.valueTable[length]);
                }
            }
        }
    }

    public boolean containsKey(int i) {
        int length = this.keyTable.length;
        int i2 = i % length;
        while (true) {
            int i3 = this.keyTable[i2];
            if (i3 == 0) {
                return false;
            }
            if (i3 == i) {
                return true;
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
    }

    public Object get(int i) {
        int length = this.keyTable.length;
        int i2 = i % length;
        while (true) {
            int i3 = this.keyTable[i2];
            if (i3 == 0) {
                return null;
            }
            if (i3 == i) {
                return this.valueTable[i2];
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
            }
        }
    }

    public Object put(int i, Object obj) {
        int length = this.keyTable.length;
        int i2 = i % length;
        while (true) {
            int[] iArr = this.keyTable;
            int i3 = iArr[i2];
            if (i3 == 0) {
                iArr[i2] = i;
                this.valueTable[i2] = obj;
                int i4 = this.elementSize + 1;
                this.elementSize = i4;
                if (i4 > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (i3 == i) {
                this.valueTable[i2] = obj;
                return obj;
            }
            i2++;
            if (i2 == length) {
                i2 = 0;
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
                str = str + this.keyTable[i] + " -> " + obj.toString() + "\n";
            }
        }
        return str;
    }

    public HashtableOfInt() {
        this(13);
    }
}
