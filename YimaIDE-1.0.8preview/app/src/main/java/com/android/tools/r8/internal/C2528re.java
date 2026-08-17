package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.re, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2528re extends YI implements InterfaceC1270cr {
    public final /* synthetic */ Iterable c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2528re(List list) {
        super(0);
        this.c = list;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1270cr
    public final Object a() {
        return this.c.iterator();
    }
}
