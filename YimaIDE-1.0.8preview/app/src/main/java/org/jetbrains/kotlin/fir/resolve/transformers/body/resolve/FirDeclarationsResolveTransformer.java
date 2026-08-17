package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.OnlyForDefaultLanguageFeatureDisabled;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveState;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWrappedDelegateExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirEmptyExpressionBlockBuilderKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtension;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponent;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponentKt;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionModeKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImpl;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeLocalVariableNoTypeOrInitializer;
import org.jetbrains.kotlin.fir.resolve.inference.FirDelegatedPropertyInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponentsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.FirStatusResolver;
import org.jetbrains.kotlin.fir.resolve.transformers.TransformUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirContractResolveTransformerAdapterKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirMemberTypeParameterScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ResolvedImplicitTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.inference.InferenceUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDirectionCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.MutableVariableWithConstraints;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.ProvideDelegateFixationPosition;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0002·\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0017H\u0004J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010$\u001a\u00020\u0013*\u00020%2\u0006\u0010 \u001a\u00020\u001fH\u0002J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001fH\u0002J \u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J\u001a\u0010+\u001a\u0004\u0018\u00010%2\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020(H\u0002J\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u00101\u001a\u00020(2\u0006\u00102\u001a\u000203H\u0002J&\u00104\u001a\u0010\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000207\u0018\u0001052\u0006\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u001fH\u0002J\u001e\u0010=\u001a\u00020\u0013*\u00020\u001f2\u0006\u0010>\u001a\u00020*2\b\b\u0002\u0010)\u001a\u00020*H\u0002J\u0014\u0010?\u001a\u00020\u0013*\u00020\u001f2\u0006\u0010)\u001a\u00020*H\u0002J\u000e\u0010@\u001a\u0004\u0018\u00010A*\u000207H\u0002J\u001c\u0010B\u001a\u00020\u0013*\u00020\u001f2\u0006\u0010>\u001a\u00020*2\u0006\u0010)\u001a\u00020*H\u0002J\u001e\u0010C\u001a\u00020\u0013*\u00020/2\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020*H\u0002J%\u0010G\u001a\u0002HH\"\b\b\u0000\u0010H*\u00020E*\u0002HH2\b\u0010I\u001a\u0004\u0018\u00010JH\u0002¢\u0006\u0002\u0010KJ \u0010L\u001a\u00020\u00132\u0006\u0010M\u001a\u00020/2\u0006\u0010N\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020*H\u0002J$\u0010O\u001a\u00020\u0010*\u00020\n2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010Q2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u001fH\u0002J\u0018\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020W2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001e\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020Z2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020Z0]H\u0016J\u0018\u0010^\u001a\u00020Z2\u0006\u0010[\u001a\u00020Z2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020`2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020c2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010h\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010i\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020T0]H\u0016J\u0018\u0010j\u001a\u00020W2\u0006\u0010X\u001a\u00020W2\u0006\u0010\r\u001a\u00020\u000eH\u0004J\u001e\u0010k\u001a\u00020W2\u0006\u0010X\u001a\u00020W2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020W0]H\u0016J\u0018\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020m2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020p2\u0006\u0010\r\u001a\u00020\u000eH\u0016J'\u0010r\u001a\u0002Hs\"\b\b\u0000\u0010s*\u00020t2\u0006\u0010u\u001a\u0002Hs2\u0006\u0010)\u001a\u00020*H\u0002¢\u0006\u0002\u0010vJ\u0018\u0010w\u001a\u00020t2\u0006\u0010u\u001a\u00020t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010x\u001a\u00020t2\u0006\u0010u\u001a\u00020t2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*H\u0014J\u0018\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020{2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010}\u001a\u00020~2\u0006\u0010\u007f\u001a\u00020~2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0019\u0010\u0080\u0001\u001a\u00020{2\u0006\u0010|\u001a\u00020{2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u001c\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u0084\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u001c\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0086\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u0089\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u008b\u0001\u001a\u00030\u008c\u00012\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u008f\u0001\u001a\u00030\u0090\u00012\b\u0010\u0091\u0001\u001a\u00030\u0090\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0092\u0001\u001a\u00030\u008c\u00012\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001H\u0002J3\u0010\u0095\u0001\u001a\u00030\u0090\u00012\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u000107H\u0003b\u0012\b\u0097\u0001\u0012\r\b\u0098\u0001\u0012\b\b\n0\u0099\u00018\u009a\u0001J/\u0010\u009b\u0001\u001a\u00030\u009c\u0001*\u00030\u0090\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009c\u0001H\u0003b\u0012\b\u0097\u0001\u0012\r\b\u0098\u0001\u0012\b\b\n0\u0099\u00018\u009a\u0001J9\u0010\u009e\u0001\u001a\n\u0012\u0005\u0012\u00030\u0089\u00010\u009f\u00012\b\u0010 \u0001\u001a\u00030¡\u00012\b\u0010¢\u0001\u001a\u00030\u0090\u0001H\u0003b\u0012\b\u0097\u0001\u0012\r\b\u0098\u0001\u0012\b\b\n0\u0099\u00018\u009a\u0001J:\u0010£\u0001\u001a\n\u0012\u0005\u0012\u00030\u0089\u00010\u009f\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u0001072\b\u0010¢\u0001\u001a\u00030\u0090\u0001H\u0003b\u0012\b\u0097\u0001\u0012\r\b\u0098\u0001\u0012\b\b\n0\u0099\u00018\u009a\u0001J?\u0010¤\u0001\u001a\n\u0012\u0005\u0012\u00030\u0089\u00010\u009f\u00012\u000e\u0010¥\u0001\u001a\t\u0012\u0004\u0012\u0002070\u009f\u00012\b\u0010¢\u0001\u001a\u00030\u0090\u0001H\u0003b\u0012\b\u0097\u0001\u0012\r\b\u0098\u0001\u0012\b\b\n0\u0099\u00018\u009a\u0001J%\u0010¦\u0001\u001a\u00020\u00132\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\n\u0010§\u0001\u001a\u0005\u0018\u00010\u009c\u0001H\u0000¢\u0006\u0003\b¨\u0001J\u001f\u0010©\u0001\u001a\u00030\u0090\u00012\b\u0010\u0091\u0001\u001a\u00030\u0090\u00012\t\u0010ª\u0001\u001a\u0004\u0018\u00010EH\u0002J\u001c\u0010«\u0001\u001a\u00030¬\u00012\b\u0010\u00ad\u0001\u001a\u00030¬\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0016J$\u0010«\u0001\u001a\u00030¬\u00012\b\u0010\u00ad\u0001\u001a\u00030¬\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010®\u0001\u001a\u00020\u00132\u0007\u0010<\u001a\u00030¯\u0001H\u0002J\u0019\u0010²\u0001\u001a\u00030\u009c\u0001*\u00020E2\t\u0010³\u0001\u001a\u0004\u0018\u00010JH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010°\u0001\u001a\u00020**\u00030¯\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u001b\u0010´\u0001\u001a\u00020**\u00020t8BX\u0082\u0004¢\u0006\b\u001a\u0006\bµ\u0001\u0010¶\u0001¨\u0006¸\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirPartialBodyResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "statusResolver", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirStatusResolver;", "visibilityForApproximation", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "transformDeclarationContent", "declaration", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "declarationStatus", "prepareSignatureForBodyResolve", Argument.Delimiters.none, "callableMember", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "doTransformTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "transformEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "transformDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "danglingModifierList", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "transformField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "field", "replacePropertyReferenceTypeInDelegateAccessors", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "transformPropertyAccessorsWithDelegate", "delegateContainer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "shouldResolveEverything", Argument.Delimiters.none, "getResolvedProvideDelegateIfSuccessful", "provideDelegateCall", "resolvedDelegateExpression", "transformPropertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "propertyAccessor", "transformDelegateExpression", "delegate", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;", "findResultTypeForInnerVariableIfNeeded", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "provideDelegate", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "transformLocalVariable", "variable", "resolveAccessors", "mayResolveSetterBody", "resolveGetter", "unwrapTopLevelVariableType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "resolveSetter", "transformTypeWithPropertyType", "propertyTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "forceUpdateForNonImplicitTypes", "copyWithNewNullableSource", "R", "newSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformAccessor", "accessor", "owner", "resolveStatus", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "containingProperty", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "regularClass", "withScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "action", "Lkotlin/Function0;", "transformScript", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "transformCodeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "codeFragment", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "doTransformFile", "withFile", "doTransformRegularClassContent", "forRegularClassBody", "transformAnonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "anonymousObject", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "transformFunctionWithGivenSignature", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "function", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Z)Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "transformFunction", "transformFunctionContent", "resolutionModeForBody", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformErrorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "errorPrimaryConstructor", "transformConstructorContent", "transformAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "transformAnonymousInitializerContent", "transformReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "receiverParameter", "transformValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "valueParameter", "transformAnonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "transformAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunction", "transformTopLevelAnonymousFunctionExpression", "expectedTypeData", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType;", "transformTopLevelAnonymousFunctionInObsoleteWay", "expectedType", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "ResolveTopLevelLambdasAsSyntheticCallArgument", "computeReturnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "expected", "obtainValueParametersFromResolvedLambdaAtom", Argument.Delimiters.none, "resolvedLambdaAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "lambda", "obtainValueParametersFromExpectedType", "obtainValueParametersFromExpectedParameterTypes", "expectedTypeParameterTypes", "doTransformAnonymousFunctionBodyFromCallCompletion", "expectedReturnTypeFromCallPosition", "doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve", "transformAnonymousFunctionBody", "expectedReturnTypeRef", "transformBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "backingField", "storeVariableReturnType", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "isLocalVariableOrParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Z", "toExpectedTypeRef", "fallbackSource", "bodyResolved", "getBodyResolved", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "ImplicitToErrorTypeTransformer", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDeclarationsResolveTransformer extends FirPartialBodyResolveTransformer {
    private final FirStatusResolver statusResolver;

    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016Ê\u0001\u000e\b\u0010\u0012\n\b\u0011\u0012\u0006\b\n0\u00128\u0013¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer$ImplicitToErrorTypeTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "<init>", "()V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformValueParameter", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "ResolveTopLevelLambdasAsSyntheticCallArgument"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ImplicitToErrorTypeTransformer extends FirTransformer<Object> {
        public static final ImplicitToErrorTypeTransformer INSTANCE = new ImplicitToErrorTypeTransformer();

        private ImplicitToErrorTypeTransformer() {
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public <E extends FirElement> E transformElement(E element, Object data) {
            element.getClass();
            return element;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformValueParameter(FirValueParameter valueParameter, Object data) {
            valueParameter.getClass();
            FirSession session = valueParameter.getModuleData().getSession();
            try {
                if (valueParameter.getReturnTypeRef() instanceof FirImplicitTypeRef) {
                    FirTypeRef returnTypeRef = valueParameter.getReturnTypeRef();
                    ConeErrorType coneErrorType = new ConeErrorType(new ConeSimpleDiagnostic("No type for parameter", DiagnosticKind.ValueParameterWithNoTypeAnnotation), false, null, null, null, null, null, 126, null);
                    KtSourceElement source = valueParameter.getSource();
                    valueParameter.replaceReturnTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef, coneErrorType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null) : null));
                }
                return valueParameter;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(valueParameter, th);
                wq6.a();
                return null;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDeclarationsResolveTransformer(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
        super(firAbstractBodyResolveTransformerDispatcher);
        firAbstractBodyResolveTransformerDispatcher.getClass();
        this.statusResolver = new FirStatusResolver(getSession(), getComponents().getScopeSession());
    }

    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    private final FirResolvedTypeRef computeReturnTypeRef(FirAnonymousFunction firAnonymousFunction, FirResolvedTypeRef firResolvedTypeRef) {
        ConeKotlinType coneKotlinTypeComputeReturnType = ResolveUtilsKt.computeReturnType(firAnonymousFunction, getSession(), firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null, false, getComponents().getDataFlowAnalyzer().returnExpressionsOfAnonymousFunction(firAnonymousFunction));
        FirTypeRef returnTypeRef = firAnonymousFunction.getReturnTypeRef();
        KtSourceElement source = firAnonymousFunction.getSource();
        return CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef, coneKotlinTypeComputeReturnType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitFunctionReturnType.INSTANCE, null, 2, null) : null);
    }

    private final <R extends FirTypeRef> R copyWithNewNullableSource(R r, KtSourceElement ktSourceElement) {
        return ktSourceElement == null ? r : (R) UtilsKt.copyWithNewSource(r, ktSourceElement);
    }

    private final FirFile doTransformFile(final FirFile file, final ResolutionMode data) {
        return withFile(file, new Function0() { // from class: w15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.j(this.b, file, data);
            }
        });
    }

    public static FirRegularClass e(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirRegularClass firRegularClass, ResolutionMode resolutionMode) {
        FirDeclaration firDeclarationTransformDeclarationContent = firDeclarationsResolveTransformer.transformDeclarationContent(firRegularClass, resolutionMode);
        firDeclarationTransformDeclarationContent.getClass();
        return (FirRegularClass) firDeclarationTransformDeclarationContent;
    }

    public static FirAnonymousFunction f(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirAnonymousFunction firAnonymousFunction, FirTypeRef firTypeRef) {
        ResolutionMode resolutionModeWithExpectedType$default;
        firDeclarationsResolveTransformer.doTransformTypeParameters(firAnonymousFunction);
        boolean implicitTypeOnly = firDeclarationsResolveTransformer.getImplicitTypeOnly();
        if (implicitTypeOnly) {
            firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(false);
        }
        try {
            if (!firDeclarationsResolveTransformer.getBodyResolved(firAnonymousFunction)) {
                if (firTypeRef instanceof FirResolvedTypeRef) {
                    firAnonymousFunction.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) firDeclarationsResolveTransformer.getTransformer(), new ResolutionMode.UpdateImplicitTypeRef((FirResolvedTypeRef) firTypeRef));
                }
                FirSession session = firDeclarationsResolveTransformer.getSession();
                if (firTypeRef != null) {
                    try {
                        resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(firTypeRef, null, null, 6, null);
                        if (resolutionModeWithExpectedType$default == null) {
                            resolutionModeWithExpectedType$default = ResolutionMode.ContextDependent.INSTANCE;
                        }
                        FirFunction firFunctionTransformFunctionContent = firDeclarationsResolveTransformer.transformFunctionContent(firAnonymousFunction, resolutionModeWithExpectedType$default, true);
                        firFunctionTransformFunctionContent.getClass();
                        firAnonymousFunction = (FirAnonymousFunction) firFunctionTransformFunctionContent;
                    } catch (Throwable th) {
                        UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firAnonymousFunction, th);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    resolutionModeWithExpectedType$default = ResolutionMode.ContextDependent.INSTANCE;
                    FirFunction firFunctionTransformFunctionContent2 = firDeclarationsResolveTransformer.transformFunctionContent(firAnonymousFunction, resolutionModeWithExpectedType$default, true);
                    firFunctionTransformFunctionContent2.getClass();
                    firAnonymousFunction = (FirAnonymousFunction) firFunctionTransformFunctionContent2;
                }
            }
            if (implicitTypeOnly) {
                firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
            }
            return firAnonymousFunction;
        } catch (Throwable th2) {
            if (implicitTypeOnly) {
                firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
            }
            throw th2;
        }
    }

    private final Pair<TypeConstructorMarker, ConeKotlinType> findResultTypeForInnerVariableIfNeeded(FirFunctionCall provideDelegate, Candidate candidate) {
        final ConeTypeVariableType coneTypeVariableTypeUnwrapTopLevelVariableType = unwrapTopLevelVariableType(candidate.getSubstitutor().substituteOrSelf(ResolveUtilsKt.typeFromCallee(getTransformer().getComponents(), provideDelegate)));
        if (coneTypeVariableTypeUnwrapTopLevelVariableType == null) {
            return null;
        }
        ConeTypeVariableTypeConstructor typeConstructor = coneTypeVariableTypeUnwrapTopLevelVariableType.getTypeConstructor();
        final NewConstraintSystemImpl system = candidate.getSystem();
        final ConstraintStorage constraintStorageCurrentStorage = system.currentStorage();
        final MutableVariableWithConstraints mutableVariableWithConstraints = (MutableVariableWithConstraints) system.getNotFixedTypeVariables().get(typeConstructor);
        if (mutableVariableWithConstraints == null) {
            w04.a("Not found type variable ", typeConstructor);
            return null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Set outerTypeVariables = system.getOuterTypeVariables();
        if (outerTypeVariables == null) {
            outerTypeVariables = SetsKt.emptySet();
        }
        ConstraintSystemCompletionContext.withTypeVariablesThatAreCountedAsProperTypes$default(system, outerTypeVariables, false, new Function0() { // from class: f25
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.l(objectRef, system, constraintStorageCurrentStorage, coneTypeVariableTypeUnwrapTopLevelVariableType, this, mutableVariableWithConstraints);
            }
        }, 2, (Object) null);
        ConeKotlinType coneKotlinType = (ConeKotlinType) objectRef.element;
        if (coneKotlinType != null) {
            return TuplesKt.to(typeConstructor, coneKotlinType);
        }
        return null;
    }

    public static FirRegularClass g(Function0 function0) {
        return (FirRegularClass) function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getBodyResolved(FirFunction firFunction) {
        FirBlock body = firFunction.getBody();
        return body != null && FirTypeUtilsKt.getHasResolvedType(body);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirFunctionCall getResolvedProvideDelegateIfSuccessful(FirFunctionCall provideDelegateCall, FirExpression resolvedDelegateExpression) throws UninitializedPropertyAccessException {
        Map mapEmptyMap;
        provideDelegateCall.replaceExplicitReceiver(resolvedDelegateExpression);
        FirExpressionsResolveTransformer expressionsTransformer = getTransformer().getExpressionsTransformer();
        if (expressionsTransformer != null) {
            expressionsTransformer.transformFunctionCallInternal$org_jetbrains_kotlin_resolve(provideDelegateCall, ResolutionMode.ReceiverResolution.INSTANCE, FirExpressionsResolveTransformer.CallResolutionMode.PROVIDE_DELEGATE);
        }
        Candidate candidate = CandidateFactoryKt.candidate(provideDelegateCall);
        if (candidate == null || !candidate.isSuccessful()) {
            FirNamedReference calleeReference = provideDelegateCall.getCalleeReference();
            if (!(calleeReference instanceof FirResolvedNamedReference) || (calleeReference instanceof FirResolvedErrorReference)) {
                return null;
            }
            return provideDelegateCall;
        }
        Pair<TypeConstructorMarker, ConeKotlinType> pairFindResultTypeForInnerVariableIfNeeded = findResultTypeForInnerVariableIfNeeded(provideDelegateCall, candidate);
        ChainedSubstitutor.Companion companion = ChainedSubstitutor.INSTANCE;
        ConeSubstitutor substitutor = candidate.getSubstitutor();
        FirInferenceSession inferenceSession = getTransformer().getContext().getInferenceSession();
        inferenceSession.getClass();
        ConstraintStorage currentConstraintStorage = ((FirDelegatedPropertyInferenceSession) inferenceSession).getCurrentConstraintStorage();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        if (pairFindResultTypeForInnerVariableIfNeeded == null || (mapEmptyMap = MapsKt.mapOf(pairFindResultTypeForInnerVariableIfNeeded)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        provideDelegateCall.replaceConeTypeOrNull(companion.invoke(substitutor, (ConeSubstitutor) InferenceUtilsKt.buildCurrentSubstitutor(currentConstraintStorage, typeContext, mapEmptyMap)).substituteOrSelf(ResolveUtilsKt.typeFromCallee(getTransformer().getComponents(), provideDelegateCall)));
        return provideDelegateCall;
    }

    public static FirStatement h(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirCodeFragment firCodeFragment, ResolutionMode resolutionMode) {
        return firDeclarationsResolveTransformer.transformBlock(firCodeFragment.getBlock(), resolutionMode);
    }

    public static Unit i(FirReplSnippet firReplSnippet, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, ResolutionMode resolutionMode) {
        FirReplSnippetResolveExtension replSnippetResolveExtension;
        firReplSnippet.transformSnippetClass(firDeclarationsResolveTransformer, resolutionMode);
        if (!firDeclarationsResolveTransformer.getImplicitTypeOnly() && (replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(firDeclarationsResolveTransformer.getSession())) != null) {
            replSnippetResolveExtension.updateResolved(firReplSnippet);
        }
        return Unit.INSTANCE;
    }

    private final boolean isLocalVariableOrParameter(FirVariable firVariable) {
        FirVariableSymbol<FirVariable> symbol = firVariable.getSymbol();
        return (symbol instanceof FirValueParameterSymbol) || (symbol instanceof FirLocalPropertySymbol);
    }

    public static FirFile j(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirFile firFile, ResolutionMode resolutionMode) {
        FirDeclaration firDeclarationTransformDeclarationContent = firDeclarationsResolveTransformer.transformDeclarationContent(firFile, resolutionMode);
        firDeclarationTransformDeclarationContent.getClass();
        return (FirFile) firDeclarationTransformDeclarationContent;
    }

    public static FirDanglingModifierList k(FirDanglingModifierList firDanglingModifierList, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, ResolutionMode resolutionMode) {
        firDanglingModifierList.transformContextParameters(firDeclarationsResolveTransformer.getTransformer(), resolutionMode);
        return firDanglingModifierList.transformAnnotations((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), resolutionMode);
    }

    public static Unit l(Ref.ObjectRef objectRef, NewConstraintSystemImpl newConstraintSystemImpl, ConstraintStorage constraintStorage, ConeTypeVariableType coneTypeVariableType, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, MutableVariableWithConstraints mutableVariableWithConstraints) {
        ConeKotlinType coneKotlinTypeFindResultTypeOrNull = InferenceComponentsKt.getInferenceComponents(firDeclarationsResolveTransformer.getSession()).getResultTypeResolver().findResultTypeOrNull(newConstraintSystemImpl, mutableVariableWithConstraints, TypeVariableDirectionCalculator.ResolveDirection.UNKNOWN);
        ConeKotlinType coneKotlinType = coneKotlinTypeFindResultTypeOrNull instanceof ConeKotlinType ? coneKotlinTypeFindResultTypeOrNull : null;
        if (coneKotlinType == null) {
            return Unit.INSTANCE;
        }
        objectRef.element = coneKotlinType;
        if (constraintStorage.getHasContradiction()) {
            k2d.a("We only should try fixing variables on successful provideDelegate candidate");
            return null;
        }
        newConstraintSystemImpl.addEqualityConstraint(coneTypeVariableType, (KotlinTypeMarker) objectRef.element, ProvideDelegateFixationPosition.INSTANCE);
        if (!constraintStorage.getHasContradiction()) {
            return Unit.INSTANCE;
        }
        k2d.a("Currently, we see no cases when contradiction might happen after adding equality constraint like that.But if you see the message, please report your case to https://youtrack.jetbrains.com/newIssue?project=KT");
        return null;
    }

    public static FirScript n(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirScript firScript, Function0 function0) {
        firDeclarationsResolveTransformer.getComponents().getDataFlowAnalyzer().enterScript(firScript, firDeclarationsResolveTransformer.getTransformer().getBuildCfgForScripts());
        return (FirScript) function0.invoke();
    }

    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    private final List<FirValueParameter> obtainValueParametersFromExpectedParameterTypes(List<? extends ConeKotlinType> expectedTypeParameterTypes, FirAnonymousFunction lambda) {
        List<FirValueParameter> valueParameters = lambda.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        int i = 0;
        for (Object obj : valueParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            if (!(firValueParameter.getReturnTypeRef() instanceof FirResolvedTypeRef)) {
                FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
                ConeKotlinType coneKotlinType = expectedTypeParameterTypes.get(i);
                KtSourceElement source = firValueParameter.getSource();
                firValueParameter.replaceReturnTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef, coneKotlinType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null) : null));
            }
            arrayList.add(firValueParameter);
            i = i2;
        }
        return arrayList;
    }

    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    private final List<FirValueParameter> obtainValueParametersFromExpectedType(ConeKotlinType expectedType, FirAnonymousFunction lambda) {
        if (expectedType != null && FunctionalTypeUtilsKt.isNonReflectFunctionType(expectedType, getSession())) {
            ConeTypeProjection[] typeArguments = expectedType.getTypeArguments();
            ArrayList arrayList = new ArrayList();
            for (ConeTypeProjection coneTypeProjection : typeArguments) {
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type == null) {
                    type = getSession().getBuiltinTypes().getNullableAnyType().getConeType();
                }
                arrayList.add(type);
            }
            CollectionsKt.removeLastOrNull(arrayList);
            if (CompilerConeAttributesKt.isExtensionFunctionType(expectedType)) {
                CollectionsKt.removeFirstOrNull(arrayList);
            }
            return obtainValueParametersFromExpectedParameterTypes(arrayList, lambda);
        }
        return lambda.getValueParameters();
    }

    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    private final List<FirValueParameter> obtainValueParametersFromResolvedLambdaAtom(ConeResolvedLambdaAtom resolvedLambdaAtom, FirAnonymousFunction lambda) {
        List<ConeKotlinType> parameterTypes$org_jetbrains_kotlin_resolve;
        ConeKotlinType coneKotlinType = (ConeKotlinType) CollectionsKt.singleOrNull(resolvedLambdaAtom.getParameterTypes$org_jetbrains_kotlin_resolve());
        if (!lambda.getValueParameters().isEmpty() || coneKotlinType == null) {
            if (resolvedLambdaAtom.getCoerceFirstParameterToExtensionReceiver()) {
                ConeKotlinType receiverType = resolvedLambdaAtom.getReceiverType();
                if (receiverType == null) {
                    k2d.a("Coercion to an extension function type, but no receiver found");
                    return null;
                }
                parameterTypes$org_jetbrains_kotlin_resolve = CollectionsKt.plus(CollectionsKt.listOf(receiverType), resolvedLambdaAtom.getParameterTypes$org_jetbrains_kotlin_resolve());
            } else {
                parameterTypes$org_jetbrains_kotlin_resolve = resolvedLambdaAtom.getParameterTypes$org_jetbrains_kotlin_resolve();
            }
            return obtainValueParametersFromExpectedParameterTypes(parameterTypes$org_jetbrains_kotlin_resolve, lambda);
        }
        Name name = StandardNames.IMPLICIT_LAMBDA_PARAMETER_NAME;
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        KtSourceElement source = lambda.getSource();
        firValueParameterBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ItLambdaParameter.INSTANCE, null, 2, null) : null);
        firValueParameterBuilder.setContainingDeclarationSymbol(resolvedLambdaAtom.getAnonymousFunction().getSymbol());
        firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        KtSourceElement source2 = lambda.getSource();
        firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ImplicitReturnTypeOfLambdaValueParameter.INSTANCE, null, 2, null) : null, null, 2, null));
        firValueParameterBuilder.setName(name);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(false);
        return CollectionsKt.listOf(firValueParameterBuilder.mo288build());
    }

    private final void prepareSignatureForBodyResolve(FirCallableDeclaration callableMember) {
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        callableMember.transformReturnTypeRef(transformer, contextIndependent);
        callableMember.transformReceiverParameter(getTransformer(), contextIndependent);
        Iterator<T> it = callableMember.getContextParameters().iterator();
        while (it.hasNext()) {
            ((FirValueParameter) it.next()).transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        }
        if (callableMember instanceof FirFunction) {
            for (FirValueParameter firValueParameter : ((FirFunction) callableMember).getValueParameters()) {
                firValueParameter.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                TransformUtilsKt.transformVarargTypeToArrayType(firValueParameter, getTransformer().getSession());
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void replacePropertyReferenceTypeInDelegateAccessors(FirFunctionCall firFunctionCall, FirProperty firProperty) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneType;
        FirTypeRef typeRef;
        Set<FirExpression> setKeySet;
        List list;
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
        FirExpression firExpression = (mapping == null || (setKeySet = mapping.keySet()) == null || (list = CollectionsKt.toList(setKeySet)) == null) ? null : (FirExpression) CollectionsKt.getOrNull(list, 1);
        FirCallableReferenceAccess firCallableReferenceAccess = firExpression instanceof FirCallableReferenceAccess ? (FirCallableReferenceAccess) firExpression : null;
        if (firCallableReferenceAccess == null) {
            return;
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firCallableReferenceAccess);
        if (firProperty.getReturnTypeRef() instanceof FirResolvedTypeRef) {
            resolvedType.getClass();
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) resolvedType;
            ConeKotlinType[] typeArguments = coneClassLikeType.getTypeArguments();
            FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
            ConeKotlinType coneType2 = (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) ? null : FirTypeUtilsKt.getConeType(typeRef);
            FirRegularClass containingRegularClass = getTransformer().getContext().getContainingRegularClass();
            ConeClassLikeType coneClassLikeTypeConstructStarProjectedType$default = containingRegularClass != null ? TypeConstructionUtilsKt.constructStarProjectedType$default(containingRegularClass.getSymbol(), containingRegularClass.getTypeParameters().size(), false, 2, null) : null;
            ConeClassLikeLookupTag lookupTag = coneClassLikeType.getLookupTag();
            ArrayList arrayList = new ArrayList(typeArguments.length);
            int length = typeArguments.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                ConeKotlinType coneKotlinType = typeArguments[i];
                int i3 = i2 + 1;
                if (i2 == ArraysKt.getLastIndex(typeArguments)) {
                    coneType = FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef());
                } else {
                    coneType = (i2 != 0 || coneClassLikeTypeConstructStarProjectedType$default == null) ? coneType2 : coneClassLikeTypeConstructStarProjectedType$default;
                }
                if (coneType != null) {
                    coneKotlinType = coneType;
                }
                arrayList.add(coneKotlinType);
                i++;
                i2 = i3;
            }
            ConeClassLikeType coneClassLikeTypeConstructClassType$default = TypeConstructionUtilsKt.constructClassType$default(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]), false, null, 6, null);
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker != null) {
                KtSourceElement source = firCallableReferenceAccess.getSource();
                if (source == null) {
                    source = firFunctionCall.getSource();
                }
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneClassLikeTypeConstructClassType$default, source, getTransformer().getComponents().getFile().getSource());
            }
            firCallableReferenceAccess.replaceConeTypeOrNull(coneClassLikeTypeConstructClassType$default);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void resolveAccessors(FirProperty firProperty, boolean z, boolean z2) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirPropertyAccessor setter;
        FirPropertyAccessor setter2;
        FirPropertyAccessor getter;
        FirPropertyAccessor getter2;
        resolveGetter(firProperty, z2);
        if (firProperty.getReturnTypeRef() instanceof FirImplicitTypeRef) {
            storeVariableReturnType(firProperty);
            FirPropertyAccessor getter3 = firProperty.getGetter();
            if (getter3 != null) {
                transformTypeWithPropertyType(getter3, firProperty.getReturnTypeRef(), true);
            }
        }
        resolveSetter(firProperty, z, z2);
        if (!((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue() || firProperty.getIsLocal() || firProperty.getStatus().isInline()) {
            return;
        }
        FirPropertyAccessor getter4 = firProperty.getGetter();
        if ((getter4 != null ? getter4.getBody() : null) != null && (getter = firProperty.getGetter()) != null && !getter.getStatus().isInline() && (getter2 = firProperty.getGetter()) != null) {
            getter2.replaceBody(FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock());
        }
        FirPropertyAccessor setter3 = firProperty.getSetter();
        if ((setter3 != null ? setter3.getBody() : null) == null || (setter = firProperty.getSetter()) == null || setter.getStatus().isInline() || (setter2 = firProperty.getSetter()) == null) {
            return;
        }
        setter2.replaceBody(FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void resolveAccessors$default(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirProperty firProperty, boolean z, boolean z2, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: resolveAccessors");
            return;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        firDeclarationsResolveTransformer.resolveAccessors(firProperty, z, z2);
    }

    private final void resolveGetter(FirProperty firProperty, boolean z) {
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter != null) {
            transformAccessor(getter, firProperty, z);
        }
    }

    private final void resolveSetter(FirProperty firProperty, boolean z, boolean z2) {
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter != null) {
            transformTypeWithPropertyType$default(this, setter, firProperty.getReturnTypeRef(), false, 2, null);
            if (z) {
                transformAccessor(setter, firProperty, z2);
            }
        }
    }

    private final FirDeclarationStatus resolveStatus(FirDeclaration firDeclaration, FirClass firClass, FirProperty firProperty) {
        return this.statusResolver.resolveStatus(firDeclaration, firClass instanceof FirRegularClass ? (FirRegularClass) firClass : null, firProperty, getTransformer().getContext().getContainerIfAny() != null && firClass == null);
    }

    public static /* synthetic */ FirDeclarationStatus resolveStatus$default(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirDeclaration firDeclaration, FirClass firClass, FirProperty firProperty, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: resolveStatus");
            return null;
        }
        if ((i & 1) != 0) {
            firClass = null;
        }
        if ((i & 2) != 0) {
            firProperty = null;
        }
        return firDeclarationsResolveTransformer.resolveStatus(firDeclaration, firClass, firProperty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:29:0x009f  */
    public final void storeVariableReturnType(FirVariable variable) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirResolvedTypeRef returnTypeRef;
        FirPropertyAccessor getter;
        FirDeclarationsResolveTransformer firDeclarationsResolveTransformer;
        FirResolvedTypeRef firResolvedTypeRefBuild;
        FirPropertyAccessor getter2;
        FirPropertyAccessor getter3;
        FirExpression initializer = variable.getInitializer();
        if (variable.getReturnTypeRef() instanceof FirImplicitTypeRef) {
            if (initializer != null) {
                FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(FirExpressionUtilKt.unwrapReplExpressionRef(initializer));
                ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpressionUnwrapSmartcastExpression);
                KtSourceElement source = firExpressionUnwrapSmartcastExpression.getSource();
                returnTypeRef = UtilsKt.toFirResolvedTypeRef$default(resolvedType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null, null, 2, null);
            } else {
                FirPropertyAccessor getter4 = variable.getGetter();
                returnTypeRef = (!((getter4 != null ? getter4.getBody() : null) instanceof FirSingleExpressionBlock) || (getter = variable.getGetter()) == null) ? null : getter.getReturnTypeRef();
            }
            if (returnTypeRef != null) {
                firDeclarationsResolveTransformer = this;
                firResolvedTypeRefBuild = DeclarationApproximationUtilsKt.approximateDeclarationType$default(firDeclarationsResolveTransformer, toExpectedTypeRef(returnTypeRef, variable.getSource()), visibilityForApproximation(variable), isLocalVariableOrParameter(variable), false, false, Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(variable), Boolean.TRUE), 24, null);
                if (firResolvedTypeRefBuild == null) {
                }
                variable.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) firDeclarationsResolveTransformer.getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuild));
                getter2 = variable.getGetter();
                if (((getter2 != null ? getter2.getReturnTypeRef() : null) instanceof FirImplicitTypeRef) || (getter3 = variable.getGetter()) == null) {
                }
                getter3.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) firDeclarationsResolveTransformer.getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuild));
                return;
            }
            firDeclarationsResolveTransformer = this;
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeLocalVariableNoTypeOrInitializer(variable));
            firErrorTypeRefBuilder.setSource(variable.getSource());
            firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
            variable.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) firDeclarationsResolveTransformer.getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuild));
            getter2 = variable.getGetter();
            if ((getter2 != null ? getter2.getReturnTypeRef() : null) instanceof FirImplicitTypeRef) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirResolvedTypeRef toExpectedTypeRef(FirTypeRef firTypeRef, KtSourceElement ktSourceElement) {
        KtSourceElement source = firTypeRef.getSource();
        if (source != null) {
            ktSourceElement = source;
        }
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null;
        if (firTypeRef instanceof FirImplicitTypeRef) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("No result type for initializer", DiagnosticKind.InferenceError));
            firErrorTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
            firErrorTypeRefBuilder.getAnnotations().addAll(((FirImplicitTypeRef) firTypeRef).getAnnotations());
            return firErrorTypeRefBuilder.build();
        }
        if (!(firTypeRef instanceof FirErrorTypeRef)) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(FirTypeUtilsKt.getConeType(firTypeRef));
            firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
            firResolvedTypeRefBuilder.getAnnotations().addAll(firTypeRef.getAnnotations());
            return firResolvedTypeRefBuilder.build();
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
        FirErrorTypeRef firErrorTypeRef = (FirErrorTypeRef) firTypeRef;
        firErrorTypeRefBuilder2.setDiagnostic(firErrorTypeRef.getDiagnostic());
        firErrorTypeRefBuilder2.setSource(ktSourceElementFakeElement$default);
        firErrorTypeRefBuilder2.getAnnotations().addAll(firErrorTypeRef.getAnnotations());
        return firErrorTypeRefBuilder2.build();
    }

    private final void transformAccessor(final FirPropertyAccessor accessor, final FirProperty owner, final boolean shouldResolveEverything) {
        FirPropertyAccessor firPropertyAccessor;
        FirSession session = getSession();
        try {
            firPropertyAccessor = accessor;
            try {
                BodyResolveContext.withPropertyAccessor$default(getTransformer().getContext(), owner, firPropertyAccessor, getTransformer().getComponents(), false, new Function0() { // from class: a25
                    public final Object invoke() {
                        return FirDeclarationsResolveTransformer.transformAccessor$lambda$0$0(owner, accessor, this, shouldResolveEverything);
                    }
                }, 8, null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firPropertyAccessor, th);
                wq6.a();
            }
        } catch (Throwable th2) {
            th = th2;
            firPropertyAccessor = accessor;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static final Unit transformAccessor$lambda$0$0(FirProperty firProperty, FirPropertyAccessor firPropertyAccessor, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, boolean z) throws UninitializedPropertyAccessException {
        FirTypeRef returnTypeRef = firProperty.getReturnTypeRef();
        if ((firPropertyAccessor.getReturnTypeRef() instanceof FirImplicitTypeRef) && !(returnTypeRef instanceof FirImplicitTypeRef)) {
            firPropertyAccessor.replaceReturnTypeRef(returnTypeRef);
        }
        if ((firPropertyAccessor instanceof FirDefaultPropertyAccessor) || firPropertyAccessor.getBody() == null) {
            firDeclarationsResolveTransformer.transformFunctionContent(firPropertyAccessor, ResolutionMode.ContextIndependent.INSTANCE, z);
        } else {
            firDeclarationsResolveTransformer.transformFunctionWithGivenSignature(firPropertyAccessor, z);
        }
        return Unit.INSTANCE;
    }

    private final FirAnonymousFunction transformAnonymousFunctionBody(final FirAnonymousFunction anonymousFunction, final FirTypeRef expectedReturnTypeRef) {
        FirTypeRef typeRef = anonymousFunction.getTypeRef();
        FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) getTransformer().getContext().withAnonymousFunction(anonymousFunction, getTransformer().getComponents(), new Function0() { // from class: x15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.f(this.b, anonymousFunction, expectedReturnTypeRef);
            }
        });
        firAnonymousFunction.replaceTypeRef(typeRef);
        return firAnonymousFunction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:31:0x0067  */
    public final FirBackingField transformBackingField(FirBackingField backingField, ResolutionMode data, boolean shouldResolveEverything) {
        ResolutionMode resolutionModeWithExpectedType$default;
        FirExpression firExpressionUnwrapSmartcastExpression;
        ConeKotlinType resolvedType;
        FirResolvedTypeRef firResolvedTypeRef$default;
        FirSession session = getSession();
        try {
            if (backingField.getReturnTypeRef() instanceof FirResolvedTypeRef) {
                resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(backingField.getReturnTypeRef(), null, null, 6, null);
            } else if (data instanceof ResolutionMode.WithExpectedType) {
                resolutionModeWithExpectedType$default = data;
            } else {
                resolutionModeWithExpectedType$default = data instanceof ResolutionMode.ContextIndependent ? ResolutionMode.ContextIndependent.INSTANCE : ResolutionMode.ContextDependent.INSTANCE;
            }
            backingField.transformInitializer((FirTransformer<? super ResolutionMode>) getTransformer(), resolutionModeWithExpectedType$default);
            if (shouldResolveEverything) {
                backingField.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            }
            if (!(backingField.getReturnTypeRef() instanceof FirErrorTypeRef) && !(backingField.getReturnTypeRef() instanceof FirResolvedTypeRef)) {
                if (backingField instanceof FirDefaultPropertyBackingField) {
                    ResolutionMode.WithExpectedType withExpectedType = data instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) data : null;
                    if (withExpectedType != null) {
                        firResolvedTypeRef$default = withExpectedType.getExpectedTypeRef();
                    } else {
                        firResolvedTypeRef$default = null;
                    }
                } else {
                    FirExpression initializer = backingField.getInitializer();
                    if (initializer == null || (firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(initializer)) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(firExpressionUnwrapSmartcastExpression)) == null) {
                        firResolvedTypeRef$default = null;
                    } else {
                        firResolvedTypeRef$default = UtilsKt.toFirResolvedTypeRef$default(resolvedType, backingField.getSource(), null, 2, null);
                    }
                }
                if (firResolvedTypeRef$default != null) {
                    return backingField.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(DeclarationApproximationUtilsKt.approximateDeclarationType$default(this, toExpectedTypeRef(firResolvedTypeRef$default, backingField.getSource()), visibilityForApproximation(backingField), false, false, false, false, 56, null)));
                }
                FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Cannot infer variable type without an initializer", DiagnosticKind.InferenceError));
                firErrorTypeRefBuilder.setSource(backingField.getSource());
                Unit unit = Unit.INSTANCE;
                return backingField.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) transformer, new ResolutionMode.UpdateImplicitTypeRef(firErrorTypeRefBuilder.build()));
            }
            return backingField;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(backingField, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirConstructor transformConstructorContent$lambda$0$1(FirConstructor firConstructor, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, ResolutionMode resolutionMode) {
        return firConstructor.transformBody((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), resolutionMode);
    }

    private final FirExpression transformDelegateExpression(FirWrappedDelegateExpression delegate) {
        return (FirExpression) FirTransformerUtilKt.transformSingle(FirTransformerUtilKt.transformSingle(delegate.getExpression(), getTransformer(), ResolutionMode.Delegate.INSTANCE), getTransformer().getComponents().getIntegerLiteralAndOperatorApproximationTransformer(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:43:0x0095 A[PHI: r5
      0x0095: PHI (r5v2 org.jetbrains.kotlin.KtSourceElement) = 
      (r5v1 org.jetbrains.kotlin.KtSourceElement)
      (r5v12 org.jetbrains.kotlin.KtSourceElement)
      (r5v14 org.jetbrains.kotlin.KtSourceElement)
     binds: [B:33:0x0074, B:39:0x0087, B:42:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:71:0x0106  */
    public final <F extends FirFunction> F transformFunctionWithGivenSignature(F function, boolean shouldResolveEverything) {
        KtSourceElement ktSourceElement;
        FirResolvedTypeRef firResolvedTypeRefBuild;
        FirResolvedTypeRef firResolvedTypeRef$default;
        Visibility visibilityVisibilityForApproximation;
        KtSourceElement source;
        List<FirStatement> statements;
        F f = (F) transformFunctionContent(function, ResolutionMode.ContextIndependent.INSTANCE, shouldResolveEverything);
        f.getClass();
        FirBlock body = f.getBody();
        Object returnTypeRef = f.getReturnTypeRef();
        ResolvedImplicitTypeRef resolvedImplicitTypeRef = returnTypeRef instanceof ResolvedImplicitTypeRef ? (ResolvedImplicitTypeRef) returnTypeRef : null;
        FirResolvedTypeRef typeRef = resolvedImplicitTypeRef != null ? resolvedImplicitTypeRef.getTypeRef() : null;
        if (typeRef != null) {
            f.transformReturnTypeRef(getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(typeRef));
        } else if (f.getReturnTypeRef() instanceof FirImplicitTypeRef) {
            FirNamedFunction firNamedFunction = function instanceof FirNamedFunction ? (FirNamedFunction) function : null;
            FirStatement firStatement = (body == null || (statements = body.getStatements()) == null) ? null : (FirStatement) CollectionsKt.singleOrNull(statements);
            FirReturnExpression firReturnExpression = firStatement instanceof FirReturnExpression ? (FirReturnExpression) firStatement : null;
            FirExpression result = firReturnExpression != null ? firReturnExpression.getResult() : null;
            ConeKotlinType resolvedType = result != null ? FirTypeUtilsKt.getResolvedType(result) : null;
            KtSourceElement source2 = f.getReturnTypeRef().getSource();
            if (source2 != null) {
                ktSourceElement = source2;
            } else {
                source2 = (result == null || (source = result.getSource()) == null) ? null : KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null);
                if (source2 != null) {
                    ktSourceElement = source2;
                } else {
                    KtSourceElement source3 = function.getSource();
                    if (source3 != null) {
                        source2 = KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null);
                        ktSourceElement = source2;
                    } else {
                        ktSourceElement = null;
                    }
                }
            }
            if (resolvedType == null || (firResolvedTypeRef$default = UtilsKt.toFirResolvedTypeRef$default(resolvedType, ktSourceElement, null, 2, null)) == null) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setSource(ktSourceElement);
                firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("empty body", DiagnosticKind.Other));
                firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
            } else {
                boolean z = false;
                if (Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(function), Boolean.TRUE)) {
                    visibilityVisibilityForApproximation = firNamedFunction != null ? visibilityForApproximation(firNamedFunction) : null;
                    if (firNamedFunction != null && firNamedFunction.getStatus().isInline()) {
                        z = true;
                    }
                    firResolvedTypeRefBuild = DeclarationApproximationUtilsKt.approximateDeclarationType$default(this, firResolvedTypeRef$default, visibilityVisibilityForApproximation, false, z, false, false, 48, null);
                } else {
                    visibilityVisibilityForApproximation = firNamedFunction != null ? visibilityForApproximation(firNamedFunction) : null;
                    boolean z2 = firNamedFunction != null && Intrinsics.areEqual(firNamedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE);
                    if (firNamedFunction != null && firNamedFunction.getStatus().isInline()) {
                        z = true;
                    }
                    firResolvedTypeRefBuild = DeclarationApproximationUtilsKt.approximateDeclarationType$default(this, firResolvedTypeRef$default, visibilityVisibilityForApproximation, z2, z, false, false, 48, null);
                }
                if (firResolvedTypeRefBuild == null) {
                    FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder2.setSource(ktSourceElement);
                    firErrorTypeRefBuilder2.setDiagnostic(new ConeSimpleDiagnostic("empty body", DiagnosticKind.Other));
                    firResolvedTypeRefBuild = firErrorTypeRefBuilder2.build();
                }
            }
            f.transformReturnTypeRef(getTransformer(), new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuild));
        }
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue() && !(function instanceof FirPropertyAccessor) && !function.getStatus().isInline() && !function.getIsLocal() && f.getBody() != null && !(ToSymbolUtilsKt.toClassSymbol(FirTypeUtilsKt.getConeType(f.getReturnTypeRef()), getSession()) instanceof FirAnonymousObjectSymbol)) {
            f.replaceBody(FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock());
        }
        return f;
    }

    private final FirProperty transformLocalVariable(FirProperty variable) {
        FirSession session = getSession();
        try {
            isLocalVariableOrParameter(variable);
            FirExpression delegate = variable.getDelegate();
            boolean z = !(variable.getReturnTypeRef() instanceof FirImplicitTypeRef);
            BodyResolveContext context = getTransformer().getContext();
            if (!(getTransformer().getContext().getContainerIfAny() instanceof FirReplSnippet)) {
                FirDeclarationOrigin origin = variable.getOrigin();
                FirDeclarationOrigin.ScriptCustomization.Parameter parameter = FirDeclarationOrigin.ScriptCustomization.Parameter.INSTANCE;
                if (!Intrinsics.areEqual(origin, parameter) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                    getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(variable);
                }
                if (delegate != null) {
                    transformPropertyAccessorsWithDelegate(variable, delegate, true);
                    if (variable.getDelegateFieldSymbol() != null) {
                        replacePropertyReferenceTypeInDelegateAccessors(variable);
                    }
                    variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                } else {
                    ResolutionMode resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null);
                    if (variable.getInitializer() != null && variable.getBodyResolveState().compareTo(FirPropertyBodyResolveState.INITIALIZER_RESOLVED) < 0) {
                        variable.transformInitializer((FirTransformer<? super ResolutionMode>) getTransformer(), resolutionModeWithExpectedType$default);
                        storeVariableReturnType(variable);
                    }
                    variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                    resolveAccessors$default(this, variable, true, false, 2, null);
                }
                FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
                ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
                variable.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent).transformOtherChildren((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent);
                getTransformer().getContext().storeVariable(variable, getSession());
                if (!Intrinsics.areEqual(variable.getOrigin(), parameter) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                    getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(variable, z);
                }
                return variable;
            }
            FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(variable) ? FirTowerDataMode.COMPANION_BLOCK : null;
            FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
            if (towerDataMode == null) {
                try {
                    towerDataMode = context.getTowerDataMode();
                } catch (Throwable th) {
                    context.setTowerDataMode(towerDataMode2);
                    throw th;
                }
            }
            context.setTowerDataMode(towerDataMode);
            if (variable.getTypeParameters().isEmpty()) {
                context.getContainers().add(variable);
                try {
                    FirDeclarationOrigin origin2 = variable.getOrigin();
                    FirDeclarationOrigin.ScriptCustomization.Parameter parameter2 = FirDeclarationOrigin.ScriptCustomization.Parameter.INSTANCE;
                    if (!Intrinsics.areEqual(origin2, parameter2) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                        getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(variable);
                    }
                    if (delegate != null) {
                        transformPropertyAccessorsWithDelegate(variable, delegate, true);
                        if (variable.getDelegateFieldSymbol() != null) {
                            replacePropertyReferenceTypeInDelegateAccessors(variable);
                        }
                        variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                    } else {
                        ResolutionMode resolutionModeWithExpectedType$default2 = ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null);
                        if (variable.getInitializer() != null && variable.getBodyResolveState().compareTo(FirPropertyBodyResolveState.INITIALIZER_RESOLVED) < 0) {
                            variable.transformInitializer((FirTransformer<? super ResolutionMode>) getTransformer(), resolutionModeWithExpectedType$default2);
                            storeVariableReturnType(variable);
                        }
                        variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                        resolveAccessors$default(this, variable, true, false, 2, null);
                    }
                    FirAbstractBodyResolveTransformerDispatcher transformer2 = getTransformer();
                    ResolutionMode.ContextIndependent contextIndependent2 = ResolutionMode.ContextIndependent.INSTANCE;
                    variable.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer2, contextIndependent2).transformOtherChildren((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent2);
                    getTransformer().getContext().storeVariable(variable, getSession());
                    if (!Intrinsics.areEqual(variable.getOrigin(), parameter2) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                        getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(variable, z);
                    }
                    Unit unit = Unit.INSTANCE;
                    context.getContainers().removeLast();
                } catch (Throwable th2) {
                    context.getContainers().removeLast();
                    throw th2;
                }
            } else {
                FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(variable);
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                    context.getContainers().add(variable);
                    try {
                        FirDeclarationOrigin origin3 = variable.getOrigin();
                        FirDeclarationOrigin.ScriptCustomization.Parameter parameter3 = FirDeclarationOrigin.ScriptCustomization.Parameter.INSTANCE;
                        if (!Intrinsics.areEqual(origin3, parameter3) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                            getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(variable);
                        }
                        if (delegate != null) {
                            transformPropertyAccessorsWithDelegate(variable, delegate, true);
                            if (variable.getDelegateFieldSymbol() != null) {
                                replacePropertyReferenceTypeInDelegateAccessors(variable);
                            }
                            variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                        } else {
                            ResolutionMode resolutionModeWithExpectedType$default3 = ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null);
                            if (variable.getInitializer() != null && variable.getBodyResolveState().compareTo(FirPropertyBodyResolveState.INITIALIZER_RESOLVED) < 0) {
                                variable.transformInitializer((FirTransformer<? super ResolutionMode>) getTransformer(), resolutionModeWithExpectedType$default3);
                                storeVariableReturnType(variable);
                            }
                            variable.transformBackingField((FirTransformer<? super ResolutionMode>) getTransformer(), ResolutionModeKt.withExpectedType$default(variable.getReturnTypeRef(), null, null, 6, null));
                            resolveAccessors$default(this, variable, true, false, 2, null);
                        }
                        FirAbstractBodyResolveTransformerDispatcher transformer3 = getTransformer();
                        ResolutionMode.ContextIndependent contextIndependent3 = ResolutionMode.ContextIndependent.INSTANCE;
                        variable.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer3, contextIndependent3).transformOtherChildren((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent3);
                        getTransformer().getContext().storeVariable(variable, getSession());
                        if (!Intrinsics.areEqual(variable.getOrigin(), parameter3) && !Intrinsics.areEqual(variable.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                            getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(variable, z);
                        }
                        Unit unit2 = Unit.INSTANCE;
                        context.getContainers().removeLast();
                        context.replaceTowerDataContext(towerDataContext);
                    } catch (Throwable th3) {
                        context.getContainers().removeLast();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th4;
                }
            }
            context.setTowerDataMode(towerDataMode2);
            return variable;
        } catch (Throwable th5) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(variable, th5);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirNamedFunction transformNamedFunction$lambda$0$0$0(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirNamedFunction firNamedFunction, boolean z) {
        boolean implicitTypeOnly = firDeclarationsResolveTransformer.getImplicitTypeOnly();
        if (implicitTypeOnly) {
            firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(false);
        }
        try {
            return (FirNamedFunction) firDeclarationsResolveTransformer.transformFunctionWithGivenSignature(firNamedFunction, z);
        } finally {
            if (implicitTypeOnly) {
                firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void transformPropertyAccessorsWithDelegate(final FirProperty property, FirExpression delegateContainer, final boolean shouldResolveEverything) {
        FirExpression firExpressionTransformDelegateExpression;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        FirTypeRef returnTypeRef;
        getComponents().getDataFlowAnalyzer().enterDelegateExpression();
        if (delegateContainer instanceof FirReplExpressionReference) {
            firExpressionTransformDelegateExpression = null;
        } else {
            if (!(delegateContainer instanceof FirWrappedDelegateExpression)) {
                k2d.a("delegate must be wrapped");
                return;
            }
            if (isLocalVariableOrParameter(property)) {
                firExpressionTransformDelegateExpression = transformDelegateExpression((FirWrappedDelegateExpression) delegateContainer);
            } else {
                boolean z = Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(property), Boolean.TRUE) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(getSession())) != null && scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers();
                BodyResolveContext context = getTransformer().getContext();
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    FirLocalScope primaryConstructorPureParametersScope = context.getPrimaryConstructorPureParametersScope();
                    if (primaryConstructorPureParametersScope != null) {
                        context.addLocalScope(primaryConstructorPureParametersScope);
                    }
                    firExpressionTransformDelegateExpression = transformDelegateExpression((FirWrappedDelegateExpression) delegateContainer);
                    if (!z) {
                        context.replaceTowerDataContext(towerDataContext);
                    }
                } catch (Throwable th) {
                    if (z) {
                        throw th;
                    }
                    context.replaceTowerDataContext(towerDataContext);
                    throw th;
                }
            }
        }
        BodyResolveContext context2 = getTransformer().getContext();
        FirDelegatedPropertyInferenceSession firDelegatedPropertyInferenceSession = new FirDelegatedPropertyInferenceSession(getTransformer().getResolutionContext(), getComponents().getCallCompleter(), firExpressionTransformDelegateExpression);
        FirInferenceSession inferenceSession = context2.getInferenceSession();
        context2.setInferenceSession(firDelegatedPropertyInferenceSession);
        if (firExpressionTransformDelegateExpression != null) {
            try {
                if (!(delegateContainer instanceof FirWrappedDelegateExpression)) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                FirFunctionCall resolvedProvideDelegateIfSuccessful = getResolvedProvideDelegateIfSuccessful(((FirWrappedDelegateExpression) delegateContainer).getProvideDelegateCall(), firExpressionTransformDelegateExpression);
                if (resolvedProvideDelegateIfSuccessful != null) {
                    firExpressionTransformDelegateExpression = resolvedProvideDelegateIfSuccessful;
                }
                property.replaceDelegate(firExpressionTransformDelegateExpression);
            } catch (Throwable th2) {
                context2.setInferenceSession(inferenceSession);
                throw th2;
            }
        }
        final boolean z2 = property.getReturnTypeRef() instanceof FirImplicitTypeRef;
        if (z2) {
            resolveGetter(property, shouldResolveEverything);
            FirPropertyAccessor getter = property.getGetter();
            getter.getClass();
            returnTypeRef = getter.getReturnTypeRef();
        } else {
            resolveAccessors(property, true, shouldResolveEverything);
            returnTypeRef = property.getReturnTypeRef();
        }
        returnTypeRef.getClass();
        final FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) returnTypeRef;
        if (firDelegatedPropertyInferenceSession.getParentSessionIsNonTrivial() && z2) {
            property.replaceReturnTypeRef(firResolvedTypeRef);
        }
        firDelegatedPropertyInferenceSession.completeSessionOrPostponeIfNonRoot(new Function1() { // from class: b25
            public final Object invoke(Object obj) {
                return FirDeclarationsResolveTransformer.transformPropertyAccessorsWithDelegate$lambda$1$0(firResolvedTypeRef, property, this, z2, shouldResolveEverything, (ConeSubstitutor) obj);
            }
        });
        getComponents().getDataFlowAnalyzer().exitDelegateExpression(delegateContainer);
        Unit unit = Unit.INSTANCE;
        context2.setInferenceSession(inferenceSession);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transformPropertyAccessorsWithDelegate$lambda$1$0(FirResolvedTypeRef firResolvedTypeRef, FirProperty firProperty, FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, boolean z, boolean z2, ConeSubstitutor coneSubstitutor) {
        FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default;
        coneSubstitutor.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutor.substituteOrNull(firResolvedTypeRef.getConeType());
        FirResolvedTypeRef firResolvedTypeRef2 = (coneKotlinTypeSubstituteOrNull == null || (firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRef, coneKotlinTypeSubstituteOrNull, null, 2, null)) == null) ? firResolvedTypeRef : firResolvedTypeRefWithReplacedConeType$default;
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter != null) {
            firDeclarationsResolveTransformer.transformTypeWithPropertyType(getter, firResolvedTypeRef2, true);
        }
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter != null) {
            firDeclarationsResolveTransformer.transformTypeWithPropertyType(setter, firResolvedTypeRef2, true);
        }
        firProperty.replaceReturnTypeRef(DeclarationApproximationUtilsKt.approximateDeclarationType$default(firDeclarationsResolveTransformer, firResolvedTypeRef2, firDeclarationsResolveTransformer.visibilityForApproximation(firProperty), firDeclarationsResolveTransformer.isLocalVariableOrParameter(firProperty), false, false, false, 56, null));
        if (z) {
            firDeclarationsResolveTransformer.resolveSetter(firProperty, true, z2);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirScript transformScript$lambda$0$0(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirScript firScript, ResolutionMode resolutionMode) {
        FirDeclaration firDeclarationTransformDeclarationContent = firDeclarationsResolveTransformer.transformDeclarationContent(firScript, resolutionMode);
        firDeclarationTransformDeclarationContent.getClass();
        return (FirScript) firDeclarationTransformDeclarationContent;
    }

    private final FirStatement transformTopLevelAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, ResolutionMode.WithExpectedType expectedTypeData) {
        if (LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)) {
            return getTransformer().getComponents().getSyntheticCallGenerator().resolveAnonymousFunctionExpressionWithSyntheticOuterCall(anonymousFunctionExpression, expectedTypeData, getTransformer().getResolutionContext());
        }
        anonymousFunctionExpression.replaceAnonymousFunction(transformTopLevelAnonymousFunctionInObsoleteWay(anonymousFunctionExpression, expectedTypeData != null ? expectedTypeData.getExpectedType() : null));
        return anonymousFunctionExpression;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0044  */
    /* JADX WARN: Code duplicated, block: B:31:0x0077  */
    @OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.ResolveTopLevelLambdasAsSyntheticCallArgument)
    private final FirAnonymousFunction transformTopLevelAnonymousFunctionInObsoleteWay(FirAnonymousFunctionExpression anonymousFunctionExpression, ConeKotlinType expectedType) {
        ConeKotlinType coneKotlinType;
        ConeResolvedLambdaAtom coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType;
        ConeKotlinType receiverType;
        FirReceiverParameter receiverParameter;
        FirResolvedTypeRef firResolvedTypeRefResolvedTypeFromPrototype;
        ConeKotlinType returnType;
        List<ConeKotlinType> contextParameterTypes$org_jetbrains_kotlin_resolve;
        getComponents().getDataFlowAnalyzer().enterAnonymousFunctionExpression(anonymousFunctionExpression);
        FirAnonymousFunction anonymousFunction = anonymousFunctionExpression.getAnonymousFunction();
        FunctionTypeKind.SuspendFunction expectedFunctionTypeKind = null;
        if (expectedType != null) {
            coneKotlinType = expectedType;
            coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType = org.jetbrains.kotlin.fir.resolve.inference.InferenceUtilsKt.extractLambdaInfoFromFunctionType(coneKotlinType, anonymousFunctionExpression, anonymousFunction, null, getTransformer().getComponents(), true, null);
        } else {
            coneKotlinType = expectedType;
            coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType = null;
        }
        List<FirValueParameter> listObtainValueParametersFromResolvedLambdaAtom = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType != null ? obtainValueParametersFromResolvedLambdaAtom(coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType, anonymousFunction) : obtainValueParametersFromExpectedType(coneKotlinType, anonymousFunction);
        FirReceiverParameter receiverParameter2 = anonymousFunction.getReceiverParameter();
        if (receiverParameter2 != null) {
            if (receiverParameter2.getTypeRef() instanceof FirImplicitTypeRef) {
                receiverParameter2 = null;
            }
            if (receiverParameter2 == null) {
                if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType != null) {
                    receiverParameter2 = null;
                } else {
                    receiverParameter2 = null;
                }
            }
        } else if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType != null || (receiverType = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getReceiverType()) == null) {
            receiverParameter2 = null;
        } else {
            if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getCoerceFirstParameterToExtensionReceiver()) {
                receiverType = null;
            }
            if (receiverType == null || (receiverParameter = anonymousFunction.getReceiverParameter()) == null) {
                receiverParameter2 = null;
            } else {
                FirTypeRef typeRef = receiverParameter.getTypeRef();
                KtSourceElement source = anonymousFunction.getSource();
                receiverParameter.replaceTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(typeRef, receiverType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.LambdaContextParameter.INSTANCE, null, 2, null) : null));
                receiverParameter2 = receiverParameter;
            }
        }
        anonymousFunction.replaceReceiverParameter(receiverParameter2);
        List<FirValueParameter> contextParameters = anonymousFunction.getContextParameters();
        if (contextParameters.isEmpty()) {
            contextParameters = null;
        }
        if (contextParameters == null) {
            if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType == null || (contextParameterTypes$org_jetbrains_kotlin_resolve = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getContextParameterTypes$org_jetbrains_kotlin_resolve()) == null) {
                contextParameters = null;
            } else {
                List<ConeKotlinType> list = contextParameterTypes$org_jetbrains_kotlin_resolve;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (ConeKotlinType coneKotlinType2 : list) {
                    FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                    firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
                    KtSourceElement source2 = anonymousFunction.getSource();
                    firValueParameterBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.LambdaContextParameter.INSTANCE, null, 2, null) : null);
                    firValueParameterBuilder.setContainingDeclarationSymbol(anonymousFunction.getSymbol());
                    firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
                    firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firValueParameterBuilder.setName(SpecialNames.UNDERSCORE_FOR_UNUSED_VAR);
                    firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                    KtSourceElement source3 = anonymousFunction.getSource();
                    firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType2, source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.LambdaContextParameter.INSTANCE, null, 2, null) : null, null, 2, null));
                    firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.ContextParameter);
                    arrayList.add(firValueParameterBuilder.mo288build());
                }
                contextParameters = arrayList;
            }
            if (contextParameters == null) {
                contextParameters = CollectionsKt.emptyList();
            }
        }
        anonymousFunction.replaceContextParameters(contextParameters);
        anonymousFunction.replaceValueParameters(listObtainValueParametersFromResolvedLambdaAtom);
        ImplicitToErrorTypeTransformer implicitToErrorTypeTransformer = ImplicitToErrorTypeTransformer.INSTANCE;
        FirAnonymousFunction firAnonymousFunctionTransformContextParameters = anonymousFunction.transformValueParameters((FirTransformer<? super Object>) implicitToErrorTypeTransformer, (Object) null).transformContextParameters((FirTransformer<? super Object>) implicitToErrorTypeTransformer, (Object) null);
        SetUnnamedContextParameterNamesKt.setUnnamedContextParameterNames(this, firAnonymousFunctionTransformContextParameters);
        FirResolvedTypeRef returnTypeRef = firAnonymousFunctionTransformContextParameters.getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        if (firResolvedTypeRef != null) {
            firResolvedTypeRefResolvedTypeFromPrototype = firResolvedTypeRef;
        } else if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType == null || (returnType = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getReturnType()) == null) {
            firResolvedTypeRefResolvedTypeFromPrototype = null;
        } else {
            FirTypeRef returnTypeRef2 = firAnonymousFunctionTransformContextParameters.getReturnTypeRef();
            KtSourceElement source4 = firAnonymousFunctionTransformContextParameters.getSource();
            firResolvedTypeRefResolvedTypeFromPrototype = CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef2, returnType, source4 != null ? KtSourceElementKt.fakeElement$default(source4, KtFakeSourceElementKind.ImplicitFunctionReturnType.INSTANCE, null, 2, null) : null);
        }
        FirAnonymousFunction firAnonymousFunctionTransformAnonymousFunctionBody = transformAnonymousFunctionBody(firAnonymousFunctionTransformContextParameters, firResolvedTypeRefResolvedTypeFromPrototype != null ? firResolvedTypeRefResolvedTypeFromPrototype : getTransformer().getComponents().getNoExpectedType());
        if (firResolvedTypeRef == null) {
            firAnonymousFunctionTransformAnonymousFunctionBody.replaceReturnTypeRef(computeReturnTypeRef(firAnonymousFunctionTransformAnonymousFunctionBody, firResolvedTypeRefResolvedTypeFromPrototype));
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker != null) {
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, firAnonymousFunctionTransformAnonymousFunctionBody.getReturnTypeRef(), firAnonymousFunctionTransformAnonymousFunctionBody.getSource(), getTransformer().getContext().getFile().getSource());
            }
        }
        FunctionTypeKind.SuspendFunction suspendFunction = firAnonymousFunctionTransformAnonymousFunctionBody.getStatus().isSuspend() ? FunctionTypeKind.SuspendFunction.INSTANCE : null;
        if (suspendFunction != null) {
            expectedFunctionTypeKind = suspendFunction;
        } else if (coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType != null) {
            expectedFunctionTypeKind = coneResolvedLambdaAtomExtractLambdaInfoFromFunctionType.getExpectedFunctionTypeKind();
        }
        firAnonymousFunctionTransformAnonymousFunctionBody.replaceTypeRef(ResolveUtilsKt.constructFunctionTypeRef(firAnonymousFunctionTransformAnonymousFunctionBody, getSession(), expectedFunctionTypeKind));
        FirLookupTrackerComponent lookupTracker2 = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker2 != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker2, firAnonymousFunctionTransformAnonymousFunctionBody.getTypeRef(), firAnonymousFunctionTransformAnonymousFunctionBody.getSource(), getTransformer().getContext().getFile().getSource());
        }
        ResolveUtilsKt.addReturnToLastStatementIfNeeded(firAnonymousFunctionTransformAnonymousFunctionBody, getSession());
        return firAnonymousFunctionTransformAnonymousFunctionBody;
    }

    private final void transformTypeWithPropertyType(FirPropertyAccessor firPropertyAccessor, FirTypeRef firTypeRef, boolean z) {
        FirValueParameter firValueParameter;
        if (firPropertyAccessor.getIsGetter()) {
            if ((firPropertyAccessor.getReturnTypeRef() instanceof FirImplicitTypeRef) || z) {
                firPropertyAccessor.replaceReturnTypeRef(copyWithNewNullableSource(firTypeRef, firPropertyAccessor.getReturnTypeRef().getSource()));
                return;
            }
            return;
        }
        if (!firPropertyAccessor.isSetter() || (firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(firPropertyAccessor.getValueParameters())) == null) {
            return;
        }
        if ((firValueParameter.getReturnTypeRef() instanceof FirImplicitTypeRef) || z) {
            KtSourceElement source = firPropertyAccessor.getReturnTypeRef().getSource();
            if (source == null) {
                KtSourceElement source2 = firValueParameter.getSource();
                source = source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null;
            }
            firValueParameter.replaceReturnTypeRef(copyWithNewNullableSource(firTypeRef, source));
        }
    }

    public static /* synthetic */ void transformTypeWithPropertyType$default(FirDeclarationsResolveTransformer firDeclarationsResolveTransformer, FirPropertyAccessor firPropertyAccessor, FirTypeRef firTypeRef, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: transformTypeWithPropertyType");
            return;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        firDeclarationsResolveTransformer.transformTypeWithPropertyType(firPropertyAccessor, firTypeRef, z);
    }

    private final ConeTypeVariableType unwrapTopLevelVariableType(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeTypeVariableType) {
            return (ConeTypeVariableType) coneKotlinType;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return unwrapTopLevelVariableType(((ConeFlexibleType) coneKotlinType).getLowerBound());
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return unwrapTopLevelVariableType(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        return null;
    }

    private final Visibility visibilityForApproximation(FirDeclaration firDeclaration) {
        FirDeclaration firDeclaration2 = Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firDeclaration), Boolean.TRUE) ? (FirDeclaration) CollectionsKt.getOrNull(getTransformer().getContext().getContainers(), getTransformer().getContext().getContainers().size() - 3) : (FirDeclaration) CollectionsKt.getOrNull(getTransformer().getContext().getContainers(), getTransformer().getContext().getContainers().size() - 2);
        return TypeUtilsKt.visibilityForApproximation(firDeclaration, firDeclaration2 != null ? firDeclaration2.getSymbol() : null);
    }

    public final void doTransformAnonymousFunctionBodyFromCallCompletion$org_jetbrains_kotlin_resolve(FirAnonymousFunctionExpression anonymousFunctionExpression, FirResolvedTypeRef expectedReturnTypeFromCallPosition) {
        FirResolvedTypeRef returnTypeRef;
        anonymousFunctionExpression.getClass();
        FirAnonymousFunction anonymousFunction = anonymousFunctionExpression.getAnonymousFunction();
        if (expectedReturnTypeFromCallPosition != null) {
            returnTypeRef = expectedReturnTypeFromCallPosition;
        } else {
            returnTypeRef = anonymousFunction.getReturnTypeRef();
            if (returnTypeRef instanceof FirImplicitTypeRef) {
                returnTypeRef = null;
            }
        }
        if (expectedReturnTypeFromCallPosition != null) {
            transformAnonymousFunctionBody(anonymousFunction, returnTypeRef);
            return;
        }
        BodyResolveContext context = getTransformer().getContext();
        FirAnonymousFunctionSymbol symbol = anonymousFunction.getSymbol();
        context.getAnonymousFunctionsAnalyzedInDependentContext().add(symbol);
        try {
            transformAnonymousFunctionBody(anonymousFunction, returnTypeRef);
        } finally {
            context.getAnonymousFunctionsAnalyzedInDependentContext().remove(symbol);
        }
    }

    public final FirRegularClass doTransformRegularClassContent(final FirRegularClass regularClass, final ResolutionMode data) {
        regularClass.getClass();
        data.getClass();
        return forRegularClassBody(regularClass, new Function0() { // from class: u15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.e(this.b, regularClass, data);
            }
        });
    }

    public final void doTransformTypeParameters(FirMemberDeclaration declaration) {
        declaration.getClass();
        Iterator<FirTypeParameterRef> it = declaration.getTypeParameters().iterator();
        while (it.hasNext()) {
            it.next().transformChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public FirRegularClass forRegularClassBody(FirRegularClass regularClass, final Function0<? extends FirRegularClass> action) throws UninitializedPropertyAccessException {
        regularClass.getClass();
        action.getClass();
        getComponents().getDataFlowAnalyzer().enterClass(regularClass, getTransformer().getPreserveCFGForClasses());
        FirRegularClass firRegularClass = (FirRegularClass) getTransformer().getContext().forRegularClassBody(regularClass, getTransformer().getComponents(), new Function0() { // from class: y15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.g(action);
            }
        });
        ControlFlowGraph controlFlowGraphExitClass = getComponents().getDataFlowAnalyzer().exitClass();
        if (controlFlowGraphExitClass != null) {
            firRegularClass.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitClass));
        }
        return firRegularClass;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousFunction transformAnonymousFunction(FirAnonymousFunction anonymousFunction, ResolutionMode data) throws KotlinNothingValueException {
        anonymousFunction.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            throw new IllegalStateException("Transformation of anonymous function should be performed via `transformAnonymousFunctionExpression`");
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousFunction, th);
            throw new KotlinNothingValueException();
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, ResolutionMode data) {
        FirStatement firStatementTransformTopLevelAnonymousFunctionExpression;
        anonymousFunctionExpression.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirAnonymousFunction anonymousFunction = anonymousFunctionExpression.getAnonymousFunction();
            BodyResolveContext context = getTransformer().getContext();
            if (anonymousFunction.getTypeParameters().isEmpty()) {
                FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
                ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
                anonymousFunction.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
                anonymousFunction.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent);
                anonymousFunction.transformReceiverParameter((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent);
                Iterator<T> it = anonymousFunction.getContextParameters().iterator();
                while (it.hasNext()) {
                    ((FirValueParameter) it.next()).transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }
                Iterator<T> it2 = anonymousFunction.getValueParameters().iterator();
                while (it2.hasNext()) {
                    ((FirValueParameter) it2.next()).transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }
                if (anonymousFunction.getContractDescription() != null) {
                    FirContractResolveTransformerAdapterKt.runContractResolveForFunction(anonymousFunction, getSession(), getComponents().getScopeSession(), getTransformer().getContext());
                }
                if (data instanceof ResolutionMode.ContextDependent) {
                    getComponents().getDataFlowAnalyzer().enterAnonymousFunctionExpression(anonymousFunctionExpression);
                    getTransformer().getContext().storeContextForAnonymousFunction(anonymousFunction);
                    return anonymousFunctionExpression;
                }
                if (data instanceof ResolutionMode.WithExpectedType) {
                    return transformTopLevelAnonymousFunctionExpression(anonymousFunctionExpression, (ResolutionMode.WithExpectedType) data);
                }
                if (!(data instanceof ResolutionMode.ContextIndependent) && !(data instanceof ResolutionMode.AssignmentLValue) && !(data instanceof ResolutionMode.ReceiverResolution) && !(data instanceof ResolutionMode.Delegate)) {
                    if (!(data instanceof ResolutionMode.WithStatus) && !(data instanceof ResolutionMode.UpdateImplicitTypeRef)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new IllegalStateException(("Should not be here in " + Reflection.getOrCreateKotlinClass(data.getClass()) + " mode").toString());
                }
                return transformTopLevelAnonymousFunctionExpression(anonymousFunctionExpression, null);
            }
            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(anonymousFunction);
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                FirAbstractBodyResolveTransformerDispatcher transformer2 = getTransformer();
                ResolutionMode.ContextIndependent contextIndependent2 = ResolutionMode.ContextIndependent.INSTANCE;
                anonymousFunction.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer2, contextIndependent2);
                anonymousFunction.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent2);
                anonymousFunction.transformReceiverParameter((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent2);
                Iterator<T> it3 = anonymousFunction.getContextParameters().iterator();
                while (it3.hasNext()) {
                    ((FirValueParameter) it3.next()).transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }
                Iterator<T> it4 = anonymousFunction.getValueParameters().iterator();
                while (it4.hasNext()) {
                    ((FirValueParameter) it4.next()).transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }
                if (anonymousFunction.getContractDescription() != null) {
                    FirContractResolveTransformerAdapterKt.runContractResolveForFunction(anonymousFunction, getSession(), getComponents().getScopeSession(), getTransformer().getContext());
                }
                if (data instanceof ResolutionMode.ContextDependent) {
                    getComponents().getDataFlowAnalyzer().enterAnonymousFunctionExpression(anonymousFunctionExpression);
                    getTransformer().getContext().storeContextForAnonymousFunction(anonymousFunction);
                    firStatementTransformTopLevelAnonymousFunctionExpression = anonymousFunctionExpression;
                } else if (data instanceof ResolutionMode.WithExpectedType) {
                    firStatementTransformTopLevelAnonymousFunctionExpression = transformTopLevelAnonymousFunctionExpression(anonymousFunctionExpression, (ResolutionMode.WithExpectedType) data);
                } else {
                    if (!(data instanceof ResolutionMode.ContextIndependent) && !(data instanceof ResolutionMode.AssignmentLValue) && !(data instanceof ResolutionMode.ReceiverResolution) && !(data instanceof ResolutionMode.Delegate)) {
                        if (!(data instanceof ResolutionMode.WithStatus) && !(data instanceof ResolutionMode.UpdateImplicitTypeRef)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        throw new IllegalStateException(("Should not be here in " + Reflection.getOrCreateKotlinClass(data.getClass()) + " mode").toString());
                    }
                    firStatementTransformTopLevelAnonymousFunctionExpression = transformTopLevelAnonymousFunctionExpression(anonymousFunctionExpression, null);
                }
                context.replaceTowerDataContext(towerDataContext);
                return firStatementTransformTopLevelAnonymousFunctionExpression;
            } catch (Throwable th) {
                context.replaceTowerDataContext(towerDataContext);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousFunctionExpression, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousInitializer transformAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, ResolutionMode data) {
        anonymousInitializer.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            return getImplicitTypeOnly() ? anonymousInitializer : transformAnonymousInitializerContent(anonymousInitializer, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousInitializer, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0037  */
    public FirAnonymousInitializer transformAnonymousInitializerContent(FirAnonymousInitializer anonymousInitializer, ResolutionMode data) {
        boolean z;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        anonymousInitializer.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterInitBlock(anonymousInitializer);
        BodyResolveContext context = getTransformer().getContext();
        FirSession session = getSession();
        if (Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(anonymousInitializer), Boolean.TRUE) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(session)) != null) {
            z = scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers();
        }
        FirTowerDataContext towerDataContext = context.getTowerDataContext();
        try {
            FirLocalScope primaryConstructorPureParametersScope = context.getPrimaryConstructorPureParametersScope();
            if (primaryConstructorPureParametersScope != null) {
                context.addLocalScope(primaryConstructorPureParametersScope);
            }
            context.addLocalScope(new FirLocalScope(session));
            context.addAnonymousInitializer(anonymousInitializer);
            context.getContainers().add(anonymousInitializer);
            try {
                FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(anonymousInitializer, ResolutionMode.ContextIndependent.INSTANCE);
                firDeclarationTransformDeclarationContent.getClass();
                FirAnonymousInitializer firAnonymousInitializer = (FirAnonymousInitializer) firDeclarationTransformDeclarationContent;
                firAnonymousInitializer.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(getComponents().getDataFlowAnalyzer().exitInitBlock(firAnonymousInitializer)));
                context.getContainers().removeLast();
                if (!z) {
                    context.replaceTowerDataContext(towerDataContext);
                }
                return firAnonymousInitializer;
            } catch (Throwable th) {
                context.getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            if (!z) {
                context.replaceTowerDataContext(towerDataContext);
            }
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousObject transformAnonymousObject(final FirAnonymousObject anonymousObject, final ResolutionMode data) {
        Throwable th;
        Throwable th2;
        anonymousObject.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            try {
                BodyResolveContext context = getTransformer().getContext();
                context.getContainingClassDeclarations().add(anonymousObject);
                try {
                    if (getTransformer().getContext().getTargetedLocalClasses().contains(anonymousObject)) {
                        try {
                            if (anonymousObject.getControlFlowGraphReference() != null) {
                                throw new IllegalArgumentException("Failed requirement.");
                            }
                            getComponents().getDataFlowAnalyzer().enterClass(anonymousObject, !getImplicitTypeOnly());
                            final BodyResolveContext context2 = getTransformer().getContext();
                            FirAnonymousObject firAnonymousObject = (FirAnonymousObject) context2.withScopesForClass(anonymousObject, getTransformer().getComponents(), new Function0<FirAnonymousObject>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer$transformAnonymousObject$lambda$0$0$$inlined$withAnonymousObject$1
                                public final FirAnonymousObject invoke() {
                                    BodyResolveContext bodyResolveContext = context2;
                                    bodyResolveContext.getContainers().add(anonymousObject);
                                    try {
                                        FirDeclaration firDeclarationTransformDeclarationContent = this.transformDeclarationContent(anonymousObject, data);
                                        firDeclarationTransformDeclarationContent.getClass();
                                        return (FirAnonymousObject) firDeclarationTransformDeclarationContent;
                                    } finally {
                                        bodyResolveContext.getContainers().removeLast();
                                    }
                                }
                            });
                            ControlFlowGraph controlFlowGraphExitClass = getComponents().getDataFlowAnalyzer().exitClass();
                            if (controlFlowGraphExitClass != null) {
                                firAnonymousObject.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitClass));
                            }
                            context.getContainingClassDeclarations().removeLast();
                            return firAnonymousObject;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        try {
                            FirAnonymousObject firAnonymousObject2 = (FirAnonymousObject) LocalClassesResolutionKt.runAllPhasesForLocalClassLikeDeclarations(anonymousObject, getTransformer().getComponents(), data);
                            try {
                                context.getContainingClassDeclarations().removeLast();
                                return firAnonymousObject2;
                            } catch (Throwable th4) {
                                th = th4;
                                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th);
                                wq6.a();
                                return null;
                            }
                        } catch (Throwable th5) {
                            th2 = th5;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
                th2 = th;
                context.getContainingClassDeclarations().removeLast();
                throw th2;
            } catch (Throwable th7) {
                th = th7;
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th);
                wq6.a();
                return null;
            }
        } catch (Throwable th8) {
            th = th8;
            th = th;
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirCodeFragment transformCodeFragment(final FirCodeFragment codeFragment, final ResolutionMode data) throws UninitializedPropertyAccessException {
        codeFragment.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterCodeFragment(codeFragment);
        getTransformer().getContext().withCodeFragment(codeFragment, getTransformer().getComponents(), new Function0() { // from class: c25
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.h(this.b, codeFragment, data);
            }
        });
        getComponents().getDataFlowAnalyzer().exitCodeFragment(codeFragment);
        return codeFragment;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformConstructor(FirConstructor constructor, ResolutionMode data) {
        constructor.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (getImplicitTypeOnly()) {
                return constructor;
            }
            FirDeclaration containerIfAny = getTransformer().getContext().getContainerIfAny();
            FirRegularClass firRegularClass = containerIfAny instanceof FirRegularClass ? (FirRegularClass) containerIfAny : null;
            if (constructor.getIsPrimary()) {
                if ((firRegularClass != null ? firRegularClass.getClassKind() : null) == ClassKind.ANNOTATION_CLASS) {
                    BodyResolveContext context = getTransformer().getContext();
                    boolean isInsideAnnotationContext = context.getIsInsideAnnotationContext();
                    context.setInsideAnnotationContext(true);
                    try {
                        return transformConstructorContent(constructor, data);
                    } finally {
                        context.setInsideAnnotationContext(isInsideAnnotationContext);
                    }
                }
            }
            return transformConstructorContent(constructor, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(constructor, th);
            wq6.a();
            return null;
        }
    }

    public FirConstructor transformConstructorContent(final FirConstructor constructor, final ResolutionMode data) {
        FirTowerDataMode towerDataMode;
        constructor.getClass();
        data.getClass();
        FirDeclaration containerIfAny = getTransformer().getContext().getContainerIfAny();
        FirRegularClass firRegularClass = containerIfAny instanceof FirRegularClass ? (FirRegularClass) containerIfAny : null;
        getComponents().getDataFlowAnalyzer().enterFunction(constructor);
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode firTowerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (firTowerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        } else {
            towerDataMode = firTowerDataMode;
        }
        context.setTowerDataMode(towerDataMode);
        context.getContainers().add(constructor);
        try {
            constructor.transformTypeParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReceiverParameter((FirTransformer<? super ResolutionMode>) getTransformer(), data).transformReturnTypeRef((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            BodyResolveContext context2 = getTransformer().getContext();
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
            if (context2.getTowerDataMode() != firTowerDataMode) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            FirTowerDataContext towerDataContext = context2.getTowerDataContext();
            try {
                if (!constructor.getIsPrimary()) {
                    context2.addInaccessibleImplicitReceiverValue(firRegularClass, components);
                }
                context2.addLocalScope(context2.buildConstructorParametersScope(constructor, components.getSession()));
                constructor.transformValueParameters((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                context2.replaceTowerDataContext(towerDataContext);
                constructor.transformDelegatedConstructor(getTransformer(), data);
                context.getContainers().removeLast();
                context.setTowerDataMode(towerDataMode2);
                constructor.replaceControlFlowGraphReference(getComponents().getDataFlowAnalyzer().exitFunction(constructor));
                return constructor;
            } catch (Throwable th2) {
                context2.replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        } catch (Throwable th3) {
            context.getContainers().removeLast();
            throw th3;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDanglingModifierList transformDanglingModifierList(final FirDanglingModifierList danglingModifierList, final ResolutionMode data) {
        danglingModifierList.getClass();
        data.getClass();
        if (getImplicitTypeOnly()) {
            return danglingModifierList;
        }
        getTransformer().getContext().withDanglingModifierList(danglingModifierList, new Function0() { // from class: v15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.k(danglingModifierList, this, data);
            }
        });
        return danglingModifierList;
    }

    public final FirDeclaration transformDeclarationContent(FirDeclaration declaration, ResolutionMode data) {
        declaration.getClass();
        data.getClass();
        return getTransformer().transformDeclarationContent(declaration, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclarationStatus transformDeclarationStatus(FirDeclarationStatus declarationStatus, ResolutionMode data) {
        FirDeclarationStatus status;
        declarationStatus.getClass();
        data.getClass();
        ResolutionMode.WithStatus withStatus = data instanceof ResolutionMode.WithStatus ? (ResolutionMode.WithStatus) data : null;
        return (withStatus == null || (status = withStatus.getStatus()) == null) ? declarationStatus : status;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirEnumEntry transformEnumEntry(FirEnumEntry enumEntry, ResolutionMode data) {
        enumEntry.getClass();
        data.getClass();
        if (getImplicitTypeOnly()) {
            return enumEntry;
        }
        BodyResolveContext context = getTransformer().getContext();
        FirTowerDataMode towerDataMode = FirTowerDataMode.ENUM_ENTRY;
        FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
        if (towerDataMode == null) {
            try {
                towerDataMode = context.getTowerDataMode();
            } catch (Throwable th) {
                context.setTowerDataMode(towerDataMode2);
                throw th;
            }
        }
        context.setTowerDataMode(towerDataMode);
        context.getContainers().add(enumEntry);
        try {
            FirElement firElementTransformChildren = enumEntry.transformChildren(this, data);
            firElementTransformChildren.getClass();
            FirEnumEntry firEnumEntry = (FirEnumEntry) firElementTransformChildren;
            context.getContainers().removeLast();
            context.setTowerDataMode(towerDataMode2);
            return firEnumEntry;
        } catch (Throwable th2) {
            context.getContainers().removeLast();
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirErrorPrimaryConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, ResolutionMode data) {
        errorPrimaryConstructor.getClass();
        data.getClass();
        FirConstructor firConstructorTransformConstructor = transformConstructor((FirConstructor) errorPrimaryConstructor, data);
        firConstructorTransformConstructor.getClass();
        return (FirErrorPrimaryConstructor) firConstructorTransformConstructor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirField transformField(FirField field, ResolutionMode data) {
        field.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirTypeRef returnTypeRef = field.getReturnTypeRef();
            if (!getImplicitTypeOnly()) {
                getComponents().getDataFlowAnalyzer().enterField(field);
                boolean implicitTypeOnly = getImplicitTypeOnly();
                if (implicitTypeOnly) {
                    setImplicitTypeOnly$org_jetbrains_kotlin_resolve(false);
                }
                try {
                    BodyResolveContext context = getTransformer().getContext();
                    FirTowerDataMode towerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
                    FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
                    if (towerDataMode == null) {
                        try {
                            towerDataMode = context.getTowerDataMode();
                        } catch (Throwable th) {
                            context.setTowerDataMode(towerDataMode2);
                            throw th;
                        }
                    }
                    context.setTowerDataMode(towerDataMode);
                    context.getContainers().add(field);
                    try {
                        FirTowerDataContext towerDataContext = context.getTowerDataContext();
                        try {
                            FirLocalScope primaryConstructorAllParametersScope = context.getPrimaryConstructorAllParametersScope();
                            if (primaryConstructorAllParametersScope != null) {
                                context.addLocalScope(primaryConstructorAllParametersScope);
                            }
                            field.transformChildren(getTransformer(), ResolutionModeKt.withExpectedType$default(returnTypeRef, null, null, 6, null));
                            context.replaceTowerDataContext(towerDataContext);
                            context.getContainers().removeLast();
                            context.setTowerDataMode(towerDataMode2);
                            if (field.getInitializer() != null) {
                                storeVariableReturnType(field);
                            }
                            ControlFlowGraph controlFlowGraphExitField = getComponents().getDataFlowAnalyzer().exitField(field);
                            if (controlFlowGraphExitField != null) {
                                field.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitField));
                            }
                            if (implicitTypeOnly) {
                                setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
                            }
                        } catch (Throwable th2) {
                            context.replaceTowerDataContext(towerDataContext);
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        context.getContainers().removeLast();
                        throw th3;
                    }
                } catch (Throwable th4) {
                    if (implicitTypeOnly) {
                        setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
                    }
                    throw th4;
                }
            }
            return field;
        } catch (Throwable th5) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(field, th5);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, ResolutionMode data) {
        file.getClass();
        data.getClass();
        checkSessionConsistency(file);
        try {
            return doTransformFile(file, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFunction transformFunction(FirFunction function, ResolutionMode data) {
        function.getClass();
        data.getClass();
        throw new IllegalStateException("Concrete transform functions should be called");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    public FirFunction transformFunctionContent(FirFunction function, ResolutionMode resolutionModeForBody, boolean shouldResolveEverything) throws UninitializedPropertyAccessException {
        function.getClass();
        resolutionModeForBody.getClass();
        boolean bodyResolved = getBodyResolved(function);
        getComponents().getDataFlowAnalyzer().enterFunction(function);
        if (shouldResolveEverything) {
            ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
            function.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent).transformContextParameters((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent).transformValueParameters(this, contextIndependent).transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
            SetUnnamedContextParameterNamesKt.setUnnamedContextParameterNames(this, function);
        }
        if (!bodyResolved) {
            function.transformBody(this, resolutionModeForBody);
        }
        if (shouldResolveEverything && (function instanceof FirContractDescriptionOwner)) {
            ((FirContractDescriptionOwner) function).transformContractDescription(this, ResolutionMode.ContextIndependent.INSTANCE);
        }
        FirControlFlowGraphReference firControlFlowGraphReferenceExitFunction = getComponents().getDataFlowAnalyzer().exitFunction(function);
        if (!bodyResolved) {
            function.replaceControlFlowGraphReference(firControlFlowGraphReferenceExitFunction);
        }
        return function;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(final FirNamedFunction namedFunction, ResolutionMode data) {
        FirNamedFunction firNamedFunction;
        namedFunction.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            boolean implicitTypeOnly = getImplicitTypeOnly();
            final boolean z = !implicitTypeOnly;
            if (!(namedFunction.getReturnTypeRef() instanceof FirImplicitTypeRef) && getImplicitTypeOnly()) {
                return namedFunction;
            }
            FirDeclaration containerIfAny = getTransformer().getContext().getContainerIfAny();
            BodyResolveContext context = getTransformer().getContext();
            FirSession session2 = getSession();
            if (!(context.getContainerIfAny() instanceof FirClass)) {
                context.storeFunction(namedFunction, session2);
            }
            FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(namedFunction) ? FirTowerDataMode.COMPANION_BLOCK : null;
            FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
            if (towerDataMode == null) {
                try {
                    towerDataMode = context.getTowerDataMode();
                } catch (Throwable th) {
                    context.setTowerDataMode(towerDataMode2);
                    throw th;
                }
            }
            context.setTowerDataMode(towerDataMode);
            if (namedFunction.getTypeParameters().isEmpty()) {
                FirFunction publicApiInlineFunction = context.isPublicInline(namedFunction) ? namedFunction : null;
                if (publicApiInlineFunction == null) {
                    publicApiInlineFunction = context.getPublicApiInlineFunction();
                }
                FirFunction publicApiInlineFunction2 = context.getPublicApiInlineFunction();
                try {
                    context.setPublicApiInlineFunction(publicApiInlineFunction);
                    context.getContainers().add(namedFunction);
                    if (!implicitTypeOnly) {
                        try {
                            namedFunction.transformReceiverParameter((FirTransformer<? super ResolutionMode>) this, data);
                            doTransformTypeParameters(namedFunction);
                        } catch (Throwable th2) {
                            context.getContainers().removeLast();
                            throw th2;
                        }
                    }
                    if (containerIfAny != null && !(containerIfAny instanceof FirClass) && !(containerIfAny instanceof FirFile) && (!(containerIfAny instanceof FirScript) || Intrinsics.areEqual(namedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE))) {
                        prepareSignatureForBodyResolve(namedFunction);
                        namedFunction.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, namedFunction, null, null, 3, null)));
                        if (namedFunction.getContractDescription() != null) {
                            FirContractResolveTransformerAdapterKt.runContractResolveForFunction(namedFunction, getSession(), getComponents().getScopeSession(), getTransformer().getContext());
                        }
                    }
                    firNamedFunction = (FirNamedFunction) getTransformer().getContext().forFunctionBody(namedFunction, getTransformer().getComponents(), new Function0() { // from class: e25
                        public final Object invoke() {
                            return FirDeclarationsResolveTransformer.transformNamedFunction$lambda$0$0$0(this.b, namedFunction, z);
                        }
                    });
                    context.getContainers().removeLast();
                    context.setPublicApiInlineFunction(publicApiInlineFunction2);
                } catch (Throwable th3) {
                    context.setPublicApiInlineFunction(publicApiInlineFunction2);
                    throw th3;
                }
            } else {
                FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(namedFunction);
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                    FirFunction publicApiInlineFunction3 = context.isPublicInline(namedFunction) ? namedFunction : null;
                    if (publicApiInlineFunction3 == null) {
                        publicApiInlineFunction3 = context.getPublicApiInlineFunction();
                    }
                    FirFunction publicApiInlineFunction4 = context.getPublicApiInlineFunction();
                    try {
                        context.setPublicApiInlineFunction(publicApiInlineFunction3);
                        context.getContainers().add(namedFunction);
                        if (!implicitTypeOnly) {
                            try {
                                namedFunction.transformReceiverParameter((FirTransformer<? super ResolutionMode>) this, data);
                                doTransformTypeParameters(namedFunction);
                            } catch (Throwable th4) {
                                context.getContainers().removeLast();
                                throw th4;
                            }
                        }
                        if (containerIfAny != null && !(containerIfAny instanceof FirClass) && !(containerIfAny instanceof FirFile) && (!(containerIfAny instanceof FirScript) || Intrinsics.areEqual(namedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE))) {
                            prepareSignatureForBodyResolve(namedFunction);
                            namedFunction.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, namedFunction, null, null, 3, null)));
                            if (namedFunction.getContractDescription() != null) {
                                FirContractResolveTransformerAdapterKt.runContractResolveForFunction(namedFunction, getSession(), getComponents().getScopeSession(), getTransformer().getContext());
                            }
                        }
                        firNamedFunction = (FirNamedFunction) getTransformer().getContext().forFunctionBody(namedFunction, getTransformer().getComponents(), new Function0() { // from class: e25
                            public final Object invoke() {
                                return FirDeclarationsResolveTransformer.transformNamedFunction$lambda$0$0$0(this.b, namedFunction, z);
                            }
                        });
                        context.getContainers().removeLast();
                        context.setPublicApiInlineFunction(publicApiInlineFunction4);
                        context.replaceTowerDataContext(towerDataContext);
                    } catch (Throwable th5) {
                        context.setPublicApiInlineFunction(publicApiInlineFunction4);
                        throw th5;
                    }
                } catch (Throwable th6) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th6;
                }
            }
            context.setTowerDataMode(towerDataMode2);
            return firNamedFunction;
        } catch (Throwable th7) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(namedFunction, th7);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:393:0x0662 A[Catch: all -> 0x059c, TRY_ENTER, TryCatch #26 {all -> 0x059c, blocks: (B:362:0x05e1, B:364:0x05e7, B:348:0x0598, B:352:0x05a1, B:354:0x05a7, B:355:0x05d0, B:357:0x05d5, B:359:0x05da, B:358:0x05d8, B:393:0x0662, B:394:0x0665), top: B:454:0x0477 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [org.jetbrains.kotlin.fir.FirSession] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirProperty transformProperty(FirProperty property, ResolutionMode data) {
        FirProperty firProperty;
        Throwable th;
        FirTowerDataMode firTowerDataMode;
        FirTowerDataContext firTowerDataContext;
        boolean z;
        FirBackingField backingField;
        FirBackingField backingField2;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        ControlFlowGraph controlFlowGraphExitProperty;
        int i;
        boolean z2;
        FirBackingField backingField3;
        FirBackingField backingField4;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent2;
        FirProperty firProperty2 = property;
        firProperty2.getClass();
        data.getClass();
        FirTowerDataMode session = getSession();
        try {
            try {
                if (firProperty2 instanceof FirSyntheticProperty) {
                    throw new IllegalArgumentException("Synthetic properties should not be processed by body transformers");
                }
                try {
                    if (!(firProperty2.getSymbol() instanceof FirLocalPropertySymbol) || Intrinsics.areEqual(firProperty2.getOrigin(), FirDeclarationOrigin.Synthetic.ScriptTopLevelDestructuringDeclarationContainer.INSTANCE)) {
                        FirDeclarationsResolveTransformer firDeclarationsResolveTransformer = this;
                        FirBackingField backingField5 = firProperty2.getBackingField();
                        boolean z3 = (backingField5 != null ? backingField5.getReturnTypeRef() : null) instanceof FirImplicitTypeRef;
                        if (firProperty2.getStatus().isConst() || !firDeclarationsResolveTransformer.getImplicitTypeOnly() || (firProperty2.getReturnTypeRef() instanceof FirImplicitTypeRef) || z3) {
                            boolean implicitTypeOnly = firDeclarationsResolveTransformer.getImplicitTypeOnly();
                            boolean z4 = !implicitTypeOnly;
                            FirPropertyBodyResolveState bodyResolveState = firProperty2.getBodyResolveState();
                            boolean implicitTypeOnly2 = firDeclarationsResolveTransformer.getImplicitTypeOnly();
                            if (implicitTypeOnly2) {
                                firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(false);
                            }
                            try {
                                FirPropertyBodyResolveState firPropertyBodyResolveState = FirPropertyBodyResolveState.INITIALIZER_RESOLVED;
                                boolean z5 = bodyResolveState.compareTo(firPropertyBodyResolveState) >= 0;
                                if (!z5) {
                                    firDeclarationsResolveTransformer.getComponents().getDataFlowAnalyzer().enterProperty(firProperty2);
                                }
                                BodyResolveContext context = firDeclarationsResolveTransformer.getTransformer().getContext();
                                FirTowerDataMode towerDataMode = FirStatusUtilsKt.isCompanionBlockMember(firProperty2) ? FirTowerDataMode.COMPANION_BLOCK : null;
                                FirTowerDataMode towerDataMode2 = context.getTowerDataMode();
                                try {
                                    if (towerDataMode == null) {
                                        try {
                                            towerDataMode = context.getTowerDataMode();
                                        } catch (Throwable th2) {
                                            th = th2;
                                            session = towerDataMode2;
                                            context.setTowerDataMode(session);
                                            throw th;
                                        }
                                    }
                                    context.setTowerDataMode(towerDataMode);
                                    FirTowerDataContext firTowerDataContext2 = "info.txt";
                                    try {
                                        if (firProperty2.getTypeParameters().isEmpty()) {
                                            context.getContainers().add(firProperty2);
                                            if (!implicitTypeOnly) {
                                                try {
                                                    FirAbstractBodyResolveTransformerDispatcher transformer = firDeclarationsResolveTransformer.getTransformer();
                                                    ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
                                                    firProperty2.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
                                                    firProperty2.transformReceiverParameter((FirTransformer<? super ResolutionMode.ContextIndependent>) firDeclarationsResolveTransformer.getTransformer(), contextIndependent);
                                                    firProperty2.transformContextParameters((FirTransformer<? super ResolutionMode.ContextIndependent>) firDeclarationsResolveTransformer.getTransformer(), contextIndependent);
                                                    doTransformTypeParameters(property);
                                                    SetUnnamedContextParameterNamesKt.setUnnamedContextParameterNames(this, property);
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    context.getContainers().removeLast();
                                                    throw th;
                                                }
                                            }
                                            Boolean boolIsReplSnippetDeclaration = DeclarationAttributesKt.isReplSnippetDeclaration(firProperty2);
                                            Boolean bool = Boolean.TRUE;
                                            boolean z6 = Intrinsics.areEqual(boolIsReplSnippetDeclaration, bool) || (Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(firProperty2), bool) && (scriptResolutionHacksComponent2 = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(firDeclarationsResolveTransformer.getSession())) != null && scriptResolutionHacksComponent2.getSkipTowerDataCleanupForTopLevelInitializers());
                                            BodyResolveContext context2 = firDeclarationsResolveTransformer.getTransformer().getContext();
                                            boolean z7 = z6;
                                            FirTowerDataContext towerDataContext = context2.getTowerDataContext();
                                            firTowerDataMode = towerDataMode2;
                                            try {
                                                try {
                                                    FirLocalScope primaryConstructorPureParametersScope = context2.getPrimaryConstructorPureParametersScope();
                                                    if (primaryConstructorPureParametersScope != null) {
                                                        context2.addLocalScope(primaryConstructorPureParametersScope);
                                                        Unit unit = Unit.INSTANCE;
                                                    }
                                                    if (!z5) {
                                                        try {
                                                            firProperty2.transformInitializer((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, 6, null));
                                                            firProperty2.replaceBodyResolveState(firPropertyBodyResolveState);
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            if (!z7) {
                                                                context2.replaceTowerDataContext(towerDataContext);
                                                            }
                                                            throw th;
                                                        }
                                                    }
                                                    if (firProperty2.getInitializer() != null) {
                                                        storeVariableReturnType(property);
                                                    }
                                                    boolean z8 = DeclarationAttributesKt.getHasExplicitBackingField(firProperty2) || (firProperty2.getReturnTypeRef() instanceof FirResolvedTypeRef);
                                                    if (z5 || !z8) {
                                                        i = 6;
                                                        z2 = false;
                                                    } else {
                                                        FirBackingField backingField6 = firProperty2.getBackingField();
                                                        if (backingField6 != null) {
                                                            i = 6;
                                                            firDeclarationsResolveTransformer.transformBackingField(backingField6, ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, 6, null), z4);
                                                        } else {
                                                            i = 6;
                                                        }
                                                        z2 = true;
                                                    }
                                                    Unit unit2 = Unit.INSTANCE;
                                                    if (!z7) {
                                                        try {
                                                            context2.replaceTowerDataContext(towerDataContext);
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            context.getContainers().removeLast();
                                                            throw th;
                                                        }
                                                    }
                                                    if (implicitTypeOnly == 0) {
                                                        firProperty2.transformAnnotations((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), data);
                                                        if (z5 && (backingField4 = firProperty2.getBackingField()) != null) {
                                                            backingField4.transformAnnotations((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), data);
                                                        }
                                                    }
                                                    FirExpression delegate = firProperty2.getDelegate();
                                                    if (delegate != null) {
                                                        FirPropertyBodyResolveState firPropertyBodyResolveState2 = FirPropertyBodyResolveState.ALL_BODIES_RESOLVED;
                                                        if (bodyResolveState != firPropertyBodyResolveState2) {
                                                            firDeclarationsResolveTransformer.transformPropertyAccessorsWithDelegate(firProperty2, delegate, z4);
                                                            if (firProperty2.getDelegateFieldSymbol() != null) {
                                                                replacePropertyReferenceTypeInDelegateAccessors(property);
                                                            }
                                                            firProperty2.replaceBodyResolveState(firPropertyBodyResolveState2);
                                                        } else {
                                                            if (implicitTypeOnly) {
                                                                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Invariant is broken");
                                                                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                                                                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "property", firProperty2);
                                                                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                                                                throw kotlinIllegalArgumentExceptionWithAttachments;
                                                            }
                                                            firDeclarationsResolveTransformer.resolveAccessors(firProperty2, true, true);
                                                        }
                                                        i = i;
                                                        firTowerDataMode = firTowerDataMode;
                                                    } else {
                                                        boolean z9 = implicitTypeOnly == 0 || ((firProperty2.getGetter() == null || (firProperty2.getGetter() instanceof FirDefaultPropertyAccessor)) && (firProperty2.getSetter() == null || (firProperty2.getSetter() instanceof FirDefaultPropertyAccessor)));
                                                        FirTypeRef returnTypeRef = firProperty2.getReturnTypeRef();
                                                        boolean z10 = returnTypeRef instanceof FirResolvedTypeRef;
                                                        if (z9 || !z10) {
                                                            i = i;
                                                            firTowerDataMode = firTowerDataMode;
                                                            firDeclarationsResolveTransformer.resolveAccessors(firProperty2, z9, z4);
                                                            firProperty2.replaceBodyResolveState(z9 ? FirPropertyBodyResolveState.ALL_BODIES_RESOLVED : FirPropertyBodyResolveState.INITIALIZER_AND_GETTER_RESOLVED);
                                                        } else {
                                                            FirPropertyAccessor getter = firProperty2.getGetter();
                                                            if (getter != null) {
                                                                try {
                                                                    transformTypeWithPropertyType$default(firDeclarationsResolveTransformer, getter, returnTypeRef, false, 2, null);
                                                                } catch (Throwable th6) {
                                                                    th = th6;
                                                                    context.getContainers().removeLast();
                                                                    throw th;
                                                                }
                                                            }
                                                            FirPropertyAccessor setter = firProperty2.getSetter();
                                                            if (setter != null) {
                                                                firDeclarationsResolveTransformer = this;
                                                                transformTypeWithPropertyType$default(firDeclarationsResolveTransformer, setter, returnTypeRef, false, 2, null);
                                                            } else {
                                                                firDeclarationsResolveTransformer = this;
                                                            }
                                                            FirPropertyAccessor setter2 = firProperty2.getSetter();
                                                            if (setter2 != null) {
                                                                FirAbstractBodyResolveTransformerDispatcher transformer2 = firDeclarationsResolveTransformer.getTransformer();
                                                                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                                                                firResolvedTypeRefBuilder.setConeType(firDeclarationsResolveTransformer.getSession().getBuiltinTypes().getUnitType().getConeType());
                                                                setter2.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) transformer2, new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuilder.build()));
                                                            }
                                                        }
                                                    }
                                                    if (!z5 && !z2 && (backingField3 = firProperty2.getBackingField()) != null) {
                                                        firDeclarationsResolveTransformer.transformBackingField(backingField3, ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, i, null), z4);
                                                    }
                                                    context.getContainers().removeLast();
                                                    z5 = z5;
                                                } catch (Throwable th7) {
                                                    th = th7;
                                                }
                                            } catch (Throwable th8) {
                                                th = th8;
                                            }
                                        } else {
                                            implicitTypeOnly2 = implicitTypeOnly2;
                                            firTowerDataMode = towerDataMode2;
                                            FirMemberTypeParameterScope firMemberTypeParameterScope = new FirMemberTypeParameterScope(firProperty2);
                                            FirTowerDataContext towerDataContext2 = context.getTowerDataContext();
                                            try {
                                                context.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope, false));
                                                context.getContainers().add(firProperty2);
                                                try {
                                                    if (!implicitTypeOnly) {
                                                        try {
                                                            FirAbstractBodyResolveTransformerDispatcher transformer3 = firDeclarationsResolveTransformer.getTransformer();
                                                            ResolutionMode.ContextIndependent contextIndependent2 = ResolutionMode.ContextIndependent.INSTANCE;
                                                            firProperty2.transformReturnTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer3, contextIndependent2);
                                                            firProperty2.transformReceiverParameter((FirTransformer<? super ResolutionMode.ContextIndependent>) firDeclarationsResolveTransformer.getTransformer(), contextIndependent2);
                                                            firProperty2.transformContextParameters((FirTransformer<? super ResolutionMode.ContextIndependent>) firDeclarationsResolveTransformer.getTransformer(), contextIndependent2);
                                                            doTransformTypeParameters(property);
                                                            SetUnnamedContextParameterNamesKt.setUnnamedContextParameterNames(this, property);
                                                        } catch (Throwable th9) {
                                                            th = th9;
                                                            context.getContainers().removeLast();
                                                            throw th;
                                                        }
                                                    }
                                                    Boolean boolIsReplSnippetDeclaration2 = DeclarationAttributesKt.isReplSnippetDeclaration(firProperty2);
                                                    Boolean bool2 = Boolean.TRUE;
                                                    boolean z11 = Intrinsics.areEqual(boolIsReplSnippetDeclaration2, bool2) || (Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(firProperty2), bool2) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(firDeclarationsResolveTransformer.getSession())) != null && scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers());
                                                    BodyResolveContext context3 = firDeclarationsResolveTransformer.getTransformer().getContext();
                                                    FirTowerDataContext towerDataContext3 = context3.getTowerDataContext();
                                                    boolean z12 = z11;
                                                    try {
                                                        try {
                                                            FirLocalScope primaryConstructorPureParametersScope2 = context3.getPrimaryConstructorPureParametersScope();
                                                            if (primaryConstructorPureParametersScope2 != null) {
                                                                context3.addLocalScope(primaryConstructorPureParametersScope2);
                                                                Unit unit3 = Unit.INSTANCE;
                                                            }
                                                            if (z5) {
                                                                firTowerDataContext = towerDataContext2;
                                                            } else {
                                                                try {
                                                                    firTowerDataContext = towerDataContext2;
                                                                    try {
                                                                        firProperty2.transformInitializer((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, 6, null));
                                                                        firProperty2.replaceBodyResolveState(firPropertyBodyResolveState);
                                                                    } catch (Throwable th10) {
                                                                        th = th10;
                                                                        if (!z12) {
                                                                            context3.replaceTowerDataContext(towerDataContext3);
                                                                        }
                                                                        throw th;
                                                                    }
                                                                } catch (Throwable th11) {
                                                                    th = th11;
                                                                    firTowerDataContext = towerDataContext2;
                                                                    if (!z12) {
                                                                        context3.replaceTowerDataContext(towerDataContext3);
                                                                    }
                                                                    throw th;
                                                                }
                                                            }
                                                            if (firProperty2.getInitializer() != null) {
                                                                storeVariableReturnType(property);
                                                            }
                                                            boolean z13 = DeclarationAttributesKt.getHasExplicitBackingField(firProperty2) || (firProperty2.getReturnTypeRef() instanceof FirResolvedTypeRef);
                                                            if (z5 == 0 && z13) {
                                                                FirBackingField backingField7 = firProperty2.getBackingField();
                                                                if (backingField7 != null) {
                                                                    firDeclarationsResolveTransformer.transformBackingField(backingField7, ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, 6, null), z4);
                                                                }
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            Unit unit4 = Unit.INSTANCE;
                                                            if (!z12) {
                                                                try {
                                                                    context3.replaceTowerDataContext(towerDataContext3);
                                                                } catch (Throwable th12) {
                                                                    th = th12;
                                                                    context.getContainers().removeLast();
                                                                    throw th;
                                                                }
                                                            }
                                                            if (!implicitTypeOnly) {
                                                                firProperty2.transformAnnotations((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), data);
                                                                if (z5 && (backingField2 = firProperty2.getBackingField()) != null) {
                                                                    backingField2.transformAnnotations((FirTransformer<? super ResolutionMode>) firDeclarationsResolveTransformer.getTransformer(), data);
                                                                }
                                                            }
                                                            FirExpression delegate2 = firProperty2.getDelegate();
                                                            if (delegate2 != null) {
                                                                FirPropertyBodyResolveState firPropertyBodyResolveState3 = FirPropertyBodyResolveState.ALL_BODIES_RESOLVED;
                                                                if (bodyResolveState != firPropertyBodyResolveState3) {
                                                                    firDeclarationsResolveTransformer.transformPropertyAccessorsWithDelegate(firProperty2, delegate2, z4);
                                                                    if (firProperty2.getDelegateFieldSymbol() != null) {
                                                                        replacePropertyReferenceTypeInDelegateAccessors(property);
                                                                    }
                                                                    firProperty2.replaceBodyResolveState(firPropertyBodyResolveState3);
                                                                } else {
                                                                    if (implicitTypeOnly) {
                                                                        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments("Invariant is broken");
                                                                        ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
                                                                        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder2, "property", firProperty2);
                                                                        kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
                                                                        throw kotlinIllegalArgumentExceptionWithAttachments2;
                                                                    }
                                                                    firDeclarationsResolveTransformer.resolveAccessors(firProperty2, true, true);
                                                                }
                                                                firTowerDataContext = firTowerDataContext;
                                                            } else {
                                                                boolean z14 = !implicitTypeOnly || ((firProperty2.getGetter() == null || (firProperty2.getGetter() instanceof FirDefaultPropertyAccessor)) && (firProperty2.getSetter() == null || (firProperty2.getSetter() instanceof FirDefaultPropertyAccessor)));
                                                                FirTypeRef returnTypeRef2 = firProperty2.getReturnTypeRef();
                                                                boolean z15 = returnTypeRef2 instanceof FirResolvedTypeRef;
                                                                if (z14 || !z15) {
                                                                    firTowerDataContext = firTowerDataContext;
                                                                    firDeclarationsResolveTransformer.resolveAccessors(firProperty2, z14, z4);
                                                                    firProperty2.replaceBodyResolveState(z14 ? FirPropertyBodyResolveState.ALL_BODIES_RESOLVED : FirPropertyBodyResolveState.INITIALIZER_AND_GETTER_RESOLVED);
                                                                } else {
                                                                    FirPropertyAccessor getter2 = firProperty2.getGetter();
                                                                    if (getter2 != null) {
                                                                        try {
                                                                            transformTypeWithPropertyType$default(firDeclarationsResolveTransformer, getter2, returnTypeRef2, false, 2, null);
                                                                        } catch (Throwable th13) {
                                                                            th = th13;
                                                                            context.getContainers().removeLast();
                                                                            throw th;
                                                                        }
                                                                    }
                                                                    FirPropertyAccessor setter3 = firProperty2.getSetter();
                                                                    if (setter3 != null) {
                                                                        firDeclarationsResolveTransformer = this;
                                                                        transformTypeWithPropertyType$default(firDeclarationsResolveTransformer, setter3, returnTypeRef2, false, 2, null);
                                                                    } else {
                                                                        firDeclarationsResolveTransformer = this;
                                                                    }
                                                                    FirPropertyAccessor setter4 = firProperty2.getSetter();
                                                                    if (setter4 != null) {
                                                                        FirAbstractBodyResolveTransformerDispatcher transformer4 = firDeclarationsResolveTransformer.getTransformer();
                                                                        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
                                                                        firResolvedTypeRefBuilder2.setConeType(firDeclarationsResolveTransformer.getSession().getBuiltinTypes().getUnitType().getConeType());
                                                                        setter4.transformReturnTypeRef((FirTransformer<? super ResolutionMode.UpdateImplicitTypeRef>) transformer4, new ResolutionMode.UpdateImplicitTypeRef(firResolvedTypeRefBuilder2.build()));
                                                                    }
                                                                }
                                                            }
                                                            if (z5 == 0 && !z && (backingField = firProperty2.getBackingField()) != null) {
                                                                firDeclarationsResolveTransformer.transformBackingField(backingField, ResolutionModeKt.withExpectedType$default(firProperty2.getReturnTypeRef(), null, null, 6, null), z4);
                                                            }
                                                            context.getContainers().removeLast();
                                                            context.replaceTowerDataContext(firTowerDataContext);
                                                        } catch (Throwable th14) {
                                                            th = th14;
                                                        }
                                                    } catch (Throwable th15) {
                                                        th = th15;
                                                    }
                                                } catch (Throwable th16) {
                                                    th = th16;
                                                    context.replaceTowerDataContext(firTowerDataContext2);
                                                    throw th;
                                                }
                                            } catch (Throwable th17) {
                                                th = th17;
                                                firTowerDataContext2 = towerDataContext2;
                                            }
                                        }
                                        context.setTowerDataMode(firTowerDataMode);
                                        if (!z5 && (controlFlowGraphExitProperty = firDeclarationsResolveTransformer.getComponents().getDataFlowAnalyzer().exitProperty(firProperty2)) != null) {
                                            firProperty2.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitProperty));
                                        }
                                        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firDeclarationsResolveTransformer.getSession()).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue() && !firProperty2.getStatus().isConst() && !(firProperty2.getReturnTypeRef() instanceof FirImplicitTypeRef) && !(firProperty2.getInitializer() instanceof FirAnonymousObjectExpression)) {
                                            firProperty2.replaceInitializer(null);
                                        }
                                        if (implicitTypeOnly2) {
                                            firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
                                            return firProperty2;
                                        }
                                    } catch (Throwable th18) {
                                        th = th18;
                                        context.setTowerDataMode(session);
                                        throw th;
                                    }
                                } catch (Throwable th19) {
                                    th = th19;
                                    if (implicitTypeOnly2) {
                                        firDeclarationsResolveTransformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(true);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th20) {
                                th = th20;
                            }
                        }
                        return firProperty2;
                    }
                    boolean z16 = (firProperty2.getReturnTypeRef() instanceof FirImplicitTypeRef) && (getTransformer().getContext().getContainerIfAny() instanceof FirReplSnippet);
                    prepareSignatureForBodyResolve(property);
                    try {
                        property.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, property, null, null, 3, null)));
                        FirPropertyAccessor getter3 = property.getGetter();
                        if (getter3 != null) {
                            try {
                                getter3.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, getter3, null, property, 1, null)));
                            } catch (Throwable th21) {
                                th = th21;
                                firProperty = property;
                            }
                        }
                        FirPropertyAccessor setter5 = property.getSetter();
                        if (setter5 != null) {
                            setter5.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, setter5, null, property, 1, null)));
                        }
                        FirBackingField backingField8 = property.getBackingField();
                        if (backingField8 != null) {
                            firProperty2 = property;
                            firProperty = firProperty2;
                            backingField8.transformStatus((FirTransformer<? super ResolutionMode>) this, ResolutionModeKt.mode(resolveStatus$default(this, backingField8, null, firProperty2, 1, null)));
                        } else {
                            firProperty = property;
                        }
                        BodyResolveContext context4 = getTransformer().getContext();
                        FirTowerDataMode towerDataMode3 = FirStatusUtilsKt.isCompanionBlockMember(firProperty) ? FirTowerDataMode.COMPANION_BLOCK : null;
                        FirTowerDataMode towerDataMode4 = context4.getTowerDataMode();
                        if (towerDataMode3 == null) {
                            try {
                                towerDataMode3 = context4.getTowerDataMode();
                            } catch (Throwable th22) {
                                context4.setTowerDataMode(towerDataMode4);
                                throw th22;
                            }
                        }
                        context4.setTowerDataMode(towerDataMode3);
                        if (firProperty.getTypeParameters().isEmpty()) {
                            context4.getContainers().add(firProperty);
                            try {
                                doTransformTypeParameters(property);
                                Unit unit5 = Unit.INSTANCE;
                                context4.getContainers().removeLast();
                            } catch (Throwable th23) {
                                context4.getContainers().removeLast();
                                throw th23;
                            }
                        } else {
                            FirMemberTypeParameterScope firMemberTypeParameterScope2 = new FirMemberTypeParameterScope(firProperty);
                            FirTowerDataContext towerDataContext4 = context4.getTowerDataContext();
                            try {
                                context4.addNonLocalTowerDataElement(ImplicitReceiverUtilsKt.asTowerDataElement(firMemberTypeParameterScope2, false));
                                context4.getContainers().add(firProperty);
                                try {
                                    doTransformTypeParameters(property);
                                    Unit unit6 = Unit.INSTANCE;
                                    context4.getContainers().removeLast();
                                    context4.replaceTowerDataContext(towerDataContext4);
                                } catch (Throwable th24) {
                                    context4.getContainers().removeLast();
                                    throw th24;
                                }
                            } catch (Throwable th25) {
                                context4.replaceTowerDataContext(towerDataContext4);
                                throw th25;
                            }
                        }
                        context4.setTowerDataMode(towerDataMode4);
                        FirProperty firPropertyTransformLocalVariable = transformLocalVariable(property);
                        if (z16) {
                            FirResolvedTypeRef returnTypeRef3 = firPropertyTransformLocalVariable.getReturnTypeRef();
                            returnTypeRef3.getClass();
                            firPropertyTransformLocalVariable.replaceReturnTypeRef(DeclarationApproximationUtilsKt.approximateDeclarationType$default(this, returnTypeRef3, visibilityForApproximation(property), false, false, false, false, 56, null));
                        }
                        return firPropertyTransformLocalVariable;
                    } catch (Throwable th26) {
                        th = th26;
                        firProperty = property;
                    }
                } catch (Throwable th27) {
                    th = th27;
                }
            } catch (Throwable th28) {
                th = th28;
            }
        } catch (Throwable th29) {
            th = th29;
            firProperty = firProperty2;
        }
        UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firProperty, th);
        wq6.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirPropertyAccessor transformPropertyAccessor(FirPropertyAccessor propertyAccessor, ResolutionMode data) {
        propertyAccessor.getClass();
        data.getClass();
        transformProperty((FirProperty) propertyAccessor.getPropertySymbol().getFir(), data);
        return propertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReceiverParameter transformReceiverParameter(FirReceiverParameter receiverParameter, ResolutionMode data) {
        receiverParameter.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            BodyResolveContext context = getTransformer().getContext();
            context.getContainers().add(receiverParameter);
            try {
                FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(receiverParameter, data);
                firDeclarationTransformDeclarationContent.getClass();
                return (FirReceiverParameter) firDeclarationTransformDeclarationContent;
            } finally {
                context.getContainers().removeLast();
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(receiverParameter, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirRegularClass transformRegularClass(FirRegularClass regularClass, ResolutionMode data) {
        FirRegularClass firRegularClassDoTransformRegularClassContent;
        regularClass.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            BodyResolveContext context = getTransformer().getContext();
            context.getContainingClassDeclarations().add(regularClass);
            try {
                boolean isLocal = regularClass.getIsLocal();
                if (!isLocal || getTransformer().getContext().getTargetedLocalClasses().contains(regularClass)) {
                    if (isLocal || !getImplicitTypeOnly()) {
                        BodyResolveContext context2 = getTransformer().getContext();
                        FirTowerDataMode towerDataMode = context2.getTowerDataMode();
                        try {
                            if (!regularClass.getStatus().isInner() && (context2.getContainerIfAny() instanceof FirRegularClass)) {
                                context2.setTowerDataMode(regularClass.getStatus().isCompanion() ? FirTowerDataMode.COMPANION_OBJECT : FirTowerDataMode.NESTED_CLASS);
                            }
                            context2.getContainers().add(regularClass);
                            try {
                                ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
                                regularClass.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                                regularClass.transformTypeParameters((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                                regularClass.transformSuperTypeRefs((FirTransformer<? super ResolutionMode.ContextIndependent>) this, contextIndependent);
                                Unit unit = Unit.INSTANCE;
                                context2.getContainers().removeLast();
                                context2.setTowerDataMode(towerDataMode);
                            } catch (Throwable th) {
                                context2.getContainers().removeLast();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            context2.setTowerDataMode(towerDataMode);
                            throw th2;
                        }
                    }
                    firRegularClassDoTransformRegularClassContent = doTransformRegularClassContent(regularClass, data);
                } else {
                    firRegularClassDoTransformRegularClassContent = (FirRegularClass) LocalClassesResolutionKt.runAllPhasesForLocalClassLikeDeclarations(regularClass, getTransformer().getComponents(), data);
                }
                context.getContainingClassDeclarations().removeLast();
                return firRegularClassDoTransformRegularClassContent;
            } catch (Throwable th3) {
                context.getContainingClassDeclarations().removeLast();
                throw th3;
            }
        } catch (Throwable th4) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(regularClass, th4);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(final FirReplSnippet replSnippet, final ResolutionMode data) {
        replSnippet.getClass();
        data.getClass();
        getTransformer().getContext().withReplSnippet(replSnippet, getTransformer().getComponents(), new Function0() { // from class: z15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.i(replSnippet, this, data);
            }
        });
        return replSnippet;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirScript transformScript(final FirScript script, final ResolutionMode data) {
        script.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            return withScript(script, new Function0() { // from class: d25
                public final Object invoke() {
                    return FirDeclarationsResolveTransformer.transformScript$lambda$0$0(this.b, script, data);
                }
            });
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(script, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeAlias transformTypeAlias(FirTypeAlias typeAlias, ResolutionMode data) {
        typeAlias.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (getImplicitTypeOnly()) {
                return typeAlias;
            }
            if (typeAlias.getIsLocal() && !getTransformer().getContext().getTargetedLocalClasses().contains(typeAlias)) {
                return (FirTypeAlias) LocalClassesResolutionKt.runAllPhasesForLocalClassLikeDeclarations(typeAlias, getTransformer().getComponents(), data);
            }
            BodyResolveContext context = getTransformer().getContext();
            boolean z = context.getContainerIfAny() instanceof FirRegularClass;
            context.getContainers().add(typeAlias);
            try {
                if (z) {
                    FirTowerDataMode towerDataMode = context.getTowerDataMode();
                    try {
                        if (!typeAlias.getStatus().isInner()) {
                            context.setTowerDataMode(FirTowerDataMode.NESTED_CLASS);
                        }
                        doTransformTypeParameters(typeAlias);
                        typeAlias.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                        typeAlias.transformExpandedTypeRef(getTransformer(), data);
                        context.setTowerDataMode(towerDataMode);
                    } catch (Throwable th) {
                        context.setTowerDataMode(towerDataMode);
                        throw th;
                    }
                } else {
                    doTransformTypeParameters(typeAlias);
                    typeAlias.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                    typeAlias.transformExpandedTypeRef(getTransformer(), data);
                }
                context.getContainers().removeLast();
                return typeAlias;
            } catch (Throwable th2) {
                context.getContainers().removeLast();
                throw th2;
            }
        } catch (Throwable th3) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeAlias, th3);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirValueParameter transformValueParameter(FirValueParameter valueParameter, ResolutionMode data) {
        ConeKotlinType resolvedReturnType;
        FirClassSymbol<?> classSymbol;
        valueParameter.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            getComponents().getDataFlowAnalyzer().enterValueParameter(valueParameter);
            FirBasedSymbol<?> containingDeclarationSymbol = valueParameter.getContainingDeclarationSymbol();
            FirConstructorSymbol firConstructorSymbol = containingDeclarationSymbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) containingDeclarationSymbol : null;
            boolean z = ((firConstructorSymbol == null || (resolvedReturnType = firConstructorSymbol.getResolvedReturnType()) == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(this, resolvedReturnType)) == null) ? null : classSymbol.getClassKind()) == ClassKind.ANNOTATION_CLASS;
            BodyResolveContext context = getTransformer().getContext();
            context.storeValueParameterIfNeeded(valueParameter, getSession());
            context.getContainers().add(valueParameter);
            try {
                FirDeclaration firDeclarationTransformDeclarationContent = transformDeclarationContent(valueParameter, ResolutionModeKt.withExpectedType$default(valueParameter.getReturnTypeRef(), z ? ResolutionMode.ArrayLiteralPosition.AnnotationParameter : null, null, 4, null));
                firDeclarationTransformDeclarationContent.getClass();
                FirValueParameter firValueParameter = (FirValueParameter) firDeclarationTransformDeclarationContent;
                context.getContainers().removeLast();
                ControlFlowGraph controlFlowGraphExitValueParameter = getComponents().getDataFlowAnalyzer().exitValueParameter(firValueParameter);
                if (controlFlowGraphExitValueParameter != null) {
                    firValueParameter.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitValueParameter));
                }
                if (DeclarationUtilsKt.isAnnotationConstructor(firValueParameter.getContainingDeclarationSymbol(), getSession())) {
                    DeclarationAttributesKt.setEvaluatedInitializer(firValueParameter, FirExpressionEvaluator.INSTANCE.evaluateParameterDefaultValue(firValueParameter, getSession(), getComponents().getFile()));
                }
                return firValueParameter;
            } catch (Throwable th) {
                context.getContainers().removeLast();
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(valueParameter, th2);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public FirFile withFile(FirFile file, Function0<? extends FirFile> action) throws UninitializedPropertyAccessException {
        file.getClass();
        action.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
        context.clear();
        context.setFile(file);
        List<FirScope> fileImportsScope = context.getFileImportsScope();
        int size = fileImportsScope.size();
        int i = 0;
        try {
            FirTowerDataContext towerDataContext = context.getTowerDataContext();
            try {
                List listCreateImportingScopes$default = ImportingScopesKt.createImportingScopes$default(file, components.getSession(), components.getScopeSession(), false, 8, null);
                CollectionsKt.addAll(context.getFileImportsScope(), listCreateImportingScopes$default);
                List list = listCreateImportingScopes$default;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
                }
                context.addNonLocalTowerDataElements(arrayList);
                context.getContainers().add(file);
                try {
                    getComponents().getDataFlowAnalyzer().enterFile(file, getTransformer().getBuildCfgForFiles());
                    FirFile firFile = (FirFile) action.invoke();
                    context.getContainers().removeLast();
                    context.replaceTowerDataContext(towerDataContext);
                    int size2 = fileImportsScope.size() - size;
                    while (i < size2) {
                        fileImportsScope.remove(fileImportsScope.size() - 1);
                        i++;
                    }
                    ControlFlowGraph controlFlowGraphExitFile = getComponents().getDataFlowAnalyzer().exitFile();
                    if (controlFlowGraphExitFile != null) {
                        firFile.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitFile));
                    }
                    return firFile;
                } catch (Throwable th) {
                    context.getContainers().removeLast();
                    throw th;
                }
            } catch (Throwable th2) {
                context.replaceTowerDataContext(towerDataContext);
                throw th2;
            }
        } catch (Throwable th3) {
            int size3 = fileImportsScope.size() - size;
            while (i < size3) {
                fileImportsScope.remove(fileImportsScope.size() - 1);
                i++;
            }
            throw th3;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public FirScript withScript(final FirScript script, final Function0<? extends FirScript> action) throws UninitializedPropertyAccessException, KotlinIllegalArgumentExceptionWithAttachments {
        script.getClass();
        action.getClass();
        FirScript firScript = (FirScript) getTransformer().getContext().withScript(script, getTransformer().getComponents(), new Function0() { // from class: s15
            public final Object invoke() {
                return FirDeclarationsResolveTransformer.n(this.b, script, action);
            }
        });
        ControlFlowGraph controlFlowGraphExitScript = getComponents().getDataFlowAnalyzer().exitScript();
        if (controlFlowGraphExitScript != null) {
            firScript.replaceControlFlowGraphReference(new FirControlFlowGraphReferenceImpl(controlFlowGraphExitScript));
        }
        return firScript;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirBackingField transformBackingField(FirBackingField backingField, ResolutionMode data) {
        backingField.getClass();
        data.getClass();
        return transformBackingField(backingField, data, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final void replacePropertyReferenceTypeInDelegateAccessors(FirProperty property) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirBlock body;
        List<FirStatement> statements;
        FirBlock body2;
        List<FirStatement> statements2;
        FirPropertyAccessor getter = property.getGetter();
        FirStatement firStatement = (getter == null || (body2 = getter.getBody()) == null || (statements2 = body2.getStatements()) == null) ? null : (FirStatement) CollectionsKt.singleOrNull(statements2);
        FirReturnExpression firReturnExpression = firStatement instanceof FirReturnExpression ? (FirReturnExpression) firStatement : null;
        if (firReturnExpression != null) {
            FirExpression result = firReturnExpression.getResult();
            FirFunctionCall firFunctionCall = result instanceof FirFunctionCall ? (FirFunctionCall) result : null;
            if (firFunctionCall != null) {
                replacePropertyReferenceTypeInDelegateAccessors(firFunctionCall, property);
            }
        }
        FirPropertyAccessor setter = property.getSetter();
        FirStatement firStatement2 = (setter == null || (body = setter.getBody()) == null || (statements = body.getStatements()) == null) ? null : (FirStatement) CollectionsKt.singleOrNull(statements);
        FirReturnExpression firReturnExpression2 = firStatement2 instanceof FirReturnExpression ? (FirReturnExpression) firStatement2 : null;
        if (firReturnExpression2 != null) {
            FirExpression result2 = firReturnExpression2.getResult();
            FirFunctionCall firFunctionCall2 = result2 instanceof FirFunctionCall ? (FirFunctionCall) result2 : null;
            if (firFunctionCall2 != null) {
                replacePropertyReferenceTypeInDelegateAccessors(firFunctionCall2, property);
            }
        }
        FirExpression delegate = property.getDelegate();
        FirExpression firExpressionUnwrapReplExpressionRef = delegate != null ? FirExpressionUtilKt.unwrapReplExpressionRef(delegate) : null;
        if (firExpressionUnwrapReplExpressionRef instanceof FirFunctionCall) {
            FirFunctionCall firFunctionCall3 = (FirFunctionCall) firExpressionUnwrapReplExpressionRef;
            if (Intrinsics.areEqual(firFunctionCall3.getCalleeReference().getName(), OperatorNameConventions.PROVIDE_DELEGATE)) {
                KtSourceElement source = firFunctionCall3.getSource();
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
                    replacePropertyReferenceTypeInDelegateAccessors(firFunctionCall3, property);
                }
            }
        }
    }
}
