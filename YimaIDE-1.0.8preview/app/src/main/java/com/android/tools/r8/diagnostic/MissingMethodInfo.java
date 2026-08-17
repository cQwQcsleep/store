package com.android.tools.r8.diagnostic;

import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MissingMethodInfo extends MissingDefinitionInfo {
    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default MissingMethodInfo asMissingMethod() {
        return this;
    }

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    /* synthetic */ String getDiagnosticMessage();

    MethodReference getMethodReference();

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default boolean isMissingMethod() {
        return true;
    }
}
