package com.android.tools.r8.shaking;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import defpackage.hkh;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U3 extends Y2 {
    public static final /* synthetic */ int s = 0;
    public final int r;

    public U3(Origin origin, Position position, String str, AbstractC0551Hu abstractC0551Hu, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, AbstractC0551Hu abstractC0551Hu2, K3 k3, boolean z2, List list, int i) {
        super(origin, position, str, abstractC0551Hu, c3470v2, c3470v3, z, o2, f2, abstractC0551Hu2, k3, z2, list);
        this.r = i;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String D() {
        int iB = AbstractC0007c.b(this.r);
        if (iB == 0) {
            return "reprocessclassinitializer";
        }
        if (iB == 1) {
            return "neverreprocessclassinitializer";
        }
        hkh.a();
        return null;
    }

    public final int F() {
        return this.r;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final U3 r() {
        return this;
    }
}
