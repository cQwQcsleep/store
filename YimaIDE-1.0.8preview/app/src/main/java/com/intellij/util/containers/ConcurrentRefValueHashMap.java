package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.text.Strings;
import com.intellij.util.IncorrectOperationException;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ConcurrentRefValueHashMap<K, V> implements ReferenceQueueable, ConcurrentMap<K, V> {
    private final BiConsumer<? super ConcurrentMap<K, V>, ? super K> myEvictionListener;
    private final ConcurrentMap<K, ValueReference<K, V>> myMap = new ConcurrentHashMap();
    protected final ReferenceQueue<V> myQueue = new ReferenceQueue<>();

    public interface ValueReference<K, V> {
        V get();

        K getKey();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 16:
            case 17:
            case 18:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 16:
            case 17:
            case 18:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 2:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 11:
            case 15:
                objArr[0] = "value";
                break;
            case 3:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 12:
            case 14:
            default:
                objArr[0] = "key";
                break;
            case 8:
                objArr[0] = "oldValue";
                break;
            case 9:
                objArr[0] = "newValue";
                break;
            case 13:
                objArr[0] = "t";
                break;
            case 16:
            case 17:
            case 18:
                objArr[0] = "com/intellij/util/containers/ConcurrentRefValueHashMap";
                break;
        }
        switch (i) {
            case 16:
                objArr[1] = "keySet";
                break;
            case 17:
                objArr[1] = "values";
                break;
            case 18:
                objArr[1] = "entrySet";
                break;
            default:
                objArr[1] = "com/intellij/util/containers/ConcurrentRefValueHashMap";
                break;
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "put";
                break;
            case 3:
            case 4:
                objArr[2] = "putIfAbsent";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 12:
                objArr[2] = "remove";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "replace";
                break;
            case 13:
                objArr[2] = "putAll";
                break;
            case 14:
                objArr[2] = "containsKey";
                break;
            case 15:
                objArr[2] = "containsValue";
                break;
            case 16:
            case 17:
            case 18:
                break;
            default:
                objArr[2] = "get";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 16:
            case 17:
            case 18:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public ConcurrentRefValueHashMap(BiConsumer<? super ConcurrentMap<K, V>, ? super K> biConsumer) {
        this.myEvictionListener = biConsumer;
    }

    @Override // java.util.Map
    public void clear() {
        this.myMap.clear();
        processQueue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Map
    public boolean containsKey(Object obj) throws IncorrectOperationException {
        if (obj == null) {
            $$$reportNull$$$0(14);
        }
        throw RefValueHashMapUtil.pointlessContainsKey();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Map
    public boolean containsValue(Object obj) throws IncorrectOperationException {
        if (obj == null) {
            $$$reportNull$$$0(15);
        }
        throw RefValueHashMapUtil.pointlessContainsValue();
    }

    public abstract ValueReference<K, V> createValueReference(K k, V v);

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<K> setKeySet = keySet();
        HashSet hashSet = new HashSet();
        for (final K k : setKeySet) {
            final V v = get(k);
            if (v != null) {
                hashSet.add(new Map.Entry<K, V>() { // from class: com.intellij.util.containers.ConcurrentRefValueHashMap.1
                    private static /* synthetic */ void $$$reportNull$$$0(int i) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/intellij/util/containers/ConcurrentRefValueHashMap$1", "setValue"));
                    }

                    @Override // java.util.Map.Entry
                    public K getKey() {
                        return (K) k;
                    }

                    @Override // java.util.Map.Entry
                    public V getValue() {
                        return (V) v;
                    }

                    @Override // java.util.Map.Entry
                    public V setValue(V v2) {
                        if (v2 == null) {
                            $$$reportNull$$$0(0);
                        }
                        throw new UnsupportedOperationException("setValue is not implemented");
                    }

                    public String toString() {
                        return "(" + getKey() + " : " + getValue() + ")";
                    }
                });
            }
        }
        return hashSet;
    }

    @Override // java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(0);
        }
        ValueReference<K, V> valueReference = this.myMap.get(obj);
        if (valueReference == null) {
            return null;
        }
        return valueReference.get();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        processQueue();
        return this.myMap.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        Set<K> setKeySet = this.myMap.keySet();
        if (setKeySet == null) {
            $$$reportNull$$$0(16);
        }
        return setKeySet;
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        BiConsumer<? super ConcurrentMap<K, V>, ? super K> biConsumer;
        boolean z = false;
        while (true) {
            ValueReference valueReference = (ValueReference) this.myQueue.poll();
            if (valueReference == null) {
                return z;
            }
            Object key = valueReference.getKey();
            if (this.myMap.remove(key, valueReference) && (biConsumer = this.myEvictionListener) != null) {
                biConsumer.accept(this, key);
            }
            z = true;
        }
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        ValueReference<K, V> valueReferencePut = this.myMap.put(k, createValueReference(k, v));
        processQueue();
        if (valueReferencePut != null) {
            return valueReferencePut.get();
        }
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(13);
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            V value = entry.getValue();
            if (value != null) {
                put(entry.getKey(), value);
            }
        }
        processQueue();
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        ValueReference<K, V> valueReferencePutIfAbsent;
        if (k == null) {
            $$$reportNull$$$0(3);
        }
        if (v == null) {
            $$$reportNull$$$0(4);
        }
        ValueReference<K, V> valueReferenceCreateValueReference = createValueReference(k, v);
        do {
            processQueue();
            valueReferencePutIfAbsent = this.myMap.putIfAbsent(k, valueReferenceCreateValueReference);
            if (valueReferencePutIfAbsent == null) {
                return null;
            }
            V v2 = valueReferencePutIfAbsent.get();
            if (v2 != null) {
                return v2;
            }
        } while (!this.myMap.replace(k, valueReferencePutIfAbsent, valueReferenceCreateValueReference));
        return null;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(12);
        }
        ValueReference<K, V> valueReferenceRemove = this.myMap.remove(obj);
        processQueue();
        if (valueReferenceRemove == null) {
            return null;
        }
        return valueReferenceRemove.get();
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K k, V v, V v2) {
        if (k == null) {
            $$$reportNull$$$0(7);
        }
        if (v == null) {
            $$$reportNull$$$0(8);
        }
        if (v2 == null) {
            $$$reportNull$$$0(9);
        }
        boolean zReplace = this.myMap.replace(k, createValueReference(k, v), createValueReference(k, v2));
        processQueue();
        return zReplace;
    }

    @Override // java.util.Map
    public int size() {
        processQueue();
        return this.myMap.size();
    }

    public String toString() {
        return "map size:" + size() + " [" + Strings.join(entrySet(), ",") + "]";
    }

    @Override // java.util.Map
    public Collection<V> values() {
        ArrayList arrayList = new ArrayList();
        Iterator<ValueReference<K, V>> it = this.myMap.values().iterator();
        while (it.hasNext()) {
            V v = it.next().get();
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        if (obj == 0) {
            $$$reportNull$$$0(5);
        }
        if (obj2 == 0) {
            $$$reportNull$$$0(6);
        }
        boolean zRemove = this.myMap.remove(obj, createValueReference(obj, obj2));
        processQueue();
        return zRemove;
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V replace(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(10);
        }
        if (v == null) {
            $$$reportNull$$$0(11);
        }
        ValueReference<K, V> valueReferenceReplace = this.myMap.replace(k, createValueReference(k, v));
        processQueue();
        if (valueReferenceReplace == null) {
            return null;
        }
        return valueReferenceReplace.get();
    }
}
