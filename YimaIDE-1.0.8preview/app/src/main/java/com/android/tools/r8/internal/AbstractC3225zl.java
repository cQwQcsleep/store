package com.android.tools.r8.internal;

import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.G0;
import com.android.tools.r8.internal.AbstractC3225zl;
import com.android.tools.r8.internal.C2119mo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3225zl implements Q5, Map {
    public final Map b;

    public AbstractC3225zl(Supplier supplier) {
        this.b = (Map) supplier.get();
    }

    public final AbstractC1597gi0 a(BiFunction biFunction) {
        for (Map.Entry entry : this.b.entrySet()) {
            AbstractC1597gi0 abstractC1597gi0 = (AbstractC1597gi0) biFunction.apply((com.android.tools.r8.graph.G0) ((C2119mo) entry.getKey()).c, entry.getValue());
            if (abstractC1597gi0.c()) {
                return abstractC1597gi0;
            }
        }
        return C1512fi0.c;
    }

    public abstract C2119mo b(com.android.tools.r8.graph.G0 g0);

    public final Object b(com.android.tools.r8.graph.G0 g0, Object obj) {
        return this.b.put(b(g0), obj);
    }

    @Override // java.util.Map
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.b.containsKey(b((com.android.tools.r8.graph.G0) obj));
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        throw new C1345dk0();
    }

    public final ArrayList e() {
        final ArrayList arrayList = new ArrayList(this.b.size());
        this.b.keySet().forEach(new Consumer() { // from class: y0j
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC3225zl.a(arrayList, (C2119mo) obj);
            }
        });
        return arrayList;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.internal.Q5
    public final void forEach(final BiConsumer biConsumer) {
        this.b.forEach(new BiConsumer() { // from class: z0j
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AbstractC3225zl.a(biConsumer, (C2119mo) obj, obj2);
            }
        });
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return this.b.get(b((com.android.tools.r8.graph.G0) obj));
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return this.b.getOrDefault(b((com.android.tools.r8.graph.G0) obj), obj2);
    }

    public final ArrayList i() {
        return C2847vL.a((Collection) e(), new Comparator() { // from class: a1j
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((G0) obj).getReference().b((F2) ((G0) obj2).getReference());
            }
        });
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    public final Stream j() {
        return this.b.keySet().stream().map(new Function() { // from class: t0j
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (G0) ((C2119mo) obj).a();
            }
        });
    }

    @Override // java.util.Map
    public final Set keySet() {
        throw new C1345dk0();
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        return this.b.put(b((com.android.tools.r8.graph.G0) obj), obj2);
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new C1345dk0();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return this.b.remove(b((com.android.tools.r8.graph.G0) obj));
    }

    @Override // java.util.Map
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.b.values();
    }

    public final void b(Consumer consumer) {
        this.b.values().forEach(consumer);
    }

    public AbstractC3225zl(HashMap map) {
        this.b = map;
    }

    public static Object b(Function function, C2119mo c2119mo) {
        return function.apply((com.android.tools.r8.graph.G0) c2119mo.c);
    }

    public final Object a(final Function function, C2119mo c2119mo) {
        return this.b.computeIfAbsent(c2119mo, new Function() { // from class: b1j
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC3225zl.b(function, (C2119mo) obj);
            }
        });
    }

    public final void a(final Consumer consumer) {
        this.b.keySet().forEach(new Consumer() { // from class: v0j
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC3225zl.a(consumer, (C2119mo) obj);
            }
        });
    }

    public final Object a(com.android.tools.r8.graph.G0 g0) {
        return this.b.get(b(g0));
    }

    public final Object a(com.android.tools.r8.graph.G0 g0, Object obj) {
        return this.b.getOrDefault(b(g0), obj);
    }

    public final Object a(com.android.tools.r8.graph.G0 g0, Supplier supplier) {
        Object obj = this.b.get(b(g0));
        return obj != null ? obj : supplier.get();
    }

    public final boolean a(final BiPredicate biPredicate) {
        return this.b.entrySet().removeIf(new Predicate() { // from class: u0j
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC3225zl.a(biPredicate, (Map.Entry) obj);
            }
        });
    }

    public static boolean a(BiPredicate biPredicate, Map.Entry entry) {
        return biPredicate.test((com.android.tools.r8.graph.G0) ((C2119mo) entry.getKey()).c, entry.getValue());
    }

    public final void a(final InterfaceC2024li0 interfaceC2024li0) {
        this.b.entrySet().removeIf(new Predicate() { // from class: x0j
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC3225zl.a(interfaceC2024li0, (Map.Entry) obj);
            }
        });
    }

    public static boolean a(InterfaceC2024li0 interfaceC2024li0, Map.Entry entry) {
        return interfaceC2024li0.a((com.android.tools.r8.graph.G0) ((C2119mo) entry.getKey()).c, entry.getValue(), entry);
    }

    public final Object a(final com.android.tools.r8.graph.G0 g0, final BiFunction biFunction) {
        return this.b.compute(b(g0), new BiFunction() { // from class: w0j
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(g0, obj2);
            }
        });
    }

    public static void a(BiConsumer biConsumer, C2119mo c2119mo, Object obj) {
        biConsumer.accept(c2119mo.c, obj);
    }

    public static void a(Consumer consumer, C2119mo c2119mo) {
        consumer.accept((com.android.tools.r8.graph.G0) c2119mo.c);
    }

    public static void a(List list, C2119mo c2119mo) {
        list.add((com.android.tools.r8.graph.G0) c2119mo.c);
    }
}
