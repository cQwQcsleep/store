package com.android.tools.r8.internal;

import com.android.tools.r8.internal.JR;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1465f8<N extends JR<N>> {
    public final Map a;

    public AbstractC1465f8(Map map) {
        this.a = map;
    }

    public Collection<N> a() {
        return this.a.values();
    }

    public boolean b() {
        return this.a.isEmpty();
    }
}
