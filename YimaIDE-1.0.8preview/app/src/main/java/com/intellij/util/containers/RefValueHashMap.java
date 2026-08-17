package com.intellij.util.containers;

import com.intellij.reference.SoftReference;
import com.intellij.util.IncorrectOperationException;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
abstract class RefValueHashMap<K, V> implements ReferenceQueueable, Map<K, V> {
    private final ReferenceQueue<V> myQueue = new ReferenceQueue<>();
    private final Map<K, MyReference<K, V>> myMap = new HashMap();

    public interface MyReference<K, T> extends Supplier<T> {
        K getKey();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5) ? 2 : 3];
        if (i == 3) {
            objArr[0] = "t";
        } else if (i == 4 || i == 5) {
            objArr[0] = "com/intellij/util/containers/RefValueHashMap";
        } else {
            objArr[0] = "key";
        }
        if (i == 4) {
            objArr[1] = "keySet";
        } else if (i != 5) {
            objArr[1] = "com/intellij/util/containers/RefValueHashMap";
        } else {
            objArr[1] = "values";
        }
        if (i == 1) {
            objArr[2] = "put";
        } else if (i == 2) {
            objArr[2] = "remove";
        } else if (i == 3) {
            objArr[2] = "putAll";
        } else if (i != 4 && i != 5) {
            objArr[2] = "get";
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.util.Map
    public void clear() {
        this.myMap.clear();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Map
    public boolean containsKey(Object obj) throws IncorrectOperationException {
        throw RefValueHashMapUtil.pointlessContainsKey();
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public abstract MyReference<K, V> createReference(K k, V v, ReferenceQueue<? super V> referenceQueue);

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(0);
        }
        return (V) SoftReference.deref(this.myMap.get(obj));
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.myMap.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        Set<K> setKeySet = this.myMap.keySet();
        if (setKeySet == null) {
            $$$reportNull$$$0(4);
        }
        return setKeySet;
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        boolean zRemove = false;
        while (true) {
            MyReference<K, V> myReference = (MyReference) this.myQueue.poll();
            if (myReference == null) {
                return zRemove;
            }
            K key = myReference.getKey();
            if (this.myMap.get(key) == myReference) {
                zRemove |= this.myMap.remove(key, myReference);
            }
        }
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        processQueue();
        return (V) SoftReference.deref(this.myMap.put(k, createReference(k, v, this.myQueue)));
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(3);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(2);
        }
        processQueue();
        return (V) SoftReference.deref(this.myMap.remove(obj));
    }

    @Override // java.util.Map
    public int size() {
        return this.myMap.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        ArrayList arrayList = new ArrayList();
        Iterator<MyReference<K, V>> it = this.myMap.values().iterator();
        while (it.hasNext()) {
            V v = it.next().get();
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }
}
