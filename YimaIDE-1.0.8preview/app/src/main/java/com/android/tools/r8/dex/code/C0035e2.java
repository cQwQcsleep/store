package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0290r5;
import com.android.tools.r8.graph.Z5;
import com.android.tools.r8.internal.C0602Jt;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.e2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0035e2 extends AbstractC0118v1 {
    public C0035e2(int i, A1 a1, C0290r5 c0290r5) {
        super(i, a1, c0290r5.a());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.b(this.f, this.g, getField());
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "IputWide";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 90;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "iput-wide";
    }

    public C0035e2(int i, int i2, C0245l1 c0245l1) {
        super(i, i2, c0245l1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(Z5 z5) {
        z5.c(getField());
    }
}
