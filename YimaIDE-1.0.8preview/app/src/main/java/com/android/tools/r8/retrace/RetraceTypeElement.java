package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceTypeElement extends RetraceElement<RetraceTypeResult> {
    @Override // com.android.tools.r8.retrace.RetraceElement
    /* synthetic */ RetraceResult getParentResult();

    RetracedTypeReference getType();
}
