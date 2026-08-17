package org.jetbrains.kotlin.cli.pipeline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LoggingContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "Lorg/jetbrains/kotlin/config/LoggingContext;", "performanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "kaptMode", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;Z)V", "getPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "getKaptMode", "()Z", "inVerbosePhase", "getInVerbosePhase", "setInVerbosePhase", "(Z)V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PipelineContext implements LoggingContext {
    private boolean inVerbosePhase;
    private final boolean kaptMode;
    private final PerformanceManager performanceManager;

    public PipelineContext(PerformanceManager performanceManager, boolean z) {
        performanceManager.getClass();
        this.performanceManager = performanceManager;
        this.kaptMode = z;
    }

    @Override // org.jetbrains.kotlin.config.LoggingContext
    public boolean getInVerbosePhase() {
        return this.inVerbosePhase;
    }

    public final boolean getKaptMode() {
        return this.kaptMode;
    }

    public final PerformanceManager getPerformanceManager() {
        return this.performanceManager;
    }

    @Override // org.jetbrains.kotlin.config.LoggingContext
    public void setInVerbosePhase(boolean z) {
        this.inVerbosePhase = z;
    }
}
