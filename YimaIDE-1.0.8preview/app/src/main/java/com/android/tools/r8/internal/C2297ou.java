package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ou, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2297ou implements InterfaceC1115b2 {
    public static final C2297ou a = new C2297ou();
    public static final /* synthetic */ boolean b = true;

    @Override // com.android.tools.r8.internal.InterfaceC1115b2
    public final C2543rl0 a(AbstractC0890Uw abstractC0890Uw) {
        if (!b) {
            abstractC0890Uw.getClass();
            if (!(abstractC0890Uw instanceof C3165z4) && !(abstractC0890Uw instanceof C0642Lh)) {
                x1f.a();
                return null;
            }
        }
        abstractC0890Uw.getClass();
        return abstractC0890Uw instanceof C3165z4 ? abstractC0890Uw.x().L2() : abstractC0890Uw.H().L2();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1115b2
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return (abstractC0890Uw instanceof C3165z4) || (abstractC0890Uw instanceof C0642Lh);
    }
}
