package com.reandroid.dex.key;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ArrayKeyHelper {
    public static boolean isAllTypeOf(Class<? extends Key> cls, ArrayKey arrayKey) {
        int size = arrayKey.size();
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!cls.isInstance(arrayKey.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean[] toBooleanValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.BooleanKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        boolean[] zArr = new boolean[size];
        for (int i = 0; i < size; i++) {
            zArr[i] = ((PrimitiveKey.BooleanKey) arrayKey.get(i)).value();
        }
        return zArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] toByteValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.ByteKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = ((PrimitiveKey.ByteKey) arrayKey.get(i)).value();
        }
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static char[] toCharValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.CharKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        char[] cArr = new char[size];
        for (int i = 0; i < size; i++) {
            cArr[i] = ((PrimitiveKey.CharKey) arrayKey.get(i)).value();
        }
        return cArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static double[] toDoubleValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.DoubleKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((PrimitiveKey.DoubleKey) arrayKey.get(i)).value();
        }
        return dArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float[] toFloatValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.FloatKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = ((PrimitiveKey.FloatKey) arrayKey.get(i)).value();
        }
        return fArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int[] toIntValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.IntegerKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((PrimitiveKey.IntegerKey) arrayKey.get(i)).value();
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] toLongValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.LongKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((PrimitiveKey.LongKey) arrayKey.get(i)).value();
        }
        return jArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] toNumberValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.NumberKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((PrimitiveKey.NumberKey) arrayKey.get(i)).getValueAsLong();
        }
        return jArr;
    }

    public static Key[] toPrimitiveKeys(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(bArr[i]);
        }
        return keyArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static short[] toShortValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(PrimitiveKey.ShortKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        short[] sArr = new short[size];
        for (int i = 0; i < size; i++) {
            sArr[i] = ((PrimitiveKey.ShortKey) arrayKey.get(i)).value();
        }
        return sArr;
    }

    public static Key[] toStringKeys(String[] strArr) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = StringKey.create(strArr[i]);
        }
        return keyArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String[] toStringValues(ArrayKey arrayKey) {
        if (!isAllTypeOf(StringKey.class, arrayKey)) {
            return null;
        }
        int size = arrayKey.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((StringKey) arrayKey.get(i)).getString();
        }
        return strArr;
    }

    public static Key[] toPrimitiveKeys(short[] sArr) {
        int length;
        if (sArr == null || (length = sArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(sArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(int[] iArr) {
        int length;
        if (iArr == null || (length = iArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(iArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(long[] jArr) {
        int length;
        if (jArr == null || (length = jArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(jArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(float[] fArr) {
        int length;
        if (fArr == null || (length = fArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(fArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(double[] dArr) {
        int length;
        if (dArr == null || (length = dArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(dArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(char[] cArr) {
        int length;
        if (cArr == null || (length = cArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(cArr[i]);
        }
        return keyArr;
    }

    public static Key[] toPrimitiveKeys(boolean[] zArr) {
        int length;
        if (zArr == null || (length = zArr.length) == 0) {
            return null;
        }
        Key[] keyArr = new Key[length];
        for (int i = 0; i < length; i++) {
            keyArr[i] = PrimitiveKey.of(zArr[i]);
        }
        return keyArr;
    }
}
