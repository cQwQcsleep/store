package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2ReferenceMap<V> extends Long2ReferenceFunction<V>, Map<Long, V> {

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
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ReferenceFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Long, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetLong2ReferenceEntrySet = long2ReferenceEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: mh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2ReferenceMap.Entry entry = (Long2ReferenceMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), entry.getValue());
            }
        };
        if (objectSetLong2ReferenceEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2ReferenceEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2ReferenceEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ReferenceFunction, it.unimi.dsi.fastutil.Function
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
    Set<Long> keySet2();

    ObjectSet<Entry<V>> long2ReferenceEntrySet();

    @Override // it.unimi.dsi.fastutil.longs.Long2ReferenceFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ReferenceCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Long, V>> entrySet() {
        return long2ReferenceEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Long l, V v) {
        return (V) super.put(l, (Object) v);
    }
}
