package com.android.tools.r8.shaking;

import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class X1 {
    public final Set a;

    public X1(Set set) {
        this.a = set;
    }

    public final boolean a(com.android.tools.r8.graph.I2 i2) {
        return this.a.contains(i2);
    }

    public final void a(Consumer consumer) {
        this.a.forEach(consumer);
    }
}
