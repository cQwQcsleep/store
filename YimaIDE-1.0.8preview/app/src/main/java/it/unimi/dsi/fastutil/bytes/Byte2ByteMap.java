package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2ByteMap extends Byte2ByteFunction, Map<Byte, Byte> {

    public interface Entry extends Map.Entry<Byte, Byte> {
        byte getByteKey();

        byte getByteValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
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

    ObjectSet<Entry> byte2ByteEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Byte, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetByte2ByteEntrySet = byte2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: e31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2ByteMap.Entry entry = (Byte2ByteMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetByte2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Byte>> entrySet2() {
        return byte2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte compute(Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Byte b, Function<? super Byte, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Byte b, Byte b2, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(b, b2, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Byte b, Byte b2) {
        return super.put(b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Byte b, Byte b2) {
        return (Byte) super.putIfAbsent(b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Byte b2, Byte b3) {
        return super.replace(b, b2, b3);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Byte b, Byte b2) {
        return (Byte) super.replace(b, b2);
    }
}
