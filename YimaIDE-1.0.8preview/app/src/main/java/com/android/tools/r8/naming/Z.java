package com.android.tools.r8.naming;

import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.H0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.X5;
import com.android.tools.r8.internal.AbstractC0464El;
import com.android.tools.r8.internal.C0412Cl;
import com.android.tools.r8.internal.C0438Dl;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C0860Ts;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.GC;
import com.android.tools.r8.internal.InterfaceC1936kh0;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.internal.Sm0;
import com.android.tools.r8.naming.Z;
import com.android.tools.r8.shaking.C3403i;
import defpackage.jc4;
import defpackage.v5g;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z {
    public static final /* synthetic */ boolean j = true;
    public final C0333y a;
    public final W b;
    public final IdentityHashMap c = new IdentityHashMap();
    public final Y d = new Y(this);
    public final C0860Ts e;
    public final IdentityHashMap f;
    public final IdentityHashMap g;
    public final C3314b0 h;
    public final C3320e0 i;

    public Z(C0333y c0333y, C3336m0 c3336m0) {
        C0860Ts c0860TsJ = C0860Ts.j();
        this.e = c0860TsJ;
        IdentityHashMap identityHashMap = new IdentityHashMap();
        this.f = identityHashMap;
        this.g = new IdentityHashMap();
        this.a = c0333y;
        this.b = c3336m0;
        C3320e0 c3320e0 = new C3320e0(null, c());
        this.i = c3320e0;
        c0860TsJ.a(null, c3320e0, false);
        C3314b0 c3314b0 = new C3314b0(null, b(), c3336m0, c3320e0);
        this.h = c3314b0;
        identityHashMap.put(null, c3314b0);
    }

    public static Function c() {
        return new Function() { // from class: a6g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0322w2) obj).B0();
            }
        };
    }

    public final void a(final com.android.tools.r8.graph.E0 e0) {
        I2 i2 = e0.e;
        final C3320e0 c3320e0 = (C3320e0) this.e.get(this.g.getOrDefault(i2, i2));
        if (!j && c3320e0 == null) {
            throw new AssertionError("Could not find reservation state for " + i2.toString());
        }
        C3314b0 c3314b0 = (C3314b0) this.f.computeIfAbsent(i2, new Function() { // from class: u5g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(e0, c3320e0, (I2) obj);
            }
        });
        if (this.b.a(e0)) {
            for (com.android.tools.r8.graph.H0 h0 : C2847vL.a(e0.F0(), Comparator.comparing(new v5g()), e0.V().i())) {
                if (!h0.e().p1()) {
                    H2 h2A = this.b.a(h0);
                    if (h2A == null || h2A.b(h0.getReference().x0())) {
                        h2A = c3314b0.a(h0);
                    }
                    if (!h2A.b(h0.getReference().x0())) {
                        this.c.put(h0.getReference(), h2A);
                    }
                    c3314b0.getClass();
                    ((C3312a0) c3314b0.c(h0.getReference())).a(h2A, h0.getReference());
                }
            }
        }
    }

    public final C3314b0 b(I2 i2) {
        C3314b0 c3314b0B;
        C3314b0 c3314b0 = (C3314b0) this.f.get(i2);
        if (c3314b0 != null) {
            return c3314b0;
        }
        if (i2 == this.a.a().a2) {
            c3314b0B = this.h;
        } else {
            com.android.tools.r8.graph.E0 e0D = this.a.d(i2);
            c3314b0B = e0D == null ? b(this.a.a().a2) : b(e0D.g);
        }
        C3320e0 c3320e0A = a(i2);
        if (j || c3320e0A != null) {
            C3314b0 c3314b0A = c3314b0B.a(c3320e0A);
            this.f.put(i2, c3314b0A);
            return c3314b0A;
        }
        throw new AssertionError("Could not find reservation state for " + i2.toString());
    }

    public final void d() {
        if (this.a.M().H().s()) {
            ((C3403i) this.a.g()).a(new Consumer() { // from class: b6g
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a((I0) obj);
                }
            });
        }
    }

    public final void e() throws Throwable {
        a(this.a.a().a2, this.a.a().a2, this.i);
        new X5(this.a, 1).a(((C3403i) this.a.g()).e(), new InterfaceC1936kh0() { // from class: x5g
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                this.a.b((E0) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void b(com.android.tools.r8.graph.E0 e0) {
        I2 i2 = e0.e;
        I2 i3 = (I2) this.g.getOrDefault(e0.g, i2);
        if (i3 != i2 || e0.a0()) {
            I2 i4 = (I2) this.g.put(e0.e, i3);
            if (!j && i4 != null) {
                x1f.a();
                return;
            }
        }
        a(i2, i3, (C3320e0) this.e.getOrDefault(e0.g, this.i));
    }

    public final Function b() {
        if (this.a.M().j instanceof ClassFileConsumer) {
            return c();
        }
        return new Function() { // from class: w5g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Z.a((C0322w2) obj);
            }
        };
    }

    public static C3320e0 a(C3320e0 c3320e0, I2 i2) {
        return new C3320e0(c3320e0, c3320e0.b);
    }

    public static void a(C3320e0 c3320e0, Map map, com.android.tools.r8.graph.H0 h0) {
        if (c3320e0.a(h0.getReference().x0(), h0.getReference())) {
            ((Set) map.computeIfAbsent(h0.getReference().x0(), C0470Er.a(new jc4()))).add(Integer.valueOf(h0.getReference().C0().p0()));
        }
    }

    public final Map a(com.android.tools.r8.graph.E0 e0, C0438Dl c0438Dl) {
        if (c0438Dl.b.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        final Sm0 sm0 = new Sm0(2);
        sm0.b(e0);
        final HashMap map = new HashMap();
        while (sm0.b()) {
            com.android.tools.r8.graph.E0 e1 = (com.android.tools.r8.graph.E0) sm0.d();
            final C3320e0 c3320e0 = (C3320e0) this.e.get(this.g.get(e1.getType()));
            if (c3320e0 != null) {
                c0438Dl.forEach(new Consumer() { // from class: y5g
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Z.a(c3320e0, map, (H0) obj);
                    }
                });
            }
            e1.f(new Consumer() { // from class: z5g
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.a(sm0, (I2) obj);
                }
            });
        }
        return C2752uB.b() ? Collections.unmodifiableMap(map) : map;
    }

    public final void a() throws Throwable {
        X5 x5 = new X5(this.a, 1);
        x5.e = true;
        x5.a(((C3403i) this.a.g()).d(), new InterfaceC1936kh0() { // from class: r5g
            @Override // com.android.tools.r8.internal.InterfaceC1936kh0
            public final void accept(Object obj) {
                this.a.a((E0) obj);
            }
        });
    }

    public static /* synthetic */ Object a(C0322w2 c0322w2) {
        return null;
    }

    public final C3314b0 a(com.android.tools.r8.graph.E0 e0, C3320e0 c3320e0, I2 i2) {
        C3314b0 c3314b0 = (C3314b0) this.f.getOrDefault(e0.g, this.h);
        return new C3314b0(c3314b0, c3314b0.b, c3314b0.e, c3320e0);
    }

    public final void a(com.android.tools.r8.graph.I0 i0) {
        C2924wC c2924wCF0 = i0.F0();
        GC gcA = NC.a(c2924wCF0.b.iterator(), c2924wCF0.c);
        while (gcA.b.hasNext()) {
            com.android.tools.r8.graph.H0 h0 = (com.android.tools.r8.graph.H0) gcA.a(gcA.b.next());
            H2 h2A = this.b.a(h0);
            if (h2A != null && !h2A.b(h0.getReference().x0())) {
                this.c.put(h0.getReference(), h2A);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(I2 i2, I2 i3, final C3320e0 c3320e0) {
        C3320e0 c3320e1 = (C3320e0) this.e.computeIfAbsent(i3, new Function() { // from class: t5g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Z.a(c3320e0, (I2) obj);
            }
        });
        com.android.tools.r8.graph.E0 e0D = this.a.d(i2);
        if (e0D != null) {
            C0412Cl c0412Cl = AbstractC0464El.d;
            C0438Dl c0438Dl = new C0438Dl();
            Iterable<com.android.tools.r8.graph.H0> iterableA = this.a.M().u1.w.a(e0D.F0());
            for (com.android.tools.r8.graph.H0 h0 : iterableA) {
                H2 h2A = this.b.a(h0);
                if (h2A != null) {
                    c3320e1.a(h0, h2A);
                } else if (this.a.M().j instanceof ClassFileConsumer) {
                    C0231j1 c0231j1E = h0.e();
                    c0231j1E.O0();
                    if (c0231j1E.g.p() && c0231j1E.g.K()) {
                        c0438Dl.add(h0);
                    }
                }
            }
            Map mapA = a(e0D, c0438Dl);
            if (mapA.isEmpty()) {
                return;
            }
            for (com.android.tools.r8.graph.H0 h1 : iterableA) {
                if (((Set) mapA.getOrDefault(h1.getReference().x0(), Collections.EMPTY_SET)).contains(Integer.valueOf(h1.getReference().C0().p0()))) {
                    c3320e1.a(h1, h1.getReference().x0());
                }
            }
        }
    }

    public final /* synthetic */ void a(Sm0 sm0, I2 i2) {
        com.android.tools.r8.graph.E0 e0D = this.a.d(i2);
        if (e0D != null) {
            sm0.b(e0D);
        }
    }

    public final C3320e0 a(I2 i2) {
        C3320e0 c3320e0 = (C3320e0) this.e.get(i2);
        if (c3320e0 != null) {
            return c3320e0;
        }
        if (this.a.d(i2) == null) {
            return (C3320e0) this.e.get(this.a.a().a2);
        }
        boolean z = j;
        if (!z && !this.g.containsKey(i2)) {
            x1f.a();
            return null;
        }
        I2 i3 = (I2) this.g.get(i2);
        C3320e0 c3320e1 = (C3320e0) this.e.get(i3);
        if (z || c3320e1 != null) {
            return c3320e1;
        }
        throw new AssertionError("Could not find reservation state for frontier type " + i3.toString());
    }
}
