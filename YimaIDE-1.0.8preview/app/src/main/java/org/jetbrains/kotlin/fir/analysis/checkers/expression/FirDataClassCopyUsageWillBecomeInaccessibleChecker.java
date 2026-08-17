package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.PrivateToThisUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDataClassCopyUsageWillBecomeInaccessibleChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDataClassCopyUsageWillBecomeInaccessibleChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirDataClassCopyUsageWillBecomeInaccessibleChecker INSTANCE = new FirDataClassCopyUsageWillBecomeInaccessibleChecker();

    private FirDataClassCopyUsageWillBecomeInaccessibleChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if ((firQualifiedAccessExpression instanceof FirFunctionCall) || (firQualifiedAccessExpression instanceof FirCallableReferenceAccess)) {
            FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(firQualifiedAccessExpression.getCalleeReference());
            FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
            if (firCallableSymbol == null || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null || !PrivateToThisUtilsKt.isDataClassCopy(firCallableSymbol, regularClassSymbol, checkerContext.getSession()) || (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(regularClassSymbol, checkerContext.getSession())) == null) {
                return;
            }
            FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
            FirSession session = checkerContext.getSession();
            FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
            if (containingFileSymbol != null && FirVisibilityCheckerKt.isVisible$default(visibilityChecker, firCallableSymbol, session, containingFileSymbol, checkerContext.getContainingDeclarations(), null, false, 32, null)) {
                FirVisibilityChecker visibilityChecker2 = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
                FirSession session2 = checkerContext.getSession();
                FirFileSymbol containingFileSymbol2 = checkerContext.getContainingFileSymbol();
                if (containingFileSymbol2 == null || FirVisibilityCheckerKt.isVisible$default(visibilityChecker2, firConstructorSymbolPrimaryConstructorIfAny, session2, containingFileSymbol2, checkerContext.getContainingDeclarations(), null, false, 32, null)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), FirErrors.INSTANCE.getDATA_CLASS_INVISIBLE_COPY_USAGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
