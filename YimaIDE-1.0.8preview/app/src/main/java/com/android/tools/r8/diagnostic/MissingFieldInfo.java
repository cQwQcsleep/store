package com.android.tools.r8.diagnostic;

import com.android.tools.r8.references.FieldReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MissingFieldInfo extends MissingDefinitionInfo {
    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default MissingFieldInfo asMissingField() {
        return this;
    }

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    /* synthetic */ String getDiagnosticMessage();

    FieldReference getFieldReference();

    @Override // com.android.tools.r8.diagnostic.MissingDefinitionInfo
    default boolean isMissingField() {
        return true;
    }
}
