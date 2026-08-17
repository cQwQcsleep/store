package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0205f3;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import defpackage.f44;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2934wM {
    public static final /* synthetic */ boolean s = true;
    public final IdentityHashMap a = new IdentityHashMap();
    public final C1870jv b;
    public final IdentityHashMap c;
    public final C0629Ku d;
    public final C0629Ku e;
    public final C0629Ku f;
    public final C0629Ku g;
    public final C0629Ku h;
    public final C0629Ku i;
    public final C0629Ku j;
    public final C0629Ku k;
    public final LinkedHashMap l;
    public final C0629Ku m;
    public final C1870jv n;
    public final C0629Ku o;
    public final C1870jv p;
    public final C0629Ku q;
    public final C0629Ku r;

    public C2934wM() {
        int i = AbstractC2554rv.c;
        this.b = new C1870jv();
        this.c = new IdentityHashMap();
        this.d = AbstractC0706Nu.e();
        this.e = AbstractC0706Nu.e();
        this.f = AbstractC0706Nu.e();
        this.g = AbstractC0706Nu.e();
        this.h = AbstractC0706Nu.e();
        this.i = AbstractC0706Nu.e();
        this.j = AbstractC0706Nu.e();
        this.k = AbstractC0706Nu.e();
        this.l = new LinkedHashMap();
        this.m = AbstractC0706Nu.e();
        this.n = new C1870jv();
        this.o = AbstractC0706Nu.e();
        this.p = new C1870jv();
        this.q = AbstractC0706Nu.e();
        this.r = AbstractC0706Nu.e();
    }

    public final C3020xM a() {
        AbstractC2554rv abstractC2554rvA = this.b.a();
        a(abstractC2554rvA);
        return new C3020xM(this.a, abstractC2554rvA, this.c, this.d.b(), this.e.b(), this.f.b(), this.g.b(), this.h.b(), this.i.b(), this.j.b(), this.k.b(), this.l, this.m.b(), this.n.a(), this.o.b(), this.p.a(), this.q.b(), this.r.b());
    }

    public final void b(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        boolean z = s;
        if (!z && i2 == null) {
            x1f.a();
            return;
        }
        if (!z && i3 == null) {
            x1f.a();
            return;
        }
        if (!z && i2 == i3) {
            x1f.a();
        } else if (z || !this.a.containsKey(i2) || this.a.get(i2) == i3) {
            this.a.put(i2, i3);
        } else {
            x1f.a();
        }
    }

    public final void b(C0322w2 c0322w2, C0322w2 c0322w3) {
        this.f.a(c0322w2, c0322w3);
    }

    public final void a(C0322w2 c0322w2, C0322w2[] c0322w2Arr) {
        this.j.a(c0322w2, c0322w2Arr);
    }

    public final void a(C0322w2 c0322w2) {
        this.p.a(c0322w2);
    }

    public final void a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        this.m.a(i2, i3);
    }

    public final void a(com.android.tools.r8.graph.I2 i2) {
        this.n.a(i2);
    }

    public final void a(C0322w2 c0322w2, com.android.tools.r8.graph.F4 f4) {
        this.q.a(c0322w2, f4);
    }

    public final void a(C0245l1 c0245l1, C0205f3 c0205f3) {
        this.r.a(c0245l1, c0205f3);
    }

    public final void a(AbstractC2554rv abstractC2554rv) {
        ArrayList arrayList = new ArrayList();
        for (com.android.tools.r8.graph.I2 i2 : this.a.keySet()) {
            if (abstractC2554rv.contains(i2)) {
                arrayList.add(i2);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        f44.a("The compilation cannot proceed because the desugared library specification contains ambiguous flags that the compiler cannot interpret: The following types are both rewritten and maintained ", arrayList);
    }

    public final void a(C0322w2 c0322w2, C0322w2 c0322w3) {
        this.e.a(c0322w2, c0322w3);
    }
}
