package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.My, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0684My extends AbstractC1365e1 {
    public final /* synthetic */ C0866Ty b;

    public C0684My(C0866Ty c0866Ty) {
        this.b = c0866Ty;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b.j.c().comparator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        C0581Iy c0581IyD;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && (c0581IyD = this.b.j.d(((Integer) entry.getKey()).intValue())) != null && this.b.d(c0581IyD.b) && entry.equals(c0581IyD);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.i();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return ((C0866Ty) this.b.c(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        C0866Ty c0866Ty = this.b;
        C0581Iy c0581Iy = c0866Ty.j.e;
        return !(c0866Ty.i() != null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C0762Py(this.b);
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
            C0581Iy c0581IyD = this.b.j.d(((Integer) entry.getKey()).intValue());
            if (c0581IyD != null && this.b.d(c0581IyD.b)) {
                this.b.remove(c0581IyD.b);
            }
            if (c0581IyD != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        C0762Py c0762Py = new C0762Py(this.b);
        int i = 0;
        while (c0762Py.hasNext()) {
            i++;
            c0762Py.next();
        }
        return i;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return ((C0866Ty) this.b.a(((InterfaceC1959kz) obj).a(), ((InterfaceC1959kz) obj2).a())).c();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C0866Ty) this.b.b(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0762Py(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0762Py(this.b);
    }
}
