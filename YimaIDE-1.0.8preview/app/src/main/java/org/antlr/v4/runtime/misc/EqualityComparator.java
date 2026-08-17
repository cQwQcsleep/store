package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface EqualityComparator<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);
}
