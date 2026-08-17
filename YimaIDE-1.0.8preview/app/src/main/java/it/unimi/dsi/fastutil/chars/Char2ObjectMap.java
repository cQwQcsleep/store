package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2ObjectMap<V> extends Char2ObjectFunction<V>, Map<Character, V> {

    public interface Entry<V> extends Map.Entry<Character, V> {
        char getCharKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Character getKey() {
            return Character.valueOf(getCharKey());
        }
    }

    public interface FastEntrySet<V> extends ObjectSet<Entry<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<V>> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry<V>> char2ObjectEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetChar2ObjectEntrySet = char2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: lf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2ObjectMap.Entry entry = (Char2ObjectMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), entry.getValue());
            }
        };
        if (objectSetChar2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default V get(Object obj) {
        return (V) super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(obj, v);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // it.unimi.dsi.fastutil.chars.Char2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Character, V>> entrySet() {
        return char2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Character ch, V v) {
        return (V) super.put(ch, (Object) v);
    }
}
