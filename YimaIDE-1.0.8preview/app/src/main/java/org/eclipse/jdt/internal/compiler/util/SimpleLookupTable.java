package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class SimpleLookupTable implements Cloneable {
    public int elementSize;
    public Object[] keyTable;
    public int threshold;
    public Object[] valueTable;

    public SimpleLookupTable(int i) {
        this.elementSize = 0;
        this.threshold = i;
        int i2 = (int) (i * 1.5f);
        i2 = i == i2 ? i2 + 1 : i2;
        this.keyTable = new Object[i2];
        this.valueTable = new Object[i2];
    }

    private void rehash() {
        SimpleLookupTable simpleLookupTable = new SimpleLookupTable(this.elementSize * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = simpleLookupTable.keyTable;
                this.valueTable = simpleLookupTable.valueTable;
                this.elementSize = simpleLookupTable.elementSize;
                this.threshold = simpleLookupTable.threshold;
                return;
            }
            Object obj = this.keyTable[length];
            if (obj != null) {
                simpleLookupTable.put(obj, this.valueTable[length]);
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        SimpleLookupTable simpleLookupTable = (SimpleLookupTable) super.clone();
        simpleLookupTable.elementSize = this.elementSize;
        simpleLookupTable.threshold = this.threshold;
        int length = this.keyTable.length;
        Object[] objArr = new Object[length];
        simpleLookupTable.keyTable = objArr;
        System.arraycopy(this.keyTable, 0, objArr, 0, length);
        int length2 = this.valueTable.length;
        Object[] objArr2 = new Object[length2];
        simpleLookupTable.valueTable = objArr2;
        System.arraycopy(this.valueTable, 0, objArr2, 0, length2);
        return simpleLookupTable;
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

    public Object get(Object obj) {
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

    public Object getKey(Object obj) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return obj;
            }
            if (obj2.equals(obj)) {
                return obj2;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public Object keyForValue(Object obj) {
        if (obj == null) {
            return null;
        }
        int length = this.keyTable.length;
        for (int i = 0; i < length; i++) {
            if (this.keyTable[i] != null && obj.equals(this.valueTable[i])) {
                return this.keyTable[i];
            }
        }
        return null;
    }

    public Object put(Object obj, Object obj2) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object[] objArr = this.keyTable;
            Object obj3 = objArr[iHashCode];
            if (obj3 == null) {
                objArr[iHashCode] = obj;
                this.valueTable[iHashCode] = obj2;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return obj2;
            }
            if (obj3.equals(obj)) {
                this.valueTable[iHashCode] = obj2;
                return obj2;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public Object removeKey(Object obj) {
        int length = this.keyTable.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return null;
            }
            if (obj2.equals(obj)) {
                this.elementSize--;
                Object[] objArr = this.valueTable;
                Object obj3 = objArr[iHashCode];
                Object[] objArr2 = this.keyTable;
                objArr2[iHashCode] = null;
                objArr[iHashCode] = null;
                int i = iHashCode + 1;
                if (objArr2[i != length ? i : 0] != null) {
                    rehash();
                }
                return obj3;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void removeValue(Object obj) {
        int length = this.valueTable.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            Object obj2 = this.valueTable[i];
            if (obj2 != null && obj2.equals(obj)) {
                this.elementSize--;
                Object[] objArr = this.keyTable;
                objArr[i] = null;
                this.valueTable[i] = null;
                if (!z) {
                    int i2 = i + 1;
                    if (i2 == length) {
                        i2 = 0;
                    }
                    if (objArr[i2] != null) {
                        z = true;
                    }
                }
            }
        }
        if (z) {
            rehash();
        }
    }

    public String toString() {
        int length = this.valueTable.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            Object obj = this.valueTable[i];
            if (obj != null) {
                str = str + this.keyTable[i].toString() + " -> " + obj.toString() + "\n";
            }
        }
        return str;
    }

    public SimpleLookupTable() {
        this(13);
    }
}
