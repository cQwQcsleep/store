package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.internal.InterfaceC3142ym0;
import com.android.tools.r8.internal.Wf0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.code.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0048h0 extends AbstractC0014a1 implements InterfaceC3142ym0 {
    public C0048h0(int i, A1 a1) {
        super(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String a(C1581ga0 c1581ga0) {
        short s = this.f;
        return a("v" + ((int) s) + ", " + Wf0.a(this.g) + "L  # " + this.g);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String b(C1581ga0 c1581ga0) {
        short s = this.f;
        return b("v" + ((int) s) + ", " + Wf0.a(this.g) + " (" + this.g + ")");
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "ConstWide";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 24;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "const-wide";
    }

    public C0048h0(int i, long j) {
        super(i, j);
    }

    @Override // com.android.tools.r8.internal.InterfaceC3142ym0
    public final long a() {
        return this.g;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0602Jt c0602Jt) {
        c0602Jt.a(AbstractC2624sj0.q(), this.f, this.g);
    }
}
