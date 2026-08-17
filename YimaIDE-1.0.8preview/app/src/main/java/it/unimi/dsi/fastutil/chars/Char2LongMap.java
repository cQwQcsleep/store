package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2LongMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2LongMap extends Char2LongFunction, Map<Character, Long> {

    public interface Entry extends Map.Entry<Character, Long> {
        char getCharKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
        }

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

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> char2LongEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2LongFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
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
    default void forEach(final BiConsumer<? super Character, ? super Long> biConsumer) {
        ObjectSet<Entry> objectSetChar2LongEntrySet = char2LongEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: kf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2LongMap.Entry entry = (Char2LongMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Long.valueOf(entry.getLongValue()));
            }
        };
        if (objectSetChar2LongEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2LongEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2LongEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Long> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Long>> entrySet2() {
        return char2LongEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2LongFunction, it.unimi.dsi.fastutil.Function
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
    default Long compute(Character ch, BiFunction<? super Character, ? super Long, ? extends Long> biFunction) {
        return (Long) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfAbsent(Character ch, Function<? super Character, ? extends Long> function) {
        return (Long) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Long computeIfPresent(Character ch, BiFunction<? super Character, ? super Long, ? extends Long> biFunction) {
        return (Long) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Long getOrDefault(Object obj, Long l) {
        return (Long) super.getOrDefault(obj, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long merge(Character ch, Long l, BiFunction<? super Long, ? super Long, ? extends Long> biFunction) {
        return (Long) super.merge(ch, l, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Long put(Character ch, Long l) {
        return super.put(ch, l);
    }

    @Override // java.util.Map
    @Deprecated
    default Long putIfAbsent(Character ch, Long l) {
        return (Long) super.putIfAbsent(ch, l);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Long l, Long l2) {
        return super.replace(ch, l, l2);
    }

    @Override // java.util.Map
    @Deprecated
    default Long replace(Character ch, Long l) {
        return (Long) super.replace(ch, l);
    }
}
