package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1370e30 extends AbstractC1365e1 implements InterfaceC2567s30 {
    public final /* synthetic */ C1627h30 b;

    public C1370e30(C1627h30 c1627h30) {
        this.b = c1627h30;
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
        C1627h30 c1627h30;
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getValue() != null && (entry.getValue() instanceof Integer)) {
            Object key = entry.getKey();
            int iIntValue = ((Integer) entry.getValue()).intValue();
            C1627h30 c1627h31 = this.b;
            if (key == null) {
                return c1627h31.f && c1627h31.d[c1627h31.j] == iIntValue;
            }
            Object[] objArr = c1627h31.c;
            int iA = AbstractC0938Ws.a(System.identityHashCode(key));
            C1627h30 c1627h32 = this.b;
            int i = iA & c1627h32.e;
            Object obj3 = objArr[i];
            if (obj3 == null) {
                return false;
            }
            if (key == obj3) {
                return c1627h32.d[i] == iIntValue;
            }
            do {
                c1627h30 = this.b;
                i = (i + 1) & c1627h30.e;
                obj2 = objArr[i];
                if (obj2 == null) {
                    return false;
                }
            } while (key != obj2);
            if (c1627h30.d[i] == iIntValue) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.SortedSet
    public final Object first() {
        C1627h30 c1627h30 = this.b;
        if (c1627h30.l != 0) {
            return new C1286d30(c1627h30, c1627h30.g);
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
        return new C1032a30(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        C1627h30 c1627h30 = this.b;
        if (c1627h30.l != 0) {
            return new C1286d30(c1627h30, c1627h30.h);
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
        C1627h30 c1627h30 = this.b;
        if (key == null) {
            if (c1627h30.f) {
                int[] iArr = c1627h30.d;
                int i2 = c1627h30.j;
                if (iArr[i2] == iIntValue) {
                    c1627h30.f = false;
                    c1627h30.c[i2] = null;
                    c1627h30.l--;
                    c1627h30.e(i2);
                    if (c1627h30.l < c1627h30.k / 4 && (i = c1627h30.j) > 16) {
                        c1627h30.f(i / 2);
                    }
                    return true;
                }
            }
            return false;
        }
        Object[] objArr = c1627h30.c;
        int iA = AbstractC0938Ws.a(System.identityHashCode(key));
        C1627h30 c1627h31 = this.b;
        int i3 = iA & c1627h31.e;
        Object obj2 = objArr[i3];
        if (obj2 == null) {
            return false;
        }
        if (obj2 == key) {
            if (c1627h31.d[i3] != iIntValue) {
                return false;
            }
            c1627h31.g(i3);
            return true;
        }
        while (true) {
            C1627h30 c1627h32 = this.b;
            i3 = (i3 + 1) & c1627h32.e;
            Object obj3 = objArr[i3];
            if (obj3 == null) {
                return false;
            }
            if (obj3 == key && c1627h32.d[i3] == iIntValue) {
                c1627h32.g(i3);
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
        return new C1032a30(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1032a30(this.b);
    }
}
