package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2BooleanMap extends Int2BooleanFunction, Map<Integer, Boolean> {

    public interface Entry extends Map.Entry<Integer, Boolean> {
        boolean getBooleanValue();

        int getIntKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Integer getKey() {
            return Integer.valueOf(getIntKey());
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

    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Integer, ? super Boolean> biConsumer) {
        ObjectSet<Entry> objectSetInt2BooleanEntrySet = int2BooleanEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: lr6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Int2BooleanMap.Entry entry = (Int2BooleanMap.Entry) obj;
                biConsumer.accept(Integer.valueOf(entry.getIntKey()), Boolean.valueOf(entry.getBooleanValue()));
            }
        };
        if (objectSetInt2BooleanEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetInt2BooleanEntrySet).fastForEach(consumer);
        } else {
            objectSetInt2BooleanEntrySet.forEach(consumer);
        }
    }

    ObjectSet<Entry> int2BooleanEntrySet();

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Integer> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Boolean> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Integer, Boolean>> entrySet2() {
        return int2BooleanEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default Boolean put(Integer num, Boolean bool) {
        return super.put(num, bool);
    }
}
