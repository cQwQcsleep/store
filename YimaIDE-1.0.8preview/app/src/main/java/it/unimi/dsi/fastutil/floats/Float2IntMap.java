package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2IntMap extends Float2IntFunction, Map<Float, Integer> {

    public interface Entry extends Map.Entry<Float, Integer> {
        float getFloatKey();

        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getValue() {
            return Integer.valueOf(getIntValue());
        }

        int setValue(int i);

        @Override // java.util.Map.Entry
        @Deprecated
        default Integer setValue(Integer num) {
            return Integer.valueOf(setValue(num.intValue()));
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

    @Override // it.unimi.dsi.fastutil.floats.Float2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(int i);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Integer) obj).intValue());
    }

    ObjectSet<Entry> float2IntEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetFloat2IntEntrySet = float2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: th5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2IntMap.Entry entry = (Float2IntMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetFloat2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Integer> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Integer>> entrySet2() {
        return float2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer compute(Float f, BiFunction<? super Float, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Float f, Function<? super Float, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Float f, BiFunction<? super Float, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Float f, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(f, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Float f, Integer num) {
        return super.put(f, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Float f, Integer num) {
        return (Integer) super.putIfAbsent(f, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Integer num, Integer num2) {
        return super.replace(f, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Float f, Integer num) {
        return (Integer) super.replace(f, num);
    }
}
