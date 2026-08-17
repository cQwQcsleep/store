package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1606gn;
import com.android.tools.r8.internal.C1777in;
import com.android.tools.r8.internal.C1850jh;
import com.android.tools.r8.internal.C3020xM;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3020xM {
    public static final /* synthetic */ boolean s = true;
    public final Map a;
    public final Set b;
    public final Map c;
    public final Map d;
    public final Map e;
    public final Map f;
    public final Map g;
    public final Map h;
    public final Map i;
    public final Map j;
    public final Map k;
    public final LinkedHashMap l;
    public final Map m;
    public final Set n;
    public final Map o;
    public final Set p;
    public final Map q;
    public final Map r;

    public C3020xM(Map map, AbstractC2554rv abstractC2554rv, Map map2, AbstractC0706Nu abstractC0706Nu, AbstractC0706Nu abstractC0706Nu2, AbstractC0706Nu abstractC0706Nu3, AbstractC0706Nu abstractC0706Nu4, AbstractC0706Nu abstractC0706Nu5, AbstractC0706Nu abstractC0706Nu6, AbstractC0706Nu abstractC0706Nu7, AbstractC0706Nu abstractC0706Nu8, LinkedHashMap linkedHashMap, AbstractC0706Nu abstractC0706Nu9, AbstractC2554rv abstractC2554rv2, AbstractC0706Nu abstractC0706Nu10, AbstractC2554rv abstractC2554rv3, AbstractC0706Nu abstractC0706Nu11, AbstractC0706Nu abstractC0706Nu12) {
        this.a = map;
        this.b = abstractC2554rv;
        this.c = map2;
        this.d = abstractC0706Nu;
        this.e = abstractC0706Nu2;
        this.f = abstractC0706Nu3;
        this.g = abstractC0706Nu4;
        this.h = abstractC0706Nu5;
        this.i = abstractC0706Nu6;
        this.j = abstractC0706Nu7;
        this.k = abstractC0706Nu8;
        this.l = linkedHashMap;
        this.m = abstractC0706Nu9;
        this.n = abstractC2554rv2;
        this.o = abstractC0706Nu10;
        this.p = abstractC2554rv3;
        this.q = abstractC0706Nu11;
        this.r = abstractC0706Nu12;
    }

    public static C0322w2 a(C0322w2 c0322w2, com.android.tools.r8.graph.B1 b1, String str, String str2) {
        com.android.tools.r8.graph.I2 i2W0 = c0322w2.w0();
        com.android.tools.r8.graph.E2 e2C0 = c0322w2.C0();
        com.android.tools.r8.graph.I2[] i2Arr = e2C0.q0().b;
        int length = i2Arr.length;
        com.android.tools.r8.graph.I2[] i2Arr2 = new com.android.tools.r8.graph.I2[length];
        for (int i = 0; i < i2Arr.length; i++) {
            i2Arr2[i] = b1.e(i2Arr[i].Z0().replace(str, str2));
        }
        return b1.a(i2W0, b1.a(b1.e(e2C0.r0().Z0().replace(str, str2)), length == 0 ? com.android.tools.r8.graph.K2.n0() : new com.android.tools.r8.graph.K2(i2Arr2)), c0322w2.x0());
    }

    public final AbstractC0706Nu b(final String str, final com.android.tools.r8.graph.B1 b1, final String str2) {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        this.k.forEach(new BiConsumer() { // from class: cri
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0629KuE, b1, str, str2, (I2) obj, (C1777in) obj2);
            }
        });
        return c0629KuE.b();
    }

    public final C3020xM c(String str, com.android.tools.r8.graph.B1 b1, String str2) {
        AbstractC0706Nu abstractC0706NuA = a(this.a, b1, str, str2);
        Set set = this.b;
        AbstractC0706Nu abstractC0706NuA2 = a(this.c, b1, str, str2);
        Map map = this.d;
        Map map2 = this.e;
        Map map3 = this.f;
        Map map4 = this.g;
        Map map5 = this.h;
        Map map6 = this.i;
        Map map7 = this.j;
        AbstractC0706Nu abstractC0706NuB = b(str, b1, str2);
        LinkedHashMap linkedHashMap = this.l;
        Map map8 = this.m;
        Set set2 = this.n;
        return new C3020xM(abstractC0706NuA, (AbstractC2554rv) set, abstractC0706NuA2, (AbstractC0706Nu) map, (AbstractC0706Nu) map2, (AbstractC0706Nu) map3, (AbstractC0706Nu) map4, (AbstractC0706Nu) map5, (AbstractC0706Nu) map6, (AbstractC0706Nu) map7, abstractC0706NuB, linkedHashMap, (AbstractC0706Nu) map8, (AbstractC2554rv) set2, a(str, b1, str2), (AbstractC2554rv) this.p, (AbstractC0706Nu) this.q, (AbstractC0706Nu) this.r);
    }

    public Map<C0322w2, C0322w2> d() {
        return this.e;
    }

    public Map<com.android.tools.r8.graph.I2, C1850jh> e() {
        return this.o;
    }

    public Set<com.android.tools.r8.graph.I2> f() {
        return this.n;
    }

    public Map<com.android.tools.r8.graph.I2, C1777in> g() {
        return this.k;
    }

    public Map<C0322w2, C1606gn> h() {
        return this.h;
    }

    public Map<C0322w2, C0322w2> i() {
        return this.i;
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> j() {
        return this.m;
    }

    public Set<com.android.tools.r8.graph.I2> k() {
        return this.b;
    }

    public Map<C0322w2, C0322w2> l() {
        return this.g;
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> m() {
        return this.c;
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> n() {
        return this.a;
    }

    public Map<C0245l1, C0245l1> o() {
        return this.d;
    }

    public Map<C0322w2, C0322w2> p() {
        return this.f;
    }

    public LinkedHashMap<com.android.tools.r8.graph.I2, Tm0> q() {
        return this.l;
    }

    public final boolean r() {
        return (this.e.isEmpty() && this.f.isEmpty() && this.g.isEmpty() && this.h.isEmpty() && this.d.isEmpty()) ? false : true;
    }

    public final boolean b(final com.android.tools.r8.graph.I2 i2) {
        return AbstractC3179zC.b(this.k.values(), new EX() { // from class: dri
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C3020xM.a(i2, (C1777in) obj);
            }
        });
    }

    public Map<C0322w2, com.android.tools.r8.graph.F4> b() {
        return this.q;
    }

    public static C1777in a(final C1777in c1777in, final com.android.tools.r8.graph.B1 b1, String str, String str2) {
        final com.android.tools.r8.graph.I2 i2E = b1.e(c1777in.a.Z0().replace(str, str2));
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        c1777in.b.forEach(new BiConsumer() { // from class: ari
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3020xM.a(c1777in, identityHashMap, i2E, b1, (C0322w2) obj, (C1606gn) obj2);
            }
        });
        return new C1777in(i2E, identityHashMap);
    }

    public static boolean a(com.android.tools.r8.graph.I2 i2, C1777in c1777in) {
        return c1777in.a == i2;
    }

    public final void a(Consumer consumer) {
        this.f.keySet().forEach(consumer);
        this.g.keySet().forEach(consumer);
        this.h.keySet().forEach(consumer);
    }

    public final boolean a(final com.android.tools.r8.graph.I2 i2) {
        return AbstractC3179zC.b(this.o.values(), new EX() { // from class: fri
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C3020xM.a(i2, (C1850jh) obj);
            }
        });
    }

    public Map<C0245l1, C0205f3> a() {
        return this.r;
    }

    public final AbstractC0706Nu a(final String str, final com.android.tools.r8.graph.B1 b1, final String str2) {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        this.o.forEach(new BiConsumer() { // from class: eri
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0629KuE, b1, str, str2, (I2) obj, (C1850jh) obj2);
            }
        });
        return c0629KuE.b();
    }

    public final void a(C0629Ku c0629Ku, com.android.tools.r8.graph.B1 b1, String str, String str2, com.android.tools.r8.graph.I2 i2, C1850jh c1850jh) {
        c0629Ku.a(i2, new C1850jh(a(c1850jh.a, b1, str, str2), a(c1850jh.b, b1, str, str2)));
    }

    public final /* synthetic */ void a(C0629Ku c0629Ku, com.android.tools.r8.graph.B1 b1, String str, String str2, com.android.tools.r8.graph.I2 i2, C1777in c1777in) {
        c0629Ku.a(i2, a(c1777in, b1, str, str2));
    }

    public static void a(C1777in c1777in, Map map, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2, C1606gn c1606gn) {
        if (!s && c1606gn.a.a.w0() != c1777in.a) {
            x1f.a();
        } else {
            map.put(c0322w2, new C1606gn(new C2964wi(c1606gn.a.a.a(i2, b1), c1606gn.a.b), c1606gn.b, c1606gn.c, c1606gn.d));
        }
    }

    public Map<C0322w2, C0322w2[]> c() {
        return this.j;
    }

    public final AbstractC0706Nu a(Map map, final com.android.tools.r8.graph.B1 b1, final String str, final String str2) {
        final C0629Ku c0629KuE = AbstractC0706Nu.e();
        map.forEach(new BiConsumer() { // from class: bri
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(c0629KuE, b1, str, str2, (I2) obj, (I2) obj2);
            }
        });
        return c0629KuE.b();
    }

    public static boolean a(com.android.tools.r8.graph.I2 i2, C1850jh c1850jh) {
        return c1850jh.b.w0() == i2 || c1850jh.a.w0() == i2;
    }

    public final void a(C0629Ku c0629Ku, com.android.tools.r8.graph.B1 b1, String str, String str2, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        c0629Ku.a(i2, b1.e(i3.Z0().replace(str, str2)));
    }
}
