package com.intellij.util.containers;

import com.intellij.util.IncorrectOperationException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class SoftKeySoftValueHashMap<K, V> implements ReferenceQueueable, Map<K, V> {
    private final RefHashMap<K, ValueReference<K, V>> mySoftKeyMap = new SoftHashMap(4);
    private final ReferenceQueue<V> myQueue = new ReferenceQueue<>();

    public static final class ValueReference<K, V> extends SoftReference<V> {
        private final RefHashMap.Key<K> key;

        private ValueReference(RefHashMap.Key<K> key, V v, ReferenceQueue<? super V> referenceQueue) {
            super(v, referenceQueue);
            this.key = key;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "com/intellij/util/containers/SoftKeySoftValueHashMap";
        } else {
            objArr[0] = "t";
        }
        if (i == 1) {
            objArr[1] = "keySet";
        } else if (i != 2) {
            objArr[1] = "com/intellij/util/containers/SoftKeySoftValueHashMap";
        } else {
            objArr[1] = "values";
        }
        if (i != 1 && i != 2) {
            objArr[2] = "putAll";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.util.Map
    public void clear() {
        this.mySoftKeyMap.clear();
        processQueue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Map
    public boolean containsKey(Object obj) throws IncorrectOperationException {
        throw RefValueHashMapUtil.pointlessContainsKey();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // java.util.Map
    public boolean containsValue(Object obj) throws IncorrectOperationException {
        throw RefValueHashMapUtil.pointlessContainsValue();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return (V) com.intellij.reference.SoftReference.dereference(this.mySoftKeyMap.get(obj));
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.mySoftKeyMap.isEmpty();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        Set<K> setKeySet = this.mySoftKeyMap.keySet();
        if (setKeySet == null) {
            $$$reportNull$$$0(1);
        }
        return setKeySet;
    }

    @Override // com.intellij.util.containers.ReferenceQueueable
    public boolean processQueue() {
        boolean zProcessQueue = this.mySoftKeyMap.processQueue();
        while (true) {
            ValueReference valueReference = (ValueReference) this.myQueue.poll();
            if (valueReference == null) {
                return zProcessQueue;
            }
            this.mySoftKeyMap.removeKey(valueReference.key);
            zProcessQueue = true;
        }
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        processQueue();
        RefHashMap.Key<K> keyCreateKey = this.mySoftKeyMap.createKey(k);
        return (V) com.intellij.reference.SoftReference.dereference(this.mySoftKeyMap.putKey(keyCreateKey, new ValueReference<>(keyCreateKey, v, this.myQueue)));
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            $$$reportNull$$$0(0);
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        processQueue();
        return (V) com.intellij.reference.SoftReference.dereference(this.mySoftKeyMap.remove(obj));
    }

    @Override // java.util.Map
    public int size() {
        return this.mySoftKeyMap.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        ArrayList arrayList = new ArrayList();
        Iterator<ValueReference<K, V>> it = this.mySoftKeyMap.values().iterator();
        while (it.hasNext()) {
            V v = it.next().get();
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }
}
