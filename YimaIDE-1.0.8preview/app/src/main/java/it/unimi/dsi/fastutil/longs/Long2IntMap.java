package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2IntMap extends Long2IntFunction, Map<Long, Integer> {

    public interface Entry extends Map.Entry<Long, Integer> {
        int getIntValue();

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

    @Override // it.unimi.dsi.fastutil.longs.Long2IntFunction, it.unimi.dsi.fastutil.Function
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

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Long, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetLong2IntEntrySet = long2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: jh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2IntMap.Entry entry = (Long2IntMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetLong2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2IntEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Integer> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Integer>> entrySet2() {
        return long2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.longs.Long2IntFunction, it.unimi.dsi.fastutil.Function
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
    default Integer compute(Long l, BiFunction<? super Long, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Long l, Function<? super Long, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Long l, BiFunction<? super Long, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Long l, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(l, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Long l, Integer num) {
        return super.put(l, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Long l, Integer num) {
        return (Integer) super.putIfAbsent(l, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Integer num, Integer num2) {
        return super.replace(l, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Long l, Integer num) {
        return (Integer) super.replace(l, num);
    }
}
