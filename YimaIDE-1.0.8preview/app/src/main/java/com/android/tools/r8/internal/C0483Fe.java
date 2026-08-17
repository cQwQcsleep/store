package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0483Fe extends AbstractC0535He {
    public static final /* synthetic */ boolean d = true;
    public final com.android.tools.r8.graph.B5 c;

    public C0483Fe(C0509Ge c0509Ge, com.android.tools.r8.graph.B5 b5) {
        super(c0509Ge);
        this.c = b5;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder a(StringBuilder sb) {
        sb.append(this.c.a().getType().Z0());
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        com.android.tools.r8.utils.structural.n nVarB = com.android.tools.r8.utils.structural.n.b();
        this.c.getReference().a(nVarB);
        sb.append('$');
        sb.append(nVarB.a());
        return sb;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder b(StringBuilder sb) {
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        com.android.tools.r8.utils.structural.n nVarB = com.android.tools.r8.utils.structural.n.b();
        this.c.getReference().a(nVarB);
        sb.append('$');
        sb.append(nVarB.a());
        return sb;
    }

    public final C0561Ie a() {
        com.android.tools.r8.graph.D2 d2A = this.c.a();
        int i = this.b;
        this.b = i + 1;
        C0561Ie c0561Ie = new C0561Ie(this, d2A, i);
        if (!d) {
            this.a.a(c0561Ie);
        }
        return c0561Ie;
    }
}
