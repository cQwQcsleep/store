package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1013Zp implements Ua0 {
    public final Ua0 a;
    public final boolean b;
    public final InterfaceC1439er c;

    public C1013Zp(Ua0 ua0, boolean z, InterfaceC1439er interfaceC1439er) {
        this.a = ua0;
        this.b = z;
        this.c = interfaceC1439er;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        return new C0987Yp(this);
    }
}
