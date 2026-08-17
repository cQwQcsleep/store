package com.android.tools.r8.retrace;

import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackTraceResult<T> extends RetraceResultWithContext {
    void forEach(Consumer<RetraceStackFrameAmbiguousResult<T>> consumer);

    List<RetraceStackFrameAmbiguousResult<T>> getResult();

    boolean isEmpty();
}
