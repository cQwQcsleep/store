package com.android.tools.r8.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UO extends SO {
    public UO(HashMap map) {
        super(map);
    }

    public static UO a() {
        return new UO(new HashMap());
    }

    @Override // com.android.tools.r8.internal.SO
    public final com.android.tools.r8.graph.B2 b(Object obj) {
        return (com.android.tools.r8.graph.B2) obj;
    }

    @Override // com.android.tools.r8.internal.SO
    public final Object a(com.android.tools.r8.graph.B5 b5) {
        return b5.C();
    }
}
