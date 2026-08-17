package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1673hd0 implements InterfaceC1843jd0 {
    public final /* synthetic */ AbstractC2613se a;

    public C1673hd0(C1134bD c1134bD) {
        this.a = c1134bD;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1843jd0
    public final Iterator a(C1928kd0 c1928kd0, CharSequence charSequence) {
        return new C1587gd0(c1928kd0, charSequence, new C1048aD(((C1134bD) this.a).b.matcher(charSequence)));
    }
}
