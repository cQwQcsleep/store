package org.jetbrains.kotlin.analyzer;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.TargetEnvironment;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AbstractAnalyzerWithCompilerReport;", "", "targetEnvironment", "Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "getTargetEnvironment", "()Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "analysisResult", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "getAnalysisResult", "()Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "analyzeAndReport", "", "files", "", "Lorg/jetbrains/kotlin/psi/KtFile;", "analyze", "Lkotlin/Function0;", "hasErrors", "", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface AbstractAnalyzerWithCompilerReport {
    void analyzeAndReport(Collection<? extends KtFile> files, Function0<? extends AnalysisResult> analyze);

    AnalysisResult getAnalysisResult();

    TargetEnvironment getTargetEnvironment();

    boolean hasErrors();
}
