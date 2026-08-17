package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2FloatMap extends Double2FloatFunction, Map<Double, Float> {

    public interface Entry extends Map.Entry<Double, Float> {
        double getDoubleKey();

        float getFloatValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2FloatFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> double2FloatEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Float> biConsumer) {
        ObjectSet<Entry> objectSetDouble2FloatEntrySet = double2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ov3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2FloatMap.Entry entry = (Double2FloatMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetDouble2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Float>> entrySet2() {
        return double2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2FloatFunction, it.unimi.dsi.fastutil.Function
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
    default Float compute(Double d, BiFunction<? super Double, ? super Float, ? extends Float> biFunction) {
        return (Float) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfAbsent(Double d, Function<? super Double, ? extends Float> function) {
        return (Float) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfPresent(Double d, BiFunction<? super Double, ? super Float, ? extends Float> biFunction) {
        return (Float) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float merge(Double d, Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge(d, f, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(Double d, Float f) {
        return super.put(d, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(Double d, Float f) {
        return (Float) super.putIfAbsent(d, f);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Float f, Float f2) {
        return super.replace(d, f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(Double d, Float f) {
        return (Float) super.replace(d, f);
    }
}
