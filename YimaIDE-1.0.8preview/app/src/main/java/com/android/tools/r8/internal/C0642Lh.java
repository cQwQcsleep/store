package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0642Lh extends C2680tP {
    public static final /* synthetic */ boolean j = true;

    public C0642Lh(C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        super(c2543rl0, c2543rl1);
        if (j || c2543rl0.y()) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C0642Lh H() {
        return this;
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        if (!j && g()) {
            x1f.a();
        } else if (i().x()) {
            k5.a(il.b, il.e);
            il.e.previous();
        }
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        return abstractC0890Uw instanceof C0642Lh;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean i1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean r2() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return nj0.a(L2());
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        return null;
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        El0 el0I2 = I2();
        C2543rl0 c2543rl0C = c();
        j8.g.getClass();
        j8.a(new C0842Ta(el0I2, C2777ua.a(c2543rl0C)), this);
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, Ol0 ol0) {
        super.a(c0333y, b5, ol0);
        if (j || ol0.a(L2().t(), a())) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.C2680tP, com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        C2543rl0 c2543rl0L2 = L2();
        lk.getClass();
        lk.a(213, Collections.EMPTY_LIST, Collections.singletonList(c2543rl0L2));
    }
}
