package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.InterfaceC2706th0;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q2 {
    public final Set a;

    public Q2(Set set) {
        this.a = set;
    }

    public final Q2 a(AbstractC3148ys abstractC3148ys) {
        Set setC = AbstractC2780ub0.c();
        for (com.android.tools.r8.graph.I2 i2 : this.a) {
            abstractC3148ys.getClass();
            setC.add(abstractC3148ys.c(AbstractC3148ys.g(), i2));
        }
        return new Q2(setC);
    }

    public final Q2 a(final AbstractC3148ys abstractC3148ys, Ch0 ch0) {
        return (Q2) ch0.a("Rewrite ProguardCompatibilityActions", new InterfaceC2706th0() { // from class: wwb
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(abstractC3148ys);
            }
        });
    }
}
