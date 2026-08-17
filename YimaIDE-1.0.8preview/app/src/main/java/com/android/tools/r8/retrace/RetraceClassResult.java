package com.android.tools.r8.retrace;

import com.android.tools.r8.references.TypeReference;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceClassResult extends RetraceResult<RetraceClassElement> {
    @Override // com.android.tools.r8.retrace.RetraceResult
    /* bridge */ /* synthetic */ default Stream flatMap(Function function) {
        return super.flatMap(function);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    /* synthetic */ boolean isEmpty();

    RetraceFieldResult lookupField(String str);

    RetraceFieldResult lookupField(String str, TypeReference typeReference);

    RetraceFrameResult lookupFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, String str);

    RetraceFrameResult lookupFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, String str, List<TypeReference> list, TypeReference typeReference);

    RetraceMethodResult lookupMethod(String str);

    RetraceMethodResult lookupMethod(String str, List<TypeReference> list, TypeReference typeReference);

    RetraceThrownExceptionResult lookupThrownException(RetraceStackTraceContext retraceStackTraceContext);
}
