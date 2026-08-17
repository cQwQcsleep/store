package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ob, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0713Ob extends AbstractC0570In {
    public final int a;

    public C0713Ob(int i) {
        this.a = i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0570In
    public final AbstractC0570In a(com.android.tools.r8.graph.proto.c cVar) {
        if (cVar.a(this.a).c()) {
            return C2199nk0.a;
        }
        int iB = cVar.b(this.a);
        return iB != this.a ? new C0713Ob(iB) : this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0570In
    public final C0713Ob a() {
        return this;
    }
}
