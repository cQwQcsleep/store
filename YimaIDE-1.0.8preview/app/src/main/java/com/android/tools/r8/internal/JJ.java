package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.r2i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class JJ {
    public final Map a;
    public final Map b;
    public final Map c;
    public final Map d;
    public final Map e;
    public final List f;
    public final Set g;
    public final Set h;

    public JJ(AbstractC0706Nu abstractC0706Nu, AbstractC0706Nu abstractC0706Nu2, AbstractC0706Nu abstractC0706Nu3, AbstractC0706Nu abstractC0706Nu4, AbstractC0706Nu abstractC0706Nu5, AbstractC0551Hu abstractC0551Hu, AbstractC2554rv abstractC2554rv, AbstractC2554rv abstractC2554rv2) {
        this.a = abstractC0706Nu;
        this.b = abstractC0706Nu2;
        this.c = abstractC0706Nu3;
        this.d = abstractC0706Nu4;
        this.e = abstractC0706Nu5;
        this.f = abstractC0551Hu;
        this.g = abstractC2554rv;
        this.h = abstractC2554rv2;
    }

    public static a a(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, Origin origin) {
        return new a(b1, c2742u50, origin, new HashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new IdentityHashMap(), new ArrayList(), AbstractC2780ub0.c(), AbstractC2780ub0.c());
    }

    public static class a {
        public static final /* synthetic */ boolean l = true;
        public final com.android.tools.r8.graph.B1 a;
        public final C2742u50 b;
        public final Origin c;
        public final HashMap d;
        public final IdentityHashMap e;
        public final IdentityHashMap f;
        public final IdentityHashMap g;
        public final IdentityHashMap h;
        public final ArrayList i;
        public final Set j;
        public final Set k;

        public a(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, Origin origin, Map map, Map map2, Map map3, Map map4, Map map5, List list, Set set, Set set2) {
            this.a = b1;
            this.b = c2742u50;
            this.c = origin;
            this.d = new HashMap(map);
            this.e = new IdentityHashMap(map2);
            this.f = new IdentityHashMap(map3);
            this.g = new IdentityHashMap(map4);
            this.h = new IdentityHashMap(map5);
            this.i = new ArrayList(list);
            Set setC = AbstractC2780ub0.c();
            this.j = setC;
            setC.addAll(set);
            Set setC2 = AbstractC2780ub0.c();
            this.k = setC2;
            setC2.addAll(set2);
        }

        public static int d(String str, String str2) {
            int iLastIndexOf = str.lastIndexOf(35);
            if (iLastIndexOf > 0 && iLastIndexOf < str.length() - 1) {
                return iLastIndexOf;
            }
            throw new C0613Ke("Invalid " + str2 + " specification (# position) in " + str + ".");
        }

        public JJ a() {
            b();
            return new JJ(AbstractC0706Nu.a(this.d), AbstractC0706Nu.a(this.e), AbstractC0706Nu.a(this.f), AbstractC0706Nu.a(this.g), AbstractC0706Nu.a(this.h), AbstractC0551Hu.a(this.i), AbstractC2554rv.a(this.j), AbstractC2554rv.a(this.k));
        }

        public final void b(String str, String str2) {
            int iD = d(str, "retarget core library member");
            com.android.tools.r8.graph.H2 h2C = this.a.c(str.substring(iD + 1));
            this.f.putIfAbsent(h2C, new IdentityHashMap());
            Map map = (Map) this.f.get(h2C);
            com.android.tools.r8.graph.I2 i2E = this.a.e(C0929Wj.I(str.substring(0, iD)));
            com.android.tools.r8.graph.I2 i2E2 = this.a.e(C0929Wj.I(str2));
            if (l || !map.containsKey(i2E)) {
                a(map, i2E, i2E2, "retarget_lib_member");
            } else {
                x1f.a();
            }
        }

        public a c(String str, String str2) {
            a(this.d, str, str2, "rewrite_prefix");
            return this;
        }

        public a a(String str, String str2) {
            a(this.g, this.a.e(C0929Wj.I(str)), this.a.e(C0929Wj.I(str2)), "backport");
            return this;
        }

        public final void a(Map map, Object obj, Object obj2, String str) {
            if (!map.containsKey(obj)) {
                map.put(obj, obj2);
                return;
            }
            this.b.a(new StringDiagnostic("Invalid desugared library configuration.  Duplicate assignment of key: '" + obj + "' in sections for '" + str + "'", this.c));
            throw null;
        }

        public final void b() {
            C1924kb0 c1924kb0A = AbstractC2780ub0.a(this.h.keySet(), this.k);
            if (c1924kb0A.isEmpty()) {
                return;
            }
            this.b.a(new StringDiagnostic("Invalid desugared library configuration. Duplicate types in custom conversions and wrapper conversions: " + String.join(", ", (Iterable<? extends CharSequence>) c1924kb0A.stream().map(new r2i()).collect(Collectors.toSet())), this.c));
            throw null;
        }
    }
}
