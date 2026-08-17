package com.reandroid.utils.collection;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ListItr<T> implements ListIterator<T> {
    private static ListItr<?> empty_itr;
    private int cursor;
    private int lastRet;
    private final List<T> list;

    public ListItr(List<T> list, int i) {
        this.list = list;
        this.cursor = i;
        this.lastRet = -1;
    }

    public static <T1> ListIterator<T1> empty() {
        ListItr<?> listItr = empty_itr;
        if (listItr != null) {
            return listItr;
        }
        ListItr<?> listItr2 = new ListItr<>(EmptyList.of());
        empty_itr = listItr2;
        return listItr2;
    }

    public static <T1> ListIterator<T1> of(List<T1> list, int i) {
        return list.size() - i <= 0 ? empty() : new ListItr(list, i);
    }

    @Override // java.util.ListIterator
    public void add(T t) {
        int i = this.cursor;
        this.list.add(i, t);
        this.cursor = i + 1;
        this.lastRet = -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.cursor < this.list.size();
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return this.cursor != 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public T next() {
        int i = this.cursor;
        this.lastRet = i;
        this.cursor = i + 1;
        return this.list.get(i);
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.cursor;
    }

    @Override // java.util.ListIterator
    public T previous() {
        int i = this.cursor - 1;
        if (i < 0) {
            z0e.a();
            return null;
        }
        this.cursor = i;
        this.lastRet = i;
        return this.list.get(i);
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.cursor - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        int i = this.lastRet;
        this.list.remove(i);
        this.cursor = i;
        this.lastRet = -1;
    }

    @Override // java.util.ListIterator
    public void set(T t) {
        this.list.set(this.lastRet, t);
    }

    public ListItr(List<T> list) {
        this(list, 0);
    }

    public static <T1> ListIterator<T1> of(List<T1> list) {
        return of(list, 0);
    }
}
