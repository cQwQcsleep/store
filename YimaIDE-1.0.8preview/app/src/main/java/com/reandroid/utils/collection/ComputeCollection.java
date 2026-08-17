package com.reandroid.utils.collection;

import defpackage.yba;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ComputeCollection<T, E> implements Collection<T> {
    private final Function<? super E, T> function;
    private final Collection<? extends E> source;

    public ComputeCollection(Collection<? extends E> collection, Function<? super E, T> function) {
        this.source = collection;
        this.function = function;
    }

    @Override // java.util.Collection
    public boolean add(T t) {
        throw new IllegalArgumentException("Can't add on ".concat(getClass().getSimpleName()));
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        w01.a("Can't add on ".concat(getClass().getSimpleName()));
        return false;
    }

    public T apply(E e) {
        return this.function.apply(e);
    }

    @Override // java.util.Collection
    public void clear() {
        if (isEmpty()) {
            return;
        }
        yba.a("Can't clear on ", getClass().getSimpleName(), ", use getSource().clear();");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (Objects.equals(obj, it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public Collection<? extends E> getSource() {
        return this.source;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return ComputeIterator.of(this.source.iterator(), this.function);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new IllegalArgumentException("Can't remove on ".concat(getClass().getSimpleName()));
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        w01.a("Can't remove on ".concat(getClass().getSimpleName()));
        return false;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        w01.a("Can't retain on ".concat(getClass().getSimpleName()));
        return false;
    }

    @Override // java.util.Collection
    public int size() {
        return this.source.size();
    }

    @Override // java.util.Collection
    public <T1> T1[] toArray(T1[] t1Arr) {
        int size;
        if (t1Arr.length != 0 && (size = size()) != 0) {
            if (size > t1Arr.length) {
                size = t1Arr.length;
            }
            Iterator<? extends E> it = this.source.iterator();
            for (int i = 0; i < size && it.hasNext(); i++) {
                t1Arr[i] = this.function.apply(it.next());
            }
        }
        return t1Arr;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        int size = size();
        if (size == 0) {
            return ArrayCollection.EMPTY_OBJECTS;
        }
        Object[] objArr = new Object[size];
        Iterator<? extends E> it = this.source.iterator();
        int i = 0;
        while (it.hasNext()) {
            objArr[i] = this.function.apply(it.next());
            i++;
        }
        return objArr;
    }
}
