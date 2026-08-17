package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ConcurrentSoftHashMap<K, V> extends ConcurrentRefHashMap<K, V> {

    public static final class SoftKey<K> extends SoftReference<K> implements ConcurrentRefHashMap.KeyReference<K> {
        private final int myHash;
        private final HashingStrategy<? super K> myStrategy;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "strategy";
            } else if (i != 2) {
                objArr[0] = "k";
            } else {
                objArr[0] = "q";
            }
            objArr[1] = "com/intellij/util/containers/ConcurrentSoftHashMap$SoftKey";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private SoftKey(K k, int i, HashingStrategy<? super K> hashingStrategy, ReferenceQueue<K> referenceQueue) {
            super(k, referenceQueue);
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            if (hashingStrategy == null) {
                $$$reportNull$$$0(1);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(2);
            }
            this.myStrategy = hashingStrategy;
            this.myHash = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConcurrentRefHashMap.KeyReference)) {
                return false;
            }
            K k = get();
            Object obj2 = ((ConcurrentRefHashMap.KeyReference) obj).get();
            if (k == obj2) {
                return true;
            }
            if (k == null || obj2 == null) {
                return false;
            }
            return this.myStrategy.equals(k, obj2);
        }

        @Override // com.intellij.util.containers.ConcurrentRefHashMap.KeyReference
        public int hashCode() {
            return this.myHash;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "hashingStrategy";
        } else {
            objArr[0] = "key";
        }
        objArr[1] = "com/intellij/util/containers/ConcurrentSoftHashMap";
        if (i == 1 || i == 2) {
            objArr[2] = "createKeyReference";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public ConcurrentSoftHashMap(BiConsumer<? super ConcurrentMap<K, V>, ? super V> biConsumer) {
        super(biConsumer);
    }

    @Override // com.intellij.util.containers.ConcurrentRefHashMap
    public ConcurrentRefHashMap.KeyReference<K> createKeyReference(K k, HashingStrategy<? super K> hashingStrategy) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        if (hashingStrategy == null) {
            $$$reportNull$$$0(2);
        }
        return new SoftKey(k, hashingStrategy.hashCode(k), hashingStrategy, this.myReferenceQueue);
    }
}
