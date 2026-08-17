package com.android.tools.r8.internal;

import defpackage.tc6;
import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2639sv extends K40 implements Sc0 {
    public C2639sv(AbstractC3067xv abstractC3067xv, AbstractC0551Hu abstractC0551Hu) {
        super(abstractC3067xv, abstractC0551Hu);
    }

    @Override // com.android.tools.r8.internal.Sc0
    public final Comparator comparator() {
        return ((AbstractC3067xv) this.d).e;
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu, com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu
    public final AbstractC0551Hu f(int i, int i2) {
        return new X40(new C0525Gu(this, i, i2 - i), ((AbstractC3067xv) this.d).e).a();
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final int indexOf(Object obj) {
        int iIndexOf = ((AbstractC3067xv) this.d).indexOf(obj);
        if (iIndexOf < 0 || !get(iIndexOf).equals(obj)) {
            return -1;
        }
        return iIndexOf;
    }

    @Override // com.android.tools.r8.internal.K40, com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return (AbstractC3067xv) this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        int size = ((X40) k()).h.size();
        AbstractC0551Hu abstractC0551Hu = this.e;
        Objects.requireNonNull(abstractC0551Hu);
        return AbstractC1165be.a(size, 1301, new tc6(abstractC0551Hu), ((AbstractC3067xv) this.d).e);
    }
}
