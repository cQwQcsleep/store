package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0346z5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.I5;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.graph.InterfaceC0265o0;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.shaking.AbstractC3390f1;
import com.android.tools.r8.shaking.AbstractC3395g1;
import com.android.tools.r8.shaking.V1;
import defpackage.fwf;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V1 {
    public static final V1 b = new V1(Collections.EMPTY_MAP);
    public static final /* synthetic */ boolean c = true;
    public final Map a;

    public V1(Map map) {
        this.a = map;
    }

    public static boolean a(final InterfaceC0189d1 interfaceC0189d1, M m, com.android.tools.r8.graph.F2 f2, AbstractC3390f1 abstractC3390f1) {
        if (!c) {
            AbstractC3385e1 abstractC3385e1 = abstractC3390f1.a;
            if (abstractC3385e1.b(abstractC3385e1.f())) {
                x1f.a();
                return false;
            }
        }
        InterfaceC0332x5 interfaceC0332x5 = (InterfaceC0332x5) f2.a(new Function() { // from class: p4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return D2.b(interfaceC0189d1.d((I2) obj));
            }
        }, new Function() { // from class: q4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                C0245l1 c0245l1 = (C0245l1) obj;
                return c0245l1.a(D2.b(interfaceC0189d1.d(c0245l1.w0())));
            }
        }, new Function() { // from class: r4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                C0322w2 c0322w2 = (C0322w2) obj;
                return c0322w2.a(D2.b(interfaceC0189d1.d(c0322w2.w0())));
            }
        });
        return interfaceC0332x5 == null || !m.a((InterfaceC0265o0) interfaceC0332x5);
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1, final BiConsumer biConsumer, final BiConsumer biConsumer2, final BiConsumer biConsumer3) {
        this.a.forEach(new BiConsumer() { // from class: d4f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                V1.a(interfaceC0189d1, biConsumer, biConsumer2, biConsumer3, (F2) obj, (AbstractC3390f1) obj2);
            }
        });
    }

    public static /* synthetic */ void a(InterfaceC0189d1 interfaceC0189d1, final BiConsumer biConsumer, final BiConsumer biConsumer2, final BiConsumer biConsumer3, com.android.tools.r8.graph.F2 f2, final AbstractC3390f1 abstractC3390f1) {
        final com.android.tools.r8.graph.D2 d2B = com.android.tools.r8.graph.D2.b(interfaceC0189d1.d(f2.z()));
        if (d2B != null) {
            f2.a(new Consumer() { // from class: a4f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    biConsumer.accept(d2B, abstractC3390f1.a());
                }
            }, new Consumer() { // from class: h4f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    V1.a(d2B, biConsumer2, abstractC3390f1, (C0245l1) obj);
                }
            }, new Consumer() { // from class: j4f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    V1.a(d2B, biConsumer3, abstractC3390f1, (C0322w2) obj);
                }
            });
        }
    }

    public static /* synthetic */ void a(com.android.tools.r8.graph.D2 d2, BiConsumer biConsumer, AbstractC3390f1 abstractC3390f1, C0245l1 c0245l1) {
        C0346z5 c0346z5B = d2.b(c0245l1);
        if (c0346z5B != null) {
            biConsumer.accept(c0346z5B, abstractC3390f1.b());
        }
    }

    public static /* synthetic */ void a(com.android.tools.r8.graph.D2 d2, BiConsumer biConsumer, AbstractC3390f1 abstractC3390f1, C0322w2 c0322w2) {
        B5 b5F = d2.f(c0322w2);
        if (b5F != null) {
            biConsumer.accept(b5F, abstractC3390f1.d());
        }
    }

    public final void a(final BiPredicate biPredicate, final BiConsumer biConsumer) {
        this.a.forEach(new BiConsumer() { // from class: e4f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                V1.a(biPredicate, biConsumer, (F2) obj, (AbstractC3390f1) obj2);
            }
        });
    }

    public static /* synthetic */ void a(BiPredicate biPredicate, BiConsumer biConsumer, com.android.tools.r8.graph.F2 f2, AbstractC3390f1 abstractC3390f1) {
        if (biPredicate.test(f2, abstractC3390f1)) {
            biConsumer.accept(f2, abstractC3390f1);
        }
    }

    public final AbstractC3390f1 a(com.android.tools.r8.graph.F2 f2, C3430n1 c3430n1) {
        return (AbstractC3390f1) this.a.getOrDefault(f2, c3430n1);
    }

    public final AbstractC3390f1 a(final com.android.tools.r8.graph.F2 f2) {
        return (AbstractC3390f1) this.a.computeIfAbsent(f2, IM.a(new Supplier() { // from class: c4f
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC3395g1.a(f2);
            }
        }));
    }

    public final boolean a(com.android.tools.r8.graph.F2 f2, Predicate predicate) {
        AbstractC3390f1 abstractC3390f1 = (AbstractC3390f1) this.a.get(f2);
        return abstractC3390f1 != null && predicate.test(abstractC3390f1);
    }

    public final void a(V1 v1) {
        v1.a.forEach(new BiConsumer() { // from class: l4f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a((F2) obj, (AbstractC3390f1) obj2);
            }
        });
    }

    public final void a(com.android.tools.r8.graph.F2 f2, AbstractC3390f1 abstractC3390f1) {
        a(f2).a(abstractC3390f1);
    }

    public final void a(final InterfaceC0189d1 interfaceC0189d1, final M m) {
        IM.a(this.a, new BiPredicate() { // from class: g4f
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return V1.a(interfaceC0189d1, m, (F2) obj, (AbstractC3390f1) obj2);
            }
        });
    }

    public static V1 a() {
        return b;
    }

    public final void a(I5 i5) {
        this.a.keySet().removeIf(new fwf(i5));
    }

    public static void a(final AbstractC3148ys abstractC3148ys, V1 v1, com.android.tools.r8.graph.F2 f2, AbstractC3390f1 abstractC3390f1) {
        Function function = new Function() { // from class: m4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return V1.a(abstractC3148ys, (I2) obj);
            }
        };
        Objects.requireNonNull(abstractC3148ys);
        com.android.tools.r8.graph.F2 f3 = (com.android.tools.r8.graph.F2) f2.a(function, new Function() { // from class: n4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.a((C0245l1) obj);
            }
        }, new Function() { // from class: o4f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return abstractC3148ys.c((C0322w2) obj);
            }
        });
        if (f3 != null) {
            v1.a(f3).a(abstractC3390f1);
        }
    }

    public static com.android.tools.r8.graph.F2 a(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        abstractC3148ys.getClass();
        com.android.tools.r8.graph.I2 i2C = abstractC3148ys.c(AbstractC3148ys.g(), i2);
        if (!i2C.T0()) {
            return i2C;
        }
        boolean z = c;
        if (!z && !i2.M0()) {
            x1f.a();
            return null;
        }
        if (!z && !i2C.P0()) {
            x1f.a();
        }
        return null;
    }

    public final V1 a(final AbstractC3148ys abstractC3148ys) {
        final V1 v1 = new V1(new IdentityHashMap(this.a.size()));
        this.a.forEach(new BiConsumer() { // from class: f4f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                V1.a(abstractC3148ys, v1, (F2) obj, (AbstractC3390f1) obj2);
            }
        });
        return v1;
    }
}
