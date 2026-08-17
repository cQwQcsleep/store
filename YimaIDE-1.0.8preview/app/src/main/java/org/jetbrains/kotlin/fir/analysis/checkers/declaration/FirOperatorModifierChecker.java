package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.CheckResult;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.OperatorDiagnostic;
import org.jetbrains.kotlin.fir.declarations.OperatorFunctionChecks;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u0007*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorModifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "mapOperatorDiagnostic", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "modifierSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOperatorModifierChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirOperatorModifierChecker INSTANCE = new FirOperatorModifierChecker();

    private FirOperatorModifierChecker() {
        super(MppCheckerKind.Common);
    }

    private final void mapOperatorDiagnostic(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, OperatorDiagnostic operatorDiagnostic, FirFunction firFunction, KtSourceElement ktSourceElement) {
        if (operatorDiagnostic instanceof OperatorDiagnostic.DeprecatedOperatorDiagnostic) {
            OperatorDiagnostic.DeprecatedOperatorDiagnostic deprecatedOperatorDiagnostic = (OperatorDiagnostic.DeprecatedOperatorDiagnostic) operatorDiagnostic;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) (LanguageVersionUtilsKt.isEnabled(checkerContext, deprecatedOperatorDiagnostic.getFeature()) ? FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER() : FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER_WARNING()), (Object) deprecatedOperatorDiagnostic.getMessage(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (operatorDiagnostic instanceof OperatorDiagnostic.IllegalOperatorDiagnostic) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER(), (Object) ((OperatorDiagnostic.IllegalOperatorDiagnostic) operatorDiagnostic).getMessage(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (operatorDiagnostic instanceof OperatorDiagnostic.Unsupported) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(((OperatorDiagnostic.Unsupported) operatorDiagnostic).getFeature(), checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (!(operatorDiagnostic instanceof OperatorDiagnostic.ReturnTypeMismatchWithOuterClass)) {
            bu8.a();
            return;
        }
        OperatorDiagnostic.ReturnTypeMismatchWithOuterClass returnTypeMismatchWithOuterClass = (OperatorDiagnostic.ReturnTypeMismatchWithOuterClass) operatorDiagnostic;
        if (returnTypeMismatchWithOuterClass.getDueToNullability()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getNULLABLE_RETURN_TYPE_OF_OPERATOR_OF(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else if (returnTypeMismatchWithOuterClass.getDueToFlexibility()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getPOTENTIALLY_NULLABLE_RETURN_TYPE_OF_OPERATOR_OF(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH_OF_OPERATOR_OF(), (Object) returnTypeMismatchWithOuterClass.getOuter(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (firFunction.getStatus().isOperator()) {
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.OPERATOR_KEYWORD;
            ktModifierKeywordToken.getClass();
            FirModifier<?> modifier = FirKeywordUtilsKt.getModifier(firFunction, ktModifierKeywordToken);
            if (modifier == null) {
                return;
            }
            CheckResult checkResultIsOperator = OperatorFunctionChecks.INSTANCE.isOperator(firFunction, checkerContext.getSession(), checkerContext.getScopeSession());
            if (Intrinsics.areEqual(checkResultIsOperator, CheckResult.SuccessCheck.INSTANCE)) {
                return;
            }
            if (Intrinsics.areEqual(checkResultIsOperator, CheckResult.IllegalFunctionName.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER(), (Object) "illegal function name", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            if (checkResultIsOperator instanceof CheckResult.IllegalSignature) {
                mapOperatorDiagnostic(checkerContext, diagnosticReporter, ((CheckResult.IllegalSignature) checkResultIsOperator).getError(), firFunction, modifier.getSource());
            } else if (Intrinsics.areEqual(checkResultIsOperator, CheckResult.AnonymousOperatorFunction.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_OPERATOR_MODIFIER(), (Object) "anonymous function", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                bu8.a();
            }
        }
    }
}
