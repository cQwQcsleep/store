package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1970l40 extends YI implements InterfaceC1439er {
    public static final C1970l40 c = new C1970l40();

    public C1970l40() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        String str = (String) obj;
        KB.c(str, "it");
        return AbstractC1679hg0.a(str, new char[]{DataResource.SEPARATOR}, 2);
    }
}
