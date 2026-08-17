package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0283q4;
import com.android.tools.r8.graph.C0313v0;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D0;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C2035lp;
import com.android.tools.r8.internal.UY;
import com.android.tools.r8.internal.XR;
import defpackage.gff;
import defpackage.hih;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ys, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3148ys {
    public static final /* synthetic */ boolean a = true;

    public static AbstractC3148ys g() {
        return C1783iu.b;
    }

    public abstract C2035lp a(C0245l1 c0245l1, AbstractC3148ys abstractC3148ys, InterfaceC2979ws interfaceC2979ws);

    public abstract C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, AbstractC3148ys abstractC3148ys);

    public abstract C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, AbstractC3148ys abstractC3148ys, InterfaceC3064xs interfaceC3064xs);

    public abstract String a(String str);

    public final void a(C0333y c0333y, C0283q4 c0283q4) {
        Collection<com.android.tools.r8.graph.D2> collectionE = c0333y.g().e();
        Set setC = AbstractC2780ub0.c();
        Set setC2 = AbstractC2780ub0.c();
        for (com.android.tools.r8.graph.D2 d2 : c0283q4.d()) {
            Iterator<C0210g1> it = d2.M0().iterator();
            while (it.hasNext()) {
                setC.add(it.next().getReference());
            }
            Iterator<C0231j1> it2 = d2.C1().iterator();
            while (it2.hasNext()) {
                setC2.add(it2.next().getReference());
            }
        }
        for (com.android.tools.r8.graph.D2 d3 : collectionE) {
            if (!c0333y.g().g().b(d3)) {
                for (C0210g1 c0210g1 : d3.M0()) {
                    if (!c0210g1.I0()) {
                        C0245l1 c0245l1A = a(g(), c0210g1.getReference());
                        if (!a && !setC.contains(c0245l1A)) {
                            hih.a("Unable to map field `", c0210g1.getReference().m0(), "` back to original program");
                            return;
                        }
                    }
                }
                for (C0231j1 c0231j1 : d3.C1()) {
                    if (!a && !c0231j1.I0() && !setC2.contains(c0231j1.getReference())) {
                        x1f.a();
                        return;
                    }
                }
            }
        }
    }

    public abstract boolean a(AbstractC3148ys abstractC3148ys);

    public final com.android.tools.r8.graph.F2 b(com.android.tools.r8.graph.F2 f2, final AbstractC3148ys abstractC3148ys) {
        return (com.android.tools.r8.graph.F2) f2.a(new Function() { // from class: qwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c(abstractC3148ys, (I2) obj);
            }
        }, new Function() { // from class: rwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.d(abstractC3148ys, (C0245l1) obj);
            }
        }, new Function() { // from class: swi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.d(abstractC3148ys, (C0322w2) obj);
            }
        });
    }

    public abstract Iterable b(com.android.tools.r8.graph.I2 i2);

    public abstract boolean b(AbstractC3148ys abstractC3148ys);

    public final C0322w2 c(C0322w2 c0322w2) {
        return d(g(), c0322w2);
    }

    public final com.android.tools.r8.graph.I2 d(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        return (com.android.tools.r8.graph.I2) a(i2, abstractC3148ys, new BiFunction() { // from class: ywi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).d((I2) obj2);
            }
        }, new Predicate() { // from class: zwi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((I2) obj).T0();
            }
        });
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public abstract com.android.tools.r8.graph.I2 c(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2);

    public final C0245l1 e(AbstractC3148ys abstractC3148ys, C0245l1 c0245l1) {
        return (C0245l1) f(abstractC3148ys, c0245l1).a;
    }

    public abstract com.android.tools.r8.graph.proto.j e(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2);

    public boolean f(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        if (a || a(abstractC3148ys)) {
            return true;
        }
        x1f.a();
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean i() {
        return this instanceof Y2;
    }

    public boolean j() {
        return this instanceof C2868vd;
    }

    public boolean k() {
        return this instanceof C1527ft;
    }

    public abstract boolean l();

    public boolean m() {
        return this instanceof FN;
    }

    public abstract boolean n();

    public boolean o() {
        return false;
    }

    public Vl0 e() {
        return null;
    }

    public FN c() {
        return null;
    }

    public final com.android.tools.r8.graph.I2 c(com.android.tools.r8.graph.I2 i2) {
        return c(g(), i2);
    }

    public XR d() {
        return null;
    }

    public final com.android.tools.r8.graph.proto.j d(C0322w2 c0322w2) {
        return e((AbstractC3148ys) null, c0322w2);
    }

    public final C2035lp f(AbstractC3148ys abstractC3148ys, C0245l1 c0245l1) {
        return a(c0245l1, abstractC3148ys, new InterfaceC2979ws() { // from class: pwi
            @Override // com.android.tools.r8.internal.InterfaceC2979ws
            public final C2035lp a(C2035lp c2035lp) {
                return AbstractC3148ys.a(c2035lp);
            }
        });
    }

    public com.android.tools.r8.ir.optimize.A f() {
        if (a || h()) {
            return null;
        }
        x1f.a();
        return null;
    }

    public final C0322w2 b(C0322w2 c0322w2) {
        return (C0322w2) a(c0322w2, g(), new BiFunction() { // from class: cxi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).g((C0322w2) obj2);
            }
        });
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final C0245l1 d(AbstractC3148ys abstractC3148ys, C0245l1 c0245l1) {
        return (C0245l1) a(c0245l1, abstractC3148ys, new BiFunction() { // from class: mwi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).b((C0245l1) obj2);
            }
        }, MX.c);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final C0322w2 d(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        return (C0322w2) a(c0322w2, abstractC3148ys, new BiFunction() { // from class: bxi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).e((C0322w2) obj2);
            }
        }, MX.c);
    }

    public C1527ft b() {
        return null;
    }

    public final IdentityHashMap b(Map map, final BiFunction biFunction) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        map.forEach(new BiConsumer() { // from class: kwi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(identityHashMap, biFunction, (I2) obj, obj2);
            }
        });
        return identityHashMap;
    }

    public static /* synthetic */ C2035lp a(C2035lp c2035lp) {
        return c2035lp;
    }

    public final Set a(Set set) {
        if (set == C2620sh0.b) {
            return set;
        }
        Set setB = C1755ib0.b(set.size());
        Iterator it = set.iterator();
        while (it.hasNext()) {
            setB.add(b((com.android.tools.r8.graph.F2) it.next(), (AbstractC3148ys) null));
        }
        return setB;
    }

    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2) {
        return a(g(), i2);
    }

    public final com.android.tools.r8.graph.I2 a(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        return (com.android.tools.r8.graph.I2) a(i2, abstractC3148ys, new BiFunction() { // from class: nwi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).f((I2) obj2);
            }
        });
    }

    public final C0245l1 a(AbstractC3148ys abstractC3148ys, C0245l1 c0245l1) {
        return (C0245l1) a(c0245l1, abstractC3148ys, new BiFunction() { // from class: owi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).c((C0245l1) obj2);
            }
        });
    }

    public final C0322w2 a(C0322w2 c0322w2) {
        return a(g(), c0322w2);
    }

    public final C0322w2 a(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        return (C0322w2) a(c0322w2, abstractC3148ys, new BiFunction() { // from class: xwi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((XR) obj).f((C0322w2) obj2);
            }
        });
    }

    public final com.android.tools.r8.graph.F2 a(com.android.tools.r8.graph.F2 f2, AbstractC3148ys abstractC3148ys, BiFunction biFunction) {
        while (this.n() && this != abstractC3148ys) {
            XR xrD = this.d();
            f2 = (com.android.tools.r8.graph.F2) biFunction.apply(xrD, f2);
            this = xrD.d;
        }
        return f2;
    }

    public final com.android.tools.r8.graph.F2 a(com.android.tools.r8.graph.F2 f2, final AbstractC3148ys abstractC3148ys) {
        return (com.android.tools.r8.graph.F2) f2.a(new Function() { // from class: twi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(abstractC3148ys, (I2) obj);
            }
        }, new Function() { // from class: uwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c(abstractC3148ys, (C0245l1) obj);
            }
        }, new Function() { // from class: wwi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c(abstractC3148ys, (C0322w2) obj);
            }
        });
    }

    public final C0245l1 a(C0245l1 c0245l1) {
        return d(g(), c0245l1);
    }

    public final void a(C0629Ku c0629Ku, C0245l1 c0245l1, Object obj) {
        c0629Ku.a(d(g(), c0245l1), obj);
    }

    public final com.android.tools.r8.graph.F2 a(com.android.tools.r8.graph.F2 f2, AbstractC3148ys abstractC3148ys, BiFunction biFunction, Predicate predicate) {
        ArrayDeque arrayDeque = new ArrayDeque();
        while (this.n() && this != abstractC3148ys) {
            XR xrD = this.d();
            arrayDeque.addLast(xrD);
            this = xrD.d;
        }
        while (!arrayDeque.isEmpty()) {
            f2 = (com.android.tools.r8.graph.F2) biFunction.apply((XR) arrayDeque.removeLast(), f2);
            if (predicate.test(f2)) {
                break;
            }
        }
        return f2;
    }

    public boolean a(com.android.tools.r8.graph.F2 f2, com.android.tools.r8.graph.F2 f3) {
        if (a || f2 != f3) {
            return false;
        }
        x1f.a();
        return false;
    }

    public final void a(Map map, BiFunction biFunction, com.android.tools.r8.graph.I2 i2, Object obj) {
        com.android.tools.r8.graph.I2 i2C = c(g(), i2);
        Object obj2 = map.get(i2C);
        if (obj2 != null) {
            obj = biFunction.apply(obj, obj2);
        }
        map.put(i2C, obj);
    }

    public final IdentityHashMap a(Map map, final C0313v0 c0313v0, Ch0 ch0) {
        ch0.a("Rewrite call sites");
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        final RJ rj = new RJ(c0313v0, this, null);
        map.forEach(new BiConsumer() { // from class: axi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0313v0, rj, identityHashMap, (D0) obj, (UY) obj2);
            }
        });
        ch0.b();
        return identityHashMap;
    }

    public final void a(InterfaceC0189d1 interfaceC0189d1, RJ rj, Map map, com.android.tools.r8.graph.D0 d0, UY uy) {
        for (com.android.tools.r8.graph.B5 b5 : uy.a(interfaceC0189d1, this).b.values()) {
            ((UY) map.computeIfAbsent(rj.a(d0, b5), new Function() { // from class: vwi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UY.c();
                }
            })).add(b5);
        }
    }

    public final Set a(Set set, Ch0 ch0) {
        ch0.a("Rewrite fields");
        AbstractC3148ys abstractC3148ysG = g();
        Set setNewSetFromMap = null;
        if (b(abstractC3148ysG)) {
            if (!a) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    C0245l1 c0245l1 = (C0245l1) it.next();
                    if (!a && e(abstractC3148ysG, c0245l1) != c0245l1) {
                        x1f.a();
                        return null;
                    }
                }
            }
        } else {
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                C0245l1 c0245l2 = (C0245l1) it2.next();
                C0245l1 c0245l1D = d(abstractC3148ysG, c0245l2);
                if (setNewSetFromMap != null) {
                    setNewSetFromMap.add(c0245l1D);
                } else if (c0245l1D != c0245l2) {
                    setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(set.size()));
                    Objects.requireNonNull(setNewSetFromMap);
                    C1674he.a(set, new gff(setNewSetFromMap), c0245l2);
                    setNewSetFromMap.add(c0245l1D);
                }
            }
            if (setNewSetFromMap != null) {
                if (setNewSetFromMap.size() < set.size()) {
                    set = Collections.newSetFromMap(new IdentityHashMap(setNewSetFromMap.size()));
                    set.addAll(setNewSetFromMap);
                } else {
                    set = setNewSetFromMap;
                }
            }
        }
        ch0.b();
        return set;
    }

    public final void a(Map map, Map map2, com.android.tools.r8.graph.F2 f2, Object obj) {
        com.android.tools.r8.graph.F2 f2B = b(f2, (AbstractC3148ys) null);
        List list = (List) map.get(f2B);
        if (list != null) {
            list.add(obj);
            return;
        }
        Object objPut = map2.put(f2B, obj);
        if (objPut != null) {
            boolean z = C2847vL.a;
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(objPut);
            arrayList.add(obj);
            map.put(f2B, arrayList);
            map2.remove(f2B);
        }
    }

    public final IdentityHashMap a(Map map, final BiFunction biFunction) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        final IdentityHashMap identityHashMap2 = new IdentityHashMap();
        map.forEach(new BiConsumer() { // from class: exi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(identityHashMap2, identityHashMap, (F2) obj, obj2);
            }
        });
        identityHashMap2.forEach(new BiConsumer() { // from class: lwi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AbstractC3148ys.a(biFunction, identityHashMap, (F2) obj, (List) obj2);
            }
        });
        return identityHashMap;
    }

    public static /* synthetic */ void a(BiFunction biFunction, Map map, com.android.tools.r8.graph.F2 f2, List list) {
        Object objApply = biFunction.apply(f2, list);
        if (objApply != null) {
            map.put(f2, objApply);
        }
    }

    public final AbstractC0706Nu a(Map map) {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        map.forEach(new BiConsumer() { // from class: dxi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0629KuE, (C0245l1) obj, obj2);
            }
        });
        return c0629KuE.b();
    }

    public C0907Vn a() {
        return null;
    }
}
