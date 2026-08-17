package com.android.tools.r8.internal;

import java.util.ListIterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class K40 extends AbstractC2724tu {
    public final AbstractC3066xu d;
    public final AbstractC0551Hu e;

    public K40(AbstractC3066xu abstractC3066xu, Object[] objArr) {
        AbstractC0551Hu abstractC0551HuB = AbstractC0551Hu.b(objArr.length, objArr);
        this.d = abstractC3066xu;
        this.e = abstractC0551HuB;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        return this.e.a(i, objArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final Object[] b() {
        return this.e.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int c() {
        return this.e.c();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int d() {
        return this.e.d();
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.lang.Iterable
    public final void forEach(Consumer consumer) {
        this.e.forEach(consumer);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.e.get(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu
    /* JADX INFO: renamed from: j */
    public final F listIterator(int i) {
        return this.e.listIterator(i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public AbstractC3066xu k() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    public final ListIterator listIterator(int i) {
        return this.e.listIterator(i);
    }

    public K40(AbstractC3066xu abstractC3066xu, AbstractC0551Hu abstractC0551Hu) {
        this.d = abstractC3066xu;
        this.e = abstractC0551Hu;
    }
}
