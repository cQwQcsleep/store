package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ed0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1416ed0 extends AbstractC1759id0 {
    public final /* synthetic */ C1502fd0 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1416ed0(C1502fd0 c1502fd0, C1928kd0 c1928kd0, CharSequence charSequence) {
        super(c1928kd0, charSequence);
        this.i = c1502fd0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1759id0
    public final int a(int i) {
        return i + 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC1759id0
    public final int b(int i) {
        return this.i.a.a(this.d, i);
    }
}
