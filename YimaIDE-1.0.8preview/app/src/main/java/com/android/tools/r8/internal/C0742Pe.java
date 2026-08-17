package com.android.tools.r8.internal;

import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Pe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0742Pe {
    public final com.android.tools.r8.graph.B5 a;
    public final boolean b;
    public final Set c = C1755ib0.b(new C0742Pe[0]);
    public final Set d = C1755ib0.b(new C0742Pe[0]);

    public C0742Pe(boolean z, com.android.tools.r8.graph.B5 b5) {
        this.a = b5;
        this.b = z;
    }

    public final void a(Consumer consumer) {
        for (C0742Pe c0742Pe : this.d) {
            if (c0742Pe.b) {
                consumer.accept(c0742Pe);
            }
        }
    }

    public final String toString() {
        return this.a.toString();
    }

    public final boolean a() {
        return this.b;
    }
}
