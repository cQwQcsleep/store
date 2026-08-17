package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0229j;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1777in;
import com.android.tools.r8.internal.C2552rt;
import com.android.tools.r8.internal.C3020xM;
import com.android.tools.r8.internal.C3065xt;
import com.android.tools.r8.internal.O2;
import defpackage.n33;
import defpackage.ozh;
import defpackage.s2i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3065xt {
    public C0229j a;
    public C2742u50 b;
    public final Set c = AbstractC2780ub0.c();
    public final Ch0 d;

    public C3065xt(Ch0 ch0) {
        this.d = ch0;
    }

    public final C3020xM a(C2552rt c2552rt, String str, boolean z, String str2) {
        this.d.a("convert rewriting flags");
        boolean z2 = C3020xM.s;
        final C2934wM c2934wM = new C2934wM();
        Map<C0322w2, com.android.tools.r8.graph.F4> mapB = c2552rt.b();
        Map<C0245l1, C0205f3> mapA = c2552rt.a();
        C0229j c0229j = this.a;
        C2742u50 c2742u50 = this.b;
        int i = com.android.tools.r8.androidapi.f.a;
        com.android.tools.r8.androidapi.h hVar = com.android.tools.r8.androidapi.h.b;
        if (!mapB.isEmpty() || !mapA.isEmpty()) {
            new C0411Ck(c0229j, c2742u50, hVar).a(mapB, mapA);
        }
        c2552rt.b().forEach(new BiConsumer() { // from class: tsi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2934wM.a((C0322w2) obj, (F4) obj2);
            }
        });
        c2552rt.a().forEach(new BiConsumer() { // from class: ati
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2934wM.a((C0245l1) obj, (C0205f3) obj2);
            }
        });
        c2552rt.l.forEach(new BiConsumer() { // from class: bti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2934wM.a((C0322w2) obj, (C0322w2[]) obj2);
            }
        });
        c2552rt.q.forEach(new Consumer() { // from class: cti
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c2934wM.a((C0322w2) obj);
            }
        });
        new C2980wt(this.a).a(c2552rt, c2934wM, new BiConsumer() { // from class: dti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b((String) obj, (Set) obj2);
            }
        });
        new C2808ut(this.a).a(c2552rt, this.a, c2934wM, new BiConsumer() { // from class: dti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b((String) obj, (Set) obj2);
            }
        });
        C2894vt c2894vt = new C2894vt(this.a, c2934wM, str, z, str2, c2552rt);
        BiConsumer biConsumer = new BiConsumer() { // from class: eti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((String) obj, (Set) obj2);
            }
        };
        c2894vt.a();
        Iterator<com.android.tools.r8.graph.I2> it = c2552rt.g().values().iterator();
        while (it.hasNext()) {
            c2894vt.d(it.next());
        }
        Iterator it2 = c2552rt.i.values().iterator();
        while (it2.hasNext()) {
            c2894vt.d((com.android.tools.r8.graph.I2) it2.next());
        }
        Iterator<com.android.tools.r8.graph.I2> it3 = c2552rt.c().values().iterator();
        while (it3.hasNext()) {
            c2894vt.d(it3.next());
        }
        Iterator it4 = c2552rt.j.values().iterator();
        while (it4.hasNext()) {
            c2894vt.d(((C0322w2) it4.next()).w0());
        }
        Iterator it5 = c2552rt.k.values().iterator();
        while (it5.hasNext()) {
            c2894vt.d(((C0322w2) it5.next()).w0());
        }
        c2894vt.b(c2552rt.e());
        for (C0322w2 c0322w2 : c2552rt.i.keySet()) {
            com.android.tools.r8.graph.I2 i2A = c2894vt.a(c0322w2.f);
            C2934wM c2934wM2 = c2894vt.b;
            c2934wM2.c.put(c0322w2.f, i2A);
        }
        for (C0322w2 c0322w3 : c2552rt.k.keySet()) {
            com.android.tools.r8.graph.I2 i2A2 = c2894vt.a(c0322w3.f);
            C2934wM c2934wM3 = c2894vt.b;
            c2934wM3.c.put(c0322w3.f, i2A2);
        }
        c2894vt.a(c2552rt.l);
        c2894vt.a(biConsumer);
        C3235zt c3235zt = new C3235zt(this.a);
        BiConsumer biConsumer2 = new BiConsumer() { // from class: dti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b((String) obj, (Set) obj2);
            }
        };
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator<com.android.tools.r8.graph.I2> it6 = c2552rt.j().keySet().iterator();
        while (it6.hasNext()) {
            identityHashMap.put(it6.next(), new C3149yt());
        }
        c3235zt.a(c2552rt, identityHashMap);
        LinkedHashMap linkedHashMapA = C3235zt.a(identityHashMap);
        Map<com.android.tools.r8.graph.I2, Set<C0322w2>> mapJ = c2552rt.j();
        for (C3149yt c3149yt : linkedHashMapA.values()) {
            ArrayList arrayList = new ArrayList();
            for (com.android.tools.r8.graph.I2 i2 : c3149yt.b) {
                if (!mapJ.get(i2).isEmpty()) {
                    arrayList.add(i2);
                }
            }
            if (!arrayList.isEmpty()) {
                c3149yt.b.removeAll(arrayList);
            }
        }
        C3235zt.a(linkedHashMapA, c2934wM);
        biConsumer2.accept("The following types to wrap are missing: ", c3235zt.c);
        biConsumer2.accept("The following methods cannot be handled by the wrappers due to their flags: ", c3235zt.d);
        c2552rt.c().forEach(new BiConsumer() { // from class: fti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c2934wM, (I2) obj, (I2) obj2);
            }
        });
        b("Cannot register custom conversion due to missing type: ", this.c);
        c2552rt.d().forEach(new Consumer() { // from class: gti
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c2934wM.a((I2) obj);
            }
        });
        c2552rt.f().forEach(new BiConsumer() { // from class: zsi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c2934wM.a((I2) obj, (I2) obj2);
            }
        });
        C3020xM c3020xMA = c2934wM.a();
        this.d.b();
        return c3020xMA;
    }

    public final void b(String str, Set set) {
        ArrayList arrayList = new ArrayList(set);
        arrayList.sort(new ozh());
        a(str, arrayList);
    }

    public static Set b(HashMap map) {
        final Set setC = AbstractC2780ub0.c();
        map.forEach(new BiConsumer() { // from class: hti
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                setC.addAll(((C3020xM) obj2).g().keySet());
            }
        });
        return setC;
    }

    public static void a(HashMap map) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        map.forEach(new BiConsumer() { // from class: vsi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((C3020xM) obj2).g().forEach(new BiConsumer() { // from class: usi
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj3, Object obj4) {
                        C3065xt.a(map, o2, (I2) obj3, (C1777in) obj4);
                    }
                });
            }
        });
        identityHashMap.keySet().removeIf(new Predicate() { // from class: wsi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C3065xt.a(identityHashMap, (I2) obj);
            }
        });
        identityHashMap.forEach(new BiConsumer() { // from class: xsi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3065xt.a((I2) obj, (List) obj2);
            }
        });
    }

    public static /* synthetic */ void a(Map map, O2 o2, com.android.tools.r8.graph.I2 i2, C1777in c1777in) {
        map.putIfAbsent(i2, new ArrayList());
        ((List) map.get(i2)).add(o2);
    }

    public static /* synthetic */ boolean a(Map map, com.android.tools.r8.graph.I2 i2) {
        return ((List) map.get(i2)).size() == 1;
    }

    public static /* synthetic */ void a(com.android.tools.r8.graph.I2 i2, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            O2 o2 = (O2) it.next();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                O2 o3 = (O2) it2.next();
                if (!Objects.equals(o2, o3) && o2.a(o3)) {
                    throw new C0613Ke("Unsupported Machine specification for " + i2 + " " + o2 + " " + o3);
                }
            }
        }
    }

    public final HashMap a(Map map, final String str, final boolean z, final String str2) {
        final HashMap map2 = new HashMap();
        map.forEach(new BiConsumer() { // from class: ysi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(map2, str, z, str2, (O2) obj, (C2552rt) obj2);
            }
        });
        return map2;
    }

    public final /* synthetic */ void a(Map map, String str, boolean z, String str2, O2 o2, C2552rt c2552rt) {
        map.put(o2, a(c2552rt, str, z, str2));
    }

    public C2592sM a(C2124mt c2124mt, AbstractC0327x0 abstractC0327x0) {
        this.d.a("Human to machine convert");
        this.b = abstractC0327x0.d.i;
        C0215h c0215hA = C0215h.a(abstractC0327x0, com.android.tools.r8.synthesis.E.d());
        if (!C0229j.i && c0215hA.h()) {
            x1f.a();
            return null;
        }
        this.a = new C0229j(c0215hA);
        AbstractC1225cK.a(abstractC0327x0, c2124mt.a, c2124mt.b.d());
        C3020xM c3020xMA = a(c2124mt.c, c2124mt.b.e(), c2124mt.a, c2124mt.b.c());
        C0394Bt c0394Bt = c2124mt.b;
        C3189zM c3189zM = new C3189zM(c0394Bt.d(), c0394Bt.e(), c0394Bt.c(), c0394Bt.d, c0394Bt.e, c0394Bt.b());
        this.d.b();
        return new C2592sM(c2124mt.a, c3189zM, c3020xMA);
    }

    public GP a(C3192zP c3192zP, AbstractC0327x0 abstractC0327x0) {
        this.d.a("Legacy to human all API convert");
        this.b = abstractC0327x0.d.i;
        C0215h c0215hA = C0215h.a(abstractC0327x0, com.android.tools.r8.synthesis.E.d());
        if (!C0229j.i && c0215hA.h()) {
            x1f.a();
            return null;
        }
        this.a = new C0229j(c0215hA);
        C0394Bt c0394BtD = c3192zP.d();
        C3189zM c3189zM = new C3189zM(c0394BtD.d(), c0394BtD.e(), c0394BtD.c(), c0394BtD.d, c0394BtD.e, c0394BtD.b());
        String strE = c3189zM.e();
        String strC = c3189zM.c();
        HashMap mapA = a((Map) c3192zP.a(), strE, true, strC);
        HashMap mapA2 = a((Map) c3192zP.c(), strE, false, strC);
        HashMap mapA3 = a((Map) c3192zP.b(), strE, true, strC);
        Set setB = b(mapA);
        Set setB2 = b(mapA2);
        Set setB3 = b(mapA3);
        C1924kb0 c1924kb0A = AbstractC2780ub0.a(setB, setB2);
        if (Collections.disjoint(c1924kb0A.c, c1924kb0A.b)) {
            C1924kb0 c1924kb0A2 = AbstractC2780ub0.a(setB, setB3);
            if (Collections.disjoint(c1924kb0A2.c, c1924kb0A2.b)) {
                C1924kb0 c1924kb0A3 = AbstractC2780ub0.a(setB3, setB2);
                if (Collections.disjoint(c1924kb0A3.c, c1924kb0A3.b)) {
                    a(mapA);
                    a(mapA2);
                    a(mapA3);
                    GP gp = new GP(c3189zM, mapA, mapA3, mapA2);
                    this.d.b();
                    return gp;
                }
            }
        }
        n33.a("Cannot have emulated interface split across flag types");
        return null;
    }

    public final void a(C2934wM c2934wM, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        C0229j c0229j = this.a;
        com.android.tools.r8.graph.I2 i4 = (com.android.tools.r8.graph.I2) c2934wM.a.get(i2);
        if (i4 == null) {
            this.c.add(i2);
            return;
        }
        c2934wM.o.a(i2, new C1850jh(c0229j.a().a(i3, c0229j.a().a(i2, i4), c0229j.a().m0), c0229j.a().a(i3, c0229j.a().a(i4, i2), c0229j.a().m0)));
    }

    public final void a(String str, Set set) {
        ArrayList arrayList = new ArrayList(set);
        arrayList.sort(new s2i());
        a(str, arrayList);
    }

    public final void a(String str, ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            return;
        }
        this.b.c("Specification conversion: " + str + arrayList);
    }
}
