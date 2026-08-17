package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0244l0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.C0501Fw;
import com.android.tools.r8.internal.InterfaceC2603sY;
import defpackage.ge1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0501Fw {
    public static final /* synthetic */ boolean h = true;
    public final C0333y a;
    public final AbstractC2647t1 b;
    public final com.android.tools.r8.graph.B1 c;
    public final C1868jt d;
    public final List e;
    public final C0397Bw f;
    public final C1441et g;

    public C0501Fw(C0333y c0333y, AbstractC2647t1 abstractC2647t1, C1868jt c1868jt, List list, C1441et c1441et, C0397Bw c0397Bw) {
        this.a = c0333y;
        this.b = abstractC2647t1;
        this.c = c0333y.a();
        this.d = c1868jt;
        this.e = list;
        this.f = c0397Bw;
        this.g = c1441et;
        boolean z = h;
        if (!z && list.isEmpty()) {
            x1f.a();
            throw null;
        }
        if (z || list.stream().map(new Function() { // from class: qv5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((B5) obj).F();
            }
        }).distinct().count() == 1 || c0397Bw != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final void a(C0481Fc c0481Fc, AbstractC2775uY abstractC2775uY, C0559Ic c0559Ic) {
        C1159bb c1159bb;
        AbstractC0223i0 c0770Qg;
        C0231j1 c0231j1A;
        final com.android.tools.r8.graph.B5 b5 = (com.android.tools.r8.graph.B5) C2847vL.a(this.e);
        boolean z = this.e.size() > 1 && (!b() || this.d.d());
        com.android.tools.r8.graph.I2[] i2Arr = b5.E().b;
        int iA = Y6.a(z) + b5.E().size();
        com.android.tools.r8.graph.I2[] i2Arr2 = new com.android.tools.r8.graph.I2[iA];
        System.arraycopy(i2Arr, 0, i2Arr2, 0, i2Arr.length);
        for (int i = 0; i < i2Arr.length; i++) {
            Set setA = a(i, this.e);
            if (setA.size() > 1) {
                com.android.tools.r8.graph.I2 i2A = com.android.tools.r8.graph.L2.a(setA, this.a);
                if (!h) {
                    com.android.tools.r8.graph.I2 i2A2 = com.android.tools.r8.graph.L2.a(this.a, i2A);
                    i2A2.getClass();
                    if (!com.android.tools.r8.graph.I2.a(i2A2, i2A)) {
                        x1f.a();
                        return;
                    }
                }
                i2Arr2[i] = i2A;
            }
        }
        if (z) {
            if (!h && R3.b(i2Arr2) != null) {
                x1f.a();
                return;
            }
            i2Arr2[iA - 1] = this.c.B1;
        }
        com.android.tools.r8.graph.B1 b1 = this.c;
        C0322w2 c0322w2A = b1.a(this.d.d.getType(), b1.a(b1.E1, i2Arr2), b1.c1);
        com.android.tools.r8.graph.B1 b2 = this.c;
        AbstractC0551Hu abstractC0551Hu = c0481Fc.a;
        Objects.requireNonNull(c0559Ic);
        ge1 ge1Var = new ge1(c0559Ic);
        b2.getClass();
        C0322w2 c0322w2A2 = b2.a(c0322w2A, abstractC0551Hu, ge1Var, C0822Sg.b());
        List listA = AbstractC0649Lo.a(c0322w2A, c0322w2A2);
        if (b()) {
            C1441et c1441et = this.g;
            List list = this.e;
            c1441et.getClass();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                c1441et.a(((com.android.tools.r8.graph.B5) it.next()).getReference(), c0322w2A2, false);
            }
        } else if (d()) {
            for (com.android.tools.r8.graph.B5 b6 : this.e) {
                C0322w2 c0322w2A3 = a(c0481Fc, c0559Ic, b6, c0322w2A2);
                this.g.b.a(c0322w2A3, c0322w2A3);
                this.g.b(b6.getReference(), c0322w2A3, false);
                abstractC2775uY.a(b6.getReference(), new Consumer() { // from class: pv5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        C0501Fw.a(b5, (InterfaceC2603sY) obj);
                    }
                });
            }
        } else {
            this.g.a(b5.getReference(), c0322w2A2, true);
        }
        C0322w2 c0322w2A4 = a(c0559Ic, c0322w2A2);
        if (d()) {
            this.g.b(c0322w2A4, c0322w2A2, true);
        }
        Iterator it2 = this.e.iterator();
        while (true) {
            c1159bb = null;
            if (!it2.hasNext()) {
                break;
            }
            com.android.tools.r8.graph.B5 b7 = (com.android.tools.r8.graph.B5) it2.next();
            C0467Eo c0467Eo = z ? new C0467Eo(this.b.b(b7.s())) : null;
            C1441et c1441et2 = this.g;
            C0322w2 reference = b7.getReference();
            c1441et2.b.a(reference, c0322w2A2);
            if (c0467Eo != null) {
                c1441et2.d.put(reference, c0467Eo);
            }
        }
        C0231j1 c0231j1E = b5.e();
        if (b() || d()) {
            C0231j1[] c0231j1Arr = C0231j1.u;
            C0231j1.a aVarA = AbstractC3207zc.a(c0322w2A2, true).a(com.android.tools.r8.graph.F4.b(4097, true));
            int size = listA.size();
            if (b()) {
                C0397Bw c0397Bw = this.f;
                C1868jt c1868jt = this.d;
                c0397Bw.getClass();
                c0770Qg = new C0500Fv(z ? c1868jt.a() : null, size, (LinkedHashMap) c0397Bw.a, (LinkedHashMap) c0397Bw.b, c0397Bw.c, (ArrayList) c0397Bw.d);
            } else {
                boolean z2 = h;
                if (!z2 && !d()) {
                    x1f.a();
                    return;
                }
                if (!z2 && b()) {
                    x1f.a();
                    return;
                }
                C0944Wy c0944Wy = new C0944Wy();
                for (com.android.tools.r8.graph.B5 b8 : this.e) {
                    c0944Wy.a(this.b.b(b8.s()), b8.getReference());
                }
                c0770Qg = new C0770Qg(c0944Wy, this.d.d() ? this.d.a() : null, size);
            }
            C0231j1.a aVarA2 = aVarA.a(c0770Qg);
            for (com.android.tools.r8.graph.B5 b9 : this.e) {
                if (b9.e().h1()) {
                    c1159bb = (C1159bb) com.android.tools.r8.utils.structural.s.c(c1159bb, b9.e().T0());
                }
            }
            aVarA2.k = c1159bb;
            aVarA2.l = c0231j1E.e;
            aVarA2.m = c0231j1E.o;
            c0231j1A = aVarA2.a();
        } else {
            com.android.tools.r8.graph.B1 b3 = this.c;
            c0231j1E.O0();
            c0231j1A = c0231j1E.a(c0322w2A2, b3, (Consumer) null);
        }
        c0559Ic.a(c0231j1A);
        if (h) {
            return;
        }
        AbstractC0223i0 abstractC0223i0U0 = c0231j1A.U0();
        abstractC0223i0U0.getClass();
        if ((abstractC0223i0U0 instanceof C0244l0) || c0231j1A.U0().D0() || c0231j1A.U0().C0()) {
            return;
        }
        x1f.a();
    }

    public final boolean b() {
        return this.f != null;
    }

    public final void c() {
        if (b() || !d()) {
            this.e.forEach(new Consumer() { // from class: mv5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C0501Fw.a((B5) obj);
                }
            });
        }
    }

    public final boolean d() {
        return this.e.size() != 1 || this.d.d();
    }

    public static Set a(final int i, final List list) {
        return C1755ib0.a(new InterfaceC0806Rq() { // from class: ov5
            @Override // com.android.tools.r8.internal.InterfaceC0806Rq
            public final void forEach(Consumer consumer) {
                list.forEach(new Consumer() { // from class: nv5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        consumer.accept(((B5) obj).b(i));
                    }
                });
            }
        });
    }

    public final C0322w2 a(C0559Ic c0559Ic, C0322w2 c0322w2) {
        com.android.tools.r8.graph.B1 b1 = this.c;
        com.android.tools.r8.graph.E2 e2C0 = c0322w2.C0();
        com.android.tools.r8.graph.I2 i2W0 = c0322w2.w0();
        Objects.requireNonNull(c0559Ic);
        return b1.a("$r8$init$synthetic", e2C0, i2W0, new ge1(c0559Ic));
    }

    public final C0322w2 a(C0481Fc c0481Fc, final C0559Ic c0559Ic, com.android.tools.r8.graph.B5 b5, final C0322w2 c0322w2) {
        com.android.tools.r8.graph.B1 b1 = this.c;
        C0322w2 reference = b5.getReference();
        com.android.tools.r8.graph.D2 d2 = this.d.d;
        com.android.tools.r8.graph.B1 b2 = this.c;
        reference.getClass();
        C0322w2 c0322w2A = reference.a(d2.getType(), b2);
        AbstractC0551Hu abstractC0551Hu = c0481Fc.a;
        Predicate predicate = new Predicate() { // from class: rv5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0501Fw.a(c0559Ic, c0322w2, (C0322w2) obj);
            }
        };
        b1.getClass();
        C0322w2 c0322w2A2 = b1.a(c0322w2A, abstractC0551Hu, predicate, C0822Sg.b());
        if (c0322w2A2.a(b5.getReference())) {
            c0559Ic.a(b5.e());
            return c0322w2A2;
        }
        C0231j1 c0231j1E = b5.e();
        com.android.tools.r8.graph.B1 b3 = this.c;
        c0231j1E.O0();
        c0559Ic.a(c0231j1E.a(c0322w2A2, b3, (Consumer) null));
        return c0322w2A2;
    }

    public static boolean a(C0559Ic c0559Ic, C0322w2 c0322w2, C0322w2 c0322w3) {
        return c0559Ic.a(c0322w3) && !c0322w3.a(c0322w2);
    }

    public final int a() {
        return ((com.android.tools.r8.graph.B5) this.e.iterator().next()).getReference().A0();
    }

    public static void a(com.android.tools.r8.graph.B5 b5, InterfaceC2603sY interfaceC2603sY) {
        interfaceC2603sY.getClass();
        interfaceC2603sY.a((com.android.tools.r8.graph.F2) b5.getReference());
    }

    public static void a(com.android.tools.r8.graph.B5 b5) {
        b5.e().t = true;
    }
}
