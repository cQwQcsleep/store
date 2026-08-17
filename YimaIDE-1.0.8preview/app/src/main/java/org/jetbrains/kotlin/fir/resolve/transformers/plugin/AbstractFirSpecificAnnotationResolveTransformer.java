package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.AnnotationsPosition;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProvider;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProviderImplKt;
import org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProviderKt;
import org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotationsKt;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSpecificTypeResolverTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.AbstractFirSpecificAnnotationResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirPlaceholderProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirStarProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirUserTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirQualifierPartImpl;
import org.jetbrains.kotlin.fir.types.impl.FirTypeArgumentListImpl;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0004À\u0001Á\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u0016\u0010=\u001a\u00020B2\u0006\u0010?\u001a\u00020@2\u0006\u0010C\u001a\u00020DJ\u0010\u0010E\u001a\u00020B2\u0006\u0010F\u001a\u00020GH\u0002J\u001a\u0010H\u001a\u00020>2\u0006\u0010I\u001a\u00020J2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010K\u001a\u00020#2\u0006\u0010C\u001a\u00020DH\u0002J\f\u0010L\u001a\u00020#*\u00020GH\u0002J&\u0010M\u001a\u00020#*\u00020N2\u0006\u0010\u0003\u001a\u00020\u00042\u0010\u0010O\u001a\f\u0012\b\u0012\u00060\u001fj\u0002` 0\u001eH\u0002J\u001a\u0010P\u001a\u00020>2\u0006\u0010Q\u001a\u00020R2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010S\u001a\u00020>2\u0006\u0010T\u001a\u00020U2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010V\u001a\u00020B2\u0006\u0010W\u001a\u0002092\f\u0010X\u001a\b\u0012\u0004\u0012\u00020B0YH\u0086\bø\u0001\u0000J\"\u0010Z\u001a\u00020B2\u0006\u0010[\u001a\u00020\\2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020B0YH\u0086\bø\u0001\u0000J\u001a\u0010]\u001a\u00020\\2\u0006\u0010[\u001a\u00020\\2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010^\u001a\u00020B2\u0006\u0010_\u001a\u00020`2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020B0YH\u0086\bø\u0001\u0000J\u001a\u0010b\u001a\u00020`2\u0006\u0010_\u001a\u00020`2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010c\u001a\u00020B2\u0006\u0010W\u001a\u0002092\f\u0010d\u001a\b\u0012\u0004\u0012\u00020B0YH\u0086\bø\u0001\u0000J\u001a\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020f2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020i2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010k\u001a\u00020\u000b2\u0006\u0010l\u001a\u00020\u000b2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020n2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J$\u0010p\u001a\u00020B2\u0006\u0010o\u001a\u00020n2\u000e\b\u0004\u0010X\u001a\b\u0012\u0004\u0012\u00020B0YH\u0086\bø\u0001\u0000J\u001c\u0010q\u001a\u00020B2\u0006\u0010o\u001a\u00020n2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020B0YJ-\u0010u\u001a\u0002Hv\"\u0004\b\u0000\u0010v2\u0006\u0010o\u001a\u00020n2\f\u0010w\u001a\b\u0012\u0004\u0012\u0002Hv0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010xJ\u000e\u0010y\u001a\u00020B2\u0006\u0010z\u001a\u00020{J\u0010\u0010y\u001a\u00020B2\u0006\u0010|\u001a\u00020}H\u0002J-\u0010~\u001a\u0002Hv\"\f\b\u0000\u0010v*\u00020}*\u00020>2\u0006\u0010|\u001a\u0002Hv2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0002\u0010\u007fJ.\u0010\u0086\u0001\u001a\u0002Hv\"\u0004\b\u0000\u0010v2\u0006\u0010o\u001a\u00020n2\f\u0010w\u001a\b\u0012\u0004\u0012\u0002Hv0YH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010xJ\u0011\u0010\u0087\u0001\u001a\u00020#2\u0006\u0010l\u001a\u00020\u000bH&J\u001d\u0010\u0088\u0001\u001a\u00020>2\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u008b\u0001\u001a\u00020>2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J1\u0010\u008e\u0001\u001a\u0002Hv\"\r\b\u0000\u0010v*\u00030\u008f\u0001*\u00020>2\u0007\u0010\u0090\u0001\u001a\u0002Hv2\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0003\u0010\u0091\u0001J\u001d\u0010\u0092\u0001\u001a\u00020>2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010\u0095\u0001\u001a\u00020B*\u00030\u0094\u0001H\u0002J\u001e\u0010\u0096\u0001\u001a\u00030\u0097\u00012\b\u0010\u0098\u0001\u001a\u00030\u0097\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u0099\u0001\u001a\u00030\u009a\u00012\b\u0010\u009b\u0001\u001a\u00030\u009a\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u009c\u0001\u001a\u00030\u009a\u00012\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u009f\u0001\u001a\u00020>2\b\u0010 \u0001\u001a\u00030¡\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¢\u0001\u001a\u00020>2\b\u0010£\u0001\u001a\u00030¤\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¥\u0001\u001a\u00020>2\b\u0010¦\u0001\u001a\u00030§\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¨\u0001\u001a\u00020>2\b\u0010©\u0001\u001a\u00030ª\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010«\u0001\u001a\u00030¬\u00012\u0007\u0010C\u001a\u00030¬\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u00ad\u0001\u001a\u00030®\u00012\b\u0010¯\u0001\u001a\u00030®\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016J0\u0010°\u0001\u001a\u0003H±\u0001\"\n\b\u0000\u0010±\u0001*\u00030²\u00012\b\u0010³\u0001\u001a\u0003H±\u00012\b\u0010A\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0003\u0010´\u0001J\u0016\u0010µ\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b012\u0007\u0010¶\u0001\u001a\u00020\u000bJ\u0018\u0010·\u0001\u001a\u00020B2\u000f\u0010¸\u0001\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u000101J2\u0010X\u001a\u0003H¹\u0001\"\u0005\b\u0000\u0010¹\u00012\u0007\u0010¶\u0001\u001a\u00020\u000b2\r\u0010d\u001a\t\u0012\u0005\u0012\u0003H¹\u00010YH\u0086\bø\u0001\u0000¢\u0006\u0003\u0010º\u0001J\u0012\u0010»\u0001\u001a\u00020D2\u0007\u0010¼\u0001\u001a\u00020DH\u0002J\u000f\u0010½\u0001\u001a\u00030¾\u0001*\u00030¾\u0001H\u0002J\u000f\u0010½\u0001\u001a\u00030¿\u0001*\u00030¿\u0001H\u0002R \u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001d\u001a\f\u0012\b\u0012\u00060\u001fj\u0002` 0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0012\b\u0012\u00060\u001fj\u0002` 0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020#8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R \u0010&\u001a\u00020'8\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u0011\u001a\u0004\b)\u0010*R$\u0010+\u001a\u00060,R\u00020\u00008\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0011\u001a\u0004\b.\u0010/R.\u00100\u001a\b\u0012\u0004\u0012\u00020\u000b018\u0006@\u0006X\u0087\u000er\u0002\b\u0014¢\u0006\u0014\n\u0000\u0012\u0004\b2\u0010\u0011\u001a\u0004\b3\u00104\"\u0004\b5\u00106R&\u00107\u001a\b\u0012\u0004\u0012\u000209088\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b:\u0010\u0011\u001a\u0004\b;\u0010<R#\u0010r\u001a\u0004\u0018\u00010n8\u0006@\u0006X\u0087\u000er\u0002\b\u0014\u0092\u0002\u0002\bt¢\u0006\b\n\u0000\u0012\u0004\bs\u0010\u0011R&\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\nX\u0086.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Â\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "computationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "getSession$annotations", "()V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "getScopeSession$annotations", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getComputationSession$annotations", "getComputationSession", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "predicateBasedProvider", "Lorg/jetbrains/kotlin/fir/extensions/FirPredicateBasedProvider;", "annotationsFromPlugins", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "metaAnnotationsFromPlugins", "shouldRecordIntoPredicateBasedProvider", Argument.Delimiters.none, "getShouldRecordIntoPredicateBasedProvider", "()Z", "typeResolverTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "getTypeResolverTransformer$annotations", "getTypeResolverTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "argumentsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer$FirEnumAnnotationArgumentsTransformerDispatcher;", "getArgumentsTransformer$annotations", "getArgumentsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer$FirEnumAnnotationArgumentsTransformerDispatcher;", "owners", "Lkotlinx/collections/immutable/PersistentList;", "getOwners$annotations", "getOwners", "()Lkotlinx/collections/immutable/PersistentList;", "setOwners", "(Lkotlinx/collections/immutable/PersistentList;)V", "classDeclarationsStack", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getClassDeclarationsStack$annotations", "getClassDeclarationsStack", "()Lkotlin/collections/ArrayDeque;", "transformAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "data", Argument.Delimiters.none, "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "resolveAnnotationsOnAnnotationIfNeeded", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "transformAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "shouldRunAnnotationResolve", "requiredToSave", "markedWithMetaAnnotation", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "metaAnnotations", "transformRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "resolveClass", "klass", "transformChildren", "Lkotlin/Function0;", "resolveScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "transformScript", "resolveReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "block", "transformReplSnippet", "withClass", "action", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "transformDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "danglingModifierList", "transformDeclaration", "declaration", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "resolveFile", "withFileAndFileScopes", "currentFile", "getCurrentFile$annotations", "Lkotlin/jvm/JvmField;", "withFile", "T", "f", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "calculateDeprecations", "classLikeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "transformCallableDeclarationForDeprecations", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "scopes", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getScopes", "()Ljava/util/List;", "setScopes", "(Ljava/util/List;)V", "withFileScopes", "shouldTransformDeclaration", "transformBackingField", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "transformPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "transformFunctionDeclarationForDeprecations", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "function", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "transformProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "moveJavaDeprecatedAnnotationToBackingField", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "transformEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "transformField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "transformValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "transformErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "transformTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformAnnotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "annotationContainer", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "beforeTransformingChildren", "parentDeclaration", "afterTransformingChildren", "state", "R", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "createDeepCopyOfTypeRef", "original", "createDeepCopy", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "FirEnumAnnotationArgumentsTransformerDispatcher", "FirEnumAnnotationArgumentsTransformer", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirSpecificAnnotationResolveTransformer extends FirDefaultTransformer {
    private final Set<FqName> annotationsFromPlugins;
    private final FirEnumAnnotationArgumentsTransformerDispatcher argumentsTransformer;
    private final ArrayDeque<FirClass> classDeclarationsStack;
    private final CompilerRequiredAnnotationsComputationSession computationSession;
    public FirFile currentFile;
    private final Set<FqName> metaAnnotationsFromPlugins;
    private final BodyResolveContext outerBodyResolveContext;
    private PersistentList<? extends FirDeclaration> owners;
    private final FirPredicateBasedProvider predicateBasedProvider;
    private final ScopeSession scopeSession;
    public List<? extends FirScope> scopes;
    private final FirSession session;
    private final FirSpecificTypeResolverTransformer typeResolverTransformer;

    @Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0016J\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0017H\u0014J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u0002022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u0002052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u0002082\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020;2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020>2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020AH\u0014J\u0018\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020D2\u0006\u0010\n\u001a\u00020\u000bH\u0016J0\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020J2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\f\u0010K\u001a\u00020L*\u00020\u0017H\u0002J\u001c\u0010M\u001a\u00020L*\u00020\u00172\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020QH\u0002¨\u0006R"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer$FirEnumAnnotationArgumentsTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "transformAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "transformErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "transformExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isAcceptableResolvedQualifiedAccess", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "transformBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "transformComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "transformTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "transformCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "transformBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "transformVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "transformCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "transformDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "transformIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "transformCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "transformAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "transformAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "shouldComputeTypeOfGetClassCallWithNotQualifierInLhs", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "transformFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "resolveQualifiedAccessAndSelectCandidate", "qualifiedAccessExpression", "isUsedAsReceiver", "isUsedAsGetClassReceiver", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "resolveFromImportScope", Argument.Delimiters.none, "updateCallee", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/impl/FirSimpleNamedReference;", "calleeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class FirEnumAnnotationArgumentsTransformer extends FirExpressionsResolveTransformer {
        final /* synthetic */ AbstractFirSpecificAnnotationResolveTransformer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirEnumAnnotationArgumentsTransformer(AbstractFirSpecificAnnotationResolveTransformer abstractFirSpecificAnnotationResolveTransformer, FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
            super(firAbstractBodyResolveTransformerDispatcher);
            firAbstractBodyResolveTransformerDispatcher.getClass();
            this.this$0 = abstractFirSpecificAnnotationResolveTransformer;
        }

        public static FirExpression f(FirExpression firExpression) {
            firExpression.getClass();
            FirQualifiedAccessExpression firQualifiedAccessExpression = firExpression instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpression : null;
            if (firQualifiedAccessExpression != null) {
                return firQualifiedAccessExpression.getExplicitReceiver();
            }
            return null;
        }

        public static String g(FirEnumAnnotationArgumentsTransformer firEnumAnnotationArgumentsTransformer, FirExpression firExpression) {
            Name name;
            firExpression.getClass();
            FirReference reference = ReferenceUtilsKt.toReference(firExpression, firEnumAnnotationArgumentsTransformer.getSession());
            FirSimpleNamedReference firSimpleNamedReference = reference instanceof FirSimpleNamedReference ? (FirSimpleNamedReference) reference : null;
            if (firSimpleNamedReference == null || (name = firSimpleNamedReference.getName()) == null) {
                return null;
            }
            return name.getIdentifier();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX WARN: Multi-variable type inference failed */
        private final void resolveFromImportScope(FirQualifiedAccessExpression firQualifiedAccessExpression) throws UninitializedPropertyAccessException {
            Object objFindEnumEntryWithoutResolution;
            FirEnumEntrySymbol firEnumEntrySymbol;
            FirClassSymbol firClassSymbol;
            Object next;
            FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
            FirSimpleNamedReference firSimpleNamedReference = calleeReference instanceof FirSimpleNamedReference ? (FirSimpleNamedReference) calleeReference : null;
            if (firSimpleNamedReference == null) {
                return;
            }
            Name name = firSimpleNamedReference.getName();
            FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
            FirQualifiedAccessExpression firQualifiedAccessExpression2 = explicitReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) explicitReceiver : null;
            if (firQualifiedAccessExpression2 == null) {
                Iterator<T> it = this.this$0.getScopes().iterator();
                do {
                    if (!it.hasNext()) {
                        objFindEnumEntryWithoutResolution = null;
                        break;
                    } else {
                        FirScope firScope = (FirScope) it.next();
                        objFindEnumEntryWithoutResolution = firScope instanceof FirAbstractImportingScope ? ((FirAbstractImportingScope) firScope).findEnumEntryWithoutResolution(name) : (FirVariableSymbol) CollectionsKt.firstOrNull(FirScopeKt.getProperties(firScope, name));
                    }
                } while (objFindEnumEntryWithoutResolution == null);
                firEnumEntrySymbol = objFindEnumEntryWithoutResolution instanceof FirEnumEntrySymbol ? (FirEnumEntrySymbol) objFindEnumEntryWithoutResolution : null;
                if (firEnumEntrySymbol == null) {
                    return;
                }
                updateCallee(firQualifiedAccessExpression, firSimpleNamedReference, firEnumEntrySymbol);
                return;
            }
            FirReference calleeReference2 = firQualifiedAccessExpression2.getCalleeReference();
            FirSimpleNamedReference firSimpleNamedReference2 = calleeReference2 instanceof FirSimpleNamedReference ? (FirSimpleNamedReference) calleeReference2 : null;
            if (firSimpleNamedReference2 == null) {
                return;
            }
            Name name2 = firSimpleNamedReference2.getName();
            if (name2.isSpecial()) {
                name2 = null;
            }
            if (name2 == null) {
                return;
            }
            Iterator<T> it2 = this.this$0.getScopes().iterator();
            do {
                if (!it2.hasNext()) {
                    firClassSymbol = null;
                    break;
                } else {
                    FirClassifierSymbol<?> singleClassifier = FirScopeKt.getSingleClassifier((FirScope) it2.next(), name2);
                    firClassSymbol = singleClassifier instanceof FirClassSymbol ? (FirClassSymbol) singleClassifier : null;
                }
            } while (firClassSymbol == null);
            if (firClassSymbol == null) {
                return;
            }
            List list = SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.generateSequence(firQualifiedAccessExpression2.getExplicitReceiver(), new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.a
                public final Object invoke(Object obj) {
                    return AbstractFirSpecificAnnotationResolveTransformer.FirEnumAnnotationArgumentsTransformer.f((FirExpression) obj);
                }
            }), new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.b
                public final Object invoke(Object obj) {
                    return AbstractFirSpecificAnnotationResolveTransformer.FirEnumAnnotationArgumentsTransformer.g(this.b, (FirExpression) obj);
                }
            }));
            List list2 = list;
            if (list2.isEmpty() || Intrinsics.areEqual(FqName.Companion.fromSegments(CollectionsKt.asReversed(list)), firClassSymbol.getClassId().getPackageFqName())) {
                FirResolvedQualifierBuilder firResolvedQualifierBuilder = new FirResolvedQualifierBuilder();
                firResolvedQualifierBuilder.setSource(firQualifiedAccessExpression2.getSource());
                firResolvedQualifierBuilder.setPackageFqName(firClassSymbol.getClassId().getPackageFqName());
                firResolvedQualifierBuilder.setRelativeClassFqName(firClassSymbol.getClassId().getRelativeClassName());
                firResolvedQualifierBuilder.setConeTypeOrNull(getSession().getBuiltinTypes().getUnitType().getConeType());
                firResolvedQualifierBuilder.setSymbol(firClassSymbol);
                firResolvedQualifierBuilder.setFullyQualified(!list2.isEmpty());
                firResolvedQualifierBuilder.setResolvedToCompanionObject(false);
                FirResolvedQualifier firResolvedQualifierMo288build = firResolvedQualifierBuilder.mo288build();
                Iterator<T> it3 = ((FirClass) firClassSymbol.getFir()).getDeclarations().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it3.next();
                    FirDeclaration firDeclaration = (FirDeclaration) next;
                    if ((firDeclaration instanceof FirEnumEntry) && Intrinsics.areEqual(((FirEnumEntry) firDeclaration).getName(), name)) {
                        break;
                    }
                }
                FirDeclaration firDeclaration2 = (FirDeclaration) next;
                FirBasedSymbol<FirDeclaration> symbol = firDeclaration2 != null ? firDeclaration2.getSymbol() : null;
                firEnumEntrySymbol = symbol instanceof FirEnumEntrySymbol ? (FirEnumEntrySymbol) symbol : null;
                if (firEnumEntrySymbol == null) {
                    return;
                }
                updateCallee(firQualifiedAccessExpression, firSimpleNamedReference, firEnumEntrySymbol);
                firQualifiedAccessExpression.replaceExplicitReceiver(firResolvedQualifierMo288build);
                firQualifiedAccessExpression.replaceDispatchReceiver(firResolvedQualifierMo288build);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        private final void updateCallee(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSimpleNamedReference firSimpleNamedReference, FirEnumEntrySymbol firEnumEntrySymbol) throws UninitializedPropertyAccessException {
            String strAsString;
            ClassId classId;
            getTransformer().getContext().getFile();
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
            if (lookupTracker != null) {
                Name name = firSimpleNamedReference.getName();
                ConeSimpleKotlinType dispatchReceiverType = firEnumEntrySymbol.getDispatchReceiverType();
                if (dispatchReceiverType == null || (classId = ConeTypeUtilsKt.getClassId(dispatchReceiverType)) == null || (strAsString = classId.asFqNameString()) == null) {
                    strAsString = firEnumEntrySymbol.getCallableId().getPackageName().asString();
                }
                FirLookupTrackerComponentKt.recordNameLookup(lookupTracker, name, strAsString, firQualifiedAccessExpression.getSource(), getTransformer().getContext().getFile().getSource());
            }
            FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
            firResolvedNamedReferenceBuilder.setSource(firSimpleNamedReference.getSource());
            firResolvedNamedReferenceBuilder.setName(firSimpleNamedReference.getName());
            firResolvedNamedReferenceBuilder.setResolvedSymbol(firEnumEntrySymbol);
            firQualifiedAccessExpression.replaceCalleeReference(firResolvedNamedReferenceBuilder.build());
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firEnumEntrySymbol);
            if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                firQualifiedAccessExpression.replaceConeTypeOrNull(new ConeClassLikeTypeImpl(coneClassLikeLookupTagContainingClassLookupTag, new ConeTypeProjection[0], false, null, 8, null));
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer
        public boolean isAcceptableResolvedQualifiedAccess(FirQualifiedAccessExpression firQualifiedAccessExpression) {
            firQualifiedAccessExpression.getClass();
            return !(firQualifiedAccessExpression.getCalleeReference() instanceof FirErrorNamedReference);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer
        public FirQualifiedAccessExpression resolveQualifiedAccessAndSelectCandidate(FirQualifiedAccessExpression qualifiedAccessExpression, boolean isUsedAsReceiver, boolean isUsedAsGetClassReceiver, FirElement callSite, ResolutionMode data) throws UninitializedPropertyAccessException {
            qualifiedAccessExpression.getClass();
            callSite.getClass();
            data.getClass();
            resolveFromImportScope(qualifiedAccessExpression);
            return qualifiedAccessExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer
        public boolean shouldComputeTypeOfGetClassCallWithNotQualifierInLhs(FirGetClassCall getClassCall) {
            getClassCall.getClass();
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformAnnotation(FirAnnotation annotation, ResolutionMode data) {
            annotation.getClass();
            data.getClass();
            getComponents().getDataFlowAnalyzer().enterAnnotation();
            annotation.transformChildren(getTransformer(), ResolutionMode.ContextDependent.INSTANCE);
            getComponents().getDataFlowAnalyzer().exitAnnotation();
            return annotation;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ResolutionMode data) {
            annotationCall.getClass();
            data.getClass();
            return transformAnnotation((FirAnnotation) annotationCall, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, ResolutionMode data) {
            anonymousFunctionExpression.getClass();
            data.getClass();
            return anonymousFunctionExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, ResolutionMode data) {
            anonymousObjectExpression.getClass();
            data.getClass();
            return anonymousObjectExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformBlock(FirBlock block, ResolutionMode data) {
            block.getClass();
            data.getClass();
            return block;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, ResolutionMode data) {
            booleanOperatorExpression.getClass();
            data.getClass();
            return booleanOperatorExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, ResolutionMode data) {
            callableReferenceAccess.getClass();
            data.getClass();
            return callableReferenceAccess;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, ResolutionMode data) {
            checkNotNullCall.getClass();
            data.getClass();
            return checkNotNullCall;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformCollectionLiteral(FirCollectionLiteral collectionLiteral, ResolutionMode data) {
            collectionLiteral.getClass();
            data.getClass();
            collectionLiteral.transformChildren(getTransformer(), data);
            return collectionLiteral;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformComparisonExpression(FirComparisonExpression comparisonExpression, ResolutionMode data) {
            comparisonExpression.getClass();
            data.getClass();
            return comparisonExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ResolutionMode data) {
            delegatedConstructorCall.getClass();
            data.getClass();
            return delegatedConstructorCall;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, ResolutionMode data) {
            errorAnnotationCall.getClass();
            data.getClass();
            return transformAnnotation((FirAnnotation) errorAnnotationCall, data);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformExpression(FirExpression expression, ResolutionMode data) {
            expression.getClass();
            data.getClass();
            FirElement firElementTransformChildren = expression.transformChildren(getTransformer(), data);
            firElementTransformChildren.getClass();
            return (FirStatement) firElementTransformChildren;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformFunctionCall(FirFunctionCall functionCall, ResolutionMode data) {
            functionCall.getClass();
            data.getClass();
            functionCall.transformChildren(getTransformer(), data);
            return functionCall;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, ResolutionMode data) {
            indexedAccessAugmentedAssignment.getClass();
            data.getClass();
            return indexedAccessAugmentedAssignment;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, ResolutionMode data) {
            thisReceiverExpression.getClass();
            data.getClass();
            return thisReceiverExpression;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, ResolutionMode data) {
            typeOperatorCall.getClass();
            data.getClass();
            return typeOperatorCall;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
        public FirStatement transformVariableAssignment(FirVariableAssignment variableAssignment, ResolutionMode data) {
            variableAssignment.getClass();
            data.getClass();
            return variableAssignment;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer$FirEnumAnnotationArgumentsTransformerDispatcher;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;)V", "expressionsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "getExpressionsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "declarationsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "getDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class FirEnumAnnotationArgumentsTransformerDispatcher extends FirAbstractBodyResolveTransformerDispatcher {
        private final FirDeclarationsResolveTransformer declarationsTransformer;
        private final FirExpressionsResolveTransformer expressionsTransformer;

        public FirEnumAnnotationArgumentsTransformerDispatcher() {
            super(AbstractFirSpecificAnnotationResolveTransformer.this.getSession(), FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS, false, AbstractFirSpecificAnnotationResolveTransformer.this.getScopeSession(), null, AbstractFirSpecificAnnotationResolveTransformer.this.outerBodyResolveContext, false, 16, null);
            this.expressionsTransformer = new FirEnumAnnotationArgumentsTransformer(AbstractFirSpecificAnnotationResolveTransformer.this, this);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
        public FirDeclarationsResolveTransformer getDeclarationsTransformer() {
            return this.declarationsTransformer;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
        public FirExpressionsResolveTransformer getExpressionsTransformer() {
            return this.expressionsTransformer;
        }
    }

    public AbstractFirSpecificAnnotationResolveTransformer(FirSession firSession, ScopeSession scopeSession, CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationsComputationSession, List<? extends FirDeclaration> list, BodyResolveContext bodyResolveContext) {
        firSession.getClass();
        scopeSession.getClass();
        compilerRequiredAnnotationsComputationSession.getClass();
        list.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.computationSession = compilerRequiredAnnotationsComputationSession;
        this.outerBodyResolveContext = bodyResolveContext;
        this.predicateBasedProvider = FirPredicateBasedProviderKt.getPredicateBasedProvider(firSession);
        this.annotationsFromPlugins = FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(firSession).getAnnotations();
        this.metaAnnotationsFromPlugins = FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(firSession).getMetaAnnotations();
        this.typeResolverTransformer = new FirSpecificTypeResolverTransformer(firSession, false, false, null, false, 8, null);
        this.argumentsTransformer = new FirEnumAnnotationArgumentsTransformerDispatcher();
        this.owners = ExtensionsKt.persistentListOf();
        ArrayDeque<FirClass> arrayDeque = new ArrayDeque<>();
        for (FirDeclaration firDeclaration : list) {
            if (firDeclaration instanceof FirClass) {
                arrayDeque.add(firDeclaration);
            }
        }
        this.classDeclarationsStack = arrayDeque;
    }

    public static List b(AbstractFirSpecificAnnotationResolveTransformer abstractFirSpecificAnnotationResolveTransformer, FirRegularClassSymbol firRegularClassSymbol) {
        firRegularClassSymbol.getClass();
        abstractFirSpecificAnnotationResolveTransformer.computationSession.resolveAnnotationsOnAnnotationIfNeeded(firRegularClassSymbol, abstractFirSpecificAnnotationResolveTransformer.scopeSession);
        return firRegularClassSymbol.getAnnotations();
    }

    private final FirTypeProjection createDeepCopy(FirTypeProjection firTypeProjection) {
        if (firTypeProjection instanceof FirTypeProjectionWithVariance) {
            FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
            firTypeProjectionWithVarianceBuilder.setSource(firTypeProjectionWithVariance.getSource());
            FirUserTypeRef typeRef = firTypeProjectionWithVariance.getTypeRef();
            if (typeRef instanceof FirUserTypeRef) {
                typeRef = createDeepCopyOfTypeRef(typeRef);
            }
            firTypeProjectionWithVarianceBuilder.setTypeRef(typeRef);
            firTypeProjectionWithVarianceBuilder.setVariance(firTypeProjectionWithVariance.getVariance());
            return firTypeProjectionWithVarianceBuilder.build();
        }
        if (firTypeProjection instanceof FirStarProjection) {
            FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
            firStarProjectionBuilder.setSource(((FirStarProjection) firTypeProjection).getSource());
            return firStarProjectionBuilder.build();
        }
        if (!(firTypeProjection instanceof FirPlaceholderProjection)) {
            bu8.a();
            return null;
        }
        FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder = new FirPlaceholderProjectionBuilder();
        firPlaceholderProjectionBuilder.setSource(((FirPlaceholderProjection) firTypeProjection).getSource());
        return firPlaceholderProjectionBuilder.build();
    }

    private final FirUserTypeRef createDeepCopyOfTypeRef(FirUserTypeRef original) {
        FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
        firUserTypeRefBuilder.setSource(original.getSource());
        firUserTypeRefBuilder.setMarkedNullable(original.isMarkedNullable());
        firUserTypeRefBuilder.getAnnotations().addAll(original.getAnnotations());
        List qualifier = original.getQualifier();
        List<FirQualifierPart> qualifier2 = firUserTypeRefBuilder.getQualifier();
        Iterator it = qualifier.iterator();
        while (it.hasNext()) {
            qualifier2.add(createDeepCopy((FirQualifierPart) it.next()));
        }
        return firUserTypeRefBuilder.build();
    }

    @PrivateForInline
    public static /* synthetic */ void getArgumentsTransformer$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getClassDeclarationsStack$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getComputationSession$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getCurrentFile$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getOwners$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getScopeSession$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getSession$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void getTypeResolverTransformer$annotations() {
    }

    private final boolean markedWithMetaAnnotation(ConeKotlinType coneKotlinType, FirSession firSession, Set<FqName> set) {
        return FirPredicateBasedProviderImplKt.markedWithMetaAnnotationImpl(coneKotlinType, firSession, set, true, new LinkedHashSet(), new Function1() { // from class: xn
            public final Object invoke(Object obj) {
                return AbstractFirSpecificAnnotationResolveTransformer.b(this.b, (FirRegularClassSymbol) obj);
            }
        });
    }

    private final void moveJavaDeprecatedAnnotationToBackingField(FirProperty firProperty) {
        AnnotationsPosition annotationsPositionExtractBackingFieldAnnotationsFromProperty$default = FirAnnotationsPlatformSpecificSupportComponent.extractBackingFieldAnnotationsFromProperty$default(FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(this.session), firProperty, this.session, null, null, 12, null);
        if (annotationsPositionExtractBackingFieldAnnotationsFromProperty$default == null) {
            return;
        }
        firProperty.replaceAnnotations(annotationsPositionExtractBackingFieldAnnotationsFromProperty$default.getPropertyAnnotations());
        FirBackingField backingField = firProperty.getBackingField();
        if (backingField != null) {
            backingField.replaceAnnotations(annotationsPositionExtractBackingFieldAnnotationsFromProperty$default.getBackingFieldAnnotations());
        }
    }

    private final boolean requiredToSave(FirResolvedTypeRef firResolvedTypeRef) {
        ClassId classId = ConeTypeUtilsKt.getClassId(firResolvedTypeRef.getConeType());
        if (classId == null) {
            return false;
        }
        if (FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(this.session).getRequiredAnnotations().contains(classId) || this.annotationsFromPlugins.contains(classId.asSingleFqName())) {
            return true;
        }
        if (this.metaAnnotationsFromPlugins.isEmpty()) {
            return false;
        }
        return markedWithMetaAnnotation(firResolvedTypeRef.getConeType(), this.session, this.metaAnnotationsFromPlugins);
    }

    private final void resolveAnnotationsOnAnnotationIfNeeded(FirResolvedTypeRef annotationTypeRef) {
        FirRegularClassSymbol regularClassSymbol;
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(annotationTypeRef.getConeType());
        if (classLikeLookupTagIfAny == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(classLikeLookupTagIfAny, this.session)) == null) {
            return;
        }
        this.computationSession.resolveAnnotationsOnAnnotationIfNeeded(regularClassSymbol, this.scopeSession);
    }

    private final boolean shouldRunAnnotationResolve(FirUserTypeRef typeRef) {
        Name shortName = ResolveUtilsKt.getShortName(typeRef);
        if (this.metaAnnotationsFromPlugins.isEmpty() && !FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(this.session).getRequiredAnnotationsShortClassNames().contains(shortName)) {
            Set<FqName> set = this.annotationsFromPlugins;
            if ((set instanceof Collection) && set.isEmpty()) {
                return false;
            }
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((FqName) it.next()).shortName(), shortName)) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FirCallableDeclaration & FirStatement> T transformCallableDeclarationForDeprecations(T callableDeclaration, Void data) {
        if (!shouldTransformDeclaration(callableDeclaration)) {
            return callableDeclaration;
        }
        this.computationSession.recordThatAnnotationsAreResolved(callableDeclaration);
        FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) callableDeclaration, data);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(callableDeclaration);
        try {
            callableDeclaration.transformContextParameters(this, data);
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            calculateDeprecations(callableDeclaration);
            firDeclarationTransformDeclaration.getClass();
            return (T) ((FirCallableDeclaration) firDeclarationTransformDeclaration);
        } catch (Throwable th) {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FirFunction & FirStatement> T transformFunctionDeclarationForDeprecations(T function, Void data) {
        if (!shouldTransformDeclaration(function)) {
            return function;
        }
        this.computationSession.recordThatAnnotationsAreResolved(function);
        FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) function, data);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(function);
        try {
            function.transformContextParameters(this, data);
            function.transformValueParameters(this, data);
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            calculateDeprecations((FirCallableDeclaration) function);
            firDeclarationTransformDeclaration.getClass();
            return (T) ((FirFunction) firDeclarationTransformDeclaration);
        } catch (Throwable th) {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            throw th;
        }
    }

    public final void afterTransformingChildren(PersistentList<? extends FirDeclaration> state) {
        if (state != null) {
            this.owners = state;
        } else {
            w01.a("Required value was null.");
        }
    }

    public final PersistentList<FirDeclaration> beforeTransformingChildren(FirDeclaration parentDeclaration) {
        parentDeclaration.getClass();
        PersistentList persistentList = this.owners;
        this.owners = persistentList.add(parentDeclaration);
        return persistentList;
    }

    public final void calculateDeprecations(FirClassLikeDeclaration classLikeDeclaration) {
        classLikeDeclaration.getClass();
        if (Intrinsics.areEqual(classLikeDeclaration.getDeprecationsProvider(), UnresolvedDeprecationProvider.INSTANCE)) {
            classLikeDeclaration.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(classLikeDeclaration, this.session));
        }
    }

    public final FirEnumAnnotationArgumentsTransformerDispatcher getArgumentsTransformer() {
        return this.argumentsTransformer;
    }

    public final ArrayDeque<FirClass> getClassDeclarationsStack() {
        return this.classDeclarationsStack;
    }

    public final CompilerRequiredAnnotationsComputationSession getComputationSession() {
        return this.computationSession;
    }

    public final PersistentList<FirDeclaration> getOwners() {
        return this.owners;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<FirScope> getScopes() throws UninitializedPropertyAccessException {
        List list = this.scopes;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scopes");
        return null;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public boolean getShouldRecordIntoPredicateBasedProvider() {
        return FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(this.session).getHasRegisteredAnnotations();
    }

    public final FirSpecificTypeResolverTransformer getTypeResolverTransformer() {
        return this.typeResolverTransformer;
    }

    public final void resolveClass(FirClass klass, Function0<Unit> transformChildren) {
        klass.getClass();
        transformChildren.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(klass);
        try {
            if (!shouldTransformDeclaration(klass)) {
                InlineMarker.finallyStart(2);
                classDeclarationsStack.removeLast();
                InlineMarker.finallyEnd(2);
                return;
            }
            if (!getComputationSession().annotationResolutionWasAlreadyStarted(klass)) {
                getComputationSession().recordThatAnnotationResolutionStarted(klass);
                transformDeclaration((FirDeclaration) klass, (Void) null);
                getComputationSession().recordThatAnnotationsAreResolved(klass);
            }
            PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(klass);
            try {
                if (klass instanceof FirRegularClass) {
                    ((FirRegularClass) klass).transformContextParameters(this, null);
                }
                transformChildren.invoke();
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                InlineMarker.finallyEnd(1);
                calculateDeprecations(klass);
                InlineMarker.finallyStart(1);
                classDeclarationsStack.removeLast();
            } finally {
                InlineMarker.finallyStart(1);
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                InlineMarker.finallyEnd(1);
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            classDeclarationsStack.removeLast();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public final void resolveFile(FirFile file, final Function0<Unit> transformChildren) {
        file.getClass();
        transformChildren.getClass();
        if (shouldTransformDeclaration(file)) {
            withFileAndFileScopes(file, new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.AbstractFirSpecificAnnotationResolveTransformer.resolveFile.1
                public /* bridge */ /* synthetic */ Object invoke() {
                    m615invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m615invoke() {
                    transformChildren.invoke();
                }
            });
        }
    }

    public final void resolveReplSnippet(FirReplSnippet replSnippet, Function0<Unit> block) {
        replSnippet.getClass();
        block.getClass();
        if (shouldTransformDeclaration(replSnippet)) {
            getComputationSession().recordThatAnnotationsAreResolved(replSnippet);
            transformDeclaration((FirDeclaration) replSnippet, (Void) null);
            PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(replSnippet);
            try {
                block.invoke();
                Unit unit = Unit.INSTANCE;
            } finally {
                InlineMarker.finallyStart(1);
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public final void resolveScript(FirScript script, Function0<Unit> transformChildren) {
        script.getClass();
        transformChildren.getClass();
        if (shouldTransformDeclaration(script)) {
            getComputationSession().recordThatAnnotationsAreResolved(script);
            transformDeclaration((FirDeclaration) script, (Void) null);
            PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(script);
            try {
                transformChildren.invoke();
                Unit unit = Unit.INSTANCE;
            } finally {
                InlineMarker.finallyStart(1);
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                InlineMarker.finallyEnd(1);
            }
        }
    }

    public final void setOwners(PersistentList<? extends FirDeclaration> persistentList) {
        persistentList.getClass();
        this.owners = persistentList;
    }

    public final void setScopes(List<? extends FirScope> list) {
        list.getClass();
        this.scopes = list;
    }

    public abstract boolean shouldTransformDeclaration(FirDeclaration declaration);

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotation(FirAnnotation annotation, Void data) {
        annotation.getClass();
        throw new IllegalStateException("Should not be there");
    }

    public final void transformAnnotationCall(FirAnnotationCall annotationCall, FirUserTypeRef typeRef) {
        annotationCall.getClass();
        typeRef.getClass();
        FirResolvedTypeRef firResolvedTypeRefTransformUserTypeRef = this.typeResolverTransformer.transformUserTypeRef(createDeepCopyOfTypeRef(typeRef), new TypeResolutionConfiguration((Iterable) CollectionsKt.asReversed(getScopes()), (List) this.classDeclarationsStack, this.currentFile, (FirDeclaration) null, 8, (DefaultConstructorMarker) null));
        FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefTransformUserTypeRef instanceof FirResolvedTypeRef ? firResolvedTypeRefTransformUserTypeRef : null;
        if (firResolvedTypeRef == null) {
            return;
        }
        resolveAnnotationsOnAnnotationIfNeeded(firResolvedTypeRef);
        if (requiredToSave(firResolvedTypeRef)) {
            annotationCall.replaceAnnotationTypeRef(firResolvedTypeRef);
            annotationCall.replaceAnnotationResolvePhase(FirAnnotationResolvePhase.CompilerRequiredAnnotations);
            this.computationSession.annotationResolved(annotationCall);
            Set<ClassId> requiredAnnotationsWithArguments = FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(this.session).getRequiredAnnotationsWithArguments();
            ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(firResolvedTypeRef.getConeType());
            if (CollectionsKt.contains(requiredAnnotationsWithArguments, classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null)) {
                this.argumentsTransformer.transformAnnotation((FirAnnotation) annotationCall, (ResolutionMode) ResolutionMode.ContextDependent.INSTANCE);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnnotationContainer transformAnnotationContainer(FirAnnotationContainer annotationContainer, Void data) {
        annotationContainer.getClass();
        return annotationContainer.transformAnnotations(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObject(FirAnonymousObject anonymousObject, Void data) {
        anonymousObject.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(anonymousObject);
        try {
            if (!shouldTransformDeclaration(anonymousObject)) {
                classDeclarationsStack.removeLast();
                return anonymousObject;
            }
            if (!getComputationSession().annotationResolutionWasAlreadyStarted(anonymousObject)) {
                getComputationSession().recordThatAnnotationResolutionStarted(anonymousObject);
                transformDeclaration((FirDeclaration) anonymousObject, (Void) null);
                getComputationSession().recordThatAnnotationsAreResolved(anonymousObject);
            }
            PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(anonymousObject);
            try {
                anonymousObject.transformDeclarations((FirTransformer<? super Void>) this, data);
                Unit unit = Unit.INSTANCE;
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                calculateDeprecations(anonymousObject);
                classDeclarationsStack.removeLast();
                return anonymousObject;
            } catch (Throwable th) {
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                throw th;
            }
        } catch (Throwable th2) {
            classDeclarationsStack.removeLast();
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBackingField(FirBackingField backingField, Void data) {
        backingField.getClass();
        return (FirStatement) transformCallableDeclarationForDeprecations(backingField, data);
    }

    public final <R> R transformChildren(FirDeclaration parentDeclaration, Function0<? extends R> action) {
        parentDeclaration.getClass();
        action.getClass();
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(parentDeclaration);
        try {
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            InlineMarker.finallyEnd(1);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformConstructor(FirConstructor constructor, Void data) {
        constructor.getClass();
        return (FirConstructor) transformFunctionDeclarationForDeprecations(constructor, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDanglingModifierList transformDanglingModifierList(FirDanglingModifierList danglingModifierList, Void data) {
        danglingModifierList.getClass();
        if (!shouldTransformDeclaration(danglingModifierList)) {
            return danglingModifierList;
        }
        this.computationSession.recordThatAnnotationsAreResolved(danglingModifierList);
        FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) danglingModifierList, data);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(danglingModifierList);
        try {
            danglingModifierList.transformContextParameters(this, data);
            Unit unit = Unit.INSTANCE;
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            firDeclarationTransformDeclaration.getClass();
            return (FirDanglingModifierList) firDeclarationTransformDeclaration;
        } catch (Throwable th) {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclaration transformDeclaration(FirDeclaration declaration, Void data) {
        declaration.getClass();
        FirAnnotationContainer firAnnotationContainerTransformAnnotationContainer = transformAnnotationContainer((FirAnnotationContainer) declaration, data);
        firAnnotationContainerTransformAnnotationContainer.getClass();
        FirDeclaration firDeclaration = (FirDeclaration) firAnnotationContainerTransformAnnotationContainer;
        if (getShouldRecordIntoPredicateBasedProvider()) {
            this.predicateBasedProvider.registerAnnotatedDeclaration(declaration, this.owners);
        }
        return firDeclaration;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEnumEntry(FirEnumEntry enumEntry, Void data) {
        enumEntry.getClass();
        return (FirStatement) transformCallableDeclarationForDeprecations(enumEntry, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, Void data) {
        errorPrimaryConstructor.getClass();
        return transformConstructor((FirConstructor) errorPrimaryConstructor, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorProperty(FirErrorProperty errorProperty, Void data) {
        errorProperty.getClass();
        return transformProperty((FirProperty) errorProperty, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformField(FirField field, Void data) {
        field.getClass();
        return (FirStatement) transformCallableDeclarationForDeprecations(field, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(final FirFile file, final Void data) {
        file.getClass();
        if (shouldTransformDeclaration(file)) {
            withFileAndFileScopes(file, new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.plugin.AbstractFirSpecificAnnotationResolveTransformer$transformFile$$inlined$resolveFile$1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m616invoke() {
                    file.transformDeclarations(this, data);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m616invoke();
                    return Unit.INSTANCE;
                }
            });
        }
        return file;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(FirNamedFunction namedFunction, Void data) {
        namedFunction.getClass();
        return (FirNamedFunction) transformFunctionDeclarationForDeprecations(namedFunction, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformProperty(FirProperty property, Void data) {
        property.getClass();
        if (!shouldTransformDeclaration(property)) {
            return property;
        }
        this.computationSession.recordThatAnnotationsAreResolved(property);
        FirAnnotationContainer firAnnotationContainerTransformDeclaration = transformDeclaration((FirDeclaration) property, data);
        moveJavaDeprecatedAnnotationToBackingField(property);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(property);
        try {
            property.transformContextParameters((FirTransformer<? super Void>) this, data);
            property.transformSetter((FirTransformer<? super Void>) this, data);
            property.transformGetter((FirTransformer<? super Void>) this, data);
            property.transformBackingField((FirTransformer<? super Void>) this, data);
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            calculateDeprecations(property);
            firAnnotationContainerTransformDeclaration.getClass();
            return (FirStatement) firAnnotationContainerTransformDeclaration;
        } catch (Throwable th) {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformPropertyAccessor(FirPropertyAccessor propertyAccessor, Void data) {
        propertyAccessor.getClass();
        return transformFunctionDeclarationForDeprecations(propertyAccessor, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Void data) {
        regularClass.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(regularClass);
        try {
            if (!shouldTransformDeclaration(regularClass)) {
                classDeclarationsStack.removeLast();
                return regularClass;
            }
            if (!getComputationSession().annotationResolutionWasAlreadyStarted(regularClass)) {
                getComputationSession().recordThatAnnotationResolutionStarted(regularClass);
                transformDeclaration((FirDeclaration) regularClass, (Void) null);
                getComputationSession().recordThatAnnotationsAreResolved(regularClass);
            }
            PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(regularClass);
            try {
                regularClass.transformContextParameters(this, null);
                regularClass.transformDeclarations((FirTransformer<? super Void>) this, data);
                Unit unit = Unit.INSTANCE;
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                calculateDeprecations(regularClass);
                classDeclarationsStack.removeLast();
                return regularClass;
            } catch (Throwable th) {
                afterTransformingChildren(persistentListBeforeTransformingChildren);
                throw th;
            }
        } catch (Throwable th2) {
            classDeclarationsStack.removeLast();
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, Void data) {
        replSnippet.getClass();
        if (!shouldTransformDeclaration(replSnippet)) {
            return replSnippet;
        }
        getComputationSession().recordThatAnnotationsAreResolved(replSnippet);
        transformDeclaration((FirDeclaration) replSnippet, (Void) null);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(replSnippet);
        try {
            replSnippet.transformChildren(this, data);
            Unit unit = Unit.INSTANCE;
            return replSnippet;
        } finally {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirScript transformScript(FirScript script, Void data) {
        script.getClass();
        if (!shouldTransformDeclaration(script)) {
            return script;
        }
        getComputationSession().recordThatAnnotationsAreResolved(script);
        transformDeclaration((FirDeclaration) script, (Void) null);
        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(script);
        try {
            script.transformDeclarations(this, data);
            Unit unit = Unit.INSTANCE;
            return script;
        } finally {
            afterTransformingChildren(persistentListBeforeTransformingChildren);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeAlias transformTypeAlias(FirTypeAlias typeAlias, Void data) {
        typeAlias.getClass();
        if (!shouldTransformDeclaration(typeAlias)) {
            return typeAlias;
        }
        this.computationSession.recordThatAnnotationsAreResolved(typeAlias);
        FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) typeAlias, data);
        calculateDeprecations(typeAlias);
        firDeclarationTransformDeclaration.getClass();
        return (FirTypeAlias) firDeclarationTransformDeclaration;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    /* JADX INFO: renamed from: transformTypeRef, reason: merged with bridge method [inline-methods] */
    public FirTypeRef mo600transformTypeRef(FirTypeRef typeRef, Void data) {
        typeRef.getClass();
        FirAnnotationContainer firAnnotationContainerTransformAnnotationContainer = transformAnnotationContainer((FirAnnotationContainer) typeRef, data);
        firAnnotationContainerTransformAnnotationContainer.getClass();
        return (FirTypeRef) firAnnotationContainerTransformAnnotationContainer;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformValueParameter(FirValueParameter valueParameter, Void data) {
        valueParameter.getClass();
        return (FirStatement) transformCallableDeclarationForDeprecations(valueParameter, data);
    }

    public final void withClass(FirClass klass, Function0<Unit> action) {
        klass.getClass();
        action.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(klass);
        try {
            action.invoke();
            Unit unit = Unit.INSTANCE;
        } finally {
            InlineMarker.finallyStart(1);
            classDeclarationsStack.removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withFile(FirFile file, Function0<? extends T> f) {
        file.getClass();
        f.getClass();
        FirFile firFile = this.currentFile;
        this.currentFile = file;
        try {
            BodyResolveContext context = getArgumentsTransformer().getContext();
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getArgumentsTransformer().getComponents();
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
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(ImplicitReceiverUtilsKt.asTowerDataElement((FirScope) it.next(), false));
                    }
                    context.addNonLocalTowerDataElements(arrayList);
                    context.getContainers().add(file);
                    try {
                        T t = (T) f.invoke();
                        InlineMarker.finallyStart(1);
                        context.getContainers().removeLast();
                        InlineMarker.finallyEnd(1);
                        InlineMarker.finallyStart(1);
                        context.replaceTowerDataContext(towerDataContext);
                        InlineMarker.finallyEnd(1);
                        InlineMarker.finallyStart(1);
                        int size2 = fileImportsScope.size() - size;
                        while (i < size2) {
                            fileImportsScope.remove(fileImportsScope.size() - 1);
                            i++;
                        }
                        InlineMarker.finallyEnd(1);
                        InlineMarker.finallyStart(1);
                        this.currentFile = firFile;
                        InlineMarker.finallyEnd(1);
                        return t;
                    } catch (Throwable th) {
                        try {
                            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
                            throw new KotlinNothingValueException();
                        } catch (Throwable th2) {
                            InlineMarker.finallyStart(1);
                            context.getContainers().removeLast();
                            InlineMarker.finallyEnd(1);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    InlineMarker.finallyStart(1);
                    context.replaceTowerDataContext(towerDataContext);
                    InlineMarker.finallyEnd(1);
                    throw th3;
                }
            } catch (Throwable th4) {
                InlineMarker.finallyStart(1);
                int size3 = fileImportsScope.size() - size;
                while (i < size3) {
                    fileImportsScope.remove(fileImportsScope.size() - 1);
                    i++;
                }
                InlineMarker.finallyEnd(1);
                throw th4;
            }
        } catch (Throwable th5) {
            InlineMarker.finallyStart(1);
            this.currentFile = firFile;
            InlineMarker.finallyEnd(1);
            throw th5;
        }
    }

    public final void withFileAndFileScopes(FirFile file, Function0<Unit> action) {
        file.getClass();
        action.getClass();
        FirFile firFile = this.currentFile;
        this.currentFile = file;
        try {
            BodyResolveContext context = getArgumentsTransformer().getContext();
            FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components = getArgumentsTransformer().getComponents();
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
                        setScopes(ImportingScopesKt.createImportingScopes(file, getSession(), getScopeSession(), getComputationSession().getUseCacheForImportScope()));
                        PersistentList<FirDeclaration> persistentListBeforeTransformingChildren = beforeTransformingChildren(file);
                        try {
                            action.invoke();
                            Unit unit = Unit.INSTANCE;
                            afterTransformingChildren(persistentListBeforeTransformingChildren);
                            context.getContainers().removeLast();
                            context.replaceTowerDataContext(towerDataContext);
                            int size2 = fileImportsScope.size() - size;
                            while (i < size2) {
                                fileImportsScope.remove(fileImportsScope.size() - 1);
                                i++;
                            }
                            this.currentFile = firFile;
                        } catch (Throwable th) {
                            afterTransformingChildren(persistentListBeforeTransformingChildren);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        try {
                            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th2);
                            throw new KotlinNothingValueException();
                        } catch (Throwable th3) {
                            context.getContainers().removeLast();
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    context.replaceTowerDataContext(towerDataContext);
                    throw th4;
                }
            } catch (Throwable th5) {
                int size3 = fileImportsScope.size() - size;
                while (i < size3) {
                    fileImportsScope.remove(fileImportsScope.size() - 1);
                    i++;
                }
                throw th5;
            }
        } catch (Throwable th6) {
            this.currentFile = firFile;
            throw th6;
        }
    }

    public final <T> T withFileScopes(FirFile file, Function0<? extends T> f) {
        file.getClass();
        f.getClass();
        setScopes(ImportingScopesKt.createImportingScopes(file, getSession(), getScopeSession(), getComputationSession().getUseCacheForImportScope()));
        return (T) f.invoke();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        element.getClass();
        return element;
    }

    private final void calculateDeprecations(FirCallableDeclaration callableDeclaration) {
        if (Intrinsics.areEqual(callableDeclaration.getDeprecationsProvider(), UnresolvedDeprecationProvider.INSTANCE)) {
            callableDeclaration.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(callableDeclaration, this.session));
        }
    }

    private final FirQualifierPart createDeepCopy(FirQualifierPart firQualifierPart) {
        FirTypeArgumentListImpl firTypeArgumentListImpl = new FirTypeArgumentListImpl(firQualifierPart.getTypeArgumentList().getSource());
        List<FirTypeProjection> typeArguments = firQualifierPart.getTypeArgumentList().getTypeArguments();
        List<FirTypeProjection> typeArguments2 = firTypeArgumentListImpl.getTypeArguments();
        Iterator<T> it = typeArguments.iterator();
        while (it.hasNext()) {
            typeArguments2.add(createDeepCopy((FirTypeProjection) it.next()));
        }
        return new FirQualifierPartImpl(firQualifierPart.getSource(), firQualifierPart.getName(), firTypeArgumentListImpl);
    }

    public /* synthetic */ AbstractFirSpecificAnnotationResolveTransformer(FirSession firSession, ScopeSession scopeSession, CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationsComputationSession, List list, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, compilerRequiredAnnotationsComputationSession, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : bodyResolveContext);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, Void data) {
        annotationCall.getClass();
        FirTypeRef annotationTypeRef = annotationCall.getAnnotationTypeRef();
        if (annotationTypeRef instanceof FirUserTypeRef) {
            FirUserTypeRef firUserTypeRef = (FirUserTypeRef) annotationTypeRef;
            if (shouldRunAnnotationResolve(firUserTypeRef)) {
                transformAnnotationCall(annotationCall, firUserTypeRef);
                return annotationCall;
            }
        }
        return annotationCall;
    }
}
