package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.floats.Float2BooleanMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2BooleanMap extends Float2BooleanFunction, Map<Float, Boolean> {

    public interface Entry extends Map.Entry<Float, Boolean> {
        boolean getBooleanValue();

        float getFloatKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Float getKey() {
            return Float.valueOf(getFloatKey());
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

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2BooleanFunction, it.unimi.dsi.fastutil.Function
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

    ObjectSet<Entry> float2BooleanEntrySet();

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super Float, ? super Boolean> biConsumer) {
        ObjectSet<Entry> objectSetFloat2BooleanEntrySet = float2BooleanEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: oh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float2BooleanMap.Entry entry = (Float2BooleanMap.Entry) obj;
                biConsumer.accept(Float.valueOf(entry.getFloatKey()), Boolean.valueOf(entry.getBooleanValue()));
            }
        };
        if (objectSetFloat2BooleanEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetFloat2BooleanEntrySet).fastForEach(consumer);
        } else {
            objectSetFloat2BooleanEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Float> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Boolean> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Float, Boolean>> entrySet2() {
        return float2BooleanEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.floats.Float2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default Boolean put(Float f, Boolean bool) {
        return super.put(f, bool);
    }
}
