package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2141n40 extends YI implements InterfaceC1439er {
    public static final C2141n40 c = new C2141n40();

    public C2141n40() {
        super(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1439er
    public final Object b(Object obj) {
        List list = (List) obj;
        KB.c(list, "<name for destructuring parameter 0>");
        String str = (String) list.get(0);
        String str2 = (String) list.get(1);
        return new C1491fW((U50) U50.I.get(AbstractC1679hg0.b(str, '-')), AbstractC1679hg0.b(str2, '.'));
    }
}
