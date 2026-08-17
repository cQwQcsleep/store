package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IndexIterator<T> implements Iterator<T> {
    private final Predicate<? super T> mFilter;
    private int mIndex;
    private T mNext;
    private final SizedSupplier<? extends T> mSupplier;

    public IndexIterator(SizedSupplier<? extends T> sizedSupplier, Predicate<? super T> predicate) {
        this.mSupplier = sizedSupplier;
        this.mFilter = predicate;
    }

    private T getNext() {
        if (this.mNext == null) {
            while (this.mIndex < this.mSupplier.size()) {
                T t = this.mSupplier.get(this.mIndex);
                this.mIndex++;
                if (testAll(t)) {
                    this.mNext = t;
                    break;
                }
            }
        }
        return this.mNext;
    }

    public static <T1> Iterator<T1> of(SizedSupplier<T1> sizedSupplier, Predicate<? super T1> predicate) {
        return (sizedSupplier == null || sizedSupplier.size() == 0) ? EmptyIterator.of() : new IndexIterator(sizedSupplier, predicate);
    }

    private boolean testAll(T t) {
        if (t == null) {
            return false;
        }
        Predicate<? super T> predicate = this.mFilter;
        return predicate == null || predicate.test(t);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getNext() != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T next = getNext();
        if (next != null) {
            this.mNext = null;
            return next;
        }
        z0e.a();
        return null;
    }

    public IndexIterator(SizedSupplier<? extends T> sizedSupplier) {
        this(sizedSupplier, null);
    }

    public static <T1> Iterator<T1> of(SizedSupplier<T1> sizedSupplier) {
        return of(sizedSupplier, null);
    }
}
