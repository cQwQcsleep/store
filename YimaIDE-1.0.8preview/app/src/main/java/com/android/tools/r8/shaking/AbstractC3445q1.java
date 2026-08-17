package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.graph.C0231j1;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3445q1 extends D1 {
    public final C0231j1 a;

    public AbstractC3445q1(C0231j1 c0231j1) {
        this.a = c0231j1;
    }

    @Override // com.android.tools.r8.shaking.D1
    public final GraphNode a(N0 n0) {
        return n0.a(this.a.getReference());
    }
}
