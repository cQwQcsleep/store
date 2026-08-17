package org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/declaration/FirWasmExternalChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonExternalChecker;", "<init>", "()V", "isNativeOrEffectivelyExternal", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reportExternalEnum", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "additionalCheck", "isDefinedExternallyCallableId", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "hasExternalLikeAnnotations", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmExternalChecker extends FirWebCommonExternalChecker {
    public static final FirWasmExternalChecker INSTANCE = new FirWasmExternalChecker();

    private FirWasmExternalChecker() {
        super(false);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public void additionalCheck(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirFunction) {
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            if (firMemberDeclaration.getStatus().isInline()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunction) firDeclaration).getSource(), FirWebCommonErrors.INSTANCE.getINLINE_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (firMemberDeclaration.getStatus().isTailRec()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunction) firDeclaration).getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) "tailrec function", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (firMemberDeclaration.getStatus().isSuspend()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunction) firDeclaration).getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) "suspend function", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextParameters)) {
                FirFunction firFunction = (FirFunction) firDeclaration;
                if (!firFunction.getContextParameters().isEmpty()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirWasmErrors.INSTANCE.getEXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
        if (firDeclaration instanceof FirProperty) {
            if (((FirMemberDeclaration) firDeclaration).getStatus().isLateInit()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirProperty) firDeclaration).getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) "lateinit property", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextParameters)) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                if (firProperty.getContextParameters().isEmpty()) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirWasmErrors.INSTANCE.getEXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean hasExternalLikeAnnotations(FirDeclaration declaration, FirSession session) {
        declaration.getClass();
        session.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean isDefinedExternallyCallableId(CallableId callableId) {
        return Intrinsics.areEqual(callableId, WebCommonStandardClassIds.Callables.JsDefinedExternally);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public boolean isNativeOrEffectivelyExternal(FirBasedSymbol<?> symbol, FirSession session) {
        symbol.getClass();
        session.getClass();
        return FirWebCommonHelpersKt.isEffectivelyExternal(symbol, session);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalChecker
    public void reportExternalEnum(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getWRONG_EXTERNAL_DECLARATION(), (Object) "enum class", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
