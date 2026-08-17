package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Key;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ProcessingContext {
    private Map<Object, Object> myMap;
    private SharedProcessingContext mySharedContext;
    private Object singleKey;
    private Object singleValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "com/intellij/util/ProcessingContext";
                break;
            case 2:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "key";
                break;
            case 3:
                objArr[0] = "value";
                break;
            default:
                objArr[0] = "sharedContext";
                break;
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/ProcessingContext";
        } else {
            objArr[1] = "getSharedContext";
        }
        switch (i) {
            case 1:
                break;
            case 2:
            case 3:
            case 4:
                objArr[2] = "put";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "get";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "putInternal";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public ProcessingContext(SharedProcessingContext sharedProcessingContext) {
        if (sharedProcessingContext == null) {
            $$$reportNull$$$0(0);
        }
        this.mySharedContext = sharedProcessingContext;
    }

    private void putInternal(Object obj, Object obj2) {
        if (obj == null) {
            $$$reportNull$$$0(7);
        }
        if (this.singleKey == null && this.myMap == null) {
            this.singleKey = obj;
            this.singleValue = obj2;
            return;
        }
        if (this.myMap == null) {
            HashMap map = new HashMap(1);
            this.myMap = map;
            map.put(this.singleKey, this.singleValue);
            this.singleKey = null;
            this.singleValue = null;
        }
        this.myMap.put(obj, obj2);
    }

    public Object get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(6);
        }
        if (obj.equals(this.singleKey)) {
            return this.singleValue;
        }
        Map<Object, Object> map = this.myMap;
        if (map == null) {
            return null;
        }
        return map.get(obj);
    }

    public void put(Object obj, Object obj2) {
        if (obj == null) {
            $$$reportNull$$$0(2);
        }
        if (obj2 == null) {
            $$$reportNull$$$0(3);
        }
        putInternal(obj, obj2);
    }

    public ProcessingContext() {
    }

    public <T> void put(Key<T> key, T t) {
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        putInternal(key, t);
    }
}
