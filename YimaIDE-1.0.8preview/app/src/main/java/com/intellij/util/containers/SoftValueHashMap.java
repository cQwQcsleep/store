package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class SoftValueHashMap<K, V> extends RefValueHashMap<K, V> {

    public static final class MySoftReference<K, T> extends SoftReference<T> implements RefValueHashMap.MyReference<K, T> {
        private final K key;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 2 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "q";
            } else if (i != 2) {
                objArr[0] = "key";
            } else {
                objArr[0] = "com/intellij/util/containers/SoftValueHashMap$MySoftReference";
            }
            if (i != 2) {
                objArr[1] = "com/intellij/util/containers/SoftValueHashMap$MySoftReference";
            } else {
                objArr[1] = "getKey";
            }
            if (i != 2) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 2) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MySoftReference(K k, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(1);
            }
            this.key = k;
        }

        @Override // com.intellij.util.containers.RefValueHashMap.MyReference
        public K getKey() {
            K k = this.key;
            if (k == null) {
                $$$reportNull$$$0(2);
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
        objArr[1] = "com/intellij/util/containers/SoftValueHashMap";
        objArr[2] = "createReference";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // com.intellij.util.containers.RefValueHashMap
    public RefValueHashMap.MyReference<K, V> createReference(K k, V v, ReferenceQueue<? super V> referenceQueue) {
        if (k == null) {
            $$$reportNull$$$0(0);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(1);
        }
        return new MySoftReference(k, v, referenceQueue);
    }
}
