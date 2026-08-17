package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2DoubleMap extends Byte2DoubleFunction, Map<Byte, Double> {

    public interface Entry extends Map.Entry<Byte, Double> {
        byte getByteKey();

        double getDoubleValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getValue() {
            return Double.valueOf(getDoubleValue());
        }

        double setValue(double d);

        @Override // java.util.Map.Entry
        @Deprecated
        default Double setValue(Double d) {
            return Double.valueOf(setValue(d.doubleValue()));
        }
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> byte2DoubleEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(double d);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Double) obj).doubleValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Byte, ? super Double> biConsumer) {
        ObjectSet<Entry> objectSetByte2DoubleEntrySet = byte2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: g31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2DoubleMap.Entry entry = (Byte2DoubleMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetByte2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Double>> entrySet2() {
        return byte2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double compute(Byte b, BiFunction<? super Byte, ? super Double, ? extends Double> biFunction) {
        return (Double) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfAbsent(Byte b, Function<? super Byte, ? extends Double> function) {
        return (Double) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfPresent(Byte b, BiFunction<? super Byte, ? super Double, ? extends Double> biFunction) {
        return (Double) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double merge(Byte b, Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge(b, d, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(Byte b, Double d) {
        return super.put(b, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(Byte b, Double d) {
        return (Double) super.putIfAbsent(b, d);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Double d, Double d2) {
        return super.replace(b, d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(Byte b, Double d) {
        return (Double) super.replace(b, d);
    }
}
