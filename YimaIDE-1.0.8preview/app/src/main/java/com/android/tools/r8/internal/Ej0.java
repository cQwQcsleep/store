package com.android.tools.r8.internal;

import defpackage.pv9;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ej0 {
    public final List a;

    public Ej0(G00 g00) {
        KB.c(g00, "typeTable");
        List list = g00.d;
        if ((g00.c & 1) == 1) {
            int i = g00.e;
            KB.b(list, "getTypeList(...)");
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) list));
            int i2 = 0;
            for (Object obj : list) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    pv9.a("Index overflow has happened.");
                    throw null;
                }
                C2903w00 c2903w00C = (C2903w00) obj;
                if (i2 >= i) {
                    c2903w00C.getClass();
                    C2817v00 c2817v00A = C2903w00.a(c2903w00C);
                    c2817v00A.e |= 2;
                    c2817v00A.g = true;
                    c2903w00C = c2817v00A.c();
                }
                arrayList.add(c2903w00C);
                i2 = i3;
            }
            list = arrayList;
        }
        KB.b(list, "run(...)");
        this.a = list;
    }
}
