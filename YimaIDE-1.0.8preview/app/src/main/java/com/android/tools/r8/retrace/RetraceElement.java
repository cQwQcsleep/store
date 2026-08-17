package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.RetraceResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceElement<R extends RetraceResult<?>> {
    R getParentResult();

    boolean isCompilerSynthesized();
}
