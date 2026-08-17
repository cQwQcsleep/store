package org.eclipse.jdt.internal.compiler.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfLong {
    public int elementSize;
    public long[] keyTable;
    int threshold;
    public Object[] valueTable;

    public HashtableOfLong(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.75f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new long[i2];
        this.valueTable = new Object[i2];
    }

    private void rehash() {
        HashtableOfLong hashtableOfLong = new HashtableOfLong(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfLong.keyTable;
                this.valueTable = hashtableOfLong.valueTable;
                this.threshold = hashtableOfLong.threshold;
                return;
            } else {
                long j = this.keyTable[length];
                if (j != 0) {
                    hashtableOfLong.put(j, this.valueTable[length]);
                }
            }
        }
    }

    public boolean containsKey(long j) {
        int length = this.keyTable.length;
        int i = ((int) (j >>> 32)) % length;
        while (true) {
            long j2 = this.keyTable[i];
            if (j2 == 0) {
                return false;
            }
            if (j2 == j) {
                return true;
            }
            i++;
            if (i == length) {
                i = 0;
            }
        }
    }

    public Object get(long j) {
        int length = this.keyTable.length;
        int i = ((int) (j >>> 32)) % length;
        while (true) {
            long j2 = this.keyTable[i];
            if (j2 == 0) {
                return null;
            }
            if (j2 == j) {
                return this.valueTable[i];
            }
            i++;
            if (i == length) {
                i = 0;
            }
        }
    }

    public Object put(long j, Object obj) {
        int length = this.keyTable.length;
        int i = ((int) (j >>> 32)) % length;
        while (true) {
            long[] jArr = this.keyTable;
            long j2 = jArr[i];
            if (j2 == 0) {
                jArr[i] = j;
                this.valueTable[i] = obj;
                int i2 = this.elementSize + 1;
                this.elementSize = i2;
                if (i2 > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (j2 == j) {
                this.valueTable[i] = obj;
                return obj;
            }
            i++;
            if (i == length) {
                i = 0;
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

    public HashtableOfLong() {
        this(13);
    }
}
