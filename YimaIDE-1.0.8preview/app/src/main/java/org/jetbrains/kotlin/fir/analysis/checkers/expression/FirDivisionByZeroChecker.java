package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
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
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDivisionByZeroChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "defaultPackageName", "Lorg/jetbrains/kotlin/name/FqName;", "defaultDivName", "Lorg/jetbrains/kotlin/name/Name;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDivisionByZeroChecker extends FirExpressionChecker<FirFunctionCall> {
    private static final Name defaultDivName;
    public static final FirDivisionByZeroChecker INSTANCE = new FirDivisionByZeroChecker();
    private static final FqName defaultPackageName = new FqName("kotlin");

    static {
        Name nameIdentifier = Name.identifier("div");
        nameIdentifier.getClass();
        defaultDivName = nameIdentifier;
    }

    private FirDivisionByZeroChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        Object objSingleOrNull = CollectionsKt.singleOrNull(firFunctionCall.getArgumentList().getArguments());
        FirLiteralExpression firLiteralExpression = objSingleOrNull instanceof FirLiteralExpression ? (FirLiteralExpression) objSingleOrNull : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        if (value != null) {
            if (Intrinsics.areEqual(value, (Object) 0L) || Intrinsics.areEqual(value, Float.valueOf(0.0f)) || Intrinsics.areEqual(value, Double.valueOf(0.0d))) {
                FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
                CallableId callableId = resolvedNamedFunctionSymbol$default != null ? resolvedNamedFunctionSymbol$default.getCallableId() : null;
                if (callableId != null && Intrinsics.areEqual(callableId.getPackageName(), defaultPackageName) && Intrinsics.areEqual(callableId.getCallableName(), defaultDivName)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getDIVISION_BY_ZERO(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }
}
