package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0168a1;
import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0542Hl extends C0168a1 {
    public final /* synthetic */ C1975l7 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0542Hl(int i, C0322w2 c0322w2, boolean z, C1975l7 c1975l7) {
        super(i, c0322w2, z);
        this.g = c1975l7;
    }

    @Override // com.android.tools.r8.graph.C0168a1, com.android.tools.r8.graph.V0
    public final void a(com.android.tools.r8.graph.S0 s0) {
        super.a(s0);
        this.g.a(s0.d);
    }
}
