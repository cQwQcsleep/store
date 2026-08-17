package com.android.tools.r8.graph;

import com.android.tools.r8.naming.AbstractC3345r0;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.o5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0270o5 extends com.android.tools.r8.utils.structural.g {
    public final /* synthetic */ C0284q5 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0270o5(AbstractC3345r0 abstractC3345r0, ToIntFunction toIntFunction, ToIntFunction toIntFunction2, C0284q5 c0284q5) {
        super(abstractC3345r0, toIntFunction, toIntFunction2);
        this.e = c0284q5;
    }

    @Override // com.android.tools.r8.utils.structural.d, com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(C0245l1 c0245l1, C0245l1 c0245l2) {
        return Integer.compare(this.e.i.b(c0245l1), this.e.i.b(c0245l2));
    }

    @Override // com.android.tools.r8.utils.structural.d, com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(C0322w2 c0322w2, C0322w2 c0322w3) {
        return Integer.compare(this.e.h.b(c0322w2), this.e.h.b(c0322w3));
    }
}
