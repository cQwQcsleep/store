package com.android.tools.r8.internal;

import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1971l5 {
    AbstractC3175z9 a(G9 g9, com.android.tools.r8.graph.B1 b1);

    default Collection a(G9 g9, com.android.tools.r8.graph.B1 b1, LL ll) {
        AbstractC3175z9 abstractC3175z9A = a(g9, b1);
        int i = AbstractC0551Hu.c;
        return new Bc0(abstractC3175z9A);
    }
}
