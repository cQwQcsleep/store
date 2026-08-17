package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeLanguageFeature;
import androidx.compose.compiler.plugins.kotlin.ComposeLanguageFeaturesKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposableFunctionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunctionChecker extends FirDeclarationChecker<FirFunction> {
    public static final ComposableFunctionChecker INSTANCE = new ComposableFunctionChecker();

    private ComposableFunctionChecker() {
        super(MppCheckerKind.Common);
    }

    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        EffectiveVisibility effectiveVisibility;
        EffectiveVisibility effectiveVisibility2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        boolean zHasComposableAnnotation = FirUtilsKt.hasComposableAnnotation(firFunction, checkerContext.getSession());
        List<FirFunctionSymbol> directOverriddenFunctions = FirUtilsKt.getDirectOverriddenFunctions(firFunction, checkerContext);
        for (FirFunctionSymbol firFunctionSymbol : directOverriddenFunctions) {
            if (FirUtilsKt.isComposable(firFunctionSymbol, checkerContext.getSession()) != zHasComposableAnnotation) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), FirErrors.INSTANCE.getCONFLICTING_OVERLOADS(), CollectionsKt.listOf(new FirFunctionSymbol[]{firFunction.getSymbol(), firFunctionSymbol}), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else if (FirUtilsKt.isComposable(firFunctionSymbol, checkerContext.getSession()) && !ComposableTargetCheckerKt.toScheme(checkerContext, firFunctionSymbol).canOverride(ComposableTargetCheckerKt.toScheme(checkerContext, firFunction.getSymbol()))) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getCOMPOSE_APPLIER_DECLARATION_MISMATCH(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        FirFunctionSymbol singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull(firFunction.getSymbol());
        if (singleMatchedExpectForActualOrNull != null && FirUtilsKt.hasComposableAnnotation(singleMatchedExpectForActualOrNull, checkerContext.getSession()) != zHasComposableAnnotation) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getMISMATCHED_COMPOSABLE_IN_EXPECT_ACTUAL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (zHasComposableAnnotation) {
            if (firFunction.getStatus().isSuspend()) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_SUSPEND_FUN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            List<FirFunctionSymbol> list = directOverriddenFunctions;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                for (FirFunctionSymbol firFunctionSymbol2 : list) {
                    if (firFunctionSymbol2.getResolvedStatus().getModality() == Modality.OPEN) {
                        List valueParameterSymbols = firFunctionSymbol2.getValueParameterSymbols();
                        if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                            Iterator it = valueParameterSymbols.iterator();
                            while (it.hasNext()) {
                                if (((FirValueParameterSymbol) it.next()).getHasDefaultValue()) {
                                    if (!ComposableFunctionCheckerKt.isMissingCompatMetadata(firFunctionSymbol2)) {
                                        break;
                                    }
                                    KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getDEPRECATED_OPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            LanguageVersionSettings languageVersionSettings = checkerContext.getLanguageVersionSettings();
            if (!ComposeLanguageFeaturesKt.supportsComposeFeature(languageVersionSettings, ComposeLanguageFeature.DefaultParametersInAbstractFunctions)) {
                FirResolvedDeclarationStatus status = firFunction.getStatus();
                FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? status : null;
                if (firResolvedDeclarationStatus == null || (effectiveVisibility2 = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                    effectiveVisibility2 = EffectiveVisibility.Local.INSTANCE;
                }
                if (effectiveVisibility2.getPublicApi() && firFunction.getStatus().getModality() == Modality.ABSTRACT) {
                    for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
                        if (firValueParameter.getDefaultValue() != null) {
                            FirExpression defaultValue = firValueParameter.getDefaultValue();
                            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, defaultValue != null ? defaultValue.getSource() : null, ComposeErrors.INSTANCE.getABSTRACT_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), languageVersionSettings.getLanguageVersion(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        }
                    }
                }
            }
            if (!ComposeLanguageFeaturesKt.supportsComposeFeature(languageVersionSettings, ComposeLanguageFeature.DefaultParametersInOpenFunctions)) {
                FirResolvedDeclarationStatus status2 = firFunction.getStatus();
                FirResolvedDeclarationStatus firResolvedDeclarationStatus2 = status2 instanceof FirResolvedDeclarationStatus ? status2 : null;
                if (firResolvedDeclarationStatus2 == null || (effectiveVisibility = firResolvedDeclarationStatus2.getEffectiveVisibility()) == null) {
                    effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
                }
                if (effectiveVisibility.getPublicApi() && firFunction.getStatus().getModality() == Modality.OPEN) {
                    List valueParameters = firFunction.getValueParameters();
                    if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
                        Iterator it2 = valueParameters.iterator();
                        while (it2.hasNext()) {
                            if (((FirValueParameter) it2.next()).getDefaultValue() != null) {
                                for (FirValueParameter firValueParameter2 : firFunction.getValueParameters()) {
                                    if (firValueParameter2.getDefaultValue() != null) {
                                        FirExpression defaultValue2 = firValueParameter2.getDefaultValue();
                                        KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, defaultValue2 != null ? defaultValue2.getSource() : null, ComposeErrors.INSTANCE.getOPEN_COMPOSABLE_DEFAULT_PARAMETER_VALUE(), languageVersionSettings.getLanguageVersion(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (FirUtilsKt.isMain(firFunction.getSymbol(), checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_FUN_MAIN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firFunction.getStatus().isOperator() && Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName(firFunction), OperatorNameConventions.SET_VALUE)) {
                KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firFunction.getSource(), ComposeErrors.INSTANCE.getCOMPOSE_INVALID_DELEGATE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
