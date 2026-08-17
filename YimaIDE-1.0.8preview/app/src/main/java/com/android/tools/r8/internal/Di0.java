package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Di0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            c2754uD.i();
        } else {
            c2754uD.a(number.longValue());
        }
    }
}
