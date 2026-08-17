package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0654Lu extends AbstractC0836Su {
    public final /* synthetic */ AbstractC0680Mu e;

    public C0654Lu(AbstractC0680Mu abstractC0680Mu) {
        this.e = abstractC0680Mu;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return new YM(((C0369Au) this.e).f.entrySet().iterator());
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su
    public final AbstractC0706Nu m() {
        return this.e;
    }
}
