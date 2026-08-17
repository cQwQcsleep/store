package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Nn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0699Nn {
    public static final /* synthetic */ boolean e = true;
    public final com.android.tools.r8.graph.D2 a;
    public final C1907kM b;
    public final Set c = C1755ib0.a();
    public final Set d;

    public C0699Nn(C0333y c0333y, com.android.tools.r8.graph.D2 d2, AbstractC2554rv abstractC2554rv, AbstractC3148ys abstractC3148ys) {
        this.d = null;
        if (!e && c0333y.A() != abstractC3148ys) {
            x1f.a();
            throw null;
        }
        this.a = d2;
        this.d = abstractC2554rv;
        this.b = C1907kM.a(abstractC3148ys);
    }
}
