package com.android.tools.r8.diagnostic;

import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MissingClassInfo extends MissingDefinitionInfo {
    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default MissingClassInfo asMissingClass() {
        return this;
    }

    ClassReference getClassReference();

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    /* synthetic */ String getDiagnosticMessage();

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default boolean isMissingClass() {
        return true;
    }
}
