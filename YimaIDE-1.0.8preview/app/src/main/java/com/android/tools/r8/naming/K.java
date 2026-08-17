package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D0;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.G0;
import com.android.tools.r8.graph.H0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.S5;
import com.android.tools.r8.internal.AbstractC0464El;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C0386Bl;
import com.android.tools.r8.internal.C1348dm;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.C2765uO;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.GC;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.NC;
import com.android.tools.r8.naming.I;
import com.android.tools.r8.naming.K;
import com.android.tools.r8.shaking.C3403i;
import defpackage.ulg;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K {
    public static final G f = new G();
    public static final /* synthetic */ boolean g = true;
    public final C0333y a;
    public final S5 b;
    public final Y c;
    public final H d = new H(new HashMap());
    public final HashMap e = new HashMap();

    public K(C0333y c0333y, Y y, S5 s5) {
        this.a = c0333y;
        this.c = y;
        this.b = s5;
    }

    public final void a(Ch0 ch0, List list) {
        ch0.a("Interface minification");
        ch0.a("Reserve direct and compute hierarchy");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.E0 e0 = (com.android.tools.r8.graph.E0) it.next();
            if (!g && !e0.isInterface()) {
                x1f.a();
                return;
            }
            Y y = this.c;
            I2 i2 = e0.e;
            Z z = y.a;
            z.a(i2, i2, z.i);
            J j = new J(this, e0);
            j.c.add(e0.e);
            this.e.put(e0.e, j);
        }
        for (Map.Entry entry : this.e.entrySet()) {
            for (I2 i3 : ((J) entry.getValue()).a.h.b) {
                J j2 = (J) this.e.get(i3);
                if (j2 != null) {
                    j2.b.add((I2) entry.getKey());
                }
            }
        }
        ch0.b();
        ch0.a("Compute map");
        a(list);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            com.android.tools.r8.graph.E0 e1 = (com.android.tools.r8.graph.E0) it2.next();
            J j3 = (J) this.e.get(e1.e);
            if (!g && j3 == null) {
                x1f.a();
                return;
            }
            C2924wC c2924wCF0 = e1.F0();
            GC gcA = NC.a(c2924wCF0.b.iterator(), c2924wCF0.c);
            while (gcA.b.hasNext()) {
                com.android.tools.r8.graph.H0 h0 = (com.android.tools.r8.graph.H0) gcA.a(gcA.b.next());
                H h = this.d;
                Function functionA = IM.a(new Supplier() { // from class: r28
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.b.b();
                    }
                });
                h.getClass();
                ((I) h.a(functionA, h.b(h0))).a(h0, j3);
            }
        }
        ch0.b();
        Set setKeySet = ((C3403i) this.a.g()).u.keySet();
        ch0.a("Union-find");
        final C1348dm c1348dm = new C1348dm();
        setKeySet.forEach(new Consumer() { // from class: s28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(c1348dm, (D0) obj);
            }
        });
        ch0.b();
        ch0.a("States for union");
        final H h2 = new H(new HashMap());
        BiConsumer biConsumer = new BiConsumer() { // from class: t28
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Set) h2.a(IM.a(new jc4()), (C2119mo) obj)).add((C2119mo) obj2);
            }
        };
        for (Object obj : c1348dm.a.keySet()) {
            biConsumer.accept(c1348dm.b(obj), obj);
        }
        h2.forEach(new BiConsumer() { // from class: u28
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj2, Object obj3) {
                this.a.a((H0) obj2, (Set) obj3);
            }
        });
        ch0.b();
        ch0.a("Sort");
        List<com.android.tools.r8.graph.H0> list2 = (List) this.d.b.keySet().stream().filter(new Predicate() { // from class: e28
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                return c1348dm.c((C2119mo) obj2);
            }
        }).map(new Function() { // from class: f28
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                return (H0) ((C2119mo) obj2).a();
            }
        }).sorted(this.a.Q().S0.a(a())).collect(Collectors.toList());
        ch0.b();
        boolean z2 = g;
        if (!z2) {
            c(list2);
        }
        if (!z2) {
            b(list2);
        }
        ch0.a("Reserve in groups");
        ArrayList<com.android.tools.r8.graph.H0> arrayList = new ArrayList();
        for (com.android.tools.r8.graph.H0 h1 : list2) {
            H h3 = this.d;
            I i = (I) h3.b.get(h3.b(h1));
            if (!g && i == null) {
                x1f.a();
                return;
            }
            H2 h2A = i.a();
            if (h2A == null) {
                arrayList.add(h1);
            } else {
                i.b(h2A);
            }
        }
        ch0.b();
        ch0.a("Rename in groups");
        for (com.android.tools.r8.graph.H0 h4 : arrayList) {
            H h5 = this.d;
            I i4 = (I) h5.b.get(h5.b(h4));
            boolean z3 = g;
            if (!z3 && i4 == null) {
                x1f.a();
                return;
            }
            if (!z3 && i4.a() != null) {
                x1f.a();
                return;
            }
            H2 h2A2 = a(h4, i4);
            if (!z3 && h2A2 == null) {
                x1f.a();
                return;
            }
            AbstractC2554rv abstractC2554rv = this.a.M().v0;
            if (!abstractC2554rv.isEmpty() && i4.c.j().map(new Function() { // from class: g28
                @Override // java.util.function.Function
                public final Object apply(Object obj2) {
                    return ((H0) obj2).v();
                }
            }).anyMatch(new ulg(abstractC2554rv))) {
                a(h4.getReference(), i4.c.i(), System.out);
            }
        }
        for (com.android.tools.r8.graph.H0 h6 : arrayList) {
            H h7 = this.d;
            I i5 = (I) h7.b.get(h7.b(h6));
            if (!i5.d.isEmpty()) {
                C3314b0 c3314b0B = this.c.a.b(h6.s());
                H2 h2A3 = c3314b0B.a(h6);
                if (!g && h2A3 == null) {
                    x1f.a();
                    return;
                }
                for (com.android.tools.r8.graph.H0 h8 : i5.d) {
                    H2 h2A4 = a(h8, c3314b0B, i5);
                    this.c.a(h8, h2A4);
                    ((C3312a0) this.c.a.b(h8.s()).c(h8.getReference())).a(h2A4, h8.getReference());
                    ((C3312a0) c3314b0B.c(h8.getReference())).a(h2A4, h8.getReference());
                }
            }
        }
        ch0.b();
        ch0.b();
    }

    public final void b(List list) {
        final HashSet hashSet = new HashSet(list.size());
        list.forEach(new Consumer() { // from class: h28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                K.a(hashSet, (H0) obj);
            }
        });
        final HashSet hashSet2 = new HashSet();
        final HashSet hashSet3 = new HashSet();
        this.d.forEach(new BiConsumer() { // from class: i28
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                K.a(hashSet3, hashSet, hashSet2, (H0) obj, (I) obj2);
            }
        });
        boolean z = g;
        if (!z && hashSet3.size() != hashSet2.size()) {
            x1f.a();
        } else {
            if (z || hashSet2.containsAll(hashSet3)) {
                return;
            }
            x1f.a();
        }
    }

    public final void c(List list) {
        final HashSet hashSet = new HashSet(list.size());
        list.forEach(new Consumer() { // from class: j28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                K.b(hashSet, (H0) obj);
            }
        });
        final Set setC = AbstractC2780ub0.c();
        final Set setC2 = AbstractC2780ub0.c();
        this.d.forEach(new BiConsumer() { // from class: k28
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                I i = (I) obj2;
                i.c.a(new Consumer() { // from class: n28
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj3) {
                        K.a(set, set, h0, set, (H0) obj3);
                    }
                });
            }
        });
        boolean z = g;
        if (!z && setC2.size() != setC.size()) {
            x1f.a();
        } else {
            if (z || setC.containsAll(setC2)) {
                return;
            }
            x1f.a();
        }
    }

    public final /* synthetic */ I c() {
        return new I(this);
    }

    public final /* synthetic */ I b() {
        return new I(this);
    }

    public static void b(Set set, com.android.tools.r8.graph.H0 h0) {
        set.add(new C2119mo(f, h0));
    }

    public final Comparator a() {
        final H h = this.d;
        Objects.requireNonNull(h);
        return Comparator.comparing(new Function() { // from class: o28
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (I) h.a((G0) obj);
            }
        });
    }

    public final void a(C1348dm c1348dm, com.android.tools.r8.graph.D0 d0) {
        HashSet<C2119mo> hashSet = new HashSet();
        AbstractC0464El abstractC0464ElA = ((C3403i) this.a.g()).a(d0, this.a);
        Iterator it = abstractC0464ElA.b.values().iterator();
        while (it.hasNext()) {
            C2119mo c2119mo = new C2119mo(f, (com.android.tools.r8.graph.H0) it.next());
            I i = (I) this.d.b.get(c2119mo);
            if (!g && i == null) {
                x01.a(c2119mo);
                return;
            } else {
                i.b.add(d0);
                hashSet.add(c2119mo);
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        C0333y c0333y = this.a;
        C1819jJ c1819jJA = C1819jJ.a(d0, c0333y, (C0229j) c0333y.g(), null);
        List list = c1819jJA != C1819jJ.j ? c1819jJA.e : null;
        if (list != null) {
            for (int i2 = 1; i2 < list.size(); i2++) {
                com.android.tools.r8.graph.E0 e0D = this.a.d((I2) list.get(i2));
                if (!g && !e0D.isInterface()) {
                    x1f.a();
                    return;
                }
                for (com.android.tools.r8.graph.H0 h0 : abstractC0464ElA.b.values()) {
                    C2924wC c2924wCF1 = e0D.F1();
                    GC gcA = NC.a(c2924wCF1.b.iterator(), c2924wCF1.c);
                    while (gcA.b.hasNext()) {
                        com.android.tools.r8.graph.H0 h1 = (com.android.tools.r8.graph.H0) gcA.a(gcA.b.next());
                        if (h0.getReference().x0() != h1.getReference().x0() && C2765uO.c.b(h0.getReference(), h1.getReference())) {
                            H h = this.d;
                            Function functionA = IM.a(new Supplier() { // from class: q28
                                @Override // java.util.function.Supplier
                                public final Object get() {
                                    return this.b.c();
                                }
                            });
                            h.getClass();
                            ((I) h.a(functionA, h.b(h0))).d.add(h1);
                        }
                    }
                }
            }
        }
        if (hashSet.size() > 1) {
            C2119mo c2119mo2 = (C2119mo) hashSet.iterator().next();
            Object objB = c1348dm.b(c2119mo2);
            if (objB == null) {
                objB = c1348dm.d(c2119mo2);
            }
            C2119mo c2119mo3 = (C2119mo) objB;
            for (C2119mo c2119mo4 : hashSet) {
                if (c2119mo3 == c2119mo4) {
                    if (c1348dm.b(c2119mo3) == null) {
                        c1348dm.d(c2119mo3);
                    }
                } else {
                    Object objB2 = c1348dm.b(c2119mo3);
                    if (objB2 == null) {
                        objB2 = c1348dm.d(c2119mo3);
                    }
                    Object objB3 = c1348dm.b(c2119mo4);
                    if (objB3 == null) {
                        objB3 = c1348dm.d(c2119mo4);
                    }
                    c1348dm.a(objB2, objB3);
                }
            }
        }
    }

    public final void a(com.android.tools.r8.graph.H0 h0, Set set) {
        H h = this.d;
        I i = (I) h.b.get(h.b(h0));
        if (!g && i == null) {
            x1f.a();
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            i.a((I) this.d.b.get((C2119mo) it.next()));
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00a2  */
    public final H2 a(com.android.tools.r8.graph.H0 h0, final I i) {
        boolean z = g;
        if (!z && i.a() != null) {
            x1f.a();
            return null;
        }
        if (!z) {
            C0386Bl c0386Bl = i.c;
            if (!c0386Bl.b.containsKey(c0386Bl.b(h0))) {
                x1f.a();
                return null;
            }
        }
        if (!z) {
            I2 i2S = h0.s();
            C0386Bl c0386Bl2 = i.c;
            Set set = (Set) c0386Bl2.b.get(c0386Bl2.b(h0));
            if (set != null) {
                Iterator it = set.iterator();
                do {
                    if (it.hasNext()) {
                    }
                } while (!((J) it.next()).c.contains(i2S));
            }
            x1f.a();
            return null;
        }
        Y y = this.c;
        C3314b0 c3314b0B = y.a.b(h0.s());
        BiPredicate biPredicate = new BiPredicate() { // from class: l28
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return i.a((H2) obj);
            }
        };
        H2 h2D = c3314b0B.d(h0.getReference());
        if (h2D == null) {
            Set setD = c3314b0B.c.d(h0.getReference());
            if (setD != null && setD.size() == 1) {
                h2D = (H2) setD.iterator().next();
                if (!c3314b0B.b(h2D, h0.getReference())) {
                    h2D = c3314b0B.e.a(h0, (C3312a0) c3314b0B.c(h0.getReference()), biPredicate);
                    if (!C3314b0.f) {
                        x1f.a();
                        return null;
                    }
                }
            } else {
                h2D = c3314b0B.e.a(h0, (C3312a0) c3314b0B.c(h0.getReference()), biPredicate);
                if (!C3314b0.f && h2D == null) {
                    x1f.a();
                    return null;
                }
            }
        }
        i.a(h2D, this.c);
        return h2D;
    }

    public static H2 a(com.android.tools.r8.graph.H0 h0, C3314b0 c3314b0, final I i) {
        BiPredicate biPredicate = new BiPredicate() { // from class: c28
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return i.a((H2) obj);
            }
        };
        H2 h2A = c3314b0.e.a(h0, (C3312a0) c3314b0.c(h0.getReference()), biPredicate);
        if (C3314b0.f || h2A != null) {
            return h2A;
        }
        x1f.a();
        return null;
    }

    public final void a(List list) {
        list.forEach(new Consumer() { // from class: p28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((E0) obj);
            }
        });
    }

    public final /* synthetic */ void a(final com.android.tools.r8.graph.E0 e0) {
        this.b.g(e0.getType()).forEach(new Consumer() { // from class: m28
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(e0, (I2) obj);
            }
        });
    }

    public final void a(com.android.tools.r8.graph.E0 e0, I2 i2) {
        J j;
        com.android.tools.r8.graph.E0 e0A = this.a.a(i2);
        if (e0A == null || e0A.isInterface()) {
            return;
        }
        I2 i2A = this.c.a(i2);
        if (((C3320e0) this.c.a.e.get(i2A)) == null || (j = (J) this.e.get(e0.getType())) == null) {
            return;
        }
        j.c.add(i2A);
    }

    public static void a(Set set, com.android.tools.r8.graph.H0 h0) {
        set.add(new C2119mo(f, h0));
    }

    public static void a(Set set, Set set2, Set set3, com.android.tools.r8.graph.H0 h0, I i) {
        for (com.android.tools.r8.graph.D0 d0 : i.b) {
            set.add(d0);
            if (set2.contains(new C2119mo(f, h0))) {
                boolean zAdd = set3.add(d0);
                if (!g && !zAdd) {
                    x1f.a();
                    return;
                }
            }
        }
    }

    public static void a(Set set, Set set2, com.android.tools.r8.graph.H0 h0, Set set3, com.android.tools.r8.graph.H0 h1) {
        set.add(h1.e());
        if (set2.contains(new C2119mo(f, h0))) {
            boolean zAdd = set3.add(h1.e());
            if (g || zAdd) {
                return;
            }
            x1f.a();
        }
    }

    public static void a(C0322w2 c0322w2, ArrayList arrayList, PrintStream printStream) {
        printStream.println("-----------------------------------------------------------------------");
        printStream.println("assignNameToInterfaceMethod(`" + c0322w2.m0() + "`)");
        printStream.println("-----------------------------------------------------------------------");
        printStream.println("Source methods:");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            printStream.println("  " + ((com.android.tools.r8.graph.H0) it.next()).v());
        }
        printStream.println("States:");
        printStream.println();
    }
}
