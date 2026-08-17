package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2IntMap<K> extends Object2IntFunction<K>, Map<K, Integer> {

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

    @Override // it.unimi.dsi.fastutil.objects.Object2IntFunction
    int defaultReturnValue();

    void defaultReturnValue(int i);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Integer> biConsumer) {
        ObjectSet<Entry<K>> objectSetObject2IntEntrySet = object2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ona
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Object2IntMap.Entry entry = (Object2IntMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetObject2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetObject2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetObject2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ObjectSet<K> keySet();

    ObjectSet<Entry<K>> object2IntEntrySet();

    @Override // it.unimi.dsi.fastutil.Function
    int size();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Collection<Integer> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Integer>> entrySet() {
        return object2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Object2IntFunction, it.unimi.dsi.fastutil.Function
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
