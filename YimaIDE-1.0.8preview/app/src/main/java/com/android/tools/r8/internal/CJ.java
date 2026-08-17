package com.android.tools.r8.internal;

import com.android.tools.r8.dex.AbstractC0150m;
import com.android.tools.r8.dex.C0161y;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CJ {
    public static final /* synthetic */ boolean a = true;

    public static byte[] a(int i) {
        byte[] bArr = new byte[5];
        int i2 = i >> 7;
        int i3 = i >= 0 ? 0 : -1;
        int i4 = i;
        int i5 = i2;
        int i6 = 0;
        boolean z = true;
        while (z) {
            z = (i5 == i3 && (i5 & 1) == ((i4 >> 6) & 1)) ? false : true;
            int i7 = i6 + 1;
            bArr[i6] = (byte) ((i4 & 127) | (z ? 128 : 0));
            i4 = i5;
            i5 >>= 7;
            i6 = i7;
        }
        return Arrays.copyOf(bArr, i6);
    }

    public static int b(AbstractC0150m abstractC0150m) {
        byte b;
        int i = 0;
        int i2 = 0;
        do {
            b = abstractC0150m.b();
            i |= (b & 127) << i2;
            i2 += 7;
        } while ((b & (-128)) == -128);
        boolean z = a;
        if (!z && i2 > 35) {
            x1f.a();
            return 0;
        }
        if (z || i >= 0) {
            return i;
        }
        x1f.a();
        return 0;
    }

    public static int c(int i) {
        if (i < 0) {
            i = ~i;
        }
        return (39 - Integer.numberOfLeadingZeros(i)) / 7;
    }

    public static int d(int i) {
        return Math.max(1, (38 - Integer.numberOfLeadingZeros(i)) / 7);
    }

    public static byte[] b(int i) {
        byte[] bArr = new byte[5];
        int i2 = 0;
        int i3 = i;
        int i4 = i >>> 7;
        while (i4 != 0) {
            bArr[i2] = (byte) ((i3 & 127) | 128);
            i3 = i4;
            i4 >>>= 7;
            i2++;
        }
        bArr[i2] = (byte) (i3 & 127);
        return Arrays.copyOf(bArr, i2 + 1);
    }

    public static void b(C0161y c0161y, int i) {
        while (true) {
            int i2 = i;
            i >>>= 7;
            if (i != 0) {
                c0161y.a((byte) ((i2 & 127) | 128));
            } else {
                c0161y.a((byte) (i2 & 127));
                return;
            }
        }
    }

    public static int a(AbstractC0150m abstractC0150m) {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte b = abstractC0150m.b();
            i2 |= (b & 127) << i3;
            i = i3 + 7;
            if ((b & (-128)) != -128) {
                break;
            }
            i3 = i;
        }
        int i4 = 1 << (i3 + 6);
        if (a || i <= 35) {
            return (i2 ^ i4) - i4;
        }
        x1f.a();
        return 0;
    }

    public static void a(C0161y c0161y, int i) {
        int i2 = i >> 7;
        int i3 = (Integer.MIN_VALUE & i) == 0 ? 0 : -1;
        int i4 = i;
        int i5 = i2;
        boolean z = true;
        while (z) {
            z = (i5 == i3 && (i5 & 1) == ((i4 >> 6) & 1)) ? false : true;
            c0161y.a((byte) ((i4 & 127) | (z ? 128 : 0)));
            i4 = i5;
            i5 >>= 7;
        }
    }
}
