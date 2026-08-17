package com.android.tools.r8.shaking;

import defpackage.qfh;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final String a(com.android.tools.r8.graph.H2 h2) {
        return (String) this.a.computeIfAbsent(h2, new qfh());
    }
}
