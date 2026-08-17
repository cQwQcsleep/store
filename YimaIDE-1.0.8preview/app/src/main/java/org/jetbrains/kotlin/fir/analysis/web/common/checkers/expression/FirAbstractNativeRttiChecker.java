package org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.FirAbstractWebCheckerUtils;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0011H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0014H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractNativeRttiChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "webCheckerUtils", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/FirAbstractWebCheckerUtils;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/FirAbstractWebCheckerUtils;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "checkGetClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;)V", "checkTypeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)V", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractNativeRttiChecker extends FirExpressionChecker<FirStatement> {
    private final FirAbstractWebCheckerUtils webCheckerUtils;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.AS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.SAFE_AS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.IS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractNativeRttiChecker(FirAbstractWebCheckerUtils firAbstractWebCheckerUtils) {
        super(MppCheckerKind.Common);
        firAbstractWebCheckerUtils.getClass();
        this.webCheckerUtils = firAbstractWebCheckerUtils;
    }

    private final void checkGetClassCall(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirGetClassCall firGetClassCall) {
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, FirTypeUtilsKt.getResolvedType(firGetClassCall.getArgument()));
        if (regularClassSymbol != null && (CollectionsKt.firstOrNull(firGetClassCall.getArgumentList().getArguments()) instanceof FirResolvedQualifier) && this.webCheckerUtils.isNativeOrExternalInterface(regularClassSymbol, checkerContext.getSession())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firGetClassCall.getSource(), FirWebCommonErrors.INSTANCE.getEXTERNAL_INTERFACE_AS_CLASS_LITERAL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkTypeOperatorCall(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall) {
        FirTypeRef conversionTypeRef = firTypeOperatorCall.getConversionTypeRef();
        FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(conversionTypeRef, checkerContext.getSession());
        if (regularClassSymbol != null && this.webCheckerUtils.isNativeOrExternalInterface(regularClassSymbol, checkerContext.getSession())) {
            int i = WhenMappings.$EnumSwitchMapping$0[firTypeOperatorCall.getOperation().ordinal()];
            if (i == 1 || i == 2) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getSource(), (KtDiagnosticFactory2) FirWebCommonErrors.INSTANCE.getUNCHECKED_CAST_TO_EXTERNAL_INTERFACE(), (Object) FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments())), (Object) FirTypeUtilsKt.getConeType(conversionTypeRef), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else if (i == 3 || i == 4) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getCANNOT_CHECK_FOR_EXTERNAL_INTERFACE(), (Object) FirTypeUtilsKt.getConeType(conversionTypeRef), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (firStatement instanceof FirGetClassCall) {
            checkGetClassCall(checkerContext, diagnosticReporter, (FirGetClassCall) firStatement);
        } else if (firStatement instanceof FirTypeOperatorCall) {
            checkTypeOperatorCall(checkerContext, diagnosticReporter, (FirTypeOperatorCall) firStatement);
        }
    }
}
