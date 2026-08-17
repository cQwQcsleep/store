package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1899kE;
import com.android.tools.r8.internal.C1903kI;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C3015xH;
import com.android.tools.r8.internal.C3100yI;
import com.android.tools.r8.internal.GI;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.UH;
import com.android.tools.r8.internal.VH;
import com.android.tools.r8.kotlin.C3296n;
import com.android.tools.r8.kotlin.r0;
import com.android.tools.r8.kotlin.t0;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import defpackage.j2i;
import defpackage.w33;
import defpackage.z1i;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class r0 implements InterfaceC3369b0 {
    public static final P40 i;
    public final int a;
    public final AbstractC3305x b;
    public final r0 c;
    public final r0 d;
    public final List e;
    public final List f;
    public final I g;
    public final boolean h;

    static {
        int i2 = AbstractC0551Hu.c;
        i = P40.e;
    }

    public r0(int i2, AbstractC3305x abstractC3305x, r0 r0Var, r0 r0Var2, AbstractC0551Hu abstractC0551Hu, AbstractC0551Hu abstractC0551Hu2, I i3, boolean z) {
        this.a = i2;
        this.b = abstractC3305x;
        this.c = r0Var;
        this.d = r0Var2;
        this.e = abstractC0551Hu;
        this.f = abstractC0551Hu2;
        this.g = i3;
        this.h = z;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x008c  */
    /* JADX WARN: Code duplicated, block: B:26:0x008f  */
    /* JADX WARN: Code duplicated, block: B:29:0x009d A[LOOP:1: B:27:0x0097->B:29:0x009d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:33:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:34:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d9 A[LOOP:0: B:35:0x00d3->B:37:0x00d9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:41:0x0106  */
    /* JADX WARN: Code duplicated, block: B:42:0x010a  */
    public static r0 a(B1 b1, C2742u50 c2742u50, C3100yI c3100yI) {
        AbstractC3305x c3304w;
        AbstractC3305x c3300s;
        List<GI> listD;
        C0473Eu c0473EuG;
        AbstractC0551Hu abstractC0551HuA;
        List<C3015xH> listA;
        C0473Eu c0473EuG2;
        AbstractC0551Hu abstractC0551HuA2;
        C1903kI c1903kIF;
        I i2;
        if (c3100yI == null) {
            return null;
        }
        int iE = c3100yI.e();
        VH vh = c3100yI.c;
        if (vh instanceof UH) {
            String str = ((UH) vh).a;
            boolean zStartsWith = str.startsWith(".");
            String strM = C0929Wj.m(zStartsWith ? str.substring(1) : str);
            if (C0929Wj.z(strM)) {
                c3300s = new C3300s(u0.a(strM, b1, strM, false), zStartsWith);
            } else {
                c3304w = new C3303v(str);
            }
            r0 r0VarA = a(b1, c2742u50, c3100yI.c());
            r0 r0VarA2 = a(b1, c2742u50, c3100yI.g());
            listD = c3100yI.d();
            if (listD.isEmpty()) {
                abstractC0551HuA = i;
            } else {
                c0473EuG = AbstractC0551Hu.g();
                for (GI gi : listD) {
                    c0473EuG.a(new t0(gi.b(), a(b1, c2742u50, gi.a())));
                }
                abstractC0551HuA = c0473EuG.a();
            }
            listA = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.d.a(c3100yI);
            P40 p40 = C3296n.c;
            if (listA.isEmpty()) {
                abstractC0551HuA2 = C3296n.c;
            } else {
                c0473EuG2 = AbstractC0551Hu.g();
                for (C3015xH c3015xH : listA) {
                    c0473EuG2.a(new C3296n(u0.a(c3015xH.b(), b1, c3015xH.b()), AbstractC3295m.a(c3015xH.a(), b1)));
                }
                abstractC0551HuA2 = c0473EuG2.a();
            }
            c1903kIF = c3100yI.f();
            if (c1903kIF == null) {
                i2 = I.c;
            } else {
                i2 = new I(c1903kIF.b(), a(b1, c2742u50, c1903kIF.a()));
            }
            return new r0(iE, c3300s, r0VarA, r0VarA2, abstractC0551HuA, abstractC0551HuA2, i2, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.d.b(c3100yI));
        }
        if (vh instanceof VH.a) {
            c3304w = new C3301t(((VH.a) vh).a);
        } else if (vh instanceof VH.b) {
            c3304w = new C3302u(((VH.b) vh).a());
        } else {
            c2742u50.warning(S.a(vh.toString()));
            c3304w = new C3304w(vh.toString());
        }
        c3300s = c3304w;
        r0 r0VarA3 = a(b1, c2742u50, c3100yI.c());
        r0 r0VarA4 = a(b1, c2742u50, c3100yI.g());
        listD = c3100yI.d();
        if (listD.isEmpty()) {
            abstractC0551HuA = i;
        } else {
            c0473EuG = AbstractC0551Hu.g();
            while (r5.hasNext()) {
                c0473EuG.a(new t0(gi.b(), a(b1, c2742u50, gi.a())));
            }
            abstractC0551HuA = c0473EuG.a();
        }
        listA = com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.d.a(c3100yI);
        P40 p41 = C3296n.c;
        if (listA.isEmpty()) {
            abstractC0551HuA2 = C3296n.c;
        } else {
            c0473EuG2 = AbstractC0551Hu.g();
            while (r6.hasNext()) {
                c0473EuG2.a(new C3296n(u0.a(c3015xH.b(), b1, c3015xH.b()), AbstractC3295m.a(c3015xH.a(), b1)));
            }
            abstractC0551HuA2 = c0473EuG2.a();
        }
        c1903kIF = c3100yI.f();
        if (c1903kIF == null) {
            i2 = I.c;
        } else {
            i2 = new I(c1903kIF.b(), a(b1, c2742u50, c1903kIF.a()));
        }
        return new r0(iE, c3300s, r0VarA3, r0VarA4, abstractC0551HuA, abstractC0551HuA2, i2, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.d.b(c3100yI));
    }

    public final boolean b(Consumer consumer, C0333y c0333y) {
        final C3100yI c3100yI = (C3100yI) d0.a(consumer, new C3100yI(this.a));
        boolean zA = this.b.a(c3100yI, c0333y) | d0.a(c0333y, this.c, new Consumer() { // from class: y5i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c3100yI.a((C3100yI) obj);
            }
        }, new w33()) | d0.a(c0333y, this.d, new Consumer() { // from class: c6i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c3100yI.b((C3100yI) obj);
            }
        }, new w33()) | d0.a(c0333y, this.e, c3100yI.d(), new InterfaceC1938ki0() { // from class: f6i
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(((t0) obj).b((Consumer) obj2, (C0333y) obj3));
            }
        }) | this.g.b(new Consumer() { // from class: i6i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c3100yI.a((C1903kI) obj);
            }
        }, c0333y);
        if (this.f.isEmpty() && !this.h) {
            return zA;
        }
        boolean zA2 = d0.a(c0333y, this.f, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.d.a(c3100yI), new j2i()) | zA;
        ((C1899kE) c3100yI.a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.n.b)).c = this.h;
        return zA2;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        this.b.a(interfaceC0189d1);
        r0 r0Var = this.c;
        if (r0Var != null) {
            r0Var.a(interfaceC0189d1);
        }
        r0 r0Var2 = this.d;
        if (r0Var2 != null) {
            r0Var2.a(interfaceC0189d1);
        }
        C0470Er.a((Iterable) this.e, new Function() { // from class: k6i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return r0.a((t0) obj);
            }
        }, (Object) interfaceC0189d1);
        this.g.a(interfaceC0189d1);
        C0470Er.a((Iterable) this.f, new Function() { // from class: l6i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return r0.a((C3296n) obj);
            }
        }, (Object) interfaceC0189d1);
    }

    public static /* synthetic */ Consumer a(final t0 t0Var) {
        Objects.requireNonNull(t0Var);
        return new Consumer() { // from class: t5i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                t0Var.a((InterfaceC0189d1) obj);
            }
        };
    }

    public static /* synthetic */ Consumer a(C3296n c3296n) {
        Objects.requireNonNull(c3296n);
        return new z1i(c3296n);
    }

    public final I2 a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this.b.a(abstractC3148ys, abstractC3148ys2);
    }
}
