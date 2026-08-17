package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2ShortMap extends Float2ShortFunction, Map<Float, Short> {

    public interface Entry extends Map.Entry<Float, Short> {
        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
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

    @Override // it.unimi.dsi.fastutil.floats.Float2ShortFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2ShortEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetFloat2ShortEntrySet = float2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: xh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2ShortMap.Entry entry = (Float2ShortMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetFloat2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Short>> entrySet2() {
        return float2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2ShortFunction, it.unimi.dsi.fastutil.Function
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
    default Short compute(Float f, BiFunction<? super Float, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Float f, Function<? super Float, ? extends Short> function) {
        return (Short) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Float f, BiFunction<? super Float, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Float f, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(f, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Float f, Short sh) {
        return super.put(f, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Float f, Short sh) {
        return (Short) super.putIfAbsent(f, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Short sh, Short sh2) {
        return super.replace(f, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Float f, Short sh) {
        return (Short) super.replace(f, sh);
    }
}
