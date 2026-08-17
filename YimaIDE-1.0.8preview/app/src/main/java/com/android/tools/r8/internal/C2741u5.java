package com.android.tools.r8.internal;

import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2741u5 {
    public final String a;
    public final char[] b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final byte[] g;

    public C2741u5(String str, char[] cArr) {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        int i = 0;
        while (true) {
            if (i >= cArr.length) {
                this.a = str;
                this.b = cArr;
                try {
                    int iA = AbstractC2410qA.a(cArr.length, RoundingMode.UNNECESSARY);
                    this.d = iA;
                    int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(iA);
                    int i2 = 1 << (3 - iNumberOfTrailingZeros);
                    this.e = i2;
                    this.f = iA >> iNumberOfTrailingZeros;
                    this.c = cArr.length - 1;
                    this.g = bArr;
                    boolean[] zArr = new boolean[i2];
                    for (int i3 = 0; i3 < this.f; i3++) {
                        zArr[AbstractC2410qA.a(i3 * 8, this.d, RoundingMode.CEILING)] = true;
                    }
                    return;
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e);
                }
            }
            char c = cArr[i];
            if (!(c < 128)) {
                w01.a(Xf0.a("Non-ASCII character: %s", new Object[]{Character.valueOf(c)}));
                throw null;
            }
            if (!(bArr[c] == -1)) {
                w01.a(Xf0.a("Duplicate character: %s", new Object[]{Character.valueOf(c)}));
                throw null;
            }
            bArr[c] = (byte) i;
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2741u5) {
            return Arrays.equals(this.b, ((C2741u5) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b) + 1237;
    }

    public final String toString() {
        return this.a;
    }
}
