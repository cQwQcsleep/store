package com.intellij.util.containers.hash;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface EqualityPolicy<T> {
    public static final EqualityPolicy<?> IDENTITY = new EqualityPolicy<Object>() { // from class: com.intellij.util.containers.hash.EqualityPolicy.1
        @Override // com.intellij.util.containers.hash.EqualityPolicy
        public int getHashCode(Object obj) {
            return System.identityHashCode(obj);
        }

        @Override // com.intellij.util.containers.hash.EqualityPolicy
        public boolean isEqual(Object obj, Object obj2) {
            return obj == obj2;
        }
    };
    public static final EqualityPolicy<?> CANONICAL = new EqualityPolicy<Object>() { // from class: com.intellij.util.containers.hash.EqualityPolicy.2
        @Override // com.intellij.util.containers.hash.EqualityPolicy
        public int getHashCode(Object obj) {
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        @Override // com.intellij.util.containers.hash.EqualityPolicy
        public boolean isEqual(Object obj, Object obj2) {
            return Objects.equals(obj, obj2);
        }
    };

    int getHashCode(T t);

    boolean isEqual(T t, T t2);
}
