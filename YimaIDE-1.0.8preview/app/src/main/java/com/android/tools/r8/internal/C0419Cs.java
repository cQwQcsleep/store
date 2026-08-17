package com.android.tools.r8.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0419Cs extends AbstractC3220zi0 {
    public final /* synthetic */ AbstractC3220zi0 a;

    public C0419Cs(AbstractC3220zi0 abstractC3220zi0) {
        this.a = abstractC3220zi0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
        c2754uD.d();
        int length = atomicLongArray.length();
        for (int i = 0; i < length; i++) {
            this.a.a(c2754uD, Long.valueOf(atomicLongArray.get(i)));
        }
        c2754uD.f();
    }
}
