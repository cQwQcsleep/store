package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.w1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0321w1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;

    public C0321w1(B1 b1) {
        this.a = b1.a(b1.R3, b1.B1, "S_IRUSR");
        this.b = b1.a(b1.R3, b1.B1, "S_IXUSR");
    }

    @Override // com.android.tools.r8.graph.AbstractC0169a2
    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
