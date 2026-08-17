package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.I2;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class q extends o {
    public final n a;
    public final t b;

    public q(n nVar, t tVar) {
        this.a = nVar;
        this.b = tVar;
    }

    @Override // com.android.tools.r8.utils.structural.o
    public final void a(I2 i2) {
        this.a.a(this.b.a(i2).z0().f);
    }

    @Override // com.android.tools.r8.utils.structural.o
    public final void a(String str) {
        this.a.a(str.getBytes(StandardCharsets.UTF_8));
    }
}
