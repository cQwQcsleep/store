package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2905w10 extends YI implements InterfaceC1439er {
    public final /* synthetic */ H50 c;
    public final /* synthetic */ C2991x10 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2905w10(H50 h50, C2991x10 c2991x10) {
        super(1);
        this.c = h50;
        this.d = c2991x10;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        C2802un c2802un = (C2802un) obj;
        KB.c(c2802un, "<name for destructuring parameter 0>");
        int i = c2802un.a;
        E60 e60 = c2802un.d;
        W50 w50A = this.c.b.a(i);
        if (w50A == null) {
            return null;
        }
        C2991x10 c2991x10 = this.d;
        return new C2569s40(c2991x10.a, this.c, e60, w50A);
    }
}
