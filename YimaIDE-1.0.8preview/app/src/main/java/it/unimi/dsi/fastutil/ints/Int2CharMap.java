package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2CharMap extends Int2CharFunction, Map<Integer, Character> {

    public interface Entry extends Map.Entry<Integer, Character> {
        char getCharValue();

        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getValue() {
            return Character.valueOf(getCharValue());
        }

        char setValue(char c);

        @Override // java.util.Map.Entry
        @Deprecated
        default Character setValue(Character ch) {
            return Character.valueOf(setValue(ch.charValue()));
        }
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2CharFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(char c);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Character) obj).charValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Integer, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetInt2CharEntrySet = int2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: nr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2CharMap.Entry entry = (Int2CharMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetInt2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2CharEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2CharEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Integer> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Character>> entrySet2() {
        return int2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.ints.Int2CharFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Character get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character compute(Integer num, BiFunction<? super Integer, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Integer num, Function<? super Integer, ? extends Character> function) {
        return (Character) super.computeIfAbsent(num, (Function<? super Integer, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Integer num, BiFunction<? super Integer, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(num, (BiFunction<? super Integer, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Integer num, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(num, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Integer num, Character ch) {
        return super.put(num, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Integer num, Character ch) {
        return (Character) super.putIfAbsent(num, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Integer num, Character ch, Character ch2) {
        return super.replace(num, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Integer num, Character ch) {
        return (Character) super.replace(num, ch);
    }
}
