package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2ByteMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2ByteMap extends Char2ByteFunction, Map<Character, Byte> {

    public interface Entry extends Map.Entry<Character, Byte> {
        byte getByteValue();

        char getCharKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getValue() {
            return Byte.valueOf(getByteValue());
        }

        byte setValue(byte b);

        @Override // java.util.Map.Entry
        @Deprecated
        default Byte setValue(Byte b) {
            return Byte.valueOf(setValue(b.byteValue()));
        }
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> char2ByteEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ByteFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    boolean containsValue(byte b);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Byte) obj).byteValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super Byte> biConsumer) {
        ObjectSet<Entry> objectSetChar2ByteEntrySet = char2ByteEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ff1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2ByteMap.Entry entry = (Char2ByteMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Byte.valueOf(entry.getByteValue()));
            }
        };
        if (objectSetChar2ByteEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2ByteEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2ByteEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Byte> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Byte>> entrySet2() {
        return char2ByteEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2ByteFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Byte get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte compute(Character ch, BiFunction<? super Character, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.compute(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfAbsent(Character ch, Function<? super Character, ? extends Byte> function) {
        return (Byte) super.computeIfAbsent(ch, (Function<? super Character, ? extends V>) function);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte computeIfPresent(Character ch, BiFunction<? super Character, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.computeIfPresent(ch, (BiFunction<? super Character, ? super V, ? extends V>) biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte getOrDefault(Object obj, Byte b) {
        return (Byte) super.getOrDefault(obj, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte merge(Character ch, Byte b, BiFunction<? super Byte, ? super Byte, ? extends Byte> biFunction) {
        return (Byte) super.merge(ch, b, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Byte put(Character ch, Byte b) {
        return super.put(ch, b);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte putIfAbsent(Character ch, Byte b) {
        return (Byte) super.putIfAbsent(ch, b);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(Character ch, Byte b, Byte b2) {
        return super.replace(ch, b, b2);
    }

    @Override // java.util.Map
    @Deprecated
    default Byte replace(Character ch, Byte b) {
        return (Byte) super.replace(ch, b);
    }
}
