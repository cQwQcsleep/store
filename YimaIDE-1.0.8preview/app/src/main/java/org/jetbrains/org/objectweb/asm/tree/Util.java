package org.jetbrains.org.objectweb.asm.tree;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class Util {
    public static <T> List<T> add(List<T> list, T t) {
        if (list == null) {
            list = new ArrayList<>(1);
        }
        list.add(t);
        return list;
    }

    public static List<Byte> asArrayList(byte[] bArr) {
        if (bArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public static <T> List<T> asArrayList(T[] tArr) {
        if (tArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(tArr.length);
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static <T> List<T> asArrayList(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(null);
        }
        return arrayList;
    }

    public static List<Boolean> asArrayList(boolean[] zArr) {
        if (zArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    public static List<Short> asArrayList(short[] sArr) {
        if (sArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s : sArr) {
            arrayList.add(Short.valueOf(s));
        }
        return arrayList;
    }

    public static List<Character> asArrayList(char[] cArr) {
        if (cArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c : cArr) {
            arrayList.add(Character.valueOf(c));
        }
        return arrayList;
    }

    public static List<Integer> asArrayList(int[] iArr) {
        if (iArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    public static List<Float> asArrayList(float[] fArr) {
        if (fArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    public static List<Long> asArrayList(long[] jArr) {
        if (jArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j : jArr) {
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    public static List<Double> asArrayList(double[] dArr) {
        if (dArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(Double.valueOf(d));
        }
        return arrayList;
    }

    public static <T> List<T> asArrayList(int i, T[] tArr) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(tArr[i2]);
        }
        return arrayList;
    }
}
