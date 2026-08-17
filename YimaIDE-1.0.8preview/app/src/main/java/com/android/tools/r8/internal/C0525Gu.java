package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0525Gu extends AbstractC0551Hu {
    public final transient int d;
    public final transient int e;
    public final /* synthetic */ AbstractC0551Hu f;

    public C0525Gu(AbstractC0551Hu abstractC0551Hu, int i, int i2) {
        this.f = abstractC0551Hu;
        this.d = i;
        this.e = i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    /* JADX INFO: renamed from: e */
    public final AbstractC0551Hu subList(int i, int i2) {
        DX.a(i, i2, this.e);
        AbstractC0551Hu abstractC0551Hu = this.f;
        int i3 = this.d;
        return abstractC0551Hu.subList(i + i3, i2 + i3);
    }

    @Override // java.util.List
    public final Object get(int i) {
        DX.a(i, this.e);
        return this.f.get(i + this.d);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator(int i) {
        return new C0447Du(this, size(), i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }
}
