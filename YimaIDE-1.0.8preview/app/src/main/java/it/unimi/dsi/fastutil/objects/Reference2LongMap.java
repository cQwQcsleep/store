package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Reference2LongMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Reference2LongMap<K> extends Reference2LongFunction<K>, Map<K, Long> {

    public interface Entry<K> extends Map.Entry<K, Long> {
        long getLongValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getValue() {
            return Long.valueOf(getLongValue());
        }

        long setValue(long j);

        @Override // java.util.Map.Entry
        @Deprecated
        default Long setValue(Long l) {
            return Long.valueOf(setValue(l.longValue()));
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

    boolean containsValue(long j);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Long) obj).longValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Long> biConsumer) {
        ObjectSet<Entry<K>> objectSetReference2LongEntrySet = reference2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: b9c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Reference2LongMap.Entry entry = (Reference2LongMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetReference2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetReference2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetReference2LongEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ReferenceSet<K> keySet();

    ObjectSet<Entry<K>> reference2LongEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Long>> entrySet() {
        return reference2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Reference2LongFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Long get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Long merge(K k, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge((Object) k, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(K k, Long l) {
        return super.put((Object) k, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(K k, Long l) {
        return (Long) super.putIfAbsent((Object) k, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Long l, Long l2) {
        return super.replace((Object) k, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(K k, Long l) {
        return (Long) super.replace((Object) k, l);
    }
}
