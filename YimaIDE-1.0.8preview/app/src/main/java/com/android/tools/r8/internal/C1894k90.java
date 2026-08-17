package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1894k90 extends YI implements InterfaceC1439er {
    public static final C1894k90 c = new C1894k90();

    public C1894k90() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        W50 w50 = (W50) obj;
        KB.c(w50, "it");
        U50 u50 = w50.c;
        return Boolean.valueOf((u50 == U50.ATTR || u50 == U50.z) ? false : true);
    }
}
