package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.internal.C2807us;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.w1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3474w1 extends D1 {
    public final com.android.tools.r8.graph.I2 a;

    public C3474w1(com.android.tools.r8.graph.I2 i2) {
        this.a = i2;
    }

    @Override // com.android.tools.r8.shaking.D1
    public final GraphNode a(N0 n0) {
        return n0.a(this.a);
    }

    @Override // com.android.tools.r8.shaking.D1
    public final C2807us.a a() {
        return C2807us.a.n;
    }
}
