package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ed, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1415ed extends AbstractC1501fd {
    public final WP b;

    public C1415ed(com.android.tools.r8.graph.V v, C0577Iu c0577Iu) {
        super(v);
        this.b = c0577Iu;
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final void a(com.android.tools.r8.graph.I2 i2, Consumer consumer) {
        Iterator it = this.b.get(i2).iterator();
        while (it.hasNext()) {
            consumer.accept((com.android.tools.r8.graph.E0) it.next());
        }
    }

    public final String toString() {
        return "preloaded(" + this.b.size() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final Collection a() {
        return this.b.c();
    }
}
