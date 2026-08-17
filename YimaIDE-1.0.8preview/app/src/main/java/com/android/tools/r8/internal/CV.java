package com.android.tools.r8.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CV extends DV {
    public final /* synthetic */ Map c;

    public CV(HashMap map) {
        this.c = map;
    }

    @Override // com.android.tools.r8.internal.DV
    public final com.android.tools.r8.graph.H2 a(com.android.tools.r8.graph.D2 d2) {
        return (com.android.tools.r8.graph.H2) this.c.get(d2.getType());
    }
}
