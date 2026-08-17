package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yi0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3134yi0 extends AbstractC3220zi0 {
    public final /* synthetic */ AbstractC3220zi0 a;

    public C3134yi0(AbstractC3220zi0 abstractC3220zi0) {
        this.a = abstractC3220zi0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        if (obj == null) {
            c2754uD.i();
        } else {
            this.a.a(c2754uD, obj);
        }
    }
}
