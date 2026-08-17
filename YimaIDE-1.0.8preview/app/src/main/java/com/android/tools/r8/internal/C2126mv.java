package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2126mv extends AbstractC2724tu {
    public final /* synthetic */ AbstractC2212nv d;

    public C2126mv(AbstractC2212nv abstractC2212nv) {
        this.d = abstractC2212nv;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return ((AbstractC1314dQ) ((C1357dv) this.d).e.get(i)).b();
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return this.d;
    }
}
