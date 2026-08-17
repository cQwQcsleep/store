package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.internal.C2807us;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class B1 extends D1 {
    public final Origin a;

    public B1(Origin origin) {
        this.a = origin;
    }

    @Override // com.android.tools.r8.shaking.D1
    public final GraphNode a(N0 n0) {
        return new A1(this.a);
    }

    @Override // com.android.tools.r8.shaking.D1
    public final boolean b() {
        return true;
    }

    @Override // com.android.tools.r8.shaking.D1
    public final C2807us.a a() {
        return C2807us.a.s;
    }
}
