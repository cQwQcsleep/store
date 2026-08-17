package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2CharMap extends Float2CharFunction, Map<Float, Character> {

    public interface Entry extends Map.Entry<Float, Character> {
        char getCharValue();

        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
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

    @Override // it.unimi.dsi.fastutil.floats.Float2CharFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2CharEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetFloat2CharEntrySet = float2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: qh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2CharMap.Entry entry = (Float2CharMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetFloat2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Character>> entrySet2() {
        return float2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character compute(Float f, BiFunction<? super Float, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Float f, Function<? super Float, ? extends Character> function) {
        return (Character) super.computeIfAbsent(f, (Function<? super Float, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Float f, BiFunction<? super Float, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(f, (BiFunction<? super Float, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Float f, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(f, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Float f, Character ch) {
        return super.put(f, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Float f, Character ch) {
        return (Character) super.putIfAbsent(f, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Float f, Character ch, Character ch2) {
        return super.replace(f, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Float f, Character ch) {
        return (Character) super.replace(f, ch);
    }
}
