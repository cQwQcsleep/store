package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2IntMap extends Byte2IntFunction, Map<Byte, Integer> {

    public interface Entry extends Map.Entry<Byte, Integer> {
        byte getByteKey();

        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
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
    }

    ObjectSet<Entry> byte2IntEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Byte, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetByte2IntEntrySet = byte2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: i31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2IntMap.Entry entry = (Byte2IntMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetByte2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Integer> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Integer>> entrySet2() {
        return byte2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntFunction, it.unimi.dsi.fastutil.Function
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
    default Integer compute(Byte b, BiFunction<? super Byte, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Byte b, Function<? super Byte, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Byte b, BiFunction<? super Byte, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Byte b, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(b, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Byte b, Integer num) {
        return super.put(b, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Byte b, Integer num) {
        return (Integer) super.putIfAbsent(b, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Integer num, Integer num2) {
        return super.replace(b, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Byte b, Integer num) {
        return (Integer) super.replace(b, num);
    }
}
