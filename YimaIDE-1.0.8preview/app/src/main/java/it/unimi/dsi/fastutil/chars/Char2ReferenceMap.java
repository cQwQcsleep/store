package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2ReferenceMap<V> extends Char2ReferenceFunction<V>, Map<Character, V> {

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

    ObjectSet<Entry<V>> char2ReferenceEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ReferenceFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetChar2ReferenceEntrySet = char2ReferenceEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: mf1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2ReferenceMap.Entry entry = (Char2ReferenceMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), entry.getValue());
            }
        };
        if (objectSetChar2ReferenceEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2ReferenceEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2ReferenceEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ReferenceFunction, it.unimi.dsi.fastutil.Function
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

    @Override // it.unimi.dsi.fastutil.chars.Char2ReferenceFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ReferenceCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Character, V>> entrySet() {
        return char2ReferenceEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Character ch, V v) {
        return (V) super.put(ch, (Object) v);
    }
}
