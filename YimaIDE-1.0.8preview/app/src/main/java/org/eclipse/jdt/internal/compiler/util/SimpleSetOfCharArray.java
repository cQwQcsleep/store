package org.eclipse.jdt.internal.compiler.util;

import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class SimpleSetOfCharArray implements Cloneable {
    public int elementSize;
    public int threshold;
    public char[][] values;

    public SimpleSetOfCharArray(int i) {
        i = i < 3 ? 3 : i;
        this.elementSize = 0;
        this.threshold = i + 1;
        this.values = new char[(i * 2) + 1][];
    }

    private void rehash() {
        SimpleSetOfCharArray simpleSetOfCharArray = new SimpleSetOfCharArray(this.elementSize * 2);
        int length = this.values.length;
        while (true) {
            length--;
            if (length < 0) {
                this.values = simpleSetOfCharArray.values;
                this.elementSize = simpleSetOfCharArray.elementSize;
                this.threshold = simpleSetOfCharArray.threshold;
                return;
            } else {
                char[] cArr = this.values[length];
                if (cArr != null) {
                    simpleSetOfCharArray.add(cArr);
                }
            }
        }
    }

    public Object add(char[] cArr) {
        int length = this.values.length;
        int iHashCode = (CharOperation.hashCode(cArr) & TypeIds.NoId) % length;
        while (true) {
            char[][] cArr2 = this.values;
            char[] cArr3 = cArr2[iHashCode];
            if (cArr3 == null) {
                cArr2[iHashCode] = cArr;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return cArr;
            }
            if (CharOperation.equals(cArr3, cArr)) {
                this.values[iHashCode] = cArr;
                return cArr;
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
            char[] cArr = this.values[i2];
            if (cArr != null) {
                i--;
                objArr[i] = cArr;
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
        SimpleSetOfCharArray simpleSetOfCharArray = (SimpleSetOfCharArray) super.clone();
        simpleSetOfCharArray.elementSize = this.elementSize;
        simpleSetOfCharArray.threshold = this.threshold;
        int length = this.values.length;
        char[][] cArr = new char[length][];
        simpleSetOfCharArray.values = cArr;
        System.arraycopy(this.values, 0, cArr, 0, length);
        return simpleSetOfCharArray;
    }

    public char[] get(char[] cArr) {
        int length = this.values.length;
        int iHashCode = (CharOperation.hashCode(cArr) & TypeIds.NoId) % length;
        while (true) {
            char[][] cArr2 = this.values;
            char[] cArr3 = cArr2[iHashCode];
            if (cArr3 == null) {
                cArr2[iHashCode] = cArr;
                int i = this.elementSize + 1;
                this.elementSize = i;
                if (i > this.threshold) {
                    rehash();
                }
                return cArr;
            }
            if (CharOperation.equals(cArr3, cArr)) {
                return cArr3;
            }
            iHashCode++;
            if (iHashCode == length) {
                iHashCode = 0;
            }
        }
    }

    public boolean includes(char[] cArr) {
        int length = this.values.length;
        int iHashCode = (CharOperation.hashCode(cArr) & TypeIds.NoId) % length;
        while (true) {
            char[] cArr2 = this.values[iHashCode];
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

    public char[] remove(char[] cArr) {
        int length = this.values.length;
        int iHashCode = (CharOperation.hashCode(cArr) & TypeIds.NoId) % length;
        while (true) {
            char[] cArr2 = this.values[iHashCode];
            if (cArr2 == null) {
                return null;
            }
            if (CharOperation.equals(cArr2, cArr)) {
                this.elementSize--;
                char[][] cArr3 = this.values;
                char[] cArr4 = cArr3[iHashCode];
                cArr3[iHashCode] = null;
                int i = iHashCode + 1;
                if (cArr3[i != length ? i : 0] != null) {
                    rehash();
                }
                return cArr4;
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
            char[] cArr = this.values[i];
            if (cArr != null) {
                str = str + new String(cArr) + "\n";
            }
        }
        return str;
    }

    public SimpleSetOfCharArray() {
        this(13);
    }
}
