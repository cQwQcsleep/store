package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A4 implements InterfaceC1115b2 {
    public static final A4 a = new A4();
    public static final /* synthetic */ boolean b = true;

    @Override // com.android.tools.r8.internal.InterfaceC1115b2
    public final C2543rl0 a(AbstractC0890Uw abstractC0890Uw) {
        if (!b) {
            abstractC0890Uw.getClass();
            if (!(abstractC0890Uw instanceof C3165z4) && !abstractC0890Uw.u1()) {
                x1f.a();
                return null;
            }
        }
        return (C2543rl0) C2847vL.a(abstractC0890Uw.c);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1115b2
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C3165z4) || abstractC0890Uw.u1();
    }
}
