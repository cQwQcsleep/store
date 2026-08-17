package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC0507Gc;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H0 extends G0<C0231j1, C0322w2> implements InterfaceC0331x4 {
    public static final /* synthetic */ boolean e = true;

    public H0(E0 e0, C0231j1 c0231j1) {
        super(e0, c0231j1);
        boolean z = e;
        if (!z) {
            e0.getClass();
            if ((e0 instanceof I0) != (this instanceof C0195e0)) {
                x1f.a();
                throw null;
            }
        }
        if (!z && e0.b0() != (this instanceof C0310u4)) {
            x1f.a();
            throw null;
        }
        if (z || e0.a0() == (this instanceof B5)) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static H0 a(E0 e0, C0231j1 c0231j1) {
        if (e0.a0()) {
            return new B5(e0.X(), c0231j1);
        }
        if (e0.b0()) {
            return new C0310u4(e0.Z(), c0231j1);
        }
        if (e || (e0 instanceof I0)) {
            return new C0195e0(e0.m(), c0231j1);
        }
        x1f.a();
        return null;
    }

    public MethodReference A() {
        return getReference().z0();
    }

    public final C0343z2 C() {
        C0322w2 c0322w2U = getReference();
        return AbstractC0507Gc.a(c0322w2U, c0322w2U);
    }

    public com.android.tools.r8.ir.optimize.info.h D() {
        C0231j1 c0231j1E = e();
        c0231j1E.O0();
        return c0231j1E.m;
    }

    public K2 E() {
        return getReference().B0();
    }

    public final E2 F() {
        return getReference().C0();
    }

    public I2 G() {
        return getReference().D0();
    }

    public final boolean H() {
        return a().isInterface() && e().n1();
    }

    public final boolean b(H0 h0) {
        return h0 != null && e() == h0.e() && a() == h0.a();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public H0 c() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final boolean d0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0
    public final /* bridge */ /* synthetic */ C0231j1 e() {
        return (C0231j1) super.e();
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0265o0
    public final /* bridge */ /* synthetic */ C0322w2 getReference() {
        return (C0322w2) super.getReference();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final G0 j0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0331x4
    public final H0 q() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public F4 getAccessFlags() {
        return e().getAccessFlags();
    }

    public int y() {
        return getReference().A0();
    }

    public I2 b(int i) {
        return getReference().k(i);
    }

    public H0() {
    }

    public static B5 a(H0 h0) {
        if (h0 != null) {
            return h0.c0();
        }
        return null;
    }

    public final I2 a(int i) {
        C0231j1 c0231j1E = e();
        return c0231j1E.getReference().a(i, c0231j1E.z0());
    }
}
