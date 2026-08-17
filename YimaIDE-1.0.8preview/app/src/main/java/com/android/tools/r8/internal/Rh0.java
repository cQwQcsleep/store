package com.android.tools.r8.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Rh0 extends Th0 implements TraceReferencesConsumer.TracedField {
    public Rh0(com.android.tools.r8.graph.F0 f0, DefinitionContext definitionContext) {
        super(f0.getReference().z0(), definitionContext, new C0934Wo(f0.getAccessFlags()), false);
    }

    public final String toString() {
        return ((FieldReference) this.a).toString();
    }

    public Rh0(FieldReference fieldReference, DefinitionContext definitionContext, TraceReferencesConsumer.FieldAccessFlags fieldAccessFlags) {
        super(fieldReference, definitionContext, fieldAccessFlags, fieldAccessFlags == null);
    }
}
