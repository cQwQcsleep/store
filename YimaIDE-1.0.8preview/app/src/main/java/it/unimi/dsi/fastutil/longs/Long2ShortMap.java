package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2ShortMap extends Long2ShortFunction, Map<Long, Short> {

    public interface Entry extends Map.Entry<Long, Short> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getKey() {
            return Long.valueOf(getLongKey());
        }

        long getLongKey();

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

    @Override // it.unimi.dsi.fastutil.longs.Long2ShortFunction, it.unimi.dsi.fastutil.Function
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

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Long, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetLong2ShortEntrySet = long2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: nh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2ShortMap.Entry entry = (Long2ShortMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetLong2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2ShortEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Short>> entrySet2() {
        return long2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default Short compute(Long l, BiFunction<? super Long, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Long l, Function<? super Long, ? extends Short> function) {
        return (Short) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Long l, BiFunction<? super Long, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Long l, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(l, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Long l, Short sh) {
        return super.put(l, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Long l, Short sh) {
        return (Short) super.putIfAbsent(l, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Short sh, Short sh2) {
        return super.replace(l, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Long l, Short sh) {
        return (Short) super.replace(l, sh);
    }
}
