package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceStackTraceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O90 implements RetraceStackTraceResult {
    public final List a;
    public final RetraceStackTraceContext b;

    public O90(ArrayList arrayList, RetraceStackTraceContext retraceStackTraceContext) {
        this.a = arrayList;
        this.b = retraceStackTraceContext;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceResult
    public final void forEach(Consumer consumer) {
        this.a.forEach(consumer);
    }

    @Override // com.android.tools.r8.retrace.RetraceResultWithContext
    public final RetraceStackTraceContext getContext() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceResult
    public final List getResult() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceResult
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }
}
