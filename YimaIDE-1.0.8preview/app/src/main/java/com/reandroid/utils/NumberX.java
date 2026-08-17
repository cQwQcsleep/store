package com.reandroid.utils;

import defpackage.bia;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NumberX extends Number implements Comparable<Number> {
    private final long value;
    private final int width;

    private NumberX(int i, long j) {
        if (i < 1 || i > 8) {
            bia.a("Width out of range [1 ... 8], ", i);
            throw null;
        }
        this.width = i;
        this.value = j;
    }

    public static long encodeUnSigned(int i, long j) {
        return ((j >>> ((i + (-1)) * 8)) & 255) < 128 ? j : j | (~((-1) >>> ((16 - i) * 8)));
    }

    private static int hexWidth(long j) {
        if (j == 0) {
            return 1;
        }
        int i = 0;
        while (j != 0) {
            j >>>= 4;
            i++;
        }
        return i;
    }

    public static long maxValueForWidth(int i) {
        return ~minValueForWidth(i);
    }

    public static long minValueForWidth(int i) {
        return -(128 << ((i - 1) * 8));
    }

    private static byte toHexChar(int i) {
        int i2;
        if (i < 0) {
            return (byte) 0;
        }
        if (i < 10) {
            i2 = i + 48;
        } else {
            if (i > 16) {
                return (byte) 0;
            }
            i2 = i + 87;
        }
        return (byte) i2;
    }

    public static String toHexString(int i, long j) {
        boolean z;
        int i2;
        if (i == 0) {
            i = 1;
        }
        if (j < 0) {
            j = -j;
            z = true;
        } else {
            z = false;
        }
        int iMin = NumbersUtil.min(i * 2, hexWidth(j));
        int i3 = iMin + 2;
        if (z) {
            i3 = iMin + 3;
        }
        byte[] bArr = new byte[i3];
        if (z) {
            bArr[0] = 45;
            i2 = 1;
        } else {
            i2 = 0;
        }
        bArr[i2] = 48;
        bArr[i2 + 1] = 120;
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < iMin; i5++) {
            bArr[i4] = toHexChar((int) (15 & j));
            j >>>= 4;
            i4--;
        }
        return new String(bArr);
    }

    public static int toStandardWidth(int i) {
        if (i == 3) {
            return 4;
        }
        if (i == 5 || i == 6 || i == 7) {
            return 8;
        }
        return i;
    }

    public static NumberX valueOf(long j) {
        return new NumberX(widthOfSigned(j), j);
    }

    public static NumberX valueOfUnsigned(int i, long j) {
        return new NumberX(i, encodeUnSigned(i, j));
    }

    public static int widthOfSigned(long j) {
        int i = 1;
        if (j < 0) {
            while (minValueForWidth(i) > j) {
                i++;
            }
            return i;
        }
        if (j > 0) {
            while (maxValueForWidth(i) < j) {
                i++;
            }
        }
        return i;
    }

    @Override // java.lang.Comparable
    public int compareTo(Number number) {
        return Long.compare(longValue(), number.longValue());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.longBitsToDouble(longValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Number) && longValue() == ((Number) obj).longValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.intBitsToFloat(intValue());
    }

    public int hashCode() {
        return Long.hashCode(longValue());
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) longValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public String toHex() {
        return toHexString(width(), longValue());
    }

    public String toString() {
        return Long.toString(longValue());
    }

    public long unsigned() {
        long jLongValue = longValue();
        int iWidth = width();
        return iWidth == 8 ? jLongValue : jLongValue & ((-1) >>> ((8 - iWidth) * 8));
    }

    public int width() {
        return this.width;
    }

    public static NumberX valueOf(int i, long j) {
        return new NumberX(i, j);
    }
}
