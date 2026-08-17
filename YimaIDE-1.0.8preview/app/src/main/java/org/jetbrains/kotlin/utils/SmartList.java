package org.jetbrains.kotlin.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class SmartList<E> extends AbstractList<E> implements RandomAccess {
    private Object myElem;
    private int mySize;

    public static class EmptyIterator<T> implements Iterator<T> {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        private EmptyIterator() {
        }

        public static <T> EmptyIterator<T> getInstance() {
            return INSTANCE;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    public class SingletonIterator extends SingletonIteratorBase<E> {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = ((AbstractList) SmartList.this).modCount;
        }

        @Override // org.jetbrains.kotlin.utils.SmartList.SingletonIteratorBase
        public void checkCoModification() {
            if (((AbstractList) SmartList.this).modCount == this.myInitialModCount) {
                return;
            }
            throw new ConcurrentModificationException("ModCount: " + ((AbstractList) SmartList.this).modCount + "; expected: " + this.myInitialModCount);
        }

        @Override // org.jetbrains.kotlin.utils.SmartList.SingletonIteratorBase
        public E getElement() {
            return (E) SmartList.this.myElem;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    public static abstract class SingletonIteratorBase<T> implements Iterator<T> {
        private boolean myVisited;

        private SingletonIteratorBase() {
        }

        public abstract void checkCoModification();

        public abstract T getElement();

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (this.myVisited) {
                z0e.a();
                return null;
            }
            this.myVisited = true;
            checkCoModification();
            return getElement();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "org/jetbrains/kotlin/utils/SmartList";
                break;
            case 4:
                objArr[0] = "a";
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "iterator";
        } else if (i == 5 || i == 6 || i == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "org/jetbrains/kotlin/utils/SmartList";
        }
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public SmartList(Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(0);
        }
        int size = collection.size();
        if (size == 1) {
            add(collection instanceof List ? (E) ((List) collection).get(0) : collection.iterator().next());
        } else if (size > 0) {
            this.mySize = size;
            this.myElem = collection.toArray(new Object[size]);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int i2;
        if (i < 0 || i > (i2 = this.mySize)) {
            kac.a("Index: ", i, ", Size: ", this.mySize);
            return;
        }
        if (i2 == 0) {
            this.myElem = e;
        } else if (i2 == 1 && i == 0) {
            this.myElem = new Object[]{e, this.myElem};
        } else {
            Object[] objArr = new Object[i2 + 1];
            Object obj = this.myElem;
            if (i2 == 1) {
                objArr[0] = obj;
            } else {
                Object[] objArr2 = (Object[]) obj;
                System.arraycopy(objArr2, 0, objArr, 0, i);
                System.arraycopy(objArr2, i, objArr, i + 1, this.mySize - i);
            }
            objArr[i] = e;
            this.myElem = objArr;
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        ((AbstractList) this).modCount++;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            kac.a("Index: ", i, ", Size: ", this.mySize);
            return null;
        }
        E e = (E) this.myElem;
        return i2 == 1 ? e : (E) ((Object[]) e)[i];
    }

    public int getModificationCount() {
        return ((AbstractList) this).modCount;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        int i = this.mySize;
        if (i == 0) {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(2);
            }
            return emptyIterator;
        }
        if (i == 1) {
            return new SingletonIterator();
        }
        Iterator<E> it = super.iterator();
        if (it == null) {
            $$$reportNull$$$0(3);
        }
        return it;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            kac.a("Index: ", i, ", Size: ", this.mySize);
            return null;
        }
        Object obj = (E) this.myElem;
        if (i2 == 1) {
            this.myElem = null;
        } else {
            Object[] objArr = (Object[]) obj;
            Object obj2 = objArr[i];
            if (i2 == 2) {
                this.myElem = objArr[1 - i];
            } else {
                int i3 = (i2 - i) - 1;
                if (i3 > 0) {
                    System.arraycopy(objArr, i + 1, objArr, i, i3);
                }
                objArr[this.mySize - 1] = null;
            }
            obj = (E) obj2;
        }
        this.mySize--;
        ((AbstractList) this).modCount++;
        return (E) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            kac.a("Index: ", i, ", Size: ", this.mySize);
            return null;
        }
        E e2 = (E) this.myElem;
        if (i2 == 1) {
            this.myElem = e;
            return e2;
        }
        Object[] objArr = (Object[]) e2;
        E e3 = (E) objArr[i];
        objArr[i] = e;
        return e3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    @Override // java.util.List
    public void sort(Comparator<? super E> comparator) {
        int i = this.mySize;
        if (i >= 2) {
            Arrays.sort((Object[]) this.myElem, 0, i, comparator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        if (tArr == 0) {
            $$$reportNull$$$0(4);
        }
        int length = tArr.length;
        int i = this.mySize;
        if (i == 1) {
            if (length == 0) {
                T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
                tArr2[0] = this.myElem;
                return tArr2;
            }
            tArr[0] = this.myElem;
        } else {
            if (length < i) {
                T[] tArr3 = (T[]) Arrays.copyOf((Object[]) this.myElem, i, tArr.getClass());
                if (tArr3 == null) {
                    $$$reportNull$$$0(6);
                }
                return tArr3;
            }
            if (i != 0) {
                System.arraycopy(this.myElem, 0, tArr, 0, i);
            }
        }
        int i2 = this.mySize;
        if (length > i2) {
            tArr[i2] = 0;
        }
        return tArr;
    }

    public void trimToSize() {
        int i = this.mySize;
        if (i < 2) {
            return;
        }
        Object[] objArr = (Object[]) this.myElem;
        if (i < objArr.length) {
            ((AbstractList) this).modCount++;
            this.myElem = Arrays.copyOf(objArr, i);
        }
    }

    public SmartList(E e) {
        add(e);
    }

    public SmartList() {
    }

    public SmartList(E... eArr) {
        if (eArr == null) {
            $$$reportNull$$$0(1);
        }
        if (eArr.length == 1) {
            add(eArr[0]);
        } else if (eArr.length > 0) {
            int length = eArr.length;
            this.mySize = length;
            this.myElem = Arrays.copyOf(eArr, length);
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        int i = this.mySize;
        if (i == 0) {
            this.myElem = e;
        } else {
            Object obj = this.myElem;
            if (i == 1) {
                this.myElem = new Object[]{obj, e};
            } else {
                Object[] objArr = (Object[]) obj;
                int length = objArr.length;
                if (i >= length) {
                    int i2 = ((length * 3) / 2) + 1;
                    int i3 = i + 1;
                    if (i2 < i3) {
                        i2 = i3;
                    }
                    Object[] objArr2 = new Object[i2];
                    this.myElem = objArr2;
                    System.arraycopy(objArr, 0, objArr2, 0, length);
                    objArr = objArr2;
                }
                objArr[this.mySize] = e;
            }
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
        return true;
    }
}
