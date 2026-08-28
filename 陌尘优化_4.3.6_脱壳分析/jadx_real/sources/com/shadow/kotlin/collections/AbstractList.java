package com.shadow.kotlin.collections;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class AbstractList<E> extends kotlin.collections.AbstractCollection<E> implements List<E> {
    public static final Companion Companion = new Companion();
    private static final int maxArraySize = 2147483639;

    public final class Companion {
        public static void checkElementIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public static void checkPositionIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public static void checkRangeIndexes$kotlin_stdlib(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
            }
            if (i <= i2) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i + " > toIndex: " + i2);
        }

        public static int newCapacity$kotlin_stdlib(int i, int i2) {
            int i3 = i + (i >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            if (i3 - AbstractList.maxArraySize <= 0) {
                return i3;
            }
            if (i2 > AbstractList.maxArraySize) {
                return Integer.MAX_VALUE;
            }
            return AbstractList.maxArraySize;
        }
    }

    class IteratorImpl implements Iterator<Object>, KMappedMarker {
        private int index;
        final /* synthetic */ kotlin.collections.AbstractList<Object> this$0;

        public IteratorImpl(AbstractList abstractList) {
            this.this$0 = abstractList;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.this$0.size();
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int i = this.index;
            this.index = i + 1;
            return this.this$0.get(i);
        }

        public int nextIndex() {
            return getIndex();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }
    }

    final class ListIteratorImpl extends AbstractList.IteratorImpl implements ListIterator<Object> {
        final /* synthetic */ kotlin.collections.AbstractList<Object> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ListIteratorImpl(AbstractList abstractList, int i) {
            super(abstractList);
            this.this$0 = abstractList;
            Companion companion = AbstractList.Companion;
            int size = abstractList.size();
            companion.getClass();
            Companion.checkPositionIndex$kotlin_stdlib(i, size);
            setIndex(i);
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return getIndex() > 0;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            setIndex(getIndex() - 1);
            return this.this$0.get(getIndex());
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return getIndex() - 1;
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    final class SubList<E> extends kotlin.collections.AbstractList<E> implements RandomAccess {
        private final int _size;
        private final int fromIndex;
        private final kotlin.collections.AbstractList<E> list;

        /* JADX WARN: Multi-variable type inference failed */
        public SubList(kotlin.collections.AbstractList<? extends E> abstractList, int i, int i2) {
            CloseableKt.checkNotNullParameter(abstractList, "list");
            this.list = abstractList;
            this.fromIndex = i;
            Companion companion = AbstractList.Companion;
            int size = abstractList.size();
            companion.getClass();
            Companion.checkRangeIndexes$kotlin_stdlib(i, i2, size);
            this._size = i2 - i;
        }

        public final E get(int i) {
            Companion companion = AbstractList.Companion;
            int i2 = this._size;
            companion.getClass();
            Companion.checkElementIndex$kotlin_stdlib(i, i2);
            return (E) this.list.get(this.fromIndex + i);
        }

        public final int getSize() {
            return this._size;
        }
    }

    @Override // java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        Companion.getClass();
        CloseableKt.checkNotNullParameter(collection, "other");
        if (size() == collection.size()) {
            Iterator<E> it = collection.iterator();
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                if (!CloseableKt.areEqual(it2.next(), it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        Companion.getClass();
        Iterator<E> it = iterator();
        int iHashCode = 1;
        while (it.hasNext()) {
            E next = it.next();
            iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.List
    public int indexOf(E e) {
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (CloseableKt.areEqual(it.next(), e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new IteratorImpl(this);
    }

    @Override // java.util.List
    public int lastIndexOf(E e) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (CloseableKt.areEqual(listIterator.previous(), e)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(this, 0);
    }

    @Override // java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return new SubList(this, i, i2);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new ListIteratorImpl(this, i);
    }
}
