package com.android.tools.r8.internal;

import com.android.tools.r8.dex.C0161y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ln, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2033ln {
    public static final /* synthetic */ boolean a = true;

    public static byte[] a(float f) {
        long jFloatToIntBits = ((long) Float.floatToIntBits(f)) << 32;
        int iNumberOfTrailingZeros = ((63 - Long.numberOfTrailingZeros(jFloatToIntBits)) / 8) + 1;
        long j = jFloatToIntBits >> (64 - (iNumberOfTrailingZeros * 8));
        byte[] bArr = new byte[iNumberOfTrailingZeros];
        for (int i = 0; i < iNumberOfTrailingZeros; i++) {
            bArr[i] = (byte) j;
            j >>= 8;
        }
        if (a || iNumberOfTrailingZeros <= 4) {
            return bArr;
        }
        x1f.a();
        return null;
    }

    public static float b(com.android.tools.r8.dex.D d, int i) {
        return Float.intBitsToFloat((int) (d(d, i) << ((4 - i) * 8)));
    }

    public static long c(com.android.tools.r8.dex.D d, int i) {
        if (!a && i <= 0) {
            x1f.a();
            return 0L;
        }
        long jB = 0;
        int i2 = 0;
        for (int i3 = 1; i3 < i; i3++) {
            jB |= ((long) (d.b() & 255)) << i2;
            i2 += 8;
        }
        return (((long) d.b()) << i2) | jB;
    }

    public static long d(com.android.tools.r8.dex.D d, int i) {
        if (!a && i <= 0) {
            x1f.a();
            return 0L;
        }
        long jB = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            jB |= ((long) (d.b() & 255)) << i2;
            i2 += 8;
        }
        return jB;
    }

    public static int a(C0161y c0161y, float f) {
        return a(c0161y, ((long) Float.floatToIntBits(f)) << 32, 4);
    }

    public static int a(C0161y c0161y, long j, int i) {
        int iNumberOfTrailingZeros = ((63 - Long.numberOfTrailingZeros(j)) / 8) + 1;
        if (!a && (iNumberOfTrailingZeros <= 0 || iNumberOfTrailingZeros > i)) {
            x1f.a();
            return 0;
        }
        long j2 = j >> (64 - (iNumberOfTrailingZeros * 8));
        for (int i2 = 0; i2 < iNumberOfTrailingZeros; i2++) {
            c0161y.a((byte) j2);
            j2 >>= 8;
        }
        return iNumberOfTrailingZeros;
    }

    public static double a(com.android.tools.r8.dex.D d, int i) {
        return Double.longBitsToDouble(d(d, i) << ((8 - i) * 8));
    }

    public static int a(C0161y c0161y, double d) {
        return a(c0161y, Double.doubleToLongBits(d), 8);
    }

    public static byte[] a(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        int iNumberOfTrailingZeros = ((63 - Long.numberOfTrailingZeros(jDoubleToLongBits)) / 8) + 1;
        long j = jDoubleToLongBits >> (64 - (iNumberOfTrailingZeros * 8));
        byte[] bArr = new byte[iNumberOfTrailingZeros];
        for (int i = 0; i < iNumberOfTrailingZeros; i++) {
            bArr[i] = (byte) j;
            j >>= 8;
        }
        if (a || iNumberOfTrailingZeros <= 8) {
            return bArr;
        }
        x1f.a();
        return null;
    }
}
