package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.ArrayOfUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousFunctionTypeKinds;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirAbstractResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedReifiedParameterReferenceBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReturnExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSmartCastExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirUnitExpression;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedErrorReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.TypeParameterAsExpression;
import org.jetbrains.kotlin.fir.resolve.calls.VisibilityUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.dfa.Flow;
import org.jetbrains.kotlin.fir.resolve.dfa.SyntheticVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FirAnonymousFunctionReturnExpressionInfo;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeHiddenCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNoCompanionObject;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConePlaceholderProjectionInQualifierResolution;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterInQualifiedAccess;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractSimpleImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractStarImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultSimpleImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.ForbiddenNamedArgumentsTarget;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.SmartcastStability;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a4\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004H\u0000\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\b\u001a\u00020\t\u001a\f\u0010\u0010\u001a\u00020\u0001*\u00020\rH\u0002\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0005\u001a\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u001a\u001e\u0010\u0017\u001a\u00020\u0018*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u001a>\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001b\u001a \u0010\u001f\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0001\u001ar\u0010!\u001a\u00020\"*\u00020#2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010\"2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u001b2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u000e\b\u0002\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u001b2\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u001b2\n\b\u0002\u00100\u001a\u0004\u0018\u000101\u001a~\u0010!\u001a\u00020\"*\u00020#2\f\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010\u001b2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u001b2\b\u0010(\u001a\u0004\u0018\u00010\"2\b\u00100\u001a\u0004\u0018\u000101\u001a\u0012\u00105\u001a\u00020\u000f*\u00020\"2\u0006\u00106\u001a\u00020\u0001\u001a\u001e\u00107\u001a\u00020\"*\u0002082\u0006\u00109\u001a\u00020#2\b\u0010:\u001a\u0004\u0018\u00010'H\u0000\u001a\u0012\u0010;\u001a\u00020\u000f*\u00020\"2\u0006\u0010<\u001a\u00020#\u001a\u0010\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0000\u001a*\u0010@\u001a\u0004\u0018\u00010\u00072\u0006\u0010A\u001a\u00020B2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0000\u001a!\u0010G\u001a\u00020\u0007\"\b\b\u0000\u0010H*\u00020I*\u00020#2\u0006\u0010J\u001a\u0002HH¢\u0006\u0002\u0010K\u001a\u0012\u0010G\u001a\u00020\u0007*\u00020#2\u0006\u0010L\u001a\u00020M\u001a\u0018\u0010N\u001a\u00020\u0007*\u00020#2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030OH\u0002\u001a\u001a\u0010R\u001a\u00020\u0005*\u00020#2\u0006\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020U\u001a\u0012\u0010R\u001a\u00020\u0005*\u00020#2\u0006\u0010S\u001a\u00020\u0005\u001a\"\u0010V\u001a\u00020\u000f*\u00020W2\u0006\u0010X\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010E\u001a\u00020F\u001a\u001a\u0010Y\u001a\u00020\u000f*\u00020Z2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010E\u001a\u00020F\u001a\u0014\u0010[\u001a\u0004\u0018\u000108*\u00020/2\u0006\u0010\b\u001a\u00020\t\u001a\u0012\u0010\\\u001a\u00020\u0007*\u00020#2\u0006\u0010]\u001a\u00020^\u001a\u0012\u0010\\\u001a\u00020\u0007*\u00020\u00072\u0006\u0010]\u001a\u00020^\u001a\f\u0010_\u001a\u0004\u0018\u00010`*\u00020a\u001a\f\u0010b\u001a\u00020\u0001*\u00020\u0014H\u0000\u001a\u000e\u0010c\u001a\u0004\u0018\u00010d*\u00020\u0014H\u0002\u001a\u0018\u0010e\u001a\u0004\u0018\u00010d*\u00020\u00142\b\u0010f\u001a\u0004\u0018\u00010gH\u0000\u001a\u001a\u0010h\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030i2\b\u0010f\u001a\u0004\u0018\u00010gH\u0002\u001a\u001d\u0010j\u001a\u00020\u0001*\u0004\u0018\u00010\u0005\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\b\u0003\u0010\u0000\u001a\u0016\u0010k\u001a\u00020,2\u0006\u0010l\u001a\u00020m2\u0006\u0010]\u001a\u00020^\u001a\u0012\u0010n\u001a\u00020o*\u00020p2\u0006\u0010+\u001a\u00020,\u001a \u0010s\u001a\u00020t2\b\u0010:\u001a\u0004\u0018\u00010'2\u0006\u0010u\u001a\u00020v2\u0006\u0010]\u001a\u00020^\u001a\u001f\u0010\u0084\u0001\u001a\u00020\u000f*\u00030\u0085\u00012\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00052\u0006\u0010]\u001a\u00020^\u001a\u000e\u0010\u0087\u0001\u001a\u0004\u0018\u000101*\u00030\u0088\u0001\u001a\u0014\u0010\u0089\u0001\u001a\u00020\u0001*\u00030\u008a\u00012\u0006\u0010\b\u001a\u00020\t\u001a\u0011\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001*\u00030\u008a\u0001H\u0002\"\u0018\u0010P\u001a\u00020\u0001*\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010Q\"\u0015\u0010q\u001a\u00020\u0001*\u00020^8F¢\u0006\u0006\u001a\u0004\bq\u0010r\"\u0015\u0010w\u001a\u00020x*\u00020y8F¢\u0006\u0006\u001a\u0004\bz\u0010{\"\u0015\u0010|\u001a\u00020v*\u00020}8F¢\u0006\u0006\u001a\u0004\b~\u0010\u007f\"\u001f\u0010\u0080\u0001\u001a\b\u0012\u0002\b\u0003\u0018\u00010O*\u00030\u0081\u00018F¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001¨\u0006\u008d\u0001"}, d2 = {"lambdaWithExplicitEmptyReturns", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "returnStatements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "expectedReturnType", "isPassedAsFunctionArgument", "returnExpressions", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;", "addReturnToLastStatementIfNeeded", Argument.Delimiters.none, "isExplicitEmptyReturn", "isImplicitUnitForEmptyLambda", "constructFunctionType", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "kind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "constructFunctionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "createFunctionType", "parameters", Argument.Delimiters.none, "receiverType", "rawReturnType", "contextParameters", "createKPropertyType", "isMutable", "buildResolvedQualifierForClass", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "sourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "explicitParent", "typeArgumentsForQualifier", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "nonFatalDiagnostics", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassName", "unsetResolvedToCompanionIf", "condition", "toImplicitResolvedQualifierReceiver", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "bodyResolveComponents", "source", "setTypeOfQualifier", "components", "typeForReifiedParameterReference", "parameterReferenceBuilder", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirResolvedReifiedParameterReferenceBuilder;", "typeForQualifierByDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "typeFromCallee", "T", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "access", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "typeFromSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isKindOfNothing", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "transformExpressionUsingSmartcastInfo", "expression", "smartcastStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer$SmartCastStatement;", "propagateTypeFromOriginalReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "nullableReceiverExpression", "propagateTypeFromQualifiedAccessAfterNullCheck", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "getCorrespondingClassSymbolOrNull", "initialTypeOfCandidate", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getContainingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "areNamedArgumentsForbiddenIgnoringOverridden", "forbiddenNamedArgumentsTargetOrNullIgnoringOverridden", "Lorg/jetbrains/kotlin/resolve/ForbiddenNamedArgumentsTarget;", "forbiddenNamedArgumentsTargetOrNull", "originScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "hasOverrideThatAllowsNamedArguments", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "isIntegerLiteralOrOperatorCall", "createConeDiagnosticForCandidateWithError", "applicability", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "toErrorReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "isExplicitBackingFieldAccess", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)Z", "buildExplicitBackingFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "defaultType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getDefaultType", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "shortName", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "getShortName", "(Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;)Lorg/jetbrains/kotlin/name/Name;", "referencedMemberSymbol", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "getReferencedMemberSymbol", "(Lorg/jetbrains/kotlin/fir/references/FirThisReference;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "replaceExplicitReceiverIfNecessary", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "dispatchReceiver", "toResolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "isArrayOfCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getOriginalFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolveUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CandidateApplicability.values().length];
            try {
                iArr[CandidateApplicability.HIDDEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CandidateApplicability.K2_VISIBILITY_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CandidateApplicability.INAPPLICABLE_WRONG_RECEIVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CandidateApplicability.K2_NO_COMPANION_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (areNamedArgumentsForbiddenIgnoringOverridden((FirFunction) firNamedFunctionSymbol.getFir())) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    public static final void addReturnToLastStatementIfNeeded(FirAnonymousFunction firAnonymousFunction, FirSession firSession) {
        FirBlock body;
        firAnonymousFunction.getClass();
        firSession.getClass();
        if (ConeBuiltinTypeUtilsKt.isUnit(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firAnonymousFunction.getReturnTypeRef()), firSession, (Function1) null, 2, (Object) null)) || (body = firAnonymousFunction.getBody()) == null) {
            return;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(body.getStatements());
        final FirExpression firExpression = objLastOrNull instanceof FirExpression ? (FirExpression) objLastOrNull : null;
        if (firExpression == null || (firExpression instanceof FirReturnExpression) || ConeBuiltinTypeUtilsKt.isNothing(FirTypeUtilsKt.getResolvedType(body))) {
            return;
        }
        FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, firAnonymousFunction.getIsLambda());
        firFunctionTarget.bind(firAnonymousFunction);
        FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
        KtSourceElement source = firExpression.getSource();
        if (source == null && (source = body.getSource()) == null) {
            source = firAnonymousFunction.getSource();
        }
        firReturnExpressionBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReturn.FromLastStatement.INSTANCE, null, 2, null) : null);
        firReturnExpressionBuilder.setResult(firExpression);
        firReturnExpressionBuilder.setTarget(firFunctionTarget);
        final FirReturnExpression firReturnExpressionBuild = firReturnExpressionBuilder.mo288build();
        body.transformStatements(new FirTransformer() { // from class: org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt.addReturnToLastStatementIfNeeded.1
            @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
            public <E extends FirElement> E transformElement(E element, Void data) {
                element.getClass();
                if (!Intrinsics.areEqual(element, firExpression)) {
                    return element;
                }
                FirReturnExpression firReturnExpression = firReturnExpressionBuild;
                firReturnExpression.getClass();
                return firReturnExpression;
            }
        }, null);
    }

    public static final boolean areNamedArgumentsForbiddenIgnoringOverridden(FirFunction firFunction) {
        firFunction.getClass();
        return forbiddenNamedArgumentsTargetOrNullIgnoringOverridden(firFunction) != null;
    }

    public static final FirPropertyWithExplicitBackingFieldResolvedNamedReference buildExplicitBackingFieldReference(KtSourceElement ktSourceElement, Name name, Candidate candidate) {
        name.getClass();
        candidate.getClass();
        FirPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder = new FirPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder();
        firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.setSource(ktSourceElement);
        firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.setName(name);
        firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.setResolvedSymbol(candidate.getSymbol());
        firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.setHasVisibleBackingField(candidate.getHasVisibleBackingField());
        FirScope originScope = candidate.getOriginScope();
        firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.setResolvedSymbolOrigin(originScope != null ? toResolvedSymbolOrigin(originScope) : null);
        return firPropertyWithExplicitBackingFieldResolvedNamedReferenceBuilder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static final FirResolvedQualifier buildResolvedQualifierForClass(BodyResolveComponents bodyResolveComponents, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement, FqName fqName, FqName fqName2, List<? extends FirTypeProjection> list, ConeDiagnostic coneDiagnostic, List<? extends ConeDiagnostic> list2, List<? extends FirAnnotation> list3, FirResolvedQualifier firResolvedQualifier, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        FirAbstractResolvedQualifierBuilder firResolvedQualifierBuilder;
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        bodyResolveComponents.getClass();
        fqName.getClass();
        list.getClass();
        list3.getClass();
        if (coneDiagnostic == null) {
            firResolvedQualifierBuilder = new FirResolvedQualifierBuilder();
        } else {
            FirErrorResolvedQualifierBuilder firErrorResolvedQualifierBuilder = new FirErrorResolvedQualifierBuilder();
            firErrorResolvedQualifierBuilder.setDiagnostic(coneDiagnostic);
            firResolvedQualifierBuilder = firErrorResolvedQualifierBuilder;
        }
        firResolvedQualifierBuilder.setSource(ktSourceElement);
        firResolvedQualifierBuilder.setPackageFqName(fqName);
        firResolvedQualifierBuilder.setRelativeClassFqName(fqName2);
        List<FirTypeProjection> typeArguments = firResolvedQualifierBuilder.getTypeArguments();
        Iterator<T> it = list.iterator();
        while (true) {
            resolvedCompanionObjectSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirTypeProjection firTypeProjectionBuild = (FirTypeProjection) it.next();
            if (firTypeProjectionBuild instanceof FirPlaceholderProjection) {
                FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
                FirPlaceholderProjection firPlaceholderProjection = (FirPlaceholderProjection) firTypeProjectionBuild;
                firTypeProjectionWithVarianceBuilder.setSource(firPlaceholderProjection.getSource());
                firTypeProjectionWithVarianceBuilder.setVariance(Variance.INVARIANT);
                ConeErrorType coneErrorType = new ConeErrorType(ConePlaceholderProjectionInQualifierResolution.INSTANCE, false, null, null, null, null, null, 126, null);
                KtSourceElement source = firPlaceholderProjection.getSource();
                firTypeProjectionWithVarianceBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneErrorType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null, null, 2, null));
                firTypeProjectionBuild = firTypeProjectionWithVarianceBuilder.build();
            }
            typeArguments.add(firTypeProjectionBuild);
        }
        firResolvedQualifierBuilder.setSymbol(firClassLikeSymbol);
        if (list2 != null) {
            firResolvedQualifierBuilder.getNonFatalDiagnostics().addAll(list2);
        }
        firResolvedQualifierBuilder.getAnnotations().addAll(list3);
        firResolvedQualifierBuilder.setExplicitParent(firResolvedQualifier);
        if (firClassLikeSymbol != null && (firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(bodyResolveComponents, firClassLikeSymbol)) != null) {
            resolvedCompanionObjectSymbol = firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol();
        }
        firResolvedQualifierBuilder.setResolvedToCompanionObject(resolvedCompanionObjectSymbol != null);
        firResolvedQualifierBuilder.setResolvedSymbolOrigin(firResolvedSymbolOrigin);
        FirResolvedQualifier firResolvedQualifierBuild = firResolvedQualifierBuilder.build();
        if (firClassLikeSymbol == null || !((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal()) {
            setTypeOfQualifier(firResolvedQualifierBuild, bodyResolveComponents);
            return firResolvedQualifierBuild;
        }
        ConeKotlinType coneKotlinTypeTypeForQualifierByDeclaration = typeForQualifierByDeclaration(firClassLikeSymbol.getFir(), bodyResolveComponents.getSession(), firResolvedQualifierBuild, bodyResolveComponents.getFile());
        if (coneKotlinTypeTypeForQualifierByDeclaration != null) {
            firResolvedQualifierBuild.replaceCanBeValue(true);
        } else {
            coneKotlinTypeTypeForQualifierByDeclaration = bodyResolveComponents.getSession().getBuiltinTypes().getUnitType().getConeType();
        }
        firResolvedQualifierBuild.replaceConeTypeOrNull(coneKotlinTypeTypeForQualifierByDeclaration);
        return firResolvedQualifierBuild;
    }

    public static /* synthetic */ FirResolvedQualifier buildResolvedQualifierForClass$default(BodyResolveComponents bodyResolveComponents, FirClassLikeSymbol firClassLikeSymbol, KtSourceElement ktSourceElement, FirResolvedQualifier firResolvedQualifier, List list, ConeDiagnostic coneDiagnostic, List list2, List list3, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return buildResolvedQualifierForClass(bodyResolveComponents, firClassLikeSymbol, ktSourceElement, firResolvedQualifier, list, (i & 16) != 0 ? null : coneDiagnostic, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? CollectionsKt.emptyList() : list3, (i & 128) != 0 ? null : firResolvedSymbolOrigin);
    }

    public static final ConeKotlinType computeReturnType(FirAnonymousFunction firAnonymousFunction, FirSession firSession, ConeKotlinType coneKotlinType, boolean z, Collection<FirAnonymousFunctionReturnExpressionInfo> collection) {
        firAnonymousFunction.getClass();
        firSession.getClass();
        collection.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = coneKotlinType != null ? TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null) : null;
        ConeKotlinType coneType = firSession.getBuiltinTypes().getUnitType().getConeType();
        if (firAnonymousFunction.getIsLambda()) {
            if (coneKotlinTypeFullyExpandedType$default != null && TypeUtilsKt.isUnitOrFlexibleUnit(coneKotlinTypeFullyExpandedType$default)) {
                return coneType;
            }
            Collection<FirAnonymousFunctionReturnExpressionInfo> collection2 = collection;
            if (!collection2.isEmpty()) {
                Iterator<T> it = collection2.iterator();
                while (it.hasNext()) {
                    if (isExplicitEmptyReturn((FirAnonymousFunctionReturnExpressionInfo) it.next())) {
                        return coneKotlinTypeFullyExpandedType$default != null ? coneKotlinType : coneType;
                    }
                }
            }
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        Collection<FirAnonymousFunctionReturnExpressionInfo> collection3 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection3, 10));
        Iterator<T> it2 = collection3.iterator();
        while (it2.hasNext()) {
            arrayList.add(FirTypeUtilsKt.getResolvedType(((FirAnonymousFunctionReturnExpressionInfo) it2.next()).getExpression()));
        }
        ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
        if (coneKotlinTypeCommonSuperTypeOrNull != null) {
            coneType = coneKotlinTypeCommonSuperTypeOrNull;
        }
        if ((coneKotlinTypeFullyExpandedType$default instanceof ConeErrorType) && (coneType instanceof ConeErrorType)) {
            return coneType;
        }
        if (!firAnonymousFunction.getIsLambda() && (firAnonymousFunction.getReturnTypeRef() instanceof FirResolvedTypeRef)) {
            KtSourceElement source = firAnonymousFunction.getReturnTypeRef().getSource();
            if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
                if (coneKotlinType == FirTypeUtilsKt.getConeType(firAnonymousFunction.getReturnTypeRef())) {
                    return FirTypeUtilsKt.getConeType(firAnonymousFunction.getReturnTypeRef());
                }
                StringBuilder sb = new StringBuilder("Found ");
                sb.append(coneKotlinType);
                ode.a(sb, " and ", FirTypeUtilsKt.getConeType(firAnonymousFunction.getReturnTypeRef()));
                return null;
            }
        }
        return (!z || ConeBuiltinTypeUtilsKt.isUnit(TypeExpansionUtilsKt.fullyExpandedType$default(coneType, firSession, (Function1) null, 2, (Object) null)) || coneKotlinType == null) ? coneType : coneKotlinType;
    }

    public static final ConeLookupTagBasedType constructFunctionType(FirFunction firFunction, FunctionTypeKind functionTypeKind) {
        FirReceiverParameter receiverParameter;
        firFunction.getClass();
        if (firFunction instanceof FirNamedFunction) {
            receiverParameter = ((FirNamedFunction) firFunction).getReceiverParameter();
        } else {
            receiverParameter = firFunction instanceof FirAnonymousFunction ? ((FirAnonymousFunction) firFunction).getReceiverParameter() : null;
        }
        FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef returnTypeRef = ((FirValueParameter) it.next()).getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                coneType = null;
            }
            if (coneType == null) {
                coneType = new ConeErrorType(new ConeSimpleDiagnostic("No type for parameter", DiagnosticKind.ValueParameterWithNoTypeAnnotation), false, null, null, null, null, null, 126, null);
            }
            arrayList.add(coneType);
        }
        ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef());
        FunctionTypeKind functionTypeKind2 = functionTypeKind == null ? FunctionTypeKind.Function.INSTANCE : functionTypeKind;
        ConeKotlinType coneType3 = typeRef != null ? FirTypeUtilsKt.getConeType(typeRef) : null;
        List<FirValueParameter> contextParameters = firFunction.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it2 = contextParameters.iterator();
        while (it2.hasNext()) {
            arrayList2.add(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef()));
        }
        return createFunctionType(functionTypeKind2, arrayList, coneType3, coneType2, arrayList2);
    }

    public static /* synthetic */ ConeLookupTagBasedType constructFunctionType$default(FirFunction firFunction, FunctionTypeKind functionTypeKind, int i, Object obj) {
        if ((i & 1) != 0) {
            functionTypeKind = null;
        }
        return constructFunctionType(firFunction, functionTypeKind);
    }

    public static final FirResolvedTypeRef constructFunctionTypeRef(FirAnonymousFunction firAnonymousFunction, FirSession firSession, FunctionTypeKind functionTypeKind) {
        FunctionTypeKind.Function functionNonReflectKind;
        ConeAmbiguousFunctionTypeKinds coneAmbiguousFunctionTypeKinds;
        firAnonymousFunction.getClass();
        firSession.getClass();
        List<FunctionTypeKind> listExtractAllSpecialKindsForFunction = FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession).extractAllSpecialKindsForFunction(firAnonymousFunction.getSymbol());
        int size = listExtractAllSpecialKindsForFunction.size();
        if (size == 0) {
            functionNonReflectKind = null;
            coneAmbiguousFunctionTypeKinds = null;
        } else if (size != 1) {
            coneAmbiguousFunctionTypeKinds = new ConeAmbiguousFunctionTypeKinds(listExtractAllSpecialKindsForFunction);
            functionNonReflectKind = FunctionTypeKind.Function.INSTANCE;
        } else {
            functionNonReflectKind = (FunctionTypeKind) CollectionsKt.single(listExtractAllSpecialKindsForFunction);
            coneAmbiguousFunctionTypeKinds = null;
        }
        if (firAnonymousFunction.getIsLambda() && functionNonReflectKind == null) {
            functionNonReflectKind = functionTypeKind != null ? functionTypeKind.nonReflectKind() : null;
        }
        ConeLookupTagBasedType coneLookupTagBasedTypeConstructFunctionType = constructFunctionType(firAnonymousFunction, functionNonReflectKind);
        KtSourceElement source = firAnonymousFunction.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null;
        if (coneAmbiguousFunctionTypeKinds == null) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
            firResolvedTypeRefBuilder.setConeType(coneLookupTagBasedTypeConstructFunctionType);
            return firResolvedTypeRefBuilder.build();
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
        firErrorTypeRefBuilder.setConeType(coneLookupTagBasedTypeConstructFunctionType);
        firErrorTypeRefBuilder.setDiagnostic(coneAmbiguousFunctionTypeKinds);
        return firErrorTypeRefBuilder.build();
    }

    public static /* synthetic */ FirResolvedTypeRef constructFunctionTypeRef$default(FirAnonymousFunction firAnonymousFunction, FirSession firSession, FunctionTypeKind functionTypeKind, int i, Object obj) {
        if ((i & 2) != 0) {
            functionTypeKind = null;
        }
        return constructFunctionTypeRef(firAnonymousFunction, firSession, functionTypeKind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeDiagnostic createConeDiagnosticForCandidateWithError(CandidateApplicability candidateApplicability, Candidate candidate) {
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo;
        FirTypeAliasSymbol typeAliasSymbol;
        candidateApplicability.getClass();
        candidate.getClass();
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        int i = WhenMappings.$EnumSwitchMapping$0[candidateApplicability.ordinal()];
        if (i == 1) {
            return new ConeHiddenCandidateError(candidate);
        }
        if (i != 2) {
            if (i == 3) {
                return new ConeInapplicableWrongReceiver(CollectionsKt.listOf(candidate));
            }
            if (i == 4) {
                return new ConeNoCompanionObject(candidate);
            }
            if (!candidate.getDiagnostics().contains(TypeParameterAsExpression.INSTANCE)) {
                return new ConeInapplicableCandidateError(candidateApplicability, candidate);
            }
            symbol.getClass();
            return new ConeTypeParameterInQualifiedAccess((FirTypeParameterSymbol) symbol);
        }
        FirSession session = candidate.getCallInfo().getSession();
        Object obj = null;
        FirConstructorSymbol firConstructorSymbol = symbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) symbol : null;
        if (firConstructorSymbol != null && (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructorSymbol)) != null && (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) != null && !VisibilityUtilsKt.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(session), (FirMemberDeclaration) typeAliasSymbol.getFir(), candidate, false, 4, null)) {
            return new ConeVisibilityError(typeAliasSymbol);
        }
        FirDeclaration fir = symbol.getFir();
        if (fir instanceof FirMemberDeclaration) {
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) fir;
            if (VisibilityUtilsKt.isVisible(FirVisibilityCheckerKt.getVisibilityChecker(session), firMemberDeclaration, candidate, true)) {
                ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
                Sequence sequenceParentDeclarationSequence$default = FirVisibilityCheckerKt.parentDeclarationSequence$default(firMemberDeclaration, session, dispatchReceiver != null ? dispatchReceiver.getExpression() : null, candidate.getCallInfo().getContainingDeclarations(), null, 8, null);
                if (sequenceParentDeclarationSequence$default != null) {
                    for (Object obj2 : sequenceParentDeclarationSequence$default) {
                        FirSession firSession = session;
                        session = firSession;
                        if (!FirVisibilityChecker.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(firSession), (FirClassLikeDeclaration) obj2, firSession, candidate.getCallInfo().getContainingFile(), candidate.getCallInfo().getContainingDeclarations(), null, false, null, true, null, 352, null)) {
                            obj = obj2;
                            break;
                        }
                    }
                    FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) obj;
                    if (firClassLikeDeclaration != null) {
                        return new ConeVisibilityError(firClassLikeDeclaration.getSymbol());
                    }
                }
            }
        }
        return new ConeVisibilityError(symbol);
    }

    public static final ConeLookupTagBasedType createFunctionType(FunctionTypeKind functionTypeKind, List<? extends ConeKotlinType> list, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, List<? extends ConeKotlinType> list2) {
        ConeAttributes withExtensionFunctionType;
        functionTypeKind.getClass();
        list.getClass();
        coneKotlinType2.getClass();
        list2.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List<? extends ConeKotlinType> list3 = list2;
        listCreateListBuilder.addAll(list3);
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(listCreateListBuilder, coneKotlinType);
        listCreateListBuilder.addAll(list);
        listCreateListBuilder.add(coneKotlinType2);
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        ClassId classId = new ClassId(functionTypeKind.getPackageFqName(), functionTypeKind.numberedClassName(listBuild.size() - 1));
        if (list3.isEmpty()) {
            withExtensionFunctionType = coneKotlinType != null ? ConeAttributes.INSTANCE.getWithExtensionFunctionType() : ConeAttributes.INSTANCE.getEmpty();
        } else {
            ConeAttributes.Companion companion = ConeAttributes.INSTANCE;
            List listCreateListBuilder2 = CollectionsKt.createListBuilder();
            listCreateListBuilder2.add(new CompilerConeAttributes.ContextFunctionTypeParams(list2.size()));
            if (coneKotlinType != null) {
                listCreateListBuilder2.add(CompilerConeAttributes.ExtensionFunctionType.INSTANCE);
            }
            withExtensionFunctionType = companion.create(CollectionsKt.build(listCreateListBuilder2));
        }
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(classId), (ConeTypeProjection[]) listBuild.toArray(new ConeKotlinType[0]), false, withExtensionFunctionType);
    }

    public static /* synthetic */ ConeLookupTagBasedType createFunctionType$default(FunctionTypeKind functionTypeKind, List list, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, List list2, int i, Object obj) {
        if ((i & 16) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return createFunctionType(functionTypeKind, list, coneKotlinType, coneKotlinType2, list2);
    }

    public static final ConeLookupTagBasedType createKPropertyType(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        coneKotlinType2.getClass();
        List listListOf = coneKotlinType != null ? CollectionsKt.listOf(new ConeKotlinType[]{coneKotlinType, coneKotlinType2}) : CollectionsKt.listOf(coneKotlinType2);
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        StringBuilder sb = new StringBuilder("K");
        sb.append(z ? "Mutable" : Argument.Delimiters.none);
        sb.append("Property");
        sb.append(listListOf.size() - 1);
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(standardClassIds.reflectByName(sb.toString())), (ConeTypeProjection[]) listListOf.toArray(new ConeKotlinType[0]), false, null, 8, null);
    }

    public static final ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTargetOrNull(FirFunction firFunction, FirTypeScope firTypeScope) {
        FirFunction firFunction2;
        firFunction.getClass();
        if (firFunction.getStatus().getHasStableParameterNames()) {
            return null;
        }
        FirDeclarationOrigin origin = firFunction.getOrigin();
        if (Intrinsics.areEqual(origin, FirDeclarationOrigin.ImportedFromObjectOrStatic.INSTANCE)) {
            ImportedFromObjectOrStaticData importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firFunction);
            if (importedFromObjectOrStaticData == null || (firFunction2 = (FirFunction) importedFromObjectOrStaticData.getOriginal()) == null) {
                return null;
            }
            return forbiddenNamedArgumentsTargetOrNullIgnoringOverridden(firFunction2);
        }
        if (!Intrinsics.areEqual(origin, FirDeclarationOrigin.IntersectionOverride.INSTANCE) && !(origin instanceof FirDeclarationOrigin.SubstitutionOverride) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Delegated.INSTANCE)) {
            if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTarget = ForbiddenNamedArgumentsTarget.NON_KOTLIN_FUNCTION;
                if (hasOverrideThatAllowsNamedArguments(firFunction.getSymbol(), firTypeScope)) {
                    return null;
                }
                return forbiddenNamedArgumentsTarget;
            }
            if (Intrinsics.areEqual(origin, FirDeclarationOrigin.BuiltIns.INSTANCE) || Intrinsics.areEqual(origin, FirDeclarationOrigin.BuiltInsFallback.INSTANCE)) {
                return ForbiddenNamedArgumentsTarget.INVOKE_ON_FUNCTION_TYPE;
            }
            if (origin instanceof FirDeclarationOrigin.Plugin) {
                return null;
            }
            return ForbiddenNamedArgumentsTarget.NON_KOTLIN_FUNCTION;
        }
        FirCallableDeclaration firCallableDeclaration = firFunction;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(firCallableDeclaration);
                    originalForSubstitutionOverrideAttr = delegatedWrapperData != null ? delegatedWrapperData.getWrapped() : null;
                }
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTargetForbiddenNamedArgumentsTargetOrNullIgnoringOverridden = forbiddenNamedArgumentsTargetOrNullIgnoringOverridden((FirFunction) firCallableDeclaration);
        if (forbiddenNamedArgumentsTargetForbiddenNamedArgumentsTargetOrNullIgnoringOverridden == null || hasOverrideThatAllowsNamedArguments(firFunction.getSymbol(), firTypeScope)) {
            return null;
        }
        return forbiddenNamedArgumentsTargetForbiddenNamedArgumentsTargetOrNullIgnoringOverridden;
    }

    private static final ForbiddenNamedArgumentsTarget forbiddenNamedArgumentsTargetOrNullIgnoringOverridden(FirFunction firFunction) {
        return forbiddenNamedArgumentsTargetOrNull(firFunction, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass getContainingClass(FirCallableDeclaration firCallableDeclaration) {
        FirRegularClassSymbol regularClassSymbol;
        firCallableDeclaration.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration);
        if (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firCallableDeclaration.getModuleData().getSession())) == null) {
            return null;
        }
        return (FirRegularClass) regularClassSymbol.getFir();
    }

    public static final FirRegularClassSymbol getCorrespondingClassSymbolOrNull(FirAnnotation firAnnotation, FirSession firSession) {
        firAnnotation.getClass();
        firSession.getClass();
        return ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef()), firSession, (Function1) null, 2, (Object) null), firSession);
    }

    public static final ConeTypeParameterType getDefaultType(FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        return new ConeTypeParameterTypeImpl(firTypeParameterSymbol.getLookupTag(), false, null, 4, null);
    }

    private static final FirNamedFunctionSymbol getOriginalFunction(FirFunctionCall firFunctionCall) {
        FirBasedSymbol<?> candidateSymbol;
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if (calleeReference instanceof FirResolvedErrorReference) {
            candidateSymbol = ((FirResolvedErrorReference) calleeReference).getResolvedSymbol();
        } else if (calleeReference instanceof FirResolvedNamedReference) {
            candidateSymbol = ((FirResolvedNamedReference) calleeReference).getResolvedSymbol();
        } else {
            candidateSymbol = calleeReference instanceof FirNamedReferenceWithCandidate ? ((FirNamedReferenceWithCandidate) calleeReference).getCandidateSymbol() : null;
        }
        if (candidateSymbol instanceof FirNamedFunctionSymbol) {
            return (FirNamedFunctionSymbol) candidateSymbol;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Type inference failed for: r3v3, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static final FirBasedSymbol<?> getReferencedMemberSymbol(FirThisReference firThisReference) throws KotlinIllegalArgumentExceptionWithAttachments {
        firThisReference.getClass();
        FirThisOwnerSymbol<?> boundSymbol = firThisReference.getBoundSymbol();
        if (boundSymbol instanceof FirReceiverParameterSymbol) {
            return ((FirReceiverParameterSymbol) boundSymbol).getContainingDeclarationSymbol();
        }
        if (boundSymbol instanceof FirClassSymbol) {
            return boundSymbol;
        }
        if (boundSymbol == null) {
            return null;
        }
        if (!(boundSymbol instanceof FirTypeParameterSymbol) && !(boundSymbol instanceof FirTypeAliasSymbol)) {
            bu8.a();
            return null;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected FirThisOwnerSymbol " + Reflection.getOrCreateKotlinClass(boundSymbol.getClass()).getSimpleName(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "FIR", ((FirClassifierSymbol) boundSymbol).getFir());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final Name getShortName(FirUserTypeRef firUserTypeRef) {
        firUserTypeRef.getClass();
        return ((FirQualifierPart) CollectionsKt.last(firUserTypeRef.getQualifier())).getName();
    }

    private static final boolean hasOverrideThatAllowsNamedArguments(FirFunctionSymbol<?> firFunctionSymbol, FirTypeScope firTypeScope) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if ((firFunctionSymbol instanceof FirNamedFunctionSymbol) && firTypeScope != null) {
            FirTypeScopeKt.processOverriddenFunctions(firTypeScope, (FirNamedFunctionSymbol) firFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: ohc
                public final Object invoke(Object obj) {
                    return ResolveUtilsKt.a(booleanRef, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        return booleanRef.element;
    }

    public static final ConeKotlinType initialTypeOfCandidate(ConeKotlinType coneKotlinType, Candidate candidate) {
        coneKotlinType.getClass();
        candidate.getClass();
        NewConstraintSystemImpl system = candidate.getSystem();
        return MarkerExtensionsKt.safeSubstitute(system.buildCurrentSubstitutor(), system, candidate.getSubstitutor().substituteOrSelf(coneKotlinType));
    }

    public static final boolean isArrayOfCall(FirFunctionCall firFunctionCall, FirSession firSession) {
        firFunctionCall.getClass();
        firSession.getClass();
        FirNamedFunctionSymbol originalFunction = getOriginalFunction(firFunctionCall);
        if (originalFunction == null) {
            return false;
        }
        return ArrayOfUtilsKt.isArrayOfFunction(originalFunction, firSession, firFunctionCall.getArgumentList());
    }

    public static final boolean isExplicitBackingFieldAccess(Candidate candidate) {
        candidate.getClass();
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirPropertySymbol firPropertySymbol = symbol instanceof FirPropertySymbol ? (FirPropertySymbol) symbol : null;
        return firPropertySymbol != null && DeclarationAttributesKt.getHasExplicitBackingField(firPropertySymbol) && Intrinsics.areEqual(candidate.getCallInfo().getCallKind(), CallKind.VariableAccess.INSTANCE);
    }

    private static final boolean isExplicitEmptyReturn(FirAnonymousFunctionReturnExpressionInfo firAnonymousFunctionReturnExpressionInfo) {
        return firAnonymousFunctionReturnExpressionInfo.isExplicit() && (firAnonymousFunctionReturnExpressionInfo.getExpression() instanceof FirUnitExpression) && !isImplicitUnitForEmptyLambda(firAnonymousFunctionReturnExpressionInfo.getExpression());
    }

    public static final boolean isImplicitUnitForEmptyLambda(FirExpression firExpression) {
        firExpression.getClass();
        KtSourceElement source = firExpression.getSource();
        return Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitUnit.ForEmptyLambda.INSTANCE);
    }

    public static final boolean isIntegerLiteralOrOperatorCall(FirExpression firExpression) {
        if (firExpression instanceof FirLiteralExpression) {
            FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firExpression;
            return Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.Int.INSTANCE) || Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.IntegerLiteral.INSTANCE) || Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.UnsignedInt.INSTANCE) || Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.UnsignedIntegerLiteral.INSTANCE);
        }
        if (firExpression instanceof FirIntegerLiteralOperatorCall) {
            return true;
        }
        if (firExpression instanceof FirNamedArgumentExpression) {
            return isIntegerLiteralOrOperatorCall(((FirNamedArgumentExpression) firExpression).getExpression());
        }
        return false;
    }

    private static final boolean isKindOfNothing(ConeKotlinType coneKotlinType) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        return ConeBuiltinTypeUtilsKt.isNothing(coneRigidTypeLowerBoundIfFlexible) || ConeBuiltinTypeUtilsKt.isNullableNothing(coneRigidTypeLowerBoundIfFlexible);
    }

    public static final boolean lambdaWithExplicitEmptyReturns(FirAnonymousFunction firAnonymousFunction, Collection<? extends FirExpression> collection) {
        firAnonymousFunction.getClass();
        collection.getClass();
        if (!firAnonymousFunction.getIsLambda()) {
            return false;
        }
        Collection<? extends FirExpression> collection2 = collection;
        if (collection2.isEmpty()) {
            return false;
        }
        for (FirExpression firExpression : collection2) {
            if (firExpression instanceof FirUnitExpression) {
                KtSourceElement source = ((FirUnitExpression) firExpression).getSource();
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitUnit.Return.INSTANCE)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:25:0x003e  */
    public static final void propagateTypeFromOriginalReceiver(FirCheckedSafeCallSubject firCheckedSafeCallSubject, FirExpression firExpression, FirSession firSession, FirFile firFile) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        FirResolvedTypeRef smartcastTypeWithoutNullableNothing;
        firCheckedSafeCallSubject.getClass();
        firExpression.getClass();
        firSession.getClass();
        firFile.getClass();
        FirSmartCastExpression firSmartCastExpression = firExpression instanceof FirSmartCastExpression ? (FirSmartCastExpression) firExpression : null;
        if (firSmartCastExpression == null) {
            resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        } else {
            if (!firSmartCastExpression.isStable()) {
                firSmartCastExpression = null;
            }
            if (firSmartCastExpression == null || (smartcastTypeWithoutNullableNothing = firSmartCastExpression.getSmartcastTypeWithoutNullableNothing()) == null) {
                resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
            } else {
                FirResolvedTypeRef firResolvedTypeRef = smartcastTypeWithoutNullableNothing instanceof FirResolvedTypeRef ? smartcastTypeWithoutNullableNothing : null;
                resolvedType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                if (resolvedType == null) {
                    resolvedType = null;
                }
                if (resolvedType == null) {
                    resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
                }
            }
        }
        ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(TypeExpansionUtilsKt.fullyExpandedType$default(resolvedType, firSession, (Function1) null, 2, (Object) null), (ConeTypeContext) TypeComponentsKt.getTypeContext(firSession), false, false, 6, (Object) null);
        firCheckedSafeCallSubject.replaceConeTypeOrNull(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(firSession);
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default, firCheckedSafeCallSubject.getSource(), firFile.getSource());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x002c  */
    public static final void propagateTypeFromQualifiedAccessAfterNullCheck(FirSafeCallExpression firSafeCallExpression, FirSession firSession, FirFile firFile) {
        ConeKotlinType coneKotlinTypeConstructClassLikeType$default;
        firSafeCallExpression.getClass();
        firSession.getClass();
        firFile.getClass();
        FirStatement selector = firSafeCallExpression.getSelector();
        if (selector instanceof FirExpression) {
            FirExpression firExpression = (FirExpression) selector;
            if (UtilsKt.isStatementLikeExpression(firExpression)) {
                coneKotlinTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getUnit(), null, false, null, 7, null);
            } else {
                coneKotlinTypeConstructClassLikeType$default = TypeUtilsKt.withNullability$default(FirTypeUtilsKt.getResolvedType(firExpression), true, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null);
            }
        } else {
            coneKotlinTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getUnit(), null, false, null, 7, null);
        }
        firSafeCallExpression.replaceConeTypeOrNull(coneKotlinTypeConstructClassLikeType$default);
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(firSession);
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordTypeResolveAsLookup(lookupTracker, coneKotlinTypeConstructClassLikeType$default, firSafeCallExpression.getSource(), firFile.getSource());
        }
    }

    public static final void replaceExplicitReceiverIfNecessary(FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpression firExpression, Candidate candidate) {
        firQualifiedAccessExpression.getClass();
        candidate.getClass();
        if (candidate.getExplicitReceiverKind() == ExplicitReceiverKind.DISPATCH_RECEIVER && candidate.isSuccessful() && firExpression != null) {
            firQualifiedAccessExpression.replaceExplicitReceiver(firExpression);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void setTypeOfQualifier(FirResolvedQualifier firResolvedQualifier, BodyResolveComponents bodyResolveComponents) {
        ConeKotlinType coneKotlinTypeTypeForQualifierByDeclaration;
        firResolvedQualifier.getClass();
        bodyResolveComponents.getClass();
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.TYPES);
            FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir();
            if ((!(firClassLikeDeclaration instanceof FirTypeAlias) || firResolvedQualifier.getTypeArguments().isEmpty()) && (coneKotlinTypeTypeForQualifierByDeclaration = typeForQualifierByDeclaration(firClassLikeDeclaration, bodyResolveComponents.getSession(), firResolvedQualifier, bodyResolveComponents.getFile())) != null) {
                firResolvedQualifier.replaceConeTypeOrNull(coneKotlinTypeTypeForQualifierByDeclaration);
                firResolvedQualifier.replaceCanBeValue(true);
                return;
            }
        }
        firResolvedQualifier.replaceConeTypeOrNull(bodyResolveComponents.getSession().getBuiltinTypes().getUnitType().getConeType());
    }

    public static final FirNamedReference toErrorReference(FirNamedReferenceWithCandidate firNamedReferenceWithCandidate, ConeDiagnostic coneDiagnostic) {
        firNamedReferenceWithCandidate.getClass();
        coneDiagnostic.getClass();
        FirBasedSymbol<?> candidateSymbol = firNamedReferenceWithCandidate.getCandidateSymbol();
        if ((candidateSymbol instanceof FirErrorPropertySymbol) || (candidateSymbol instanceof FirErrorFunctionSymbol)) {
            FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
            firErrorNamedReferenceBuilder.setSource(firNamedReferenceWithCandidate.getSource());
            firErrorNamedReferenceBuilder.setName(firNamedReferenceWithCandidate.getName());
            firErrorNamedReferenceBuilder.setDiagnostic(coneDiagnostic);
            return firErrorNamedReferenceBuilder.build();
        }
        FirResolvedErrorReferenceBuilder firResolvedErrorReferenceBuilder = new FirResolvedErrorReferenceBuilder();
        firResolvedErrorReferenceBuilder.setSource(firNamedReferenceWithCandidate.getSource());
        firResolvedErrorReferenceBuilder.setName(firNamedReferenceWithCandidate.getName());
        firResolvedErrorReferenceBuilder.setResolvedSymbol(firNamedReferenceWithCandidate.getCandidateSymbol());
        firResolvedErrorReferenceBuilder.setDiagnostic(coneDiagnostic);
        return firResolvedErrorReferenceBuilder.build();
    }

    public static final FirResolvedQualifier toImplicitResolvedQualifierReceiver(FirRegularClassSymbol firRegularClassSymbol, BodyResolveComponents bodyResolveComponents, KtSourceElement ktSourceElement) {
        firRegularClassSymbol.getClass();
        bodyResolveComponents.getClass();
        FirResolvedQualifierBuilder firResolvedQualifierBuilder = new FirResolvedQualifierBuilder();
        firResolvedQualifierBuilder.setPackageFqName(firRegularClassSymbol.getClassId().getPackageFqName());
        firResolvedQualifierBuilder.setRelativeClassFqName(firRegularClassSymbol.getClassId().getRelativeClassName());
        firResolvedQualifierBuilder.setResolvedToCompanionObject(false);
        firResolvedQualifierBuilder.setSymbol(firRegularClassSymbol);
        firResolvedQualifierBuilder.setSource(ktSourceElement);
        FirResolvedQualifier firResolvedQualifierBuild = firResolvedQualifierBuilder.mo288build();
        setTypeOfQualifier(firResolvedQualifierBuild, bodyResolveComponents);
        return firResolvedQualifierBuild;
    }

    public static final FirResolvedSymbolOrigin toResolvedSymbolOrigin(FirScope firScope) {
        firScope.getClass();
        if ((firScope instanceof FirDefaultStarImportingScope) || (firScope instanceof FirDefaultSimpleImportingScope)) {
            return FirResolvedSymbolOrigin.DefaultImport;
        }
        if (firScope instanceof FirAbstractStarImportingScope) {
            return FirResolvedSymbolOrigin.StarImport;
        }
        if (firScope instanceof FirPackageMemberScope) {
            return FirResolvedSymbolOrigin.Package;
        }
        if (firScope instanceof FirAbstractSimpleImportingScope) {
            return FirResolvedSymbolOrigin.ExplicitImport;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirExpression transformExpressionUsingSmartcastInfo(BodyResolveComponents bodyResolveComponents, FirExpression firExpression, FirDataFlowAnalyzer.SmartCastStatement smartCastStatement) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinTypeIntersectTypes;
        bodyResolveComponents.getClass();
        firExpression.getClass();
        smartCastStatement.getClass();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(bodyResolveComponents, resolvedType);
        if (smartCastStatement.getUpperTypesStability() == SmartcastStability.PROPERTY_WITH_GETTER && (firExpression instanceof FirPropertyAccessExpression)) {
            FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(((FirPropertyAccessExpression) firExpression).getCalleeReference());
            if ((symbol instanceof FirPropertySymbol) && !((FirPropertySymbol) symbol).getTypeParameterSymbols().isEmpty()) {
                return firExpression;
            }
        }
        boolean z = coneKotlinTypeFullyExpandedType instanceof ConeStubType;
        Set<ConeKotlinType> upperTypes = smartCastStatement.getUpperTypes();
        if (!z) {
            upperTypes = SetsKt.plus(upperTypes, coneKotlinTypeFullyExpandedType);
        }
        Set<ConeKotlinType> set = upperTypes;
        if (!(set instanceof Collection) || !set.isEmpty()) {
            Iterator<T> it = set.iterator();
            while (true) {
                if (!it.hasNext()) {
                    coneKotlinTypeIntersectTypes = null;
                    break;
                }
                if (!(((ConeKotlinType) it.next()) instanceof ConeDynamicType)) {
                    coneKotlinTypeIntersectTypes = ConeTypeIntersector.INSTANCE.intersectTypes(TypeComponentsKt.getTypeContext(bodyResolveComponents.getSession()), upperTypes);
                    break;
                }
            }
        } else {
            coneKotlinTypeIntersectTypes = null;
            break;
        }
        if (Intrinsics.areEqual(coneKotlinTypeIntersectTypes, coneKotlinTypeFullyExpandedType) && !(coneKotlinTypeIntersectTypes instanceof ConeDynamicType)) {
            coneKotlinTypeIntersectTypes = null;
        }
        if (smartCastStatement.getLowerTypes().isEmpty() && (coneKotlinTypeIntersectTypes == null || (Intrinsics.areEqual(coneKotlinTypeIntersectTypes, coneKotlinTypeFullyExpandedType) && !(coneKotlinTypeIntersectTypes instanceof ConeDynamicType)))) {
            return firExpression;
        }
        if (LanguageVersionUtilsKt.isSet(bodyResolveComponents, AnalysisFlags.getIdeMode()) && (firExpression instanceof FirQualifierWithContextSensitiveAlternative)) {
            ((FirQualifierWithContextSensitiveAlternative) firExpression).replaceContextSensitiveAlternative(null);
        }
        FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
        firSmartCastExpressionBuilder.setOriginalExpression(firExpression);
        firSmartCastExpressionBuilder.setSmartcastStability(smartCastStatement.getUpperTypesStability());
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        KtSourceElement source = firExpression.getSource();
        firResolvedTypeRefBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SmartCastedTypeRef.INSTANCE, null, 2, null) : null);
        firResolvedTypeRefBuilder.setConeType(coneKotlinTypeIntersectTypes == null ? coneKotlinTypeFullyExpandedType : coneKotlinTypeIntersectTypes);
        firSmartCastExpressionBuilder.setSmartcastType(firResolvedTypeRefBuilder.build());
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            if (!isKindOfNothing((ConeKotlinType) obj)) {
                arrayList.add(obj);
            }
        }
        if (coneKotlinTypeIntersectTypes != null && isKindOfNothing(coneKotlinTypeIntersectTypes) && !ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinTypeFullyExpandedType) && !ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeFullyExpandedType) && !z && !arrayList.isEmpty()) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
            KtSourceElement source2 = firExpression.getSource();
            firResolvedTypeRefBuilder2.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.SmartCastedTypeRef.INSTANCE, null, 2, null) : null);
            firResolvedTypeRefBuilder2.setConeType(ConeTypeIntersector.INSTANCE.intersectTypes(TypeComponentsKt.getTypeContext(bodyResolveComponents.getSession()), arrayList));
            firSmartCastExpressionBuilder.setSmartcastTypeWithoutNullableNothing(firResolvedTypeRefBuilder2.build());
        }
        firSmartCastExpressionBuilder.setUpperTypesFromSmartCast(smartCastStatement.getUpperTypes());
        SmartcastStability upperTypesStability = smartCastStatement.getUpperTypesStability();
        SmartcastStability smartcastStability = SmartcastStability.STABLE_VALUE;
        if (upperTypesStability == smartcastStability && coneKotlinTypeIntersectTypes != null) {
            resolvedType = coneKotlinTypeIntersectTypes;
        }
        firSmartCastExpressionBuilder.setConeTypeOrNull(resolvedType);
        Set<DfaType> lowerTypes = smartCastStatement.getLowerTypesStability() == smartcastStability ? smartCastStatement.getLowerTypes() : null;
        if (lowerTypes == null) {
            lowerTypes = SetsKt.emptySet();
        }
        firSmartCastExpressionBuilder.setLowerTypesFromSmartCast(lowerTypes);
        return firSmartCastExpressionBuilder.mo288build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeKotlinType typeForQualifierByDeclaration(FirDeclaration firDeclaration, FirSession firSession, FirElement firElement, FirFile firFile) {
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration;
        firDeclaration.getClass();
        firSession.getClass();
        firElement.getClass();
        firFile.getClass();
        if (firDeclaration instanceof FirTypeAlias) {
            ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType((FirTypeAlias) firDeclaration);
            if (expandedConeType == null || (lookupTag = expandedConeType.getLookupTag()) == null || (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, firSession)) == null || (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) == null) {
                return null;
            }
            return typeForQualifierByDeclaration(firClassLikeDeclaration, firSession, firElement, firFile);
        }
        if (firDeclaration instanceof FirRegularClass) {
            FirRegularClass firRegularClass = (FirRegularClass) firDeclaration;
            if (firRegularClass.getClassKind() == ClassKind.OBJECT) {
                return TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firRegularClass.getSymbol(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
            }
            FirRegularClassSymbol companionObjectSymbol = firRegularClass.getCompanionObjectSymbol();
            if (companionObjectSymbol != null) {
                FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(firSession);
                if (lookupTracker != null) {
                    FirLookupTrackerComponentKt.recordCompanionLookup(lookupTracker, companionObjectSymbol.getClassId(), firElement.getSource(), firFile.getSource());
                }
                return TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) companionObjectSymbol, (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
            }
        }
        return null;
    }

    public static final ConeLookupTagBasedType typeForReifiedParameterReference(FirResolvedReifiedParameterReferenceBuilder firResolvedReifiedParameterReferenceBuilder) {
        firResolvedReifiedParameterReferenceBuilder.getClass();
        return TypeConstructionUtilsKt.constructType$default((FirClassifierSymbol) firResolvedReifiedParameterReferenceBuilder.getSymbol(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final ConeKotlinType typeFromCallee(BodyResolveComponents bodyResolveComponents, FirReference firReference) throws KotlinIllegalArgumentExceptionWithAttachments {
        bodyResolveComponents.getClass();
        firReference.getClass();
        if (firReference instanceof FirErrorNamedReference) {
            return new ConeErrorType(new ConeUnreportedDuplicateDiagnostic(((FirErrorNamedReference) firReference).getDiagnostic()), false, null, null, null, null, null, 126, null);
        }
        if (firReference instanceof FirNamedReferenceWithCandidate) {
            return typeFromSymbol(bodyResolveComponents, ((FirNamedReferenceWithCandidate) firReference).getCandidateSymbol());
        }
        if (firReference instanceof FirResolvedNamedReference) {
            return typeFromSymbol(bodyResolveComponents, ((FirResolvedNamedReference) firReference).getResolvedSymbol());
        }
        if (!(firReference instanceof FirThisReference)) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Failed to extract type from: " + Reflection.getOrCreateKotlinClass(firReference.getClass()).getSimpleName(), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "reference", firReference);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        String labelName = ((FirThisReference) firReference).getLabelName();
        Set<ImplicitReceiverValue<?>> set = bodyResolveComponents.getImplicitValueStorage().get(labelName);
        if (set.size() >= 2) {
            return new ConeErrorType(new ConeSimpleDiagnostic("Ambiguous this@" + labelName, DiagnosticKind.AmbiguousLabel), false, null, null, null, null, null, 126, null);
        }
        if (!set.isEmpty()) {
            return ((ImplicitReceiverValue) CollectionsKt.single(set)).getType();
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("Unresolved this@" + labelName, DiagnosticKind.UnresolvedLabel), false, null, null, null, null, null, 126, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeKotlinType typeFromSymbol(BodyResolveComponents bodyResolveComponents, FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (firBasedSymbol instanceof FirSyntheticPropertySymbol) {
            FirSyntheticPropertyAccessorSymbol getterSymbol = ((FirSyntheticPropertySymbol) firBasedSymbol).getGetterSymbol();
            getterSymbol.getClass();
            return typeFromSymbol(bodyResolveComponents, getterSymbol.getDelegateFunctionSymbol());
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return bodyResolveComponents.getReturnTypeCalculator().tryCalculateReturnType((FirCallableDeclaration) ((FirCallableSymbol) firBasedSymbol).getFir()).getConeType();
        }
        if (firBasedSymbol instanceof FirClassifierSymbol) {
            return TypeConstructionUtilsKt.constructType$default((FirClassifierSymbol) firBasedSymbol, (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Failed to extract type from symbol: " + firBasedSymbol.getClass(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", firBasedSymbol.getFir());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final void unsetResolvedToCompanionIf(FirResolvedQualifier firResolvedQualifier, boolean z) {
        firResolvedQualifier.getClass();
        if (z) {
            firResolvedQualifier.replaceResolvedToCompanionObject(false);
        }
    }

    public static final ConeKotlinType initialTypeOfCandidate(BodyResolveComponents bodyResolveComponents, Candidate candidate) {
        bodyResolveComponents.getClass();
        candidate.getClass();
        return initialTypeOfCandidate(typeFromSymbol(bodyResolveComponents, candidate.getSymbol()), candidate);
    }

    public static final <T extends FirResolvable> ConeKotlinType typeFromCallee(BodyResolveComponents bodyResolveComponents, T t) {
        bodyResolveComponents.getClass();
        t.getClass();
        return typeFromCallee(bodyResolveComponents, t.getCalleeReference());
    }

    public static final FirResolvedQualifier buildResolvedQualifierForClass(BodyResolveComponents bodyResolveComponents, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement, FirResolvedQualifier firResolvedQualifier, List<? extends FirTypeProjection> list, ConeDiagnostic coneDiagnostic, List<? extends ConeDiagnostic> list2, List<? extends FirAnnotation> list3, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        bodyResolveComponents.getClass();
        firClassLikeSymbol.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        return buildResolvedQualifierForClass(bodyResolveComponents, firClassLikeSymbol, ktSourceElement, firClassLikeSymbol.getClassId().getPackageFqName(), firClassLikeSymbol.getClassId().getRelativeClassName(), list, coneDiagnostic, list2, list3, firResolvedQualifier, firResolvedSymbolOrigin);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0027  */
    /* JADX WARN: Code duplicated, block: B:15:0x002f  */
    public static final FirExpression transformExpressionUsingSmartcastInfo(BodyResolveComponents bodyResolveComponents, FirExpression firExpression) {
        TypeStatement typeStatementExtractTypeStatementFrom;
        bodyResolveComponents.getClass();
        firExpression.getClass();
        FirDataFlowAnalyzer dataFlowAnalyzer = bodyResolveComponents.getDataFlowAnalyzer();
        Flow currentSmartCastPosition = dataFlowAnalyzer.getCurrentSmartCastPosition();
        FirDataFlowAnalyzer.SmartCastStatement smartCastStatementBuildSmartCastStatement = null;
        if (currentSmartCastPosition != null) {
            DataFlowVariable syntheticVariable = new SyntheticVariable(firExpression);
            TypeStatement typeStatementExtractTypeStatementFrom2 = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
            if (typeStatementExtractTypeStatementFrom2 == null) {
                syntheticVariable = dataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firExpression, false);
                if (syntheticVariable != null) {
                    typeStatementExtractTypeStatementFrom = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
                    if (typeStatementExtractTypeStatementFrom != null && typeStatementExtractTypeStatementFrom.isNotEmpty()) {
                        smartCastStatementBuildSmartCastStatement = typeStatementExtractTypeStatementFrom;
                    }
                    typeStatementExtractTypeStatementFrom2 = smartCastStatementBuildSmartCastStatement;
                    smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                }
            } else {
                if (!typeStatementExtractTypeStatementFrom2.isNotEmpty()) {
                    typeStatementExtractTypeStatementFrom2 = null;
                }
                if (typeStatementExtractTypeStatementFrom2 == null) {
                    syntheticVariable = dataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firExpression, false);
                    if (syntheticVariable != null) {
                        typeStatementExtractTypeStatementFrom = dataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable);
                        if (typeStatementExtractTypeStatementFrom != null) {
                            smartCastStatementBuildSmartCastStatement = typeStatementExtractTypeStatementFrom;
                        }
                        typeStatementExtractTypeStatementFrom2 = smartCastStatementBuildSmartCastStatement;
                        smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                    }
                } else {
                    smartCastStatementBuildSmartCastStatement = dataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatementExtractTypeStatementFrom2);
                }
            }
        }
        return smartCastStatementBuildSmartCastStatement == null ? firExpression : transformExpressionUsingSmartcastInfo(bodyResolveComponents, firExpression, smartCastStatementBuildSmartCastStatement);
    }
}
