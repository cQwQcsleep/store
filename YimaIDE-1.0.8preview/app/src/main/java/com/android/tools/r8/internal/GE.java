package com.android.tools.r8.internal;

import defpackage.kx5;
import java.util.HashMap;
import java.util.IdentityHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GE {
    public final HashMap a = new HashMap();
    public final IdentityHashMap b = new IdentityHashMap();

    public final FE a(HE he) {
        AbstractC2671tG abstractC2671tG = (AbstractC2671tG) this.b.get(he);
        for (EE ee : abstractC2671tG.c()) {
            if (ee.b().equals(he)) {
                kx5.a("Recursive binding for name '", ee);
                return null;
            }
            if (!this.b.containsKey(ee.b())) {
                throw new C2499rF("Undefined binding for binding '" + ee.b() + "' or type '" + (ee.c() ? "class" : "member") + "' referenced in binding of '" + he + "'");
            }
        }
        return new FE(abstractC2671tG);
    }

    public final void a(HE he, AbstractC2671tG abstractC2671tG) {
        if (he != null && abstractC2671tG != null) {
            if (((AbstractC2671tG) this.b.put(he, abstractC2671tG)) == null) {
                return;
            }
            kx5.a("Multiple definitions for binding '", he);
            return;
        }
        kx5.a("Invalid binding of '", he);
    }

    public final IE a() {
        if (this.b.isEmpty()) {
            return IE.b;
        }
        HashMap map = new HashMap(this.b.size());
        for (HE he : this.b.keySet()) {
            HE he2 = (HE) this.a.get(he.toString());
            if (he2 != he) {
                int i = 0;
                while (he2 != null) {
                    i++;
                    he.b = Integer.toString(i);
                    he2 = (HE) this.a.get(he.toString());
                }
                this.a.put(he.toString(), he);
            }
            map.put(he, a(he));
        }
        return new IE(map);
    }

    public final HE a(String str) {
        HE he = new HE(str);
        if (((HE) this.a.put(str, he)) == null) {
            return he;
        }
        kx5.a("Multiple bindings with name '", str);
        return null;
    }
}
