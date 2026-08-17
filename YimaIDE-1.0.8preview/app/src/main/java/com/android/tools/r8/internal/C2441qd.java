package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C2270od;
import com.android.tools.r8.internal.C2427qS;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.C2512rS;
import defpackage.vbg;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2441qd extends AbstractC1120b40 {
    public static final /* synthetic */ boolean i = true;
    public final IA e;
    public final C0333y f;
    public final C2512rS g;
    public final com.android.tools.r8.graph.I2 h;

    public C2441qd(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, IA ia, C0333y c0333y, C2512rS c2512rS) {
        super(c2427qS);
        boolean z = i;
        if (!z && (c0333y == null ? ia == null || !ia.a.isEmpty() : !c0333y.o())) {
            x1f.a();
            throw null;
        }
        if (!z && !i2.M0()) {
            x1f.a();
            throw null;
        }
        this.h = i2;
        this.f = c0333y;
        this.e = ia;
        this.g = c2512rS;
    }

    /* JADX WARN: Code duplicated, block: B:122:0x012d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:129:0x006b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x0135 A[LOOP:1: B:84:0x0133->B:85:0x0135, LOOP_END] */
    public static IA a(C0333y<? extends C0229j> c0333y, IA ia, IA ia2) {
        com.android.tools.r8.graph.E0 e0D;
        int i2;
        if (ia.a.isEmpty() || ia2.a.isEmpty()) {
            return IA.b;
        }
        synchronized (c0333y.a().v) {
            try {
                VI vi = c0333y.a().v;
                vi.getClass();
                T40 t40 = T40.i;
                IA ia3 = (IA) ((Map) vi.getOrDefault(ia, t40)).get(ia2);
                if (ia3 != null) {
                    return ia3;
                }
                VI vi2 = c0333y.a().v;
                vi2.getClass();
                IA ia4 = (IA) ((Map) vi2.getOrDefault(ia2, t40)).get(ia);
                if (ia4 != null) {
                    return ia4;
                }
                IdentityHashMap identityHashMap = new IdentityHashMap();
                final ArrayDeque arrayDeque = new ArrayDeque();
                ia.a(new BiConsumer() { // from class: p3i
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        C2441qd.a(arrayDeque, (I2) obj, (Boolean) obj2);
                    }
                });
                ia2.a(new BiConsumer() { // from class: q3i
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        C2441qd.b(arrayDeque, (I2) obj, (Boolean) obj2);
                    }
                });
                while (!arrayDeque.isEmpty()) {
                    C2356pd c2356pd = (C2356pd) arrayDeque.poll();
                    com.android.tools.r8.graph.I2 i3 = c2356pd.a;
                    C2270od c2270od = c2356pd.b;
                    C2270od c2270od2 = (C2270od) identityHashMap.computeIfAbsent(i3, new Function() { // from class: r3i
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return C2441qd.b((I2) obj);
                        }
                    });
                    c2270od2.getClass();
                    boolean z = C2270od.g;
                    if (!z && !c2270od.a.c() && !c2270od.b.c()) {
                        x1f.a();
                        return null;
                    }
                    if (!z && c2270od.a()) {
                        x1f.a();
                        return null;
                    }
                    if (c2270od.a.c()) {
                        AbstractC2173nV abstractC2173nV = c2270od2.a;
                        AbstractC2173nV abstractC2173nV2 = c2270od.a;
                        if (!z && !abstractC2173nV.c() && !abstractC2173nV2.c()) {
                            x1f.a();
                            return null;
                        }
                        c2270od2.a = (abstractC2173nV.d() || abstractC2173nV2.d()) ? AbstractC2173nV.a : AbstractC2173nV.c;
                        if (c2270od2.b.a() && c2270od2.a != abstractC2173nV) {
                            e0D = c0333y.d(i3);
                            if (e0D != null) {
                                for (com.android.tools.r8.graph.I2 i4 : e0D.h.b) {
                                    arrayDeque.add(new C2356pd(i4, c2270od));
                                }
                            }
                        }
                    } else {
                        AbstractC2173nV abstractC2173nV3 = c2270od2.b;
                        AbstractC2173nV abstractC2173nV4 = c2270od.b;
                        if (!z && !abstractC2173nV3.c() && !abstractC2173nV4.c()) {
                            x1f.a();
                            return null;
                        }
                        c2270od2.b = (abstractC2173nV3.d() || abstractC2173nV4.d()) ? AbstractC2173nV.a : AbstractC2173nV.c;
                        if (c2270od2.a.a() && c2270od2.b != abstractC2173nV3) {
                            e0D = c0333y.d(i3);
                            if (e0D != null) {
                                while (i2 < r4) {
                                    arrayDeque.add(new C2356pd(i4, c2270od));
                                }
                            }
                        }
                    }
                }
                final ArrayList<C1405eW> arrayList = new ArrayList(identityHashMap.size());
                identityHashMap.forEach(new BiConsumer() { // from class: s3i
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        C2441qd.a(arrayList, (I2) obj, (C2270od) obj2);
                    }
                });
                IA.a aVarA = IA.a();
                for (C1405eW c1405eW : arrayList) {
                    Iterator it = arrayList.iterator();
                    do {
                        if (!it.hasNext()) {
                            aVarA.a((com.android.tools.r8.graph.I2) c1405eW.a(), ((Boolean) c1405eW.b()).booleanValue());
                            break;
                        }
                    } while (!((C0229j) c0333y.g()).b((com.android.tools.r8.graph.I2) ((C1405eW) it.next()).a(), (com.android.tools.r8.graph.I2) c1405eW.a()));
                }
                IA iaA = aVarA.a();
                if (ia.equals(ia2)) {
                    return iaA;
                }
                synchronized (c0333y.a().v) {
                    c0333y.a().v.a(ia, ia2, iaA);
                }
                return iaA;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static C2441qd b(final com.android.tools.r8.graph.I2 i2, final C2427qS c2427qS, final C0333y<? extends C0229j> c0333y) {
        boolean z = i;
        if (!z && c0333y == null) {
            x1f.a();
            return null;
        }
        if (z || c0333y.o()) {
            return (C2441qd) C2512rS.a(c2427qS, new Function() { // from class: o3i
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2441qd.a(i2, c2427qS, c0333y, (C2512rS) obj);
                }
            });
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public final AbstractC2624sj0 P() {
        C2427qS c2427qS = this.b;
        C2427qS c2427qSB = C2427qS.b();
        c2427qS.getClass();
        C2427qS c2427qS2 = C2427qS.c;
        if (c2427qS == c2427qS2) {
            c2427qS = c2427qSB;
        } else if (c2427qSB != c2427qS2 && c2427qS != c2427qSB) {
            c2427qS = C2427qS.d;
        }
        return a(c2427qS);
    }

    public com.android.tools.r8.graph.I2 Q() {
        return this.h;
    }

    public IA R() {
        IA ia = this.e;
        if (ia != null) {
            return ia;
        }
        boolean z = i;
        if (!z && this.f == null) {
            x1f.a();
            return null;
        }
        if (!z && !this.f.o()) {
            x1f.a();
            return null;
        }
        return this.f.a().a(this.f, this.h);
    }

    public final C2441qd c(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, C0333y c0333y) {
        boolean z = i;
        if (!z && c0333y.o()) {
            x1f.a();
            return null;
        }
        if (!z && this.e == null) {
            x1f.a();
            return null;
        }
        if (z || this.e.a.isEmpty()) {
            return a(Q() == i2 ? Q() : c0333y.a().a2, this.b.a(c2427qS));
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C2441qd b(C2427qS c2427qS) {
        return a(this.b.a(c2427qS));
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40, com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2441qd)) {
            return false;
        }
        C2441qd c2441qd = (C2441qd) obj;
        if (this.b == c2441qd.b && this.h.equals(c2441qd.h)) {
            return R().equals(c2441qd.R());
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40, com.android.tools.r8.internal.AbstractC2624sj0
    public final int hashCode() {
        return Objects.hash(this.b, this.h);
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(" ");
        sb.append(this.h);
        sb.append(" {");
        ArrayList arrayListB = R().b();
        arrayListB.sort(Comparator.comparing(new Function() { // from class: j3i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (I2) ((C1405eW) obj).a();
            }
        }));
        sb.append((String) arrayListB.stream().map(new Function() { // from class: k3i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2441qd.a((C1405eW) obj);
            }
        }).collect(Collectors.joining(", ")));
        sb.append("}");
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean w() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final C2441qd b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public final com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1) {
        com.android.tools.r8.graph.I2 i2C;
        return (this.h != b1.a2 || (i2C = R().c()) == null) ? this.h : i2C;
    }

    public static C2270od b(com.android.tools.r8.graph.I2 i2) {
        AbstractC2173nV abstractC2173nV = AbstractC2173nV.b;
        return new C2270od(abstractC2173nV, abstractC2173nV);
    }

    public static void b(Queue queue, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        queue.add(new C2356pd(i2, bool.booleanValue() ? C2270od.e : C2270od.f));
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final C2441qd a(C2427qS c2427qS) {
        return c2427qS.equals(this.b) ? this : (C2441qd) this.g.a(c2427qS, new BiFunction() { // from class: u3i
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.b.a((C2427qS) obj, (C2512rS) obj2);
            }
        });
    }

    public static C2441qd a(final com.android.tools.r8.graph.I2 i2, final C2427qS c2427qS, final C0333y<? extends C0229j> c0333y, final IA ia) {
        boolean z = i;
        if (!z && c0333y == null) {
            x1f.a();
            return null;
        }
        if (!z && !c0333y.o()) {
            x1f.a();
            return null;
        }
        if (!z && ia == null) {
            x1f.a();
            return null;
        }
        return (C2441qd) C2512rS.a(c2427qS, new Function() { // from class: t3i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2441qd.a(i2, c2427qS, ia, c0333y, (C2512rS) obj);
            }
        });
    }

    public static /* synthetic */ C2441qd a(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, IA ia, C0333y c0333y, C2512rS c2512rS) {
        return new C2441qd(i2, c2427qS, ia, c0333y, c2512rS);
    }

    public static /* synthetic */ C2441qd a(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, C0333y c0333y, C2512rS c2512rS) {
        return new C2441qd(i2, c2427qS, null, c0333y, c2512rS);
    }

    public static C2441qd a(final com.android.tools.r8.graph.I2 i2, final C2427qS c2427qS) {
        return (C2441qd) C2512rS.a(c2427qS, new Function() { // from class: i3i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2441qd.a(i2, c2427qS, (C2512rS) obj);
            }
        });
    }

    public static C2441qd a(com.android.tools.r8.graph.I2 i2, C2427qS c2427qS, C2512rS c2512rS) {
        return new C2441qd(i2, c2427qS, IA.b, null, c2512rS);
    }

    public final C2441qd a(C2427qS c2427qS, C2512rS c2512rS) {
        if (i || this.b != c2427qS) {
            return new C2441qd(this.h, c2427qS, this.e, this.f, c2512rS);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean a(final C0333y c0333y) {
        return ((C0229j) c0333y.g()).i(Q()) || R().a(new BiPredicate() { // from class: l3i
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return ((C0229j) c0333y.g()).i((I2) obj);
            }
        });
    }

    public static /* synthetic */ String a(C1405eW c1405eW) {
        if (((Boolean) c1405eW.b()).booleanValue()) {
            return ((com.android.tools.r8.graph.I2) c1405eW.a()).toString();
        }
        return "maybe(" + c1405eW.a() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final AbstractC2624sj0 a(C0333y c0333y, final Function function, final Set set) {
        final C2441qd c2441qd;
        boolean z = i;
        if (!z && this.f == null) {
            x1f.a();
            return null;
        }
        if (!z && !this.f.o()) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) function.apply(this.h);
        if (i2.T0()) {
            if (AbstractC2005lY.b || i2.T0()) {
                return AbstractC2005lY.a((char) i2.f.f[0], false);
            }
            x1f.a();
            return null;
        }
        IA ia = this.e;
        if (ia != null && !ia.a.isEmpty()) {
            final E6 e6 = new E6();
            final C1975l7 c1975l7 = new C1975l7();
            c2441qd = this;
            R().a(new BiConsumer() { // from class: m3i
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(set, function, e6, c1975l7, (I2) obj, (Boolean) obj2);
                }
            });
            if (e6.a()) {
                if (c1975l7.b()) {
                    if (!z && ((com.android.tools.r8.graph.E0) c1975l7.a()).isInterface()) {
                        x1f.a();
                        return null;
                    }
                    if (z || i2 == c2441qd.f.a().a2) {
                        return b(((com.android.tools.r8.graph.E0) c1975l7.a()).e, c2441qd.b, (C0333y<? extends C0229j>) c2441qd.f);
                    }
                    x1f.a();
                    return null;
                }
                final IA.a aVarA = IA.a();
                c2441qd.e.a(new BiConsumer() { // from class: n3i
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        C2441qd.a(function, aVarA, (I2) obj, (Boolean) obj2);
                    }
                });
                return a(i2, c2441qd.b, (C0333y<? extends C0229j>) c2441qd.f, aVarA.a());
            }
            if (i2 != c2441qd.h) {
                return a(i2, c2441qd.b, (C0333y<? extends C0229j>) c2441qd.f, c2441qd.R());
            }
        } else {
            c2441qd = this;
            if (i2 != c2441qd.h) {
                return b(i2, c2441qd.b, (C0333y<? extends C0229j>) c2441qd.f);
            }
        }
        return c2441qd;
    }

    public final /* synthetic */ void a(Set set, Function function, E6 e6, C1975l7 c1975l7, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        com.android.tools.r8.graph.I2 i3;
        if (set.contains(i2) || i2 == (i3 = (com.android.tools.r8.graph.I2) function.apply(i2))) {
            return;
        }
        e6.f();
        com.android.tools.r8.graph.E0 e0D = this.f.d(i3);
        if (e0D.isInterface()) {
            return;
        }
        if (c1975l7.b() && e0D != c1975l7.a()) {
            vbg.a("More than one interface has changed to a class: ", c1975l7.a(), " and ", e0D);
        } else {
            c1975l7.a(e0D);
        }
    }

    public static /* synthetic */ void a(Function function, IA.a aVar, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        com.android.tools.r8.graph.I2 i3 = (com.android.tools.r8.graph.I2) function.apply(i2);
        if (i || i2 == i3 || bool.booleanValue()) {
            aVar.a(i3, bool.booleanValue());
        } else {
            x01.a("Rewritten implies program types thus known.");
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1120b40
    public AbstractC1120b40 a(AbstractC1120b40 abstractC1120b40, C0333y<?> c0333y) {
        if (abstractC1120b40.r()) {
            Q3 q3A = abstractC1120b40.a();
            com.android.tools.r8.graph.B1 b1A = c0333y.a();
            if (c0333y.o()) {
                return a(c0333y.a().a2, IA.a().a(b1A.I5).a(b1A.G5).a(), q3A.b);
            }
            return c(b1A.a2, q3A.b, c0333y);
        }
        if (abstractC1120b40.w()) {
            C2441qd c2441qdB = abstractC1120b40.b();
            if (c0333y.o()) {
                boolean z = i;
                if (!z && this.f == null) {
                    x1f.a();
                    return null;
                }
                if (z || this.f.o()) {
                    return a(a((C0229j) this.f.g(), Q(), c2441qdB.Q()), c2441qdB.R(), c2441qdB.b);
                }
                x1f.a();
                return null;
            }
            return c(c2441qdB.Q(), c2441qdB.b, c0333y);
        }
        if (!i && !(abstractC1120b40 instanceof C1034a40)) {
            x1f.a();
            return null;
        }
        return b(abstractC1120b40.b);
    }

    public final C2441qd a(com.android.tools.r8.graph.I2 i2, IA ia, C2427qS c2427qS) {
        boolean z = i;
        if (!z && this.f == null) {
            x1f.a();
            return null;
        }
        if (!z && !this.f.o()) {
            x1f.a();
            return null;
        }
        IA iaR = R();
        if (!iaR.equals(ia)) {
            iaR = a((C0333y<? extends C0229j>) this.f, iaR, ia);
        }
        IA iaA = this.f.a().a(this.f, i2);
        C2427qS c2427qSA = this.b.a(c2427qS);
        boolean zEquals = iaR.equals(iaA);
        C0333y c0333y = this.f;
        if (zEquals) {
            return b(i2, c2427qSA, (C0333y<? extends C0229j>) c0333y);
        }
        return a(i2, c2427qSA, (C0333y<? extends C0229j>) c0333y, iaR);
    }

    public static com.android.tools.r8.graph.I2 a(C0229j c0229j, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        if (i2 == i3) {
            return i2;
        }
        com.android.tools.r8.graph.I2 i4 = c0229j.a().a2;
        if (i2 != i4 && i3 != i4) {
            ArrayList arrayList = new ArrayList(10);
            while (i2 != i3) {
                arrayList.add(i2);
                com.android.tools.r8.graph.E0 e0D = c0229j.d(i2);
                if (e0D == null || (i2 = e0D.g) == null || i2 == i4) {
                    int size = arrayList.size();
                    Collection collectionA = arrayList;
                    if (size > 20) {
                        collectionA = C1755ib0.a(arrayList);
                    }
                    while (!collectionA.contains(i3)) {
                        com.android.tools.r8.graph.E0 e0D2 = c0229j.d(i3);
                        if (e0D2 == null || (i3 = e0D2.g) == null || i3 == i4) {
                        }
                    }
                    return i3;
                }
            }
            return i2;
        }
        return i4;
    }

    public static void a(Queue queue, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        queue.add(new C2356pd(i2, bool.booleanValue() ? C2270od.c : C2270od.d));
    }

    public static void a(List list, com.android.tools.r8.graph.I2 i2, C2270od c2270od) {
        if (c2270od.a()) {
            if (C2270od.g || c2270od.a()) {
                list.add(new C1405eW(i2, Boolean.valueOf(c2270od.a.d() && c2270od.b.d())));
            } else {
                x1f.a();
            }
        }
    }
}
