package com.android.tools.r8.internal;

import defpackage.q5g;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2990x1 extends AbstractC2733u1 implements S30 {
    public boolean addAll(int i, Collection collection) {
        j(i);
        Iterator it = collection.iterator();
        boolean zHasNext = it.hasNext();
        while (it.hasNext()) {
            add(i, it.next());
            i++;
        }
        return zHasNext;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        a(0, size());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public C2904w1 subList(int i, int i2) {
        j(i);
        j(i2);
        if (i <= i2) {
            return new C2904w1(this, i, i2);
        }
        qnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        HU huL = l(0);
        ListIterator listIterator = list.listIterator();
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return true;
            }
            if (huL.next() != listIterator.next()) {
                return false;
            }
            size = i;
        }
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        HU huL = l(0);
        int size = size();
        int iIdentityHashCode = 1;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iIdentityHashCode;
            }
            iIdentityHashCode = (iIdentityHashCode * 31) + System.identityHashCode(huL.next());
            size = i;
        }
    }

    public int indexOf(Object obj) {
        HU huL = l(0);
        while (huL.hasNext()) {
            if (obj == huL.next()) {
                return huL.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.P30
    public BU iterator() {
        return l(0);
    }

    public final void j(int i) {
        if (i < 0) {
            jb9.a(AbstractC1784iv.a(i, "Index (", ") is negative"));
        } else {
            if (i <= size()) {
                return;
            }
            q5g.a(Ni0.a(i, "Index (", ") is greater than list size ("), size());
        }
    }

    public final void k(int i) {
        if (i < 0) {
            jb9.a(AbstractC1784iv.a(i, "Index (", ") is negative"));
        } else {
            if (i < size()) {
                return;
            }
            q5g.a(Ni0.a(i, "Index (", ") is greater than or equal to list size ("), size());
        }
    }

    public abstract HU l(int i);

    public int lastIndexOf(Object obj) {
        HU huL = l(size());
        while (huL.hasPrevious()) {
            if (obj == huL.previous()) {
                return huL.nextIndex();
            }
        }
        return -1;
    }

    public ListIterator listIterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC2733u1, java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        HU huL = l(0);
        int size = size();
        boolean z = true;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                sb.append("]");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            E next = huL.next();
            if (this == next) {
                sb.append("(this list)");
            } else {
                sb.append(String.valueOf(next));
            }
            size = i;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return l(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
