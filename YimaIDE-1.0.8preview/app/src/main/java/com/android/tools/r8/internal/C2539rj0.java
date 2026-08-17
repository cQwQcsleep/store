package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2539rj0 {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final C0602Jt b;
    public final HashMap c = new HashMap();

    public C2539rj0(C0333y c0333y, C0602Jt c0602Jt) {
        this.a = c0333y;
        this.b = c0602Jt;
    }

    public static void a(C2543rl0 c2543rl0, Set set) {
        for (AbstractC0890Uw abstractC0890Uw : c2543rl0.b0()) {
            if (abstractC0890Uw.L1()) {
                C2040lu c2040luR = abstractC0890Uw.R();
                if (!c2040luR.P2()) {
                    ArrayList arrayList = c2040luR.c;
                    C2543rl0 c2543rl1 = (C2543rl0) arrayList.get(1 - arrayList.indexOf(c2543rl0));
                    if (!set.contains(c2543rl1) && !d) {
                        int i = AbstractC2554rv.c;
                        C1870jv c1870jv = new C1870jv();
                        Iterator it = set.iterator();
                        while (it.hasNext()) {
                            c1870jv.a(it.next());
                        }
                        a(c2543rl1, c1870jv.a(c2543rl0).a());
                    }
                }
            } else if (abstractC0890Uw.p1()) {
                N3 n3W = abstractC0890Uw.w();
                boolean z = d;
                if (!z && c2543rl0 != n3W.value()) {
                    x1f.a();
                    return;
                }
                if (!z && n3W.i.a()) {
                    x1f.a();
                    return;
                } else if (!z && !n3W.K2().t().N().e()) {
                    x1f.a();
                    return;
                }
            } else if (!d) {
                x1f.a();
                return;
            }
        }
    }

    public static AbstractC2624sj0 a(Gl0 gl0) {
        switch (gl0.ordinal()) {
            case 0:
                return AbstractC2624sj0.f();
            case 1:
                return AbstractC2624sj0.k();
            case 2:
                return AbstractC2624sj0.j();
            case XmlPullParser.END_TAG /* 3 */:
                return AbstractC2624sj0.o();
            case 4:
                return AbstractC2624sj0.p();
            case XmlPullParser.CDSECT /* 5 */:
                return AbstractC2624sj0.l();
            case XmlPullParser.ENTITY_REF /* 6 */:
                return AbstractC2624sj0.i();
            case 7:
                return AbstractC2624sj0.q();
            default:
                defpackage.gk0.a("Unexpected constraint type: ", gl0);
                return null;
        }
    }

    public final void a(C0705Nt c0705Nt, List list, ArrayList arrayList) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((InterfaceC3237zv) it.next()).a(this);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C2543rl0 c2543rl0 = (C2543rl0) it2.next();
            C0602Jt c0602Jt = this.b;
            c2543rl0.a(a(true, c2543rl0), c0602Jt.m, c0602Jt.o.M().i);
            if (!c2543rl0.t().G()) {
                arrayList2.add(c2543rl0);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        this.a.O().a(new StringDiagnostic("Cannot determine precise type for value: " + arrayList2.get(0) + ", its imprecise type is: " + ((C2543rl0) arrayList2.get(0)).t(), c0705Nt.i().getOrigin(), new MethodPosition(c0705Nt.i().A())));
        throw null;
    }

    public final void a(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1, Consumer consumer) {
        Gl0 gl0A;
        if (!d && kn.a()) {
            x1f.a();
            return;
        }
        C2543rl0 c2543rl0A = a(c2543rl0);
        if (c2543rl1.t().r()) {
            gl0A = Gl0.a(c2543rl1.t().a().S());
        } else {
            gl0A = a(true, c2543rl0A);
        }
        C0602Jt c0602Jt = this.b;
        c2543rl0A.a(gl0A, c0602Jt.m, c0602Jt.o.M().i);
        consumer.accept(KN.a(kn, gl0A));
    }

    public final Gl0 a(boolean z, C2543rl0 c2543rl0) {
        Gl0 gl0A = a(a(c2543rl0).t());
        int iOrdinal = gl0A.ordinal();
        if (iOrdinal == 3) {
            if (!d && z) {
                int i = AbstractC2554rv.c;
                a(c2543rl0, W40.j);
            }
            return z ? Gl0.c : gl0A;
        }
        if (iOrdinal == 4) {
            if (d || !z) {
                return Gl0.e;
            }
            x1f.a();
            return null;
        }
        if (iOrdinal != 7) {
            return gl0A;
        }
        if (!d && z) {
            int i2 = AbstractC2554rv.c;
            a(c2543rl0, W40.j);
        }
        return z ? Gl0.g : gl0A;
    }

    public static Gl0 a(AbstractC2624sj0 abstractC2624sj0) {
        abstractC2624sj0.getClass();
        return abstractC2624sj0 instanceof C1720i7 ? Gl0.b : Gl0.a(abstractC2624sj0);
    }

    public final void a(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        if (c2543rl0 == c2543rl1) {
            return;
        }
        AbstractC2624sj0 abstractC2624sj0T = c2543rl0.t();
        AbstractC2624sj0 abstractC2624sj0T2 = c2543rl1.t();
        if (abstractC2624sj0T.G() && abstractC2624sj0T2.G()) {
            if (abstractC2624sj0T == abstractC2624sj0T2 || a(abstractC2624sj0T) == a(abstractC2624sj0T2)) {
                return;
            }
            throw new C0613Ke("Cannot unify types for values " + c2543rl0 + ":" + abstractC2624sj0T + " and " + c2543rl1 + ":" + abstractC2624sj0T2);
        }
        boolean zG = abstractC2624sj0T.G();
        HashMap map = this.c;
        if (zG) {
            map.put(c2543rl1, c2543rl0);
        } else {
            map.put(c2543rl0, c2543rl1);
        }
    }

    public final C2543rl0 a(C2543rl0 c2543rl0) {
        C2543rl0 c2543rl1 = c2543rl0;
        while (c2543rl0 != null) {
            C2543rl0 c2543rl2 = (C2543rl0) this.c.get(c2543rl0);
            if (c2543rl2 != null) {
                this.c.put(c2543rl1, c2543rl2);
            }
            c2543rl1 = c2543rl0;
            c2543rl0 = c2543rl2;
        }
        return c2543rl1;
    }
}
