package com.android.tools.r8.internal;

import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.h90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1639h90 extends YI implements InterfaceC1439er {
    public final /* synthetic */ Consumer c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1639h90(Consumer consumer) {
        super(1);
        this.c = consumer;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        List list = (List) obj;
        KB.c(list, "it");
        this.c.accept(list);
        return C2028lk0.a;
    }
}
