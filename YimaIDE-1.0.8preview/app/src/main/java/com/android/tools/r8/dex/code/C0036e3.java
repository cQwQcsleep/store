package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.e3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0036e3 extends S0 {
    public C0036e3(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.k(US.g, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "RemFloat";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 170;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "rem-float";
    }

    public C0036e3(int i, int i2, int i3) {
        super(i, i2, i3);
    }
}
