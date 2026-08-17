package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1242cb implements InterfaceC1326db {
    public final /* synthetic */ Consumer a;

    public C1242cb(Consumer consumer) {
        this.a = consumer;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1326db
    public final InterfaceC1326db a(String str) {
        this.a.accept(str);
        return this;
    }
}
