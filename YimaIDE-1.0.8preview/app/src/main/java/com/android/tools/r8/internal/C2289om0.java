package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.om0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2289om0 {
    public static final /* synthetic */ boolean d = true;
    public final C2289om0 a;
    public final com.android.tools.r8.graph.B5 b;
    public final UY c = UY.c();

    public C2289om0(com.android.tools.r8.graph.B5 b5, C2289om0 c2289om0) {
        if (!d && b5 == null) {
            x1f.a();
            throw null;
        }
        this.a = c2289om0;
        this.b = b5;
    }

    public final void a(com.android.tools.r8.graph.B5 b5) {
        boolean z = d;
        if (!z && b5.e() == this.b.e()) {
            x1f.a();
            return;
        }
        if (!z && !b5.C().equals(this.b.C())) {
            x1f.a();
            return;
        }
        this.c.add(b5);
        C2289om0 c2289om0 = this.a;
        if (c2289om0 != null) {
            c2289om0.a(b5);
        }
    }

    public final void a(Consumer consumer) {
        consumer.accept(this.b);
        this.c.forEach(consumer);
    }
}
