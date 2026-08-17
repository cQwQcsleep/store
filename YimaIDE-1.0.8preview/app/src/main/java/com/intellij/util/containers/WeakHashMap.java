package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class WeakHashMap<K, V> extends RefHashMap<K, V> {

    public static final class WeakKey<T> extends WeakReference<T> implements RefHashMap.Key<T> {
        private final int myHash;
        private final HashingStrategy<? super T> myStrategy;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "strategy";
            } else if (i != 2) {
                objArr[0] = "k";
            } else {
                objArr[0] = "q";
            }
            objArr[1] = "com/intellij/util/containers/WeakHashMap$WeakKey";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private WeakKey(T t, HashingStrategy<? super T> hashingStrategy, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            if (t == null) {
                $$$reportNull$$$0(0);
            }
            if (hashingStrategy == null) {
                $$$reportNull$$$0(1);
            }
            if (referenceQueue == null) {
                $$$reportNull$$$0(2);
            }
            this.myStrategy = hashingStrategy;
            this.myHash = hashingStrategy.hashCode(t);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RefHashMap.Key) {
                return RefHashMap.keysEqual(get(), ((RefHashMap.Key) obj).get(), this.myStrategy);
            }
            return false;
        }

        @Override // com.intellij.util.containers.RefHashMap.Key
        public int hashCode() {
            return this.myHash;
        }

        public String toString() {
            return "WeakKey(" + get() + ", " + this.myHash + ")";
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "k";
        } else if (i != 3) {
            objArr[0] = "strategy";
        } else {
            objArr[0] = "q";
        }
        objArr[1] = "com/intellij/util/containers/WeakHashMap";
        if (i == 1 || i == 2 || i == 3) {
            objArr[2] = "createKey";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeakHashMap(int i, float f, HashingStrategy<? super K> hashingStrategy) {
        super(i, f, hashingStrategy);
        if (hashingStrategy == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // com.intellij.util.containers.RefHashMap
    public <T> RefHashMap.Key<T> createKey(T t, HashingStrategy<? super T> hashingStrategy, ReferenceQueue<? super T> referenceQueue) {
        if (t == null) {
            $$$reportNull$$$0(1);
        }
        if (hashingStrategy == null) {
            $$$reportNull$$$0(2);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(3);
        }
        return new WeakKey(t, hashingStrategy, referenceQueue);
    }
}
