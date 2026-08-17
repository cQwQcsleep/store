package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC2835v90;
import com.android.tools.r8.retrace.MappingPartitionFromKeySupplier;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceAsync<T, ST extends StackTraceElementProxy<T, ST>> extends AbstractC2835v90 {
    private final MappingSupplierAsync d;
    private final DiagnosticsHandler e;

    public RetraceAsync(StackTraceLineParser stackTraceLineParser, MappingSupplierAsync mappingSupplierAsync, DiagnosticsHandler diagnosticsHandler, boolean z) {
        super(stackTraceLineParser, mappingSupplierAsync, diagnosticsHandler, z);
        this.d = mappingSupplierAsync;
        this.e = diagnosticsHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ RetraceStackTraceResult a(List list, RetraceStackTraceContext retraceStackTraceContext, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        return retraceStackTraceParsedWithRetracer(this.d.createRetracer(this.e, mappingPartitionFromKeySupplier), list, retraceStackTraceContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ RetraceStackFrameResultWithContext b(StackTraceElementProxy stackTraceElementProxy, RetraceStackTraceContext retraceStackTraceContext, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        return retraceLineWithRetracer(this.d.createRetracer(this.e, mappingPartitionFromKeySupplier), stackTraceElementProxy, retraceStackTraceContext);
    }

    public static <T, ST extends StackTraceElementProxy<T, ST>> Builder<T, ST> builder() {
        return new Builder<>();
    }

    public RetraceAsyncResult<RetraceStackFrameAmbiguousResultWithContext<T>> retraceFrame(T t, final RetraceStackTraceContext retraceStackTraceContext) {
        final StackTraceElementProxy<Object, Object> stackTraceElementProxy = parse(t);
        registerUses(stackTraceElementProxy);
        return new RetraceAsyncResult() { // from class: gjc
            @Override // com.android.tools.r8.retrace.RetraceAsyncResult
            public final Object getResult(MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
                return this.a.a(stackTraceElementProxy, retraceStackTraceContext, mappingPartitionFromKeySupplier);
            }
        };
    }

    public RetraceAsyncResult<RetraceStackFrameResultWithContext<T>> retraceLine(T t, final RetraceStackTraceContext retraceStackTraceContext) {
        final StackTraceElementProxy<Object, Object> stackTraceElementProxy = parse(t);
        registerUses(stackTraceElementProxy);
        return new RetraceAsyncResult() { // from class: hjc
            @Override // com.android.tools.r8.retrace.RetraceAsyncResult
            public final Object getResult(MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
                return this.a.b(stackTraceElementProxy, retraceStackTraceContext, mappingPartitionFromKeySupplier);
            }
        };
    }

    public RetraceAsyncResult<RetraceStackTraceResult<T>> retraceStackTrace(List<T> list, RetraceStackTraceContext retraceStackTraceContext) {
        return retraceStackTraceParsed(parse((List<Object>) list), retraceStackTraceContext);
    }

    public RetraceAsyncResult<RetraceStackTraceResult<T>> retraceStackTraceParsed(final List<ST> list, final RetraceStackTraceContext retraceStackTraceContext) {
        registerUses(list);
        return new RetraceAsyncResult() { // from class: fjc
            @Override // com.android.tools.r8.retrace.RetraceAsyncResult
            public final Object getResult(MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
                return this.a.a(list, retraceStackTraceContext, mappingPartitionFromKeySupplier);
            }
        };
    }

    public static class Builder<T, ST extends StackTraceElementProxy<T, ST>> extends RetraceBuilderBase<Builder<T, ST>, T, ST> {
        private MappingSupplierAsync a;

        public RetraceAsync<T, ST> build() {
            return new RetraceAsync<>(this.stackTraceLineParser, this.a, this.diagnosticsHandler, this.isVerbose);
        }

        public Builder<T, ST> setMappingSupplier(MappingSupplierAsync<?> mappingSupplierAsync) {
            this.a = mappingSupplierAsync;
            return self();
        }

        @Override // com.android.tools.r8.retrace.RetraceBuilderBase
        public Builder<T, ST> self() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ RetraceStackFrameAmbiguousResultWithContext a(StackTraceElementProxy stackTraceElementProxy, RetraceStackTraceContext retraceStackTraceContext, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        return retraceFrameWithRetracer(this.d.createRetracer(this.e, mappingPartitionFromKeySupplier), stackTraceElementProxy, retraceStackTraceContext);
    }
}
