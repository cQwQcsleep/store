package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceFieldElement extends RetraceElement<RetraceFieldResult> {
    RetraceClassElement getClassElement();

    RetracedFieldReference getField();

    @Override // com.android.tools.r8.retrace.RetraceElement
    /* synthetic */ RetraceResult getParentResult();

    RetracedSourceFile getSourceFile();

    boolean isUnknown();
}
