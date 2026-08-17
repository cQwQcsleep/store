package com.android.tools.r8.shaking;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import defpackage.pah;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S0 extends Y2 {
    public static final /* synthetic */ int s = 0;
    public final R0 r;

    public S0(Origin origin, Position position, String str, List list, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, List list2, K3 k3, boolean z2, List list3, R0 r0) {
        super(origin, position, str, list, c3470v2, c3470v3, z, o2, f2, list2, k3, z2, list3);
        this.r = r0;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String D() {
        int iOrdinal = this.r.ordinal();
        if (iOrdinal == 0) {
            return "alwaysinline";
        }
        if (iOrdinal == 1) {
            return "neverinline";
        }
        if (iOrdinal == 2) {
            return "neverclassinlinemethod";
        }
        if (iOrdinal == 3) {
            return "neversinglecaller";
        }
        pah.a("Unknown inline type ", this.r);
        return null;
    }

    public final R0 F() {
        return this.r;
    }
}
