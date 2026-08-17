package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2CharMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2CharMap extends Byte2CharFunction, Map<Byte, Character> {

    public interface Entry extends Map.Entry<Byte, Character> {
        byte getByteKey();

        char getCharValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
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

    ObjectSet<Entry> byte2CharEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2CharFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Byte, ? super Character> biConsumer) {
        ObjectSet<Entry> objectSetByte2CharEntrySet = byte2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: f31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2CharMap.Entry entry = (Byte2CharMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetByte2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Character>> entrySet2() {
        return byte2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character compute(Byte b, BiFunction<? super Byte, ? super Character, ? extends Character> biFunction) {
        return (Character) super.compute(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfAbsent(Byte b, Function<? super Byte, ? extends Character> function) {
        return (Character) super.computeIfAbsent(b, (Function<? super Byte, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Character computeIfPresent(Byte b, BiFunction<? super Byte, ? super Character, ? extends Character> biFunction) {
        return (Character) super.computeIfPresent(b, (BiFunction<? super Byte, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character merge(Byte b, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge(b, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(Byte b, Character ch) {
        return super.put(b, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(Byte b, Character ch) {
        return (Character) super.putIfAbsent(b, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Byte b, Character ch, Character ch2) {
        return super.replace(b, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(Byte b, Character ch) {
        return (Character) super.replace(b, ch);
    }
}
