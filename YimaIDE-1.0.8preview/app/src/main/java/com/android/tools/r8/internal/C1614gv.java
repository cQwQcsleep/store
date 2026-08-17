package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1614gv {
    public final ArrayList a = new ArrayList();

    public final C1700hv a() {
        K10 k10;
        Object[] objArrCopyOf = new Object[this.a.size()];
        ArrayList arrayList = this.a;
        K10 k11 = K10.d;
        Collections.sort(arrayList, J10.b);
        Iterator it = this.a.iterator();
        MC mc = it instanceof MC ? (MC) it : new MC(it);
        int i = 0;
        while (mc.hasNext()) {
            K10 k12 = (K10) mc.next();
            while (mc.hasNext()) {
                if (!mc.c) {
                    mc.d = mc.b.next();
                    mc.c = true;
                }
                K10 k13 = (K10) mc.d;
                if (k12.b.compareTo(k13.c) > 0 || k13.b.compareTo(k12.c) > 0) {
                    break;
                }
                int iCompareTo = k12.b.compareTo(k13.b);
                int iCompareTo2 = k12.c.compareTo(k13.c);
                if (iCompareTo >= 0 && iCompareTo2 <= 0) {
                    k10 = k12;
                } else if (iCompareTo > 0 || iCompareTo2 < 0) {
                    AbstractC2106mh abstractC2106mh = iCompareTo >= 0 ? k12.b : k13.b;
                    AbstractC2106mh abstractC2106mh2 = iCompareTo2 <= 0 ? k12.c : k13.c;
                    if (!(abstractC2106mh.compareTo(abstractC2106mh2) <= 0)) {
                        w01.a(Xf0.a("intersection is undefined for disconnected ranges %s and %s", new Object[]{k12, k13}));
                        return null;
                    }
                    k10 = new K10(abstractC2106mh, abstractC2106mh2);
                } else {
                    k10 = k13;
                }
                if (!k10.b.equals(k10.c)) {
                    w01.a(Xf0.a("Overlapping ranges not permitted but found %s overlapping %s", new Object[]{k12, k13}));
                    return null;
                }
                K10 k14 = (K10) mc.next();
                int iCompareTo3 = k12.b.compareTo(k14.b);
                int iCompareTo4 = k12.c.compareTo(k14.c);
                if (iCompareTo3 > 0 || iCompareTo4 < 0) {
                    if (iCompareTo3 < 0 || iCompareTo4 > 0) {
                        AbstractC2106mh abstractC2106mh3 = iCompareTo3 <= 0 ? k12.b : k14.b;
                        if (iCompareTo4 < 0) {
                            k12 = k14;
                        }
                        k14 = new K10(abstractC2106mh3, k12.c);
                    }
                    k12 = k14;
                }
            }
            k12.getClass();
            int i2 = i + 1;
            if (objArrCopyOf.length < i2) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, AbstractC2981wu.a(objArrCopyOf.length, i2));
            }
            objArrCopyOf[i] = k12;
            i = i2;
        }
        AbstractC0551Hu abstractC0551HuB = AbstractC0551Hu.b(i, objArrCopyOf);
        if (abstractC0551HuB.isEmpty()) {
            return C1700hv.c;
        }
        return (abstractC0551HuB.size() == 1 && ((K10) AbstractC3179zC.a(abstractC0551HuB)).equals(K10.d)) ? C1700hv.d : new C1700hv(abstractC0551HuB);
    }

    public final C1614gv a(C1614gv c1614gv) {
        Iterator it = c1614gv.a.iterator();
        while (it.hasNext()) {
            a((K10) it.next());
        }
        return this;
    }

    public final C1614gv a(K10 k10) {
        DX.a(!k10.b.equals(k10.c), "range must not be empty, but was %s", k10);
        this.a.add(k10);
        return this;
    }
}
