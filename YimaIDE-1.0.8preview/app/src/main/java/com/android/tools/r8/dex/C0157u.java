package com.android.tools.r8.dex;

import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.Z0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.CJ;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.dex.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0157u {
    public final C0284q5 a;
    public final AbstractC3148ys b;
    public final AbstractC3148ys c;
    public final Z0 d;
    public ByteBuffer e;

    public C0157u(Z0 z0, C0284q5 c0284q5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        this.d = z0;
        this.a = c0284q5;
        this.b = abstractC3148ys;
        this.c = abstractC3148ys2;
        this.e = ByteBuffer.allocate(z0.k0());
    }

    public final void a(int i) {
        if (this.e.remaining() < i) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.e.capacity() * 2);
            byteBufferAllocate.put(this.e.array(), 0, this.e.position());
            this.e = byteBufferAllocate;
        }
    }

    public final void b(int i) {
        a(1);
        this.e.put((byte) i);
    }

    public final void c(int i) {
        byte[] bArrB = CJ.b(i);
        a(bArrB.length);
        this.e.put(bArrB, 0, bArrB.length);
    }

    public byte[] a() {
        this.d.a(this, this.a, this.b, this.c);
        return Arrays.copyOf(this.e.array(), this.e.position());
    }
}
