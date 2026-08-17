package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2916w7 extends AbstractC1511fi {
    public final Y5 f;

    public C2916w7(C0333y c0333y, Z5 z5) {
        super(c0333y);
        this.f = z5;
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C0322w2 e(C0322w2 c0322w2) {
        return (C0322w2) this.f.getOrDefault(c0322w2, c0322w2);
    }

    @Override // com.android.tools.r8.internal.AbstractC1511fi, com.android.tools.r8.internal.XR
    public final C0322w2 f(C0322w2 c0322w2) {
        Set setA = this.f.a(c0322w2);
        return setA.isEmpty() ? c0322w2 : (C0322w2) setA.iterator().next();
    }
}
