package com.android.tools.r8.internal;

import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Th0 implements TraceReferencesConsumer.TracedReference {
    public static final /* synthetic */ boolean e = true;
    public final Object a;
    public final DefinitionContext b;
    public final Object c;
    public final boolean d;

    public Th0(Object obj, DefinitionContext definitionContext, Object obj2, boolean z) {
        if (!e && obj2 == null && !z) {
            x1f.a();
            throw null;
        }
        this.a = obj;
        this.b = definitionContext;
        this.c = obj2;
        this.d = z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Th0) {
            return this.a.equals(((Th0) obj).a);
        }
        return false;
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
    public final Object getAccessFlags() {
        return this.c;
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
    public final Object getReference() {
        return this.a;
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
    public final DefinitionContext getReferencedFromContext() {
        return this.b;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.TracedReference
    public final boolean isMissingDefinition() {
        return this.d;
    }
}
