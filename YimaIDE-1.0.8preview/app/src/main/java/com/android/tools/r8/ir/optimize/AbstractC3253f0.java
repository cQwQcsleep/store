package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.KN;
import defpackage.nl0;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3253f0 {
    public final C2543rl0 a;
    public final KN b;

    public AbstractC3253f0(C2543rl0 c2543rl0, KN kn) {
        this.a = c2543rl0;
        this.b = kn;
    }

    public static AbstractC3253f0 a(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return c2543rl1.c(new nl0()) ? new C3255g0(c2543rl0, c2543rl1.p().F().N2(), kn) : new C3257h0(kn, c2543rl0, c2543rl1);
    }

    public abstract boolean a(int i);
}
