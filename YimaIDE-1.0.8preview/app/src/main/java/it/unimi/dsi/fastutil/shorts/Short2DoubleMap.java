package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2DoubleMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2DoubleMap extends Short2DoubleFunction, Map<Short, Double> {

    public interface Entry extends Map.Entry<Short, Double> {
        double getDoubleValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        short getShortKey();

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

    @Override // it.unimi.dsi.fastutil.shorts.Short2DoubleFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Short, ? super Double> biConsumer) {
        ObjectSet<Entry> objectSetShort2DoubleEntrySet = short2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: p9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2DoubleMap.Entry entry = (Short2DoubleMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetShort2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Short> keySet2();

    ObjectSet<Entry> short2DoubleEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Short, Double>> entrySet2() {
        return short2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.shorts.Short2DoubleFunction, it.unimi.dsi.fastutil.Function
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
    default Double compute(Short sh, BiFunction<? super Short, ? super Double, ? extends Double> biFunction) {
        return (Double) super.compute(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfAbsent(Short sh, Function<? super Short, ? extends Double> function) {
        return (Double) super.computeIfAbsent(sh, (Function<? super Short, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfPresent(Short sh, BiFunction<? super Short, ? super Double, ? extends Double> biFunction) {
        return (Double) super.computeIfPresent(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double merge(Short sh, Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge(sh, d, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(Short sh, Double d) {
        return super.put(sh, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(Short sh, Double d) {
        return (Double) super.putIfAbsent(sh, d);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Short sh, Double d, Double d2) {
        return super.replace(sh, d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(Short sh, Double d) {
        return (Double) super.replace(sh, d);
    }
}
