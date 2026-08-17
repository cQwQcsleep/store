package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceMethodElement extends RetraceElement<RetraceMethodResult> {
    RetraceClassElement getClassElement();

    @Override // com.android.tools.r8.retrace.RetraceElement
    /* synthetic */ RetraceResult getParentResult();

    RetracedMethodReference getRetracedMethod();

    RetracedSourceFile getSourceFile();

    boolean isUnknown();
}
