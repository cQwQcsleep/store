package com.sun.tools.javac.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ListBuffer<A> extends AbstractQueue<A> {
    private int count;
    private List<A> elems;
    private List<A> last;
    private boolean shared;

    public ListBuffer() {
        clear();
    }

    private void copy() {
        if (!this.elems.nonEmpty()) {
            return;
        }
        List<A> list = this.elems;
        List<A> listOf = List.of((Object) list.head);
        this.last = listOf;
        this.elems = listOf;
        while (true) {
            list = list.tail;
            if (!list.nonEmpty()) {
                return;
            }
            this.last.tail = List.of((Object) list.head);
            this.last = this.last.tail;
        }
    }

    public static <T> ListBuffer<T> of(T t) {
        ListBuffer<T> listBuffer = new ListBuffer<>();
        listBuffer.add(t);
        return listBuffer;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(A a) {
        append(a);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends A> collection) {
        Iterator<? extends A> it = collection.iterator();
        while (it.hasNext()) {
            append(it.next());
        }
        return true;
    }

    public ListBuffer<A> append(A a) {
        Assert.checkNonNull(a);
        if (this.shared) {
            copy();
        }
        List<A> listOf = List.of((Object) a);
        List<A> list = this.last;
        if (list != null) {
            list.tail = listOf;
            this.last = listOf;
        } else {
            this.last = listOf;
            this.elems = listOf;
        }
        this.count++;
        return this;
    }

    public ListBuffer<A> appendArray(A[] aArr) {
        for (A a : aArr) {
            append(a);
        }
        return this;
    }

    public ListBuffer<A> appendList(List<A> list) {
        while (list.nonEmpty()) {
            append(list.head);
            list = list.tail;
        }
        return this;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.elems = List.nil();
        this.last = null;
        this.count = 0;
        this.shared = false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return this.elems.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public A first() {
        return this.elems.head;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<A> iterator() {
        return new Iterator<A>() { // from class: com.sun.tools.javac.util.ListBuffer.1
            List<A> elems;

            {
                this.elems = ListBuffer.this.elems;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return !this.elems.isEmpty();
            }

            @Override // java.util.Iterator
            public A next() {
                if (this.elems.isEmpty()) {
                    z0e.a();
                    return null;
                }
                List<A> list = this.elems;
                A a = list.head;
                this.elems = list.tail;
                return a;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public A last() {
        List<A> list = this.last;
        if (list != null) {
            return list.head;
        }
        return null;
    }

    public int length() {
        return this.count;
    }

    public A next() {
        List<A> list = this.elems;
        A a = list.head;
        if (!list.isEmpty()) {
            List<A> list2 = this.elems.tail;
            this.elems = list2;
            if (list2.isEmpty()) {
                this.last = null;
            }
            this.count--;
        }
        return a;
    }

    public boolean nonEmpty() {
        return this.count != 0;
    }

    @Override // java.util.Queue
    public boolean offer(A a) {
        append(a);
        return true;
    }

    @Override // java.util.Queue
    public A peek() {
        return first();
    }

    @Override // java.util.Queue
    public A poll() {
        return next();
    }

    public ListBuffer<A> prepend(A a) {
        List<A> listPrepend = this.elems.prepend(a);
        this.elems = listPrepend;
        if (this.last == null) {
            this.last = listPrepend;
        }
        this.count++;
        return this;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public List<A> toList() {
        this.shared = true;
        return this.elems;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.elems.toArray(tArr);
    }

    public ListBuffer<A> appendList(ListBuffer<A> listBuffer) {
        return appendList(listBuffer.toList());
    }
}
