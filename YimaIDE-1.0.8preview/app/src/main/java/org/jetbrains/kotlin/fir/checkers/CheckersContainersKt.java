package org.jetbrains.kotlin.fir.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.CommonDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.CommonExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.CommonLanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.CommonTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExperimentalExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExperimentalLanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExperimentalTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExtraDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExtraExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.ExtraLanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.CliFrontendDiagnostics;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.JsDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.js.checkers.JsExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.JvmDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.JvmExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.JvmTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.NativeDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.NativeDeclarationExtraCheckers;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.NativeExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.NativeTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmBaseDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmBaseExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmBaseTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmJsDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmJsExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.WasmWasiDeclarationCheckers;
import org.jetbrains.kotlin.fir.builder.FirSyntaxErrors;
import org.jetbrains.kotlin.fir.session.FirSessionConfigurator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0002¨\u0006\u000b"}, d2 = {"registerCommonCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "registerExtraCommonCheckers", "registerExperimentalCheckers", "registerJvmCheckers", "registerJsCheckers", "registerNativeCheckers", "registerExtraNativeCheckers", "registerWasmJsCheckers", "registerWasmWasiCheckers", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckersContainersKt {
    public static final void registerCommonCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(CommonDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(CommonExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(CommonTypeCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(CommonLanguageVersionSettingsCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirErrors.INSTANCE, FirSyntaxErrors.INSTANCE, CliFrontendDiagnostics.INSTANCE);
    }

    public static final void registerExperimentalCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(ExperimentalExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(ExperimentalTypeCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(ExperimentalLanguageVersionSettingsCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirErrors.INSTANCE);
    }

    public static final void registerExtraCommonCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(ExtraExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(ExtraDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(ExtraLanguageVersionSettingsCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirErrors.INSTANCE);
    }

    public static final void registerExtraNativeCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(NativeDeclarationExtraCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirNativeErrors.INSTANCE);
    }

    public static final void registerJsCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(JsDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(JsExpressionCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirWebCommonErrors.INSTANCE, FirJsErrors.INSTANCE);
    }

    public static final void registerJvmCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(JvmDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(JvmExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(JvmTypeCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirJvmErrors.INSTANCE);
    }

    public static final void registerNativeCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(NativeDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(NativeExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(NativeTypeCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirNativeErrors.INSTANCE);
    }

    public static final void registerWasmJsCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(WasmBaseDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(WasmBaseExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(WasmBaseTypeCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirWebCommonErrors.INSTANCE, FirWasmErrors.INSTANCE);
        firSessionConfigurator.useCheckers(WasmJsDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(WasmJsExpressionCheckers.INSTANCE);
    }

    public static final void registerWasmWasiCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        firSessionConfigurator.useCheckers(WasmBaseDeclarationCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(WasmBaseExpressionCheckers.INSTANCE);
        firSessionConfigurator.useCheckers(WasmBaseTypeCheckers.INSTANCE);
        firSessionConfigurator.registerDiagnosticContainers(FirWebCommonErrors.INSTANCE, FirWasmErrors.INSTANCE);
        firSessionConfigurator.useCheckers(WasmWasiDeclarationCheckers.INSTANCE);
    }
}
