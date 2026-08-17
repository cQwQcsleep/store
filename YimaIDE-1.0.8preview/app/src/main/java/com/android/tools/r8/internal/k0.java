package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k0 extends AbstractC1961l0 implements Serializable {
    public static final /* synthetic */ boolean e = true;
    public final InterfaceC1566gM b;
    public final int c;
    public int d;

    public k0(InterfaceC1566gM interfaceC1566gM, int i, int i2) {
        this.b = interfaceC1566gM;
        this.c = i;
        this.d = i2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final void a(int i, int i2) {
        j(i);
        j(i2);
        InterfaceC1566gM interfaceC1566gM = this.b;
        int i3 = this.c;
        interfaceC1566gM.a(i3 + i, i3 + i2);
        this.d -= i2 - i;
        if (e) {
            return;
        }
        b();
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final void add(int i, Object obj) {
        a(i, ((Long) obj).longValue());
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final boolean addAll(int i, Collection collection) {
        j(i);
        this.d = collection.size() + this.d;
        return this.b.addAll(this.c + i, collection);
    }

    public final void b() {
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

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean c(long j) {
        int iD = d(j);
        if (iD == -1) {
            return false;
        }
        this.d--;
        this.b.d(this.c + iD);
        if (!e) {
            b();
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((List) obj);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long d(int i) {
        k(i);
        this.d--;
        return this.b.d(this.c + i);
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    /* JADX INFO: renamed from: e */
    public final k0 subList(int i, int i2) {
        j(i);
        j(i2);
        if (i <= i2) {
            return new k0(this, i, i2);
        }
        pnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final Object get(int i) {
        return Long.valueOf(a(i));
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0
    public final AbstractC2047m0 l(int i) {
        j(i);
        return new C1790j0(this, i);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        j(i);
        return new C1790j0(this, i);
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final Object remove(int i) {
        return Long.valueOf(d(i));
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final Object set(int i, Object obj) {
        return Long.valueOf(b(i, ((Long) obj).longValue()));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d - this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, java.util.List
    public final ListIterator listIterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1706i0
    public final boolean a(long j) {
        this.b.a(this.d, j);
        this.d++;
        if (!e) {
            b();
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final void a(int i, long j) {
        j(i);
        this.b.a(this.c + i, j);
        this.d++;
        if (e) {
            return;
        }
        b();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long a(int i) {
        k(i);
        return this.b.a(this.c + i);
    }

    @Override // com.android.tools.r8.internal.AbstractC1961l0, com.android.tools.r8.internal.AbstractC1706i0
    public final InterfaceC1481fM a() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1566gM
    public final long b(int i, long j) {
        k(i);
        return this.b.b(this.c + i, j);
    }
}
