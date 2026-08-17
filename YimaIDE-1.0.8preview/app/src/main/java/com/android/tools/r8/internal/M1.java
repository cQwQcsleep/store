package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0208g;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class M1 implements TraceReferencesConsumer.AccessFlags {
    public final AbstractC0208g a;

    public M1(AbstractC0208g abstractC0208g) {
        this.a = abstractC0208g;
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
    public final boolean isPrivate() {
        return this.a.i();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
    public final boolean isProtected() {
        return this.a.l();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
    public final boolean isPublic() {
        return this.a.m();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.AccessFlags
    public final boolean isStatic() {
        return this.a.n();
    }
}
