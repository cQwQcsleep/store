package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.Y1;
import defpackage.bii;
import java.util.IdentityHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3276v implements Y1 {
    public final IdentityHashMap b;

    public C3276v(IdentityHashMap identityHashMap) {
        IdentityHashMap identityHashMap2 = new IdentityHashMap();
        this.b = identityHashMap2;
        identityHashMap2.putAll(identityHashMap);
    }

    public final synchronized void a(IdentityHashMap identityHashMap) {
        this.b.putAll(identityHashMap);
    }

    @Override // com.android.tools.r8.internal.Y1
    public final void b() {
        this.b.forEach(new bii());
    }
}
