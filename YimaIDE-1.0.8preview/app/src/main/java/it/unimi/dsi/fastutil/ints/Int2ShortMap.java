package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2ShortMap extends Int2ShortFunction, Map<Integer, Short> {

    public interface Entry extends Map.Entry<Integer, Short> {
        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
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

    @Override // it.unimi.dsi.fastutil.ints.Int2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Integer, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetInt2ShortEntrySet = int2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ur6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2ShortMap.Entry entry = (Int2ShortMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetInt2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2ShortEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2ShortEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Integer> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Short>> entrySet2() {
        return int2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default Short compute(Integer num, BiFunction<? super Integer, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Integer num, Function<? super Integer, ? extends Short> function) {
        return (Short) super.computeIfAbsent(num, (Function<? super Integer, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Integer num, BiFunction<? super Integer, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Integer num, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(num, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Integer num, Short sh) {
        return super.put(num, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Integer num, Short sh) {
        return (Short) super.putIfAbsent(num, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Integer num, Short sh, Short sh2) {
        return super.replace(num, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Integer num, Short sh) {
        return (Short) super.replace(num, sh);
    }
}
