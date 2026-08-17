package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0332x5;
import defpackage.h14;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E extends F {
    public final /* synthetic */ V1 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(C0333y c0333y, V1 v1) {
        super(c0333y);
        this.d = v1;
    }

    @Override // com.android.tools.r8.shaking.F
    public final boolean b(InterfaceC0332x5 interfaceC0332x5) {
        return this.d.a(interfaceC0332x5.getReference(), new h14());
    }
}
