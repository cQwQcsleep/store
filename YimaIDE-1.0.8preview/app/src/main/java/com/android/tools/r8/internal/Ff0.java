package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ff0 extends AbstractC1751iZ {
    public static final /* synthetic */ boolean g = true;

    public Ff0(C2516rW c2516rW) {
        super(c2516rW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        Ef0 ef0 = (Ef0) obj;
        if (!g && !Ef0.b.equals(ef0)) {
            x1f.a();
            return false;
        }
        if (!(obj2 instanceof String)) {
            return false;
        }
        consumer.accept((String) obj2);
        return true;
    }
}
