package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ei0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Number numberValueOf = (Number) obj;
        if (numberValueOf == null) {
            c2754uD.i();
            return;
        }
        if (!(numberValueOf instanceof Float)) {
            numberValueOf = Float.valueOf(numberValueOf.floatValue());
        }
        c2754uD.a(numberValueOf);
    }
}
