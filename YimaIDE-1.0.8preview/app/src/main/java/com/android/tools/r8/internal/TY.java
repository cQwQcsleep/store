package com.android.tools.r8.internal;

import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TY extends UY {
    public TY() {
    }

    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public final Map a() {
        return new IdentityHashMap();
    }

    @Override // com.android.tools.r8.internal.UY, com.android.tools.r8.internal.AbstractC0490Fl
    public final Map j(int i) {
        return new IdentityHashMap(i);
    }

    public TY(int i) {
        super(i);
    }
}
