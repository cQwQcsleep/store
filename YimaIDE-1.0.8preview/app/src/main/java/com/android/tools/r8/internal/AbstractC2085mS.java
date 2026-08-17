package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2085mS;
import com.android.tools.r8.internal.XR;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2085mS {
    public static AbstractC2085mS a(C0333y c0333y, C0705Nt c0705Nt, XR xr, final AbstractC3148ys abstractC3148ys) {
        XR xrA = xr.a(new Predicate() { // from class: ulh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbstractC2085mS.a(abstractC3148ys, (XR) obj);
            }
        });
        return (xrA == null || xrA == abstractC3148ys || !(xrA instanceof C2224o3)) ? new C1913kS() : new C1999lS(c0333y.V(), c0705Nt, xr);
    }

    public abstract void a();

    public abstract void a(AbstractC1047aC abstractC1047aC, AbstractC1047aC abstractC1047aC2, C2850vO c2850vO);

    public static boolean a(AbstractC3148ys abstractC3148ys, XR xr) {
        xr.getClass();
        return (xr instanceof C2224o3) || xr == abstractC3148ys;
    }
}
