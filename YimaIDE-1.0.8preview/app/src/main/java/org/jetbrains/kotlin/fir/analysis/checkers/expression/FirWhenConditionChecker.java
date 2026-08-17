package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenConditionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)V", "checkDuplicatedLabels", "isArgumentWhenSubject", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenConditionChecker extends FirExpressionChecker<FirWhenExpression> {
    public static final FirWhenConditionChecker INSTANCE = new FirWhenConditionChecker();

    private FirWhenConditionChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkDuplicatedLabels(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        Object value;
        FirClassLikeSymbol<?> symbol;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator<FirWhenBranch> it = firWhenExpression.getBranches().iterator();
        while (it.hasNext()) {
            FirAnnotationContainer condition = it.next().getCondition();
            if (condition instanceof FirEqualityOperatorCall) {
                FirCall firCall = (FirCall) condition;
                if (isArgumentWhenSubject(firCall)) {
                    FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(firCall.getArgumentList().getArguments().get(1));
                    if (firExpressionUnwrapSmartcastExpression instanceof FirLiteralExpression) {
                        value = ((FirLiteralExpression) firExpressionUnwrapSmartcastExpression).getValue();
                    } else if (firExpressionUnwrapSmartcastExpression instanceof FirQualifiedAccessExpression) {
                        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(((FirQualifiedAccessExpression) firExpressionUnwrapSmartcastExpression).getCalleeReference(), false, 1, null);
                        value = resolvedCallableSymbol$default instanceof FirEnumEntrySymbol ? (FirEnumEntrySymbol) resolvedCallableSymbol$default : null;
                        if (value == null) {
                        }
                    } else if ((firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) && (symbol = ((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression).getSymbol()) != null && FirHelpersKt.getClassKind(symbol) == ClassKind.OBJECT) {
                        value = symbol.getClassId();
                    }
                    if (!hashSet2.add(value)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirEqualityOperatorCall) condition).getSource(), FirErrors.INSTANCE.getDUPLICATE_BRANCH_CONDITION_IN_WHEN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
            if ((condition instanceof FirTypeOperatorCall) && isArgumentWhenSubject((FirCall) condition)) {
                FirTypeOperatorCall firTypeOperatorCall = (FirTypeOperatorCall) condition;
                if (!hashSet.add(TuplesKt.to(FirTypeUtilsKt.getConeType(firTypeOperatorCall.getConversionTypeRef()), firTypeOperatorCall.getOperation()))) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getConversionTypeRef().getSource(), FirErrors.INSTANCE.getDUPLICATE_BRANCH_CONDITION_IN_WHEN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    private final boolean isArgumentWhenSubject(FirCall firCall) {
        return FirExpressionUtilKt.unwrapSmartcastExpression((FirExpression) CollectionsKt.first(firCall.getArgumentList().getArguments())) instanceof FirWhenSubjectExpression;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        Iterator<FirWhenBranch> it = firWhenExpression.getBranches().iterator();
        while (it.hasNext()) {
            FirExpression condition = it.next().getCondition();
            if (!(condition instanceof FirElseIfTrueCondition)) {
                FirHelpersKt.checkCondition(checkerContext, diagnosticReporter, condition);
            }
        }
        if (firWhenExpression.getSubjectVariable() != null) {
            checkDuplicatedLabels(checkerContext, diagnosticReporter, firWhenExpression);
        }
    }
}
