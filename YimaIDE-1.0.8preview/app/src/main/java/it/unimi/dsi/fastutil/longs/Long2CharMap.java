package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.longs.Long2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2CharMap extends Long2CharFunction, Map<Long, Character> {

    public interface Entry extends Map.Entry<Long, Character> {
        char getCharValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Long getKey() {
            return Long.valueOf(getLongKey());
        }

        long getLongKey();

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

    @Override // it.unimi.dsi.fastutil.longs.Long2CharFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Long, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetLong2CharEntrySet = long2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: gh9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Long2CharMap.Entry entry = (Long2CharMap.Entry) obj;
                biConsumer.accept(Long.valueOf(entry.getLongKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetLong2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetLong2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetLong2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Long> keySet2();

    ObjectSet<Entry> long2CharEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Long, Character>> entrySet2() {
        return long2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.longs.Long2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character compute(Long l, BiFunction<? super Long, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Long l, Function<? super Long, ? extends Character> function) {
        return (Character) super.computeIfAbsent(l, (Function<? super Long, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Long l, BiFunction<? super Long, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(l, (BiFunction<? super Long, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Long l, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(l, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Long l, Character ch) {
        return super.put(l, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Long l, Character ch) {
        return (Character) super.putIfAbsent(l, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Long l, Character ch, Character ch2) {
        return super.replace(l, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Long l, Character ch) {
        return (Character) super.replace(l, ch);
    }
}
