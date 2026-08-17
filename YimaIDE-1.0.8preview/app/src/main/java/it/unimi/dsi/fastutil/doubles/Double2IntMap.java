package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2IntMap extends Double2IntFunction, Map<Double, Integer> {

    public interface Entry extends Map.Entry<Double, Integer> {
        double getDoubleKey();

        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getValue() {
            return Integer.valueOf(getIntValue());
        }

        int setValue(int i);

        @Override // java.util.Map.Entry
        @Deprecated
        default Integer setValue(Integer num) {
            return Integer.valueOf(setValue(num.intValue()));
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(int i);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Integer) obj).intValue());
    }

    ObjectSet<Entry> double2IntEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetDouble2IntEntrySet = double2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: pv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2IntMap.Entry entry = (Double2IntMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetDouble2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Integer> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Integer>> entrySet2() {
        return double2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer compute(Double d, BiFunction<? super Double, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Double d, Function<? super Double, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Double d, BiFunction<? super Double, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Double d, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(d, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Double d, Integer num) {
        return super.put(d, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Double d, Integer num) {
        return (Integer) super.putIfAbsent(d, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Integer num, Integer num2) {
        return super.replace(d, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Double d, Integer num) {
        return (Integer) super.replace(d, num);
    }
}
