package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L40 extends AbstractC2724tu {
    public final /* synthetic */ M40 d;

    public L40(M40 m40) {
        this.d = m40;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Map.Entry entry = this.d.e.f.h[i];
        return new C3236zu(entry.getValue(), entry.getKey());
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return this.d;
    }
}
