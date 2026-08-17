package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.doubles.Double2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2CharMap extends Double2CharFunction, Map<Double, Character> {

    public interface Entry extends Map.Entry<Double, Character> {
        char getCharValue();

        double getDoubleKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getKey() {
            return Double.valueOf(getDoubleKey());
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

    @Override // it.unimi.dsi.fastutil.doubles.Double2CharFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> double2CharEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Double, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetDouble2CharEntrySet = double2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: mv3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Double2CharMap.Entry entry = (Double2CharMap.Entry) obj;
                biConsumer.accept(Double.valueOf(entry.getDoubleKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetDouble2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetDouble2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetDouble2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Double> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Double, Character>> entrySet2() {
        return double2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.doubles.Double2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character compute(Double d, BiFunction<? super Double, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Double d, Function<? super Double, ? extends Character> function) {
        return (Character) super.computeIfAbsent(d, (Function<? super Double, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Double d, BiFunction<? super Double, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(d, (BiFunction<? super Double, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Double d, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(d, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Double d, Character ch) {
        return super.put(d, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Double d, Character ch) {
        return (Character) super.putIfAbsent(d, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Double d, Character ch, Character ch2) {
        return super.replace(d, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Double d, Character ch) {
        return (Character) super.replace(d, ch);
    }
}
