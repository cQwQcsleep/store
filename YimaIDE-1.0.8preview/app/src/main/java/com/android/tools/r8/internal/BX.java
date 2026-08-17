package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface BX extends InterfaceC1101ar {
    static {
        boolean z = AX.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1101ar
    default BX a(Function function) {
        if (!AX.a && z()) {
            x1f.a();
            return null;
        }
        if (F()) {
            com.android.tools.r8.graph.I2 i2 = e().c;
            com.android.tools.r8.graph.I2 i3 = (com.android.tools.r8.graph.I2) function.apply(i2);
            if (i2 != i3) {
                return InterfaceC1101ar.b(i3);
            }
        } else if (t()) {
            com.android.tools.r8.graph.I2 i2W = w();
            com.android.tools.r8.graph.I2 i4 = (com.android.tools.r8.graph.I2) function.apply(i2W);
            if (i2W != i4) {
                return new C1687hk0(i4, I());
            }
        }
        return this;
    }
}
