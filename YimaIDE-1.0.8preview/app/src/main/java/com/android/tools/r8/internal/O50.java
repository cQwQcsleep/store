package com.android.tools.r8.internal;

import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O50 extends YI implements InterfaceC1439er {
    public final /* synthetic */ R50 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O50(R50 r50) {
        super(1);
        this.c = r50;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        W50 w50 = (W50) obj;
        KB.c(w50, "it");
        w50.a(true);
        HashSet hashSet = this.c.f;
        U50 u50 = w50.c;
        KB.b(u50, "it.type");
        String str = w50.d;
        KB.b(str, "it.name");
        hashSet.add(new F50(u50, str, w50.e));
        return C2028lk0.a;
    }
}
