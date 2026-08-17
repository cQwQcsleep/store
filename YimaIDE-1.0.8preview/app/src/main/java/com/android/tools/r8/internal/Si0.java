package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Si0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        UUID uuid = (UUID) obj;
        c2754uD.d(uuid == null ? null : uuid.toString());
    }
}
