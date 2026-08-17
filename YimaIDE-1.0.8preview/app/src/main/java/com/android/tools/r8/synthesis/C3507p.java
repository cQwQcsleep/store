package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.I0;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3507p extends AbstractC3504m implements r {
    public C3507p(S.b bVar, C3502k c3502k, I0 i0) {
        super(bVar, c3502k, i0);
    }

    @Override // com.android.tools.r8.synthesis.AbstractC3509s
    public final int a(AbstractC3509s abstractC3509s, com.android.tools.r8.utils.structural.t tVar) {
        return ((I0) this.d).a((I0) ((C3507p) abstractC3509s).d, tVar);
    }

    @Override // com.android.tools.r8.synthesis.AbstractC3509s
    public final r b() {
        return this;
    }

    @Override // com.android.tools.r8.synthesis.AbstractC3509s
    public final boolean i() {
        return ((I0) this.d).z1() && ((I0) this.d).f.f() && ((I0) this.d).f.p();
    }

    @Override // com.android.tools.r8.synthesis.AbstractC3509s
    public final a0 j() {
        return new C3508q(this.a, this.b, ((I0) this.d).getType());
    }

    public final String toString() {
        return "SyntheticClasspathClass{ clazz = " + ((I0) this.d).e.m0() + ", kind = " + f() + ", context = " + d() + " }";
    }

    @Override // com.android.tools.r8.synthesis.AbstractC3509s
    public final void a(com.android.tools.r8.utils.structural.m mVar, com.android.tools.r8.utils.structural.t tVar) {
        ((I0) this.d).a(mVar, tVar);
    }
}
