package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ge, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0509Ge extends AbstractC0431De {
    public static final /* synthetic */ boolean c = true;
    public final C0587Je a;
    public final int b;

    public C0509Ge(C0587Je c0587Je, int i) {
        this.a = c0587Je;
        this.b = i;
    }

    public final C0483Fe a(com.android.tools.r8.graph.B5 b5) {
        C0483Fe c0483Fe = new C0483Fe(this, b5);
        if (!c) {
            a(c0483Fe);
        }
        return c0483Fe;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder b(StringBuilder sb) {
        sb.append('$');
        sb.append(this.b);
        return sb;
    }

    public final void a(AbstractC0431De abstractC0431De) {
        if (c) {
            return;
        }
        this.a.a(abstractC0431De);
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder a(StringBuilder sb) {
        sb.append('$');
        sb.append(this.b);
        return sb;
    }
}
