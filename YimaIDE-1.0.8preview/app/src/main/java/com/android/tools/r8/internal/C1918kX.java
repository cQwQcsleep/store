package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.utils.structural.A;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1918kX extends AbstractC2004lX {
    public static final /* synthetic */ boolean i = true;
    public final C0322w2 h;

    public C1918kX(int i2, C0322w2 c0322w2, AbstractC2004lX abstractC2004lX, boolean z, C0322w2 c0322w3) {
        super(i2, c0322w2, abstractC2004lX, z, true);
        this.h = c0322w3;
        if (i || c0322w3 != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final AbstractC2004lX.a b() {
        C0322w2 c0322w2 = this.h;
        C1833jX c1833jX = new C1833jX();
        c1833jX.h = c0322w2;
        C1833jX c1833jX2 = (C1833jX) ((C1833jX) c1833jX.a(this.b)).a(this.c);
        c1833jX2.c = this.d;
        c1833jX2.d = this.e;
        c1833jX2.e = this.f;
        return c1833jX2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final int d() {
        return 3;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: dih
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                AbstractC2004lX.a(a);
            }
        };
    }
}
