package com.android.tools.r8.internal;

import defpackage.q5g;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1961l0 extends AbstractC1706i0 implements InterfaceC1566gM {
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(List list) {
        if (list == this) {
            return 0;
        }
        if (list instanceof InterfaceC1566gM) {
            AbstractC2047m0 abstractC2047m0L = l(0);
            AbstractC2047m0 abstractC2047m0L2 = ((AbstractC1961l0) ((InterfaceC1566gM) list)).l(0);
            while (abstractC2047m0L.hasNext() && abstractC2047m0L2.hasNext()) {
                int iCompare = Long.compare(abstractC2047m0L.c(), abstractC2047m0L2.c());
                if (iCompare != 0) {
                    return iCompare;
                }
            }
            if (abstractC2047m0L2.hasNext()) {
                return -1;
            }
            return abstractC2047m0L.hasNext() ? 1 : 0;
        }
        AbstractC2047m0 abstractC2047m0L3 = l(0);
        ListIterator listIterator = list.listIterator();
        while (abstractC2047m0L3.hasNext() && listIterator.hasNext()) {
            int iCompareTo = Long.valueOf(abstractC2047m0L3.c()).compareTo(listIterator.next());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (listIterator.hasNext()) {
            return -1;
        }
        return abstractC2047m0L3.hasNext() ? 1 : 0;
    }

    public void add(int i, Object obj) {
        a(i, ((Long) obj).longValue());
    }

    public boolean addAll(int i, Collection collection) {
        j(i);
        Iterator it = collection.iterator();
        boolean zHasNext = it.hasNext();
        while (it.hasNext()) {
            a(i, ((Long) it.next()).longValue());
            i++;
        }
        return zHasNext;
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean b(long j) {
        return d(j) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        a(0, size());
    }

    public int d(long j) {
        AbstractC2047m0 abstractC2047m0L = l(0);
        while (abstractC2047m0L.hasNext()) {
            if (j == abstractC2047m0L.c()) {
                return abstractC2047m0L.previousIndex();
            }
        }
        return -1;
    }

    public int e(long j) {
        AbstractC2047m0 abstractC2047m0L = l(size());
        while (abstractC2047m0L.hasPrevious()) {
            if (j == abstractC2047m0L.a()) {
                return abstractC2047m0L.nextIndex();
            }
        }
        return -1;
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
        if (list instanceof InterfaceC1566gM) {
            AbstractC2047m0 abstractC2047m0L = l(0);
            AbstractC2047m0 abstractC2047m0L2 = ((AbstractC1961l0) ((InterfaceC1566gM) list)).l(0);
            while (true) {
                int i = size - 1;
                if (size == 0) {
                    return true;
                }
                if (abstractC2047m0L.c() != abstractC2047m0L2.c()) {
                    return false;
                }
                size = i;
            }
        } else {
            AbstractC2047m0 abstractC2047m0L3 = l(0);
            ListIterator listIterator = list.listIterator();
            while (true) {
                int i2 = size - 1;
                if (size == 0) {
                    return true;
                }
                if (!Long.valueOf(abstractC2047m0L3.c()).equals(listIterator.next())) {
                    return false;
                }
                size = i2;
            }
        }
    }

    public Object get(int i) {
        return Long.valueOf(a(i));
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        AbstractC2047m0 abstractC2047m0L = l(0);
        int size = size();
        int i = 1;
        while (true) {
            int i2 = size - 1;
            if (size == 0) {
                return i;
            }
            long jC = abstractC2047m0L.c();
            i = (i * 31) + ((int) (jC ^ (jC >>> 32)));
            size = i2;
        }
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return d(((Long) obj).longValue());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
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

    public abstract AbstractC2047m0 l(int i);

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return e(((Long) obj).longValue());
    }

    public ListIterator listIterator() {
        return l(0);
    }

    public Object remove(int i) {
        return Long.valueOf(d(i));
    }

    public Object set(int i, Object obj) {
        return Long.valueOf(b(i, ((Long) obj).longValue()));
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0, java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        AbstractC2047m0 abstractC2047m0L = l(0);
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
            sb.append(String.valueOf(abstractC2047m0L.c()));
            size = i;
        }
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public k0 subList(int i, int i2) {
        j(i);
        j(i2);
        if (i <= i2) {
            return new k0(this, i, i2);
        }
        qnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public InterfaceC1481fM a() {
        return l(0);
    }
}
