package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2LongMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2LongMap extends Int2LongFunction, Map<Integer, Long> {

    public interface Entry extends Map.Entry<Integer, Long> {
        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
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

    @Override // it.unimi.dsi.fastutil.ints.Int2LongFunction, it.unimi.dsi.fastutil.Function
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

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Integer, ? super Long> biConsumer) {
        ObjectSet<Entry> objectSetInt2LongEntrySet = int2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: rr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2LongMap.Entry entry = (Int2LongMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetInt2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2LongEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2LongEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Integer> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Long>> entrySet2() {
        return int2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.ints.Int2LongFunction, it.unimi.dsi.fastutil.Function
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
    default Long compute(Integer num, BiFunction<? super Integer, ? super Long, ? extends Long> biFunction) {
        return (Long) super.compute(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfAbsent(Integer num, Function<? super Integer, ? extends Long> function) {
        return (Long) super.computeIfAbsent(num, (Function<? super Integer, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfPresent(Integer num, BiFunction<? super Integer, ? super Long, ? extends Long> biFunction) {
        return (Long) super.computeIfPresent(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long merge(Integer num, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge(num, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(Integer num, Long l) {
        return super.put(num, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(Integer num, Long l) {
        return (Long) super.putIfAbsent(num, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Integer num, Long l, Long l2) {
        return super.replace(num, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(Integer num, Long l) {
        return (Long) super.replace(num, l);
    }
}
