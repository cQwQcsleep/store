package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2422qN implements InterfaceC2507rN {
    public final /* synthetic */ C0230j0 b;
    public final /* synthetic */ AbstractC2624sj0 c;
    public final /* synthetic */ AbstractC2004lX d;

    public C2422qN(C0230j0 c0230j0, AbstractC2624sj0 abstractC2624sj0, AbstractC2004lX abstractC2004lX) {
        this.b = c0230j0;
        this.c = abstractC2624sj0;
        this.d = abstractC2004lX;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public final AbstractC2624sj0 a() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public final AbstractC2004lX getPosition() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2507rN
    public final C0230j0 k() {
        return this.b;
    }
}
