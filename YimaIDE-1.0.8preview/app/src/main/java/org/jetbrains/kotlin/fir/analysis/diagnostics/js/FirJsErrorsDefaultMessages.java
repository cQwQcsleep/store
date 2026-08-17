package org.jetbrains.kotlin.fir.analysis.diagnostics.js;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrorsDefaultMessages;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/js/FirJsErrorsDefaultMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsErrorsDefaultMessages extends BaseDiagnosticRendererFactory {
    public static final FirJsErrorsDefaultMessages INSTANCE = new FirJsErrorsDefaultMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FIR", new Function1() { // from class: o95
        public final Object invoke(Object obj) {
            return FirJsErrorsDefaultMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private FirJsErrorsDefaultMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        FirJsErrors firJsErrors = FirJsErrors.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_MODULE_PROHIBITED_ON_NON_NATIVE(), "'@JsModule' and '@JsNonModule' annotations are prohibited for non-external declarations.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getCALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE(), "When accessing module declarations from UMD, they must be marked with both @JsModule and @JsNonModule.");
        KtDiagnosticFactory1<FirBasedSymbol<?>> call_to_js_module_without_module_system = firJsErrors.getCALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(call_to_js_module_without_module_system, "Cannot access ''{0}'' marked with @JsModule annotation from non-modular project.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getCALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM(), "Cannot access ''{0}'' marked with @JsNonModule annotation from modular project.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getWRONG_MULTIPLE_INHERITANCE(), "Multiple inheritance cannot be used here, since it''s impossible to generate a bridge for system function {0}.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getDELEGATION_BY_DYNAMIC(), "Cannot delegate to dynamic value.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getPROPERTY_DELEGATION_BY_DYNAMIC(), "Cannot apply property delegation by dynamic handler.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getSPREAD_OPERATOR_IN_DYNAMIC_CALL(), "Cannot apply spread operator in dynamic call.");
        KtDiagnosticFactory1<String> wrong_operation_with_dynamic = firJsErrors.getWRONG_OPERATION_WITH_DYNAMIC();
        ContextIndependentParameterRenderer<String> contextIndependentParameterRenderer = CommonRenderers.STRING;
        ktDiagnosticFactoryToRendererMap.put(wrong_operation_with_dynamic, "Wrong operation with dynamic value: {0}.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getIMPLEMENTING_FUNCTION_INTERFACE(), "Implementing a function interface is prohibited in JavaScript.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS(), "Overriding 'external' function with optional parameters.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE(), "Overriding ''external'' function with optional parameters by declaration from superclass: {0}.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getRUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION(), "Runtime annotation cannot be put on external declaration.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getEXTERNAL_ENUM_ENTRY_WITH_BODY(), "Entry of external enum class cannot have a body.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getINLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING(), "Using value classes as parameter type or return type of external declarations is experimental.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING(), "Using enum classes with an 'external' qualifier becomes deprecated and will be an error in future releases.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getINLINE_CLASS_IN_EXTERNAL_DECLARATION(), "Using value classes as parameter type or return type of external declarations is not supported.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getEXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION(), "Function types with receivers are prohibited in external declarations.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN(), "Annotation ''{0}'' is only allowed on member functions of declarations annotated with ''kotlin.js.native'' or on top-level extension functions.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER(), "Native {0}''s first parameter type must be ''kotlin.String'' or a subtype of ''kotlin.Number''.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS(), "Native {0}''s parameter cannot have default value.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE(), "Native getter's return type must be nullable.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_SETTER_WRONG_RETURN_TYPE(), "Native setter's return type must be 'Unit' or a supertype of the second parameter's type.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNATIVE_INDEXER_WRONG_PARAMETER_COUNT(), "Expected {0} parameters for native {1}.", KtDiagnosticRenderers.INSTANCE.getTO_STRING(), contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_EXTERNAL_INHERITORS_ONLY(), "External {0} cannot be a parent of non-external {1}.", firDiagnosticRenderers.getRENDER_CLASS_OR_OBJECT_NAME_QUOTED(), firDiagnosticRenderers.getRENDER_CLASS_OR_OBJECT_NAME_QUOTED());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_EXTERNAL_ARGUMENT(), "Expected argument with external type, but type ''{0}'' is non-external.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY(), "'@JsName' is prohibited for extension properties.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_BUILTIN_NAME_CLASH(), "JavaScript name generated for this declaration clashes with built-in declaration ''{0}''.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNAME_CONTAINS_ILLEGAL_CHARS(), "Name contains illegal chars that cannot appear in JavaScript identifier.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_CLASH(), "JavaScript name ''{0}'' generated for this declaration clashes with other declarations:{1}", contextIndependentParameterRenderer, firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_FAKE_NAME_CLASH(), "JavaScript name ''{0}'' is generated for different inherited members:\n{1}{2}", contextIndependentParameterRenderer, firDiagnosticRenderers.getSYMBOL(), firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_IS_NOT_ON_ALL_ACCESSORS(), "All property accessors must be annotated with '@JsName'.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_PROHIBITED_FOR_NAMED_NATIVE(), "'@JsName' is prohibited for external declaration with explicit name.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_PROHIBITED_FOR_OVERRIDE(), "'@JsName' is prohibited for overridden members.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED(), "'@JsName' annotation is prohibited for primary constructors.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NAME_ON_ACCESSOR_AND_PROPERTY(), "'@JsName' can be either on a property or its accessors, not both of them.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_SYMBOL_ON_TOP_LEVEL_DECLARATION(), "'@JsSymbol' annotation is only allowed on member functions.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_SYMBOL_PROHIBITED_FOR_OVERRIDE(), "'@JsSymbol' is prohibited for overridden members.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getWRONG_EXPORTED_DECLARATION(), "Declaration of such kind ({0}) cannot be exported to JavaScript.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNON_EXPORTABLE_TYPE(), "Exported declaration uses non-exportable {0} type ''{1}''.", contextIndependentParameterRenderer, firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNON_CONSUMABLE_EXPORTED_IDENTIFIER(), "Exported declaration contains non-consumable identifier ''{0}'', which cannot be represented inside TS definitions and ESM.", contextIndependentParameterRenderer);
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNAMED_COMPANION_IN_EXPORTED_INTERFACE(), "Named companions are not allowed inside exported interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getNOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED(), "The corresponding expect declaration is marked as exported, but the actual one is not.\nTo fix it, mark the actual declaration with '@JsExport' as well, remove @JsExport.Ignore if there is one, or remove the '@JsExport' annotation from the expect declaration.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_STATIC_NOT_IN_CLASS_COMPANION(), "Only members of class companion objects can be annotated with '@JsStatic'.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_STATIC_ON_NON_PUBLIC_MEMBER(), "Only public members of class companion objects can be annotated with '@JsStatic'.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_STATIC_ON_CONST(), "'@JsStatic' annotation is redundant for const properties.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getEXPOSED_NOT_EXPORTED_SUPER_INTERFACE(), "Exported sub-interface exposes its non-exported supertype ''{0}''.", firDiagnosticRenderers.getDECLARATION_NAME());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_WRONG_TARGET(), "'@JsNoRuntime' is only allowed on interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_FORBIDDEN_IS_CHECK(), "Runtime type checks ('is'/'!is') are forbidden for '@JsNoRuntime' interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_FORBIDDEN_AS_CAST(), "Runtime type casts ('as'/'as?') are forbidden for '@JsNoRuntime' interfaces. Consider using `unsafeCast` instead.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE(), "Class references are forbidden for '@JsNoRuntime' interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE(), "'@JsNoRuntime' on external interface has no effect.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT(), "Cannot pass ''@JsNoRuntime'' interface ''{0}'' for reified type parameter.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME(), "This 'actual' external interface corresponds to an 'expect' interface with runtime. Consider adding '@JsNoRuntime' to the 'expect' interface.");
        ktDiagnosticFactoryToRendererMap.put(firJsErrors.getJS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT(), "@JsNoRuntime annotations from expect must either be present with on actual as well, or the actual interface must be external.");
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
