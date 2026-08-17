package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2IntMap extends Int2IntFunction, Map<Integer, Integer> {

    public interface Entry extends Map.Entry<Integer, Integer> {
        int getIntKey();

        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
        }

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

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }

        ObjectIterator<Entry> fastIterator();
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2IntFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
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
    default void forEach(final BiConsumer<? super Integer, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetInt2IntEntrySet = int2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: qr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2IntMap.Entry entry = (Int2IntMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetInt2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2IntEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2IntEntrySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Set<Integer> keySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    Collection<Integer> values();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Integer>> entrySet2() {
        return int2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.ints.Int2IntFunction, it.unimi.dsi.fastutil.Function
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
    default Integer compute(Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Integer num, Function<? super Integer, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(num, (Function<? super Integer, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Integer num, Integer num2, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(num, num2, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Integer num, Integer num2) {
        return super.put(num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Integer num, Integer num2) {
        return (Integer) super.putIfAbsent(num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Integer num, Integer num2, Integer num3) {
        return super.replace(num, num2, num3);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Integer num, Integer num2) {
        return (Integer) super.replace(num, num2);
    }
}
