package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2FloatMap extends Long2FloatFunction, Map<Long, Float> {

    public interface Entry extends Map.Entry<Long, Float> {
        float getFloatValue();

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

    @Override // it.unimi.dsi.fastutil.longs.Long2FloatFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Long, ? super Float> biConsumer) {
        ObjectSet<Entry> objectSetLong2FloatEntrySet = long2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ih9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2FloatMap.Entry entry = (Long2FloatMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetLong2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2FloatEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Float>> entrySet2() {
        return long2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.longs.Long2FloatFunction, it.unimi.dsi.fastutil.Function
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
    default Float compute(Long l, BiFunction<? super Long, ? super Float, ? extends Float> biFunction) {
        return (Float) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfAbsent(Long l, Function<? super Long, ? extends Float> function) {
        return (Float) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfPresent(Long l, BiFunction<? super Long, ? super Float, ? extends Float> biFunction) {
        return (Float) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float merge(Long l, Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge(l, f, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(Long l, Float f) {
        return super.put(l, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(Long l, Float f) {
        return (Float) super.putIfAbsent(l, f);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Float f, Float f2) {
        return super.replace(l, f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(Long l, Float f) {
        return (Float) super.replace(l, f);
    }
}
