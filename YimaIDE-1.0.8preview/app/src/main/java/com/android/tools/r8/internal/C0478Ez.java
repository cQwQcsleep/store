package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ez, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0478Ez extends AbstractC1365e1 {
    public final /* synthetic */ C0659Lz b;

    public C0478Ez(C0659Lz c0659Lz) {
        this.b = c0659Lz;
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
        C0374Az c0374AzD;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && (c0374AzD = this.b.j.d(((Integer) entry.getKey()).intValue())) != null && this.b.d(c0374AzD.b) && entry.equals(c0374AzD);
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.b.i();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return ((C0659Lz) this.b.c(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        C0659Lz c0659Lz = this.b;
        C0374Az c0374Az = c0659Lz.j.e;
        return !(c0659Lz.i() != null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C0556Hz(this.b);
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
            C0374Az c0374AzD = this.b.j.d(((Integer) entry.getKey()).intValue());
            if (c0374AzD != null && this.b.d(c0374AzD.b)) {
                this.b.remove(c0374AzD.b);
            }
            if (c0374AzD != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        C0556Hz c0556Hz = new C0556Hz(this.b);
        int i = 0;
        while (c0556Hz.hasNext()) {
            i++;
            c0556Hz.next();
        }
        return i;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return ((C0659Lz) this.b.a(((InterfaceC1959kz) obj).a(), ((InterfaceC1959kz) obj2).a())).c();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C0659Lz) this.b.b(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0556Hz(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0556Hz(this.b);
    }
}
