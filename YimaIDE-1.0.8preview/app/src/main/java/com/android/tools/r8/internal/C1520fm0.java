package com.android.tools.r8.internal;

import java.util.IdentityHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fm0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1520fm0 {
    public final Z5 a = Z5.a();
    public final X5 b = new X5(new IdentityHashMap(), new IdentityHashMap());
    public final X5 c = new X5(new IdentityHashMap(), new IdentityHashMap());

    public final void a(com.android.tools.r8.graph.D2 d2, com.android.tools.r8.graph.D2 d3) {
        this.a.a(d2.getType(), d3.getType());
        if (d2.isInterface()) {
            if (d3.isInterface()) {
                this.c.a(d2.getType(), d3.getType());
            } else {
                this.b.a(d2.getType(), d3.getType());
            }
        }
    }
}
