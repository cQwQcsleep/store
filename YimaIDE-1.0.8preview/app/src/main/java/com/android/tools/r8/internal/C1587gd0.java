package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1587gd0 extends AbstractC1759id0 {
    public final /* synthetic */ C1048aD i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1587gd0(C1928kd0 c1928kd0, CharSequence charSequence, C1048aD c1048aD) {
        super(c1928kd0, charSequence);
        this.i = c1048aD;
    }

    @Override // com.android.tools.r8.internal.AbstractC1759id0
    public final int a(int i) {
        return this.i.a.end();
    }

    @Override // com.android.tools.r8.internal.AbstractC1759id0
    public final int b(int i) {
        if (this.i.a.find(i)) {
            return this.i.a.start();
        }
        return -1;
    }
}
