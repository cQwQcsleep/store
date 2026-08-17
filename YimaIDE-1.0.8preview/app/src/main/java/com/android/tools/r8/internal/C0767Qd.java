package com.android.tools.r8.internal;

import defpackage.f0c;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0767Qd {
    public final byte[] a;
    public final int b;
    public int c;
    public final OutputStream d;

    public C0767Qd(int i, byte[] bArr) {
        this.d = null;
        this.a = bArr;
        this.c = 0;
        this.b = i;
    }

    public static int b(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public final void a(T7 t7) throws IOException {
        int size = t7.size();
        int i = this.b;
        int i2 = this.c;
        int i3 = i - i2;
        byte[] bArr = this.a;
        if (i3 >= size) {
            t7.a(bArr, 0, i2, size);
            this.c += size;
            return;
        }
        t7.a(bArr, 0, i2, i3);
        int i4 = size - i3;
        int i5 = this.b;
        this.c = i5;
        OutputStream outputStream = this.d;
        if (outputStream == null) {
            throw new C0715Od();
        }
        outputStream.write(this.a, 0, i5);
        this.c = 0;
        if (i4 <= this.b) {
            t7.a(this.a, i3, 0, i4);
            this.c = i4;
            return;
        }
        OutputStream outputStream2 = this.d;
        if (i3 < 0) {
            f0c.a(30, "Source offset < 0: ", i3);
            return;
        }
        if (i4 < 0) {
            f0c.a(23, "Length < 0: ", i4);
            return;
        }
        int i6 = i3 + i4;
        if (i6 > t7.size()) {
            f0c.a(39, "Source end offset exceeded: ", i6);
        } else if (i4 > 0) {
            t7.a(outputStream2, i3, i4);
        }
    }

    public final void c(long j) throws IOException {
        e(((int) j) & 255);
        e(((int) (j >> 8)) & 255);
        e(((int) (j >> 16)) & 255);
        e(((int) (j >> 24)) & 255);
        e(((int) (j >> 32)) & 255);
        e(((int) (j >> 40)) & 255);
        e(((int) (j >> 48)) & 255);
        e(((int) (j >> 56)) & 255);
    }

    public final void d(long j) throws IOException {
        while (((-128) & j) != 0) {
            e((((int) j) & 127) | 128);
            j >>>= 7;
        }
        e((int) j);
    }

    public final void e(int i) throws IOException {
        byte b = (byte) i;
        int i2 = this.c;
        if (i2 == this.b) {
            OutputStream outputStream = this.d;
            if (outputStream == null) {
                throw new C0715Od();
            }
            outputStream.write(this.a, 0, i2);
            this.c = 0;
        }
        byte[] bArr = this.a;
        int i3 = this.c;
        this.c = i3 + 1;
        bArr[i3] = b;
    }

    public final void f(int i) throws IOException {
        e(i & 255);
        e((i >> 8) & 255);
        e((i >> 16) & 255);
        e((i >> 24) & 255);
    }

    public final void g(int i) throws IOException {
        while ((i & (-128)) != 0) {
            e((i & 127) | 128);
            i >>>= 7;
        }
        e(i);
    }

    public C0767Qd(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.a = bArr;
        this.c = 0;
        this.b = bArr.length;
    }

    public final void d(int i) throws IOException {
        if (i >= 0) {
            g(i);
        } else {
            d(i);
        }
    }

    public static long b(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public final void b(int i, L0 l0) throws IOException {
        c(i, 2);
        g(l0.c());
        l0.a(this);
    }

    public final void b(int i, int i2) throws IOException {
        c(i, 0);
        d(i2);
    }

    public static int c(int i) {
        return b(i << 3);
    }

    public final void c(int i, int i2) throws IOException {
        g((i << 3) | i2);
    }

    public final void a(int i, T7 t7) throws IOException {
        c(i, 2);
        g(t7.size());
        a(t7);
    }

    public static int a(int i, int i2) {
        return c(i) + (i2 >= 0 ? b(i2) : 10);
    }

    public static int a(int i, L0 l0) {
        int iC = c(i);
        int iC2 = l0.c();
        return b(iC2) + iC2 + iC;
    }

    public static int a(int i) {
        if (i >= 0) {
            return b(i);
        }
        return 10;
    }

    public final void a() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream != null) {
            outputStream.write(this.a, 0, this.c);
            this.c = 0;
        }
    }

    public static int a(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public final void a(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = this.b;
        int i2 = this.c;
        int i3 = i - i2;
        byte[] bArr2 = this.a;
        if (i3 >= length) {
            System.arraycopy(bArr, 0, bArr2, i2, length);
            this.c += length;
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i2, i3);
        int i4 = length - i3;
        int i5 = this.b;
        this.c = i5;
        OutputStream outputStream = this.d;
        if (outputStream != null) {
            outputStream.write(this.a, 0, i5);
            this.c = 0;
            if (i4 <= this.b) {
                System.arraycopy(bArr, i3, this.a, 0, i4);
                this.c = i4;
                return;
            } else {
                this.d.write(bArr, i3, i4);
                return;
            }
        }
        throw new C0715Od();
    }
}
