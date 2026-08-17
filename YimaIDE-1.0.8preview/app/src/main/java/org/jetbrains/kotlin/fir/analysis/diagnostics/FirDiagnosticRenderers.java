package org.jetbrains.kotlin.fir.analysis.diagnostics;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractRenderer;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.renderer.ConeIdRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeIdShortRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForReadability;
import org.jetbrains.kotlin.fir.renderer.FirAllModifierRenderer;
import org.jetbrains.kotlin.fir.renderer.FirAnnotationRenderer;
import org.jetbrains.kotlin.fir.renderer.FirBodyRenderer;
import org.jetbrains.kotlin.fir.renderer.FirCallNoArgumentsRenderer;
import org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRenderer;
import org.jetbrains.kotlin.fir.renderer.FirCallableSignatureRendererForReadability;
import org.jetbrains.kotlin.fir.renderer.FirContextArgumentRenderer;
import org.jetbrains.kotlin.fir.renderer.FirDeclarationRenderer;
import org.jetbrains.kotlin.fir.renderer.FirErrorExpressionRenderer;
import org.jetbrains.kotlin.fir.renderer.FirGetClassCallRenderer;
import org.jetbrains.kotlin.fir.renderer.FirModifierRenderer;
import org.jetbrains.kotlin.fir.renderer.FirNoClassMemberRenderer;
import org.jetbrains.kotlin.fir.renderer.FirPackageDirectiveRenderer;
import org.jetbrains.kotlin.fir.renderer.FirPartialModifierRenderer;
import org.jetbrains.kotlin.fir.renderer.FirPropertyAccessorRenderer;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvePhaseRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvedNamedReferenceRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvedQualifierRenderer;
import org.jetbrains.kotlin.fir.renderer.FirSupertypeRenderer;
import org.jetbrains.kotlin.fir.renderer.FirSymbolRenderer;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameRenderingUtils;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001|B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00052\u0010\b\u0002\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0002J<\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\u00170\u0005\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\u00170\u0005J(\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001a0 \"\u0004\b\u0000\u0010\u001a2\u0006\u0010!\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001a0 J&\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001a0 \"\b\b\u0000\u0010\u001a*\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001a0 J.\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001a0 \"\b\b\u0000\u0010\u001a*\u00020\u00012\u0006\u0010!\u001a\u00020\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001a0 J,\u0010?\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030@0\u00052\u0006\u0010A\u001a\u00020B2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\u001c0DR\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\bR!\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\bR!\u0010\u0016\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR!\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u001b\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\bR!\u0010)\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\bR\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u00170 ¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\bR\u001b\u00102\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003030\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\bR\u001b\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\bR\u001b\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\bR\u001b\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030:0\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\bR\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\bR\u001b\u0010F\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030@0\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\bR\u001b\u0010H\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030@0\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\bR\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020,0 ¢\u0006\b\n\u0000\u001a\u0004\bK\u0010.R\u001b\u0010L\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\bR\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020,0 ¢\u0006\b\n\u0000\u001a\u0004\bO\u0010.R\u000e\u0010P\u001a\u00020QX\u0082T¢\u0006\u0002\n\u0000R\u001d\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0S0\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\bR\u0017\u0010V\u001a\b\u0012\u0004\u0012\u00020W0\u0005¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\bR\u0017\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\u0005¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\bR\u0019\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\u0005¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\bR\u0017\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u0005¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\bR\u001d\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\bR\u0017\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u0005¢\u0006\b\n\u0000\u001a\u0004\be\u0010\bR\u0019\u0010f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0005¢\u0006\b\n\u0000\u001a\u0004\bg\u0010\bR\u0019\u0010h\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0005¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\bR\u0019\u0010j\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010k0\u0005¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\bR\u0017\u0010m\u001a\b\u0012\u0004\u0012\u00020n0\u0005¢\u0006\b\n\u0000\u001a\u0004\bo\u0010\bR\u001b\u0010p\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bq\u0010\bR\u001b\u0010r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\bs\u0010\bR\u001d\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020u0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\bR\u001d\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\bR3\u0010y\u001a$\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0S0z0\u00170\u0005¢\u0006\b\n\u0000\u001a\u0004\b{\u0010\b¨\u0006}"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirDiagnosticRenderers;", Argument.Delimiters.none, "<init>", "()V", "SYMBOL", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getSYMBOL", "()Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "SYMBOL_WITH_ALL_MODIFIERS", "getSYMBOL_WITH_ALL_MODIFIERS", "symbolRenderer", "modifierRenderer", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "SYMBOL_WITH_LOCATION", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "getSYMBOL_WITH_LOCATION$annotations", "getSYMBOL_WITH_LOCATION", "TYPE_PARAMETER_OWNER_SYMBOL", "getTYPE_PARAMETER_OWNER_SYMBOL$annotations", "getTYPE_PARAMETER_OWNER_SYMBOL", "SYMBOLS_ON_NEXT_LINES", Argument.Delimiters.none, "getSYMBOLS_ON_NEXT_LINES", "prefix", "Q", "singular", Argument.Delimiters.none, "plural", "renderer", "formatted", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "message", "emptyStringIfNullOr", "suggestIfNotNull", "SYMBOLS_ON_NEWLINE_WITH_INDENT", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getSYMBOLS_ON_NEWLINE_WITH_INDENT", "CALLABLE_FQ_NAME", "getCALLABLE_FQ_NAME", "CALLABLES_FQ_NAMES", "getCALLABLES_FQ_NAMES", "RENDER_COLLECTION_OF_TYPES", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getRENDER_COLLECTION_OF_TYPES", "()Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "CALLEE_NAME", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCALLEE_NAME", "VARIABLE_NAME", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getVARIABLE_NAME", "DECLARATION_NAME", "getDECLARATION_NAME", "DECLARATION_FQ_NAME", "getDECLARATION_FQ_NAME", "RENDER_CLASS_OR_OBJECT_QUOTED", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getRENDER_CLASS_OR_OBJECT_QUOTED", "RENDER_ENUM_ENTRY_QUOTED", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "getRENDER_ENUM_ENTRY_QUOTED", "RENDER_CLASS_OR_OBJECT", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "quoted", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/ClassId;", "RENDER_CLASS_OR_OBJECT_NAME_QUOTED", "getRENDER_CLASS_OR_OBJECT_NAME_QUOTED", "STAR_PROJECTED_CLASS", "getSTAR_PROJECTED_CLASS", "RENDER_TYPE", "getRENDER_TYPE", "RENDER_FQ_NAME_WITH_PREFIX", "getRENDER_FQ_NAME_WITH_PREFIX", "RENDER_TYPE_WITH_ANNOTATIONS", "getRENDER_TYPE_WITH_ANNOTATIONS", "WHEN_MISSING_LIMIT", Argument.Delimiters.none, "WHEN_MISSING_CASES", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "getWHEN_MISSING_CASES", "MODULE_DATA", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getMODULE_DATA", "NAME_OF_CONTAINING_DECLARATION_OR_FILE", "Lorg/jetbrains/kotlin/name/CallableId;", "getNAME_OF_CONTAINING_DECLARATION_OR_FILE", "NAME_OF_DECLARATION_OR_FILE", "getNAME_OF_DECLARATION_OR_FILE", "FUNCTIONAL_TYPE_KIND", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getFUNCTIONAL_TYPE_KIND", "FUNCTIONAL_TYPE_KINDS", "getFUNCTIONAL_TYPE_KINDS", "REQUIRE_KOTLIN_VERSION", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "getREQUIRE_KOTLIN_VERSION", "OPTIONAL_SENTENCE", "getOPTIONAL_SENTENCE", "FOR_OPTIONAL_OPERATOR", "getFOR_OPTIONAL_OPERATOR", "OF_OPTIONAL_NAME", "Lorg/jetbrains/kotlin/name/Name;", "getOF_OPTIONAL_NAME", "IGNORABILITY_STATUS", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "getIGNORABILITY_STATUS", "SYMBOL_WITH_CONTAINING_DECLARATION", "getSYMBOL_WITH_CONTAINING_DECLARATION", "SYMBOL_KIND", "getSYMBOL_KIND", "KOTLIN_TARGETS", "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "getKOTLIN_TARGETS", "STRING_TARGETS", "getSTRING_TARGETS", "CANDIDATES_WITH_DIAGNOSTIC_MESSAGES", "Lkotlin/Pair;", "getCANDIDATES_WITH_DIAGNOSTIC_MESSAGES", "DiagnosticStaticPolicy", "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDiagnosticRenderers {
    private static final ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>> CALLABLES_FQ_NAMES;
    private static final ContextIndependentParameterRenderer<FirCallableSymbol<?>> CALLABLE_FQ_NAME;
    private static final ContextIndependentParameterRenderer<FirExpression> CALLEE_NAME;
    private static final ContextIndependentParameterRenderer<Collection<? extends Pair<? extends FirBasedSymbol<?>, ? extends List<String>>>> CANDIDATES_WITH_DIAGNOSTIC_MESSAGES;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> DECLARATION_FQ_NAME;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> DECLARATION_NAME;
    private static final ContextIndependentParameterRenderer<String> FOR_OPTIONAL_OPERATOR;
    private static final ContextIndependentParameterRenderer<FunctionTypeKind> FUNCTIONAL_TYPE_KIND;
    private static final ContextIndependentParameterRenderer<Collection<? extends FunctionTypeKind>> FUNCTIONAL_TYPE_KINDS;
    private static final ContextIndependentParameterRenderer<ReturnValueStatus> IGNORABILITY_STATUS;
    public static final FirDiagnosticRenderers INSTANCE;
    private static final ContextIndependentParameterRenderer<Collection<? extends KotlinTarget>> KOTLIN_TARGETS;
    private static final ContextIndependentParameterRenderer<FirModuleData> MODULE_DATA;
    private static final ContextIndependentParameterRenderer<CallableId> NAME_OF_CONTAINING_DECLARATION_OR_FILE;
    private static final ContextIndependentParameterRenderer<ClassId> NAME_OF_DECLARATION_OR_FILE;
    private static final ContextIndependentParameterRenderer<Name> OF_OPTIONAL_NAME;
    private static final ContextIndependentParameterRenderer<String> OPTIONAL_SENTENCE;
    private static final ContextIndependentParameterRenderer<FirClassLikeSymbol<?>> RENDER_CLASS_OR_OBJECT_NAME_QUOTED;
    private static final ContextIndependentParameterRenderer<FirClassSymbol<?>> RENDER_CLASS_OR_OBJECT_QUOTED;
    private static final DiagnosticParameterRenderer<Collection<? extends ConeKotlinType>> RENDER_COLLECTION_OF_TYPES;
    private static final ContextIndependentParameterRenderer<FirEnumEntrySymbol> RENDER_ENUM_ENTRY_QUOTED;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> RENDER_FQ_NAME_WITH_PREFIX;
    private static final DiagnosticParameterRenderer<ConeKotlinType> RENDER_TYPE;
    private static final DiagnosticParameterRenderer<ConeKotlinType> RENDER_TYPE_WITH_ANNOTATIONS;
    private static final ContextIndependentParameterRenderer<VersionRequirement.Version> REQUIRE_KOTLIN_VERSION;
    private static final ContextIndependentParameterRenderer<FirClassLikeSymbol<?>> STAR_PROJECTED_CLASS;
    private static final ContextIndependentParameterRenderer<Collection<String>> STRING_TARGETS;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> SYMBOL;
    private static final ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>> SYMBOLS_ON_NEWLINE_WITH_INDENT;
    private static final ContextIndependentParameterRenderer<Collection<? extends FirBasedSymbol<?>>> SYMBOLS_ON_NEXT_LINES;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> SYMBOL_KIND;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> SYMBOL_WITH_ALL_MODIFIERS;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> SYMBOL_WITH_CONTAINING_DECLARATION;
    private static final ContextIndependentParameterRenderer<DeclarationSymbolMarker> SYMBOL_WITH_LOCATION;
    private static final ContextIndependentParameterRenderer<FirBasedSymbol<?>> TYPE_PARAMETER_OWNER_SYMBOL;
    private static final ContextIndependentParameterRenderer<FirVariableSymbol<?>> VARIABLE_NAME;
    private static final ContextIndependentParameterRenderer<List<? extends WhenMissingCase>> WHEN_MISSING_CASES;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/FirDiagnosticRenderers$DiagnosticStaticPolicy;", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy;", "<init>", "()V", "renderStatic", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:diagnostic-renderers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DiagnosticStaticPolicy implements FirModifierRenderer.StaticPolicy {
        public static final DiagnosticStaticPolicy INSTANCE = new DiagnosticStaticPolicy();

        private DiagnosticStaticPolicy() {
        }

        @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer.StaticPolicy
        public String renderStatic(FirDeclaration memberDeclaration) {
            memberDeclaration.getClass();
            if (memberDeclaration instanceof FirEnumEntry) {
                return null;
            }
            return DeclarationUtilsKt.isJavaOrEnhancement(memberDeclaration) ? "static" : "companion";
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassKind.CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ReturnValueStatus.values().length];
            try {
                iArr2[ReturnValueStatus.MustUse.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ReturnValueStatus.ExplicitlyIgnorable.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ReturnValueStatus.Unspecified.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        FirDiagnosticRenderers firDiagnosticRenderers = new FirDiagnosticRenderers();
        INSTANCE = firDiagnosticRenderers;
        ContextIndependentParameterRenderer<FirBasedSymbol<?>> contextIndependentParameterRendererSymbolRenderer = firDiagnosticRenderers.symbolRenderer(new Function0() { // from class: x25
            public final Object invoke() {
                return FirDiagnosticRenderers.p();
            }
        });
        SYMBOL = contextIndependentParameterRendererSymbolRenderer;
        SYMBOL_WITH_ALL_MODIFIERS = symbolRenderer$default(firDiagnosticRenderers, null, 1, null);
        SYMBOL_WITH_LOCATION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: z25
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.e((DeclarationSymbolMarker) obj);
            }
        });
        TYPE_PARAMETER_OWNER_SYMBOL = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: l35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.n((FirBasedSymbol) obj);
            }
        });
        SYMBOLS_ON_NEXT_LINES = CommonRenderers.onNextLines(contextIndependentParameterRendererSymbolRenderer);
        SYMBOLS_ON_NEWLINE_WITH_INDENT = new ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>>() { // from class: org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers$SYMBOLS_ON_NEWLINE_WITH_INDENT$1
            private final MultiplatformDiagnosticRenderingMode mode = new MultiplatformDiagnosticRenderingMode();

            @Override // org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer
            public String render(Collection<? extends FirCallableSymbol<?>> obj) {
                obj.getClass();
                StringBuilder sb = new StringBuilder();
                for (FirCallableSymbol<?> firCallableSymbol : obj) {
                    this.mode.newLine(sb);
                    this.mode.renderSymbol(sb, firCallableSymbol, Argument.Delimiters.none);
                }
                return sb.toString();
            }
        };
        CALLABLE_FQ_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: m35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.G((FirCallableSymbol) obj);
            }
        });
        CALLABLES_FQ_NAMES = new FirDiagnosticRenderers$CALLABLES_FQ_NAMES$1();
        RENDER_COLLECTION_OF_TYPES = DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: n35
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticRenderers.y((Collection) obj, (RenderingContext) obj2);
            }
        });
        CALLEE_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: o35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.q((FirExpression) obj);
            }
        });
        VARIABLE_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: p35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.P((FirVariableSymbol) obj);
            }
        });
        DECLARATION_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: q35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.l((FirBasedSymbol) obj);
            }
        });
        DECLARATION_FQ_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: r35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.c((FirBasedSymbol) obj);
            }
        });
        RENDER_CLASS_OR_OBJECT_QUOTED = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: s35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.t((FirClassSymbol) obj);
            }
        });
        RENDER_ENUM_ENTRY_QUOTED = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: i35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.h((FirEnumEntrySymbol) obj);
            }
        });
        RENDER_CLASS_OR_OBJECT_NAME_QUOTED = firDiagnosticRenderers.RENDER_CLASS_OR_OBJECT(true, new Function1() { // from class: t35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.R((ClassId) obj);
            }
        });
        STAR_PROJECTED_CLASS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: e45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.a((FirClassLikeSymbol) obj);
            }
        });
        DiagnosticParameterRenderer<ConeKotlinType> diagnosticParameterRendererContextDependentRenderer = DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: j45
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticRenderers.A((ConeKotlinType) obj, (RenderingContext) obj2);
            }
        });
        RENDER_TYPE = diagnosticParameterRendererContextDependentRenderer;
        RENDER_FQ_NAME_WITH_PREFIX = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: k45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.j((FirBasedSymbol) obj);
            }
        });
        RENDER_TYPE_WITH_ANNOTATIONS = diagnosticParameterRendererContextDependentRenderer;
        WHEN_MISSING_CASES = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: l45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.C((List) obj);
            }
        });
        MODULE_DATA = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: m45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.N((FirModuleData) obj);
            }
        });
        NAME_OF_CONTAINING_DECLARATION_OR_FILE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: n45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.Q((CallableId) obj);
            }
        });
        NAME_OF_DECLARATION_OR_FILE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: o45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.o((ClassId) obj);
            }
        });
        ContextIndependentParameterRenderer<FunctionTypeKind> contextIndependentParameterRendererRenderer = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: y25
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.d((FunctionTypeKind) obj);
            }
        });
        FUNCTIONAL_TYPE_KIND = contextIndependentParameterRendererRenderer;
        FUNCTIONAL_TYPE_KINDS = KtDiagnosticRenderers.INSTANCE.COLLECTION(contextIndependentParameterRendererRenderer);
        REQUIRE_KOTLIN_VERSION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: a35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.u((VersionRequirement.Version) obj);
            }
        });
        OPTIONAL_SENTENCE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: b35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.r((String) obj);
            }
        });
        FOR_OPTIONAL_OPERATOR = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: c35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.b((String) obj);
            }
        });
        OF_OPTIONAL_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: d35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.D((Name) obj);
            }
        });
        IGNORABILITY_STATUS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: e35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.m((ReturnValueStatus) obj);
            }
        });
        SYMBOL_WITH_CONTAINING_DECLARATION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: f35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.K((FirBasedSymbol) obj);
            }
        });
        SYMBOL_KIND = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: g35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.v((FirBasedSymbol) obj);
            }
        });
        KOTLIN_TARGETS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: h35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.M((Collection) obj);
            }
        });
        STRING_TARGETS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: j35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.O((Collection) obj);
            }
        });
        CANDIDATES_WITH_DIAGNOSTIC_MESSAGES = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: k35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.f((Collection) obj);
            }
        });
    }

    private FirDiagnosticRenderers() {
    }

    public static String A(ConeKotlinType coneKotlinType, RenderingContext renderingContext) {
        coneKotlinType.getClass();
        renderingContext.getClass();
        return (String) MapsKt.getValue((Map) renderingContext.get(FirAdaptiveTypeRenderingKey.INSTANCE), coneKotlinType);
    }

    public static String C(List list) {
        list.getClass();
        if (Intrinsics.areEqual(CollectionsKt.singleOrNull(list), WhenMissingCase.Unknown.INSTANCE)) {
            return "an 'else' branch";
        }
        return "the " + CollectionsKt.joinToString$default(list, ", ", (CharSequence) null, (CharSequence) null, 7, (CharSequence) null, new Function1() { // from class: f45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.WHEN_MISSING_CASES$lambda$0$0((WhenMissingCase) obj);
            }
        }, 22, (Object) null) + ' ' + (list.size() > 1 ? "branches" : "branch") + " or an 'else' branch";
    }

    public static String D(Name name) {
        String strAsString;
        if (name == null || (strAsString = name.asString()) == null) {
            return Argument.Delimiters.none;
        }
        if (StringsKt.isBlank(strAsString)) {
            strAsString = null;
        }
        if (strAsString == null) {
            return Argument.Delimiters.none;
        }
        return " of '" + strAsString + '\'';
    }

    public static String E(String str, String str2, ContextIndependentParameterRenderer contextIndependentParameterRenderer, Collection collection) {
        collection.getClass();
        if (collection.size() != 1) {
            str = str2;
        }
        return str + contextIndependentParameterRenderer.render(collection);
    }

    public static String F(DiagnosticParameterRenderer diagnosticParameterRenderer, Object obj, RenderingContext renderingContext) {
        String strRender;
        renderingContext.getClass();
        return (obj == null || (strRender = diagnosticParameterRenderer.render(obj, renderingContext)) == null) ? Argument.Delimiters.none : strRender;
    }

    public static String G(FirCallableSymbol firCallableSymbol) {
        ClassId classId;
        firCallableSymbol.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol);
        String strAsFqNameString = (coneClassLikeLookupTagContainingClassLookupTag == null || (classId = coneClassLikeLookupTagContainingClassLookupTag.getClassId()) == null) ? null : classId.asFqNameString();
        StringBuilder sb = new StringBuilder();
        sb.append(SYMBOL.render(firCallableSymbol));
        String strConcat = strAsFqNameString != null ? ", defined in ".concat(strAsFqNameString) : null;
        if (strConcat == null) {
            strConcat = Argument.Delimiters.none;
        }
        sb.append(strConcat);
        return sb.toString();
    }

    public static String K(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        ClassId classId = null;
        if (firBasedSymbol instanceof FirCallableSymbol) {
            CallableId callableId = ((FirCallableSymbol) firBasedSymbol).getCallableId();
            if (callableId != null) {
                classId = callableId.getClassId();
            }
        } else if (firBasedSymbol instanceof FirTypeParameterSymbol) {
            FirBasedSymbol<?> containingDeclarationSymbol = ((FirTypeParameterSymbol) firBasedSymbol).getContainingDeclarationSymbol();
            FirClassLikeSymbol firClassLikeSymbol = containingDeclarationSymbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) containingDeclarationSymbol : null;
            if (firClassLikeSymbol != null) {
                classId = firClassLikeSymbol.getClassId();
            }
        }
        if (classId == null) {
            return "'" + SYMBOL.render(firBasedSymbol) + '\'';
        }
        return "'" + SYMBOL.render(firBasedSymbol) + "' defined in " + NAME_OF_DECLARATION_OR_FILE.render(classId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence KOTLIN_TARGETS$lambda$0$0(KotlinTarget kotlinTarget) {
        kotlinTarget.getClass();
        return kotlinTarget.getDescription();
    }

    public static String L(String str, DiagnosticParameterRenderer diagnosticParameterRenderer, Object obj, RenderingContext renderingContext) {
        renderingContext.getClass();
        String str2 = new MessageFormat(str).format(new String[]{diagnosticParameterRenderer.render(obj, renderingContext)});
        str2.getClass();
        return str2;
    }

    public static String M(Collection collection) {
        collection.getClass();
        return CollectionsKt.joinToString$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: c45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.KOTLIN_TARGETS$lambda$0$0((KotlinTarget) obj);
            }
        }, 31, (Object) null);
    }

    public static String N(FirModuleData firModuleData) {
        firModuleData.getClass();
        return "module " + firModuleData.getName();
    }

    public static String O(Collection collection) {
        collection.getClass();
        String strJoinToString$default = CollectionsKt.joinToString$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: i45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.STRING_TARGETS$lambda$0$0((String) obj);
            }
        }, 31, (Object) null);
        int size = collection.size();
        if (size == 0) {
            return "no targets";
        }
        if (size != 1) {
            return "targets " + strJoinToString$default;
        }
        return "target " + strJoinToString$default;
    }

    public static String P(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        String strAsString = firVariableSymbol.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public static String Q(CallableId callableId) {
        callableId.getClass();
        return NAME_OF_DECLARATION_OR_FILE.render(callableId.getClassId());
    }

    public static String R(ClassId classId) {
        classId.getClass();
        String strAsString = classId.getRelativeClassName().shortName().asString();
        strAsString.getClass();
        return strAsString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence RENDER_COLLECTION_OF_TYPES$lambda$0$0(RenderingContext renderingContext, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return RENDER_TYPE.render(coneKotlinType, renderingContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String RENDER_FQ_NAME_WITH_PREFIX$lambda$0$0(ClassId classId) {
        classId.getClass();
        return classId.asFqNameString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence STRING_TARGETS$lambda$0$0(String str) {
        str.getClass();
        return "'" + str + '\'';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeIdRenderer SYMBOL_WITH_LOCATION$lambda$0$0() {
        return new ConeIdShortRenderer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeIdRenderer TYPE_PARAMETER_OWNER_SYMBOL$lambda$0$0() {
        return new ConeIdShortRenderer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence WHEN_MISSING_CASES$lambda$0$0(WhenMissingCase whenMissingCase) {
        whenMissingCase.getClass();
        return "'" + whenMissingCase + '\'';
    }

    public static String a(FirClassLikeSymbol firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        boolean zIsInner = true;
        while (firClassLikeSymbol != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(firClassLikeSymbol.getClassId().getShortClassName());
            int size = firClassLikeSymbol.getOwnTypeParameterSymbols().size();
            if (zIsInner && size > 0) {
                sb.append("<");
                String[] strArr = new String[size];
                for (int i = 0; i < size; i++) {
                    strArr[i] = "*";
                }
                ArraysKt.joinTo$default(strArr, sb, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null);
                sb.append(">");
            }
            listCreateListBuilder.add(sb.toString());
            zIsInner = firClassLikeSymbol.getRawStatus().isInner();
            firClassLikeSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firClassLikeSymbol);
        }
        return CollectionsKt.joinToString$default(CollectionsKt.reversed(CollectionsKt.build(listCreateListBuilder)), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public static String b(String str) {
        if (str == null || StringsKt.isBlank(str)) {
            return Argument.Delimiters.none;
        }
        return " for operator '" + str + '\'';
    }

    public static String c(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (!(firBasedSymbol instanceof FirCallableSymbol)) {
            return firBasedSymbol instanceof FirClassLikeSymbol ? ((FirClassLikeSymbol) firBasedSymbol).getClassId().asFqNameString() : "???";
        }
        String strAsString = ((FirCallableSymbol) firBasedSymbol).getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public static String d(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        String prefixForTypeRender = functionTypeKind.getPrefixForTypeRender();
        return prefixForTypeRender == null ? functionTypeKind.getClassNamePrefix() : prefixForTypeRender;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String e(DeclarationSymbolMarker declarationSymbolMarker) {
        declarationSymbolMarker.getClass();
        if (!(declarationSymbolMarker instanceof FirClassLikeSymbol) && !(declarationSymbolMarker instanceof FirCallableSymbol)) {
            if (!(declarationSymbolMarker instanceof FirTypeParameterSymbol)) {
                return Argument.Delimiters.none;
            }
            String strAsString = ((FirTypeParameterSymbol) declarationSymbolMarker).getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        ConeTypeRendererForReadability coneTypeRendererForReadability = new ConeTypeRendererForReadability(null, new Function0() { // from class: g45
            public final Object invoke() {
                return FirDiagnosticRenderers.SYMBOL_WITH_LOCATION$lambda$0$0();
            }
        }, 1, 0 == true ? 1 : 0);
        ConeIdShortRenderer coneIdShortRenderer = new ConeIdShortRenderer();
        Object[] objArr = 0 == true ? 1 : 0;
        FirRenderer firRenderer = new FirRenderer(objArr, null, null, new FirCallNoArgumentsRenderer(), null, new FirNoClassMemberRenderer(), null, new FirDeclarationRenderer("local ", true), coneIdShortRenderer, new FirPartialModifierRenderer(DiagnosticStaticPolicy.INSTANCE), null, null, null, coneTypeRendererForReadability, null, new FirCallableSignatureRendererForReadability(), null, null, null, null, null, false, false, false, 10441745, null);
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) declarationSymbolMarker;
        return firRenderer.renderElementAsString(firBasedSymbol.getFir(), true) + " defined in " + (UtilsKt.packageFqName(firBasedSymbol).isRoot() ? "root package" : NameRenderingUtils.render(UtilsKt.packageFqName(firBasedSymbol))) + " in module " + firBasedSymbol.getModuleData().getName();
    }

    public static String f(Collection collection) {
        collection.getClass();
        StringBuilder sb = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) pair.component1();
            List<String> list = (List) pair.component2();
            sb.append(SYMBOL.render(firBasedSymbol));
            if (!list.isEmpty()) {
                sb.append(":\n");
                for (String str : list) {
                    sb.append("  ");
                    sb.append(str);
                    sb.append('\n');
                }
            }
            sb.append('\n');
        }
        return StringsKt.trim(sb.toString()).toString();
    }

    public static /* synthetic */ void getSYMBOL_WITH_LOCATION$annotations() {
    }

    public static /* synthetic */ void getTYPE_PARAMETER_OWNER_SYMBOL$annotations() {
    }

    public static String h(FirEnumEntrySymbol firEnumEntrySymbol) {
        firEnumEntrySymbol.getClass();
        String strAsString = firEnumEntrySymbol.getCallableId().getCallableName().asString();
        strAsString.getClass();
        ClassId classId = firEnumEntrySymbol.getCallableId().getClassId();
        if (classId != null) {
            strAsString = classId.getShortClassName().asString() + '.' + strAsString;
        }
        return "Enum entry '" + strAsString + '\'';
    }

    public static String j(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return CALLABLE_FQ_NAME.render(firBasedSymbol);
        }
        return firBasedSymbol instanceof FirClassLikeSymbol ? INSTANCE.RENDER_CLASS_OR_OBJECT(false, new Function1() { // from class: a45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.RENDER_FQ_NAME_WITH_PREFIX$lambda$0$0((ClassId) obj);
            }
        }).render(firBasedSymbol) : "???";
    }

    public static String l(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            String strAsString = ((FirCallableSymbol) firBasedSymbol).getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            String strAsString2 = ((FirClassLikeSymbol) firBasedSymbol).getClassId().getShortClassName().asString();
            strAsString2.getClass();
            return strAsString2;
        }
        if (!(firBasedSymbol instanceof FirTypeParameterSymbol)) {
            return "???";
        }
        String strAsString3 = ((FirTypeParameterSymbol) firBasedSymbol).getName().asString();
        strAsString3.getClass();
        return strAsString3;
    }

    public static String m(ReturnValueStatus returnValueStatus) {
        returnValueStatus.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[returnValueStatus.ordinal()];
        if (i == 1) {
            return "must-use";
        }
        if (i == 2) {
            return "ignorable";
        }
        if (i == 3) {
            return "unspecified (implicitly ignorable)";
        }
        bu8.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String n(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (!(firBasedSymbol instanceof FirClassLikeSymbol) && !(firBasedSymbol instanceof FirCallableSymbol)) {
            if (!(firBasedSymbol instanceof FirTypeParameterSymbol)) {
                return "???";
            }
            String strAsString = ((FirTypeParameterSymbol) firBasedSymbol).getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        ConeTypeRendererForReadability coneTypeRendererForReadability = new ConeTypeRendererForReadability(null, new Function0() { // from class: d45
            public final Object invoke() {
                return FirDiagnosticRenderers.TYPE_PARAMETER_OWNER_SYMBOL$lambda$0$0();
            }
        }, 1, 0 == true ? 1 : 0);
        ConeIdShortRenderer coneIdShortRenderer = new ConeIdShortRenderer();
        FirNoClassMemberRenderer firNoClassMemberRenderer = new FirNoClassMemberRenderer();
        String str = "local ";
        FirAnnotationRenderer firAnnotationRenderer = null;
        FirBodyRenderer firBodyRenderer = null;
        FirContextArgumentRenderer firContextArgumentRenderer = null;
        ConeContractRenderer coneContractRenderer = null;
        FirModifierRenderer firModifierRenderer = null;
        FirPackageDirectiveRenderer firPackageDirectiveRenderer = null;
        FirPropertyAccessorRenderer firPropertyAccessorRenderer = null;
        FirResolvePhaseRenderer firResolvePhaseRenderer = null;
        FirSymbolRenderer firSymbolRenderer = null;
        FirCallableSignatureRenderer firCallableSignatureRenderer = null;
        FirErrorExpressionRenderer firErrorExpressionRenderer = null;
        FirResolvedNamedReferenceRenderer firResolvedNamedReferenceRenderer = null;
        FirResolvedQualifierRenderer firResolvedQualifierRenderer = null;
        FirGetClassCallRenderer firGetClassCallRenderer = null;
        FirSupertypeRenderer firSupertypeRenderer = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        return new FirRenderer(0 == true ? 1 : 0, firAnnotationRenderer, firBodyRenderer, new FirCallNoArgumentsRenderer(), firContextArgumentRenderer, firNoClassMemberRenderer, coneContractRenderer, new FirDeclarationRenderer(str, false, 2, 0 == true ? 1 : 0), coneIdShortRenderer, firModifierRenderer, firPackageDirectiveRenderer, firPropertyAccessorRenderer, firResolvePhaseRenderer, coneTypeRendererForReadability, firSymbolRenderer, firCallableSignatureRenderer, firErrorExpressionRenderer, firResolvedNamedReferenceRenderer, firResolvedQualifierRenderer, firGetClassCallRenderer, firSupertypeRenderer, z, z2, z3, 9393169, null).renderElementAsString(firBasedSymbol.getFir(), true);
    }

    public static String o(ClassId classId) {
        if (classId == null) {
            return "file";
        }
        return "'" + classId.asFqNameString() + '\'';
    }

    public static FirModifierRenderer p() {
        return new FirPartialModifierRenderer(DiagnosticStaticPolicy.INSTANCE);
    }

    public static String q(FirExpression firExpression) {
        firExpression.getClass();
        FirReference referenceUnsafe = ReferenceUtilsKt.toReferenceUnsafe(FirExpressionUtilKt.unwrapSmartcastExpression(firExpression));
        if (referenceUnsafe instanceof FirNamedReference) {
            String strAsString = ((FirNamedReference) referenceUnsafe).getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        if (referenceUnsafe instanceof FirThisReference) {
            return "this";
        }
        return referenceUnsafe instanceof FirSuperReference ? "super" : "???";
    }

    public static String r(String str) {
        if (str == null || StringsKt.isBlank(str)) {
            return Argument.Delimiters.none;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Argument.Delimiters.space);
        sb.append(StringsKt.trim(str).toString());
        if (!StringsKt.endsWith$default(sb, ".", false, 2, (Object) null)) {
            sb.append(".");
        }
        return sb.toString();
    }

    public static FirModifierRenderer s() {
        return new FirAllModifierRenderer(DiagnosticStaticPolicy.INSTANCE);
    }

    private final ContextIndependentParameterRenderer<FirBasedSymbol<?>> symbolRenderer(final Function0<? extends FirModifierRenderer> modifierRenderer) {
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: u35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.w(modifierRenderer, (FirBasedSymbol) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ContextIndependentParameterRenderer symbolRenderer$default(FirDiagnosticRenderers firDiagnosticRenderers, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: z35
                public final Object invoke() {
                    return FirDiagnosticRenderers.s();
                }
            };
        }
        return firDiagnosticRenderers.symbolRenderer(function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeIdRenderer symbolRenderer$lambda$1$0() {
        return new ConeIdShortRenderer();
    }

    public static String t(FirClassSymbol firClassSymbol) {
        String str;
        firClassSymbol.getClass();
        String strAsString = firClassSymbol.getClassId().getRelativeClassName().asString();
        int i = WhenMappings.$EnumSwitchMapping$0[firClassSymbol.getClassKind().ordinal()];
        if (i != 1) {
            str = i != 2 ? "Class" : "Interface";
        } else {
            str = "Object";
        }
        return str + " '" + strAsString + '\'';
    }

    public static String u(VersionRequirement.Version version) {
        version.getClass();
        if (Intrinsics.areEqual(version, VersionRequirement.Version.INFINITY)) {
            return Argument.Delimiters.none;
        }
        return " is only available since Kotlin " + version.asString() + " and";
    }

    public static String v(FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirPropertyAccessorSymbol) {
            return "property accessor";
        }
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            return "constructor";
        }
        if (firBasedSymbol instanceof FirFunctionSymbol) {
            return "function";
        }
        if (firBasedSymbol instanceof FirPropertySymbol) {
            return "property";
        }
        if (firBasedSymbol instanceof FirBackingFieldSymbol) {
            return "backing field";
        }
        if (firBasedSymbol instanceof FirDelegateFieldSymbol) {
            return "delegate field";
        }
        if (firBasedSymbol instanceof FirEnumEntrySymbol) {
            return "enum entry";
        }
        if (firBasedSymbol instanceof FirFieldSymbol) {
            return "field";
        }
        if (firBasedSymbol instanceof FirValueParameterSymbol) {
            return "value parameter";
        }
        if (firBasedSymbol instanceof FirFileSymbol) {
            return "file";
        }
        if (firBasedSymbol instanceof FirAnonymousInitializerSymbol) {
            return "initializer";
        }
        if (firBasedSymbol instanceof FirTypeParameterSymbol) {
            return "type parameter";
        }
        if (!(firBasedSymbol instanceof FirRegularClassSymbol)) {
            if (firBasedSymbol instanceof FirAnonymousObjectSymbol) {
                return "anonymous object";
            }
            return firBasedSymbol instanceof FirTypeAliasSymbol ? "type alias" : "declaration";
        }
        switch (WhenMappings.$EnumSwitchMapping$0[((FirRegularClassSymbol) firBasedSymbol).getClassKind().ordinal()]) {
            case 1:
                return "object";
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return "interface";
            case 3:
                return "class";
            case 4:
                return "enum class";
            case 5:
                return "enum entry";
            case 6:
                return "annotation class";
            default:
                bu8.a();
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String w(Function0 function0, FirBasedSymbol firBasedSymbol) {
        firBasedSymbol.getClass();
        if (!(firBasedSymbol instanceof FirClassLikeSymbol) && !(firBasedSymbol instanceof FirCallableSymbol)) {
            if (!(firBasedSymbol instanceof FirTypeParameterSymbol)) {
                return "???";
            }
            String strAsString = ((FirTypeParameterSymbol) firBasedSymbol).getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        ConeTypeRendererForReadability coneTypeRendererForReadability = new ConeTypeRendererForReadability(null, new Function0() { // from class: v35
            public final Object invoke() {
                return FirDiagnosticRenderers.symbolRenderer$lambda$1$0();
            }
        }, 1, 0 == true ? 1 : 0);
        ConeIdShortRenderer coneIdShortRenderer = new ConeIdShortRenderer();
        FirNoClassMemberRenderer firNoClassMemberRenderer = new FirNoClassMemberRenderer();
        FirCallNoArgumentsRenderer firCallNoArgumentsRenderer = new FirCallNoArgumentsRenderer();
        FirModifierRenderer firModifierRenderer = (FirModifierRenderer) function0.invoke();
        FirCallableSignatureRendererForReadability firCallableSignatureRendererForReadability = new FirCallableSignatureRendererForReadability();
        String str = "local ";
        FirAnnotationRenderer firAnnotationRenderer = null;
        FirBodyRenderer firBodyRenderer = null;
        FirContextArgumentRenderer firContextArgumentRenderer = null;
        ConeContractRenderer coneContractRenderer = null;
        FirPackageDirectiveRenderer firPackageDirectiveRenderer = null;
        FirPropertyAccessorRenderer firPropertyAccessorRenderer = null;
        FirResolvePhaseRenderer firResolvePhaseRenderer = null;
        FirSymbolRenderer firSymbolRenderer = null;
        FirErrorExpressionRenderer firErrorExpressionRenderer = null;
        FirResolvedNamedReferenceRenderer firResolvedNamedReferenceRenderer = null;
        FirResolvedQualifierRenderer firResolvedQualifierRenderer = null;
        FirGetClassCallRenderer firGetClassCallRenderer = null;
        FirSupertypeRenderer firSupertypeRenderer = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        return new FirRenderer(0 == true ? 1 : 0, firAnnotationRenderer, firBodyRenderer, firCallNoArgumentsRenderer, firContextArgumentRenderer, firNoClassMemberRenderer, coneContractRenderer, new FirDeclarationRenderer(str, false, 2, 0 == true ? 1 : 0), coneIdShortRenderer, firModifierRenderer, firPackageDirectiveRenderer, firPropertyAccessorRenderer, firResolvePhaseRenderer, coneTypeRendererForReadability, firSymbolRenderer, firCallableSignatureRendererForReadability, firErrorExpressionRenderer, firResolvedNamedReferenceRenderer, firResolvedQualifierRenderer, firGetClassCallRenderer, firSupertypeRenderer, z, z2, z3, 10441745, null).renderElementAsString(firBasedSymbol.getFir(), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String x(Function1 function1, boolean z, FirClassLikeSymbol firClassLikeSymbol) {
        Object assertionError;
        StringBuilder sb;
        firClassLikeSymbol.getClass();
        String str = (String) function1.invoke(firClassLikeSymbol.getClassId());
        if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
            assertionError = "typealias";
        } else if (!(firClassLikeSymbol instanceof FirRegularClassSymbol)) {
            assertionError = new AssertionError("Unexpected class: " + firClassLikeSymbol);
        } else if (firClassLikeSymbol.getRawStatus().isCompanion()) {
            assertionError = "companion object";
        } else {
            FirClassSymbol firClassSymbol = (FirClassSymbol) firClassLikeSymbol;
            if (firClassSymbol.getClassKind() == ClassKind.INTERFACE) {
                assertionError = "interface";
            } else if (firClassSymbol.getClassKind() == ClassKind.ENUM_CLASS) {
                assertionError = "enum class";
            } else if (firClassLikeSymbol.getRawStatus().isFromEnumClass()) {
                assertionError = "enum entry";
            } else {
                assertionError = ((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal() ? "object" : "class";
            }
        }
        if (z) {
            sb = new StringBuilder();
            sb.append(assertionError);
            sb.append(" '");
            sb.append(str);
            sb.append('\'');
        } else {
            sb = new StringBuilder();
            sb.append(assertionError);
            sb.append(' ');
            sb.append(str);
        }
        return sb.toString();
    }

    public static String y(Collection collection, final RenderingContext renderingContext) {
        collection.getClass();
        renderingContext.getClass();
        return CollectionsKt.joinToString$default(collection, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: w35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.RENDER_COLLECTION_OF_TYPES$lambda$0$0(renderingContext, (ConeKotlinType) obj);
            }
        }, 30, (Object) null);
    }

    public final ContextIndependentParameterRenderer<FirClassLikeSymbol<?>> RENDER_CLASS_OR_OBJECT(final boolean quoted, final Function1<? super ClassId, String> name) {
        name.getClass();
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: h45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.x(name, quoted, (FirClassLikeSymbol) obj);
            }
        });
    }

    public final <Q> DiagnosticParameterRenderer<Q> emptyStringIfNullOr(final DiagnosticParameterRenderer<? super Q> renderer) {
        renderer.getClass();
        return DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: y35
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticRenderers.F(renderer, obj, (RenderingContext) obj2);
            }
        });
    }

    public final <Q> DiagnosticParameterRenderer<Q> formatted(final String message, final DiagnosticParameterRenderer<? super Q> renderer) {
        message.getClass();
        renderer.getClass();
        return DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: b45
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticRenderers.L(message, renderer, obj, (RenderingContext) obj2);
            }
        });
    }

    public final ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>> getCALLABLES_FQ_NAMES() {
        return CALLABLES_FQ_NAMES;
    }

    public final ContextIndependentParameterRenderer<FirCallableSymbol<?>> getCALLABLE_FQ_NAME() {
        return CALLABLE_FQ_NAME;
    }

    public final ContextIndependentParameterRenderer<FirExpression> getCALLEE_NAME() {
        return CALLEE_NAME;
    }

    public final ContextIndependentParameterRenderer<Collection<? extends Pair<? extends FirBasedSymbol<?>, ? extends List<String>>>> getCANDIDATES_WITH_DIAGNOSTIC_MESSAGES() {
        return CANDIDATES_WITH_DIAGNOSTIC_MESSAGES;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getDECLARATION_FQ_NAME() {
        return DECLARATION_FQ_NAME;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getDECLARATION_NAME() {
        return DECLARATION_NAME;
    }

    public final ContextIndependentParameterRenderer<String> getFOR_OPTIONAL_OPERATOR() {
        return FOR_OPTIONAL_OPERATOR;
    }

    public final ContextIndependentParameterRenderer<FunctionTypeKind> getFUNCTIONAL_TYPE_KIND() {
        return FUNCTIONAL_TYPE_KIND;
    }

    public final ContextIndependentParameterRenderer<Collection<? extends FunctionTypeKind>> getFUNCTIONAL_TYPE_KINDS() {
        return FUNCTIONAL_TYPE_KINDS;
    }

    public final ContextIndependentParameterRenderer<ReturnValueStatus> getIGNORABILITY_STATUS() {
        return IGNORABILITY_STATUS;
    }

    public final ContextIndependentParameterRenderer<Collection<? extends KotlinTarget>> getKOTLIN_TARGETS() {
        return KOTLIN_TARGETS;
    }

    public final ContextIndependentParameterRenderer<FirModuleData> getMODULE_DATA() {
        return MODULE_DATA;
    }

    public final ContextIndependentParameterRenderer<CallableId> getNAME_OF_CONTAINING_DECLARATION_OR_FILE() {
        return NAME_OF_CONTAINING_DECLARATION_OR_FILE;
    }

    public final ContextIndependentParameterRenderer<ClassId> getNAME_OF_DECLARATION_OR_FILE() {
        return NAME_OF_DECLARATION_OR_FILE;
    }

    public final ContextIndependentParameterRenderer<Name> getOF_OPTIONAL_NAME() {
        return OF_OPTIONAL_NAME;
    }

    public final ContextIndependentParameterRenderer<String> getOPTIONAL_SENTENCE() {
        return OPTIONAL_SENTENCE;
    }

    public final ContextIndependentParameterRenderer<FirClassLikeSymbol<?>> getRENDER_CLASS_OR_OBJECT_NAME_QUOTED() {
        return RENDER_CLASS_OR_OBJECT_NAME_QUOTED;
    }

    public final ContextIndependentParameterRenderer<FirClassSymbol<?>> getRENDER_CLASS_OR_OBJECT_QUOTED() {
        return RENDER_CLASS_OR_OBJECT_QUOTED;
    }

    public final DiagnosticParameterRenderer<Collection<? extends ConeKotlinType>> getRENDER_COLLECTION_OF_TYPES() {
        return RENDER_COLLECTION_OF_TYPES;
    }

    public final ContextIndependentParameterRenderer<FirEnumEntrySymbol> getRENDER_ENUM_ENTRY_QUOTED() {
        return RENDER_ENUM_ENTRY_QUOTED;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getRENDER_FQ_NAME_WITH_PREFIX() {
        return RENDER_FQ_NAME_WITH_PREFIX;
    }

    public final DiagnosticParameterRenderer<ConeKotlinType> getRENDER_TYPE() {
        return RENDER_TYPE;
    }

    public final DiagnosticParameterRenderer<ConeKotlinType> getRENDER_TYPE_WITH_ANNOTATIONS() {
        return RENDER_TYPE_WITH_ANNOTATIONS;
    }

    public final ContextIndependentParameterRenderer<VersionRequirement.Version> getREQUIRE_KOTLIN_VERSION() {
        return REQUIRE_KOTLIN_VERSION;
    }

    public final ContextIndependentParameterRenderer<FirClassLikeSymbol<?>> getSTAR_PROJECTED_CLASS() {
        return STAR_PROJECTED_CLASS;
    }

    public final ContextIndependentParameterRenderer<Collection<String>> getSTRING_TARGETS() {
        return STRING_TARGETS;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getSYMBOL() {
        return SYMBOL;
    }

    public final ContextIndependentParameterRenderer<Collection<? extends FirCallableSymbol<?>>> getSYMBOLS_ON_NEWLINE_WITH_INDENT() {
        return SYMBOLS_ON_NEWLINE_WITH_INDENT;
    }

    public final ContextIndependentParameterRenderer<Collection<? extends FirBasedSymbol<?>>> getSYMBOLS_ON_NEXT_LINES() {
        return SYMBOLS_ON_NEXT_LINES;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getSYMBOL_KIND() {
        return SYMBOL_KIND;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getSYMBOL_WITH_ALL_MODIFIERS() {
        return SYMBOL_WITH_ALL_MODIFIERS;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getSYMBOL_WITH_CONTAINING_DECLARATION() {
        return SYMBOL_WITH_CONTAINING_DECLARATION;
    }

    public final ContextIndependentParameterRenderer<DeclarationSymbolMarker> getSYMBOL_WITH_LOCATION() {
        return SYMBOL_WITH_LOCATION;
    }

    public final ContextIndependentParameterRenderer<FirBasedSymbol<?>> getTYPE_PARAMETER_OWNER_SYMBOL() {
        return TYPE_PARAMETER_OWNER_SYMBOL;
    }

    public final ContextIndependentParameterRenderer<FirVariableSymbol<?>> getVARIABLE_NAME() {
        return VARIABLE_NAME;
    }

    public final ContextIndependentParameterRenderer<List<? extends WhenMissingCase>> getWHEN_MISSING_CASES() {
        return WHEN_MISSING_CASES;
    }

    public final <Q> ContextIndependentParameterRenderer<Collection<? extends Q>> prefix(final String singular, final String plural, final ContextIndependentParameterRenderer<? super Collection<? extends Q>> renderer) {
        singular.getClass();
        plural.getClass();
        renderer.getClass();
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: x35
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderers.E(singular, plural, renderer, (Collection) obj);
            }
        });
    }

    public final <Q> DiagnosticParameterRenderer<Q> suggestIfNotNull(String message, DiagnosticParameterRenderer<? super Q> renderer) {
        message.getClass();
        renderer.getClass();
        return emptyStringIfNullOr(formatted(message, renderer));
    }
}
