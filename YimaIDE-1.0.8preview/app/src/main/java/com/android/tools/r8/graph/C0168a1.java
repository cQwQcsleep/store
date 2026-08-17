package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC2004lX;

/* JADX INFO: renamed from: com.android.tools.r8.graph.a1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0168a1 implements V0 {
    public static final /* synthetic */ boolean f = true;
    public int a = 0;
    public int b;
    public C0322w2 c;
    public boolean d;
    public AbstractC2004lX e;

    public C0168a1(int i, C0322w2 c0322w2, boolean z) {
        this.b = i;
        this.c = c0322w2;
        this.d = z;
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(O0.b bVar) {
        if (!f && bVar.t0() < 0) {
            x1f.a();
            return;
        }
        this.a = bVar.t0() + this.a;
        this.b = bVar.s0() + this.b;
    }

    public int b() {
        return this.a;
    }

    public final AbstractC2004lX c() {
        AbstractC2004lX abstractC2004lX = this.e;
        if (abstractC2004lX != null) {
            return abstractC2004lX.b().a(a()).a();
        }
        AbstractC2004lX.a aVarA = (a() > 0 ? AbstractC2004lX.b.s() : AbstractC2004lX.c.s()).a(a()).a(this.c);
        aVarA.e = this.d;
        return aVarA.c().a();
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(O0.d dVar) {
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(P0 p0) {
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(Q0 q0) {
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(R0 r0) {
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(T0 t0) {
    }

    @Override // com.android.tools.r8.graph.V0
    public final void a(O0.a aVar) {
        if (f || aVar.d >= 0) {
            this.a += aVar.d;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.graph.V0
    public final void a(N0 n0) {
        this.b += n0.d;
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(S0 s0) {
        if (!f && s0.d == null) {
            x1f.a();
            return;
        }
        AbstractC2004lX abstractC2004lX = s0.d;
        this.c = abstractC2004lX.c;
        this.d = abstractC2004lX.f;
        this.e = abstractC2004lX;
    }

    @Override // com.android.tools.r8.graph.V0
    public void a(O0.c cVar) {
    }

    public int a() {
        return this.b;
    }
}
