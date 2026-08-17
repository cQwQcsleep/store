package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.AbstractC2544rm;
import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.G9;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2544rm {
    public static /* synthetic */ AbstractC3175z9 a(G9 g9, com.android.tools.r8.graph.B1 b1) {
        return new G9(182, b1.Q4.a, false);
    }

    public static InterfaceC1971l5 a() {
        return new InterfaceC1971l5() { // from class: n8i
            @Override // com.android.tools.r8.internal.InterfaceC1971l5
            public final AbstractC3175z9 a(G9 g9, B1 b1) {
                return AbstractC2544rm.a(g9, b1);
            }
        };
    }
}
