package com.android.tools.r8.retrace;

import com.android.tools.r8.internal.J90;
import com.android.tools.r8.internal.K90;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackTraceContext {
    static RetraceStackTraceContext empty() {
        J90 j90 = new J90();
        return new K90(j90.a, j90.b);
    }
}
