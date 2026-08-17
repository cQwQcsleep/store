package com.android.tools.r8.ir.regalloc;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC2624sj0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class g implements Comparable<g> {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC2624sj0 b;
    public final int c;
    public final int d;
    public final AbstractC0890Uw e;

    public g(int i, AbstractC2624sj0 abstractC2624sj0, AbstractC0890Uw abstractC0890Uw) {
        if (!f && !abstractC0890Uw.r2()) {
            x1f.a();
            throw null;
        }
        this.c = i;
        this.d = Integer.MIN_VALUE;
        this.e = abstractC0890Uw;
        this.b = abstractC2624sj0;
    }

    public final boolean a(TreeSet treeSet, HashMap map) {
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            int i = gVar.d;
            if (i != Integer.MIN_VALUE && gVar != this) {
                int iIntValue = ((Integer) map.get(Integer.valueOf(i))).intValue();
                if ((this.b.M() && this.c + 1 == iIntValue) || this.c == iIntValue) {
                    return true;
                }
                if (gVar.b.M()) {
                    int iIntValue2 = ((Integer) map.get(Integer.valueOf(gVar.d))).intValue() + 1;
                    if ((this.b.M() && this.c + 1 == iIntValue2) || this.c == iIntValue2) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    public final int compareTo(g gVar) {
        g gVar2 = gVar;
        int i = this.d - gVar2.d;
        if (i != 0) {
            return i;
        }
        int i2 = this.c - gVar2.c;
        if (i2 != 0) {
            return i2;
        }
        boolean zH = this.b.H();
        boolean zH2 = gVar2.b.H();
        AbstractC2624sj0 abstractC2624sj0 = this.b;
        if (zH != zH2) {
            return Boolean.compare(abstractC2624sj0.H(), gVar2.b.H());
        }
        boolean zM = abstractC2624sj0.M();
        boolean zM2 = gVar2.b.M();
        AbstractC2624sj0 abstractC2624sj1 = this.b;
        if (zM != zM2) {
            return Boolean.compare(abstractC2624sj1.M(), gVar2.b.M());
        }
        if (abstractC2624sj1.I() != gVar2.b.I()) {
            return Boolean.compare(this.b.I(), gVar2.b.I());
        }
        AbstractC0890Uw abstractC0890Uw = this.e;
        AbstractC0890Uw abstractC0890Uw2 = gVar2.e;
        if (abstractC0890Uw == null) {
            return abstractC0890Uw2 != null ? -1 : 0;
        }
        if (abstractC0890Uw2 == null) {
            return 1;
        }
        return abstractC0890Uw.e - abstractC0890Uw2.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return gVar.d == this.d && gVar.c == this.c && gVar.b == this.b && gVar.e == this.e;
    }

    public final int hashCode() {
        int iHashCode = (this.b.hashCode() * 5) + (this.c * 3) + this.d;
        AbstractC0890Uw abstractC0890Uw = this.e;
        return iHashCode + (abstractC0890Uw == null ? 0 : abstractC0890Uw.hashCode());
    }

    public g(int i, int i2, AbstractC2624sj0 abstractC2624sj0) {
        this.c = i;
        this.d = i2;
        this.e = null;
        this.b = abstractC2624sj0;
    }
}
