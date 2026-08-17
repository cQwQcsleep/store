package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayIterator<T> implements Iterator<T>, SizedItem, SizedIterator {
    private final Object[] elements;
    private int index;
    private final Predicate<? super T> mFilter;
    private final int mLength;
    private T mNext;
    private final int mStart;

    public ArrayIterator(Object[] objArr, int i, int i2, Predicate<? super T> predicate) {
        this.elements = objArr;
        this.mStart = i;
        this.mLength = i2;
        this.mFilter = predicate;
    }

    private T getNext() {
        T t;
        Object[] objArr = this.elements;
        if (this.mNext == null && objArr != null) {
            do {
                int i = this.index;
                if (i < this.mLength) {
                    t = (T) objArr[this.mStart + i];
                    this.index = i + 1;
                }
            } while (!testAll(t));
            this.mNext = t;
        }
        return this.mNext;
    }

    private static boolean isEmpty(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            for (Object obj : objArr) {
                if (obj != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static <T1> Iterator<T1> of(Object[] objArr) {
        return isEmpty(objArr) ? EmptyIterator.of() : new ArrayIterator(objArr);
    }

    private boolean testAll(T t) {
        if (t == null) {
            return false;
        }
        Predicate<? super T> predicate = this.mFilter;
        return predicate == null || predicate.test(t);
    }

    @Override // com.reandroid.utils.collection.SizedIterator
    public int getRemainingSize() {
        return this.mLength - this.index;
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

    @Override // com.reandroid.utils.collection.SizedItem
    public int size() {
        return this.mLength;
    }

    public ArrayIterator(Object[] objArr, int i, int i2) {
        this(objArr, i, i2, null);
    }

    public ArrayIterator(Object[] objArr, Predicate<? super T> predicate) {
        this(objArr, 0, objArr.length, predicate);
    }

    public ArrayIterator(Object[] objArr) {
        this(objArr, null);
    }

    public static <T1> Iterator<T1> of(Object[] objArr, Predicate<? super T1> predicate) {
        if (isEmpty(objArr)) {
            return EmptyIterator.of();
        }
        return new ArrayIterator(objArr, predicate);
    }

    public static <T1> Iterator<T1> of(Object[] objArr, int i, int i2) {
        if (isEmpty(objArr)) {
            return EmptyIterator.of();
        }
        return new ArrayIterator(objArr, i, i2);
    }
}
