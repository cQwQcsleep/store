package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0047h extends S0 {
    public C0047h(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(US.g, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "AddFloat";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 166;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "add-float";
    }

    public C0047h(int i, int i2, int i3) {
        super(i, i2, i3);
    }
}
