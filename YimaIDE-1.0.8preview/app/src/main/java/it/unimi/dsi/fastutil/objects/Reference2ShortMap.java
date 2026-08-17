package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Reference2ShortMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Reference2ShortMap<K> extends Reference2ShortFunction<K>, Map<K, Short> {

    public interface Entry<K> extends Map.Entry<K, Short> {
        short getShortValue();

        @Override // java.util.Map.Entry
        @Deprecated
        default Short getValue() {
            return Short.valueOf(getShortValue());
        }

        @Override // java.util.Map.Entry
        @Deprecated
        default Short setValue(Short sh) {
            return Short.valueOf(setValue(sh.shortValue()));
        }

        short setValue(short s);
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

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Short) obj).shortValue());
    }

    boolean containsValue(short s);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Short> biConsumer) {
        ObjectSet<Entry<K>> objectSetReference2ShortEntrySet = reference2ShortEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: e9c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Reference2ShortMap.Entry entry = (Reference2ShortMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Short.valueOf(entry.getShortValue()));
            }
        };
        if (objectSetReference2ShortEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetReference2ShortEntrySet).fastForEach(consumer);
        } else {
            objectSetReference2ShortEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ReferenceSet<K> keySet();

    ObjectSet<Entry<K>> reference2ShortEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Short> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Short>> entrySet() {
        return reference2ShortEntrySet();
    }

    @Override // it.unimi.dsi.fastutil.objects.Reference2ShortFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Short get(Object obj) {
        return super.get(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default Short remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short getOrDefault(Object obj, Short sh) {
        return (Short) super.getOrDefault(obj, sh);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Short merge(K k, Short sh, BiFunction<? super Short, ? super Short, ? extends Short> biFunction) {
        return (Short) super.merge((Object) k, sh, biFunction);
    }

    @Override // java.util.Map
    @Deprecated
    default Short put(K k, Short sh) {
        return super.put((Object) k, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default Short putIfAbsent(K k, Short sh) {
        return (Short) super.putIfAbsent((Object) k, sh);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Short sh, Short sh2) {
        return super.replace((Object) k, sh, sh2);
    }

    @Override // java.util.Map
    @Deprecated
    default Short replace(K k, Short sh) {
        return (Short) super.replace((Object) k, sh);
    }
}
