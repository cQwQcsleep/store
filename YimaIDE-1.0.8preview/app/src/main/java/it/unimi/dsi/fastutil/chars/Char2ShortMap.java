package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2ShortMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2ShortMap extends Char2ShortFunction, Map<Character, Short> {

    public interface Entry extends Map.Entry<Character, Short> {
        char getCharKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
        }

        short getShortValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getValue() {
            return Short.valueOf(getShortValue());
        }

        @Override // java.util.Map.Entry
        @Deprecated
        default Short setValue(Short sh) {
            return Short.valueOf(setValue(sh.shortValue()));
        }

        short setValue(short s);
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> char2ShortEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Short) obj).shortValue());
    }

    boolean containsValue(short s);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super Short> biConsumer) {
        ObjectSet<Entry> objectSetChar2ShortEntrySet = char2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: nf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2ShortMap.Entry entry = (Char2ShortMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetChar2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Short>> entrySet2() {
        return char2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        return super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default Short remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short compute(Character ch, BiFunction<? super Character, ? super Short, ? extends Short> biFunction) {
        return (Short) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfAbsent(Character ch, Function<? super Character, ? extends Short> function) {
        return (Short) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Short computeIfPresent(Character ch, BiFunction<? super Character, ? super Short, ? extends Short> biFunction) {
        return (Short) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short merge(Character ch, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge(ch, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(Character ch, Short sh) {
        return super.put(ch, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(Character ch, Short sh) {
        return (Short) super.putIfAbsent(ch, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Short sh, Short sh2) {
        return super.replace(ch, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(Character ch, Short sh) {
        return (Short) super.replace(ch, sh);
    }
}
