package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceThrownExceptionElement extends RetraceElement<RetraceThrownExceptionResult> {
    RetraceStackTraceContext getContext();

    @Override // com.android.tools.r8.retrace.RetraceElement
    /* synthetic */ RetraceResult getParentResult();

    RetracedClassReference getRetracedClass();

    RetracedSourceFile getSourceFile();
}
