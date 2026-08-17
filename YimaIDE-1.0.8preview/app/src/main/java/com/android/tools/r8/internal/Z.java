package com.android.tools.r8.internal;

import defpackage.q5g;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Z extends V implements InterfaceC1981lA {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(List list) {
        if (list == this) {
            return 0;
        }
        if (list instanceof InterfaceC1981lA) {
            InterfaceC2067mA interfaceC2067mAO = o(0);
            InterfaceC2067mA interfaceC2067mAListIterator = ((InterfaceC1981lA) list).listIterator();
            while (interfaceC2067mAO.hasNext() && interfaceC2067mAListIterator.hasNext()) {
                int iCompare = Integer.compare(interfaceC2067mAO.q(), interfaceC2067mAListIterator.q());
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            if (interfaceC2067mAListIterator.hasNext()) {
                return -1;
            }
            return interfaceC2067mAO.hasNext() ? 1 : 0;
        }
        InterfaceC2067mA interfaceC2067mAO2 = o(0);
        ListIterator listIterator = list.listIterator();
        while (interfaceC2067mAO2.hasNext() && listIterator.hasNext()) {
            int iCompareTo = Integer.valueOf(((W) interfaceC2067mAO2).q()).compareTo(listIterator.next());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (listIterator.hasNext()) {
            return -1;
        }
        return interfaceC2067mAO2.hasNext() ? 1 : 0;
    }

    public abstract boolean a(int i, InterfaceC1981lA interfaceC1981lA);

    public void add(int i, Object obj) {
        b(i, ((Integer) obj).intValue());
    }

    @Override // com.android.tools.r8.internal.V, com.android.tools.r8.internal.InterfaceC1215cA
    public abstract boolean add(int i);

    public boolean addAll(int i, Collection collection) {
        k(i);
        Iterator it = collection.iterator();
        boolean zHasNext = it.hasNext();
        while (it.hasNext()) {
            b(i, ((Integer) it.next()).intValue());
            i++;
        }
        return zHasNext;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        a(0, size());
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public InterfaceC1981lA subList(int i, int i2) {
        k(i);
        k(i2);
        if (i <= i2) {
            return new Y(this, i, i2);
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
        if (list instanceof InterfaceC1981lA) {
            InterfaceC2067mA interfaceC2067mAO = o(0);
            InterfaceC2067mA interfaceC2067mAListIterator = ((InterfaceC1981lA) list).listIterator();
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return true;
                }
                if (interfaceC2067mAO.q() != interfaceC2067mAListIterator.q()) {
                    return false;
                }
                size = i;
            }
        } else {
            Iterator itO = o(0);
            ListIterator listIterator = list.listIterator();
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return true;
                }
                if (!Integer.valueOf(((W) itO).q()).equals(listIterator.next())) {
                    return false;
                }
                size = i2;
            }
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean f(int i) {
        return m(i) >= 0;
    }

    public Object get(int i) {
        return Integer.valueOf(i(i));
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        InterfaceC2067mA interfaceC2067mAO = o(0);
        int size = size();
        int iQ = 1;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                return iQ;
            }
            iQ = (iQ * 31) + interfaceC2067mAO.q();
            size = i;
        }
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return m(((Integer) obj).intValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public InterfaceC1640hA iterator() {
        return o(0);
    }

    public final void k(int i) {
        if (i < 0) {
            jb9.a(AbstractC1784iv.a(i, "Index (", ") is negative"));
        } else {
            if (i <= size()) {
                return;
            }
            q5g.a(Ni0.a(i, "Index (", ") is greater than list size ("), size());
        }
    }

    public final void l(int i) {
        if (i < 0) {
            jb9.a(AbstractC1784iv.a(i, "Index (", ") is negative"));
        } else {
            if (i < size()) {
                return;
            }
            q5g.a(Ni0.a(i, "Index (", ") is greater than or equal to list size ("), size());
        }
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return n(((Integer) obj).intValue());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA, java.util.List
    public final InterfaceC2067mA listIterator() {
        return o(0);
    }

    public int m(int i) {
        InterfaceC2067mA interfaceC2067mAO = o(0);
        while (interfaceC2067mAO.hasNext()) {
            if (i == interfaceC2067mAO.q()) {
                return interfaceC2067mAO.previousIndex();
            }
        }
        return -1;
    }

    public int n(int i) {
        InterfaceC2067mA interfaceC2067mAO = o(size());
        while (interfaceC2067mAO.hasPrevious()) {
            if (i == interfaceC2067mAO.g()) {
                return interfaceC2067mAO.nextIndex();
            }
        }
        return -1;
    }

    public abstract InterfaceC2067mA o(int i);

    public Object remove(int i) {
        return Integer.valueOf(h(i));
    }

    public Object set(int i, Object obj) {
        return Integer.valueOf(c(i, ((Integer) obj).intValue()));
    }

    @Override // com.android.tools.r8.internal.V, java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        InterfaceC2067mA interfaceC2067mAO = o(0);
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
            sb.append(String.valueOf(interfaceC2067mAO.q()));
            size = i;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC2067mA iterator() {
        return o(0);
    }

    public ListIterator listIterator() {
        return o(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return o(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
