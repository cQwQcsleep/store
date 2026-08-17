package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Wa0 extends Za0 {
    public static List a(Ua0 ua0) {
        Iterator it = ua0.iterator();
        if (!it.hasNext()) {
            return C0984Ym.b;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            List listSingletonList = Collections.singletonList(next);
            KB.b(listSingletonList, "singletonList(...)");
            return listSingletonList;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static C1013Zp a(Ua0 ua0, InterfaceC1439er interfaceC1439er) {
        return new C1013Zp(ua0, true, interfaceC1439er);
    }

    public static C1013Zp a(C2443qe c2443qe) {
        return new C1013Zp(c2443qe, false, C1327db0.c);
    }

    public static Ua0 a(Object... objArr) {
        if (objArr.length != 0 && objArr.length != 0) {
            return new W3(objArr);
        }
        return C1183bn.a;
    }
}
