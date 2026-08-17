package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2848vM extends C2382pt {
    public final Function k;

    public C2848vM(com.android.tools.r8.graph.B1 b1, Function function) {
        super(b1);
        this.k = function;
    }

    @Override // com.android.tools.r8.internal.G0
    public final com.android.tools.r8.graph.I2 a(String str) {
        return (com.android.tools.r8.graph.I2) this.k.apply(str);
    }
}
