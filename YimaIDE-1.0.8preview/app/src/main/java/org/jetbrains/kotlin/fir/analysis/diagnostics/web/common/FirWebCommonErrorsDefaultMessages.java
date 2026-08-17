package org.jetbrains.kotlin.fir.analysis.diagnostics.web.common;

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
import org.jetbrains.kotlin.diagnostics.rendering.BaseSourcelessDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrorsDefaultMessages;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/web/common/FirWebCommonErrorsDefaultMessages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWebCommonErrorsDefaultMessages extends BaseDiagnosticRendererFactory {
    public static final FirWebCommonErrorsDefaultMessages INSTANCE = new FirWebCommonErrorsDefaultMessages();

    /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
    private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FIR", new Function1() { // from class: qg5
        public final Object invoke(Object obj) {
            return FirWebCommonErrorsDefaultMessages.a((KtDiagnosticFactoryToRendererMap) obj);
        }
    });

    private FirWebCommonErrorsDefaultMessages() {
    }

    public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
        ktDiagnosticFactoryToRendererMap.getClass();
        FirWebCommonErrors firWebCommonErrors = FirWebCommonErrors.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_JS_QUALIFIER(), "Qualifier contains illegal characters.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNESTED_EXTERNAL_DECLARATION(), "Non-top-level 'external' declaration.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_EXTERNAL_DECLARATION(), "Declaration of such kind ({0}) cannot be external.", CommonRenderers.STRING);
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNESTED_CLASS_IN_EXTERNAL_INTERFACE(), "Interface cannot contain nested classes and objects.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getINLINE_EXTERNAL_DECLARATION(), "Inline external declaration.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE(), "Only nullable properties of external interfaces are allowed to be non-abstract.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER(), "External class constructor cannot have a property parameter.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_ANONYMOUS_INITIALIZER(), "Anonymous initializers in external classes are prohibited.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_DELEGATION(), "Cannot use delegate on external declaration.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_DELEGATED_CONSTRUCTOR_CALL(), "Delegated constructor call in external class is prohibited.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_BODY_OF_EXTERNAL_DECLARATION(), "Wrong body of external declaration. Must be either ' = definedExternally' or '{ definedExternally }'.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_INITIALIZER_OF_EXTERNAL_DECLARATION(), "Wrong initializer of external declaration. Must be ' = definedExternally'.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER(), "Wrong default value for parameter of external function. Must be ' = definedExternally'.");
        KtDiagnosticFactory1<ConeKotlinType> cannot_check_for_external_interface = firWebCommonErrors.getCANNOT_CHECK_FOR_EXTERNAL_INTERFACE();
        FirDiagnosticRenderers firDiagnosticRenderers = FirDiagnosticRenderers.INSTANCE;
        ktDiagnosticFactoryToRendererMap.put(cannot_check_for_external_interface, "Cannot check for external interface ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getUNCHECKED_CAST_TO_EXTERNAL_INTERFACE(), "Unchecked cast to external interface: ''{0}'' to ''{1}''.", firDiagnosticRenderers.getRENDER_TYPE(), firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_INTERFACE_AS_CLASS_LITERAL(), "Cannot refer to external interface from class literal.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT(), "Cannot pass external interface ''{0}'' for reified type parameter.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNESTED_JS_EXPORT(), "'@JsExport' is only allowed on files and top-level declarations.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getMULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE(), "Only one declaration with '@JsExport.Default' is allowed per file.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getWRONG_JS_EXPORT_TARGET_VISIBILITY(), "'@JsExport' is only allowed for public declarations. This declaration will not be exported.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getJSCODE_ARGUMENT_NON_CONST_EXPRESSION(), "An argument for the 'js()' function must be a constant string expression.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNAMED_COMPANION_IN_EXTERNAL_INTERFACE(), "Named companions are not allowed inside external interfaces.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getUNSUPPORTED_REFLECTION_API(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER, KtDiagnosticRenderers.INSTANCE.getTO_STRING());
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getCALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION(), "This property can only be used from external declarations.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getEXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE(), "External type extends non-external type ''{0}''.", firDiagnosticRenderers.getRENDER_TYPE());
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getJS_MODULE_PROHIBITED_ON_VAR(), "'@JsModule' annotation is prohibited for 'var' declarations. Use 'val' instead.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNESTED_JS_MODULE_PROHIBITED(), "'@JsModule' and '@JsNonModule' cannot appear here since the file is already marked by either '@JsModule' or '@JsNonModule'.");
        ktDiagnosticFactoryToRendererMap.put(firWebCommonErrors.getNON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE(), "Only external declarations are allowed in files marked with ''{0}'' annotation.", firDiagnosticRenderers.getRENDER_TYPE());
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
    public KtDiagnosticFactoryToRendererMap getMAP() {
        return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
    }
}
