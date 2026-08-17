package com.android.tools.r8.graph;

import com.android.tools.r8.ir.optimize.info.AbstractC3264e;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F0 extends G0<C0210g1, C0245l1> {
    public static final /* synthetic */ boolean e = true;

    public F0(E0 e0, C0210g1 c0210g1) {
        super(e0, c0210g1);
        boolean z = e;
        if (!z) {
            e0.getClass();
            if ((e0 instanceof I0) != (this instanceof C0188d0)) {
                x1f.a();
                throw null;
            }
        }
        if (!z && e0.b0() != (this instanceof C0303t4)) {
            x1f.a();
            throw null;
        }
        if (z || e0.a0() == (this instanceof C0346z5)) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static F0 a(E0 e0, C0210g1 c0210g1) {
        if (e0.a0()) {
            return new C0346z5(e0.X(), c0210g1);
        }
        if (e0.b0()) {
            return new C0303t4(e0.Z(), c0210g1);
        }
        if (e || (e0 instanceof I0)) {
            return new C0188d0(e0.m(), c0210g1);
        }
        x1f.a();
        return null;
    }

    public final boolean b(C0333y c0333y) {
        if (getAccessFlags().f()) {
            return true;
        }
        return c0333y.g().i() && a(c0333y.V());
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public F0 d() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final boolean f0() {
        return true;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final G0 j0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public C0205f3 getAccessFlags() {
        return e().getAccessFlags();
    }

    public final AbstractC3264e y() {
        return e().l;
    }

    public boolean a(C0333y c0333y) {
        return false;
    }
}
