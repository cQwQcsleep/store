package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2ByteMap extends Long2ByteFunction, Map<Long, Byte> {

    public interface Entry extends Map.Entry<Long, Byte> {
        byte getByteValue();

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

    @Override // it.unimi.dsi.fastutil.longs.Long2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Long, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetLong2ByteEntrySet = long2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: fh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2ByteMap.Entry entry = (Long2ByteMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetLong2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2ByteEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Byte>> entrySet2() {
        return long2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.longs.Long2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte compute(Long l, BiFunction<? super Long, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Long l, Function<? super Long, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Long l, BiFunction<? super Long, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Long l, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(l, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Long l, Byte b) {
        return super.put(l, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Long l, Byte b) {
        return (Byte) super.putIfAbsent(l, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Byte b, Byte b2) {
        return super.replace(l, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Long l, Byte b) {
        return (Byte) super.replace(l, b);
    }
}
