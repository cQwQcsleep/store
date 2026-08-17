package com.android.tools.r8.internal;

import java.util.ListIterator;
import java.util.Spliterator;
import java.util.Spliterators;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P40 extends AbstractC0551Hu {
    public static final P40 e = new P40(new Object[0]);
    public final transient Object[] d;

    public P40(Object[] objArr) {
        this.d = objArr;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        Object[] objArr2 = this.d;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.d.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final Object[] b() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int c() {
        return this.d.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int d() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.d[i];
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu
    /* JADX INFO: renamed from: j */
    public final F listIterator(int i) {
        Object[] objArr = this.d;
        return NC.a(objArr.length, i, objArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator(int i) {
        Object[] objArr = this.d;
        return NC.a(objArr.length, i, objArr);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d.length;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this.d, 1296);
    }
}
