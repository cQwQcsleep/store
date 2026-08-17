package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0676Mq extends F {
    public final /* synthetic */ C0702Nq d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0676Mq(C0702Nq c0702Nq, int i) {
        super(i, 0);
        this.d = c0702Nq;
    }

    @Override // com.android.tools.r8.internal.F
    public final Object a(int i) {
        return this.d.b[i].iterator();
    }
}
