package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3505n extends a0 {
    public final I2 d;

    public AbstractC3505n(S.b bVar, C3502k c3502k, I2 i2) {
        super(bVar, c3502k);
        this.d = i2;
    }

    @Override // com.android.tools.r8.synthesis.a0, com.android.tools.r8.synthesis.InterfaceC3501j
    public final I2 a() {
        return this.d;
    }

    @Override // com.android.tools.r8.synthesis.a0
    public final F2 d() {
        return this.d;
    }
}
