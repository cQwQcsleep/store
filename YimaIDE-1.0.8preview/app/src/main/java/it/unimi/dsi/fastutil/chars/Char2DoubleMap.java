package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2DoubleMap extends Char2DoubleFunction, Map<Character, Double> {

    public interface Entry extends Map.Entry<Character, Double> {
        char getCharKey();

        double getDoubleValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Double getValue() {
            return Double.valueOf(getDoubleValue());
        }

        double setValue(double d);

        @Override // java.util.Map.Entry
        @Deprecated
        default Double setValue(Double d) {
            return Double.valueOf(setValue(d.doubleValue()));
        }
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> char2DoubleEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(double d);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Double) obj).doubleValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super Double> biConsumer) {
        ObjectSet<Entry> objectSetChar2DoubleEntrySet = char2DoubleEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: hf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2DoubleMap.Entry entry = (Char2DoubleMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Double.valueOf(entry.getDoubleValue()));
            }
        };
        if (objectSetChar2DoubleEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2DoubleEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2DoubleEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Double> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Double>> entrySet2() {
        return char2DoubleEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2DoubleFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Double get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double compute(Character ch, BiFunction<? super Character, ? super Double, ? extends Double> biFunction) {
        return (Double) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfAbsent(Character ch, Function<? super Character, ? extends Double> function) {
        return (Double) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Double computeIfPresent(Character ch, BiFunction<? super Character, ? super Double, ? extends Double> biFunction) {
        return (Double) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Double getOrDefault(Object obj, Double d) {
        return (Double) super.getOrDefault(obj, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double merge(Character ch, Double d, BiFunction<? super Double, ? super Double, ? extends Double> biFunction) {
        return (Double) super.merge(ch, d, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Double put(Character ch, Double d) {
        return super.put(ch, d);
    }

    @Override // java.util.Map
    @Deprecated
    default Double putIfAbsent(Character ch, Double d) {
        return (Double) super.putIfAbsent(ch, d);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Double d, Double d2) {
        return super.replace(ch, d, d2);
    }

    @Override // java.util.Map
    @Deprecated
    default Double replace(Character ch, Double d) {
        return (Double) super.replace(ch, d);
    }
}
