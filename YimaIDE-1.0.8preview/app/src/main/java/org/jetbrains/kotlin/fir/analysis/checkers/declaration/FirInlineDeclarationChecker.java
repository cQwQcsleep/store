package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001%B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJA\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0015J-\u0010\u0016\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0014H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0017J-\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001aJ5\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001fJ!\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010#J+\u0010$\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0014R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0017¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "checkParameters", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "overriddenSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Ljava/util/List;)V", "checkContextParameters", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "checkParametersInNotInline", "checkNothingToInline", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "checkCanBeInlined", Argument.Delimiters.none, "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)Z", "isInlinableDefaultValue", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "checkCallableDeclaration", "InlineFunctionBodyContext", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineDeclarationChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirInlineDeclarationChecker INSTANCE = new FirInlineDeclarationChecker();

    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001CB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002JC\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0000R\u00020\u001dR\u00020\u001fj\u0006\u0010\u001e\u001a\u00020\u001dj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b#\u0010$JI\u0010%\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190&2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\u001dj\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0002\u0010'J\u0014\u0010(\u001a\u00020\u0014*\u00020\u00052\u0006\u0010)\u001a\u00020\u0017H\u0002J\u000e\u0010*\u001a\u0004\u0018\u00010+*\u00020\u0017H\u0002J7\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00172\n\u0010/\u001a\u0006\u0012\u0002\b\u000300R\u00020\u001dR\u00020\u001fj\u0006\u0010\u001e\u001a\u00020\u001dj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0002\u00101JA\u00102\u001a\u00020-2\u0006\u0010\u0016\u001a\u00020\u00172\n\u00103\u001a\u0006\u0012\u0002\b\u0003002\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\u001dR\u00020\u001fj\u0006\u0010\u001e\u001a\u00020\u001dj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0002\u00104J9\u00105\u001a\u00020-2\n\u00103\u001a\u0006\u0012\u0002\b\u0003002\u0006\u00106\u001a\u00020\u0017H\u0002R\u00020\u001dR\u00020\u001fj\u0006\u0010\u001e\u001a\u00020\u001dj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u0014*\u0006\u0012\u0002\b\u000309H\u0002J9\u0010:\u001a\u00020-2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\u001dR\u00020\u001fj\u0006\u0010\u001e\u001a\u00020\u001dj\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0002\u0010;J\u0010\u0010<\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u0019H\u0002J\u000e\u0010=\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u0005J\u001c\u0010?\u001a\u0004\u0018\u00010\u00052\n\u0010@\u001a\u0006\u0012\u0002\b\u00030A2\u0006\u0010B\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "inlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "inlineFunEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "parentInlineContext", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;)V", "getInlineFunction", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getInlineFunEffectiveVisibility", "()Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getParentInlineContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "isEffectivelyPrivateApiFunction", Argument.Delimiters.none, "accessedDeclarationEffectiveVisibility", "accessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "accessedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "shouldReportNonPublicCallFromPublicInline", "checkAccessedDeclaration", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext$AccessedDeclarationVisibilityData;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkAccessedDeclaration$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext$AccessedDeclarationVisibilityData;", "getNonPublicCallFromPublicInlineFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "isReachableDueToLocalDispatchReceiver", "access", "localDispatchReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "check", Argument.Delimiters.none, "statement", "targetSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "checkVisibilityAndAccess", "calledDeclaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkSuperCalls", "callExpression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "isDefinedInInlineFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "checkRecursion", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "isInsidePrivateClass", "isLessVisibleThanInlineFunction", "visibility", "lessVisibleVisibilityOrNull", "classLikeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "ignoreLocal", "AccessedDeclarationVisibilityData", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InlineFunctionBodyContext implements SessionHolder {
        private final EffectiveVisibility inlineFunEffectiveVisibility;
        private final FirFunction inlineFunction;
        private final boolean isEffectivelyPrivateApiFunction;
        private final InlineFunctionBodyContext parentInlineContext;
        private final FirSession session;

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext$AccessedDeclarationVisibilityData;", Argument.Delimiters.none, "isInlineFunPublicOrPublishedApi", Argument.Delimiters.none, "isCalledFunPublicOrPublishedApi", "calledFunEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "(ZZLorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "()Z", "getCalledFunEffectiveVisibility", "()Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class AccessedDeclarationVisibilityData {
            private final EffectiveVisibility calledFunEffectiveVisibility;
            private final boolean isCalledFunPublicOrPublishedApi;
            private final boolean isInlineFunPublicOrPublishedApi;

            public AccessedDeclarationVisibilityData(boolean z, boolean z2, EffectiveVisibility effectiveVisibility) {
                effectiveVisibility.getClass();
                this.isInlineFunPublicOrPublishedApi = z;
                this.isCalledFunPublicOrPublishedApi = z2;
                this.calledFunEffectiveVisibility = effectiveVisibility;
            }

            public static /* synthetic */ AccessedDeclarationVisibilityData copy$default(AccessedDeclarationVisibilityData accessedDeclarationVisibilityData, boolean z, boolean z2, EffectiveVisibility effectiveVisibility, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = accessedDeclarationVisibilityData.isInlineFunPublicOrPublishedApi;
                }
                if ((i & 2) != 0) {
                    z2 = accessedDeclarationVisibilityData.isCalledFunPublicOrPublishedApi;
                }
                if ((i & 4) != 0) {
                    effectiveVisibility = accessedDeclarationVisibilityData.calledFunEffectiveVisibility;
                }
                return accessedDeclarationVisibilityData.copy(z, z2, effectiveVisibility);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final boolean getIsInlineFunPublicOrPublishedApi() {
                return this.isInlineFunPublicOrPublishedApi;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getIsCalledFunPublicOrPublishedApi() {
                return this.isCalledFunPublicOrPublishedApi;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final EffectiveVisibility getCalledFunEffectiveVisibility() {
                return this.calledFunEffectiveVisibility;
            }

            public final AccessedDeclarationVisibilityData copy(boolean isInlineFunPublicOrPublishedApi, boolean isCalledFunPublicOrPublishedApi, EffectiveVisibility calledFunEffectiveVisibility) {
                calledFunEffectiveVisibility.getClass();
                return new AccessedDeclarationVisibilityData(isInlineFunPublicOrPublishedApi, isCalledFunPublicOrPublishedApi, calledFunEffectiveVisibility);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof AccessedDeclarationVisibilityData)) {
                    return false;
                }
                AccessedDeclarationVisibilityData accessedDeclarationVisibilityData = (AccessedDeclarationVisibilityData) other;
                return this.isInlineFunPublicOrPublishedApi == accessedDeclarationVisibilityData.isInlineFunPublicOrPublishedApi && this.isCalledFunPublicOrPublishedApi == accessedDeclarationVisibilityData.isCalledFunPublicOrPublishedApi && Intrinsics.areEqual(this.calledFunEffectiveVisibility, accessedDeclarationVisibilityData.calledFunEffectiveVisibility);
            }

            public final EffectiveVisibility getCalledFunEffectiveVisibility() {
                return this.calledFunEffectiveVisibility;
            }

            public int hashCode() {
                return (((Boolean.hashCode(this.isInlineFunPublicOrPublishedApi) * 31) + Boolean.hashCode(this.isCalledFunPublicOrPublishedApi)) * 31) + this.calledFunEffectiveVisibility.hashCode();
            }

            public final boolean isCalledFunPublicOrPublishedApi() {
                return this.isCalledFunPublicOrPublishedApi;
            }

            public final boolean isInlineFunPublicOrPublishedApi() {
                return this.isInlineFunPublicOrPublishedApi;
            }

            public String toString() {
                return "AccessedDeclarationVisibilityData(isInlineFunPublicOrPublishedApi=" + this.isInlineFunPublicOrPublishedApi + ", isCalledFunPublicOrPublishedApi=" + this.isCalledFunPublicOrPublishedApi + ", calledFunEffectiveVisibility=" + this.calledFunEffectiveVisibility + ')';
            }
        }

        public InlineFunctionBodyContext(FirFunction firFunction, EffectiveVisibility effectiveVisibility, FirSession firSession, InlineFunctionBodyContext inlineFunctionBodyContext) {
            firFunction.getClass();
            effectiveVisibility.getClass();
            firSession.getClass();
            this.inlineFunction = firFunction;
            this.inlineFunEffectiveVisibility = effectiveVisibility;
            this.session = firSession;
            this.parentInlineContext = inlineFunctionBodyContext;
            this.isEffectivelyPrivateApiFunction = effectiveVisibility.getPrivateApi();
        }

        private final EffectiveVisibility accessedDeclarationEffectiveVisibility(FirStatement accessExpression, FirBasedSymbol<?> accessedSymbol) {
            EffectiveVisibility publishedApiEffectiveVisibility;
            if (accessedSymbol instanceof FirCallableSymbol) {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(accessedSymbol, FirResolvePhase.STATUS);
                publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(accessedSymbol.getFir());
                if (publishedApiEffectiveVisibility == null) {
                    publishedApiEffectiveVisibility = ((FirCallableSymbol) accessedSymbol).getResolvedStatus().getEffectiveVisibility();
                }
            } else {
                if (!(accessedSymbol instanceof FirClassLikeSymbol)) {
                    AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                    wq6.a();
                    return null;
                }
                FirLazyDeclarationResolverKt.lazyResolveToPhase(accessedSymbol, FirResolvePhase.STATUS);
                publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(accessedSymbol.getFir());
                if (publishedApiEffectiveVisibility == null) {
                    publishedApiEffectiveVisibility = ((FirClassLikeSymbol) accessedSymbol).getResolvedStatus().getEffectiveVisibility();
                }
            }
            return (isReachableDueToLocalDispatchReceiver(publishedApiEffectiveVisibility, accessExpression) || Intrinsics.areEqual(publishedApiEffectiveVisibility, EffectiveVisibility.Local.INSTANCE)) ? EffectiveVisibility.Public.INSTANCE : publishedApiEffectiveVisibility;
        }

        private final void checkRecursion(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement) {
            if (Intrinsics.areEqual(firBasedSymbol, this.inlineFunction.getSymbol())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getRECURSION_IN_INLINE(), (Object) firBasedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }

        private final void checkSuperCalls(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirStatement firStatement) {
            FirExpression dispatchReceiver;
            FirClassifierSymbol<?> symbol;
            if (firStatement instanceof FirQualifiedAccessExpression) {
                dispatchReceiver = ((FirQualifiedAccessExpression) firStatement).getDispatchReceiver();
            } else {
                dispatchReceiver = firStatement instanceof FirVariableAssignment ? FirExpressionUtilKt.getDispatchReceiver((FirVariableAssignment) firStatement) : null;
            }
            FirQualifiedAccessExpression firQualifiedAccessExpression = dispatchReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) dispatchReceiver : null;
            if (firQualifiedAccessExpression != null && (firQualifiedAccessExpression instanceof FirSuperReceiverExpression)) {
                FirSuperReceiverExpression firSuperReceiverExpression = (FirSuperReceiverExpression) firQualifiedAccessExpression;
                FirExpression dispatchReceiver2 = firSuperReceiverExpression.getDispatchReceiver();
                ConeKotlinType resolvedType = dispatchReceiver2 != null ? FirTypeUtilsKt.getResolvedType(dispatchReceiver2) : null;
                if (resolvedType == null || (symbol = ToSymbolUtilsKt.toSymbol(checkerContext, resolvedType)) == null || isDefinedInInlineFunction(symbol)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSuperReceiverExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getSUPER_CALL_FROM_PUBLIC_INLINE(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }

        private final void checkVisibilityAndAccess(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol, KtSourceElement ktSourceElement) {
            if (Intrinsics.areEqual(firCallableSymbol.getName(), StandardNames.BACKING_FIELD)) {
                return;
            }
            AccessedDeclarationVisibilityData accessedDeclarationVisibilityDataCheckAccessedDeclaration$org_jetbrains_kotlin_checkers = checkAccessedDeclaration$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, ktSourceElement, firStatement, firCallableSymbol);
            boolean isInlineFunPublicOrPublishedApi = accessedDeclarationVisibilityDataCheckAccessedDeclaration$org_jetbrains_kotlin_checkers.getIsInlineFunPublicOrPublishedApi();
            boolean isCalledFunPublicOrPublishedApi = accessedDeclarationVisibilityDataCheckAccessedDeclaration$org_jetbrains_kotlin_checkers.getIsCalledFunPublicOrPublishedApi();
            EffectiveVisibility calledFunEffectiveVisibility = accessedDeclarationVisibilityDataCheckAccessedDeclaration$org_jetbrains_kotlin_checkers.getCalledFunEffectiveVisibility();
            if (isInlineFunPublicOrPublishedApi && isCalledFunPublicOrPublishedApi) {
                checkSuperCalls(checkerContext, diagnosticReporter, firCallableSymbol, firStatement);
            }
            boolean z = firCallableSymbol instanceof FirConstructorSymbol;
            if (isInlineFunPublicOrPublishedApi) {
                Visibility visibility = this.inlineFunEffectiveVisibility.toVisibility();
                Visibilities.Protected r8 = Visibilities.Protected.INSTANCE;
                if (visibility == r8 || calledFunEffectiveVisibility.toVisibility() != r8 || (firStatement instanceof FirDelegatedConstructorCall)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) (z ? FirErrors.INSTANCE.getPROTECTED_CONSTRUCTOR_CALL_FROM_PUBLIC_INLINE() : FirErrors.INSTANCE.getPROTECTED_CALL_FROM_PUBLIC_INLINE_ERROR()), (Object) this.inlineFunction.getSymbol(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }

        /* JADX WARN: Code duplicated, block: B:15:0x003f  */
        private final KtDiagnosticFactory2<FirBasedSymbol<?>, FirBasedSymbol<?>> getNonPublicCallFromPublicInlineFactory(CheckerContext checkerContext, FirStatement firStatement, FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement) {
            FirPropertyAccessorSymbol setterSymbol;
            boolean z;
            FirBasedSymbol<?> symbol;
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProhibitPrivateOperatorCallInInline)) {
                boolean zAreEqual = Intrinsics.areEqual(ktSourceElement.getKind(), KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE);
                if (Intrinsics.areEqual(ktSourceElement.getKind(), KtFakeSourceElementKind.DesugaredForLoop.INSTANCE)) {
                    FirReference reference = ReferenceUtilsKt.toReference(firStatement, getSession());
                    if (Intrinsics.areEqual((reference == null || (symbol = FirReferenceUtilsKt.getSymbol(reference)) == null) ? null : FirDeclarationUtilKt.getMemberDeclarationNameOrNull(symbol), OperatorNameConventions.ITERATOR)) {
                        z = false;
                    } else {
                        z = true;
                    }
                } else {
                    z = false;
                }
                if (zAreEqual || z) {
                    return FirErrors.INSTANCE.getNON_PUBLIC_CALL_FROM_PUBLIC_INLINE_DEPRECATION();
                }
            }
            if ((firBasedSymbol instanceof FirCallableSymbol) && ((FirCallableSymbol) firBasedSymbol).getRawStatus().isInline()) {
                return FirErrors.INSTANCE.getNON_PUBLIC_INLINE_CALL_FROM_PUBLIC_INLINE();
            }
            if (firBasedSymbol instanceof FirPropertySymbol) {
                FirStatement firStatement2 = (FirStatement) CollectionsKt.getOrNull(checkerContext.getCallsOrAssignments(), CollectionsKt.getLastIndex(checkerContext.getCallsOrAssignments()) - 1);
                if ((firStatement2 instanceof FirVariableAssignment) && Intrinsics.areEqual(((FirVariableAssignment) firStatement2).getLValue(), firStatement) && (setterSymbol = ((FirPropertySymbol) firBasedSymbol).getSetterSymbol()) != null && setterSymbol.getRawStatus().isInline()) {
                    return FirErrors.INSTANCE.getNON_PUBLIC_INLINE_CALL_FROM_PUBLIC_INLINE();
                }
                FirPropertyAccessorSymbol getterSymbol = ((FirPropertySymbol) firBasedSymbol).getGetterSymbol();
                if (getterSymbol != null && getterSymbol.getRawStatus().isInline()) {
                    return FirErrors.INSTANCE.getNON_PUBLIC_INLINE_CALL_FROM_PUBLIC_INLINE();
                }
            }
            return FirErrors.INSTANCE.getNON_PUBLIC_CALL_FROM_PUBLIC_INLINE();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final boolean isDefinedInInlineFunction(FirClassifierSymbol<?> firClassifierSymbol) {
            if (firClassifierSymbol instanceof FirAnonymousObjectSymbol) {
                return true;
            }
            if (firClassifierSymbol instanceof FirRegularClassSymbol) {
                return ((FirClassLikeDeclaration) ((FirClassLikeSymbol) firClassifierSymbol).getFir()).getIsLocal();
            }
            if ((firClassifierSymbol instanceof FirTypeAliasSymbol) || (firClassifierSymbol instanceof FirTypeParameterSymbol)) {
                w04.a("Unexpected classifier declaration type: ", firClassifierSymbol);
                return false;
            }
            bu8.a();
            return false;
        }

        private final boolean isInsidePrivateClass(FirBasedSymbol<?> firBasedSymbol) {
            FirClassLikeSymbol<?> symbol;
            ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(firBasedSymbol);
            if (ownerLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, ownerLookupTag)) == null || (symbol instanceof FirAnonymousObjectSymbol)) {
                return false;
            }
            boolean z = symbol instanceof FirRegularClassSymbol;
            if (!z && !(symbol instanceof FirTypeAliasSymbol)) {
                bu8.a();
                return false;
            }
            Visibility visibility = symbol.getRawStatus().getVisibility();
            if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) {
                return true;
            }
            if ((firBasedSymbol instanceof FirCallableSymbol) && z && symbol.getRawStatus().isCompanion()) {
                return isInsidePrivateClass(symbol);
            }
            return false;
        }

        private final boolean isReachableDueToLocalDispatchReceiver(EffectiveVisibility effectiveVisibility, FirStatement firStatement) {
            ConeKotlinType coneKotlinTypeLocalDispatchReceiver = localDispatchReceiver(firStatement);
            if (coneKotlinTypeLocalDispatchReceiver == null) {
                return false;
            }
            EffectiveVisibility.Permissiveness permissivenessRelation = new EffectiveVisibility.Protected(TypeSystemContextHelpersKt.typeConstructor(coneKotlinTypeLocalDispatchReceiver, TypeComponentsKt.getTypeContext(getSession()))).relation(effectiveVisibility, TypeComponentsKt.getTypeContext(getSession()));
            return permissivenessRelation == EffectiveVisibility.Permissiveness.SAME || permissivenessRelation == EffectiveVisibility.Permissiveness.LESS;
        }

        private final ConeKotlinType localDispatchReceiver(FirStatement firStatement) {
            FirExpression dispatchReceiver;
            ConeKotlinType resolvedType;
            FirQualifiedAccessExpression firQualifiedAccessExpression = firStatement instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firStatement : null;
            if (firQualifiedAccessExpression != null && (dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver()) != null && (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) != null) {
                FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(this, resolvedType);
                if (Intrinsics.areEqual(classLikeSymbol != null ? classLikeSymbol.getResolvedStatus().getEffectiveVisibility() : null, EffectiveVisibility.Local.INSTANCE)) {
                    return resolvedType;
                }
            }
            return null;
        }

        private final boolean shouldReportNonPublicCallFromPublicInline(EffectiveVisibility accessedDeclarationEffectiveVisibility) {
            return (!this.inlineFunEffectiveVisibility.getPublicApi() || accessedDeclarationEffectiveVisibility.getPublicApi() || accessedDeclarationEffectiveVisibility == EffectiveVisibility.Local.INSTANCE) ? false : true;
        }

        public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firStatement.getClass();
            firCallableSymbol.getClass();
            KtSourceElement source = firStatement.getSource();
            if (source == null) {
                return;
            }
            checkVisibilityAndAccess(checkerContext, diagnosticReporter, firStatement, firCallableSymbol, source);
            checkRecursion(checkerContext, diagnosticReporter, firCallableSymbol, source);
        }

        public final AccessedDeclarationVisibilityData checkAccessedDeclaration$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirStatement firStatement, FirBasedSymbol<?> firBasedSymbol) {
            EffectiveVisibility effectiveVisibility;
            checkerContext.getClass();
            diagnosticReporter.getClass();
            ktSourceElement.getClass();
            firStatement.getClass();
            firBasedSymbol.getClass();
            EffectiveVisibility effectiveVisibilityAccessedDeclarationEffectiveVisibility = accessedDeclarationEffectiveVisibility(firStatement, firBasedSymbol);
            FirCallableSymbol<?> firCallableSymbolUnwrapDataClassCopyWithPrimaryConstructorOrNull = FirInlineDeclarationCheckerKt.unwrapDataClassCopyWithPrimaryConstructorOrNull(firBasedSymbol, getSession());
            EffectiveVisibility effectiveVisibility2 = firCallableSymbolUnwrapDataClassCopyWithPrimaryConstructorOrNull != null ? firCallableSymbolUnwrapDataClassCopyWithPrimaryConstructorOrNull.getResolvedStatus().getEffectiveVisibility() : null;
            if (shouldReportNonPublicCallFromPublicInline(effectiveVisibilityAccessedDeclarationEffectiveVisibility)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) getNonPublicCallFromPublicInlineFactory(checkerContext, firStatement, firBasedSymbol, ktSourceElement), (Object) firBasedSymbol, (Object) this.inlineFunction.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else if (effectiveVisibility2 != null && shouldReportNonPublicCallFromPublicInline(effectiveVisibility2)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation1) FirErrors.INSTANCE.getNON_PUBLIC_DATA_COPY_CALL_FROM_PUBLIC_INLINE(), (Object) this.inlineFunction.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                if (this.isEffectivelyPrivateApiFunction || !isInsidePrivateClass(firBasedSymbol)) {
                    if (!Intrinsics.areEqual(this.inlineFunEffectiveVisibility, EffectiveVisibility.Public.INSTANCE) && (firStatement instanceof FirCallableReferenceAccess) && isLessVisibleThanInlineFunction(effectiveVisibilityAccessedDeclarationEffectiveVisibility)) {
                        effectiveVisibility = effectiveVisibilityAccessedDeclarationEffectiveVisibility;
                        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation3<FirBasedSymbol<?>, EffectiveVisibility, EffectiveVisibility>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getCALLABLE_REFERENCE_TO_LESS_VISIBLE_DECLARATION_IN_INLINE()), firBasedSymbol, effectiveVisibility, this.inlineFunEffectiveVisibility, (64 & 64) != 0 ? null : null);
                    }
                    return new AccessedDeclarationVisibilityData(this.inlineFunEffectiveVisibility.getPublicApi(), effectiveVisibility.getPublicApi(), effectiveVisibility);
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getPRIVATE_CLASS_MEMBER_FROM_INLINE(), (Object) firBasedSymbol, (Object) this.inlineFunction.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            effectiveVisibility = effectiveVisibilityAccessedDeclarationEffectiveVisibility;
            return new AccessedDeclarationVisibilityData(this.inlineFunEffectiveVisibility.getPublicApi(), effectiveVisibility.getPublicApi(), effectiveVisibility);
        }

        public final EffectiveVisibility getInlineFunEffectiveVisibility() {
            return this.inlineFunEffectiveVisibility;
        }

        public final FirFunction getInlineFunction() {
            return this.inlineFunction;
        }

        public final InlineFunctionBodyContext getParentInlineContext() {
            return this.parentInlineContext;
        }

        @Override // org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.session;
        }

        public final boolean isLessVisibleThanInlineFunction(EffectiveVisibility visibility) {
            visibility.getClass();
            if (Intrinsics.areEqual(visibility, EffectiveVisibility.Local.INSTANCE) && this.inlineFunEffectiveVisibility.getPrivateApi()) {
                return false;
            }
            EffectiveVisibility.Permissiveness permissivenessRelation = visibility.relation(this.inlineFunEffectiveVisibility, TypeComponentsKt.getTypeContext(getSession()));
            return permissivenessRelation == EffectiveVisibility.Permissiveness.LESS || permissivenessRelation == EffectiveVisibility.Permissiveness.UNKNOWN;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public final EffectiveVisibility lessVisibleVisibilityOrNull(FirClassLikeSymbol<?> classLikeSymbol, boolean ignoreLocal) {
            classLikeSymbol.getClass();
            if (((FirClassLikeDeclaration) classLikeSymbol.getFir()).getIsLocal() && ignoreLocal) {
                return null;
            }
            FirLazyDeclarationResolverKt.lazyResolveToPhase(classLikeSymbol, FirResolvePhase.STATUS);
            EffectiveVisibility publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility((FirDeclaration) classLikeSymbol.getFir());
            if (publishedApiEffectiveVisibility == null) {
                publishedApiEffectiveVisibility = classLikeSymbol.getResolvedStatus().getEffectiveVisibility();
            }
            if (isLessVisibleThanInlineFunction(publishedApiEffectiveVisibility)) {
                return publishedApiEffectiveVisibility;
            }
            return null;
        }
    }

    private FirInlineDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean checkCanBeInlined(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration, EffectiveVisibility effectiveVisibility) {
        if (ClassMembersKt.containingClassLookupTag(firCallableDeclaration) == null || Intrinsics.areEqual(effectiveVisibility, EffectiveVisibility.PrivateInClass.INSTANCE) || DeclarationUtilsKt.isEffectivelyFinal(firCallableDeclaration)) {
            return true;
        }
        KtSourceElement source = firCallableDeclaration.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), FirErrors.INSTANCE.getDECLARATION_CANT_BE_INLINED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        return false;
    }

    private final void checkContextParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        for (FirValueParameter firValueParameter : firCallableDeclaration.getContextParameters()) {
            if (FunctionalTypeUtilsKt.functionTypeKind$default(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())), checkerContext.getSession(), false, 2, (Object) null) != null) {
                if (!firValueParameter.getIsNoinline()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONTEXT_PARAMETER_MUST_BE_NOINLINE(), (Object) firValueParameter.getSymbol(), (Object) firCallableDeclaration.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
            } else if (firValueParameter.getIsNoinline() || firValueParameter.getIsCrossinline()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getILLEGAL_INLINE_PARAMETER_MODIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkNothingToInline(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction) {
        if (firNamedFunction.getStatus().isExpect() || firNamedFunction.getStatus().isSuspend()) {
            return;
        }
        List<FirTypeParameter> typeParameters = firNamedFunction.getTypeParameters();
        if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
            Iterator<T> it = typeParameters.iterator();
            while (it.hasNext()) {
                if (((FirTypeParameter) it.next()).getSymbol().isReified()) {
                    return;
                }
            }
        }
        FirSession session = checkerContext.getSession();
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
            Iterator<T> it2 = valueParameters.iterator();
            while (it2.hasNext()) {
                if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isInlinable((FirValueParameter) it2.next(), checkerContext.getSession())) {
                    return;
                }
            }
        }
        List<FirValueParameter> contextParameters = firNamedFunction.getContextParameters();
        if (!(contextParameters instanceof Collection) || !contextParameters.isEmpty()) {
            Iterator<T> it3 = contextParameters.iterator();
            while (it3.hasNext()) {
                if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isInlinable((FirValueParameter) it3.next(), checkerContext.getSession())) {
                    return;
                }
            }
        }
        if (FirHelpersKt.isInlineOnly(firNamedFunction, session) || DeclarationUtilsKt.needsMultiFieldValueClassFlattening(firNamedFunction.getReturnTypeRef(), session)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), FirErrors.INSTANCE.getNOTHING_TO_INLINE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction, List<? extends FirCallableSymbol<? extends FirCallableDeclaration>> list) {
        DiagnosticReporter diagnosticReporter2;
        for (FirValueParameter firValueParameter : firNamedFunction.getValueParameters()) {
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()));
            FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinTypeFullyExpandedType, checkerContext.getSession(), false, 2, (Object) null);
            boolean z = functionTypeKindFunctionTypeKind$default != null;
            boolean z2 = functionTypeKindFunctionTypeKind$default != null && FunctionTypeKindKt.isSuspendOrKSuspendFunction(functionTypeKindFunctionTypeKind$default);
            FirExpression defaultValue = firValueParameter.getDefaultValue();
            if (z || !(firValueParameter.getIsNoinline() || firValueParameter.getIsCrossinline())) {
                diagnosticReporter2 = diagnosticReporter;
            } else {
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter2, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getILLEGAL_INLINE_PARAMETER_MODIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if (!firValueParameter.getIsNoinline()) {
                if (firNamedFunction.getStatus().isSuspend() && defaultValue != null && z2) {
                    FirInlineCheckerPlatformSpecificComponentKt.getInlineCheckerExtension(checkerContext.getSession()).checkSuspendFunctionalParameterWithDefaultValue(checkerContext, diagnosticReporter2, firValueParameter);
                }
                if (z2 && !firValueParameter.getIsCrossinline() && !firNamedFunction.getStatus().isSuspend()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter2, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getINLINE_SUSPEND_FUNCTION_TYPE_UNSUPPORTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType) && z) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getNULLABLE_INLINE_PARAMETER(), (Object) firValueParameter.getSymbol(), (Object) firNamedFunction.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                if (z && defaultValue != null && !isInlinableDefaultValue(checkerContext, defaultValue)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) defaultValue.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINVALID_DEFAULT_FUNCTIONAL_PARAMETER_FOR_INLINE(), (Object) firValueParameter.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        if (!list.isEmpty()) {
            for (FirTypeParameter firTypeParameter : firNamedFunction.getTypeParameters()) {
                if (firTypeParameter.getIsReified()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeParameter.getSource(), FirErrors.INSTANCE.getREIFIED_TYPE_PARAMETER_IN_OVERRIDE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
        FirInlineCheckerPlatformSpecificComponentKt.getInlineCheckerExtension(checkerContext.getSession()).checkParametersWithInheritedDefaultValues(checkerContext, diagnosticReporter, firNamedFunction, list);
    }

    private final void checkParametersInNotInline(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        for (FirValueParameter firValueParameter : firFunction.getValueParameters()) {
            if (firValueParameter.getIsNoinline() || firValueParameter.getIsCrossinline()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getILLEGAL_INLINE_PARAMETER_MODIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final boolean isInlinableDefaultValue(CheckerContext checkerContext, FirExpression firExpression) {
        if ((firExpression instanceof FirCallableReferenceAccess) || (firExpression instanceof FirAnonymousFunctionExpression)) {
            return true;
        }
        if ((firExpression instanceof FirLiteralExpression) && ((FirLiteralExpression) firExpression).getValue() == null) {
            return true;
        }
        return (firExpression instanceof FirFunctionCall) && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ProhibitFunctionCallsInDefaultParametersOfInline);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (!firFunction.getStatus().isInline()) {
            checkParametersInNotInline(checkerContext, diagnosticReporter, firFunction);
        } else if (FirInlineCheckerPlatformSpecificComponentKt.getInlineCheckerExtension(checkerContext.getSession()).isGenerallyOk(checkerContext, diagnosticReporter, firFunction)) {
            if ((firFunction instanceof FirPropertyAccessor) || (firFunction instanceof FirNamedFunction)) {
                checkCallableDeclaration(checkerContext, diagnosticReporter, firFunction);
            }
        }
    }

    public final void checkCallableDeclaration(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        EffectiveVisibility effectiveVisibility;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (firCallableDeclaration instanceof FirPropertyAccessor) {
            return;
        }
        List<FirCallableSymbol<?>> listDirectOverriddenSymbolsSafe = FirHelpersKt.directOverriddenSymbolsSafe(checkerContext, firCallableDeclaration.getSymbol());
        if (firCallableDeclaration instanceof FirNamedFunction) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firCallableDeclaration;
            checkParameters(checkerContext, diagnosticReporter, firNamedFunction, listDirectOverriddenSymbolsSafe);
            checkNothingToInline(checkerContext, diagnosticReporter, firNamedFunction);
        }
        checkContextParameters(checkerContext, diagnosticReporter, firCallableDeclaration);
        FirDeclarationStatus status = firCallableDeclaration.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        if (!checkCanBeInlined(checkerContext, diagnosticReporter, firCallableDeclaration, effectiveVisibility) || listDirectOverriddenSymbolsSafe.isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), FirErrors.INSTANCE.getOVERRIDE_BY_INLINE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
