package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Od0 extends C2543rl0 {
    public static final /* synthetic */ boolean t = true;
    public final int r;
    public final Mj0 s;

    public Od0(Mj0 mj0, AbstractC2624sj0 abstractC2624sj0, int i) {
        super(-1, abstractC2624sj0, null);
        this.r = i;
        this.s = mj0;
        if (t || i >= 0) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static Od0 a(Mj0 mj0, int i, C0333y c0333y) {
        return new Od0(mj0, AbstractC2624sj0.a(mj0.a(), C2427qS.h(), (C0333y<?>) c0333y), i);
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean T() {
        return false;
    }

    public final Mj0 c0() {
        return this.s;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final String toString() {
        return "s" + this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final void a(boolean z) {
        if (t || !z) {
            return;
        }
        x1f.a();
    }
}
