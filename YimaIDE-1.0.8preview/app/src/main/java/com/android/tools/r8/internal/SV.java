package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SV extends WV {
    public final List p;

    public SV(XV xv, com.android.tools.r8.graph.B5 b5, List list, List list2) {
        super(xv, b5, list);
        this.p = list2;
    }

    @Override // com.android.tools.r8.internal.WV
    public final void a(int i, int i2, OV ov) {
        this.p.add(ov);
    }
}
