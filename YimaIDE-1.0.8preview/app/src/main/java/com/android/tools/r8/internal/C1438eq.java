package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1438eq extends C2543rl0 {
    public static final /* synthetic */ boolean s = true;
    public final int r;

    public C1438eq(int i, AbstractC2624sj0 abstractC2624sj0) {
        super(-1, abstractC2624sj0, null);
        a(true);
        this.r = i;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean J() {
        return false;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final El0 Y() {
        AbstractC2624sj0 abstractC2624sj0T = t();
        if (!abstractC2624sj0T.H()) {
            if (s || abstractC2624sj0T.I()) {
                return El0.b;
            }
            x1f.a();
            return null;
        }
        if (abstractC2624sj0T.K()) {
            if (abstractC2624sj0T.C()) {
                return El0.c;
            }
            if (abstractC2624sj0T.B()) {
                return El0.d;
            }
        } else {
            if (!s && !abstractC2624sj0T.M()) {
                x1f.a();
                return null;
            }
            if (abstractC2624sj0T.z()) {
                return El0.f;
            }
            if (abstractC2624sj0T.D()) {
                return El0.e;
            }
        }
        defpackage.gk0.a("Unexpected imprecise type: ", abstractC2624sj0T);
        return null;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final boolean c(Predicate predicate) {
        return false;
    }

    public int c0() {
        return this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final String toString() {
        return "r" + this.r;
    }

    @Override // com.android.tools.r8.internal.C2543rl0
    public final C1438eq c() {
        return this;
    }
}
