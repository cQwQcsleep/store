package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.C0171a4;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.UA;
import com.android.tools.r8.internal.VA;
import com.android.tools.r8.synthesis.S;
import com.android.tools.r8.synthesis.W;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.iti;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class VA {
    public static final /* synthetic */ boolean e = true;
    public final C0333y a;
    public final KA b;
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final int d;

    public VA(C0333y c0333y, int i) {
        this.a = c0333y;
        this.b = new KA(c0333y);
        this.d = i;
    }

    public static /* synthetic */ UA b(com.android.tools.r8.graph.D2 d2) {
        return new UA();
    }

    /* JADX WARN: Code duplicated, block: B:69:0x0114  */
    /* JADX WARN: Code duplicated, block: B:72:0x0134  */
    /* JADX WARN: Code duplicated, block: B:75:0x0177  */
    public final void a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6) {
        com.android.tools.r8.graph.D2 d2A;
        C0231j1 c0231j1E;
        boolean z;
        AbstractC0223i0 abstractC0223i0A;
        boolean z2 = e;
        if (!z2 && !C0171a4.a(b6.e().U0())) {
            x1f.a();
            return;
        }
        if (b5.e().U0() == null) {
            throw new C0613Ke("Code is missing for private instance interface method: " + b5.getReference().m0(), b5.getOrigin());
        }
        AbstractC0223i0 abstractC0223i0U0 = b5.e().U0();
        if (!z2 && abstractC0223i0U0 == null) {
            x1f.a();
            return;
        }
        if (!abstractC0223i0U0.y0()) {
            if (!z2 && !abstractC0223i0U0.w0()) {
                x1f.a();
                return;
            }
            for (AbstractC3175z9 abstractC3175z9 : abstractC0223i0U0.H().H0()) {
                if ((abstractC3175z9 instanceof G9) && ((G9) abstractC3175z9).b(b5.s())) {
                    throw new C0613Ke("One or more instruction is preventing default interface method from being desugared: " + b5.v(), b5.getOrigin());
                }
            }
            d2A = b5.a();
            c0231j1E = b5.e();
            z = e;
            if (z) {
            }
            if (z) {
            }
            if (c0231j1E.z0()) {
                a(d2A).a();
                a(d2A).a(b5.getReference(), b6.getReference());
            } else {
                a(d2A).a();
                a(d2A).a(b5.getReference(), b6.getReference());
            }
            if (c0231j1E.h1()) {
                b6.e().a(c0231j1E.T0());
            }
            abstractC0223i0A = c0231j1E.U0().a(b6.getReference(), b6.e().I0(), b5.getReference(), b5.e().I0(), this.a.a());
            if (!c0231j1E.z0()) {
                C0231j1.a(abstractC0223i0A, b6.getReference().A0(), this.a);
            }
            b6.a(abstractC0223i0A, this.a);
            b5.a(C0171a4.e, this.a);
        }
        for (AbstractC0138z1 abstractC0138z1 : abstractC0223i0U0.N().j) {
            if (abstractC0138z1 instanceof com.android.tools.r8.dex.code.U1) {
                throw new C0613Ke("One or more instruction is preventing default interface method from being desugared: " + b5.v(), b5.getOrigin());
            }
        }
        d2A = b5.a();
        c0231j1E = b5.e();
        z = e;
        if (z && c0231j1E.p1()) {
            x1f.a();
            return;
        }
        if (z && c0231j1E.z0() && !c0231j1E.J0() && !c0231j1E.K0()) {
            mu3.a("Static interface method ", b5.v(), " is expected to either be public or private in ", b5.getOrigin());
            return;
        }
        if (c0231j1E.z0() || c0231j1E.J0()) {
            a(d2A).a();
            a(d2A).a(b5.getReference(), b6.getReference());
        } else {
            if (!z) {
                this.b.getClass();
                if (!KA.a(c0231j1E)) {
                    x1f.a();
                    return;
                }
            }
            a(d2A).a(b5.e(), b6.e());
        }
        if (c0231j1E.h1()) {
            b6.e().a(c0231j1E.T0());
        }
        abstractC0223i0A = c0231j1E.U0().a(b6.getReference(), b6.e().I0(), b5.getReference(), b5.e().I0(), this.a.a());
        if (!c0231j1E.z0()) {
            C0231j1.a(abstractC0223i0A, b6.getReference().A0(), this.a);
        }
        b6.a(abstractC0223i0A, this.a);
        b5.a(C0171a4.e, this.a);
    }

    public final void c(com.android.tools.r8.graph.D2 d2) {
        if (!e && this.a.o()) {
            x1f.a();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = d2.U1().iterator();
        while (true) {
            Wh0 wh0 = (Wh0) it;
            if (!wh0.hasNext()) {
                break;
            }
            com.android.tools.r8.graph.B5 b5 = (com.android.tools.r8.graph.B5) wh0.next();
            if (a(b5)) {
                arrayList.add(b5.e());
            }
        }
        if (arrayList.size() < d2.V().h()) {
            d2.a((C0231j1[]) arrayList.toArray(C0231j1.u));
        } else {
            if (e) {
                return;
            }
            iti.a("Interface ", d2, " was analysed as having bridges to remove, but no bridges were found.");
        }
    }

    public static void a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.synthesis.W w) {
        w.k = d2.i;
    }

    public final void a(com.android.tools.r8.graph.B5 b5, MA ma) {
        boolean z = e;
        if (!z && this.a.o()) {
            x1f.a();
            return;
        }
        if (b5.a().isInterface()) {
            if (this.d == 2) {
                if (this.a.M().K1.a && !b5.e().g.F() && this.b.d(b5.s()) && this.b.b(b5.a(), b5) != null) {
                    KA ka = this.b;
                    C0231j1 c0231j1E = b5.e();
                    ka.getClass();
                    if (KA.a(c0231j1E)) {
                        a(b5, this.b.a(b5, ma));
                    }
                    if (a(b5)) {
                        return;
                    }
                    a(b5.a()).d = true;
                    return;
                }
                return;
            }
            if (b5.e().g.F()) {
                if (b5.e().m1()) {
                    return;
                }
                a(b5.a()).c = true;
                a(b5, this.b.b(b5, ma));
                return;
            }
            if (!z && !b5.e().M0()) {
                x1f.a();
                return;
            }
            KA ka2 = this.b;
            C0231j1 c0231j1E2 = b5.e();
            ka2.getClass();
            if (KA.a(c0231j1E2)) {
                a(b5, this.b.a(b5, ma));
            }
            if (a(b5)) {
                return;
            }
            a(b5.a()).d = true;
        }
    }

    public final boolean a(com.android.tools.r8.graph.B5 b5) {
        com.android.tools.r8.graph.E0 e0D;
        if (!e && this.a.o()) {
            x1f.a();
            return false;
        }
        com.android.tools.r8.graph.D2 d2A = b5.a();
        if (!b5.getAccessFlags().K()) {
            return true;
        }
        if (this.a.M().U()) {
            return false;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        com.android.tools.r8.graph.I2 i2 = d2A.g;
        if (i2 != null) {
            arrayDeque.add(new C1405eW(d2A, i2));
        }
        for (com.android.tools.r8.graph.I2 i3 : d2A.h.b) {
            arrayDeque.add(new C1405eW(d2A, i3));
        }
        while (!arrayDeque.isEmpty()) {
            C1405eW c1405eW = (C1405eW) arrayDeque.pop();
            com.android.tools.r8.graph.I2 i4 = (com.android.tools.r8.graph.I2) c1405eW.b();
            com.android.tools.r8.graph.E0 e0 = (com.android.tools.r8.graph.E0) c1405eW.a();
            boolean zA0 = e0.a0();
            C0333y c0333y = this.a;
            if (zA0) {
                C0215h c0215hG = c0333y.g();
                e0D = e0.X();
                c0215hG.getClass();
                if (e0D.e != i4) {
                    com.android.tools.r8.graph.E0 e0D2 = c0215hG.d(i4);
                    if (e0D2 != null && !e0D2.b0() && !e0D.b0()) {
                        c0215hG.a(e0D, e0D2);
                    }
                    e0D = e0D2;
                }
            } else {
                e0D = c0333y.d(i4);
            }
            if (e0D != null && hashSet.add(e0D.e)) {
                if (e0D.d(b5.getReference()) != null) {
                    return false;
                }
                com.android.tools.r8.graph.I2 i5 = e0D.g;
                if (i5 != null) {
                    arrayDeque.add(new C1405eW(e0D, i5));
                }
                for (com.android.tools.r8.graph.I2 i6 : e0D.h.b) {
                    arrayDeque.add(new C1405eW(e0D, i6));
                }
            }
        }
        return true;
    }

    public final TA a() {
        int i = TA.o;
        final SA sa = new SA();
        this.c.forEach(new BiConsumer() { // from class: l6f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(sa, (D2) obj, (UA) obj2);
            }
        });
        C0333y c0333y = this.a;
        if (sa.a.b.isEmpty() && sa.b.b.isEmpty()) {
            return null;
        }
        return new TA(c0333y, sa.a, sa.b);
    }

    public static void a(SA sa, C0231j1 c0231j1, C0231j1 c0231j2) {
        boolean z = e;
        if (!z && !C0171a4.a(c0231j1.U0())) {
            x1f.a();
            return;
        }
        if (!z && C0171a4.a(c0231j2.U0())) {
            x1f.a();
            return;
        }
        c0231j1.g.b(Fcntl.S_ISGID);
        c0231j1.O0();
        c0231j1.j = null;
        sa.b.b.a(c0231j1.getReference(), c0231j2.getReference());
    }

    public final UA a(com.android.tools.r8.graph.D2 d2) {
        return (UA) this.c.computeIfAbsent(d2, new Function() { // from class: r6f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VA.b((D2) obj);
            }
        });
    }

    public final void a(final BiConsumer biConsumer) {
        this.c.forEach(new BiConsumer() { // from class: m6f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                VA.a(biConsumer, (D2) obj, (UA) obj2);
            }
        });
    }

    public static /* synthetic */ void a(BiConsumer biConsumer, com.android.tools.r8.graph.D2 d2, UA ua) {
        IdentityHashMap identityHashMap = ua.b;
        if (identityHashMap != null) {
            identityHashMap.forEach(biConsumer);
        }
    }

    public final void a(final SA sa, com.android.tools.r8.graph.D2 d2, UA ua) {
        if (ua.c || this.a.o()) {
            C0231j1 c0231j1O0 = d2.O0();
            com.android.tools.r8.graph.H4 h4V = d2.V();
            if (c0231j1O0 != null) {
                h4V.a(new C0231j1[]{c0231j1O0});
            } else {
                h4V.b();
            }
        }
        IdentityHashMap identityHashMap = ua.a;
        if (identityHashMap != null) {
            identityHashMap.forEach(new BiConsumer() { // from class: p6f
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    VA.a(sa, (C0231j1) obj, (C0231j1) obj2);
                }
            });
        }
        IdentityHashMap identityHashMap2 = ua.b;
        if (identityHashMap2 != null) {
            Objects.requireNonNull(sa);
            identityHashMap2.forEach(new BiConsumer() { // from class: q6f
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    sa.a((C0322w2) obj, (C0322w2) obj2);
                }
            });
        }
        if (ua.d) {
            if (e || !this.a.o()) {
                c(d2);
            } else {
                x1f.a();
            }
        }
    }

    public static com.android.tools.r8.graph.B5 a(final com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.E2 e2, C0333y c0333y, Consumer consumer, Consumer consumer2) {
        return c0333y.a.g().a(h2, e2, new com.android.tools.r8.synthesis.I() { // from class: n6f
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.j;
            }
        }, d2, c0333y, new Consumer() { // from class: o6f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VA.a(d2, (W) obj);
            }
        }, consumer, consumer2);
    }
}
