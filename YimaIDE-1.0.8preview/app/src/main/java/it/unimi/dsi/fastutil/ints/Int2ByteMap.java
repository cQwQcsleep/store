package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2ByteMap extends Int2ByteFunction, Map<Integer, Byte> {

    public interface Entry extends Map.Entry<Integer, Byte> {
        byte getByteValue();

        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
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

    @Override // it.unimi.dsi.fastutil.ints.Int2ByteFunction, it.unimi.dsi.fastutil.Function
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

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Integer, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetInt2ByteEntrySet = int2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: mr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2ByteMap.Entry entry = (Int2ByteMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetInt2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2ByteEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2ByteEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Integer> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Byte>> entrySet2() {
        return int2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.ints.Int2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte compute(Integer num, BiFunction<? super Integer, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Integer num, Function<? super Integer, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(num, (Function<? super Integer, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Integer num, BiFunction<? super Integer, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Integer num, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(num, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Integer num, Byte b) {
        return super.put(num, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Integer num, Byte b) {
        return (Byte) super.putIfAbsent(num, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Integer num, Byte b, Byte b2) {
        return super.replace(num, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Integer num, Byte b) {
        return (Byte) super.replace(num, b);
    }
}
