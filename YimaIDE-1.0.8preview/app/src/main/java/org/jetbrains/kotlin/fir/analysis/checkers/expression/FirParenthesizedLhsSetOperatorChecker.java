package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.resolve.source.SourceElementUtilsKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u000f*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0013\u001a\u00020\u000f*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirParenthesizedLhsSetOperatorChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "isArrayAccess", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;)Z", "isAugmentedAssign", "isIncrementOrDecrement", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirParenthesizedLhsSetOperatorChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirParenthesizedLhsSetOperatorChecker INSTANCE = new FirParenthesizedLhsSetOperatorChecker();

    private FirParenthesizedLhsSetOperatorChecker() {
        super(MppCheckerKind.Platform);
    }

    private final boolean isArrayAccess(FirNamedReference firNamedReference) {
        KtSourceElement source = firNamedReference.getSource();
        return Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE);
    }

    private final boolean isAugmentedAssign(FirNamedReference firNamedReference) {
        KtSourceElement source = firNamedReference.getSource();
        return (source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign;
    }

    private final boolean isIncrementOrDecrement(FirNamedReference firNamedReference) {
        KtSourceElement source = firNamedReference.getSource();
        return (source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        KtSourceElement source = firFunctionCall.getSource();
        if (source == null || !Intrinsics.areEqual(calleeReference.getName(), OperatorNameConventions.SET) || (calleeReference instanceof FirErrorNamedReference)) {
            return;
        }
        if ((CollectionsKt.contains(PsiUtilsKt.getUNWRAPPABLE_TOKEN_TYPES(), source.getElementType()) && isArrayAccess(calleeReference)) || ((SourceElementUtilsKt.hasUnwrappableAsAssignmentLhs(source) && isAugmentedAssign(calleeReference)) || (SourceElementUtilsKt.hasUnwrappableAsAssignmentLhs(source) && isIncrementOrDecrement(calleeReference)))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getWRAPPED_LHS_IN_ASSIGNMENT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}
