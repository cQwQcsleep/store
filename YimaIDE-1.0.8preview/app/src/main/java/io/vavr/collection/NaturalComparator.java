package io.vavr.collection;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class NaturalComparator<T> implements Comparator<T>, Serializable {
    private static final NaturalComparator<?> INSTANCE = new NaturalComparator<>();
    private static final long serialVersionUID = 1;

    private NaturalComparator() {
    }

    public static <T> NaturalComparator<T> instance() {
        return (NaturalComparator<T>) INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        return ((Comparable) t).compareTo(t2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj instanceof NaturalComparator;
    }

    public int hashCode() {
        return 1;
    }
}
