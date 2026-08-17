package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C0920Wa;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.hkh;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0920Wa extends J9 {
    public static final /* synthetic */ boolean g = true;
    public final a c;
    public final K9 d;
    public final int[] e;
    public final List f;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.Wa$a */
    public enum a {
        b,
        c;

        a() {
        }
    }

    public C0920Wa(a aVar, K9 k9, int[] iArr, ArrayList arrayList) {
        this.c = aVar;
        this.d = k9;
        this.e = iArr;
        this.f = arrayList;
        boolean z = g;
        if (!z && aVar == a.b && iArr.length != arrayList.size()) {
            x1f.a();
            throw null;
        }
        if (z || aVar != a.c || iArr.length == 1) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean N() {
        return true;
    }

    public final K9 U() {
        return this.d;
    }

    public List<Integer> V() {
        return new C0919Vz(this.e);
    }

    public a W() {
        return this.c;
    }

    public final List X() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B1 b1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC0837Sv abstractC0837Sv, AbstractC3345r0 abstractC3345r0, RJ rj, YO yo) {
        WI[] wiArr = new WI[this.f.size()];
        for (int i = 0; i < this.f.size(); i++) {
            wiArr[i] = ((K9) this.f.get(i)).T();
        }
        int i2 = AbstractC0894Va.a[this.c.ordinal()];
        if (i2 == 1) {
            yo.a(this.d.T(), this.e, wiArr);
        } else {
            if (i2 != 2) {
                return;
            }
            int i3 = this.e[0];
            yo.a(i3, (this.f.size() + i3) - 1, this.d.T(), wiArr);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        int i = AbstractC0894Va.a[this.c.ordinal()];
        if (i == 1) {
            return (this.e.length * 8) + 8;
        }
        if (i == 2) {
            int i2 = this.e[0];
            return (((((this.f.size() + i2) - 1) - i2) + 1) * 4) + 16;
        }
        hkh.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int z() {
        return this.c == a.b ? 171 : 170;
    }

    public final /* synthetic */ AbstractC1597gi0 a(BiFunction biFunction, C1512fi0 c1512fi0) {
        return (AbstractC1597gi0) biFunction.apply(this.d, c1512fi0.f());
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, final com.android.tools.r8.graph.O o) {
        if (g || this.c == ((C0920Wa) abstractC3175z9).c) {
            return abstractC3519a.a(this, (C0920Wa) abstractC3175z9, (com.android.tools.r8.utils.structural.y<C0920Wa>) new com.android.tools.r8.utils.structural.y() { // from class: wff
                @Override // com.android.tools.r8.utils.structural.y
                public final void a(A a2) {
                    C0920Wa.a(o, a2);
                }
            });
        }
        x1f.a();
        return 0;
    }

    public static void a(com.android.tools.r8.graph.O o, com.android.tools.r8.utils.structural.A a2) {
        Function function = new Function() { // from class: sff
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0920Wa) obj).U();
            }
        };
        com.android.tools.r8.utils.structural.u uVarA = o.a();
        a2.a(function, uVarA, uVarA).d(new Function() { // from class: tff
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0920Wa) obj).e;
            }
        }).a(new Function() { // from class: uff
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0920Wa) obj).X();
            }
        }, o.a());
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
        qVar.a.a(this.e.length);
        int i = 0;
        while (true) {
            int[] iArr = this.e;
            if (i >= iArr.length) {
                return;
            }
            qVar.a.a(iArr[i]);
            i++;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1597gi0 a(final BiFunction biFunction, AbstractC3175z9 abstractC3175z9, AbstractC1808j9 abstractC1808j9) {
        return AbstractC1683hi0.a(this.f, biFunction, abstractC1808j9).a(new Function() { // from class: vff
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(biFunction, (C1512fi0) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C2520ra c2520ra) {
        c2520ra.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(C0602Jt c0602Jt, C0738Pa c0738Pa, C0401Ca c0401Ca) {
        int[] iArr = new int[this.f.size()];
        for (int i = 0; i < this.f.size(); i++) {
            iArr[i] = c0401Ca.a((K9) this.f.get(i));
        }
        c0602Jt.a(c0738Pa.a().a, this.e, c0401Ca.a(this.d), iArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final AbstractC1808j9 a(AbstractC1808j9 abstractC1808j9, C0333y c0333y, InterfaceC2576s8 interfaceC2576s8) {
        return abstractC1808j9.a(c0333y, interfaceC2576s8, c0333y.a().B1);
    }
}
