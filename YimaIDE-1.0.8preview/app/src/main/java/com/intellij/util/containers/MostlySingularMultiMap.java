package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import com.intellij.util.Processor;
import com.intellij.util.containers.MostlySingularMultiMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class MostlySingularMultiMap<K, V> implements Serializable {
    private static final long serialVersionUID = 2784473565881807109L;
    protected final Map<K, Object> myMap = CollectionFactory.createSmallMemoryFootprintMap();

    public static final class ValueList<V> extends ArrayList<V> {
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0013  */
    /* JADX WARN: Code duplicated, block: B:6:0x0009  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 6) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 6) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 3:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
            case 12:
                objArr[0] = "key";
                break;
            case 2:
            case 4:
                objArr[0] = "value";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[0] = "com/intellij/util/containers/MostlySingularMultiMap";
                break;
            case 8:
            case 9:
            case 10:
                objArr[0] = "p";
                break;
            case 13:
                objArr[0] = "name";
                break;
            case 18:
                objArr[0] = "other";
                break;
            default:
                objArr[0] = "map";
                break;
        }
        if (i != 6) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                    objArr[1] = "rawValueToCollection";
                    break;
                case 17:
                    objArr[1] = "emptyMap";
                    break;
                default:
                    objArr[1] = "com/intellij/util/containers/MostlySingularMultiMap";
                    break;
            }
        } else {
            objArr[1] = "keySet";
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "add";
                break;
            case 3:
            case 4:
                objArr[2] = "remove";
                break;
            case 5:
                objArr[2] = "removeAllValues";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 14:
            case 15:
            case 16:
            case 17:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "processForKey";
                break;
            case 9:
                objArr[2] = "processValue";
                break;
            case 10:
                objArr[2] = "processAllValues";
                break;
            case 11:
                objArr[2] = "containsKey";
                break;
            case 12:
                objArr[2] = "valuesForKey";
                break;
            case 13:
                objArr[2] = "get";
                break;
            case 18:
                objArr[2] = "addAll";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 6) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ String b(Map.Entry entry) {
        Object value = entry.getValue();
        if (!(value instanceof ValueList)) {
            value = Collections.singletonList(value);
        }
        return entry.getKey() + ": " + value.toString();
    }

    private boolean processValue(Processor<? super V> processor, Object obj) {
        if (processor == null) {
            $$$reportNull$$$0(9);
        }
        if (!(obj instanceof ValueList)) {
            return obj == null || processor.process(obj);
        }
        Iterator<V> it = ((ValueList) obj).iterator();
        while (it.hasNext()) {
            if (!processor.process(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void add(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        Object obj = this.myMap.get(k);
        if (obj == null) {
            this.myMap.put(k, v);
            return;
        }
        if (obj instanceof ValueList) {
            ((List) obj).add(v);
            return;
        }
        ValueList valueList = new ValueList();
        valueList.add(obj);
        valueList.add(v);
        this.myMap.put(k, valueList);
    }

    public boolean processAllValues(Processor<? super V> processor) {
        if (processor == null) {
            $$$reportNull$$$0(10);
        }
        Iterator<Object> it = this.myMap.values().iterator();
        while (it.hasNext()) {
            if (!processValue(processor, it.next())) {
                return false;
            }
        }
        return true;
    }

    public final boolean processForKey(K k, Processor<? super V> processor) {
        if (k == null) {
            $$$reportNull$$$0(7);
        }
        if (processor == null) {
            $$$reportNull$$$0(8);
        }
        return processValue(processor, this.myMap.get(k));
    }

    public final String toString() {
        return "{" + StringUtil.join(this.myMap.entrySet(), new Function() { // from class: f7a
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return MostlySingularMultiMap.b((Map.Entry) obj);
            }
        }, "; ") + "}";
    }
}
