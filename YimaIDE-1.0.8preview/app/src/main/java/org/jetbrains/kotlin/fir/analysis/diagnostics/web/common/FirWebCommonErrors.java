package org.jetbrains.kotlin.fir.analysis.diagnostics.web.common;

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
import org.jetbrains.kotlin.psi.KtAnonymousInitializer;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010D\u001a\u00020EH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0007R\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0012R\u001d\u0010*\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020(0+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020(0\u000f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0012R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020(0\u000f¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0012R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020(0\u000f¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0012R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0012¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/web/common/FirWebCommonErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "WRONG_JS_QUALIFIER", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getWRONG_JS_QUALIFIER", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "JS_MODULE_PROHIBITED_ON_VAR", "getJS_MODULE_PROHIBITED_ON_VAR", "NESTED_JS_MODULE_PROHIBITED", "getNESTED_JS_MODULE_PROHIBITED", "NESTED_EXTERNAL_DECLARATION", "getNESTED_EXTERNAL_DECLARATION", "WRONG_EXTERNAL_DECLARATION", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", Argument.Delimiters.none, "getWRONG_EXTERNAL_DECLARATION", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "NESTED_CLASS_IN_EXTERNAL_INTERFACE", "getNESTED_CLASS_IN_EXTERNAL_INTERFACE", "INLINE_EXTERNAL_DECLARATION", "getINLINE_EXTERNAL_DECLARATION", "NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE", "getNON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE", "EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER", "getEXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER", "EXTERNAL_ANONYMOUS_INITIALIZER", "getEXTERNAL_ANONYMOUS_INITIALIZER", "EXTERNAL_DELEGATION", "getEXTERNAL_DELEGATION", "EXTERNAL_DELEGATED_CONSTRUCTOR_CALL", "getEXTERNAL_DELEGATED_CONSTRUCTOR_CALL", "WRONG_BODY_OF_EXTERNAL_DECLARATION", "getWRONG_BODY_OF_EXTERNAL_DECLARATION", "WRONG_INITIALIZER_OF_EXTERNAL_DECLARATION", "getWRONG_INITIALIZER_OF_EXTERNAL_DECLARATION", "WRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER", "getWRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER", "CANNOT_CHECK_FOR_EXTERNAL_INTERFACE", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getCANNOT_CHECK_FOR_EXTERNAL_INTERFACE", "UNCHECKED_CAST_TO_EXTERNAL_INTERFACE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "getUNCHECKED_CAST_TO_EXTERNAL_INTERFACE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "EXTERNAL_INTERFACE_AS_CLASS_LITERAL", "getEXTERNAL_INTERFACE_AS_CLASS_LITERAL", "EXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", "getEXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", "NAMED_COMPANION_IN_EXTERNAL_INTERFACE", "getNAMED_COMPANION_IN_EXTERNAL_INTERFACE", "CALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION", "getCALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION", "EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE", "getEXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE", "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE", "getNON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE", "NESTED_JS_EXPORT", "getNESTED_JS_EXPORT", "MULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE", "getMULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE", "WRONG_JS_EXPORT_TARGET_VISIBILITY", "getWRONG_JS_EXPORT_TARGET_VISIBILITY", "JSCODE_ARGUMENT_NON_CONST_EXPRESSION", "getJSCODE_ARGUMENT_NON_CONST_EXPRESSION", "UNSUPPORTED_REFLECTION_API", "getUNSUPPORTED_REFLECTION_API", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWebCommonErrors extends KtDiagnosticsContainer {
    private static final KtDiagnosticFactory0 CALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory1<ConeKotlinType> CANNOT_CHECK_FOR_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory0 EXTERNAL_ANONYMOUS_INITIALIZER;
    private static final KtDiagnosticFactory0 EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER;
    private static final KtDiagnosticFactory0 EXTERNAL_DELEGATED_CONSTRUCTOR_CALL;
    private static final KtDiagnosticFactory0 EXTERNAL_DELEGATION;
    private static final KtDiagnosticFactory0 EXTERNAL_INTERFACE_AS_CLASS_LITERAL;
    private static final KtDiagnosticFactory1<ConeKotlinType> EXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT;
    private static final KtDiagnosticFactory1<ConeKotlinType> EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE;
    private static final KtDiagnosticFactory0 INLINE_EXTERNAL_DECLARATION;
    public static final FirWebCommonErrors INSTANCE;
    private static final KtDiagnosticFactory0 JSCODE_ARGUMENT_NON_CONST_EXPRESSION;
    private static final KtDiagnosticFactory0 JS_MODULE_PROHIBITED_ON_VAR;
    private static final KtDiagnosticFactory0 MULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE;
    private static final KtDiagnosticFactory0 NAMED_COMPANION_IN_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory0 NESTED_CLASS_IN_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory0 NESTED_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 NESTED_JS_EXPORT;
    private static final KtDiagnosticFactory0 NESTED_JS_MODULE_PROHIBITED;
    private static final KtDiagnosticFactory0 NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory1<ConeKotlinType> NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE;
    private static final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> UNCHECKED_CAST_TO_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory1<String> UNSUPPORTED_REFLECTION_API;
    private static final KtDiagnosticFactory0 WRONG_BODY_OF_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 WRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER;
    private static final KtDiagnosticFactory1<String> WRONG_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 WRONG_INITIALIZER_OF_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 WRONG_JS_EXPORT_TARGET_VISIBILITY;
    private static final KtDiagnosticFactory0 WRONG_JS_QUALIFIER;

    static {
        FirWebCommonErrors firWebCommonErrors = new FirWebCommonErrors();
        INSTANCE = firWebCommonErrors;
        Severity severity = Severity.ERROR;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        WRONG_JS_QUALIFIER = new KtDiagnosticFactory0("WRONG_JS_QUALIFIER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        JS_MODULE_PROHIBITED_ON_VAR = new KtDiagnosticFactory0("JS_MODULE_PROHIBITED_ON_VAR", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        NESTED_JS_MODULE_PROHIBITED = new KtDiagnosticFactory0("NESTED_JS_MODULE_PROHIBITED", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        NESTED_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("NESTED_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firWebCommonErrors.getRendererFactory());
        WRONG_EXTERNAL_DECLARATION = new KtDiagnosticFactory1<>("WRONG_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firWebCommonErrors.getRendererFactory());
        NESTED_CLASS_IN_EXTERNAL_INTERFACE = new KtDiagnosticFactory0("NESTED_CLASS_IN_EXTERNAL_INTERFACE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firWebCommonErrors.getRendererFactory());
        INLINE_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("INLINE_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firWebCommonErrors.getRendererFactory());
        NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE = new KtDiagnosticFactory0("NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER = new KtDiagnosticFactory0("EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtParameter.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_ANONYMOUS_INITIALIZER = new KtDiagnosticFactory0("EXTERNAL_ANONYMOUS_INITIALIZER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtAnonymousInitializer.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_DELEGATION = new KtDiagnosticFactory0("EXTERNAL_DELEGATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_DELEGATED_CONSTRUCTOR_CALL = new KtDiagnosticFactory0("EXTERNAL_DELEGATED_CONSTRUCTOR_CALL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        WRONG_BODY_OF_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("WRONG_BODY_OF_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        WRONG_INITIALIZER_OF_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("WRONG_INITIALIZER_OF_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        WRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER = new KtDiagnosticFactory0("WRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        CANNOT_CHECK_FOR_EXTERNAL_INTERFACE = new KtDiagnosticFactory1<>("CANNOT_CHECK_FOR_EXTERNAL_INTERFACE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        Severity severity2 = Severity.WARNING;
        UNCHECKED_CAST_TO_EXTERNAL_INTERFACE = new KtDiagnosticFactory2<>("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_INTERFACE_AS_CLASS_LITERAL = new KtDiagnosticFactory0("EXTERNAL_INTERFACE_AS_CLASS_LITERAL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT = new KtDiagnosticFactory1<>("EXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        NAMED_COMPANION_IN_EXTERNAL_INTERFACE = new KtDiagnosticFactory0("NAMED_COMPANION_IN_EXTERNAL_INTERFACE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        CALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("CALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firWebCommonErrors.getRendererFactory());
        EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE = new KtDiagnosticFactory1<>("EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE = new KtDiagnosticFactory1<>("NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        NESTED_JS_EXPORT = new KtDiagnosticFactory0("NESTED_JS_EXPORT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        MULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE = new KtDiagnosticFactory0("MULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        WRONG_JS_EXPORT_TARGET_VISIBILITY = new KtDiagnosticFactory0("WRONG_JS_EXPORT_TARGET_VISIBILITY", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        JSCODE_ARGUMENT_NON_CONST_EXPRESSION = new KtDiagnosticFactory0("JSCODE_ARGUMENT_NON_CONST_EXPRESSION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
        UNSUPPORTED_REFLECTION_API = new KtDiagnosticFactory1<>("UNSUPPORTED_REFLECTION_API", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firWebCommonErrors.getRendererFactory());
    }

    private FirWebCommonErrors() {
    }

    public final KtDiagnosticFactory0 getCALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION() {
        return CALL_TO_DEFINED_EXTERNALLY_FROM_NON_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getCANNOT_CHECK_FOR_EXTERNAL_INTERFACE() {
        return CANNOT_CHECK_FOR_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_ANONYMOUS_INITIALIZER() {
        return EXTERNAL_ANONYMOUS_INITIALIZER;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER() {
        return EXTERNAL_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DELEGATED_CONSTRUCTOR_CALL() {
        return EXTERNAL_DELEGATED_CONSTRUCTOR_CALL;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_DELEGATION() {
        return EXTERNAL_DELEGATION;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_INTERFACE_AS_CLASS_LITERAL() {
        return EXTERNAL_INTERFACE_AS_CLASS_LITERAL;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getEXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT() {
        return EXTERNAL_INTERFACE_AS_REIFIED_TYPE_ARGUMENT;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getEXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE() {
        return EXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE;
    }

    public final KtDiagnosticFactory0 getINLINE_EXTERNAL_DECLARATION() {
        return INLINE_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getJSCODE_ARGUMENT_NON_CONST_EXPRESSION() {
        return JSCODE_ARGUMENT_NON_CONST_EXPRESSION;
    }

    public final KtDiagnosticFactory0 getJS_MODULE_PROHIBITED_ON_VAR() {
        return JS_MODULE_PROHIBITED_ON_VAR;
    }

    public final KtDiagnosticFactory0 getMULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE() {
        return MULTIPLE_JS_EXPORT_DEFAULT_IN_ONE_FILE;
    }

    public final KtDiagnosticFactory0 getNAMED_COMPANION_IN_EXTERNAL_INTERFACE() {
        return NAMED_COMPANION_IN_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory0 getNESTED_CLASS_IN_EXTERNAL_INTERFACE() {
        return NESTED_CLASS_IN_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory0 getNESTED_EXTERNAL_DECLARATION() {
        return NESTED_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getNESTED_JS_EXPORT() {
        return NESTED_JS_EXPORT;
    }

    public final KtDiagnosticFactory0 getNESTED_JS_MODULE_PROHIBITED() {
        return NESTED_JS_MODULE_PROHIBITED;
    }

    public final KtDiagnosticFactory0 getNON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE() {
        return NON_ABSTRACT_MEMBER_OF_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getNON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE() {
        return NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirWebCommonErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getUNCHECKED_CAST_TO_EXTERNAL_INTERFACE() {
        return UNCHECKED_CAST_TO_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory1<String> getUNSUPPORTED_REFLECTION_API() {
        return UNSUPPORTED_REFLECTION_API;
    }

    public final KtDiagnosticFactory0 getWRONG_BODY_OF_EXTERNAL_DECLARATION() {
        return WRONG_BODY_OF_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getWRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER() {
        return WRONG_DEFAULT_VALUE_FOR_EXTERNAL_FUN_PARAMETER;
    }

    public final KtDiagnosticFactory1<String> getWRONG_EXTERNAL_DECLARATION() {
        return WRONG_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getWRONG_INITIALIZER_OF_EXTERNAL_DECLARATION() {
        return WRONG_INITIALIZER_OF_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getWRONG_JS_EXPORT_TARGET_VISIBILITY() {
        return WRONG_JS_EXPORT_TARGET_VISIBILITY;
    }

    public final KtDiagnosticFactory0 getWRONG_JS_QUALIFIER() {
        return WRONG_JS_QUALIFIER;
    }
}
