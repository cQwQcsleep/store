package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.v2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0315v2 extends AbstractC0259n1 {
    public final AbstractC0287r2 b;
    public final AbstractC0259n1 c;

    public AbstractC0315v2(AbstractC0287r2 abstractC0287r2, AbstractC0259n1 abstractC0259n1) {
        this.b = abstractC0287r2;
        this.c = abstractC0259n1;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        this.c.a(x);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AbstractC0315v2) {
            AbstractC0315v2 abstractC0315v2 = (AbstractC0315v2) obj;
            if (this.b.equals(abstractC0315v2.b) && this.c.equals(abstractC0315v2.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 7);
    }
}
