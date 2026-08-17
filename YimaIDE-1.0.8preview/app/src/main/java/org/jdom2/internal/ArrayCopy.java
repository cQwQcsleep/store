package org.jdom2.internal;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ArrayCopy {
    private ArrayCopy() {
    }

    public static final <E> E[] copyOf(E[] eArr, int i) {
        E[] eArr2 = (E[]) ((Object[]) Array.newInstance(eArr.getClass().getComponentType(), i));
        if (i >= eArr.length) {
            i = eArr.length;
        }
        System.arraycopy(eArr, 0, eArr2, 0, i);
        return eArr2;
    }

    public static final <E> E[] copyOfRange(E[] eArr, int i, int i2) {
        int length = i2 - i;
        if (length < 0) {
            pnd.a("From(", i, ") > To (", i2, ")");
            return null;
        }
        E[] eArr2 = (E[]) ((Object[]) Array.newInstance(eArr.getClass().getComponentType(), length));
        if (i + length > eArr.length) {
            length = eArr.length - i;
        }
        System.arraycopy(eArr, i, eArr2, 0, length);
        return eArr2;
    }

    public static final char[] copyOf(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        if (i >= cArr.length) {
            i = cArr.length;
        }
        System.arraycopy(cArr, 0, cArr2, 0, i);
        return cArr2;
    }

    public static final int[] copyOf(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        if (i >= iArr.length) {
            i = iArr.length;
        }
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    public static final boolean[] copyOf(boolean[] zArr, int i) {
        boolean[] zArr2 = new boolean[i];
        if (i >= zArr.length) {
            i = zArr.length;
        }
        System.arraycopy(zArr, 0, zArr2, 0, i);
        return zArr2;
    }
}
