package com.google.crypto.tink.subtle;

import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class Field25519 {
    static final int FIELD_LEN = 32;
    static final int LIMB_CNT = 10;
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;
    private static final int[] EXPAND_START = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] EXPAND_SHIFT = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] MASK = {67108863, 33554431};
    private static final int[] SHIFT = {26, 25};

    private Field25519() {
    }

    public static byte[] contract(long[] jArr) {
        int i;
        long[] jArrCopyOf = Arrays.copyOf(jArr, 10);
        int i2 = 0;
        while (true) {
            if (i2 >= 2) {
                break;
            }
            int i3 = 0;
            while (i3 < 9) {
                long j = jArrCopyOf[i3];
                int i4 = SHIFT[i3 & 1];
                int i5 = -((int) (((j >> 31) & j) >> i4));
                jArrCopyOf[i3] = j + ((long) (i5 << i4));
                i3++;
                jArrCopyOf[i3] = jArrCopyOf[i3] - ((long) i5);
            }
            long j2 = jArrCopyOf[9];
            int i6 = -((int) (((j2 >> 31) & j2) >> 25));
            jArrCopyOf[9] = j2 + ((long) (i6 << 25));
            jArrCopyOf[0] = jArrCopyOf[0] - ((long) (i6 * 19));
            i2++;
        }
        long j3 = jArrCopyOf[0];
        int i7 = -((int) (((j3 >> 31) & j3) >> 26));
        jArrCopyOf[0] = j3 + ((long) (i7 << 26));
        jArrCopyOf[1] = jArrCopyOf[1] - ((long) i7);
        for (int i8 = 0; i8 < 2; i8++) {
            int i9 = 0;
            while (i9 < 9) {
                long j4 = jArrCopyOf[i9];
                int i10 = i9 & 1;
                int i11 = (int) (j4 >> SHIFT[i10]);
                jArrCopyOf[i9] = j4 & ((long) MASK[i10]);
                i9++;
                jArrCopyOf[i9] = jArrCopyOf[i9] + ((long) i11);
            }
        }
        long j5 = jArrCopyOf[9];
        jArrCopyOf[9] = j5 & 33554431;
        long j6 = jArrCopyOf[0] + ((long) (((int) (j5 >> 25)) * 19));
        jArrCopyOf[0] = j6;
        int iGte = gte((int) j6, 67108845);
        for (int i12 = 1; i12 < 10; i12++) {
            iGte &= eq((int) jArrCopyOf[i12], MASK[i12 & 1]);
        }
        jArrCopyOf[0] = jArrCopyOf[0] - ((long) (67108845 & iGte));
        long j7 = 33554431 & iGte;
        jArrCopyOf[1] = jArrCopyOf[1] - j7;
        for (i = 2; i < 10; i += 2) {
            jArrCopyOf[i] = jArrCopyOf[i] - ((long) (67108863 & iGte));
            int i13 = i + 1;
            jArrCopyOf[i13] = jArrCopyOf[i13] - j7;
        }
        for (int i14 = 0; i14 < 10; i14++) {
            jArrCopyOf[i14] = jArrCopyOf[i14] << EXPAND_SHIFT[i14];
        }
        byte[] bArr = new byte[32];
        for (int i15 = 0; i15 < 10; i15++) {
            int i16 = EXPAND_START[i15];
            long j8 = bArr[i16];
            long j9 = jArrCopyOf[i15];
            bArr[i16] = (byte) (j8 | (j9 & 255));
            int i17 = i16 + 1;
            bArr[i17] = (byte) (((long) bArr[i17]) | ((j9 >> 8) & 255));
            int i18 = i16 + 2;
            bArr[i18] = (byte) (((long) bArr[i18]) | ((j9 >> 16) & 255));
            int i19 = i16 + 3;
            bArr[i19] = (byte) (((long) bArr[i19]) | ((j9 >> 24) & 255));
        }
        return bArr;
    }

    private static int eq(int i, int i2) {
        int i3 = ~(i ^ i2);
        int i4 = i3 & (i3 << 16);
        int i5 = i4 & (i4 << 8);
        int i6 = i5 & (i5 << 4);
        int i7 = i6 & (i6 << 2);
        return (i7 & (i7 << 1)) >> 31;
    }

    public static long[] expand(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i = 0; i < 10; i++) {
            int i2 = EXPAND_START[i];
            jArr[i] = ((((((long) (bArr[i2] & UnsignedBytes.MAX_VALUE)) | (((long) (bArr[i2 + 1] & UnsignedBytes.MAX_VALUE)) << 8)) | (((long) (bArr[i2 + 2] & UnsignedBytes.MAX_VALUE)) << 16)) | (((long) (bArr[i2 + 3] & UnsignedBytes.MAX_VALUE)) << 24)) >> EXPAND_SHIFT[i]) & ((long) MASK[i & 1]);
        }
        return jArr;
    }

    private static int gte(int i, int i2) {
        return ~((i - i2) >> 31);
    }

    public static void inverse(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        square(jArr3, jArr2);
        square(jArr12, jArr3);
        square(jArr11, jArr12);
        mult(jArr4, jArr11, jArr2);
        mult(jArr5, jArr4, jArr3);
        square(jArr11, jArr5);
        mult(jArr6, jArr11, jArr4);
        square(jArr11, jArr6);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        mult(jArr7, jArr11, jArr6);
        square(jArr11, jArr7);
        square(jArr12, jArr11);
        for (int i = 2; i < 10; i += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr8, jArr12, jArr7);
        square(jArr11, jArr8);
        square(jArr12, jArr11);
        for (int i2 = 2; i2 < 20; i2 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr8);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        for (int i3 = 2; i3 < 10; i3 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr9, jArr11, jArr7);
        square(jArr11, jArr9);
        square(jArr12, jArr11);
        for (int i4 = 2; i4 < 50; i4 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr10, jArr12, jArr9);
        square(jArr12, jArr10);
        square(jArr11, jArr12);
        for (int i5 = 2; i5 < 100; i5 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr12, jArr11, jArr10);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        for (int i6 = 2; i6 < 50; i6 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr9);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        mult(jArr, jArr12, jArr5);
    }

    public static void mult(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        product(jArr4, jArr2, jArr3);
        reduce(jArr4, jArr);
    }

    public static void product(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        long j = jArr2[0];
        long j2 = jArr3[1] * j;
        long j3 = jArr2[1];
        long j4 = jArr3[0];
        jArr[1] = j2 + (j3 * j4);
        long j5 = jArr2[1];
        long j6 = jArr3[1];
        jArr[2] = (j5 * 2 * j6) + (jArr3[2] * j) + (jArr2[2] * j4);
        long j7 = jArr3[2];
        long j8 = jArr2[2];
        jArr[3] = (j5 * j7) + (j8 * j6) + (jArr3[3] * j) + (jArr2[3] * j4);
        long j9 = jArr3[3];
        long j10 = jArr2[3];
        jArr[4] = (j8 * j7) + (((j5 * j9) + (j10 * j6)) * 2) + (jArr3[4] * j) + (jArr2[4] * j4);
        long j11 = jArr3[4];
        long j12 = jArr2[4];
        jArr[5] = (j8 * j9) + (j10 * j7) + (j5 * j11) + (j12 * j6) + (jArr3[5] * j) + (jArr2[5] * j4);
        long j13 = jArr3[5];
        long j14 = jArr2[5];
        jArr[6] = (((j10 * j9) + (j5 * j13) + (j14 * j6)) * 2) + (j8 * j11) + (j12 * j7) + (jArr3[6] * j) + (jArr2[6] * j4);
        long j15 = jArr3[6];
        long j16 = jArr2[6];
        jArr[7] = (j10 * j11) + (j12 * j9) + (j8 * j13) + (j14 * j7) + (j5 * j15) + (j16 * j6) + (jArr3[7] * j) + (jArr2[7] * j4);
        long j17 = jArr3[7];
        long j18 = jArr2[7];
        jArr[8] = (j12 * j11) + (((j10 * j13) + (j14 * j9) + (j5 * j17) + (j18 * j6)) * 2) + (j8 * j15) + (j16 * j7) + (jArr3[8] * j) + (jArr2[8] * j4);
        long j19 = jArr3[8];
        long j20 = jArr2[8];
        jArr[9] = (j12 * j13) + (j14 * j11) + (j10 * j15) + (j16 * j9) + (j8 * j17) + (j18 * j7) + (j5 * j19) + (j20 * j6) + (j * jArr3[9]) + (jArr2[9] * j4);
        long j21 = jArr3[9];
        long j22 = jArr2[9];
        jArr[10] = (((j14 * j13) + (j10 * j17) + (j18 * j9) + (j5 * j21) + (j6 * j22)) * 2) + (j12 * j15) + (j16 * j11) + (j8 * j19) + (j20 * j7);
        jArr[11] = (j14 * j15) + (j16 * j13) + (j12 * j17) + (j18 * j11) + (j10 * j19) + (j20 * j9) + (j8 * j21) + (j7 * j22);
        jArr[12] = (j16 * j15) + (((j14 * j17) + (j18 * j13) + (j10 * j21) + (j9 * j22)) * 2) + (j12 * j19) + (j20 * j11);
        jArr[13] = (j16 * j17) + (j18 * j15) + (j14 * j19) + (j20 * j13) + (j12 * j21) + (j11 * j22);
        jArr[14] = (((j18 * j17) + (j14 * j21) + (j13 * j22)) * 2) + (j16 * j19) + (j20 * j15);
        jArr[15] = (j18 * j19) + (j20 * j17) + (j16 * j21) + (j15 * j22);
        jArr[16] = (j20 * j19) + (((j18 * j21) + (j17 * j22)) * 2);
        jArr[17] = (j20 * j21) + (j19 * j22);
        jArr[18] = j22 * 2 * j21;
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        reduceSizeByModularReduction(jArr);
        reduceCoefficients(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    public static void reduceCoefficients(long[] jArr) {
        jArr[10] = 0;
        int i = 0;
        while (i < 10) {
            long j = jArr[i];
            long j2 = j / TWO_TO_26;
            jArr[i] = j - (j2 << 26);
            int i2 = i + 1;
            long j3 = jArr[i2] + j2;
            jArr[i2] = j3;
            long j4 = j3 / TWO_TO_25;
            jArr[i2] = j3 - (j4 << 25);
            i += 2;
            jArr[i] = jArr[i] + j4;
        }
        long j5 = jArr[0];
        long j6 = jArr[10];
        long j7 = j5 + (j6 << 4);
        jArr[0] = j7;
        long j8 = j7 + (j6 << 1);
        jArr[0] = j8;
        long j9 = j8 + j6;
        jArr[0] = j9;
        jArr[10] = 0;
        long j10 = j9 / TWO_TO_26;
        jArr[0] = j9 - (j10 << 26);
        jArr[1] = jArr[1] + j10;
    }

    public static void reduceSizeByModularReduction(long[] jArr) {
        long j = jArr[8];
        long j2 = jArr[18];
        long j3 = j + (j2 << 4);
        jArr[8] = j3;
        long j4 = j3 + (j2 << 1);
        jArr[8] = j4;
        jArr[8] = j4 + j2;
        long j5 = jArr[7];
        long j6 = jArr[17];
        long j7 = j5 + (j6 << 4);
        jArr[7] = j7;
        long j8 = j7 + (j6 << 1);
        jArr[7] = j8;
        jArr[7] = j8 + j6;
        long j9 = jArr[6];
        long j10 = jArr[16];
        long j11 = j9 + (j10 << 4);
        jArr[6] = j11;
        long j12 = j11 + (j10 << 1);
        jArr[6] = j12;
        jArr[6] = j12 + j10;
        long j13 = jArr[5];
        long j14 = jArr[15];
        long j15 = j13 + (j14 << 4);
        jArr[5] = j15;
        long j16 = j15 + (j14 << 1);
        jArr[5] = j16;
        jArr[5] = j16 + j14;
        long j17 = jArr[4];
        long j18 = jArr[14];
        long j19 = j17 + (j18 << 4);
        jArr[4] = j19;
        long j20 = j19 + (j18 << 1);
        jArr[4] = j20;
        jArr[4] = j20 + j18;
        long j21 = jArr[3];
        long j22 = jArr[13];
        long j23 = j21 + (j22 << 4);
        jArr[3] = j23;
        long j24 = j23 + (j22 << 1);
        jArr[3] = j24;
        jArr[3] = j24 + j22;
        long j25 = jArr[2];
        long j26 = jArr[12];
        long j27 = j25 + (j26 << 4);
        jArr[2] = j27;
        long j28 = j27 + (j26 << 1);
        jArr[2] = j28;
        jArr[2] = j28 + j26;
        long j29 = jArr[1];
        long j30 = jArr[11];
        long j31 = j29 + (j30 << 4);
        jArr[1] = j31;
        long j32 = j31 + (j30 << 1);
        jArr[1] = j32;
        jArr[1] = j32 + j30;
        long j33 = jArr[0];
        long j34 = jArr[10];
        long j35 = j33 + (j34 << 4);
        jArr[0] = j35;
        long j36 = j35 + (j34 << 1);
        jArr[0] = j36;
        jArr[0] = j36 + j34;
    }

    public static void scalarProduct(long[] jArr, long[] jArr2, long j) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] * j;
        }
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[19];
        squareInner(jArr3, jArr2);
        reduce(jArr3, jArr);
    }

    private static void squareInner(long[] jArr, long[] jArr2) {
        long j = jArr2[0];
        jArr[0] = j * j;
        long j2 = jArr2[0];
        jArr[1] = j2 * 2 * jArr2[1];
        long j3 = jArr2[1];
        jArr[2] = ((j3 * j3) + (jArr2[2] * j2)) * 2;
        long j4 = jArr2[2];
        jArr[3] = ((j3 * j4) + (jArr2[3] * j2)) * 2;
        long j5 = jArr2[3];
        jArr[4] = (j4 * j4) + (j3 * 4 * j5) + (j2 * 2 * jArr2[4]);
        long j6 = jArr2[4];
        jArr[5] = ((j4 * j5) + (j3 * j6) + (jArr2[5] * j2)) * 2;
        long j7 = (j5 * j5) + (j4 * j6) + (jArr2[6] * j2);
        long j8 = jArr2[5];
        jArr[6] = (j7 + (j3 * 2 * j8)) * 2;
        long j9 = jArr2[6];
        jArr[7] = ((j5 * j6) + (j4 * j8) + (j3 * j9) + (jArr2[7] * j2)) * 2;
        long j10 = (j4 * j9) + (jArr2[8] * j2);
        long j11 = jArr2[7];
        jArr[8] = (j6 * j6) + ((j10 + (((j3 * j11) + (j5 * j8)) * 2)) * 2);
        long j12 = jArr2[8];
        jArr[9] = ((j6 * j8) + (j5 * j9) + (j4 * j11) + (j3 * j12) + (j2 * jArr2[9])) * 2;
        long j13 = jArr2[9];
        jArr[10] = ((j8 * j8) + (j6 * j9) + (j4 * j12) + (((j5 * j11) + (j3 * j13)) * 2)) * 2;
        jArr[11] = ((j8 * j9) + (j6 * j11) + (j5 * j12) + (j4 * j13)) * 2;
        jArr[12] = (j9 * j9) + (((j6 * j12) + (((j8 * j11) + (j5 * j13)) * 2)) * 2);
        jArr[13] = ((j9 * j11) + (j8 * j12) + (j6 * j13)) * 2;
        jArr[14] = ((j11 * j11) + (j9 * j12) + (j8 * 2 * j13)) * 2;
        jArr[15] = ((j11 * j12) + (j9 * j13)) * 2;
        jArr[16] = (j12 * j12) + (j11 * 4 * j13);
        jArr[17] = j12 * 2 * j13;
        jArr[18] = 2 * j13 * j13;
    }

    public static void sub(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] - jArr3[i];
        }
    }

    public static void sum(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] + jArr3[i];
        }
    }

    public static void sub(long[] jArr, long[] jArr2) {
        sub(jArr, jArr2, jArr);
    }

    public static void sum(long[] jArr, long[] jArr2) {
        sum(jArr, jArr, jArr2);
    }
}
