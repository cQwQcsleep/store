package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2802un;
import java.util.HashMap;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H10 extends H50 {
    public final HashMap g;

    public H10() {
        C1062aR c1062aR = C1062aR.a;
        this.g = new HashMap();
    }

    public final void a(boolean z, C2802un c2802un) {
        String strC;
        U50 u50 = (U50) U50.I.get(c2802un.c);
        E60 e60 = c2802un.d;
        int i = c2802un.a;
        if (!e60.q() && e60.j.size() == 1 && u50 == U50.STRING) {
            C3085y60 c3085y60 = (C3085y60) e60.j.get(0);
            if (c3085y60.m()) {
                O80 o80L = c3085y60.l();
                if (o80L.e == 4) {
                    R60 r60K = o80L.k();
                    if (r60K.e == 2) {
                        HashMap map = this.g;
                        Integer numValueOf = Integer.valueOf(i);
                        Y70 y70 = r60K.e == 2 ? (Y70) r60K.f : Y70.g;
                        Object obj = y70.e;
                        if (obj instanceof String) {
                            strC = (String) obj;
                        } else {
                            strC = ((U7) obj).c();
                            y70.e = strC;
                        }
                        map.put(numValueOf, strC);
                    }
                }
            }
        }
        if (u50 != U50.z || z) {
            this.b.a(new W50(c2802un.b, u50, AbstractC2066m90.a(e60.m()), i));
        }
    }

    public final void a(M70 m70, final boolean z) {
        KB.c(m70, "<this>");
        S50 s50 = new S50(m70, null);
        Va0 va0 = new Va0();
        va0.d = s50.a(va0, va0);
        va0.forEachRemaining(new Consumer() { // from class: d26
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(z, (C2802un) obj);
            }
        });
    }
}
