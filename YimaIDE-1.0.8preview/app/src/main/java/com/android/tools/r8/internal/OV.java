package com.android.tools.r8.internal;

import com.android.tools.r8.internal.RV;
import defpackage.hkh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OV implements Comparable {
    public static final /* synthetic */ boolean h = true;
    public final List b;
    public final List c;
    public final ArrayList d = new ArrayList();
    public final com.android.tools.r8.graph.I2 e;
    public com.android.tools.r8.graph.E2 f;
    public final /* synthetic */ XV g;

    public OV(XV xv, List list, List list2, List list3, com.android.tools.r8.graph.I2 i2, int i, int i3) {
        RV mv;
        QV qv;
        this.g = xv;
        this.b = list2;
        this.c = list3;
        this.e = i2;
        while (i < i3) {
            AbstractC0890Uw abstractC0890Uw = (AbstractC0890Uw) list.get(i);
            if (abstractC0890Uw.S1() || abstractC0890Uw.o2() || abstractC0890Uw.l1()) {
                ArrayList arrayList = this.d;
                if (abstractC0890Uw.t1()) {
                    AbstractC1547g6 abstractC1547g6Y = abstractC0890Uw.y();
                    if (abstractC1547g6Y.h1()) {
                        qv = QV.b;
                    } else if (abstractC1547g6Y instanceof C1764ig0) {
                        qv = QV.c;
                    } else if (abstractC1547g6Y instanceof C3023xP) {
                        qv = QV.d;
                    } else if (abstractC1547g6Y instanceof C1433em) {
                        qv = QV.e;
                    } else if (abstractC1547g6Y instanceof C1036a50) {
                        qv = QV.f;
                    } else if (abstractC1547g6Y.W1()) {
                        qv = QV.g;
                    } else {
                        if (!abstractC1547g6Y.o2()) {
                            hkh.a();
                            throw null;
                        }
                        qv = QV.h;
                    }
                    mv = new LV(qv, abstractC1547g6Y.K2());
                } else if (abstractC0890Uw.o2()) {
                    mv = new NV(abstractC0890Uw.t0().i);
                } else {
                    if (!RV.b && !abstractC0890Uw.W1()) {
                        x1f.a();
                        throw null;
                    }
                    AbstractC1047aC abstractC1047aCB0 = abstractC0890Uw.b0();
                    El0[] el0Arr = new El0[abstractC1047aCB0.c.size()];
                    Iterator it = abstractC1047aCB0.c.iterator();
                    int i4 = 0;
                    while (it.hasNext()) {
                        el0Arr[i4] = ((C2543rl0) it.next()).Y();
                        i4++;
                    }
                    mv = new MV(abstractC1047aCB0.U2(), abstractC1047aCB0.P2(), abstractC1047aCB0.c() != null, el0Arr, abstractC1047aCB0 instanceof C1301dC ? abstractC1047aCB0.e0().l : null, abstractC1047aCB0.T2());
                }
                arrayList.add(mv);
            } else if (!abstractC0890Uw.w1() && !(abstractC0890Uw instanceof C3165z4) && !h) {
                x01.a("Unexpected type of instruction in outlining template.");
                throw null;
            }
            i++;
        }
    }

    public final boolean a(final AbstractC3148ys abstractC3148ys) {
        for (com.android.tools.r8.graph.I2 i2 : this.b) {
            abstractC3148ys.getClass();
            if (abstractC3148ys.c(AbstractC3148ys.g(), i2) != i2) {
                return true;
            }
        }
        com.android.tools.r8.graph.I2 i3 = this.e;
        abstractC3148ys.getClass();
        if (abstractC3148ys.c(AbstractC3148ys.g(), i3) != this.e) {
            return true;
        }
        return AbstractC3179zC.b(this.d, new EX() { // from class: yma
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((RV) obj).a(abstractC3148ys);
            }
        });
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        OV ov = (OV) obj;
        if (this != ov) {
            if (this.f == null) {
                this.f = this.g.e.a(this.e, (com.android.tools.r8.graph.I2[]) this.b.toArray(com.android.tools.r8.graph.I2.h));
            }
            com.android.tools.r8.graph.E2 e2 = this.f;
            if (ov.f == null) {
                ov.f = ov.g.e.a(ov.e, (com.android.tools.r8.graph.I2[]) ov.b.toArray(com.android.tools.r8.graph.I2.h));
            }
            int iCompareTo = e2.compareTo(ov.f);
            if (iCompareTo != 0) {
                if (h || !equals(ov)) {
                    return iCompareTo;
                }
                x1f.a();
                return 0;
            }
            boolean z = h;
            if (!z && this.b.size() != ov.b.size()) {
                x1f.a();
                return 0;
            }
            ArrayList arrayList = this.d;
            ArrayList arrayList2 = ov.d;
            int size = arrayList.size() - arrayList2.size();
            if (size != 0) {
                if (z || !equals(ov)) {
                    return size;
                }
                x1f.a();
                return 0;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                RV rv = (RV) arrayList.get(i);
                RV rv2 = (RV) arrayList2.get(i);
                int iCompareTo2 = rv.compareTo(rv2);
                if (iCompareTo2 != 0) {
                    if (h || !rv.equals(rv2)) {
                        return iCompareTo2;
                    }
                    x1f.a();
                    return 0;
                }
            }
            int size2 = this.c.size() - ov.c.size();
            if (size2 != 0) {
                if (h || !equals(ov)) {
                    return size2;
                }
                x1f.a();
                return 0;
            }
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                int iIntValue = ((Integer) this.c.get(i2)).intValue() - ((Integer) ov.c.get(i2)).intValue();
                if (iIntValue != 0) {
                    if (h || !equals(ov)) {
                        return iIntValue;
                    }
                    x1f.a();
                    return 0;
                }
            }
            if (!h && !equals(ov)) {
                x1f.a();
            }
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof OV)) {
            return false;
        }
        OV ov = (OV) obj;
        ArrayList arrayList = this.d;
        ArrayList arrayList2 = ov.d;
        if (arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((RV) arrayList.get(i)).equals((RV) arrayList2.get(i))) {
                return false;
            }
        }
        return this.b.equals(ov.b) && this.c.equals(ov.c) && this.e == ov.e;
    }

    public final int hashCode() {
        int size = this.d.size();
        int iHashCode = 0;
        for (int i = 0; i < this.d.size() && i < 5; i++) {
            iHashCode = (iHashCode << 4) + ((RV) this.d.get(i)).hashCode();
            size = (size * 3) + iHashCode;
        }
        return size;
    }

    public final String toString() {
        int size = this.b.size();
        StringBuilder sb = new StringBuilder();
        sb.append(this.e);
        sb.append(" anOutline");
        Wf0.a(sb, this.b, ", ", Wf0.a.b);
        sb.append("\n");
        int i = 0;
        for (RV rv : this.d) {
            sb.append(rv.toString());
            Wf0.b(20, rv.b(), sb);
            if (rv.d()) {
                sb.append("v" + size);
                sb.append(" <- ");
            }
            int i2 = 0;
            while (i2 < rv.e()) {
                sb.append(i2 > 0 ? ", " : XmlPullParser.NO_NAMESPACE);
                sb.append("v");
                int i3 = i + 1;
                int iIntValue = ((Integer) this.c.get(i)).intValue();
                if (iIntValue >= 0) {
                    sb.append(iIntValue);
                } else {
                    sb.append(size);
                }
                i2++;
                i = i3;
            }
            sb.append(rv.a());
            sb.append("\n");
        }
        if (this.e == this.g.e.E1) {
            sb.append("Return-Void");
        } else {
            Wf0.b(20, "Return", sb);
            sb.append("v" + size);
        }
        sb.append("\n");
        sb.append(this.c);
        return sb.toString();
    }

    public final int a() {
        return this.b.size();
    }
}
