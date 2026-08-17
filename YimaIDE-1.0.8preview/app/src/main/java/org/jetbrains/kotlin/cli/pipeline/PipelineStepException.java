package org.jetbrains.kotlin.cli.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PipelineStepException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "definitelyCompilationError", Argument.Delimiters.none, "<init>", "(Z)V", "getDefinitelyCompilationError", "()Z", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PipelineStepException extends RuntimeException {
    private final boolean definitelyCompilationError;

    public /* synthetic */ PipelineStepException(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getDefinitelyCompilationError() {
        return this.definitelyCompilationError;
    }

    public PipelineStepException(boolean z) {
        this.definitelyCompilationError = z;
    }

    public PipelineStepException() {
        this(false, 1, null);
    }
}
