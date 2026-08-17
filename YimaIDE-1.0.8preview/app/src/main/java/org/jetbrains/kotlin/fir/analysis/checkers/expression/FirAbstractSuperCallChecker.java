package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAbstractSuperCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAbstractSuperCallChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirAbstractSuperCallChecker INSTANCE = new FirAbstractSuperCallChecker();

    private FirAbstractSuperCallChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirCallableSymbol<?> resolvedCallableSymbol;
        Object classKind;
        FirClassLikeSymbol<?> symbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if (FirHelpersKt.explicitReceiverIsNotSuperReference(firQualifiedAccessExpression) || (resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression)) == 0) {
            return;
        }
        if (resolvedCallableSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), FirErrors.INSTANCE.getABSTRACT_SUPER_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (resolvedCallableSymbol instanceof FirIntersectionCallableSymbol) {
            Iterator<T> it = ((FirIntersectionCallableSymbol) resolvedCallableSymbol).getIntersections().iterator();
            while (true) {
                classKind = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) next);
                if (coneClassLikeLookupTagContainingClassLookupTag != null && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) != null) {
                    classKind = FirHelpersKt.getClassKind(symbol);
                }
                if (classKind != ClassKind.INTERFACE) {
                    classKind = next;
                    break;
                }
            }
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) classKind;
            if (firCallableSymbol == null || firCallableSymbol.getResolvedStatus().getModality() != Modality.ABSTRACT) {
                return;
            }
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidSuperDelegationToAbstractFakeOverride)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), FirErrors.INSTANCE.getABSTRACT_SUPER_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), FirErrors.INSTANCE.getABSTRACT_SUPER_CALL_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
