package com.android.tools.r8.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Sh0 extends Th0 implements TraceReferencesConsumer.TracedMethod {
    public Sh0(C0231j1 c0231j1, DefinitionContext definitionContext) {
        super(c0231j1.getReference().z0(), definitionContext, new C1909kO(c0231j1.getAccessFlags()), false);
    }

    public final String toString() {
        return ((MethodReference) this.a).toString();
    }

    public Sh0(MethodReference methodReference, DefinitionContext definitionContext, TraceReferencesConsumer.MethodAccessFlags methodAccessFlags) {
        super(methodReference, definitionContext, methodAccessFlags, methodAccessFlags == null);
    }
}
