package org.jetbrains.kotlin.fir.resolve.transformers;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirPureAbstractElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionConversionKind;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionTypeConversionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSpreadArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedCallableReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceAdaptation;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.resolve.calls.NotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgumentKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.TypeArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FirAnonymousFunctionReturnExpressionInfo;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeConstraintSystemHasContradiction;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterInQualifiedAccess;
import org.jetbrains.kotlin.fir.resolve.inference.FirTypeVariablesAfterPCLATransformer;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeTypeSubstitutorByTypeConstructorKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirArrayOfCallTransformer;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.ConvertibleIntegerOperators;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassSubstitutionScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorTypeImpl;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralTypeImplKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeApproximator;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.RefinedTypeForDataFlowTypeAttribute;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirStarProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.resolve.calls.NewCommonSuperTypeCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.InferredEmptyIntersection;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.TypeSystemContextContextualKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003:\u0006Ë\u0001Ì\u0001Í\u0001Bc\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u001c\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020\tH\u0002J\u0010\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\"\u0010(\u001a\u0002H)\"\u0004\b\u0000\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+H\u0082\b¢\u0006\u0002\u0010,J\"\u0010-\u001a\u0002H)\"\u0004\b\u0000\u0010)2\f\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0+H\u0082\b¢\u0006\u0002\u0010,J'\u0010.\u001a\u0002H)\"\b\b\u0000\u0010)*\u00020/2\u0006\u00100\u001a\u0002H)2\u0006\u00101\u001a\u000202H\u0002¢\u0006\u0002\u00103J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0002J\f\u00108\u001a\u000205*\u000207H\u0002J\u001e\u00108\u001a\b\u0012\u0002\b\u0003\u0018\u000109*\u0006\u0012\u0002\b\u0003092\u0006\u0010:\u001a\u00020\u0019H\u0002J<\u0010;\u001a\u0006\u0012\u0002\b\u0003092\n\u0010<\u001a\u0006\u0012\u0002\b\u0003092\"\u0010=\u001a\u001e\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030?\u0012\u0004\u0012\u0002050>\u0012\u0004\u0012\u0002050>H\u0002J\u001a\u0010@\u001a\u00020A2\u0006\u00100\u001a\u00020/2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020E2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010F\u001a\u00020A2\u0006\u0010G\u001a\u00020H2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0002J\u001a\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020H2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010K\u001a\u00020A2\u0006\u0010L\u001a\u00020M2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u0014\u0010N\u001a\u000205*\u00020M2\u0006\u0010O\u001a\u000207H\u0002JD\u0010P\u001a\b\u0012\u0004\u0012\u00020R0Q*\u0002022\u0006\u0010S\u001a\u00020T2(\b\u0002\u0010U\u001a\"\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020W\u0018\u00010Vj\u0010\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020W\u0018\u0001`XH\u0002J\u000e\u0010Y\u001a\u0004\u0018\u00010\t*\u000207H\u0002J\u0010\u0010Z\u001a\u00020\u0019*\u0006\u0012\u0002\b\u000309H\u0002J\u0010\u0010[\u001a\u00020\u0019*\u0006\u0012\u0002\b\u000309H\u0002J\u0016\u0010\\\u001a\u000205*\u00020]2\b\u0010^\u001a\u0004\u0018\u00010_H\u0002J$\u0010`\u001a\u00020a*\u00020R2\u0006\u0010b\u001a\u00020!2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0002J\u001a\u0010g\u001a\u00020A2\u0006\u0010h\u001a\u00020i2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010j\u001a\u00020A2\u0006\u0010k\u001a\u00020l2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016JD\u0010m\u001a\u00020n*\u0002072\f\u0010o\u001a\b\u0012\u0004\u0012\u00020R0Q2(\b\u0002\u0010p\u001a\"\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020W\u0018\u00010Vj\u0010\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020W\u0018\u0001`XH\u0002J+\u0010q\u001a\u0002Hr\"\b\b\u0000\u0010r*\u00020R*\u0002Hr2\u0006\u00101\u001a\u0002022\u0006\u0010s\u001a\u00020tH\u0002¢\u0006\u0002\u0010uJ\u001e\u0010v\u001a\u00020!*\u00020!2\u0006\u00106\u001a\u0002072\b\b\u0002\u0010#\u001a\u00020\tH\u0002J\f\u0010w\u001a\u00020!*\u00020!H\u0002J\u001a\u0010x\u001a\u00020A2\u0006\u0010y\u001a\u00020z2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010{\u001a\u00020A2\u0006\u0010|\u001a\u00020}2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001b\u0010~\u001a\u00020A2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u0016\u0010\u0081\u0001\u001a\u00020!*\u00030\u0082\u00012\u0006\u00106\u001a\u000207H\u0002J\u0015\u0010\u0081\u0001\u001a\u00020!*\u00020!2\u0006\u00106\u001a\u000207H\u0002J\u0018\u0010\u0083\u0001\u001a\u0004\u0018\u00010_*\u0002072\u0007\u0010\u0084\u0001\u001a\u00020\u0019H\u0002J\u001d\u0010\u0085\u0001\u001a\u00020A2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J!\u0010\u0088\u0001\u001a\t\u0012\u0005\u0012\u00030\u0089\u00010Q2\u0007\u0010\u008a\u0001\u001a\u00020/2\u0006\u00106\u001a\u000207H\u0002J\u0019\u0010\u008b\u0001\u001a\u00020!*\u00020!2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u0089\u0001H\u0002J\u0017\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020!0Q2\u0006\u00106\u001a\u000207H\u0002J\u001d\u0010\u008e\u0001\u001a\u00020A2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u0091\u0001\u001a\u00020A2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J'\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u0001*\n\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u0001*\u00020!H\u0002J\u001d\u0010\u0099\u0001\u001a\u00020A2\b\u0010\u009a\u0001\u001a\u00030\u009b\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001c\u0010\u009c\u0001\u001a\u00020A2\u0007\u0010*\u001a\u00030\u009d\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u009e\u0001\u001a\u00020A2\b\u0010\u009f\u0001\u001a\u00030 \u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¡\u0001\u001a\u00020A2\b\u0010¢\u0001\u001a\u00030£\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¤\u0001\u001a\u00020A2\b\u0010¥\u0001\u001a\u00030¦\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016JW\u0010§\u0001\u001a\u0002Hr\"\u000f\b\u0000\u0010r\u0018\u0001*\u00030¨\u0001*\u00020R2\u0007\u0010©\u0001\u001a\u0002Hr2\b\u0010B\u001a\u0004\u0018\u00010\u00022!\u0010ª\u0001\u001a\u001c\u0012\u0004\u0012\u0002Hr\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010Q0>¢\u0006\u0003\b«\u0001H\u0082\b¢\u0006\u0003\u0010¬\u0001J\u000e\u0010\u00ad\u0001\u001a\u00020\u0019*\u00030¨\u0001H\u0002J\u001c\u0010®\u0001\u001a\u000205*\u00020R2\r\u0010¯\u0001\u001a\b\u0012\u0004\u0012\u00020!0QH\u0002J\u001d\u0010°\u0001\u001a\u00020A2\b\u0010±\u0001\u001a\u00030²\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010³\u0001\u001a\u00020A2\b\u0010´\u0001\u001a\u00030µ\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J4\u0010¶\u0001\u001a\u0002Hr\"\u000f\b\u0000\u0010r\u0018\u0001*\u00030¨\u0001*\u00020R2\u0007\u0010©\u0001\u001a\u0002Hr2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0082\b¢\u0006\u0003\u0010·\u0001J4\u0010¸\u0001\u001a\u000205\"\u000f\b\u0000\u0010r\u0018\u0001*\u00030¨\u0001*\u00020R2\u0007\u0010©\u0001\u001a\u0002Hr2\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0082\b¢\u0006\u0003\u0010¹\u0001J\u001d\u0010º\u0001\u001a\u00020A2\b\u0010»\u0001\u001a\u00030¼\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010½\u0001\u001a\u00020A2\b\u0010¾\u0001\u001a\u00030¿\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010À\u0001\u001a\u00020A2\b\u0010Á\u0001\u001a\u00030Â\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\r\u0010Ã\u0001\u001a\u00020\u0019*\u000202H\u0002J\u000e\u0010Ä\u0001\u001a\u00030Å\u0001*\u000202H\u0002J0\u0010Æ\u0001\u001a\u0003HÇ\u0001\"\n\b\u0000\u0010Ç\u0001*\u00030È\u00012\b\u0010É\u0001\u001a\u0003HÇ\u00012\b\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0003\u0010Ê\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Î\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "finalSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "typeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "typeApproximator", "Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "dataFlowAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "integerOperatorApproximator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;", "samResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "mode", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$Mode;", "insideAnnotationContext", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;Lorg/jetbrains/kotlin/fir/resolve/transformers/IntegerLiteralAndOperatorApproximationTransformer;Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$Mode;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "finallySubstituteOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "substitutor", "finallySubstituteOrSelf", "arrayOfCallTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirArrayOfCallTransformer;", "enableArrayOfCallTransformation", "withCollectionLiteralInAnnotationResolution", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withFirArrayOfCallTransformer", "prepareQualifiedTransform", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "qualifiedAccessExpression", "calleeReference", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "runPCLARelatedTasksForCandidate", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "updateSubstitutedMemberIfReceiverContainsTypeVariable", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "usedOuterCs", "findSingleSubstitutedSymbolWithOriginal", "original", "processCallables", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "transformQualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "data", "transformPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "transformArrayLiteralInAnnotation", "arrayLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "transformCollectionLiteral", "collectionLiteral", "transformFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "updateExplicitContextArgumentsFromArgumentList", "subCandidate", "computeAllArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "originalArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "predefinedMapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "prepareCustomReturnTypeSubstitutorForFunctionCall", "isJavaConstructor", "isSyntheticSamConstructor", "transformArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "expectedArgumentsTypeMapping", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ArgumentsMap;", "wrapInFunctionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "expectedArgumentType", "kind", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind;", "newSourceKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "transformAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "transformErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "handleVarargsAndReturnResultingArgumentsMapping", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$ResultingArgumentsMapping;", "argumentList", "precomputedArgumentMapping", "replaceTypeWithSubstituted", "D", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "substituteType", "removeExactAttribute", "transformSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "transformCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "transformSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "substitute", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "createArgumentsMapping", "forErrorReference", "transformDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "computeTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "access", "storeNonFlexibleCounterpartInAttributeIfNecessary", "argument", "computeTypeArgumentTypes", "transformAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "transformAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "replacePostponedAtomsInReturnExpressions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;", "functionTypeKindForDeserializedConeType", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "transformReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "transformBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "transformTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "transformCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "transformSyntheticCallWithDataFlowTypeRefining", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "syntheticCall", "computeBranchTypes", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "wasExpectedTypeAddedAsEqualityForSyntheticCall", "addRefinedTypeForDataFlow", "branchTypes", "transformElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "transformEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "transformSyntheticCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "transformSyntheticCallChildren", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;)V", "transformLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "transformIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "transformVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "hasAdditionalResolutionErrors", "toResolvedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;)Lorg/jetbrains/kotlin/fir/FirElement;", "Mode", "ResultingArgumentsMapping", "TypeUpdaterForPCLAAndDelegateReceivers", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallCompletionResultsWriterTransformer extends FirAbstractTreeTransformer<ExpectedArgumentType> implements SessionAndScopeSessionHolder {
    private final FirArrayOfCallTransformer arrayOfCallTransformer;
    private final BodyResolveContext context;
    private final FirDataFlowAnalyzer dataFlowAnalyzer;
    private boolean enableArrayOfCallTransformation;
    private final ConeSubstitutor finalSubstitutor;
    private boolean insideAnnotationContext;
    private final IntegerLiteralAndOperatorApproximationTransformer integerOperatorApproximator;
    private final Mode mode;
    private final FirSamResolver samResolver;
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final ConeTypeApproximator typeApproximator;
    private final ReturnTypeCalculator typeCalculator;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$Mode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Normal", "DelegatedPropertyCompletion", "TopLevelSyntheticCallInPclaCompletion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Mode {
        Normal,
        DelegatedPropertyCompletion,
        TopLevelSyntheticCallInPclaCompletion;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Mode> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BS\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006\u0012&\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006HÆ\u0003J)\u0010\u000e\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006HÆ\u0003JY\u0010\u000f\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u00062(\b\u0002\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR1\u0010\u0007\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$ResultingArgumentsMapping;", Argument.Delimiters.none, "regularMapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "allArgsMapping", "<init>", "(Ljava/util/LinkedHashMap;Ljava/util/LinkedHashMap;)V", "getRegularMapping", "()Ljava/util/LinkedHashMap;", "getAllArgsMapping", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ResultingArgumentsMapping {
        private final LinkedHashMap<FirExpression, FirValueParameter> allArgsMapping;
        private final LinkedHashMap<FirExpression, FirValueParameter> regularMapping;

        public ResultingArgumentsMapping(LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap2) {
            linkedHashMap.getClass();
            linkedHashMap2.getClass();
            this.regularMapping = linkedHashMap;
            this.allArgsMapping = linkedHashMap2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ResultingArgumentsMapping copy$default(ResultingArgumentsMapping resultingArgumentsMapping, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, int i, Object obj) {
            if ((i & 1) != 0) {
                linkedHashMap = resultingArgumentsMapping.regularMapping;
            }
            if ((i & 2) != 0) {
                linkedHashMap2 = resultingArgumentsMapping.allArgsMapping;
            }
            return resultingArgumentsMapping.copy(linkedHashMap, linkedHashMap2);
        }

        public final LinkedHashMap<FirExpression, FirValueParameter> component1() {
            return this.regularMapping;
        }

        public final LinkedHashMap<FirExpression, FirValueParameter> component2() {
            return this.allArgsMapping;
        }

        public final ResultingArgumentsMapping copy(LinkedHashMap<FirExpression, FirValueParameter> regularMapping, LinkedHashMap<FirExpression, FirValueParameter> allArgsMapping) {
            regularMapping.getClass();
            allArgsMapping.getClass();
            return new ResultingArgumentsMapping(regularMapping, allArgsMapping);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResultingArgumentsMapping)) {
                return false;
            }
            ResultingArgumentsMapping resultingArgumentsMapping = (ResultingArgumentsMapping) other;
            return Intrinsics.areEqual(this.regularMapping, resultingArgumentsMapping.regularMapping) && Intrinsics.areEqual(this.allArgsMapping, resultingArgumentsMapping.allArgsMapping);
        }

        public final LinkedHashMap<FirExpression, FirValueParameter> getAllArgsMapping() {
            return this.allArgsMapping;
        }

        public final LinkedHashMap<FirExpression, FirValueParameter> getRegularMapping() {
            return this.regularMapping;
        }

        public int hashCode() {
            return (this.regularMapping.hashCode() * 31) + this.allArgsMapping.hashCode();
        }

        public String toString() {
            return "ResultingArgumentsMapping(regularMapping=" + this.regularMapping + ", allArgsMapping=" + this.allArgsMapping + ')';
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$TypeUpdaterForPCLAAndDelegateReceivers;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformThisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "transformQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "transformPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "transformTypeRefForQualifiedAccess", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class TypeUpdaterForPCLAAndDelegateReceivers extends FirTransformer<Object> {
        public TypeUpdaterForPCLAAndDelegateReceivers() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        private final FirQualifiedAccessExpression transformTypeRefForQualifiedAccess(FirQualifiedAccessExpression qualifiedAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
            ConeKotlinType coneKotlinTypeFinallySubstituteOrNull$default = FirCallCompletionResultsWriterTransformer.finallySubstituteOrNull$default(FirCallCompletionResultsWriterTransformer.this, FirTypeUtilsKt.getResolvedType(qualifiedAccessExpression), null, 2, null);
            if (coneKotlinTypeFinallySubstituteOrNull$default == null) {
                return qualifiedAccessExpression;
            }
            qualifiedAccessExpression.replaceConeTypeOrNull(coneKotlinTypeFinallySubstituteOrNull$default);
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(FirCallCompletionResultsWriterTransformer.this.getSession());
            if (lookupTracker != null) {
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeFinallySubstituteOrNull$default, qualifiedAccessExpression.getSource(), FirCallCompletionResultsWriterTransformer.this.context.getFile().getSource());
            }
            return qualifiedAccessExpression;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public <E extends FirElement> E transformElement(E element, Object data) {
            element.getClass();
            return element;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, Object data) {
            propertyAccessExpression.getClass();
            return transformQualifiedAccessExpression(propertyAccessExpression, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Object data) {
            qualifiedAccessExpression.getClass();
            return transformTypeRefForQualifiedAccess(qualifiedAccessExpression);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, Object data) {
            thisReceiverExpression.getClass();
            return transformTypeRefForQualifiedAccess(thisReceiverExpression);
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u000f"}, d2 = {"org/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer$transformArgumentList$ArgumentTransformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCallCompletionResultsWriterTransformer;Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ArgumentsMap;Ljava/util/LinkedHashMap;)V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformNamedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ArgumentTransformer extends FirTransformer {
        final /* synthetic */ ExpectedArgumentType.ArgumentsMap $expectedArgumentsTypeMapping;
        final /* synthetic */ LinkedHashMap<FirExpression, FirValueParameter> $mapping;

        public ArgumentTransformer(ExpectedArgumentType.ArgumentsMap argumentsMap, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
            this.$expectedArgumentsTypeMapping = argumentsMap;
            this.$mapping = linkedHashMap;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public <E extends FirElement> E transformElement(E element, Void data) {
            Map<FirElement, FirSamResolver.SamConversionInfo> samConversions;
            FirSamResolver.SamConversionInfo samConversionInfo;
            Candidate.FunctionConversionDescription functionConversionDescription;
            FirAnonymousFunction anonymousFunction;
            element.getClass();
            if ((element instanceof FirWrappedArgumentExpression) || (element instanceof FirVarargArgumentsExpression)) {
                E e = (E) ((FirExpression) element).transformChildren(this, null);
                e.getClass();
                return e;
            }
            FirFunctionTypeConversionExpression firFunctionTypeConversionExpressionTransformSingle = FirTransformerUtilKt.transformSingle(element, FirCallCompletionResultsWriterTransformer.this, this.$expectedArgumentsTypeMapping);
            FirAnonymousFunctionExpression firAnonymousFunctionExpression = element instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) element : null;
            if (firAnonymousFunctionExpression != null && (anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction()) != null) {
                element = anonymousFunction;
            }
            ExpectedArgumentType.ArgumentsMap argumentsMap = this.$expectedArgumentsTypeMapping;
            if (argumentsMap != null && (functionConversionDescription = argumentsMap.getArgumentsWithFunctionKindConversion().get(element)) != null) {
                FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer = FirCallCompletionResultsWriterTransformer.this;
                if (!(firFunctionTypeConversionExpressionTransformSingle instanceof FirExpression)) {
                    k2d.a("Function kind conversion should be applied to expressions only");
                    return null;
                }
                firFunctionTypeConversionExpressionTransformSingle = firCallCompletionResultsWriterTransformer.wrapInFunctionTypeConversionExpression((FirExpression) firFunctionTypeConversionExpressionTransformSingle, functionConversionDescription.getExpectedType(), functionConversionDescription.toKind(), KtFakeSourceElementKind.FunctionTypeConversion.INSTANCE);
            }
            ExpectedArgumentType.ArgumentsMap argumentsMap2 = this.$expectedArgumentsTypeMapping;
            if (argumentsMap2 != null && (samConversions = argumentsMap2.getSamConversions()) != null && (samConversionInfo = samConversions.get(element)) != null) {
                FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer2 = FirCallCompletionResultsWriterTransformer.this;
                if (!(firFunctionTypeConversionExpressionTransformSingle instanceof FirExpression)) {
                    k2d.a("SAM conversion should be applied to expressions only");
                    return null;
                }
                firFunctionTypeConversionExpressionTransformSingle = firCallCompletionResultsWriterTransformer2.wrapInFunctionTypeConversionExpression((FirExpression) firFunctionTypeConversionExpressionTransformSingle, samConversionInfo.getSamType(), FirFunctionConversionKind.Sam.INSTANCE, KtFakeSourceElementKind.SamConversion.INSTANCE);
            }
            firFunctionTypeConversionExpressionTransformSingle.getClass();
            return (E) firFunctionTypeConversionExpressionTransformSingle;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, Void data) {
            namedArgumentExpression.getClass();
            FirExpression firExpression = (FirExpression) transformElement(namedArgumentExpression.getExpression(), data);
            LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap = this.$mapping;
            FirValueParameter firValueParameter = linkedHashMap != null ? linkedHashMap.get(namedArgumentExpression) : null;
            if (!namedArgumentExpression.getIsSpread() && (firValueParameter == null || !firValueParameter.getIsVararg())) {
                return firExpression;
            }
            FirSpreadArgumentExpressionBuilder firSpreadArgumentExpressionBuilder = new FirSpreadArgumentExpressionBuilder();
            firSpreadArgumentExpressionBuilder.setSource(namedArgumentExpression.getSource());
            firSpreadArgumentExpressionBuilder.setExpression(firExpression);
            firSpreadArgumentExpressionBuilder.setNamed(true);
            firSpreadArgumentExpressionBuilder.setFakeSpread(!namedArgumentExpression.getIsSpread());
            return firSpreadArgumentExpressionBuilder.mo288build();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCallCompletionResultsWriterTransformer(FirSession firSession, ScopeSession scopeSession, ConeSubstitutor coneSubstitutor, ReturnTypeCalculator returnTypeCalculator, ConeTypeApproximator coneTypeApproximator, FirDataFlowAnalyzer firDataFlowAnalyzer, IntegerLiteralAndOperatorApproximationTransformer integerLiteralAndOperatorApproximationTransformer, FirSamResolver firSamResolver, BodyResolveContext bodyResolveContext, Mode mode, boolean z) {
        super(FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE);
        firSession.getClass();
        scopeSession.getClass();
        coneSubstitutor.getClass();
        returnTypeCalculator.getClass();
        coneTypeApproximator.getClass();
        firDataFlowAnalyzer.getClass();
        integerLiteralAndOperatorApproximationTransformer.getClass();
        firSamResolver.getClass();
        bodyResolveContext.getClass();
        mode.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.finalSubstitutor = coneSubstitutor;
        this.typeCalculator = returnTypeCalculator;
        this.typeApproximator = coneTypeApproximator;
        this.dataFlowAnalyzer = firDataFlowAnalyzer;
        this.integerOperatorApproximator = integerLiteralAndOperatorApproximationTransformer;
        this.samResolver = firSamResolver;
        this.context = bodyResolveContext;
        this.mode = mode;
        this.insideAnnotationContext = z;
        this.arrayOfCallTransformer = new FirArrayOfCallTransformer();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void addRefinedTypeForDataFlow(FirExpression firExpression, List<? extends ConeKotlinType> list) throws KotlinIllegalArgumentExceptionWithAttachments {
        final ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        if (TypeUtilsKt.isUnitOrFlexibleUnit(resolvedType)) {
            return;
        }
        List<? extends ConeKotlinType> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (ConeTypeUtilsKt.contains((ConeKotlinType) it.next(), new Function1() { // from class: wy4
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(FirCallCompletionResultsWriterTransformer.addRefinedTypeForDataFlow$lambda$0$0$0(typeContext, (ConeKotlinType) obj));
                    }
                })) {
                    return;
                }
            }
        }
        ConeKotlinType coneKotlinTypeCommonSuperType = NewCommonSuperTypeCalculator.INSTANCE.commonSuperType(typeContext, list);
        coneKotlinTypeCommonSuperType.getClass();
        ConeKotlinType coneKotlinType = coneKotlinTypeCommonSuperType;
        if (TypeUtilsKt.isUnitOrFlexibleUnit(coneKotlinType) || Intrinsics.areEqual(resolvedType, coneKotlinType) || !AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(getSession()), coneKotlinType, resolvedType, false, 8, (Object) null)) {
            return;
        }
        firExpression.replaceConeTypeOrNull(TypeUtilsKt.withAttributes(resolvedType, resolvedType.getAttributes().add(new RefinedTypeForDataFlowTypeAttribute(coneKotlinType))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addRefinedTypeForDataFlow$lambda$0$0$0(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return TypeSystemContextContextualKt.isError(coneInferenceContext, coneKotlinType);
    }

    public static Unit b(FirCallableDeclaration firCallableDeclaration, FirClassSubstitutionScope firClassSubstitutionScope, Function1 function1) {
        function1.getClass();
        if (firCallableDeclaration instanceof FirNamedFunction) {
            firClassSubstitutionScope.processFunctionsByName(((FirNamedFunction) firCallableDeclaration).getName(), function1);
        } else if (firCallableDeclaration instanceof FirProperty) {
            firClassSubstitutionScope.processPropertiesByName(((FirProperty) firCallableDeclaration).getName(), function1);
        } else {
            if (!(firCallableDeclaration instanceof FirConstructor)) {
                f2f.a("Unexpected declaration kind ", UtilsKt.render(firCallableDeclaration));
                return null;
            }
            firClassSubstitutionScope.processDeclaredConstructors(function1);
        }
        return Unit.INSTANCE;
    }

    private final List<FirExpression> computeAllArguments(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirArgumentList firArgumentList, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
        if (firNamedReferenceWithCandidate.isError()) {
            return firArgumentList.getArguments();
        }
        if (linkedHashMap != null) {
            Set<FirExpression> setKeySet = linkedHashMap.keySet();
            setKeySet.getClass();
            return CollectionsKt.toList(setKeySet);
        }
        Set<ConeResolutionAtom> setKeySet2 = firNamedReferenceWithCandidate.getCandidate().getArgumentMapping().keySet();
        setKeySet2.getClass();
        return FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List computeAllArguments$default(FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirArgumentList firArgumentList, LinkedHashMap linkedHashMap, int i, Object obj) {
        if ((i & 2) != 0) {
            linkedHashMap = null;
        }
        return firCallCompletionResultsWriterTransformer.computeAllArguments(firNamedReferenceWithCandidate, firArgumentList, linkedHashMap);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final List<ConeKotlinType> computeTypeArgumentTypes(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir = candidate.getSymbol().getFir();
        FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
        if (firCallableDeclaration == null) {
            return CollectionsKt.emptyList();
        }
        List<FirTypeParameterRef> typeParameters = firCallableDeclaration.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            ConeKotlinType coneKotlinTypeFinallySubstituteOrSelf = finallySubstituteOrSelf(candidate.getSubstitutor().substituteOrSelf(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), false, null, 4, null)));
            ConeKotlinType coneKotlinTypeApproximateToSuperType = this.typeApproximator.approximateToSuperType(coneKotlinTypeFinallySubstituteOrSelf, TypeApproximatorConfiguration.TypeArgumentApproximationAfterCompletionInK2.INSTANCE);
            if (coneKotlinTypeApproximateToSuperType != null) {
                coneKotlinTypeFinallySubstituteOrSelf = coneKotlinTypeApproximateToSuperType;
            }
            arrayList.add(coneKotlinTypeFinallySubstituteOrSelf);
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final List<FirTypeProjection> computeTypeArguments(FirQualifiedAccessExpression access, Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtSourceElement ktSourceElementFakeElement$default;
        FirTypeProjection firTypeProjectionBuild;
        List<ConeKotlinType> listComputeTypeArgumentTypes = computeTypeArgumentTypes(candidate);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeTypeArgumentTypes, 10));
        int i = 0;
        for (Object obj : listComputeTypeArgumentTypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirTypeProjection firTypeProjection = (FirTypeProjection) CollectionsKt.getOrNull(access.getTypeArguments(), i);
            ConeKotlinType coneKotlinTypeStoreNonFlexibleCounterpartInAttributeIfNecessary = storeNonFlexibleCounterpartInAttributeIfNecessary((ConeKotlinType) obj, firTypeProjection);
            if (firTypeProjection == null || (ktSourceElementFakeElement$default = firTypeProjection.getSource()) == null) {
                KtSourceElement source = access.getCalleeReference().getSource();
                ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeArgument.INSTANCE, null, 2, null) : null;
            }
            if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
                FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
                FirResolvedTypeRef typeRef = firTypeProjectionWithVariance.getTypeRef();
                typeRef.getClass();
                FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default = typeRef;
                FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
                firTypeProjectionWithVarianceBuilder.setSource(ktSourceElementFakeElement$default);
                if (!(TypeExpansionUtilsKt.fullyExpandedType(this, firResolvedTypeRefWithReplacedConeType$default.getConeType()) instanceof ConeErrorType)) {
                    firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRefWithReplacedConeType$default, coneKotlinTypeStoreNonFlexibleCounterpartInAttributeIfNecessary, null, 2, null);
                }
                firTypeProjectionWithVarianceBuilder.setTypeRef(firResolvedTypeRefWithReplacedConeType$default);
                firTypeProjectionWithVarianceBuilder.setVariance(firTypeProjectionWithVariance.getVariance());
                firTypeProjectionBuild = firTypeProjectionWithVarianceBuilder.build();
            } else if (firTypeProjection instanceof FirStarProjection) {
                FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
                firStarProjectionBuilder.setSource(ktSourceElementFakeElement$default);
                firTypeProjectionBuild = firStarProjectionBuilder.build();
            } else {
                FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder2 = new FirTypeProjectionWithVarianceBuilder();
                firTypeProjectionWithVarianceBuilder2.setSource(ktSourceElementFakeElement$default);
                firTypeProjectionWithVarianceBuilder2.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinTypeStoreNonFlexibleCounterpartInAttributeIfNecessary, ktSourceElementFakeElement$default, null, 2, null));
                firTypeProjectionWithVarianceBuilder2.setVariance(Variance.INVARIANT);
                firTypeProjectionBuild = firTypeProjectionWithVarianceBuilder2.build();
            }
            arrayList.add(firTypeProjectionBuild);
            i = i2;
        }
        if (arrayList.size() >= access.getTypeArguments().size()) {
            return arrayList;
        }
        List<FirTypeProjection> listSubList = access.getTypeArguments().subList(arrayList.size(), access.getTypeArguments().size());
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSubList, 10));
        for (FirPureAbstractElement firPureAbstractElementBuild : listSubList) {
            if (firPureAbstractElementBuild instanceof FirPlaceholderProjection) {
                FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder3 = new FirTypeProjectionWithVarianceBuilder();
                FirPlaceholderProjection firPlaceholderProjection = (FirPlaceholderProjection) firPureAbstractElementBuild;
                firTypeProjectionWithVarianceBuilder3.setSource(firPlaceholderProjection.getSource());
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setSource(firPlaceholderProjection.getSource());
                firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Unmapped placeholder type argument", null, 2, null));
                firTypeProjectionWithVarianceBuilder3.setTypeRef(firErrorTypeRefBuilder.build());
                firTypeProjectionWithVarianceBuilder3.setVariance(Variance.INVARIANT);
                firPureAbstractElementBuild = firTypeProjectionWithVarianceBuilder3.build();
            }
            arrayList2.add(firPureAbstractElementBuild);
        }
        return CollectionsKt.plus(arrayList, arrayList2);
    }

    private final ExpectedArgumentType.ArgumentsMap createArgumentsMapping(Candidate candidate, boolean z) {
        Object anonymousFunction;
        FirExpression firExpression;
        Object obj;
        Map map;
        boolean z2;
        Candidate.FunctionConversionDescription functionConversionDescription;
        FirSamResolver.SamConversionInfo samConversionInfo;
        FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer = this;
        Candidate candidate2 = candidate;
        List<ConePostponedResolvedAtom> postponedAtoms = candidate2.getPostponedAtoms();
        ArrayList<ConeResolvedLambdaAtom> arrayList = new ArrayList();
        for (Object obj2 : postponedAtoms) {
            if (obj2 instanceof ConeResolvedLambdaAtom) {
                arrayList.add(obj2);
            }
        }
        int i = 10;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (ConeResolvedLambdaAtom coneResolvedLambdaAtom : arrayList) {
            Pair pair = new Pair(coneResolvedLambdaAtom.getAnonymousFunction(), firCallCompletionResultsWriterTransformer.finallySubstituteOrSelf(candidate2.getSubstitutor().substituteOrSelf(coneResolvedLambdaAtom.getReturnType())));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        boolean zIsWrappedIntegerOperator = FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperator(candidate2.getSymbol());
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping = candidate2.getArgumentMapping();
        ArrayList arrayList2 = new ArrayList();
        Map mapEmptyMap = null;
        Map mapEmptyMap2 = null;
        for (Map.Entry<ConeResolutionAtom, FirValueParameter> entry : argumentMapping.entrySet()) {
            ConeResolutionAtom key = entry.getKey();
            FirValueParameter value = entry.getValue();
            FirExpression expression = key.getExpression();
            ConeIntegerConstantOperatorTypeImpl coneIntegerConstantOperatorTypeImpl = zIsWrappedIntegerOperator ? new ConeIntegerConstantOperatorTypeImpl(FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperatorForUnsignedType(candidate2.getSymbol()) && ConvertibleIntegerOperators.INSTANCE.getBinaryOperatorsWithSignedArgument().contains(candidate2.getCallInfo().getName()), false) : value.getIsVararg() ? ArrayUtilsKt.varargElementType(firCallCompletionResultsWriterTransformer.substitute(value.getReturnTypeRef(), candidate2)) : firCallCompletionResultsWriterTransformer.substitute(value.getReturnTypeRef(), candidate2);
            List<FirExpression> listUnwrapAndFlattenArgument = FirExpressionUtilKt.unwrapAndFlattenArgument(expression, false);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAndFlattenArgument, i));
            Map linkedHashMap2 = mapEmptyMap2;
            for (FirExpression firExpression2 : listUnwrapAndFlattenArgument) {
                FirAnonymousFunctionExpression firAnonymousFunctionExpression = firExpression2 instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) firExpression2 : null;
                if (firAnonymousFunctionExpression == null || (anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction()) == null) {
                    anonymousFunction = firExpression2;
                }
                HashMap<FirExpression, FirSamResolver.SamConversionInfo> samConversionInfosOfArguments = candidate2.getSamConversionInfosOfArguments();
                if (samConversionInfosOfArguments == null || (samConversionInfo = samConversionInfosOfArguments.get(firExpression2)) == null) {
                    firExpression = firExpression2;
                    obj = anonymousFunction;
                    map = mapEmptyMap;
                } else {
                    if (mapEmptyMap == null) {
                        mapEmptyMap = new LinkedHashMap();
                    }
                    map = mapEmptyMap;
                    obj = anonymousFunction;
                    firExpression = firExpression2;
                    map.put(obj, new FirSamResolver.SamConversionInfo(substituteType$default(firCallCompletionResultsWriterTransformer, samConversionInfo.getFunctionalType(), candidate2, null, 2, null), substituteType$default(this, samConversionInfo.getSamType(), candidate, null, 2, null)));
                }
                Map<FirExpression, Candidate.FunctionConversionDescription> argumentsWithFunctionKindConversion = candidate.getArgumentsWithFunctionKindConversion();
                if (argumentsWithFunctionKindConversion == null || (functionConversionDescription = argumentsWithFunctionKindConversion.get(firExpression)) == null) {
                    z2 = false;
                } else {
                    if (linkedHashMap2 == null) {
                        linkedHashMap2 = new LinkedHashMap();
                    }
                    Map map2 = linkedHashMap2;
                    z2 = false;
                    map2.put(firExpression, Candidate.FunctionConversionDescription.copy$default(functionConversionDescription, false, substituteType$default(this, functionConversionDescription.getExpectedType(), candidate, null, 2, null), 1, null));
                    linkedHashMap2 = map2;
                }
                arrayList3.add(TuplesKt.to(obj, coneIntegerConstantOperatorTypeImpl));
                firCallCompletionResultsWriterTransformer = this;
                mapEmptyMap = map;
                zIsWrappedIntegerOperator = zIsWrappedIntegerOperator;
                candidate2 = candidate;
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
            firCallCompletionResultsWriterTransformer = this;
            candidate2 = candidate;
            mapEmptyMap2 = linkedHashMap2;
            i = 10;
        }
        Map map3 = MapsKt.toMap(arrayList2);
        Map<FirElement, FirExpression> argumentReplacements = candidate.getArgumentReplacements();
        if (linkedHashMap.isEmpty() && map3.isEmpty() && (argumentReplacements == null || argumentReplacements.isEmpty())) {
            return null;
        }
        if (mapEmptyMap == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Map map4 = mapEmptyMap;
        if (mapEmptyMap2 == null) {
            mapEmptyMap2 = MapsKt.emptyMap();
        }
        return new ExpectedArgumentType.ArgumentsMap(map3, linkedHashMap, map4, mapEmptyMap2, z, argumentReplacements);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static Unit d(FirBasedSymbol firBasedSymbol, Ref.ObjectRef objectRef, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        if (Intrinsics.areEqual(originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null, firBasedSymbol)) {
            if (objectRef.element != null) {
                Object obj = objectRef.element;
                obj.getClass();
                n9a.a("Expected single, but ", UtilsKt.render(((FirBasedSymbol) obj).getFir()), " and ", UtilsKt.render(firCallableSymbol.getFir()), " found");
                return null;
            }
            objectRef.element = firCallableSymbol;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static Unit f(FirCallableDeclaration firCallableDeclaration, Ref.ObjectRef objectRef, FirVariableSymbol firVariableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firVariableSymbol.getClass();
        D fir = firVariableSymbol.getFir();
        FirSyntheticProperty firSyntheticProperty = fir instanceof FirSyntheticProperty ? (FirSyntheticProperty) fir : null;
        if (firSyntheticProperty == null) {
            return Unit.INSTANCE;
        }
        FirSyntheticProperty firSyntheticProperty2 = (FirSyntheticProperty) ((ClassMembersKt.isSubstitutionOverride(firSyntheticProperty) || (firSyntheticProperty.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firSyntheticProperty) : null);
        if (firSyntheticProperty2 == null) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(firSyntheticProperty2.getGetter().getDelegate(), ((FirSyntheticProperty) firCallableDeclaration).getGetter().getDelegate())) {
            if (objectRef.element != null) {
                k2d.a("Check failed.");
                return null;
            }
            objectRef.element = firVariableSymbol;
        }
        return Unit.INSTANCE;
    }

    private final ConeKotlinType finallySubstituteOrNull(ConeKotlinType type, ConeSubstitutor substitutor) {
        ConeKotlinType coneKotlinTypeSubstituteOrNull = substitutor.substituteOrNull(type);
        if (coneKotlinTypeSubstituteOrNull == null && (type instanceof ConeIntegerLiteralType)) {
            return ConeIntegerLiteralTypeImplKt.approximateIntegerLiteralType$default(type, (ConeKotlinType) null, 1, (Object) null);
        }
        if (coneKotlinTypeSubstituteOrNull != null) {
            return ConeIntegerLiteralTypeImplKt.approximateIntegerLiteralType$default(coneKotlinTypeSubstituteOrNull, (ConeKotlinType) null, 1, (Object) null);
        }
        return null;
    }

    public static /* synthetic */ ConeKotlinType finallySubstituteOrNull$default(FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer, ConeKotlinType coneKotlinType, ConeSubstitutor coneSubstitutor, int i, Object obj) {
        if ((i & 2) != 0) {
            coneSubstitutor = firCallCompletionResultsWriterTransformer.finalSubstitutor;
        }
        return firCallCompletionResultsWriterTransformer.finallySubstituteOrNull(coneKotlinType, coneSubstitutor);
    }

    private final ConeKotlinType finallySubstituteOrSelf(ConeKotlinType type) {
        ConeKotlinType coneKotlinTypeFinallySubstituteOrNull$default = finallySubstituteOrNull$default(this, type, null, 2, null);
        return coneKotlinTypeFinallySubstituteOrNull$default == null ? type : coneKotlinTypeFinallySubstituteOrNull$default;
    }

    private final FirBasedSymbol<?> findSingleSubstitutedSymbolWithOriginal(final FirBasedSymbol<?> original, Function1<? super Function1<? super FirCallableSymbol<?>, Unit>, Unit> processCallables) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        processCallables.invoke(new Function1() { // from class: vy4
            public final Object invoke(Object obj) {
                return FirCallCompletionResultsWriterTransformer.d(original, objectRef, (FirCallableSymbol) obj);
            }
        });
        FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) objectRef.element;
        if (firBasedSymbol != null) {
            return firBasedSymbol;
        }
        f2f.a("No symbol found for ", UtilsKt.render(original.getFir()));
        return null;
    }

    private final FunctionTypeKind functionTypeKindForDeserializedConeType(ConeKotlinType coneKotlinType) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null) {
            return null;
        }
        return FirFunctionTypeKindServiceKt.getFunctionTypeService(getSession()).extractSingleExtensionKindForDeserializedConeType(ConeTypeUtilsKt.getClassId(coneClassLikeType), CustomAnnotationTypeAttributeKt.getCustomAnnotations(coneClassLikeType));
    }

    private final ResultingArgumentsMapping handleVarargsAndReturnResultingArgumentsMapping(Candidate candidate, List<? extends FirExpression> list, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
        Object next;
        if (linkedHashMap == null) {
            linkedHashMap = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms(candidate.getArgumentMapping());
        }
        Collection<FirValueParameter> collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        Iterator<T> it = collectionValues.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((FirValueParameter) next).getIsVararg());
        FirValueParameter firValueParameter = (FirValueParameter) next;
        if (firValueParameter != null) {
            LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapRemapArgumentsWithVararg = BodyResolveUtilsKt.remapArgumentsWithVararg(getSession(), firValueParameter, substitute(firValueParameter.getReturnTypeRef(), candidate), linkedHashMap, list);
            return new ResultingArgumentsMapping(FirCallCompletionResultsWriterTransformerKt.filterValuesNotNull(linkedHashMapRemapArgumentsWithVararg), linkedHashMapRemapArgumentsWithVararg);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj : list) {
            linkedHashMap2.put(obj, linkedHashMap.get((FirExpression) obj));
        }
        return new ResultingArgumentsMapping(linkedHashMap, linkedHashMap2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ResultingArgumentsMapping handleVarargsAndReturnResultingArgumentsMapping$default(FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer, Candidate candidate, List list, LinkedHashMap linkedHashMap, int i, Object obj) {
        if ((i & 2) != 0) {
            linkedHashMap = null;
        }
        return firCallCompletionResultsWriterTransformer.handleVarargsAndReturnResultingArgumentsMapping(candidate, list, linkedHashMap);
    }

    private final boolean hasAdditionalResolutionErrors(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate) {
        List errors = firNamedReferenceWithCandidate.getCandidate().getSystem().getErrors();
        if ((errors instanceof Collection) && errors.isEmpty()) {
            return false;
        }
        Iterator it = errors.iterator();
        while (it.hasNext()) {
            if (((ConstraintSystemError) it.next()) instanceof InferredEmptyIntersection) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isJavaConstructor(FirBasedSymbol<?> firBasedSymbol) {
        if (!(firBasedSymbol instanceof FirConstructorSymbol)) {
            return false;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) firBasedSymbol).getFir();
        while (Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol != null) {
            return Intrinsics.areEqual(((FirConstructorSymbol) symbol).getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE);
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isSyntheticSamConstructor(FirBasedSymbol<?> firBasedSymbol) {
        if (!(firBasedSymbol instanceof FirSyntheticFunctionSymbol)) {
            return false;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) firBasedSymbol).getFir();
        while (Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol != null) {
            return Intrinsics.areEqual(((FirSyntheticFunctionSymbol) symbol).getOrigin(), FirDeclarationOrigin.SamConstructor.INSTANCE);
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol");
        return false;
    }

    private final ConeSubstitutor prepareCustomReturnTypeSubstitutorForFunctionCall(Candidate candidate) {
        ConeKotlinType type;
        if (Intrinsics.areEqual(candidate.getTypeArgumentMapping(), TypeArgumentMapping.NoExplicitArguments.INSTANCE)) {
            return null;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (!isJavaConstructor(symbol) && !isSyntheticSamConstructor(symbol)) {
            return null;
        }
        ConeSubstitutor coneSubstitutor = this.finalSubstitutor;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (ConeTypeVariable coneTypeVariable : candidate.getFreshVariables()) {
            int i2 = i + 1;
            ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutor.substituteOrNull(coneTypeVariable.getDefaultType());
            if (coneKotlinTypeSubstituteOrNull != null && (coneKotlinTypeSubstituteOrNull instanceof ConeFlexibleType)) {
                FirTypeProjection firTypeProjection = candidate.getTypeArgumentMapping().get(i);
                if (!(firTypeProjection instanceof FirPlaceholderProjection) && (type = ConeTypeProjectionKt.getType(FirTypeUtilsKt.toConeTypeProjection(firTypeProjection))) != null) {
                    linkedHashMap.put(coneTypeVariable.getTypeConstructor(), TypeUtilsKt.withNullabilityOf(coneKotlinTypeSubstituteOrNull, type, TypeComponentsKt.getTypeContext(getSession())));
                }
            }
            i = i2;
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return ChainedSubstitutor.INSTANCE.invoke(ConeTypeSubstitutorByTypeConstructorKt.createTypeSubstitutorByTypeConstructor(linkedHashMap, TypeComponentsKt.getTypeContext(getSession()), false), coneSubstitutor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:36:0x00cb  */
    private final <T extends FirQualifiedAccessExpression> T prepareQualifiedTransform(T qualifiedAccessExpression, FirNamedReferenceWithCandidate calleeReference) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneErrorType;
        ConeKotlinType coneType;
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        Candidate candidate = calleeReference.getCandidate();
        updateSubstitutedMemberIfReceiverContainsTypeVariable(candidate);
        FirDeclaration fir = candidate.getSymbol().getFir();
        List<FirTypeProjection> listComputeTypeArguments = computeTypeArguments(qualifiedAccessExpression, candidate);
        boolean z = fir instanceof FirCallableDeclaration;
        Object obj = null;
        if (z) {
            FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnType = this.typeCalculator.tryCalculateReturnType((FirCallableDeclaration) fir);
            coneErrorType = !(firResolvedTypeRefTryCalculateReturnType instanceof FirErrorTypeRef) ? firResolvedTypeRefTryCalculateReturnType.getConeType() : new ConeErrorType(((FirErrorTypeRef) firResolvedTypeRefTryCalculateReturnType).getDiagnostic(), false, null, null, null, null, null, 126, null);
        } else {
            coneErrorType = new ConeErrorType(fir instanceof FirTypeParameter ? new ConeTypeParameterInQualifiedAccess(((FirTypeParameter) fir).getSymbol()) : new ConeSimpleDiagnostic("Callee reference to candidate without return type: " + UtilsKt.render(fir), null, 2, null), false, null, null, null, null, null, 126, null);
        }
        if (this.mode == Mode.DelegatedPropertyCompletion) {
            qualifiedAccessExpression.transformExplicitReceiver(new TypeUpdaterForPCLAAndDelegateReceivers(), null);
        }
        FirExpression firExpressionDispatchReceiverExpression = candidate.dispatchReceiverExpression();
        FirExpression firExpressionChosenExtensionReceiverExpression = candidate.chosenExtensionReceiverExpression();
        if (!FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperator(fir)) {
            FirCallableDeclaration firCallableDeclaration = z ? (FirCallableDeclaration) fir : null;
            ConeSimpleKotlinType dispatchReceiverType = firCallableDeclaration != null ? firCallableDeclaration.getDispatchReceiverType() : null;
            if (candidate.isSuccessful()) {
                FirCallableDeclaration firCallableDeclaration2 = z ? (FirCallableDeclaration) fir : null;
                if (firCallableDeclaration2 == null || (receiverParameter = firCallableDeclaration2.getReceiverParameter()) == null || (typeRef = receiverParameter.getTypeRef()) == null) {
                    coneType = null;
                } else {
                    coneType = FirTypeUtilsKt.getConeType(typeRef);
                }
            } else {
                coneType = null;
            }
            firExpressionDispatchReceiverExpression = firExpressionDispatchReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionDispatchReceiverExpression, this.integerOperatorApproximator, dispatchReceiverType) : null;
            firExpressionChosenExtensionReceiverExpression = firExpressionChosenExtensionReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionChosenExtensionReceiverExpression, this.integerOperatorApproximator, coneType) : null;
        }
        if (candidate.getUsedOuterCs()) {
            TypeUpdaterForPCLAAndDelegateReceivers typeUpdaterForPCLAAndDelegateReceivers = new TypeUpdaterForPCLAAndDelegateReceivers();
            firExpressionDispatchReceiverExpression = firExpressionDispatchReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionDispatchReceiverExpression, typeUpdaterForPCLAAndDelegateReceivers, null) : null;
            firExpressionChosenExtensionReceiverExpression = firExpressionChosenExtensionReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionChosenExtensionReceiverExpression, typeUpdaterForPCLAAndDelegateReceivers, null) : null;
        }
        qualifiedAccessExpression.replaceCalleeReference(toResolvedReference(calleeReference));
        qualifiedAccessExpression.replaceDispatchReceiver(firExpressionDispatchReceiverExpression);
        qualifiedAccessExpression.replaceExtensionReceiver(firExpressionChosenExtensionReceiverExpression);
        ResolveUtilsKt.replaceExplicitReceiverIfNecessary(qualifiedAccessExpression, firExpressionDispatchReceiverExpression, candidate);
        qualifiedAccessExpression.replaceContextArguments(candidate.contextArguments());
        for (Object obj2 : candidate.getDiagnostics()) {
            if (obj2 instanceof NotFunctionAsOperator) {
                obj = obj2;
                break;
            }
        }
        NotFunctionAsOperator notFunctionAsOperator = (NotFunctionAsOperator) obj;
        if (notFunctionAsOperator != null) {
            ConeNotFunctionAsOperator coneNotFunctionAsOperator = new ConeNotFunctionAsOperator(notFunctionAsOperator.getSymbol());
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            listCreateListBuilder.addAll(qualifiedAccessExpression.getNonFatalDiagnostics());
            listCreateListBuilder.add(coneNotFunctionAsOperator);
            qualifiedAccessExpression.replaceNonFatalDiagnostics(CollectionsKt.build(listCreateListBuilder));
        }
        qualifiedAccessExpression.replaceConeTypeOrNull(coneErrorType);
        qualifiedAccessExpression.replaceTypeArguments(listComputeTypeArguments);
        runPCLARelatedTasksForCandidate(candidate);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneErrorType, qualifiedAccessExpression.getSource(), this.context.getFile().getSource());
        }
        return qualifiedAccessExpression;
    }

    private final ConeKotlinType removeExactAttribute(ConeKotlinType coneKotlinType) {
        ConeAttributes attributes = coneKotlinType.getAttributes();
        ConeAttribute<?> coneAttribute = CompilerConeAttributes.Exact.INSTANCE;
        return attributes.contains(coneAttribute) ? TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().remove(coneAttribute)) : coneKotlinType;
    }

    private final Collection<FirAnonymousFunctionReturnExpressionInfo> replacePostponedAtomsInReturnExpressions(Collection<FirAnonymousFunctionReturnExpressionInfo> collection, ExpectedArgumentType expectedArgumentType) {
        Map<FirElement, FirExpression> argumentReplacements;
        if (expectedArgumentType == null || (argumentReplacements = expectedArgumentType.getArgumentReplacements()) == null) {
            return collection;
        }
        Collection<FirAnonymousFunctionReturnExpressionInfo> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        for (FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfoCopy$default : collection2) {
            FirExpression firExpression = argumentReplacements.get(firAnonymousFunctionReturnExpressionInfoCopy$default.getExpression());
            if (firExpression != null) {
                firAnonymousFunctionReturnExpressionInfoCopy$default = FirAnonymousFunctionReturnExpressionInfo.copy$default(firAnonymousFunctionReturnExpressionInfoCopy$default, firExpression, false, (FirStatement) FirTransformerUtilKt.transformSingle(firAnonymousFunctionReturnExpressionInfoCopy$default.getContainingStatement(), new FirCallCompletionResultsWriterTransformer$replacePostponedAtomsInReturnExpressions$1$ReturnExpressionReplacer(firAnonymousFunctionReturnExpressionInfoCopy$default, firExpression), null), 2, null);
            }
            arrayList.add(firAnonymousFunctionReturnExpressionInfoCopy$default);
        }
        return arrayList;
    }

    private final <D extends FirExpression> D replaceTypeWithSubstituted(D d, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirResolvedTypeRef firResolvedTypeRef) {
        ConeKotlinType coneKotlinTypeSubstituteType$default = substituteType$default(this, firResolvedTypeRef.getConeType(), firNamedReferenceWithCandidate.getCandidate(), null, 2, null);
        d.replaceConeTypeOrNull(coneKotlinTypeSubstituteType$default);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeSubstituteType$default, d.getSource(), this.context.getFile().getSource());
        }
        return d;
    }

    private final void runPCLARelatedTasksForCandidate(Candidate candidate) {
        Iterator<ConeResolutionAtom> it = candidate.getPostponedPCLACalls().iterator();
        while (it.hasNext()) {
            FirTransformerUtilKt.transformSingle(it.next().getExpression(), this, null);
        }
        Iterator<Function1<ConeSubstitutor, Unit>> it2 = candidate.getOnPCLACompletionResultsWritingCallbacks().iterator();
        while (it2.hasNext()) {
            it2.next().invoke(this.finalSubstitutor);
        }
        FirTypeVariablesAfterPCLATransformer firTypeVariablesAfterPCLATransformer = new FirTypeVariablesAfterPCLATransformer(this.finalSubstitutor);
        Iterator<FirAnonymousFunction> it3 = candidate.getLambdasAnalyzedWithPCLA().iterator();
        while (it3.hasNext()) {
            FirTransformerUtilKt.transformSingle(it3.next(), firTypeVariablesAfterPCLATransformer, null);
        }
    }

    private final ConeKotlinType storeNonFlexibleCounterpartInAttributeIfNecessary(ConeKotlinType coneKotlinType, FirTypeProjection firTypeProjection) {
        return ((coneKotlinType instanceof ConeFlexibleType) && (firTypeProjection instanceof FirTypeProjectionWithVariance) && !FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible)) ? TypeUtilsKt.withAttributes(coneKotlinType, ((ConeFlexibleType) coneKotlinType).getAttributes().add(new ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute(TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef())), null, 2, null))) : coneKotlinType;
    }

    private final ConeKotlinType substitute(ConeKotlinType coneKotlinType, Candidate candidate) {
        return finallySubstituteOrSelf(candidate.getSubstitutor().substituteOrSelf(coneKotlinType));
    }

    private final ConeKotlinType substituteType(ConeKotlinType coneKotlinType, Candidate candidate, ConeSubstitutor coneSubstitutor) {
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = candidate.getSubstitutor().substituteOrSelf(coneKotlinType);
        ConeKotlinType coneKotlinTypeFinallySubstituteOrNull = finallySubstituteOrNull(coneKotlinTypeSubstituteOrSelf, coneSubstitutor);
        if (coneKotlinTypeFinallySubstituteOrNull != null) {
            coneKotlinTypeSubstituteOrSelf = coneKotlinTypeFinallySubstituteOrNull;
        }
        ConeKotlinType coneKotlinTypeApproximateToSuperType = this.typeApproximator.approximateToSuperType(coneKotlinTypeSubstituteOrSelf, TypeApproximatorConfiguration.IntermediateApproximationToSupertypeAfterCompletionInK2.INSTANCE);
        if (coneKotlinTypeApproximateToSuperType != null) {
            coneKotlinTypeSubstituteOrSelf = coneKotlinTypeApproximateToSuperType;
        }
        return removeExactAttribute(coneKotlinTypeSubstituteOrSelf);
    }

    public static /* synthetic */ ConeKotlinType substituteType$default(FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformer, ConeKotlinType coneKotlinType, Candidate candidate, ConeSubstitutor coneSubstitutor, int i, Object obj) {
        if ((i & 2) != 0) {
            coneSubstitutor = firCallCompletionResultsWriterTransformer.finalSubstitutor;
        }
        return firCallCompletionResultsWriterTransformer.substituteType(coneKotlinType, candidate, coneSubstitutor);
    }

    private final FirNamedReference toResolvedReference(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate) {
        ConeDiagnostic coneConstraintSystemHasContradiction;
        if (firNamedReferenceWithCandidate instanceof FirErrorReferenceWithCandidate) {
            coneConstraintSystemHasContradiction = ((FirErrorReferenceWithCandidate) firNamedReferenceWithCandidate).getDiagnostic();
        } else if (!CandidateApplicabilityKt.isSuccess(firNamedReferenceWithCandidate.getCandidate().getLowestApplicability())) {
            coneConstraintSystemHasContradiction = new ConeInapplicableCandidateError(firNamedReferenceWithCandidate.getCandidate().getLowestApplicability(), firNamedReferenceWithCandidate.getCandidate());
        } else if (firNamedReferenceWithCandidate.getCandidate().isSuccessful()) {
            coneConstraintSystemHasContradiction = hasAdditionalResolutionErrors(firNamedReferenceWithCandidate) ? new ConeConstraintSystemHasContradiction(firNamedReferenceWithCandidate.getCandidate()) : null;
        } else {
            if (!firNamedReferenceWithCandidate.getCandidate().getSystem().getHasContradiction()) {
                w01.a("Candidate is not successful, but system has no contradiction");
                return null;
            }
            coneConstraintSystemHasContradiction = new ConeConstraintSystemHasContradiction(firNamedReferenceWithCandidate.getCandidate());
        }
        if (coneConstraintSystemHasContradiction != null) {
            return ResolveUtilsKt.toErrorReference(firNamedReferenceWithCandidate, coneConstraintSystemHasContradiction);
        }
        if (ResolveUtilsKt.isExplicitBackingFieldAccess(firNamedReferenceWithCandidate.getCandidate())) {
            return ResolveUtilsKt.buildExplicitBackingFieldReference(firNamedReferenceWithCandidate.getSource(), firNamedReferenceWithCandidate.getName(), firNamedReferenceWithCandidate.getCandidate());
        }
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(firNamedReferenceWithCandidate.getSource());
        firResolvedNamedReferenceBuilder.setName(firNamedReferenceWithCandidate.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firNamedReferenceWithCandidate.getCandidateSymbol());
        return firResolvedNamedReferenceBuilder.build();
    }

    private final void transformArgumentList(FirCall firCall, ExpectedArgumentType.ArgumentsMap argumentsMap) {
        FirArgumentList argumentList = firCall.getArgumentList();
        FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
        firCall.getArgumentList().transformArguments(new ArgumentTransformer(argumentsMap, firResolvedArgumentList != null ? firResolvedArgumentList.getMapping() : null), null);
    }

    private final FirStatement transformArrayLiteralInAnnotation(FirCollectionLiteral arrayLiteral, ExpectedArgumentType data) {
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        if (FirTypeUtilsKt.getHasResolvedType(arrayLiteral)) {
            return arrayLiteral;
        }
        ConeKotlinType expectedType = data != null ? FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, arrayLiteral) : null;
        ConeTypeProjection coneTypeProjectionArrayElementType$default = expectedType != null ? FirTypeUtilsKt.arrayElementType$default(expectedType, false, 1, null) : null;
        arrayLiteral.transformChildren(this, coneTypeProjectionArrayElementType$default != null ? FirCallCompletionResultsWriterTransformerKt.toExpectedType(coneTypeProjectionArrayElementType$default, data.getArgumentReplacements()) : null);
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        List<FirExpression> arguments = arrayLiteral.getArgumentList().getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
        }
        ConeTypeProjection coneTypeProjectionCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
        if (coneTypeProjectionCommonSuperTypeOrNull != null) {
            coneTypeProjectionArrayElementType$default = this.typeApproximator.approximateToSuperType(coneTypeProjectionCommonSuperTypeOrNull, TypeApproximatorConfiguration.IntermediateApproximationToSupertypeAfterCompletionInK2.INSTANCE);
            if (coneTypeProjectionArrayElementType$default == null) {
                coneTypeProjectionArrayElementType$default = coneTypeProjectionCommonSuperTypeOrNull;
            }
        } else if (coneTypeProjectionArrayElementType$default == null) {
            coneTypeProjectionArrayElementType$default = getSession().getBuiltinTypes().getNullableAnyType().getConeType();
        }
        arrayLiteral.replaceConeTypeOrNull(ArrayUtilsKt.createArrayType$default(coneTypeProjectionArrayElementType$default, false, (expectedType == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, expectedType)) == null || !ConeBuiltinTypeUtilsKt.isPrimitiveArray(coneKotlinTypeFullyExpandedType)) ? false : true, 1, null));
        return arrayLiteral;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression transformCallableReferenceAccess$lambda$1$0$0(ConeResolutionAtom coneResolutionAtom) {
        coneResolutionAtom.getClass();
        return coneResolutionAtom.getExpression();
    }

    private final void updateExplicitContextArgumentsFromArgumentList(FirFunctionCall firFunctionCall, Candidate candidate) {
        LinkedHashMap linkedHashMap;
        List<FirValueParameterSymbol> contextParameterSymbols;
        Iterable<IndexedValue> iterableWithIndex;
        LinkedHashMap<FirExpression, FirValueParameter> mappingIncludingContextArguments;
        if (firFunctionCall.getContextArguments().isEmpty()) {
            return;
        }
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
        if (firResolvedArgumentList == null || (mappingIncludingContextArguments = firResolvedArgumentList.getMappingIncludingContextArguments()) == null) {
            linkedHashMap = null;
        } else {
            linkedHashMap = new LinkedHashMap();
            for (Map.Entry<FirExpression, FirValueParameter> entry : mappingIncludingContextArguments.entrySet()) {
                if (entry.getValue().getValueParameterKind() == FirValueParameterKind.ContextParameter) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            return;
        }
        List<? extends FirExpression> mutableList = CollectionsKt.toMutableList(firFunctionCall.getContextArguments());
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol != null && (contextParameterSymbols = firCallableSymbol.getContextParameterSymbols()) != null && (iterableWithIndex = CollectionsKt.withIndex(contextParameterSymbols)) != null) {
            for (IndexedValue indexedValue : iterableWithIndex) {
                mapCreateMapBuilder.put((FirValueParameterSymbol) indexedValue.component2(), Integer.valueOf(indexedValue.getIndex()));
            }
        }
        Map mapBuild = MapsKt.build(mapCreateMapBuilder);
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            FirExpression firExpression = (FirExpression) entry2.getKey();
            FirValueParameter firValueParameter = (FirValueParameter) entry2.getValue();
            if (firValueParameter.getValueParameterKind() == FirValueParameterKind.ContextParameter) {
                mutableList.set(((Number) MapsKt.getValue(mapBuild, firValueParameter.getSymbol())).intValue(), firExpression);
            }
        }
        firFunctionCall.replaceContextArguments(mutableList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final void updateSubstitutedMemberIfReceiverContainsTypeVariable(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirBasedSymbol<?> firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable = updateSubstitutedMemberIfReceiverContainsTypeVariable(candidate.getSymbol(), candidate.getUsedOuterCs());
        if (firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable == null) {
            return;
        }
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        candidate.updateSymbol(firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable);
        if (!(firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable instanceof FirCallableSymbol)) {
            k2d.a("Check failed.");
            return;
        }
        List<Pair> listZip = CollectionsKt.zip(((FirCallableSymbol) firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable).getTypeParameterSymbols(), candidate.getFreshVariables());
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listZip, 10)), 16));
        for (Pair pair : listZip) {
            Pair pair2 = TuplesKt.to((FirTypeParameterSymbol) pair.component1(), ((ConeTypeVariable) pair.component2()).getDefaultType());
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        candidate.updateSubstitutor(ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, getSession(), false, 4, null));
        if (firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable instanceof FirFunctionSymbol) {
            if (!(symbol instanceof FirFunctionSymbol)) {
                w01.a("Failed requirement.");
                return;
            }
            LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping = candidate.getArgumentMapping();
            Map map = MapsKt.toMap(CollectionsKt.zip(((FirFunctionSymbol) symbol).getValueParameterSymbols(), ((FirFunctionSymbol) firBasedSymbolUpdateSubstitutedMemberIfReceiverContainsTypeVariable).getValueParameterSymbols()));
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            Iterator<T> it = argumentMapping.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Object obj = map.get(((FirValueParameter) entry.getValue()).getSymbol());
                obj.getClass();
                linkedHashMap2.put(key, (FirValueParameter) ((FirValueParameterSymbol) obj).getFir());
            }
            candidate.updateArgumentMapping(linkedHashMap2);
        }
    }

    private final boolean wasExpectedTypeAddedAsEqualityForSyntheticCall(FirResolvable firResolvable) {
        Candidate candidate = CandidateFactoryKt.candidate(firResolvable);
        return candidate != null && candidate.getWasExpectedTypeAddedAsEqualityForSyntheticCall();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirFunctionTypeConversionExpression wrapInFunctionTypeConversionExpression(FirExpression firExpression, ConeKotlinType coneKotlinType, FirFunctionConversionKind firFunctionConversionKind, KtFakeSourceElementKind ktFakeSourceElementKind) {
        FirFunctionTypeConversionExpressionBuilder firFunctionTypeConversionExpressionBuilder = new FirFunctionTypeConversionExpressionBuilder();
        firFunctionTypeConversionExpressionBuilder.setExpression(firExpression);
        ConeKotlinType coneKotlinTypeWithNullabilityOf = TypeUtilsKt.withNullabilityOf(coneKotlinType, FirTypeUtilsKt.getResolvedType(firExpression), TypeComponentsKt.getTypeContext(getSession()));
        ConeKotlinType coneKotlinTypeApproximateToSuperType = this.typeApproximator.approximateToSuperType(coneKotlinTypeWithNullabilityOf, TypeApproximatorConfiguration.TypeArgumentApproximationAfterCompletionInK2.INSTANCE);
        if (coneKotlinTypeApproximateToSuperType != null) {
            coneKotlinTypeWithNullabilityOf = coneKotlinTypeApproximateToSuperType;
        }
        firFunctionTypeConversionExpressionBuilder.setConeTypeOrNull(coneKotlinTypeWithNullabilityOf);
        firFunctionTypeConversionExpressionBuilder.setKind(firFunctionConversionKind);
        KtSourceElement source = firExpression.getSource();
        firFunctionTypeConversionExpressionBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, ktFakeSourceElementKind, null, 2, null) : null);
        return firFunctionTypeConversionExpressionBuilder.mo288build();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ExpectedArgumentType data) {
        annotationCall.getClass();
        FirReference calleeReference = annotationCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return annotationCall;
        }
        annotationCall.replaceCalleeReference(toResolvedReference(firNamedReferenceWithCandidate));
        Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
        ExpectedArgumentType.ArgumentsMap argumentsMapCreateArgumentsMapping = createArgumentsMapping(candidate, firNamedReferenceWithCandidate.isError());
        boolean z = this.insideAnnotationContext;
        this.insideAnnotationContext = true;
        try {
            this.enableArrayOfCallTransformation = true;
            try {
                annotationCall.getArgumentList().transformArguments(this, argumentsMapCreateArgumentsMapping);
                LinkedHashMap linkedHashMap = new LinkedHashMap(candidate.getArgumentMapping().size());
                int i = 0;
                for (Object obj : candidate.getArgumentMapping().entrySet()) {
                    int i2 = i + 1;
                    linkedHashMap.put(annotationCall.getArgumentList().getArguments().get(i), ((Map.Entry) obj).getValue());
                    i = i2;
                }
                this.enableArrayOfCallTransformation = false;
                this.insideAnnotationContext = z;
                ResultingArgumentsMapping resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping = handleVarargsAndReturnResultingArgumentsMapping(candidate, computeAllArguments(firNamedReferenceWithCandidate, annotationCall.getArgumentList(), linkedHashMap), linkedHashMap);
                LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent1 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping.component1();
                LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent2 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping.component2();
                if (firNamedReferenceWithCandidate.isError()) {
                    annotationCall.replaceArgumentList(FirArgumentUtilKt.buildArgumentListForErrorCall(annotationCall.getArgumentList(), linkedHashMapComponent2));
                } else {
                    annotationCall.replaceArgumentList(FirArgumentUtilKt.buildResolvedArgumentList(annotationCall.getArgumentList(), linkedHashMapComponent1));
                }
                boolean z2 = this.insideAnnotationContext;
                this.insideAnnotationContext = true;
                try {
                    transformArgumentList(annotationCall, (ExpectedArgumentType.ArgumentsMap) null);
                    Unit unit = Unit.INSTANCE;
                    return annotationCall;
                } finally {
                    this.insideAnnotationContext = z2;
                }
            } catch (Throwable th) {
                this.enableArrayOfCallTransformation = false;
                throw th;
            }
        } catch (Throwable th2) {
            this.insideAnnotationContext = z;
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:108:0x0153  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ef A[PHI: r2
      0x01ef: PHI (r2v23 org.jetbrains.kotlin.builtins.functions.FunctionTypeKind) = 
      (r2v6 org.jetbrains.kotlin.builtins.functions.FunctionTypeKind)
      (r2v24 org.jetbrains.kotlin.builtins.functions.FunctionTypeKind)
     binds: [B:148:0x01fe, B:142:0x01ec] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:32:0x006c  */
    /* JADX WARN: Code duplicated, block: B:49:0x0098  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousFunction(FirAnonymousFunction anonymousFunction, ExpectedArgumentType data) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible;
        ConeKotlinType coneType;
        boolean z;
        ConeKotlinType coneType2;
        Map<FirAnonymousFunction, ConeKotlinType> lambdasReturnTypes;
        ExpectedArgumentType expectedType;
        FunctionTypeKind functionTypeKindFunctionTypeKind$default;
        FirResolvedTypeRef typeRef;
        ConeKotlinType expectedType2;
        FirSamResolver.SamConversionInfo samInfoForPossibleSamType;
        ConeKotlinType functionalType;
        Map<FirElement, FirSamResolver.SamConversionInfo> samConversions;
        anonymousFunction.getClass();
        Collection<FirAnonymousFunctionReturnExpressionInfo> collectionReplacePostponedAtomsInReturnExpressions = replacePostponedAtomsInReturnExpressions(this.dataFlowAnalyzer.returnExpressionsOfAnonymousFunction(anonymousFunction), data);
        boolean z2 = data instanceof ExpectedArgumentType.ArgumentsMap;
        FunctionTypeKind functionTypeKindFunctionTypeKind$default2 = null;
        ExpectedArgumentType.ArgumentsMap argumentsMap = z2 ? (ExpectedArgumentType.ArgumentsMap) data : null;
        boolean z3 = true;
        boolean z4 = argumentsMap != null && argumentsMap.getForErrorReference();
        if (data == null || (expectedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, anonymousFunction)) == null) {
            coneRigidTypeLowerBoundIfFlexible = null;
        } else if (FunctionalTypeUtilsKt.isSomeFunctionType(expectedType2, getSession())) {
            coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(expectedType2);
        } else {
            ExpectedArgumentType.ArgumentsMap argumentsMap2 = data instanceof ExpectedArgumentType.ArgumentsMap ? (ExpectedArgumentType.ArgumentsMap) data : null;
            if (argumentsMap2 == null || (samConversions = argumentsMap2.getSamConversions()) == null || (samInfoForPossibleSamType = samConversions.get(anonymousFunction)) == null) {
                samInfoForPossibleSamType = this.samResolver.getSamInfoForPossibleSamType(expectedType2);
            }
            if (samInfoForPossibleSamType == null || (functionalType = samInfoForPossibleSamType.getFunctionalType()) == null) {
                coneRigidTypeLowerBoundIfFlexible = null;
            } else {
                coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(functionalType);
            }
        }
        ConeRigidType coneRigidType = (coneRigidTypeLowerBoundIfFlexible == null || z4) ? null : coneRigidTypeLowerBoundIfFlexible;
        boolean z5 = anonymousFunction.getTypeRef() instanceof FirImplicitTypeRef;
        FirReceiverParameter receiverParameter = anonymousFunction.getReceiverParameter();
        if (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) {
            coneType = null;
        } else {
            FirResolvedTypeRef firResolvedTypeRef = typeRef instanceof FirResolvedTypeRef ? typeRef : null;
            coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                coneType = null;
            }
        }
        ConeKotlinType coneKotlinTypeFinallySubstituteOrNull$default = coneType != null ? finallySubstituteOrNull$default(this, coneType, null, 2, null) : null;
        if (coneKotlinTypeFinallySubstituteOrNull$default != null) {
            FirTypeRef typeRef2 = receiverParameter.getTypeRef();
            KtSourceElement source = receiverParameter.getSource();
            if (source == null) {
                source = anonymousFunction.getSource();
            }
            receiverParameter.replaceTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(typeRef2, coneKotlinTypeFinallySubstituteOrNull$default, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.LambdaReceiver.INSTANCE, null, 2, null) : null));
            z5 = true;
        }
        FirResolvedTypeRef returnTypeRef = anonymousFunction.getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef2 = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType3 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
        if (coneType3 == null) {
            coneType3 = null;
        }
        if (coneType3 == null || (coneType2 = finallySubstituteOrSelf(coneType3)) == null) {
            Collection<FirAnonymousFunctionReturnExpressionInfo> collection = collectionReplacePostponedAtomsInReturnExpressions;
            if (!(collection instanceof Collection) || !collection.isEmpty()) {
                Iterator<T> it = collection.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    KtSourceElement source2 = ((FirAnonymousFunctionReturnExpressionInfo) it.next()).getExpression().getSource();
                    if ((source2 != null ? source2.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitUnit.Return) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
            }
            coneType2 = z ? getSession().getBuiltinTypes().getUnitType().getConeType() : null;
            if (coneType2 == null) {
                ConeClassLikeType coneClassLikeType = coneRigidType instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidType : null;
                ConeKotlinType coneKotlinTypeReturnType = coneClassLikeType != null ? FunctionalTypeUtilsKt.returnType(coneClassLikeType, getSession()) : null;
                coneType2 = coneKotlinTypeReturnType instanceof ConeClassLikeType ? (ConeClassLikeType) coneKotlinTypeReturnType : null;
                if (coneType2 == null) {
                    if (z4) {
                        coneType2 = null;
                    } else {
                        ExpectedArgumentType.ArgumentsMap argumentsMap3 = z2 ? (ExpectedArgumentType.ArgumentsMap) data : null;
                        if (argumentsMap3 == null || (lambdasReturnTypes = argumentsMap3.getLambdasReturnTypes()) == null) {
                            coneType2 = null;
                        } else {
                            coneType2 = lambdasReturnTypes.get(anonymousFunction);
                        }
                    }
                }
            }
        }
        if (coneType2 != null) {
            expectedType = FirCallCompletionResultsWriterTransformerKt.toExpectedType(coneType2, data != null ? data.getArgumentReplacements() : null);
        } else {
            expectedType = null;
        }
        Iterator<FirAnonymousFunctionReturnExpressionInfo> it2 = collectionReplacePostponedAtomsInReturnExpressions.iterator();
        while (it2.hasNext()) {
            FirTransformerUtilKt.transformSingle(it2.next().getExpression(), this, expectedType);
        }
        FirBlock body = anonymousFunction.getBody();
        if (body != null) {
            transformBlock(body, expectedType);
        }
        ConeKotlinType coneKotlinTypeComputeReturnType = ResolveUtilsKt.computeReturnType(anonymousFunction, getSession(), coneType2, true, collectionReplacePostponedAtomsInReturnExpressions);
        if (Intrinsics.areEqual(coneType3, coneKotlinTypeComputeReturnType)) {
            z3 = z5;
        } else {
            KtSourceElement source3 = anonymousFunction.getSource();
            anonymousFunction.replaceReturnTypeRef(CopyUtilsKt.resolvedTypeFromPrototype(anonymousFunction.getReturnTypeRef(), coneKotlinTypeComputeReturnType, source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ImplicitFunctionReturnType.INSTANCE, null, 2, null) : null));
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker != null) {
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, anonymousFunction.getReturnTypeRef(), anonymousFunction.getSource(), this.context.getFile().getSource());
            }
        }
        if (z3) {
            if (coneRigidTypeLowerBoundIfFlexible == null || (functionTypeKindFunctionTypeKind$default = functionTypeKindForDeserializedConeType(coneRigidTypeLowerBoundIfFlexible)) == null) {
                functionTypeKindFunctionTypeKind$default = coneRigidTypeLowerBoundIfFlexible != null ? FunctionalTypeUtilsKt.functionTypeKind$default(coneRigidTypeLowerBoundIfFlexible, getSession(), false, 2, (Object) null) : null;
                if (functionTypeKindFunctionTypeKind$default == null) {
                    FirResolvedTypeRef typeRef3 = anonymousFunction.getTypeRef();
                    FirResolvedTypeRef firResolvedTypeRef3 = typeRef3 instanceof FirResolvedTypeRef ? typeRef3 : null;
                    ConeKotlinType coneType4 = firResolvedTypeRef3 != null ? firResolvedTypeRef3.getConeType() : null;
                    if (!(coneType4 instanceof ConeClassLikeType)) {
                        coneType4 = null;
                    }
                    ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) coneType4;
                    if (coneClassLikeType2 != null) {
                        functionTypeKindFunctionTypeKind$default2 = FunctionalTypeUtilsKt.functionTypeKind$default((ConeRigidType) coneClassLikeType2, getSession(), false, 2, (Object) null);
                    }
                } else {
                    functionTypeKindFunctionTypeKind$default2 = functionTypeKindFunctionTypeKind$default;
                }
            } else {
                functionTypeKindFunctionTypeKind$default2 = functionTypeKindFunctionTypeKind$default;
            }
            anonymousFunction.replaceTypeRef(ResolveUtilsKt.constructFunctionTypeRef(anonymousFunction, getSession(), functionTypeKindFunctionTypeKind$default2));
            FirLookupTrackerComponent lookupTracker2 = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker2 != null) {
                FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker2, anonymousFunction.getTypeRef(), anonymousFunction.getSource(), this.context.getFile().getSource());
            }
        }
        ResolveUtilsKt.addReturnToLastStatementIfNeeded(anonymousFunction, getSession());
        return anonymousFunction;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, ExpectedArgumentType data) {
        anonymousFunctionExpression.getClass();
        return anonymousFunctionExpression.transformAnonymousFunction(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(FirBlock block, ExpectedArgumentType data) {
        block.getClass();
        transformElement(block, data);
        if (!block.getIsUnitCoerced()) {
            BodyResolveUtilsKt.writeResultType(block, getSession());
        }
        return block;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        Map mapEmptyMap;
        FirNamedReference firNamedReferenceBuild;
        Map<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> mappedArguments;
        callableReferenceAccess.getClass();
        FirNamedReference calleeReference = callableReferenceAccess.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return callableReferenceAccess;
        }
        Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
        List<FirTypeProjection> listComputeTypeArguments = computeTypeArguments(callableReferenceAccess, candidate);
        ConeKotlinType coneKotlinTypeFinallySubstituteOrSelf = finallySubstituteOrSelf(firNamedReferenceWithCandidate.getCandidate().getSubstitutor().substituteOrSelf(FirTypeUtilsKt.getResolvedType(callableReferenceAccess)));
        CallInfo callInfo = candidate.getCallInfo();
        CallableReferenceInfo callableReferenceInfo = callInfo instanceof CallableReferenceInfo ? (CallableReferenceInfo) callInfo : null;
        if (callableReferenceInfo != null) {
            DoubleColonLHS lhs = callableReferenceInfo.getLhs();
            DoubleColonLHS.Type type = lhs instanceof DoubleColonLHS.Type ? (DoubleColonLHS.Type) lhs : null;
            if (type != null) {
                FirExpression explicitReceiver = callableReferenceAccess.getExplicitReceiver();
                FirExpression firExpressionUnwrapSmartcastExpression = explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null;
                FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
                if (firResolvedQualifier != null) {
                    firResolvedQualifier.replaceResolvedLHSTypeForCallableReferenceOrNull(type.getType());
                }
            }
        }
        callableReferenceAccess.replaceConeTypeOrNull(coneKotlinTypeFinallySubstituteOrSelf);
        callableReferenceAccess.replaceTypeArguments(listComputeTypeArguments);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            KtSourceElement source = callableReferenceAccess.getSource();
            if (source == null) {
                source = callableReferenceAccess.getSource();
            }
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeFinallySubstituteOrSelf, source, this.context.getFile().getSource());
        }
        if (firNamedReferenceWithCandidate instanceof FirErrorReferenceWithCandidate) {
            firNamedReferenceBuild = ResolveUtilsKt.toErrorReference(firNamedReferenceWithCandidate, ((FirErrorReferenceWithCandidate) firNamedReferenceWithCandidate).getDiagnostic());
        } else {
            FirResolvedCallableReferenceBuilder firResolvedCallableReferenceBuilder = new FirResolvedCallableReferenceBuilder();
            firResolvedCallableReferenceBuilder.setSource(firNamedReferenceWithCandidate.getSource());
            firResolvedCallableReferenceBuilder.setName(firNamedReferenceWithCandidate.getName());
            firResolvedCallableReferenceBuilder.setResolvedSymbol(firNamedReferenceWithCandidate.getCandidateSymbol());
            firResolvedCallableReferenceBuilder.getInferredTypeArguments().addAll(computeTypeArgumentTypes(firNamedReferenceWithCandidate.getCandidate()));
            CallableReferenceAdaptation callableReferenceAdaptation = candidate.getCallableReferenceAdaptation();
            if (callableReferenceAdaptation == null || (mappedArguments = callableReferenceAdaptation.getMappedArguments()) == null) {
                mapEmptyMap = MapsKt.emptyMap();
            } else {
                mapEmptyMap = new LinkedHashMap(MapsKt.mapCapacity(mappedArguments.size()));
                Iterator<T> it = mappedArguments.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    mapEmptyMap.put(entry.getKey(), ResolvedCallArgumentKt.map((ResolvedCallArgument) entry.getValue(), new Function1() { // from class: zy4
                        public final Object invoke(Object obj) {
                            return FirCallCompletionResultsWriterTransformer.transformCallableReferenceAccess$lambda$1$0$0((ConeResolutionAtom) obj);
                        }
                    }));
                }
            }
            firResolvedCallableReferenceBuilder.setMappedArguments(mapEmptyMap);
            firNamedReferenceBuild = firResolvedCallableReferenceBuilder.build();
        }
        FirExpression firExpressionDispatchReceiverExpression = candidate.dispatchReceiverExpression();
        FirExpression firExpressionChosenExtensionReceiverExpression = candidate.chosenExtensionReceiverExpression();
        if (candidate.getUsedOuterCs()) {
            TypeUpdaterForPCLAAndDelegateReceivers typeUpdaterForPCLAAndDelegateReceivers = new TypeUpdaterForPCLAAndDelegateReceivers();
            firExpressionDispatchReceiverExpression = firExpressionDispatchReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionDispatchReceiverExpression, typeUpdaterForPCLAAndDelegateReceivers, null) : null;
            firExpressionChosenExtensionReceiverExpression = firExpressionChosenExtensionReceiverExpression != null ? (FirExpression) FirTransformerUtilKt.transformSingle(firExpressionChosenExtensionReceiverExpression, typeUpdaterForPCLAAndDelegateReceivers, null) : null;
        }
        callableReferenceAccess.replaceCalleeReference(firNamedReferenceBuild);
        callableReferenceAccess.replaceDispatchReceiver(firExpressionDispatchReceiverExpression);
        callableReferenceAccess.replaceExtensionReceiver(firExpressionChosenExtensionReceiverExpression);
        ResolveUtilsKt.replaceExplicitReceiverIfNecessary(callableReferenceAccess, firExpressionDispatchReceiverExpression, candidate);
        FirCallCompletionResultsWriterTransformerKt.addNonFatalDiagnostics(callableReferenceAccess, candidate);
        return callableReferenceAccess;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        List<? extends ConeKotlinType> listListOf;
        ConeKotlinType resolvedType2;
        Candidate candidate;
        FirBasedSymbol<?> symbol;
        checkNotNullCall.getClass();
        boolean zWasExpectedTypeAddedAsEqualityForSyntheticCall = wasExpectedTypeAddedAsEqualityForSyntheticCall(checkNotNullCall);
        FirReference calleeReference = checkNotNullCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        FirDeclaration fir = (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null || (symbol = candidate.getSymbol()) == null) ? null : symbol.getFir();
        FirNamedFunction firNamedFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firNamedReferenceWithCandidate == null || firNamedFunction == null) {
            if (data == null || (resolvedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, checkNotNullCall)) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(checkNotNullCall);
            }
            Candidate candidate2 = CandidateFactoryKt.candidate(checkNotNullCall);
            checkNotNullCall.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType, candidate2 != null ? candidate2.getArgumentReplacements() : null));
        } else {
            replaceTypeWithSubstituted(checkNotNullCall, firNamedReferenceWithCandidate, this.typeCalculator.tryCalculateReturnType(firNamedFunction));
            if (data == null || (resolvedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, checkNotNullCall)) == null) {
                resolvedType2 = FirTypeUtilsKt.getResolvedType(checkNotNullCall);
            }
            Candidate candidate3 = CandidateFactoryKt.candidate(checkNotNullCall);
            checkNotNullCall.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType2, candidate3 != null ? candidate3.getArgumentReplacements() : null));
            runPCLARelatedTasksForCandidate(firNamedReferenceWithCandidate.getCandidate());
            FirNamedReference resolvedReference = toResolvedReference(firNamedReferenceWithCandidate);
            if (!(FirTypeUtilsKt.getResolvedType(checkNotNullCall) instanceof ConeErrorType) && (resolvedReference instanceof FirResolvedErrorReference)) {
                ConeDiagnostic diagnostic = ((FirResolvedErrorReference) resolvedReference).getDiagnostic();
                if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                    AbstractCallCandidate<?> candidate4 = ((ConeConstraintSystemHasContradiction) diagnostic).getCandidate();
                    candidate4.getClass();
                    ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                    Set<ConeResolutionAtom> setKeySet = ((Candidate) candidate4).getArgumentMapping().keySet();
                    setKeySet.getClass();
                    List listUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAtoms, 10));
                    Iterator it = listUnwrapAtoms.iterator();
                    while (it.hasNext()) {
                        arrayList.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
                    }
                    ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
                    if (coneKotlinTypeCommonSuperTypeOrNull != null && !ConeTypeUtilsKt.hasError(coneKotlinTypeCommonSuperTypeOrNull)) {
                        checkNotNullCall.replaceConeTypeOrNull(coneKotlinTypeCommonSuperTypeOrNull);
                    }
                }
            }
            checkNotNullCall.replaceCalleeReference(resolvedReference);
        }
        if (zWasExpectedTypeAddedAsEqualityForSyntheticCall && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EqualityConstraintForOperatorsUnderAssignments) && (listListOf = CollectionsKt.listOf(TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(FirTypeUtilsKt.getResolvedType(checkNotNullCall.getArgumentList().getArguments().get(0)), (ConeTypeContext) TypeComponentsKt.getTypeContext(getSession()), false, false, 6, (Object) null))) != null) {
            addRefinedTypeForDataFlow(checkNotNullCall, listListOf);
        }
        return checkNotNullCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCollectionLiteral(FirCollectionLiteral collectionLiteral, ExpectedArgumentType data) {
        Map<FirElement, FirExpression> argumentReplacements;
        FirExpression firExpression;
        collectionLiteral.getClass();
        if (!FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.CollectionLiterals) || this.insideAnnotationContext) {
            return transformArrayLiteralInAnnotation(collectionLiteral, data);
        }
        if (data == null || (argumentReplacements = data.getArgumentReplacements()) == null || (firExpression = argumentReplacements.get(collectionLiteral)) == null) {
            return collectionLiteral;
        }
        return this.mode == Mode.TopLevelSyntheticCallInPclaCompletion ? firExpression : (FirStatement) firExpression.transform(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ExpectedArgumentType data) {
        delegatedConstructorCall.getClass();
        FirReference calleeReference = delegatedConstructorCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return delegatedConstructorCall;
        }
        Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
        FirArgumentList argumentList = delegatedConstructorCall.getArgumentList();
        ResultingArgumentsMapping resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default = handleVarargsAndReturnResultingArgumentsMapping$default(this, candidate, computeAllArguments$default(this, firNamedReferenceWithCandidate, argumentList, null, 2, null), null, 2, null);
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent1 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default.component1();
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent2 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default.component2();
        if (firNamedReferenceWithCandidate.isError()) {
            delegatedConstructorCall.replaceArgumentList(FirArgumentUtilKt.buildArgumentListForErrorCall(argumentList, linkedHashMapComponent2));
        } else {
            delegatedConstructorCall.replaceArgumentList(FirArgumentUtilKt.buildResolvedArgumentList(argumentList, linkedHashMapComponent1));
        }
        runPCLARelatedTasksForCandidate(candidate);
        transformArgumentList(delegatedConstructorCall, createArgumentsMapping(candidate, firNamedReferenceWithCandidate.isError()));
        delegatedConstructorCall.replaceCalleeReference(toResolvedReference(firNamedReferenceWithCandidate));
        return delegatedConstructorCall;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractTreeTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, ExpectedArgumentType data) {
        element.getClass();
        return element instanceof FirDeclaration ? element : (E) super.transformElement((FirElement) element, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformElvisExpression(FirElvisExpression elvisExpression, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        Candidate candidate;
        FirBasedSymbol<?> symbol;
        elvisExpression.getClass();
        FirReference calleeReference = elvisExpression.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        FirDeclaration fir = (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null || (symbol = candidate.getSymbol()) == null) ? null : symbol.getFir();
        FirNamedFunction firNamedFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firNamedReferenceWithCandidate == null || firNamedFunction == null) {
            if (data == null || (resolvedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, elvisExpression)) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(elvisExpression);
            }
            Candidate candidate2 = CandidateFactoryKt.candidate(elvisExpression);
            elvisExpression.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType, candidate2 != null ? candidate2.getArgumentReplacements() : null));
            return elvisExpression;
        }
        replaceTypeWithSubstituted(elvisExpression, firNamedReferenceWithCandidate, this.typeCalculator.tryCalculateReturnType(firNamedFunction));
        if (data == null || (resolvedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, elvisExpression)) == null) {
            resolvedType2 = FirTypeUtilsKt.getResolvedType(elvisExpression);
        }
        Candidate candidate3 = CandidateFactoryKt.candidate(elvisExpression);
        elvisExpression.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType2, candidate3 != null ? candidate3.getArgumentReplacements() : null));
        runPCLARelatedTasksForCandidate(firNamedReferenceWithCandidate.getCandidate());
        FirNamedReference resolvedReference = toResolvedReference(firNamedReferenceWithCandidate);
        if (!(FirTypeUtilsKt.getResolvedType(elvisExpression) instanceof ConeErrorType) && (resolvedReference instanceof FirResolvedErrorReference)) {
            ConeDiagnostic diagnostic = ((FirResolvedErrorReference) resolvedReference).getDiagnostic();
            if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                AbstractCallCandidate<?> candidate4 = ((ConeConstraintSystemHasContradiction) diagnostic).getCandidate();
                candidate4.getClass();
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                Set<ConeResolutionAtom> setKeySet = ((Candidate) candidate4).getArgumentMapping().keySet();
                setKeySet.getClass();
                List listUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAtoms, 10));
                Iterator it = listUnwrapAtoms.iterator();
                while (it.hasNext()) {
                    arrayList.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
                }
                ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
                if (coneKotlinTypeCommonSuperTypeOrNull != null && !ConeTypeUtilsKt.hasError(coneKotlinTypeCommonSuperTypeOrNull)) {
                    elvisExpression.replaceConeTypeOrNull(coneKotlinTypeCommonSuperTypeOrNull);
                }
            }
        }
        elvisExpression.replaceCalleeReference(resolvedReference);
        return elvisExpression;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        Candidate candidate;
        FirBasedSymbol<?> symbol;
        equalityOperatorCall.getClass();
        FirReference calleeReference = equalityOperatorCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        FirDeclaration fir = (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null || (symbol = candidate.getSymbol()) == null) ? null : symbol.getFir();
        FirNamedFunction firNamedFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firNamedReferenceWithCandidate == null || firNamedFunction == null) {
            if (data == null || (resolvedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, equalityOperatorCall)) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(equalityOperatorCall);
            }
            Candidate candidate2 = CandidateFactoryKt.candidate(equalityOperatorCall);
            equalityOperatorCall.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType, candidate2 != null ? candidate2.getArgumentReplacements() : null));
            return equalityOperatorCall;
        }
        replaceTypeWithSubstituted(equalityOperatorCall, firNamedReferenceWithCandidate, this.typeCalculator.tryCalculateReturnType(firNamedFunction));
        if (data == null || (resolvedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, equalityOperatorCall)) == null) {
            resolvedType2 = FirTypeUtilsKt.getResolvedType(equalityOperatorCall);
        }
        Candidate candidate3 = CandidateFactoryKt.candidate(equalityOperatorCall);
        equalityOperatorCall.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType2, candidate3 != null ? candidate3.getArgumentReplacements() : null));
        runPCLARelatedTasksForCandidate(firNamedReferenceWithCandidate.getCandidate());
        FirNamedReference resolvedReference = toResolvedReference(firNamedReferenceWithCandidate);
        if (!(FirTypeUtilsKt.getResolvedType(equalityOperatorCall) instanceof ConeErrorType) && (resolvedReference instanceof FirResolvedErrorReference)) {
            ConeDiagnostic diagnostic = ((FirResolvedErrorReference) resolvedReference).getDiagnostic();
            if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                AbstractCallCandidate<?> candidate4 = ((ConeConstraintSystemHasContradiction) diagnostic).getCandidate();
                candidate4.getClass();
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                Set<ConeResolutionAtom> setKeySet = ((Candidate) candidate4).getArgumentMapping().keySet();
                setKeySet.getClass();
                List listUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAtoms, 10));
                Iterator it = listUnwrapAtoms.iterator();
                while (it.hasNext()) {
                    arrayList.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
                }
                ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
                if (coneKotlinTypeCommonSuperTypeOrNull != null && !ConeTypeUtilsKt.hasError(coneKotlinTypeCommonSuperTypeOrNull)) {
                    equalityOperatorCall.replaceConeTypeOrNull(coneKotlinTypeCommonSuperTypeOrNull);
                }
            }
        }
        equalityOperatorCall.replaceCalleeReference(resolvedReference);
        return equalityOperatorCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, ExpectedArgumentType data) {
        errorAnnotationCall.getClass();
        return transformAnnotationCall((FirAnnotationCall) errorAnnotationCall, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformFunctionCall(FirFunctionCall functionCall, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirAnonymousFunction anonymousFunction;
        FirNamedFunction firNamedFunction;
        functionCall.getClass();
        FirNamedReference calleeReference = functionCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return functionCall;
        }
        Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
        if (this.mode == Mode.TopLevelSyntheticCallInPclaCompletion) {
            transformArgumentList(functionCall, createArgumentsMapping(candidate, firNamedReferenceWithCandidate.isError()));
            return functionCall;
        }
        FirFunctionCall firFunctionCall = (FirFunctionCall) prepareQualifiedTransform(functionCall, firNamedReferenceWithCandidate);
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firFunctionCall);
        ConeSubstitutor coneSubstitutorPrepareCustomReturnTypeSubstitutorForFunctionCall = prepareCustomReturnTypeSubstitutorForFunctionCall(candidate);
        if (coneSubstitutorPrepareCustomReturnTypeSubstitutorForFunctionCall == null) {
            coneSubstitutorPrepareCustomReturnTypeSubstitutorForFunctionCall = this.finalSubstitutor;
        }
        ConeKotlinType coneKotlinTypeSubstituteType = substituteType(resolvedType, candidate, coneSubstitutorPrepareCustomReturnTypeSubstitutorForFunctionCall);
        ResultingArgumentsMapping resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default = handleVarargsAndReturnResultingArgumentsMapping$default(this, candidate, computeAllArguments$default(this, firNamedReferenceWithCandidate, argumentList, null, 2, null), null, 2, null);
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent1 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default.component1();
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMapComponent2 = resultingArgumentsMappingHandleVarargsAndReturnResultingArgumentsMapping$default.component2();
        if (firNamedReferenceWithCandidate.isError()) {
            firFunctionCall.replaceArgumentList(FirArgumentUtilKt.buildArgumentListForErrorCall(argumentList, linkedHashMapComponent2));
        } else {
            FirResolvedArgumentList firResolvedArgumentListBuildResolvedArgumentList = FirArgumentUtilKt.buildResolvedArgumentList(argumentList, linkedHashMapComponent1);
            FirBasedSymbol<?> symbol = candidate.getSymbol();
            FirNamedFunctionSymbol firNamedFunctionSymbol = symbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) symbol : null;
            boolean z = true;
            if ((firNamedFunctionSymbol == null || (firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir()) == null || !firNamedFunction.getStatus().isInline()) && !TransformUtilsKt.isArrayConstructorWithLambda(symbol)) {
                z = false;
            }
            for (Map.Entry<FirExpression, FirValueParameter> entry : firResolvedArgumentListBuildResolvedArgumentList.getMapping().entrySet()) {
                FirExpression key = entry.getKey();
                FirValueParameter value = entry.getValue();
                FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(key);
                FirAnonymousFunctionExpression firAnonymousFunctionExpression = firExpressionUnwrapArgument instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) firExpressionUnwrapArgument : null;
                if (firAnonymousFunctionExpression != null && (anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction()) != null) {
                    TransformUtilsKt.transformInlineStatus(anonymousFunction, value, z, getSession());
                }
            }
            firFunctionCall.replaceArgumentList(firResolvedArgumentListBuildResolvedArgumentList);
        }
        ExpectedArgumentType.ArgumentsMap argumentsMapCreateArgumentsMapping = createArgumentsMapping(candidate, firNamedReferenceWithCandidate.isError());
        transformArgumentList(firFunctionCall, argumentsMapCreateArgumentsMapping);
        firFunctionCall.transformContextArguments((FirTransformer<? super ExpectedArgumentType.ArgumentsMap>) this, argumentsMapCreateArgumentsMapping);
        updateExplicitContextArgumentsFromArgumentList(firFunctionCall, candidate);
        firFunctionCall.replaceConeTypeOrNull(coneKotlinTypeSubstituteType);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeSubstituteType, functionCall.getSource(), this.context.getFile().getSource());
        }
        if (this.enableArrayOfCallTransformation) {
            return this.arrayOfCallTransformer.transformFunctionCall(firFunctionCall, getSession());
        }
        FirCallCompletionResultsWriterTransformerKt.addNonFatalDiagnostics(firFunctionCall, candidate);
        return firFunctionCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, ExpectedArgumentType data) {
        integerLiteralOperatorCall.getClass();
        ConeKotlinType expectedType = data != null ? FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, integerLiteralOperatorCall) : null;
        return expectedType instanceof ConeIntegerConstantOperatorType ? integerLiteralOperatorCall : (FirStatement) FirTransformerUtilKt.transformSingle(integerLiteralOperatorCall, this.integerOperatorApproximator, expectedType);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLiteralExpression(FirLiteralExpression literalExpression, ExpectedArgumentType data) {
        literalExpression.getClass();
        ConeKotlinType expectedType = data != null ? FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, literalExpression) : null;
        return expectedType instanceof ConeIntegerConstantOperatorType ? literalExpression : (FirStatement) FirTransformerUtilKt.transformSingle(literalExpression, this.integerOperatorApproximator, expectedType);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, ExpectedArgumentType data) {
        Map<FirElement, FirExpression> argumentReplacements;
        FirExpression firExpression;
        propertyAccessExpression.getClass();
        return (data == null || (argumentReplacements = data.getArgumentReplacements()) == null || (firExpression = argumentReplacements.get(propertyAccessExpression)) == null) ? transformQualifiedAccessExpression((FirQualifiedAccessExpression) propertyAccessExpression, data) : (FirStatement) FirTransformerUtilKt.transformSingle(firExpression, this, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        qualifiedAccessExpression.getClass();
        FirReference calleeReference = qualifiedAccessExpression.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return qualifiedAccessExpression;
        }
        FirQualifiedAccessExpression firQualifiedAccessExpressionPrepareQualifiedTransform = prepareQualifiedTransform(qualifiedAccessExpression, firNamedReferenceWithCandidate);
        Candidate candidate = firNamedReferenceWithCandidate.getCandidate();
        ConeKotlinType coneKotlinTypeSubstituteType$default = substituteType$default(this, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpressionPrepareQualifiedTransform), candidate, null, 2, null);
        PhaseUtilsKt.ensureResolvedTypeDeclaration$default(coneKotlinTypeSubstituteType$default, getSession(), (FirResolvePhase) null, 2, (Object) null);
        firQualifiedAccessExpressionPrepareQualifiedTransform.replaceConeTypeOrNull(coneKotlinTypeSubstituteType$default);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeSubstituteType$default, qualifiedAccessExpression.getSource(), this.context.getFile().getSource());
        }
        FirCallCompletionResultsWriterTransformerKt.addNonFatalDiagnostics(firQualifiedAccessExpressionPrepareQualifiedTransform, candidate);
        return firQualifiedAccessExpressionPrepareQualifiedTransform;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReturnExpression(FirReturnExpression returnExpression, ExpectedArgumentType data) {
        returnExpression.getClass();
        FirFunction firFunction = (FirFunction) returnExpression.getTarget().getLabeledElement();
        if (firFunction instanceof FirAnonymousFunction) {
            return returnExpression;
        }
        FirResolvedTypeRef returnTypeRef = firFunction.getReturnTypeRef();
        ExpectedArgumentType expectedType = null;
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            coneType = null;
        }
        if (coneType != null) {
            expectedType = FirCallCompletionResultsWriterTransformerKt.toExpectedType(coneType, data != null ? data.getArgumentReplacements() : null);
        }
        return super.transformReturnExpression(returnExpression, expectedType);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSafeCallExpression(FirSafeCallExpression safeCallExpression, ExpectedArgumentType data) {
        ConeKotlinType expectedType;
        safeCallExpression.getClass();
        safeCallExpression.transformSelector(this, (data == null || (expectedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, safeCallExpression)) == null) ? null : FirCallCompletionResultsWriterTransformerKt.toExpectedType(expectedType, data.getArgumentReplacements()));
        ResolveUtilsKt.propagateTypeFromQualifiedAccessAfterNullCheck(safeCallExpression, getSession(), this.context.getFile());
        return safeCallExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSmartCastExpression(FirSmartCastExpression smartCastExpression, ExpectedArgumentType data) {
        smartCastExpression.getClass();
        return smartCastExpression.transformOriginalExpression(this, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTryExpression(FirTryExpression tryExpression, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        Candidate candidate;
        FirBasedSymbol<?> symbol;
        tryExpression.getClass();
        boolean zWasExpectedTypeAddedAsEqualityForSyntheticCall = wasExpectedTypeAddedAsEqualityForSyntheticCall(tryExpression);
        FirReference calleeReference = tryExpression.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        FirDeclaration fir = (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null || (symbol = candidate.getSymbol()) == null) ? null : symbol.getFir();
        FirNamedFunction firNamedFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firNamedReferenceWithCandidate == null || firNamedFunction == null) {
            if (data == null || (resolvedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, tryExpression)) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(tryExpression);
            }
            Candidate candidate2 = CandidateFactoryKt.candidate(tryExpression);
            ExpectedArgumentType expectedType = FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType, candidate2 != null ? candidate2.getArgumentReplacements() : null);
            tryExpression.transformCalleeReference((FirTransformer<? super ExpectedArgumentType>) this, expectedType);
            tryExpression.transformTryBlock(this, expectedType);
            tryExpression.transformCatches(this, expectedType);
        } else {
            replaceTypeWithSubstituted(tryExpression, firNamedReferenceWithCandidate, this.typeCalculator.tryCalculateReturnType(firNamedFunction));
            if (data == null || (resolvedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, tryExpression)) == null) {
                resolvedType2 = FirTypeUtilsKt.getResolvedType(tryExpression);
            }
            Candidate candidate3 = CandidateFactoryKt.candidate(tryExpression);
            ExpectedArgumentType expectedType2 = FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType2, candidate3 != null ? candidate3.getArgumentReplacements() : null);
            tryExpression.transformCalleeReference((FirTransformer<? super ExpectedArgumentType>) this, expectedType2);
            tryExpression.transformTryBlock(this, expectedType2);
            tryExpression.transformCatches(this, expectedType2);
            runPCLARelatedTasksForCandidate(firNamedReferenceWithCandidate.getCandidate());
            FirNamedReference resolvedReference = toResolvedReference(firNamedReferenceWithCandidate);
            if (!(FirTypeUtilsKt.getResolvedType(tryExpression) instanceof ConeErrorType) && (resolvedReference instanceof FirResolvedErrorReference)) {
                ConeDiagnostic diagnostic = ((FirResolvedErrorReference) resolvedReference).getDiagnostic();
                if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                    AbstractCallCandidate<?> candidate4 = ((ConeConstraintSystemHasContradiction) diagnostic).getCandidate();
                    candidate4.getClass();
                    ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                    Set<ConeResolutionAtom> setKeySet = ((Candidate) candidate4).getArgumentMapping().keySet();
                    setKeySet.getClass();
                    List listUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAtoms, 10));
                    Iterator it = listUnwrapAtoms.iterator();
                    while (it.hasNext()) {
                        arrayList.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
                    }
                    ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
                    if (coneKotlinTypeCommonSuperTypeOrNull != null && !ConeTypeUtilsKt.hasError(coneKotlinTypeCommonSuperTypeOrNull)) {
                        tryExpression.replaceConeTypeOrNull(coneKotlinTypeCommonSuperTypeOrNull);
                    }
                }
            }
            tryExpression.replaceCalleeReference(resolvedReference);
        }
        if (zWasExpectedTypeAddedAsEqualityForSyntheticCall && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EqualityConstraintForOperatorsUnderAssignments)) {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            listCreateListBuilder.add(FirTypeUtilsKt.getResolvedType(tryExpression.getTryBlock()));
            List list = listCreateListBuilder;
            Iterator<T> it2 = tryExpression.getCatches().iterator();
            while (it2.hasNext()) {
                list.add(FirTypeUtilsKt.getResolvedType(((FirCatch) it2.next()).getBlock()));
            }
            List<? extends ConeKotlinType> listBuild = CollectionsKt.build(listCreateListBuilder);
            if (listBuild != null) {
                addRefinedTypeForDataFlow(tryExpression, listBuild);
            }
        }
        return tryExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, ExpectedArgumentType data) {
        ConeKotlinType expectedType;
        varargArgumentsExpression.getClass();
        varargArgumentsExpression.transformChildren(this, (data == null || (expectedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, varargArgumentsExpression)) == null) ? null : new ExpectedArgumentType.ExpectedType(expectedType, data.getArgumentReplacements()));
        return varargArgumentsExpression;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhenExpression(FirWhenExpression whenExpression, ExpectedArgumentType data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        Candidate candidate;
        FirBasedSymbol<?> symbol;
        whenExpression.getClass();
        boolean zWasExpectedTypeAddedAsEqualityForSyntheticCall = wasExpectedTypeAddedAsEqualityForSyntheticCall(whenExpression);
        FirReference calleeReference = whenExpression.getCalleeReference();
        ArrayList arrayList = null;
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        FirDeclaration fir = (firNamedReferenceWithCandidate == null || (candidate = firNamedReferenceWithCandidate.getCandidate()) == null || (symbol = candidate.getSymbol()) == null) ? null : symbol.getFir();
        FirNamedFunction firNamedFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firNamedReferenceWithCandidate == null || firNamedFunction == null) {
            if (data == null || (resolvedType = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, whenExpression)) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(whenExpression);
            }
            Candidate candidate2 = CandidateFactoryKt.candidate(whenExpression);
            whenExpression.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType, candidate2 != null ? candidate2.getArgumentReplacements() : null));
        } else {
            replaceTypeWithSubstituted(whenExpression, firNamedReferenceWithCandidate, this.typeCalculator.tryCalculateReturnType(firNamedFunction));
            if (data == null || (resolvedType2 = FirCallCompletionResultsWriterTransformerKt.getExpectedType(data, whenExpression)) == null) {
                resolvedType2 = FirTypeUtilsKt.getResolvedType(whenExpression);
            }
            Candidate candidate3 = CandidateFactoryKt.candidate(whenExpression);
            whenExpression.transformChildren(this, FirCallCompletionResultsWriterTransformerKt.toExpectedType(resolvedType2, candidate3 != null ? candidate3.getArgumentReplacements() : null));
            runPCLARelatedTasksForCandidate(firNamedReferenceWithCandidate.getCandidate());
            FirNamedReference resolvedReference = toResolvedReference(firNamedReferenceWithCandidate);
            if (!(FirTypeUtilsKt.getResolvedType(whenExpression) instanceof ConeErrorType) && (resolvedReference instanceof FirResolvedErrorReference)) {
                ConeDiagnostic diagnostic = ((FirResolvedErrorReference) resolvedReference).getDiagnostic();
                if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                    AbstractCallCandidate<?> candidate4 = ((ConeConstraintSystemHasContradiction) diagnostic).getCandidate();
                    candidate4.getClass();
                    ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                    Set<ConeResolutionAtom> setKeySet = ((Candidate) candidate4).getArgumentMapping().keySet();
                    setKeySet.getClass();
                    List listUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms((Collection<? extends ConeResolutionAtom>) setKeySet);
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listUnwrapAtoms, 10));
                    Iterator it = listUnwrapAtoms.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(FirTypeUtilsKt.getResolvedType((FirExpression) it.next()));
                    }
                    ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList2);
                    if (coneKotlinTypeCommonSuperTypeOrNull != null && !ConeTypeUtilsKt.hasError(coneKotlinTypeCommonSuperTypeOrNull)) {
                        whenExpression.replaceConeTypeOrNull(coneKotlinTypeCommonSuperTypeOrNull);
                    }
                }
            }
            whenExpression.replaceCalleeReference(resolvedReference);
        }
        if (zWasExpectedTypeAddedAsEqualityForSyntheticCall && LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EqualityConstraintForOperatorsUnderAssignments)) {
            if (ExhaustivenessStatusKt.isProperlyExhaustive(whenExpression)) {
                List<FirWhenBranch> branches = whenExpression.getBranches();
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(branches, 10));
                Iterator<T> it2 = branches.iterator();
                while (it2.hasNext()) {
                    arrayList.add(FirTypeUtilsKt.getResolvedType(((FirWhenBranch) it2.next()).getResult()));
                }
            }
            if (arrayList != null) {
                addRefinedTypeForDataFlow(whenExpression, arrayList);
            }
        }
        BodyResolveUtilsKt.replaceReturnTypeIfNotExhaustive(whenExpression, getSession());
        return whenExpression;
    }

    private final ConeKotlinType substitute(FirTypeRef firTypeRef, Candidate candidate) {
        return substitute(FirTypeUtilsKt.getConeType(firTypeRef), candidate);
    }

    public /* synthetic */ FirCallCompletionResultsWriterTransformer(FirSession firSession, ScopeSession scopeSession, ConeSubstitutor coneSubstitutor, ReturnTypeCalculator returnTypeCalculator, ConeTypeApproximator coneTypeApproximator, FirDataFlowAnalyzer firDataFlowAnalyzer, IntegerLiteralAndOperatorApproximationTransformer integerLiteralAndOperatorApproximationTransformer, FirSamResolver firSamResolver, BodyResolveContext bodyResolveContext, Mode mode, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, coneSubstitutor, returnTypeCalculator, coneTypeApproximator, firDataFlowAnalyzer, integerLiteralAndOperatorApproximationTransformer, firSamResolver, bodyResolveContext, (i & 512) != 0 ? Mode.Normal : mode, (i & 1024) != 0 ? false : z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final FirBasedSymbol<?> updateSubstitutedMemberIfReceiverContainsTypeVariable(FirBasedSymbol<?> firBasedSymbol, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration firCallableDeclaration;
        ConeSimpleKotlinType dispatchReceiverType;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        if (this.mode != Mode.DelegatedPropertyCompletion && !z) {
            return null;
        }
        FirDeclaration fir = firBasedSymbol.getFir();
        if (!(fir instanceof FirCallableDeclaration) || (dispatchReceiverType = (firCallableDeclaration = (FirCallableDeclaration) fir).getDispatchReceiverType()) == null || (coneKotlinTypeSubstituteOrNull = this.finalSubstitutor.substituteOrNull(dispatchReceiverType)) == null) {
            return null;
        }
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(this, coneKotlinTypeSubstituteOrNull, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        final FirClassSubstitutionScope firClassSubstitutionScope = firTypeScopeScope instanceof FirClassSubstitutionScope ? (FirClassSubstitutionScope) firTypeScopeScope : null;
        if (firClassSubstitutionScope == null) {
            return null;
        }
        final FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        if (originalForSubstitutionOverrideAttr == null) {
            return null;
        }
        if (fir instanceof FirSyntheticProperty) {
            FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) fir;
            if ((firSyntheticProperty.getSymbol() instanceof FirSimpleSyntheticPropertySymbol) && (originalForSubstitutionOverrideAttr instanceof FirSyntheticProperty)) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                FirSyntheticPropertiesScope firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined$default = FirSyntheticPropertiesScope.Companion.createIfSyntheticNamesProviderIsDefined$default(FirSyntheticPropertiesScope.INSTANCE, getSession(), coneKotlinTypeSubstituteOrNull, firClassSubstitutionScope, null, false, 24, null);
                if (firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined$default != null) {
                    firSyntheticPropertiesScopeCreateIfSyntheticNamesProviderIsDefined$default.processPropertiesByName(firSyntheticProperty.getName(), new Function1() { // from class: xy4
                        public final Object invoke(Object obj) {
                            return FirCallCompletionResultsWriterTransformer.f(originalForSubstitutionOverrideAttr, objectRef, (FirVariableSymbol) obj);
                        }
                    });
                }
                FirBasedSymbol<?> firBasedSymbol2 = (FirBasedSymbol) objectRef.element;
                if (firBasedSymbol2 != null) {
                    return firBasedSymbol2;
                }
                f2f.a("Not found synthetic property: ", UtilsKt.renderWithType(fir));
                return null;
            }
        }
        return findSingleSubstitutedSymbolWithOriginal(originalForSubstitutionOverrideAttr.getSymbol(), new Function1() { // from class: yy4
            public final Object invoke(Object obj) {
                return FirCallCompletionResultsWriterTransformer.b(originalForSubstitutionOverrideAttr, firClassSubstitutionScope, (Function1) obj);
            }
        });
    }
}
