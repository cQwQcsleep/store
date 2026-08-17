package com.intellij.util.containers;

import com.intellij.openapi.util.Comparing;
import java.util.AbstractList;
import java.util.Iterator;
import kotlin.jvm.PurelyImplements;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@PurelyImplements("kotlin.collections.MutableList")
public final class FList<E> extends AbstractList<E> {
    private static final FList<?> EMPTY_LIST = new FList<>(null, null, 0);
    private final E myHead;
    private final int mySize;
    private final FList<E> myTail;

    private FList(E e, FList<E> fList, int i) {
        this.myHead = e;
        this.myTail = fList;
        this.mySize = i;
    }

    public static <E> FList<E> emptyList() {
        return (FList<E>) EMPTY_LIST;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FList)) {
            return super.equals(obj);
        }
        FList<E> tail = (FList) obj;
        if (this.mySize != tail.mySize) {
            return false;
        }
        while (this != null) {
            if (!Comparing.equal(this.myHead, tail.myHead)) {
                return false;
            }
            this = this.getTail();
            tail = tail.getTail();
            if (this == tail) {
                break;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        if (i < 0 || i >= this.mySize) {
            kac.a("index = ", i, ", size = ", this.mySize);
            return null;
        }
        while (i > 0) {
            this = this.myTail;
            i--;
        }
        return this.myHead;
    }

    public FList<E> getTail() {
        return this.myTail;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int iHashCode = 1;
        while (this != null) {
            int i = iHashCode * 31;
            E e = this.myHead;
            iHashCode = i + (e != null ? e.hashCode() : 0);
            this = this.getTail();
        }
        return iHashCode;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: com.intellij.util.containers.FList.1
            static final /* synthetic */ boolean $assertionsDisabled = false;
            private FList<E> list;

            {
                this.list = FList.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.list.size() > 0;
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.list.size() == 0) {
                    z0e.a();
                    return null;
                }
                E e = (E) ((FList) this.list).myHead;
                this.list = this.list.getTail();
                return e;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public FList<E> prepend(E e) {
        return new FList<>(e, this, this.mySize + 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }
}
