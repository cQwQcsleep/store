package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2ObjectMap<V> extends Long2ObjectFunction<V>, Map<Long, V> {

    public interface Entry<V> extends Map.Entry<Long, V> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getKey() {
            return Long.valueOf(getLongKey());
        }

        long getLongKey();
    }

    public interface FastEntrySet<V> extends ObjectSet<Entry<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<V>> consumer) {
            forEach(consumer);
        }

        ObjectIterator<Entry<V>> fastIterator();
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Long, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetLong2ObjectEntrySet = long2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: lh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2ObjectMap.Entry entry = (Long2ObjectMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), entry.getValue());
            }
        };
        if (objectSetLong2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Set<Long> keySet();

    ObjectSet<Entry<V>> long2ObjectEntrySet();

    @Override // it.unimi.dsi.fastutil.longs.Long2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Long, V>> entrySet() {
        return long2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Long l, V v) {
        return (V) super.put(l, (Object) v);
    }
}
