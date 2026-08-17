package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZP extends AbstractC1314dQ {
    public final /* synthetic */ Map.Entry b;

    public ZP(Map.Entry entry) {
        this.b = entry;
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final int a() {
        return ((Collection) this.b.getValue()).size();
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final Object b() {
        return this.b.getKey();
    }
}
