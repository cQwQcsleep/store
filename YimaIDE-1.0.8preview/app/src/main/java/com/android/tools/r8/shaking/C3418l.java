package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.I5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.C1097an;
import com.android.tools.r8.internal.C4;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.shaking.C3418l;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3418l {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public C3418l(IdentityHashMap identityHashMap) {
        if (b || identityHashMap.values().stream().noneMatch(new Predicate() { // from class: oih
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C4) obj).b();
            }
        })) {
            this.a = identityHashMap;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static void a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, C0333y c0333y, Map map, AbstractC0287r2 abstractC0287r2, C4 c4) {
        C0245l1 c0245l1;
        C0245l1 c0245l1D;
        abstractC3148ys.getClass();
        com.android.tools.r8.graph.E eD = abstractC0287r2.s0() ? abstractC3148ys.d(abstractC3148ys2, abstractC0287r2.o0()) : abstractC3148ys.d(abstractC3148ys2, abstractC0287r2.q0());
        if (!C4.e && !c4.a.j() && !c4.a.l()) {
            x1f.a();
            return;
        }
        if (c4.b.K() && (c0245l1D = abstractC3148ys.d(AbstractC3148ys.g(), (c0245l1 = c4.b.r().b))) != c0245l1) {
            com.android.tools.r8.internal.C1 c1 = c0333y.t;
            C1097an c1097an = C1097an.a;
            c1.getClass();
            c4 = C4.a(c4.a, com.android.tools.r8.internal.C1.a(c0245l1D, c1097an), c4.c);
        }
        if (b || !c4.b()) {
            map.put(eD, c4);
        } else {
            x1f.a();
        }
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C3418l a(final C0333y c0333y, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        this.a.forEach(new BiConsumer() { // from class: jih
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3418l.a(abstractC3148ys, abstractC3148ys2, c0333y, identityHashMap, (AbstractC0287r2) obj, (C4) obj2);
            }
        });
        return new C3418l(identityHashMap);
    }

    public final C4 a(AbstractC0287r2 abstractC0287r2) {
        return (C4) this.a.getOrDefault(abstractC0287r2, C4.d);
    }

    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.F0 f0) {
        com.android.tools.r8.internal.B1 b1 = a(f0.getReference()).b;
        return b1.g() && b1.j().a(c0333y);
    }

    public final C3418l a(final C0333y c0333y, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2, Ch0 ch0) {
        return (C3418l) ch0.a("Rewrite AssumeInfoCollection", new InterfaceC2706th0() { // from class: rih
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(c0333y, abstractC3148ys, abstractC3148ys2);
            }
        });
    }

    public static C3413k a() {
        return new C3413k();
    }

    public final C3418l a(final I5 i5, Ch0 ch0) {
        ch0.a("Prune AssumeInfoCollection");
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        this.a.forEach(new BiConsumer() { // from class: tih
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3418l.a(i5, identityHashMap, (AbstractC0287r2) obj, (C4) obj2);
            }
        });
        C3418l c3418l = new C3418l(identityHashMap);
        ch0.b();
        return c3418l;
    }

    public static void a(I5 i5, Map map, AbstractC0287r2 abstractC0287r2, C4 c4) {
        if (i5.a(abstractC0287r2)) {
            return;
        }
        if (!C4.e && !c4.a.j() && !c4.a.l()) {
            x1f.a();
            return;
        }
        if (c4.b.K() && i5.a(c4.b.r().b)) {
            c4 = C4.a(c4.a, Ak0.a, c4.c);
        }
        if (c4.b()) {
            return;
        }
        map.put(abstractC0287r2, c4);
    }
}
