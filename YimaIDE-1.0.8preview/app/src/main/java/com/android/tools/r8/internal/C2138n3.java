package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2138n3 {
    public static final /* synthetic */ boolean e = true;
    public final C0333y a;
    public final C1291d6 b = new C1291d6();
    public final C1291d6 c = new C1291d6();
    public final IdentityHashMap d = new IdentityHashMap();

    public C2138n3(C0333y c0333y) {
        this.a = c0333y;
    }

    public final C2138n3 a(C2138n3 c2138n3) {
        this.b.a((V5) c2138n3.b);
        this.c.a((V5) c2138n3.c);
        this.d.putAll(c2138n3.d);
        return this;
    }
}
