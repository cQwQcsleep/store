package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.load.java.JvmAbi;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005H\u0014R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ/\u0010\t\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u00020\u0006R\u00020\u000bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmReflectionApiCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractFirReflectionApiCallChecker;", "<init>", "()V", "isWholeReflectionApiAvailable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmReflectionApiCallChecker extends AbstractFirReflectionApiCallChecker {
    public static final FirJvmReflectionApiCallChecker INSTANCE = new FirJvmReflectionApiCallChecker();

    private FirJvmReflectionApiCallChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public boolean isWholeReflectionApiAvailable(CheckerContext checkerContext) {
        checkerContext.getClass();
        return FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId(JvmAbi.INSTANCE.getREFLECTION_FACTORY_IMPL()) != null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public void report(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getNO_REFLECTION_IN_CLASS_PATH(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
