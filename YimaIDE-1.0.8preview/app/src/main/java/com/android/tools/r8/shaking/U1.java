package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class U1 extends Y2 {
    public static final /* synthetic */ int s = 0;
    public final int r;

    public U1(Origin origin, Position position, String str, AbstractC0551Hu abstractC0551Hu, C3470v2 c3470v2, C3470v2 c3470v3, boolean z, O2 o2, F2 f2, AbstractC0551Hu abstractC0551Hu2, K3 k3, boolean z2, List list, int i) {
        super(origin, position, str, abstractC0551Hu, c3470v2, c3470v3, z, o2, f2, abstractC0551Hu2, k3, z2, list);
        this.r = i;
    }

    public static int a(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        return i2 == 0 ? i : Math.min(i, i2);
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String D() {
        return "maximumremovedandroidloglevel";
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final String E() {
        return Integer.toString(this.r);
    }

    public int F() {
        return this.r;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final U1 n() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.Y2
    public final boolean w() {
        return true;
    }
}
