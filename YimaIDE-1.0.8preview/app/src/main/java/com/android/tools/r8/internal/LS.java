package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LS extends DQ {
    public static final /* synthetic */ int q = 0;
    public final Map p;

    public LS(C0333y c0333y, InterfaceC1037a6 interfaceC1037a6, Map map, OS os) {
        super(c0333y, interfaceC1037a6, os);
        this.p = map;
    }

    @Override // com.android.tools.r8.internal.CQ
    public final com.android.tools.r8.graph.proto.j a(com.android.tools.r8.graph.proto.j jVar, C0322w2 c0322w2, C0322w2 c0322w3) {
        return jVar.a((com.android.tools.r8.graph.proto.j) this.p.getOrDefault(c0322w3, com.android.tools.r8.graph.proto.j.d));
    }
}
