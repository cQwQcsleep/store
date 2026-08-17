package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2ObjectMap<V> extends Float2ObjectFunction<V>, Map<Float, V> {

    public interface Entry<V> extends Map.Entry<Float, V> {
        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
        }
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

    @Override // it.unimi.dsi.fastutil.floats.Float2ObjectFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    ObjectSet<Entry<V>> float2ObjectEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetFloat2ObjectEntrySet = float2ObjectEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: vh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2ObjectMap.Entry entry = (Float2ObjectMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), entry.getValue());
            }
        };
        if (objectSetFloat2ObjectEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2ObjectEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2ObjectEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2ObjectFunction, it.unimi.dsi.fastutil.Function
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
    Set<Float> keySet2();

    @Override // it.unimi.dsi.fastutil.floats.Float2ObjectFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ObjectCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Float, V>> entrySet() {
        return float2ObjectEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Float f, V v) {
        return (V) super.put(f, (Object) v);
    }
}
