package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ru, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0810Ru extends AbstractC0836Su {
    public final transient AbstractC0706Nu e;
    public final transient AbstractC0551Hu f;

    public C0810Ru(AbstractC0706Nu abstractC0706Nu, Map.Entry[] entryArr) {
        AbstractC0551Hu abstractC0551HuB = AbstractC0551Hu.b(entryArr.length, entryArr);
        this.e = abstractC0706Nu;
        this.f = abstractC0551HuB;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        return this.f.a(i, objArr);
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        this.f.forEach(consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return this.f.iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return new K40(this, this.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su
    public final AbstractC0706Nu m() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return this.f.spliterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return this.f.iterator();
    }

    public C0810Ru(AbstractC0706Nu abstractC0706Nu, AbstractC0551Hu abstractC0551Hu) {
        this.e = abstractC0706Nu;
        this.f = abstractC0551Hu;
    }
}
