package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.Kk0;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D extends C {
    @Override // com.android.tools.r8.synthesis.C, com.android.tools.r8.synthesis.A
    public final void a(I2 i2, Collection collection) {
        throw new Kk0("Unexpected attempt to add globals to non-desugaring build.");
    }
}
