package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WI {
    public static final WI o = new WI();
    public XI a;
    public short b;
    public short c;
    public int[] d;
    public int e;
    public int[] f;
    public short g;
    public short h;
    public short i;
    public short j;
    public C0962Xq k;
    public WI l;
    public C0517Gm m;
    public WI n;

    public final boolean a(byte[] bArr, X7 x7, int i) {
        this.b = (short) (this.b | 4);
        this.e = i;
        int[] iArr = this.f;
        boolean z = false;
        if (iArr == null) {
            return false;
        }
        for (int i2 = iArr[0]; i2 > 0; i2 -= 2) {
            int[] iArr2 = this.f;
            int i3 = iArr2[i2 - 1];
            int i4 = iArr2[i2];
            int i5 = i - i3;
            int i6 = 268435455 & i4;
            int i7 = i4 & (-268435456);
            if (i7 == 268435456) {
                if (i5 < -32768 || i5 > 32767) {
                    int i8 = bArr[i3] & 255;
                    if (i8 < 198) {
                        bArr[i3] = (byte) (i8 + 49);
                    } else {
                        bArr[i3] = (byte) (i8 + 20);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i5 >>> 8);
                bArr[i6 + 1] = (byte) i5;
            } else if (i7 == 536870912) {
                bArr[i6] = (byte) (i5 >>> 24);
                bArr[i6 + 1] = (byte) (i5 >>> 16);
                bArr[i6 + 2] = (byte) (i5 >>> 8);
                bArr[i6 + 3] = (byte) i5;
            } else {
                byte[] bArr2 = x7.a;
                bArr2[i6] = (byte) (i >>> 8);
                bArr2[i6 + 1] = (byte) i;
            }
        }
        return z;
    }

    public final String toString() {
        return CX.a(System.identityHashCode(this), "L");
    }

    public final void a(X7 x7, int i, boolean z) {
        if ((this.b & 4) != 0) {
            int i2 = this.e;
            if (z) {
                x7.c(i2 - i);
                return;
            } else {
                x7.d(i2 - i);
                return;
            }
        }
        if (z) {
            a(i, 536870912, x7.b);
            x7.c(-1);
        } else {
            a(i, 268435456, x7.b);
            x7.d(-1);
        }
    }

    public final void a(int i, int i2, int i3) {
        if (this.f == null) {
            this.f = new int[6];
        }
        int[] iArr = this.f;
        int i4 = iArr[0];
        int i5 = i4 + 2;
        if (i5 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.f = iArr2;
        }
        int[] iArr3 = this.f;
        iArr3[i4 + 1] = i;
        iArr3[i5] = i2 | i3;
        iArr3[0] = i5;
    }

    public final WI a() {
        C0962Xq c0962Xq = this.k;
        return c0962Xq == null ? this : c0962Xq.a;
    }
}
