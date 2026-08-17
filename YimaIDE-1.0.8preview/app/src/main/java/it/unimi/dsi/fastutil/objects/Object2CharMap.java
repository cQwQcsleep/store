package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Object2CharMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2CharMap<K> extends Object2CharFunction<K>, Map<K, Character> {

    public interface Entry<K> extends Map.Entry<K, Character> {
        char getCharValue();

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

    public interface FastEntrySet<K> extends ObjectSet<Entry<K>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<K>> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
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
    default void forEach(final BiConsumer<? super K, ? super Character> biConsumer) {
        ObjectSet<Entry<K>> objectSetObject2CharEntrySet = object2CharEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: lna
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Object2CharMap.Entry entry = (Object2CharMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Character.valueOf(entry.getCharValue()));
            }
        };
        if (objectSetObject2CharEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetObject2CharEntrySet).fastForEach(consumer);
        } else {
            objectSetObject2CharEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ObjectSet<K> keySet();

    ObjectSet<Entry<K>> object2CharEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Character> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Character>> entrySet() {
        return object2CharEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Object2CharFunction, it.unimi.dsi.fastutil.Function
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
    default Character getOrDefault(Object obj, Character ch) {
        return (Character) super.getOrDefault(obj, ch);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Character merge(K k, Character ch, BiFunction<? super Character, ? super Character, ? extends Character> biFunction) {
        return (Character) super.merge((Object) k, ch, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Character put(K k, Character ch) {
        return super.put((Object) k, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default Character putIfAbsent(K k, Character ch) {
        return (Character) super.putIfAbsent((Object) k, ch);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Character ch, Character ch2) {
        return super.replace((Object) k, ch, ch2);
    }

    @Override // java.util.Map
    @Deprecated
    default Character replace(K k, Character ch) {
        return (Character) super.replace((Object) k, ch);
    }
}
