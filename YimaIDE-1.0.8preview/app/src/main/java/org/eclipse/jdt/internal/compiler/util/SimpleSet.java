package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class SimpleSet implements Cloneable {
    public int elementSize;
    public int threshold;
    public Object[] values;

    public SimpleSet(int i) {
        i = i < 3 ? 3 : i;
        this.elementSize = 0;
        this.threshold = i + 1;
        this.values = new Object[(i * 2) + 1];
    }

    private void rehash() {
        SimpleSet simpleSet = new SimpleSet(this.elementSize * 2);
        int length = this.values.length;
        while (true) {
            length--;
            if (length < 0) {
                this.values = simpleSet.values;
                this.elementSize = simpleSet.elementSize;
                this.threshold = simpleSet.threshold;
                return;
            } else {
                Object obj = this.values[length];
                if (obj != null) {
                    simpleSet.add(obj);
                }
            }
        }
    }

    public Object add(Object obj) {
        int length = this.values.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object[] objArr = this.values;
            Object obj2 = objArr[iHashCode];
            if (obj2 == null) {
                objArr[iHashCode] = obj;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (obj2.equals(obj)) {
                this.values[iHashCode] = obj;
                return obj;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public Object addIfNotIncluded(Object obj) {
        int length = this.values.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object[] objArr = this.values;
            Object obj2 = objArr[iHashCode];
            if (obj2 == null) {
                objArr[iHashCode] = obj;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return obj;
            }
            if (obj2.equals(obj)) {
                return null;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public void asArray(Object[] objArr) {
        int i = this.elementSize;
        if (i != objArr.length) {
            j2d.a();
            return;
        }
        int length = this.values.length;
        for (int i2 = 0; i2 < length && i > 0; i2++) {
            Object obj = this.values[i2];
            if (obj != null) {
                i--;
                objArr[i] = obj;
            }
        }
    }

    public void clear() {
        int length = this.values.length;
        while (true) {
            length--;
            if (length < 0) {
                this.elementSize = 0;
                return;
            }
            this.values[length] = null;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        SimpleSet simpleSet = (SimpleSet) super.clone();
        simpleSet.elementSize = this.elementSize;
        simpleSet.threshold = this.threshold;
        int length = this.values.length;
        Object[] objArr = new Object[length];
        simpleSet.values = objArr;
        System.arraycopy(this.values, 0, objArr, 0, length);
        return simpleSet;
    }

    public boolean includes(Object obj) {
        int length = this.values.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.values[iHashCode];
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

    public Object remove(Object obj) {
        int length = this.values.length;
        int iHashCode = (obj.hashCode() & TypeIds.NoId) % length;
        while (true) {
            Object obj2 = this.values[iHashCode];
            if (obj2 == null) {
                return null;
            }
            if (obj2.equals(obj)) {
                this.elementSize--;
                Object[] objArr = this.values;
                Object obj3 = objArr[iHashCode];
                objArr[iHashCode] = null;
                int i = iHashCode + 1;
                if (objArr[i != length ? i : 0] != null) {
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

    public String toString() {
        int length = this.values.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            Object obj = this.values[i];
            if (obj != null) {
                str = str + obj.toString() + "\n";
            }
        }
        return str;
    }

    public SimpleSet() {
        this(13);
    }
}
