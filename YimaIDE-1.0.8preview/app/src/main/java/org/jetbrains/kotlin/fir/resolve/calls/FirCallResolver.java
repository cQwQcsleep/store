package org.jetbrains.kotlin.fir.resolve.calls;

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
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoConstructorError;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoImplicitDefaultConstructorOnExpectClass;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.builder.FirImplicitInvokeCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedReifiedParameterReferenceBuilder;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.builder.FirBackingFieldReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralOuterCandidateContext;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.FirRegularTowerDataContexts;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.FirTowerDataMode;
import org.jetbrains.kotlin.fir.resolve.QualifiedNameResolutionKt;
import org.jetbrains.kotlin.fir.resolve.QualifierResolutionResult;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.FirCallResolver;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateTraversalKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ErrorCandidateUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ImplicitInvokeMode;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverKt;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.FirOverloadByLambdaReturnTypeResolver;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolver;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerResolveManager;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSymbol;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeFunctionCallExpectedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeFunctionExpectedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeHiddenCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeIllegalAnnotationError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeImplicitPropertyTypeMakesBehaviorOrderDependant;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionResultOverridesOtherToPreserveCompatibility;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionToClassifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeArgumentsForOuterClass;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedReferenceError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnsupported;
import org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleterKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponentsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirStarProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Þ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0088\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015J\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'H\u0002J>\u0010/\u001a\b\u0012\u0004\u0012\u0002000$2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u0002060$2\b\b\u0002\u00107\u001a\u0002082\u0006\u0010\u001f\u001a\u00020 J|\u00109\u001a\u00020'2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010<\u001a\u00020*2\b\b\u0002\u0010=\u001a\u00020>2\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u0002060$2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\b\b\u0002\u0010A\u001a\u00020B2\u0006\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0002J,\u0010C\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0E\u0012\u0004\u0012\u00020G0D2\u0006\u0010?\u001a\u00020@2\b\b\u0002\u00107\u001a\u000208H\u0002J.\u0010H\u001a\u00020I2\u0006\u00101\u001a\u0002022\u0006\u0010J\u001a\u00020*2\u0006\u0010<\u001a\u00020*2\u0006\u0010A\u001a\u00020B2\u0006\u0010\u001f\u001a\u00020 JL\u0010K\u001a\u00020I2\u0006\u00101\u001a\u0002022\u0006\u0010J\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010<\u001a\u00020*2\b\b\u0002\u0010A\u001a\u00020B2\u0018\u0010L\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0N\u0012\u0004\u0012\u00020*0MH\u0002J*\u0010O\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020*0D2\u0006\u0010P\u001a\u00020F2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020*J\u0018\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010YJ$\u0010Z\u001a\u00020W2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\n\u0010[\u001a\u0006\u0012\u0002\b\u00030\\J\f\u0010]\u001a\u00020^*\u00020_H\u0002J\u0010\u0010`\u001a\u0004\u0018\u00010a2\u0006\u0010b\u001a\u00020aJ\u001a\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010hJ\u0018\u0010i\u001a\u00020U2\u0006\u0010b\u001a\u00020a2\u0006\u0010j\u001a\u00020kH\u0002J\u001c\u0010l\u001a\u00020'2\u0006\u0010m\u001a\u00020U2\n\u0010n\u001a\u0006\u0012\u0002\b\u00030oH\u0002J(\u0010p\u001a\u00020W2\u0006\u0010q\u001a\u00020W2\u0006\u00103\u001a\u0002042\u0006\u0010&\u001a\u00020@2\u0006\u0010m\u001a\u00020UH\u0002J,\u0010r\u001a\u00020U2\u0006\u0010s\u001a\u00020t2\b\u0010u\u001a\u0004\u0018\u00010v2\b\u0010w\u001a\u0004\u0018\u00010f2\u0006\u0010S\u001a\u00020*H\u0002Jk\u0010x\u001a\u00020y2\u0006\u0010j\u001a\u00020z2\u0006\u00103\u001a\u0002042\u0006\u0010m\u001a\u00020U2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020F0N2\u0006\u0010|\u001a\u00020G2\n\b\u0002\u0010}\u001a\u0004\u0018\u00010I2\b\b\u0002\u0010~\u001a\u00020*2\n\b\u0002\u0010\u007f\u001a\u0004\u0018\u00010;2\u0011\b\u0002\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010NH\u0002J1\u0010\u0081\u0001\u001a\u00020y2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010F2\u0007\u0010\u0083\u0001\u001a\u00020%2\u0006\u0010m\u001a\u00020U2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002J'\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010m\u001a\u00020U2\u0007\u0010\u0083\u0001\u001a\u00020%2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR&\u0010\f\u001a\u00020\r8\u0002X\u0083\u0004r\u000e\b\u0010\u0012\n\b\u0011\u0012\u0006\b\n0\u00128\u0013¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\"\u0010)\u001a\u00020**\u00020'8BX\u0083\u0004r\u0002\b.¢\u0006\f\u0012\u0004\b+\u0010,\u001a\u0004\b)\u0010-¨\u0006\u0089\u0001²\u0006\u000b\u0010\u008a\u0001\u001a\u00020'X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "towerResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolver;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolver;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "overloadByLambdaReturnTypeResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/FirOverloadByLambdaReturnTypeResolver;", "getOverloadByLambdaReturnTypeResolver$annotations", "()V", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "EagerLambdaAnalysis", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "initTransformer", Argument.Delimiters.none, "conflictResolver", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "getConflictResolver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "resolveCallAndSelectCandidate", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "functionCall", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "collectionLiteralContext", "Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;", "convertForwardedDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver$ResolutionResult;", "processContextSensitiveResolutionAlternatives", "isSuccess", Argument.Delimiters.none, "isSuccess$annotations", "(Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver$ResolutionResult;)V", "(Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver$ResolutionResult;)Z", "Lorg/jetbrains/kotlin/resolve/calls/tower/ApplicabilityDetail;", "collectAllCandidates", "Lorg/jetbrains/kotlin/fir/resolve/calls/OverloadCandidate;", "qualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "collectCandidates", "forceCallKind", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "isUsedAsGetClassReceiver", "origin", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "reduceCandidates", "Lkotlin/Pair;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "resolveVariableAccessAndSelectCandidate", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isUsedAsReceiver", "resolveVariableAccessAndSelectCandidateImpl", "acceptCandidates", "Lkotlin/Function1;", Argument.Delimiters.none, "resolveCallableReference", "containingCallCandidate", "resolvedCallableReferenceAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedCallableReferenceAtom;", "hasSyntheticOuterCall", "callInfoForDelegatingConstructorCall", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "constructedType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "resolveDelegatingConstructorCall", "derivedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "toFirTypeProjection", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "resolveAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "annotation", "getAnnotationConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "annotationConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotationClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "toCallInfo", "reference", "Lorg/jetbrains/kotlin/fir/references/impl/FirSimpleNamedReference;", "runResolutionForGivenSymbol", "callInfo", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "selectDelegatingConstructorCall", K2JsArgumentConstants.CALL, "createCallableReferencesInfoForLHS", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "lhs", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "expectedType", "createResolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "candidates", "applicability", "explicitReceiver", "createResolvedReferenceWithoutCandidateForLocalVariables", "expectedCallKind", "expectedCandidates", "createErrorReferenceForSingleCandidate", "candidate", "diagnostic", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "buildReferenceWithErrorCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirErrorReferenceWithCandidate;", "ResolutionResult", "org.jetbrains.kotlin:resolve", "basicResult"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallResolver implements SessionHolder {
    private final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components;
    private final ConeCallConflictResolver conflictResolver;
    private final FirOverloadByLambdaReturnTypeResolver overloadByLambdaReturnTypeResolver;
    private final FirSession session;
    private final FirTowerResolver towerResolver;
    private FirExpressionsResolveTransformer transformer;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\rHÆ\u0003JG\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0014\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/FirCallResolver$ResolutionResult;", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "applicability", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "candidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "forwardedDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "metInapplicableCandidate", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;Ljava/util/Collection;Ljava/util/List;Z)V", "getInfo", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "getApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "getCandidates", "()Ljava/util/Collection;", "getForwardedDiagnostics", "()Ljava/util/List;", "getMetInapplicableCandidate", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ResolutionResult {
        private final CandidateApplicability applicability;
        private final Collection<Candidate> candidates;
        private final List<ResolutionDiagnostic> forwardedDiagnostics;
        private final CallInfo info;
        private final boolean metInapplicableCandidate;

        /* JADX WARN: Multi-variable type inference failed */
        public ResolutionResult(CallInfo callInfo, CandidateApplicability candidateApplicability, Collection<Candidate> collection, List<? extends ResolutionDiagnostic> list, boolean z) {
            callInfo.getClass();
            candidateApplicability.getClass();
            collection.getClass();
            list.getClass();
            this.info = callInfo;
            this.applicability = candidateApplicability;
            this.candidates = collection;
            this.forwardedDiagnostics = list;
            this.metInapplicableCandidate = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ResolutionResult copy$default(ResolutionResult resolutionResult, CallInfo callInfo, CandidateApplicability candidateApplicability, Collection collection, List list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                callInfo = resolutionResult.info;
            }
            if ((i & 2) != 0) {
                candidateApplicability = resolutionResult.applicability;
            }
            if ((i & 4) != 0) {
                collection = resolutionResult.candidates;
            }
            if ((i & 8) != 0) {
                list = resolutionResult.forwardedDiagnostics;
            }
            if ((i & 16) != 0) {
                z = resolutionResult.metInapplicableCandidate;
            }
            boolean z2 = z;
            Collection collection2 = collection;
            return resolutionResult.copy(callInfo, candidateApplicability, collection2, list, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CallInfo getInfo() {
            return this.info;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CandidateApplicability getApplicability() {
            return this.applicability;
        }

        public final Collection<Candidate> component3() {
            return this.candidates;
        }

        public final List<ResolutionDiagnostic> component4() {
            return this.forwardedDiagnostics;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getMetInapplicableCandidate() {
            return this.metInapplicableCandidate;
        }

        public final ResolutionResult copy(CallInfo info, CandidateApplicability applicability, Collection<Candidate> candidates, List<? extends ResolutionDiagnostic> forwardedDiagnostics, boolean metInapplicableCandidate) {
            info.getClass();
            applicability.getClass();
            candidates.getClass();
            forwardedDiagnostics.getClass();
            return new ResolutionResult(info, applicability, candidates, forwardedDiagnostics, metInapplicableCandidate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResolutionResult)) {
                return false;
            }
            ResolutionResult resolutionResult = (ResolutionResult) other;
            return Intrinsics.areEqual(this.info, resolutionResult.info) && this.applicability == resolutionResult.applicability && Intrinsics.areEqual(this.candidates, resolutionResult.candidates) && Intrinsics.areEqual(this.forwardedDiagnostics, resolutionResult.forwardedDiagnostics) && this.metInapplicableCandidate == resolutionResult.metInapplicableCandidate;
        }

        public final CandidateApplicability getApplicability() {
            return this.applicability;
        }

        public final Collection<Candidate> getCandidates() {
            return this.candidates;
        }

        public final List<ResolutionDiagnostic> getForwardedDiagnostics() {
            return this.forwardedDiagnostics;
        }

        public final CallInfo getInfo() {
            return this.info;
        }

        public final boolean getMetInapplicableCandidate() {
            return this.metInapplicableCandidate;
        }

        public int hashCode() {
            return (((((((this.info.hashCode() * 31) + this.applicability.hashCode()) * 31) + this.candidates.hashCode()) * 31) + this.forwardedDiagnostics.hashCode()) * 31) + Boolean.hashCode(this.metInapplicableCandidate);
        }

        public String toString() {
            return "ResolutionResult(info=" + this.info + ", applicability=" + this.applicability + ", candidates=" + this.candidates + ", forwardedDiagnostics=" + this.forwardedDiagnostics + ", metInapplicableCandidate=" + this.metInapplicableCandidate + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProjectionKind.INVARIANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FirCallResolver(FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, FirTowerResolver firTowerResolver) {
        bodyResolveTransformerComponents.getClass();
        firTowerResolver.getClass();
        this.components = bodyResolveTransformerComponents;
        this.towerResolver = firTowerResolver;
        this.session = bodyResolveTransformerComponents.getSession();
        this.overloadByLambdaReturnTypeResolver = new FirOverloadByLambdaReturnTypeResolver(bodyResolveTransformerComponents);
        this.conflictResolver = ConeCallConflictResolverKt.getCallConflictResolverFactory(getSession()).create(InferenceComponentsKt.getInferenceComponents(getSession()), bodyResolveTransformerComponents);
    }

    public static ResolutionResult b(FirCallResolver firCallResolver, FirQualifiedAccessExpression firQualifiedAccessExpression, FirSimpleNamedReference firSimpleNamedReference, boolean z, FirElement firElement, ResolutionMode resolutionMode) {
        return collectCandidates$default(firCallResolver, firQualifiedAccessExpression, firSimpleNamedReference.getName(), null, z, null, null, null, null, firElement, resolutionMode, null, 1268, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirErrorReferenceWithCandidate buildReferenceWithErrorCandidate(CallInfo callInfo, ConeDiagnostic diagnostic, KtSourceElement source) throws UninitializedPropertyAccessException {
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = this.transformer;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer = null;
        }
        return ErrorCandidateUtilsKt.createErrorReferenceWithErrorCandidate(callInfo, diagnostic, source, firExpressionsResolveTransformer.getTransformer().getResolutionContext(), this.components.getResolutionStageRunner());
    }

    public static boolean c(Collection collection) {
        collection.getClass();
        return true;
    }

    private static final ResolutionResult collectAllCandidates$collectCandidates(FirCallResolver firCallResolver, FirQualifiedAccessExpression firQualifiedAccessExpression, Name name, FirFunctionCallOrigin firFunctionCallOrigin, List<? extends FirDeclaration> list, ResolutionContext resolutionContext, AllCandidatesCollector allCandidatesCollector, ResolutionMode resolutionMode, CallKind callKind) {
        return collectCandidates$default(firCallResolver, firQualifiedAccessExpression, name, callKind, false, firFunctionCallOrigin, list, resolutionContext, allCandidatesCollector, null, resolutionMode, null, 1288, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List collectAllCandidates$default(FirCallResolver firCallResolver, FirQualifiedAccessExpression firQualifiedAccessExpression, Name name, List list, ResolutionContext resolutionContext, ResolutionMode resolutionMode, int i, Object obj) throws UninitializedPropertyAccessException {
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = null;
        if ((i & 4) != 0) {
            FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = firCallResolver.transformer;
            if (firExpressionsResolveTransformer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer2 = null;
            }
            list = firExpressionsResolveTransformer2.getTransformer().getComponents().getContainingDeclarations();
        }
        List list2 = list;
        if ((i & 8) != 0) {
            FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = firCallResolver.transformer;
            if (firExpressionsResolveTransformer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
            } else {
                firExpressionsResolveTransformer = firExpressionsResolveTransformer3;
            }
            resolutionContext = firExpressionsResolveTransformer.getTransformer().getResolutionContext();
        }
        return firCallResolver.collectAllCandidates(firQualifiedAccessExpression, name, list2, resolutionContext, resolutionMode);
    }

    private final ResolutionResult collectCandidates(FirQualifiedAccessExpression qualifiedAccess, Name name, CallKind forceCallKind, boolean isUsedAsGetClassReceiver, FirFunctionCallOrigin origin, List<? extends FirDeclaration> containingDeclarations, ResolutionContext resolutionContext, CandidateCollector collector, FirElement callSite, ResolutionMode resolutionMode, CollectionLiteralOuterCandidateContext collectionLiteralContext) {
        FirArgumentList argumentList;
        CallInfo callInfo;
        CandidateCollector candidateCollectorRunResolver$default;
        CallKind callKind = forceCallKind;
        FirExpression explicitReceiver = qualifiedAccess.getExplicitReceiver();
        boolean z = qualifiedAccess instanceof FirFunctionCall;
        FirFunctionCall firFunctionCall = z ? (FirFunctionCall) qualifiedAccess : null;
        if (firFunctionCall == null || (argumentList = firFunctionCall.getArgumentList()) == null) {
            argumentList = FirEmptyArgumentList.INSTANCE;
        }
        FirArgumentList firArgumentList = argumentList;
        List<FirTypeProjection> typeArguments = (z || Intrinsics.areEqual(callKind, CallKind.Function.INSTANCE)) ? qualifiedAccess.getTypeArguments() : CollectionsKt.emptyList();
        if (callKind == null) {
            if (collectionLiteralContext != null) {
                callKind = CallKind.CollectionLiteral.INSTANCE;
            } else {
                callKind = z ? CallKind.Function.INSTANCE : CallKind.VariableAccess.INSTANCE;
            }
        }
        CallInfo callInfo2 = new CallInfo(callSite, callKind, name, explicitReceiver, firArgumentList, isUsedAsGetClassReceiver, typeArguments, getSession(), this.components.getFile(), containingDeclarations, null, resolutionMode, origin, qualifiedAccess instanceof FirImplicitInvokeCall ? ImplicitInvokeMode.Regular : ImplicitInvokeMode.None, collectionLiteralContext != null ? collectionLiteralContext.getContainingCandidate() : null, 1024, null);
        if (collectionLiteralContext != null) {
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents = this.components;
            CandidateCollector candidateCollector = new CandidateCollector(bodyResolveTransformerComponents, bodyResolveTransformerComponents.getResolutionStageRunner());
            callInfo = callInfo2;
            candidateCollectorRunResolver$default = FirTowerResolver.runResolver$default(this.towerResolver, callInfo, resolutionContext, candidateCollector, new TowerResolveManager(candidateCollector), null, 16, null);
        } else {
            callInfo = callInfo2;
            this.towerResolver.reset();
            candidateCollectorRunResolver$default = FirTowerResolver.runResolver$default(this.towerResolver, callInfo, resolutionContext, collector, null, 8, null);
        }
        Pair<Set<Candidate>, CandidateApplicability> pairReduceCandidates = reduceCandidates(candidateCollectorRunResolver$default, resolutionContext);
        Set<Candidate> setReduceCandidates = (Set) pairReduceCandidates.component1();
        CandidateApplicability candidateApplicability = (CandidateApplicability) pairReduceCandidates.component2();
        if (LanguageVersionUtilsKt.isDisabled(this, LanguageFeature.EagerLambdaAnalysis)) {
            setReduceCandidates = this.overloadByLambdaReturnTypeResolver.reduceCandidates(qualifiedAccess, setReduceCandidates, setReduceCandidates);
        }
        return new ResolutionResult(callInfo, candidateApplicability, setReduceCandidates, candidateCollectorRunResolver$default.forwardedDiagnostics(), candidateCollectorRunResolver$default.getMetInapplicableCandidate());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ResolutionResult collectCandidates$default(FirCallResolver firCallResolver, FirQualifiedAccessExpression firQualifiedAccessExpression, Name name, CallKind callKind, boolean z, FirFunctionCallOrigin firFunctionCallOrigin, List list, ResolutionContext resolutionContext, CandidateCollector candidateCollector, FirElement firElement, ResolutionMode resolutionMode, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, int i, Object obj) throws UninitializedPropertyAccessException {
        if ((i & 4) != 0) {
            callKind = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            firFunctionCallOrigin = FirFunctionCallOrigin.Regular;
        }
        if ((i & 32) != 0) {
            FirExpressionsResolveTransformer firExpressionsResolveTransformer = firCallResolver.transformer;
            if (firExpressionsResolveTransformer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer = null;
            }
            list = firExpressionsResolveTransformer.getTransformer().getComponents().getContainingDeclarations();
        }
        if ((i & 64) != 0) {
            FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = firCallResolver.transformer;
            if (firExpressionsResolveTransformer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer2 = null;
            }
            resolutionContext = firExpressionsResolveTransformer2.getTransformer().getResolutionContext();
        }
        if ((i & 128) != 0) {
            candidateCollector = null;
        }
        if ((i & 256) != 0) {
            firElement = firQualifiedAccessExpression;
        }
        if ((i & 1024) != 0) {
            collectionLiteralOuterCandidateContext = null;
        }
        return firCallResolver.collectCandidates(firQualifiedAccessExpression, name, callKind, z, firFunctionCallOrigin, list, resolutionContext, candidateCollector, firElement, resolutionMode, collectionLiteralOuterCandidateContext);
    }

    private final List<ConeDiagnostic> convertForwardedDiagnostics(ResolutionResult result) {
        List<ResolutionDiagnostic> forwardedDiagnostics = result.getForwardedDiagnostics();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(forwardedDiagnostics, 10));
        for (ResolutionDiagnostic resolutionDiagnostic : forwardedDiagnostics) {
            if (!(resolutionDiagnostic instanceof ImplicitPropertyTypeMakesBehaviorOrderDependant)) {
                AddToStdlibKt.shouldNotBeCalled("Implement conversion of the " + resolutionDiagnostic + " forwarded diagnostic");
                wq6.a();
                return null;
            }
            arrayList.add(new ConeImplicitPropertyTypeMakesBehaviorOrderDependant(((ImplicitPropertyTypeMakesBehaviorOrderDependant) resolutionDiagnostic).getCandidateSymbol()));
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final CallInfo createCallableReferencesInfoForLHS(FirCallableReferenceAccess callableReferenceAccess, DoubleColonLHS lhs, ConeKotlinType expectedType, boolean hasSyntheticOuterCall) throws UninitializedPropertyAccessException {
        Name name = callableReferenceAccess.getCalleeReference().getName();
        FirExpression explicitReceiver = callableReferenceAccess.getExplicitReceiver();
        FirSession session = getSession();
        FirFile file = this.components.getFile();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = this.transformer;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer = null;
        }
        return new CallableReferenceInfo(callableReferenceAccess, name, explicitReceiver, session, file, firExpressionsResolveTransformer.getTransformer().getComponents().getContainingDeclarations(), expectedType, lhs, hasSyntheticOuterCall, null, null, 1536, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirNamedReference createErrorReferenceForSingleCandidate(Candidate candidate, ConeDiagnostic diagnostic, CallInfo callInfo, KtSourceElement source) throws UninitializedPropertyAccessException {
        if (candidate == null) {
            return buildReferenceWithErrorCandidate(callInfo, diagnostic, source);
        }
        if ((diagnostic instanceof ConeUnresolvedError) || (diagnostic instanceof ConeHiddenCandidateError)) {
            return buildReferenceWithErrorCandidate(callInfo, diagnostic, source);
        }
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = this.transformer;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer = null;
        }
        return ErrorCandidateUtilsKt.createErrorReferenceWithExistingCandidate(candidate, diagnostic, source, firExpressionsResolveTransformer.getTransformer().getResolutionContext(), this.components.getResolutionStageRunner());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:157:0x029e  */
    /* JADX WARN: Code duplicated, block: B:159:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:161:0x02b8  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedReference createResolvedNamedReference(FirReference reference, Name name, CallInfo callInfo, Collection<Candidate> candidates, CandidateApplicability applicability, FirExpression explicitReceiver, boolean createResolvedReferenceWithoutCandidateForLocalVariables, CallKind expectedCallKind, Collection<Candidate> expectedCandidates) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeDiagnostic coneDiagnosticCreateConeDiagnosticForCandidateWithError;
        FirTypeRef superTypeRef;
        String string;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        List<FirValueParameterSymbol> valueParameterSymbols;
        KtSourceElement source = reference.getSource();
        boolean z = false;
        String str = callInfo.getOrigin() == FirFunctionCallOrigin.Operator ? (String) OperatorNameConventions.INSTANCE.getTOKENS_BY_OPERATOR_NAME().get(name) : null;
        if (expectedCallKind != null) {
            if (Intrinsics.areEqual(expectedCallKind, CallKind.Function.INSTANCE)) {
                Collection<Candidate> collection = candidates;
                if (!(collection instanceof Collection) || !collection.isEmpty()) {
                    Iterator<T> it = collection.iterator();
                    while (it.hasNext()) {
                        FirBasedSymbol<?> symbol = ((Candidate) it.next()).getSymbol();
                        FirFunctionSymbol firFunctionSymbol = symbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) symbol : null;
                        if (firFunctionSymbol != null && (valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols()) != null && (!valueParameterSymbols.isEmpty())) {
                            z = true;
                            break;
                        }
                    }
                }
                coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeFunctionCallExpectedError(name, z, candidates);
            } else {
                Candidate candidate = expectedCandidates != null ? (Candidate) CollectionsKt.singleOrNull(expectedCandidates) : null;
                FirBasedSymbol<?> symbol2 = candidate != null ? candidate.getSymbol() : null;
                if ((symbol2 instanceof FirTypeAliasSymbol) && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass((FirClassLikeSymbol<?>) symbol2, getSession())) != null) {
                    symbol2 = firRegularClassSymbolFullyExpandedClass;
                }
                if (symbol2 instanceof FirRegularClassSymbol) {
                    candidate.getClass();
                    coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeResolutionToClassifierError(candidate, (FirRegularClassSymbol) symbol2);
                } else {
                    ConeKotlinType resolvedType = explicitReceiver != null ? FirTypeUtilsKt.getResolvedType(explicitReceiver) : null;
                    if (resolvedType == null || ConeBuiltinTypeUtilsKt.isUnit(resolvedType)) {
                        coneDiagnosticCreateConeDiagnosticForCandidateWithError = (candidate == null || candidate.isSuccessful()) ? new ConeUnresolvedNameError(name, str, null, 4, null) : ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate.getLowestApplicability(), candidate);
                    } else {
                        FirCallableSymbol<?> firCallableSymbol = symbol2 instanceof FirCallableSymbol ? (FirCallableSymbol) symbol2 : null;
                        ConeKotlinType coneType = firCallableSymbol != null ? this.components.getReturnTypeCalculator().tryCalculateReturnType(firCallableSymbol).getConeType() : null;
                        if (candidate == null || candidate.isSuccessful() || coneType == null || !FunctionalTypeUtilsKt.isSomeFunctionType(coneType, getSession())) {
                            String strAsString = name.asString();
                            strAsString.getClass();
                            if (coneType != null) {
                                resolvedType = coneType;
                            }
                            coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeFunctionExpectedError(strAsString, resolvedType);
                        } else {
                            coneDiagnosticCreateConeDiagnosticForCandidateWithError = ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate.getLowestApplicability(), candidate);
                        }
                    }
                }
            }
        } else if (candidates.isEmpty()) {
            if (Intrinsics.areEqual(name.asString(), "invoke") && (explicitReceiver instanceof FirLiteralExpression)) {
                Object value = ((FirLiteralExpression) explicitReceiver).getValue();
                if (value == null || (string = value.toString()) == null) {
                    string = Argument.Delimiters.none;
                }
                coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeFunctionExpectedError(string, FirTypeUtilsKt.getResolvedType(explicitReceiver));
            } else {
                FirSuperReference firSuperReference = reference instanceof FirSuperReference ? (FirSuperReference) reference : null;
                FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = (firSuperReference == null || (superTypeRef = firSuperReference.getSuperTypeRef()) == null) ? null : org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.firClassLike(superTypeRef, getSession());
                FirClass firClass = firClassLikeDeclarationFirClassLike instanceof FirClass ? (FirClass) firClassLikeDeclarationFirClassLike : null;
                if (firClass != null && firClass.getClassKind() == ClassKind.INTERFACE) {
                    coneDiagnosticCreateConeDiagnosticForCandidateWithError = ConeNoConstructorError.INSTANCE;
                } else if (firClass == null || !firClass.getStatus().isExpect()) {
                    coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeUnresolvedNameError(name, str, explicitReceiver != null ? FirTypeUtilsKt.getResolvedType(explicitReceiver) : null);
                } else {
                    coneDiagnosticCreateConeDiagnosticForCandidateWithError = ConeNoImplicitDefaultConstructorOnExpectClass.INSTANCE;
                }
            }
        } else if (candidates.size() > 1) {
            Collection<Candidate> collection2 = candidates;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection2, 10)), 16));
            for (Object obj : collection2) {
                Candidate candidate2 = (Candidate) obj;
                linkedHashMap.put(obj, !candidate2.isSuccessful() ? ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate2.getApplicability(), candidate2) : null);
            }
            coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeAmbiguityError(name, applicability, linkedHashMap);
        } else {
            Candidate candidate3 = (Candidate) CollectionsKt.single(candidates);
            coneDiagnosticCreateConeDiagnosticForCandidateWithError = !candidate3.isSuccessful() ? ResolveUtilsKt.createConeDiagnosticForCandidateWithError(applicability, candidate3) : null;
        }
        if (coneDiagnosticCreateConeDiagnosticForCandidateWithError != null) {
            return createErrorReferenceForSingleCandidate((Candidate) CollectionsKt.singleOrNull(candidates), coneDiagnosticCreateConeDiagnosticForCandidateWithError, callInfo, source);
        }
        Candidate candidate4 = (Candidate) CollectionsKt.single(candidates);
        FirBasedSymbol<?> symbol3 = candidate4.getSymbol();
        if (symbol3 instanceof FirBackingFieldSymbol) {
            FirBackingFieldSymbol firBackingFieldSymbol = (FirBackingFieldSymbol) symbol3;
            DeclarationAttributesKt.setReferredViaField((FirProperty) ((FirBackingField) firBackingFieldSymbol.getFir()).getPropertySymbol().getFir(), Boolean.TRUE);
            FirBackingFieldReferenceBuilder firBackingFieldReferenceBuilder = new FirBackingFieldReferenceBuilder();
            firBackingFieldReferenceBuilder.setSource(source);
            firBackingFieldReferenceBuilder.setResolvedSymbol(firBackingFieldSymbol);
            FirScope originScope = candidate4.getOriginScope();
            firBackingFieldReferenceBuilder.setResolvedSymbolOrigin(originScope != null ? ResolveUtilsKt.toResolvedSymbolOrigin(originScope) : null);
            return firBackingFieldReferenceBuilder.build();
        }
        if (ResolveUtilsKt.isExplicitBackingFieldAccess(candidate4)) {
            return ResolveUtilsKt.buildExplicitBackingFieldReference(source, name, candidate4);
        }
        if (!candidate4.getUsedOuterCs() && createResolvedReferenceWithoutCandidateForLocalVariables) {
            if (!((explicitReceiver != null ? FirTypeUtilsKt.getResolvedType(explicitReceiver) : null) instanceof ConeIntegerLiteralType) && (symbol3 instanceof FirVariableSymbol)) {
                if (symbol3 instanceof FirPropertySymbol) {
                    D fir = ((FirPropertySymbol) symbol3).getFir();
                    fir.getClass();
                    if (((FirMemberDeclaration) fir).getTypeParameters().isEmpty()) {
                        if (!FirCallCompletionResultsWriterTransformerKt.doesResolutionResultOverrideOtherToPreserveCompatibility(candidate4)) {
                            FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                            firResolvedNamedReferenceBuilder.setSource(source);
                            firResolvedNamedReferenceBuilder.setName(name);
                            firResolvedNamedReferenceBuilder.setResolvedSymbol(symbol3);
                            FirScope originScope2 = candidate4.getOriginScope();
                            firResolvedNamedReferenceBuilder.setResolvedSymbolOrigin(originScope2 != null ? ResolveUtilsKt.toResolvedSymbolOrigin(originScope2) : null);
                            return firResolvedNamedReferenceBuilder.build();
                        }
                    }
                } else if (!FirCallCompletionResultsWriterTransformerKt.doesResolutionResultOverrideOtherToPreserveCompatibility(candidate4)) {
                    FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder2 = new FirResolvedNamedReferenceBuilder();
                    firResolvedNamedReferenceBuilder2.setSource(source);
                    firResolvedNamedReferenceBuilder2.setName(name);
                    firResolvedNamedReferenceBuilder2.setResolvedSymbol(symbol3);
                    FirScope originScope3 = candidate4.getOriginScope();
                    firResolvedNamedReferenceBuilder2.setResolvedSymbolOrigin(originScope3 != null ? ResolveUtilsKt.toResolvedSymbolOrigin(originScope3) : null);
                    return firResolvedNamedReferenceBuilder2.build();
                }
            }
        }
        return new FirNamedReferenceWithCandidate(source, name, candidate4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirNamedReference createResolvedNamedReference$default(FirCallResolver firCallResolver, FirReference firReference, Name name, CallInfo callInfo, Collection collection, CandidateApplicability candidateApplicability, FirExpression firExpression, boolean z, CallKind callKind, Collection collection2, int i, Object obj) {
        if ((i & 32) != 0) {
            firExpression = null;
        }
        if ((i & 64) != 0) {
            z = true;
        }
        if ((i & 128) != 0) {
            callKind = null;
        }
        if ((i & 256) != 0) {
            collection2 = null;
        }
        return firCallResolver.createResolvedNamedReference(firReference, name, callInfo, collection, candidateApplicability, firExpression, z, callKind, collection2);
    }

    public static Unit d(ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        if (conePostponedResolvedAtom instanceof ConeContextSensitiveAlternativeForQualifierAtom) {
            ((ConeContextSensitiveAlternativeForQualifierAtom) conePostponedResolvedAtom).markDiscarded();
        }
        return Unit.INSTANCE;
    }

    private final boolean isSuccess(ResolutionResult resolutionResult) {
        return CandidateApplicabilityKt.isSuccess(resolutionResult.getApplicability());
    }

    private final void processContextSensitiveResolutionAlternatives(ResolutionResult result) {
        if (LanguageVersionUtilsKt.isSet(this, AnalysisFlags.getIdeMode()) && result.getMetInapplicableCandidate()) {
            FirElement callSite = result.getInfo().getCallSite();
            FirExpression firExpression = callSite instanceof FirExpression ? (FirExpression) callSite : null;
            if (firExpression == null) {
                return;
            }
            Iterator<Candidate> it = result.getCandidates().iterator();
            while (it.hasNext()) {
                CandidateTraversalKt.processPostponedAtoms(new ConeAtomWithCandidate(firExpression, it.next()), new Function1() { // from class: dz4
                    public final Object invoke(Object obj) {
                        return FirCallResolver.d((ConePostponedResolvedAtom) obj);
                    }
                });
            }
        }
    }

    private final Pair<Set<Candidate>, CandidateApplicability> reduceCandidates(CandidateCollector collector, ResolutionContext resolutionContext) {
        List<Candidate> listBestCandidates = collector.bestCandidates();
        CandidateApplicability currentApplicability = collector.getCurrentApplicability();
        if (collector.isSuccess()) {
            return TuplesKt.to(reduceCandidates$chooseMostSpecific(this, listBestCandidates), currentApplicability);
        }
        if (listBestCandidates.isEmpty()) {
            return TuplesKt.to(CollectionsKt.toSet(listBestCandidates), currentApplicability);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : listBestCandidates) {
            Candidate candidate = (Candidate) obj;
            ErrorCandidateUtilsKt.fullyProcessCandidate(this.components.getResolutionStageRunner(), candidate, resolutionContext);
            Iterator<T> it = candidate.getDiagnostics().iterator();
            if (!it.hasNext()) {
                z0e.a();
                return null;
            }
            CandidateApplicability applicability = ((ResolutionDiagnostic) it.next()).getApplicability();
            while (it.hasNext()) {
                CandidateApplicability applicability2 = ((ResolutionDiagnostic) it.next()).getApplicability();
                if (applicability.compareTo(applicability2) > 0) {
                    applicability = applicability2;
                }
            }
            Object arrayList = linkedHashMap.get(applicability);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(applicability, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        Iterator it2 = linkedHashMap.entrySet().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        Object next = it2.next();
        if (it2.hasNext()) {
            Comparable comparable = (CandidateApplicability) ((Map.Entry) next).getKey();
            do {
                Object next2 = it2.next();
                Comparable comparable2 = (CandidateApplicability) ((Map.Entry) next2).getKey();
                if (comparable.compareTo(comparable2) < 0) {
                    next = next2;
                    comparable = comparable2;
                }
            } while (it2.hasNext());
        }
        Map.Entry entry = (Map.Entry) next;
        return TuplesKt.to(reduceCandidates$chooseMostSpecific(this, (List) entry.getValue()), entry.getKey());
    }

    private static final Set<Candidate> reduceCandidates$chooseMostSpecific(FirCallResolver firCallResolver, List<Candidate> list) {
        Candidate candidate = (Candidate) CollectionsKt.singleOrNull(list);
        return candidate != null ? SetsKt.setOf(candidate) : firCallResolver.conflictResolver.chooseMaximallySpecificCandidates(list);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static /* synthetic */ Pair reduceCandidates$default(FirCallResolver firCallResolver, CandidateCollector candidateCollector, ResolutionContext resolutionContext, int i, Object obj) throws UninitializedPropertyAccessException {
        if ((i & 2) != 0) {
            FirExpressionsResolveTransformer firExpressionsResolveTransformer = firCallResolver.transformer;
            if (firExpressionsResolveTransformer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer = null;
            }
            resolutionContext = firExpressionsResolveTransformer.getTransformer().getResolutionContext();
        }
        return firCallResolver.reduceCandidates(candidateCollector, resolutionContext);
    }

    public static /* synthetic */ FirFunctionCall resolveCallAndSelectCandidate$default(FirCallResolver firCallResolver, FirFunctionCall firFunctionCall, ResolutionMode resolutionMode, CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext, int i, Object obj) {
        if ((i & 4) != 0) {
            collectionLiteralOuterCandidateContext = null;
        }
        return firCallResolver.resolveCallAndSelectCandidate(firFunctionCall, resolutionMode, collectionLiteralOuterCandidateContext);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:100:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:101:0x01df  */
    /* JADX WARN: Code duplicated, block: B:103:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:105:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:106:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:108:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:111:0x0215  */
    /* JADX WARN: Code duplicated, block: B:112:0x021d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0221  */
    /* JADX WARN: Code duplicated, block: B:115:0x0229  */
    /* JADX WARN: Code duplicated, block: B:117:0x022d  */
    /* JADX WARN: Code duplicated, block: B:118:0x0235  */
    /* JADX WARN: Code duplicated, block: B:121:0x023c  */
    /* JADX WARN: Code duplicated, block: B:122:0x0241  */
    /* JADX WARN: Code duplicated, block: B:125:0x0246  */
    /* JADX WARN: Code duplicated, block: B:126:0x0249  */
    /* JADX WARN: Code duplicated, block: B:128:0x024c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0252  */
    /* JADX WARN: Code duplicated, block: B:135:0x0263  */
    /* JADX WARN: Code duplicated, block: B:138:0x026d  */
    /* JADX WARN: Code duplicated, block: B:142:0x027d  */
    /* JADX WARN: Code duplicated, block: B:146:0x0285  */
    /* JADX WARN: Code duplicated, block: B:152:0x029a  */
    /* JADX WARN: Code duplicated, block: B:153:0x029d  */
    /* JADX WARN: Code duplicated, block: B:156:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:159:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:162:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:164:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:171:0x032b  */
    /* JADX WARN: Code duplicated, block: B:174:0x0355  */
    /* JADX WARN: Code duplicated, block: B:176:0x035b  */
    /* JADX WARN: Code duplicated, block: B:182:0x027a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x027d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:? A[LOOP:1: B:136:0x0267->B:184:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0064  */
    /* JADX WARN: Code duplicated, block: B:67:0x0116  */
    /* JADX WARN: Code duplicated, block: B:69:0x011e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0126  */
    /* JADX WARN: Code duplicated, block: B:76:0x0130  */
    /* JADX WARN: Code duplicated, block: B:93:0x01a6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:94:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:96:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:97:0x01bd  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirExpression resolveVariableAccessAndSelectCandidateImpl(FirQualifiedAccessExpression qualifiedAccess, boolean isUsedAsReceiver, final ResolutionMode resolutionMode, final boolean isUsedAsGetClassReceiver, final FirElement callSite, Function1<? super Collection<Candidate>, Boolean> acceptCandidates) throws UninitializedPropertyAccessException {
        ConeTypeArgumentsForOuterClass coneTypeArgumentsForOuterClass;
        List<ConeDiagnostic> list;
        FirSimpleNamedReference firSimpleNamedReference;
        List<ConeDiagnostic> list2;
        FirSimpleNamedReference firSimpleNamedReference2;
        FirQualifiedAccessExpression firQualifiedAccessExpression;
        boolean z;
        ResolutionResult resolutionResult;
        Collection<Candidate> candidates;
        CallKind.Function function;
        FirNamedReference firNamedReferenceCreateResolvedNamedReference$default;
        Pair pair;
        FirScope originScope;
        FirResolvedSymbolOrigin resolvedSymbolOrigin;
        FirBasedSymbol firBasedSymbol;
        FirResolvedSymbolOrigin firResolvedSymbolOrigin;
        ConeDiagnostic diagnostic;
        FirExpression explicitReceiver;
        FirExpression firExpressionUnwrapSmartcastExpression;
        FirResolvedQualifier firResolvedQualifier;
        FirExpressionsResolveTransformer firExpressionsResolveTransformer;
        FirTypeParameterSymbol firTypeParameterSymbol;
        Candidate candidate;
        ConeResolutionResultOverridesOtherToPreserveCompatibility coneResolutionResultOverridesOtherToPreserveCompatibility;
        FirClassLikeSymbol firClassLikeSymbol;
        boolean z2;
        Collection<Candidate> collection;
        Iterator<T> it;
        QualifierResolutionResult qualifierResolutionResultResolveRootPartOfQualifier;
        FirReference calleeReference = qualifiedAccess.getCalleeReference();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = null;
        FirSimpleNamedReference firSimpleNamedReference3 = calleeReference instanceof FirSimpleNamedReference ? (FirSimpleNamedReference) calleeReference : null;
        if (firSimpleNamedReference3 == null) {
            return qualifiedAccess;
        }
        FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this.transformer;
        if (firExpressionsResolveTransformer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer3 = null;
        }
        final FirQualifiedAccessExpression firQualifiedAccessExpressionTransformExplicitReceiverOf = firExpressionsResolveTransformer3.transformExplicitReceiverOf(qualifiedAccess);
        FirPropertyAccessExpression firPropertyAccessExpression = firQualifiedAccessExpressionTransformExplicitReceiverOf instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) firQualifiedAccessExpressionTransformExplicitReceiverOf : null;
        List<ConeDiagnostic> nonFatalDiagnostics = firPropertyAccessExpression != null ? firPropertyAccessExpression.getNonFatalDiagnostics() : null;
        if (nonFatalDiagnostics == null) {
            nonFatalDiagnostics = CollectionsKt.emptyList();
        }
        List<ConeDiagnostic> list3 = nonFatalDiagnostics;
        final FirSimpleNamedReference firSimpleNamedReference4 = firSimpleNamedReference3;
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: cz4
            public final Object invoke() {
                return FirCallResolver.b(this.b, firQualifiedAccessExpressionTransformExplicitReceiverOf, firSimpleNamedReference4, isUsedAsGetClassReceiver, callSite, resolutionMode);
            }
        });
        if (isUsedAsReceiver || !isSuccess(resolveVariableAccessAndSelectCandidateImpl$lambda$1(lazy))) {
            FirExpression explicitReceiver2 = firQualifiedAccessExpressionTransformExplicitReceiverOf.getExplicitReceiver();
            FirExpression firExpressionUnwrapSmartcastExpression2 = explicitReceiver2 != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver2) : null;
            FirResolvedQualifier firResolvedQualifier2 = firExpressionUnwrapSmartcastExpression2 instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression2 : null;
            if (firResolvedQualifier2 == null || firResolvedQualifier2.getTypeArguments().isEmpty()) {
                coneTypeArgumentsForOuterClass = null;
            } else {
                KtSourceElement source = firResolvedQualifier2.getSource();
                source.getClass();
                coneTypeArgumentsForOuterClass = new ConeTypeArgumentsForOuterClass(source);
            }
            if (firResolvedQualifier2 != null) {
                FirResolvedQualifier firResolvedQualifier3 = firResolvedQualifier2;
                list = list3;
                QualifierResolutionResult qualifierResolutionResultContinueQualifier = QualifiedNameResolutionKt.continueQualifier(firResolvedQualifier3, firSimpleNamedReference4, firQualifiedAccessExpressionTransformExplicitReceiverOf, CollectionsKt.plus(list3, CollectionsKt.listOfNotNull(coneTypeArgumentsForOuterClass)), getSession(), this.components);
                firSimpleNamedReference = firSimpleNamedReference4;
                if (qualifierResolutionResultContinueQualifier != null) {
                    if (qualifierResolutionResultContinueQualifier.getApplicability() != CandidateApplicability.RESOLVED && isSuccess(resolveVariableAccessAndSelectCandidateImpl$lambda$1(lazy))) {
                        qualifierResolutionResultContinueQualifier = null;
                    }
                    if (qualifierResolutionResultContinueQualifier != null) {
                        ResolveUtilsKt.unsetResolvedToCompanionIf(firResolvedQualifier3, true);
                        return qualifierResolutionResultContinueQualifier.getQualifier();
                    }
                }
            } else {
                list = list3;
                firSimpleNamedReference = firSimpleNamedReference4;
            }
        } else {
            list = list3;
            firSimpleNamedReference = firSimpleNamedReference4;
        }
        ResolutionResult resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1 = resolveVariableAccessAndSelectCandidateImpl$lambda$1(lazy);
        if (firQualifiedAccessExpressionTransformExplicitReceiverOf.getExplicitReceiver() == null) {
            if (!isSuccess(resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1)) {
                qualifierResolutionResultResolveRootPartOfQualifier = QualifiedNameResolutionKt.resolveRootPartOfQualifier(this.components, firSimpleNamedReference, firQualifiedAccessExpressionTransformExplicitReceiverOf, list, isUsedAsReceiver);
                if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                    if (qualifierResolutionResultResolveRootPartOfQualifier.getApplicability() != CandidateApplicability.RESOLVED && isSuccess(resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1)) {
                        qualifierResolutionResultResolveRootPartOfQualifier = null;
                    }
                    if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                        return qualifierResolutionResultResolveRootPartOfQualifier.getQualifier();
                    }
                }
            } else if (isUsedAsReceiver) {
                Collection<Candidate> candidates2 = resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1.getCandidates();
                if ((candidates2 instanceof Collection) && candidates2.isEmpty()) {
                    qualifierResolutionResultResolveRootPartOfQualifier = QualifiedNameResolutionKt.resolveRootPartOfQualifier(this.components, firSimpleNamedReference, firQualifiedAccessExpressionTransformExplicitReceiverOf, list, isUsedAsReceiver);
                    if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                        if (qualifierResolutionResultResolveRootPartOfQualifier.getApplicability() != CandidateApplicability.RESOLVED) {
                            qualifierResolutionResultResolveRootPartOfQualifier = null;
                        }
                        if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                            return qualifierResolutionResultResolveRootPartOfQualifier.getQualifier();
                        }
                    }
                } else {
                    Iterator<T> it2 = candidates2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            qualifierResolutionResultResolveRootPartOfQualifier = QualifiedNameResolutionKt.resolveRootPartOfQualifier(this.components, firSimpleNamedReference, firQualifiedAccessExpressionTransformExplicitReceiverOf, list, isUsedAsReceiver);
                            if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                                if (qualifierResolutionResultResolveRootPartOfQualifier.getApplicability() != CandidateApplicability.RESOLVED) {
                                    qualifierResolutionResultResolveRootPartOfQualifier = null;
                                }
                                if (qualifierResolutionResultResolveRootPartOfQualifier != null) {
                                    return qualifierResolutionResultResolveRootPartOfQualifier.getQualifier();
                                }
                            }
                        } else if (!(((Candidate) it2.next()).getSymbol() instanceof FirClassLikeSymbol)) {
                        }
                    }
                }
            }
        }
        boolean z3 = false;
        if (resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1.getCandidates().isEmpty() && !(firQualifiedAccessExpressionTransformExplicitReceiverOf instanceof FirFunctionCall)) {
            firSimpleNamedReference2 = firSimpleNamedReference;
            list2 = list;
            ResolutionResult resolutionResultCollectCandidates$default = collectCandidates$default(this, firQualifiedAccessExpressionTransformExplicitReceiverOf, firSimpleNamedReference.getName(), CallKind.Function.INSTANCE, false, null, null, null, null, null, resolutionMode, null, 1528, null);
            firQualifiedAccessExpression = firQualifiedAccessExpressionTransformExplicitReceiverOf;
            if (!resolutionResultCollectCandidates$default.getCandidates().isEmpty()) {
                z = resolutionResultCollectCandidates$default.getApplicability().compareTo(CandidateApplicability.INAPPLICABLE_WRONG_RECEIVER) > 0;
                resolutionResult = resolutionResultCollectCandidates$default;
            }
            candidates = resolutionResult.getCandidates();
            if (!((Boolean) acceptCandidates.invoke(candidates)).booleanValue()) {
                return firQualifiedAccessExpression;
            }
            Name name = firSimpleNamedReference2.getName();
            CallInfo info = resolutionResult.getInfo();
            CandidateApplicability applicability = resolutionResult.getApplicability();
            FirExpression explicitReceiver3 = firQualifiedAccessExpression.getExplicitReceiver();
            if (z) {
                function = CallKind.Function.INSTANCE;
            } else {
                function = null;
            }
            firNamedReferenceCreateResolvedNamedReference$default = createResolvedNamedReference$default(this, firSimpleNamedReference2, name, info, candidates, applicability, explicitReceiver3, false, function, null, 320, null);
            if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirResolvedNamedReference) {
                FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) firNamedReferenceCreateResolvedNamedReference$default;
                pair = TuplesKt.to(firResolvedNamedReference.getResolvedSymbol(), firResolvedNamedReference.getResolvedSymbolOrigin());
            } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirNamedReferenceWithCandidate) {
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = (FirNamedReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default;
                FirBasedSymbol<?> candidateSymbol = firNamedReferenceWithCandidate.getCandidateSymbol();
                originScope = firNamedReferenceWithCandidate.getCandidate().getOriginScope();
                if (originScope != null) {
                    resolvedSymbolOrigin = ResolveUtilsKt.toResolvedSymbolOrigin(originScope);
                } else {
                    resolvedSymbolOrigin = null;
                }
                pair = TuplesKt.to(candidateSymbol, resolvedSymbolOrigin);
            } else {
                pair = TuplesKt.to(null, null);
            }
            firBasedSymbol = (FirBasedSymbol) pair.component1();
            firResolvedSymbolOrigin = (FirResolvedSymbolOrigin) pair.component2();
            if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirErrorReferenceWithCandidate) {
                diagnostic = ((FirErrorReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
            } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirResolvedErrorReference) {
                diagnostic = ((FirResolvedErrorReference) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
            } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirErrorNamedReference) {
                diagnostic = ((FirErrorNamedReference) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
            } else {
                diagnostic = null;
            }
            explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
            if (explicitReceiver != null) {
                firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver);
            } else {
                firExpressionUnwrapSmartcastExpression = null;
            }
            if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
                firResolvedQualifier = (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression;
            } else {
                firResolvedQualifier = null;
            }
            if (firResolvedQualifier != null) {
                if (candidates.isEmpty()) {
                    z2 = true;
                } else {
                    collection = candidates;
                    if ((collection instanceof Collection) || !collection.isEmpty()) {
                        it = collection.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (!((Candidate) it.next()).getIsFromCompanionObjectTypeScope()) {
                                    z2 = true;
                                }
                            }
                        }
                    }
                    z2 = false;
                }
                ResolveUtilsKt.unsetResolvedToCompanionIf(firResolvedQualifier, z2);
            }
            if (firBasedSymbol instanceof FirClassLikeSymbol) {
                candidate = (Candidate) CollectionsKt.singleOrNull(candidates);
                if (candidate != null && FirCallCompletionResultsWriterTransformerKt.doesResolutionResultOverrideOtherToPreserveCompatibility(candidate)) {
                    z3 = true;
                }
                if (z3) {
                    coneResolutionResultOverridesOtherToPreserveCompatibility = ConeResolutionResultOverridesOtherToPreserveCompatibility.INSTANCE;
                } else {
                    coneResolutionResultOverridesOtherToPreserveCompatibility = null;
                }
                List listPlus = CollectionsKt.plus(list2, CollectionsKt.listOfNotNull(coneResolutionResultOverridesOtherToPreserveCompatibility));
                FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents = this.components;
                firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
                KtSourceElement source2 = firQualifiedAccessExpression.getSource();
                FirExpression explicitReceiver4 = firQualifiedAccessExpression.getExplicitReceiver();
                FirResolvedQualifier firResolvedQualifier4 = explicitReceiver4 instanceof FirResolvedQualifier ? (FirResolvedQualifier) explicitReceiver4 : null;
                List<FirTypeProjection> typeArguments = firQualifiedAccessExpression.getTypeArguments();
                if (diagnostic == null) {
                    diagnostic = QualifiedNameResolutionKt.extractNestedClassAccessDiagnostic(firNamedReferenceCreateResolvedNamedReference$default.getSource(), firQualifiedAccessExpression.getExplicitReceiver(), firClassLikeSymbol);
                }
                return ResolveUtilsKt.buildResolvedQualifierForClass(bodyResolveTransformerComponents, firClassLikeSymbol, source2, firResolvedQualifier4, typeArguments, diagnostic, QualifiedNameResolutionKt.extractNonFatalDiagnostics(firNamedReferenceCreateResolvedNamedReference$default.getSource(), firQualifiedAccessExpression.getExplicitReceiver(), firClassLikeSymbol, listPlus, getSession()), firQualifiedAccessExpression.getAnnotations(), firResolvedSymbolOrigin);
            }
            if (firBasedSymbol instanceof FirTypeParameterSymbol) {
                firTypeParameterSymbol = (FirTypeParameterSymbol) firBasedSymbol;
                if (((FirTypeParameter) firTypeParameterSymbol.getFir()).getIsReified() && diagnostic == null) {
                    FirResolvedReifiedParameterReferenceBuilder firResolvedReifiedParameterReferenceBuilder = new FirResolvedReifiedParameterReferenceBuilder();
                    firResolvedReifiedParameterReferenceBuilder.setSource(firNamedReferenceCreateResolvedNamedReference$default.getSource());
                    firResolvedReifiedParameterReferenceBuilder.setSymbol(firTypeParameterSymbol);
                    firResolvedReifiedParameterReferenceBuilder.setConeTypeOrNull(ResolveUtilsKt.typeForReifiedParameterReference(firResolvedReifiedParameterReferenceBuilder));
                    return firResolvedReifiedParameterReferenceBuilder.mo288build();
                }
            }
            firQualifiedAccessExpression.replaceCalleeReference(firNamedReferenceCreateResolvedNamedReference$default);
            if (candidates.size() == 1) {
                Candidate candidate2 = (Candidate) CollectionsKt.single(candidates);
                candidate2.updateSourcesOfReceivers();
                FirExpression firExpressionDispatchReceiverExpression = candidate2.dispatchReceiverExpression();
                firQualifiedAccessExpression.replaceDispatchReceiver(firExpressionDispatchReceiverExpression);
                ResolveUtilsKt.replaceExplicitReceiverIfNecessary(firQualifiedAccessExpression, firExpressionDispatchReceiverExpression, candidate2);
                firQualifiedAccessExpression.replaceExtensionReceiver(candidate2.chosenExtensionReceiverExpression());
                firQualifiedAccessExpression.replaceContextArguments(candidate2.contextArguments());
                FirCallCompletionResultsWriterTransformerKt.addNonFatalDiagnostics(firQualifiedAccessExpression, candidate2);
            }
            firExpressionsResolveTransformer = this.transformer;
            if (firExpressionsResolveTransformer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
            } else {
                firExpressionsResolveTransformer2 = firExpressionsResolveTransformer;
            }
            firExpressionsResolveTransformer2.storeTypeFromCallee$org_jetbrains_kotlin_resolve(firQualifiedAccessExpression, callSite instanceof FirVariableAssignment);
            return firQualifiedAccessExpression;
        }
        list2 = list;
        firSimpleNamedReference2 = firSimpleNamedReference;
        firQualifiedAccessExpression = firQualifiedAccessExpressionTransformExplicitReceiverOf;
        z = false;
        resolutionResult = resolutionResultResolveVariableAccessAndSelectCandidateImpl$lambda$1;
        candidates = resolutionResult.getCandidates();
        if (!((Boolean) acceptCandidates.invoke(candidates)).booleanValue()) {
            return firQualifiedAccessExpression;
        }
        Name name2 = firSimpleNamedReference2.getName();
        CallInfo info2 = resolutionResult.getInfo();
        CandidateApplicability applicability2 = resolutionResult.getApplicability();
        FirExpression explicitReceiver5 = firQualifiedAccessExpression.getExplicitReceiver();
        if (z) {
            function = CallKind.Function.INSTANCE;
        } else {
            function = null;
        }
        firNamedReferenceCreateResolvedNamedReference$default = createResolvedNamedReference$default(this, firSimpleNamedReference2, name2, info2, candidates, applicability2, explicitReceiver5, false, function, null, 320, null);
        if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirResolvedNamedReference) {
            FirResolvedNamedReference firResolvedNamedReference2 = (FirResolvedNamedReference) firNamedReferenceCreateResolvedNamedReference$default;
            pair = TuplesKt.to(firResolvedNamedReference2.getResolvedSymbol(), firResolvedNamedReference2.getResolvedSymbolOrigin());
        } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirNamedReferenceWithCandidate) {
            FirNamedReferenceWithCandidate firNamedReferenceWithCandidate2 = (FirNamedReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default;
            FirBasedSymbol<?> candidateSymbol2 = firNamedReferenceWithCandidate2.getCandidateSymbol();
            originScope = firNamedReferenceWithCandidate2.getCandidate().getOriginScope();
            if (originScope != null) {
                resolvedSymbolOrigin = ResolveUtilsKt.toResolvedSymbolOrigin(originScope);
            } else {
                resolvedSymbolOrigin = null;
            }
            pair = TuplesKt.to(candidateSymbol2, resolvedSymbolOrigin);
        } else {
            pair = TuplesKt.to(null, null);
        }
        firBasedSymbol = (FirBasedSymbol) pair.component1();
        firResolvedSymbolOrigin = (FirResolvedSymbolOrigin) pair.component2();
        if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirErrorReferenceWithCandidate) {
            diagnostic = ((FirErrorReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
        } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirResolvedErrorReference) {
            diagnostic = ((FirResolvedErrorReference) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
        } else if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirErrorNamedReference) {
            diagnostic = ((FirErrorNamedReference) firNamedReferenceCreateResolvedNamedReference$default).getDiagnostic();
        } else {
            diagnostic = null;
        }
        explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        if (explicitReceiver != null) {
            firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver);
        } else {
            firExpressionUnwrapSmartcastExpression = null;
        }
        if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
            firResolvedQualifier = (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression;
        } else {
            firResolvedQualifier = null;
        }
        if (firResolvedQualifier != null) {
            if (candidates.isEmpty()) {
                collection = candidates;
                if (collection instanceof Collection) {
                    it = collection.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!((Candidate) it.next()).getIsFromCompanionObjectTypeScope()) {
                                z2 = true;
                            }
                        }
                    }
                } else {
                    it = collection.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!((Candidate) it.next()).getIsFromCompanionObjectTypeScope()) {
                                z2 = true;
                            }
                        }
                    }
                }
                z2 = false;
            } else {
                z2 = true;
            }
            ResolveUtilsKt.unsetResolvedToCompanionIf(firResolvedQualifier, z2);
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            candidate = (Candidate) CollectionsKt.singleOrNull(candidates);
            if (candidate != null) {
                z3 = true;
            }
            if (z3) {
                coneResolutionResultOverridesOtherToPreserveCompatibility = ConeResolutionResultOverridesOtherToPreserveCompatibility.INSTANCE;
            } else {
                coneResolutionResultOverridesOtherToPreserveCompatibility = null;
            }
            List listPlus2 = CollectionsKt.plus(list2, CollectionsKt.listOfNotNull(coneResolutionResultOverridesOtherToPreserveCompatibility));
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents2 = this.components;
            firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
            KtSourceElement source3 = firQualifiedAccessExpression.getSource();
            FirExpression explicitReceiver6 = firQualifiedAccessExpression.getExplicitReceiver();
            FirResolvedQualifier firResolvedQualifier5 = explicitReceiver6 instanceof FirResolvedQualifier ? (FirResolvedQualifier) explicitReceiver6 : null;
            List<FirTypeProjection> typeArguments2 = firQualifiedAccessExpression.getTypeArguments();
            if (diagnostic == null) {
                diagnostic = QualifiedNameResolutionKt.extractNestedClassAccessDiagnostic(firNamedReferenceCreateResolvedNamedReference$default.getSource(), firQualifiedAccessExpression.getExplicitReceiver(), firClassLikeSymbol);
            }
            return ResolveUtilsKt.buildResolvedQualifierForClass(bodyResolveTransformerComponents2, firClassLikeSymbol, source3, firResolvedQualifier5, typeArguments2, diagnostic, QualifiedNameResolutionKt.extractNonFatalDiagnostics(firNamedReferenceCreateResolvedNamedReference$default.getSource(), firQualifiedAccessExpression.getExplicitReceiver(), firClassLikeSymbol, listPlus2, getSession()), firQualifiedAccessExpression.getAnnotations(), firResolvedSymbolOrigin);
        }
        if (firBasedSymbol instanceof FirTypeParameterSymbol) {
            firTypeParameterSymbol = (FirTypeParameterSymbol) firBasedSymbol;
            if (((FirTypeParameter) firTypeParameterSymbol.getFir()).getIsReified()) {
                FirResolvedReifiedParameterReferenceBuilder firResolvedReifiedParameterReferenceBuilder2 = new FirResolvedReifiedParameterReferenceBuilder();
                firResolvedReifiedParameterReferenceBuilder2.setSource(firNamedReferenceCreateResolvedNamedReference$default.getSource());
                firResolvedReifiedParameterReferenceBuilder2.setSymbol(firTypeParameterSymbol);
                firResolvedReifiedParameterReferenceBuilder2.setConeTypeOrNull(ResolveUtilsKt.typeForReifiedParameterReference(firResolvedReifiedParameterReferenceBuilder2));
                return firResolvedReifiedParameterReferenceBuilder2.mo288build();
            }
        }
        firQualifiedAccessExpression.replaceCalleeReference(firNamedReferenceCreateResolvedNamedReference$default);
        if (candidates.size() == 1) {
            Candidate candidate3 = (Candidate) CollectionsKt.single(candidates);
            candidate3.updateSourcesOfReceivers();
            FirExpression firExpressionDispatchReceiverExpression2 = candidate3.dispatchReceiverExpression();
            firQualifiedAccessExpression.replaceDispatchReceiver(firExpressionDispatchReceiverExpression2);
            ResolveUtilsKt.replaceExplicitReceiverIfNecessary(firQualifiedAccessExpression, firExpressionDispatchReceiverExpression2, candidate3);
            firQualifiedAccessExpression.replaceExtensionReceiver(candidate3.chosenExtensionReceiverExpression());
            firQualifiedAccessExpression.replaceContextArguments(candidate3.contextArguments());
            FirCallCompletionResultsWriterTransformerKt.addNonFatalDiagnostics(firQualifiedAccessExpression, candidate3);
        }
        firExpressionsResolveTransformer = this.transformer;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
        } else {
            firExpressionsResolveTransformer2 = firExpressionsResolveTransformer;
        }
        firExpressionsResolveTransformer2.storeTypeFromCallee$org_jetbrains_kotlin_resolve(firQualifiedAccessExpression, callSite instanceof FirVariableAssignment);
        return firQualifiedAccessExpression;
    }

    private static final ResolutionResult resolveVariableAccessAndSelectCandidateImpl$lambda$1(Lazy<ResolutionResult> lazy) {
        return (ResolutionResult) lazy.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final ResolutionResult runResolutionForGivenSymbol(CallInfo callInfo, FirBasedSymbol<?> symbol) throws UninitializedPropertyAccessException {
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = this.transformer;
        FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = null;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer = null;
        }
        Candidate candidateCreateCandidate$default = CandidateFactory.createCandidate$default(new CandidateFactory(firExpressionsResolveTransformer.getTransformer().getResolutionContext(), callInfo), callInfo, symbol, ExplicitReceiverKind.NO_EXPLICIT_RECEIVER, null, null, null, false, false, 240, null);
        ResolutionStageRunner resolutionStageRunner = this.components.getResolutionStageRunner();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this.transformer;
        if (firExpressionsResolveTransformer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
        } else {
            firExpressionsResolveTransformer2 = firExpressionsResolveTransformer3;
        }
        CandidateApplicability candidateApplicabilityProcessCandidate$default = ResolutionStageRunner.processCandidate$default(resolutionStageRunner, candidateCreateCandidate$default, firExpressionsResolveTransformer2.getTransformer().getResolutionContext(), false, false, 12, null);
        return new ResolutionResult(callInfo, candidateApplicabilityProcessCandidate$default, CollectionsKt.listOf(candidateCreateCandidate$default), CollectionsKt.emptyList(), candidateApplicabilityProcessCandidate$default == CandidateApplicability.INAPPLICABLE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirDelegatedConstructorCall selectDelegatingConstructorCall(FirDelegatedConstructorCall call, Name name, CandidateCollector result, CallInfo callInfo) throws UninitializedPropertyAccessException {
        Pair pairReduceCandidates$default = reduceCandidates$default(this, result, null, 2, null);
        Set set = (Set) pairReduceCandidates$default.component1();
        call.replaceCalleeReference(createResolvedNamedReference$default(this, call.getCalleeReference(), name, callInfo, set, (CandidateApplicability) pairReduceCandidates$default.component2(), null, false, null, null, 480, null));
        Candidate candidate = (Candidate) CollectionsKt.singleOrNull(set);
        if (candidate != null) {
            candidate.updateSourcesOfReceivers();
        }
        if (candidate != null) {
            FirBasedSymbol<?> symbol = candidate.getSymbol();
            if ((symbol instanceof FirConstructorSymbol) && ((FirMemberDeclaration) ((FirConstructorSymbol) symbol).getFir()).getStatus().isInner()) {
                call.replaceDispatchReceiver(candidate.dispatchReceiverExpression());
            }
            call.replaceContextArguments(candidate.contextArguments());
        }
        return call;
    }

    private final CallInfo toCallInfo(FirAnnotationCall annotation, FirSimpleNamedReference reference) {
        return new CallInfo(annotation, CallKind.Function.INSTANCE, reference.getName(), null, annotation.getArgumentList(), false, annotation.getTypeArguments(), getSession(), this.components.getFile(), this.components.getContainingDeclarations(), null, ResolutionMode.ContextIndependent.INSTANCE, null, ImplicitInvokeMode.None, null, 21504, null);
    }

    private final FirTypeProjection toFirTypeProjection(ConeTypeProjection coneTypeProjection) {
        Variance variance;
        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
        if (type == null) {
            return new FirStarProjectionBuilder().build();
        }
        FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(type);
        firTypeProjectionWithVarianceBuilder.setTypeRef(firResolvedTypeRefBuilder.build());
        int i = WhenMappings.$EnumSwitchMapping$0[coneTypeProjection.getKind().ordinal()];
        if (i == 1) {
            variance = Variance.IN_VARIANCE;
        } else if (i == 2) {
            variance = Variance.OUT_VARIANCE;
        } else {
            if (i != 3) {
                if (i != 4) {
                    bu8.a();
                    return null;
                }
                g33.a();
                return null;
            }
            variance = Variance.INVARIANT;
        }
        firTypeProjectionWithVarianceBuilder.setVariance(variance);
        return firTypeProjectionWithVarianceBuilder.build();
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0097  */
    /* JADX WARN: Multi-variable type inference failed */
    public final CallInfo callInfoForDelegatingConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ConeClassLikeType constructedType) {
        List listEmptyList;
        ConeTypeProjection[] typeArguments;
        List<FirTypeParameterRef> typeParameters;
        ConeClassLikeLookupTag lookupTag;
        delegatedConstructorCall.getClass();
        Name name = SpecialNames.INIT;
        FirClassLikeSymbol<?> symbol = (constructedType == null || (lookupTag = constructedType.getLookupTag()) == null) ? null : ToSymbolUtilsKt.toSymbol(lookupTag, this.components.getSession());
        if (constructedType == null || (typeArguments = constructedType.getTypeArguments()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
            FirRegularClass firRegularClass = firClassLikeDeclaration instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration : null;
            int i = 0;
            if (firRegularClass != null && (typeParameters = firRegularClass.getTypeParameters()) != null) {
                List<FirTypeParameterRef> list = typeParameters;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
            }
            List listTake = ArraysKt.take(typeArguments, i);
            if (listTake != null) {
                List list2 = listTake;
                listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    listEmptyList.add(toFirTypeProjection((ConeTypeProjection) it2.next()));
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
        }
        return new CallInfo(delegatedConstructorCall, CallKind.DelegatingConstructorCall.INSTANCE, name, null, delegatedConstructorCall.getArgumentList(), false, listEmptyList, getSession(), this.components.getFile(), this.components.getContainingDeclarations(), null, ResolutionMode.ContextIndependent.INSTANCE, null, ImplicitInvokeMode.None, null, 21504, null);
    }

    public final List<OverloadCandidate> collectAllCandidates(FirQualifiedAccessExpression qualifiedAccess, Name name, List<? extends FirDeclaration> containingDeclarations, ResolutionContext resolutionContext, ResolutionMode resolutionMode) {
        FirFunctionCallOrigin origin;
        qualifiedAccess.getClass();
        name.getClass();
        containingDeclarations.getClass();
        resolutionContext.getClass();
        resolutionMode.getClass();
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents = this.components;
        AllCandidatesCollector allCandidatesCollector = new AllCandidatesCollector(bodyResolveTransformerComponents, bodyResolveTransformerComponents.getResolutionStageRunner());
        boolean z = qualifiedAccess instanceof FirFunctionCall;
        FirFunctionCall firFunctionCall = z ? (FirFunctionCall) qualifiedAccess : null;
        if (firFunctionCall == null || (origin = firFunctionCall.getOrigin()) == null) {
            origin = FirFunctionCallOrigin.Regular;
        }
        FirFunctionCallOrigin firFunctionCallOrigin = origin;
        ResolutionResult resolutionResultCollectAllCandidates$collectCandidates = collectAllCandidates$collectCandidates(this, qualifiedAccess, name, firFunctionCallOrigin, containingDeclarations, resolutionContext, allCandidatesCollector, resolutionMode, null);
        if (resolutionResultCollectAllCandidates$collectCandidates.getCandidates().isEmpty() && !z) {
            ResolutionResult resolutionResultCollectAllCandidates$collectCandidates2 = collectAllCandidates$collectCandidates(this, qualifiedAccess, name, firFunctionCallOrigin, containingDeclarations, resolutionContext, allCandidatesCollector, resolutionMode, CallKind.Function.INSTANCE);
            if (!resolutionResultCollectAllCandidates$collectCandidates2.getCandidates().isEmpty()) {
                resolutionResultCollectAllCandidates$collectCandidates = resolutionResultCollectAllCandidates$collectCandidates2;
            }
        }
        Set<Candidate> setChooseMaximallySpecificCandidates = new ConeEquivalentCallConflictResolver(getSession()).chooseMaximallySpecificCandidates(allCandidatesCollector.getAllCandidates());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setChooseMaximallySpecificCandidates, 10));
        for (Candidate candidate : setChooseMaximallySpecificCandidates) {
            arrayList.add(new OverloadCandidate(candidate, resolutionResultCollectAllCandidates$collectCandidates.getCandidates().contains(candidate)));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol] */
    /* JADX WARN: Type inference failed for: r4v1, types: [org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol] */
    /* JADX WARN: Type inference failed for: r4v2 */
    public final FirConstructorSymbol getAnnotationConstructorSymbol(ConeKotlinType annotationConeType, FirRegularClassSymbol annotationClassSymbol) {
        annotationConeType.getClass();
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(annotationConeType), getSession());
        FirClassLikeSymbol firClassLikeSymbol = symbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) symbol : null;
        if (firClassLikeSymbol != null) {
            annotationClassSymbol = firClassLikeSymbol;
        }
        FirConstructorSymbol primaryConstructorSymbol = annotationClassSymbol != 0 ? ScopeUtilsKt.getPrimaryConstructorSymbol(annotationClassSymbol, getSession(), this.components.getScopeSession()) : null;
        if (primaryConstructorSymbol != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(primaryConstructorSymbol, FirResolvePhase.TYPES);
        }
        return primaryConstructorSymbol;
    }

    public final ConeCallConflictResolver getConflictResolver() {
        return this.conflictResolver;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public final void initTransformer(FirExpressionsResolveTransformer transformer) {
        transformer.getClass();
        this.transformer = transformer;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirAnnotationCall resolveAnnotationCall(FirAnnotationCall annotation) throws UninitializedPropertyAccessException {
        ConeDiagnostic coneUnreportedDuplicateDiagnostic;
        FirReference firReferenceBuildReferenceWithErrorCandidate;
        annotation.getClass();
        FirReference calleeReference = annotation.getCalleeReference();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = null;
        FirSimpleNamedReference firSimpleNamedReference = calleeReference instanceof FirSimpleNamedReference ? (FirSimpleNamedReference) calleeReference : null;
        if (firSimpleNamedReference == null) {
            return null;
        }
        FirRegularClassSymbol correspondingClassSymbolOrNull = ResolveUtilsKt.getCorrespondingClassSymbolOrNull(annotation, getSession());
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(annotation.getAnnotationTypeRef());
        if (correspondingClassSymbolOrNull == null || ((FirRegularClass) correspondingClassSymbolOrNull.getFir()).getClassKind() != ClassKind.ANNOTATION_CLASS) {
            FirArgumentList argumentList = annotation.getArgumentList();
            FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = this.transformer;
            if (firExpressionsResolveTransformer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer2 = null;
            }
            annotation.replaceArgumentList((FirArgumentList) argumentList.transform(firExpressionsResolveTransformer2, ResolutionMode.ContextDependent.INSTANCE));
            CallInfo callInfo = toCallInfo(annotation, firSimpleNamedReference);
            if (correspondingClassSymbolOrNull != null) {
                coneUnreportedDuplicateDiagnostic = new ConeIllegalAnnotationError(firSimpleNamedReference.getName());
            } else {
                boolean z = coneType instanceof ConeErrorType;
                if (z || !(coneType instanceof ConeClassLikeType)) {
                    ConeErrorType coneErrorType = z ? (ConeErrorType) coneType : null;
                    ConeDiagnostic diagnostic = coneErrorType != null ? coneErrorType.getDiagnostic() : null;
                    ConeDiagnostic coneUnresolvedNameError = diagnostic instanceof ConeDiagnosticWithSymbol ? (ConeDiagnosticWithSymbol) diagnostic : null;
                    if (coneUnresolvedNameError == null) {
                        coneUnresolvedNameError = new ConeUnresolvedNameError(firSimpleNamedReference.getName(), null, null, 6, null);
                    }
                    coneUnreportedDuplicateDiagnostic = new ConeUnreportedDuplicateDiagnostic(coneUnresolvedNameError);
                } else {
                    coneUnreportedDuplicateDiagnostic = new ConeIllegalAnnotationError(firSimpleNamedReference.getName());
                }
            }
            firReferenceBuildReferenceWithErrorCandidate = buildReferenceWithErrorCandidate(callInfo, coneUnreportedDuplicateDiagnostic, firSimpleNamedReference.getSource());
        } else {
            FirConstructorSymbol annotationConstructorSymbol = getAnnotationConstructorSymbol(coneType, correspondingClassSymbolOrNull);
            FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this.transformer;
            if (firExpressionsResolveTransformer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
            } else {
                firExpressionsResolveTransformer = firExpressionsResolveTransformer3;
            }
            firExpressionsResolveTransformer.transformAnnotationCallArguments(annotation, annotationConstructorSymbol);
            CallInfo callInfo2 = toCallInfo(annotation, firSimpleNamedReference);
            if (annotationConstructorSymbol != null) {
                ResolutionResult resolutionResultRunResolutionForGivenSymbol = runResolutionForGivenSymbol(callInfo2, annotationConstructorSymbol);
                firReferenceBuildReferenceWithErrorCandidate = createResolvedNamedReference$default(this, firSimpleNamedReference, firSimpleNamedReference.getName(), callInfo2, resolutionResultRunResolutionForGivenSymbol.getCandidates(), resolutionResultRunResolutionForGivenSymbol.getApplicability(), null, false, null, null, 448, null);
            } else {
                firReferenceBuildReferenceWithErrorCandidate = buildReferenceWithErrorCandidate(callInfo2, correspondingClassSymbolOrNull.getRawStatus().isExpect() ? ConeNoImplicitDefaultConstructorOnExpectClass.INSTANCE : ConeNoConstructorError.INSTANCE, firSimpleNamedReference.getSource());
            }
        }
        annotation.replaceCalleeReference(firReferenceBuildReferenceWithErrorCandidate);
        return annotation;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:16:0x0083  */
    /* JADX WARN: Code duplicated, block: B:17:0x0087  */
    /* JADX WARN: Code duplicated, block: B:20:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:23:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:24:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:27:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:29:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:36:0x0158  */
    /* JADX WARN: Code duplicated, block: B:39:0x0162 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x0164  */
    /* JADX WARN: Code duplicated, block: B:43:0x0177  */
    /* JADX WARN: Code duplicated, block: B:46:0x0184  */
    public final FirFunctionCall resolveCallAndSelectCandidate(FirFunctionCall functionCall, ResolutionMode resolutionMode, CollectionLiteralOuterCandidateContext collectionLiteralContext) throws UninitializedPropertyAccessException {
        FirFunctionCall firFunctionCallBuild;
        ResolutionResult resolutionResult;
        Collection<Candidate> candidates;
        CallKind.VariableAccess variableAccess;
        FirNamedReference firNamedReferenceCreateResolvedNamedReference$default;
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate;
        Candidate candidate;
        FirExpression firExpressionUnwrapSmartcastExpression;
        ConeKotlinType coneKotlinTypeTypeFromCallee;
        FirResolvedQualifier explicitParent;
        functionCall.getClass();
        resolutionMode.getClass();
        boolean z = collectionLiteralContext != null;
        Name name = functionCall.getCalleeReference().getName();
        ResolutionResult resolutionResultCollectCandidates$default = collectCandidates$default(this, functionCall, name, null, false, functionCall.getOrigin(), null, null, null, null, resolutionMode, collectionLiteralContext, 492, null);
        if (resolutionResultCollectCandidates$default.getCandidates().isEmpty() && !z) {
            resolutionResult = resolutionResultCollectCandidates$default;
            ResolutionResult resolutionResultCollectCandidates$default2 = collectCandidates$default(this, functionCall, name, CallKind.VariableAccess.INSTANCE, false, functionCall.getOrigin(), null, null, null, null, resolutionMode, null, 1512, null);
            firFunctionCallBuild = functionCall;
            if (!resolutionResultCollectCandidates$default2.getCandidates().isEmpty()) {
                candidates = resolutionResultCollectCandidates$default2.getCandidates();
            }
            FirNamedReference calleeReference = firFunctionCallBuild.getCalleeReference();
            CallInfo info = resolutionResult.getInfo();
            Collection<Candidate> candidates2 = resolutionResult.getCandidates();
            CandidateApplicability applicability = resolutionResult.getApplicability();
            FirExpression explicitReceiver = firFunctionCallBuild.getExplicitReceiver();
            if (candidates != null) {
                variableAccess = CallKind.VariableAccess.INSTANCE;
            } else {
                variableAccess = null;
            }
            firNamedReferenceCreateResolvedNamedReference$default = createResolvedNamedReference$default(this, calleeReference, name, info, candidates2, applicability, explicitReceiver, false, variableAccess, candidates, 64, null);
            firFunctionCallBuild.replaceCalleeReference(firNamedReferenceCreateResolvedNamedReference$default);
            processContextSensitiveResolutionAlternatives(resolutionResult);
            if (!resolutionResult.getForwardedDiagnostics().isEmpty()) {
                FirCallCompletionResultsWriterTransformerKt.appendNonFatalDiagnostics(firFunctionCallBuild, convertForwardedDiagnostics(resolutionResult));
            }
            if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirNamedReferenceWithCandidate) {
                firNamedReferenceWithCandidate = (FirNamedReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default;
            } else {
                firNamedReferenceWithCandidate = null;
            }
            if (firNamedReferenceWithCandidate != null) {
                candidate = firNamedReferenceWithCandidate.getCandidate();
            } else {
                candidate = null;
            }
            if (candidate != null) {
                candidate.updateSourcesOfReceivers();
            }
            if (candidate != null && !Intrinsics.areEqual(candidate.getCallInfo(), resolutionResult.getInfo())) {
                FirImplicitInvokeCallBuilder firImplicitInvokeCallBuilder = new FirImplicitInvokeCallBuilder();
                firImplicitInvokeCallBuilder.setSource(firFunctionCallBuild.getSource());
                firImplicitInvokeCallBuilder.getAnnotations().addAll(firFunctionCallBuild.getAnnotations());
                firImplicitInvokeCallBuilder.getTypeArguments().addAll(firFunctionCallBuild.getTypeArguments());
                firImplicitInvokeCallBuilder.setExplicitReceiver(firFunctionCallBuild.getExplicitReceiver());
                firImplicitInvokeCallBuilder.setDispatchReceiver(firFunctionCallBuild.getDispatchReceiver());
                firImplicitInvokeCallBuilder.setExtensionReceiver(firFunctionCallBuild.getExtensionReceiver());
                firImplicitInvokeCallBuilder.setArgumentList(firFunctionCallBuild.getArgumentList());
                firImplicitInvokeCallBuilder.setCalleeReference(firFunctionCallBuild.getCalleeReference());
                firImplicitInvokeCallBuilder.setExplicitReceiver(candidate.getCallInfo().getExplicitReceiver());
                firImplicitInvokeCallBuilder.setDispatchReceiver(candidate.dispatchReceiverExpression());
                firImplicitInvokeCallBuilder.setExtensionReceiver(candidate.chosenExtensionReceiverExpression());
                firImplicitInvokeCallBuilder.setArgumentList(candidate.getCallInfo().getArgumentList());
                firImplicitInvokeCallBuilder.getContextArguments().addAll(candidate.contextArguments());
                firFunctionCallBuild = firImplicitInvokeCallBuilder.mo288build();
            }
            FirExpression explicitReceiver2 = firFunctionCallBuild.getExplicitReceiver();
            firExpressionUnwrapSmartcastExpression = explicitReceiver2 != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver2) : null;
            if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
                if (candidate != null) {
                    ResolveUtilsKt.unsetResolvedToCompanionIf((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression, !candidate.getIsFromCompanionObjectTypeScope());
                }
                explicitParent = ((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression).getExplicitParent();
                if (explicitParent != null) {
                    ResolveUtilsKt.unsetResolvedToCompanionIf(explicitParent, true);
                }
            }
            coneKotlinTypeTypeFromCallee = ResolveUtilsKt.typeFromCallee(this.components, firFunctionCallBuild);
            if (coneKotlinTypeTypeFromCallee instanceof ConeErrorType) {
                firFunctionCallBuild.replaceConeTypeOrNull(coneKotlinTypeTypeFromCallee);
            }
            return firFunctionCallBuild;
        }
        firFunctionCallBuild = functionCall;
        resolutionResult = resolutionResultCollectCandidates$default;
        candidates = null;
        FirNamedReference calleeReference2 = firFunctionCallBuild.getCalleeReference();
        CallInfo info2 = resolutionResult.getInfo();
        Collection<Candidate> candidates3 = resolutionResult.getCandidates();
        CandidateApplicability applicability2 = resolutionResult.getApplicability();
        FirExpression explicitReceiver3 = firFunctionCallBuild.getExplicitReceiver();
        if (candidates != null) {
            variableAccess = CallKind.VariableAccess.INSTANCE;
        } else {
            variableAccess = null;
        }
        firNamedReferenceCreateResolvedNamedReference$default = createResolvedNamedReference$default(this, calleeReference2, name, info2, candidates3, applicability2, explicitReceiver3, false, variableAccess, candidates, 64, null);
        firFunctionCallBuild.replaceCalleeReference(firNamedReferenceCreateResolvedNamedReference$default);
        processContextSensitiveResolutionAlternatives(resolutionResult);
        if (!resolutionResult.getForwardedDiagnostics().isEmpty()) {
            FirCallCompletionResultsWriterTransformerKt.appendNonFatalDiagnostics(firFunctionCallBuild, convertForwardedDiagnostics(resolutionResult));
        }
        if (firNamedReferenceCreateResolvedNamedReference$default instanceof FirNamedReferenceWithCandidate) {
            firNamedReferenceWithCandidate = (FirNamedReferenceWithCandidate) firNamedReferenceCreateResolvedNamedReference$default;
        } else {
            firNamedReferenceWithCandidate = null;
        }
        if (firNamedReferenceWithCandidate != null) {
            candidate = firNamedReferenceWithCandidate.getCandidate();
        } else {
            candidate = null;
        }
        if (candidate != null) {
            candidate.updateSourcesOfReceivers();
        }
        if (candidate != null) {
            FirImplicitInvokeCallBuilder firImplicitInvokeCallBuilder2 = new FirImplicitInvokeCallBuilder();
            firImplicitInvokeCallBuilder2.setSource(firFunctionCallBuild.getSource());
            firImplicitInvokeCallBuilder2.getAnnotations().addAll(firFunctionCallBuild.getAnnotations());
            firImplicitInvokeCallBuilder2.getTypeArguments().addAll(firFunctionCallBuild.getTypeArguments());
            firImplicitInvokeCallBuilder2.setExplicitReceiver(firFunctionCallBuild.getExplicitReceiver());
            firImplicitInvokeCallBuilder2.setDispatchReceiver(firFunctionCallBuild.getDispatchReceiver());
            firImplicitInvokeCallBuilder2.setExtensionReceiver(firFunctionCallBuild.getExtensionReceiver());
            firImplicitInvokeCallBuilder2.setArgumentList(firFunctionCallBuild.getArgumentList());
            firImplicitInvokeCallBuilder2.setCalleeReference(firFunctionCallBuild.getCalleeReference());
            firImplicitInvokeCallBuilder2.setExplicitReceiver(candidate.getCallInfo().getExplicitReceiver());
            firImplicitInvokeCallBuilder2.setDispatchReceiver(candidate.dispatchReceiverExpression());
            firImplicitInvokeCallBuilder2.setExtensionReceiver(candidate.chosenExtensionReceiverExpression());
            firImplicitInvokeCallBuilder2.setArgumentList(candidate.getCallInfo().getArgumentList());
            firImplicitInvokeCallBuilder2.getContextArguments().addAll(candidate.contextArguments());
            firFunctionCallBuild = firImplicitInvokeCallBuilder2.mo288build();
        }
        FirExpression explicitReceiver4 = firFunctionCallBuild.getExplicitReceiver();
        firExpressionUnwrapSmartcastExpression = explicitReceiver4 != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver4) : null;
        if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
            if (candidate != null) {
                ResolveUtilsKt.unsetResolvedToCompanionIf((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression, !candidate.getIsFromCompanionObjectTypeScope());
            }
            explicitParent = ((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression).getExplicitParent();
            if (explicitParent != null) {
                ResolveUtilsKt.unsetResolvedToCompanionIf(explicitParent, true);
            }
        }
        coneKotlinTypeTypeFromCallee = ResolveUtilsKt.typeFromCallee(this.components, firFunctionCallBuild);
        if (coneKotlinTypeTypeFromCallee instanceof ConeErrorType) {
            firFunctionCallBuild.replaceConeTypeOrNull(coneKotlinTypeTypeFromCallee);
        }
        return firFunctionCallBuild;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v6 */
    public final Pair<CandidateApplicability, Boolean> resolveCallableReference(Candidate containingCallCandidate, ConeResolvedCallableReferenceAtom resolvedCallableReferenceAtom, boolean hasSyntheticOuterCall) throws UninitializedPropertyAccessException {
        FirRegularTowerDataContexts firRegularTowerDataContexts;
        FirRegularTowerDataContexts firRegularTowerDataContexts2;
        CandidateCollector candidateCollectorRunResolver;
        ConeDiagnostic coneDiagnosticCreateConeDiagnosticForCandidateWithError;
        String message;
        List<ResolutionDiagnostic> diagnostics;
        boolean z;
        containingCallCandidate.getClass();
        resolvedCallableReferenceAtom.getClass();
        if (!resolvedCallableReferenceAtom.getNeedsResolution()) {
            w01.a("Failed requirement.");
            return null;
        }
        NewConstraintSystemImpl csBuilder = ConstraintSystemCompleterKt.getCsBuilder(containingCallCandidate);
        FirCallableReferenceAccess expression = resolvedCallableReferenceAtom.getExpression();
        FirNamedReference calleeReference = expression.getCalleeReference();
        DoubleColonLHS lhs = resolvedCallableReferenceAtom.getLhs();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) csBuilder.buildCurrentSubstitutor();
        ConeKotlinType coneKotlinTypeMo581getExpectedType = resolvedCallableReferenceAtom.mo581getExpectedType();
        CallInfo callInfoCreateCallableReferencesInfoForLHS = createCallableReferencesInfoForLHS(expression, lhs, coneKotlinTypeMo581getExpectedType != null ? coneSubstitutor.substituteOrSelf(coneKotlinTypeMo581getExpectedType) : null, hasSyntheticOuterCall);
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents = this.components;
        CandidateCollector candidateCollector = new CandidateCollector(bodyResolveTransformerComponents, bodyResolveTransformerComponents.getResolutionStageRunner());
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = this.transformer;
        if (firExpressionsResolveTransformer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
            firExpressionsResolveTransformer = null;
        }
        BodyResolveContext context = firExpressionsResolveTransformer.getTransformer().getContext();
        Pair<FirTowerDataContext, FirInferenceSession> callableReferenceContext = context.getSpecialTowerDataContexts().getCallableReferenceContext(expression);
        if (callableReferenceContext == null) {
            FirTowerResolver firTowerResolver = this.towerResolver;
            FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = this.transformer;
            if (firExpressionsResolveTransformer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer2 = null;
            }
            ResolutionContext resolutionContext = firExpressionsResolveTransformer2.getTransformer().getResolutionContext();
            TowerResolveManager towerResolveManager = new TowerResolveManager(candidateCollector);
            CandidateFactory.Companion companion = CandidateFactory.INSTANCE;
            FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this.transformer;
            if (firExpressionsResolveTransformer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                firExpressionsResolveTransformer3 = null;
            }
            candidateCollectorRunResolver = firTowerResolver.runResolver(callInfoCreateCallableReferencesInfoForLHS, resolutionContext, candidateCollector, towerResolveManager, companion.createForCallableReferences(firExpressionsResolveTransformer3.getTransformer().getResolutionContext(), containingCallCandidate));
            expression = expression;
        } else {
            FirTowerDataContext firTowerDataContext = (FirTowerDataContext) callableReferenceContext.component1();
            FirInferenceSession firInferenceSession = (FirInferenceSession) callableReferenceContext.component2();
            FirTowerDataMode towerDataMode = context.getTowerDataMode();
            try {
                FirRegularTowerDataContexts firRegularTowerDataContextsReplaceAndSetActiveRegularContext = context.getRegularTowerDataContexts().replaceAndSetActiveRegularContext(firTowerDataContext);
                FirRegularTowerDataContexts regularTowerDataContexts = context.getRegularTowerDataContexts();
                context.setRegularTowerDataContexts(firRegularTowerDataContextsReplaceAndSetActiveRegularContext);
                try {
                    try {
                        if (firInferenceSession != context.getInferenceSession()) {
                            FirInferenceSession inferenceSession = context.getInferenceSession();
                            context.setInferenceSession(firInferenceSession);
                            firInferenceSession = regularTowerDataContexts;
                            try {
                                FirTowerResolver firTowerResolver2 = this.towerResolver;
                                FirExpressionsResolveTransformer firExpressionsResolveTransformer4 = this.transformer;
                                if (firExpressionsResolveTransformer4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("transformer");
                                    firExpressionsResolveTransformer4 = null;
                                }
                                ResolutionContext resolutionContext2 = firExpressionsResolveTransformer4.getTransformer().getResolutionContext();
                                TowerResolveManager towerResolveManager2 = new TowerResolveManager(candidateCollector);
                                CandidateFactory.Companion companion2 = CandidateFactory.INSTANCE;
                                FirExpressionsResolveTransformer firExpressionsResolveTransformer5 = this.transformer;
                                if (firExpressionsResolveTransformer5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("transformer");
                                    firExpressionsResolveTransformer5 = null;
                                }
                                candidateCollectorRunResolver = firTowerResolver2.runResolver(callInfoCreateCallableReferencesInfoForLHS, resolutionContext2, candidateCollector, towerResolveManager2, companion2.createForCallableReferences(firExpressionsResolveTransformer5.getTransformer().getResolutionContext(), containingCallCandidate));
                                context.setInferenceSession(inferenceSession);
                                firRegularTowerDataContexts2 = firInferenceSession;
                            } catch (Throwable th) {
                                context.setInferenceSession(inferenceSession);
                                throw th;
                            }
                        } else {
                            firRegularTowerDataContexts2 = regularTowerDataContexts;
                            FirTowerResolver firTowerResolver3 = this.towerResolver;
                            FirExpressionsResolveTransformer firExpressionsResolveTransformer6 = this.transformer;
                            if (firExpressionsResolveTransformer6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                                firExpressionsResolveTransformer6 = null;
                            }
                            ResolutionContext resolutionContext3 = firExpressionsResolveTransformer6.getTransformer().getResolutionContext();
                            TowerResolveManager towerResolveManager3 = new TowerResolveManager(candidateCollector);
                            CandidateFactory.Companion companion3 = CandidateFactory.INSTANCE;
                            FirExpressionsResolveTransformer firExpressionsResolveTransformer7 = this.transformer;
                            if (firExpressionsResolveTransformer7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("transformer");
                                firExpressionsResolveTransformer7 = null;
                            }
                            candidateCollectorRunResolver = firTowerResolver3.runResolver(callInfoCreateCallableReferencesInfoForLHS, resolutionContext3, candidateCollector, towerResolveManager3, companion3.createForCallableReferences(firExpressionsResolveTransformer7.getTransformer().getResolutionContext(), containingCallCandidate));
                        }
                        context.setRegularTowerDataContexts(firRegularTowerDataContexts2);
                        context.setTowerDataMode(towerDataMode);
                    } catch (Throwable th2) {
                        th = th2;
                        firRegularTowerDataContexts = firInferenceSession;
                        context.setRegularTowerDataContexts(firRegularTowerDataContexts);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    firRegularTowerDataContexts = regularTowerDataContexts;
                }
            } catch (Throwable th4) {
                context.setTowerDataMode(towerDataMode);
                throw th4;
            }
        }
        Pair pairReduceCandidates$default = reduceCandidates$default(this, candidateCollectorRunResolver, null, 2, null);
        Set set = (Set) pairReduceCandidates$default.component1();
        CandidateApplicability candidateApplicability = (CandidateApplicability) pairReduceCandidates$default.component2();
        FirExpression explicitReceiver = expression.getExplicitReceiver();
        FirExpression firExpressionUnwrapSmartcastExpression = explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null;
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier != null) {
            if (set.isEmpty()) {
                z = true;
            } else {
                Set set2 = set;
                if (!(set2 instanceof Collection) || !set2.isEmpty()) {
                    Iterator it = set2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!((Candidate) it.next()).getIsFromCompanionObjectTypeScope()) {
                                z = true;
                            }
                        }
                    }
                }
                z = false;
            }
            ResolveUtilsKt.unsetResolvedToCompanionIf(firResolvedQualifier, z);
        }
        if (!set.isEmpty()) {
            Set set3 = set;
            if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                Iterator it2 = set3.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!((Candidate) it2.next()).isSuccessful()) {
                        }
                    }
                }
            }
            if (set.size() <= 1) {
                Candidate candidate = (Candidate) CollectionsKt.single(set3);
                candidate.updateSourcesOfReceivers();
                csBuilder.replaceContentWith(candidate.getSystem().currentStorage());
                resolvedCallableReferenceAtom.initializeResultingReference(createResolvedNamedReference$default(this, calleeReference, callInfoCreateCallableReferencesInfoForLHS.getName(), callInfoCreateCallableReferencesInfoForLHS, set, candidateApplicability, null, false, null, null, 416, null));
                resolvedCallableReferenceAtom.setResultingTypeForCallableReference(candidate.getResultingTypeForCallableReference());
                return TuplesKt.to(candidateApplicability, Boolean.TRUE);
            }
            if (!resolvedCallableReferenceAtom.isPostponedBecauseOfAmbiguity()) {
                resolvedCallableReferenceAtom.setState(ConeResolvedCallableReferenceAtom.State.POSTPONED_BECAUSE_OF_AMBIGUITY);
                return TuplesKt.to(candidateApplicability, Boolean.TRUE);
            }
            Name name = callInfoCreateCallableReferencesInfoForLHS.getName();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set3, 10)), 16));
            for (Object obj : set3) {
                linkedHashMap.put(obj, null);
            }
            resolvedCallableReferenceAtom.initializeResultingReference(buildReferenceWithErrorCandidate(callInfoCreateCallableReferencesInfoForLHS, new ConeAmbiguityError(name, candidateApplicability, linkedHashMap), calleeReference.getSource()));
            return TuplesKt.to(candidateApplicability, Boolean.FALSE);
        }
        if (candidateApplicability == CandidateApplicability.K2_UNSUPPORTED) {
            Candidate candidate2 = (Candidate) CollectionsKt.firstOrNull(set);
            ResolutionDiagnostic resolutionDiagnostic = (candidate2 == null || (diagnostics = candidate2.getDiagnostics()) == null) ? null : (ResolutionDiagnostic) CollectionsKt.firstOrNull(diagnostics);
            Unsupported unsupported = resolutionDiagnostic instanceof Unsupported ? (Unsupported) resolutionDiagnostic : null;
            if (unsupported == null || (message = unsupported.getMessage()) == null) {
                message = Argument.Delimiters.none;
            }
            coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeUnsupported(message, unsupported != null ? unsupported.getSource() : null);
        } else if (set.size() > 1) {
            Set set4 = set;
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set4, 10)), 16));
            for (Object obj2 : set4) {
                Candidate candidate3 = (Candidate) obj2;
                linkedHashMap2.put(obj2, ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate3.getApplicability(), candidate3));
            }
            coneDiagnosticCreateConeDiagnosticForCandidateWithError = new ConeAmbiguityError(callInfoCreateCallableReferencesInfoForLHS.getName(), candidateApplicability, linkedHashMap2);
        } else {
            coneDiagnosticCreateConeDiagnosticForCandidateWithError = set.size() == 1 ? ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidateApplicability, (Candidate) CollectionsKt.single(set)) : new ConeUnresolvedReferenceError(callInfoCreateCallableReferencesInfoForLHS.getName());
        }
        resolvedCallableReferenceAtom.initializeResultingReference(buildReferenceWithErrorCandidate(callInfoCreateCallableReferencesInfoForLHS, coneDiagnosticCreateConeDiagnosticForCandidateWithError, calleeReference.getSource()));
        return TuplesKt.to(candidateApplicability, Boolean.FALSE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirDelegatedConstructorCall resolveDelegatingConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ConeClassLikeType constructedType, FirClassSymbol<?> derivedClass) throws UninitializedPropertyAccessException {
        delegatedConstructorCall.getClass();
        derivedClass.getClass();
        CallInfo callInfoCallInfoForDelegatingConstructorCall = callInfoForDelegatingConstructorCall(delegatedConstructorCall, constructedType);
        this.towerResolver.reset();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer = null;
        if (constructedType != null) {
            FirTowerResolver firTowerResolver = this.towerResolver;
            FirExpressionsResolveTransformer firExpressionsResolveTransformer2 = this.transformer;
            if (firExpressionsResolveTransformer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transformer");
            } else {
                firExpressionsResolveTransformer = firExpressionsResolveTransformer2;
            }
            return selectDelegatingConstructorCall(delegatedConstructorCall, callInfoCallInfoForDelegatingConstructorCall.getName(), firTowerResolver.runResolverForDelegatingConstructor(callInfoCallInfoForDelegatingConstructorCall, constructedType, derivedClass, firExpressionsResolveTransformer.getTransformer().getResolutionContext()), callInfoCallInfoForDelegatingConstructorCall);
        }
        ConeSimpleDiagnostic coneSimpleDiagnostic = new ConeSimpleDiagnostic("Erroneous delegated constructor call", DiagnosticKind.UnresolvedSupertype);
        KtSourceElement source = delegatedConstructorCall.getCalleeReference().getSource();
        FirExpressionsResolveTransformer firExpressionsResolveTransformer3 = this.transformer;
        if (firExpressionsResolveTransformer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformer");
        } else {
            firExpressionsResolveTransformer = firExpressionsResolveTransformer3;
        }
        delegatedConstructorCall.replaceCalleeReference(ErrorCandidateUtilsKt.createErrorReferenceWithErrorCandidate(callInfoCallInfoForDelegatingConstructorCall, coneSimpleDiagnostic, source, firExpressionsResolveTransformer.getTransformer().getResolutionContext(), this.components.getResolutionStageRunner()));
        return delegatedConstructorCall;
    }

    public final FirExpression resolveVariableAccessAndSelectCandidate(FirQualifiedAccessExpression qualifiedAccess, boolean isUsedAsReceiver, boolean isUsedAsGetClassReceiver, FirElement callSite, ResolutionMode resolutionMode) {
        qualifiedAccess.getClass();
        callSite.getClass();
        resolutionMode.getClass();
        return resolveVariableAccessAndSelectCandidateImpl(qualifiedAccess, isUsedAsReceiver, resolutionMode, isUsedAsGetClassReceiver, callSite, new Function1() { // from class: bz4
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirCallResolver.c((Collection) obj));
            }
        });
    }

    public /* synthetic */ FirCallResolver(FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, FirTowerResolver firTowerResolver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents2;
        if ((i & 2) != 0) {
            bodyResolveTransformerComponents2 = bodyResolveTransformerComponents;
            firTowerResolver = new FirTowerResolver(bodyResolveTransformerComponents2, bodyResolveTransformerComponents.getResolutionStageRunner(), null, 4, null);
        } else {
            bodyResolveTransformerComponents2 = bodyResolveTransformerComponents;
        }
        this(bodyResolveTransformerComponents2, firTowerResolver);
    }
}
