package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1927kd extends AbstractC1751iZ {
    public C1927kd(C2516rW c2516rW) {
        super(c2516rW);
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final boolean a(Object obj, String str, Object obj2, Consumer consumer) {
        if (((EnumC1842jd) obj).ordinal() != 0) {
            return false;
        }
        consumer.accept(new C2758uH((String) obj2));
        return true;
    }
}
