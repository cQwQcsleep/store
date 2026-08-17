package com.reandroid.utils.collection;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ComputeList<T, E> extends ComputeCollection<T, E> implements List<T> {
    public ComputeList(List<? extends E> list, Function<? super E, T> function) {
        super(list, function);
    }

    @Override // java.util.List
    public void add(int i, T t) {
        throw new IllegalArgumentException("Can't add on ".concat(getClass().getSimpleName()));
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        w01.a("Can't add on ".concat(getClass().getSimpleName()));
        return false;
    }

    @Override // java.util.List
    public T get(int i) {
        return apply(getSource().get(i));
    }

    @Override // com.reandroid.utils.collection.ComputeCollection
    public List<? extends E> getSource() {
        return (List) super.getSource();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(obj, get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int size = size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            if (Objects.equals(obj, get(i2))) {
                i = i2;
            }
        }
        return i;
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return ListItr.of(this);
    }

    @Override // java.util.List
    public T remove(int i) {
        throw new IllegalArgumentException("Can't remove on ".concat(getClass().getSimpleName()));
    }

    @Override // java.util.List
    public T set(int i, T t) {
        throw new IllegalArgumentException("Can't set on ".concat(getClass().getSimpleName()));
    }

    @Override // java.util.List
    public List<T> subList(int i, int i2) {
        return null;
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i) {
        return ListItr.of(this);
    }
}
