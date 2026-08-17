package org.bouncycastle.math.raw;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class Mont256 {
    private static final long M = 4294967295L;

    public static int inverse32(int i) {
        int i2 = (2 - (i * i)) * i;
        int i3 = i2 * (2 - (i * i2));
        int i4 = i3 * (2 - (i * i3));
        return i4 * (2 - (i * i4));
    }

    public static void multAdd(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i) {
        char c = 0;
        long j = 4294967295L;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        int i2 = 0;
        int i3 = 0;
        while (i2 < 8) {
            long j3 = ((long) iArr3[c]) & j;
            long j4 = ((long) iArr[i2]) & j;
            long j5 = j4 * j2;
            long j6 = (j5 & j) + j3;
            char c2 = c;
            long j7 = j;
            long j8 = ((long) (((int) j6) * i)) & j7;
            long j9 = (((long) iArr4[c2]) & j7) * j8;
            char c3 = ' ';
            long j10 = ((j6 + (j9 & j7)) >>> 32) + (j5 >>> 32) + (j9 >>> 32);
            int i4 = 1;
            while (i4 < 8) {
                long j11 = (((long) iArr2[i4]) & j7) * j4;
                char c4 = c3;
                long j12 = (((long) iArr4[i4]) & j7) * j8;
                long j13 = j10 + (j11 & j7) + (j12 & j7) + (((long) iArr3[i4]) & j7);
                iArr3[i4 - 1] = (int) j13;
                j10 = (j13 >>> c4) + (j11 >>> c4) + (j12 >>> c4);
                i4++;
                c3 = c4;
                j2 = j2;
                j8 = j8;
            }
            char c5 = c3;
            long j14 = j10 + (((long) i3) & j7);
            iArr3[7] = (int) j14;
            i3 = (int) (j14 >>> c5);
            i2++;
            c = c2;
            j = j7;
            j2 = j2;
        }
        if (i3 != 0 || Nat256.gte(iArr3, iArr4)) {
            Nat256.sub(iArr3, iArr4, iArr3);
        }
    }

    public static void multAddXF(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        char c = 0;
        long j = 4294967295L;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= 8) {
                break;
            }
            long j3 = ((long) iArr[i]) & j;
            long j4 = (j3 * j2) + (((long) iArr3[c]) & j);
            long j5 = j4 & j;
            long j6 = (j4 >>> 32) + j5;
            int i3 = 1;
            for (int i4 = 8; i3 < i4; i4 = 8) {
                long j7 = j;
                long j8 = (((long) iArr2[i3]) & j7) * j3;
                int i5 = i3;
                long j9 = (((long) iArr4[i3]) & j7) * j5;
                long j10 = j6 + (j8 & j7) + (j9 & j7) + (((long) iArr3[i5]) & j7);
                iArr3[i5 - 1] = (int) j10;
                j6 = (j10 >>> 32) + (j8 >>> 32) + (j9 >>> 32);
                i3 = i5 + 1;
                j = j7;
                j2 = j2;
            }
            long j11 = j6 + (((long) i2) & j);
            iArr3[7] = (int) j11;
            i2 = (int) (j11 >>> 32);
            i++;
            j2 = j2;
            c = 0;
        }
        if (i2 != 0 || Nat256.gte(iArr3, iArr4)) {
            Nat256.sub(iArr3, iArr4, iArr3);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2, int i) {
        char c = 0;
        int i2 = 0;
        while (i2 < 8) {
            int i3 = iArr[c];
            long j = ((long) (i3 * i)) & 4294967295L;
            long j2 = (((((long) iArr2[c]) & 4294967295L) * j) + (((long) i3) & 4294967295L)) >>> 32;
            int i4 = 1;
            while (i4 < 8) {
                long j3 = j2 + ((((long) iArr2[i4]) & 4294967295L) * j) + (((long) iArr[i4]) & 4294967295L);
                iArr[i4 - 1] = (int) j3;
                j2 = j3 >>> 32;
                i4++;
                i2 = i2;
            }
            iArr[7] = (int) j2;
            i2++;
            c = 0;
        }
        if (Nat256.gte(iArr, iArr2)) {
            Nat256.sub(iArr, iArr2, iArr);
        }
    }

    public static void reduceXF(int[] iArr, int[] iArr2) {
        for (int i = 0; i < 8; i++) {
            long j = ((long) iArr[0]) & 4294967295L;
            long j2 = j;
            for (int i2 = 1; i2 < 8; i2++) {
                long j3 = j2 + ((((long) iArr2[i2]) & 4294967295L) * j) + (((long) iArr[i2]) & 4294967295L);
                iArr[i2 - 1] = (int) j3;
                j2 = j3 >>> 32;
            }
            iArr[7] = (int) j2;
        }
        if (Nat256.gte(iArr, iArr2)) {
            Nat256.sub(iArr, iArr2, iArr);
        }
    }
}
