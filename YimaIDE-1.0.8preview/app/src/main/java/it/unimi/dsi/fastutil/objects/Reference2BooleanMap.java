package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.Reference2BooleanMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Reference2BooleanMap<K> extends Reference2BooleanFunction<K>, Map<K, Boolean> {

    public interface Entry<K> extends Map.Entry<K, Boolean> {
        boolean getBooleanValue();

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
        return containsValue(((Boolean) obj).booleanValue());
    }

    boolean containsValue(boolean z);

    @Override // java.util.Map
    default void forEach(final BiConsumer<? super K, ? super Boolean> biConsumer) {
        ObjectSet<Entry<K>> objectSetReference2BooleanEntrySet = reference2BooleanEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: v8c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Reference2BooleanMap.Entry entry = (Reference2BooleanMap.Entry) obj;
                biConsumer.accept(entry.getKey(), Boolean.valueOf(entry.getBooleanValue()));
            }
        };
        if (objectSetReference2BooleanEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetReference2BooleanEntrySet).fastForEach(consumer);
        } else {
            objectSetReference2BooleanEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    ReferenceSet<K> keySet();

    ObjectSet<Entry<K>> reference2BooleanEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Boolean> values2();

    @Override // java.util.Map
    @Deprecated
    default ObjectSet<Map.Entry<K, Boolean>> entrySet() {
        return reference2BooleanEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.objects.Reference2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default Boolean put(K k, Boolean bool) {
        return super.put((Object) k, bool);
    }
}
