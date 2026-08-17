package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.El0;
import com.android.tools.r8.internal.Gl0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.r2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0099r2 extends R0 {
    public C0099r2(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        El0 el0 = El0.b;
        short s = this.f;
        char c = this.g;
        c0602Jt.getClass();
        c0602Jt.a(Gl0.a(el0), s, c);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "MoveObjectFrom16";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 8;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "move-object-from/16";
    }

    public C0099r2(int i, int i2) {
        super(i, i2);
    }
}
