package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Y2 extends W0 {
    public Y2(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean C() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        short s = this.f;
        return a("v" + ((int) s) + ", :label_" + (q() + this.g));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "PackedSwitch";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 43;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "packed-switch";
    }

    public Y2(int i) {
        super(i);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        int iQ = q();
        int i = this.g + iQ;
        short s = this.f;
        c0602Jt.t.a(s, iQ + 3, i, c0602Jt);
    }
}
