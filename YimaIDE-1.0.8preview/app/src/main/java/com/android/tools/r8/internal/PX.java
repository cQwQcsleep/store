package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PX extends Origin {
    public final String f;

    public PX(String str) {
        super(Origin.unknown());
        this.f = str;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return this.f;
    }
}
