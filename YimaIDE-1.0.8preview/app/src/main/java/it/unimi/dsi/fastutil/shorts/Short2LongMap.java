package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2LongMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2LongMap extends Short2LongFunction, Map<Short, Long> {

    public interface Entry extends Map.Entry<Short, Long> {
        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        long getLongValue();

        short getShortKey();

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

    @Override // it.unimi.dsi.fastutil.shorts.Short2LongFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Short, ? super Long> biConsumer) {
        ObjectSet<Entry> objectSetShort2LongEntrySet = short2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: s9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2LongMap.Entry entry = (Short2LongMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetShort2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2LongEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Short> keySet2();

    ObjectSet<Entry> short2LongEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Short, Long>> entrySet2() {
        return short2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.shorts.Short2LongFunction, it.unimi.dsi.fastutil.Function
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
    default Long compute(Short sh, BiFunction<? super Short, ? super Long, ? extends Long> biFunction) {
        return (Long) super.compute(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfAbsent(Short sh, Function<? super Short, ? extends Long> function) {
        return (Long) super.computeIfAbsent(sh, (Function<? super Short, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfPresent(Short sh, BiFunction<? super Short, ? super Long, ? extends Long> biFunction) {
        return (Long) super.computeIfPresent(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long merge(Short sh, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge(sh, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(Short sh, Long l) {
        return super.put(sh, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(Short sh, Long l) {
        return (Long) super.putIfAbsent(sh, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Short sh, Long l, Long l2) {
        return super.replace(sh, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(Short sh, Long l) {
        return (Long) super.replace(sh, l);
    }
}
