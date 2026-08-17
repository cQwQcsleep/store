package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1501fd {
    public final com.android.tools.r8.graph.V a;

    public AbstractC1501fd(com.android.tools.r8.graph.V v) {
        this.a = v;
    }

    public C1330dd a(Cc0 cc0) {
        return new C1330dd(this.a, this, cc0);
    }

    public abstract Collection a();

    public abstract void a(com.android.tools.r8.graph.I2 i2, Consumer consumer);
}
