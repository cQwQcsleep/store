package com.android.tools.r8.graph;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.graph.v3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0316v3 extends AbstractC0302t3 {
    public C0316v3(C0344z3 c0344z3, ArrayList arrayList, ArrayList arrayList2) {
        super(c0344z3, arrayList, arrayList2);
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final C0346z5 o() {
        AbstractC0330x3.a aVar = this.b;
        if (aVar == null) {
            return null;
        }
        return ((C0344z3) aVar).r();
    }
}
