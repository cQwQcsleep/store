package com.android.tools.r8.internal;

import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3229zn extends AbstractC3143yn {
    public final C0421Cu a;

    public C3229zn(C0421Cu c0421Cu) {
        this.a = c0421Cu;
    }

    public final void a(BiConsumer biConsumer) {
        this.a.forEach(biConsumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC3143yn
    public final C3229zn c() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3143yn
    public final boolean d() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3143yn
    public final boolean e() {
        return false;
    }
}
