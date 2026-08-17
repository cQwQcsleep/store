package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2ObjectMap<V> extends Double2ObjectFunction<V>, Map<Double, V> {

    public interface Entry<V> extends Map.Entry<Double, V> {
        double getDoubleKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }
    }

    public interface FastEntrySet<V> extends ObjectSet<Entry<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<V>> consumer) {
            forEach(consumer);
        }

        ObjectIterator<Entry<V>> fastIterator();
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    ObjectSet<Entry<V>> double2ObjectEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetDouble2ObjectEntrySet = double2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: rv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2ObjectMap.Entry entry = (Double2ObjectMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), entry.getValue());
            }
        };
        if (objectSetDouble2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Set<Double> keySet();

    @Override // it.unimi.dsi.fastutil.doubles.Double2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Double, V>> entrySet() {
        return double2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Double d, V v) {
        return (V) super.put(d, (Object) v);
    }
}
