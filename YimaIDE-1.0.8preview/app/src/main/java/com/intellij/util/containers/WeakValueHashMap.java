package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
public final class WeakValueHashMap<K, V> extends RefValueHashMap<K, V> {

    public static final class MyWeakReference<K, T> extends WeakReference<T> implements RefValueHashMap.MyReference<K, T> {
        private final K key;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "key";
            } else {
                objArr[0] = "com/intellij/util/containers/WeakValueHashMap$MyWeakReference";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/containers/WeakValueHashMap$MyWeakReference";
            } else {
                objArr[1] = "getKey";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private MyWeakReference(K k, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            this.key = k;
        }

        @Override // com.intellij.util.containers.RefValueHashMap.MyReference
        public K getKey() {
            K k = this.key;
            if (k == null) {
                $$$reportNull$$$0(1);
            }
            return k;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "key";
        } else {
            objArr[0] = "queue";
        }
        objArr[1] = "com/intellij/util/containers/WeakValueHashMap";
        objArr[2] = "createReference";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Deprecated
    public WeakValueHashMap() {
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.intellij.util.containers.RefValueHashMap
    public RefValueHashMap.MyReference<K, V> createReference(K k, V v, ReferenceQueue<? super V> referenceQueue) {
        if (k == null) {
            $$$reportNull$$$0(0);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(1);
        }
        return new MyWeakReference(k, v, referenceQueue);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Object get(Object obj) {
        return super.get(obj);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.intellij.util.containers.RefValueHashMap, com.intellij.util.containers.ReferenceQueueable
    public /* bridge */ /* synthetic */ boolean processQueue() {
        return super.processQueue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return super.put(obj, obj2);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ void putAll(Map map) {
        super.putAll(map);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Object remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.intellij.util.containers.RefValueHashMap, java.util.Map
    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }
}
