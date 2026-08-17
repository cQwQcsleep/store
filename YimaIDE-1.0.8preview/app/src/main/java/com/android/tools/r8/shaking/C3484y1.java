package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.C2807us;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.y1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3484y1 extends D1 {
    public final C0285r0 a;
    public final InterfaceC0332x5 b;

    public C3484y1(C0285r0 c0285r0, InterfaceC0332x5 interfaceC0332x5) {
        this.a = c0285r0;
        this.b = interfaceC0332x5;
    }

    @Override // com.android.tools.r8.shaking.D1
    public final GraphNode a(N0 n0) {
        return n0.a(this.a, this.b);
    }

    @Override // com.android.tools.r8.shaking.D1
    public final C2807us.a a() {
        return C2807us.a.o;
    }
}
