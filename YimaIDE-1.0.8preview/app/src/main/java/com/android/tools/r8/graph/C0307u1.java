package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.u1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0307u1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;
    public final C0245l1 d;
    public final C0245l1 e;

    public C0307u1(B1 b1) {
        this.a = b1.a(b1.O3, b1.Y1, "CODENAME");
        this.b = b1.a(b1.O3, b1.Y1, "RELEASE");
        this.c = b1.a(b1.O3, b1.Y1, "SDK");
        this.d = b1.a(b1.O3, b1.B1, "SDK_INT");
        this.e = b1.a(b1.O3, b1.Y1, "SECURITY_PATCH");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
        consumer.accept(this.c);
        consumer.accept(this.d);
        consumer.accept(this.e);
    }
}
