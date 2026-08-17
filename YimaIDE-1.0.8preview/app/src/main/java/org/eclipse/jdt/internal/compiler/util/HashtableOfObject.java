package org.eclipse.jdt.internal.compiler.util;

import java.util.Arrays;
import org.eclipse.jdt.core.compiler.CharOperation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class HashtableOfObject {
    public static final int MAX_ARRAY_SIZE = 2147483645;
    public int elementSize;
    public char[][] keyTable;
    private int threshold;
    public Object[] valueTable;

    public HashtableOfObject(int i) {
        if (i < 0) {
            throw new NegativeArraySizeException("Bad attempt to create table with " + i + " elements");
        }
        this.threshold = i;
        int iCalculateNewSize = (int) (i * 1.75f);
        iCalculateNewSize = i == iCalculateNewSize ? iCalculateNewSize + 1 : iCalculateNewSize;
        iCalculateNewSize = (iCalculateNewSize < 1 || iCalculateNewSize > 2147483645) ? calculateNewSize(i) : iCalculateNewSize;
        this.keyTable = new char[iCalculateNewSize][];
        this.valueTable = new Object[iCalculateNewSize];
    }

    public static int calculateNewSize(int i) {
        if (i == 0) {
            return 1;
        }
        if (i < 0) {
            throw new NegativeArraySizeException("Bad attempt to calculate table size with " + i + " elements");
        }
        int i2 = i * 2;
        int i3 = i2 + 1;
        if (i3 >= 1 && i3 < 2147483645) {
            return i2;
        }
        int i4 = ((MAX_ARRAY_SIZE - i) / 2) + i;
        int i5 = i4 + 1;
        if (i5 >= 1 && i5 < 2147483645) {
            return i4;
        }
        if (2147483643 > i) {
            return 2147483643;
        }
        throw new OutOfMemoryError("Unable to increase table size over " + i + " elements");
    }

    private void rehash() {
        HashtableOfObject hashtableOfObject = new HashtableOfObject(calculateNewSize(this.elementSize));
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = hashtableOfObject.keyTable;
                this.valueTable = hashtableOfObject.valueTable;
                this.threshold = hashtableOfObject.threshold;
                return;
            } else {
                char[] cArr = this.keyTable[length];
                if (cArr != null) {
                    hashtableOfObject.putUnsafely(cArr, this.valueTable[length]);
                }
            }
        }
    }

    public void clear() {
        Arrays.fill(this.keyTable, (Object) null);
        Arrays.fill(this.valueTable, (Object) null);
        this.elementSize = 0;
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

    public Object get(char[] cArr) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[] cArr2 = this.keyTable[iHashCode];
            if (cArr2 == null) {
                return null;
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

    public Object put(char[] cArr, Object obj) {
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            char[][] cArr2 = this.keyTable;
            char[] cArr3 = cArr2[iHashCode];
            if (cArr3 == null) {
                cArr2[iHashCode] = cArr;
                this.valueTable[iHashCode] = obj;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (CharOperation.equals(cArr3, cArr)) {
                this.valueTable[iHashCode] = obj;
                return obj;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void putUnsafely(char[] cArr, Object obj) {
        char[][] cArr2;
        int length = this.keyTable.length;
        int iHashCode = CharOperation.hashCode(cArr) % length;
        while (true) {
            cArr2 = this.keyTable;
            if (cArr2[iHashCode] == null) {
                break;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
        cArr2[iHashCode] = cArr;
        this.valueTable[iHashCode] = obj;
        int i = this.elementSize + 1;
        this.elementSize = i;
        if (i > this.threshold) {
            rehash();
        }
    }

    public int size() {
        return this.elementSize;
    }

    public int storageSize() {
        return this.keyTable.length;
    }

    public String toString() {
        int length = this.valueTable.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            Object obj = this.valueTable[i];
            if (obj != null) {
                str = str + new String(this.keyTable[i]) + " -> " + obj.toString() + "\n";
            }
        }
        return str;
    }

    public HashtableOfObject() {
        this(13);
    }
}
