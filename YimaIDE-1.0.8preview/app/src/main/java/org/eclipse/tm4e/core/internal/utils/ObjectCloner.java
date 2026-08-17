package org.eclipse.tm4e.core.internal.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.eclipse.tm4e.core.internal.utils.ObjectCloner;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ObjectCloner {
    private static final WeakHashMap<Class<?>, Optional<Method>> CLONE_METHODS_CACHE = new WeakHashMap<>();

    private ObjectCloner() {
    }

    public static /* synthetic */ List b(List list) {
        return new ArrayList(list);
    }

    public static /* synthetic */ Optional c(Class cls) {
        try {
            return Optional.of(cls.getMethod("clone", null));
        } catch (Exception unused) {
            return Optional.empty();
        }
    }

    public static /* synthetic */ Object d(Object obj) {
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.Object, java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v17, types: [T, java.lang.Object, java.util.List] */
    private static <T> T deepClone(final T t, final Map<Object, Object> map) {
        T t2 = (T) map.get(t);
        if (t2 != null) {
            return t2;
        }
        if (t instanceof List) {
            final List list = (List) t;
            ?? r0 = (T) ((List) shallowClone(list, new Supplier() { // from class: zna
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ObjectCloner.b(list);
                }
            }));
            map.put(list, r0);
            r0.replaceAll(new UnaryOperator() { // from class: aoa
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ObjectCloner.deepCloneNullable(obj, map);
                }
            });
            return r0;
        }
        if (t instanceof Set) {
            Set set = (Set) t;
            ?? r1 = (T) ((Set) shallowClone(set, new jc4()));
            map.put(set, r1);
            r1.clear();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                r1.add(deepCloneNullable(it.next(), map));
            }
            return r1;
        }
        if (t instanceof Map) {
            final Map map2 = (Map) t;
            ?? r2 = (T) ((Map) shallowClone(map2, new Supplier() { // from class: boa
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ObjectCloner.e(map2);
                }
            }));
            map.put(map2, r2);
            r2.replaceAll(new BiFunction() { // from class: coa
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return ObjectCloner.deepCloneNullable(obj2, map);
                }
            });
            return r2;
        }
        if (!t.getClass().isArray()) {
            map.put(t, shallowClone(t, new Supplier() { // from class: doa
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ObjectCloner.d(t);
                }
            }));
            return t;
        }
        int length = Array.getLength(t);
        T t3 = (T) Array.newInstance(t.getClass().getComponentType(), length);
        map.put(t, t3);
        for (int i = 0; i < length; i++) {
            Array.set(t3, i, deepCloneNullable(Array.get(t, i), map));
        }
        return t3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T deepCloneNullable(T t, Map<Object, Object> map) {
        if (t == null) {
            return null;
        }
        return (T) deepClone(t, map);
    }

    public static /* synthetic */ Map e(Map map) {
        return new HashMap(map);
    }

    private static <T> T shallowClone(T t, Supplier<T> supplier) {
        if (t instanceof Cloneable) {
            try {
                Optional<Method> optionalComputeIfAbsent = CLONE_METHODS_CACHE.computeIfAbsent(t.getClass(), new Function() { // from class: yna
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ObjectCloner.c((Class) obj);
                    }
                });
                if (optionalComputeIfAbsent.isPresent()) {
                    return (T) optionalComputeIfAbsent.get().invoke(t, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return supplier.get();
    }

    public static <T> T deepClone(T t) {
        return (T) deepClone(t, new IdentityHashMap());
    }
}
