package com.android.tools.r8;

import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.C0702Nq;
import com.android.tools.r8.internal.Kl0;
import defpackage.b06;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L implements Kl0 {
    public final /* synthetic */ List a;

    public L(ArrayList arrayList) {
        this.a = arrayList;
    }

    @Override // com.android.tools.r8.internal.Kl0
    public final void a(D2 d2, InterfaceC0332x5 interfaceC0332x5) {
    }

    @Override // com.android.tools.r8.internal.Kl0
    public final void b(D2 d2) {
        C0702Nq c0702NqT1 = d2.T1();
        List list = this.a;
        Objects.requireNonNull(list);
        c0702NqT1.forEach(new b06(list));
    }
}
