package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J/\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\u0011H\u0002J=\u0010\u0019\u001a\u00020\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnSyntaxAndLabelChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;)V", "returnNotAllowedFactoryOrNull", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "targetSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "returnNotAllowedInExpressionBodyFactoryOrNull", "edgeCase", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;Z)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "expressionBodyOrNull", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirSingleExpressionBlock;", "checkBuiltInSuspend", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReturnSyntaxAndLabelChecker extends FirExpressionChecker<FirReturnExpression> {
    public static final FirReturnSyntaxAndLabelChecker INSTANCE = new FirReturnSyntaxAndLabelChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DiagnosticKind.values().length];
            try {
                iArr[DiagnosticKind.NotAFunctionLabel.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DiagnosticKind.UnresolvedLabel.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirReturnSyntaxAndLabelChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkBuiltInSuspend(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionSymbol<? extends FirFunction> firFunctionSymbol, KtSourceElement ktSourceElement) {
        KtSourceElement source;
        if (firFunctionSymbol instanceof FirAnonymousFunctionSymbol) {
            FirLabel label = ((FirAnonymousFunctionSymbol) firFunctionSymbol).getLabel();
            Object obj = null;
            if (((label == null || (source = label.getSource()) == null) ? null : source.getKind()) instanceof KtRealSourceElementKind) {
                return;
            }
            for (Object obj2 : CollectionsKt.asReversed(checkerContext.getCallsOrAssignments())) {
                FirStatement firStatement = (FirStatement) obj2;
                if (firStatement instanceof FirFunctionCall) {
                    FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(((FirFunctionCall) firStatement).getCalleeReference(), false, 1, null);
                    if (Intrinsics.areEqual(resolvedNamedFunctionSymbol$default != null ? resolvedNamedFunctionSymbol$default.getCallableId() : null, StandardClassIds.Callables.INSTANCE.getSuspend())) {
                        obj = obj2;
                        break;
                    }
                }
            }
            FirStatement firStatement2 = (FirStatement) obj;
            if (firStatement2 instanceof FirFunctionCall) {
                List<FirExpression> arguments = ((FirCall) firStatement2).getArgumentList().getArguments();
                if ((arguments instanceof Collection) && arguments.isEmpty()) {
                    return;
                }
                for (FirExpression firExpression : arguments) {
                    if ((firExpression instanceof FirAnonymousFunctionExpression) && Intrinsics.areEqual(((FirAnonymousFunctionExpression) firExpression).getAnonymousFunction().getSymbol(), firFunctionSymbol)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getRETURN_FOR_BUILT_IN_SUSPEND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        return;
                    } else {
                        checkerContext = checkerContext;
                        diagnosticReporter = diagnosticReporter;
                        ktSourceElement = ktSourceElement;
                    }
                }
            }
        }
    }

    private final FirSingleExpressionBlock expressionBodyOrNull(FirFunctionSymbol<?> firFunctionSymbol) {
        FirBlock body = ((FirFunction) firFunctionSymbol.getFir()).getBody();
        if (body instanceof FirSingleExpressionBlock) {
            return (FirSingleExpressionBlock) body;
        }
        return null;
    }

    private final KtDiagnosticFactory0 returnNotAllowedFactoryOrNull(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        boolean z = false;
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (firBasedSymbol instanceof FirClassSymbol) {
                return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
            }
            boolean z2 = firBasedSymbol instanceof FirFunctionSymbol;
            if (z2 && Intrinsics.areEqual(firBasedSymbol, firFunctionSymbol)) {
                return returnNotAllowedInExpressionBodyFactoryOrNull(checkerContext, firFunctionSymbol, z);
            }
            if (!(firBasedSymbol instanceof FirAnonymousFunctionSymbol)) {
                if (z2) {
                    return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
                }
                if (!(firBasedSymbol instanceof FirPropertySymbol)) {
                    if (firBasedSymbol instanceof FirValueParameterSymbol) {
                        return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
                    }
                } else if (firBasedSymbol instanceof FirRegularPropertySymbol) {
                    return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
                }
            } else if (!((FirAnonymousFunctionSymbol) firBasedSymbol).getInlineStatus().getReturnAllowed()) {
                return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
            }
            z = true;
        }
        return null;
    }

    private final KtDiagnosticFactory0 returnNotAllowedInExpressionBodyFactoryOrNull(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol, boolean z) {
        if (expressionBodyOrNull(firFunctionSymbol) == null) {
            return null;
        }
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowReturnInExpressionBodyWithExplicitType);
        if ((zIsEnabled || z) && DeclarationUtilsKt.getHasExplicitReturnType(firFunctionSymbol)) {
            return null;
        }
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases) && zIsEnabled) {
            return FirErrors.INSTANCE.getRETURN_IN_FUNCTION_WITH_EXPRESSION_BODY_AND_IMPLICIT_TYPE();
        }
        if (zIsEnabled && z) {
            return FirErrors.INSTANCE.getRETURN_IN_FUNCTION_WITH_EXPRESSION_BODY_WARNING();
        }
        if (zIsEnabled && !z) {
            return FirErrors.INSTANCE.getRETURN_IN_FUNCTION_WITH_EXPRESSION_BODY_AND_IMPLICIT_TYPE();
        }
        if (z) {
            return null;
        }
        return FirErrors.INSTANCE.getRETURN_IN_FUNCTION_WITH_EXPRESSION_BODY();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirReturnExpression firReturnExpression) {
        KtDiagnosticFactory0 not_a_function_label;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firReturnExpression.getClass();
        KtSourceElement source = firReturnExpression.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitReturn) {
            return;
        }
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DelegatedPropertyAccessor) {
            return;
        }
        FirFunction firFunction = (FirFunction) firReturnExpression.getTarget().getLabeledElement();
        FirFunctionSymbol<FirFunction> symbol = firFunction.getSymbol();
        FirErrorFunction firErrorFunction = firFunction instanceof FirErrorFunction ? (FirErrorFunction) firFunction : null;
        ConeDiagnostic diagnostic = firErrorFunction != null ? firErrorFunction.getDiagnostic() : null;
        ConeSimpleDiagnostic coneSimpleDiagnostic = diagnostic instanceof ConeSimpleDiagnostic ? (ConeSimpleDiagnostic) diagnostic : null;
        DiagnosticKind kind = coneSimpleDiagnostic != null ? coneSimpleDiagnostic.getKind() : null;
        int i = kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i != 1) {
            not_a_function_label = i != 2 ? returnNotAllowedFactoryOrNull(checkerContext, symbol) : FirErrors.INSTANCE.getUNRESOLVED_LABEL();
        } else {
            not_a_function_label = FirErrors.INSTANCE.getNOT_A_FUNCTION_LABEL();
        }
        KtDiagnosticFactory0 ktDiagnosticFactory0 = not_a_function_label;
        if (ktDiagnosticFactory0 != null) {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
        }
        checkBuiltInSuspend(checkerContext2, diagnosticReporter2, symbol, source);
        if (LanguageVersionUtilsKt.isEnabled(checkerContext2, LanguageFeature.AllowReturnInExpressionBodyWithExplicitType)) {
            FirSingleExpressionBlock firSingleExpressionBlockExpressionBodyOrNull = expressionBodyOrNull(symbol);
            FirStatement statement = firSingleExpressionBlockExpressionBodyOrNull != null ? firSingleExpressionBlockExpressionBodyOrNull.getStatement() : null;
            if ((statement instanceof FirReturnExpression) && Intrinsics.areEqual(((FirReturnExpression) statement).getResult(), firReturnExpression) && DeclarationUtilsKt.getHasExplicitReturnType(symbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getREDUNDANT_RETURN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
