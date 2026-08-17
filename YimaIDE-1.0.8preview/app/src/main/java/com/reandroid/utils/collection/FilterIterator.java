package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FilterIterator<T> implements Iterator<T>, Predicate<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> mFilter;
    private boolean mFinished;
    private T mNext;

    public FilterIterator(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.mFilter = predicate;
    }

    private T getNext() {
        if (this.mNext == null) {
            while (this.iterator.hasNext()) {
                T next = this.iterator.next();
                if (testAll(next)) {
                    this.mNext = next;
                    break;
                }
            }
        }
        return this.mNext;
    }

    public static <T1> Iterator<T1> of(Iterator<? extends T1> it, Predicate<? super T1> predicate) {
        return (it == null || !it.hasNext()) ? EmptyIterator.of() : new FilterIterator(it, predicate);
    }

    private boolean testAll(T t) {
        if (t == null || !test(t)) {
            return false;
        }
        Predicate<? super T> predicate = this.mFilter;
        return predicate == null || predicate.test(t);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.mFinished) {
            return false;
        }
        if (getNext() != null) {
            return true;
        }
        this.mFinished = true;
        onFinished();
        return false;
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

    public void onFinished() {
    }

    @Override // java.util.function.Predicate
    public boolean test(T t) {
        return t != null;
    }

    public static final class Except<T1> extends FilterIterator<T1> {
        private final T1 excludeItem;
        private final boolean useEquals;

        public Except(Iterator<? extends T1> it, T1 t1, boolean z) {
            super(it);
            this.excludeItem = t1;
            this.useEquals = z;
        }

        @Override // com.reandroid.utils.collection.FilterIterator, java.util.function.Predicate
        public boolean test(T1 t1) {
            T1 t2;
            if (t1 == null || t1 == (t2 = this.excludeItem)) {
                return false;
            }
            if (this.useEquals) {
                return t1.equals(t2);
            }
            return true;
        }

        public Except(Iterator<? extends T1> it, T1 t1) {
            this(it, t1, false);
        }
    }

    public FilterIterator(Iterator<? extends T> it) {
        this(it, null);
    }
}
