package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NumbersUtil {
    public static int abs(int i) {
        return i < 0 ? -i : i;
    }

    public static int countBits(long j) {
        int i = 0;
        while (j != 0) {
            j >>>= 1;
            i++;
        }
        return i;
    }

    public static int getUInt(int i, int i2, int i3) {
        return (i >> ((i2 - i3) + 1)) & ((1 << i3) - 1);
    }

    public static int max(int i, int i2) {
        return i > i2 ? i : i2;
    }

    public static long maxValue(int i) {
        if (i <= 0) {
            return 0L;
        }
        return (1 << (i - 1)) - 1;
    }

    public static int min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public static long minValue(int i) {
        if (i <= 0) {
            return 0L;
        }
        return -(1 << (i - 1));
    }

    public static int minimumBitsForSigned(long j) {
        int i;
        if (j < 0) {
            j = -j;
            i = 0;
        } else {
            i = 1;
        }
        int iCountBits = i + countBits(j);
        return (j <= (1 << (iCountBits + (-1))) || iCountBits >= 64) ? iCountBits : iCountBits + 1;
    }

    public static int minimumBitsForUnSigned(long j) {
        if (j == 0) {
            return 1;
        }
        return countBits(j);
    }

    public static int minimumBytesForSigned(long j) {
        int iMinimumBitsForSigned = minimumBitsForSigned(j);
        int i = iMinimumBitsForSigned / 8;
        return (iMinimumBitsForSigned & 7) != 0 ? i + 1 : i;
    }

    public static int setUInt(int i, int i2, int i3, int i4) {
        int i5 = (1 << i4) - 1;
        int i6 = (i3 - i4) + 1;
        return (i & (~(i5 << i6))) | ((i2 & i5) << i6);
    }

    public static String toBinaryString(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("0b");
        String binaryString = Integer.toBinaryString(i);
        for (int length = 32 - binaryString.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(binaryString);
        return sb.toString();
    }

    public static long toSigned(int i, long j) {
        if (j == 0 || i <= 0) {
            return 0L;
        }
        if (i > 63) {
            return j;
        }
        long j2 = 1 << (i - 1);
        long j3 = j2 << 1;
        long j4 = j & (j3 - 1);
        return j4 < j2 ? j4 : j4 - j3;
    }

    public static int toSignedInt(int i, int i2) {
        if (i2 == 0 || i <= 0) {
            return 0;
        }
        if (i > 31) {
            return i2;
        }
        int i3 = 1 << i;
        int i4 = i2 & (i3 - 1);
        return i4 < (1 << (i - 1)) ? i4 : i4 - i3;
    }
}
