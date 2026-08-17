package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ry, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2557ry extends AbstractC1365e1 {
    public final /* synthetic */ C3154yy b;

    public C2557ry(C3154yy c3154yy) {
        this.b = c3154yy;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b.j.b().comparator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C2215ny c2215nyD;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && (c2215nyD = this.b.j.d(((Integer) entry.getKey()).intValue())) != null && this.b.d(c2215nyD.b) && entry.equals(c2215nyD);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.i();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return ((C3154yy) this.b.c(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        C3154yy c3154yy = this.b;
        C2215ny c2215ny = c3154yy.j.e;
        return !(c3154yy.i() != null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C2813uy(this.b);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.b.j();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            C2215ny c2215nyD = this.b.j.d(((Integer) entry.getKey()).intValue());
            if (c2215nyD != null && this.b.d(c2215nyD.b)) {
                this.b.remove(c2215nyD.b);
            }
            if (c2215nyD != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        C2813uy c2813uy = new C2813uy(this.b);
        int i = 0;
        while (c2813uy.hasNext()) {
            i++;
            c2813uy.next();
        }
        return i;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return ((C3154yy) this.b.a(((InterfaceC0943Wx) obj).a(), ((InterfaceC0943Wx) obj2).a())).b();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C3154yy) this.b.b(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C2813uy(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2813uy(this.b);
    }
}
