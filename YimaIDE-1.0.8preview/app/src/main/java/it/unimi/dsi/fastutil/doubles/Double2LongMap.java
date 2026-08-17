package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2LongMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2LongMap extends Double2LongFunction, Map<Double, Long> {

    public interface Entry extends Map.Entry<Double, Long> {
        double getDoubleKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }

        long getLongValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getValue() {
            return Long.valueOf(getLongValue());
        }

        long setValue(long j);

        @Override // java.util.Map.Entry
        @Deprecated
        default Long setValue(Long l) {
            return Long.valueOf(setValue(l.longValue()));
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2LongFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(long j);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Long) obj).longValue());
    }

    ObjectSet<Entry> double2LongEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Long> biConsumer) {
        ObjectSet<Entry> objectSetDouble2LongEntrySet = double2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: qv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2LongMap.Entry entry = (Double2LongMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetDouble2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2LongEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Long>> entrySet2() {
        return double2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2LongFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long compute(Double d, BiFunction<? super Double, ? super Long, ? extends Long> biFunction) {
        return (Long) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfAbsent(Double d, Function<? super Double, ? extends Long> function) {
        return (Long) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfPresent(Double d, BiFunction<? super Double, ? super Long, ? extends Long> biFunction) {
        return (Long) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long merge(Double d, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge(d, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(Double d, Long l) {
        return super.put(d, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(Double d, Long l) {
        return (Long) super.putIfAbsent(d, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Long l, Long l2) {
        return super.replace(d, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(Double d, Long l) {
        return (Long) super.replace(d, l);
    }
}
