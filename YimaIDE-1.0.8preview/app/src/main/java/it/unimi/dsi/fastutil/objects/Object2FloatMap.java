package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2FloatMap<K> extends Object2FloatFunction<K>, Map<K, Float> {

    public interface Entry<K> extends Map.Entry<K, Float> {
        float getFloatValue();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getValue() {
            return Float.valueOf(getFloatValue());
        }

        float setValue(float f);

        @Override // java.util.Map.Entry
        @Deprecated
        default Float setValue(Float f) {
            return Float.valueOf(setValue(f.floatValue()));
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

    boolean containsValue(float f);

    @Override // java.util.Map
    @Deprecated
    default boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return containsValue(((Float) obj).floatValue());
    }

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Float> biConsumer) {
        ObjectSet<Entry<K>> objectSetObject2FloatEntrySet = object2FloatEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: nna
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Object2FloatMap.Entry entry = (Object2FloatMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Float.valueOf(entry.getFloatValue()));
            }
        };
        if (objectSetObject2FloatEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetObject2FloatEntrySet).fastForEach(consumer);
        } else {
            objectSetObject2FloatEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ObjectSet<K> keySet();

    ObjectSet<Entry<K>> object2FloatEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Float> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Float>> entrySet() {
        return object2FloatEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Object2FloatFunction, it.unimi.dsi.fastutil.Function
    @Deprecated
    default Float get(Object obj) {
        return super.get(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float getOrDefault(Object obj, Float f) {
        return (Float) super.getOrDefault(obj, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    @Deprecated
    default Float merge(K k, Float f, BiFunction<? super Float, ? super Float, ? extends Float> biFunction) {
        return (Float) super.merge((Object) k, f, biFunction);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Deprecated
    default Float put(K k, Float f) {
        return super.put((Object) k, f);
    }

    @Override // java.util.Map
    @Deprecated
    default Float putIfAbsent(K k, Float f) {
        return (Float) super.putIfAbsent((Object) k, f);
    }

    @Override // java.util.Map
    @Deprecated
    default boolean replace(K k, Float f, Float f2) {
        return super.replace((Object) k, f, f2);
    }

    @Override // java.util.Map
    @Deprecated
    default Float replace(K k, Float f) {
        return (Float) super.replace((Object) k, f);
    }
}
