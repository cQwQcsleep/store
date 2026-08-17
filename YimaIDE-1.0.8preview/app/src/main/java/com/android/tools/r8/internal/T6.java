package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T6 extends AbstractC1751iZ {
    public T6(C2516rW c2516rW) {
        super(c2516rW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        if (!S6.b.equals((S6) obj) || !(obj2 instanceof Boolean)) {
            return false;
        }
        consumer.accept((Boolean) obj2);
        return true;
    }
}
