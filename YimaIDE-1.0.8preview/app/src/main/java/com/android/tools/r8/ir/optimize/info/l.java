package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.internal.AbstractC0423Cw;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC0570In;
import com.android.tools.r8.internal.AbstractC1047aC;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.B7;
import com.android.tools.r8.internal.C2113mk0;
import com.android.tools.r8.internal.Gb0;
import com.android.tools.r8.internal.InterfaceC2182nc;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.VB;
import com.android.tools.r8.ir.optimize.info.k;
import com.android.tools.r8.ir.optimize.info.l;
import java.util.BitSet;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l extends h {
    public static final /* synthetic */ int j = 0;
    public final B1 b;
    public final AbstractC0439Dm c;
    public final boolean d;
    public final boolean e;
    public final BitSet f;
    public final BitSet g;
    public final int h;
    public final boolean i;

    public l(B1 b1, AbstractC0439Dm abstractC0439Dm, boolean z, boolean z2, BitSet bitSet, BitSet bitSet2, int i, boolean z3) {
        this.b = b1;
        this.c = abstractC0439Dm;
        this.d = z;
        this.e = z2;
        this.f = bitSet;
        this.g = bitSet2;
        this.h = i;
        this.i = z3;
    }

    public static h a(final w wVar) {
        k kVar = new k();
        kVar.a = wVar.f;
        kVar.b = wVar.j;
        kVar.c = wVar.a(8);
        kVar.d = wVar.a(32);
        kVar.e = wVar.o;
        kVar.f = wVar.n;
        k kVarA = kVar.a(wVar.G(), new Consumer() { // from class: iih
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                l.a(wVar, (k) obj);
            }
        });
        kVarA.h = wVar.a(16);
        return (kVarA.b.l() && kVarA.a.isUnknown() && kVarA.g < 0 && kVarA.e == null && kVarA.f == null && kVarA.c && !kVarA.d && !kVarA.h) ? C3263d.b : new l(kVarA.a, kVarA.b, kVarA.c, kVarA.d, kVarA.e, kVarA.f, kVarA.g, kVarA.h);
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean A() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC2173nV B() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean C() {
        return this.d;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean D() {
        return this.e;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean E() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean F() {
        return this.i;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean G() {
        return this.h >= 0;
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final g b() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean e() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean f() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean g() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final com.android.tools.r8.internal.r h() {
        return C2113mk0.a;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final B1 i() {
        return this.b;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC3260a j() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final B7 k() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final InterfaceC2182nc l() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0423Cw m() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0439Dm n() {
        return this.c;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0570In o() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final Set p() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final int q() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet r() {
        return this.f;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet s() {
        return this.g;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final int t() {
        return this.h;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final Gb0 u() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final BitSet v() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean w() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean y() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean z() {
        throw new Kk0();
    }

    public static void a(h hVar, k kVar) {
        kVar.g = hVar.t();
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final boolean a(AbstractC1047aC abstractC1047aC) {
        return this.d;
    }

    @Override // com.android.tools.r8.ir.optimize.info.h
    public final AbstractC0423Cw a(VB vb) {
        throw new Kk0();
    }
}
