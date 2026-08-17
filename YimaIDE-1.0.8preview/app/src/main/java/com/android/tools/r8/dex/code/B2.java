package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class B2 extends S0 {
    public static final /* synthetic */ boolean j = true;

    public B2(int i, int i2, int i3) {
        super(i, i2, i3);
        if (j || i != i3 || i == i2) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.g(US.g, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "MulFloat";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 168;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "mul-float";
    }

    public B2(int i, A1 a1) {
        super(i, a1);
    }
}
