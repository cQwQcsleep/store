package com.android.tools.r8.internal;

import defpackage.tc6;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0551Hu extends AbstractC3066xu implements List, RandomAccess {
    public static final /* synthetic */ int c = 0;

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object... objArr) {
        if (!(objArr.length <= 2147483635)) {
            w01.a("the total number of elements must fit in an int");
            return null;
        }
        int length = objArr.length + 12;
        Object[] objArr2 = new Object[length];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        objArr2[6] = obj7;
        objArr2[7] = obj8;
        objArr2[8] = obj9;
        objArr2[9] = obj10;
        objArr2[10] = obj11;
        objArr2[11] = obj12;
        System.arraycopy(objArr, 0, objArr2, 12, objArr.length);
        Object[] objArrA = AbstractC2856vU.a(length, objArr2);
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu b(int i, Object[] objArr) {
        if (i == 0) {
            return P40.e;
        }
        if (i != 1) {
            if (i < objArr.length) {
                objArr = Arrays.copyOf(objArr, i);
            }
            return new P40(objArr);
        }
        Object obj = objArr[0];
        Objects.requireNonNull(obj);
        return new Bc0(obj);
    }

    public static Bc0 c(Object obj) {
        return new Bc0(obj);
    }

    public static C0473Eu g() {
        return new C0473Eu(4);
    }

    public static P40 i() {
        return P40.e;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public AbstractC0551Hu subList(int i, int i2) {
        DX.a(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return P40.e;
        }
        return i3 == 1 ? new Bc0(get(i)) : f(i, i2);
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof List) {
                List list = (List) obj;
                int size = size();
                if (size == list.size()) {
                    if (!(list instanceof RandomAccess)) {
                        Iterator it = iterator();
                        Iterator it2 = list.iterator();
                        while (it.hasNext()) {
                            if (it2.hasNext() && WU.a(it.next(), it2.next())) {
                            }
                        }
                        return !it2.hasNext();
                    }
                    for (int i = 0; i < size; i++) {
                        if (WU.a(get(i), list.get(i))) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public AbstractC0551Hu f(int i, int i2) {
        return new C0525Gu(this, i, i2 - i);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer consumer) {
        DX.a(consumer);
        int size = size();
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~(get(i2).hashCode() + (i * 31)));
        }
        return i;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Ck0 iterator() {
        return listIterator(0);
    }

    public AbstractC0551Hu j() {
        return size() <= 1 ? this : new C0499Fu(this);
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public Spliterator spliterator() {
        return AbstractC1165be.a(size(), 1296, new tc6(this), (Comparator) null);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public F listIterator(int i) {
        return new C0447Du(this, size(), i);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2) {
        Object[] objArrA = AbstractC2856vU.a(2, new Object[]{obj, obj2});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3) {
        Object[] objArrA = AbstractC2856vU.a(3, new Object[]{obj, obj2, obj3});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4) {
        Object[] objArrA = AbstractC2856vU.a(4, new Object[]{obj, obj2, obj3, obj4});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArrA = AbstractC2856vU.a(5, new Object[]{obj, obj2, obj3, obj4, obj5});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] objArrA = AbstractC2856vU.a(6, new Object[]{obj, obj2, obj3, obj4, obj5, obj6});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Object[] objArrA = AbstractC2856vU.a(7, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        Object[] objArrA = AbstractC2856vU.a(8, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8});
        return b(objArrA.length, objArrA);
    }

    public static AbstractC0551Hu a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Object[] objArrA = AbstractC2856vU.a(10, new Object[]{obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10});
        return b(objArrA.length, objArrA);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final AbstractC0551Hu a() {
        return this;
    }

    public static AbstractC0551Hu a(Collection collection) {
        if (collection instanceof AbstractC3066xu) {
            AbstractC0551Hu abstractC0551HuA = ((AbstractC3066xu) collection).a();
            if (!abstractC0551HuA.e()) {
                return abstractC0551HuA;
            }
            Object[] array = abstractC0551HuA.toArray(AbstractC3066xu.b);
            return b(array.length, array);
        }
        Object[] array2 = collection.toArray();
        Object[] objArrA = AbstractC2856vU.a(array2.length, array2);
        return b(objArrA.length, objArrA);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public int a(int i, Object[] objArr) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }
}
