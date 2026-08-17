package com.android.tools.r8.diagnostic;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DefinitionMethodContext extends DefinitionContext {
    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default DefinitionMethodContext asMethodContext() {
        return this;
    }

    MethodReference getMethodReference();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    /* synthetic */ Origin getOrigin();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default boolean isMethodContext() {
        return true;
    }
}
