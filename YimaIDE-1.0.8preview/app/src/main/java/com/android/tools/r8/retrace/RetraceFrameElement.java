package com.android.tools.r8.retrace;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceFrameElement extends RetraceElement<RetraceFrameResult> {
    void forEach(Consumer<RetracedSingleFrame> consumer);

    void forEachRewritten(Consumer<RetracedSingleFrame> consumer);

    RetraceClassElement getClassElement();

    List<? extends RetracedMethodReference> getOuterFrames();

    @Override // com.android.tools.r8.retrace.RetraceElement
    /* synthetic */ RetraceResult getParentResult();

    RetraceStackTraceContext getRetraceStackTraceContext();

    RetracedSourceFile getSourceFile(RetracedClassMemberReference retracedClassMemberReference);

    RetracedMethodReference getTopFrame();

    boolean isUnknown();

    Stream<RetracedSingleFrame> stream();

    Stream<RetracedSingleFrame> streamRewritten(RetraceStackTraceContext retraceStackTraceContext);
}
