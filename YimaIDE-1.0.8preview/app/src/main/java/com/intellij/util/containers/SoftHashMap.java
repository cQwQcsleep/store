package com.intellij.util.containers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class SoftHashMap<K, V> extends RefHashMap<K, V> {

    public static final class SoftKey<T> extends SoftReference<T> implements RefHashMap.Key<T> {
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
            objArr[1] = "com/intellij/util/containers/SoftHashMap$SoftKey";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private SoftKey(T t, HashingStrategy<? super T> hashingStrategy, ReferenceQueue<? super T> referenceQueue) {
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
            if (!(obj instanceof RefHashMap.Key) || this.myHash != obj.hashCode()) {
                return false;
            }
            T t = get();
            Object obj2 = ((RefHashMap.Key) obj).get();
            if (t == null || obj2 == null) {
                return false;
            }
            return RefHashMap.keysEqual(t, obj2, this.myStrategy);
        }

        @Override // com.intellij.util.containers.RefHashMap.Key
        public int hashCode() {
            return this.myHash;
        }

        public String toString() {
            return "SoftHashMap.SoftKey(" + get() + ")";
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 2) {
            objArr[0] = "k";
        } else if (i == 3) {
            objArr[0] = "strategy";
        } else if (i != 4) {
            objArr[0] = "hashingStrategy";
        } else {
            objArr[0] = "q";
        }
        objArr[1] = "com/intellij/util/containers/SoftHashMap";
        if (i == 2 || i == 3 || i == 4) {
            objArr[2] = "createKey";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public SoftHashMap(int i) {
        super(i, 0.8f, HashingStrategy.canonical());
    }

    @Override // com.intellij.util.containers.RefHashMap
    public <T> RefHashMap.Key<T> createKey(T t, HashingStrategy<? super T> hashingStrategy, ReferenceQueue<? super T> referenceQueue) {
        if (t == null) {
            $$$reportNull$$$0(2);
        }
        if (hashingStrategy == null) {
            $$$reportNull$$$0(3);
        }
        if (referenceQueue == null) {
            $$$reportNull$$$0(4);
        }
        return new SoftKey(t, hashingStrategy, referenceQueue);
    }
}
