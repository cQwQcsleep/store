package org.jetbrains.kotlin.fir.analysis.diagnostics.p000native;

import com.intellij.psi.PsiElement;
import java.util.Collection;
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
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010m\u001a\u00020nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\fR\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R'\u0010 \u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0!¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0007R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\fR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\fR\u0011\u0010-\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0007R'\u0010/\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0!¢\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0011\u00101\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0007R\u0011\u00103\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0007R\u0011\u00105\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0007R\u0011\u00107\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0007R\u0011\u00109\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0007R\u0011\u0010;\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0007R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\t¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\fR\u001d\u0010@\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020>0!¢\u0006\b\n\u0000\u001a\u0004\bA\u0010$R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020>0\t¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\fR\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020>0\t¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\fR\u0011\u0010F\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u0017\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\fR\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001c0\t¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\fR\u001d\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020>0!¢\u0006\b\n\u0000\u001a\u0004\bM\u0010$R\u001d\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020>0!¢\u0006\b\n\u0000\u001a\u0004\bO\u0010$R\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\fR\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\fR\u0017\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\fR!\u0010V\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\fR\u0011\u0010X\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0007R\u0017\u0010Z\u001a\b\u0012\u0004\u0012\u00020[0\t¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\fR\u0017\u0010]\u001a\b\u0012\u0004\u0012\u00020>0\t¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\fR\u001b\u0010_\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\t¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\fR\u001b\u0010a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\t¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\fR\u001b\u0010c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\t¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\fR\u001b\u0010e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\t¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\fR\u0011\u0010g\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0007R\u0011\u0010i\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0007R\u0011\u0010k\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0007¨\u0006o"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/native/FirNativeErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "THROWS_LIST_EMPTY", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getTHROWS_LIST_EMPTY", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "INCOMPATIBLE_THROWS_OVERRIDE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getINCOMPATIBLE_THROWS_OVERRIDE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "INCOMPATIBLE_THROWS_INHERITED", Argument.Delimiters.none, "getINCOMPATIBLE_THROWS_INHERITED", "MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND", "Lorg/jetbrains/kotlin/name/FqName;", "getMISSING_EXCEPTION_IN_THROWS_ON_SUSPEND", "INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY", "getINAPPLICABLE_SHARED_IMMUTABLE_PROPERTY", "INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL", "getINAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL", "INAPPLICABLE_THREAD_LOCAL", "getINAPPLICABLE_THREAD_LOCAL", "INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL", "getINAPPLICABLE_THREAD_LOCAL_TOP_LEVEL", "INVALID_CHARACTERS_NATIVE_ERROR", Argument.Delimiters.none, "getINVALID_CHARACTERS_NATIVE_ERROR", "REDUNDANT_SWIFT_REFINEMENT", "getREDUNDANT_SWIFT_REFINEMENT", "INCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getINCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "INAPPLICABLE_OBJC_NAME", "getINAPPLICABLE_OBJC_NAME", "INVALID_OBJC_NAME", "getINVALID_OBJC_NAME", "INVALID_OBJC_NAME_CHARS", "getINVALID_OBJC_NAME_CHARS", "INVALID_OBJC_NAME_FIRST_CHAR", "getINVALID_OBJC_NAME_FIRST_CHAR", "EMPTY_OBJC_NAME", "getEMPTY_OBJC_NAME", "INCOMPATIBLE_OBJC_NAME_OVERRIDE", "getINCOMPATIBLE_OBJC_NAME_OVERRIDE", "INAPPLICABLE_EXACT_OBJC_NAME", "getINAPPLICABLE_EXACT_OBJC_NAME", "MISSING_EXACT_OBJC_NAME", "getMISSING_EXACT_OBJC_NAME", "NON_LITERAL_OBJC_NAME_ARG", "getNON_LITERAL_OBJC_NAME_ARG", "INVALID_OBJC_HIDES_TARGETS", "getINVALID_OBJC_HIDES_TARGETS", "INVALID_REFINES_IN_SWIFT_TARGETS", "getINVALID_REFINES_IN_SWIFT_TARGETS", "SUBTYPE_OF_HIDDEN_FROM_OBJC", "getSUBTYPE_OF_HIDDEN_FROM_OBJC", "CANNOT_CHECK_FOR_FORWARD_DECLARATION", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getCANNOT_CHECK_FOR_FORWARD_DECLARATION", "UNCHECKED_CAST_TO_FORWARD_DECLARATION", "getUNCHECKED_CAST_TO_FORWARD_DECLARATION", "FORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT", "getFORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT", "FORWARD_DECLARATION_AS_CLASS_LITERAL", "getFORWARD_DECLARATION_AS_CLASS_LITERAL", "TWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE", "getTWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE", "PROPERTY_MUST_BE_VAR", "getPROPERTY_MUST_BE_VAR", "MUST_NOT_HAVE_EXTENSION_RECEIVER", "getMUST_NOT_HAVE_EXTENSION_RECEIVER", "MUST_BE_OBJC_OBJECT_TYPE", "getMUST_BE_OBJC_OBJECT_TYPE", "MUST_BE_UNIT_TYPE", "getMUST_BE_UNIT_TYPE", "CONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER", "getCONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER", "CONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR", "getCONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR", "CONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS", "getCONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS", "CONFLICTING_OBJC_OVERLOADS", "getCONFLICTING_OBJC_OVERLOADS", "INAPPLICABLE_OBJC_OVERRIDE", "getINAPPLICABLE_OBJC_OVERRIDE", "NATIVE_SPECIFIC_ATOMIC", "Lorg/jetbrains/kotlin/name/Name;", "getNATIVE_SPECIFIC_ATOMIC", "IDENTITY_HASH_CODE_ON_VALUE_TYPE", "getIDENTITY_HASH_CODE_ON_VALUE_TYPE", "VARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED", "getVARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED", "OVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED", "getOVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED", "CALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED", "getCALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED", "CALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED", "getCALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED", "STRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS", "getSTRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS", "VARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", "getVARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", "VARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", "getVARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeErrors extends KtDiagnosticsContainer {
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> CALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> CALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED;
    private static final KtDiagnosticFactory1<ConeKotlinType> CANNOT_CHECK_FOR_FORWARD_DECLARATION;
    private static final KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> CONFLICTING_OBJC_OVERLOADS;
    private static final KtDiagnosticFactory1<FqName> CONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR;
    private static final KtDiagnosticFactory1<FqName> CONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS;
    private static final KtDiagnosticFactory1<FqName> CONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER;
    private static final KtDiagnosticFactory0 EMPTY_OBJC_NAME;
    private static final KtDiagnosticFactory1<ConeKotlinType> FORWARD_DECLARATION_AS_CLASS_LITERAL;
    private static final KtDiagnosticFactory1<ConeKotlinType> FORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT;
    private static final KtDiagnosticFactory1<ConeKotlinType> IDENTITY_HASH_CODE_ON_VALUE_TYPE;
    private static final KtDiagnosticFactory0 INAPPLICABLE_EXACT_OBJC_NAME;
    private static final KtDiagnosticFactory0 INAPPLICABLE_OBJC_NAME;
    private static final KtDiagnosticFactory0 INAPPLICABLE_OBJC_OVERRIDE;
    private static final KtDiagnosticFactory0 INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY;
    private static final KtDiagnosticFactory0 INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL;
    private static final KtDiagnosticFactory0 INAPPLICABLE_THREAD_LOCAL;
    private static final KtDiagnosticFactory0 INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL;
    private static final KtDiagnosticFactory2<FirBasedSymbol<?>, Collection<FirRegularClassSymbol>> INCOMPATIBLE_OBJC_NAME_OVERRIDE;
    private static final KtDiagnosticFactory2<FirBasedSymbol<?>, Collection<FirRegularClassSymbol>> INCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE;
    private static final KtDiagnosticFactory1<Collection<FirRegularClassSymbol>> INCOMPATIBLE_THROWS_INHERITED;
    private static final KtDiagnosticFactory1<FirRegularClassSymbol> INCOMPATIBLE_THROWS_OVERRIDE;
    public static final FirNativeErrors INSTANCE;
    private static final KtDiagnosticFactory1<String> INVALID_CHARACTERS_NATIVE_ERROR;
    private static final KtDiagnosticFactory0 INVALID_OBJC_HIDES_TARGETS;
    private static final KtDiagnosticFactory0 INVALID_OBJC_NAME;
    private static final KtDiagnosticFactory1<String> INVALID_OBJC_NAME_CHARS;
    private static final KtDiagnosticFactory1<String> INVALID_OBJC_NAME_FIRST_CHAR;
    private static final KtDiagnosticFactory0 INVALID_REFINES_IN_SWIFT_TARGETS;
    private static final KtDiagnosticFactory0 MISSING_EXACT_OBJC_NAME;
    private static final KtDiagnosticFactory1<FqName> MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND;
    private static final KtDiagnosticFactory2<String, ConeKotlinType> MUST_BE_OBJC_OBJECT_TYPE;
    private static final KtDiagnosticFactory2<String, ConeKotlinType> MUST_BE_UNIT_TYPE;
    private static final KtDiagnosticFactory1<String> MUST_NOT_HAVE_EXTENSION_RECEIVER;
    private static final KtDiagnosticFactory1<Name> NATIVE_SPECIFIC_ATOMIC;
    private static final KtDiagnosticFactory0 NON_LITERAL_OBJC_NAME_ARG;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> OVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED;
    private static final KtDiagnosticFactory1<FqName> PROPERTY_MUST_BE_VAR;
    private static final KtDiagnosticFactory0 REDUNDANT_SWIFT_REFINEMENT;
    private static final KtDiagnosticFactory0 STRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS;
    private static final KtDiagnosticFactory0 SUBTYPE_OF_HIDDEN_FROM_OBJC;
    private static final KtDiagnosticFactory0 THROWS_LIST_EMPTY;
    private static final KtDiagnosticFactory0 TWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE;
    private static final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> UNCHECKED_CAST_TO_FORWARD_DECLARATION;
    private static final KtDiagnosticFactory0 VARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> VARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED;
    private static final KtDiagnosticFactory0 VARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF;

    static {
        FirNativeErrors firNativeErrors = new FirNativeErrors();
        INSTANCE = firNativeErrors;
        Severity severity = Severity.ERROR;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        THROWS_LIST_EMPTY = new KtDiagnosticFactory0("THROWS_LIST_EMPTY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INCOMPATIBLE_THROWS_OVERRIDE = new KtDiagnosticFactory1<>("INCOMPATIBLE_THROWS_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INCOMPATIBLE_THROWS_INHERITED = new KtDiagnosticFactory1<>("INCOMPATIBLE_THROWS_INHERITED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firNativeErrors.getRendererFactory());
        MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND = new KtDiagnosticFactory1<>("MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY = new KtDiagnosticFactory0("INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL = new KtDiagnosticFactory0("INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_THREAD_LOCAL = new KtDiagnosticFactory0("INAPPLICABLE_THREAD_LOCAL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL = new KtDiagnosticFactory0("INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_CHARACTERS_NATIVE_ERROR = new KtDiagnosticFactory1<>("INVALID_CHARACTERS_NATIVE_ERROR", severity, sourceElementPositioningStrategies.getNAME_IDENTIFIER(), Reflection.getOrCreateKotlinClass(PsiElement.class), firNativeErrors.getRendererFactory());
        REDUNDANT_SWIFT_REFINEMENT = new KtDiagnosticFactory0("REDUNDANT_SWIFT_REFINEMENT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE = new KtDiagnosticFactory2<>("INCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_OBJC_NAME = new KtDiagnosticFactory0("INAPPLICABLE_OBJC_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_OBJC_NAME = new KtDiagnosticFactory0("INVALID_OBJC_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_OBJC_NAME_CHARS = new KtDiagnosticFactory1<>("INVALID_OBJC_NAME_CHARS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_OBJC_NAME_FIRST_CHAR = new KtDiagnosticFactory1<>("INVALID_OBJC_NAME_FIRST_CHAR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        EMPTY_OBJC_NAME = new KtDiagnosticFactory0("EMPTY_OBJC_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INCOMPATIBLE_OBJC_NAME_OVERRIDE = new KtDiagnosticFactory2<>("INCOMPATIBLE_OBJC_NAME_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_EXACT_OBJC_NAME = new KtDiagnosticFactory0("INAPPLICABLE_EXACT_OBJC_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        MISSING_EXACT_OBJC_NAME = new KtDiagnosticFactory0("MISSING_EXACT_OBJC_NAME", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        NON_LITERAL_OBJC_NAME_ARG = new KtDiagnosticFactory0("NON_LITERAL_OBJC_NAME_ARG", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_OBJC_HIDES_TARGETS = new KtDiagnosticFactory0("INVALID_OBJC_HIDES_TARGETS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        INVALID_REFINES_IN_SWIFT_TARGETS = new KtDiagnosticFactory0("INVALID_REFINES_IN_SWIFT_TARGETS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        SUBTYPE_OF_HIDDEN_FROM_OBJC = new KtDiagnosticFactory0("SUBTYPE_OF_HIDDEN_FROM_OBJC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CANNOT_CHECK_FOR_FORWARD_DECLARATION = new KtDiagnosticFactory1<>("CANNOT_CHECK_FOR_FORWARD_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        Severity severity2 = Severity.WARNING;
        UNCHECKED_CAST_TO_FORWARD_DECLARATION = new KtDiagnosticFactory2<>("UNCHECKED_CAST_TO_FORWARD_DECLARATION", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        FORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT = new KtDiagnosticFactory1<>("FORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        FORWARD_DECLARATION_AS_CLASS_LITERAL = new KtDiagnosticFactory1<>("FORWARD_DECLARATION_AS_CLASS_LITERAL", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        TWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE = new KtDiagnosticFactory0("TWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        PROPERTY_MUST_BE_VAR = new KtDiagnosticFactory1<>("PROPERTY_MUST_BE_VAR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        MUST_NOT_HAVE_EXTENSION_RECEIVER = new KtDiagnosticFactory1<>("MUST_NOT_HAVE_EXTENSION_RECEIVER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        MUST_BE_OBJC_OBJECT_TYPE = new KtDiagnosticFactory2<>("MUST_BE_OBJC_OBJECT_TYPE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        MUST_BE_UNIT_TYPE = new KtDiagnosticFactory2<>("MUST_BE_UNIT_TYPE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER = new KtDiagnosticFactory1<>("CONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR = new KtDiagnosticFactory1<>("CONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS = new KtDiagnosticFactory1<>("CONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CONFLICTING_OBJC_OVERLOADS = new KtDiagnosticFactory1<>("CONFLICTING_OBJC_OVERLOADS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firNativeErrors.getRendererFactory());
        INAPPLICABLE_OBJC_OVERRIDE = new KtDiagnosticFactory0("INAPPLICABLE_OBJC_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firNativeErrors.getRendererFactory());
        NATIVE_SPECIFIC_ATOMIC = new KtDiagnosticFactory1<>("NATIVE_SPECIFIC_ATOMIC", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtTypeReference.class), firNativeErrors.getRendererFactory());
        IDENTITY_HASH_CODE_ON_VALUE_TYPE = new KtDiagnosticFactory1<>("IDENTITY_HASH_CODE_ON_VALUE_TYPE", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        VARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED = new KtDiagnosticFactory1<>("VARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        OVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED = new KtDiagnosticFactory1<>("OVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED", severity, sourceElementPositioningStrategies.getOVERRIDE_MODIFIER(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED = new KtDiagnosticFactory1<>("CALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        CALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED = new KtDiagnosticFactory1<>("CALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        STRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS = new KtDiagnosticFactory0("STRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        VARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF = new KtDiagnosticFactory0("VARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
        VARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF = new KtDiagnosticFactory0("VARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firNativeErrors.getRendererFactory());
    }

    private FirNativeErrors() {
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getCALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED() {
        return CALLABLE_REFERENCES_TO_VARIADIC_C_FUNCTIONS_ARE_NOT_SUPPORTED;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getCALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED() {
        return CALLABLE_REFERENCES_TO_VARIADIC_OBJECTIVE_C_METHODS_ARE_NOT_SUPPORTED;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getCANNOT_CHECK_FOR_FORWARD_DECLARATION() {
        return CANNOT_CHECK_FOR_FORWARD_DECLARATION;
    }

    public final KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> getCONFLICTING_OBJC_OVERLOADS() {
        return CONFLICTING_OBJC_OVERLOADS;
    }

    public final KtDiagnosticFactory1<FqName> getCONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR() {
        return CONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR;
    }

    public final KtDiagnosticFactory1<FqName> getCONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS() {
        return CONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS;
    }

    public final KtDiagnosticFactory1<FqName> getCONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER() {
        return CONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER;
    }

    public final KtDiagnosticFactory0 getEMPTY_OBJC_NAME() {
        return EMPTY_OBJC_NAME;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getFORWARD_DECLARATION_AS_CLASS_LITERAL() {
        return FORWARD_DECLARATION_AS_CLASS_LITERAL;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getFORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT() {
        return FORWARD_DECLARATION_AS_REIFIED_TYPE_ARGUMENT;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getIDENTITY_HASH_CODE_ON_VALUE_TYPE() {
        return IDENTITY_HASH_CODE_ON_VALUE_TYPE;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_EXACT_OBJC_NAME() {
        return INAPPLICABLE_EXACT_OBJC_NAME;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_OBJC_NAME() {
        return INAPPLICABLE_OBJC_NAME;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_OBJC_OVERRIDE() {
        return INAPPLICABLE_OBJC_OVERRIDE;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_SHARED_IMMUTABLE_PROPERTY() {
        return INAPPLICABLE_SHARED_IMMUTABLE_PROPERTY;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL() {
        return INAPPLICABLE_SHARED_IMMUTABLE_TOP_LEVEL;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_THREAD_LOCAL() {
        return INAPPLICABLE_THREAD_LOCAL;
    }

    public final KtDiagnosticFactory0 getINAPPLICABLE_THREAD_LOCAL_TOP_LEVEL() {
        return INAPPLICABLE_THREAD_LOCAL_TOP_LEVEL;
    }

    public final KtDiagnosticFactory2<FirBasedSymbol<?>, Collection<FirRegularClassSymbol>> getINCOMPATIBLE_OBJC_NAME_OVERRIDE() {
        return INCOMPATIBLE_OBJC_NAME_OVERRIDE;
    }

    public final KtDiagnosticFactory2<FirBasedSymbol<?>, Collection<FirRegularClassSymbol>> getINCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE() {
        return INCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE;
    }

    public final KtDiagnosticFactory1<Collection<FirRegularClassSymbol>> getINCOMPATIBLE_THROWS_INHERITED() {
        return INCOMPATIBLE_THROWS_INHERITED;
    }

    public final KtDiagnosticFactory1<FirRegularClassSymbol> getINCOMPATIBLE_THROWS_OVERRIDE() {
        return INCOMPATIBLE_THROWS_OVERRIDE;
    }

    public final KtDiagnosticFactory1<String> getINVALID_CHARACTERS_NATIVE_ERROR() {
        return INVALID_CHARACTERS_NATIVE_ERROR;
    }

    public final KtDiagnosticFactory0 getINVALID_OBJC_HIDES_TARGETS() {
        return INVALID_OBJC_HIDES_TARGETS;
    }

    public final KtDiagnosticFactory0 getINVALID_OBJC_NAME() {
        return INVALID_OBJC_NAME;
    }

    public final KtDiagnosticFactory1<String> getINVALID_OBJC_NAME_CHARS() {
        return INVALID_OBJC_NAME_CHARS;
    }

    public final KtDiagnosticFactory1<String> getINVALID_OBJC_NAME_FIRST_CHAR() {
        return INVALID_OBJC_NAME_FIRST_CHAR;
    }

    public final KtDiagnosticFactory0 getINVALID_REFINES_IN_SWIFT_TARGETS() {
        return INVALID_REFINES_IN_SWIFT_TARGETS;
    }

    public final KtDiagnosticFactory0 getMISSING_EXACT_OBJC_NAME() {
        return MISSING_EXACT_OBJC_NAME;
    }

    public final KtDiagnosticFactory1<FqName> getMISSING_EXCEPTION_IN_THROWS_ON_SUSPEND() {
        return MISSING_EXCEPTION_IN_THROWS_ON_SUSPEND;
    }

    public final KtDiagnosticFactory2<String, ConeKotlinType> getMUST_BE_OBJC_OBJECT_TYPE() {
        return MUST_BE_OBJC_OBJECT_TYPE;
    }

    public final KtDiagnosticFactory2<String, ConeKotlinType> getMUST_BE_UNIT_TYPE() {
        return MUST_BE_UNIT_TYPE;
    }

    public final KtDiagnosticFactory1<String> getMUST_NOT_HAVE_EXTENSION_RECEIVER() {
        return MUST_NOT_HAVE_EXTENSION_RECEIVER;
    }

    public final KtDiagnosticFactory1<Name> getNATIVE_SPECIFIC_ATOMIC() {
        return NATIVE_SPECIFIC_ATOMIC;
    }

    public final KtDiagnosticFactory0 getNON_LITERAL_OBJC_NAME_ARG() {
        return NON_LITERAL_OBJC_NAME_ARG;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getOVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED() {
        return OVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED;
    }

    public final KtDiagnosticFactory1<FqName> getPROPERTY_MUST_BE_VAR() {
        return PROPERTY_MUST_BE_VAR;
    }

    public final KtDiagnosticFactory0 getREDUNDANT_SWIFT_REFINEMENT() {
        return REDUNDANT_SWIFT_REFINEMENT;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirNativeErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory0 getSTRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS() {
        return STRING_AS_VARIADIC_OBJC_PARAM_IS_AMBIGUOUS;
    }

    public final KtDiagnosticFactory0 getSUBTYPE_OF_HIDDEN_FROM_OBJC() {
        return SUBTYPE_OF_HIDDEN_FROM_OBJC;
    }

    public final KtDiagnosticFactory0 getTHROWS_LIST_EMPTY() {
        return THROWS_LIST_EMPTY;
    }

    public final KtDiagnosticFactory0 getTWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE() {
        return TWO_OR_LESS_PARAMETERS_ARE_SUPPORTED_HERE;
    }

    public final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getUNCHECKED_CAST_TO_FORWARD_DECLARATION() {
        return UNCHECKED_CAST_TO_FORWARD_DECLARATION;
    }

    public final KtDiagnosticFactory0 getVARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF() {
        return VARIADIC_C_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getVARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED() {
        return VARIADIC_FUNCTION_POINTERS_ARE_NOT_SUPPORTED;
    }

    public final KtDiagnosticFactory0 getVARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF() {
        return VARIADIC_OBJC_SPREAD_IS_SUPPORTED_ONLY_FOR_ARRAYOF;
    }
}
