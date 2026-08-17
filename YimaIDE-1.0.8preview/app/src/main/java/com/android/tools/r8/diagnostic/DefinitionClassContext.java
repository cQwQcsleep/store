package com.android.tools.r8.diagnostic;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DefinitionClassContext extends DefinitionContext {
    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default DefinitionClassContext asClassContext() {
        return this;
    }

    ClassReference getClassReference();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    /* synthetic */ Origin getOrigin();

    @Override // com.android.tools.r8.diagnostic.DefinitionContext
    default boolean isClassContext() {
        return true;
    }
}
