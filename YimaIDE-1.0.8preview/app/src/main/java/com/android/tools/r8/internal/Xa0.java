package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xa0 implements Ua0 {
    public final /* synthetic */ InterfaceC2635sr a;

    public Xa0(InterfaceC2635sr interfaceC2635sr) {
        this.a = interfaceC2635sr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.Ua0
    public final Iterator iterator() {
        InterfaceC2635sr interfaceC2635sr = this.a;
        KB.c(interfaceC2635sr, "block");
        Va0 va0 = new Va0();
        va0.d = interfaceC2635sr instanceof AbstractC2323p90 ? ((AbstractC2323p90) interfaceC2635sr).a(va0, va0) : new LB(va0, interfaceC2635sr, va0);
        return va0;
    }
}
