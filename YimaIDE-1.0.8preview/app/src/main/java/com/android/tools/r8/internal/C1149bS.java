package com.android.tools.r8.internal;

import com.android.tools.r8.ir.optimize.info.C3261b;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1149bS extends AbstractC1748iW {
    public static final /* synthetic */ boolean a = true;

    public C1149bS(boolean z) {
        if (a || !z) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1748iW
    public final boolean a(com.android.tools.r8.graph.B5 b5, C3261b c3261b, int i, com.android.tools.r8.graph.I2 i2) {
        if (i2.U0()) {
            if (c3261b.a(i).isUnknown()) {
                return (i != 0 || b5.e().z0()) && !c3261b.b(i).d().f();
            }
            return true;
        }
        if (a || i2.T0()) {
            return c3261b.a(i).F();
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1748iW
    public final boolean a() {
        return false;
    }
}
