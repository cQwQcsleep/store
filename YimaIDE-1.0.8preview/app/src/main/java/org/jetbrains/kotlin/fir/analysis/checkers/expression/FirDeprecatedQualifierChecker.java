package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDeprecated;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDeprecatedQualifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeprecatedQualifierChecker extends FirExpressionChecker<FirResolvedQualifier> {
    public static final FirDeprecatedQualifierChecker INSTANCE = new FirDeprecatedQualifierChecker();

    private FirDeprecatedQualifierChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        FirClassLikeSymbol<?> symbol;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedQualifier.getClass();
        List<ConeDiagnostic> nonFatalDiagnostics = firResolvedQualifier.getNonFatalDiagnostics();
        ArrayList<ConeDeprecated> arrayList = new ArrayList();
        for (Object obj : nonFatalDiagnostics) {
            if (obj instanceof ConeDeprecated) {
                arrayList.add(obj);
            }
        }
        for (ConeDeprecated coneDeprecated : arrayList) {
            FirDeprecationChecker.INSTANCE.reportApiStatus$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, coneDeprecated.getSource(), coneDeprecated.getSymbol(), false, coneDeprecated.getDeprecationInfo());
        }
        if (!firResolvedQualifier.getResolvedToCompanionObject() || (symbol = firResolvedQualifier.getSymbol()) == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol)) == null || (resolvedCompanionObjectSymbol = firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol()) == null) {
            return;
        }
        FirDeprecationChecker.reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers$default(FirDeprecationChecker.INSTANCE, checkerContext, diagnosticReporter, firResolvedQualifier.getSource(), resolvedCompanionObjectSymbol, null, 16, null);
    }
}
