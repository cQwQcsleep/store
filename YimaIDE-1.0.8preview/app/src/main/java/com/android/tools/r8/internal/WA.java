package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.WA;
import com.android.tools.r8.internal.XR;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class WA {
    public static WA a(C0333y c0333y, C0705Nt c0705Nt, XR xr, final AbstractC3148ys abstractC3148ys) {
        XR xrA = xr.a(new Predicate() { // from class: eff
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WA.a(abstractC3148ys, (XR) obj);
            }
        });
        return (xrA == null || xrA == abstractC3148ys || !(xrA instanceof Vl0)) ? new C0932Wm() : new YA(c0333y, c0705Nt, xr, abstractC3148ys);
    }

    public abstract void a();

    public abstract void a(AbstractC1047aC abstractC1047aC, AbstractC1047aC abstractC1047aC2, C2850vO c2850vO, H5 h5);

    public abstract void a(C1837ja0 c1837ja0, H5 h5);

    public abstract void a(InterfaceC3145yp interfaceC3145yp, InterfaceC3145yp interfaceC3145yp2, H5 h5);

    public static boolean a(AbstractC3148ys abstractC3148ys, XR xr) {
        xr.getClass();
        return (xr instanceof Vl0) || xr == abstractC3148ys;
    }
}
