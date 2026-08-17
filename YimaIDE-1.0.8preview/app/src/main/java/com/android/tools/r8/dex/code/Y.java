package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.InterfaceC2012lc0;
import com.android.tools.r8.internal.Wf0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Y extends V0 implements InterfaceC2012lc0 {
    public Y(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        short s = this.f;
        return a("v" + ((int) s) + ", " + Wf0.a(this.g, 8) + "  # " + this.g);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        short s = this.f;
        return b("v" + ((int) s) + ", " + Wf0.a(this.g, 8) + " (" + this.g + ")");
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "Const";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 20;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "const";
    }

    public Y(int i, int i2) {
        super(i, i2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2012lc0
    public final int a() {
        return this.g;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        int i = this.g;
        c0602Jt.a(i == 0 ? AbstractC2624sj0.p() : AbstractC2624sj0.o(), this.f, i);
    }
}
