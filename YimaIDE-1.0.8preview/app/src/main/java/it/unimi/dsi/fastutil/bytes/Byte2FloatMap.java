package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2FloatMap extends Byte2FloatFunction, Map<Byte, Float> {

    public interface Entry extends Map.Entry<Byte, Float> {
        byte getByteKey();

        float getFloatValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
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

    ObjectSet<Entry> byte2FloatEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2FloatFunction, it.unimi.dsi.fastutil.Function
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

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Byte, ? super Float> biConsumer) {
        ObjectSet<Entry> objectSetByte2FloatEntrySet = byte2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: h31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2FloatMap.Entry entry = (Byte2FloatMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetByte2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Float>> entrySet2() {
        return byte2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2FloatFunction, it.unimi.dsi.fastutil.Function
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
    default Float compute(Byte b, BiFunction<? super Byte, ? super Float, ? extends Float> biFunction) {
        return (Float) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfAbsent(Byte b, Function<? super Byte, ? extends Float> function) {
        return (Float) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Float computeIfPresent(Byte b, BiFunction<? super Byte, ? super Float, ? extends Float> biFunction) {
        return (Float) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float merge(Byte b, Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge(b, f, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(Byte b, Float f) {
        return super.put(b, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(Byte b, Float f) {
        return (Float) super.putIfAbsent(b, f);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Float f, Float f2) {
        return super.replace(b, f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(Byte b, Float f) {
        return (Float) super.replace(b, f);
    }
}
