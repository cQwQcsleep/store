package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2DoubleMap<K> extends Object2DoubleFunction<K>, Map<K, Double> {

    public interface Entry<K> extends Map.Entry<K, Double> {
        double getDoubleValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getValue() {
            return Double.valueOf(getDoubleValue());
        }

        double setValue(double d);

        @Override // java.util.Map.Entry
        @Deprecated
        default Double setValue(Double d) {
            return Double.valueOf(setValue(d.doubleValue()));
        }
    }

    public interface FastEntrySet<K> extends ObjectSet<Entry<K>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<K>> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    boolean containsValue(double d);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Double) obj).doubleValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Double> biConsumer) {
        ObjectSet<Entry<K>> objectSetObject2DoubleEntrySet = object2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: mna
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Object2DoubleMap.Entry entry = (Object2DoubleMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetObject2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetObject2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetObject2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ObjectSet<K> keySet();

    ObjectSet<Entry<K>> object2DoubleEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Double>> entrySet() {
        return object2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Object2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Double merge(K k, Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge((Object) k, d, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(K k, Double d) {
        return super.put((Object) k, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(K k, Double d) {
        return (Double) super.putIfAbsent((Object) k, d);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Double d, Double d2) {
        return super.replace((Object) k, d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(K k, Double d) {
        return (Double) super.replace((Object) k, d);
    }
}
