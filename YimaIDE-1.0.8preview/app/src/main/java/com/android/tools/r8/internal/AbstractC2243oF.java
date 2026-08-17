package com.android.tools.r8.internal;

import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2243oF {
    public final void a(Consumer consumer, Consumer consumer2) {
        Function functionA = E4.a(consumer);
        Function functionA2 = E4.a(consumer2);
        if (b() != null) {
            functionA.apply(b());
        } else {
            functionA2.apply(a());
        }
    }

    public C2415qF b() {
        return null;
    }

    public final boolean equals(Object obj) {
        throw new RuntimeException();
    }

    public final int hashCode() {
        throw new RuntimeException();
    }

    public JE a() {
        return null;
    }
}
