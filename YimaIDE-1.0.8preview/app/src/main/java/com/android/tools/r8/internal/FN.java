package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.FN;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FN extends AbstractC1511fi {
    public static final /* synthetic */ boolean h = true;
    public final Map f;
    public final Map g;

    public FN(Map map, Map map2, C0333y c0333y, AbstractC3148ys abstractC3148ys) {
        super(c0333y, abstractC3148ys);
        this.f = map;
        this.g = map2;
    }

    public static void a(Deque deque, AbstractC3148ys abstractC3148ys, XR xr, com.android.tools.r8.graph.B1 b1, EN en, C0322w2 c0322w2, C0322w2 c0322w3) {
        Iterator it = deque.iterator();
        while (it.hasNext()) {
            XR xr2 = (XR) it.next();
            xr2.getClass();
            c0322w3 = xr2 instanceof Vl0 ? xr2.e().h(c0322w3) : xr2.e(c0322w3);
        }
        C0322w2 c0322w2A = c0322w3.a(abstractC3148ys.c(xr, c0322w2.w0()), b1);
        if (c0322w2A.a(c0322w3)) {
            return;
        }
        if (!EN.e) {
            en.getClass();
            if (c0322w2A.a(c0322w3)) {
                x1f.a();
                return;
            }
        }
        en.d.put(c0322w2A, c0322w3);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C2035lp b(C2035lp c2035lp) {
        boolean z = h;
        if (!z && c2035lp.d()) {
            x1f.a();
            return null;
        }
        if (!z && c2035lp.b()) {
            x1f.a();
            return null;
        }
        AbstractC0287r2 abstractC0287r2 = c2035lp.a;
        C0245l1 c0245l1 = (C0245l1) abstractC0287r2;
        C0245l1 c0245l2 = (C0245l1) abstractC0287r2;
        return new C2035lp(c0245l1, (C0245l1) this.f.getOrDefault(c0245l2, c0245l2), null, null);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final FN c() {
        return this;
    }

    public final FN a(C0333y c0333y, final AbstractC3148ys abstractC3148ys, final XR xr, AbstractC3148ys abstractC3148ys2) {
        final com.android.tools.r8.graph.B1 b1A = c0333y.a();
        final EN en = new EN(c0333y, abstractC3148ys2, new IdentityHashMap(), new IdentityHashMap());
        this.f.forEach(new BiConsumer() { // from class: ol4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FN.a(abstractC3148ys, xr, b1A, en, (C0245l1) obj, (C0245l1) obj2);
            }
        });
        final ArrayDeque arrayDeque = new ArrayDeque();
        AbstractC3148ys abstractC3148ys3 = abstractC3148ys;
        while (abstractC3148ys3.n()) {
            XR xrD = abstractC3148ys3.d();
            arrayDeque.addFirst(xrD);
            abstractC3148ys3 = xrD.d;
        }
        this.g.forEach(new BiConsumer() { // from class: pl4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FN.a(arrayDeque, abstractC3148ys, xr, b1A, en, (C0322w2) obj, (C0322w2) obj2);
            }
        });
        return new FN(en.c, en.d, en.a, en.b);
    }

    public static void a(AbstractC3148ys abstractC3148ys, XR xr, com.android.tools.r8.graph.B1 b1, EN en, C0245l1 c0245l1, C0245l1 c0245l2) {
        C0245l1 c0245l1E = abstractC3148ys.e(xr, c0245l2);
        C0245l1 c0245l1A = c0245l1E.a(abstractC3148ys.c(xr, c0245l1.w0()), b1);
        if (c0245l1A.a(c0245l1E)) {
            return;
        }
        if (!EN.e) {
            en.getClass();
            if (c0245l1A.a(c0245l1E)) {
                x1f.a();
                return;
            }
        }
        en.c.put(c0245l1A, c0245l1E);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C2850vO a(C2850vO c2850vO, C0322w2 c0322w2, AbstractC3148ys abstractC3148ys) {
        boolean z = h;
        if (!z && c2850vO.b != null) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.proto.j jVar = com.android.tools.r8.graph.proto.j.d;
        C0322w2 c0322w3 = (C0322w2) c2850vO.a;
        C0322w2 c0322w4 = (C0322w2) this.g.get(c0322w3);
        if (!z && c0322w3.a(c0322w4)) {
            x1f.a();
            return null;
        }
        C0322w2 c0322w5 = c0322w3;
        while (c0322w4 != null) {
            C0322w2 c0322w6 = c0322w4;
            c0322w4 = (C0322w2) this.g.get(c0322w4);
            c0322w5 = c0322w6;
        }
        return new C2850vO(c0322w3, c0322w5, c2850vO.c, c2850vO.d).a(this);
    }
}
