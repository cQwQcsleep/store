package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0499Fu extends AbstractC0551Hu {
    public final transient AbstractC0551Hu d;

    public C0499Fu(AbstractC0551Hu abstractC0551Hu) {
        this.d = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.d.contains(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    /* JADX INFO: renamed from: e */
    public final AbstractC0551Hu subList(int i, int i2) {
        DX.a(i, i2, this.d.size());
        AbstractC0551Hu abstractC0551Hu = this.d;
        return abstractC0551Hu.subList(abstractC0551Hu.size() - i2, this.d.size() - i).j();
    }

    @Override // java.util.List
    public final Object get(int i) {
        DX.a(i, this.d.size());
        AbstractC0551Hu abstractC0551Hu = this.d;
        return abstractC0551Hu.get((abstractC0551Hu.size() - 1) - i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final int indexOf(Object obj) {
        int iLastIndexOf = this.d.lastIndexOf(obj);
        if (iLastIndexOf >= 0) {
            return (this.d.size() - 1) - iLastIndexOf;
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu
    public final AbstractC0551Hu j() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final int lastIndexOf(Object obj) {
        int iIndexOf = this.d.indexOf(obj);
        if (iIndexOf >= 0) {
            return (this.d.size() - 1) - iIndexOf;
        }
        return -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator(int i) {
        return new C0447Du(this, size(), i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return this.d.e();
    }
}
