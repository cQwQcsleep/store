package com.intellij.util.keyFMap;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;
import com.intellij.util.ArrayUtil;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Arrays;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class MapBackedFMap extends Int2ObjectOpenHashMap<Object> implements KeyFMap {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 9 || i == 11) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 9 || i == 11) ? 2 : 3];
        switch (i) {
            case 2:
            case 3:
                objArr[0] = "newValue";
                break;
            case 4:
                objArr[0] = "keys";
                break;
            case 5:
                objArr[0] = "values";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
                objArr[0] = "key";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "value";
                break;
            case 9:
            case 11:
                objArr[0] = "com/intellij/util/keyFMap/MapBackedFMap";
                break;
            case 12:
                objArr[0] = "other";
                break;
            default:
                objArr[0] = "oldMap";
                break;
        }
        if (i == 9) {
            objArr[1] = "minus";
        } else if (i != 11) {
            objArr[1] = "com/intellij/util/keyFMap/MapBackedFMap";
        } else {
            objArr[1] = "getKeys";
        }
        switch (i) {
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "plus";
                break;
            case 8:
                objArr[2] = "minus";
                break;
            case 9:
            case 11:
                break;
            case 10:
                objArr[2] = "get";
                break;
            case 12:
                objArr[2] = "equalsByReference";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 9 && i != 11) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private MapBackedFMap(MapBackedFMap mapBackedFMap, int i) {
        super(mapBackedFMap.size());
        if (mapBackedFMap == null) {
            $$$reportNull$$$0(0);
        }
        ObjectIterator objectIteratorFastIterator = mapBackedFMap.int2ObjectEntrySet().fastIterator();
        while (objectIteratorFastIterator.hasNext()) {
            Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry) objectIteratorFastIterator.next();
            int intKey = entry.getIntKey();
            if (intKey != i) {
                put(intKey, entry.getValue());
            }
        }
    }

    public static /* synthetic */ void b(StringBuilder sb, Int2ObjectMap.Entry entry) {
        sb.append(sb.length() == 0 ? "" : ", ");
        sb.append(Key.getKeyByIndex(entry.getIntKey()));
        sb.append(" -> ");
        sb.append(entry.getValue());
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> V get(Key<V> key) {
        if (key == null) {
            $$$reportNull$$$0(10);
        }
        return (V) get(key.hashCode());
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public Key<?>[] getKeys() {
        Key<?>[] keysByIndices = ArrayBackedFMap.getKeysByIndices(keySet().toIntArray());
        if (keysByIndices == null) {
            $$$reportNull$$$0(11);
        }
        return keysByIndices;
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public KeyFMap minus(Key<?> key) {
        if (key == null) {
            $$$reportNull$$$0(8);
        }
        int size = size();
        int iHashCode = key.hashCode();
        if (!containsKey(iHashCode)) {
            return this;
        }
        if (size != 9) {
            return new MapBackedFMap(this, iHashCode);
        }
        int[] intArray = keySet().toIntArray();
        int[] iArrRemove = ArrayUtil.remove(intArray, ArrayUtil.indexOf(intArray, iHashCode));
        Arrays.sort(iArrRemove);
        Object[] objArr = new Object[iArrRemove.length];
        for (int i = 0; i < iArrRemove.length; i++) {
            objArr[i] = get(iArrRemove[i]);
        }
        return new ArrayBackedFMap(iArrRemove, objArr);
    }

    @Override // com.intellij.util.keyFMap.KeyFMap
    public <V> KeyFMap plus(Key<V> key, V v) {
        if (key == null) {
            $$$reportNull$$$0(6);
        }
        if (v == null) {
            $$$reportNull$$$0(7);
        }
        int iHashCode = key.hashCode();
        return v == get(iHashCode) ? this : new MapBackedFMap(this, iHashCode, v);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int2ObjectEntrySet().fastForEach(new Consumer() { // from class: com.intellij.util.keyFMap.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MapBackedFMap.b(sb, (Int2ObjectMap.Entry) obj);
            }
        });
        return "[" + ((Object) sb) + "]";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    private MapBackedFMap(MapBackedFMap mapBackedFMap, int i, Object obj) {
        super(mapBackedFMap.size() + 1);
        if (mapBackedFMap == 0) {
            $$$reportNull$$$0(1);
        }
        if (obj == null) {
            $$$reportNull$$$0(2);
        }
        putAll(mapBackedFMap);
        put(i, obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapBackedFMap(int[] iArr, int i, Object[] objArr, Object obj) {
        super(iArr.length + 1);
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        if (iArr == null) {
            $$$reportNull$$$0(4);
        }
        if (objArr == null) {
            $$$reportNull$$$0(5);
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            put(iArr[i2], objArr[i2]);
        }
        put(i, obj);
    }
}
