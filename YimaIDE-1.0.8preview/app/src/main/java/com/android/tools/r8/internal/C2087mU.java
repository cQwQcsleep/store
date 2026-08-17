package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2087mU extends AbstractC1365e1 {
    public final /* synthetic */ C2344pU b;

    public C2087mU(C2344pU c2344pU) {
        this.b = c2344pU;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        C2344pU c2344pU = this.b;
        if (key == null) {
            return c2344pU.e && c2344pU.c[c2344pU.i] == value;
        }
        Object[] objArr = c2344pU.b;
        int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.d;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (key.equals(obj3)) {
            return this.b.c[iA] == value;
        }
        do {
            iA = (iA + 1) & this.b.d;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (!key.equals(obj2));
        return this.b.c[iA] == value;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C2344pU c2344pU = this.b;
        if (c2344pU.k != 0) {
            return new C2001lU(c2344pU, c2344pU.f);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C1746iU(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C2344pU c2344pU = this.b;
        if (c2344pU.k != 0) {
            return new C2001lU(c2344pU, c2344pU.g);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        C2344pU c2344pU = this.b;
        if (key == null) {
            if (!c2344pU.e || c2344pU.c[c2344pU.i] != value) {
                return false;
            }
            c2344pU.e();
            return true;
        }
        Object[] objArr = c2344pU.b;
        int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.d;
        Object obj2 = objArr[iA];
        if (obj2 == null) {
            return false;
        }
        if (obj2.equals(key)) {
            C2344pU c2344pU2 = this.b;
            if (c2344pU2.c[iA] != value) {
                return false;
            }
            c2344pU2.f(iA);
            return true;
        }
        while (true) {
            iA = (iA + 1) & this.b.d;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (obj3.equals(key)) {
                C2344pU c2344pU3 = this.b;
                if (c2344pU3.c[iA] == value) {
                    c2344pU3.f(iA);
                    return true;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.k;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C1746iU(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1746iU(this.b);
    }
}
