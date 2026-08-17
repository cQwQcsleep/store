package com.android.tools.r8.internal;

import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1184bo {
    public static final /* synthetic */ boolean c = true;
    public final AbstractC0706Nu a;
    public final C2951wb0 b;

    public C1184bo(C2951wb0 c2951wb0, AbstractC0706Nu abstractC0706Nu) {
        this.b = c2951wb0;
        this.a = abstractC0706Nu;
    }

    public final KL a(com.android.tools.r8.graph.I2 i2) {
        KL kl = (KL) this.a.get(i2);
        if (c || kl != null) {
            return kl;
        }
        x1f.a();
        return null;
    }

    public final void a(Consumer consumer) {
        this.a.values().forEach(consumer);
        consumer.accept(this.b);
    }
}
