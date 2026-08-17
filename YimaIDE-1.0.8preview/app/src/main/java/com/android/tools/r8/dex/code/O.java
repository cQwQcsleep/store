package com.android.tools.r8.dex.code;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0290r5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.Z5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0602Jt;
import com.android.tools.r8.internal.RJ;
import java.nio.ShortBuffer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class O extends J0<com.android.tools.r8.graph.I2> {
    public final boolean i;

    public O(int i, A1 a1, C0290r5 c0290r5) {
        super(i, a1, c0290r5.c());
        this.i = false;
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 b(J0 j0) {
        return (com.android.tools.r8.graph.I2) j0.g;
    }

    public com.android.tools.r8.graph.I2 I() {
        return (com.android.tools.r8.graph.I2) this.g;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0284q5 c0284q5, B5 b5, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, ShortBuffer shortBuffer) {
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(abstractC3148ys2, I());
        AbstractC0138z1.a(this.f, 31, shortBuffer);
        AbstractC0138z1.a(i2C, shortBuffer, c0284q5);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String m() {
        return "CheckCast";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final int r() {
        return 31;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final String u() {
        return "check-cast";
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final boolean y() {
        return true;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final O b() {
        return this;
    }

    public O(int i, com.android.tools.r8.graph.I2 i2, boolean z) {
        super(i, i2);
        this.i = z;
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m, B5 b5, RJ rj) {
        c0333y.A().c(abstractC3148ys, I()).a(c0333y, m);
    }

    @Override // com.android.tools.r8.dex.code.J0
    public final void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: com.android.tools.r8.dex.code.b5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return O.b((J0) obj);
            }
        });
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public void a(Z5 z5) {
        z5.a(I(), this.i);
    }

    @Override // com.android.tools.r8.dex.code.AbstractC0138z1
    public void a(C0602Jt c0602Jt) {
        c0602Jt.a((int) this.f, I(), false);
    }
}
