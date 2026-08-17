package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirIdeOnly;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveState;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousSuper;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirOperationNameConventions;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedErrorAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirResolvedReifiedParameterReference;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirDesugaredAssignmentValueReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionTypeConversionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirIntegerLiteralOperatorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVarargArgumentsExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVariableAssignmentBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirAnnotationArgumentMappingImplKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.extensions.FirAssignExpressionAltererExtension;
import org.jetbrains.kotlin.fir.extensions.FirAssignExpressionAltererExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirFunctionCallRefinementExtension;
import org.jetbrains.kotlin.fir.extensions.FirFunctionCallRefinementExtensionKt;
import org.jetbrains.kotlin.fir.extensions.OriginalCallData;
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionKt;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorageKt;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolutionModeKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitDispatchReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.InaccessibleImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.resolve.calls.SuperCallsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.calls.stages.FirArgumentsToParametersMapperKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguousAlteredAssign;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInaccessibleOuterClass;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInstanceAccessBeforeSuperCall;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNoTypeArgumentsOnRhsError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeOperatorAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSpecificTypeResolverTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.LambdaArgumentEffectsTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorTypeImpl;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantTypeImpl;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitorVoid;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.TransformData;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.inference.InferenceUtilsKt;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ü\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0018\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0006ä\u0001å\u0001æ\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J0\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&H\u0002J(\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u001eH\u0002J \u0010.\u001a\u00020/*\u00020\u001c2\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u001eH\u0003b\u0002\b2J\u0014\u00103\u001a\u00020\u001c*\u00020\u001c2\u0006\u00104\u001a\u00020\u001eH\u0002J\u0010\u00105\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001cH\u0002J\"\u00106\u001a\u0004\u0018\u00010\u001c2\u0006\u00107\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u00108\u001a\u00020&H\u0002J\u0018\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020;2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001d\u0010<\u001a\u0002H=\"\b\b\u0000\u0010=*\u00020$2\u0006\u0010#\u001a\u0002H=¢\u0006\u0002\u0010>J\u001c\u0010?\u001a\u00020\u001c*\u00020\u001c2\u0006\u00104\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020&H\u0002J\u0018\u0010@\u001a\u00020\u001a2\u0006\u0010A\u001a\u0002012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J0\u0010B\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010C\u001a\u00020D2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u0018\u0010E\u001a\u00020$2\u0006\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010$J \u0010I\u001a\u00020$2\u0006\u0010J\u001a\u00020K2\u0006\u0010F\u001a\u00020G2\u0006\u0010L\u001a\u00020MH\u0002J\f\u0010N\u001a\u00020&*\u00020$H\u0014J\u0018\u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020G2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010Q\u001a\u00020\u001a2\u0006\u0010R\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J*\u0010S\u001a\u0002HT\"\u0004\b\u0000\u0010T2\u0006\u0010R\u001a\u00020\r2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002HT0VH\u0082\b¢\u0006\u0002\u0010WJ\u0018\u0010X\u001a\u00020\u001a2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010[\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020]2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J%\u0010^\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020]2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010_\u001a\u00020`H\u0000¢\u0006\u0002\baJ\u0018\u0010b\u001a\u00020/2\u0006\u0010c\u001a\u00020]2\u0006\u0010\u001d\u001a\u00020dH\u0002J\u0018\u0010e\u001a\u00020/2\u0006\u0010c\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010hJ\u0014\u0010i\u001a\u00020]*\u00020]2\u0006\u00104\u001a\u00020\u001eH\u0002J\u0018\u0010j\u001a\u00020\u001a2\u0006\u0010U\u001a\u00020k2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001d\u0010l\u001a\u00020/2\u0006\u0010U\u001a\u00020k2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\bmJ\u0018\u0010n\u001a\u00020\u001a2\u0006\u0010o\u001a\u00020p2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010q\u001a\u00020\u001a2\u0006\u0010r\u001a\u00020s2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010t\u001a\u00020\u001a2\u0006\u0010u\u001a\u00020v2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010w\u001a\u00020\u001a2\u0006\u0010x\u001a\u00020y2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010z\u001a\u00020\u001a2\u0006\u0010{\u001a\u00020|2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\f\u0010}\u001a\u00020&*\u00020\u001cH\u0002J\u0010\u0010~\u001a\u00020\u001e2\u0006\u0010\u007f\u001a\u00020\u001cH\u0002J\r\u0010\u0080\u0001\u001a\u00020]*\u00020]H\u0002J\"\u0010\u0081\u0001\u001a\u00030\u0082\u0001*\u00030\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020\u001c2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u001b\u0010\u0086\u0001\u001a\u00020\u001a2\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u000f\u0010\u0089\u0001\u001a\u00030\u0088\u0001*\u00030\u0088\u0001H\u0002J\u000e\u0010\u008a\u0001\u001a\u00020/*\u00030\u0088\u0001H\u0002J\u001b\u0010\u008b\u0001\u001a\u00020\u001a2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\r\u0010\u008e\u0001\u001a\u00020&*\u00020KH\u0002J\u001b\u0010\u008f\u0001\u001a\u00020\u001a2\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010\u0092\u0001\u001a\u00020\u001a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010\u0095\u0001\u001a\u00020\u001a2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010\u0098\u0001\u001a\u00020\u001a2\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0013\u0010\u009b\u0001\u001a\u00020&2\b\u0010\u0099\u0001\u001a\u00030\u009a\u0001H\u0014J\u001b\u0010\u009c\u0001\u001a\u00020\u001a2\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010\u009f\u0001\u001a\u00020\u001a2\b\u0010 \u0001\u001a\u00030¡\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010¢\u0001\u001a\u00020\u001a2\b\u0010£\u0001\u001a\u00030¤\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0013\u0010¥\u0001\u001a\u00020/2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0002J\u001b\u0010¦\u0001\u001a\u00020\u001a2\b\u0010§\u0001\u001a\u00030¨\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010©\u0001\u001a\u00020\u001a2\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010¬\u0001\u001a\u00020\u001a2\b\u0010\u00ad\u0001\u001a\u00030®\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0016\u0010±\u0001\u001a\u0005\u0018\u00010²\u00012\b\u0010³\u0001\u001a\u00030\u0082\u0001H\u0002J\u001b\u0010´\u0001\u001a\u00020\u001a2\b\u0010µ\u0001\u001a\u00030¶\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J4\u0010·\u0001\u001a\u00070¸\u0001R\u00020\u00002\b\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010¹\u0001\u001a\u00020]2\u0007\u0010º\u0001\u001a\u00020\u001c2\b\u0010»\u0001\u001a\u00030¼\u0001H\u0002J\u001b\u0010½\u0001\u001a\u00020\u001c2\u0007\u0010¾\u0001\u001a\u00020$2\u0007\u0010¿\u0001\u001a\u00020\u001cH\u0002J\r\u0010À\u0001\u001a\u00020\u001c*\u00020\u001cH\u0002J&\u0010Æ\u0001\u001a\u00030Ç\u0001*\u00030Ç\u00012\u0014\u0010È\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c0É\u0001H\u0082\bJ\u001b\u0010Ê\u0001\u001a\u00020\u001a2\b\u0010Ë\u0001\u001a\u00030Ì\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010Í\u0001\u001a\u00020\u001a2\b\u0010Ë\u0001\u001a\u00030Ì\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001b\u0010Î\u0001\u001a\u00020\u001a2\b\u0010Ï\u0001\u001a\u00030Ð\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010Ñ\u0001\u001a\u00020\u001a2\b\u0010Ò\u0001\u001a\u00030Ó\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010Ô\u0001\u001a\u00020\u001a2\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010×\u0001\u001a\u00020\u001a2\b\u0010Ø\u0001\u001a\u00030Ù\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010Ú\u0001\u001a\u00020\u001a2\b\u0010Û\u0001\u001a\u00030Ü\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001b\u0010Ý\u0001\u001a\u00020\u001a2\b\u0010Þ\u0001\u001a\u00030ß\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J!\u0010à\u0001\u001a\u00020/2\u0007\u0010á\u0001\u001a\u00020$2\u0007\u0010â\u0001\u001a\u00020&H\u0000¢\u0006\u0003\bã\u0001R\u0015\u0010\u0006\u001a\u00020\u00078Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0013X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0018R\u001c\u0010¯\u0001\u001a\u00020&*\u00030«\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R4\u0010Á\u0001\u001a\u00020&*\u00020\u001e8BX\u0082\u0004Â\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000¢\u0006\u0010\u0012\u0006\bÂ\u0001\u0010Ã\u0001\u001a\u0006\bÄ\u0001\u0010Å\u0001ò\u0001\u0004\n\u00020d¨\u0006ç\u0001²\u0006\u0017\u0010è\u0001\u001a\u000e\u0012\t\u0012\u0007\u0012\u0002\b\u00030ê\u00010é\u0001X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirPartialBodyResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "builtinTypes", "Lorg/jetbrains/kotlin/fir/BuiltinTypes;", "getBuiltinTypes", "()Lorg/jetbrains/kotlin/fir/BuiltinTypes;", "arrayOfCallTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirArrayOfCallTransformer;", "containingSafeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "getContainingSafeCallExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "setContainingSafeCallExpression", "(Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;)V", "assignAltererExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirAssignExpressionAltererExtension;", "callRefinementExtensions", "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "getCallRefinementExtensions$annotations", "()V", "transformExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "transformQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "isUsedAsReceiver", Argument.Delimiters.none, "isUsedAsGetClassReceiver", "isUsedForContextSensitiveAlternative", "handleContextSensitiveResolution", "resolvedPropertyAccess", "expressionBeforeResolution", "isForContextSensitiveAlternative", "mode", "prepareContextSensitiveAlternativeIfNeeded", Argument.Delimiters.none, "original", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "addSmartcastIfNeeded", "resolutionMode", "transformExpressionUsingSmartcastInfo", "runContextSensitiveResolutionIfNeeded", "originalExpression", "forceResolutionInIdeMode", "transformQualifiedErrorAccessExpression", "qualifiedErrorAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;", "transformExplicitReceiverOf", "Q", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "transformAsExplicitReceiver", "transformPropertyAccessExpression", "propertyAccessExpression", "resolveQualifiedAccessAndSelectCandidate", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformSuperReceiver", "superReferenceContainer", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "containingCall", "markSuperReferenceError", "superNotAvailableDiagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "superReference", "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "isAcceptableResolvedQualifiedAccess", "transformSuperReceiverExpression", "superReceiverExpression", "transformSafeCallExpression", "safeCallExpression", "withContainingSafeCallExpression", "T", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "transformCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "transformFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "transformFunctionCallInternal", "callResolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$CallResolutionMode;", "transformFunctionCallInternal$org_jetbrains_kotlin_resolve", "transformCallArgumentsInsideAnnotationContext", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType;", "transformAnnotationCallArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "constructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "transformToIntegerOperatorCallOrApproximateItIfNeeded", "transformBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformBlockInCurrentScope", "transformBlockInCurrentScope$org_jetbrains_kotlin_resolve", "transformThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "transformComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "transformAugmentedAssignment", "augmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;", "transformIncrementDecrementExpression", "incrementDecrementExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;", "transformEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "hasProperTypeForEqualityOperatorCallArgument", "resolutionModeForEqualityOperatorRhs", "lhsTransformed", "resolveCandidateForAssignmentOperatorCall", "withTypeArgumentsForBareType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "argument", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "transformTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "transformTypeOperatorCallChildren", "resolveConversionTypeRefInContextSensitiveModeIfNecessary", "transformCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "meansAbsenceOfVisibleClass", "transformBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "transformVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "transformCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "transformGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "shouldComputeTypeOfGetClassCallWithNotQualifierInLhs", "transformLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "transformAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "transformAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "evaluateAndReplaceArgumentMapping", "transformErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "transformDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "transformMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "isCallToDelegatedConstructorWithoutArguments", "(Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;)Z", "extractSuperTypeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "typeRef", "transformIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "tryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$IndexedAccessAugmentedAssignmentDesugaringInfo;", "lhsGetCall", "transformedRhs", "fakeSourceElementKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "wrapIntoFunctionConversionsIfNecessary", "temporaryVariableAccess", "initialGetArgument", "unwrapFunctionTypeConversions", "forCollectionLiteralInAnnotationResolution", "getForCollectionLiteralInAnnotationResolution$annotations", "(Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)V", "getForCollectionLiteralInAnnotationResolution", "(Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)Z", "mapArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "transform", "Lkotlin/Function1;", "transformCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "transformCollectionLiteralInAnnotation", "transformStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "transformAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "transformReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "transformReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "transformReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "transformReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "storeTypeFromCallee", "access", "isLhsOfAssignment", "storeTypeFromCallee$org_jetbrains_kotlin_resolve", "CallResolutionMode", "GeneratorOfPlusAssignCalls", "IndexedAccessAugmentedAssignmentDesugaringInfo", "org.jetbrains.kotlin:resolve", "outerClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirExpressionsResolveTransformer extends FirPartialBodyResolveTransformer {
    private final FirArrayOfCallTransformer arrayOfCallTransformer;
    private final List<FirAssignExpressionAltererExtension> assignAltererExtensions;
    private final List<FirFunctionCallRefinementExtension> callRefinementExtensions;
    private FirSafeCallExpression containingSafeCallExpression;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$CallResolutionMode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "REGULAR", "PROVIDE_DELEGATE", "OPTION_FOR_AUGMENTED_ASSIGNMENT", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum CallResolutionMode {
        REGULAR,
        PROVIDE_DELEGATE,
        OPTION_FOR_AUGMENTED_ASSIGNMENT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<CallResolutionMode> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$IndexedAccessAugmentedAssignmentDesugaringInfo;", Argument.Delimiters.none, "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "arrayVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "indexVariables", Argument.Delimiters.none, "operatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "setCall", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "getIndexedAccessAugmentedAssignment", "()Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "getArrayVariable", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getIndexVariables", "()Ljava/util/List;", "getOperatorCall", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getSetCall", "toBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class IndexedAccessAugmentedAssignmentDesugaringInfo {
        private final FirProperty arrayVariable;
        private final List<FirProperty> indexVariables;
        private final FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment;
        private final FirFunctionCall operatorCall;
        private final FirFunctionCall setCall;
        final /* synthetic */ FirExpressionsResolveTransformer this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public IndexedAccessAugmentedAssignmentDesugaringInfo(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirIndexedAccessAugmentedAssignment firIndexedAccessAugmentedAssignment, FirProperty firProperty, List<? extends FirProperty> list, FirFunctionCall firFunctionCall, FirFunctionCall firFunctionCall2) {
            firIndexedAccessAugmentedAssignment.getClass();
            firProperty.getClass();
            list.getClass();
            firFunctionCall.getClass();
            firFunctionCall2.getClass();
            this.this$0 = firExpressionsResolveTransformer;
            this.indexedAccessAugmentedAssignment = firIndexedAccessAugmentedAssignment;
            this.arrayVariable = firProperty;
            this.indexVariables = list;
            this.operatorCall = firFunctionCall;
            this.setCall = firFunctionCall2;
        }

        public final FirProperty getArrayVariable() {
            return this.arrayVariable;
        }

        public final List<FirProperty> getIndexVariables() {
            return this.indexVariables;
        }

        public final FirIndexedAccessAugmentedAssignment getIndexedAccessAugmentedAssignment() {
            return this.indexedAccessAugmentedAssignment;
        }

        public final FirFunctionCall getOperatorCall() {
            return this.operatorCall;
        }

        public final FirFunctionCall getSetCall() {
            return this.setCall;
        }

        public final FirBlock toBlock() {
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            CollectionsKt.addAll(firBlockBuilder.getAnnotations(), this.indexedAccessAugmentedAssignment.getAnnotations());
            firBlockBuilder.getStatements().add(this.arrayVariable);
            CollectionsKt.addAll(firBlockBuilder.getStatements(), this.indexVariables);
            firBlockBuilder.getStatements().add(this.setCall);
            KtSourceElement source = this.indexedAccessAugmentedAssignment.getSource();
            firBlockBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, UtilsKt.toAugmentedAssignSourceKind(this.indexedAccessAugmentedAssignment.getOperation()), null, 2, null) : null);
            FirBlock firBlockMo288build = firBlockBuilder.mo288build();
            firBlockMo288build.replaceConeTypeOrNull(this.this$0.getSession().getBuiltinTypes().getUnitType().getConeType());
            return firBlockMo288build;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[InaccessibleReceiverKind.values().length];
            try {
                iArr[InaccessibleReceiverKind.SecondaryConstructor.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InaccessibleReceiverKind.ClassHeader.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InaccessibleReceiverKind.OuterClassOfNonInner.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CallResolutionMode.values().length];
            try {
                iArr2[CallResolutionMode.PROVIDE_DELEGATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[FirOperation.values().length];
            try {
                iArr3[FirOperation.IS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr3[FirOperation.NOT_IS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr3[FirOperation.AS.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr3[FirOperation.SAFE_AS.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirExpressionsResolveTransformer(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
        super(firAbstractBodyResolveTransformerDispatcher);
        firAbstractBodyResolveTransformerDispatcher.getClass();
        this.arrayOfCallTransformer = new FirArrayOfCallTransformer();
        List<FirAssignExpressionAltererExtension> assignAltererExtensions = FirAssignExpressionAltererExtensionKt.getAssignAltererExtensions(FirExtensionServiceKt.getExtensionService(getSession()));
        this.assignAltererExtensions = assignAltererExtensions.isEmpty() ? null : assignAltererExtensions;
        List<FirFunctionCallRefinementExtension> callRefinementExtensions = FirFunctionCallRefinementExtensionKt.getCallRefinementExtensions(FirExtensionServiceKt.getExtensionService(getSession()));
        this.callRefinementExtensions = callRefinementExtensions.isEmpty() ? null : callRefinementExtensions;
        getTransformer().getComponents().getCallResolver().initTransformer(this);
    }

    private final FirExpression addSmartcastIfNeeded(FirExpression firExpression, ResolutionMode resolutionMode) {
        return !(resolutionMode instanceof ResolutionMode.AssignmentLValue) ? transformExpressionUsingSmartcastInfo(firExpression) : firExpression;
    }

    public static boolean b(FirExpressionsResolveTransformer firExpressionsResolveTransformer, ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return ToSymbolUtilsKt.toSymbol((SessionHolder) firExpressionsResolveTransformer, coneClassLikeType.getLookupTag()) != null;
    }

    public static Set c(FirClassLikeDeclaration firClassLikeDeclaration, FirExpressionsResolveTransformer firExpressionsResolveTransformer) {
        return LookupTagUtilsKt.getClassAndItsOuterClassesWhenLocal(firClassLikeDeclaration.getSymbol(), firExpressionsResolveTransformer.getSession());
    }

    public static boolean d(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return (coneKotlinType instanceof ConeTypeVariableType) || (coneKotlinType instanceof ConeErrorType) || (coneKotlinType instanceof ConeIntegerLiteralType);
    }

    public static TransformData e(int i, ResolutionMode resolutionMode, int i2) {
        if (i2 != i - 1) {
            resolutionMode = ResolutionMode.ContextIndependent.INSTANCE;
        } else if (resolutionMode instanceof ResolutionMode.WithExpectedType) {
            resolutionMode = ResolutionMode.WithExpectedType.copy$default((ResolutionMode.WithExpectedType) resolutionMode, null, true, false, 5, null);
        }
        return new TransformData.Data(resolutionMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void evaluateAndReplaceArgumentMapping(FirAnnotationCall annotationCall) {
        Map<Name, FirEvaluatorResult> mapEvaluateAnnotationArguments = FirExpressionEvaluator.INSTANCE.evaluateAnnotationArguments(annotationCall, getSession(), getComponents().getFile());
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        firAnnotationArgumentMappingBuilder.setSource(annotationCall.getArgumentMapping().getSource());
        firAnnotationArgumentMappingBuilder.getMapping().putAll(annotationCall.getArgumentMapping().getMapping());
        for (Map.Entry<Name, FirEvaluatorResult> entry : mapEvaluateAnnotationArguments.entrySet()) {
            Name key = entry.getKey();
            FirEvaluatorResult value = entry.getValue();
            Map<Name, FirExpression> mapping = firAnnotationArgumentMappingBuilder.getMapping();
            FirElement firElement = null;
            if (!(value instanceof FirEvaluatorResult.CompileTimeException) && (value instanceof FirEvaluatorResult.Evaluated)) {
                FirElement result = ((FirEvaluatorResult.Evaluated) value).getResult();
                firElement = (FirExpression) (result instanceof FirExpression ? result : null);
            }
            if (firElement != null) {
                mapping.put(key, firElement);
            }
        }
        annotationCall.replaceArgumentMapping(firAnnotationArgumentMappingBuilder.build());
    }

    private final FirRegularClass extractSuperTypeDeclaration(FirTypeRef typeRef) {
        if (!(typeRef instanceof FirResolvedTypeRef)) {
            return null;
        }
        FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = DeclarationUtilsKt.firClassLike(typeRef, getSession());
        if (firClassLikeDeclarationFirClassLike instanceof FirRegularClass) {
            return (FirRegularClass) firClassLikeDeclarationFirClassLike;
        }
        if (firClassLikeDeclarationFirClassLike instanceof FirTypeAlias) {
            return extractSuperTypeDeclaration(((FirTypeAlias) firClassLikeDeclarationFirClassLike).getExpandedTypeRef());
        }
        return null;
    }

    private final boolean getForCollectionLiteralInAnnotationResolution(ResolutionMode resolutionMode) {
        ResolutionMode.WithExpectedType withExpectedType = resolutionMode instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) resolutionMode : null;
        return (withExpectedType != null ? withExpectedType.getArrayLiteralPosition() : null) != null;
    }

    private final FirExpression handleContextSensitiveResolution(FirExpression resolvedPropertyAccess, FirQualifiedAccessExpression expressionBeforeResolution, boolean isForContextSensitiveAlternative, ResolutionMode mode) {
        if (!isForContextSensitiveAlternative) {
            FirExpression firExpressionRunContextSensitiveResolutionIfNeeded = runContextSensitiveResolutionIfNeeded(resolvedPropertyAccess, mode, false);
            if (firExpressionRunContextSensitiveResolutionIfNeeded != null) {
                return firExpressionRunContextSensitiveResolutionIfNeeded;
            }
            if (LanguageVersionUtilsKt.isSet(this, AnalysisFlags.getIdeMode()) && (expressionBeforeResolution instanceof FirPropertyAccessExpression)) {
                prepareContextSensitiveAlternativeIfNeeded(resolvedPropertyAccess, (FirPropertyAccessExpression) expressionBeforeResolution, mode);
            }
        }
        return resolvedPropertyAccess;
    }

    private final boolean hasProperTypeForEqualityOperatorCallArgument(FirExpression firExpression) {
        ConeKotlinType coneTypeOrNull = firExpression.getConeTypeOrNull();
        if (coneTypeOrNull == null) {
            return false;
        }
        return !ConeTypeUtilsKt.contains(coneTypeOrNull, new Function1() { // from class: s65
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirExpressionsResolveTransformer.d((ConeKotlinType) obj));
            }
        });
    }

    private final boolean isCallToDelegatedConstructorWithoutArguments(FirDelegatedConstructorCall firDelegatedConstructorCall) {
        KtSourceElement source = firDelegatedConstructorCall.getSource();
        return Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [org.jetbrains.kotlin.fir.types.FirErrorTypeRef, org.jetbrains.kotlin.fir.types.FirTypeRef] */
    private final FirQualifiedAccessExpression markSuperReferenceError(ConeDiagnostic superNotAvailableDiagnostic, FirSuperReceiverExpression superReferenceContainer, FirSuperReference superReference) {
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setDiagnostic(superNotAvailableDiagnostic);
        ?? Build = firErrorTypeRefBuilder.build();
        superReferenceContainer.replaceConeTypeOrNull(Build.getConeType());
        superReference.replaceSuperTypeRef(Build);
        FirErrorSuperReferenceBuilder firErrorSuperReferenceBuilder = new FirErrorSuperReferenceBuilder();
        KtSourceElement source = superReferenceContainer.getSource();
        firErrorSuperReferenceBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null) : null);
        firErrorSuperReferenceBuilder.setDiagnostic(superNotAvailableDiagnostic);
        firErrorSuperReferenceBuilder.setSuperTypeRef(superReference.getSuperTypeRef());
        superReferenceContainer.replaceCalleeReference((FirSuperReference) firErrorSuperReferenceBuilder.build());
        return superReferenceContainer;
    }

    private final boolean meansAbsenceOfVisibleClass(ConeDiagnostic coneDiagnostic) {
        if ((coneDiagnostic instanceof ConeUnresolvedTypeQualifierError) || (coneDiagnostic instanceof ConeVisibilityError)) {
            return true;
        }
        return (coneDiagnostic instanceof ConeAmbiguityError) && !CandidateApplicabilityKt.isSuccess(((ConeAmbiguityError) coneDiagnostic).getApplicability());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @FirIdeOnly
    private final void prepareContextSensitiveAlternativeIfNeeded(FirExpression firExpression, FirPropertyAccessExpression firPropertyAccessExpression, ResolutionMode resolutionMode) {
        if (firPropertyAccessExpression.getExplicitReceiver() == null || (firPropertyAccessExpression.getCalleeReference() instanceof FirErrorNamedReference) || (firPropertyAccessExpression.getCalleeReference() instanceof FirNamedReferenceWithCandidate)) {
            return;
        }
        if ((!(firExpression instanceof FirResolvedQualifier) && !(firExpression instanceof FirPropertyAccessExpression)) || (resolutionMode instanceof ResolutionMode.AssignmentLValue) || (resolutionMode instanceof ResolutionMode.ContextIndependent) || (resolutionMode instanceof ResolutionMode.ReceiverResolution) || (resolutionMode instanceof ResolutionMode.Delegate)) {
            return;
        }
        if (!(resolutionMode instanceof ResolutionMode.ContextDependent) && !(resolutionMode instanceof ResolutionMode.WithExpectedType)) {
            if ((resolutionMode instanceof ResolutionMode.UpdateImplicitTypeRef) || (resolutionMode instanceof ResolutionMode.WithStatus)) {
                w04.a("Unexpected mode for expression: ", resolutionMode);
                return;
            } else {
                bu8.a();
                return;
            }
        }
        if (!(firExpression instanceof FirPropertyAccessExpression) || (((FirPropertyAccessExpression) firExpression).getExplicitReceiver() instanceof FirResolvedQualifier)) {
            Name name = firPropertyAccessExpression.getCalleeReference().getName();
            FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
            firPropertyAccessExpressionBuilder.setExplicitReceiver(null);
            KtSourceElement source = firExpression.getSource();
            firPropertyAccessExpressionBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ContextSensitiveAlternative.INSTANCE, null, 2, null) : null);
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setName(name);
            KtSourceElement source2 = firExpression.getSource();
            firSimpleNamedReferenceBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ReferenceForContextSensitiveAlternative.INSTANCE, null, 2, null) : null);
            firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            FirExpression firExpressionTransformQualifiedAccessExpression = transformQualifiedAccessExpression(firPropertyAccessExpressionBuilder.mo288build(), resolutionMode, false, false, true);
            if (firExpressionTransformQualifiedAccessExpression instanceof FirPropertyAccessExpression) {
                FirPropertyAccessExpression firPropertyAccessExpression2 = (FirPropertyAccessExpression) firExpressionTransformQualifiedAccessExpression;
                if (ContextSensitiveResolutionUtilsKt.shouldBeResolvedInContextSensitiveMode(firPropertyAccessExpression2)) {
                    if ((resolutionMode instanceof ResolutionMode.WithExpectedType) || resolutionMode.getHintForContextSensitiveResolution() != null) {
                        FirExpression firExpressionRunContextSensitiveResolutionIfNeeded = runContextSensitiveResolutionIfNeeded(firExpressionTransformQualifiedAccessExpression, resolutionMode, true);
                        if (firExpressionRunContextSensitiveResolutionIfNeeded != null) {
                            ContextSensitiveResolutionUtilsKt.appendCSRAlternativeDiagnosticIfNeeded((FirQualifierWithContextSensitiveAlternative) firExpression, firExpressionRunContextSensitiveResolutionIfNeeded);
                            return;
                        }
                        return;
                    }
                    if (resolutionMode instanceof ResolutionMode.ContextDependent) {
                        ((FirQualifierWithContextSensitiveAlternative) firExpression).replaceContextSensitiveAlternative(firPropertyAccessExpression2);
                    } else {
                        w04.a("When should be exhaustive: ", resolutionMode);
                    }
                }
            }
        }
    }

    private final ResolutionMode resolutionModeForEqualityOperatorRhs(FirExpression lhsTransformed) {
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ResolveEqualsRhsInDependentContextWithCompletion);
        if (zIsEnabled) {
            return new ResolutionMode.ContextDependent(FirTypeUtilsKt.getResolvedType(lhsTransformed));
        }
        if (!zIsEnabled) {
            return new ResolutionMode.WithExpectedType(getSession().getBuiltinTypes().getNullableAnyType(), false, false, null, FirTypeUtilsKt.getResolvedType(lhsTransformed), false, 46, null);
        }
        bu8.a();
        return null;
    }

    private final FirFunctionCall resolveCandidateForAssignmentOperatorCall(FirFunctionCall firFunctionCall) {
        FirStatement firStatementTransformFunctionCallInternal$org_jetbrains_kotlin_resolve = transformFunctionCallInternal$org_jetbrains_kotlin_resolve(firFunctionCall, ResolutionMode.ContextDependent.INSTANCE, CallResolutionMode.OPTION_FOR_AUGMENTED_ASSIGNMENT);
        firStatementTransformFunctionCallInternal$org_jetbrains_kotlin_resolve.getClass();
        return (FirFunctionCall) firStatementTransformFunctionCallInternal$org_jetbrains_kotlin_resolve;
    }

    private final void resolveConversionTypeRefInContextSensitiveModeIfNecessary(FirTypeOperatorCall firTypeOperatorCall) {
        if (LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ContextSensitiveResolutionUsingExpectedType)) {
            Object conversionTypeRef = firTypeOperatorCall.getConversionTypeRef();
            FirErrorTypeRef firErrorTypeRef = conversionTypeRef instanceof FirErrorTypeRef ? (FirErrorTypeRef) conversionTypeRef : null;
            if (firErrorTypeRef != null && meansAbsenceOfVisibleClass(firErrorTypeRef.getDiagnostic())) {
                FirTypeRef delegatedTypeRef = firErrorTypeRef.getDelegatedTypeRef();
                FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) delegatedTypeRef : null;
                if (firUserTypeRef != null && firUserTypeRef.getQualifier().size() == 1) {
                    FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(firTypeOperatorCall.getArgumentList().getArguments());
                    if (firExpression == null) {
                        f2f.a("Not a single argument: ", UtilsKt.render(firTypeOperatorCall));
                        return;
                    }
                    for (FirRegularClassSymbol firRegularClassSymbol : ContextSensitiveResolutionKt.getParentChainForContextSensitiveResolutionOfTypes(FirTypeUtilsKt.getResolvedType(firExpression), getSession())) {
                        FirSpecificTypeResolverTransformer typeResolverTransformer = getComponents().getTypeResolverTransformer();
                        boolean areBareTypesAllowed = typeResolverTransformer.getAreBareTypesAllowed();
                        typeResolverTransformer.setAreBareTypesAllowed(true);
                        try {
                            FirResolvedTypeRef firResolvedTypeRefMo600transformTypeRef = getComponents().getTypeResolverTransformer().mo600transformTypeRef((FirTypeRef) firUserTypeRef, TypeResolutionConfiguration.INSTANCE.createForContextSensitiveResolution(getTransformer().getContext().getContainingClassDeclarations(), getTransformer().getContext().getFile(), getTransformer().getContext().getTopContainerForTypeResolution(), firRegularClassSymbol));
                            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
                            if (!(firResolvedTypeRefMo600transformTypeRef instanceof FirErrorTypeRef) && !(firResolvedTypeRefMo600transformTypeRef.getConeType() instanceof ConeErrorType)) {
                                firTypeOperatorCall.replaceConversionTypeRef(firResolvedTypeRefMo600transformTypeRef);
                                return;
                            }
                        } catch (Throwable th) {
                            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
                            throw th;
                        }
                    }
                }
            }
        }
    }

    private final FirExpression runContextSensitiveResolutionIfNeeded(FirExpression originalExpression, ResolutionMode data, boolean forceResolutionInIdeMode) {
        if (!(originalExpression instanceof FirPropertyAccessExpression)) {
            return null;
        }
        if (!forceResolutionInIdeMode && LanguageVersionUtilsKt.isDisabled(this, LanguageFeature.ContextSensitiveResolutionUsingExpectedType)) {
            return null;
        }
        ConeKotlinType hintForContextSensitiveResolution = data.getHintForContextSensitiveResolution();
        if (hintForContextSensitiveResolution == null && (hintForContextSensitiveResolution = ResolutionModeKt.getExpectedType(data)) == null) {
            return null;
        }
        FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) originalExpression;
        if (ContextSensitiveResolutionUtilsKt.shouldBeResolvedInContextSensitiveMode(firPropertyAccessExpression)) {
            return ContextSensitiveResolutionUtilsKt.runContextSensitiveResolutionForPropertyAccess(getTransformer().getComponents(), firPropertyAccessExpression, hintForContextSensitiveResolution);
        }
        return null;
    }

    private final FirExpression transformAsExplicitReceiver(FirExpression firExpression, ResolutionMode resolutionMode, boolean z) {
        FirExpressionsResolveTransformer firExpressionsResolveTransformer;
        ResolutionMode resolutionMode2;
        FirExpression firExpressionTransformQualifiedAccessExpression;
        FirBasedSymbol<?> resolvedSymbol;
        if (firExpression instanceof FirPropertyAccessExpression) {
            firExpressionsResolveTransformer = this;
            resolutionMode2 = resolutionMode;
            firExpressionTransformQualifiedAccessExpression = firExpressionsResolveTransformer.transformQualifiedAccessExpression((FirQualifiedAccessExpression) firExpression, resolutionMode2, true, z, false);
        } else {
            firExpressionsResolveTransformer = this;
            resolutionMode2 = resolutionMode;
            firExpressionTransformQualifiedAccessExpression = (FirExpression) FirTransformerUtilKt.transformSingle(firExpression, firExpressionsResolveTransformer, resolutionMode2);
        }
        if (!(firExpressionTransformQualifiedAccessExpression instanceof FirFunctionCall)) {
            return firExpressionTransformQualifiedAccessExpression;
        }
        FirNamedReference calleeReference = ((FirFunctionCall) firExpressionTransformQualifiedAccessExpression).getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        FirAnnotationContainer fir = (firResolvedNamedReference == null || (resolvedSymbol = firResolvedNamedReference.getResolvedSymbol()) == null) ? null : resolvedSymbol.getFir();
        FirContractDescriptionOwner firContractDescriptionOwner = fir instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) fir : null;
        return (firContractDescriptionOwner != null ? firContractDescriptionOwner.getContractDescription() : null) != null ? firExpressionsResolveTransformer.addSmartcastIfNeeded(firExpressionTransformQualifiedAccessExpression, resolutionMode2) : firExpressionTransformQualifiedAccessExpression;
    }

    private static final FirStatement transformAugmentedAssignment$lambda$0$chooseAssign(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall) {
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().enterFunctionCall(firFunctionCall);
        FirCallCompleter.completeCall$default(firExpressionsResolveTransformer.getComponents().getCallCompleter(), firFunctionCall, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().exitFunctionCall(firFunctionCall, true);
        return firFunctionCall;
    }

    private static final FirStatement transformAugmentedAssignment$lambda$0$chooseOperator(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, FirVariable firVariable, FirExpression firExpression, KtFakeSourceElementKind.DesugaredAugmentedAssign desugaredAugmentedAssign, FirAugmentedAssignment firAugmentedAssignment) {
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().enterFunctionCall(firFunctionCall);
        FirCallCompleter callCompleter = firExpressionsResolveTransformer.getComponents().getCallCompleter();
        KtSourceElement ktSourceElementFakeElement$default = null;
        FirTypeRef returnTypeRef = firVariable != null ? firVariable.getReturnTypeRef() : null;
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) returnTypeRef : null;
        FirCallCompleter.completeCall$default(callCompleter, firFunctionCall, firResolvedTypeRef != null ? new ResolutionMode.WithExpectedType(firResolvedTypeRef, false, false, null, null, false, 62, null) : ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().exitFunctionCall(firFunctionCall, true);
        KtSourceElement source = firExpression.getSource();
        KtSourceElement ktSourceElementFakeElement$default2 = source != null ? KtSourceElementKt.fakeElement$default(source, desugaredAugmentedAssign, null, 2, null) : null;
        FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(firExpression);
        KtSourceElement source2 = firAugmentedAssignment.getSource();
        KtSourceElement ktSourceElementFakeElement$default3 = source2 != null ? KtSourceElementKt.fakeElement$default(source2, desugaredAugmentedAssign, null, 2, null) : null;
        FirDesugaredAssignmentValueReferenceExpressionBuilder firDesugaredAssignmentValueReferenceExpressionBuilder = new FirDesugaredAssignmentValueReferenceExpressionBuilder();
        FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
        firExpressionRef.bind(firExpressionUnwrapSmartcastExpression);
        firDesugaredAssignmentValueReferenceExpressionBuilder.setExpressionRef(firExpressionRef);
        if (ktSourceElementFakeElement$default2 != null) {
            ktSourceElementFakeElement$default = ktSourceElementFakeElement$default2;
        } else if (ktSourceElementFakeElement$default3 != null) {
            ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(ktSourceElementFakeElement$default3, KtFakeSourceElementKind.DesugaredAssignmentLValueSourceIsNull.INSTANCE, null, 2, null);
        }
        firDesugaredAssignmentValueReferenceExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        FirDesugaredAssignmentValueReferenceExpression firDesugaredAssignmentValueReferenceExpressionMo288build = firDesugaredAssignmentValueReferenceExpressionBuilder.mo288build();
        FirVariableAssignmentBuilder firVariableAssignmentBuilder = new FirVariableAssignmentBuilder();
        firVariableAssignmentBuilder.setSource(ktSourceElementFakeElement$default3);
        firVariableAssignmentBuilder.setLValue(firDesugaredAssignmentValueReferenceExpressionMo288build);
        firVariableAssignmentBuilder.setRValue(firFunctionCall);
        CollectionsKt.addAll(firVariableAssignmentBuilder.getAnnotations(), firAugmentedAssignment.getAnnotations());
        FirStatement firStatementMo288build = firVariableAssignmentBuilder.mo288build();
        FirProperty firPropertyGenerateExplicitReceiverTemporaryVariable = FirGenerationKt.generateExplicitReceiverTemporaryVariable(firExpressionsResolveTransformer.getSession(), firExpressionUnwrapSmartcastExpression, ktSourceElementFakeElement$default2);
        if (firPropertyGenerateExplicitReceiverTemporaryVariable != null) {
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            firBlockBuilder.setSource(ktSourceElementFakeElement$default3);
            CollectionsKt.addAll(firBlockBuilder.getAnnotations(), firAugmentedAssignment.getAnnotations());
            firBlockBuilder.getStatements().add(firPropertyGenerateExplicitReceiverTemporaryVariable);
            firBlockBuilder.getStatements().add(firStatementMo288build);
            firStatementMo288build = firBlockBuilder.mo288build();
        }
        return (FirStatement) firStatementMo288build.transform(firExpressionsResolveTransformer.getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
    }

    private static final FirStatement transformAugmentedAssignment$lambda$0$chooseResolved(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2, FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, FirFunctionCall firFunctionCall2, FirVariable firVariable, FirExpression firExpression, KtFakeSourceElementKind.DesugaredAugmentedAssign desugaredAugmentedAssign, FirAugmentedAssignment firAugmentedAssignment) {
        FirErrorReferenceWithCandidate firErrorReferenceWithCandidate = firNamedReferenceWithCandidate instanceof FirErrorReferenceWithCandidate ? (FirErrorReferenceWithCandidate) firNamedReferenceWithCandidate : null;
        boolean z = (firErrorReferenceWithCandidate != null ? firErrorReferenceWithCandidate.getDiagnostic() : null) instanceof ConeUnresolvedNameError;
        FirErrorReferenceWithCandidate firErrorReferenceWithCandidate2 = firNamedReferenceWithCandidate2 instanceof FirErrorReferenceWithCandidate ? (FirErrorReferenceWithCandidate) firNamedReferenceWithCandidate2 : null;
        boolean z2 = (firErrorReferenceWithCandidate2 != null ? firErrorReferenceWithCandidate2.getDiagnostic() : null) instanceof ConeUnresolvedNameError;
        if (z && !z2) {
            return transformAugmentedAssignment$lambda$0$chooseOperator(firExpressionsResolveTransformer, firFunctionCall2, firVariable, firExpression, desugaredAugmentedAssign, firAugmentedAssignment);
        }
        return transformAugmentedAssignment$lambda$0$chooseAssign(firExpressionsResolveTransformer, firFunctionCall);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean transformAugmentedAssignment$lambda$0$operatorReturnTypeMatches(FirFunctionCall firFunctionCall, FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirExpression firExpression, Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firFunctionCall);
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(firExpressionsResolveTransformer.getSession()), ((ConeSubstitutor) InferenceUtilsKt.buildAbstractResultingSubstitutor$default(candidate.getSystem().currentStorage(), candidate.getSystem().getTypeSystemContext(), false, 2, (Object) null)).substituteOrSelf(resolvedType), FirTypeUtilsKt.getResolvedType(firExpression), false, 8, (Object) null);
    }

    private static final FirStatement transformAugmentedAssignment$lambda$0$reportAmbiguity(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2, FirAugmentedAssignment firAugmentedAssignment) {
        Candidate candidate = firNamedReferenceWithCandidate != null ? firNamedReferenceWithCandidate.getCandidate() : null;
        if (candidate == null) {
            w01.a("Required value was null.");
            return null;
        }
        Candidate candidate2 = firNamedReferenceWithCandidate2 != null ? firNamedReferenceWithCandidate2.getCandidate() : null;
        if (candidate2 == null) {
            w01.a("Required value was null.");
            return null;
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(firAugmentedAssignment.getSource());
        firErrorExpressionBuilder.setDiagnostic(new ConeOperatorAmbiguityError(CollectionsKt.listOf(new Candidate[]{candidate, candidate2})));
        return firErrorExpressionBuilder.mo288build();
    }

    private final void transformCallArgumentsInsideAnnotationContext(FirFunctionCall call, ResolutionMode.WithExpectedType data) {
        ConeKotlinType expectedType = data.getExpectedType();
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(this, TypeExpansionUtilsKt.fullyExpandedType(this, expectedType));
        if ((classSymbol != null ? classSymbol.getClassKind() : null) == ClassKind.ANNOTATION_CLASS) {
            transformAnnotationCallArguments(call, getComponents().getCallResolver().getAnnotationConstructorSymbol(expectedType, null));
            return;
        }
        ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(expectedType, false, 1, null);
        ResolutionMode resolutionModeCopy$default = coneKotlinTypeArrayElementType$default != null ? ResolutionMode.WithExpectedType.copy$default(data, UtilsKt.toFirResolvedTypeRef$default(coneKotlinTypeArrayElementType$default, null, null, 3, null), false, false, 6, null) : null;
        FirArgumentList argumentList = call.getArgumentList();
        if (resolutionModeCopy$default == null) {
            resolutionModeCopy$default = ResolutionMode.ContextDependent.INSTANCE;
        }
        call.replaceArgumentList((FirArgumentList) argumentList.transform(this, resolutionModeCopy$default));
    }

    private final FirStatement transformCollectionLiteralInAnnotation(FirCollectionLiteral collectionLiteral, ResolutionMode data) {
        ConeKotlinType coneKotlinTypeConstructClassLikeType$default;
        ResolutionMode resolutionModeWithExpectedType$default;
        if (getForCollectionLiteralInAnnotationResolution(data)) {
            FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
            ResolutionMode.WithExpectedType withExpectedType = (ResolutionMode.WithExpectedType) data;
            ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(withExpectedType.getExpectedType(), false, 1, null);
            if (coneKotlinTypeArrayElementType$default == null || (resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(coneKotlinTypeArrayElementType$default, false, 2, null)) == null) {
                resolutionModeWithExpectedType$default = ResolutionMode.ContextDependent.INSTANCE;
            }
            collectionLiteral.transformChildren(transformer, resolutionModeWithExpectedType$default);
            FirFunctionCall firFunctionCallGenerateSyntheticArrayOfCall = getTransformer().getComponents().getSyntheticCallGenerator().generateSyntheticArrayOfCall(collectionLiteral, withExpectedType.getExpectedType(), getTransformer().getResolutionContext(), data);
            FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), firFunctionCallGenerateSyntheticArrayOfCall, data, false, 4, null);
            return this.arrayOfCallTransformer.transformFunctionCall(firFunctionCallGenerateSyntheticArrayOfCall, getSession());
        }
        collectionLiteral.transformChildren(getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
        ResolutionMode.WithExpectedType withExpectedType2 = data instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) data : null;
        if (withExpectedType2 == null || (coneKotlinTypeConstructClassLikeType$default = withExpectedType2.getExpectedType()) == null) {
            coneKotlinTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getArray(), new ConeClassLikeType[]{StandardTypes.INSTANCE.getAny()}, false, null, 6, null);
        }
        collectionLiteral.replaceConeTypeOrNull(coneKotlinTypeConstructClassLikeType$default);
        FirFunctionCall firFunctionCallGenerateSyntheticIdCall = getTransformer().getComponents().getSyntheticCallGenerator().generateSyntheticIdCall(collectionLiteral, getTransformer().getResolutionContext(), data);
        collectionLiteral.replaceConeTypeOrNull(null);
        FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), firFunctionCallGenerateSyntheticIdCall, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        return collectionLiteral;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002f  */
    /* JADX WARN: Code duplicated, block: B:15:0x0033  */
    /* JADX WARN: Code duplicated, block: B:16:0x0041 A[RETURN] */
    private final FirExpression transformExpressionUsingSmartcastInfo(FirExpression original) {
        FirExpression firExpressionUnwrapDesugaredAssignmentValueRef = FirExpressionUtilKt.unwrapDesugaredAssignmentValueRef(original);
        if (!(firExpressionUnwrapDesugaredAssignmentValueRef instanceof FirFunctionCall)) {
            if (firExpressionUnwrapDesugaredAssignmentValueRef instanceof FirQualifiedAccessExpression) {
                FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firExpressionUnwrapDesugaredAssignmentValueRef;
                KtSourceElement source = firQualifiedAccessExpression.getSource();
                if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ContextSensitiveAlternative.INSTANCE)) {
                    getComponents().getDataFlowAnalyzer().exitQualifiedAccessExpression(firQualifiedAccessExpression);
                } else {
                    if (firExpressionUnwrapDesugaredAssignmentValueRef instanceof FirResolvedQualifier) {
                        return original;
                    }
                    getComponents().getDataFlowAnalyzer().exitResolvedQualifierNode((FirResolvedQualifier) firExpressionUnwrapDesugaredAssignmentValueRef);
                }
            } else {
                if (firExpressionUnwrapDesugaredAssignmentValueRef instanceof FirResolvedQualifier) {
                    return original;
                }
                getComponents().getDataFlowAnalyzer().exitResolvedQualifierNode((FirResolvedQualifier) firExpressionUnwrapDesugaredAssignmentValueRef);
            }
        }
        FirExpression firExpressionTransformExpressionUsingSmartcastInfo = ResolveUtilsKt.transformExpressionUsingSmartcastInfo(getTransformer().getComponents(), original);
        if (firExpressionTransformExpressionUsingSmartcastInfo instanceof FirSmartCastExpression) {
            getComponents().getDataFlowAnalyzer().exitSmartCastExpression((FirSmartCastExpression) firExpressionTransformExpressionUsingSmartcastInfo);
        }
        return firExpressionTransformExpressionUsingSmartcastInfo;
    }

    private static final FirExpression transformIncrementDecrementExpression$buildAndResolveOperatorCall(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirIncrementDecrementExpression firIncrementDecrementExpression, FirExpression firExpression, KtFakeSourceElementKind.DesugaredIncrementOrDecrement desugaredIncrementOrDecrement) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(firIncrementDecrementExpression.getOperationSource());
        firFunctionCallBuilder.setExplicitReceiver(firExpression);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        KtSourceElement operationSource = firIncrementDecrementExpression.getOperationSource();
        firSimpleNamedReferenceBuilder.setSource(operationSource != null ? KtSourceElementKt.fakeElement$default(operationSource, desugaredIncrementOrDecrement, null, 2, null) : null);
        firSimpleNamedReferenceBuilder.setName(firIncrementDecrementExpression.getOperationName());
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return (FirExpression) FirTransformerUtilKt.transformSingle(firFunctionCallBuilder.mo288build(), firExpressionsResolveTransformer.getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
    }

    private static final FirVariableAssignment transformIncrementDecrementExpression$buildAndResolveVariableAssignment(FirExpressionsResolveTransformer firExpressionsResolveTransformer, KtSourceElement ktSourceElement, FirExpression firExpression, KtFakeSourceElementKind.DesugaredIncrementOrDecrement desugaredIncrementOrDecrement, FirExpression firExpression2) {
        FirExpression expression;
        KtSourceElement ktSourceElementFakeElement$default;
        FirVariableAssignmentBuilder firVariableAssignmentBuilder = new FirVariableAssignmentBuilder();
        firVariableAssignmentBuilder.setSource(ktSourceElement);
        FirDesugaredAssignmentValueReferenceExpressionBuilder firDesugaredAssignmentValueReferenceExpressionBuilder = new FirDesugaredAssignmentValueReferenceExpressionBuilder();
        KtSourceElement ktSourceElementFakeElement$default2 = null;
        FirErrorExpression firErrorExpression = firExpression instanceof FirErrorExpression ? (FirErrorExpression) firExpression : null;
        if (firErrorExpression == null || (expression = firErrorExpression.getExpression()) == null) {
            expression = firExpression;
        }
        KtSourceElement source = expression.getSource();
        if (source != null && (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, desugaredIncrementOrDecrement, null, 2, null)) != null) {
            ktSourceElementFakeElement$default2 = ktSourceElementFakeElement$default;
        } else if (ktSourceElement != null) {
            ktSourceElementFakeElement$default2 = KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DesugaredAssignmentLValueSourceIsNull.INSTANCE, null, 2, null);
        }
        firDesugaredAssignmentValueReferenceExpressionBuilder.setSource(ktSourceElementFakeElement$default2);
        FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
        firExpressionRef.bind(FirExpressionUtilKt.unwrapSmartcastExpression(firExpression));
        firDesugaredAssignmentValueReferenceExpressionBuilder.setExpressionRef(firExpressionRef);
        firVariableAssignmentBuilder.setLValue(firDesugaredAssignmentValueReferenceExpressionBuilder.mo288build());
        firVariableAssignmentBuilder.setRValue(firExpression2);
        return (FirVariableAssignment) FirTransformerUtilKt.transformSingle(firVariableAssignmentBuilder.mo288build(), firExpressionsResolveTransformer.getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
    }

    private static final FirProperty transformIncrementDecrementExpression$generateTemporaryVariable(FirExpressionsResolveTransformer firExpressionsResolveTransformer, KtSourceElement ktSourceElement, Name name, FirExpression firExpression) {
        return FirGenerationKt.generateTemporaryVariable$default(FirModuleDataKt.getModuleData(firExpressionsResolveTransformer.getSession()), ktSourceElement, name, firExpression, UtilsKt.toFirResolvedTypeRef$default(FirTypeUtilsKt.getResolvedType(firExpression), ktSourceElement, null, 2, null), null, null, 96, null);
    }

    private static final FirFunctionCall transformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall) {
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().enterFunctionCall(firFunctionCall);
        FirCallCompleter.completeCall$default(firExpressionsResolveTransformer.getComponents().getCallCompleter(), firFunctionCall, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().exitFunctionCall(firFunctionCall, true);
        return firFunctionCall;
    }

    private static final FirStatement transformIndexedAccessAugmentedAssignment$lambda$0$chooseSetOperator(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, IndexedAccessAugmentedAssignmentDesugaringInfo indexedAccessAugmentedAssignmentDesugaringInfo) {
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().enterFunctionCall(firFunctionCall);
        FirCallCompleter.completeCall$default(firExpressionsResolveTransformer.getComponents().getCallCompleter(), firFunctionCall, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        firExpressionsResolveTransformer.getComponents().getDataFlowAnalyzer().exitFunctionCall(firFunctionCall, true);
        return indexedAccessAugmentedAssignmentDesugaringInfo.toBlock();
    }

    private static final FirStatement transformIndexedAccessAugmentedAssignment$lambda$0$reportAmbiguity(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, FirIndexedAccessAugmentedAssignment firIndexedAccessAugmentedAssignment, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2) {
        Candidate candidate = firNamedReferenceWithCandidate != null ? firNamedReferenceWithCandidate.getCandidate() : null;
        Candidate candidate2 = firNamedReferenceWithCandidate2 != null ? firNamedReferenceWithCandidate2.getCandidate() : null;
        if (candidate == null) {
            w01.a("Required value was null.");
            return null;
        }
        if (candidate2 != null) {
            return transformIndexedAccessAugmentedAssignment$lambda$0$reportError(firExpressionsResolveTransformer, firFunctionCall, firIndexedAccessAugmentedAssignment, new ConeOperatorAmbiguityError(CollectionsKt.listOf(new Candidate[]{candidate, candidate2})));
        }
        w01.a("Required value was null.");
        return null;
    }

    private static final FirStatement transformIndexedAccessAugmentedAssignment$lambda$0$reportError(FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, FirIndexedAccessAugmentedAssignment firIndexedAccessAugmentedAssignment, ConeDiagnostic coneDiagnostic) {
        FirFunctionCall firFunctionCallTransformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign = transformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign(firExpressionsResolveTransformer, firFunctionCall);
        firFunctionCallTransformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign.replaceCalleeReference((FirNamedReference) FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(coneDiagnostic, firIndexedAccessAugmentedAssignment.getSource()));
        return firFunctionCallTransformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign;
    }

    private static final FirStatement transformIndexedAccessAugmentedAssignment$lambda$0$reportUnresolvedReference(FirOperation firOperation, FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirFunctionCall firFunctionCall, FirIndexedAccessAugmentedAssignment firIndexedAccessAugmentedAssignment) {
        Name nameIdentifier = Name.identifier(firOperation.getOperator());
        nameIdentifier.getClass();
        return transformIndexedAccessAugmentedAssignment$lambda$0$reportError(firExpressionsResolveTransformer, firFunctionCall, firIndexedAccessAugmentedAssignment, new ConeUnresolvedNameError(nameIdentifier, null, null, 6, null));
    }

    /* JADX WARN: Code duplicated, block: B:60:0x0155  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v6, types: [org.jetbrains.kotlin.fir.expressions.FirExpression] */
    private final FirExpression transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, ResolutionMode data, boolean isUsedAsReceiver, boolean isUsedAsGetClassReceiver, boolean isUsedForContextSensitiveAlternative) {
        ?? CompleteCall$default;
        ?? r1;
        FirExpression firExpressionTransformQualifiedAccessExpression$alsoRecordLookup;
        FirStatement selector;
        FirExpressionsResolveTransformer firExpressionsResolveTransformer;
        FirQualifiedAccessExpression firQualifiedAccessExpressionTransformSuperReceiver;
        ConeDiagnostic diagnostic;
        ConeDiagnostic coneInstanceAccessBeforeSuperCall;
        FirQualifiedAccessExpression firQualifiedAccessExpression = qualifiedAccessExpression;
        ResolutionMode resolutionMode = data;
        if (FirTypeUtilsKt.getHasResolvedType(firQualifiedAccessExpression) && !(firQualifiedAccessExpression.getCalleeReference() instanceof FirSimpleNamedReference)) {
            return firQualifiedAccessExpression;
        }
        firQualifiedAccessExpression.transformAnnotations((FirTransformer<? super ResolutionMode>) this, resolutionMode);
        firQualifiedAccessExpression.transformTypeArguments(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        if (calleeReference instanceof FirThisReference) {
            FirThisReference firThisReference = (FirThisReference) calleeReference;
            String labelName = firThisReference.getLabelName();
            Set<ImplicitReceiverValue<?>> set = getComponents().getImplicitValueStorage().get(labelName);
            Set<ImplicitReceiverValue<?>> set2 = set;
            ImplicitReceiverValue implicitReceiverValue = (ImplicitReceiverValue) CollectionsKt.singleOrNull(set2);
            if (implicitReceiverValue == null) {
                ConeSimpleDiagnostic coneSimpleDiagnosticAmbiguityDiagnosticFor = ImplicitValueStorageKt.ambiguityDiagnosticFor(set, labelName);
                firQualifiedAccessExpression.replaceConeTypeOrNull(new ConeErrorType(coneSimpleDiagnosticAmbiguityDiagnosticFor, false, null, null, null, null, null, 126, null));
                firThisReference.replaceDiagnostic(coneSimpleDiagnosticAmbiguityDiagnosticFor);
                if (coneSimpleDiagnosticAmbiguityDiagnosticFor.getKind() == DiagnosticKind.AmbiguousLabel) {
                    return firQualifiedAccessExpression;
                }
                implicitReceiverValue = (ImplicitReceiverValue) CollectionsKt.lastOrNull(set2);
            }
            if (implicitReceiverValue != null) {
                firThisReference.replaceBoundSymbol(implicitReceiverValue.getBoundSymbol());
            }
            ConeKotlinType originalType = implicitReceiverValue != null ? implicitReceiverValue.getOriginalType() : null;
            if (implicitReceiverValue instanceof InaccessibleImplicitReceiverValue) {
                InaccessibleImplicitReceiverValue inaccessibleImplicitReceiverValue = (InaccessibleImplicitReceiverValue) implicitReceiverValue;
                int i = WhenMappings.$EnumSwitchMapping$0[inaccessibleImplicitReceiverValue.getKind().ordinal()];
                if (i == 1 || i == 2) {
                    coneInstanceAccessBeforeSuperCall = new ConeInstanceAccessBeforeSuperCall("<this>");
                } else {
                    if (i != 3) {
                        bu8.a();
                        return null;
                    }
                    coneInstanceAccessBeforeSuperCall = new ConeInaccessibleOuterClass(inaccessibleImplicitReceiverValue.getBoundSymbol());
                }
                originalType = new ConeErrorType(coneInstanceAccessBeforeSuperCall, false, null, null, null, null, null, 126, null);
            } else if (originalType == null) {
                originalType = labelName != null ? new ConeErrorType(new ConeSimpleDiagnostic("Unresolved this@".concat(labelName), DiagnosticKind.UnresolvedLabel), false, null, null, null, null, null, 126, null) : new ConeErrorType(new ConeSimpleDiagnostic("'this' is not defined in this context", DiagnosticKind.NoThis), false, null, null, null, null, null, 126, null);
            }
            ConeErrorType coneErrorType = originalType instanceof ConeErrorType ? (ConeErrorType) originalType : null;
            if (coneErrorType != null && (diagnostic = coneErrorType.getDiagnostic()) != null) {
                firThisReference.replaceDiagnostic(diagnostic);
            }
            ConeKotlinType expectedType = ResolutionModeKt.getExpectedType(resolutionMode);
            if (expectedType != null) {
                getTransformer().getContext().getInferenceSession().addSubtypeConstraintIfCompatible(originalType, expectedType, firQualifiedAccessExpression);
            }
            firQualifiedAccessExpression.replaceConeTypeOrNull(originalType);
            firQualifiedAccessExpressionTransformSuperReceiver = firQualifiedAccessExpression;
        } else {
            if (!(calleeReference instanceof FirSuperReference)) {
                if (calleeReference instanceof FirDelegateFieldReference) {
                    FirExpression delegate = ((FirProperty) ((FirDelegateFieldReference) calleeReference).getResolvedSymbol().getFir()).getDelegate();
                    delegate.getClass();
                    firQualifiedAccessExpression.replaceConeTypeOrNull(FirTypeUtilsKt.getResolvedType(FirExpressionUtilKt.unwrapReplExpressionRef(delegate)));
                    firQualifiedAccessExpressionTransformSuperReceiver = firQualifiedAccessExpression;
                } else if ((calleeReference instanceof FirResolvedNamedReference) || (calleeReference instanceof FirErrorNamedReference)) {
                    FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = this;
                    boolean hasResolvedType = FirTypeUtilsKt.getHasResolvedType(firQualifiedAccessExpression);
                    firExpressionTransformQualifiedAccessExpression$alsoRecordLookup = firQualifiedAccessExpression;
                    firExpressionsResolveTransformer = firExpressionsResolveTransformer2;
                    if (!hasResolvedType) {
                        firExpressionsResolveTransformer2.storeTypeFromCallee$org_jetbrains_kotlin_resolve(firQualifiedAccessExpression, false);
                        firExpressionTransformQualifiedAccessExpression$alsoRecordLookup = firQualifiedAccessExpression;
                        firExpressionsResolveTransformer = firExpressionsResolveTransformer2;
                    }
                } else {
                    FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this;
                    ?? HandleContextSensitiveResolution = firExpressionsResolveTransformer3.handleContextSensitiveResolution(resolveQualifiedAccessAndSelectCandidate(firQualifiedAccessExpression, isUsedAsReceiver, isUsedAsGetClassReceiver, resolutionMode instanceof ResolutionMode.AssignmentLValue ? ((ResolutionMode.AssignmentLValue) resolutionMode).getVariableAssignment() : firQualifiedAccessExpression, resolutionMode), firQualifiedAccessExpression, isUsedForContextSensitiveAlternative, resolutionMode);
                    if (!(HandleContextSensitiveResolution instanceof FirQualifiedAccessExpression) || CandidateFactoryKt.candidate((FirResolvable) HandleContextSensitiveResolution) == null || isUsedForContextSensitiveAlternative) {
                        ?? r2 = HandleContextSensitiveResolution;
                        resolutionMode = resolutionMode;
                        CompleteCall$default = r2;
                        r1 = r2;
                    } else {
                        if (!firExpressionsResolveTransformer3.isAcceptableResolvedQualifiedAccess((FirQualifiedAccessExpression) HandleContextSensitiveResolution)) {
                            return transformQualifiedAccessExpression$alsoRecordLookup(firQualifiedAccessExpression, HandleContextSensitiveResolution, firExpressionsResolveTransformer3, calleeReference);
                        }
                        ?? r3 = HandleContextSensitiveResolution;
                        resolutionMode = data;
                        CompleteCall$default = FirCallCompleter.completeCall$default(firExpressionsResolveTransformer3.getComponents().getCallCompleter(), r3, resolutionMode, false, 4, null);
                        r1 = r3;
                    }
                    firExpressionTransformQualifiedAccessExpression$alsoRecordLookup = transformQualifiedAccessExpression$alsoRecordLookup(CompleteCall$default, r1, firExpressionsResolveTransformer3, calleeReference);
                    firExpressionsResolveTransformer = firExpressionsResolveTransformer3;
                }
                return firExpressionsResolveTransformer.addSmartcastIfNeeded(firExpressionTransformQualifiedAccessExpression$alsoRecordLookup, resolutionMode);
            }
            FirSuperReceiverExpression firSuperReceiverExpression = (FirSuperReceiverExpression) firQualifiedAccessExpression;
            FirSafeCallExpression firSafeCallExpression = this.containingSafeCallExpression;
            if (firSafeCallExpression == null) {
                selector = null;
            } else {
                if (!Intrinsics.areEqual(firQualifiedAccessExpression, firSafeCallExpression.getReceiver())) {
                    firSafeCallExpression = null;
                }
                if (firSafeCallExpression != null) {
                    selector = firSafeCallExpression.getSelector();
                } else {
                    selector = null;
                }
            }
            firQualifiedAccessExpressionTransformSuperReceiver = transformSuperReceiver(firSuperReceiverExpression, selector instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) selector : null);
        }
        firExpressionsResolveTransformer = this;
        firExpressionTransformQualifiedAccessExpression$alsoRecordLookup = firQualifiedAccessExpressionTransformSuperReceiver;
        return firExpressionsResolveTransformer.addSmartcastIfNeeded(firExpressionTransformQualifiedAccessExpression$alsoRecordLookup, resolutionMode);
    }

    private static final FirExpression transformQualifiedAccessExpression$alsoRecordLookup(FirExpression firExpression, FirExpression firExpression2, FirExpressionsResolveTransformer firExpressionsResolveTransformer, FirReference firReference) {
        FirLookupTrackerComponent lookupTracker;
        if (FirTypeUtilsKt.getHasResolvedType(firExpression2) && (lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(firExpressionsResolveTransformer.getSession())) != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, FirTypeUtilsKt.getResolvedType(firExpression2), firReference.getSource(), firExpressionsResolveTransformer.getTransformer().getComponents().getFile().getSource());
        }
        return firExpression;
    }

    private final FirFunctionCall transformToIntegerOperatorCallOrApproximateItIfNeeded(FirFunctionCall firFunctionCall, ResolutionMode resolutionMode) {
        FirExpression firExpression;
        if (ResolveUtilsKt.isIntegerLiteralOrOperatorCall(firFunctionCall.getExplicitReceiver())) {
            boolean z = false;
            FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
            if (resolvedNamedFunctionSymbol$default != null && FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperator(resolvedNamedFunctionSymbol$default)) {
                List<FirExpression> arguments = firFunctionCall.getArgumentList().getArguments();
                int size = arguments.size();
                if (size != 0) {
                    firExpression = size == 1 ? (FirExpression) CollectionsKt.first(arguments) : null;
                }
                if (firExpression != null) {
                    ResolveUtilsKt.isIntegerLiteralOrOperatorCall(firExpression);
                }
                ConeKotlinType coneIntegerConstantOperatorTypeImpl = new ConeIntegerConstantOperatorTypeImpl(FirIntegerConstantOperatorScopeKt.isWrappedIntegerOperatorForUnsignedType(resolvedNamedFunctionSymbol$default), false);
                if (!(resolutionMode instanceof ResolutionMode.ReceiverResolution) && !(resolutionMode instanceof ResolutionMode.ContextDependent)) {
                    z = true;
                }
                FirIntegerLiteralOperatorCallBuilder firIntegerLiteralOperatorCallBuilder = new FirIntegerLiteralOperatorCallBuilder();
                firIntegerLiteralOperatorCallBuilder.setSource(firFunctionCall.getSource());
                firIntegerLiteralOperatorCallBuilder.setConeTypeOrNull(coneIntegerConstantOperatorTypeImpl);
                firIntegerLiteralOperatorCallBuilder.getAnnotations().addAll(firFunctionCall.getAnnotations());
                firIntegerLiteralOperatorCallBuilder.getTypeArguments().addAll(firFunctionCall.getTypeArguments());
                firIntegerLiteralOperatorCallBuilder.setCalleeReference(firFunctionCall.getCalleeReference());
                firIntegerLiteralOperatorCallBuilder.setOrigin(firFunctionCall.getOrigin());
                firIntegerLiteralOperatorCallBuilder.setArgumentList(firFunctionCall.getArgumentList());
                firIntegerLiteralOperatorCallBuilder.setExplicitReceiver(firFunctionCall.getExplicitReceiver());
                firIntegerLiteralOperatorCallBuilder.setDispatchReceiver(firFunctionCall.getDispatchReceiver());
                firIntegerLiteralOperatorCallBuilder.setExtensionReceiver(firFunctionCall.getExtensionReceiver());
                FirIntegerLiteralOperatorCall firIntegerLiteralOperatorCallMo288build = firIntegerLiteralOperatorCallBuilder.mo288build();
                return z ? (FirFunctionCall) FirTransformerUtilKt.transformSingle(firIntegerLiteralOperatorCallMo288build, getTransformer().getComponents().getIntegerLiteralAndOperatorApproximationTransformer(), ResolutionModeKt.getExpectedType(resolutionMode)) : firIntegerLiteralOperatorCallMo288build;
            }
        }
        return firFunctionCall;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0081  */
    /* JADX WARN: Code duplicated, block: B:46:0x009f  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirTypeOperatorCall transformTypeOperatorCallChildren(FirTypeOperatorCall firTypeOperatorCall) {
        ConeKotlinType coneKotlinTypeWithNullability$default;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration;
        List<FirTypeParameterRef> typeParameters;
        if (firTypeOperatorCall.getOperation() == FirOperation.AS || firTypeOperatorCall.getOperation() == FirOperation.SAFE_AS) {
            FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(firTypeOperatorCall.getArgumentList().getArguments());
            if (firExpression == null) {
                f2f.a("Not a single argument: ", UtilsKt.render(firTypeOperatorCall));
                return null;
            }
            if ((firExpression instanceof FirFunctionCall) || ((firExpression instanceof FirSafeCallExpression) && (((FirSafeCallExpression) firExpression).getSelector() instanceof FirFunctionCall))) {
                FirResolvedTypeRef conversionTypeRef = firTypeOperatorCall.getConversionTypeRef();
                FirResolvedTypeRef firResolvedTypeRef = conversionTypeRef instanceof FirResolvedTypeRef ? conversionTypeRef : null;
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                if (coneType == null) {
                    coneType = null;
                }
                if (coneType == null) {
                    coneKotlinTypeWithNullability$default = null;
                } else {
                    if (coneType instanceof ConeClassLikeType) {
                        if ((coneType.getTypeArguments().length == 0) && ((symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, ((ConeClassLikeType) coneType).getLookupTag())) == null || (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) == null || (typeParameters = firClassLikeDeclaration.getTypeParameters()) == null || !typeParameters.isEmpty())) {
                            coneKotlinTypeWithNullability$default = null;
                        } else {
                            coneKotlinTypeWithNullability$default = coneType;
                        }
                    } else {
                        coneKotlinTypeWithNullability$default = coneType;
                    }
                    if (coneKotlinTypeWithNullability$default == null) {
                        coneKotlinTypeWithNullability$default = null;
                    } else if (firTypeOperatorCall.getOperation() == FirOperation.SAFE_AS) {
                        coneKotlinTypeWithNullability$default = TypeUtilsKt.withNullability$default(coneKotlinTypeWithNullability$default, true, TypeComponentsKt.getTypeContext(getSession()), null, false, 12, null);
                    }
                }
                if (coneKotlinTypeWithNullability$default != null) {
                    return firTypeOperatorCall.transformOtherChildren(getTransformer(), new ResolutionMode.WithExpectedType(TypeUtilsKt.withReplacedConeType$default(firTypeOperatorCall.getConversionTypeRef(), coneKotlinTypeWithNullability$default, null, 2, null), false, true, null, null, false, 58, null));
                }
            }
        }
        return firTypeOperatorCall.transformOtherChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IndexedAccessAugmentedAssignmentDesugaringInfo tryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, FirFunctionCall lhsGetCall, FirExpression transformedRhs, KtFakeSourceElementKind fakeSourceElementKind) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirArgumentList firArgumentListBuild;
        FirExpression firExpressionMo288build;
        int size;
        int i;
        KtSourceElement ktSourceElementFakeElement$default;
        FirExpression firExpressionMo288build2;
        KtSourceElement source;
        FirExpression explicitReceiver = lhsGetCall.getExplicitReceiver();
        if (explicitReceiver == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            KtSourceElement source2 = indexedAccessAugmentedAssignment.getSource();
            firErrorExpressionBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, fakeSourceElementKind, null, 2, null) : null);
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("No receiver for array access"));
            explicitReceiver = firErrorExpressionBuilder.mo288build();
        }
        FirExpression firExpression = explicitReceiver;
        FirModuleData moduleData = FirModuleDataKt.getModuleData(getSession());
        FirExpression explicitReceiver2 = lhsGetCall.getExplicitReceiver();
        KtSourceElement ktSourceElementFakeElement$default2 = (explicitReceiver2 == null || (source = explicitReceiver2.getSource()) == null) ? null : KtSourceElementKt.fakeElement$default(source, fakeSourceElementKind, null, 2, null);
        Name name = SpecialNames.ARRAY;
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        KtSourceElement source3 = firExpression.getSource();
        FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(moduleData, ktSourceElementFakeElement$default2, name, firExpression, UtilsKt.toFirResolvedTypeRef$default(resolvedType, source3 != null ? KtSourceElementKt.fakeElement$default(source3, fakeSourceElementKind, null, 2, null) : null, null, 2, null), null, null, 96, null);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        for (FirExpression firExpression2 : lhsGetCall.getArgumentList().getArguments()) {
            if (firExpression2 instanceof FirVarargArgumentsExpression) {
                listCreateListBuilder.addAll(((FirVarargArgumentsExpression) firExpression2).getArguments());
            } else {
                listCreateListBuilder.add(firExpression2);
            }
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        List list = listBuild;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirExpression firExpressionUnwrapFunctionTypeConversions = unwrapFunctionTypeConversions((FirExpression) obj);
            FirModuleData moduleData2 = FirModuleDataKt.getModuleData(getSession());
            KtSourceElement source4 = firExpressionUnwrapFunctionTypeConversions.getSource();
            KtSourceElement ktSourceElementFakeElement$default3 = source4 != null ? KtSourceElementKt.fakeElement$default(source4, fakeSourceElementKind, null, 2, null) : null;
            Name nameSubscribeOperatorIndex = SpecialNames.subscribeOperatorIndex(i2);
            ConeKotlinType resolvedType2 = FirTypeUtilsKt.getResolvedType(firExpressionUnwrapFunctionTypeConversions);
            KtSourceElement source5 = firExpressionUnwrapFunctionTypeConversions.getSource();
            FirProperty firPropertyGenerateTemporaryVariable$default2 = FirGenerationKt.generateTemporaryVariable$default(moduleData2, ktSourceElementFakeElement$default3, nameSubscribeOperatorIndex, firExpressionUnwrapFunctionTypeConversions, UtilsKt.toFirResolvedTypeRef$default(resolvedType2, source5 != null ? KtSourceElementKt.fakeElement$default(source5, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null, null, 2, null), null, null, 96, null);
            firPropertyGenerateTemporaryVariable$default2.replaceBodyResolveState(FirPropertyBodyResolveState.INITIALIZER_RESOLVED);
            arrayList.add(firPropertyGenerateTemporaryVariable$default2);
            i2 = i3;
        }
        FirTransformerUtilKt.transformSingle(firPropertyGenerateTemporaryVariable$default, getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FirTransformerUtilKt.transformSingle((FirProperty) it.next(), getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        }
        FirQualifiedAccessExpression qualifiedAccess$default = FirGenerationKt.toQualifiedAccess$default(firPropertyGenerateTemporaryVariable$default, null, null, 3, null);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(FirGenerationKt.toQualifiedAccess$default((FirProperty) it2.next(), null, null, 3, null));
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        int i4 = 0;
        for (Object obj2 : arrayList2) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList3.add(wrapIntoFunctionConversionsIfNecessary((FirQualifiedAccessExpression) obj2, (FirExpression) listBuild.get(i4)));
            i4 = i5;
        }
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        KtSourceElement arrayAccessSource = indexedAccessAugmentedAssignment.getArrayAccessSource();
        firFunctionCallBuilder.setSource(arrayAccessSource != null ? KtSourceElementKt.fakeElement$default(arrayAccessSource, fakeSourceElementKind, null, 2, null) : null);
        firFunctionCallBuilder.setExplicitReceiver(qualifiedAccess$default);
        if (Intrinsics.areEqual(lhsGetCall.getExplicitReceiver(), lhsGetCall.getDispatchReceiver())) {
            firFunctionCallBuilder.setDispatchReceiver(qualifiedAccess$default);
            firFunctionCallBuilder.setExtensionReceiver(lhsGetCall.getExtensionReceiver());
        } else {
            firFunctionCallBuilder.setExtensionReceiver(qualifiedAccess$default);
            firFunctionCallBuilder.setDispatchReceiver(lhsGetCall.getDispatchReceiver());
        }
        firFunctionCallBuilder.setCalleeReference(lhsGetCall.getCalleeReference());
        FirArgumentList argumentList = lhsGetCall.getArgumentList();
        if (argumentList instanceof FirResolvedArgumentList) {
            LinkedHashMap<FirExpression, FirValueParameter> mapping = ((FirResolvedArgumentList) argumentList).getMapping();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<T> it3 = mapping.entrySet().iterator();
            int i6 = 0;
            while (it3.hasNext()) {
                Map.Entry entry = (Map.Entry) it3.next();
                FirExpression firExpression3 = (FirExpression) entry.getKey();
                if (firExpression3 instanceof FirVarargArgumentsExpression) {
                    FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder = new FirVarargArgumentsExpressionBuilder();
                    FirVarargArgumentsExpression firVarargArgumentsExpression = (FirVarargArgumentsExpression) firExpression3;
                    int size2 = i6 + firVarargArgumentsExpression.getArguments().size();
                    CollectionsKt.addAll(firVarargArgumentsExpressionBuilder.getArguments(), arrayList3.subList(i6, size2));
                    firVarargArgumentsExpressionBuilder.setSource(firVarargArgumentsExpression.getSource());
                    firVarargArgumentsExpressionBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(firExpression3));
                    firVarargArgumentsExpressionBuilder.setConeElementTypeOrNull(firVarargArgumentsExpression.getConeElementTypeOrNull());
                    firExpressionMo288build2 = firVarargArgumentsExpressionBuilder.mo288build();
                    i6 = size2;
                } else {
                    FirExpression firExpression4 = (FirExpression) arrayList3.get(i6);
                    i6++;
                    firExpressionMo288build2 = firExpression4;
                }
                linkedHashMap.put(firExpressionMo288build2, entry.getValue());
            }
            firArgumentListBuild = FirArgumentUtilKt.buildResolvedArgumentList(argumentList, linkedHashMap);
        } else {
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            List<FirExpression> arguments = firArgumentListBuilder.getArguments();
            List<FirExpression> arguments2 = firArgumentListBuilder.getArguments();
            int i7 = 0;
            for (FirExpression firExpression5 : arguments) {
                if (firExpression5 instanceof FirVarargArgumentsExpression) {
                    FirVarargArgumentsExpressionBuilder firVarargArgumentsExpressionBuilder2 = new FirVarargArgumentsExpressionBuilder();
                    FirVarargArgumentsExpression firVarargArgumentsExpression2 = (FirVarargArgumentsExpression) firExpression5;
                    size = firVarargArgumentsExpression2.getArguments().size() + i7;
                    CollectionsKt.addAll(firVarargArgumentsExpressionBuilder2.getArguments(), arrayList3.subList(i7, size));
                    firVarargArgumentsExpressionBuilder2.setSource(firVarargArgumentsExpression2.getSource());
                    firVarargArgumentsExpressionBuilder2.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(firExpression5));
                    firVarargArgumentsExpressionBuilder2.setConeElementTypeOrNull(firVarargArgumentsExpression2.getConeElementTypeOrNull());
                    firExpressionMo288build = firVarargArgumentsExpressionBuilder2.mo288build();
                } else {
                    int i8 = i7 + 1;
                    firExpressionMo288build = (FirExpression) arrayList3.get(i7);
                    size = i8;
                }
                arguments2.add(firExpressionMo288build);
                i7 = size;
            }
            firArgumentListBuild = firArgumentListBuilder.build();
        }
        firFunctionCallBuilder.setArgumentList(firArgumentListBuild);
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        firFunctionCallBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(lhsGetCall));
        FirFunctionCall firFunctionCallResolveCandidateForAssignmentOperatorCall = resolveCandidateForAssignmentOperatorCall(new GeneratorOfPlusAssignCalls(indexedAccessAugmentedAssignment, indexedAccessAugmentedAssignment.getCalleeReference().getSource(), indexedAccessAugmentedAssignment.getOperation(), firFunctionCallBuilder.mo288build(), transformedRhs).createSimpleOperatorCall(UtilsKt.toAugmentedAssignSourceKind(indexedAccessAugmentedAssignment.getOperation())));
        GeneratorOfPlusAssignCalls.Companion companion = GeneratorOfPlusAssignCalls.INSTANCE;
        Name name2 = OperatorNameConventions.SET;
        KtSourceElement source6 = indexedAccessAugmentedAssignment.getSource();
        if (source6 != null) {
            i = 2;
            ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source6, UtilsKt.toAugmentedAssignSourceKind(indexedAccessAugmentedAssignment.getOperation()), null, 2, null);
        } else {
            i = 2;
            ktSourceElementFakeElement$default = null;
        }
        KtSourceElement source7 = indexedAccessAugmentedAssignment.getCalleeReference().getSource();
        List<FirAnnotation> annotations = indexedAccessAugmentedAssignment.getAnnotations();
        SpreadBuilder spreadBuilder = new SpreadBuilder(i);
        spreadBuilder.addSpread(arrayList2.toArray(new FirQualifiedAccessExpression[0]));
        spreadBuilder.add(firFunctionCallResolveCandidateForAssignmentOperatorCall);
        return new IndexedAccessAugmentedAssignmentDesugaringInfo(this, indexedAccessAugmentedAssignment, firPropertyGenerateTemporaryVariable$default, arrayList, firFunctionCallResolveCandidateForAssignmentOperatorCall, resolveCandidateForAssignmentOperatorCall(companion.createFunctionCall(name2, ktSourceElementFakeElement$default, source7, annotations, qualifiedAccess$default, (FirExpression[]) spreadBuilder.toArray(new FirExpression[spreadBuilder.size()]))));
    }

    private final FirExpression unwrapFunctionTypeConversions(FirExpression firExpression) {
        FirExpression expression;
        FirExpression firExpressionUnwrapFunctionTypeConversions;
        FirFunctionTypeConversionExpression firFunctionTypeConversionExpression = firExpression instanceof FirFunctionTypeConversionExpression ? (FirFunctionTypeConversionExpression) firExpression : null;
        return (firFunctionTypeConversionExpression == null || (expression = firFunctionTypeConversionExpression.getExpression()) == null || (firExpressionUnwrapFunctionTypeConversions = unwrapFunctionTypeConversions(expression)) == null) ? firExpression : firExpressionUnwrapFunctionTypeConversions;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirTypeRef withTypeArgumentsForBareType(FirTypeRef firTypeRef, FirExpression firExpression, FirOperation firOperation) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        FirClassLikeSymbol<?> symbol;
        final FirClassLikeDeclaration firClassLikeDeclaration;
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (!(coneType instanceof ConeClassLikeType)) {
            coneType = null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
        if (coneClassLikeType != null && (coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType)) != null) {
            if ((coneClassLikeTypeFullyExpandedType.getTypeArguments().length == 0) && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, coneClassLikeTypeFullyExpandedType.getLookupTag())) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) != null && !firClassLikeDeclaration.getTypeParameters().isEmpty()) {
                ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(FirExpressionUtilKt.unwrapExpression(firExpression));
                ConeKotlinType andSemiFixCurrentResultIfTypeVariable = getTransformer().getComponents().getContext().getInferenceSession().getAndSemiFixCurrentResultIfTypeVariable(resolvedType);
                if (andSemiFixCurrentResultIfTypeVariable != null) {
                    resolvedType = andSemiFixCurrentResultIfTypeVariable;
                }
                Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: t65
                    public final Object invoke() {
                        return FirExpressionsResolveTransformer.c(firClassLikeDeclaration, this);
                    }
                });
                ConeKotlinType coneKotlinTypeComputeRepresentativeTypeForBareType = BareTypesKt.computeRepresentativeTypeForBareType(getTransformer().getComponents(), coneClassLikeTypeFullyExpandedType, resolvedType);
                if (coneKotlinTypeComputeRepresentativeTypeForBareType == null) {
                    if (firClassLikeDeclaration.getIsLocal()) {
                        List<FirTypeParameterRef> typeParameters = firClassLikeDeclaration.getTypeParameters();
                        if ((typeParameters instanceof Collection) && typeParameters.isEmpty()) {
                            if (firOperation != FirOperation.NOT_IS) {
                            }
                            coneKotlinTypeComputeRepresentativeTypeForBareType = ScopeUtilsKt.defaultType(firClassLikeDeclaration);
                        } else {
                            Iterator<T> it = typeParameters.iterator();
                            while (it.hasNext()) {
                                if (CollectionsKt.contains(withTypeArgumentsForBareType$lambda$2(lazy), ((FirTypeParameterRef) it.next()).getSymbol().getContainingDeclarationSymbol())) {
                                }
                            }
                            if (firOperation != FirOperation.NOT_IS || firOperation == FirOperation.IS || firOperation == FirOperation.AS || firOperation == FirOperation.SAFE_AS) {
                                coneKotlinTypeComputeRepresentativeTypeForBareType = ScopeUtilsKt.defaultType(firClassLikeDeclaration);
                            }
                        }
                    }
                    FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder.setSource(((FirResolvedTypeRef) firTypeRef).getSource());
                    firErrorTypeRefBuilder.setDiagnostic(new ConeNoTypeArgumentsOnRhsError(firClassLikeDeclaration.getTypeParameters().size(), firClassLikeDeclaration.getSymbol()));
                    return firErrorTypeRefBuilder.build();
                }
                return coneKotlinTypeComputeRepresentativeTypeForBareType.getTypeArguments().length == 0 ? firTypeRef : TypeUtilsKt.withReplacedConeType$default(firTypeRef, coneKotlinTypeComputeRepresentativeTypeForBareType, null, 2, null);
            }
        }
        return firTypeRef;
    }

    private static final Set<FirClassLikeSymbol<?>> withTypeArgumentsForBareType$lambda$2(Lazy<? extends Set<? extends FirClassLikeSymbol<?>>> lazy) {
        return (Set) lazy.getValue();
    }

    private final FirExpression wrapIntoFunctionConversionsIfNecessary(FirQualifiedAccessExpression temporaryVariableAccess, FirExpression initialGetArgument) {
        FirFunctionTypeConversionExpression firFunctionTypeConversionExpression = initialGetArgument instanceof FirFunctionTypeConversionExpression ? (FirFunctionTypeConversionExpression) initialGetArgument : null;
        if (firFunctionTypeConversionExpression == null) {
            return temporaryVariableAccess;
        }
        FirFunctionTypeConversionExpressionBuilder firFunctionTypeConversionExpressionBuilder = new FirFunctionTypeConversionExpressionBuilder();
        firFunctionTypeConversionExpressionBuilder.setSource(firFunctionTypeConversionExpression.getSource());
        firFunctionTypeConversionExpressionBuilder.setConeTypeOrNull(firFunctionTypeConversionExpression.getConeTypeOrNull());
        firFunctionTypeConversionExpressionBuilder.getAnnotations().addAll(firFunctionTypeConversionExpression.getAnnotations());
        firFunctionTypeConversionExpressionBuilder.setExpression(firFunctionTypeConversionExpression.getExpression());
        firFunctionTypeConversionExpressionBuilder.setKind(firFunctionTypeConversionExpression.getKind());
        firFunctionTypeConversionExpressionBuilder.setExpression(wrapIntoFunctionConversionsIfNecessary(temporaryVariableAccess, firFunctionTypeConversionExpression.getExpression()));
        return firFunctionTypeConversionExpressionBuilder.mo288build();
    }

    public final FirSafeCallExpression getContainingSafeCallExpression() {
        return this.containingSafeCallExpression;
    }

    public boolean isAcceptableResolvedQualifiedAccess(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        return true;
    }

    public FirExpression resolveQualifiedAccessAndSelectCandidate(FirQualifiedAccessExpression qualifiedAccessExpression, boolean isUsedAsReceiver, boolean isUsedAsGetClassReceiver, FirElement callSite, ResolutionMode data) {
        qualifiedAccessExpression.getClass();
        callSite.getClass();
        data.getClass();
        return getComponents().getCallResolver().resolveVariableAccessAndSelectCandidate(qualifiedAccessExpression, isUsedAsReceiver, isUsedAsGetClassReceiver, callSite, data);
    }

    public final void setContainingSafeCallExpression(FirSafeCallExpression firSafeCallExpression) {
        this.containingSafeCallExpression = firSafeCallExpression;
    }

    public boolean shouldComputeTypeOfGetClassCallWithNotQualifierInLhs(FirGetClassCall getClassCall) {
        getClassCall.getClass();
        return true;
    }

    public final void storeTypeFromCallee$org_jetbrains_kotlin_resolve(FirQualifiedAccessExpression access, boolean isLhsOfAssignment) {
        access.getClass();
        ConeKotlinType coneKotlinTypeTypeFromCallee = ResolveUtilsKt.typeFromCallee(getTransformer().getComponents(), access);
        ConeKotlinType coneKotlinTypeApproximateToSubType = isLhsOfAssignment ? TypeComponentsKt.getTypeApproximator(getSession()).approximateToSubType(coneKotlinTypeTypeFromCallee, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE) : TypeComponentsKt.getTypeApproximator(getSession()).approximateToSuperType(coneKotlinTypeTypeFromCallee, TypeApproximatorConfiguration.IntermediateApproximationToSupertypeAfterCompletionInK2.INSTANCE);
        if (coneKotlinTypeApproximateToSubType != null) {
            coneKotlinTypeTypeFromCallee = coneKotlinTypeApproximateToSubType;
        }
        access.replaceConeTypeOrNull(coneKotlinTypeTypeFromCallee);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotation(FirAnnotation annotation, ResolutionMode data) {
        annotation.getClass();
        data.getClass();
        if (FirAnnotationUtilsKt.getResolved(annotation)) {
            return annotation;
        }
        annotation.transformAnnotationTypeRef(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        return annotation;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ResolutionMode data) {
        annotationCall.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (FirAnnotationUtilsKt.getResolved(annotationCall)) {
                return annotationCall;
            }
            FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
            ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
            annotationCall.transformAnnotationTypeRef((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
            annotationCall.replaceAnnotationResolvePhase(FirAnnotationResolvePhase.Types);
            BodyResolveContext context = getTransformer().getContext();
            boolean isInsideAnnotationContext = context.getIsInsideAnnotationContext();
            context.setInsideAnnotationContext(true);
            try {
                getComponents().getDataFlowAnalyzer().enterAnnotation();
                FirAnnotationCall firAnnotationCallResolveAnnotationCall = getComponents().getCallResolver().resolveAnnotationCall(annotationCall);
                getComponents().getDataFlowAnalyzer().exitAnnotation();
                if (firAnnotationCallResolveAnnotationCall != null) {
                    FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), firAnnotationCallResolveAnnotationCall, contextIndependent, false, 4, null);
                    FirArgumentList argumentList = firAnnotationCallResolveAnnotationCall.getArgumentList();
                    argumentList.getClass();
                    annotationCall.replaceArgumentMapping(FirAnnotationArgumentMappingImplKt.toAnnotationArgumentMapping((FirResolvedArgumentList) argumentList));
                    evaluateAndReplaceArgumentMapping(annotationCall);
                }
                return annotationCall;
            } finally {
                context.setInsideAnnotationContext(isInsideAnnotationContext);
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(annotationCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void transformAnnotationCallArguments(FirCall call, FirConstructorSymbol constructorSymbol) {
        FirTypeRef returnTypeRef;
        call.getClass();
        if (constructorSymbol == null || call.getArgumentList().getArguments().isEmpty()) {
            call.replaceArgumentList((FirArgumentList) call.getArgumentList().transform(getTransformer(), ResolutionMode.ContextDependent.INSTANCE));
            return;
        }
        BodyResolveComponents bodyResolveComponents = getTransformer().getResolutionContext().getBodyResolveComponents();
        List<FirExpression> arguments = call.getArgumentList().getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(ConeResolutionAtom.INSTANCE.createRawAtomForPotentiallyUnresolvedExpression((FirExpression) it.next()));
        }
        ArgumentMapping argumentMappingMapArguments = FirArgumentsToParametersMapperKt.mapArguments(bodyResolveComponents, arrayList, (FirFunction) constructorSymbol.getFir(), null, false, false);
        LinkedHashMap linkedHashMapUnwrapAtoms = FirCallCompletionResultsWriterTransformerKt.unwrapAtoms(argumentMappingMapArguments.toArgumentToParameterMapping());
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.setSource(call.getArgumentList().getSource());
        List<FirExpression> arguments2 = call.getArgumentList().getArguments();
        List<FirExpression> arguments3 = firArgumentListBuilder.getArguments();
        for (FirExpression firExpression : arguments2) {
            FirValueParameter firValueParameter = (FirValueParameter) linkedHashMapUnwrapAtoms.get(firExpression);
            ConeKotlinType coneTypeOrNull = (firValueParameter == null || (returnTypeRef = firValueParameter.getReturnTypeRef()) == null) ? null : FirTypeUtilsKt.getConeTypeOrNull(returnTypeRef);
            if (coneTypeOrNull == null) {
                coneTypeOrNull = null;
            } else if ((argumentMappingMapArguments.getParameterToCallArgumentMap().get(firValueParameter) instanceof ResolvedCallArgument.VarargArgument) && !(firExpression instanceof FirWrappedArgumentExpression)) {
                coneTypeOrNull = FirTypeUtilsKt.arrayElementType$default(coneTypeOrNull, false, 1, null);
            }
            arguments3.add((FirExpression) FirTransformerUtilKt.transformSingle(firExpression, getTransformer(), coneTypeOrNull != null ? new ResolutionMode.WithExpectedType(UtilsKt.toFirResolvedTypeRef$default(coneTypeOrNull, null, null, 3, null), false, false, ResolutionMode.ArrayLiteralPosition.AnnotationArgument, null, false, 22, null) : ResolutionMode.ContextDependent.INSTANCE));
        }
        call.replaceArgumentList(firArgumentListBuilder.build());
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, ResolutionMode data) {
        anonymousObjectExpression.getClass();
        data.getClass();
        anonymousObjectExpression.transformAnonymousObject(getTransformer(), data);
        if (!FirTypeUtilsKt.getHasResolvedType(anonymousObjectExpression)) {
            anonymousObjectExpression.replaceConeTypeOrNull(ScopeUtilsKt.defaultType(anonymousObjectExpression.getAnonymousObject()));
        }
        getComponents().getDataFlowAnalyzer().exitAnonymousObjectExpression(anonymousObjectExpression);
        return anonymousObjectExpression;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00f8  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAugmentedAssignment(FirAugmentedAssignment augmentedAssignment, ResolutionMode data) {
        FirVariableSymbol resolvedVariableSymbol$default;
        FirAugmentedAssignment firAugmentedAssignment = augmentedAssignment;
        firAugmentedAssignment.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirOperation operation = firAugmentedAssignment.getOperation();
            KtFakeSourceElementKind augmentedAssignSourceKind = UtilsKt.toAugmentedAssignSourceKind(operation);
            if (operation == FirOperation.ASSIGN) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            firAugmentedAssignment.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            getComponents().getDataFlowAnalyzer().enterCallArguments(firAugmentedAssignment, CollectionsKt.listOf(firAugmentedAssignment.getRightArgument()));
            boolean z = false;
            FirExpression firExpressionTransformAsExplicitReceiver = transformAsExplicitReceiver(firAugmentedAssignment.getLeftArgument(), ResolutionMode.ReceiverResolution.INSTANCE, false);
            FirExpression firExpression = (FirExpression) FirTransformerUtilKt.transformSingle(firAugmentedAssignment.getRightArgument(), getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
            getComponents().getDataFlowAnalyzer().exitCallArguments();
            FirReference reference = ReferenceUtilsKt.toReference(firAugmentedAssignment, getSession());
            GeneratorOfPlusAssignCalls generatorOfPlusAssignCalls = new GeneratorOfPlusAssignCalls(firAugmentedAssignment, reference != null ? reference.getSource() : null, operation, firExpressionTransformAsExplicitReceiver, firExpression);
            try {
                FirFunctionCall firFunctionCallResolveCandidateForAssignmentOperatorCall = resolveCandidateForAssignmentOperatorCall(generatorOfPlusAssignCalls.createAssignOperatorCall(augmentedAssignSourceKind));
                FirNamedReference calleeReference = firFunctionCallResolveCandidateForAssignmentOperatorCall.getCalleeReference();
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
                boolean z2 = (firNamedReferenceWithCandidate == null || firNamedReferenceWithCandidate.isError()) ? false : true;
                FirFunctionCall firFunctionCallResolveCandidateForAssignmentOperatorCall2 = resolveCandidateForAssignmentOperatorCall(generatorOfPlusAssignCalls.createSimpleOperatorCall(augmentedAssignSourceKind));
                FirNamedReference calleeReference2 = firFunctionCallResolveCandidateForAssignmentOperatorCall2.getCalleeReference();
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2 = calleeReference2 instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference2 : null;
                boolean z3 = (firNamedReferenceWithCandidate2 == null || firNamedReferenceWithCandidate2.isError()) ? false : true;
                boolean z4 = z3 && transformAugmentedAssignment$lambda$0$operatorReturnTypeMatches(firFunctionCallResolveCandidateForAssignmentOperatorCall2, this, firExpressionTransformAsExplicitReceiver, firNamedReferenceWithCandidate2.getCandidate());
                FirReference reference2 = ReferenceUtilsKt.toReference(firExpressionTransformAsExplicitReceiver, getSession());
                if (reference2 == null) {
                    resolvedVariableSymbol$default = null;
                } else {
                    resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(reference2, false, 1, null);
                    if (resolvedVariableSymbol$default == null) {
                        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate3 = reference2 instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) reference2 : null;
                        FirBasedSymbol<?> candidateSymbol = firNamedReferenceWithCandidate3 != null ? firNamedReferenceWithCandidate3.getCandidateSymbol() : null;
                        if (candidateSymbol instanceof FirVariableSymbol) {
                            resolvedVariableSymbol$default = (FirVariableSymbol) candidateSymbol;
                        } else {
                            resolvedVariableSymbol$default = null;
                        }
                    }
                }
                FirVariable firVariable = resolvedVariableSymbol$default != null ? (FirVariable) resolvedVariableSymbol$default.getFir() : null;
                if (firVariable != null && firVariable.getIsVar()) {
                    z = true;
                }
                if (z2 && !z) {
                    return transformAugmentedAssignment$lambda$0$chooseAssign(this, firFunctionCallResolveCandidateForAssignmentOperatorCall);
                }
                if (!z2 && !z3) {
                    return transformAugmentedAssignment$lambda$0$chooseResolved(firNamedReferenceWithCandidate, firNamedReferenceWithCandidate2, this, firFunctionCallResolveCandidateForAssignmentOperatorCall, firFunctionCallResolveCandidateForAssignmentOperatorCall2, firVariable, firExpressionTransformAsExplicitReceiver, augmentedAssignSourceKind, augmentedAssignment);
                }
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate4 = firNamedReferenceWithCandidate;
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate5 = firNamedReferenceWithCandidate2;
                FirVariable firVariable2 = firVariable;
                if (z2 || !z3) {
                    if ((!z2 || z3) && !(FirTypeUtilsKt.getResolvedType(firExpressionTransformAsExplicitReceiver) instanceof ConeDynamicType) && z4) {
                        return transformAugmentedAssignment$lambda$0$reportAmbiguity(firNamedReferenceWithCandidate5, firNamedReferenceWithCandidate4, augmentedAssignment);
                    }
                    return transformAugmentedAssignment$lambda$0$chooseAssign(this, firFunctionCallResolveCandidateForAssignmentOperatorCall);
                }
                try {
                    return transformAugmentedAssignment$lambda$0$chooseOperator(this, firFunctionCallResolveCandidateForAssignmentOperatorCall2, firVariable2, firExpressionTransformAsExplicitReceiver, augmentedAssignSourceKind, augmentedAssignment);
                } catch (Throwable th) {
                    th = th;
                    firAugmentedAssignment = augmentedAssignment;
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firAugmentedAssignment, th);
                    wq6.a();
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                firAugmentedAssignment = augmentedAssignment;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(FirBlock block, ResolutionMode data) {
        block.getClass();
        data.getClass();
        BodyResolveContext context = getTransformer().getContext();
        FirSession session = getSession();
        FirTowerDataContext towerDataContext = context.getTowerDataContext();
        try {
            context.addLocalScope(new FirLocalScope(session));
            transformBlockInCurrentScope$org_jetbrains_kotlin_resolve(block, data);
            Unit unit = Unit.INSTANCE;
            return block;
        } finally {
            context.replaceTowerDataContext(towerDataContext);
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x005b  */
    public final void transformBlockInCurrentScope$org_jetbrains_kotlin_resolve(FirBlock block, final ResolutionMode data) {
        block.getClass();
        data.getClass();
        getComponents().getDataFlowAnalyzer().enterBlock(block);
        final int size = block.getStatements().size();
        FirExpressionUtilKt.transformStatementsIndexed(block, getTransformer(), new Function1() { // from class: r65
            public final Object invoke(Object obj) {
                return FirExpressionsResolveTransformer.e(size, data, ((Integer) obj).intValue());
            }
        });
        block.transformOtherChildren(getTransformer(), data);
        if (block instanceof FirContractCallBlock) {
            ((FirContractCallBlock) block).getCall().acceptChildren(new FirDefaultVisitorVoid() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer$transformBlockInCurrentScope$2
                public void visitAnnotation(FirAnnotation annotation) {
                    annotation.getClass();
                    FirTransformerUtilKt.transformSingle(annotation, this.this$0.getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }

                public void visitAnnotationCall(FirAnnotationCall annotationCall) {
                    annotationCall.getClass();
                    visitAnnotation(annotationCall);
                }

                public void visitElement(FirElement element) {
                    element.getClass();
                    element.acceptChildren(this);
                }
            });
        }
        if (data instanceof ResolutionMode.WithExpectedType) {
            ResolutionMode.WithExpectedType withExpectedType = (ResolutionMode.WithExpectedType) data;
            if (TypeUtilsKt.isUnitOrFlexibleUnit(withExpectedType.getExpectedType())) {
                block.replaceConeTypeOrNull(withExpectedType.getExpectedType());
                block.replaceIsUnitCoerced(true);
            } else {
                BodyResolveUtilsKt.writeResultType(block, getSession());
            }
        } else {
            BodyResolveUtilsKt.writeResultType(block, getSession());
        }
        getComponents().getDataFlowAnalyzer().exitBlock(block);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, ResolutionMode data) {
        booleanOperatorExpression.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirResolvedTypeRef firResolvedTypeRef$default = UtilsKt.toFirResolvedTypeRef$default(getSession().getBuiltinTypes().getBooleanType().getConeType(), null, null, 3, null);
            getComponents().getDataFlowAnalyzer().enterBooleanOperatorExpression(booleanOperatorExpression);
            FirBooleanOperatorExpression firBooleanOperatorExpressionTransformLeftOperand = booleanOperatorExpression.transformLeftOperand(this, new ResolutionMode.WithExpectedType(firResolvedTypeRef$default, false, false, null, null, false, 62, null));
            getComponents().getDataFlowAnalyzer().exitLeftBooleanOperatorExpressionArgument(firBooleanOperatorExpressionTransformLeftOperand);
            FirBooleanOperatorExpression firBooleanOperatorExpressionTransformRightOperand = firBooleanOperatorExpressionTransformLeftOperand.transformRightOperand(this, new ResolutionMode.WithExpectedType(firResolvedTypeRef$default, false, false, null, null, false, 62, null));
            getComponents().getDataFlowAnalyzer().exitBooleanOperatorExpression(firBooleanOperatorExpressionTransformRightOperand);
            FirBooleanOperatorExpression firBooleanOperatorExpressionTransformOtherChildren = firBooleanOperatorExpressionTransformRightOperand.transformOtherChildren(getTransformer(), new ResolutionMode.WithExpectedType(firResolvedTypeRef$default, false, false, null, null, false, 62, null));
            firBooleanOperatorExpressionTransformOtherChildren.replaceConeTypeOrNull(firResolvedTypeRef$default.getConeType());
            return firBooleanOperatorExpressionTransformOtherChildren;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(booleanOperatorExpression, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, ResolutionMode data) {
        FirCallableReferenceAccess firCallableReferenceAccessResolveCallableReferenceWithSyntheticOuterCall;
        callableReferenceAccess.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (callableReferenceAccess.getCalleeReference() instanceof FirResolvedNamedReference) {
                return callableReferenceAccess;
            }
            callableReferenceAccess.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            callableReferenceAccess.transformErrorArgumentList(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            FirExpression explicitReceiver = callableReferenceAccess.getExplicitReceiver();
            FirExpression firExpressionTransformAsExplicitReceiver = explicitReceiver != null ? transformAsExplicitReceiver(explicitReceiver, ResolutionMode.ReceiverResolution.ForCallableReference.INSTANCE, false) : null;
            if ((firExpressionTransformAsExplicitReceiver instanceof FirResolvedQualifier) && callableReferenceAccess.getHasQuestionMarkAtLHS()) {
                ((FirResolvedQualifier) firExpressionTransformAsExplicitReceiver).replaceIsNullableLHSForCallableReference(true);
            }
            if (firExpressionTransformAsExplicitReceiver != null) {
                callableReferenceAccess.replaceExplicitReceiver(firExpressionTransformAsExplicitReceiver);
            }
            if (data instanceof ResolutionMode.ContextDependent) {
                getTransformer().getContext().storeCallableReferenceContext(callableReferenceAccess);
                firCallableReferenceAccessResolveCallableReferenceWithSyntheticOuterCall = callableReferenceAccess;
            } else {
                firCallableReferenceAccessResolveCallableReferenceWithSyntheticOuterCall = getTransformer().getComponents().getSyntheticCallGenerator().resolveCallableReferenceWithSyntheticOuterCall(callableReferenceAccess, ResolutionModeKt.getExpectedType(data), getTransformer().getResolutionContext());
            }
            getComponents().getDataFlowAnalyzer().exitCallableReference(firCallableReferenceAccessResolveCallableReferenceWithSyntheticOuterCall);
            return firCallableReferenceAccessResolveCallableReferenceWithSyntheticOuterCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(callableReferenceAccess, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, ResolutionMode data) {
        checkNotNullCall.getClass();
        data.getClass();
        if ((checkNotNullCall.getCalleeReference() instanceof FirResolvedNamedReference) && FirTypeUtilsKt.getHasResolvedType(checkNotNullCall)) {
            return checkNotNullCall;
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(checkNotNullCall.getArgumentList().getArguments());
        if (!((firExpression != null ? firExpression.getConeTypeOrNull() : null) instanceof ConeDynamicType)) {
            getComponents().getDataFlowAnalyzer().enterCheckNotNullCall();
        }
        checkNotNullCall.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE).replaceArgumentList((FirArgumentList) checkNotNullCall.getArgumentList().transform(getTransformer(), ResolutionMode.ContextDependent.INSTANCE));
        FirCheckNotNullCall firCheckNotNullCall = (FirCheckNotNullCall) FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), getTransformer().getComponents().getSyntheticCallGenerator().generateCalleeForCheckNotNullCall(checkNotNullCall, getTransformer().getResolutionContext(), data), data, false, 4, null);
        FirExpression firExpression2 = (FirExpression) CollectionsKt.firstOrNull(checkNotNullCall.getArgumentList().getArguments());
        if (!((firExpression2 != null ? firExpression2.getConeTypeOrNull() : null) instanceof ConeDynamicType)) {
            getComponents().getDataFlowAnalyzer().exitCheckNotNullCall(firCheckNotNullCall, data.getForceFullCompletion());
        }
        return firCheckNotNullCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject, ResolutionMode data) {
        checkedSafeCallSubject.getClass();
        data.getClass();
        return checkedSafeCallSubject;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCollectionLiteral(FirCollectionLiteral collectionLiteral, ResolutionMode data) {
        collectionLiteral.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.CollectionLiterals) && !getTransformer().getContext().getIsInsideAnnotationContext()) {
                collectionLiteral.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
                ResolutionMode.ContextDependent.Companion companion = ResolutionMode.ContextDependent.INSTANCE;
                collectionLiteral.transformChildren(transformer, companion);
                if (Intrinsics.areEqual(data, companion)) {
                    return collectionLiteral;
                }
                return getTransformer().getComponents().getSyntheticCallGenerator().resolveCollectionLiteralExpressionWithSyntheticOuterCall(collectionLiteral, data instanceof ResolutionMode.WithExpectedType ? (ResolutionMode.WithExpectedType) data : null, getTransformer().getResolutionContext());
            }
            return transformCollectionLiteralInAnnotation(collectionLiteral, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(collectionLiteral, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformComparisonExpression(FirComparisonExpression comparisonExpression, ResolutionMode data) {
        comparisonExpression.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirElement firElementTransformChildren = comparisonExpression.transformChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            firElementTransformChildren.getClass();
            FirComparisonExpression firComparisonExpression = (FirComparisonExpression) firElementTransformChildren;
            firComparisonExpression.replaceConeTypeOrNull(getSession().getBuiltinTypes().getBooleanType().getConeType());
            getComponents().getDataFlowAnalyzer().exitComparisonExpressionCall(firComparisonExpression);
            return firComparisonExpression;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(comparisonExpression, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:78:0x0177  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ResolutionMode data) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        Object next;
        KtSourceElement source;
        ConeKotlinType coneType;
        FirCallableSymbol<?> resolvedCallableSymbol;
        FirExpression dispatchReceiver;
        ConeKotlinType resolvedType;
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        FirClassLikeSymbol<?> symbol;
        Object next2;
        FirRegularClass firRegularClassExtractSuperTypeDeclaration;
        delegatedConstructorCall.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            if (getTransformer().getImplicitTypeOnly()) {
                return delegatedConstructorCall;
            }
            FirReference calleeReference = delegatedConstructorCall.getCalleeReference();
            if (!(calleeReference instanceof FirResolvedNamedReference) && !(calleeReference instanceof FirErrorNamedReference)) {
                ArrayDeque<FirDeclaration> containers = getTransformer().getComponents().getContext().getContainers();
                Object obj = containers.get(CollectionsKt.getLastIndex(containers) - 1);
                obj.getClass();
                FirClass firClass = (FirClass) obj;
                Object objLast = containers.last();
                objLast.getClass();
                FirConstructor firConstructor = (FirConstructor) objLast;
                if (delegatedConstructorCall.isSuper() && (delegatedConstructorCall.getConstructedTypeRef() instanceof FirImplicitTypeRef)) {
                    Iterator<T> it = firClass.getSuperTypeRefs().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        FirTypeRef firTypeRef = (FirTypeRef) next2;
                        if ((firTypeRef instanceof FirResolvedTypeRef) && (firRegularClassExtractSuperTypeDeclaration = extractSuperTypeDeclaration(firTypeRef)) != null) {
                            boolean z = firRegularClassExtractSuperTypeDeclaration.getStatus().isExternal() && isCallToDelegatedConstructorWithoutArguments(delegatedConstructorCall);
                            if (firRegularClassExtractSuperTypeDeclaration.getClassKind() == ClassKind.CLASS && !z) {
                                break;
                            }
                        }
                    }
                    FirImplicitBuiltinTypeRef anyType = (FirResolvedTypeRef) next2;
                    if (anyType == null) {
                        anyType = getSession().getBuiltinTypes().getAnyType();
                    }
                    delegatedConstructorCall.replaceConstructedTypeRef(anyType);
                    FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
                    firExplicitSuperReferenceBuilder.setSource(delegatedConstructorCall.getCalleeReference().getSource());
                    firExplicitSuperReferenceBuilder.setSuperTypeRef(anyType);
                    delegatedConstructorCall.replaceCalleeReference(firExplicitSuperReferenceBuilder.build());
                }
                getComponents().getDataFlowAnalyzer().enterCallArguments(delegatedConstructorCall, delegatedConstructorCall.getArgumentList().getArguments());
                BodyResolveContext context = getTransformer().getContext();
                FirRegularClass firRegularClass = firClass instanceof FirRegularClass ? (FirRegularClass) firClass : null;
                FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getTransformer().getComponents();
                FirTowerDataMode towerDataMode = context.getTowerDataMode();
                FirTowerDataMode firTowerDataMode = FirTowerDataMode.CONSTRUCTOR_HEADER;
                if (towerDataMode != firTowerDataMode) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                FirTowerDataContext towerDataContext = context.getTowerDataContext();
                try {
                    if (!firConstructor.getIsPrimary()) {
                        context.addInaccessibleImplicitReceiverValue(firRegularClass, components);
                    }
                    context.addLocalScope(context.buildConstructorParametersScope(firConstructor, components.getSession()));
                    delegatedConstructorCall.transformChildren(getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
                    context.replaceTowerDataContext(towerDataContext);
                    getComponents().getDataFlowAnalyzer().exitCallArguments();
                    FirReference calleeReference2 = delegatedConstructorCall.getCalleeReference();
                    if (calleeReference2 instanceof FirThisReference) {
                        coneClassLikeTypeFullyExpandedType = ScopeUtilsKt.defaultType(firClass);
                    } else if (calleeReference2 instanceof FirSuperReference) {
                        FirResolvedTypeRef superTypeRef = ((FirSuperReference) calleeReference2).getSuperTypeRef();
                        FirResolvedTypeRef firResolvedTypeRef = superTypeRef instanceof FirResolvedTypeRef ? superTypeRef : null;
                        ConeKotlinType coneType2 = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                        if (!(coneType2 instanceof ConeClassLikeType)) {
                            coneType2 = null;
                        }
                        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType2;
                        if (coneClassLikeType == null) {
                            coneClassLikeTypeFullyExpandedType = null;
                        } else {
                            if (coneClassLikeType instanceof ConeErrorType) {
                                coneClassLikeType = null;
                            }
                            if (coneClassLikeType != null) {
                                coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType);
                            } else {
                                coneClassLikeTypeFullyExpandedType = null;
                            }
                        }
                    } else {
                        coneClassLikeTypeFullyExpandedType = null;
                    }
                    BodyResolveContext context2 = getTransformer().getContext();
                    if (context2.getTowerDataMode() != firTowerDataMode) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                    FirTowerDataMode towerDataMode2 = FirTowerDataMode.REGULAR;
                    FirTowerDataMode towerDataMode3 = context2.getTowerDataMode();
                    if (towerDataMode2 == null) {
                        try {
                            towerDataMode2 = context2.getTowerDataMode();
                        } catch (Throwable th) {
                            context2.setTowerDataMode(towerDataMode3);
                            throw th;
                        }
                    }
                    context2.setTowerDataMode(towerDataMode2);
                    FirDelegatedConstructorCall firDelegatedConstructorCallResolveDelegatingConstructorCall = getComponents().getCallResolver().resolveDelegatingConstructorCall(delegatedConstructorCall, coneClassLikeTypeFullyExpandedType, firClass.getSymbol());
                    context2.setTowerDataMode(towerDataMode3);
                    if ((calleeReference2 instanceof FirThisReference) && ((FirThisReference) calleeReference2).getBoundSymbol() == null && (dispatchReceiver = firDelegatedConstructorCallResolveDelegatingConstructorCall.getDispatchReceiver()) != null && (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) != null && (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(resolvedType)) != null && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, classLikeLookupTagIfAny)) != null) {
                        ((FirThisReference) calleeReference2).replaceBoundSymbol(symbol);
                    }
                    FirDelegatedConstructorCall firDelegatedConstructorCall = (FirDelegatedConstructorCall) FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), firDelegatedConstructorCallResolveDelegatingConstructorCall, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
                    getComponents().getDataFlowAnalyzer().exitDelegatedConstructorCall(firDelegatedConstructorCall, data.getForceFullCompletion());
                    KtSourceElement source2 = firDelegatedConstructorCall.getSource();
                    KtSourceElementKind kind = source2 != null ? source2.getKind() : null;
                    if (firConstructor.getIsPrimary() && (kind instanceof KtFakeSourceElementKind)) {
                        KtSourceElement source3 = firDelegatedConstructorCall.getSource();
                        KtSourceElement source4 = firClass.getSource();
                        if (Intrinsics.areEqual(source3, source4 != null ? KtSourceElementKt.fakeElement$default(source4, (KtFakeSourceElementKind) kind, null, 2, null) : null)) {
                            Iterator<T> it2 = firClass.getSuperTypeRefs().iterator();
                            do {
                                if (!it2.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it2.next();
                                coneType = FirTypeUtilsKt.getConeType((FirTypeRef) next);
                                resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firDelegatedConstructorCall);
                            } while (!Intrinsics.areEqual(coneType, resolvedCallableSymbol != null ? resolvedCallableSymbol.getResolvedReturnType() : null));
                            FirTypeRef firTypeRef2 = (FirTypeRef) next;
                            if (((firTypeRef2 == null || (source = firTypeRef2.getSource()) == null) ? null : source.getKind()) instanceof KtRealSourceElementKind) {
                                KtSourceElement source5 = firTypeRef2.getSource();
                                firDelegatedConstructorCall.replaceSource(source5 != null ? KtSourceElementKt.fakeElement$default(source5, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null) : null);
                            }
                        }
                    }
                    return firDelegatedConstructorCall;
                } catch (Throwable th2) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th2;
                }
            }
            return delegatedConstructorCall;
        } catch (Throwable th3) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(delegatedConstructorCall, th3);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, ResolutionMode data) {
        ResolutionMode resolutionMode;
        FirEqualityOperatorCall firEqualityOperatorCall;
        equalityOperatorCall.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            List<FirExpression> arguments = equalityOperatorCall.getArgumentList().getArguments();
            if (arguments.size() != 2) {
                throw new IllegalArgumentException(("Unexpected number of arguments in equality call: " + arguments.size()).toString());
            }
            getComponents().getDataFlowAnalyzer().enterEqualityOperatorCall();
            FirExpression firExpression = arguments.get(0);
            FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
            ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
            FirExpression firExpression2 = (FirExpression) firExpression.transform(transformer, contextIndependent);
            getComponents().getDataFlowAnalyzer().exitEqualityOperatorLhs();
            FirExpression firExpression3 = (FirExpression) arguments.get(1).transform(getTransformer(), resolutionModeForEqualityOperatorRhs(firExpression2));
            equalityOperatorCall.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), contextIndependent).replaceArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(firExpression2, firExpression3));
            equalityOperatorCall.replaceConeTypeOrNull(getSession().getBuiltinTypes().getBooleanType().getConeType());
            if (LanguageVersionUtilsKt.isDisabled(this, LanguageFeature.ResolveEqualsRhsInDependentContextWithCompletion) || hasProperTypeForEqualityOperatorCallArgument(firExpression3)) {
                resolutionMode = data;
                firEqualityOperatorCall = equalityOperatorCall;
            } else {
                resolutionMode = data;
                firEqualityOperatorCall = (FirEqualityOperatorCall) FirCallCompleter.completeCall$default(getComponents().getCallCompleter(), getTransformer().getComponents().getSyntheticCallGenerator().generateCalleeForEqualityOperatorCall(equalityOperatorCall, getTransformer().getResolutionContext(), data), resolutionMode, false, 4, null);
            }
            getComponents().getDataFlowAnalyzer().exitEqualityOperatorCall(equalityOperatorCall, resolutionMode.getForceFullCompletion());
            return firEqualityOperatorCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(equalityOperatorCall, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, ResolutionMode data) {
        errorAnnotationCall.getClass();
        data.getClass();
        return transformAnnotationCall((FirAnnotationCall) errorAnnotationCall, data);
    }

    public final <Q extends FirQualifiedAccessExpression> Q transformExplicitReceiverOf(Q qualifiedAccessExpression) {
        qualifiedAccessExpression.getClass();
        FirExpression explicitReceiver = qualifiedAccessExpression.getExplicitReceiver();
        if (explicitReceiver instanceof FirSuperReceiverExpression) {
            transformSuperReceiver((FirSuperReceiverExpression) explicitReceiver, qualifiedAccessExpression);
            return qualifiedAccessExpression;
        }
        if (explicitReceiver != null) {
            qualifiedAccessExpression.replaceExplicitReceiver(transformAsExplicitReceiver(explicitReceiver, ResolutionMode.ReceiverResolution.INSTANCE, false));
        }
        return qualifiedAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformExpression(FirExpression expression, ResolutionMode data) {
        expression.getClass();
        data.getClass();
        if (!FirTypeUtilsKt.getHasResolvedType(expression) && !(expression instanceof FirWrappedExpression)) {
            expression.replaceConeTypeOrNull(new ConeErrorType(new ConeSimpleDiagnostic("Type calculating for " + Reflection.getOrCreateKotlinClass(expression.getClass()) + " is not supported", DiagnosticKind.InferenceError), false, null, null, null, null, null, 126, null));
        }
        FirElement firElementTransformChildren = expression.transformChildren(getTransformer(), data);
        firElementTransformChildren.getClass();
        return (FirStatement) firElementTransformChildren;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformFunctionCall(FirFunctionCall functionCall, ResolutionMode data) {
        functionCall.getClass();
        data.getClass();
        return transformFunctionCallInternal$org_jetbrains_kotlin_resolve(functionCall, data, CallResolutionMode.REGULAR);
    }

    public final FirStatement transformFunctionCallInternal$org_jetbrains_kotlin_resolve(FirFunctionCall functionCall, ResolutionMode data, CallResolutionMode callResolutionMode) {
        FirFunctionCall firFunctionCall;
        OriginalCallData originalCallDataForPluginRefinedCall;
        functionCall.getClass();
        data.getClass();
        callResolutionMode.getClass();
        FirSession session = getSession();
        try {
            FirNamedReference calleeReference = functionCall.getCalleeReference();
            if ((calleeReference instanceof FirResolvedNamedReference) || (calleeReference instanceof FirErrorNamedReference)) {
                if (!FirTypeUtilsKt.getHasResolvedType(functionCall)) {
                    storeTypeFromCallee$org_jetbrains_kotlin_resolve(functionCall, false);
                }
            }
            if (!(calleeReference instanceof FirNamedReferenceWithCandidate)) {
                if (calleeReference instanceof FirSimpleNamedReference) {
                    functionCall.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
                    LambdaArgumentEffectsTransformerKt.replaceLambdaArgumentEffects(functionCall, getSession());
                    functionCall.transformTypeArguments((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                    boolean z = callResolutionMode == CallResolutionMode.OPTION_FOR_AUGMENTED_ASSIGNMENT;
                    if (z) {
                        firFunctionCall = functionCall;
                    } else {
                        getComponents().getDataFlowAnalyzer().enterCallArguments(functionCall, functionCall.getArgumentList().getArguments());
                        FirFunctionCall firFunctionCall2 = WhenMappings.$EnumSwitchMapping$1[callResolutionMode.ordinal()] == 1 ? functionCall : (FirFunctionCall) transformExplicitReceiverOf(functionCall);
                        getComponents().getDataFlowAnalyzer().exitCallExplicitReceiver();
                        if (getTransformer().getContext().getIsInsideAnnotationContext() && (data instanceof ResolutionMode.WithExpectedType)) {
                            transformCallArgumentsInsideAnnotationContext(functionCall, (ResolutionMode.WithExpectedType) data);
                        } else {
                            firFunctionCall2.replaceArgumentList((FirArgumentList) firFunctionCall2.getArgumentList().transform(this, ResolutionMode.ContextDependent.INSTANCE));
                        }
                        getComponents().getDataFlowAnalyzer().exitCallArguments();
                        firFunctionCall = firFunctionCall2;
                    }
                    FirFunctionCall firFunctionCallResolveCallAndSelectCandidate$default = FirCallResolver.resolveCallAndSelectCandidate$default(getComponents().getCallResolver(), firFunctionCall, data, null, 4, null);
                    if (!z) {
                        getComponents().getDataFlowAnalyzer().enterFunctionCall(firFunctionCallResolveCallAndSelectCandidate$default);
                    }
                    FirFunctionCall firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded = transformToIntegerOperatorCallOrApproximateItIfNeeded((FirFunctionCall) getComponents().getCallCompleter().completeCall(firFunctionCallResolveCallAndSelectCandidate$default, data, z), data);
                    if (!z) {
                        getComponents().getDataFlowAnalyzer().exitFunctionCall(firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded, data.getForceFullCompletion());
                    }
                    if (this.callRefinementExtensions != null) {
                        FirNamedReference calleeReference2 = firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded.getCalleeReference();
                        if ((calleeReference2 instanceof FirResolvedNamedReference) && (originalCallDataForPluginRefinedCall = FirFunctionCallRefinementExtensionKt.getOriginalCallDataForPluginRefinedCall(((FirResolvedNamedReference) calleeReference2).getResolvedSymbol().getFir())) != null) {
                            firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded = originalCallDataForPluginRefinedCall.getExtension().transform(firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded, originalCallDataForPluginRefinedCall.getOriginalSymbol());
                        }
                    }
                    FirExpressionsResolveTransformerKt.addReceiversFromExtensions(getTransformer().getContext(), firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded, getTransformer().getComponents());
                    return getTransformer().getContext().getIsInsideAnnotationContext() ? this.arrayOfCallTransformer.transformFunctionCall(firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded, getSession()) : addSmartcastIfNeeded(firFunctionCallTransformToIntegerOperatorCallOrApproximateItIfNeeded, data);
                }
                if (!(calleeReference instanceof FirResolvedNamedReference) && !(functionCall.getArgumentList() instanceof FirResolvedArgumentList)) {
                    functionCall.transformChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                }
            }
            return functionCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(functionCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformGetClassCall(FirGetClassCall getClassCall, ResolutionMode data) {
        ConeKotlinType resolvedType;
        ConeTypeProjection[] coneTypeProjectionArr;
        List<FirTypeParameterRef> typeParameters;
        getClassCall.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
            ResolutionMode resolutionModeWithExpectedType$default = ResolutionMode.ContextIndependent.INSTANCE;
            getClassCall.transformAnnotations((FirTransformer<? super ResolutionMode>) transformer, resolutionModeWithExpectedType$default);
            FirExpression argument = getClassCall.getArgument();
            if (argument instanceof FirLiteralExpression) {
                resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(UtilsKt.toFirResolvedTypeRef$default(BodyResolveUtilsKt.expectedConeType(((FirLiteralExpression) argument).getKind(), getSession()), null, null, 3, null), null, null, 6, null);
            }
            final FirExpression firExpressionTransformAsExplicitReceiver = transformAsExplicitReceiver(getClassCall.getArgument(), resolutionModeWithExpectedType$default, true);
            getClassCall.getArgumentList().transformArguments(new FirTransformer() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer$transformGetClassCall$1$transformedGetClassCall$1$1
                @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
                public <E extends FirElement> E transformElement(E element, Void data2) {
                    element.getClass();
                    FirExpression firExpression = firExpressionTransformAsExplicitReceiver;
                    firExpression.getClass();
                    return firExpression;
                }
            }, null);
            FirExpression argument2 = getClassCall.getArgument();
            if (argument2 instanceof FirResolvedQualifier) {
                ((FirResolvedQualifier) argument2).replaceResolvedToCompanionObject(false);
                FirClassLikeSymbol<?> symbol = ((FirResolvedQualifier) argument2).getSymbol();
                if (((FirResolvedQualifier) argument2).getTypeArguments().isEmpty()) {
                    FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
                    if (firClassLikeDeclaration == null) {
                        firClassLikeDeclaration = null;
                    }
                    int size = (firClassLikeDeclaration == null || (typeParameters = firClassLikeDeclaration.getTypeParameters()) == null) ? 0 : typeParameters.size();
                    coneTypeProjectionArr = new ConeTypeProjection[size];
                    for (int i = 0; i < size; i++) {
                        coneTypeProjectionArr[i] = ConeStarProjection.INSTANCE;
                    }
                } else {
                    List<FirTypeProjection> typeArguments = ((FirResolvedQualifier) argument2).getTypeArguments();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));
                    Iterator<T> it = typeArguments.iterator();
                    while (it.hasNext()) {
                        arrayList.add(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it.next()));
                    }
                    coneTypeProjectionArr = (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]);
                }
                resolvedType = symbol != null ? TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) symbol, coneTypeProjectionArr, false, (ConeAttributes) null, 6, (Object) null) : null;
                if (resolvedType != null) {
                    FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) argument2;
                    FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
                    if (lookupTracker != null) {
                        FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, resolvedType, getClassCall.getSource(), getTransformer().getComponents().getFile().getSource());
                    }
                    firResolvedQualifier.replaceConeTypeOrNull(resolvedType);
                } else {
                    resolvedType = FirTypeUtilsKt.getResolvedType(argument2);
                }
            } else if (argument2 instanceof FirResolvedReifiedParameterReference) {
                resolvedType = TypeConstructionUtilsKt.constructType$default((FirClassifierSymbol) ((FirResolvedReifiedParameterReference) argument2).getSymbol(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
            } else {
                if (!shouldComputeTypeOfGetClassCallWithNotQualifierInLhs(getClassCall)) {
                    return getClassCall;
                }
                resolvedType = FirTypeUtilsKt.getResolvedType(argument2);
                if (!(resolvedType instanceof ConeErrorType)) {
                    resolvedType = new ConeKotlinTypeProjectionOut(resolvedType);
                }
            }
            getClassCall.replaceConeTypeOrNull(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKClass(), new ConeKotlinTypeProjection[]{resolvedType}, false, null, 4, null));
            getComponents().getDataFlowAnalyzer().exitGetClassCall(getClassCall);
            return getClassCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(getClassCall, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIncrementDecrementExpression(FirIncrementDecrementExpression incrementDecrementExpression, ResolutionMode data) {
        FirExpression expression;
        KtSourceElement ktSourceElementFakeElement$default;
        incrementDecrementExpression.getClass();
        data.getClass();
        KtFakeSourceElementKind.DesugaredIncrementOrDecrement desugaredIncrementOrDecrementSourceKindForIncOrDec = KtSourceElementKt.sourceKindForIncOrDec(incrementDecrementExpression.getOperationName(), incrementDecrementExpression.getIsPrefix());
        FirAbstractBodyResolveTransformerDispatcher transformer = getTransformer();
        ResolutionMode.ContextIndependent contextIndependent = ResolutionMode.ContextIndependent.INSTANCE;
        incrementDecrementExpression.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) transformer, contextIndependent);
        FirExpression firExpression = (FirExpression) FirTransformerUtilKt.transformSingle(incrementDecrementExpression.getExpression(), getTransformer(), contextIndependent);
        KtSourceElement ktSourceElementFakeElement$default2 = null;
        if (firExpression instanceof FirQualifiedAccessExpression) {
            FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firExpression;
            KtSourceElement source = firQualifiedAccessExpression.getSource();
            firQualifiedAccessExpression.replaceSource(source != null ? KtSourceElementKt.fakeElement$default(source, desugaredIncrementOrDecrementSourceKindForIncOrDec, null, 2, null) : null);
        }
        KtSourceElement source2 = incrementDecrementExpression.getSource();
        KtSourceElement ktSourceElementFakeElement$default3 = source2 != null ? KtSourceElementKt.fakeElement$default(source2, desugaredIncrementOrDecrementSourceKindForIncOrDec, null, 2, null) : null;
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        firBlockBuilder.setSource(ktSourceElementFakeElement$default3);
        CollectionsKt.addAll(firBlockBuilder.getAnnotations(), incrementDecrementExpression.getAnnotations());
        FirProperty firPropertyGenerateExplicitReceiverTemporaryVariable = FirGenerationKt.generateExplicitReceiverTemporaryVariable(getSession(), firExpression, ktSourceElementFakeElement$default3);
        if (firPropertyGenerateExplicitReceiverTemporaryVariable != null) {
            firBlockBuilder.getStatements().add(firPropertyGenerateExplicitReceiverTemporaryVariable);
        }
        if (incrementDecrementExpression.getIsPrefix()) {
            firBlockBuilder.getStatements().add(transformIncrementDecrementExpression$buildAndResolveVariableAssignment(this, ktSourceElementFakeElement$default3, firExpression, desugaredIncrementOrDecrementSourceKindForIncOrDec, transformIncrementDecrementExpression$buildAndResolveOperatorCall(this, incrementDecrementExpression, firExpression, desugaredIncrementOrDecrementSourceKindForIncOrDec)));
            List<FirStatement> statements = firBlockBuilder.getStatements();
            FirDesugaredAssignmentValueReferenceExpressionBuilder firDesugaredAssignmentValueReferenceExpressionBuilder = new FirDesugaredAssignmentValueReferenceExpressionBuilder();
            FirErrorExpression firErrorExpression = firExpression instanceof FirErrorExpression ? (FirErrorExpression) firExpression : null;
            if (firErrorExpression == null || (expression = firErrorExpression.getExpression()) == null) {
                expression = firExpression;
            }
            KtSourceElement source3 = expression.getSource();
            if (source3 != null && (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source3, desugaredIncrementOrDecrementSourceKindForIncOrDec, null, 2, null)) != null) {
                ktSourceElementFakeElement$default2 = ktSourceElementFakeElement$default;
            } else if (ktSourceElementFakeElement$default3 != null) {
                ktSourceElementFakeElement$default2 = KtSourceElementKt.fakeElement$default(ktSourceElementFakeElement$default3, KtFakeSourceElementKind.DesugaredAssignmentLValueSourceIsNull.INSTANCE, null, 2, null);
            }
            firDesugaredAssignmentValueReferenceExpressionBuilder.setSource(ktSourceElementFakeElement$default2);
            FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
            firExpressionRef.bind(FirExpressionUtilKt.unwrapSmartcastExpression(firExpression));
            firDesugaredAssignmentValueReferenceExpressionBuilder.setExpressionRef(firExpressionRef);
            FirDesugaredAssignmentValueReferenceExpression firDesugaredAssignmentValueReferenceExpressionMo288build = firDesugaredAssignmentValueReferenceExpressionBuilder.mo288build();
            FirTransformerUtilKt.transformSingle(firDesugaredAssignmentValueReferenceExpressionMo288build, getTransformer(), contextIndependent);
            statements.add(transformExpressionUsingSmartcastInfo(firDesugaredAssignmentValueReferenceExpressionMo288build));
        } else {
            FirProperty firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable = transformIncrementDecrementExpression$generateTemporaryVariable(this, ktSourceElementFakeElement$default3, SpecialNames.UNARY, firExpression);
            getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable);
            getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable, false);
            firBlockBuilder.getStatements().add(firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable);
            firBlockBuilder.getStatements().add(transformIncrementDecrementExpression$buildAndResolveVariableAssignment(this, ktSourceElementFakeElement$default3, firExpression, desugaredIncrementOrDecrementSourceKindForIncOrDec, transformIncrementDecrementExpression$buildAndResolveOperatorCall(this, incrementDecrementExpression, transformExpressionUsingSmartcastInfo(FirGenerationKt.toQualifiedAccess$default(firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable, null, null, 3, null)), desugaredIncrementOrDecrementSourceKindForIncOrDec)));
            firBlockBuilder.getStatements().add(transformExpressionUsingSmartcastInfo(FirGenerationKt.toQualifiedAccess$default(firPropertyTransformIncrementDecrementExpression$generateTemporaryVariable, null, null, 3, null)));
        }
        FirBlock firBlockMo288build = firBlockBuilder.mo288build();
        Object objLast = CollectionsKt.last(firBlockMo288build.getStatements());
        objLast.getClass();
        firBlockMo288build.replaceConeTypeOrNull(FirTypeUtilsKt.getResolvedType((FirExpression) objLast));
        return firBlockMo288build;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, ResolutionMode data) {
        FirIndexedAccessAugmentedAssignment firIndexedAccessAugmentedAssignment;
        indexedAccessAugmentedAssignment.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirOperation operation = indexedAccessAugmentedAssignment.getOperation();
            FirOperation.INSTANCE.getASSIGNMENTS().contains(operation);
            FirOperation.Companion companion = FirOperation.INSTANCE;
            KtFakeSourceElementKind.DesugaredAugmentedAssign augmentedAssignSourceKind = UtilsKt.toAugmentedAssignSourceKind(operation);
            indexedAccessAugmentedAssignment.transformAnnotations((FirTransformer<? super ResolutionMode>) getTransformer(), data);
            getComponents().getDataFlowAnalyzer().enterCallArguments(indexedAccessAugmentedAssignment, CollectionsKt.listOf(indexedAccessAugmentedAssignment.getRhs()));
            FirElement firElementTransformSingle = FirTransformerUtilKt.transformSingle(indexedAccessAugmentedAssignment.getLhsGetCall(), getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            FirExpressionsResolveTransformerKt.setIndexedAccessAugmentedAssignSource((FirFunctionCall) firElementTransformSingle, augmentedAssignSourceKind);
            FirFunctionCall firFunctionCall = (FirFunctionCall) firElementTransformSingle;
            FirExpression firExpression = (FirExpression) FirTransformerUtilKt.transformSingle(indexedAccessAugmentedAssignment.getRhs(), getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
            getComponents().getDataFlowAnalyzer().exitCallArguments();
            firIndexedAccessAugmentedAssignment = indexedAccessAugmentedAssignment;
            try {
                FirFunctionCall firFunctionCallResolveCandidateForAssignmentOperatorCall = resolveCandidateForAssignmentOperatorCall(new GeneratorOfPlusAssignCalls(firIndexedAccessAugmentedAssignment, indexedAccessAugmentedAssignment.getCalleeReference().getSource(), operation, firFunctionCall, firExpression).createAssignOperatorCall(UtilsKt.toAugmentedAssignSourceKind(operation)));
                FirNamedReference calleeReference = firFunctionCallResolveCandidateForAssignmentOperatorCall.getCalleeReference();
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
                boolean z = true;
                boolean z2 = (firNamedReferenceWithCandidate == null || firNamedReferenceWithCandidate.isError()) ? false : true;
                FirBasedSymbol resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
                if (Intrinsics.areEqual(resolvedBaseSymbol$default != null ? resolvedBaseSymbol$default.getOrigin() : null, FirDeclarationOrigin.DynamicScope.INSTANCE)) {
                    return transformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign(this, firFunctionCallResolveCandidateForAssignmentOperatorCall);
                }
                IndexedAccessAugmentedAssignmentDesugaringInfo indexedAccessAugmentedAssignmentDesugaringInfoTryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock = tryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock(firIndexedAccessAugmentedAssignment, firFunctionCall, firExpression, augmentedAssignSourceKind);
                FirNamedReference calleeReference2 = indexedAccessAugmentedAssignmentDesugaringInfoTryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock.getOperatorCall().getCalleeReference();
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2 = calleeReference2 instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference2 : null;
                boolean z3 = (firNamedReferenceWithCandidate2 == null || firNamedReferenceWithCandidate2.isError()) ? false : true;
                if (z2 && !z3) {
                    return transformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign(this, firFunctionCallResolveCandidateForAssignmentOperatorCall);
                }
                FirFunctionCall setCall = indexedAccessAugmentedAssignmentDesugaringInfoTryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock.getSetCall();
                FirNamedReference calleeReference3 = setCall.getCalleeReference();
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate3 = calleeReference3 instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference3 : null;
                if (firNamedReferenceWithCandidate3 == null || firNamedReferenceWithCandidate3.isError()) {
                    z = false;
                }
                if (z2 && z) {
                    return transformIndexedAccessAugmentedAssignment$lambda$0$reportAmbiguity(this, firFunctionCallResolveCandidateForAssignmentOperatorCall, firIndexedAccessAugmentedAssignment, firNamedReferenceWithCandidate, firNamedReferenceWithCandidate3);
                }
                if (z2) {
                    return transformIndexedAccessAugmentedAssignment$lambda$0$chooseAssign(this, firFunctionCallResolveCandidateForAssignmentOperatorCall);
                }
                return z ? transformIndexedAccessAugmentedAssignment$lambda$0$chooseSetOperator(this, setCall, indexedAccessAugmentedAssignmentDesugaringInfoTryResolveIndexedAccessAugmentedAssignmentAsSetGetBlock) : transformIndexedAccessAugmentedAssignment$lambda$0$reportUnresolvedReference(operation, this, firFunctionCallResolveCandidateForAssignmentOperatorCall, firIndexedAccessAugmentedAssignment);
            } catch (Throwable th) {
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firIndexedAccessAugmentedAssignment, th);
                wq6.a();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            firIndexedAccessAugmentedAssignment = indexedAccessAugmentedAssignment;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLiteralExpression(FirLiteralExpression literalExpression, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeIntegerLiteralConstantTypeImpl approximatedType;
        literalExpression.getClass();
        data.getClass();
        literalExpression.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
        ConstantValueKind kind = literalExpression.getKind();
        if (Intrinsics.areEqual(kind, ConstantValueKind.IntegerLiteral.INSTANCE) || Intrinsics.areEqual(kind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE)) {
            ConeIntegerLiteralConstantTypeImpl.Companion companion = ConeIntegerLiteralConstantTypeImpl.Companion;
            Object value = literalExpression.getValue();
            value.getClass();
            ConeIntegerLiteralConstantTypeImpl coneIntegerLiteralConstantTypeImplCreate = companion.create(((Long) value).longValue(), Intrinsics.areEqual(kind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE), new Function1() { // from class: u65
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirExpressionsResolveTransformer.b(this.b, (ConeClassLikeType) obj));
                }
            });
            ConeKotlinType expectedType = ResolutionModeKt.getExpectedType(data);
            if (coneIntegerLiteralConstantTypeImplCreate instanceof ConeErrorType) {
                approximatedType = coneIntegerLiteralConstantTypeImplCreate;
            } else {
                if (coneIntegerLiteralConstantTypeImplCreate instanceof ConeClassLikeType) {
                    ConstantValueKind constKind = FirTypeUtilsKt.toConstKind((ConeClassLikeType) coneIntegerLiteralConstantTypeImplCreate);
                    constKind.getClass();
                    literalExpression.replaceKind(constKind);
                } else {
                    boolean z = data instanceof ResolutionMode.ReceiverResolution;
                    if (!z || ((ResolutionMode.ReceiverResolution) data).getForCallableReference()) {
                        if ((data instanceof ResolutionMode.WithExpectedType) || (data instanceof ResolutionMode.ContextIndependent) || (data instanceof ResolutionMode.AssignmentLValue) || z) {
                            if (!(coneIntegerLiteralConstantTypeImplCreate instanceof ConeIntegerLiteralConstantTypeImpl)) {
                                w01.a("Failed requirement.");
                                return null;
                            }
                            approximatedType = coneIntegerLiteralConstantTypeImplCreate.getApproximatedType(expectedType != null ? TypeExpansionUtilsKt.fullyExpandedType(this, expectedType) : null);
                            ConstantValueKind constKind2 = FirTypeUtilsKt.toConstKind(approximatedType);
                            constKind2.getClass();
                            literalExpression.replaceKind(constKind2);
                        }
                    } else {
                        if (!(coneIntegerLiteralConstantTypeImplCreate instanceof ConeIntegerLiteralConstantTypeImpl)) {
                            w01.a("Failed requirement.");
                            return null;
                        }
                        approximatedType = new ConeIntegerConstantOperatorTypeImpl(coneIntegerLiteralConstantTypeImplCreate.getIsUnsigned(), false);
                    }
                }
                approximatedType = coneIntegerLiteralConstantTypeImplCreate;
            }
        } else {
            approximatedType = BodyResolveUtilsKt.expectedConeType(kind, getSession());
        }
        getComponents().getDataFlowAnalyzer().exitLiteralExpression(literalExpression);
        literalExpression.replaceConeTypeOrNull(approximatedType);
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(literalExpression);
        if (!(resolvedType instanceof ConeErrorType)) {
            return literalExpression;
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setExpression(literalExpression);
        firErrorExpressionBuilder.setDiagnostic(((ConeErrorType) resolvedType).getDiagnostic());
        firErrorExpressionBuilder.setSource(literalExpression.getSource());
        return firErrorExpressionBuilder.mo288build();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall, ResolutionMode data) {
        multiDelegatedConstructorCall.getClass();
        data.getClass();
        multiDelegatedConstructorCall.transformChildren(getTransformer(), data);
        return multiDelegatedConstructorCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, ResolutionMode data) {
        propertyAccessExpression.getClass();
        data.getClass();
        return transformQualifiedAccessExpression((FirQualifiedAccessExpression) propertyAccessExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformQualifiedErrorAccessExpression(FirQualifiedErrorAccessExpression qualifiedErrorAccessExpression, ResolutionMode data) {
        qualifiedErrorAccessExpression.getClass();
        data.getClass();
        qualifiedErrorAccessExpression.transformAnnotations((FirTransformer<? super ResolutionMode>) this, data);
        qualifiedErrorAccessExpression.transformSelector(this, data);
        qualifiedErrorAccessExpression.replaceReceiver(transformAsExplicitReceiver(qualifiedErrorAccessExpression.getReceiver(), ResolutionMode.ReceiverResolution.INSTANCE, false));
        return qualifiedErrorAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplDeclarationReference(FirReplDeclarationReference replDeclarationReference, ResolutionMode data) {
        replDeclarationReference.getClass();
        data.getClass();
        return replDeclarationReference;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplExpressionReference(FirReplExpressionReference replExpressionReference, ResolutionMode data) {
        replExpressionReference.getClass();
        data.getClass();
        return replExpressionReference;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplPropertyDelegate(FirReplPropertyDelegate replPropertyDelegate, ResolutionMode data) {
        FirExpressionRef<FirExpression> expressionRef;
        replPropertyDelegate.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirProperty firProperty = (FirProperty) replPropertyDelegate.getPropertySymbol().getFir();
            boolean z = !(firProperty.getReturnTypeRef() instanceof FirImplicitTypeRef);
            getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(firProperty);
            Object objLast = getTransformer().getContext().getContainers().last();
            objLast.getClass();
            FirNamedFunction firNamedFunction = (FirNamedFunction) objLast;
            Map<FirPropertySymbol, FirProperty> replSnippetDelegatedPropertyCopies = DeclarationAttributesKt.getReplSnippetDelegatedPropertyCopies(firNamedFunction);
            FirProperty firProperty2 = replSnippetDelegatedPropertyCopies != null ? replSnippetDelegatedPropertyCopies.get(firProperty.getSymbol()) : null;
            if (firProperty2 != null) {
                FirDeclarationsResolveTransformer declarationsTransformer = getTransformer().getDeclarationsTransformer();
                if (declarationsTransformer != null) {
                    declarationsTransformer.transformProperty(firProperty2, data);
                }
                FirExpression delegate = firProperty2.getDelegate();
                if (delegate == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                replPropertyDelegate.replaceDelegate(delegate);
                FirExpression delegate2 = firProperty.getDelegate();
                FirReplExpressionReference firReplExpressionReference = delegate2 instanceof FirReplExpressionReference ? (FirReplExpressionReference) delegate2 : null;
                if (firReplExpressionReference != null && (expressionRef = firReplExpressionReference.getExpressionRef()) != null) {
                    expressionRef.bind(delegate);
                }
                Map<FirPropertySymbol, FirProperty> replSnippetDelegatedPropertyCopies2 = DeclarationAttributesKt.getReplSnippetDelegatedPropertyCopies(firNamedFunction);
                if (replSnippetDelegatedPropertyCopies2 != null) {
                    replSnippetDelegatedPropertyCopies2.remove(firProperty.getSymbol());
                }
            }
            getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(firProperty, z);
            Unit unit = Unit.INSTANCE;
            return replPropertyDelegate;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(replPropertyDelegate, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplPropertyInitializer(FirReplPropertyInitializer replPropertyInitializer, ResolutionMode data) {
        FirExpressionRef<FirExpression> expressionRef;
        replPropertyInitializer.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirProperty firProperty = (FirProperty) replPropertyInitializer.getPropertySymbol().getFir();
            boolean z = !(firProperty.getReturnTypeRef() instanceof FirImplicitTypeRef);
            ResolutionMode resolutionModeWithExpectedType$default = ResolutionModeKt.withExpectedType$default(firProperty.getReturnTypeRef(), null, null, 6, null);
            getComponents().getDataFlowAnalyzer().enterLocalVariableDeclaration(firProperty);
            replPropertyInitializer.transformInitializer(getTransformer(), resolutionModeWithExpectedType$default);
            FirExpression initializer = firProperty.getInitializer();
            FirReplExpressionReference firReplExpressionReference = initializer instanceof FirReplExpressionReference ? (FirReplExpressionReference) initializer : null;
            if (firReplExpressionReference != null && (expressionRef = firReplExpressionReference.getExpressionRef()) != null) {
                expressionRef.bind(replPropertyInitializer.getInitializer());
            }
            getComponents().getDataFlowAnalyzer().exitLocalVariableDeclaration(firProperty, z);
            Unit unit = Unit.INSTANCE;
            return replPropertyInitializer;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(replPropertyInitializer, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSafeCallExpression(FirSafeCallExpression safeCallExpression, ResolutionMode data) {
        safeCallExpression.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            FirSafeCallExpression firSafeCallExpression = this.containingSafeCallExpression;
            try {
                this.containingSafeCallExpression = safeCallExpression;
                safeCallExpression.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) this, ResolutionMode.ContextIndependent.INSTANCE);
                safeCallExpression.transformReceiver(this, ResolutionMode.ReceiverResolution.INSTANCE);
                safeCallExpression.transformReceiver(getTransformer().getComponents().getIntegerLiteralAndOperatorApproximationTransformer(), null);
                FirExpression receiver = safeCallExpression.getReceiver();
                getComponents().getDataFlowAnalyzer().enterSafeCallAfterNullCheck(safeCallExpression);
                ResolveUtilsKt.propagateTypeFromOriginalReceiver((FirCheckedSafeCallSubject) safeCallExpression.getCheckedSubjectRef().getValue(), receiver, getTransformer().getComponents().getSession(), getTransformer().getComponents().getFile());
                safeCallExpression.transformSelector(this, data);
                ResolveUtilsKt.propagateTypeFromQualifiedAccessAfterNullCheck(safeCallExpression, getSession(), getTransformer().getContext().getFile());
                getComponents().getDataFlowAnalyzer().exitSafeCall(safeCallExpression);
                return safeCallExpression;
            } finally {
                this.containingSafeCallExpression = firSafeCallExpression;
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(safeCallExpression, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSmartCastExpression(FirSmartCastExpression smartCastExpression, ResolutionMode data) {
        smartCastExpression.getClass();
        data.getClass();
        return smartCastExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, ResolutionMode data) {
        stringConcatenationCall.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            getComponents().getDataFlowAnalyzer().enterStringConcatenationCall();
            stringConcatenationCall.transformChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            getComponents().getDataFlowAnalyzer().exitStringConcatenationCall(stringConcatenationCall);
            return stringConcatenationCall;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(stringConcatenationCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:81:0x0149  */
    /* JADX WARN: Code duplicated, block: B:83:0x014f  */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirQualifiedAccessExpression transformSuperReceiver(FirSuperReceiverExpression superReferenceContainer, FirQualifiedAccessExpression containingCall) {
        FirResolvedTypeRef firResolvedTypeRefBuild;
        FirResolvedTypeRef firResolvedTypeRefBuild2;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        Object next;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType2;
        FirClassSymbol<?> boundSymbol;
        FirClass firClass;
        FirExpression receiverExpression;
        superReferenceContainer.getClass();
        FirSuperReference calleeReference = superReferenceContainer.getCalleeReference();
        String labelName = calleeReference.getLabelName();
        ImplicitDispatchReceiverValue implicitDispatchReceiverValueLastDispatchReceiver = getComponents().getImplicitValueStorage().lastDispatchReceiver();
        ConeClassLikeType coneClassLikeType = null;
        if (labelName != null && implicitDispatchReceiverValueLastDispatchReceiver != null) {
            Set<ImplicitReceiverValue<?>> set = getComponents().getImplicitValueStorage().get(labelName);
            Object objSingleOrNull = CollectionsKt.singleOrNull(set);
            implicitDispatchReceiverValueLastDispatchReceiver = objSingleOrNull instanceof ImplicitDispatchReceiverValue ? (ImplicitDispatchReceiverValue) objSingleOrNull : null;
            if (implicitDispatchReceiverValueLastDispatchReceiver == null) {
                return markSuperReferenceError(set.size() >= 2 ? new ConeSimpleDiagnostic("Ambiguous label", DiagnosticKind.AmbiguousLabel) : new ConeSimpleDiagnostic("Unresolved label", DiagnosticKind.UnresolvedLabel), superReferenceContainer, calleeReference);
            }
        }
        if (implicitDispatchReceiverValueLastDispatchReceiver != null && (receiverExpression = implicitDispatchReceiverValueLastDispatchReceiver.getReceiverExpression()) != null) {
            superReferenceContainer.replaceDispatchReceiver(receiverExpression);
        }
        List<FirTypeRef> superTypeRefs = (implicitDispatchReceiverValueLastDispatchReceiver == null || (boundSymbol = implicitDispatchReceiverValueLastDispatchReceiver.getBoundSymbol()) == null || (firClass = (FirClass) boundSymbol.getFir()) == null) ? null : firClass.getSuperTypeRefs();
        FirTypeRef superTypeRef = calleeReference.getSuperTypeRef();
        if (containingCall == null) {
            return markSuperReferenceError(new ConeSimpleDiagnostic("Super not allowed", DiagnosticKind.SuperNotAllowed), superReferenceContainer, calleeReference);
        }
        if (implicitDispatchReceiverValueLastDispatchReceiver == null || superTypeRefs == null || superTypeRefs.isEmpty()) {
            return markSuperReferenceError(CollectionsKt.lastOrNull(getComponents().getImplicitValueStorage().getImplicitReceivers()) instanceof InaccessibleImplicitReceiverValue ? new ConeInstanceAccessBeforeSuperCall("<super>") : new ConeSimpleDiagnostic("Super not available", DiagnosticKind.SuperNotAvailable), superReferenceContainer, calleeReference);
        }
        if (superTypeRef instanceof FirResolvedTypeRef) {
            superReferenceContainer.replaceConeTypeOrNull(((FirResolvedTypeRef) superTypeRef).getConeType());
            return superReferenceContainer;
        }
        if (superTypeRef instanceof FirImplicitTypeRef) {
            List<ConeKotlinType> listFindTypesForSuperCandidates = SuperCallsKt.findTypesForSuperCandidates(getTransformer().getComponents(), superTypeRefs, containingCall);
            int size = listFindTypesForSuperCandidates.size();
            if (size == 0) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setSource(superReferenceContainer.getSource());
                firErrorTypeRefBuilder.setDiagnostic(new ConeUnreportedDuplicateDiagnostic(new ConeSimpleDiagnostic("Unresolved super method", DiagnosticKind.Other)));
                firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
            } else if (size != 1) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder2.setSource(superReferenceContainer.getSource());
                firErrorTypeRefBuilder2.setDiagnostic(new ConeAmbiguousSuper(listFindTypesForSuperCandidates));
                firResolvedTypeRefBuild = firErrorTypeRefBuilder2.build();
            } else {
                ConeKotlinType coneKotlinType = (ConeKotlinType) CollectionsKt.single(listFindTypesForSuperCandidates);
                KtSourceElement source = superReferenceContainer.getSource();
                firResolvedTypeRefBuild = UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SuperCallImplicitType.INSTANCE, null, 2, null) : null, null, 2, null);
            }
            superReferenceContainer.replaceConeTypeOrNull(firResolvedTypeRefBuild.getConeType());
            calleeReference.replaceSuperTypeRef(firResolvedTypeRefBuild);
            return superReferenceContainer;
        }
        FirSpecificTypeResolverTransformer typeResolverTransformer = getTransformer().getComponents().getTypeResolverTransformer();
        boolean areBareTypesAllowed = typeResolverTransformer.getAreBareTypesAllowed();
        typeResolverTransformer.setAreBareTypesAllowed(true);
        try {
            calleeReference.transformChildren(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(calleeReference.getSuperTypeRef());
            ConeClassLikeType coneClassLikeType2 = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            if (coneClassLikeType2 != null && (coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType2)) != null) {
                ClassId classId = coneClassLikeTypeFullyExpandedType.getLookupTag().getClassId();
                Iterator<T> it = superTypeRefs.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType((FirTypeRef) next))), classId));
                FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) next;
                if (firResolvedTypeRef != null) {
                    FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef instanceof FirResolvedTypeRef ? firResolvedTypeRef : null;
                    ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
                    if (!(coneType2 instanceof ConeClassLikeType)) {
                        coneType2 = null;
                    }
                    ConeClassLikeType coneClassLikeType3 = (ConeClassLikeType) coneType2;
                    if (coneClassLikeType3 != null && (coneClassLikeTypeFullyExpandedType2 = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType3)) != null) {
                        if (coneClassLikeTypeFullyExpandedType.getTypeArguments().length == 0) {
                            if (!(coneClassLikeTypeFullyExpandedType2.getTypeArguments().length == 0)) {
                                coneClassLikeType = coneClassLikeTypeFullyExpandedType2;
                            } else if (Intrinsics.areEqual(coneClassLikeTypeFullyExpandedType, coneClassLikeTypeFullyExpandedType2)) {
                                coneClassLikeType = coneClassLikeTypeFullyExpandedType2;
                            }
                        } else if (Intrinsics.areEqual(coneClassLikeTypeFullyExpandedType, coneClassLikeTypeFullyExpandedType2)) {
                            coneClassLikeType = coneClassLikeTypeFullyExpandedType2;
                        }
                    }
                }
            }
            if (coneClassLikeType == null || (firResolvedTypeRefBuild2 = UtilsKt.toFirResolvedTypeRef(coneClassLikeType, superTypeRef.getSource(), superTypeRef)) == null) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder3 = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder3.setSource(superTypeRef.getSource());
                firErrorTypeRefBuilder3.setDiagnostic(new ConeSimpleDiagnostic("Not a super type", DiagnosticKind.NotASupertype));
                firErrorTypeRefBuilder3.setAnnotations(CollectionsKt.toMutableList(superTypeRef.getAnnotations()));
                firResolvedTypeRefBuild2 = firErrorTypeRefBuilder3.build();
            }
            superReferenceContainer.replaceConeTypeOrNull(firResolvedTypeRefBuild2.getConeType());
            calleeReference.replaceSuperTypeRef(firResolvedTypeRefBuild2);
            return superReferenceContainer;
        } catch (Throwable th) {
            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSuperReceiverExpression(FirSuperReceiverExpression superReceiverExpression, ResolutionMode data) {
        superReceiverExpression.getClass();
        data.getClass();
        return transformQualifiedAccessExpression((FirQualifiedAccessExpression) superReceiverExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, ResolutionMode data) {
        thisReceiverExpression.getClass();
        data.getClass();
        return transformQualifiedAccessExpression((FirQualifiedAccessExpression) thisReceiverExpression, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, ResolutionMode data) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirTypeOperatorCall firTypeOperatorCallTransformConversionTypeRef;
        typeOperatorCall.getClass();
        data.getClass();
        FirSpecificTypeResolverTransformer typeResolverTransformer = getTransformer().getComponents().getTypeResolverTransformer();
        boolean areBareTypesAllowed = typeResolverTransformer.getAreBareTypesAllowed();
        typeResolverTransformer.setAreBareTypesAllowed(true);
        try {
            if (typeOperatorCall.getOperation() == FirOperation.IS || typeOperatorCall.getOperation() == FirOperation.NOT_IS) {
                FirSpecificTypeResolverTransformer typeResolverTransformer2 = getTransformer().getComponents().getTypeResolverTransformer();
                boolean isOperandOfIsOperator = typeResolverTransformer2.getIsOperandOfIsOperator();
                typeResolverTransformer2.setOperandOfIsOperator(true);
                try {
                    FirTypeOperatorCall firTypeOperatorCallTransformConversionTypeRef2 = typeOperatorCall.transformConversionTypeRef(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                    typeResolverTransformer2.setOperandOfIsOperator(isOperandOfIsOperator);
                    firTypeOperatorCallTransformConversionTypeRef = firTypeOperatorCallTransformConversionTypeRef2;
                } catch (Throwable th) {
                    typeResolverTransformer2.setOperandOfIsOperator(isOperandOfIsOperator);
                    throw th;
                }
            } else {
                firTypeOperatorCallTransformConversionTypeRef = typeOperatorCall.transformConversionTypeRef(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            }
            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            final FirTypeOperatorCall firTypeOperatorCallTransformTypeOperatorCallChildren = transformTypeOperatorCallChildren(firTypeOperatorCallTransformConversionTypeRef);
            resolveConversionTypeRefInContextSensitiveModeIfNecessary(firTypeOperatorCallTransformTypeOperatorCallChildren);
            final FirTypeRef firTypeRefWithTypeArgumentsForBareType = withTypeArgumentsForBareType(firTypeOperatorCallTransformTypeOperatorCallChildren.getConversionTypeRef(), (FirExpression) CollectionsKt.first(firTypeOperatorCallTransformTypeOperatorCallChildren.getArgumentList().getArguments()), typeOperatorCall.getOperation());
            firTypeOperatorCallTransformTypeOperatorCallChildren.transformChildren(new FirDefaultTransformer<Object>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer.transformTypeOperatorCall.1
                @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
                public <E extends FirElement> E transformElement(E element, Object data2) {
                    element.getClass();
                    return element;
                }

                @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
                /* JADX INFO: renamed from: transformTypeRef */
                public FirTypeRef mo600transformTypeRef(FirTypeRef typeRef, Object data2) {
                    typeRef.getClass();
                    return typeRef == firTypeOperatorCallTransformTypeOperatorCallChildren.getConversionTypeRef() ? firTypeRefWithTypeArgumentsForBareType : typeRef;
                }
            }, null);
            int i = WhenMappings.$EnumSwitchMapping$2[firTypeOperatorCallTransformTypeOperatorCallChildren.getOperation().ordinal()];
            if (i == 1 || i == 2) {
                firTypeOperatorCallTransformTypeOperatorCallChildren.replaceConeTypeOrNull(getSession().getBuiltinTypes().getBooleanType().getConeType());
            } else if (i == 3) {
                firTypeOperatorCallTransformTypeOperatorCallChildren.replaceConeTypeOrNull(FirTypeUtilsKt.getConeType(firTypeRefWithTypeArgumentsForBareType));
            } else {
                if (i != 4) {
                    f2f.a("Unknown type operator: ", firTypeOperatorCallTransformTypeOperatorCallChildren.getOperation());
                    return null;
                }
                firTypeOperatorCallTransformTypeOperatorCallChildren.replaceConeTypeOrNull(TypeUtilsKt.withNullability$default(FirTypeUtilsKt.getConeType(firTypeRefWithTypeArgumentsForBareType), true, TypeComponentsKt.getTypeContext(getSession()), null, false, 12, null));
            }
            getComponents().getDataFlowAnalyzer().exitTypeOperatorCall(firTypeOperatorCallTransformTypeOperatorCallChildren);
            return firTypeOperatorCallTransformTypeOperatorCallChildren;
        } catch (Throwable th2) {
            typeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformVariableAssignment(FirVariableAssignment variableAssignment, ResolutionMode data) {
        variableAssignment.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            variableAssignment.transformAnnotations((FirTransformer<? super ResolutionMode.ContextIndependent>) getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
            variableAssignment.transformLValue(getTransformer(), new ResolutionMode.AssignmentLValue(variableAssignment));
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(variableAssignment);
            List<FirAssignExpressionAltererExtension> list = this.assignAltererExtensions;
            if (list != null && (calleeReference instanceof FirResolvedNamedReference)) {
                ArrayList arrayList = new ArrayList();
                for (FirAssignExpressionAltererExtension firAssignExpressionAltererExtension : list) {
                    FirStatement firStatementTransformVariableAssignment = firAssignExpressionAltererExtension.transformVariableAssignment(variableAssignment);
                    Pair pair = firStatementTransformVariableAssignment != null ? TuplesKt.to(firStatementTransformVariableAssignment, firAssignExpressionAltererExtension) : null;
                    if (pair != null) {
                        arrayList.add(pair);
                    }
                }
                int size = arrayList.size();
                if (size != 0) {
                    if (size == 1) {
                        return (FirStatement) ((FirStatement) ((Pair) CollectionsKt.first(arrayList)).getFirst()).transform(getTransformer(), ResolutionMode.ContextIndependent.INSTANCE);
                    }
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(Reflection.getOrCreateKotlinClass(((Pair) it.next()).getSecond().getClass()).getQualifiedName());
                    }
                    FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                    firErrorExpressionBuilder.setExpression(variableAssignment.getLValue());
                    KtSourceElement source = variableAssignment.getLValue().getSource();
                    firErrorExpressionBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.AssignmentLValueError.INSTANCE, null, 2, null) : null);
                    firErrorExpressionBuilder.setDiagnostic(new ConeAmbiguousAlteredAssign(arrayList2));
                    variableAssignment.replaceLValue(firErrorExpressionBuilder.mo288build());
                }
            }
            BodyResolveContext context = getTransformer().getContext();
            boolean isInsideAssignmentRhs = context.getIsInsideAssignmentRhs();
            context.setInsideAssignmentRhs(true);
            try {
                FirVariableAssignment firVariableAssignmentTransformRValue = variableAssignment.transformRValue(getTransformer(), ResolutionModeKt.withExpectedType$default(UtilsKt.toFirResolvedTypeRef$default(FirTypeUtilsKt.getResolvedType(variableAssignment.getLValue()), null, null, 3, null), null, null, 6, null));
                context.setInsideAssignmentRhs(isInsideAssignmentRhs);
                getTransformer().getContext().getInferenceSession().addSubtypeConstraintIfCompatible(FirTypeUtilsKt.getResolvedType(variableAssignment.getRValue()), FirTypeUtilsKt.getResolvedType(variableAssignment.getLValue()), variableAssignment);
                getComponents().getDataFlowAnalyzer().exitVariableAssignment(firVariableAssignmentTransformRValue);
                return firVariableAssignmentTransformRValue;
            } catch (Throwable th) {
                context.setInsideAssignmentRhs(isInsideAssignmentRhs);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(variableAssignment, th2);
            wq6.a();
            return null;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$GeneratorOfPlusAssignCalls;", Argument.Delimiters.none, "baseElement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "referenceSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "lhs", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "rhs", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getBaseElement", "()Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getReferenceSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getOperation", "()Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "getLhs", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getRhs", "createFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "fakeSourceElementKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "createAssignOperatorCall", "createSimpleOperatorCall", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class GeneratorOfPlusAssignCalls {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final FirStatement baseElement;
        private final FirExpression lhs;
        private final FirOperation operation;
        private final KtSourceElement referenceSource;
        private final FirExpression rhs;

        public GeneratorOfPlusAssignCalls(FirStatement firStatement, KtSourceElement ktSourceElement, FirOperation firOperation, FirExpression firExpression, FirExpression firExpression2) {
            firStatement.getClass();
            firOperation.getClass();
            firExpression.getClass();
            firExpression2.getClass();
            this.baseElement = firStatement;
            this.referenceSource = ktSourceElement;
            this.operation = firOperation;
            this.lhs = firExpression;
            this.rhs = firExpression2;
        }

        private final FirFunctionCall createFunctionCall(Name name, KtFakeSourceElementKind fakeSourceElementKind) {
            Companion companion = INSTANCE;
            KtSourceElement source = this.baseElement.getSource();
            return companion.createFunctionCall(name, source != null ? KtSourceElementKt.fakeElement$default(source, fakeSourceElementKind, null, 2, null) : null, this.referenceSource, this.baseElement.getAnnotations(), this.lhs, this.rhs);
        }

        public final FirFunctionCall createAssignOperatorCall(KtFakeSourceElementKind fakeSourceElementKind) {
            fakeSourceElementKind.getClass();
            return createFunctionCall((Name) MapsKt.getValue(FirOperationNameConventions.INSTANCE.getASSIGNMENTS(), this.operation), fakeSourceElementKind);
        }

        public final FirFunctionCall createSimpleOperatorCall(KtFakeSourceElementKind fakeSourceElementKind) {
            fakeSourceElementKind.getClass();
            return createFunctionCall((Name) MapsKt.getValue(FirOperationNameConventions.INSTANCE.getASSIGNMENTS_TO_SIMPLE_OPERATOR(), this.operation), fakeSourceElementKind);
        }

        public final FirStatement getBaseElement() {
            return this.baseElement;
        }

        public final FirExpression getLhs() {
            return this.lhs;
        }

        public final FirOperation getOperation() {
            return this.operation;
        }

        public final KtSourceElement getReferenceSource() {
            return this.referenceSource;
        }

        public final FirExpression getRhs() {
            return this.rhs;
        }

        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JQ\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u0011\"\u00020\u000f¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer$GeneratorOfPlusAssignCalls$Companion;", Argument.Delimiters.none, "<init>", "()V", "createFunctionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "referenceSource", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "arguments", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;[Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final FirFunctionCall createFunctionCall(Name name, KtSourceElement source, KtSourceElement referenceSource, List<? extends FirAnnotation> annotations, FirExpression receiver, FirExpression... arguments) {
                FirArgumentList firArgumentListBuildUnaryArgumentList;
                name.getClass();
                annotations.getClass();
                receiver.getClass();
                arguments.getClass();
                FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
                firFunctionCallBuilder.setSource(source);
                firFunctionCallBuilder.setExplicitReceiver(receiver);
                int length = arguments.length;
                if (length == 0) {
                    firArgumentListBuildUnaryArgumentList = FirEmptyArgumentList.INSTANCE;
                } else if (length != 1) {
                    FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
                    CollectionsKt.addAll(firArgumentListBuilder.getArguments(), arguments);
                    firArgumentListBuildUnaryArgumentList = firArgumentListBuilder.build();
                } else {
                    firArgumentListBuildUnaryArgumentList = FirArgumentUtilKt.buildUnaryArgumentList((FirExpression) ArraysKt.first(arguments));
                }
                firFunctionCallBuilder.setArgumentList(firArgumentListBuildUnaryArgumentList);
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                if (referenceSource != null) {
                    source = referenceSource;
                }
                firSimpleNamedReferenceBuilder.setSource(source);
                firSimpleNamedReferenceBuilder.setName(name);
                firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
                firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
                firFunctionCallBuilder.getAnnotations().addAll(annotations);
                return firFunctionCallBuilder.mo288build();
            }

            private Companion() {
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirExpression transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, ResolutionMode data) {
        qualifiedAccessExpression.getClass();
        data.getClass();
        FirSession session = getSession();
        try {
            return transformQualifiedAccessExpression(qualifiedAccessExpression, data, false, false, false);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(qualifiedAccessExpression, th);
            wq6.a();
            return null;
        }
    }
}
