package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2ObjectMap<V> extends Short2ObjectFunction<V>, Map<Short, V> {

    public interface Entry<V> extends Map.Entry<Short, V> {
        @Override // java.util.Map.Entry
        @Deprecated
        default Short getKey() {
            return Short.valueOf(getShortKey());
        }

        short getShortKey();
    }

    public interface FastEntrySet<V> extends ObjectSet<Entry<V>> {
        /* JADX WARN: Multi-variable type inference failed */
        default void fastForEach(Consumer<? super Entry<V>> consumer) {
            forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.shorts.Short2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Short, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetShort2ObjectEntrySet = short2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: t9d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Short2ObjectMap.Entry entry = (Short2ObjectMap.Entry) obj;
                biConsumer.accept(Short.valueOf(entry.getShortKey()), entry.getValue());
            }
        };
        if (objectSetShort2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetShort2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetShort2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.shorts.Short2ObjectFunction, it.unimi.dsi.fastutil.Function
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
    Set<Short> keySet2();

    @Override // it.unimi.dsi.fastutil.shorts.Short2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    ObjectSet<Entry<V>> short2ObjectEntrySet();

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Short, V>> entrySet() {
        return short2ObjectEntrySet();
    }

    @Override // java.util.Map
    @Deprecated
    default V put(Short sh, V v) {
        return (V) super.put(sh, (Object) v);
    }
}
