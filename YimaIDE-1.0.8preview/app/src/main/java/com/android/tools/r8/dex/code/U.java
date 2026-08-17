package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.EnumC0430Dd;
import com.android.tools.r8.internal.US;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U extends S0 {
    public U(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(US.g, EnumC0430Dd.d, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "CmplFloat";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 45;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "cmpl-float";
    }

    public U(int i, int i2, int i3) {
        super(i, i2, i3);
    }
}
