package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2977wq implements Ua0 {
    public final Ua0 a;
    public final InterfaceC1439er b;
    public final InterfaceC1439er c;

    public C2977wq(Ua0 ua0, InterfaceC1439er interfaceC1439er, InterfaceC1439er interfaceC1439er2) {
        KB.c(ua0, "sequence");
        KB.c(interfaceC1439er, "transformer");
        this.a = ua0;
        this.b = interfaceC1439er;
        this.c = interfaceC1439er2;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        return new C2891vq(this);
    }
}
