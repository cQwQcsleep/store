package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0630Kv extends AbstractC2724tu {
    public final /* synthetic */ AbstractC0655Lv d;

    public C0630Kv(AbstractC0655Lv abstractC0655Lv) {
        this.d = abstractC0655Lv;
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu, com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return this.d.e();
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.d.get(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d.size();
    }
}
