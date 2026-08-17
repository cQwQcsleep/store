package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0554Hx extends AbstractC1365e1 {
    public final /* synthetic */ C0735Ox b;

    public C0554Hx(C0735Ox c0735Ox) {
        this.b = c0735Ox;
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
        C0450Dx c0450DxD;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && (c0450DxD = this.b.j.d(((Integer) entry.getKey()).intValue())) != null && this.b.d(c0450DxD.b) && entry.equals(c0450DxD);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.i();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return ((C0735Ox) this.b.c(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        C0735Ox c0735Ox = this.b;
        C0450Dx c0450Dx = c0735Ox.j.e;
        return !(c0735Ox.i() != null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C0632Kx(this.b);
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
            C0450Dx c0450DxD = this.b.j.d(((Integer) entry.getKey()).intValue());
            if (c0450DxD != null && this.b.d(c0450DxD.b)) {
                this.b.remove(c0450DxD.b);
            }
            if (c0450DxD != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        C0632Kx c0632Kx = new C0632Kx(this.b);
        int i = 0;
        while (c0632Kx.hasNext()) {
            i++;
            c0632Kx.next();
        }
        return i;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return ((C0735Ox) this.b.a(((InterfaceC0943Wx) obj).a(), ((InterfaceC0943Wx) obj2).a())).b();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C0735Ox) this.b.b(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0632Kx(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0632Kx(this.b);
    }
}
