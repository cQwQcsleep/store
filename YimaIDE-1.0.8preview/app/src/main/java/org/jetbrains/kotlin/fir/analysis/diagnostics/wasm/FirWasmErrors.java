package org.jetbrains.kotlin.fir.analysis.diagnostics.wasm;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u00108\u001a\u000209H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fR\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\fR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\fR\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/wasm/FirWasmErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "JS_MODULE_PROHIBITED_ON_NON_EXTERNAL", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getJS_MODULE_PROHIBITED_ON_NON_EXTERNAL", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "NON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE", "getNON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE", "WRONG_JS_INTEROP_TYPE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", Argument.Delimiters.none, "getWRONG_JS_INTEROP_TYPE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "WRONG_JS_FUN_TARGET", "getWRONG_JS_FUN_TARGET", "JSCODE_WRONG_CONTEXT", "getJSCODE_WRONG_CONTEXT", "JSCODE_UNSUPPORTED_FUNCTION_KIND", "getJSCODE_UNSUPPORTED_FUNCTION_KIND", "JSCODE_INVALID_PARAMETER_NAME", "getJSCODE_INVALID_PARAMETER_NAME", "NESTED_WASM_EXPORT", "getNESTED_WASM_EXPORT", "WASM_EXPORT_ON_EXTERNAL_DECLARATION", "getWASM_EXPORT_ON_EXTERNAL_DECLARATION", "JS_AND_WASM_EXPORTS_ON_SAME_DECLARATION", "getJS_AND_WASM_EXPORTS_ON_SAME_DECLARATION", "NESTED_WASM_IMPORT", "getNESTED_WASM_IMPORT", "WASM_IMPORT_ON_NON_EXTERNAL_DECLARATION", "getWASM_IMPORT_ON_NON_EXTERNAL_DECLARATION", "WASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE", "getWASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE", "WASM_IMPORT_EXPORT_VARARG_PARAMETER", "getWASM_IMPORT_EXPORT_VARARG_PARAMETER", "WASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE", "getWASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE", "WASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE", "getWASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE", "EXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS", "getEXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS", "EXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS", "getEXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS", "WASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION", "getWASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION", "WASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT", "getWASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT", "ASSOCIATED_OBJECT_INVALID_BINDING", "getASSOCIATED_OBJECT_INVALID_BINDING", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmErrors extends KtDiagnosticsContainer {
    private static final KtDiagnosticFactory0 ASSOCIATED_OBJECT_INVALID_BINDING;
    private static final KtDiagnosticFactory0 EXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS;
    private static final KtDiagnosticFactory0 EXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS;
    public static final FirWasmErrors INSTANCE;
    private static final KtDiagnosticFactory0 JSCODE_INVALID_PARAMETER_NAME;
    private static final KtDiagnosticFactory1<String> JSCODE_UNSUPPORTED_FUNCTION_KIND;
    private static final KtDiagnosticFactory0 JSCODE_WRONG_CONTEXT;
    private static final KtDiagnosticFactory0 JS_AND_WASM_EXPORTS_ON_SAME_DECLARATION;
    private static final KtDiagnosticFactory0 JS_MODULE_PROHIBITED_ON_NON_EXTERNAL;
    private static final KtDiagnosticFactory1<ConeKotlinType> NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN;
    private static final KtDiagnosticFactory0 NESTED_WASM_EXPORT;
    private static final KtDiagnosticFactory0 NESTED_WASM_IMPORT;
    private static final KtDiagnosticFactory1<ConeKotlinType> NON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE;
    private static final KtDiagnosticFactory0 WASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT;
    private static final KtDiagnosticFactory0 WASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION;
    private static final KtDiagnosticFactory0 WASM_EXPORT_ON_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 WASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE;
    private static final KtDiagnosticFactory1<ConeKotlinType> WASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE;
    private static final KtDiagnosticFactory1<ConeKotlinType> WASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE;
    private static final KtDiagnosticFactory0 WASM_IMPORT_EXPORT_VARARG_PARAMETER;
    private static final KtDiagnosticFactory0 WASM_IMPORT_ON_NON_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 WRONG_JS_FUN_TARGET;
    private static final KtDiagnosticFactory2<ConeKotlinType, String> WRONG_JS_INTEROP_TYPE;

    static {
        FirWasmErrors firWasmErrors = new FirWasmErrors();
        INSTANCE = firWasmErrors;
        Severity severity = Severity.ERROR;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        JS_MODULE_PROHIBITED_ON_NON_EXTERNAL = new KtDiagnosticFactory0("JS_MODULE_PROHIBITED_ON_NON_EXTERNAL", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN = new KtDiagnosticFactory1<>("NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        NON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE = new KtDiagnosticFactory1<>("NON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WRONG_JS_INTEROP_TYPE = new KtDiagnosticFactory2<>("WRONG_JS_INTEROP_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WRONG_JS_FUN_TARGET = new KtDiagnosticFactory0("WRONG_JS_FUN_TARGET", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firWasmErrors.getRendererFactory());
        JSCODE_WRONG_CONTEXT = new KtDiagnosticFactory0("JSCODE_WRONG_CONTEXT", severity, sourceElementPositioningStrategies.getNAME_IDENTIFIER(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        JSCODE_UNSUPPORTED_FUNCTION_KIND = new KtDiagnosticFactory1<>("JSCODE_UNSUPPORTED_FUNCTION_KIND", severity, sourceElementPositioningStrategies.getNAME_IDENTIFIER(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        JSCODE_INVALID_PARAMETER_NAME = new KtDiagnosticFactory0("JSCODE_INVALID_PARAMETER_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        NESTED_WASM_EXPORT = new KtDiagnosticFactory0("NESTED_WASM_EXPORT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_EXPORT_ON_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("WASM_EXPORT_ON_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        JS_AND_WASM_EXPORTS_ON_SAME_DECLARATION = new KtDiagnosticFactory0("JS_AND_WASM_EXPORTS_ON_SAME_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        NESTED_WASM_IMPORT = new KtDiagnosticFactory0("NESTED_WASM_IMPORT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_IMPORT_ON_NON_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("WASM_IMPORT_ON_NON_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE = new KtDiagnosticFactory0("WASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_IMPORT_EXPORT_VARARG_PARAMETER = new KtDiagnosticFactory0("WASM_IMPORT_EXPORT_VARARG_PARAMETER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE = new KtDiagnosticFactory1<>("WASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE = new KtDiagnosticFactory1<>("WASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        EXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS = new KtDiagnosticFactory0("EXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        EXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS = new KtDiagnosticFactory0("EXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION = new KtDiagnosticFactory0("WASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        WASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT = new KtDiagnosticFactory0("WASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
        ASSOCIATED_OBJECT_INVALID_BINDING = new KtDiagnosticFactory0("ASSOCIATED_OBJECT_INVALID_BINDING", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWasmErrors.getRendererFactory());
    }

    private FirWasmErrors() {
    }

    public final KtDiagnosticFactory0 getASSOCIATED_OBJECT_INVALID_BINDING() {
        return ASSOCIATED_OBJECT_INVALID_BINDING;
    }

    public final KtDiagnosticFactory0 getEXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS() {
        return EXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS() {
        return EXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS;
    }

    public final KtDiagnosticFactory0 getJSCODE_INVALID_PARAMETER_NAME() {
        return JSCODE_INVALID_PARAMETER_NAME;
    }

    public final KtDiagnosticFactory1<String> getJSCODE_UNSUPPORTED_FUNCTION_KIND() {
        return JSCODE_UNSUPPORTED_FUNCTION_KIND;
    }

    public final KtDiagnosticFactory0 getJSCODE_WRONG_CONTEXT() {
        return JSCODE_WRONG_CONTEXT;
    }

    public final KtDiagnosticFactory0 getJS_AND_WASM_EXPORTS_ON_SAME_DECLARATION() {
        return JS_AND_WASM_EXPORTS_ON_SAME_DECLARATION;
    }

    public final KtDiagnosticFactory0 getJS_MODULE_PROHIBITED_ON_NON_EXTERNAL() {
        return JS_MODULE_PROHIBITED_ON_NON_EXTERNAL;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN() {
        return NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN;
    }

    public final KtDiagnosticFactory0 getNESTED_WASM_EXPORT() {
        return NESTED_WASM_EXPORT;
    }

    public final KtDiagnosticFactory0 getNESTED_WASM_IMPORT() {
        return NESTED_WASM_IMPORT;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getNON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE() {
        return NON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirWasmErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory0 getWASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT() {
        return WASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT;
    }

    public final KtDiagnosticFactory0 getWASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION() {
        return WASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION;
    }

    public final KtDiagnosticFactory0 getWASM_EXPORT_ON_EXTERNAL_DECLARATION() {
        return WASM_EXPORT_ON_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getWASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE() {
        return WASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getWASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE() {
        return WASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getWASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE() {
        return WASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE;
    }

    public final KtDiagnosticFactory0 getWASM_IMPORT_EXPORT_VARARG_PARAMETER() {
        return WASM_IMPORT_EXPORT_VARARG_PARAMETER;
    }

    public final KtDiagnosticFactory0 getWASM_IMPORT_ON_NON_EXTERNAL_DECLARATION() {
        return WASM_IMPORT_ON_NON_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getWRONG_JS_FUN_TARGET() {
        return WRONG_JS_FUN_TARGET;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, String> getWRONG_JS_INTEROP_TYPE() {
        return WRONG_JS_INTEROP_TYPE;
    }
}
