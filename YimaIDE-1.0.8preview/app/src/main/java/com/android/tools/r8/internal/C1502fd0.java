package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1502fd0 implements InterfaceC1843jd0 {
    public final /* synthetic */ AbstractC2352pb a;

    public C1502fd0(C1838jb c1838jb) {
        this.a = c1838jb;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1843jd0
    public final Iterator a(C1928kd0 c1928kd0, CharSequence charSequence) {
        return new C1416ed0(this, c1928kd0, charSequence);
    }
}
