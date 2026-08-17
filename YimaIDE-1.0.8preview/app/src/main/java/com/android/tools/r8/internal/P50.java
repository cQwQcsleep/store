package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P50 extends YI implements InterfaceC1439er {
    public final /* synthetic */ R50 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P50(R50 r50) {
        super(1);
        this.c = r50;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        String str = (String) obj;
        KB.c(str, "it");
        return R50.a(this.c, str);
    }
}
