package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackTraceElementProxyResult<T, ST extends StackTraceElementProxy<T, ST>> {
    RetraceStackTraceContext getResultContext();

    Stream<? extends RetraceStackTraceElementProxy<T, ST>> stream();
}
