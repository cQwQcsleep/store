package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2411qB extends Origin {
    public final String f;

    public C2411qB(String str, Origin origin) {
        super(origin);
        this.f = str;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "global(" + this.f + ")";
    }
}
