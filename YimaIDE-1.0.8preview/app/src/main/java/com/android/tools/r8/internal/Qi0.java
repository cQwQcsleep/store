package com.android.tools.r8.internal;

import java.io.IOException;
import java.net.URI;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Qi0 extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        URI uri = (URI) obj;
        c2754uD.d(uri == null ? null : uri.toASCIIString());
    }
}
