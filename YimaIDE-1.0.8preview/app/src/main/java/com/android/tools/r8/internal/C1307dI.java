package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1307dI {
    public final EnumC1563gI a;
    public final EnumC1477fI b;
    public final ArrayList c;
    public C1391eI d;

    public C1307dI(EnumC1563gI enumC1563gI, EnumC1477fI enumC1477fI) {
        KB.c(enumC1563gI, "type");
        this.a = enumC1563gI;
        this.b = enumC1477fI;
        this.c = new ArrayList(1);
    }

    public final void a(C1391eI c1391eI) {
        this.d = c1391eI;
    }
}
