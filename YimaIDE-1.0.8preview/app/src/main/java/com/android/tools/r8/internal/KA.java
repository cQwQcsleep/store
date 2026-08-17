package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.errors.InterfaceDesugarMissingTypeDiagnostic;
import com.android.tools.r8.graph.AbstractC0201f;
import com.android.tools.r8.graph.AbstractC0223i0;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0171a4;
import com.android.tools.r8.graph.C0195e0;
import com.android.tools.r8.graph.C0196e1;
import com.android.tools.r8.graph.C0198e3;
import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0304t5;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.C0310u4;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0202f0;
import com.android.tools.r8.graph.W5;
import com.android.tools.r8.internal.KA;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.synthesis.C3502k;
import com.android.tools.r8.synthesis.C3506o;
import com.android.tools.r8.synthesis.N;
import com.android.tools.r8.synthesis.S;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KA {
    public static final C1159bb c = C1159bb.h;
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final Predicate b;

    public KA(C0333y c0333y) {
        this.a = c0333y;
        this.b = a(c0333y);
    }

    public final void a(com.android.tools.r8.graph.B5 b5, C0231j1 c0231j1, com.android.tools.r8.synthesis.N n) {
        com.android.tools.r8.graph.F4 accessFlags = b5.getAccessFlags();
        com.android.tools.r8.graph.F4 f4 = new com.android.tools.r8.graph.F4(accessFlags.b, accessFlags.c);
        f4.c |= 8;
        n.h = f4;
        n.i = com.android.tools.r8.graph.B3.g.d();
        n.p = false;
        C0306u0 c0306u0N0 = c0231j1.n0();
        com.android.tools.r8.graph.B1 b1A = this.a.a();
        C0285r0[] c0285r0Arr = null;
        int i = 0;
        while (true) {
            C0285r0[] c0285r0Arr2 = c0306u0N0.d;
            if (i >= c0285r0Arr2.length) {
                if (c0285r0Arr != null) {
                    boolean z = R3.a;
                    c0306u0N0 = c0285r0Arr.length == 0 ? C0306u0.o0() : new C0306u0(c0285r0Arr);
                }
                n.j = c0306u0N0;
                C0304t5 c0304t5B1 = c0231j1.b1();
                if (!c0304t5B1.isEmpty()) {
                    C0306u0[] c0306u0Arr = new C0306u0[c0304t5B1.size() + 1];
                    System.arraycopy(c0304t5B1.b, 0, c0306u0Arr, 1, c0304t5B1.size());
                    c0306u0Arr[0] = C0306u0.o0();
                    c0304t5B1 = new C0304t5(c0306u0Arr, 0);
                }
                n.k = c0304t5B1;
                n.g = new com.android.tools.r8.synthesis.M() { // from class: v48
                    @Override // com.android.tools.r8.synthesis.M
                    public final AbstractC0223i0 a(C0322w2 c0322w2) {
                        return C0171a4.e;
                    }
                };
                return;
            }
            C0285r0 c0285r0 = c0285r0Arr2[i];
            C0196e1 c0196e1 = c0285r0.c;
            if (c0196e1.b == b1A.h5) {
                boolean z2 = C0306u0.h;
                if (!z2 && c0285r0.b != 2) {
                    x1f.a();
                    return;
                }
                if (!z2 && c0196e1.c.length != 2) {
                    x1f.a();
                    return;
                }
                if (!z2 && !c0196e1.c[0].b.toString().equals("names")) {
                    x1f.a();
                    return;
                }
                if (!z2 && !c0285r0.c.c[1].b.toString().equals("accessFlags")) {
                    x1f.a();
                    return;
                }
                com.android.tools.r8.graph.O2.a aVarQ0 = c0285r0.c.c[0].c.q0();
                com.android.tools.r8.graph.O2.a aVarQ1 = c0285r0.c.c[1].c.q0();
                if (!z2 && (aVarQ0 == null || aVarQ1 == null)) {
                    x1f.a();
                    return;
                }
                if (!z2 && aVarQ0.d1().length != aVarQ1.d1().length) {
                    x1f.a();
                    return;
                }
                if (c0285r0Arr == null) {
                    C0285r0[] c0285r0Arr3 = c0306u0N0.d;
                    C0285r0[] c0285r0Arr4 = new C0285r0[c0285r0Arr3.length];
                    System.arraycopy(c0285r0Arr3, 0, c0285r0Arr4, 0, i);
                    c0285r0Arr = c0285r0Arr4;
                }
                com.android.tools.r8.graph.O2[] o2Arr = new com.android.tools.r8.graph.O2[aVarQ0.d1().length + 1];
                o2Arr[0] = new com.android.tools.r8.graph.O2.j(b1A.c("_this"));
                System.arraycopy(aVarQ0.d1(), 0, o2Arr, 1, aVarQ0.d1().length);
                com.android.tools.r8.graph.O2[] o2Arr2 = new com.android.tools.r8.graph.O2[aVarQ1.d1().length + 1];
                o2Arr2[0] = com.android.tools.r8.graph.O2.g.j(0);
                System.arraycopy(aVarQ1.d1(), 0, o2Arr2, 1, aVarQ1.d1().length);
                c0285r0Arr[i] = C0285r0.a(o2Arr, o2Arr2, b1A);
            } else if (c0285r0Arr != null) {
                c0285r0Arr[i] = c0285r0;
            }
            i++;
        }
    }

    public final C1606gn b(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.H0 h0) {
        if (h0 == null) {
            return null;
        }
        boolean z = d;
        if (!z && e0 == null) {
            x1f.a();
            return null;
        }
        if (!(h0 instanceof C0310u4) && !d(h0.s()) && !this.a.M().K1.c.i().containsKey(h0.getReference())) {
            return null;
        }
        if (!z && !h0.s().M0()) {
            x1f.a();
            return null;
        }
        if (!h0.a().isInterface()) {
            h0 = this.a.h().b(e0, h0.getReference());
        }
        if (h0 == null) {
            return null;
        }
        return this.a.M().K1.a(h0.getReference());
    }

    public final com.android.tools.r8.graph.B5 c(final com.android.tools.r8.graph.B5 b5, final MA ma) {
        C0322w2 c0322w2A = a(b5.getReference(), "$private$", this.a.a());
        final C0231j1 c0231j1E = b5.e();
        return VA.a(b5.a(), c0322w2A.x0(), c0322w2A.C0(), this.a, new Consumer() { // from class: j48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.a(c0231j1E, (N) obj);
            }
        }, new Consumer() { // from class: k48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ma.h(b5, (B5) obj);
            }
        });
    }

    public final com.android.tools.r8.graph.B5 d(final com.android.tools.r8.graph.B5 b5, final MA ma) {
        if (!d && b5.e().m1()) {
            x1f.a();
            return null;
        }
        if (b5.a().f1()) {
            a(b5.a(), ma);
        }
        C0322w2 reference = b5.getReference();
        com.android.tools.r8.graph.B1 b1A = this.a.a();
        C0322w2 c0322w2A = reference.a(a(b1A, reference.w0()), b1A);
        final C0231j1 c0231j1E = b5.e();
        return VA.a(b5.a(), c0322w2A.x0(), c0322w2A.C0(), this.a, new Consumer() { // from class: e48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.b(c0231j1E, (N) obj);
            }
        }, new Consumer() { // from class: f48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ma.k(b5, (B5) obj);
            }
        });
    }

    public final boolean e(com.android.tools.r8.graph.I2 i2) {
        return this.b.test(i2);
    }

    public static boolean c(com.android.tools.r8.graph.I2 i2) {
        return i2.f.toString().endsWith("$-CC;");
    }

    public static boolean c(com.android.tools.r8.graph.D2 d2) {
        return d2.f1() && d2.V().a(new Predicate() { // from class: b48
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return KA.b((C0231j1) obj);
            }
        });
    }

    public final boolean d(com.android.tools.r8.graph.I2 i2) {
        return this.a.M().K1.c.g().containsKey(i2);
    }

    public static /* synthetic */ void b(C3506o c3506o) {
    }

    public static void b(com.android.tools.r8.synthesis.N n) {
        n.h = com.android.tools.r8.graph.F4.b(4105, false);
        n.g = new com.android.tools.r8.synthesis.M() { // from class: t38
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return W5.e;
            }
        };
    }

    public final com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.I2 i2) {
        C1777in c1777in = this.a.M().K1.c.g().get(i2);
        if (c1777in == null) {
            return null;
        }
        return c1777in.a;
    }

    public static com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.I2 i2) {
        if (!d && !i2.f.toString().endsWith("$-CC;")) {
            x1f.a();
            return null;
        }
        return b1.e(AbstractC0005a.a(5, 0, i2.f.toString()) + ";");
    }

    public static /* synthetic */ void b(com.android.tools.r8.graph.I0 i0) {
    }

    public static void b(C0231j1 c0231j1, com.android.tools.r8.synthesis.N n) {
        com.android.tools.r8.graph.F4 accessFlags = c0231j1.getAccessFlags();
        com.android.tools.r8.graph.F4 f4 = new com.android.tools.r8.graph.F4(accessFlags.b, accessFlags.c);
        f4.s();
        n.h = f4;
        n.i = c0231j1.W0();
        n.j = c0231j1.n0();
        n.k = c0231j1.b1();
        n.p = false;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: w48
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C0171a4.e;
            }
        };
    }

    public final com.android.tools.r8.graph.B5 b(com.android.tools.r8.graph.B5 b5, MA ma) {
        C0231j1 c0231j1E = b5.e();
        boolean z = d;
        if (!z && !b5.a().isInterface()) {
            x1f.a();
            return null;
        }
        if (!z && !c0231j1E.t1()) {
            x1f.a();
            return null;
        }
        if (!z && c0231j1E.U0() == null) {
            x1f.a();
            return null;
        }
        if (!z && C0171a4.a(c0231j1E.U0())) {
            x1f.a();
            return null;
        }
        if (c0231j1E.z0()) {
            return d(b5, ma);
        }
        if (c0231j1E.J0()) {
            return c(b5, ma);
        }
        return a(b5, ma);
    }

    public static /* synthetic */ boolean b(C0231j1 c0231j1) {
        return c0231j1.z0() && !c0231j1.m1();
    }

    public static C0210g1 b(com.android.tools.r8.graph.D2 d2) {
        Iterator it = C2753uC.a(d2.D1(), new Predicate() { // from class: m48
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return KA.a((C0210g1) obj);
            }
        }).iterator();
        if (it.hasNext()) {
            return (C0210g1) it.next();
        }
        return null;
    }

    public static /* synthetic */ void a(C3506o c3506o) {
    }

    public static String a(String str) {
        return AbstractC0005a.a(1, 0, str) + "$-CC;";
    }

    public static int a(C2752uB c2752uB) {
        if (c2752uB.a0()) {
            return 1;
        }
        return c2752uB.K1.c.g().isEmpty() ? 3 : 2;
    }

    public static void a(com.android.tools.r8.synthesis.N n) {
        n.h = com.android.tools.r8.graph.F4.b(4105, false);
        n.g = new com.android.tools.r8.synthesis.M() { // from class: h48
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return W5.e;
            }
        };
    }

    public final boolean a(com.android.tools.r8.graph.E0 e0) {
        if (!d && !e0.b0() && !this.a.M().K1.a) {
            x1f.a();
            return false;
        }
        if (d(e0.e) || this.a.M().K1.l().contains(e0.e)) {
            return true;
        }
        C0333y c0333y = this.a;
        return c0333y.z.a(c0333y, e0.e);
    }

    public static boolean a(C0231j1 c0231j1) {
        boolean z = d;
        if (!z && c0231j1.g.L()) {
            x1f.a();
            return false;
        }
        if (!z && c0231j1.g.n()) {
            x1f.a();
            return false;
        }
        if (c0231j1.g.J()) {
            return false;
        }
        if (!c0231j1.g.M()) {
            if (c0231j1.g.m()) {
                return true;
            }
            throw new C1345dk0("Non public default interface methods are not yet supported.");
        }
        throw new C1345dk0("Native default interface methods are not yet supported.");
    }

    public final void a(C2964wi c2964wi, com.android.tools.r8.synthesis.I i) {
        com.android.tools.r8.synthesis.S.b bVarA = i.a(this.a.a.g().b);
        if (d) {
            return;
        }
        com.android.tools.r8.synthesis.S.b bVarA2 = c2964wi.a(this.a);
        bVarA2.getClass();
        if (com.android.tools.r8.utils.structural.k.a(bVarA2, bVarA)) {
            return;
        }
        x1f.a();
    }

    public final C0322w2 a(com.android.tools.r8.graph.I2 i2, C2964wi c2964wi) {
        if (!d) {
            a(c2964wi, new com.android.tools.r8.synthesis.I() { // from class: s38
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.k;
                }
            });
        }
        com.android.tools.r8.graph.B1 b1A = this.a.a();
        C0322w2 c0322w2 = c2964wi.a;
        b1A.getClass();
        return this.a.a().a(i2, c0322w2.C0().b(b1A, c0322w2.w0()), c2964wi.a.x0());
    }

    public static com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.I2 i2D;
        if (!d && !i2.M0()) {
            x1f.a();
            return null;
        }
        String strA = a(i2.f.toString());
        synchronized (b1) {
            i2D = b1.d(b1.c(strA));
            b1.a(i2D);
        }
        return i2D;
    }

    public final com.android.tools.r8.graph.I0 a(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.synthesis.J jG = this.a.a.g();
        com.android.tools.r8.synthesis.I i = new com.android.tools.r8.synthesis.I() { // from class: n48
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.g;
            }
        };
        C0333y c0333y = this.a;
        return jG.a(i.a(jG.b), new Consumer() { // from class: o48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C3506o) obj).d();
            }
        }, new Consumer() { // from class: p48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.a((I0) obj);
            }
        }, new C3502k(i2, i2, com.android.tools.r8.origin.c.a(), FeatureSplit.BASE), c0333y);
    }

    public final C2964wi a(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.H0 h0) {
        if (h0 == null) {
            return null;
        }
        C0322w2 c0322w2 = this.a.M().K1.c.i().get(h0.getReference());
        if (c0322w2 != null) {
            return new C2964wi(c0322w2);
        }
        C1606gn c1606gnB = b(e0, h0);
        if (c1606gnB == null) {
            return null;
        }
        return c1606gnB.c;
    }

    public final C0322w2 a(C2964wi c2964wi) {
        return a(c2964wi, NA.a);
    }

    public final C0322w2 a(C2964wi c2964wi, MA ma) {
        if (c2964wi.a(this.a) == null) {
            return c2964wi.a;
        }
        if (!d) {
            a(c2964wi, new com.android.tools.r8.synthesis.I() { // from class: g48
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.j;
                }
            });
        }
        return a(this.a.h().b(c2964wi.a, true).p(), ma).getReference();
    }

    public final com.android.tools.r8.graph.H0 a(C2964wi c2964wi, final InterfaceC1862jn interfaceC1862jn) {
        boolean z = d;
        if (!z) {
            a(c2964wi, new com.android.tools.r8.synthesis.I() { // from class: p38
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.k;
                }
            });
        }
        com.android.tools.r8.graph.H0 h0P = this.a.h().b(c2964wi.a, true).p();
        if (!z) {
            a(c2964wi, new com.android.tools.r8.synthesis.I() { // from class: a48
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.k;
                }
            });
        }
        h0P.getClass();
        if (h0P instanceof com.android.tools.r8.graph.B5) {
            if (!z && !this.a.M().K1.a) {
                x1f.a();
                return null;
            }
            com.android.tools.r8.graph.D2 d2A = this.a.a.g().a(new com.android.tools.r8.synthesis.I() { // from class: l48
                @Override // com.android.tools.r8.synthesis.I
                public final S.b a(S s) {
                    return s.k;
                }
            }, h0P.c0().a(), this.a);
            C0322w2 c0322w2A = a(d2A.e, c2964wi);
            if (z || d2A.f(c0322w2A) != null) {
                return d2A.f(c0322w2A);
            }
            x1f.a();
            return null;
        }
        C0322w2 c0322w2A2 = a(this.a.a().a2, c2964wi);
        com.android.tools.r8.synthesis.J jG = this.a.a.g();
        com.android.tools.r8.graph.H2 h2X0 = c0322w2A2.x0();
        com.android.tools.r8.graph.E2 e2C0 = c0322w2A2.C0();
        com.android.tools.r8.synthesis.I i = new com.android.tools.r8.synthesis.I() { // from class: q48
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.k;
            }
        };
        InterfaceC0202f0 interfaceC0202f0K = h0P.a().K();
        C0333y c0333y = this.a;
        Consumer consumer = new Consumer() { // from class: r48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.a((C3506o) obj);
            }
        };
        Objects.requireNonNull(interfaceC1862jn);
        Consumer consumer2 = new Consumer() { // from class: s48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                interfaceC1862jn.d((I0) obj);
            }
        };
        Consumer consumer3 = new Consumer() { // from class: t48
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.a((N) obj);
            }
        };
        jG.getClass();
        return com.android.tools.r8.synthesis.J.a(h2X0, e2C0, i.a(jG.b), c0333y, consumer3, jG.a(i.a(jG.b), consumer, consumer2, C3502k.a(interfaceC0202f0K), c0333y));
    }

    public static void a(MA ma, com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.B5 b5) {
        ma.l(d2.i(d2.O0()), b5);
    }

    public final com.android.tools.r8.graph.H0 a(com.android.tools.r8.graph.H0 h0) {
        return a(h0, NA.a);
    }

    public final com.android.tools.r8.graph.H0 a(com.android.tools.r8.graph.H0 h0, MA ma) {
        h0.getClass();
        if (h0 instanceof com.android.tools.r8.graph.B5) {
            return a(h0.c0(), ma);
        }
        return a(a(h0.getReference(), "$default$", this.a.a()), h0.a().K(), this.a);
    }

    public final com.android.tools.r8.graph.B5 a(final com.android.tools.r8.graph.B5 b5, final MA ma) {
        final C0231j1 c0231j1E = b5.e();
        C0322w2 c0322w2A = a(b5.getReference(), "$default$", this.a.a());
        return VA.a(b5.a(), c0322w2A.x0(), c0322w2A.C0(), this.a, new Consumer() { // from class: u38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(b5, c0231j1E, (N) obj);
            }
        }, new Consumer() { // from class: v38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ma.a(b5, (B5) obj);
            }
        });
    }

    public static /* synthetic */ void a(com.android.tools.r8.graph.I0 i0) {
    }

    public static void a(C0231j1 c0231j1, com.android.tools.r8.synthesis.N n) {
        com.android.tools.r8.graph.F4 accessFlags = c0231j1.getAccessFlags();
        com.android.tools.r8.graph.F4 f4 = new com.android.tools.r8.graph.F4(accessFlags.b, accessFlags.c);
        if (!d && !f4.i()) {
            x1f.a();
            return;
        }
        f4.s();
        f4.c |= 8;
        n.h = f4;
        n.i = c0231j1.W0();
        n.j = c0231j1.n0();
        n.p = false;
        n.k = c0231j1.b1();
        n.g = new com.android.tools.r8.synthesis.M() { // from class: u48
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return C0171a4.e;
            }
        };
    }

    public static C0322w2 a(C0322w2 c0322w2, String str, com.android.tools.r8.graph.B1 b1) {
        com.android.tools.r8.graph.I2[] i2Arr = c0322w2.i.f.b;
        com.android.tools.r8.graph.I2[] i2Arr2 = new com.android.tools.r8.graph.I2[i2Arr.length + 1];
        i2Arr2[0] = c0322w2.f;
        System.arraycopy(i2Arr, 0, i2Arr2, 1, i2Arr.length);
        return b1.a(a(b1, c0322w2.f), b1.a(c0322w2.i.e, i2Arr2), b1.c(str + c0322w2.g.toString()));
    }

    public final void a(final com.android.tools.r8.graph.D2 d2, final MA ma) {
        if (d || c(d2)) {
            VA.a(d2, this.a.a().d1, this.a.a().a(this.a.a().E1, new com.android.tools.r8.graph.I2[0]), this.a, new Consumer() { // from class: q38
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.b(d2, (N) obj);
                }
            }, new Consumer() { // from class: r38
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    KA.a(ma, d2, (B5) obj);
                }
            });
        } else {
            x1f.a();
        }
    }

    public static C0195e0 a(C0322w2 c0322w2, InterfaceC0202f0 interfaceC0202f0, C0333y c0333y) {
        com.android.tools.r8.synthesis.J jG = c0333y.a.g();
        com.android.tools.r8.graph.H2 h2X0 = c0322w2.x0();
        com.android.tools.r8.graph.E2 e2C0 = c0322w2.C0();
        com.android.tools.r8.synthesis.I i = new com.android.tools.r8.synthesis.I() { // from class: w38
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.j;
            }
        };
        Consumer consumer = new Consumer() { // from class: x38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.b((C3506o) obj);
            }
        };
        Consumer consumer2 = new Consumer() { // from class: y38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.b((I0) obj);
            }
        };
        Consumer consumer3 = new Consumer() { // from class: z38
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KA.b((N) obj);
            }
        };
        jG.getClass();
        return com.android.tools.r8.synthesis.J.a(h2X0, e2C0, i.a(jG.b), c0333y, consumer3, jG.a(i.a(jG.b), consumer, consumer2, C3502k.a(interfaceC0202f0), c0333y));
    }

    public static boolean a(C0210g1 c0210g1) {
        return (c0210g1.J0() || c0210g1.l.i()) ? false : true;
    }

    public final C0210g1 a(final com.android.tools.r8.graph.D2 d2) {
        com.android.tools.r8.graph.B1 b1A = this.a.a();
        C0245l1 c0245l1A = b1A.a(d2.getType(), b1A.B1, "$desugar$clinit", new Predicate() { // from class: d48
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return KA.a(d2, (C0245l1) obj);
            }
        });
        C0210g1[] c0210g1Arr = C0210g1.o;
        C0210g1.a aVarA = new C0210g1.a(true).a(c0245l1A);
        boolean z = C0205f3.f;
        C0198e3 c0198e3 = new C0198e3();
        if (!AbstractC0201f.b && !c0198e3.a.g()) {
            x1f.a();
            return null;
        }
        C0198e3 c0198e4 = (C0198e3) c0198e3.a();
        c0198e4.a.z();
        c0198e4.a.A();
        aVarA.c = (C0205f3) c0198e4.a;
        aVarA.f = com.android.tools.r8.graph.O2.g.d;
        return aVarA.c().a();
    }

    public static /* synthetic */ boolean a(com.android.tools.r8.graph.D2 d2, C0245l1 c0245l1) {
        return d2.a(c0245l1) == null;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void b(final com.android.tools.r8.graph.D2 d2, com.android.tools.r8.synthesis.N n) {
        boolean z = com.android.tools.r8.graph.F4.f;
        com.android.tools.r8.graph.E4 e4 = new com.android.tools.r8.graph.E4();
        ((com.android.tools.r8.graph.F4) e4.a).b(65536);
        if (!AbstractC0201f.b && !e4.a.g()) {
            x1f.a();
            return;
        }
        com.android.tools.r8.graph.E4 e5 = (com.android.tools.r8.graph.E4) e4.a();
        e5.a.z();
        n.h = (com.android.tools.r8.graph.F4) e5.a;
        C1159bb c1159bbK1 = d2.K1();
        C1159bb c1159bb = c;
        if (!c1159bbK1.e(c1159bb)) {
            c1159bbK1 = c1159bb;
        }
        n.f = c1159bbK1;
        n.g = new com.android.tools.r8.synthesis.M() { // from class: c48
            @Override // com.android.tools.r8.synthesis.M
            public final AbstractC0223i0 a(C0322w2 c0322w2) {
                return this.a.a(d2, c0322w2);
            }
        };
    }

    public final AbstractC0223i0 a(com.android.tools.r8.graph.D2 d2, C0322w2 c0322w2) {
        C0479Fa c0479Fa;
        if (this.a.j()) {
            com.android.tools.r8.graph.I2 i2 = c0322w2.f;
            AbstractC0551Hu abstractC0551HuA = AbstractC0551Hu.a(new C2834v9(d2.getType()), new C0479Fa(C0479Fa.a.c), new C3034xa());
            List list = Collections.EMPTY_LIST;
            return new com.android.tools.r8.graph.G(i2, 1, 0, abstractC0551HuA, list, list);
        }
        C0210g1 c0210g1B = b(d2);
        if (c0210g1B == null) {
            c0210g1B = a(d2);
            d2.a(c0210g1B);
        }
        boolean zX0 = c0210g1B.getType().X0();
        com.android.tools.r8.graph.I2 i3 = c0322w2.f;
        int i = zX0 ? 2 : 1;
        C0764Qa c0764Qa = new C0764Qa(c0210g1B.getReference(), c0210g1B.getReference());
        if (zX0) {
            c0479Fa = new C0479Fa(C0479Fa.a.d);
        } else {
            c0479Fa = new C0479Fa(C0479Fa.a.c);
        }
        AbstractC0551Hu abstractC0551HuA2 = AbstractC0551Hu.a(c0764Qa, c0479Fa, new C3034xa());
        List list2 = Collections.EMPTY_LIST;
        return new com.android.tools.r8.graph.G(i3, i, 0, abstractC0551HuA2, list2, list2);
    }

    public final Predicate a(final C0333y c0333y) {
        com.android.tools.r8.graph.B1 b1A = c0333y.a();
        final C2752uB c2752uBM = c0333y.M();
        final com.android.tools.r8.graph.H2 h2C = b1A.c("$-CC;");
        return new Predicate() { // from class: i48
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.a(c0333y, h2C, c2752uBM, (I2) obj);
            }
        };
    }

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.H2 h2, C2752uB c2752uB, com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.H2 h2Z0 = i2.z0();
        if (c0333y.z.a(c0333y, i2)) {
            return true;
        }
        h2Z0.getClass();
        return h2Z0.a(h2.f) || this.a.M().K1.c.b(i2) || c2752uB.K1.c.a(i2) || c0333y.f.a(i2);
    }

    public final void a(com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.E0 e1, com.android.tools.r8.graph.I2 i2) {
        if (this.b.test(i2)) {
            return;
        }
        C2752uB c2752uBM = this.a.M();
        if (c2752uBM.T1.add(i2)) {
            c2752uBM.i.warning(new InterfaceDesugarMissingTypeDiagnostic(e0.d, Position.UNKNOWN, Reference.classFromDescriptor(i2.Z0()), Reference.classFromDescriptor(e0.getType().Z0()), e0 == e1 ? null : Reference.classFromDescriptor(e1.getType().Z0())));
        }
    }
}
