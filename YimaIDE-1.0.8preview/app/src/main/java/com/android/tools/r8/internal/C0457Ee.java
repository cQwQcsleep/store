package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ee, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0457Ee extends AbstractC0535He {
    public static final /* synthetic */ boolean d = true;
    public final Thread c;

    public C0457Ee(C0509Ge c0509Ge, Thread thread) {
        super(c0509Ge);
        this.c = thread;
    }

    public final C0561Ie a(com.android.tools.r8.graph.D2 d2) {
        boolean z = d;
        if (!z && this.c != Thread.currentThread()) {
            x01.a("Invoked on another thread than main");
            return null;
        }
        int i = this.b;
        this.b = i + 1;
        C0561Ie c0561Ie = new C0561Ie(this, d2, i);
        if (!z) {
            this.a.a(c0561Ie);
        }
        return c0561Ie;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder b(StringBuilder sb) {
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        sb.append("main");
        return sb;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder a(StringBuilder sb) {
        C0509Ge c0509Ge = this.a;
        c0509Ge.getClass();
        sb.append('$');
        sb.append(c0509Ge.b);
        sb.append("main");
        return sb;
    }
}
