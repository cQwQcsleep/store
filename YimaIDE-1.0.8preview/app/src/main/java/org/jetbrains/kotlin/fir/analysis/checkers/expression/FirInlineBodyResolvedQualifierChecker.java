package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirInlineExposedLessVisibleTypeChecker;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvedQualifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineBodyResolvedQualifierChecker extends FirExpressionChecker<FirResolvedQualifier> {
    public static final FirInlineBodyResolvedQualifierChecker INSTANCE = new FirInlineBodyResolvedQualifierChecker();

    private FirInlineBodyResolvedQualifierChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        FirClassLikeSymbol<?> firClassLikeSymbolResolvedSymbolOrCompanionSymbol;
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedQualifier.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext == null || FirHelpersKt.isExplicitParentOfResolvedQualifier(checkerContext, firResolvedQualifier) || (firClassLikeSymbolResolvedSymbolOrCompanionSymbol = FirHelpersKt.resolvedSymbolOrCompanionSymbol(checkerContext, firResolvedQualifier)) == null || (source = firResolvedQualifier.getSource()) == null) {
            return;
        }
        if (firClassLikeSymbolResolvedSymbolOrCompanionSymbol.getRawStatus().isCompanion()) {
            inlineFunctionBodyContext.checkAccessedDeclaration$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, source, firResolvedQualifier, firClassLikeSymbolResolvedSymbolOrCompanionSymbol);
        }
        if (FirHelpersKt.isDispatchReceiver(checkerContext, firResolvedQualifier)) {
            return;
        }
        FirInlineExposedLessVisibleTypeChecker.INSTANCE.check$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, ScopeUtilsKt.defaultType(firClassLikeSymbolResolvedSymbolOrCompanionSymbol), source, inlineFunctionBodyContext);
    }
}
