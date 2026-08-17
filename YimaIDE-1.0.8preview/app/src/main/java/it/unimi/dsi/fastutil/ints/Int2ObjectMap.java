package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2ObjectMap<V> extends Int2ObjectFunction<V>, Map<Integer, V> {

    public interface Entry<V> extends Map.Entry<Integer, V> {
        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
        }
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

    default V computeIfAbsent(int i, Int2ObjectFunction<? extends V> int2ObjectFunction) {
        Objects.requireNonNull(int2ObjectFunction);
        V v = get(i);
        V vDefaultReturnValue = defaultReturnValue();
        if (v != vDefaultReturnValue || containsKey(i)) {
            return v;
        }
        if (!int2ObjectFunction.containsKey(i)) {
            return vDefaultReturnValue;
        }
        V v2 = int2ObjectFunction.get(i);
        put(i, v2);
        return v2;
    }

    boolean containsKey(int i);

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
    V defaultReturnValue();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Integer, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetInt2ObjectEntrySet = int2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: sr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), entry.getValue());
            }
        };
        if (objectSetInt2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    ObjectSet<Entry<V>> int2ObjectEntrySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Set<Integer> keySet();

    default boolean remove(int i, Object obj) {
        V v = get(i);
        if (!Objects.equals(v, obj)) {
            return false;
        }
        if (v == defaultReturnValue() && !containsKey(i)) {
            return false;
        }
        remove(i);
        return true;
    }

    @Override // it.unimi.dsi.fastutil.Function
    int size();

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Integer, V>> entrySet() {
        return int2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Integer num, V v) {
        return (V) super.put(num, (Object) v);
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2ObjectFunction
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }
}
