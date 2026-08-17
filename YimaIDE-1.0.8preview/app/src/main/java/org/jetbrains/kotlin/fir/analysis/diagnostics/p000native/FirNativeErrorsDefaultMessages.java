package org.jetbrains.kotlin.fir.analysis.diagnostics.p000native;

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
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrorsDefaultMessages;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/native/FirNativeErrorsDefaultMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeErrorsDefaultMessages extends BaseDiagnosticRendererFactory {
    public static final FirNativeErrorsDefaultMessages INSTANCE = new FirNativeErrorsDefaultMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FIR", new Function1() { // from class: wa5
        public final Object invoke(Object obj) {
            return FirNativeErrorsDefaultMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private FirNativeErrorsDefaultMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        FirNativeErrors firNativeErrors = FirNativeErrors.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getTHROWS_LIST_EMPTY(), "Throws must have a non-empty class list.");
        KtDiagnosticFactory1<FirRegularClassSymbol> incompatible_throws_override = firNativeErrors.getINCOMPATIBLE_THROWS_OVERRIDE();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(incompatible_throws_override, "Member overrides different ''@Throws'' filter from ''{0}''.", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINCOMPATIBLE_THROWS_INHERITED(), "Member inherits different ''@Throws'' filters from:{0}", firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        KtDiagnosticFactory1<FqName> missing_exception_in_throws_on_suspend = firNativeErrors.getMISSING_EXCEPTION_IN_THROWS_ON_SUSPEND();
        KtDiagnosticRenderers ktDiagnosticRenderers = KtDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(missing_exception_in_throws_on_suspend, "''@Throws'' on suspend declaration must have ''{0}'' (or any of its superclasses) listed.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_SHARED_IMMUTABLE_PROPERTY(), "'@SharedImmutable' is applicable only to 'val' with backing field or to property with delegation.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL(), "'@SharedImmutable' is applicable only to top-level declarations.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_THREAD_LOCAL(), "'@ThreadLocal' is applicable only to property with backing field, to property with delegation, or to objects.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_THREAD_LOCAL_TOP_LEVEL(), "'@ThreadLocal' is applicable only to top-level declarations.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_CHARACTERS_NATIVE_ERROR(), "Name {0}.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getREDUNDANT_SWIFT_REFINEMENT(), "ObjC refined declarations cannot be refined in Swift.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE(), "Refined declaration ''{0}'' overrides declarations with different or no refinement from:{1}", firDiagnosticRenderers.getSYMBOL(), firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_OBJC_HIDES_TARGETS(), "'@HidesFromObjC' annotation is only applicable to annotations with targets CLASS, FUNCTION, and/or PROPERTY.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_REFINES_IN_SWIFT_TARGETS(), "'@RefinesInSwift' annotation is only applicable to annotations with targets FUNCTION and/or PROPERTY.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_OBJC_NAME(), "'@ObjCName' is not applicable to overrides.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_OBJC_NAME(), "'@ObjCName' must have a 'name' and/or 'swiftName'.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getEMPTY_OBJC_NAME(), "Empty '@ObjCName' names aren't supported.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_OBJC_NAME_CHARS(), "''@ObjCName'' contains illegal characters ''{0}''.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINVALID_OBJC_NAME_FIRST_CHAR(), "''@ObjCName'' contains illegal first characters ''{0}''.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINCOMPATIBLE_OBJC_NAME_OVERRIDE(), "Member ''{0}'' inherits inconsistent ''@ObjCName'' from:{1}", firDiagnosticRenderers.getSYMBOL(), firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_EXACT_OBJC_NAME(), "Exact '@ObjCName' is only applicable to classes, objects, and interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getMISSING_EXACT_OBJC_NAME(), "Exact '@ObjCName' is required to have an ObjC name.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getNON_LITERAL_OBJC_NAME_ARG(), "'@ObjCName' accepts only literal 'String' and 'Boolean' values.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getSUBTYPE_OF_HIDDEN_FROM_OBJC(), "Only '@HiddenFromObjC' declaration can be a subtype of '@HiddenFromObjC' declaration.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCANNOT_CHECK_FOR_FORWARD_DECLARATION(), "Cannot check for forward declaration ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getUNCHECKED_CAST_TO_FORWARD_DECLARATION(), "Unchecked cast to forward declaration from ''{0}'' to ''{1}''.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getFORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT(), "Cannot pass forward declaration ''{0}'' for reified type parameter.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getFORWARD_DECLARATION_AS_CLASS_LITERAL(), "Cannot refer to forward declaration ''{0}'' from class literal.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getTWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE(), "Only 0, 1 or 2 parameters are supported here.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getPROPERTY_MUST_BE_VAR(), "''@{0}'' property must be var.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getMUST_NOT_HAVE_EXTENSION_RECEIVER(), "''{0}'' cannot have extension receiver.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getMUST_BE_OBJC_OBJECT_TYPE(), "Unexpected {0}: ''{1}''\nOnly Objective-C object types are supported here.", ktDiagnosticRenderers.getTO_STRING(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getMUST_BE_UNIT_TYPE(), "Unexpected {0}: ''{1}''\nOnly ''Unit'' is supported here.", ktDiagnosticRenderers.getTO_STRING(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER(), "Constructor with ''@{0}'' overrides initializer that is already overridden explicitly.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR(), "Constructor with ''@{0}'' doesn''t override any super class constructor.\nIt must completely match by parameter names and types.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS(), "Constructor with ''@{0}'' matches more than one of super constructors.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCONFLICTING_OBJC_OVERLOADS(), "Conflicting overloads:{0}\nAdd @ObjCSignatureOverride to allow collision for functions inherited from Objective-C.", firDiagnosticRenderers.getSYMBOLS_ON_NEXT_LINES());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getINAPPLICABLE_OBJC_OVERRIDE(), "@ObjCSignatureOverride is only allowed on methods overriding methods from Objective-C.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getNATIVE_SPECIFIC_ATOMIC(), "Native-specific atomic type ''kotlin.concurrent.{0}'' is going to be deprecated soon. Consider using ''kotlin.concurrent.atomics.{0}'' instead.", ktDiagnosticRenderers.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getIDENTITY_HASH_CODE_ON_VALUE_TYPE(), "Call to ''kotlin.native.identityHashCode'' on an instance of value type ''{0}'' can have unexpected behavior.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getVARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED(), "Variadic function pointers are not supported: {0}", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getOVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED(), "Overriding variadic Objective-C methods are not supported: {0}", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED(), "Callable references to variadic C functions are not supported: {0}", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getCALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED(), "Callable references to variadic Objective-C methods are not supported: {0}", firDiagnosticRenderers.getSYMBOL());
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getSTRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS(), "Passing String as variadic Objective-C argument is ambiguous; cast it to NSString or pass with '.cstr' as C string.");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getVARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF(), "When calling variadic Objective-C methods spread operator is supported only for *arrayOf(...).");
        ktDiagnosticFactoryToRendererMap.put(firNativeErrors.getVARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF(), "When calling variadic C functions spread operator is supported only for *arrayOf(...).");
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
