package com.android.tools.r8.internal;

import defpackage.w0g;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y extends Z implements Serializable {
    public static final /* synthetic */ boolean e = true;
    public final InterfaceC1981lA b;
    public final int c;
    public int d;

    public Y(InterfaceC1981lA interfaceC1981lA, int i, int i2) {
        this.b = interfaceC1981lA;
        this.c = i;
        this.d = i2;
    }

    public final void a() {
        boolean z = e;
        if (!z && this.c > this.b.size()) {
            x1f.a();
            return;
        }
        if (!z && this.d > this.b.size()) {
            x1f.a();
        } else {
            if (z || this.d >= this.c) {
                return;
            }
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.Z, com.android.tools.r8.internal.V, com.android.tools.r8.internal.InterfaceC1215cA
    public final boolean add(int i) {
        this.b.b(this.d, i);
        this.d++;
        if (!e) {
            a();
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final boolean addAll(int i, Collection collection) {
        k(i);
        this.d = collection.size() + this.d;
        return this.b.addAll(this.c + i, collection);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void b(int i, int i2) {
        k(i);
        this.b.b(this.c + i, i2);
        this.d++;
        if (e) {
            return;
        }
        a();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int c(int i, int i2) {
        l(i);
        return this.b.c(this.c + i, i2);
    }

    @Override // com.android.tools.r8.internal.Z, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((List) obj);
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    /* JADX INFO: renamed from: e */
    public final InterfaceC1981lA subList(int i, int i2) {
        k(i);
        k(i2);
        if (i <= i2) {
            return new Y(this, i, i2);
        }
        pnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final Object get(int i) {
        return Integer.valueOf(i(i));
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int h(int i) {
        l(i);
        this.d--;
        return this.b.h(this.c + i);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final int i(int i) {
        l(i);
        return this.b.i(this.c + i);
    }

    @Override // com.android.tools.r8.internal.Z, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1215cA, java.util.Set
    public final InterfaceC1640hA iterator() {
        return o(0);
    }

    @Override // com.android.tools.r8.internal.V
    public final boolean j(int i) {
        int iM = m(i);
        if (iM == -1) {
            return false;
        }
        this.d--;
        this.b.h(this.c + iM);
        if (!e) {
            a();
        }
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        k(i);
        return new X(this, i);
    }

    @Override // com.android.tools.r8.internal.Z
    public final InterfaceC2067mA o(int i) {
        k(i);
        return new X(this, i);
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final Object remove(int i) {
        return Integer.valueOf(h(i));
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final Object set(int i, Object obj) {
        return Integer.valueOf(c(i, ((Integer) obj).intValue()));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d - this.c;
    }

    @Override // com.android.tools.r8.internal.Z, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return o(0);
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final ListIterator listIterator() {
        return o(0);
    }

    @Override // com.android.tools.r8.internal.Z, java.util.List
    public final void add(int i, Object obj) {
        b(i, ((Integer) obj).intValue());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int[] iArr, int i2, int i3) {
        k(i);
        int i4 = i + i3;
        int i5 = this.d;
        int i6 = this.c;
        if (i4 <= i5 - i6) {
            this.b.a(i6 + i, iArr, i2, i3);
        } else {
            w0g.a(i, i3, this.d - this.c);
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1981lA
    public final void a(int i, int i2) {
        k(i);
        k(i2);
        InterfaceC1981lA interfaceC1981lA = this.b;
        int i3 = this.c;
        interfaceC1981lA.a(i3 + i, i3 + i2);
        this.d -= i2 - i;
        if (e) {
            return;
        }
        a();
    }

    public final boolean a(int i, InterfaceC1215cA interfaceC1215cA) {
        k(i);
        k(i);
        InterfaceC1640hA it = interfaceC1215cA.iterator();
        boolean zHasNext = it.hasNext();
        while (it.hasNext()) {
            b(i, it.q());
            i++;
        }
        return zHasNext;
    }

    @Override // com.android.tools.r8.internal.Z
    public final boolean a(int i, InterfaceC1981lA interfaceC1981lA) {
        k(i);
        return a(i, (InterfaceC1215cA) interfaceC1981lA);
    }
}
