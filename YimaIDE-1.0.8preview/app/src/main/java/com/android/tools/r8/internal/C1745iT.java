package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1745iT extends AbstractC1365e1 {
    public final /* synthetic */ C2000lT b;

    public C1745iT(C2000lT c2000lT) {
        this.b = c2000lT;
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
        if (entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            Object key = entry.getKey();
            int iIntValue = ((Integer) entry.getValue()).intValue();
            C2000lT c2000lT = this.b;
            if (key == null) {
                return c2000lT.f && c2000lT.d[c2000lT.j] == iIntValue;
            }
            Object[] objArr = c2000lT.c;
            int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.e;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (key.equals(obj3)) {
                return this.b.d[iA] == iIntValue;
            }
            do {
                iA = (iA + 1) & this.b.e;
                obj2 = objArr[iA];
                if (obj2 == null) {
                    return false;
                }
            } while (!key.equals(obj2));
            if (this.b.d[iA] == iIntValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C2000lT c2000lT = this.b;
        if (c2000lT.l != 0) {
            return new C1659hT(c2000lT, c2000lT.g);
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
        return new C1402eT(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C2000lT c2000lT = this.b;
        if (c2000lT.l != 0) {
            return new C1659hT(c2000lT, c2000lT.h);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() == null || !(entry.getValue() instanceof Integer)) {
            return false;
        }
        Object key = entry.getKey();
        int iIntValue = ((Integer) entry.getValue()).intValue();
        C2000lT c2000lT = this.b;
        if (key == null) {
            if (c2000lT.f) {
                int[] iArr = c2000lT.d;
                int i2 = c2000lT.j;
                if (iArr[i2] == iIntValue) {
                    c2000lT.f = false;
                    c2000lT.c[i2] = null;
                    c2000lT.l--;
                    c2000lT.e(i2);
                    if (c2000lT.l < c2000lT.k / 4 && (i = c2000lT.j) > 16) {
                        c2000lT.f(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = c2000lT.c;
        int iA = AbstractC0938Ws.a(key.hashCode()) & this.b.e;
        Object obj2 = objArr[iA];
        if (obj2 == null) {
            return false;
        }
        if (obj2.equals(key)) {
            C2000lT c2000lT2 = this.b;
            if (c2000lT2.d[iA] != iIntValue) {
                return false;
            }
            c2000lT2.g(iA);
            return true;
        }
        while (true) {
            iA = (iA + 1) & this.b.e;
            Object obj3 = objArr[iA];
            if (obj3 == null) {
                return false;
            }
            if (obj3.equals(key)) {
                C2000lT c2000lT3 = this.b;
                if (c2000lT3.d[iA] == iIntValue) {
                    c2000lT3.g(iA);
                    return true;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.l;
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
        return new C1402eT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1402eT(this.b);
    }
}
