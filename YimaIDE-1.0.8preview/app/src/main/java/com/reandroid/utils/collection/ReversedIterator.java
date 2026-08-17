package com.reandroid.utils.collection;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ReversedIterator<T> implements Iterator<T> {
    private int index;
    private final int mEndIndex;
    private T mNext;

    public ReversedIterator(int i, int i2) {
        int i3 = i - i2;
        this.mEndIndex = i3 < 0 ? 0 : i3;
        this.index = i;
    }

    private T computeNext() {
        T tTestAll = this.mNext;
        while (tTestAll == null) {
            int i = this.index;
            if (i < this.mEndIndex) {
                break;
            }
            tTestAll = testAll(get(i));
            this.index--;
        }
        this.mNext = tTestAll;
        return tTestAll;
    }

    public static <T1> Iterator<T1> of(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return EmptyIterator.of();
        }
        int length = objArr.length;
        return of(objArr, length - 1, length);
    }

    private T testAll(T t) {
        return t;
    }

    public abstract T get(int i);

    @Override // java.util.Iterator
    public boolean hasNext() {
        return computeNext() != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T tComputeNext = computeNext();
        this.mNext = null;
        return tComputeNext;
    }

    public static <T1> Iterator<T1> of(List<? extends T1> list, int i) {
        return of(list, i, i + 1);
    }

    public static <T1> Iterator<T1> of(final List<? extends T1> list, int i, int i2) {
        if (list.isEmpty()) {
            return EmptyIterator.of();
        }
        return new ReversedIterator<T1>(i, i2) { // from class: com.reandroid.utils.collection.ReversedIterator.1
            @Override // com.reandroid.utils.collection.ReversedIterator
            public T1 get(int i3) {
                return (T1) list.get(i3);
            }
        };
    }

    public static <T1> Iterator<T1> of(List<? extends T1> list) {
        int size = list.size();
        if (size == 0) {
            return EmptyIterator.of();
        }
        return of(list, size - 1, size);
    }

    public static <T1> Iterator<T1> of(Object[] objArr, int i) {
        return of(objArr, i, i + 1);
    }

    public static <T1> Iterator<T1> of(final Object[] objArr, int i, int i2) {
        if (objArr != null && objArr.length != 0) {
            return new ReversedIterator<T1>(i, i2) { // from class: com.reandroid.utils.collection.ReversedIterator.2
                @Override // com.reandroid.utils.collection.ReversedIterator
                public T1 get(int i3) {
                    return (T1) objArr[i3];
                }
            };
        }
        return EmptyIterator.of();
    }
}
