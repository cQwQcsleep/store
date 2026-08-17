package com.android.tools.r8.naming;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.C5;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.Y3;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.shaking.C3403i;
import defpackage.eug;
import defpackage.ulg;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.naming.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class C3321f {
    public static final /* synthetic */ boolean k = true;
    public final C0333y a;
    public final InterfaceC3315c b;
    public final Iterable c;
    public final HashSet d;
    public final IdentityHashMap e;
    public final HashMap f;
    public final boolean g;
    public final C3319e h;
    public final boolean i;
    public final Predicate j;

    public C3321f(C0333y c0333y, C3330j0 c3330j0, Collection collection) {
        HashSet hashSet = new HashSet();
        this.d = hashSet;
        this.e = new IdentityHashMap();
        HashMap map = new HashMap();
        this.f = map;
        this.a = c0333y;
        this.b = c3330j0;
        this.c = collection;
        C2752uB c2752uBM = c0333y.M();
        this.g = c2752uBM.h0();
        C3319e c3319e = new C3319e(this, XmlPullParser.NO_NAMESPACE, String.valueOf(DataResource.SEPARATOR));
        this.h = c3319e;
        map.put(XmlPullParser.NO_NAMESPACE, c3319e);
        if (c2752uBM.H().J) {
            this.i = false;
            this.j = new Predicate() { // from class: com.android.tools.r8.naming.e1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.c((String) obj);
                }
            };
        } else {
            this.i = true;
            this.j = new ulg(hashSet);
        }
    }

    public final C3317d a(Ch0 ch0) {
        ch0.a("reserve");
        for (C5 c5 : this.c) {
            H2 h2B = this.b.b(c5.getType());
            if (h2B != null) {
                if (!k && this.e.containsKey(c5.getType())) {
                    x1f.a();
                    return null;
                }
                a(h2B, c5.getType());
            }
        }
        ((C3403i) this.a.g()).h.a(new Consumer() { // from class: com.android.tools.r8.naming.d1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((I2) obj);
            }
        });
        ch0.b();
        ch0.a("rename-classes");
        for (com.android.tools.r8.graph.S s : this.c) {
            if (!this.e.containsKey(s.getType())) {
                H2 h2A = a(s.getType());
                this.e.put(s.getType(), h2A);
                if (!k) {
                    a((com.android.tools.r8.graph.E0) s, h2A);
                }
            }
        }
        ch0.b();
        ch0.a("rename-dangling-types");
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a((C5) it.next());
        }
        ch0.b();
        Map mapUnmodifiableMap = Collections.unmodifiableMap(this.e);
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        for (Map.Entry entry : this.f.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = ((C3319e) entry.getValue()).b;
            if (!str2.equals(str)) {
                c0629KuE.a(str, str2);
            }
        }
        return new C3317d(mapUnmodifiableMap, c0629KuE.b());
    }

    public final C3319e b(I2 i2) {
        return (C3319e) this.f.computeIfAbsent(C0929Wj.o(i2.D0()), new Function() { // from class: com.android.tools.r8.naming.c1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((String) obj);
            }
        });
    }

    public final /* synthetic */ boolean c(String str) {
        return this.d.contains(Wf0.i(str));
    }

    public final void d(I2 i2) {
        if (!((C3403i) this.a.g()).l(i2) || this.e.containsKey(i2)) {
            return;
        }
        if (!k && this.a.d(i2) != null) {
            x1f.a();
            return;
        }
        H2 h2B = this.b.b(i2);
        IdentityHashMap identityHashMap = this.e;
        if (h2B == null) {
            C3319e c3319e = this.h;
            C3321f c3321f = c3319e.f;
            h2B = c3321f.b.a(i2, c3319e.c, c3319e, c3321f.j);
            if (!C3319e.g && c3319e.f.d.contains(h2B.toString())) {
                x1f.a();
                return;
            }
            C3321f c3321f2 = c3319e.f;
            String string = h2B.toString();
            HashSet hashSet = c3321f2.d;
            if (!c3321f2.i) {
                string = Wf0.i(string);
            }
            hashSet.add(string);
        }
        identityHashMap.put(i2, h2B);
    }

    public final /* synthetic */ void c(I2 i2) {
        a(i2.z0(), i2);
    }

    public final C3319e b(String str) {
        return new C3319e(this, str, String.valueOf(DataResource.SEPARATOR));
    }

    public final void a(com.android.tools.r8.graph.E0 e0, H2 h2) {
        if (k || !this.g || !e0.v1() || !e0.getType().A0().contains(String.valueOf('$')) || h2.toString().contains(String.valueOf('$')) || this.b.a(e0.getType())) {
            return;
        }
        eug.a(e0, " -> ", h2);
    }

    public final void a(C5 c5) {
        c5.c(new Consumer() { // from class: com.android.tools.r8.naming.a1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((com.android.tools.r8.graph.H0) obj);
            }
        });
        c5.a(new Consumer() { // from class: com.android.tools.r8.naming.b1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((com.android.tools.r8.graph.F0) obj);
            }
        });
    }

    public final void a(com.android.tools.r8.graph.F0 f0) {
        d(f0.getReference().i);
    }

    public final void a(com.android.tools.r8.graph.H0 h0) {
        E2 e2 = h0.getReference().i;
        d(e2.e);
        for (I2 i2 : e2.f.b) {
            d(i2);
        }
    }

    public final void a(H2 h2, I2 i2) {
        Y3 y3S0;
        this.e.put(i2, h2);
        String string = h2.toString();
        HashSet hashSet = this.d;
        if (!this.i) {
            string = Wf0.i(string);
        }
        hashSet.add(string);
        if (this.g) {
            com.android.tools.r8.graph.E0 e0D = this.a.d(i2);
            I2 i2A = (e0D == null || (y3S0 = e0D.S0()) == null) ? null : y3S0.a(this.a);
            if (i2A == null || this.e.containsKey(i2A) || this.b.b(i2A) != null) {
                return;
            }
            a(i2A.f, i2A);
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0088  */
    public final H2 a(I2 i2) {
        C3319e c3319eB;
        Y3 y3S0;
        if (this.g) {
            com.android.tools.r8.graph.E0 e0D = this.a.d(i2);
            I2 i2A = (e0D == null || (y3S0 = e0D.S0()) == null) ? null : y3S0.a(this.a);
            if (i2A != null) {
                com.android.tools.r8.graph.E0 e0D2 = this.a.d(i2);
                boolean z = k;
                if (!z && e0D2 == null) {
                    x1f.a();
                    return null;
                }
                Y3 y3S1 = e0D2.S0();
                if (!z && y3S1 == null) {
                    x1f.a();
                    return null;
                }
                String strA = C0929Wj.a(y3S1.d(), i2, y3S1.c());
                if (strA == null) {
                    strA = String.valueOf('$');
                }
                String strI = C0929Wj.i(i2A.Z0());
                c3319eB = (C3319e) this.f.get(strI);
                if (c3319eB == null) {
                    H2 h2A = (H2) this.e.get(i2A);
                    if (h2A == null) {
                        h2A = a(i2A);
                        this.e.put(i2A, h2A);
                    }
                    c3319eB = new C3319e(this, C0929Wj.i(h2A.toString()), strA);
                    this.f.put(strI, c3319eB);
                }
            } else {
                c3319eB = null;
            }
        } else {
            c3319eB = null;
        }
        if (c3319eB == null) {
            c3319eB = b(i2);
        }
        C3321f c3321f = c3319eB.f;
        H2 h2A2 = c3321f.b.a(i2, c3319eB.c, c3319eB, c3321f.j);
        if (!C3319e.g && c3319eB.f.d.contains(h2A2.toString())) {
            x1f.a();
            return null;
        }
        C3321f c3321f2 = c3319eB.f;
        String string = h2A2.toString();
        HashSet hashSet = c3321f2.d;
        if (!c3321f2.i) {
            string = Wf0.i(string);
        }
        hashSet.add(string);
        return h2A2;
    }

    public static String a(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            return XmlPullParser.NO_NAMESPACE;
        }
        return str.substring(0, iLastIndexOf);
    }
}
