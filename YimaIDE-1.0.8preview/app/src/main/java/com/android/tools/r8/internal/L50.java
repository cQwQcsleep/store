package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L50 extends YI implements InterfaceC1439er {
    public final /* synthetic */ J40 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L50(J40 j40) {
        super(1);
        this.c = j40;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        J40 j40 = this.c;
        Object key = ((Map.Entry) obj).getKey();
        KB.b(key, "it.key");
        j40.getClass();
        return Boolean.valueOf(j40.b.matcher((CharSequence) key).matches());
    }
}
