package com.android.tools.r8.dex;

import com.android.tools.r8.ByteBufferProvider;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C2033ln;
import defpackage.n33;
import java.nio.ByteOrder;

/* JADX INFO: renamed from: com.android.tools.r8.dex.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0161y {
    public static final /* synthetic */ boolean c = true;
    public final ByteBufferProvider a;
    public C0155s b;

    public C0161y(ByteBufferProvider byteBufferProvider) {
        this.a = byteBufferProvider;
        this.b = b(262144);
    }

    public final int a(int i, long j) {
        boolean z = C2033ln.a;
        int iMax = Math.max((72 - Long.numberOfLeadingZeros((j >> 63) ^ j)) / 8, 1);
        if (!C2033ln.a && (iMax <= 0 || iMax > i)) {
            x1f.a();
            return 0;
        }
        for (int i2 = 0; i2 < iMax; i2++) {
            a((byte) j);
            j >>= 8;
        }
        return iMax;
    }

    public final C0155s b(int i) {
        C0155s c0155s = new C0155s(this.a.acquireByteBuffer(i));
        if (!c0155s.k()) {
            n33.a("Provided byte-buffer is required to have an array backing, but does not.");
            return null;
        }
        if (c0155s.d() < i) {
            throw new C0613Ke("Insufficient capacity of provided byte-buffer. Requested capacity " + i + ", actual capacity: " + c0155s.d());
        }
        if (c0155s.m() == 0) {
            c0155s.a(ByteOrder.LITTLE_ENDIAN);
            return c0155s;
        }
        throw new C0613Ke("Provided byte-buffer is required to start at position zero, but starts at " + c0155s.m() + ".");
    }

    public final void c(int i) {
        if (this.b.c().remaining() < i) {
            C0155s c0155sB = b(Math.max(this.b.d(), i * 2) + this.b.d());
            System.arraycopy(this.b.c().array(), 0, c0155sB.c().array(), 0, this.b.d());
            c0155sB.c(this.b.c().position());
            C0155s c0155s = this.b;
            if (!c && c0155s == null) {
                x1f.a();
            } else {
                this.a.releaseByteBuffer(c0155s.c());
                this.b = c0155sB;
            }
        }
    }

    public final void d(int i) {
        c(i);
        C0155s c0155s = this.b;
        c0155s.c(c0155s.c().position() + i);
    }

    public final void e(int i) {
        c(i - this.b.c().position());
        this.b.c(i);
    }

    public final void f(int i) {
        c(4);
        this.b.c().putInt(i);
    }

    public C0161y() {
        this(new C0160x());
    }

    public void a(byte b) {
        c(1);
        this.b.c().put(b);
    }

    public void a(byte[] bArr) {
        c(bArr.length);
        this.b.c().put(bArr);
    }

    public final void a(short s) {
        c(2);
        this.b.c().putShort(s);
    }

    public final int a(int i) {
        if (!c && i <= 0) {
            x1f.a();
            return 0;
        }
        int i2 = i - 1;
        int iB = (~i2) & (b() + i2);
        c(iB - b());
        this.b.c(iB);
        return iB;
    }

    public byte[] a() {
        return this.b.c().array();
    }

    public final C0155s c() {
        C0155s c0155s = this.b;
        this.b = null;
        return c0155s;
    }

    public final int b(int i, long j) {
        boolean z = C2033ln.a;
        int iMax = Math.max((71 - Long.numberOfLeadingZeros(j)) / 8, 1);
        if (!C2033ln.a && (iMax <= 0 || iMax > i)) {
            x1f.a();
            return 0;
        }
        for (int i2 = 0; i2 < iMax; i2++) {
            a((byte) j);
            j >>= 8;
        }
        return iMax;
    }

    public int b() {
        return this.b.c().position();
    }
}
