package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2ReferenceMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2ReferenceMap<V> extends Float2ReferenceFunction<V>, Map<Float, V> {

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

    @Override // it.unimi.dsi.fastutil.floats.Float2ReferenceFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    ObjectSet<Entry<V>> float2ReferenceEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super V> biConsumer) {
        ObjectSet<Entry<V>> objectSetFloat2ReferenceEntrySet = float2ReferenceEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: wh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2ReferenceMap.Entry entry = (Float2ReferenceMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), entry.getValue());
            }
        };
        if (objectSetFloat2ReferenceEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2ReferenceEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2ReferenceEntrySet.forEach(consumer);
        }
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2ReferenceFunction, it.unimi.dsi.fastutil.Function
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

    @Override // it.unimi.dsi.fastutil.floats.Float2ReferenceFunction, java.util.Map
    @Deprecated
    default V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    ReferenceCollection<V> values();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<Float, V>> entrySet() {
        return float2ReferenceEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default V put(Float f, V v) {
        return (V) super.put(f, (Object) v);
    }
}
