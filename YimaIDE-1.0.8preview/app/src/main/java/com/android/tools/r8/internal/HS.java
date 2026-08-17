package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HS {
    public static final /* synthetic */ boolean c = true;
    public GS a;
    public final GS[] b;

    public HS(C0322w2 c0322w2) {
        GS gs = GS.b;
        this.a = gs;
        GS[] gsArr = new GS[c0322w2.A0()];
        this.b = gsArr;
        Arrays.fill(gsArr, gs);
    }

    public final boolean a() {
        GS gs = this.a;
        GS gs2 = GS.d;
        return gs == gs2 && R3.a(this.b, gs2);
    }

    public static HS a(C0322w2 c0322w2) {
        return new HS(c0322w2);
    }
}
