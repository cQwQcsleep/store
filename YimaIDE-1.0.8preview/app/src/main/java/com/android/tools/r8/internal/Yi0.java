package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Yi0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        BitSet bitSet = (BitSet) obj;
        c2754uD.d();
        int length = bitSet.length();
        for (int i = 0; i < length; i++) {
            c2754uD.a(bitSet.get(i) ? 1L : 0L);
        }
        c2754uD.f();
    }
}
