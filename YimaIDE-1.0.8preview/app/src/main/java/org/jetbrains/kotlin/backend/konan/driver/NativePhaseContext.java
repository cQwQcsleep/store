package org.jetbrains.kotlin.backend.konan.driver;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.DisposableContext;
import org.jetbrains.kotlin.backend.common.ErrorReportingContext;
import org.jetbrains.kotlin.backend.konan.NativeCompilationConfig;
import org.jetbrains.kotlin.config.LoggingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/driver/NativePhaseContext;", "Lorg/jetbrains/kotlin/config/LoggingContext;", "Lorg/jetbrains/kotlin/backend/common/ErrorReportingContext;", "Lorg/jetbrains/kotlin/backend/common/DisposableContext;", "Lorg/jetbrains/kotlin/backend/konan/driver/PerformanceManagerContext;", "config", "Lorg/jetbrains/kotlin/backend/konan/NativeCompilationConfig;", "getConfig", "()Lorg/jetbrains/kotlin/backend/konan/NativeCompilationConfig;", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface NativePhaseContext extends DisposableContext, ErrorReportingContext, PerformanceManagerContext, LoggingContext {
    NativeCompilationConfig getConfig();
}
