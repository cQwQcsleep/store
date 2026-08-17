package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackFrameResultWithContext;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I90 implements RetraceStackFrameResultWithContext {
    public final RetraceStackTraceContext a;
    public final List b;

    public I90(List list, RetraceStackTraceContext retraceStackTraceContext) {
        this.b = list;
        this.a = retraceStackTraceContext;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameResult
    public final void forEach(Consumer consumer) {
        this.b.forEach(consumer);
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameResult
    public final Object get(int i) {
        return this.b.get(i);
    }

    @Override // com.android.tools.r8.retrace.RetraceResultWithContext
    public final RetraceStackTraceContext getContext() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameResult
    public final List getResult() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameResultWithContext, com.android.tools.r8.retrace.RetraceStackFrameResult
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameResultWithContext, com.android.tools.r8.retrace.RetraceStackFrameResult
    public final int size() {
        return this.b.size();
    }
}
