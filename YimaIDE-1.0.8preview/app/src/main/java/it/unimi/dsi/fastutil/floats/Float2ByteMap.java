package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2ByteMap extends Float2ByteFunction, Map<Float, Byte> {

    public interface Entry extends Map.Entry<Float, Byte> {
        byte getByteValue();

        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
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

    @Override // it.unimi.dsi.fastutil.floats.Float2ByteFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2ByteEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetFloat2ByteEntrySet = float2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ph5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2ByteMap.Entry entry = (Float2ByteMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetFloat2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Byte>> entrySet2() {
        return float2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte compute(Float f, BiFunction<? super Float, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Float f, Function<? super Float, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Float f, BiFunction<? super Float, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Float f, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(f, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Float f, Byte b) {
        return super.put(f, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Float f, Byte b) {
        return (Byte) super.putIfAbsent(f, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Byte b, Byte b2) {
        return super.replace(f, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Float f, Byte b) {
        return (Byte) super.replace(f, b);
    }
}
