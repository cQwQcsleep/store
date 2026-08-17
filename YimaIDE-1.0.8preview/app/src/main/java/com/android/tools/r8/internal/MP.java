package com.android.tools.r8.internal;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MP extends JR {
    public static final /* synthetic */ boolean d = true;
    public final AtomicInteger c;

    public MP(com.android.tools.r8.graph.B5 b5) {
        super(b5);
        this.c = new AtomicInteger();
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(JR jr, boolean z) {
        if (d || !d().m1()) {
            this.c.incrementAndGet();
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.JR
    public final void a(JR jr) {
        throw new Kk0();
    }
}
