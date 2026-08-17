package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2FloatMap extends Float2FloatFunction, Map<Float, Float> {

    public interface Entry extends Map.Entry<Float, Float> {
        float getFloatKey();

        float getFloatValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
        }

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

    @Override // it.unimi.dsi.fastutil.floats.Float2FloatFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2FloatEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Float> biConsumer) {
        ObjectSet<Entry> objectSetFloat2FloatEntrySet = float2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: sh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2FloatMap.Entry entry = (Float2FloatMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetFloat2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Float>> entrySet2() {
        return float2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2FloatFunction, it.unimi.dsi.fastutil.Function
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
    default Float compute(Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfAbsent(Float f, Function<? super Float, ? extends Float> function) {
        return (Float) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfPresent(Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float merge(Float f, Float f2, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge(f, f2, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(Float f, Float f2) {
        return super.put(f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(Float f, Float f2) {
        return (Float) super.putIfAbsent(f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Float f2, Float f3) {
        return super.replace(f, f2, f3);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(Float f, Float f2) {
        return (Float) super.replace(f, f2);
    }
}
