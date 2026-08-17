package com.intellij.util.containers;

import com.intellij.util.ArrayUtil;
import defpackage.kac;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Deprecated
public final class IntArrayList implements Cloneable {
    private int[] myData;
    private int mySize;

    /* JADX WARN: Code duplicated, block: B:21:0x002f  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 3 || i == 4) ? 2 : 3];
        if (i == 1) {
            objArr[0] = "com/intellij/util/containers/IntArrayList";
        } else if (i == 2) {
            objArr[0] = "a";
        } else if (i == 3 || i == 4) {
            objArr[0] = "com/intellij/util/containers/IntArrayList";
        } else {
            objArr[0] = "values";
        }
        if (i == 1 || i == 3 || i == 4) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "com/intellij/util/containers/IntArrayList";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "toArray";
            } else if (i != 3 && i != 4) {
                objArr[2] = "add";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public IntArrayList(int i) {
        this.myData = new int[i];
    }

    private void checkRange(int i) {
        if (i >= this.mySize || i < 0) {
            kac.a("Index: ", i, ", Size: ", this.mySize);
        }
    }

    public void add(int i) {
        ensureCapacity(this.mySize + 1);
        int[] iArr = this.myData;
        int i2 = this.mySize;
        this.mySize = i2 + 1;
        iArr[i2] = i;
    }

    public Object clone() {
        try {
            IntArrayList intArrayList = (IntArrayList) super.clone();
            intArrayList.myData = toArray();
            return intArrayList;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    public void ensureCapacity(int i) {
        int[] iArr = this.myData;
        int length = iArr.length;
        if (i > length) {
            int i2 = ((length * 3) / 2) + 1;
            if (i2 >= i) {
                i = i2;
            }
            this.myData = ArrayUtil.realloc(iArr, i);
        }
    }

    public int get(int i) {
        checkRange(i);
        return this.myData[i];
    }

    public boolean isEmpty() {
        return this.mySize == 0;
    }

    public int size() {
        return this.mySize;
    }

    public int[] toArray() {
        int[] array = toArray(0, this.mySize);
        if (array == null) {
            $$$reportNull$$$0(1);
        }
        return array;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public void trimToSize() {
        int i = this.mySize;
        int[] iArr = this.myData;
        if (i < iArr.length) {
            this.myData = ArrayUtil.realloc(iArr, i);
        }
    }

    public IntArrayList() {
        this(10);
    }

    public int[] toArray(int i, int i2) {
        int[] iArrCopyOfRange = Arrays.copyOfRange(this.myData, i, i2 + i);
        if (iArrCopyOfRange == null) {
            $$$reportNull$$$0(4);
        }
        return iArrCopyOfRange;
    }
}
