package com.android.tools.r8.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Qh0 extends Th0 implements TraceReferencesConsumer.TracedClass {
    public Qh0(DefinitionContext definitionContext, com.android.tools.r8.graph.E0 e0) {
        super(e0.P0(), definitionContext, new C0817Sb(e0.getAccessFlags()), false);
    }

    public final String toString() {
        return ((ClassReference) this.a).getTypeName();
    }

    public Qh0(ClassReference classReference, DefinitionContext definitionContext, TraceReferencesConsumer.ClassAccessFlags classAccessFlags) {
        super(classReference, definitionContext, classAccessFlags, classAccessFlags == null);
    }
}
