package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.graph.InterfaceC0265o0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.InterfaceC1853ji0;
import com.android.tools.r8.shaking.C;
import com.android.tools.r8.shaking.C3375c1;
import com.android.tools.r8.shaking.C3430n1;
import com.android.tools.r8.shaking.V1;
import com.android.tools.r8.shaking.X0;
import com.android.tools.r8.shaking.Z;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class C {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public C() {
        this.a = new ConcurrentHashMap();
    }

    public static boolean a(InterfaceC0189d1 interfaceC0189d1, M m, Z z, V1 v1) {
        if (z.b()) {
            com.android.tools.r8.graph.E0 e0D = interfaceC0189d1.d(z.a().a);
            if (e0D == null || !m.a((InterfaceC0265o0) e0D)) {
                return true;
            }
        } else if (!b && !(z instanceof Y)) {
            x1f.a();
            return false;
        }
        if (b || !v1.a.isEmpty()) {
            v1.a(interfaceC0189d1, m);
            return v1.a.isEmpty();
        }
        x1f.a();
        return false;
    }

    public abstract V1 a();

    public final V1 b(V1 v1) {
        return (V1) this.a.getOrDefault(Y.a, v1);
    }

    public C(int i) {
        this.a = new HashMap(i);
    }

    public final V1 b() {
        return a(Y.a);
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1, final InterfaceC1853ji0 interfaceC1853ji0, final InterfaceC1853ji0 interfaceC1853ji1, final InterfaceC1853ji0 interfaceC1853ji2) {
        this.a.forEach(new BiConsumer() { // from class: i51
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Z z = (Z) obj;
                ((V1) obj2).a(interfaceC0189d1, new BiConsumer() { // from class: y51
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj3, Object obj4) {
                        interfaceC1853ji0.a(z, (D2) obj3, (X0) obj4);
                    }
                }, new BiConsumer() { // from class: c61
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj3, Object obj4) {
                        interfaceC1853ji0.a(z, (C0346z5) obj3, (C3375c1) obj4);
                    }
                }, new BiConsumer() { // from class: g61
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj3, Object obj4) {
                        interfaceC1853ji0.a(z, (B5) obj3, (C3430n1) obj4);
                    }
                });
            }
        });
    }

    public final AbstractC3390f1 a(Function function) {
        Y y = Y.a;
        V1 v1 = (V1) this.a.get(y);
        if (v1 == null) {
            return null;
        }
        AbstractC3390f1 abstractC3390f1 = (AbstractC3390f1) function.apply(v1);
        if (v1.a.isEmpty()) {
        }
        return abstractC3390f1;
    }

    public final V1 a(Z z) {
        return (V1) this.a.computeIfAbsent(z, IM.a(new Supplier() { // from class: u51
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.a();
            }
        }));
    }

    public final AbstractC3390f1 a(com.android.tools.r8.graph.F2 f2) {
        return a(Y.a).a(f2);
    }

    public final V1 a(V1 v1) {
        return (V1) this.a.getOrDefault(Y.a, v1);
    }

    public final void a(C c) {
        c.a.forEach(new BiConsumer() { // from class: t41
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((Z) obj, (V1) obj2);
            }
        });
    }

    public final /* synthetic */ void a(Z z, V1 v1) {
        a(z).a(v1);
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1, final M m) {
        IM.a(this.a, new BiPredicate() { // from class: k41
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return C.a(interfaceC0189d1, m, (Z) obj, (V1) obj2);
            }
        });
    }

    public final X0 a(final com.android.tools.r8.graph.I2 i2) {
        return (X0) a(new Function() { // from class: m51
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C.a(i2, (V1) obj);
            }
        });
    }

    public static X0 a(com.android.tools.r8.graph.I2 i2, V1 v1) {
        return (X0) v1.a.remove(i2);
    }

    public final C3375c1 a(final C0245l1 c0245l1) {
        return (C3375c1) a(new Function() { // from class: k61
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C.a(c0245l1, (V1) obj);
            }
        });
    }

    public static C3375c1 a(C0245l1 c0245l1, V1 v1) {
        return (C3375c1) v1.a.remove(c0245l1);
    }

    public final C3430n1 a(final C0322w2 c0322w2) {
        return (C3430n1) a(new Function() { // from class: o41
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C.a(c0322w2, (V1) obj);
            }
        });
    }

    public static C3430n1 a(C0322w2 c0322w2, V1 v1) {
        return (C3430n1) v1.a.remove(c0322w2);
    }

    public final B a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        ch0.a("Rewrite DependentMinimumKeepInfoCollection");
        final B b2 = new B(this.a.size());
        this.a.forEach(new BiConsumer() { // from class: q51
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C.a(abstractC3148ys, b2, (Z) obj, (V1) obj2);
            }
        });
        ch0.b();
        return b2;
    }

    public static void a(AbstractC3148ys abstractC3148ys, C c, Z z, V1 v1) {
        Z zA = z.a(abstractC3148ys);
        if (zA instanceof X) {
            return;
        }
        c.a(zA).a(v1.a(abstractC3148ys));
    }
}
