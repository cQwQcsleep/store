package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxyResult;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N90 implements RetraceStackTraceElementProxyResult {
    public final Stream a;
    public final Supplier b;

    public N90(Stream stream, Supplier supplier) {
        this.a = stream;
        this.b = supplier;
    }

    public final M90 a() {
        M90 m90 = new M90();
        m90.a = this.b;
        return m90;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxyResult
    public final RetraceStackTraceContext getResultContext() {
        return (RetraceStackTraceContext) this.b.get();
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxyResult
    public final Stream stream() {
        return this.a;
    }
}
