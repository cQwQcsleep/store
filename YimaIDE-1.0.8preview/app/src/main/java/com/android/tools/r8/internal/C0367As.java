package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.As, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0367As extends AbstractC3220zi0 {
    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Number numberValueOf = (Number) obj;
        if (numberValueOf == null) {
            c2754uD.i();
            return;
        }
        float fFloatValue = numberValueOf.floatValue();
        double d = fFloatValue;
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            o06.a(d, " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            return;
        }
        if (!(numberValueOf instanceof Float)) {
            numberValueOf = Float.valueOf(fFloatValue);
        }
        c2754uD.a(numberValueOf);
    }
}
