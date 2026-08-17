package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ComputeIterator<E, T> implements Iterator<T> {
    private final Predicate<T> filter;
    private final Function<? super E, T> function;
    private final Iterator<? extends E> iterator;
    private T mNext;

    public ComputeIterator(Iterator<? extends E> it, Function<? super E, T> function, Predicate<T> predicate) {
        this.iterator = it;
        this.function = function;
        this.filter = predicate;
    }

    private T getNext() {
        Predicate<T> predicate;
        if (this.mNext == null) {
            while (this.iterator.hasNext()) {
                T tApply = this.function.apply(this.iterator.next());
                if (tApply != null && ((predicate = this.filter) == null || predicate.test(tApply))) {
                    this.mNext = tApply;
                    break;
                }
            }
        }
        return this.mNext;
    }

    public static <E1, T1> Iterator<T1> of(Iterator<? extends E1> it, Function<? super E1, T1> function) {
        return !it.hasNext() ? EmptyIterator.of() : new ComputeIterator(it, function);
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

    public ComputeIterator(Iterator<? extends E> it, Function<? super E, T> function) {
        this(it, function, null);
    }
}
