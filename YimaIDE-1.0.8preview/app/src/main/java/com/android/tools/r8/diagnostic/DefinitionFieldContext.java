package com.android.tools.r8.diagnostic;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.FieldReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DefinitionFieldContext extends DefinitionContext {
    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default DefinitionFieldContext asFieldContext() {
        return this;
    }

    FieldReference getFieldReference();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    /* synthetic */ Origin getOrigin();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default boolean isFieldContext() {
        return true;
    }
}
