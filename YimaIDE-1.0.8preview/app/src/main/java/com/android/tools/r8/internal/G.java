package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class G {
    public final int a;
    public List b;
    public List c;
    public G d;
    public G e;
    public int f = -1;

    public G(int i) {
        this.a = i;
    }

    public abstract int a();

    public final G a(G g) {
        if (g.b != null) {
            this.b = new ArrayList();
            int size = g.b.size();
            for (int i = 0; i < size; i++) {
                C2369pj0 c2369pj0 = (C2369pj0) g.b.get(i);
                C2369pj0 c2369pj1 = new C2369pj0(c2369pj0.d, c2369pj0.e, c2369pj0.b);
                c2369pj0.a(c2369pj1);
                this.b.add(c2369pj1);
            }
        }
        if (g.c != null) {
            this.c = new ArrayList();
            int size2 = g.c.size();
            for (int i2 = 0; i2 < size2; i2++) {
                C2369pj0 c2369pj2 = (C2369pj0) g.c.get(i2);
                C2369pj0 c2369pj3 = new C2369pj0(c2369pj2.d, c2369pj2.e, c2369pj2.b);
                c2369pj2.a(c2369pj3);
                this.c.add(c2369pj3);
            }
        }
        return this;
    }

    public abstract G a(RC rc);

    public abstract void a(XO xo);

    public final void b(XO xo) {
        List list = this.b;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                C2369pj0 c2369pj0 = (C2369pj0) this.b.get(i);
                c2369pj0.a(xo.a(c2369pj0.d, c2369pj0.e, c2369pj0.b, true));
            }
        }
        List list2 = this.c;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                C2369pj0 c2369pj1 = (C2369pj0) this.c.get(i2);
                c2369pj1.a(xo.a(c2369pj1.d, c2369pj1.e, c2369pj1.b, false));
            }
        }
    }
}
