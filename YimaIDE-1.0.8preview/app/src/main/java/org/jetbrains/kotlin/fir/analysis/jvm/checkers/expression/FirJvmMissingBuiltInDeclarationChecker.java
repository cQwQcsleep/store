package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmMissingBuiltInDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmMissingBuiltInDeclarationChecker extends FirExpressionChecker<FirStatement> {
    public static final FirJvmMissingBuiltInDeclarationChecker INSTANCE = new FirJvmMissingBuiltInDeclarationChecker();

    private FirJvmMissingBuiltInDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    private static final boolean check$reportIfNeeded(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, FirClassLikeSymbol<?> firClassLikeSymbol) {
        if (!Intrinsics.areEqual(firClassLikeSymbol != null ? firClassLikeSymbol.getOrigin() : null, FirDeclarationOrigin.BuiltInsFallback.INSTANCE)) {
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firStatement.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getMISSING_BUILT_IN_DECLARATION(), (Object) firClassLikeSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirResolvedNamedReference resolved;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (((Boolean) checkerContext.get$languageVersionSettings().getFlag(JvmAnalysisFlags.getSuppressMissingBuiltinsError())).booleanValue()) {
            return;
        }
        if ((firStatement instanceof FirResolvedQualifier) && check$reportIfNeeded(checkerContext, diagnosticReporter, firStatement, ((FirResolvedQualifier) firStatement).getSymbol())) {
            return;
        }
        FirReference reference = ReferenceUtilsKt.toReference(firStatement, checkerContext.getSession());
        FirBasedSymbol<?> symbol = (reference == null || (resolved = FirReferenceUtilsKt.getResolved(reference)) == null) ? null : FirReferenceUtilsKt.getSymbol(resolved);
        if (!check$reportIfNeeded(checkerContext, diagnosticReporter, firStatement, symbol != null ? ContainingClassUtilsKt.getContainingClassSymbol(symbol) : null) && (symbol instanceof FirCallableSymbol)) {
            check$reportIfNeeded(checkerContext, diagnosticReporter, firStatement, TypeUtilsKt.toRegularClassSymbol(((FirCallableSymbol) symbol).getResolvedReturnTypeRef(), checkerContext.getSession()));
        }
    }
}
