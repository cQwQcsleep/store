package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Yh0 implements Ua0 {
    public final Ua0 a;
    public final InterfaceC1439er b;

    public Yh0(Ua0 ua0, InterfaceC1439er interfaceC1439er) {
        this.a = ua0;
        this.b = interfaceC1439er;
    }

    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        return new Xh0(this);
    }
}
