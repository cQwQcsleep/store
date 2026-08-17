package com.android.tools.r8.retrace;

import java.util.List;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackFrameAmbiguousResultWithContext<T> extends RetraceStackFrameAmbiguousResult<T>, RetraceResultWithContext {
    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    /* synthetic */ void forEachWithIndex(BiConsumer biConsumer);

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    /* synthetic */ List getAmbiguousResult();
}
