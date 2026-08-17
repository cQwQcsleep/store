package com.intellij.util.containers;

import com.intellij.util.ObjectUtilsRt;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class ConcurrentWeakKeyWeakValueHashMap<K, V> extends ConcurrentWeakKeySoftValueHashMap<K, V> {

    public static final class WeakValue<K, V> extends WeakReference<V> implements ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> {
        private volatile ConcurrentWeakKeySoftValueHashMap.KeyReference<K, V> myKeyReference;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "value";
            } else {
                objArr[0] = "queue";
            }
            objArr[1] = "com/intellij/util/containers/ConcurrentWeakKeyWeakValueHashMap$WeakValue";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private WeakValue(V v, ReferenceQueue<? super V> referenceQueue) {
            super(v, referenceQueue);
            if (v == null) {
                $$$reportNull$$$0(0);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(1);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            V v = get();
            return v != null && v.equals(((ConcurrentWeakKeySoftValueHashMap.ValueReference) obj).get());
        }

        @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap.ValueReference
        public ConcurrentWeakKeySoftValueHashMap.KeyReference<K, V> getKeyReference() {
            return this.myKeyReference;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "k";
        } else if (i == 2) {
            objArr[0] = "v";
        } else if (i == 3) {
            objArr[0] = "com/intellij/util/containers/ConcurrentWeakKeyWeakValueHashMap";
        } else if (i == 4) {
            objArr[0] = "value";
        } else if (i != 5) {
            objArr[0] = "hashingStrategy";
        } else {
            objArr[0] = "queue";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/containers/ConcurrentWeakKeyWeakValueHashMap";
        } else {
            objArr[1] = "createKeyReference";
        }
        if (i == 1 || i == 2) {
            objArr[2] = "createKeyReference";
        } else if (i != 3) {
            if (i == 4 || i == 5) {
                objArr[2] = "createValueReference";
            } else {
                objArr[2] = "<init>";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcurrentWeakKeyWeakValueHashMap(int i, float f, int i2, HashingStrategy<? super K> hashingStrategy) {
        super(i, f, i2, hashingStrategy);
        if (hashingStrategy == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap
    public ConcurrentWeakKeySoftValueHashMap.KeyReference<K, V> createKeyReference(K k, V v) {
        if (k == null) {
            $$$reportNull$$$0(1);
        }
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> valueReferenceCreateValueReference = createValueReference(v, this.myValueQueue);
        ConcurrentWeakKeySoftValueHashMap.WeakKey weakKey = new ConcurrentWeakKeySoftValueHashMap.WeakKey(k, valueReferenceCreateValueReference, this.myHashingStrategy, this.myKeyQueue);
        if (valueReferenceCreateValueReference instanceof WeakValue) {
            ((WeakValue) valueReferenceCreateValueReference).myKeyReference = weakKey;
        }
        ObjectUtilsRt.reachabilityFence(k);
        ObjectUtilsRt.reachabilityFence(v);
        return weakKey;
    }

    @Override // com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap
    public ConcurrentWeakKeySoftValueHashMap.ValueReference<K, V> createValueReference(V v, ReferenceQueue<? super V> referenceQueue) {
        if (v == null) {
            $$$reportNull$$$0(4);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(5);
        }
        return new WeakValue(v, referenceQueue);
    }
}
