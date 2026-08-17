package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC2775uY;
import com.android.tools.r8.internal.C1233cS;
import com.android.tools.r8.internal.FY;
import com.android.tools.r8.internal.MA;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Y3 extends MA {
    static Y3 a(AbstractC2775uY abstractC2775uY) {
        X3 x3 = X3.a;
        abstractC2775uY.getClass();
        return abstractC2775uY instanceof C1233cS ? x3 : new FY(abstractC2775uY.a());
    }
}
