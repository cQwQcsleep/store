package com.android.tools.r8.internal;

import defpackage.q5g;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1113b1 extends X0 implements GU {
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(List list) {
        if (list == this) {
            return 0;
        }
        if (list instanceof GU) {
            HU huL = l(0);
            HU huL2 = ((AbstractC1113b1) ((GU) list)).l(0);
            while (huL.hasNext() && huL2.hasNext()) {
                int iCompareTo = ((Comparable) huL.next()).compareTo(huL2.next());
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            }
            if (huL2.hasNext()) {
                return -1;
            }
            return huL.hasNext() ? 1 : 0;
        }
        HU huL3 = l(0);
        ListIterator listIterator = list.listIterator();
        while (huL3.hasNext() && listIterator.hasNext()) {
            int iCompareTo2 = ((Comparable) huL3.next()).compareTo(listIterator.next());
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (listIterator.hasNext()) {
            return -1;
        }
        return huL3.hasNext() ? 1 : 0;
    }

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
    public C1027a1 subList(int i, int i2) {
        j(i);
        j(i2);
        if (i <= i2) {
            return new C1027a1(this, i, i2);
        }
        qnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        boolean zEquals;
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
            Object next = huL.next();
            Object next2 = listIterator.next();
            if (next == null) {
                zEquals = next2 == null;
            } else {
                zEquals = next.equals(next2);
            }
            if (!zEquals) {
                return false;
            }
            size = i;
        }
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        HU huL = l(0);
        int size = size();
        int iHashCode = 1;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iHashCode;
            }
            E next = huL.next();
            iHashCode = (iHashCode * 31) + (next == 0 ? 0 : next.hashCode());
            size = i;
        }
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        HU huL = l(0);
        while (huL.hasNext()) {
            Object next = huL.next();
            if (obj == null) {
                if (next == null) {
                    return huL.previousIndex();
                }
            } else if (obj.equals(next)) {
                return huL.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
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

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        HU huL = l(size());
        while (huL.hasPrevious()) {
            Object objPrevious = huL.previous();
            if (obj == null) {
                if (objPrevious == null) {
                    return huL.nextIndex();
                }
            } else if (obj.equals(objPrevious)) {
                return huL.nextIndex();
            }
        }
        return -1;
    }

    public ListIterator listIterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.X0, java.util.AbstractCollection
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
