package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2DoubleMap extends Long2DoubleFunction, Map<Long, Double> {

    public interface Entry extends Map.Entry<Long, Double> {
        double getDoubleValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getKey() {
            return Long.valueOf(getLongKey());
        }

        long getLongKey();

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

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
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
    default void forEach(final BiConsumer<? super Long, ? super Double> biConsumer) {
        ObjectSet<Entry> objectSetLong2DoubleEntrySet = long2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: hh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2DoubleMap.Entry entry = (Long2DoubleMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetLong2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2DoubleEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Double>> entrySet2() {
        return long2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.longs.Long2DoubleFunction, it.unimi.dsi.fastutil.Function
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
    default Double compute(Long l, BiFunction<? super Long, ? super Double, ? extends Double> biFunction) {
        return (Double) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfAbsent(Long l, Function<? super Long, ? extends Double> function) {
        return (Double) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfPresent(Long l, BiFunction<? super Long, ? super Double, ? extends Double> biFunction) {
        return (Double) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double merge(Long l, Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge(l, d, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(Long l, Double d) {
        return super.put(l, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(Long l, Double d) {
        return (Double) super.putIfAbsent(l, d);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Double d, Double d2) {
        return super.replace(l, d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(Long l, Double d) {
        return (Double) super.replace(l, d);
    }
}
