package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2994x3 {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final IdentityHashMap b = new IdentityHashMap();
    public final ConcurrentHashMap c = new ConcurrentHashMap();

    public C2994x3(C0333y c0333y) {
        this.a = c0333y;
    }

    public final NO a(com.android.tools.r8.graph.B5 b5) {
        return (NO) this.b.getOrDefault(b5.getReference(), NO.b);
    }
}
