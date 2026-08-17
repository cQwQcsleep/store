package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.shaking.L1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface L1 {
    static L1 b() {
        return new L1() { // from class: pk8
            @Override // com.android.tools.r8.shaking.L1
            public final boolean e(I2 i2) {
                return L1.c(i2);
            }
        };
    }

    static /* synthetic */ boolean c(com.android.tools.r8.graph.I2 i2) {
        return false;
    }

    boolean e(com.android.tools.r8.graph.I2 i2);
}
