package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.chars.Char2BooleanMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2BooleanMap extends Char2BooleanFunction, Map<Character, Boolean> {

    public interface Entry extends Map.Entry<Character, Boolean> {
        boolean getBooleanValue();

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
        default Boolean getValue() {
            return Boolean.valueOf(getBooleanValue());
        }

        @Override // java.util.Map.Entry
        @Deprecated
        default Boolean setValue(Boolean bool) {
            return Boolean.valueOf(setValue(bool.booleanValue()));
        }

        boolean setValue(boolean z);
    }

    public interface FastEntrySet extends ObjectSet<Entry> {
        default void fastForEach(Consumer<? super Entry> consumer) {
            forEach(consumer);
        }
    }

    ObjectSet<Entry> char2BooleanEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2BooleanFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Boolean) obj).booleanValue());
    }

    boolean containsValue(boolean z);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Character, ? super Boolean> biConsumer) {
        ObjectSet<Entry> objectSetChar2BooleanEntrySet = char2BooleanEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: ef1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Char2BooleanMap.Entry entry = (Char2BooleanMap.Entry) obj;
                biConsumer.accept(Character.valueOf(entry.getCharKey()), Boolean.valueOf(entry.getBooleanValue()));
            }
        };
        if (objectSetChar2BooleanEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetChar2BooleanEntrySet).fastForEach(consumer);
        } else {
            objectSetChar2BooleanEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Character> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Boolean> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Character, Boolean>> entrySet2() {
        return char2BooleanEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.chars.Char2BooleanFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Boolean get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default Boolean getOrDefault(Object obj, Boolean bool) {
        return (Boolean) super.getOrDefault(obj, bool);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Boolean put(Character ch, Boolean bool) {
        return super.put(ch, bool);
    }
}
