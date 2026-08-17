package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2ObjectMap<V> extends Byte2ObjectFunction<V>, Map<Byte, V> {

    public interface Entry<V> extends Map.Entry<Byte, V> {
        byte getByteKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
        }
    }

    public interface FastEntrySet<V> extends ObjectSet<Entry<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<V>> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry<V>> byte2ObjectEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Byte, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetByte2ObjectEntrySet = byte2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: k31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2ObjectMap.Entry entry = (Byte2ObjectMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), entry.getValue());
            }
        };
        if (objectSetByte2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // it.unimi.dsi.fastutil.bytes.Byte2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Byte, V>> entrySet() {
        return byte2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Byte b, V v) {
        return (V) super.put(b, (Object) v);
    }
}
