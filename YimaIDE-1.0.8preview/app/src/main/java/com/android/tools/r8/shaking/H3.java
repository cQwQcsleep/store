package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H3 extends I3 {
    public final List a;

    public H3(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.shaking.I3
    public final boolean a(String str) {
        for (G3 g3 : this.a) {
            if (G3.a(0, 0, g3.b, str)) {
                return !g3.a;
            }
        }
        return false;
    }
}
