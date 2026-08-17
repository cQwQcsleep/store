package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ConcurrentWeakHashMap<K, V> extends ConcurrentRefHashMap<K, V> {

    public static final class WeakKey<K> extends WeakReference<K> implements ConcurrentRefHashMap.KeyReference<K> {
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
            objArr[1] = "com/intellij/util/containers/ConcurrentWeakHashMap$WeakKey";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private WeakKey(K k, int i, HashingStrategy<? super K> hashingStrategy, ReferenceQueue<K> referenceQueue) {
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
            if (k == null || obj2 == null) {
                return false;
            }
            return k == obj2 || this.myStrategy.equals(k, obj2);
        }

        @Override // com.intellij.util.containers.ConcurrentRefHashMap.KeyReference
        public int hashCode() {
            return this.myHash;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 2) {
            objArr[0] = "hashingStrategy";
        } else {
            objArr[0] = "key";
        }
        objArr[1] = "com/intellij/util/containers/ConcurrentWeakHashMap";
        if (i == 2 || i == 3) {
            objArr[2] = "createKeyReference";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentWeakHashMap(HashingStrategy<? super K> hashingStrategy) {
        super(16, 0.75f, ConcurrentRefHashMap.DEFAULT_CONCURRENCY_LEVEL, hashingStrategy);
        if (hashingStrategy == null) {
            $$$reportNull$$$0(1);
        }
    }

    @Override // com.intellij.util.containers.ConcurrentRefHashMap
    public ConcurrentRefHashMap.KeyReference<K> createKeyReference(K k, HashingStrategy<? super K> hashingStrategy) {
        if (k == null) {
            $$$reportNull$$$0(2);
        }
        if (hashingStrategy == null) {
            $$$reportNull$$$0(3);
        }
        return new WeakKey(k, hashingStrategy.hashCode(k), hashingStrategy, this.myReferenceQueue);
    }

    public ConcurrentWeakHashMap(float f) {
        super(16, f, ConcurrentRefHashMap.DEFAULT_CONCURRENCY_LEVEL, HashingStrategy.canonical());
    }
}
