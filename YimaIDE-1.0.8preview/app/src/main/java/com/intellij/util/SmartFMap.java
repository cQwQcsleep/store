package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Comparing;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class SmartFMap<K, V> implements Map<K, V> {
    private static final SmartFMap<?, ?> EMPTY = new SmartFMap<>(ArrayUtilRt.EMPTY_OBJECT_ARRAY);
    private final Object myMap;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 4 || i == 11) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 4 || i == 11) ? 3 : 2];
        if (i == 1 || i == 2) {
            objArr[0] = "key";
        } else if (i == 3) {
            objArr[0] = "keys";
        } else if (i == 4) {
            objArr[0] = "m";
        } else if (i != 11) {
            objArr[0] = "com/intellij/util/SmartFMap";
        } else {
            objArr[0] = "action";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 11:
                objArr[1] = "com/intellij/util/SmartFMap";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "keySet";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[1] = "values";
                break;
            case 9:
            case 10:
                objArr[1] = "entrySet";
                break;
            default:
                objArr[1] = "emptyMap";
                break;
        }
        if (i == 1) {
            objArr[2] = "plus";
        } else if (i == 2) {
            objArr[2] = "minus";
        } else if (i == 3) {
            objArr[2] = "minusAll";
        } else if (i == 4) {
            objArr[2] = "putAll";
        } else if (i == 11) {
            objArr[2] = "forEach";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 11) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    private SmartFMap(Object obj) {
        this.myMap = obj;
    }

    private Map<K, V> asMap() {
        return (Map) this.myMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object doPlus(Object obj, Object obj2, Object obj3) {
        if (obj instanceof Map) {
            HashMap map = new HashMap((Map) obj);
            map.put(obj2, obj3);
            return map;
        }
        Object[] objArr = (Object[]) obj;
        for (int i = 0; i < objArr.length; i += 2) {
            if (obj2.equals(objArr[i])) {
                Object[] objArr2 = (Object[]) objArr.clone();
                objArr2[i + 1] = obj3;
                return objArr2;
            }
        }
        if (objArr.length != 16) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + 2);
            objArrCopyOf[objArr.length] = obj2;
            objArrCopyOf[objArr.length + 1] = obj3;
            return objArrCopyOf;
        }
        HashMap map2 = new HashMap();
        for (int i2 = 0; i2 < objArr.length; i2 += 2) {
            map2.put(objArr[i2], objArr[i2 + 1]);
        }
        map2.put(obj2, obj3);
        return map2;
    }

    public static <K, V> SmartFMap<K, V> emptyMap() {
        SmartFMap<K, V> smartFMap = (SmartFMap<K, V>) EMPTY;
        if (smartFMap == null) {
            $$$reportNull$$$0(0);
        }
        return smartFMap;
    }

    @Override // java.util.Map
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        Object obj2 = this.myMap;
        if (obj2 instanceof Map) {
            return asMap().containsKey(obj);
        }
        Object[] objArr = (Object[]) obj2;
        for (int i = 0; i < objArr.length; i += 2) {
            if (obj.equals(objArr[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (isEmpty()) {
            Set<Map.Entry<K, V>> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(9);
            }
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Object obj = this.myMap;
        if (obj instanceof Map) {
            Iterator<Map.Entry<K, V>> it = asMap().entrySet().iterator();
            while (it.hasNext()) {
                linkedHashSet.add(new AbstractMap.SimpleImmutableEntry(it.next()));
            }
        } else {
            Object[] objArr = (Object[]) obj;
            for (int i = 0; i < objArr.length; i += 2) {
                linkedHashSet.add(new AbstractMap.SimpleImmutableEntry(objArr[i], objArr[i + 1]));
            }
        }
        Set<Map.Entry<K, V>> setUnmodifiableSet = Collections.unmodifiableSet(linkedHashSet);
        if (setUnmodifiableSet == null) {
            $$$reportNull$$$0(10);
        }
        return setUnmodifiableSet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        Object obj2 = this.myMap;
        if (obj2 instanceof Map) {
            return obj2.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        Object[] objArr = (Object[]) this.myMap;
        for (int i = 0; i < objArr.length; i += 2) {
            if (!Comparing.equal(objArr[i + 1], map.get(objArr[i]))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (biConsumer == null) {
            $$$reportNull$$$0(11);
        }
        Object obj = this.myMap;
        if (obj instanceof Map) {
            asMap().forEach(biConsumer);
            return;
        }
        Object[] objArr = (Object[]) obj;
        for (int i = 0; i < objArr.length; i += 2) {
            biConsumer.accept(objArr[i], objArr[i + 1]);
        }
    }

    @Override // java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        Object obj2 = this.myMap;
        if (obj2 instanceof Map) {
            return asMap().get(obj);
        }
        Object[] objArr = (Object[]) obj2;
        for (int i = 0; i < objArr.length; i += 2) {
            if (obj.equals(objArr[i])) {
                return (V) objArr[i + 1];
            }
        }
        return null;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        if (isEmpty()) {
            Set<K> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(5);
            }
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next().getKey());
        }
        Set<K> setUnmodifiableSet = Collections.unmodifiableSet(linkedHashSet);
        if (setUnmodifiableSet == null) {
            $$$reportNull$$$0(6);
        }
        return setUnmodifiableSet;
    }

    public SmartFMap<K, V> plus(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        return new SmartFMap<>(doPlus(this.myMap, k, v));
    }

    public SmartFMap<K, V> plusAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            this = this.plus(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // java.util.Map
    @Deprecated
    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(4);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public int size() {
        Object obj = this.myMap;
        return obj instanceof Map ? asMap().size() : ((Object[]) obj).length >> 1;
    }

    public String toString() {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        if (!it.hasNext()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        while (true) {
            Map.Entry<K, V> next = it.next();
            Object key = next.getKey();
            Object value = next.getValue();
            if (key == this) {
                key = "(this Map)";
            }
            sb.append(key);
            sb.append('=');
            if (value == this) {
                value = "(this Map)";
            }
            sb.append(value);
            if (!it.hasNext()) {
                sb.append('}');
                return sb.toString();
            }
            sb.append(", ");
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (isEmpty()) {
            List list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(7);
            }
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        Collection<V> collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
        if (collectionUnmodifiableCollection == null) {
            $$$reportNull$$$0(8);
        }
        return collectionUnmodifiableCollection;
    }
}
