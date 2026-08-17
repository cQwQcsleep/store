package org.jetbrains.kotlin.cli.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/AnalysisResultWithHasErrors;", Argument.Delimiters.none, CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "hasErrors", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/analyzer/AnalysisResult;Z)V", "getResult", "()Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "getHasErrors", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class AnalysisResultWithHasErrors {
    private final boolean hasErrors;
    private final AnalysisResult result;

    public AnalysisResultWithHasErrors(AnalysisResult analysisResult, boolean z) {
        analysisResult.getClass();
        this.result = analysisResult;
        this.hasErrors = z;
    }

    public static /* synthetic */ AnalysisResultWithHasErrors copy$default(AnalysisResultWithHasErrors analysisResultWithHasErrors, AnalysisResult analysisResult, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            analysisResult = analysisResultWithHasErrors.result;
        }
        if ((i & 2) != 0) {
            z = analysisResultWithHasErrors.hasErrors;
        }
        return analysisResultWithHasErrors.copy(analysisResult, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AnalysisResult getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasErrors() {
        return this.hasErrors;
    }

    public final AnalysisResultWithHasErrors copy(AnalysisResult result, boolean hasErrors) {
        result.getClass();
        return new AnalysisResultWithHasErrors(result, hasErrors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnalysisResultWithHasErrors)) {
            return false;
        }
        AnalysisResultWithHasErrors analysisResultWithHasErrors = (AnalysisResultWithHasErrors) other;
        return Intrinsics.areEqual(this.result, analysisResultWithHasErrors.result) && this.hasErrors == analysisResultWithHasErrors.hasErrors;
    }

    public final boolean getHasErrors() {
        return this.hasErrors;
    }

    public final AnalysisResult getResult() {
        return this.result;
    }

    public int hashCode() {
        return (this.result.hashCode() * 31) + Boolean.hashCode(this.hasErrors);
    }

    public String toString() {
        return "AnalysisResultWithHasErrors(result=" + this.result + ", hasErrors=" + this.hasErrors + ')';
    }
}
