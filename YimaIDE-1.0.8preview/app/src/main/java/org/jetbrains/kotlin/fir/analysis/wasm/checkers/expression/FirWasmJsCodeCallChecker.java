package org.jetbrains.kotlin.fir.analysis.wasm.checkers.expression;

import java.util.List;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrors;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.FirWasmJsCodeHelpersKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.js.common.IdentifierPolicyKt;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/expression/FirWasmJsCodeCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsCodeCallChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirWasmJsCodeCallChecker INSTANCE = new FirWasmJsCodeCallChecker();

    private FirWasmJsCodeCallChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default != null && Intrinsics.areEqual(resolvedCallableSymbol$default.getCallableId(), WebCommonStandardClassIds.Callables.Js)) {
            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(containingDeclarations);
            if (firBasedSymbol == null) {
                return;
            }
            FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, containingDeclarations.size() - 2);
            boolean z = (firBasedSymbol2 instanceof FirFileSymbol) || (firBasedSymbol2 instanceof FirScriptSymbol);
            KtSourceElement source = firFunctionCall.getCalleeReference().getSource();
            if (!z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirWasmErrors.INSTANCE.getJSCODE_WRONG_CONTEXT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            if (!(firBasedSymbol instanceof FirNamedFunctionSymbol)) {
                if (!(firBasedSymbol instanceof FirPropertySymbol)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirWasmErrors.INSTANCE.getJSCODE_WRONG_CONTEXT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                } else {
                    if (FirWasmJsCodeHelpersKt.hasValidJsCodeBody((FirPropertySymbol) firBasedSymbol)) {
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirWasmErrors.INSTANCE.getJSCODE_WRONG_CONTEXT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firBasedSymbol;
            if (!FirWasmJsCodeHelpersKt.hasValidJsCodeBody(firNamedFunctionSymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirWasmErrors.INSTANCE.getJSCODE_WRONG_CONTEXT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            if (firCallableSymbol.getRawStatus().isSuspend()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirWasmErrors.INSTANCE.getJSCODE_UNSUPPORTED_FUNCTION_KIND(), (Object) "suspend function", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (firCallableSymbol.getRawStatus().isInline()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirWasmErrors.INSTANCE.getJSCODE_UNSUPPORTED_FUNCTION_KIND(), (Object) "inline function", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (FirSymbolStatusUtilsKt.isExtension(firCallableSymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirWasmErrors.INSTANCE.getJSCODE_UNSUPPORTED_FUNCTION_KIND(), (Object) "function with extension receiver", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            for (FirValueParameterSymbol firValueParameterSymbol : firNamedFunctionSymbol.getValueParameterSymbols()) {
                String identifierOrNullIfSpecial = firValueParameterSymbol.getName().getIdentifierOrNullIfSpecial();
                if (identifierOrNullIfSpecial == null || !IdentifierPolicyKt.isValidES5Identifier(identifierOrNullIfSpecial)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), FirWasmErrors.INSTANCE.getJSCODE_INVALID_PARAMETER_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
