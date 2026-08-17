package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Reference2IntMap<K> extends Reference2IntFunction<K>, Map<K, Integer> {

    public interface Entry<K> extends Map.Entry<K, Integer> {
        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getValue() {
            return Integer.valueOf(getIntValue());
        }

        int setValue(int i);

        @Override // java.util.Map.Entry
        @Deprecated
        default Integer setValue(Integer num) {
            return Integer.valueOf(setValue(num.intValue()));
        }
    }

    public interface FastEntrySet<K> extends ObjectSet<Entry<K>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<K>> consumer) {
            forEach(consumer);
        }

        ObjectIterator<Entry<K>> fastIterator();
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    boolean containsValue(int i);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Integer) obj).intValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Integer> biConsumer) {
        ObjectSet<Entry<K>> objectSetReference2IntEntrySet = reference2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: a9c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Reference2IntMap.Entry entry = (Reference2IntMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetReference2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetReference2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetReference2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ReferenceSet<K> keySet();

    ObjectSet<Entry<K>> reference2IntEntrySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Collection<Integer> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Integer>> entrySet() {
        return reference2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Reference2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Integer get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Integer merge(K k, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge((Object) k, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(K k, Integer num) {
        return super.put((Object) k, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(K k, Integer num) {
        return (Integer) super.putIfAbsent((Object) k, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Integer num, Integer num2) {
        return super.replace((Object) k, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(K k, Integer num) {
        return (Integer) super.replace((Object) k, num);
    }
}
