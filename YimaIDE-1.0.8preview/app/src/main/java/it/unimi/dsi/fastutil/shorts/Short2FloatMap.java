package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2FloatMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2FloatMap extends Short2FloatFunction, Map<Short, Float> {

    public interface Entry extends Map.Entry<Short, Float> {
        float getFloatValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        short getShortKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getValue() {
            return Float.valueOf(getFloatValue());
        }

        float setValue(float f);

        @Override // java.util.Map.Entry
        @Deprecated
        default Float setValue(Float f) {
            return Float.valueOf(setValue(f.floatValue()));
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

    @Override // it.unimi.dsi.fastutil.shorts.Short2FloatFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(float f);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Float) obj).floatValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Short, ? super Float> biConsumer) {
        ObjectSet<Entry> objectSetShort2FloatEntrySet = short2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: q9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2FloatMap.Entry entry = (Short2FloatMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetShort2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Short> keySet2();

    ObjectSet<Entry> short2FloatEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Short, Float>> entrySet2() {
        return short2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.shorts.Short2FloatFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float compute(Short sh, BiFunction<? super Short, ? super Float, ? extends Float> biFunction) {
        return (Float) super.compute(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfAbsent(Short sh, Function<? super Short, ? extends Float> function) {
        return (Float) super.computeIfAbsent(sh, (Function<? super Short, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfPresent(Short sh, BiFunction<? super Short, ? super Float, ? extends Float> biFunction) {
        return (Float) super.computeIfPresent(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float merge(Short sh, Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge(sh, f, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(Short sh, Float f) {
        return super.put(sh, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(Short sh, Float f) {
        return (Float) super.putIfAbsent(sh, f);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Short sh, Float f, Float f2) {
        return super.replace(sh, f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(Short sh, Float f) {
        return (Float) super.replace(sh, f);
    }
}
