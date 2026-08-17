package io.vavr.collection;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
interface ArrayType<T> extends Serializable {
    public static final long serialVersionUID = 1;

    static Object[] asArray(java.util.Iterator<?> it2, int i) {
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = it2.next();
        }
        return objArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> T asPrimitives(Class<?> cls, Iterable<?> iterable) {
        Object[] javaArray = Array.ofAll(iterable).toJavaArray();
        ArrayType arrayTypeOf = of((Class) cls);
        T t = (T) arrayTypeOf.newInstance(javaArray.length);
        for (int i = 0; i < javaArray.length; i++) {
            arrayTypeOf.setAt(t, i, javaArray[i]);
        }
        return t;
    }

    static <T> ArrayType<T> obj() {
        return ObjectArrayType.INSTANCE;
    }

    static <T> ArrayType<T> of(Class<T> cls) {
        return !cls.isPrimitive() ? obj() : ofPrimitive(cls);
    }

    static <T> ArrayType<T> ofPrimitive(Class<T> cls) {
        if (Boolean.TYPE == cls) {
            return BooleanArrayType.INSTANCE;
        }
        if (Byte.TYPE == cls) {
            return ByteArrayType.INSTANCE;
        }
        if (Character.TYPE == cls) {
            return CharArrayType.INSTANCE;
        }
        if (Double.TYPE == cls) {
            return DoubleArrayType.INSTANCE;
        }
        if (Float.TYPE == cls) {
            return FloatArrayType.INSTANCE;
        }
        if (Integer.TYPE == cls) {
            return IntArrayType.INSTANCE;
        }
        if (Long.TYPE == cls) {
            return LongArrayType.INSTANCE;
        }
        if (Short.TYPE == cls) {
            return ShortArrayType.INSTANCE;
        }
        throw new IllegalArgumentException(String.valueOf(cls));
    }

    default Object copy(Object obj, int i) {
        int iLengthOf = lengthOf(obj);
        return copy(obj, Math.max(iLengthOf, i), 0, 0, iLengthOf);
    }

    Object copy(Object obj, int i, int i2, int i3, int i4);

    default Object copyDrop(Object obj, int i) {
        int iLengthOf = lengthOf(obj);
        return copy(obj, iLengthOf, i, i, iLengthOf - i);
    }

    default Object copyRange(Object obj, int i, int i2) {
        int i3 = i2 - i;
        return copy(obj, i3, i, 0, i3);
    }

    default Object copyTake(Object obj, int i) {
        return copyRange(obj, 0, i + 1);
    }

    default Object copyUpdate(Object obj, int i, T t) {
        Object objCopy = copy(obj, i + 1);
        setAt(objCopy, i, t);
        return objCopy;
    }

    Object empty();

    T getAt(Object obj, int i);

    /* JADX WARN: Multi-variable type inference failed */
    default Object grouped(Object obj, int i) {
        int iLengthOf = lengthOf(obj);
        Object objNewInstance = obj().newInstance(((iLengthOf - 1) / i) + 1);
        obj().setAt(objNewInstance, 0, copyRange(obj, 0, i));
        int i2 = i;
        int i3 = 1;
        while (i2 < iLengthOf) {
            int iMin = Math.min(i, iLengthOf - (i3 * i)) + i2;
            obj().setAt(objNewInstance, i3, copyRange(obj, i2, iMin));
            i3++;
            i2 = iMin;
        }
        return objNewInstance;
    }

    int lengthOf(Object obj);

    default Object newInstance(int i) {
        return copy(empty(), i);
    }

    void setAt(Object obj, int i, T t) throws ClassCastException;

    Class<T> type();

    public static final class BooleanArrayType implements ArrayType<Boolean>, Serializable {
        private static final long serialVersionUID = 1;
        static final BooleanArrayType INSTANCE = new BooleanArrayType();
        static final boolean[] EMPTY = new boolean[0];

