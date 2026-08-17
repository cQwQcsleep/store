package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Reference2ByteMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Reference2ByteMap<K> extends Reference2ByteFunction<K>, Map<K, Byte> {

    public interface Entry<K> extends Map.Entry<K, Byte> {
        byte getByteValue();

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

    public interface FastEntrySet<K> extends ObjectSet<Entry<K>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<K>> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
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
    default void forEach(final BiConsumer<? super K, ? super Byte> biConsumer) {
        ObjectSet<Entry<K>> objectSetReference2ByteEntrySet = reference2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: w8c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Reference2ByteMap.Entry entry = (Reference2ByteMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetReference2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetReference2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetReference2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ReferenceSet<K> keySet();

    ObjectSet<Entry<K>> reference2ByteEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Byte>> entrySet() {
        return reference2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Reference2ByteFunction, it.unimi.dsi.fastutil.Function
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
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Byte merge(K k, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge((Object) k, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(K k, Byte b) {
        return super.put((Object) k, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(K k, Byte b) {
        return (Byte) super.putIfAbsent((Object) k, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Byte b, Byte b2) {
        return super.replace((Object) k, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(K k, Byte b) {
        return (Byte) super.replace((Object) k, b);
    }
}
