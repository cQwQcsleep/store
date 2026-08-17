package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.AnnotationGraphNode;
import com.android.tools.r8.experimental.graphinfo.ClassGraphNode;
import com.android.tools.r8.experimental.graphinfo.FieldGraphNode;
import com.android.tools.r8.experimental.graphinfo.GraphConsumer;
import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.experimental.graphinfo.KeepRuleGraphNode;
import com.android.tools.r8.experimental.graphinfo.MethodGraphNode;
import com.android.tools.r8.graph.AbstractC0175b1;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC2878vi;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2807us;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.shaking.C3432n3;
import com.android.tools.r8.shaking.N0;
import defpackage.gk0;
import defpackage.hkh;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N0 {
    public static final /* synthetic */ boolean k = true;
    public final C0333y a;
    public final C2752uB b;
    public final GraphConsumer c;
    public final C3457t d;
    public final IdentityHashMap e = new IdentityHashMap();
    public final IdentityHashMap f = new IdentityHashMap();
    public final IdentityHashMap g = new IdentityHashMap();
    public final IdentityHashMap h = new IdentityHashMap();
    public final IdentityHashMap i = new IdentityHashMap();
    public final IdentityHashMap j = new IdentityHashMap();

    public N0(C0333y c0333y, GraphConsumer graphConsumer) {
        this.a = c0333y;
        this.b = c0333y.M();
        if (!c0333y.M().u1.K0) {
            this.d = null;
            this.c = graphConsumer;
        } else {
            C3457t c3457t = new C3457t(graphConsumer);
            this.d = c3457t;
            this.c = c3457t;
        }
    }

    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        if (!k && this.d == null) {
            x1f.a();
            return false;
        }
        ClassGraphNode classGraphNodeA = a(d2.e);
        Set setC = AbstractC2780ub0.c();
        ArrayDeque arrayDequeA = AbstractC2878vi.a(classGraphNodeA);
        while (!arrayDequeA.isEmpty()) {
            GraphNode graphNode = (GraphNode) arrayDequeA.pop();
            if ((graphNode instanceof KeepRuleGraphNode) && ((KeepRuleGraphNode) graphNode).getPreconditions().isEmpty()) {
                return true;
            }
            if (setC.add(graphNode)) {
                Map<GraphNode, Set<C2807us>> mapA = this.d.a(graphNode);
                boolean z = k;
                if (!z && mapA == null) {
                    s22.a("No sources set for ", graphNode);
                    return false;
                }
                if (!z && mapA.isEmpty()) {
                    s22.a("Empty sources set for ", graphNode);
                    return false;
                }
                arrayDequeA.addAll(mapA.keySet());
            }
        }
        if (k) {
            return false;
        }
        pe1.a("No rooted path to ", d2.e);
        return false;
    }

    public final ClassGraphNode b(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0C = this.a.g().c(i2);
        return new ClassGraphNode(e0C != null && e0C.y1(), Reference.classFromDescriptor(i2.Z0()));
    }

    public final C2807us.a a(KeepRuleGraphNode keepRuleGraphNode) {
        if (keepRuleGraphNode.getPreconditions().isEmpty()) {
            return C2807us.a.b;
        }
        Iterator<GraphNode> it = keepRuleGraphNode.getPreconditions().iterator();
        while (it.hasNext()) {
            a(it.next(), keepRuleGraphNode, C2807us.a.e);
        }
        return C2807us.a.d;
    }

    public final void a(B5 b5) {
        if (!k) {
            com.android.tools.r8.graph.D2 d2A = b5.a();
            d2A.getClass();
            if (d2A.a(com.android.tools.r8.graph.I2.h) != b5.e()) {
                x1f.a();
                return;
            }
        }
        if (this.c != null) {
            a(a(b5.s()), a(b5.getReference()), C2807us.a.c);
        }
    }

    public final M0 a(com.android.tools.r8.graph.D2 d2, InterfaceC0332x5 interfaceC0332x5) {
        M0 m0 = M0.a;
        if (interfaceC0332x5.a0()) {
            return a(d2, interfaceC0332x5.X());
        }
        if (interfaceC0332x5.h0()) {
            C0346z5 c0346z5O = interfaceC0332x5.O();
            if (this.c != null) {
                a(a(c0346z5O.getReference()), a(d2.e), C2807us.a.l);
                return m0;
            }
        } else {
            if (!k && !interfaceC0332x5.k()) {
                x1f.a();
                return null;
            }
            B5 b5C0 = interfaceC0332x5.c0();
            if (this.c != null) {
                a(a(b5C0.getReference()), a(d2.e), C2807us.a.l);
            }
        }
        return m0;
    }

    public final M0 a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.D2 d3) {
        if (this.c != null) {
            a(a(d3.e), a(d2.e), C2807us.a.l);
            return M0.a;
        }
        return M0.a;
    }

    public final M0 a(com.android.tools.r8.graph.D2 d2, B5 b5) {
        if (b5 != null) {
            if (!k && !b5.e().m1()) {
                x1f.a();
                return null;
            }
            if (this.c != null) {
                a(a(d2.e), a(b5.getReference()), C2807us.a.n);
                return M0.a;
            }
        } else if (!k && d2.f1()) {
            x1f.a();
            return null;
        }
        return M0.a;
    }

    public final void a(GraphNode graphNode, GraphNode graphNode2, C2807us.a aVar) {
        if (k || this.c != null) {
            this.c.acceptEdge(graphNode, graphNode2, a(aVar));
        } else {
            x1f.a();
        }
    }

    public final boolean a(D1 d1) {
        boolean z = k;
        if (!z && d1 == null) {
            x1f.a();
            return false;
        }
        if (d1 == M0.a) {
            return true;
        }
        if (z || d1.a(this) != null) {
            return !(this.c != null);
        }
        x1f.a();
        return false;
    }

    public final M0 a(com.android.tools.r8.graph.D2 d2, D1 d1) {
        if (a(d1)) {
            return M0.a;
        }
        a(a(d2.e), d1);
        return M0.a;
    }

    public final M0 a(C0231j1 c0231j1, D1 d1) {
        if (a(d1)) {
            return M0.a;
        }
        if (d1.a() == C2807us.a.p) {
            com.android.tools.r8.graph.E0 e0D = this.a.d(c0231j1.E0());
            if (e0D == null || e0D.y1()) {
                return M0.a;
            }
        }
        a(a(c0231j1.getReference()), d1);
        return M0.a;
    }

    public final M0 a(C0210g1 c0210g1, D1 d1) {
        if (a(d1)) {
            return M0.a;
        }
        a(a(c0210g1.getReference()), d1);
        return M0.a;
    }

    public final void a(GraphNode graphNode, D1 d1) {
        if (!k && a(d1)) {
            x1f.a();
            return;
        }
        GraphNode graphNodeA = d1.a(this);
        if (graphNodeA.isLibraryNode()) {
            return;
        }
        this.c.acceptEdge(graphNodeA, graphNode, a(d1.a()));
    }

    public final GraphNode a(com.android.tools.r8.graph.F2 f2) {
        f2.getClass();
        if (f2 instanceof com.android.tools.r8.graph.I2) {
            return a(f2.r0());
        }
        if (f2.u0()) {
            return a(f2.q0());
        }
        if (f2.s0()) {
            return a(f2.o0());
        }
        hkh.a();
        return null;
    }

    public final C2807us a(C2807us.a aVar) {
        return (C2807us) this.j.computeIfAbsent(aVar, new Function() { // from class: eaa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C2807us((C2807us.a) obj);
            }
        });
    }

    public final AnnotationGraphNode a(final C0285r0 c0285r0, final InterfaceC0332x5 interfaceC0332x5) {
        return (AnnotationGraphNode) this.e.computeIfAbsent(c0285r0, new Function() { // from class: jaa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(interfaceC0332x5, c0285r0, (C0285r0) obj);
            }
        });
    }

    public final /* synthetic */ AnnotationGraphNode a(InterfaceC0332x5 interfaceC0332x5, C0285r0 c0285r0, C0285r0 c0285r1) {
        return new AnnotationGraphNode((GraphNode) interfaceC0332x5.getReference().a(new Function() { // from class: y9a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((I2) obj);
            }
        }, new Function() { // from class: aaa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C0245l1) obj);
            }
        }, new Function() { // from class: caa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C0322w2) obj);
            }
        }), a(c0285r0.o0()));
    }

    public final ClassGraphNode a(com.android.tools.r8.graph.I2 i2) {
        return (ClassGraphNode) this.f.computeIfAbsent(i2, new Function() { // from class: laa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((I2) obj);
            }
        });
    }

    public final MethodGraphNode a(final C0322w2 c0322w2) {
        return (MethodGraphNode) this.g.computeIfAbsent(c0322w2, new Function() { // from class: gaa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c0322w2, (C0322w2) obj);
            }
        });
    }

    public final MethodGraphNode a(C0322w2 c0322w2, C0322w2 c0322w3) {
        com.android.tools.r8.graph.E0 e0C = this.a.g().c(c0322w2.f);
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        boolean z = false;
        for (com.android.tools.r8.graph.I2 i2 : c0322w3.i.f.b) {
            c0473EuG.a(Reference.typeFromDescriptor(i2.Z0()));
        }
        if (e0C != null && e0C.y1()) {
            z = true;
        }
        return new MethodGraphNode(z, Reference.method(Reference.classFromDescriptor(c0322w3.f.Z0()), c0322w3.g.toString(), c0473EuG.a(), c0322w3.i.e.W0() ? null : Reference.typeFromDescriptor(c0322w3.i.e.Z0())));
    }

    public final FieldGraphNode a(final C0245l1 c0245l1) {
        return (FieldGraphNode) this.h.computeIfAbsent(c0245l1, new Function() { // from class: kaa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c0245l1, (C0245l1) obj);
            }
        });
    }

    public final FieldGraphNode a(C0245l1 c0245l1, C0245l1 c0245l2) {
        com.android.tools.r8.graph.E0 e0C = this.a.g().c(c0245l1.f);
        return new FieldGraphNode(e0C != null && e0C.y1(), Reference.field(Reference.classFromDescriptor(c0245l2.f.Z0()), c0245l2.g.toString(), Reference.typeFromDescriptor(c0245l2.i.Z0())));
    }

    public final KeepRuleGraphNode a(AbstractC0175b1 abstractC0175b1, final C3432n3 c3432n3) {
        final Set setSingleton;
        if (c3432n3 instanceof C3427m3) {
            if (abstractC0175b1 != null) {
                setSingleton = Collections.singleton(a(abstractC0175b1.getReference()));
            } else {
                setSingleton = Collections.EMPTY_SET;
            }
            return (KeepRuleGraphNode) this.i.computeIfAbsent(c3432n3, new Function() { // from class: haa
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return N0.a(c3432n3, setSingleton, (C3432n3) obj);
                }
            });
        }
        if (c3432n3 instanceof C3412j3) {
            final C3412j3 c3412j3 = (C3412j3) c3432n3;
            if (k || c3412j3.H() != null) {
                return (KeepRuleGraphNode) this.i.computeIfAbsent(c3412j3, new Function() { // from class: iaa
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.b.a(c3412j3, (C3432n3) obj);
                    }
                });
            }
            x1f.a();
            return null;
        }
        gk0.a("Unexpected type of keep rule: ", c3432n3);
        return null;
    }

    public static /* synthetic */ KeepRuleGraphNode a(C3432n3 c3432n3, Set set, C3432n3 c3432n4) {
        return new KeepRuleGraphNode(c3432n3, set);
    }

    public final KeepRuleGraphNode a(C3412j3 c3412j3, C3432n3 c3432n3) {
        GraphNode graphNodeA = a(c3412j3.H().getReference());
        HashSet hashSet = new HashSet(1);
        hashSet.add(graphNodeA);
        return new KeepRuleGraphNode(c3412j3, hashSet);
    }
}
