package org.eclipse.jdt.internal.compiler.codegen;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ObjectCache {
    int elementSize;
    public Object[] keyTable;
    int threshold;
    public int[] valueTable;

    public ObjectCache(int i) {
        this.elementSize = 0;
        this.threshold = (int) (i * 0.66f);
        this.keyTable = new Object[i];
        this.valueTable = new int[i];
    }

    private void rehash() {
        ObjectCache objectCache = new ObjectCache(this.keyTable.length * 2);
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.keyTable = objectCache.keyTable;
                this.valueTable = objectCache.valueTable;
                this.threshold = objectCache.threshold;
                return;
            } else {
                Object obj = this.keyTable[length];
                if (obj != null) {
                    objectCache.put(obj, this.valueTable[length]);
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

    public boolean containsKey(Object obj) {
        int iHashCode = hashCode(obj);
        int length = this.keyTable.length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return false;
            }
            if (obj2 == obj) {
                return true;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int get(Object obj) {
        int iHashCode = hashCode(obj);
        int length = this.keyTable.length;
        while (true) {
            Object obj2 = this.keyTable[iHashCode];
            if (obj2 == null) {
                return -1;
            }
            if (obj2 == obj) {
                return this.valueTable[iHashCode];
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public int hashCode(Object obj) {
        return (obj.hashCode() & TypeIds.NoId) % this.keyTable.length;
    }

    public int put(Object obj, int i) {
        int iHashCode = hashCode(obj);
        int length = this.keyTable.length;
        while (true) {
            Object[] objArr = this.keyTable;
            Object obj2 = objArr[iHashCode];
            if (obj2 == null) {
                objArr[iHashCode] = obj;
                this.valueTable[iHashCode] = i;
                int i2 = this.elementSize + 1;
                this.elementSize = i2;
                if (i2 > this.threshold) {
                    rehash();
                }
                return i;
            }
            if (obj2 == obj) {
                this.valueTable[iHashCode] = i;
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
        int size = size();
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            Object obj = this.keyTable[i];
            if (obj != null) {
                sb.append(obj);
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

    public ObjectCache() {
        this(13);
    }
}
