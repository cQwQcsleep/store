package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1957kx extends AbstractC1365e1 {
    public final /* synthetic */ C2214nx b;

    public C1957kx(C2214nx c2214nx) {
        this.b = c2214nx;
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
        C2214nx c2214nx;
        int i;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer) && entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            C2214nx c2214nx2 = this.b;
            if (iIntValue == 0) {
                return c2214nx2.f && c2214nx2.d[c2214nx2.j] == iIntValue2;
            }
            int[] iArr = c2214nx2.c;
            int iA = AbstractC0938Ws.a(iIntValue);
            C2214nx c2214nx3 = this.b;
            int i2 = iA & c2214nx3.e;
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            if (iIntValue == i3) {
                return c2214nx3.d[i2] == iIntValue2;
            }
            do {
                c2214nx = this.b;
                i2 = (i2 + 1) & c2214nx.e;
                i = iArr[i2];
                if (i == 0) {
                    return false;
                }
            } while (iIntValue != i);
            if (c2214nx.d[i2] == iIntValue2) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C2214nx c2214nx = this.b;
        if (c2214nx.l != 0) {
            return new C1872jx(c2214nx, c2214nx.g);
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
        return new C1616gx(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C2214nx c2214nx = this.b;
        if (c2214nx.l != 0) {
            return new C1872jx(c2214nx, c2214nx.h);
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
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer) || entry.getValue() == null || !(entry.getValue() instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Integer) entry.getKey()).intValue();
        int iIntValue2 = ((Integer) entry.getValue()).intValue();
        C2214nx c2214nx = this.b;
        if (iIntValue == 0) {
            if (c2214nx.f) {
                int[] iArr = c2214nx.d;
                int i2 = c2214nx.j;
                if (iArr[i2] == iIntValue2) {
                    c2214nx.f = false;
                    c2214nx.l--;
                    c2214nx.e(i2);
                    if (c2214nx.l < c2214nx.k / 4 && (i = c2214nx.j) > 16) {
                        c2214nx.f(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        int[] iArr2 = c2214nx.c;
        int iA = AbstractC0938Ws.a(iIntValue);
        C2214nx c2214nx2 = this.b;
        int i3 = iA & c2214nx2.e;
        int i4 = iArr2[i3];
        if (i4 == 0) {
            return false;
        }
        if (i4 == iIntValue) {
            if (c2214nx2.d[i3] != iIntValue2) {
                return false;
            }
            c2214nx2.g(i3);
            return true;
        }
        while (true) {
            C2214nx c2214nx3 = this.b;
            i3 = (i3 + 1) & c2214nx3.e;
            int i5 = iArr2[i3];
            if (i5 == 0) {
                return false;
            }
            if (i5 == iIntValue && c2214nx3.d[i3] == iIntValue2) {
                c2214nx3.g(i3);
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
        return new C1616gx(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1616gx(this.b);
    }
}
