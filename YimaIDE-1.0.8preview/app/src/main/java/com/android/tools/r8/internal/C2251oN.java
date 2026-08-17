package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2251oN {
    public static final /* synthetic */ boolean c = true;
    public final List a;
    public final com.android.tools.r8.shaking.V1 b;

    public C2251oN(com.android.tools.r8.shaking.V1 v1, List list) {
        if (!c && list.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.a = list;
        this.b = v1;
    }

    public final GW a() {
        return new GW(this.b, this.a);
    }
}
