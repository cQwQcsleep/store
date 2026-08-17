package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0263n5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C1341di0;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.C1819jJ;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import com.android.tools.r8.internal.Sm0;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.n5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0263n5 implements InterfaceC0242k5 {
    public static final /* synthetic */ boolean g = true;
    public final IdentityHashMap a = new IdentityHashMap();
    public final Set b = AbstractC2780ub0.c();
    public final Set c = AbstractC2780ub0.c();
    public final Set d = AbstractC2780ub0.c();
    public final IdentityHashMap e = new IdentityHashMap();
    public IdentityHashMap f = new IdentityHashMap();

    public final AbstractC1597gi0 a(I2 i2, final Function function, final Function function2, final C0215h c0215h) {
        final Sm0 sm0 = new Sm0(2);
        if (i2 == c0215h.a().a2) {
            this.f.forEach(new BiConsumer() { // from class: vth
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    AbstractC0263n5.a(c0215h, sm0, (I2) obj, (Set) obj2);
                }
            });
        } else {
            E0 e0D = c0215h.d(i2);
            if (e0D == null) {
                sm0.b((Iterable) this.f.getOrDefault(i2, Collections.EMPTY_SET));
                Iterator it = ((List) this.e.getOrDefault(i2, Collections.EMPTY_LIST)).iterator();
                while (it.hasNext()) {
                    if (((AbstractC1597gi0) function2.apply((C1819jJ) it.next())).c()) {
                        return C1341di0.c;
                    }
                }
            } else {
                sm0.b(e0D);
            }
        }
        return sm0.a(new Function() { // from class: huh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(function, sm0, function2, (E0) obj);
            }
        });
    }

    public abstract void a(Consumer consumer, C0215h c0215h);

    public final AbstractC0263n5 b(final I5 i5) {
        if (!i5.g.isEmpty()) {
            Iterator it = this.a.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Set set = (Set) entry.getValue();
                set.removeIf(new Predicate() { // from class: bqh
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return AbstractC0263n5.a(i5, (C0231j1) obj);
                    }
                });
                if (set.isEmpty()) {
                    this.b.add((D2) entry.getKey());
                    it.remove();
                }
            }
        }
        return this;
    }

    public final boolean c(D2 d2) {
        if (d2.isInterface()) {
            return false;
        }
        if (!this.a.containsKey(d2)) {
            return this.b.contains(d2);
        }
        if (g || !((Set) this.a.get(d2)).isEmpty()) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final boolean d(D2 d2) {
        return (!d2.isInterface() && c(d2)) || a(d2);
    }

    public final boolean e(D2 d2) {
        return d2.isInterface() && this.d.contains(d2);
    }

    public final boolean b(D2 d2) {
        return d2.isInterface() && this.e.get(d2.e) != null;
    }

    public final AbstractC0263n5 a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        C0256m5 c0256m5A = new C0256m5(true, null).a(this, interfaceC0189d1, abstractC3148ys, abstractC3148ys2);
        boolean z = C0256m5.i;
        if (!z && c0256m5A.h == null) {
            x1f.a();
            return null;
        }
        if (c0256m5A.f == null) {
            c0256m5A.a(interfaceC0189d1);
        }
        if (!z) {
            c0256m5A.b(interfaceC0189d1);
        }
        c0256m5A.h = null;
        return c0256m5A;
    }

    public final boolean a(D2 d2) {
        if (this.f.get(d2.e) != null) {
            return true;
        }
        if (d2.isInterface()) {
            return this.c.contains(d2) || this.d.contains(d2) || b(d2);
        }
        return false;
    }

    public final void a(I5 i5) {
        final Set set = i5.e;
        if (set.isEmpty()) {
            return;
        }
        this.a.entrySet().removeIf(new Predicate() { // from class: nrh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains(((D2) ((Map.Entry) obj).getKey()).getType());
            }
        });
        this.b.removeIf(new Predicate() { // from class: zrh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains(((D2) obj).getType());
            }
        });
        this.c.removeIf(new Predicate() { // from class: lsh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains(((D2) obj).getType());
            }
        });
        boolean zRemoveIf = this.d.removeIf(new Predicate() { // from class: xsh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains(((D2) obj).getType());
            }
        });
        if (!g && zRemoveIf) {
            x01.a("Unexpected removal of an interface marking an unknown hierarchy.");
            return;
        }
        final IdentityHashMap identityHashMap = this.e;
        Objects.requireNonNull(identityHashMap);
        set.forEach(new Consumer() { // from class: jth
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                identityHashMap.remove((I2) obj);
            }
        });
    }

    public static boolean a(I5 i5, C0231j1 c0231j1) {
        return i5.g.contains(c0231j1.getReference());
    }

    public final void a(BiConsumer biConsumer) {
        this.a.forEach(biConsumer);
    }

    public final AbstractC0263n5 a(final C0313v0 c0313v0, final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2, Ch0 ch0) {
        return (AbstractC0263n5) ch0.a("Rewrite ObjectAllocationInfoCollectionImpl", new InterfaceC2706th0() { // from class: nqh
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(c0313v0, abstractC3148ys, abstractC3148ys2);
            }
        });
    }

    public final void a(I2 i2, final Consumer consumer, final Consumer consumer2, C0215h c0215h) {
        a(i2, new Function() { // from class: tuh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC0263n5.a(consumer, (D2) obj);
            }
        }, new Function() { // from class: fvh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC0263n5.a(consumer2, (C1819jJ) obj);
            }
        }, c0215h);
    }

    public static /* synthetic */ AbstractC1597gi0 a(Consumer consumer, D2 d2) {
        consumer.accept(d2);
        return C1512fi0.c;
    }

    public static /* synthetic */ AbstractC1597gi0 a(Consumer consumer, C1819jJ c1819jJ) {
        consumer.accept(c1819jJ);
        return C1512fi0.c;
    }

    public static /* synthetic */ void a(C0215h c0215h, Sm0 sm0, I2 i2, Set set) {
        E0 e0D = c0215h.d(i2);
        if (e0D != null) {
            sm0.b(e0D);
        }
        sm0.b((Iterable) set);
    }

    public final AbstractC1597gi0 a(Function function, Sm0 sm0, Function function2, E0 e0) {
        if (e0.a0()) {
            D2 d2X = e0.X();
            if ((c(d2X) || e(d2X)) && ((AbstractC1597gi0) function.apply(d2X)).c()) {
                return C1341di0.c;
            }
        }
        sm0.b((Iterable) this.f.getOrDefault(e0.e, Collections.EMPTY_SET));
        Iterator it = ((List) this.e.getOrDefault(e0.e, Collections.EMPTY_LIST)).iterator();
        while (it.hasNext()) {
            if (((AbstractC1597gi0) function2.apply((C1819jJ) it.next())).c()) {
                return C1341di0.c;
            }
        }
        return C1512fi0.c;
    }

    public final void a(Consumer consumer) {
        this.e.keySet().forEach(consumer);
    }
}
