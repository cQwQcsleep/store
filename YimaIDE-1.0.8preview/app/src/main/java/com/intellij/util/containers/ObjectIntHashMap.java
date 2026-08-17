package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
public final class ObjectIntHashMap<K> implements ObjectIntMap<K> {
    private final Object2IntMap<K> myMap;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6 || i == 7) ? 2 : 3];
        if (i == 5 || i == 6 || i == 7) {
            objArr[0] = "com/intellij/util/containers/ObjectIntHashMap";
        } else {
            objArr[0] = "key";
        }
        if (i == 5) {
            objArr[1] = "keySet";
        } else if (i == 6) {
            objArr[1] = "values";
        } else if (i != 7) {
            objArr[1] = "com/intellij/util/containers/ObjectIntHashMap";
        } else {
            objArr[1] = "entries";
        }
        switch (i) {
            case 1:
                objArr[2] = "getOrDefault";
                break;
            case 2:
                objArr[2] = "put";
                break;
            case 3:
                objArr[2] = "remove";
                break;
            case 4:
                objArr[2] = "containsKey";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                break;
            default:
                objArr[2] = "get";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public ObjectIntHashMap(int i) {
        Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap(i);
        this.myMap = object2IntOpenHashMap;
        object2IntOpenHashMap.defaultReturnValue(-1);
    }

    public int get(K k) {
        if (k == null) {
            $$$reportNull$$$0(0);
        }
        return this.myMap.getInt(k);
    }

    public int put(K k, int i) {
        if (k == null) {
            $$$reportNull$$$0(2);
        }
        return this.myMap.put(k, i);
    }

    public ObjectIntHashMap() {
        this(10);
    }
}
