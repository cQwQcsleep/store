package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.bytes.Byte2BooleanMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2BooleanMap extends Byte2BooleanFunction, Map<Byte, Boolean> {

    public interface Entry extends Map.Entry<Byte, Boolean> {
        boolean getBooleanValue();

        byte getByteKey();

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        @Deprecated
        default Byte getKey() {
            return Byte.valueOf(getByteKey());
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

    ObjectSet<Entry> byte2BooleanEntrySet();

    @Override // it.unimi.dsi.fastutil.Function, java.util.Map
    default void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default void forEach(final BiConsumer<? super Byte, ? super Boolean> biConsumer) {
        ObjectSet<Entry> objectSetByte2BooleanEntrySet = byte2BooleanEntrySet();
        Consumer<? super T> consumer = new Consumer() { // from class: d31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Byte2BooleanMap.Entry entry = (Byte2BooleanMap.Entry) obj;
                biConsumer.accept(Byte.valueOf(entry.getByteKey()), Boolean.valueOf(entry.getBooleanValue()));
            }
        };
        if (objectSetByte2BooleanEntrySet instanceof FastEntrySet) {
            ((FastEntrySet) objectSetByte2BooleanEntrySet).fastForEach(consumer);
        } else {
            objectSetByte2BooleanEntrySet.forEach(consumer);
        }
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: keySet, reason: merged with bridge method [inline-methods] */
    Set<Byte> keySet2();

    @Override // java.util.Map
    /* JADX INFO: renamed from: values, reason: merged with bridge method [inline-methods] */
    Collection<Boolean> values2();

    @Override // java.util.Map
    @Deprecated
    /* JADX INFO: renamed from: entrySet, reason: merged with bridge method [inline-methods] */
    default Set<Map.Entry<Byte, Boolean>> entrySet2() {
        return byte2BooleanEntrySet();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanFunction, it.unimi.dsi.fastutil.Function
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
    default Boolean put(Byte b, Boolean bool) {
        return super.put(b, bool);
    }
}
