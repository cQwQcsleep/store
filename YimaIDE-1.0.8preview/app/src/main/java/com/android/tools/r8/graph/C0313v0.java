package com.android.tools.r8.graph;

import com.android.tools.r8.synthesis.C3492a;
import com.android.tools.r8.synthesis.InterfaceC3510t;
import defpackage.hzg;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.graph.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0313v0 implements InterfaceC0189d1 {
    public final /* synthetic */ InterfaceC3510t a;
    public final /* synthetic */ AbstractC0327x0 b;

    public C0313v0(C3492a c3492a, AbstractC0327x0 abstractC0327x0) {
        this.a = c3492a;
        this.b = abstractC0327x0;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final B1 a() {
        return this.b.e;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final E0 d(I2 i2) {
        InterfaceC3510t interfaceC3510t = this.a;
        AbstractC0327x0 abstractC0327x0 = this.b;
        Objects.requireNonNull(abstractC0327x0);
        return interfaceC3510t.a(new hzg(abstractC0327x0), i2).P();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final InterfaceC0174b0 g(I2 i2) {
        InterfaceC3510t interfaceC3510t = this.a;
        AbstractC0327x0 abstractC0327x0 = this.b;
        Objects.requireNonNull(abstractC0327x0);
        return interfaceC3510t.a(new hzg(abstractC0327x0), i2);
    }
}
