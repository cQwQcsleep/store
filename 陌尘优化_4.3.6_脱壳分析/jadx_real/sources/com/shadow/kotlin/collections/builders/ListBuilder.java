package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.collections.AbstractList;
import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractMutableList;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ListBuilder<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable {
    private static final ListBuilder Empty;
    private E[] array;
    private final kotlin.collections.builders.ListBuilder<E> backing;
    private boolean isReadOnly;
    private int length;
    private int offset;
    private final kotlin.collections.builders.ListBuilder<E> root;

    final class Itr<E> implements ListIterator<E>, KMappedMarker {
        private int expectedModCount;
        private int index;
        private int lastIndex;
        private final kotlin.collections.builders.ListBuilder<E> list;

        public Itr(kotlin.collections.builders.ListBuilder<E> listBuilder, int i) {
            CloseableKt.checkNotNullParameter(listBuilder, "list");
            this.list = listBuilder;
            this.index = i;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) listBuilder).modCount;
        }

        private final void checkForComodification() {
            if (((AbstractList) this.list).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator
        public final void add(E e) {
            checkForComodification();
            int i = this.index;
            this.index = i + 1;
            kotlin.collections.builders.ListBuilder<E> listBuilder = this.list;
            listBuilder.add(i, e);
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) listBuilder).modCount;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.index < ((ListBuilder) this.list).length;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final E next() {
            checkForComodification();
            int i = this.index;
            kotlin.collections.builders.ListBuilder<E> listBuilder = this.list;
            if (i >= ((ListBuilder) listBuilder).length) {
                throw new NoSuchElementException();
            }
            int i2 = this.index;
            this.index = i2 + 1;
            this.lastIndex = i2;
            return (E) ((ListBuilder) listBuilder).array[((ListBuilder) listBuilder).offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final E previous() {
            checkForComodification();
            int i = this.index;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            int i2 = i - 1;
            this.index = i2;
            this.lastIndex = i2;
            kotlin.collections.builders.ListBuilder<E> listBuilder = this.list;
            return (E) ((ListBuilder) listBuilder).array[((ListBuilder) listBuilder).offset + this.lastIndex];
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            checkForComodification();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            }
            kotlin.collections.builders.ListBuilder<E> listBuilder = this.list;
            listBuilder.remove(i);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ((AbstractList) listBuilder).modCount;
        }

        @Override // java.util.ListIterator
        public final void set(E e) {
            checkForComodification();
            int i = this.lastIndex;
            if (i == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            }
            this.list.set(i, e);
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    private ListBuilder(E[] eArr, int i, int i2, boolean z, kotlin.collections.builders.ListBuilder<E> listBuilder, kotlin.collections.builders.ListBuilder<E> listBuilder2) {
        this.array = eArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
        if (listBuilder != null) {
            ((AbstractList) this).modCount = ((AbstractList) listBuilder).modCount;
        }
    }

    private final void addAllInternal(int i, Collection<? extends E> collection, int i2) {
        ((AbstractList) this).modCount++;
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAllInternal(i, collection, i2);
            this.array = this.backing.array;
            this.length += i2;
        } else {
            insertAtInternal(i, i2);
            Iterator<? extends E> it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    private final void addAtInternal(int i, E e) {
        ((AbstractList) this).modCount++;
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.backing;
        if (listBuilder == null) {
            insertAtInternal(i, 1);
            this.array[i] = e;
        } else {
            listBuilder.addAtInternal(i, e);
            this.array = this.backing.array;
            this.length++;
        }
    }

    private final void checkForComodification() {
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.root;
        if (listBuilder != null && ((AbstractList) listBuilder).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    private final void checkIsMutable() {
        kotlin.collections.builders.ListBuilder<E> listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            throw new UnsupportedOperationException();
        }
    }

    private final void insertAtInternal(int i, int i2) {
        int i3 = this.length + i2;
        if (i3 < 0) {
            throw new OutOfMemoryError();
        }
        E[] eArr = this.array;
        if (i3 > eArr.length) {
            AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
            int length = eArr.length;
            companion.getClass();
            int iNewCapacity$kotlin_stdlib = AbstractList.Companion.newCapacity$kotlin_stdlib(length, i3);
            E[] eArr2 = this.array;
            CloseableKt.checkNotNullParameter(eArr2, "<this>");
            E[] eArr3 = (E[]) Arrays.copyOf(eArr2, iNewCapacity$kotlin_stdlib);
            CloseableKt.checkNotNullExpressionValue(eArr3, "copyOf(...)");
            this.array = eArr3;
        }
        E[] eArr4 = this.array;
        ArraysKt.c(eArr4, eArr4, i + i2, i, this.offset + this.length);
        this.length += i2;
    }

    private final E removeAtInternal(int i) {
        ((java.util.AbstractList) this).modCount++;
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return (E) listBuilder.removeAtInternal(i);
        }
        E[] eArr = this.array;
        E e = eArr[i];
        ArraysKt.c(eArr, eArr, i, i + 1, this.offset + this.length);
        E[] eArr2 = this.array;
        int i2 = (this.offset + this.length) - 1;
        CloseableKt.checkNotNullParameter(eArr2, "<this>");
        eArr2[i2] = null;
        this.length--;
        return e;
    }

    private final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            ((java.util.AbstractList) this).modCount++;
        }
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.removeRangeInternal(i, i2);
        } else {
            E[] eArr = this.array;
            ArraysKt.c(eArr, eArr, i, i + i2, this.length);
            E[] eArr2 = this.array;
            int i3 = this.length;
            ListBuilderKt.resetRange(eArr2, i3 - i2, i3);
        }
        this.length -= i2;
    }

    private final int retainOrRemoveAllInternal(int i, int i2, Collection<? extends E> collection, boolean z) {
        int iRetainOrRemoveAllInternal;
        kotlin.collections.builders.ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            iRetainOrRemoveAllInternal = listBuilder.retainOrRemoveAllInternal(i, i2, collection, z);
        } else {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i + i3;
                if (collection.contains(this.array[i5]) == z) {
                    E[] eArr = this.array;
                    i3++;
                    eArr[i4 + i] = eArr[i5];
                    i4++;
                } else {
                    i3++;
                }
            }
            int i6 = i2 - i4;
            E[] eArr2 = this.array;
            ArraysKt.c(eArr2, eArr2, i + i4, i2 + i, this.length);
            E[] eArr3 = this.array;
            int i7 = this.length;
            ListBuilderKt.resetRange(eArr3, i7 - i6, i7);
            iRetainOrRemoveAllInternal = i6;
        }
        if (iRetainOrRemoveAllInternal > 0) {
            ((java.util.AbstractList) this).modCount++;
        }
        this.length -= iRetainOrRemoveAllInternal;
        return iRetainOrRemoveAllInternal;
    }

    private final Object writeReplace() throws NotSerializableException {
        kotlin.collections.builders.ListBuilder<E> listBuilder;
        if (this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly)) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        checkIsMutable();
        checkForComodification();
        addAtInternal(this.offset + this.length, e);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        checkForComodification();
        int size = collection.size();
        addAllInternal(this.offset + this.length, collection, size);
        return size > 0;
    }

    public final List<E> build() {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        checkIsMutable();
        this.isReadOnly = true;
        return this.length > 0 ? this : Empty;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        checkIsMutable();
        checkForComodification();
        removeRangeInternal(this.offset, this.length);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        checkForComodification();
        if (obj != this) {
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            E[] eArr = this.array;
            int i = this.offset;
            int i2 = this.length;
            if (i2 != list.size()) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (!CloseableKt.areEqual(eArr[i + i3], list.get(i3))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.List
    public E get(int i) {
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, i2);
        return this.array[this.offset + i];
    }

    public int getSize() {
        checkForComodification();
        return this.length;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        checkForComodification();
        E[] eArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        int iHashCode = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            E e = eArr[i + i3];
            iHashCode = (iHashCode * 31) + (e != null ? e.hashCode() : 0);
        }
        return iHashCode;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        checkForComodification();
        for (int i = 0; i < this.length; i++) {
            if (CloseableKt.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        checkForComodification();
        return this.length == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        checkForComodification();
        for (int i = this.length - 1; i >= 0; i--) {
            if (CloseableKt.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        checkIsMutable();
        checkForComodification();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            remove(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        checkForComodification();
        return retainOrRemoveAllInternal(this.offset, this.length, collection, false) > 0;
    }

    public E removeAt(int i) {
        checkIsMutable();
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, i2);
        return removeAtInternal(this.offset + i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        checkForComodification();
        return retainOrRemoveAllInternal(this.offset, this.length, collection, true) > 0;
    }

    @Override // java.util.List
    public E set(int i, E e) {
        checkIsMutable();
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, i2);
        E[] eArr = this.array;
        int i3 = this.offset + i;
        E e2 = eArr[i3];
        eArr[i3] = e;
        return e2;
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i3 = this.length;
        companion.getClass();
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, i3);
        E[] eArr = this.array;
        int i4 = this.offset + i;
        int i5 = i2 - i;
        boolean z = this.isReadOnly;
        ListBuilder<E> listBuilder = this.root;
        return new ListBuilder(eArr, i4, i5, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        CloseableKt.checkNotNullParameter(tArr, "destination");
        checkForComodification();
        int length = tArr.length;
        int i = this.length;
        if (length < i) {
            E[] eArr = this.array;
            int i2 = this.offset;
            T[] tArr2 = (T[]) Arrays.copyOfRange(eArr, i2, i + i2, tArr.getClass());
            CloseableKt.checkNotNullExpressionValue(tArr2, "copyOfRange(...)");
            return tArr2;
        }
        E[] eArr2 = this.array;
        int i3 = this.offset;
        ArraysKt.c(eArr2, tArr, 0, i3, i + i3);
        int i4 = this.length;
        if (i4 < tArr.length) {
            tArr[i4] = null;
        }
        return tArr;
    }

    public String toString() {
        checkForComodification();
        E[] eArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            E e = eArr[i + i3];
            if (e == this) {
                sb.append("(this Collection)");
            } else {
                sb.append(e);
            }
        }
        sb.append("]");
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, i2);
        return new Itr(this, i);
    }

    @Override // java.util.List
    public void add(int i, E e) {
        checkIsMutable();
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, i2);
        addAtInternal(this.offset + i, e);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        checkForComodification();
        AbstractList.Companion companion = com.shadow.kotlin.collections.AbstractList.Companion;
        int i2 = this.length;
        companion.getClass();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, i2);
        int size = collection.size();
        addAllInternal(this.offset + i, collection, size);
        return size > 0;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        checkForComodification();
        E[] eArr = this.array;
        int i = this.offset;
        return ArraysKt.e(eArr, i, this.length + i);
    }

    public ListBuilder() {
        this(10);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ListBuilder(int i) {
        this(new Object[i], 0, 0, false, null, null);
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }
}
