package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.C2552rt;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2552rt {
    public final Map a;
    public final Set b;
    public final Set c;
    public final Map d;
    public final Map e;
    public final Map f;
    public final Map g;
    public final Map h;
    public final Map i;
    public final Map j;
    public final Map k;
    public final Map l;
    public final Map m;
    public final Map n;
    public final Set o;
    public final Map p;
    public final Set q;
    public final Map r;
    public final Map s;

    public C2552rt(AbstractC0706Nu abstractC0706Nu, Set set, Set set2, AbstractC0706Nu abstractC0706Nu2, AbstractC0706Nu abstractC0706Nu3, AbstractC0706Nu abstractC0706Nu4, AbstractC0706Nu abstractC0706Nu5, AbstractC0706Nu abstractC0706Nu6, AbstractC0706Nu abstractC0706Nu7, AbstractC0706Nu abstractC0706Nu8, AbstractC0706Nu abstractC0706Nu9, AbstractC0706Nu abstractC0706Nu10, AbstractC0706Nu abstractC0706Nu11, AbstractC0706Nu abstractC0706Nu12, Set set3, AbstractC0706Nu abstractC0706Nu13, Set set4, AbstractC0706Nu abstractC0706Nu14, AbstractC0706Nu abstractC0706Nu15) {
        this.a = abstractC0706Nu;
        this.b = set;
        this.c = set2;
        this.d = abstractC0706Nu2;
        this.e = abstractC0706Nu3;
        this.f = abstractC0706Nu4;
        this.g = abstractC0706Nu5;
        this.h = abstractC0706Nu6;
        this.i = abstractC0706Nu7;
        this.j = abstractC0706Nu8;
        this.k = abstractC0706Nu9;
        this.l = abstractC0706Nu10;
        this.m = abstractC0706Nu11;
        this.n = abstractC0706Nu12;
        this.o = set3;
        this.p = abstractC0706Nu13;
        this.q = set4;
        this.r = abstractC0706Nu14;
        this.s = abstractC0706Nu15;
    }

    public static a a(C2742u50 c2742u50, Origin origin) {
        return new a(c2742u50, origin, new HashMap(), AbstractC2780ub0.c(), AbstractC2780ub0.c(), new HashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), AbstractC2780ub0.c(), new IdentityHashMap(), AbstractC2780ub0.c(), new IdentityHashMap(), new IdentityHashMap());
    }

    public final a b(C2742u50 c2742u50, Origin origin) {
        return new a(c2742u50, origin, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s);
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> c() {
        return this.n;
    }

    public Set<com.android.tools.r8.graph.I2> d() {
        return this.o;
    }

    public Map<com.android.tools.r8.graph.I2, C2637st> e() {
        return this.e;
    }

    public Map<com.android.tools.r8.graph.I2, com.android.tools.r8.graph.I2> f() {
        return this.m;
    }

    public Map<C0322w2, com.android.tools.r8.graph.I2> g() {
        return this.h;
    }

    public Map<String, Map<String, String>> h() {
        return this.d;
    }

    public Map<String, String> i() {
        return this.a;
    }

    public Map<com.android.tools.r8.graph.I2, Set<C0322w2>> j() {
        return this.p;
    }

    public final boolean k() {
        return this.a.isEmpty() && this.d.isEmpty() && this.c.isEmpty() && this.e.isEmpty() && this.g.isEmpty() && this.h.isEmpty() && this.i.isEmpty() && this.f.isEmpty();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.rt$a */
    public static class a {
        public static final /* synthetic */ boolean v = true;
        public final C2742u50 a;
        public final Origin b;
        public final HashMap c;
        public final HashSet d;
        public final HashSet e;
        public final HashMap f;
        public final IdentityHashMap g;
        public final IdentityHashMap h;
        public final IdentityHashMap i;
        public final IdentityHashMap j;
        public final IdentityHashMap k;
        public final IdentityHashMap l;
        public final IdentityHashMap m;
        public final IdentityHashMap n;
        public final IdentityHashMap o;
        public final IdentityHashMap p;
        public final Set q;
        public final IdentityHashMap r;
        public final Set s;
        public final IdentityHashMap t;
        public final IdentityHashMap u;

        public a(C2742u50 c2742u50, Origin origin, Map map, Set set, Set set2, Map map2, Map map3, Map map4, Map map5, Map map6, Map map7, Map map8, Map map9, Map map10, Map map11, Map map12, Set set3, Map map13, Set set4, Map map14, Map map15) {
            this.a = c2742u50;
            this.b = origin;
            this.c = new HashMap(map);
            this.d = AbstractC2780ub0.a((Iterable) set);
            this.e = AbstractC2780ub0.a((Iterable) set2);
            this.f = new HashMap(map2);
            this.g = new IdentityHashMap(map3);
            this.h = new IdentityHashMap(map4);
            this.i = new IdentityHashMap(map5);
            this.j = new IdentityHashMap(map6);
            this.k = new IdentityHashMap(map7);
            this.l = new IdentityHashMap(map8);
            this.m = new IdentityHashMap(map9);
            this.n = new IdentityHashMap(map10);
            this.o = new IdentityHashMap(map11);
            this.p = new IdentityHashMap(map12);
            Set setC = AbstractC2780ub0.c();
            this.q = setC;
            setC.addAll(set3);
            this.r = new IdentityHashMap(map13);
            Set setC2 = AbstractC2780ub0.c();
            this.s = setC2;
            setC2.addAll(set4);
            this.t = new IdentityHashMap(map14);
            this.u = new IdentityHashMap(map15);
        }

        public C2552rt a() {
            return new C2552rt(AbstractC0706Nu.a(this.c), AbstractC2554rv.a(this.d), AbstractC2554rv.a(this.e), AbstractC0706Nu.a(this.f), AbstractC0706Nu.a(this.g), AbstractC0706Nu.a(this.h), AbstractC0706Nu.a(this.i), AbstractC0706Nu.a(this.j), AbstractC0706Nu.a(this.k), AbstractC0706Nu.a(this.l), AbstractC0706Nu.a(this.m), AbstractC0706Nu.a(this.n), AbstractC0706Nu.a(this.o), AbstractC0706Nu.a(this.p), AbstractC2554rv.a(this.q), AbstractC0706Nu.a(this.r), AbstractC2554rv.a(this.s), AbstractC0706Nu.a(this.t), AbstractC0706Nu.a(this.u));
        }

        public final a b(com.android.tools.r8.graph.I2 i2) {
            this.r.put(i2, Collections.EMPTY_SET);
            return this;
        }

        public a b(String str) {
            this.e.add(str);
            return this;
        }

        public final a b(C0322w2 c0322w2, com.android.tools.r8.graph.I2 i2) {
            a(this.j, c0322w2, i2, "retarget_method");
            return this;
        }

        public final a b(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
            a(this.o, i2, i3, "backport");
            return this;
        }

        public a a(String str, String str2) {
            a(this.c, str, str2, "rewrite_prefix");
            return this;
        }

        public final void a(String str, String str2, String str3) {
            a((Map) this.f.computeIfAbsent(str, new Function() { // from class: p8i
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2552rt.a.a((String) obj);
                }
            }), str2, str3, "rewrite_derived_prefix");
        }

        public static /* synthetic */ Map a(String str) {
            return new HashMap();
        }

        public final a a(com.android.tools.r8.graph.I2 i2, C2637st c2637st) {
            if (!v && c2637st == null) {
                x1f.a();
                return null;
            }
            C2637st c2637st2 = (C2637st) this.g.get(i2);
            if (c2637st2 != null) {
                c2637st = c2637st.a(c2637st2);
            }
            this.g.put(i2, c2637st);
            return this;
        }

        public final a a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
            a(this.p, i2, i3, "custom_conversion");
            return this;
        }

        public final a a(C0322w2 c0322w2, com.android.tools.r8.graph.I2 i2) {
            a(this.k, c0322w2, i2, "retarget_method_with_emulated_dispatch");
            return this;
        }

        public a a(C0245l1 c0245l1, C0245l1 c0245l2) {
            a(this.h, c0245l1, c0245l2, "retarget_static_field");
            return this;
        }

        public final void a(final C0322w2 c0322w2, int i, C0322w2 c0322w3) {
            C0322w2[] c0322w2Arr = (C0322w2[]) this.n.computeIfAbsent(c0322w2, new Function() { // from class: q8i
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2552rt.a.a(c0322w2, (C0322w2) obj);
                }
            });
            if (i == -1) {
                i = c0322w2.A0();
            }
            if (v || c0322w2Arr[i] == null) {
                c0322w2Arr[i] = c0322w3;
            } else {
                x1f.a();
            }
        }

        public static /* synthetic */ C0322w2[] a(C0322w2 c0322w2, C0322w2 c0322w3) {
            return new C0322w2[c0322w2.A0() + 1];
        }

        public final a a(com.android.tools.r8.graph.I2 i2) {
            this.q.add(i2);
            return this;
        }

        public final void a(C0322w2 c0322w2, com.android.tools.r8.graph.F4 f4) {
            this.t.put(c0322w2, f4);
        }

        public a a(C0245l1 c0245l1, C0205f3 c0205f3) {
            this.u.put(c0245l1, c0205f3);
            return this;
        }

        public final void a(Map map, Object obj, Object obj2, String str) {
            if (map.containsKey(obj) && !map.get(obj).equals(obj2)) {
                this.a.a(new StringDiagnostic("Invalid desugared library configuration.  Duplicate assignment of key: '" + obj + "' in sections for '" + str + "'", this.b));
                throw null;
            }
            map.put(obj, obj2);
        }
    }

    public Map<C0322w2, com.android.tools.r8.graph.F4> b() {
        return this.r;
    }

    public Map<C0245l1, C0205f3> a() {
        return this.s;
    }
}
