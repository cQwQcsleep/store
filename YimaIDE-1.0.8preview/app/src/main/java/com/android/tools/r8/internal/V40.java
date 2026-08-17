package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class V40 extends AbstractC1529fv {
    public static final C1485fQ[] k = new C1485fQ[0];
    public static final AbstractC1529fv l = a((Collection) P40.e);
    public final transient C1485fQ[] f;
    public final transient C1485fQ[] g;
    public final transient int h;
    public final transient int i;
    public transient AbstractC2554rv j;

    public V40(C1485fQ[] c1485fQArr, C1485fQ[] c1485fQArr2, int i, int i2, AbstractC2554rv abstractC2554rv) {
        this.f = c1485fQArr;
        this.g = c1485fQArr2;
        this.h = i;
        this.i = i2;
        this.j = abstractC2554rv;
    }

    public static AbstractC1529fv a(Collection collection) {
        int size = collection.size();
        C1485fQ[] c1485fQArr = new C1485fQ[size];
        if (size == 0) {
            return new V40(c1485fQArr, k, 0, 0, W40.j);
        }
        int iA = AbstractC1189bt.a(size, 1.0d);
        int i = iA - 1;
        C1485fQ[] c1485fQArr2 = new C1485fQ[iA];
        Iterator it = collection.iterator();
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        while (it.hasNext()) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) it.next();
            Object objB = abstractC1314dQ.b();
            objB.getClass();
            int iA2 = abstractC1314dQ.a();
            int iHashCode = objB.hashCode();
            int iA3 = AbstractC1189bt.a(iHashCode) & i;
            C1485fQ c1485fQ = c1485fQArr2[iA3];
            C1485fQ c1485fQ2 = c1485fQ == null ? (!(abstractC1314dQ instanceof C1485fQ) || (abstractC1314dQ instanceof U40)) ? new C1485fQ(iA2, objB) : (C1485fQ) abstractC1314dQ : new U40(objB, iA2, c1485fQ);
            i2 += iHashCode ^ iA2;
            c1485fQArr[i3] = c1485fQ2;
            c1485fQArr2[iA3] = c1485fQ2;
            j += (long) iA2;
            i3++;
        }
        for (int i4 = 0; i4 < iA; i4++) {
            int i5 = 0;
            for (C1485fQ c1485fQC = c1485fQArr2[i4]; c1485fQC != null; c1485fQC = c1485fQC.c()) {
                i5++;
                if (i5 > 9) {
                    AbstractC1314dQ[] abstractC1314dQArr = (AbstractC1314dQ[]) AbstractC0551Hu.b(size, c1485fQArr).toArray(new AbstractC1314dQ[0]);
                    HashMap map = new HashMap(AbstractC1739iN.a(abstractC1314dQArr.length));
                    long j2 = 0;
                    for (int i6 = 0; i6 < abstractC1314dQArr.length; i6++) {
                        AbstractC1314dQ abstractC1314dQ2 = abstractC1314dQArr[i6];
                        int iA4 = abstractC1314dQ2.a();
                        j2 += (long) iA4;
                        Object objB2 = abstractC1314dQ2.b();
                        objB2.getClass();
                        map.put(objB2, Integer.valueOf(iA4));
                        if (!(abstractC1314dQ2 instanceof C1485fQ)) {
                            abstractC1314dQArr[i6] = new C1485fQ(iA4, objB2);
                        }
                    }
                    return new YC(map, AbstractC0551Hu.b(abstractC1314dQArr.length, abstractC1314dQArr), j2);
                }
            }
        }
        return new V40(c1485fQArr, c1485fQArr2, MB.a(j), i2, null);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        C1485fQ[] c1485fQArr = this.g;
        if (obj != null && c1485fQArr.length != 0) {
            for (C1485fQ c1485fQC = c1485fQArr[AbstractC1189bt.a(obj) & (c1485fQArr.length - 1)]; c1485fQC != null; c1485fQC = c1485fQC.c()) {
                if (WU.a(obj, c1485fQC.b)) {
                    return c1485fQC.c;
                }
            }
        }
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    /* JADX INFO: renamed from: g */
    public final AbstractC2554rv F() {
        AbstractC2554rv abstractC2554rv = this.j;
        if (abstractC2554rv != null) {
            return abstractC2554rv;
        }
        C1357dv c1357dv = new C1357dv(Arrays.asList(this.f), this);
        this.j = c1357dv;
        return c1357dv;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv, java.util.Collection, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int hashCode() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC1529fv
    public final AbstractC1314dQ j(int i) {
        return this.f[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.h;
    }
}
