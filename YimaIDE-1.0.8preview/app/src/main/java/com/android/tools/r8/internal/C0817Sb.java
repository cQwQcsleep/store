package com.android.tools.r8.internal;

import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0817Sb extends M1 implements TraceReferencesConsumer.ClassAccessFlags {
    public C0817Sb(com.android.tools.r8.graph.Q q) {
        super(q);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ClassAccessFlags
    public final boolean isEnum() {
        return ((com.android.tools.r8.graph.Q) this.a).K();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ClassAccessFlags
    public final boolean isInterface() {
        return ((com.android.tools.r8.graph.Q) this.a).L();
    }
}
