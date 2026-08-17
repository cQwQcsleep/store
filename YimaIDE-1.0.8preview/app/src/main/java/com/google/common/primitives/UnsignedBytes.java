package com.google.common.primitives;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = -128;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    public static final class LexicographicalComparatorHolder {
        static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName().concat("$UnsafeComparator");
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();

        public interface LexicographicalComparator extends Comparator<byte[]> {
            boolean isFunctional();
        }

        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                int iMin = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < iMin; i++) {
                    int iCompare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                }
                return bArr.length - bArr2.length;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }
        }

        public enum UnsafeComparator implements LexicographicalComparator {
            INSTANCE;

            static final int OFFSET_UNSAFE_APPROACH_IS_UNAVAILABLE = -1;
            static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            static final Unsafe theUnsafe = getUnsafe();
            static final int BYTE_ARRAY_BASE_OFFSET = getByteArrayBaseOffset();

            private static int getByteArrayBaseOffset() {
                Unsafe unsafe = theUnsafe;
                if (unsafe == null) {
                    return -1;
                }
                try {
                    int iArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
                    int iArrayIndexScale = unsafe.arrayIndexScale(byte[].class);
                    if (Objects.equals(System.getProperty("sun.arch.data.model"), "64") && iArrayBaseOffset % 8 == 0 && iArrayIndexScale == 1) {
                        return iArrayBaseOffset;
                    }
                    return -1;
                } catch (UnsupportedOperationException unused) {
                }
            }

            private static Unsafe getUnsafe() {
                try {
                    return Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    try {
                        PrivilegedExceptionAction privilegedExceptionAction = new PrivilegedExceptionAction() { // from class: com.google.common.primitives.a
                            @Override // java.security.PrivilegedExceptionAction
                            public final Object run() {
                                return UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.lambda$getUnsafe$0();
                            }
                        };
                        try {
                            return (Unsafe) Class.forName("java.security.AccessController").getMethod("doPrivileged", PrivilegedExceptionAction.class).invoke(null, privilegedExceptionAction);
                        } catch (Exception unused2) {
                            return (Unsafe) privilegedExceptionAction.run();
                        }
                    } catch (Exception unused3) {
                        return null;
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static /* synthetic */ Unsafe lambda$getUnsafe$0() throws Exception {
                for (Field field : Unsafe.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    if (Unsafe.class.isInstance(obj)) {
                        return (Unsafe) Unsafe.class.cast(obj);
                    }
                }
                return null;
            }

            @Override // java.util.Comparator
            public int compare(byte[] bArr, byte[] bArr2) {
                Unsafe unsafe = theUnsafe;
                Objects.requireNonNull(unsafe);
                int iMin = Math.min(bArr.length, bArr2.length);
                int i = iMin & (-8);
                int i2 = 0;
                while (i2 < i) {
                    int i3 = BYTE_ARRAY_BASE_OFFSET;
                    long j = i2;
                    long j2 = unsafe.getLong(bArr, ((long) i3) + j);
                    long j3 = unsafe.getLong(bArr2, ((long) i3) + j);
                    if (j2 != j3) {
                        if (BIG_ENDIAN) {
                            return Long.compareUnsigned(j2, j3);
                        }
                        int iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & (-8);
                        return ((int) ((j2 >>> iNumberOfTrailingZeros) & 255)) - ((int) (255 & (j3 >>> iNumberOfTrailingZeros)));
                    }
                    i2 += 8;
                }
                while (i2 < iMin) {
                    int iCompare = UnsignedBytes.compare(bArr[i2], bArr2[i2]);
                    if (iCompare != 0) {
                        return iCompare;
                    }
                    i2++;
                }
                return bArr.length - bArr2.length;
            }

            @Override // com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.LexicographicalComparator
            public boolean isFunctional() {
                return BYTE_ARRAY_BASE_OFFSET != -1;
            }

            @Override // java.lang.Enum
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }
        }

        private LexicographicalComparatorHolder() {
        }

        public static Comparator<byte[]> getBestComparator() {
            try {
                LexicographicalComparator[] lexicographicalComparatorArr = (LexicographicalComparator[]) Class.forName(UNSAFE_COMPARATOR_NAME).asSubclass(LexicographicalComparator.class).getEnumConstants();
                Objects.requireNonNull(lexicographicalComparatorArr);
                LexicographicalComparator lexicographicalComparator = lexicographicalComparatorArr[0];
                return lexicographicalComparator.isFunctional() ? lexicographicalComparator : UnsignedBytes.lexicographicalComparatorJavaImpl();
            } catch (Throwable unused) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    private UnsignedBytes() {
    }

    public static byte checkedCast(long j) {
        Preconditions.checkArgument((j >> 8) == 0, "out of range: %s", j);
        return (byte) j;
    }

    public static int compare(byte b, byte b2) {
        return Byte.toUnsignedInt(b) - Byte.toUnsignedInt(b2);
    }

    private static byte flip(byte b) {
        return (byte) (b ^ MAX_POWER_OF_TWO);
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * (str.length() + 3));
        sb.append(Byte.toUnsignedInt(bArr[0]));
        for (int i = 1; i < bArr.length; i++) {
            sb.append(str);
            sb.append(toString(bArr[i]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    public static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int unsignedInt = Byte.toUnsignedInt(bArr[0]);
        for (int i = 1; i < bArr.length; i++) {
            int unsignedInt2 = Byte.toUnsignedInt(bArr[i]);
            if (unsignedInt2 > unsignedInt) {
                unsignedInt = unsignedInt2;
            }
        }
        return (byte) unsignedInt;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int unsignedInt = Byte.toUnsignedInt(bArr[0]);
        for (int i = 1; i < bArr.length; i++) {
            int unsignedInt2 = Byte.toUnsignedInt(bArr[i]);
            if (unsignedInt2 < unsignedInt) {
                unsignedInt = unsignedInt2;
            }
        }
        return (byte) unsignedInt;
    }

    public static byte parseUnsignedByte(String str, int i) {
        int i2 = Integer.parseInt((String) Preconditions.checkNotNull(str), i);
        if ((i2 >> 8) == 0) {
            return (byte) i2;
        }
        bia.a("out of range: ", i2);
        return (byte) 0;
    }

    public static byte saturatedCast(long j) {
        if (j > Byte.toUnsignedInt((byte) -1)) {
            return (byte) -1;
        }
        if (j < 0) {
            return (byte) 0;
        }
        return (byte) j;
    }

    public static void sort(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i, i2, bArr.length);
        for (int i3 = i; i3 < i2; i3++) {
            bArr[i3] = flip(bArr[i3]);
        }
        Arrays.sort(bArr, i, i2);
        while (i < i2) {
            bArr[i] = flip(bArr[i]);
            i++;
        }
    }

    public static void sortDescending(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i, i2, bArr.length);
        for (int i3 = i; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ Ascii.DEL);
        }
        Arrays.sort(bArr, i, i2);
        while (i < i2) {
            bArr[i] = (byte) (bArr[i] ^ Ascii.DEL);
            i++;
        }
    }

    public static int toInt(byte b) {
        return Byte.toUnsignedInt(b);
    }

    public static String toString(byte b, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i);
        return Integer.toString(Byte.toUnsignedInt(b), i);
    }

    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    public static String toString(byte b) {
        return toString(b, 10);
    }

    public static void sortDescending(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sortDescending(bArr, 0, bArr.length);
    }

    public static void sort(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sort(bArr, 0, bArr.length);
    }
}
