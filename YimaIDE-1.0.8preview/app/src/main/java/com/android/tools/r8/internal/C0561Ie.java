package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ie, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0561Ie extends AbstractC0431De {
    public final AbstractC0535He a;
    public final com.android.tools.r8.graph.D2 b;
    public final int c;

    public C0561Ie(AbstractC0535He abstractC0535He, com.android.tools.r8.graph.D2 d2, int i) {
        this.a = abstractC0535He;
        this.b = d2;
        this.c = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder a(StringBuilder sb) {
        StringBuilder sbA = this.a.a(sb);
        sbA.append('$');
        sbA.append(this.c);
        return sbA;
    }

    @Override // com.android.tools.r8.internal.AbstractC0431De
    public final StringBuilder b(StringBuilder sb) {
        StringBuilder sbB = this.a.b(sb);
        sbB.append('$');
        sbB.append(this.c);
        return sbB;
    }

    public final String a() {
        return b(new StringBuilder()).toString();
    }
}
