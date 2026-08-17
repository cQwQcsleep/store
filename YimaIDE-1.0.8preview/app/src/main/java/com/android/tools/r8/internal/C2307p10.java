package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2307p10 {
    public final Y00 a;
    public final Y00 b;

    public C2307p10(Y00 y00, Y00 y01) {
        this.a = y00;
        this.b = y01;
    }

    public final void a(Consumer consumer) {
        consumer.accept(this.a);
        consumer.accept(this.b);
    }
}
