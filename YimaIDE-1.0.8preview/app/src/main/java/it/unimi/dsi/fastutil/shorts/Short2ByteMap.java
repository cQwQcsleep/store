package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2ByteMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2ByteMap extends Short2ByteFunction, Map<Short, Byte> {

    public interface Entry extends Map.Entry<Short, Byte> {
        byte getByteValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        short getShortKey();

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

    @Override // it.unimi.dsi.fastutil.shorts.Short2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Short, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetShort2ByteEntrySet = short2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: n9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2ByteMap.Entry entry = (Short2ByteMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetShort2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Short> keySet2();

    ObjectSet<Entry> short2ByteEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Short, Byte>> entrySet2() {
        return short2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.shorts.Short2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte compute(Short sh, BiFunction<? super Short, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Short sh, Function<? super Short, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(sh, (Function<? super Short, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Short sh, BiFunction<? super Short, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(sh, (BiFunction<? super Short, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Short sh, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(sh, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Short sh, Byte b) {
        return super.put(sh, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Short sh, Byte b) {
        return (Byte) super.putIfAbsent(sh, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Short sh, Byte b, Byte b2) {
        return super.replace(sh, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Short sh, Byte b) {
        return (Byte) super.replace(sh, b);
    }
}
