package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResultWithContext;
import com.android.tools.r8.retrace.RetraceStackFrameResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class H90 implements RetraceStackFrameAmbiguousResultWithContext {
    public final RetraceStackTraceContext a;
    public final List b;

    public H90(List list, RetraceStackTraceContext retraceStackTraceContext) {
        this.b = list;
        this.a = retraceStackTraceContext;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final void forEach(Consumer consumer) {
        this.b.forEach(consumer);
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResultWithContext, com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final void forEachWithIndex(BiConsumer biConsumer) {
        for (int i = 0; i < this.b.size(); i++) {
            biConsumer.accept((RetraceStackFrameResult) this.b.get(i), Integer.valueOf(i));
        }
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final RetraceStackFrameResult get(int i) {
        return (RetraceStackFrameResult) this.b.get(i);
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResultWithContext, com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final List getAmbiguousResult() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceResultWithContext
    public final RetraceStackTraceContext getContext() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final boolean isAmbiguous() {
        return this.b.size() > 1;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResult
    public final int size() {
        return this.b.size();
    }
}
