package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2LongMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2LongMap extends Float2LongFunction, Map<Float, Long> {

    public interface Entry extends Map.Entry<Float, Long> {
        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
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

    @Override // it.unimi.dsi.fastutil.floats.Float2LongFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2LongEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Long> biConsumer) {
        ObjectSet<Entry> objectSetFloat2LongEntrySet = float2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: uh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2LongMap.Entry entry = (Float2LongMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetFloat2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2LongEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Long>> entrySet2() {
        return float2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2LongFunction, it.unimi.dsi.fastutil.Function
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
    default Long compute(Float f, BiFunction<? super Float, ? super Long, ? extends Long> biFunction) {
        return (Long) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfAbsent(Float f, Function<? super Float, ? extends Long> function) {
        return (Long) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfPresent(Float f, BiFunction<? super Float, ? super Long, ? extends Long> biFunction) {
        return (Long) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long merge(Float f, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge(f, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(Float f, Long l) {
        return super.put(f, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(Float f, Long l) {
        return (Long) super.putIfAbsent(f, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Long l, Long l2) {
        return super.replace(f, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(Float f, Long l) {
        return (Long) super.replace(f, l);
    }
}
