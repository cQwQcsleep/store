package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DR extends AbstractC1757ic0 {
    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        String strB1 = d2.b1();
        return (strB1.startsWith("R$") || strB1.contains("$R$")) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoResourceClasses";
    }
}
