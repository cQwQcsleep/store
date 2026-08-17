package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0122w0 extends W0 {
    public C0122w0(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        short s = this.f;
        return a("v" + ((int) s) + ", :label_" + (q() + this.g));
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "FillArrayData";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 38;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "fill-array-data";
    }

    public C0122w0(int i) {
        super(i);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.t.a(this.f, q() + this.g, c0602Jt);
    }
}
