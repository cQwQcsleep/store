package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2DoubleMap extends Double2DoubleFunction, Map<Double, Double> {

    public interface Entry extends Map.Entry<Double, Double> {
        double getDoubleKey();

        double getDoubleValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }

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

    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> double2DoubleEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Double> biConsumer) {
        ObjectSet<Entry> objectSetDouble2DoubleEntrySet = double2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: nv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2DoubleMap.Entry entry = (Double2DoubleMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetDouble2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Double>> entrySet2() {
        return double2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleFunction, it.unimi.dsi.fastutil.Function
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
    default Double compute(Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfAbsent(Double d, Function<? super Double, ? extends Double> function) {
        return (Double) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfPresent(Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double merge(Double d, Double d2, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge(d, d2, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(Double d, Double d2) {
        return super.put(d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(Double d, Double d2) {
        return (Double) super.putIfAbsent(d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Double d2, Double d3) {
        return super.replace(d, d2, d3);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(Double d, Double d2) {
        return (Double) super.replace(d, d2);
    }
}
