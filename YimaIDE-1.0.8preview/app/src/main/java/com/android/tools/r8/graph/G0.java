package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class G0<D extends AbstractC0217h1<D, R>, R extends AbstractC0287r2<D, R>> implements InterfaceC0265o0 {
    public static final /* synthetic */ boolean d = true;
    public final E0 b;
    public final AbstractC0217h1 c;

    public G0(E0 e0, AbstractC0217h1 abstractC0217h1) {
        boolean z = d;
        if (!z && e0 == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC0217h1 == null) {
            x1f.a();
            throw null;
        }
        if (!z && e0.e != abstractC0217h1.E0()) {
            x1f.a();
            throw null;
        }
        this.b = e0;
        this.c = abstractC0217h1;
    }

    public E0 a() {
        return this.b;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0, com.android.tools.r8.graph.InterfaceC0332x5
    public E0 b() {
        return a();
    }

    public final boolean equals(Object obj) {
        throw new Kk0("Unsupported attempt at comparing Class and DexClassAndMember");
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final Origin getOrigin() {
        return this.b.d;
    }

    public final int hashCode() {
        throw new Kk0("Unsupported attempt at computing the hash code of DexClassAndMember");
    }

    public I2 s() {
        return this.b.e;
    }

    public final H2 t() {
        return getReference().x0();
    }

    public String toString() {
        return v();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public R getReference() {
        return (R) this.c.getReference();
    }

    public String v() {
        return getReference().m0();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final I2 z() {
        return s();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public D e() {
        return (D) this.c;
    }

    public G0() {
        this.b = null;
        this.c = null;
    }
}
