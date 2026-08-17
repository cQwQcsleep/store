package com.sun.tools.javac.util;

import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import defpackage.a9g;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class List<A> extends AbstractCollection<A> implements java.util.List<A> {
    private static final List<?> EMPTY_LIST;
    public A head;
    public List<A> tail;

    static {
        List list = null;
        EMPTY_LIST = new List<Object>(list, list) { // from class: com.sun.tools.javac.util.List.1
            @Override // com.sun.tools.javac.util.List, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean isEmpty() {
                return true;
            }

            @Override // com.sun.tools.javac.util.List
            public List<Object> setTail(List<Object> list2) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public List(A a, List<A> list) {
        this.tail = list;
        this.head = a;
    }

    public static /* synthetic */ ListBuffer a(ListBuffer listBuffer, ListBuffer listBuffer2) {
        listBuffer.addAll(listBuffer2);
        return listBuffer;
    }

    public static <Z> Collector<Z, ListBuffer<Z>, List<Z>> collector() {
        return Collector.of(new Supplier() { // from class: kb9
            @Override // java.util.function.Supplier
            public final Object get() {
                return new ListBuffer();
            }
        }, new BiConsumer() { // from class: vb9
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((ListBuffer) obj).add(obj2);
            }
        }, new BinaryOperator() { // from class: wb9
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return List.a((ListBuffer) obj, (ListBuffer) obj2);
            }
        }, new Function() { // from class: yb9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ListBuffer) obj).toList();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> List<T> convert(Class<T> cls, List<?> list) {
        if (list == 0) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            cls.cast(it.next());
        }
        return list;
    }

    @Deprecated
    public static <A> List<A> fill(int i, A a) {
        List<A> listNil = nil();
        int i2 = 0;
        while (i2 < i) {
            i2++;
            listNil = new List<>(a, listNil);
        }
        return listNil;
    }

    public static <A> List<A> filter(List<A> list, A a) {
        Assert.checkNonNull(a);
        List listNil = nil();
        for (A a2 : list) {
            if (a2 != null && !a2.equals(a)) {
                listNil = listNil.prepend(a2);
            }
        }
        return listNil.reverse();
    }

    public static <A> List<A> from(Iterable<? extends A> iterable) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<? extends A> it = iterable.iterator();
        while (it.hasNext()) {
            listBuffer.append(it.next());
        }
        return listBuffer.toList();
    }

    public static <A> List<A> nil() {
        return (List<A>) EMPTY_LIST;
    }

    public static <A> List<A> of(A a, A a2, A a3, A... aArr) {
        return new List<>(a, new List(a2, new List(a3, from(aArr))));
    }

    @Override // java.util.List
    public void add(int i, A a) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends A> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        a9g.a();
        return false;
    }

    public List<A> append(A a) {
        return of((Object) a).prependList(this);
    }

    public List<A> appendList(ListBuffer<A> listBuffer) {
        return appendList(listBuffer.toList());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        while (this.tail != null) {
            A a = this.head;
            if (obj == null) {
                if (a == null) {
                    return true;
                }
            } else if (a.equals(obj)) {
                return true;
            }
            this = this.tail;
        }
        return false;
    }

    public List<A> diff(List<A> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (A a : this) {
            if (!list.contains(a)) {
                listBuffer.append(a);
            }
        }
        return listBuffer.toList();
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj instanceof List) {
            return equals(this, (List) obj);
        }
        if (obj instanceof java.util.List) {
            Iterator it = ((java.util.List) obj).iterator();
            while (this.tail != null && it.hasNext()) {
                Object next = it.next();
                A a = this.head;
                if (a == null) {
                    if (next != null) {
                        return false;
                    }
                    this = this.tail;
                } else {
                    if (!a.equals(next)) {
                        return false;
                    }
                    this = this.tail;
                }
            }
            if (this.isEmpty() && !it.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List
    public A get(int i) {
        if (i < 0) {
            jb9.a(String.valueOf(i));
            return null;
        }
        List<A> list = this;
        int i2 = i;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0 || list.isEmpty()) {
                break;
            }
            list = list.tail;
            i2 = i3;
        }
        if (!list.isEmpty()) {
            return list.head;
        }
        kac.a("Index: ", i, ", Size: ", size());
        return null;
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int iHashCode = 1;
        while (this.tail != null) {
            int i = iHashCode * 31;
            A a = this.head;
            iHashCode = i + (a == null ? 0 : a.hashCode());
            this = this.tail;
        }
        return iHashCode;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int i = 0;
        while (this.tail != null) {
            A a = this.head;
            if (a == null) {
                if (obj == null) {
                    return i;
                }
                this = this.tail;
                i++;
            } else {
                if (a.equals(obj)) {
                    return i;
                }
                this = this.tail;
                i++;
            }
        }
        return -1;
    }

    public List<A> intersect(List<A> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (A a : this) {
            if (list.contains(a)) {
                listBuffer.append(a);
            }
        }
        return listBuffer.toList();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.tail == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<A> iterator() {
        return this.tail == null ? Iterators.emptyIterator() : new Iterator<A>() { // from class: com.sun.tools.javac.util.List.2
            List<A> elems;

            {
                this.elems = List.this;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.elems.tail != null;
            }

            @Override // java.util.Iterator
            public A next() {
                List<A> list = this.elems;
                List<A> list2 = list.tail;
                if (list2 == null) {
                    z0e.a();
                    return null;
                }
                A a = list.head;
                this.elems = list2;
                return a;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public A last() {
        A a = null;
        while (true) {
            List<A> list = this.tail;
            if (list == null) {
                return a;
            }
            a = this.head;
            this = list;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0013  */
    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int i = -1;
        int i2 = 0;
        while (this.tail != null) {
            A a = this.head;
            if (a == null) {
                if (obj == null) {
                    i = i2;
                }
            } else if (a.equals(obj)) {
                i = i2;
            }
            this = this.tail;
            i2++;
        }
        return i;
    }

    public int length() {
        int i = 0;
        while (true) {
            this = this.tail;
            if (this == null) {
                return i;
            }
            i++;
        }
    }

    @Override // java.util.List
    public ListIterator<A> listIterator() {
        return Collections.unmodifiableList(new ArrayList(this)).listIterator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <Z> List<Z> map(Function<A, Z> function) {
        if (isEmpty()) {
            return this;
        }
        ListBuffer listBuffer = new ListBuffer();
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            Object objApply = function.apply(next);
            listBuffer.append(objApply);
            z |= objApply != next;
        }
        return z ? listBuffer.toList() : this;
    }

    public boolean nonEmpty() {
        return this.tail != null;
    }

    public List<A> prepend(A a) {
        return new List<>(a, this);
    }

    public List<A> prependList(List<A> list) {
        if (isEmpty()) {
            return list;
        }
        if (list.isEmpty()) {
            return this;
        }
        if (list.tail.isEmpty()) {
            return prepend(list.head);
        }
        List<A> listReverse = list.reverse();
        Assert.check(listReverse != list);
        while (true) {
            List<A> list2 = this;
            this = listReverse;
            if (!this.nonEmpty()) {
                return list2;
            }
            listReverse = this.tail;
            this.setTail(list2);
        }
    }

    @Override // java.util.List
    public A remove(int i) {
        throw new UnsupportedOperationException();
    }

    public List<A> reverse() {
        if (isEmpty() || this.tail.isEmpty()) {
            return this;
        }
        List<A> listNil = nil();
        while (this.nonEmpty()) {
            List<A> list = new List<>(this.head, listNil);
            this = this.tail;
            listNil = list;
        }
        return listNil;
    }

    @Override // java.util.List
    public A set(int i, A a) {
        throw new UnsupportedOperationException();
    }

    public List<A> setTail(List<A> list) {
        this.tail = list;
        return list;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return length();
    }

    @Override // java.util.List
    public java.util.List<A> subList(int i, int i2) {
        if (i < 0 || i2 > size() || i > i2) {
            j2d.a();
            return null;
        }
        ArrayList arrayList = new ArrayList(i2 - i);
        for (int i3 = 0; this.tail != null && i3 != i2; i3++) {
            if (i3 >= i) {
                arrayList.add(this.head);
            }
            this = this.tail;
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<A> take(int i) {
        ListBuffer listBuffer = new ListBuffer();
        int i2 = 0;
        for (A a : this) {
            int i3 = i2 + 1;
            if (i2 == i) {
                break;
            }
            listBuffer.append(a);
            i2 = i3;
        }
        return listBuffer.toList();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        int i = 0;
        List<A> list = this;
        while (list.nonEmpty() && i < tArr.length) {
            tArr[i] = list.head;
            list = list.tail;
            i++;
        }
        if (!list.isEmpty()) {
            return (T[]) toArray((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size()));
        }
        if (i < tArr.length) {
            tArr[i] = 0;
        }
        return tArr;
    }

    public String toString(String str) {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.head);
        while (true) {
            this = this.tail;
            if (!this.nonEmpty()) {
                return sb.toString();
            }
            sb.append(str);
            sb.append(this.head);
        }
    }

    public List<A> appendList(List<A> list) {
        return list.prependList(this);
    }

    @Override // java.util.List
    public ListIterator<A> listIterator(int i) {
        return Collections.unmodifiableList(new ArrayList(this)).listIterator(i);
    }

    public static <A> List<A> of(A a, A a2) {
        return new List<>(a, of((Object) a2));
    }

    public static <A> List<A> of(A a, A a2, A a3) {
        return new List<>(a, of((Object) a2, (Object) a3));
    }

    public static <A> List<A> of(A a) {
        return new List<>(a, nil());
    }

    public static <A> List<A> from(A[] aArr) {
        List<A> listNil = nil();
        if (aArr != null) {
            int length = aArr.length - 1;
            while (length >= 0) {
                List<A> list = new List<>(aArr[length], listNil);
                length--;
                listNil = list;
            }
        }
        return listNil;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return toString(",");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public static boolean equals(List<?> list, List<?> list2) {
        List<?> list3;
        while (true) {
            list3 = list.tail;
            if (list3 == null || list2.tail == null) {
                break;
            }
            A a = list.head;
            A a2 = list2.head;
            if (a == null) {
                if (a2 != null) {
                    return false;
                }
            } else if (!a.equals(a2)) {
                return false;
            }
            list = (List<A>) list.tail;
            list2 = (List<A>) list2.tail;
        }
        return list3 == null && list2.tail == null;
    }
}
