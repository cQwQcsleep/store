package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.y1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0335y1 extends AbstractC0169a2 {
    public final C0245l1 a;
    public final C0245l1 b;
    public final C0245l1 c;
    public final C0245l1 d;
    public final C0245l1 e;

    public C0335y1(B1 b1) {
        this.a = b1.a(b1.U3, b1.T3, "TRANSLATION_Z");
        this.b = b1.a(b1.U3, b1.K1, "EMPTY_STATE_SET");
        this.c = b1.a(b1.U3, b1.K1, "ENABLED_STATE_SET");
        this.d = b1.a(b1.U3, b1.K1, "PRESSED_ENABLED_STATE_SET");
        this.e = b1.a(b1.U3, b1.K1, "SELECTED_STATE_SET");
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
