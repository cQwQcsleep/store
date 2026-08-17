package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1529fv extends AbstractC3066xu implements InterfaceC1231cQ {
    public static final /* synthetic */ int e = 0;
    public transient AbstractC0551Hu c;
    public transient AbstractC1955kv d;

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        Ck0 it = entrySet().iterator();
        while (it.hasNext()) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) it.next();
            Arrays.fill(objArr, i, abstractC1314dQ.a() + i, abstractC1314dQ.b());
            i += abstractC1314dQ.a();
        }
        return i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return b(obj) > 0;
    }

    @Override // java.util.Collection, com.android.tools.r8.internal.InterfaceC1231cQ
    public final boolean equals(Object obj) {
        return AbstractC1656hQ.a(this, obj);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public abstract AbstractC2554rv F();

    @Override // java.util.Collection, com.android.tools.r8.internal.InterfaceC1231cQ
    public int hashCode() {
        return AbstractC2780ub0.a((Set) entrySet());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final AbstractC2554rv entrySet() {
        AbstractC1955kv c1443ev = this.d;
        if (c1443ev == null) {
            c1443ev = isEmpty() ? W40.j : new C1443ev(this);
            this.d = c1443ev;
        }
        return c1443ev;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return new C1191bv(entrySet().iterator());
    }

    public abstract AbstractC1314dQ j(int i);

    @Override // java.util.AbstractCollection
    public final String toString() {
        return entrySet().toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new C1191bv(entrySet().iterator());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int a(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final boolean a(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final AbstractC0551Hu a() {
        AbstractC0551Hu abstractC0551Hu = this.c;
        if (abstractC0551Hu != null) {
            return abstractC0551Hu;
        }
        AbstractC0551Hu abstractC0551HuA = super.a();
        this.c = abstractC0551HuA;
        return abstractC0551HuA;
    }
}
