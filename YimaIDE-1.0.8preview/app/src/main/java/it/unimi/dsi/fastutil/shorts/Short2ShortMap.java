package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2ShortMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2ShortMap extends Short2ShortFunction, Map<Short, Short> {

    public interface Entry extends Map.Entry<Short, Short> {
        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        short getShortKey();

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

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Short, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetShort2ShortEntrySet = short2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: v9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2ShortMap.Entry entry = (Short2ShortMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetShort2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Short> keySet2();

    ObjectSet<Entry> short2ShortEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Short, Short>> entrySet2() {
        return short2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default Short compute(Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Short sh, Function<? super Short, ? extends Short> function) {
        return (Short) super.computeIfAbsent(sh, (Function<? super Short, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Short sh, Short sh2, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(sh, sh2, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Short sh, Short sh2) {
        return super.put(sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Short sh, Short sh2) {
        return (Short) super.putIfAbsent(sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Short sh, Short sh2, Short sh3) {
        return super.replace(sh, sh2, sh3);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Short sh, Short sh2) {
        return (Short) super.replace(sh, sh2);
    }
}
