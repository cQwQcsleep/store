package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2ShortMap extends Byte2ShortFunction, Map<Byte, Short> {

    public interface Entry extends Map.Entry<Byte, Short> {
        byte getByteKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
        }

        short getShortValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getValue() {
            return Short.valueOf(getShortValue());
        }

        @Override // java.util.Map.Entry
        @Deprecated
        default Short setValue(Short sh) {
            return Short.valueOf(setValue(sh.shortValue()));
        }

        short setValue(short s);
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> byte2ShortEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Short) obj).shortValue());
    }

    boolean containsValue(short s);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Byte, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetByte2ShortEntrySet = byte2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: m31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2ShortMap.Entry entry = (Byte2ShortMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetByte2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Short>> entrySet2() {
        return byte2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        return super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default Short remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short compute(Byte b, BiFunction<? super Byte, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Byte b, Function<? super Byte, ? extends Short> function) {
        return (Short) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Byte b, BiFunction<? super Byte, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Byte b, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(b, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Byte b, Short sh) {
        return super.put(b, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Byte b, Short sh) {
        return (Short) super.putIfAbsent(b, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Short sh, Short sh2) {
        return super.replace(b, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Byte b, Short sh) {
        return (Short) super.replace(b, sh);
    }
}
