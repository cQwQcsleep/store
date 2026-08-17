package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Hq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0547Hq implements InterfaceC0599Jq {
    public static final /* synthetic */ boolean c = true;
    public final /* synthetic */ r a;
    public final /* synthetic */ C0391Bq b;

    public C0547Hq(r rVar, C0391Bq c0391Bq) {
        this.a = rVar;
        this.b = c0391Bq;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0599Jq
    public final Cl0 a(B5 b5, Supplier supplier) {
        if (c || this.a.a(b5)) {
            return this.b.a(b5, supplier);
        }
        x1f.a();
        return null;
    }
}
