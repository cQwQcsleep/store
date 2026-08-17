package com.android.tools.r8.dex.code;

import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class P2 extends E0 {
    public P2(int i, A1 a1) {
        super(a1);
    }

    public static P2 a(int i, A1 a1) {
        if (i == 1) {
            return new Z2(i, a1);
        }
        if (i != 2) {
            return i != 3 ? new P2(i, a1) : new C0127x0(i, a1);
        }
        return new L3(i, a1);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public void b(com.android.tools.r8.utils.structural.o oVar) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public int hashCode() {
        return 549577;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "Nop";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 0;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "nop";
    }

    public P2() {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public void a(C0602Jt c0602Jt) {
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public int a(AbstractC0138z1 abstractC0138z1, AbstractC3519a abstractC3519a) {
        V.a(this, abstractC0138z1);
        return 0;
    }
}
