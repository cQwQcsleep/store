package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.Gl0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.v2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0119v2 extends X0 {
    public C0119v2(int i, A1 a1) {
        super(a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(Gl0.i, this.f, this.g);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "MoveWide16";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 6;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "move-wide/16";
    }

    public C0119v2(int i, int i2) {
        super(i, i2);
    }
}
