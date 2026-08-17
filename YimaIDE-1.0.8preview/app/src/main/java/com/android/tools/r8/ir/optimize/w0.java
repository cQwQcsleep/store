package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.AbstractC1597gi0;
import com.android.tools.r8.internal.AbstractC2536ri;
import com.android.tools.r8.internal.C1131bA;
import com.android.tools.r8.internal.C1341di0;
import com.android.tools.r8.internal.C1512fi0;
import com.android.tools.r8.internal.C2366pi;
import com.android.tools.r8.internal.H5;
import com.android.tools.r8.internal.P40;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class w0 extends AbstractC2536ri {
    public static final /* synthetic */ boolean i = true;
    public final /* synthetic */ C1131bA f;
    public final /* synthetic */ x0 g;
    public final /* synthetic */ C0 h;

    public w0(C1131bA c1131bA, x0 x0Var, C0 c0) {
        this.f = c1131bA;
        this.g = x0Var;
        this.h = c0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2536ri
    public final C1512fi0 a(C2366pi c2366pi, List list) {
        A0 a0 = (A0) c2366pi.d;
        if (!z0.a(a0.a)) {
            boolean z = i;
            if (!z) {
                int i2 = a0.a;
                if (i2 == 0) {
                    throw null;
                }
                if (i2 != 2 && i2 != 3) {
                    x1f.a();
                    return null;
                }
            }
            if (!z && !list.isEmpty()) {
                x1f.a();
                return null;
            }
        } else {
            if (!A0.c && !z0.a(a0.a)) {
                x1f.a();
                return null;
            }
            int i3 = list.isEmpty() ? 3 : 4;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                A0 a1 = (A0) ((C2366pi) it.next()).d;
                if (!A0.c && z0.a(a1.a)) {
                    x1f.a();
                    return null;
                }
                int i4 = a1.a;
                if (!z0.b(i3) && !z0.a(i4)) {
                    if (z0.a(i3) || z0.b(i4)) {
                        i3 = i4;
                    } else if (i3 != i4) {
                        i3 = 1;
                    }
                }
            }
            if (!A0.c && z0.a(i3)) {
                x1f.a();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (z0.b(i3)) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    C2366pi c2366pi2 = (C2366pi) it2.next();
                    int i5 = ((A0) c2366pi2.d).a;
                    if (i5 == 3) {
                        arrayList.add((H5) c2366pi2.a);
                    } else if (z0.b(i5)) {
                        arrayList.addAll(((A0) c2366pi2.d).b);
                    }
                }
            }
            a0 = new A0(i3, arrayList);
        }
        c2366pi.d = a0;
        return new C1512fi0(a0);
    }

    @Override // com.android.tools.r8.internal.AbstractC2536ri
    public final AbstractC1597gi0 a(C2366pi c2366pi, Function function) {
        int i2;
        Iterator<AbstractC0890Uw> it = ((H5) c2366pi.a).k().iterator();
        int i3 = 1;
        while (true) {
            i2 = 2;
            if (!it.hasNext()) {
                break;
            }
            AbstractC0890Uw next = it.next();
            if (this.f.b() > this.g.a()) {
                return C1341di0.c;
            }
            int iA = this.g.a(next);
            if (iA != 1) {
                if (iA == 2) {
                    this.h.a.add(next);
                }
                i3 = iA;
                break;
            }
            i3 = iA;
        }
        if (i3 == 1) {
            Iterator it2 = this.g.a((H5) c2366pi.a).iterator();
            while (it2.hasNext()) {
                if (((C2366pi) function.apply((H5) it2.next())).d != null) {
                    return C1341di0.c;
                }
            }
        }
        int iB = AbstractC0007c.b(i3);
        if (iB == 0) {
            i2 = 4;
        } else if (iB != 1) {
            i2 = 3;
            if (!y0.a && i3 != 3) {
                x1f.a();
                return null;
            }
        }
        int i4 = AbstractC0551Hu.c;
        c2366pi.d = new A0(i2, P40.e);
        return C1512fi0.c;
    }
}
