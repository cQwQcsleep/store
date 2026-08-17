package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Da, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0427Da {
    public static K9 a(List list) {
        AbstractC3175z9 abstractC3175z9 = list.isEmpty() ? null : (AbstractC3175z9) list.get(list.size() - 1);
        if (abstractC3175z9 != null && (abstractC3175z9 instanceof K9)) {
            return abstractC3175z9.o();
        }
        K9 k9 = new K9();
        list.add(k9);
        return k9;
    }
}
