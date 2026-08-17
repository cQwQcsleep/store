package com.android.tools.r8.retrace;

import com.android.tools.r8.internal.C3210zd0;
import com.android.tools.r8.internal.Nd0;
import com.android.tools.r8.retrace.StackTraceElementProxy;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface StackTraceLineParser<T, ST extends StackTraceElementProxy<T, ST>> {
    static StackTraceLineParser<String, C3210zd0> createRegularExpressionParser(String str) {
        return new Nd0(str);
    }

    ST parse(T t);
}
