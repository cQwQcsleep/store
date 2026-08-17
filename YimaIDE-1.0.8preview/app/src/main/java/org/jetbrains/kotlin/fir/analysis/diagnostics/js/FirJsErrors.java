package org.jetbrains.kotlin.fir.analysis.diagnostics.js;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001b\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0007R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00170\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000eR\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u000eR\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R'\u00104\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f050\u001a¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001dR1\u00107\u001a\"\u0012\u0004\u0012\u00020\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0508¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010;\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0007R\u0011\u0010=\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0007R\u001b\u0010?\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030@0\u000b¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u000eR\u0011\u0010B\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0007R\u0011\u0010D\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0007R\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u000b¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u000eR\u0011\u0010I\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0007R\u0011\u0010K\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0007R\u0011\u0010M\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0007R\u0011\u0010O\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0007R\u0011\u0010Q\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0007R%\u0010S\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030T\u0012\b\u0012\u0006\u0012\u0002\b\u00030T0\u001a¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\u001dR\u0017\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\u000eR\u0017\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u000eR\u001d\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u001dR\u0017\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\u000eR\u0011\u0010^\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0007R\u0011\u0010`\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0007R\u001b\u0010b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030T0c¢\u0006\b\n\u0000\u001a\u0004\bd\u0010eR\u0011\u0010f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bg\u0010\u0007R\u0011\u0010h\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\u0007R\u0011\u0010j\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bk\u0010\u0007R\u0017\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\bm\u0010\u000eR\u0011\u0010n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\u0007R\u0011\u0010p\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\u0007R\u0011\u0010r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bs\u0010\u0007R\u0011\u0010t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bu\u0010\u0007R\u0011\u0010v\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0007R\u0011\u0010x\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\by\u0010\u0007R\u0011\u0010z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\u0007R\u0011\u0010|\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b}\u0010\u0007R\u0017\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010\u000eR\u0013\u0010\u0080\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0007R\u0013\u0010\u0082\u0001\u001a\u00020\u0005¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0007¨\u0006\u0086\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/js/FirJsErrors;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "JS_MODULE_PROHIBITED_ON_NON_NATIVE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getJS_MODULE_PROHIBITED_ON_NON_NATIVE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "CALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE", "getCALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE", "CALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getCALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "CALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM", "getCALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM", "RUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION", "getRUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION", "NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN", "NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER", Argument.Delimiters.none, "getNATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER", "NATIVE_INDEXER_WRONG_PARAMETER_COUNT", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", Argument.Delimiters.none, "getNATIVE_INDEXER_WRONG_PARAMETER_COUNT", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "NATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS", "getNATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS", "NATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE", "getNATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE", "NATIVE_SETTER_WRONG_RETURN_TYPE", "getNATIVE_SETTER_WRONG_RETURN_TYPE", "JS_NAME_IS_NOT_ON_ALL_ACCESSORS", "getJS_NAME_IS_NOT_ON_ALL_ACCESSORS", "JS_NAME_PROHIBITED_FOR_NAMED_NATIVE", "getJS_NAME_PROHIBITED_FOR_NAMED_NATIVE", "JS_NAME_PROHIBITED_FOR_OVERRIDE", "getJS_NAME_PROHIBITED_FOR_OVERRIDE", "JS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED", "getJS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED", "JS_NAME_ON_ACCESSOR_AND_PROPERTY", "getJS_NAME_ON_ACCESSOR_AND_PROPERTY", "JS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY", "getJS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY", "JS_BUILTIN_NAME_CLASH", "getJS_BUILTIN_NAME_CLASH", "NAME_CONTAINS_ILLEGAL_CHARS", "getNAME_CONTAINS_ILLEGAL_CHARS", "JS_NAME_CLASH", Argument.Delimiters.none, "getJS_NAME_CLASH", "JS_FAKE_NAME_CLASH", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "getJS_FAKE_NAME_CLASH", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "JS_SYMBOL_ON_TOP_LEVEL_DECLARATION", "getJS_SYMBOL_ON_TOP_LEVEL_DECLARATION", "JS_SYMBOL_PROHIBITED_FOR_OVERRIDE", "getJS_SYMBOL_PROHIBITED_FOR_OVERRIDE", "WRONG_MULTIPLE_INHERITANCE", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getWRONG_MULTIPLE_INHERITANCE", "IMPLEMENTING_FUNCTION_INTERFACE", "getIMPLEMENTING_FUNCTION_INTERFACE", "OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS", "getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS", "OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE", "EXTERNAL_ENUM_ENTRY_WITH_BODY", "getEXTERNAL_ENUM_ENTRY_WITH_BODY", "ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING", "getENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING", "INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING", "getINLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING", "INLINE_CLASS_IN_EXTERNAL_DECLARATION", "getINLINE_CLASS_IN_EXTERNAL_DECLARATION", "EXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION", "getEXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION", "JS_EXTERNAL_INHERITORS_ONLY", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getJS_EXTERNAL_INHERITORS_ONLY", "JS_EXTERNAL_ARGUMENT", "getJS_EXTERNAL_ARGUMENT", "WRONG_EXPORTED_DECLARATION", "getWRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE", "getNON_EXPORTABLE_TYPE", "NON_CONSUMABLE_EXPORTED_IDENTIFIER", "getNON_CONSUMABLE_EXPORTED_IDENTIFIER", "NAMED_COMPANION_IN_EXPORTED_INTERFACE", "getNAMED_COMPANION_IN_EXPORTED_INTERFACE", "NOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED", "getNOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED", "EXPOSED_NOT_EXPORTED_SUPER_INTERFACE", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "getEXPOSED_NOT_EXPORTED_SUPER_INTERFACE", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "DELEGATION_BY_DYNAMIC", "getDELEGATION_BY_DYNAMIC", "PROPERTY_DELEGATION_BY_DYNAMIC", "getPROPERTY_DELEGATION_BY_DYNAMIC", "SPREAD_OPERATOR_IN_DYNAMIC_CALL", "getSPREAD_OPERATOR_IN_DYNAMIC_CALL", "WRONG_OPERATION_WITH_DYNAMIC", "getWRONG_OPERATION_WITH_DYNAMIC", "JS_STATIC_NOT_IN_CLASS_COMPANION", "getJS_STATIC_NOT_IN_CLASS_COMPANION", "JS_STATIC_ON_NON_PUBLIC_MEMBER", "getJS_STATIC_ON_NON_PUBLIC_MEMBER", "JS_STATIC_ON_CONST", "getJS_STATIC_ON_CONST", "JS_NO_RUNTIME_WRONG_TARGET", "getJS_NO_RUNTIME_WRONG_TARGET", "JS_NO_RUNTIME_FORBIDDEN_IS_CHECK", "getJS_NO_RUNTIME_FORBIDDEN_IS_CHECK", "JS_NO_RUNTIME_FORBIDDEN_AS_CAST", "getJS_NO_RUNTIME_FORBIDDEN_AS_CAST", "JS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE", "getJS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE", "JS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE", "getJS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE", "JS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", "getJS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", "JS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME", "getJS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME", "JS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT", "getJS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsErrors extends KtDiagnosticsContainer {
    private static final KtDiagnosticFactory0 CALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> CALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM;
    private static final KtDiagnosticFactory1<FirBasedSymbol<?>> CALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM;
    private static final KtDiagnosticFactory0 DELEGATION_BY_DYNAMIC;
    private static final KtDiagnosticFactory0 ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING;
    private static final KtDiagnosticFactoryForDeprecation1<FirClassLikeSymbol<?>> EXPOSED_NOT_EXPORTED_SUPER_INTERFACE;
    private static final KtDiagnosticFactory0 EXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 EXTERNAL_ENUM_ENTRY_WITH_BODY;
    private static final KtDiagnosticFactory0 IMPLEMENTING_FUNCTION_INTERFACE;
    private static final KtDiagnosticFactory0 INLINE_CLASS_IN_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING;
    public static final FirJsErrors INSTANCE;
    private static final KtDiagnosticFactory0 JS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME;
    private static final KtDiagnosticFactory1<String> JS_BUILTIN_NAME_CLASH;
    private static final KtDiagnosticFactory1<ConeKotlinType> JS_EXTERNAL_ARGUMENT;
    private static final KtDiagnosticFactory2<FirClassLikeSymbol<?>, FirClassLikeSymbol<?>> JS_EXTERNAL_INHERITORS_ONLY;
    private static final KtDiagnosticFactory3<String, FirBasedSymbol<?>, Collection<FirBasedSymbol<?>>> JS_FAKE_NAME_CLASH;
    private static final KtDiagnosticFactory0 JS_MODULE_PROHIBITED_ON_NON_NATIVE;
    private static final KtDiagnosticFactory2<String, Collection<FirBasedSymbol<?>>> JS_NAME_CLASH;
    private static final KtDiagnosticFactory0 JS_NAME_IS_NOT_ON_ALL_ACCESSORS;
    private static final KtDiagnosticFactory0 JS_NAME_ON_ACCESSOR_AND_PROPERTY;
    private static final KtDiagnosticFactory0 JS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED;
    private static final KtDiagnosticFactory0 JS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY;
    private static final KtDiagnosticFactory0 JS_NAME_PROHIBITED_FOR_NAMED_NATIVE;
    private static final KtDiagnosticFactory0 JS_NAME_PROHIBITED_FOR_OVERRIDE;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_FORBIDDEN_AS_CAST;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_FORBIDDEN_IS_CHECK;
    private static final KtDiagnosticFactory1<ConeKotlinType> JS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE;
    private static final KtDiagnosticFactory0 JS_NO_RUNTIME_WRONG_TARGET;
    private static final KtDiagnosticFactory0 JS_STATIC_NOT_IN_CLASS_COMPANION;
    private static final KtDiagnosticFactory0 JS_STATIC_ON_CONST;
    private static final KtDiagnosticFactory0 JS_STATIC_ON_NON_PUBLIC_MEMBER;
    private static final KtDiagnosticFactory0 JS_SYMBOL_ON_TOP_LEVEL_DECLARATION;
    private static final KtDiagnosticFactory0 JS_SYMBOL_PROHIBITED_FOR_OVERRIDE;
    private static final KtDiagnosticFactory0 NAMED_COMPANION_IN_EXPORTED_INTERFACE;
    private static final KtDiagnosticFactory0 NAME_CONTAINS_ILLEGAL_CHARS;
    private static final KtDiagnosticFactory1<ConeKotlinType> NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN;
    private static final KtDiagnosticFactory0 NATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE;
    private static final KtDiagnosticFactory1<String> NATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS;
    private static final KtDiagnosticFactory1<String> NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER;
    private static final KtDiagnosticFactory2<Integer, String> NATIVE_INDEXER_WRONG_PARAMETER_COUNT;
    private static final KtDiagnosticFactory0 NATIVE_SETTER_WRONG_RETURN_TYPE;
    private static final KtDiagnosticFactory1<String> NON_CONSUMABLE_EXPORTED_IDENTIFIER;
    private static final KtDiagnosticFactory2<String, ConeKotlinType> NON_EXPORTABLE_TYPE;
    private static final KtDiagnosticFactory0 NOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED;
    private static final KtDiagnosticFactory0 OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS;
    private static final KtDiagnosticFactory1<FirNamedFunctionSymbol> OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE;
    private static final KtDiagnosticFactory0 PROPERTY_DELEGATION_BY_DYNAMIC;
    private static final KtDiagnosticFactory0 RUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION;
    private static final KtDiagnosticFactory0 SPREAD_OPERATOR_IN_DYNAMIC_CALL;
    private static final KtDiagnosticFactory1<String> WRONG_EXPORTED_DECLARATION;
    private static final KtDiagnosticFactory1<FirCallableSymbol<?>> WRONG_MULTIPLE_INHERITANCE;
    private static final KtDiagnosticFactory1<String> WRONG_OPERATION_WITH_DYNAMIC;

    static {
        FirJsErrors firJsErrors = new FirJsErrors();
        INSTANCE = firJsErrors;
        Severity severity = Severity.ERROR;
        SourceElementPositioningStrategies sourceElementPositioningStrategies = SourceElementPositioningStrategies.INSTANCE;
        JS_MODULE_PROHIBITED_ON_NON_NATIVE = new KtDiagnosticFactory0("JS_MODULE_PROHIBITED_ON_NON_NATIVE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        CALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE = new KtDiagnosticFactory0("CALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        CALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM = new KtDiagnosticFactory1<>("CALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        CALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM = new KtDiagnosticFactory1<>("CALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        RUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("RUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJsErrors.getRendererFactory());
        NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN = new KtDiagnosticFactory1<>("NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER = new KtDiagnosticFactory1<>("NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NATIVE_INDEXER_WRONG_PARAMETER_COUNT = new KtDiagnosticFactory2<>("NATIVE_INDEXER_WRONG_PARAMETER_COUNT", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS = new KtDiagnosticFactory1<>("NATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE = new KtDiagnosticFactory0("NATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE", severity, sourceElementPositioningStrategies.getDECLARATION_RETURN_TYPE(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJsErrors.getRendererFactory());
        NATIVE_SETTER_WRONG_RETURN_TYPE = new KtDiagnosticFactory0("NATIVE_SETTER_WRONG_RETURN_TYPE", severity, sourceElementPositioningStrategies.getDECLARATION_RETURN_TYPE(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJsErrors.getRendererFactory());
        JS_NAME_IS_NOT_ON_ALL_ACCESSORS = new KtDiagnosticFactory0("JS_NAME_IS_NOT_ON_ALL_ACCESSORS", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_PROHIBITED_FOR_NAMED_NATIVE = new KtDiagnosticFactory0("JS_NAME_PROHIBITED_FOR_NAMED_NATIVE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_PROHIBITED_FOR_OVERRIDE = new KtDiagnosticFactory0("JS_NAME_PROHIBITED_FOR_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED = new KtDiagnosticFactory0("JS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_ON_ACCESSOR_AND_PROPERTY = new KtDiagnosticFactory0("JS_NAME_ON_ACCESSOR_AND_PROPERTY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY = new KtDiagnosticFactory0("JS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_BUILTIN_NAME_CLASH = new KtDiagnosticFactory1<>("JS_BUILTIN_NAME_CLASH", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NAME_CONTAINS_ILLEGAL_CHARS = new KtDiagnosticFactory0("NAME_CONTAINS_ILLEGAL_CHARS", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NAME_CLASH = new KtDiagnosticFactory2<>("JS_NAME_CLASH", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_FAKE_NAME_CLASH = new KtDiagnosticFactory3<>("JS_FAKE_NAME_CLASH", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_SYMBOL_ON_TOP_LEVEL_DECLARATION = new KtDiagnosticFactory0("JS_SYMBOL_ON_TOP_LEVEL_DECLARATION", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_SYMBOL_PROHIBITED_FOR_OVERRIDE = new KtDiagnosticFactory0("JS_SYMBOL_PROHIBITED_FOR_OVERRIDE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        WRONG_MULTIPLE_INHERITANCE = new KtDiagnosticFactory1<>("WRONG_MULTIPLE_INHERITANCE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        IMPLEMENTING_FUNCTION_INTERFACE = new KtDiagnosticFactory0("IMPLEMENTING_FUNCTION_INTERFACE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtClassOrObject.class), firJsErrors.getRendererFactory());
        OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS = new KtDiagnosticFactory0("OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE = new KtDiagnosticFactory1<>("OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        EXTERNAL_ENUM_ENTRY_WITH_BODY = new KtDiagnosticFactory0("EXTERNAL_ENUM_ENTRY_WITH_BODY", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        Severity severity2 = Severity.WARNING;
        ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING = new KtDiagnosticFactory0("ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING", severity2, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJsErrors.getRendererFactory());
        INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING = new KtDiagnosticFactory0("INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING", severity2, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        INLINE_CLASS_IN_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("INLINE_CLASS_IN_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        EXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION = new KtDiagnosticFactory0("EXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION", severity, sourceElementPositioningStrategies.getFUNCTION_TYPE_RECEIVER(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_EXTERNAL_INHERITORS_ONLY = new KtDiagnosticFactory2<>("JS_EXTERNAL_INHERITORS_ONLY", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtDeclaration.class), firJsErrors.getRendererFactory());
        JS_EXTERNAL_ARGUMENT = new KtDiagnosticFactory1<>("JS_EXTERNAL_ARGUMENT", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtExpression.class), firJsErrors.getRendererFactory());
        WRONG_EXPORTED_DECLARATION = new KtDiagnosticFactory1<>("WRONG_EXPORTED_DECLARATION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NON_EXPORTABLE_TYPE = new KtDiagnosticFactory2<>("NON_EXPORTABLE_TYPE", severity2, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NON_CONSUMABLE_EXPORTED_IDENTIFIER = new KtDiagnosticFactory1<>("NON_CONSUMABLE_EXPORTED_IDENTIFIER", severity2, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NAMED_COMPANION_IN_EXPORTED_INTERFACE = new KtDiagnosticFactory0("NAMED_COMPANION_IN_EXPORTED_INTERFACE", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        NOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED = new KtDiagnosticFactory0("NOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        EXPOSED_NOT_EXPORTED_SUPER_INTERFACE = new KtDiagnosticFactoryForDeprecation1<>("EXPOSED_NOT_EXPORTED_SUPER_INTERFACE", LanguageFeature.JsExposedNotExportedSuperInterfaceApiByExportedOne, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        DELEGATION_BY_DYNAMIC = new KtDiagnosticFactory0("DELEGATION_BY_DYNAMIC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        PROPERTY_DELEGATION_BY_DYNAMIC = new KtDiagnosticFactory0("PROPERTY_DELEGATION_BY_DYNAMIC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        SPREAD_OPERATOR_IN_DYNAMIC_CALL = new KtDiagnosticFactory0("SPREAD_OPERATOR_IN_DYNAMIC_CALL", severity, sourceElementPositioningStrategies.getSPREAD_OPERATOR(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        WRONG_OPERATION_WITH_DYNAMIC = new KtDiagnosticFactory1<>("WRONG_OPERATION_WITH_DYNAMIC", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_STATIC_NOT_IN_CLASS_COMPANION = new KtDiagnosticFactory0("JS_STATIC_NOT_IN_CLASS_COMPANION", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJsErrors.getRendererFactory());
        JS_STATIC_ON_NON_PUBLIC_MEMBER = new KtDiagnosticFactory0("JS_STATIC_ON_NON_PUBLIC_MEMBER", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJsErrors.getRendererFactory());
        JS_STATIC_ON_CONST = new KtDiagnosticFactory0("JS_STATIC_ON_CONST", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE(), Reflection.getOrCreateKotlinClass(PsiElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_WRONG_TARGET = new KtDiagnosticFactory0("JS_NO_RUNTIME_WRONG_TARGET", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_FORBIDDEN_IS_CHECK = new KtDiagnosticFactory0("JS_NO_RUNTIME_FORBIDDEN_IS_CHECK", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_FORBIDDEN_AS_CAST = new KtDiagnosticFactory0("JS_NO_RUNTIME_FORBIDDEN_AS_CAST", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE = new KtDiagnosticFactory0("JS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE", severity, sourceElementPositioningStrategies.getDEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE = new KtDiagnosticFactory0("JS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE", severity2, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT = new KtDiagnosticFactory1<>("JS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT", severity, sourceElementPositioningStrategies.getDECLARATION_SIGNATURE_OR_DEFAULT(), Reflection.getOrCreateKotlinClass(KtElement.class), firJsErrors.getRendererFactory());
        JS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME = new KtDiagnosticFactory0("JS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME", severity2, sourceElementPositioningStrategies.getDECLARATION_NAME_ONLY(), Reflection.getOrCreateKotlinClass(KtNamedDeclaration.class), firJsErrors.getRendererFactory());
        JS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT = new KtDiagnosticFactory0("JS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT", severity2, sourceElementPositioningStrategies.getDECLARATION_NAME_ONLY(), Reflection.getOrCreateKotlinClass(KtNamedDeclaration.class), firJsErrors.getRendererFactory());
    }

    private FirJsErrors() {
    }

    public final KtDiagnosticFactory0 getCALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE() {
        return CALL_FROM_UMD_MUST_BE_JS_MODULE_AND_JS_NON_MODULE;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getCALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM() {
        return CALL_TO_JS_MODULE_WITHOUT_MODULE_SYSTEM;
    }

    public final KtDiagnosticFactory1<FirBasedSymbol<?>> getCALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM() {
        return CALL_TO_JS_NON_MODULE_WITH_MODULE_SYSTEM;
    }

    public final KtDiagnosticFactory0 getDELEGATION_BY_DYNAMIC() {
        return DELEGATION_BY_DYNAMIC;
    }

    public final KtDiagnosticFactory0 getENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING() {
        return ENUM_CLASS_IN_EXTERNAL_DECLARATION_WARNING;
    }

    public final KtDiagnosticFactoryForDeprecation1<FirClassLikeSymbol<?>> getEXPOSED_NOT_EXPORTED_SUPER_INTERFACE() {
        return EXPOSED_NOT_EXPORTED_SUPER_INTERFACE;
    }

    public final KtDiagnosticFactory0 getEXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION() {
        return EXTENSION_FUNCTION_IN_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getEXTERNAL_ENUM_ENTRY_WITH_BODY() {
        return EXTERNAL_ENUM_ENTRY_WITH_BODY;
    }

    public final KtDiagnosticFactory0 getIMPLEMENTING_FUNCTION_INTERFACE() {
        return IMPLEMENTING_FUNCTION_INTERFACE;
    }

    public final KtDiagnosticFactory0 getINLINE_CLASS_IN_EXTERNAL_DECLARATION() {
        return INLINE_CLASS_IN_EXTERNAL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getINLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING() {
        return INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING;
    }

    public final KtDiagnosticFactory0 getJS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME() {
        return JS_ACTUAL_EXTERNAL_INTERFACE_WHILE_EXPECT_WITHOUT_JS_NO_RUNTIME;
    }

    public final KtDiagnosticFactory1<String> getJS_BUILTIN_NAME_CLASH() {
        return JS_BUILTIN_NAME_CLASH;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getJS_EXTERNAL_ARGUMENT() {
        return JS_EXTERNAL_ARGUMENT;
    }

    public final KtDiagnosticFactory2<FirClassLikeSymbol<?>, FirClassLikeSymbol<?>> getJS_EXTERNAL_INHERITORS_ONLY() {
        return JS_EXTERNAL_INHERITORS_ONLY;
    }

    public final KtDiagnosticFactory3<String, FirBasedSymbol<?>, Collection<FirBasedSymbol<?>>> getJS_FAKE_NAME_CLASH() {
        return JS_FAKE_NAME_CLASH;
    }

    public final KtDiagnosticFactory0 getJS_MODULE_PROHIBITED_ON_NON_NATIVE() {
        return JS_MODULE_PROHIBITED_ON_NON_NATIVE;
    }

    public final KtDiagnosticFactory2<String, Collection<FirBasedSymbol<?>>> getJS_NAME_CLASH() {
        return JS_NAME_CLASH;
    }

    public final KtDiagnosticFactory0 getJS_NAME_IS_NOT_ON_ALL_ACCESSORS() {
        return JS_NAME_IS_NOT_ON_ALL_ACCESSORS;
    }

    public final KtDiagnosticFactory0 getJS_NAME_ON_ACCESSOR_AND_PROPERTY() {
        return JS_NAME_ON_ACCESSOR_AND_PROPERTY;
    }

    public final KtDiagnosticFactory0 getJS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED() {
        return JS_NAME_ON_PRIMARY_CONSTRUCTOR_PROHIBITED;
    }

    public final KtDiagnosticFactory0 getJS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY() {
        return JS_NAME_PROHIBITED_FOR_EXTENSION_PROPERTY;
    }

    public final KtDiagnosticFactory0 getJS_NAME_PROHIBITED_FOR_NAMED_NATIVE() {
        return JS_NAME_PROHIBITED_FOR_NAMED_NATIVE;
    }

    public final KtDiagnosticFactory0 getJS_NAME_PROHIBITED_FOR_OVERRIDE() {
        return JS_NAME_PROHIBITED_FOR_OVERRIDE;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT() {
        return JS_NO_RUNTIME_ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_FORBIDDEN_AS_CAST() {
        return JS_NO_RUNTIME_FORBIDDEN_AS_CAST;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE() {
        return JS_NO_RUNTIME_FORBIDDEN_CLASS_REFERENCE;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_FORBIDDEN_IS_CHECK() {
        return JS_NO_RUNTIME_FORBIDDEN_IS_CHECK;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getJS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT() {
        return JS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE() {
        return JS_NO_RUNTIME_USELESS_ON_EXTERNAL_INTERFACE;
    }

    public final KtDiagnosticFactory0 getJS_NO_RUNTIME_WRONG_TARGET() {
        return JS_NO_RUNTIME_WRONG_TARGET;
    }

    public final KtDiagnosticFactory0 getJS_STATIC_NOT_IN_CLASS_COMPANION() {
        return JS_STATIC_NOT_IN_CLASS_COMPANION;
    }

    public final KtDiagnosticFactory0 getJS_STATIC_ON_CONST() {
        return JS_STATIC_ON_CONST;
    }

    public final KtDiagnosticFactory0 getJS_STATIC_ON_NON_PUBLIC_MEMBER() {
        return JS_STATIC_ON_NON_PUBLIC_MEMBER;
    }

    public final KtDiagnosticFactory0 getJS_SYMBOL_ON_TOP_LEVEL_DECLARATION() {
        return JS_SYMBOL_ON_TOP_LEVEL_DECLARATION;
    }

    public final KtDiagnosticFactory0 getJS_SYMBOL_PROHIBITED_FOR_OVERRIDE() {
        return JS_SYMBOL_PROHIBITED_FOR_OVERRIDE;
    }

    public final KtDiagnosticFactory0 getNAMED_COMPANION_IN_EXPORTED_INTERFACE() {
        return NAMED_COMPANION_IN_EXPORTED_INTERFACE;
    }

    public final KtDiagnosticFactory0 getNAME_CONTAINS_ILLEGAL_CHARS() {
        return NAME_CONTAINS_ILLEGAL_CHARS;
    }

    public final KtDiagnosticFactory1<ConeKotlinType> getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN() {
        return NATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN;
    }

    public final KtDiagnosticFactory0 getNATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE() {
        return NATIVE_GETTER_RETURN_TYPE_SHOULD_BE_NULLABLE;
    }

    public final KtDiagnosticFactory1<String> getNATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS() {
        return NATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS;
    }

    public final KtDiagnosticFactory1<String> getNATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER() {
        return NATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER;
    }

    public final KtDiagnosticFactory2<Integer, String> getNATIVE_INDEXER_WRONG_PARAMETER_COUNT() {
        return NATIVE_INDEXER_WRONG_PARAMETER_COUNT;
    }

    public final KtDiagnosticFactory0 getNATIVE_SETTER_WRONG_RETURN_TYPE() {
        return NATIVE_SETTER_WRONG_RETURN_TYPE;
    }

    public final KtDiagnosticFactory1<String> getNON_CONSUMABLE_EXPORTED_IDENTIFIER() {
        return NON_CONSUMABLE_EXPORTED_IDENTIFIER;
    }

    public final KtDiagnosticFactory2<String, ConeKotlinType> getNON_EXPORTABLE_TYPE() {
        return NON_EXPORTABLE_TYPE;
    }

    public final KtDiagnosticFactory0 getNOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED() {
        return NOT_EXPORTED_OR_EXTERNAL_ACTUAL_DECLARATION_WHILE_EXPECT_IS_EXPORTED;
    }

    public final KtDiagnosticFactory0 getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS() {
        return OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS;
    }

    public final KtDiagnosticFactory1<FirNamedFunctionSymbol> getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE() {
        return OVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE;
    }

    public final KtDiagnosticFactory0 getPROPERTY_DELEGATION_BY_DYNAMIC() {
        return PROPERTY_DELEGATION_BY_DYNAMIC;
    }

    public final KtDiagnosticFactory0 getRUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION() {
        return RUNTIME_ANNOTATION_ON_EXTERNAL_DECLARATION;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return FirJsErrorsDefaultMessages.INSTANCE;
    }

    public final KtDiagnosticFactory0 getSPREAD_OPERATOR_IN_DYNAMIC_CALL() {
        return SPREAD_OPERATOR_IN_DYNAMIC_CALL;
    }

    public final KtDiagnosticFactory1<String> getWRONG_EXPORTED_DECLARATION() {
        return WRONG_EXPORTED_DECLARATION;
    }

    public final KtDiagnosticFactory1<FirCallableSymbol<?>> getWRONG_MULTIPLE_INHERITANCE() {
        return WRONG_MULTIPLE_INHERITANCE;
    }

    public final KtDiagnosticFactory1<String> getWRONG_OPERATION_WITH_DYNAMIC() {
        return WRONG_OPERATION_WITH_DYNAMIC;
    }
}
