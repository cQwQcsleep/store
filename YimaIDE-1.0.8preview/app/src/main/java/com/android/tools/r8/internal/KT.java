package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KT extends AbstractC1365e1 {
    public final /* synthetic */ NT b;

    public KT(NT nt) {
        this.b = nt;
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
        NT nt;
        Object obj2;
        Object obj3;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        boolean zA = this.b.g.a(key, null);
        NT nt2 = this.b;
        if (zA) {
            return nt2.f && ((obj3 = nt2.d[nt2.k]) != null ? obj3.equals(value) : value == null);
        }
        Object[] objArr = nt2.c;
        int iA = AbstractC0938Ws.a(nt2.g.a(key));
        NT nt3 = this.b;
        int i = iA & nt3.e;
        Object obj4 = objArr[i];
        if (obj4 == null) {
            return false;
        }
        if (nt3.g.a(key, obj4)) {
            Object obj5 = this.b.d[i];
            if (obj5 == null) {
                return value == null;
            }
            return obj5.equals(value);
        }
        do {
            nt = this.b;
            i = (i + 1) & nt.e;
            obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
        } while (!nt.g.a(key, obj2));
        Object obj6 = this.b.d[i];
        if (obj6 == null) {
            return value == null;
        }
        return obj6.equals(value);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        NT nt = this.b;
        if (nt.m != 0) {
            return new JT(nt, nt.h);
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
        return new GT(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        NT nt = this.b;
        if (nt.m != 0) {
            return new JT(nt, nt.i);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        boolean zA = this.b.g.a(key, null);
        NT nt = this.b;
        if (zA) {
            if (!nt.f || ((obj2 = nt.d[nt.k]) != null ? !obj2.equals(value) : value != null)) {
                return false;
            }
            this.b.j();
            return true;
        }
        Object[] objArr = nt.c;
        int iA = AbstractC0938Ws.a(nt.g.a(key));
        NT nt2 = this.b;
        int i = iA & nt2.e;
        Object obj3 = objArr[i];
        if (obj3 == null) {
            return false;
        }
        if (nt2.g.a(obj3, key)) {
            Object obj4 = this.b.d[i];
            if (obj4 != null ? !obj4.equals(value) : value != null) {
                return false;
            }
            this.b.f(i);
            return true;
        }
        while (true) {
            NT nt3 = this.b;
            i = (i + 1) & nt3.e;
            Object obj5 = objArr[i];
            if (obj5 == null) {
                return false;
            }
            if (nt3.g.a(obj5, key)) {
                Object obj6 = this.b.d[i];
                if (obj6 == null) {
                    if (value == null) {
                        this.b.f(i);
                        return true;
                    }
                } else if (obj6.equals(value)) {
                    this.b.f(i);
                    return true;
                }
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.m;
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
        return new GT(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new GT(this.b);
    }
}
