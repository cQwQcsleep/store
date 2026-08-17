package com.intellij.util.containers;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class SequenceIterator<T> implements Iterator<T> {
    private int myCurrentIndex;
    private final Iterator<? extends T>[] myIterators;

    /* JADX WARN: Code duplicated, block: B:10:0x001d  */
    /* JADX WARN: Code duplicated, block: B:11:0x0023  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 2) {
            objArr[0] = "first";
        } else if (i == 3) {
            objArr[0] = "second";
        } else if (i == 4) {
            objArr[0] = "first";
        } else if (i == 5) {
            objArr[0] = "second";
        } else if (i != 6) {
            objArr[0] = "iterators";
        } else {
            objArr[0] = "third";
        }
        objArr[1] = "com/intellij/util/containers/SequenceIterator";
        if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            objArr[2] = "create";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @SafeVarargs
    public SequenceIterator(Iterator<? extends T>... itArr) {
        if (itArr == null) {
            $$$reportNull$$$0(0);
        }
        this.myIterators = (Iterator[]) itArr.clone();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i = this.myCurrentIndex;
        while (true) {
            Iterator<? extends T>[] itArr = this.myIterators;
            if (i >= itArr.length) {
                return false;
            }
            Iterator<? extends T> it = itArr[i];
            if (it != null && it.hasNext()) {
                this.myCurrentIndex = i;
                return true;
            }
            i++;
        }
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            return this.myIterators[this.myCurrentIndex].next();
        }
        hb9.a("Iterator has no more elements");
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        int i = this.myCurrentIndex;
        Iterator<? extends T>[] itArr = this.myIterators;
        if (i < itArr.length) {
            itArr[i].remove();
        } else {
            g33.a();
        }
    }
}
