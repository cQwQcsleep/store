package com.android.tools.r8.dex;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0284q5;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C0901Vh;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1350dn;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.RJ;
import com.android.tools.r8.internal.Vd0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.C3313b;
import defpackage.f63;
import defpackage.obi;
import defpackage.ubi;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class t0 {
    public static final /* synthetic */ boolean i = true;
    public final int a;
    public final s0 b;
    public final n0 c;
    public final FeatureSplit d;
    public final Vd0 e;
    public final H2 f;
    public C0901Vh g;
    public C0284q5 h;

    public t0(int i2, C0333y c0333y, D2 d2, FeatureSplit featureSplit, Vd0 vd0) {
        this.h = null;
        this.a = i2;
        s0 s0Var = new s0(c0333y);
        this.b = s0Var;
        this.c = new n0(s0Var, c0333y);
        this.d = featureSplit;
        this.e = vd0;
        if (d2 == null) {
            this.f = null;
            return;
        }
        I2 type = d2.getType();
        this.f = c0333y.w().b(type);
        AbstractC0551Hu abstractC0551HuB = c0333y.a.g().b(type);
        if (abstractC0551HuB.size() == 1) {
            c0333y.w().b((I2) abstractC0551HuB.iterator().next());
        } else {
            if (i || abstractC0551HuB.isEmpty()) {
                return;
            }
            x1f.a();
            throw null;
        }
    }

    public static String a(List<String> list) {
        Iterator<String> it = list.iterator();
        String next = it.next();
        if (!Wf0.i(next).endsWith(".dex")) {
            obi.a("Illegal suffix for dex file: `", next, "`.");
            return null;
        }
        String strA = AbstractC0005a.a(4, 0, next);
        int i2 = 2;
        while (it.hasNext()) {
            String next2 = it.next();
            if (!Wf0.i(next2).endsWith(".dex")) {
                obi.a("Illegal suffix for dex file: `", next, "`.");
                return null;
            }
            if (!next2.startsWith(strA)) {
                f63.a("Input filenames lack common prefix.");
                return null;
            }
            int i3 = i2 + 1;
            if (Integer.parseInt(next2.substring(strA.length(), next2.length() - 4)) != i2) {
                f63.a("DEX files are not numbered consecutively.");
                return null;
            }
            i2 = i3;
        }
        return strA;
    }

    public final Set b() {
        return this.b.c;
    }

    public final void c() {
        this.c.a();
    }

    public final HashSet d() {
        HashSet hashSet = new HashSet();
        Iterator it = this.b.c.iterator();
        while (it.hasNext()) {
            boolean zAdd = hashSet.add(((D2) it.next()).e.f.toString());
            if (!i && !zAdd) {
                x1f.a();
                return null;
            }
        }
        return hashSet;
    }

    public int e() {
        return this.a;
    }

    public final C0284q5 f() {
        if (i || this.h != null) {
            return this.h;
        }
        x1f.a();
        return null;
    }

    public final String g() {
        H2 h2 = this.f;
        if (h2 == null) {
            return null;
        }
        return h2.toString();
    }

    public final boolean h() {
        return this.b.c.isEmpty();
    }

    public final boolean i() {
        return a(65536);
    }

    public t0(int i2, C0333y c0333y, FeatureSplit featureSplit) {
        this(i2, c0333y, null, featureSplit, new C1350dn());
    }

    public static IdentityHashMap a(Collection collection, final AbstractC3148ys abstractC3148ys, final C3313b c3313b) {
        final IdentityHashMap identityHashMap = new IdentityHashMap(collection.size());
        collection.forEach(new Consumer() { // from class: xbi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                D2 d2 = (D2) obj;
                identityHashMap.put(d2, C0929Wj.a(abstractC3148ys.a(d2.getType()).Z0(), c3313b));
            }
        });
        return identityHashMap;
    }

    public final void a(C0333y c0333y, int i2, Ch0 ch0, C0284q5 c0284q5) {
        boolean z = i;
        if (!z && !this.c.b()) {
            x1f.a();
            return;
        }
        if (!z && this.h != null) {
            x1f.a();
            return;
        }
        RJ rj = this.c.c;
        s0 s0Var = this.b;
        this.h = new C0284q5(c0333y, c0284q5, rj, s0Var.c, s0Var.d, s0Var.e, s0Var.f, s0Var.g, s0Var.h, s0Var.i, s0Var.j, i2, ch0);
    }

    public final void a(D2 d2) {
        n0 n0Var = this.c;
        d2.a(n0Var.a, n0Var, n0Var.c);
        n0Var.m = null;
        n0Var.l.getClass();
        n0Var.l.getClass();
    }

    public final boolean a(int i2) {
        n0 n0Var = this.c;
        if (n0Var.b.f.size() + n0Var.f.size() > i2) {
            return true;
        }
        n0 n0Var2 = this.c;
        return n0Var2.b.g.size() + n0Var2.e.size() > i2;
    }

    public final void a() {
        n0 n0Var = this.c;
        n0Var.d.clear();
        n0Var.e.clear();
        n0Var.f.clear();
        n0Var.h.clear();
        n0Var.g.clear();
        n0Var.i.clear();
        n0Var.j.clear();
        n0Var.k.clear();
        n0Var.l.getClass();
    }

    public static boolean a(E2 e2, Map map, Map map2, Consumer consumer, B1 b1) {
        if (map.containsKey(e2)) {
            return false;
        }
        String strO0 = e2.o0();
        Objects.requireNonNull(b1);
        H2 h2 = (H2) map2.computeIfAbsent(strO0, new ubi(b1));
        consumer.accept(h2);
        map.put(e2, h2);
        return true;
    }
}
