package org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.expressions.FirConstChecksKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u00020\u000eR\u00020\u0010j\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirJsCodeConstantArgumentChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "jsCodeCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsCodeConstantArgumentChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJsCodeConstantArgumentChecker INSTANCE = new FirJsCodeConstantArgumentChecker();
    private static final CallableId jsCodeCallableId = WebCommonStandardClassIds.Callables.Js;

    private FirJsCodeConstantArgumentChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (Intrinsics.areEqual(resolvedCallableSymbol$default != null ? resolvedCallableSymbol$default.getCallableId() : null, jsCodeCallableId)) {
            final FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(firFunctionCall.getArgumentList().getArguments());
            if (firExpression != null && ConeBuiltinTypeUtilsKt.isString(FirTypeUtilsKt.getResolvedType(firExpression))) {
                firExpression.accept(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirJsCodeConstantArgumentChecker.check.1
                    private FirElement lastReportedElement;

                    public final FirElement getLastReportedElement() {
                        return this.lastReportedElement;
                    }

                    public final void setLastReportedElement(FirElement firElement) {
                        this.lastReportedElement = firElement;
                    }

                    public void visitElement(FirElement element) {
                        element.getClass();
                        FirElement firElement = this.lastReportedElement;
                        element.acceptChildren(this);
                        if (Intrinsics.areEqual(firElement, this.lastReportedElement)) {
                            if (FirConstChecksKt.canBeEvaluatedAtCompileTime(element instanceof FirExpression ? (FirExpression) element : null, checkerContext.getSession(), true, true)) {
                                return;
                            }
                            this.lastReportedElement = element;
                            KtSourceElement source2 = element.getSource();
                            if (source2 == null) {
                                source2 = firExpression.getSource();
                            }
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, FirWebCommonErrors.INSTANCE.getJSCODE_ARGUMENT_NON_CONST_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }

                    public void visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression) {
                        propertyAccessExpression.getClass();
                        FirCallableSymbol resolvedCallableSymbol$default2 = FirReferenceUtilsKt.toResolvedCallableSymbol$default(propertyAccessExpression.getCalleeReference(), false, 1, null);
                        if (resolvedCallableSymbol$default2 == null || !resolvedCallableSymbol$default2.getRawStatus().isConst()) {
                            super.visitPropertyAccessExpression(propertyAccessExpression);
                        }
                    }
                });
                return;
            }
            if (firExpression == null || (source = firExpression.getSource()) == null) {
                source = firFunctionCall.getSource();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirWebCommonErrors.INSTANCE.getJSCODE_ARGUMENT_NON_CONST_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
