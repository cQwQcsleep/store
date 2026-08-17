package com.android.tools.r8.graph;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.W3;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C2838vC;
import com.android.tools.r8.internal.EX;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.InterfaceC0392Br;
import defpackage.vef;
import defpackage.xef;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W3 {
    public static final /* synthetic */ boolean c = true;
    public final C0333y a;
    public final Map b;

    public W3(C0333y c0333y, IdentityHashMap identityHashMap) {
        this.a = c0333y;
        this.b = identityHashMap;
    }

    public static W3 a(final C0333y c0333y, Collection collection) {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            final D2 d2 = (D2) it.next();
            d2.f(new Consumer() { // from class: cff
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    W3.a(c0333y, identityHashMap, d2, (I2) obj);
                }
            });
        }
        return new W3(c0333y, identityHashMap);
    }

    public static /* synthetic */ void c(Predicate predicate, Consumer consumer, D2 d2) {
        if (predicate.test(d2)) {
            consumer.accept(d2);
        }
    }

    public final Iterable b(D2 d2) {
        if (c || d2.isInterface()) {
            return AbstractC3179zC.c((List) this.b.getOrDefault(d2, Collections.EMPTY_LIST), new xef());
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ boolean a(I2 i2, E0 e0) {
        return e0 != null;
    }

    public final void b(final Predicate predicate, final Consumer consumer, D2 d2) {
        ((List) this.b.getOrDefault(d2, Collections.EMPTY_LIST)).forEach(new Consumer() { // from class: dff
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                W3.c(predicate, consumer, (D2) obj);
            }
        });
    }

    public static /* synthetic */ void a(C0333y c0333y, Map map, D2 d2, I2 i2) {
        D2 d2B = D2.b(c0333y.d(i2));
        if (d2B != null) {
            ((List) map.computeIfAbsent(d2B, IM.a(new vef()))).add(d2);
        }
    }

    public final void a(final Consumer consumer, E0 e0) {
        a(e0, new BiPredicate() { // from class: aff
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return W3.a((I2) obj, (E0) obj2);
            }
        }, new BiConsumer() { // from class: bff
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept((E0) obj2);
            }
        });
    }

    public final /* synthetic */ void b(Predicate predicate, Consumer consumer, I2 i2) {
        E0 e0D = this.a.d(i2);
        if (e0D == null || !predicate.test(e0D)) {
            return;
        }
        consumer.accept(e0D);
    }

    public final void a(E0 e0, final BiPredicate biPredicate, final BiConsumer biConsumer) {
        e0.f(new Consumer() { // from class: tef
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(biPredicate, biConsumer, (I2) obj);
            }
        });
    }

    public final /* synthetic */ void a(BiPredicate biPredicate, BiConsumer biConsumer, I2 i2) {
        E0 e0D = this.a.d(i2);
        if (biPredicate.test(i2, e0D)) {
            biConsumer.accept(i2, e0D);
        }
    }

    public final void a(E0 e0, final Predicate predicate, final Consumer consumer) {
        e0.f(new Consumer() { // from class: uef
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(predicate, consumer, (I2) obj);
            }
        });
    }

    public final void a(final Predicate predicate, final Consumer consumer, D2 d2) {
        d2.f(new Consumer() { // from class: wef
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(predicate, consumer, (I2) obj);
            }
        });
    }

    public final /* synthetic */ void a(Predicate predicate, Consumer consumer, I2 i2) {
        D2 d2B = D2.b(this.a.d(i2));
        if (d2B == null || !predicate.test(d2B)) {
            return;
        }
        consumer.accept(d2B);
    }

    public final List a(D2 d2) {
        return (List) this.b.getOrDefault(d2, Collections.EMPTY_LIST);
    }

    public static C2838vC a(final InterfaceC0189d1 interfaceC0189d1, final D2 d2) {
        return new C2838vC(AbstractC3179zC.a(d2.U0(), new InterfaceC0392Br() { // from class: yef
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return D2.b(interfaceC0189d1.a(d2, (I2) obj));
            }
        }), new EX() { // from class: zef
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return Objects.nonNull((D2) obj);
            }
        });
    }
}
