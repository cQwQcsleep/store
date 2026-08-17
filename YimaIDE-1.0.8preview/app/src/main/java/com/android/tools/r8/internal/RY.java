package com.android.tools.r8.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RY extends UY {
    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public final Map a() {
        return new ConcurrentHashMap();
    }

    @Override // com.android.tools.r8.internal.UY, com.android.tools.r8.internal.AbstractC0490Fl
    public final Map j(int i) {
        return new ConcurrentHashMap(i);
    }
}
