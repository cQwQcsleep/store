package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import defpackage.pah;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3452s extends Y2 {
    public static final /* synthetic */ int s = 0;
    public final r r;

    public C3452s(Origin origin, Position position, String str, AbstractC0551Hu abstractC0551Hu, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, AbstractC0551Hu abstractC0551Hu2, K3 k3, boolean z2, List list, r rVar) {
        super(origin, position, str, abstractC0551Hu, c3470v2, c3470v3, z, o2, f2, abstractC0551Hu2, k3, z2, list);
        this.r = rVar;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String D() {
        int iOrdinal = this.r.ordinal();
        if (iOrdinal == 0) {
            return "alwaysclassinline";
        }
        if (iOrdinal == 1) {
            return "neverclassinline";
        }
        pah.a("Unknown class inline type ", this.r);
        return null;
    }

    public final r F() {
        return this.r;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final C3452s m() {
        return this;
    }
}
