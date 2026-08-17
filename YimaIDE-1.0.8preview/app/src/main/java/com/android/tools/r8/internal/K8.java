package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class K8 extends AbstractC3175z9 implements InterfaceC0998Za {
    public final com.android.tools.r8.graph.I2 c;
    public final boolean d;

    public K8(com.android.tools.r8.graph.I2 i2) {
        this.c = i2;
        this.d = false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean B() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean S() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        c0738Pa.a();
        a(c0602Jt, c0738Pa.a(new C0635La(this.c)));
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final K8 b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0998Za
    public com.android.tools.r8.graph.I2 getType() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final InterfaceC0998Za v() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 3;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean x() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return 192;
    }

    public K8(com.android.tools.r8.graph.I2 i2, boolean z) {
        this.c = i2;
        this.d = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        com.android.tools.r8.graph.I2 i2 = this.c;
        com.android.tools.r8.graph.I2 i3 = ((K8) abstractC3175z9).c;
        i2.getClass();
        return abstractC3519a.a(i2, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.graph.I2 i2 = this.c;
        i2.getClass();
        oVar.a(i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        yo.c(192, abstractC3345r0.d(abstractC3148ys.c(abstractC3148ys2, this.c)));
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public void a(com.android.tools.r8.graph.Z5 z5, ListIterator listIterator) {
        z5.a(this.c, this.d);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0998Za
    public AbstractC3175z9 a(com.android.tools.r8.graph.I2 i2) {
        return new K8(i2, this.d);
    }

    public void a(C0602Jt c0602Jt, C0583Ja c0583Ja) {
        c0602Jt.a(c0583Ja.a, this.c, false);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        return abstractC1808j9.a(c0333y, interfaceC2576s8, c0333y.a().a2).a(interfaceC2576s8, this.c);
    }
}
