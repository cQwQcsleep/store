package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2056m40 extends YI implements InterfaceC1439er {
    public static final C2056m40 c = new C2056m40();

    public C2056m40() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        List list = (List) obj;
        KB.c(list, "it");
        return Boolean.valueOf(list.size() == 2);
    }
}
