package com.android.tools.r8.internal;

import com.android.tools.r8.internal.IM;
import defpackage.m7h;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IM {
    public static void a(Map map, BiConsumer biConsumer, Object obj) {
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            if (key.equals(obj)) {
                return;
            } else {
                biConsumer.accept(key, entry.getValue());
            }
        }
    }

    public static /* synthetic */ String b(Map.Entry entry) {
        return entry.getKey() + ":" + entry.getValue();
    }

    public static String b(Map map) {
        return Wf0.a(",", map.entrySet(), new Function() { // from class: gg6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IM.b((Map.Entry) obj);
            }
        }, Wf0.a.d);
    }

    public static Map a(Map map, final HashMap map2, final Function function) {
        map.forEach(new BiConsumer() { // from class: cg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                map2.put(obj, function.apply(obj2));
            }
        });
        return map2;
    }

    public static <T, R> Function<T, R> a(final Supplier<R> supplier) {
        return new Function() { // from class: kg6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return supplier.get();
            }
        };
    }

    public static IdentityHashMap a(Consumer consumer) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        consumer.accept(identityHashMap);
        return identityHashMap;
    }

    public static IdentityHashMap a(Q5 q5, int i) {
        final IdentityHashMap identityHashMap = new IdentityHashMap(i);
        q5.forEach(new BiConsumer() { // from class: eg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                identityHashMap.put(obj, obj2);
            }
        });
        return identityHashMap;
    }

    public static /* synthetic */ boolean a(Map.Entry entry) {
        return entry.getKey() == entry.getValue();
    }

    public static void a(Map map) {
        map.entrySet().removeIf(new Predicate() { // from class: fg6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return IM.a((Map.Entry) obj);
            }
        });
    }

    public static void a(Map map, final BiPredicate biPredicate) {
        map.entrySet().removeIf(new Predicate() { // from class: hg6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                return biPredicate.test(entry.getKey(), entry.getValue());
            }
        });
    }

    public static Object a(Map map, Object obj, Object obj2) {
        Object objRemove = map.remove(obj);
        return objRemove != null ? objRemove : obj2;
    }

    public static <K1, V1, K2, V2> Map<K2, V2> a(Map<K1, V1> map, IntFunction<Map<K2, V2>> intFunction, final Function<K1, K2> function, final Function<V1, V2> function2, InterfaceC1938ki0<K2, V2, V2, V2> interfaceC1938ki0) {
        return a(map, intFunction, new BiFunction() { // from class: ig6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return function.apply(obj);
            }
        }, new BiFunction() { // from class: jg6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return function2.apply(obj2);
            }
        }, interfaceC1938ki0);
    }

    public static Map a(Map map, IntFunction intFunction, final BiFunction biFunction, final BiFunction biFunction2, final InterfaceC1938ki0 interfaceC1938ki0) {
        final Map map2 = (Map) intFunction.apply(map.size());
        map.forEach(new BiConsumer() { // from class: dg6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                IM.a(biFunction, biFunction2, map2, interfaceC1938ki0, obj, obj2);
            }
        });
        return map2;
    }

    public static /* synthetic */ void a(BiFunction biFunction, BiFunction biFunction2, Map map, InterfaceC1938ki0 interfaceC1938ki0, Object obj, Object obj2) {
        Object objApply;
        Object objPut;
        Object objApply2 = biFunction.apply(obj, obj2);
        if (objApply2 == null || (objApply = biFunction2.apply(obj, obj2)) == null || (objPut = map.put(objApply2, objApply)) == null) {
            return;
        }
        map.put(objApply2, interfaceC1938ki0.a(objApply2, objPut, objApply));
    }

    public static Map a(IdentityHashMap identityHashMap, int i) {
        if (identityHashMap.size() >= i) {
            return identityHashMap;
        }
        Map map = (Map) new m7h().apply(identityHashMap.size());
        map.putAll(identityHashMap);
        return map;
    }
}
