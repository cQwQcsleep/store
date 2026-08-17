package com.android.tools.r8.graph;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Y implements InterfaceC0174b0 {
    public final E0 b;
    public final C0281q2 c;

    public Y(E0 e0, C0281q2 c0281q2) {
        this.b = e0;
        this.c = c0281q2;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final boolean Q() {
        return true;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final void b(Consumer consumer) {
        consumer.accept(this.b);
        consumer.accept(this.c);
    }

    @Override // com.android.tools.r8.graph.InterfaceC0174b0
    public final boolean q() {
        return true;
    }
}
