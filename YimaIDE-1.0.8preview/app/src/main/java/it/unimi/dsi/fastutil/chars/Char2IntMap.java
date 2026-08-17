package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2IntMap extends Char2IntFunction, Map<Character, Integer> {

    public interface Entry extends Map.Entry<Character, Integer> {
        char getCharKey();

        int getIntValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
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

    ObjectSet<Entry> char2IntEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2IntFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Character, ? super Integer> biConsumer) {
        ObjectSet<Entry> objectSetChar2IntEntrySet = char2IntEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: jf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2IntMap.Entry entry = (Char2IntMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Integer.valueOf(entry.getIntValue()));
            }
        };
        if (objectSetChar2IntEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2IntEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2IntEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Integer> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Integer>> entrySet2() {
        return char2IntEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2IntFunction, it.unimi.dsi.fastutil.Function
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
    default Integer compute(Character ch, BiFunction<? super Character, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfAbsent(Character ch, Function<? super Character, ? extends Integer> function) {
        return (Integer) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer computeIfPresent(Character ch, BiFunction<? super Character, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer getOrDefault(Object obj, Integer num) {
        return (Integer) super.getOrDefault(obj, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer merge(Character ch, Integer num, BiFunction<? super Integer, ? super Integer, ? extends Integer> biFunction) {
        return (Integer) super.merge(ch, num, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Integer put(Character ch, Integer num) {
        return super.put(ch, num);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer putIfAbsent(Character ch, Integer num) {
        return (Integer) super.putIfAbsent(ch, num);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Integer num, Integer num2) {
        return super.replace(ch, num, num2);
    }

    @Override // java.util.Map
    @Deprecated
    default Integer replace(Character ch, Integer num) {
        return (Integer) super.replace(ch, num);
    }
}
