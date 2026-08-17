package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2263oZ {
    public static final void a(C1382e90 c1382e90, H50 h50) {
        KB.c(c1382e90, "node");
        KB.c(h50, "model");
        if (c1382e90.e == 1) {
            List list = c1382e90.k().h;
            KB.b(list, "node.element.attributeList");
            C2891vq c2891vq = new C2891vq(new C2977wq(new Yh0(new C1013Zp(new Yh0(new C1013Zp(new C2443qe(list), true, C1835jZ.c), C1920kZ.c), true, C2006lZ.c), C2091mZ.c), new C2177nZ(h50), C1412eb0.j));
            while (c2891vq.a()) {
                Y50.a((W50) c2891vq.next());
            }
            List<C1382e90> list2 = c1382e90.k().i;
            KB.b(list2, "node.element.childList");
            for (C1382e90 c1382e91 : list2) {
                KB.b(c1382e91, "it");
                a(c1382e91, h50);
            }
        }
    }
}
