package com.shadow.kotlin.collections;

import com.shadow.kotlin.collections.AbstractList;
import com.shadow.kotlin.io.CloseableKt;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ArrayDeque<E> extends kotlin.collections.AbstractMutableList<E> {
    private static final Object[] emptyElementData = new Object[0];
    private Object[] elementData = emptyElementData;
    private int head;
    private int size;

    private final void copyCollectionElements(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = collection.size() + size();
    }

    private final void ensureCapacity(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == emptyElementData) {
            if (i < 10) {
                i = 10;
            }
            this.elementData = new Object[i];
            return;
        }
        AbstractList.Companion companion = AbstractList.Companion;
        int length = objArr.length;
        companion.getClass();
        Object[] objArr2 = new Object[AbstractList.Companion.newCapacity$kotlin_stdlib(length, i)];
        Object[] objArr3 = this.elementData;
        ArraysKt.c(objArr3, objArr2, 0, this.head, objArr3.length);
        Object[] objArr4 = this.elementData;
        int length2 = objArr4.length;
        int i2 = this.head;
        ArraysKt.c(objArr4, objArr2, length2 - i2, 0, i2);
        this.head = 0;
        this.elementData = objArr2;
    }

    private final int incremented(int i) {
        CloseableKt.checkNotNullParameter(this.elementData, "<this>");
        if (i == r0.length - 1) {
            return 0;
        }
        return i + 1;
    }

    private final int positiveMod(int i) {
        Object[] objArr = this.elementData;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final boolean add(E e) {
        addLast(e);
        return true;
    }

    public final boolean addAll(Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(collection.size() + size());
        copyCollectionElements(positiveMod(size() + this.head), collection);
        return true;
    }

    public final void addFirst(E e) {
        ensureCapacity(size() + 1);
        int length = this.head;
        if (length == 0) {
            Object[] objArr = this.elementData;
            CloseableKt.checkNotNullParameter(objArr, "<this>");
            length = objArr.length;
        }
        int i = length - 1;
        this.head = i;
        this.elementData[i] = e;
        this.size = size() + 1;
    }

    public final void addLast(E e) {
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(size() + this.head)] = e;
        this.size = size() + 1;
    }

    public final void clear() {
        int iPositiveMod = positiveMod(size() + this.head);
        int i = this.head;
        if (i < iPositiveMod) {
            ArraysKt.f(this.elementData, i, iPositiveMod);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt.f(objArr, this.head, objArr.length);
            ArraysKt.f(this.elementData, 0, iPositiveMod);
        }
        this.head = 0;
        this.size = 0;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final E get(int i) {
        AbstractList.Companion companion = AbstractList.Companion;
        int size = size();
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size);
        return (E) this.elementData[positiveMod(this.head + i)];
    }

    public final int getSize() {
        return this.size;
    }

    public final int indexOf(Object obj) {
        int i;
        int iPositiveMod = positiveMod(size() + this.head);
        int length = this.head;
        if (length < iPositiveMod) {
            while (length < iPositiveMod) {
                if (CloseableKt.areEqual(obj, this.elementData[length])) {
                    i = this.head;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iPositiveMod) {
            return -1;
        }
        int length2 = this.elementData.length;
        while (true) {
            if (length >= length2) {
                for (int i2 = 0; i2 < iPositiveMod; i2++) {
                    if (CloseableKt.areEqual(obj, this.elementData[i2])) {
                        length = i2 + this.elementData.length;
                        i = this.head;
                    }
                }
                return -1;
            }
            if (CloseableKt.areEqual(obj, this.elementData[length])) {
                i = this.head;
                break;
            }
            length++;
        }
        return length - i;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int iPositiveMod = positiveMod(size() + this.head);
        int i2 = this.head;
        if (i2 < iPositiveMod) {
            length = iPositiveMod - 1;
            if (i2 <= length) {
                while (!CloseableKt.areEqual(obj, this.elementData[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.head;
                return length - i;
            }
            return -1;
        }
        if (i2 > iPositiveMod) {
            int i3 = iPositiveMod - 1;
            while (true) {
                if (-1 >= i3) {
                    Object[] objArr = this.elementData;
                    CloseableKt.checkNotNullParameter(objArr, "<this>");
                    length = objArr.length - 1;
                    int i4 = this.head;
                    if (i4 <= length) {
                        while (!CloseableKt.areEqual(obj, this.elementData[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.head;
                    }
                } else {
                    if (CloseableKt.areEqual(obj, this.elementData[i3])) {
                        length = i3 + this.elementData.length;
                        i = this.head;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        remove(iIndexOf);
        return true;
    }

    public final boolean removeAll(Collection<? extends Object> collection) {
        int iPositiveMod;
        CloseableKt.checkNotNullParameter(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int iPositiveMod2 = positiveMod(size() + this.head);
            int i = this.head;
            if (i < iPositiveMod2) {
                iPositiveMod = i;
                while (i < iPositiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.elementData[iPositiveMod] = obj;
                        iPositiveMod++;
                    }
                    i++;
                }
                ArraysKt.f(this.elementData, iPositiveMod, iPositiveMod2);
            } else {
                int length = this.elementData.length;
                int i2 = i;
                boolean z2 = false;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.elementData[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iPositiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < iPositiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.elementData[iPositiveMod] = obj3;
                        iPositiveMod = incremented(iPositiveMod);
                    }
                }
                z = z2;
            }
            if (z) {
                int length2 = iPositiveMod - this.head;
                if (length2 < 0) {
                    length2 += this.elementData.length;
                }
                this.size = length2;
            }
        }
        return z;
    }

    public final E removeAt(int i) {
        AbstractList.Companion companion = AbstractList.Companion;
        int size = size();
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size);
        if (i == size() - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        int iPositiveMod = positiveMod(this.head + i);
        E e = (E) this.elementData[iPositiveMod];
        if (i < (size() >> 1)) {
            int i2 = this.head;
            if (iPositiveMod >= i2) {
                Object[] objArr = this.elementData;
                ArraysKt.c(objArr, objArr, i2 + 1, i2, iPositiveMod);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt.c(objArr2, objArr2, 1, 0, iPositiveMod);
                Object[] objArr3 = this.elementData;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.head;
                ArraysKt.c(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.elementData;
            int i4 = this.head;
            objArr4[i4] = null;
            this.head = incremented(i4);
        } else {
            int iPositiveMod2 = positiveMod((size() - 1) + this.head);
            if (iPositiveMod <= iPositiveMod2) {
                Object[] objArr5 = this.elementData;
                ArraysKt.c(objArr5, objArr5, iPositiveMod, iPositiveMod + 1, iPositiveMod2 + 1);
            } else {
                Object[] objArr6 = this.elementData;
                ArraysKt.c(objArr6, objArr6, iPositiveMod, iPositiveMod + 1, objArr6.length);
                Object[] objArr7 = this.elementData;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt.c(objArr7, objArr7, 0, 1, iPositiveMod2 + 1);
            }
            this.elementData[iPositiveMod2] = null;
        }
        this.size = size() - 1;
        return e;
    }

    public final E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int i = this.head;
        E e = (E) objArr[i];
        objArr[i] = null;
        this.head = incremented(i);
        this.size = size() - 1;
        return e;
    }

    public final E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int iPositiveMod = positiveMod((size() - 1) + this.head);
        Object[] objArr = this.elementData;
        E e = (E) objArr[iPositiveMod];
        objArr[iPositiveMod] = null;
        this.size = size() - 1;
        return e;
    }

    public final boolean retainAll(Collection<? extends Object> collection) {
        int iPositiveMod;
        CloseableKt.checkNotNullParameter(collection, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int iPositiveMod2 = positiveMod(size() + this.head);
            int i = this.head;
            if (i < iPositiveMod2) {
                iPositiveMod = i;
                while (i < iPositiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        this.elementData[iPositiveMod] = obj;
                        iPositiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                ArraysKt.f(this.elementData, iPositiveMod, iPositiveMod2);
            } else {
                int length = this.elementData.length;
                int i2 = i;
                boolean z2 = false;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iPositiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < iPositiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.elementData[iPositiveMod] = obj3;
                        iPositiveMod = incremented(iPositiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int length2 = iPositiveMod - this.head;
                if (length2 < 0) {
                    length2 += this.elementData.length;
                }
                this.size = length2;
            }
        }
        return z;
    }

    public final E set(int i, E e) {
        AbstractList.Companion companion = AbstractList.Companion;
        int size = size();
        companion.getClass();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size);
        int iPositiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        E e2 = (E) objArr[iPositiveMod];
        objArr[iPositiveMod] = e;
        return e2;
    }

    public final <T> T[] toArray(T[] tArr) throws NegativeArraySizeException {
        CloseableKt.checkNotNullParameter(tArr, "array");
        if (tArr.length < size()) {
            Object objNewInstance = Array.newInstance(tArr.getClass().getComponentType(), size());
            CloseableKt.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
            tArr = (T[]) ((Object[]) objNewInstance);
        }
        int iPositiveMod = positiveMod(size() + this.head);
        int i = this.head;
        if (i < iPositiveMod) {
            ArraysKt.c(this.elementData, tArr, 0, i, iPositiveMod);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            ArraysKt.c(objArr, tArr, 0, this.head, objArr.length);
            Object[] objArr2 = this.elementData;
            ArraysKt.c(objArr2, tArr, objArr2.length - this.head, 0, iPositiveMod);
        }
        int size = size();
        if (size < tArr.length) {
            tArr[size] = null;
        }
        return tArr;
    }

    public final void add(int i, E e) {
        int length;
        AbstractList.Companion companion = AbstractList.Companion;
        int size = size();
        companion.getClass();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, size);
        if (i == size()) {
            addLast(e);
            return;
        }
        if (i == 0) {
            addFirst(e);
            return;
        }
        ensureCapacity(size() + 1);
        int iPositiveMod = positiveMod(this.head + i);
        if (i < ((size() + 1) >> 1)) {
            if (iPositiveMod == 0) {
                Object[] objArr = this.elementData;
                CloseableKt.checkNotNullParameter(objArr, "<this>");
                iPositiveMod = objArr.length;
            }
            int i2 = iPositiveMod - 1;
            int i3 = this.head;
            if (i3 == 0) {
                Object[] objArr2 = this.elementData;
                CloseableKt.checkNotNullParameter(objArr2, "<this>");
                length = objArr2.length - 1;
            } else {
                length = i3 - 1;
            }
            int i4 = this.head;
            if (i2 >= i4) {
                Object[] objArr3 = this.elementData;
                objArr3[length] = objArr3[i4];
                ArraysKt.c(objArr3, objArr3, i4, i4 + 1, i2 + 1);
            } else {
                Object[] objArr4 = this.elementData;
                ArraysKt.c(objArr4, objArr4, i4 - 1, i4, objArr4.length);
                Object[] objArr5 = this.elementData;
                objArr5[objArr5.length - 1] = objArr5[0];
                ArraysKt.c(objArr5, objArr5, 0, 1, i2 + 1);
            }
            this.elementData[i2] = e;
            this.head = length;
        } else {
            int iPositiveMod2 = positiveMod(size() + this.head);
            if (iPositiveMod < iPositiveMod2) {
                Object[] objArr6 = this.elementData;
                ArraysKt.c(objArr6, objArr6, iPositiveMod + 1, iPositiveMod, iPositiveMod2);
            } else {
                Object[] objArr7 = this.elementData;
                ArraysKt.c(objArr7, objArr7, 1, 0, iPositiveMod2);
                Object[] objArr8 = this.elementData;
                objArr8[0] = objArr8[objArr8.length - 1];
                ArraysKt.c(objArr8, objArr8, iPositiveMod + 1, iPositiveMod, objArr8.length - 1);
            }
            this.elementData[iPositiveMod] = e;
        }
        this.size = size() + 1;
    }

    public final boolean addAll(int i, Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        AbstractList.Companion companion = AbstractList.Companion;
        int size = size();
        companion.getClass();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, size);
        if (collection.isEmpty()) {
            return false;
        }
        if (i == size()) {
            return addAll(collection);
        }
        ensureCapacity(collection.size() + size());
        int iPositiveMod = positiveMod(size() + this.head);
        int iPositiveMod2 = positiveMod(this.head + i);
        int size2 = collection.size();
        if (i < ((size() + 1) >> 1)) {
            int i2 = this.head;
            int length = i2 - size2;
            if (iPositiveMod2 < i2) {
                Object[] objArr = this.elementData;
                ArraysKt.c(objArr, objArr, length, i2, objArr.length);
                if (size2 >= iPositiveMod2) {
                    Object[] objArr2 = this.elementData;
                    ArraysKt.c(objArr2, objArr2, objArr2.length - size2, 0, iPositiveMod2);
                } else {
                    Object[] objArr3 = this.elementData;
                    ArraysKt.c(objArr3, objArr3, objArr3.length - size2, 0, size2);
                    Object[] objArr4 = this.elementData;
                    ArraysKt.c(objArr4, objArr4, 0, size2, iPositiveMod2);
                }
            } else if (length >= 0) {
                Object[] objArr5 = this.elementData;
                ArraysKt.c(objArr5, objArr5, length, i2, iPositiveMod2);
            } else {
                Object[] objArr6 = this.elementData;
                length += objArr6.length;
                int i3 = iPositiveMod2 - i2;
                int length2 = objArr6.length - length;
                if (length2 >= i3) {
                    ArraysKt.c(objArr6, objArr6, length, i2, iPositiveMod2);
                } else {
                    ArraysKt.c(objArr6, objArr6, length, i2, i2 + length2);
                    Object[] objArr7 = this.elementData;
                    ArraysKt.c(objArr7, objArr7, 0, this.head + length2, iPositiveMod2);
                }
            }
            this.head = length;
            int length3 = iPositiveMod2 - size2;
            if (length3 < 0) {
                length3 += this.elementData.length;
            }
            copyCollectionElements(length3, collection);
        } else {
            int i4 = iPositiveMod2 + size2;
            if (iPositiveMod2 < iPositiveMod) {
                int i5 = size2 + iPositiveMod;
                Object[] objArr8 = this.elementData;
                if (i5 <= objArr8.length) {
                    ArraysKt.c(objArr8, objArr8, i4, iPositiveMod2, iPositiveMod);
                } else if (i4 >= objArr8.length) {
                    ArraysKt.c(objArr8, objArr8, i4 - objArr8.length, iPositiveMod2, iPositiveMod);
                } else {
                    int length4 = iPositiveMod - (i5 - objArr8.length);
                    ArraysKt.c(objArr8, objArr8, 0, length4, iPositiveMod);
                    Object[] objArr9 = this.elementData;
                    ArraysKt.c(objArr9, objArr9, i4, iPositiveMod2, length4);
                }
            } else {
                Object[] objArr10 = this.elementData;
                ArraysKt.c(objArr10, objArr10, size2, 0, iPositiveMod);
                Object[] objArr11 = this.elementData;
                if (i4 >= objArr11.length) {
                    ArraysKt.c(objArr11, objArr11, i4 - objArr11.length, iPositiveMod2, objArr11.length);
                } else {
                    ArraysKt.c(objArr11, objArr11, 0, objArr11.length - size2, objArr11.length);
                    Object[] objArr12 = this.elementData;
                    ArraysKt.c(objArr12, objArr12, i4, iPositiveMod2, objArr12.length - size2);
                }
            }
            copyCollectionElements(iPositiveMod2, collection);
        }
        return true;
    }

    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
