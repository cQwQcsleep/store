package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.US;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0062k extends P0 {
    public C0062k(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.b(US.e, this.f, this.g, this.h);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "AddIntLit16";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 208;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "add-int/lit16";
    }

    public C0062k(int i, int i2, int i3) {
        super(i, i2, i3);
    }
}
