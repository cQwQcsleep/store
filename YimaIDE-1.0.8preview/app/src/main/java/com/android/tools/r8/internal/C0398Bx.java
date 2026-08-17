package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0398Bx extends AbstractC1365e1 {
    public final C0372Ax b = new C0372Ax(this);
    public final /* synthetic */ C0813Rx c;

    public C0398Bx(C0813Rx c0813Rx) {
        this.c = c0813Rx;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return this.b;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() == null || !(entry.getKey() instanceof Integer)) {
            return false;
        }
        return entry.equals(this.c.d(((Integer) entry.getKey()).intValue()));
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return this.c.e;
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return ((C0735Ox) this.c.c(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C0476Ex(this.c);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return this.c.f;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer)) {
            C0450Dx c0450DxD = this.c.d(((Integer) entry.getKey()).intValue());
            if (c0450DxD != null) {
                this.c.remove(c0450DxD.b);
            }
            if (c0450DxD != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.c.d;
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        C0813Rx c0813Rx = this.c;
        int iA = ((InterfaceC0943Wx) obj).a();
        int iA2 = ((InterfaceC0943Wx) obj2).a();
        c0813Rx.getClass();
        return new C0735Ox(c0813Rx, iA, false, iA2, false).b();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C0735Ox) this.c.b(((InterfaceC0943Wx) obj).a())).b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0476Ex(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0476Ex(this.c);
    }
}
