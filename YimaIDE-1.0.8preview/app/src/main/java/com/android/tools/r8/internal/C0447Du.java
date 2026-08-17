package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Du, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0447Du extends F {
    public final /* synthetic */ AbstractC0551Hu d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0447Du(AbstractC0551Hu abstractC0551Hu, int i, int i2) {
        super(i, i2);
        this.d = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.internal.F
    public final Object a(int i) {
        return this.d.get(i);
    }
}
