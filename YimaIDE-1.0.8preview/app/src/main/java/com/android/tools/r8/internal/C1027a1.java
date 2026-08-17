package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1027a1 extends AbstractC1113b1 implements Serializable {
    public static final /* synthetic */ boolean e = true;
    public final GU b;
    public final int c;
    public int d;

    public C1027a1(GU gu, int i, int i2) {
        this.b = gu;
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

    @Override // java.util.List
    public final void add(int i, Object obj) {
        j(i);
        this.b.add(this.c + i, obj);
        this.d++;
        if (e) {
            return;
        }
        a();
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.util.List
    public final boolean addAll(int i, Collection collection) {
        j(i);
        this.d = collection.size() + this.d;
        return this.b.addAll(this.c + i, collection);
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((List) obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.util.List
    /* JADX INFO: renamed from: e */
    public final C1027a1 subList(int i, int i2) {
        j(i);
        j(i2);
        if (i <= i2) {
            return new C1027a1(this, i, i2);
        }
        pnd.a("Start index (", i, ") is greater than end index (", i2, ")");
        return null;
    }

    @Override // java.util.List
    public final Object get(int i) {
        k(i);
        return this.b.get(this.c + i);
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC3028xU, com.android.tools.r8.internal.JU, java.util.Set
    public final BU iterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1
    public final HU l(int i) {
        j(i);
        return new Z0(this, i);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        j(i);
        return new Z0(this, i);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        k(i);
        this.d--;
        return this.b.remove(this.c + i);
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        k(i);
        return this.b.set(this.c + i, obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d - this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return l(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1113b1, java.util.List
    public final ListIterator listIterator() {
        return l(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        this.b.add(this.d, obj);
        this.d++;
        if (!e) {
            a();
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.GU
    public final void a(int i, int i2) {
        j(i);
        j(i2);
        GU gu = this.b;
        int i3 = this.c;
        gu.a(i3 + i, i3 + i2);
        this.d -= i2 - i;
        if (e) {
            return;
        }
        a();
    }
}
