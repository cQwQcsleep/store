package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0019b1 extends I0 {
    public C0019b1(int i, A1 a1) {
        super(a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(q() + this.f);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "Goto16";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 41;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "goto/16";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int[] w() {
        return new int[]{this.f};
    }

    public C0019b1(int i) {
        super(i);
    }
}
