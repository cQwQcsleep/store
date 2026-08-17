package com.android.tools.r8.internal;

import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0438Dl extends AbstractC0464El {
    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public final Map a() {
        return new IdentityHashMap();
    }

    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public final Map j(int i) {
        return new IdentityHashMap(i);
    }
}
