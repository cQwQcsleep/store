package com.android.tools.r8.internal;

import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G2 extends AbstractC1751iZ {
    public static final /* synthetic */ boolean g = true;

    public G2(AbstractC3114yW abstractC3114yW) {
        super(abstractC3114yW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean b(Object obj, String str, String str2, Consumer consumer) {
        H2 h2 = (H2) obj;
        if (!g && h2 != H2.b) {
            x1f.a();
            return false;
        }
        if (!"Ljava/lang/annotation/RetentionPolicy;".equals(str)) {
            return false;
        }
        consumer.accept(RetentionPolicy.valueOf(str2));
        return true;
    }
}
