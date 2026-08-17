package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2ByteMap extends Double2ByteFunction, Map<Double, Byte> {

    public interface Entry extends Map.Entry<Double, Byte> {
        byte getByteValue();

        double getDoubleKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getValue() {
            return Byte.valueOf(getByteValue());
        }

        byte setValue(byte b);

        @Override // java.util.Map.Entry
        @Deprecated
        default Byte setValue(Byte b) {
            return Byte.valueOf(setValue(b.byteValue()));
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2ByteFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(byte b);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Byte) obj).byteValue());
    }

    ObjectSet<Entry> double2ByteEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetDouble2ByteEntrySet = double2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: lv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2ByteMap.Entry entry = (Double2ByteMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetDouble2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Byte>> entrySet2() {
        return double2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2ByteFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte compute(Double d, BiFunction<? super Double, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Double d, Function<? super Double, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Double d, BiFunction<? super Double, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Double d, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(d, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Double d, Byte b) {
        return super.put(d, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Double d, Byte b) {
        return (Byte) super.putIfAbsent(d, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Byte b, Byte b2) {
        return super.replace(d, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Double d, Byte b) {
        return (Byte) super.replace(d, b);
    }
}
