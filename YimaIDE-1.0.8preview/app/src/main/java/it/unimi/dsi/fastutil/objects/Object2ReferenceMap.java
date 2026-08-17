package it.unimi.dsi.fastutil.objects;

import defpackage.a30;
import it.unimi.dsi.fastutil.objects.Object2ReferenceMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2ReferenceMap<K, V> extends Object2ReferenceFunction<K, V>, Map<K, V> {

    public interface Entry<K, V> extends Map.Entry<K, V> {
    }

    public interface FastEntrySet<K, V> extends ObjectSet<Entry<K, V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<K, V>> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    default V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        Objects.requireNonNull(biFunction);
        V v = get(k);
        V vDefaultReturnValue = defaultReturnValue();
        boolean z = v != vDefaultReturnValue || containsKey(k);
        if (!z) {
            v = (Object) null;
        }
        V vApply = biFunction.apply(k, v);
        if (vApply != null) {
            put(k, vApply);
            return vApply;
        }
        if (z) {
            remove(k);
        }
        return vDefaultReturnValue;
    }

    @Override // java.util.Map
    default V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        Objects.requireNonNull(biFunction);
        V v = get(k);
        V vDefaultReturnValue = defaultReturnValue();
        if (v == vDefaultReturnValue && !containsKey(k)) {
            return vDefaultReturnValue;
        }
        V vApply = biFunction.apply(k, v);
        if (vApply == null) {
            remove(k);
            return vDefaultReturnValue;
        }
        put(k, vApply);
        return vApply;
    }

    @Override // it.unimi.dsi.fastutil.Function
    boolean containsKey(Object obj);

    V defaultReturnValue();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        ObjectSet<Entry<K, V>> objectSetObject2ReferenceEntrySet = object2ReferenceEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: rna
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Object2ReferenceMap.Entry entry = (Object2ReferenceMap.Entry) obj;
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        };
        if (objectSetObject2ReferenceEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetObject2ReferenceEntrySet).fastForEach(consumer);
        } else {
            objectSetObject2ReferenceEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    default V getOrDefault(Object obj, V v) {
        V v2 = get(obj);
        return (v2 != defaultReturnValue() || containsKey(obj)) ? v2 : v;
    }

    @Override // java.util.Map
    ObjectSet<K> keySet();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [V, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // java.util.Map
    default V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        ?? r5;
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(v);
        a30 a30Var = (Object) get(k);
        V vDefaultReturnValue = defaultReturnValue();
        if (a30Var != vDefaultReturnValue || containsKey(k)) {
            r5 = v;
            V vApply = biFunction.apply(a30Var, v);
            r5 = vApply;
            if (vApply == false) {
                remove(k);
                return vDefaultReturnValue;
            }
        }
        r5 = v;
        put(k, (Object) r5);
        return (V) r5;
    }

    ObjectSet<Entry<K, V>> object2ReferenceEntrySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2ReferenceFunction, java.util.Map
    default V put(K k, V v) {
        return (V) super.put(k, v);
    }

    @Override // java.util.Map
    default V putIfAbsent(K k, V v) {
        V v2 = get(k);
        V vDefaultReturnValue = defaultReturnValue();
        if (v2 != vDefaultReturnValue || containsKey(k)) {
            return v2;
        }
        put(k, v);
        return vDefaultReturnValue;
    }

    @Override // java.util.Map
    default boolean remove(Object obj, Object obj2) {
        V v = get(obj);
        if (v != obj2) {
            return false;
        }
        if (v == defaultReturnValue() && !containsKey(obj)) {
            return false;
        }
        remove(obj);
        return true;
    }

    @Override // java.util.Map
    default boolean replace(K k, V v, V v2) {
        V v3 = get(k);
        if (v3 != v) {
            return false;
        }
        if (v3 == defaultReturnValue() && !containsKey(k)) {
            return false;
        }
        put(k, v2);
        return true;
    }

    @Override // java.util.Map
    ReferenceCollection<V> values();

    @Override // java.util.Map
    default ObjectSet<Map.Entry<K, V>> entrySet() {
        return object2ReferenceEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.objects.Object2ReferenceFunction, java.util.Map
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    default V replace(K k, V v) {
        return containsKey(k) ? put(k, v) : defaultReturnValue();
    }
}
