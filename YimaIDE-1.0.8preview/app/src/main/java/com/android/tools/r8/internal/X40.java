package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X40 extends AbstractC3067xv {
    public static final X40 i = new X40(P40.e, C2767uQ.b);
    public final transient AbstractC0551Hu h;

    public X40(AbstractC0551Hu abstractC0551Hu, Comparator comparator) {
        super(comparator);
        this.h = abstractC0551Hu;
    }

    public final int a(Object obj, boolean z) {
        AbstractC0551Hu abstractC0551Hu = this.h;
        obj.getClass();
        int iBinarySearch = Collections.binarySearch(abstractC0551Hu, obj, this.e);
        if (iBinarySearch >= 0) {
            return z ? iBinarySearch + 1 : iBinarySearch;
        }
        return ~iBinarySearch;
    }

    public final int b(Object obj, boolean z) {
        AbstractC0551Hu abstractC0551Hu = this.h;
        obj.getClass();
        int iBinarySearch = Collections.binarySearch(abstractC0551Hu, obj, this.e);
        if (iBinarySearch >= 0) {
            return z ? iBinarySearch : iBinarySearch + 1;
        }
        return ~iBinarySearch;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int c() {
        return this.h.c();
    }

    @Override // java.util.NavigableSet
    public final Object ceiling(Object obj) {
        int iB = b(obj, true);
        if (iB == this.h.size()) {
            return null;
        }
        return this.h.get(iB);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj != null) {
            try {
                if (Collections.binarySearch(this.h, obj, this.e) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        boolean zEquals;
        Comparator comparator;
        if (collection instanceof InterfaceC1231cQ) {
            collection = ((InterfaceC1231cQ) collection).F();
        }
        Comparator comparator2 = this.e;
        comparator2.getClass();
        collection.getClass();
        if (!(collection instanceof SortedSet)) {
            if (collection instanceof Sc0) {
                comparator = ((Sc0) collection).comparator();
            } else {
                zEquals = false;
            }
            if (zEquals || collection.size() <= 1) {
                return super.containsAll(collection);
            }
            Ck0 it = this.h.iterator();
            Iterator it2 = collection.iterator();
            if (!it.hasNext()) {
                return false;
            }
            Object next = it2.next();
            Object next2 = it.next();
            while (true) {
                try {
                    int iCompare = this.e.compare(next2, next);
                    if (iCompare < 0) {
                        if (!it.hasNext()) {
                            return false;
                        }
                        next2 = it.next();
                    } else if (iCompare == 0) {
                        if (!it2.hasNext()) {
                            return true;
                        }
                        next = it2.next();
                    } else if (iCompare > 0) {
                        break;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return false;
        }
        comparator = ((SortedSet) collection).comparator();
        if (comparator == null) {
            comparator = C2767uQ.b;
        }
        zEquals = comparator2.equals(comparator);
        if (zEquals) {
        }
        return super.containsAll(collection);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int d() {
        return this.h.d();
    }

    @Override // java.util.NavigableSet
    public final Iterator descendingIterator() {
        return this.h.j().iterator();
    }

    public final X40 e(int i2, int i3) {
        if (i2 == 0 && i3 == this.h.size()) {
            return this;
        }
        return i2 < i3 ? new X40(this.h.subList(i2, i3), this.e) : AbstractC3067xv.a(this.e);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0048  */
    /* JADX WARN: Code duplicated, block: B:29:0x0058 A[Catch: ClassCastException | NoSuchElementException -> 0x006c, TryCatch #0 {ClassCastException | NoSuchElementException -> 0x006c, blocks: (B:26:0x004c, B:27:0x0052, B:29:0x0058, B:31:0x0062), top: B:38:0x004c }] */
    /* JADX WARN: Code duplicated, block: B:36:0x006d  */
    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        boolean zEquals;
        Comparator comparator;
        Iterator it;
        Ck0 it2;
        Object next;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (this.h.size() != set.size()) {
            return false;
        }
        if (isEmpty()) {
            return true;
        }
        Comparator comparator2 = this.e;
        comparator2.getClass();
        if (!(set instanceof SortedSet)) {
            if (set instanceof Sc0) {
                comparator = ((Sc0) set).comparator();
            } else {
                zEquals = false;
            }
            if (zEquals) {
                return containsAll(set);
            }
            it = set.iterator();
            try {
                it2 = this.h.iterator();
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    next = it.next();
                    if (next != null || this.e.compare(next2, next) != 0) {
                        return false;
                    }
                }
                return true;
            } catch (ClassCastException | NoSuchElementException unused) {
                return false;
            }
        }
        comparator = ((SortedSet) set).comparator();
        if (comparator == null) {
            comparator = C2767uQ.b;
        }
        zEquals = comparator2.equals(comparator);
        if (zEquals) {
            return containsAll(set);
        }
        it = set.iterator();
        it2 = this.h.iterator();
        while (it2.hasNext()) {
            Object next3 = it2.next();
            next = it.next();
            if (next != null) {
            }
            return false;
        }
        return true;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        if (!isEmpty()) {
            return this.h.get(0);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.NavigableSet
    public final Object floor(Object obj) {
        int iA = a(obj, true) - 1;
        if (iA == -1) {
            return null;
        }
        return this.h.get(iA);
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        this.h.forEach(consumer);
    }

    @Override // java.util.NavigableSet
    public final Object higher(Object obj) {
        int iB = b(obj, false);
        if (iB == this.h.size()) {
            return null;
        }
        return this.h.get(iB);
    }

    @Override // com.android.tools.r8.internal.AbstractC3067xv
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int iBinarySearch = Collections.binarySearch(this.h, obj, this.e);
            if (iBinarySearch >= 0) {
                return iBinarySearch;
            }
            return -1;
        } catch (ClassCastException unused) {
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return this.h.iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        int size = this.h.size();
        AbstractC0551Hu abstractC0551Hu = this.h;
        return size <= 1 ? abstractC0551Hu : new C2639sv(this, abstractC0551Hu);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        if (isEmpty()) {
            z0e.a();
            return null;
        }
        AbstractC0551Hu abstractC0551Hu = this.h;
        return abstractC0551Hu.get(abstractC0551Hu.size() - 1);
    }

    @Override // java.util.NavigableSet
    public final Object lower(Object obj) {
        int iA = a(obj, false) - 1;
        if (iA == -1) {
            return null;
        }
        return this.h.get(iA);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.h.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return a().spliterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return this.h.iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i2, Object[] objArr) {
        return this.h.a(i2, objArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final Object[] b() {
        return this.h.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return this.h.e();
    }
}
