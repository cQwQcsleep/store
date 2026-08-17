package com.android.tools.r8.retrace;

import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceFieldResult extends RetraceResult<RetraceFieldElement> {
    @Override // com.android.tools.r8.retrace.RetraceResult
    /* bridge */ /* synthetic */ default Stream flatMap(Function function) {
        return super.flatMap(function);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    /* synthetic */ boolean isEmpty();
}
