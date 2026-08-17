package org.jetbrains.kotlin.fir.analysis.diagnostics.wasm;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.wasm.FirWasmErrorsDefaultMessages;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/wasm/FirWasmErrorsDefaultMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmErrorsDefaultMessages extends BaseDiagnosticRendererFactory {
    public static final FirWasmErrorsDefaultMessages INSTANCE = new FirWasmErrorsDefaultMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FIR", new Function1() { // from class: pg5
        public final Object invoke(Object obj) {
            return FirWasmErrorsDefaultMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private FirWasmErrorsDefaultMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        FirWasmErrors firWasmErrors = FirWasmErrors.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getJS_MODULE_PROHIBITED_ON_NON_EXTERNAL(), "'@JsModule' annotation is prohibited for non-external declarations.");
        KtDiagnosticFactory1<ConeKotlinType> native_annotations_allowed_only_on_member_fun = firWasmErrors.getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_FUN();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(native_annotations_allowed_only_on_member_fun, "Annotation ''{0}'' is only allowed on member functions of declarations annotated with ''kotlin.js.native'' functions.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getNON_EXTERNAL_TYPE_EXTENDS_EXTERNAL_TYPE(), "Non-external type extends external type ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        KtDiagnosticFactory2<ConeKotlinType, String> wrong_js_interop_type = firWasmErrors.getWRONG_JS_INTEROP_TYPE();
        DiagnosticParameterRenderer<ConeKotlinType> render_type = firDiagnosticRenderers.getRENDER_TYPE();
        KtDiagnosticRenderers ktDiagnosticRenderers = KtDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(wrong_js_interop_type, "Type ''{0}'' cannot be used as {1}. Only external, primitive, string, and function types are supported in Kotlin/Wasm JS interop.", render_type, ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWRONG_JS_FUN_TARGET(), "Only top-level external functions can be implemented using '@JsFun'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getJSCODE_WRONG_CONTEXT(), "Calls to 'js(code)' must be a single expression inside a top-level function body or a property initializer in Kotlin/Wasm.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getJSCODE_UNSUPPORTED_FUNCTION_KIND(), "Calls to ''js(code)'' are not supported in {0} in Kotlin/Wasm.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getJSCODE_INVALID_PARAMETER_NAME(), "Parameters passed to 'js(code)' must have a valid JavaScript name.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getNESTED_WASM_EXPORT(), "Only top-level functions can be exported with '@WasmExport'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_EXPORT_ON_EXTERNAL_DECLARATION(), "Functions annotated with '@WasmExport' cannot be external.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getJS_AND_WASM_EXPORTS_ON_SAME_DECLARATION(), "Cannot use '@WasmExport' and '@JsExport' for same function.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getNESTED_WASM_IMPORT(), "Only top-level functions can be imported with '@WasmImport'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_IMPORT_ON_NON_EXTERNAL_DECLARATION(), "Functions annotated with '@WasmImport' must be external.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_IMPORT_EXPORT_PARAMETER_DEFAULT_VALUE(), "Default parameter values are not supported with '@WasmImport' and '@WasmExport'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_IMPORT_EXPORT_VARARG_PARAMETER(), "Vararg parameters are not supported with '@WasmImport' and '@WasmExport'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_IMPORT_EXPORT_UNSUPPORTED_PARAMETER_TYPE(), "Unsupported ''@WasmImport'' and ''@WasmExport'' parameter type ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASM_IMPORT_EXPORT_UNSUPPORTED_RETURN_TYPE(), "Unsupported ''@WasmImport'' and ''@WasmExport'' return type ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASI_EXTERNAL_NOT_TOP_LEVEL_FUNCTION(), "Only top-level functions can be external.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getWASI_EXTERNAL_FUNCTION_WITHOUT_IMPORT(), "External functions must be annotated with '@WasmImport'.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getASSOCIATED_OBJECT_INVALID_BINDING(), "Invalid associated object binding.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getEXTERNAL_DECLARATION_WITH_CONTEXT_PARAMETERS(), "External declaration cannot have context parameters.");
        ktDiagnosticFactoryToRendererMap.put(firWasmErrors.getEXPORT_DECLARATION_WITH_CONTEXT_PARAMETERS(), "Exported declaration cannot have context parameters.");
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
