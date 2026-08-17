package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class l4 extends N0 {
    public l4(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.s(US.e, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "XorIntLit8";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 223;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "xor-int/lit8";
    }

    public l4(int i, int i2, int i3) {
        super(i, i2, i3);
    }
}
