package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2217o extends YI implements InterfaceC1439er {
    public final /* synthetic */ AbstractC1620h0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2217o(AbstractC1620h0 abstractC1620h0) {
        super(1);
        this.c = abstractC1620h0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        return obj == this.c ? "(this Collection)" : String.valueOf(obj);
    }
}
