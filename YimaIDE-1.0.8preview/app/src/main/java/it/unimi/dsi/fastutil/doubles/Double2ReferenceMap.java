package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2ReferenceMap<V> extends Double2ReferenceFunction<V>, Map<Double, V> {

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
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    ObjectSet<Entry<V>> double2ReferenceEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetDouble2ReferenceEntrySet = double2ReferenceEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: sv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2ReferenceMap.Entry entry = (Double2ReferenceMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), entry.getValue());
            }
        };
        if (objectSetDouble2ReferenceEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2ReferenceEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2ReferenceEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ReferenceCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Double, V>> entrySet() {
        return double2ReferenceEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Double d, V v) {
        return (V) super.put(d, (Object) v);
    }
}
