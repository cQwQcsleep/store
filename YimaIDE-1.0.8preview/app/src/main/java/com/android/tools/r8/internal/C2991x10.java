package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2991x10 {
    public final InterfaceC2827v50 a;
    public final InterfaceC1439er b;

    public C2991x10(InterfaceC2827v50 interfaceC2827v50, InterfaceC1439er interfaceC1439er) {
        KB.c(interfaceC2827v50, "resourceRoot");
        KB.c(interfaceC1439er, "resourceTableProducer");
        this.a = interfaceC2827v50;
        this.b = interfaceC1439er;
    }

    public final void a(H50 h50) {
        KB.c(h50, "model");
        M70 m70 = (M70) this.b.b(h50);
        KB.c(m70, "<this>");
        C0987Yp c0987Yp = new C0987Yp(new C1013Zp(new Yh0(new Xa0(new S50(m70, null)), new C2905w10(h50, this)), false, C1327db0.c));
        while (c0987Yp.hasNext()) {
            C2569s40 c2569s40 = (C2569s40) c0987Yp.next();
            List list = c2569s40.c.j;
            KB.b(list, "entry.configValueList");
            C2891vq c2891vq = new C2891vq(new C2977wq(new Yh0(new C2443qe(list), C1544g40.c), C1884k40.c, C1412eb0.j));
            while (c2891vq.a()) {
                R60 r60 = (R60) c2891vq.next();
                KB.b(r60, "it");
                c2569s40.a(r60);
            }
        }
    }
}
