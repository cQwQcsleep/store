package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1618gz extends AbstractC1365e1 {
    public final /* synthetic */ C1874jz b;

    public C1618gz(C1874jz c1874jz) {
        this.b = c1874jz;
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
        C1874jz c1874jz;
        int i;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            Object value = entry.getValue();
            C1874jz c1874jz2 = this.b;
            if (iIntValue == 0) {
                return c1874jz2.f && c1874jz2.d[c1874jz2.j] == value;
            }
            int[] iArr = c1874jz2.c;
            int iA = AbstractC0938Ws.a(iIntValue);
            C1874jz c1874jz3 = this.b;
            int i2 = iA & c1874jz3.e;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            if (iIntValue == i3) {
                return c1874jz3.d[i2] == value;
            }
            do {
                c1874jz = this.b;
                i2 = (i2 + 1) & c1874jz.e;
                i = iArr[i2];
                if (i == 0) {
                    return false;
                }
            } while (iIntValue != i);
            if (c1874jz.d[i2] == value) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C1874jz c1874jz = this.b;
        if (c1874jz.l != 0) {
            return new C1533fz(c1874jz, c1874jz.g);
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
        return new C1277cz(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C1874jz c1874jz = this.b;
        if (c1874jz.l != 0) {
            return new C1533fz(c1874jz, c1874jz.h);
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
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        Object value = entry.getValue();
        C1874jz c1874jz = this.b;
        if (iIntValue == 0) {
            if (c1874jz.f) {
                Object[] objArr = c1874jz.d;
                int i2 = c1874jz.j;
                if (objArr[i2] == value) {
                    c1874jz.f = false;
                    objArr[i2] = null;
                    c1874jz.l--;
                    c1874jz.d(i2);
                    if (c1874jz.l < c1874jz.k / 4 && (i = c1874jz.j) > 16) {
                        c1874jz.e(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        int[] iArr = c1874jz.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C1874jz c1874jz2 = this.b;
        int i3 = iA & c1874jz2.e;
        int i4 = iArr[i3];
        if (i4 == 0) {
            return false;
        }
        if (i4 == iIntValue) {
            if (c1874jz2.d[i3] != value) {
                return false;
            }
            c1874jz2.f(i3);
            return true;
        }
        while (true) {
            C1874jz c1874jz3 = this.b;
            i3 = (i3 + 1) & c1874jz3.e;
            int i5 = iArr[i3];
            if (i5 == 0) {
                return false;
            }
            if (i5 == iIntValue && c1874jz3.d[i3] == value) {
                c1874jz3.f(i3);
                return true;
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
        return new C1277cz(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1277cz(this.b);
    }
}
