package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2CharMap extends Char2CharFunction, Map<Character, Character> {

    public interface Entry extends Map.Entry<Character, Character> {
        char getCharKey();

        char getCharValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
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

    ObjectSet<Entry> char2CharEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2CharFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Character, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetChar2CharEntrySet = char2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: gf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2CharMap.Entry entry = (Char2CharMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetChar2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Character>> entrySet2() {
        return char2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character compute(Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Character ch, Function<? super Character, ? extends Character> function) {
        return (Character) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Character ch, Character ch2, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(ch, ch2, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Character ch, Character ch2) {
        return super.put(ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Character ch, Character ch2) {
        return (Character) super.putIfAbsent(ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Character ch2, Character ch3) {
        return super.replace(ch, ch2, ch3);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Character ch, Character ch2) {
        return (Character) super.replace(ch, ch2);
    }
}
