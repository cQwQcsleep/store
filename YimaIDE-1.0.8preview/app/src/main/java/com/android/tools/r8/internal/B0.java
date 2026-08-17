package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B0 extends AbstractC1314dQ {
    public final /* synthetic */ Map.Entry b;
    public final /* synthetic */ C0 c;

    public B0(C0 c0, Map.Entry entry) {
        this.c = c0;
        this.b = entry;
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final int a() {
        C1254ch c1254ch;
        C1254ch c1254ch2 = (C1254ch) this.b.getValue();
        if ((c1254ch2 == null || c1254ch2.b == 0) && (c1254ch = (C1254ch) this.c.d.d.get(this.b.getKey())) != null) {
            return c1254ch.b;
        }
        if (c1254ch2 == null) {
            return 0;
        }
        return c1254ch2.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1314dQ
    public final Object b() {
        return this.b.getKey();
    }
}
