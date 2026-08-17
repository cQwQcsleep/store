package org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrors;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\b\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"checkWasmInteropSignature", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "isTypeSupportedInWasmInterop", Argument.Delimiters.none, "unexpandedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isInFunctionReturnPosition", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.wasm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmImportAnnotationCheckerKt {
    public static final void checkWasmInteropSignature(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
            if (firValueParameter.getDefaultValue() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirWasmErrors.INSTANCE.getWASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firValueParameter.getIsVararg()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirWasmErrors.INSTANCE.getWASM_IMPORT_EXPORT_VARARG_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (!isTypeSupportedInWasmInterop(coneType, false, checkerContext.getSession())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory1) FirWasmErrors.INSTANCE.getWASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE(), (Object) coneType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
        ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef());
        if (isTypeSupportedInWasmInterop(coneType2, true, checkerContext.getSession())) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirWasmErrors.INSTANCE.getWASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE(), (Object) coneType2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private static final boolean isTypeSupportedInWasmInterop(ConeKotlinType coneKotlinType, boolean z, FirSession firSession) {
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        if (ConeBuiltinTypeUtilsKt.isUnit(coneKotlinTypeFullyExpandedType$default)) {
            return z;
        }
        return (ConeBuiltinTypeUtilsKt.isPrimitive(coneKotlinTypeFullyExpandedType$default) && !ConeBuiltinTypeUtilsKt.isChar(coneKotlinTypeFullyExpandedType$default)) || ConeBuiltinTypeUtilsKt.isUnsignedType(coneKotlinTypeFullyExpandedType$default);
    }
}
