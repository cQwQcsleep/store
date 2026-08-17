package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2ShortMap extends Double2ShortFunction, Map<Double, Short> {

    public interface Entry extends Map.Entry<Double, Short> {
        double getDoubleKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }

        short getShortValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getValue() {
            return Short.valueOf(getShortValue());
        }

        @Override // java.util.Map.Entry
        @Deprecated
        default Short setValue(Short sh) {
            return Short.valueOf(setValue(sh.shortValue()));
        }

        short setValue(short s);
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Short) obj).shortValue());
    }

    boolean containsValue(short s);

    ObjectSet<Entry> double2ShortEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetDouble2ShortEntrySet = double2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: tv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2ShortMap.Entry entry = (Double2ShortMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetDouble2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Short>> entrySet2() {
        return double2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        return super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default Short remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short compute(Double d, BiFunction<? super Double, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Double d, Function<? super Double, ? extends Short> function) {
        return (Short) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Double d, BiFunction<? super Double, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Double d, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(d, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Double d, Short sh) {
        return super.put(d, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Double d, Short sh) {
        return (Short) super.putIfAbsent(d, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Short sh, Short sh2) {
        return super.replace(d, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Double d, Short sh) {
        return (Short) super.replace(d, sh);
    }
}
