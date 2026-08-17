package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RecursiveIterator<T> implements Iterator<T> {
    private T current;
    private Iterator<? extends T> currentIterator;
    private RecursiveIterator<T> currentRecursiveIterator;
    private final Predicate<? super T> filter;
    private boolean firstProcessed;
    private final Function<T, Iterator<? extends T>> function;
    private T item;

    public RecursiveIterator(T t, Function<T, Iterator<? extends T>> function, Predicate<? super T> predicate) {
        this.item = t;
        this.function = function;
        this.filter = predicate;
    }

    public static <T1, E> Iterator<E> compute(T1 t1, Function<T1, Iterator<? extends T1>> function, Predicate<? super T1> predicate, final Function<T1, Iterator<? extends E>> function2) {
        return new IterableIterator<T1, E>(new RecursiveIterator(t1, function, predicate)) { // from class: com.reandroid.utils.collection.RecursiveIterator.1
            public Iterator<E> iterator(T1 t2) {
                return (Iterator) function2.apply(t2);
            }
        };
    }

    private T computeCurrent() {
        if (!this.firstProcessed) {
            this.firstProcessed = true;
            T t = this.item;
            if (matches(t)) {
                return t;
            }
            this.item = null;
            return null;
        }
        if (this.item != null) {
            initCurrentIterator();
        }
        Iterator<? extends T> currentIterator = getCurrentIterator();
        if (currentIterator == null || !currentIterator.hasNext()) {
            return null;
        }
        return currentIterator.next();
    }

    private T getCurrent() {
        T t = this.current;
        if (t != null) {
            return t;
        }
        T tComputeCurrent = computeCurrent();
        this.current = tComputeCurrent;
        return tComputeCurrent;
    }

    private Iterator<? extends T> getCurrentIterator() {
        RecursiveIterator<T> recursiveIterator = this.currentRecursiveIterator;
        if (recursiveIterator != null && recursiveIterator.hasNext()) {
            return recursiveIterator;
        }
        RecursiveIterator<T> recursiveIterator2 = null;
        this.currentRecursiveIterator = null;
        Iterator<? extends T> it = this.currentIterator;
        if (it == null || !it.hasNext()) {
            this.currentIterator = null;
        } else {
            recursiveIterator2 = new RecursiveIterator<>(it.next(), this.function, this.filter);
            this.currentRecursiveIterator = recursiveIterator2;
        }
        return (recursiveIterator2 == null || recursiveIterator2.hasNext()) ? recursiveIterator2 : getCurrentIterator();
    }

    private void initCurrentIterator() {
        T t = this.item;
        this.item = null;
        this.currentIterator = t != null ? this.function.apply(t) : null;
    }

    private boolean matches(T t) {
        if (t == null) {
            return false;
        }
        Predicate<? super T> predicate = this.filter;
        return predicate == null || predicate.test(t);
    }

    public static <T1> Iterator<T1> of(T1 t1, Function<T1, Iterator<? extends T1>> function, Predicate<? super T1> predicate) {
        return new RecursiveIterator(t1, function, predicate);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getCurrent() != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T current = getCurrent();
        if (current != null) {
            this.current = null;
            return current;
        }
        z0e.a();
        return null;
    }

    public static <T1> Iterator<T1> of(T1 t1, Function<T1, Iterator<? extends T1>> function) {
        return new RecursiveIterator(t1, function);
    }

    public RecursiveIterator(T t, Function<T, Iterator<? extends T>> function) {
        this(t, function, null);
    }

    public static <T1, E> Iterator<E> compute(T1 t1, Function<T1, Iterator<? extends T1>> function, Function<T1, Iterator<? extends E>> function2) {
        return compute(t1, function, null, function2);
    }
}
