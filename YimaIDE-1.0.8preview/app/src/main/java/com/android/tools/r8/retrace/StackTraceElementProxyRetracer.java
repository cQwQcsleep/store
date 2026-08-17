package com.android.tools.r8.retrace;

import com.android.tools.r8.internal.C2784ud0;
import com.android.tools.r8.retrace.StackTraceElementProxy;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface StackTraceElementProxyRetracer<T, ST extends StackTraceElementProxy<T, ST>> {
    static <T, ST extends StackTraceElementProxy<T, ST>> StackTraceElementProxyRetracer<T, ST> createDefault(Retracer retracer) {
        return new C2784ud0(retracer);
    }

    RetraceStackTraceElementProxyResult<T, ST> retrace(ST st, RetraceStackTraceContext retraceStackTraceContext);
}
