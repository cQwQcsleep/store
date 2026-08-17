package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0256m5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.RJ;
import defpackage.vef;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.m5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0256m5 extends AbstractC0263n5 {
    public static final /* synthetic */ boolean i = true;
    public C0249l5 h;

    public C0256m5(boolean z, com.android.tools.r8.shaking.N0 n0) {
        this.h = new C0249l5(z, n0);
    }

    public final void a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, InterfaceC0189d1 interfaceC0189d1, D2 d2, Set set) {
        I2 i2C = abstractC3148ys.c(abstractC3148ys2, d2.e);
        if (i2C.T0()) {
            return;
        }
        D2 d2B = D2.b(interfaceC0189d1.d(i2C));
        if (!i && d2B == null) {
            x1f.a();
            return;
        }
        if (this.b.contains(d2B)) {
            return;
        }
        Set set2 = (Set) this.a.computeIfAbsent(d2B, new Function() { // from class: elh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2780ub0.c();
            }
        });
        AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
        Set setC = AbstractC2780ub0.c();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            C0231j1 c0231j1 = (C0231j1) it.next();
            c0231j1.getClass();
            boolean z = C0231j1.x;
            if (!z && c0231j1 == C0231j1.v) {
                x1f.a();
                return;
            }
            C0322w2 c0322w2D = abstractC3148ys.d(abstractC3148ysG, c0231j1.getReference());
            E0 e0D = interfaceC0189d1.d(c0322w2D.w0());
            if (!z && e0D == null) {
                x1f.a();
                return;
            }
            C0231j1 c0231j1C = e0D.c(c0322w2D);
            if (!z && c0231j1C == null) {
                x01.a(c0322w2D.m0());
                return;
            }
            setC.add(c0231j1C);
        }
        set2.addAll(setC);
    }

    public final void b(final InterfaceC0189d1 interfaceC0189d1) {
        this.a.forEach(new BiConsumer() { // from class: glh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(interfaceC0189d1, (D2) obj, (Set) obj2);
            }
        });
        this.b.forEach(new Consumer() { // from class: hlh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.d(interfaceC0189d1, (D2) obj);
            }
        });
        this.e.forEach(new BiConsumer() { // from class: ilh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(interfaceC0189d1, (I2) obj, (List) obj2);
            }
        });
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            a(((D2) it.next()).C0(), interfaceC0189d1);
        }
        this.f.forEach(new BiConsumer() { // from class: jlh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((I2) obj, (Set) obj2);
            }
        });
    }

    public final /* synthetic */ void c(InterfaceC0189d1 interfaceC0189d1, D2 d2) {
        a(interfaceC0189d1, (E0) d2);
    }

    public final /* synthetic */ void d(InterfaceC0189d1 interfaceC0189d1, D2 d2) {
        boolean z = i;
        if (!z && d2.isInterface()) {
            x1f.a();
            return;
        }
        if (!z && this.a.containsKey(d2)) {
            x1f.a();
        } else {
            if (z) {
                return;
            }
            a(d2.C0(), interfaceC0189d1);
        }
    }

    public final void h(D2 d2) {
        this.a.remove(d2);
        this.b.remove(d2);
        this.f = null;
    }

    public final /* synthetic */ void b(InterfaceC0189d1 interfaceC0189d1, D2 d2) {
        a(interfaceC0189d1, (E0) d2);
    }

    public final boolean a(D2 d2, B5 b5, int i2, com.android.tools.r8.shaking.D1 d1, C0215h c0215h) {
        boolean z = i;
        if (!z && d2.isInterface()) {
            x1f.a();
            return false;
        }
        com.android.tools.r8.shaking.N0 n0 = this.h.b;
        if (n0 != null) {
            n0.a(d2, d1);
        }
        a((InterfaceC0189d1) c0215h, (E0) d2);
        if (!this.h.a || i2 != 3 || this.b.contains(d2)) {
            return this.b.add(d2) && ((Set) this.a.remove(d2)) == null;
        }
        if (!z && b5 == null) {
            x1f.a();
            return false;
        }
        Set set = (Set) this.a.computeIfAbsent(d2, new Function() { // from class: flh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2780ub0.c();
            }
        });
        set.add(b5.e());
        return set.size() == 1;
    }

    public static /* synthetic */ List a(I2 i2) {
        return new ArrayList();
    }

    public final void a(I2 i2, C1819jJ c1819jJ, C0215h c0215h) {
        ((List) this.e.computeIfAbsent(i2, new Function() { // from class: xkh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0256m5.a((I2) obj);
            }
        })).add(c1819jJ);
        E0 e0D = c0215h.d(i2);
        if (e0D != null) {
            a(c0215h, e0D);
        }
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1) {
        this.f = new IdentityHashMap();
        this.a.keySet().forEach(new Consumer() { // from class: wkh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(interfaceC0189d1, (D2) obj);
            }
        });
        this.b.forEach(new Consumer() { // from class: blh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(interfaceC0189d1, (D2) obj);
            }
        });
        this.d.forEach(new Consumer() { // from class: clh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c(interfaceC0189d1, (D2) obj);
            }
        });
        this.e.keySet().forEach(new Consumer() { // from class: dlh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(interfaceC0189d1, (I2) obj);
            }
        });
    }

    public final /* synthetic */ void a(InterfaceC0189d1 interfaceC0189d1, D2 d2) {
        a(interfaceC0189d1, (E0) d2);
    }

    public final void a(InterfaceC0189d1 interfaceC0189d1, I2 i2) {
        E0 e0D = interfaceC0189d1.d(i2);
        if (e0D != null) {
            a(interfaceC0189d1, e0D);
        }
    }

    public final void a(InterfaceC0189d1 interfaceC0189d1, E0 e0) {
        I2 i2 = e0.g;
        if (i2 != null) {
            a(interfaceC0189d1, i2, e0);
        }
        for (I2 i3 : e0.h.b) {
            a(interfaceC0189d1, i3, e0);
        }
    }

    public final void a(InterfaceC0189d1 interfaceC0189d1, I2 i2, E0 e0) {
        if (i2 == interfaceC0189d1.a().a2) {
            return;
        }
        Set set = (Set) this.f.get(i2);
        if (set != null) {
            set.add(e0);
            return;
        }
        Set setC = AbstractC2780ub0.c();
        setC.add(e0);
        this.f.put(i2, setC);
        E0 e0D = interfaceC0189d1.d(i2);
        if (e0D != null) {
            a(interfaceC0189d1, e0D);
        }
    }

    public final C0256m5 a(AbstractC0263n5 abstractC0263n5, final InterfaceC0189d1 interfaceC0189d1, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2) {
        this.f = null;
        abstractC0263n5.b.forEach(new Consumer() { // from class: ykh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(abstractC3148ys, abstractC3148ys2, interfaceC0189d1, (D2) obj);
            }
        });
        abstractC0263n5.a.forEach(new BiConsumer() { // from class: zkh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(abstractC3148ys, abstractC3148ys2, interfaceC0189d1, (D2) obj, (Set) obj2);
            }
        });
        Iterator it = abstractC0263n5.d.iterator();
        while (it.hasNext()) {
            I2 i2C = abstractC3148ys.c(abstractC3148ys2, ((D2) it.next()).e);
            if (i2C.T0()) {
                if (!i) {
                    x1f.a();
                    return null;
                }
            } else {
                D2 d2B = D2.b(interfaceC0189d1.d(i2C));
                boolean z = i;
                if (!z && d2B == null) {
                    x1f.a();
                    return null;
                }
                if (!z && this.d.contains(d2B)) {
                    x1f.a();
                    return null;
                }
                this.d.add(d2B);
            }
        }
        final RJ rj = new RJ(interfaceC0189d1, abstractC3148ys, abstractC3148ys2);
        abstractC0263n5.e.forEach(new BiConsumer() { // from class: alh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(abstractC3148ys, abstractC3148ys2, rj, (I2) obj, (List) obj2);
            }
        });
        return this;
    }

    public final /* synthetic */ void a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, InterfaceC0189d1 interfaceC0189d1, D2 d2) {
        I2 i2C = abstractC3148ys.c(abstractC3148ys2, d2.e);
        if (i2C.T0()) {
            return;
        }
        D2 d2B = D2.b(interfaceC0189d1.d(i2C));
        if (i || d2B != null) {
            this.b.add(d2B);
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0263n5
    public final void a(Consumer consumer, C0215h c0215h) {
        consumer.accept(this);
        a(c0215h);
    }

    public final /* synthetic */ void a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, RJ rj, I2 i2, List list) {
        I2 i2C = abstractC3148ys.c(abstractC3148ys2, i2);
        if (i2C.T0()) {
            if (i) {
                return;
            }
            x1f.a();
        } else {
            List list2 = (List) this.e.computeIfAbsent(i2C, IM.a(new vef()));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                list2.add(((C1819jJ) it.next()).a(abstractC3148ys, abstractC3148ys2, rj));
            }
        }
    }

    public final /* synthetic */ void a(InterfaceC0189d1 interfaceC0189d1, D2 d2, Set set) {
        boolean z = i;
        if (!z && d2.isInterface()) {
            x1f.a();
            return;
        }
        if (!z && this.b.contains(d2)) {
            x1f.a();
        } else {
            if (z) {
                return;
            }
            a(d2.C0(), interfaceC0189d1);
        }
    }

    public final /* synthetic */ void a(InterfaceC0189d1 interfaceC0189d1, I2 i2, List list) {
        boolean z = i;
        if (!z && list.isEmpty()) {
            x1f.a();
            return;
        }
        E0 e0D = interfaceC0189d1.d(i2);
        if (e0D != null) {
            if (!z && !e0D.isInterface()) {
                x1f.a();
            } else {
                if (z) {
                    return;
                }
                a(e0D.C0(), interfaceC0189d1);
            }
        }
    }

    public final void a(I2 i2, Set set) {
        if (!i && set.isEmpty()) {
            x1f.a();
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            E0 e0 = (E0) it.next();
            if (!i) {
                Iterator it2 = e0.C0().iterator();
                do {
                    if (!it2.hasNext()) {
                        x1f.a();
                        return;
                    }
                } while (i2 != ((I2) it2.next()));
            }
        }
    }

    public final void a(Iterable iterable, InterfaceC0189d1 interfaceC0189d1) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            I2 i2 = (I2) it.next();
            if (!i && i2 != interfaceC0189d1.a().a2 && !this.f.containsKey(i2)) {
                s22.a("Type not found in hierarchy: ", i2);
                return;
            }
        }
    }
}
