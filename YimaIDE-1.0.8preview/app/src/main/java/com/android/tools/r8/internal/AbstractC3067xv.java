package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3067xv extends AbstractC3151yv implements NavigableSet, Sc0 {
    public static final /* synthetic */ int g = 0;
    public final transient Comparator e;
    public transient AbstractC3067xv f;

    public AbstractC3067xv(Comparator comparator) {
        this.e = comparator;
    }

    public static X40 a(Comparator comparator) {
        return C2767uQ.b.equals(comparator) ? X40.i : new X40(P40.e, comparator);
    }

    @Override // java.util.SortedSet, com.android.tools.r8.internal.Sc0
    public final Comparator comparator() {
        return this.e;
    }

    @Override // java.util.NavigableSet
    public final NavigableSet headSet(Object obj, boolean z) {
        obj.getClass();
        X40 x40 = (X40) this;
        return x40.e(0, x40.a(obj, z));
    }

    public abstract int indexOf(Object obj);

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final AbstractC3067xv descendingSet() {
        AbstractC3067xv abstractC3067xvA = this.f;
        if (abstractC3067xvA == null) {
            X40 x40 = (X40) this;
            Comparator comparatorReverseOrder = Collections.reverseOrder(x40.e);
            abstractC3067xvA = x40.isEmpty() ? a(comparatorReverseOrder) : new X40(x40.h.j(), comparatorReverseOrder);
            this.f = abstractC3067xvA;
            abstractC3067xvA.f = this;
        }
        return abstractC3067xvA;
    }

    @Override // java.util.NavigableSet
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
        if (this.e.compare(obj, obj2) > 0) {
            j2d.a();
            return null;
        }
        X40 x40 = (X40) this;
        X40 x40E = x40.e(x40.b(obj, true), x40.h.size());
        return x40E.e(0, x40E.a(obj2, false));
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        obj.getClass();
        X40 x40 = (X40) this;
        return x40.e(x40.b(obj, true), x40.h.size());
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        obj.getClass();
        X40 x40 = (X40) this;
        return x40.e(0, x40.a(obj, false));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet tailSet(Object obj, boolean z) {
        obj.getClass();
        X40 x40 = (X40) this;
        return x40.e(x40.b(obj, z), x40.h.size());
    }

    @Override // java.util.NavigableSet
    public final NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        obj.getClass();
        obj2.getClass();
        if (this.e.compare(obj, obj2) <= 0) {
            X40 x40 = (X40) this;
            X40 x40E = x40.e(x40.b(obj, z), x40.h.size());
            return x40E.e(0, x40E.a(obj2, z2));
        }
        j2d.a();
        return null;
    }
}
