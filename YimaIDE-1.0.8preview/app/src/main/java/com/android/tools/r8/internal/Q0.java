package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Q0 extends AbstractC2608sb0 {
    public final /* synthetic */ R0 b;

    public Q0(R0 r0) {
        this.b = r0;
    }

    public InterfaceC1231cQ a() {
        return this.b;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        a().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof AbstractC1314dQ) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
            if (abstractC1314dQ.a() > 0 && a().b(abstractC1314dQ.b()) == abstractC1314dQ.a()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return this.b.e();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (!(obj instanceof AbstractC1314dQ)) {
            return false;
        }
        AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
        Object objB = abstractC1314dQ.b();
        int iA = abstractC1314dQ.a();
        if (iA != 0) {
            return a().a(iA, objB);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.c();
    }
}