        private static boolean[] cast(Object obj) {
            return (boolean[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            boolean[] zArr = new boolean[i];
            System.arraycopy(obj, i2, zArr, i3, i4);
            return zArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new boolean[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Boolean getAt(Object obj, int i) {
            return Boolean.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Boolean bool) throws ClassCastException {
            if (bool == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = bool.booleanValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Boolean> type() {
            return Boolean.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public boolean[] empty() {
            return EMPTY;
        }
    }

    public static final class ByteArrayType implements ArrayType<Byte>, Serializable {
        private static final long serialVersionUID = 1;
        static final ByteArrayType INSTANCE = new ByteArrayType();
        static final byte[] EMPTY = new byte[0];

        private static byte[] cast(Object obj) {
            return (byte[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            byte[] bArr = new byte[i];
            System.arraycopy(obj, i2, bArr, i3, i4);
            return bArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new byte[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Byte getAt(Object obj, int i) {
            return Byte.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Byte b) throws ClassCastException {
            if (b == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = b.byteValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Byte> type() {
            return Byte.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public byte[] empty() {
            return EMPTY;
        }
    }

    public static final class CharArrayType implements ArrayType<Character>, Serializable {
        private static final long serialVersionUID = 1;
        static final CharArrayType INSTANCE = new CharArrayType();
        static final char[] EMPTY = new char[0];

        private static char[] cast(Object obj) {
            return (char[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            char[] cArr = new char[i];
            System.arraycopy(obj, i2, cArr, i3, i4);
            return cArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new char[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Character getAt(Object obj, int i) {
            return Character.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Character ch) throws ClassCastException {
            if (ch == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = ch.charValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Character> type() {
            return Character.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public char[] empty() {
            return EMPTY;
        }
    }

    public static final class DoubleArrayType implements ArrayType<Double>, Serializable {
        private static final long serialVersionUID = 1;
        static final DoubleArrayType INSTANCE = new DoubleArrayType();
        static final double[] EMPTY = new double[0];

        private static double[] cast(Object obj) {
            return (double[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            double[] dArr = new double[i];
            System.arraycopy(obj, i2, dArr, i3, i4);
            return dArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new double[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Double getAt(Object obj, int i) {
            return Double.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Double d) throws ClassCastException {
            if (d == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = d.doubleValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Double> type() {
            return Double.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public double[] empty() {
            return EMPTY;
        }
    }

    public static final class FloatArrayType implements ArrayType<Float>, Serializable {
        private static final long serialVersionUID = 1;
        static final FloatArrayType INSTANCE = new FloatArrayType();
        static final float[] EMPTY = new float[0];

        private static float[] cast(Object obj) {
            return (float[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            float[] fArr = new float[i];
            System.arraycopy(obj, i2, fArr, i3, i4);
            return fArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new float[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Float getAt(Object obj, int i) {
            return Float.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Float f) throws ClassCastException {
            if (f == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = f.floatValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Float> type() {
            return Float.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public float[] empty() {
            return EMPTY;
        }
    }

    public static final class IntArrayType implements ArrayType<Integer>, Serializable {
        private static final long serialVersionUID = 1;
        static final IntArrayType INSTANCE = new IntArrayType();
        static final int[] EMPTY = new int[0];

        private static int[] cast(Object obj) {
            return (int[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            int[] iArr = new int[i];
            System.arraycopy(obj, i2, iArr, i3, i4);
            return iArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new int[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Integer getAt(Object obj, int i) {
            return Integer.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Integer num) throws ClassCastException {
            if (num == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = num.intValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Integer> type() {
            return Integer.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public int[] empty() {
            return EMPTY;
        }
    }

    public static final class LongArrayType implements ArrayType<Long>, Serializable {
        private static final long serialVersionUID = 1;
        static final LongArrayType INSTANCE = new LongArrayType();
        static final long[] EMPTY = new long[0];

        private static long[] cast(Object obj) {
            return (long[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            long[] jArr = new long[i];
            System.arraycopy(obj, i2, jArr, i3, i4);
            return jArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new long[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.vavr.collection.ArrayType
        public Long getAt(Object obj, int i) {
            return Long.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Long l) throws ClassCastException {
            if (l == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = l.longValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Long> type() {
            return Long.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public long[] empty() {
            return EMPTY;
        }
    }

    public static final class ObjectArrayType implements ArrayType<Object>, Serializable {
        private static final long serialVersionUID = 1;
        static final ObjectArrayType INSTANCE = new ObjectArrayType();
        static final Object[] EMPTY = new Object[0];

        private static Object[] cast(Object obj) {
            return (Object[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            Object[] objArr = new Object[i];
            System.arraycopy(obj, i2, objArr, i3, i4);
            return objArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new Object[i];
        }

        @Override // io.vavr.collection.ArrayType
        public Object getAt(Object obj, int i) {
            return cast(obj)[i];
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Object obj2) {
            cast(obj)[i] = obj2;
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Object> type() {
            return Object.class;
        }

        @Override // io.vavr.collection.ArrayType
        public Object[] empty() {
            return EMPTY;
        }
    }

    public static final class ShortArrayType implements ArrayType<Short>, Serializable {
        private static final long serialVersionUID = 1;
        static final ShortArrayType INSTANCE = new ShortArrayType();
        static final short[] EMPTY = new short[0];

        private static short[] cast(Object obj) {
            return (short[]) obj;
        }

        private static Object copyNonEmpty(Object obj, int i, int i2, int i3, int i4) {
            short[] sArr = new short[i];
            System.arraycopy(obj, i2, sArr, i3, i4);
            return sArr;
        }

        @Override // io.vavr.collection.ArrayType
        public Object copy(Object obj, int i, int i2, int i3, int i4) {
            return i4 > 0 ? copyNonEmpty(obj, i, i2, i3, i4) : new short[i];
        }

        @Override // io.vavr.collection.ArrayType
        public Short getAt(Object obj, int i) {
            return Short.valueOf(cast(obj)[i]);
        }

        @Override // io.vavr.collection.ArrayType
        public int lengthOf(Object obj) {
            if (obj != null) {
                return cast(obj).length;
            }
            return 0;
        }

        @Override // io.vavr.collection.ArrayType
        public void setAt(Object obj, int i, Short sh) throws ClassCastException {
            if (sh == null) {
                throw new ClassCastException();
            }
            cast(obj)[i] = sh.shortValue();
        }

        @Override // io.vavr.collection.ArrayType
        public Class<Short> type() {
            return Short.TYPE;
        }

        @Override // io.vavr.collection.ArrayType
        public short[] empty() {
            return EMPTY;
        }
    }

    default Object asArray(T t) {
        Object objNewInstance = newInstance(1);
        setAt(objNewInstance, 0, t);
        return objNewInstance;
    }

    static <T> ArrayType<T> of(Object obj) {
        return of((Class) obj.getClass().getComponentType());
    }
}
