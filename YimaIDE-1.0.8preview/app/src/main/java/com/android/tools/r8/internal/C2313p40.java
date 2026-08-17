package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2313p40 extends YI implements InterfaceC1439er {
    public final /* synthetic */ C2569s40 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2313p40(C2569s40 c2569s40) {
        super(1);
        this.c = c2569s40;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        C1491fW c1491fW = (C1491fW) obj;
        KB.c(c1491fW, "<name for destructuring parameter 0>");
        U50 u50 = (U50) c1491fW.b;
        String str = (String) c1491fW.c;
        R50 r50 = this.c.b.b;
        KB.a(u50);
        return new C2443qe(r50.a(u50, str));
    }
}
