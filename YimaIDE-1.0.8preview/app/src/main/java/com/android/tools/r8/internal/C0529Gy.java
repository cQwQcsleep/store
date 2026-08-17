package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0529Gy extends AbstractC1365e1 {
    public final C0503Fy b = new C0503Fy(this);
    public final /* synthetic */ C0944Wy c;

    public C0529Gy(C0944Wy c0944Wy) {
        this.c = c0944Wy;
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
        return ((C0866Ty) this.c.c(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final InterfaceC2942wU iterator() {
        return new C0607Jy(this.c);
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
            C0581Iy c0581IyD = this.c.d(((Integer) entry.getKey()).intValue());
            if (c0581IyD != null) {
                this.c.remove(c0581IyD.b);
            }
            if (c0581IyD != null) {
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
        C0944Wy c0944Wy = this.c;
        int iA = ((InterfaceC1959kz) obj).a();
        int iA2 = ((InterfaceC1959kz) obj2).a();
        c0944Wy.getClass();
        return new C0866Ty(c0944Wy, iA, false, iA2, false).c();
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return ((C0866Ty) this.c.b(((InterfaceC1959kz) obj).a())).c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return new C0607Jy(this.c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C0607Jy(this.c);
    }
}
