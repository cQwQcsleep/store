package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.AllowedToUsedOnlyInK1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContext;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextFactory;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSourceElement;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirRetentionAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SessionHolderImpl;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualMatchingContextImpl;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionWithoutNameSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ExpectActualUtilsKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.mpp.CallableSymbolMarker;
import org.jetbrains.kotlin.mpp.ClassLikeSymbolMarker;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.mpp.FunctionSymbolMarker;
import org.jetbrains.kotlin.mpp.PropertySymbolMarker;
import org.jetbrains.kotlin.mpp.RegularClassSymbolMarker;
import org.jetbrains.kotlin.mpp.SourceElementMarker;
import org.jetbrains.kotlin.mpp.TypeAliasSymbolMarker;
import org.jetbrains.kotlin.mpp.TypeParameterSymbolMarker;
import org.jetbrains.kotlin.mpp.TypeRefMarker;
import org.jetbrains.kotlin.mpp.ValueParameterSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualCollectionArgumentsCompatibilityCheckStrategy;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualMatchingContext;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.AnnotationMarker;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.CapturedTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;
import org.jetbrains.kotlin.types.model.DefinitelyNotNullTypeMarker;
import org.jetbrains.kotlin.types.model.DynamicTypeMarker;
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.ObsoleteTypeKind;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContext;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.kotlin.utils.CollectionsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010(\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u0004\u008b\u0003\u008c\u0003B!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012*\u00020\u0013H\u0002J\u0010\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0014*\u00020\u0015H\u0002J\u0010\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0016*\u00020\u0017H\u0002J\f\u0010\u0011\u001a\u00020\u0018*\u00020\u0019H\u0002J\f\u0010\u0011\u001a\u00020\u001a*\u00020\u001bH\u0002J\f\u0010\u0011\u001a\u00020\u001c*\u00020\u001dH\u0002J\u0010\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u001e*\u00020\u001fH\u0002J\f\u0010\u0011\u001a\u00020 *\u00020!H\u0002J\f\u0010\u0011\u001a\u00020\"*\u00020#H\u0002J\u000e\u00102\u001a\u0004\u0018\u00010!*\u00020#H\u0016J,\u0010b\u001a\u00020c2\u0018\u0010d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0e0=2\b\u0010f\u001a\u0004\u0018\u00010cH\u0016J\u001e\u0010q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120=*\u00020!2\u0006\u0010r\u001a\u00020\bH\u0016J\u001e\u0010s\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140=*\u00020!2\u0006\u0010r\u001a\u00020\bH\u0016J\u001e\u0010t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140=*\u00020!2\u0006\u0010u\u001a\u00020.H\u0016J\u001e\u0010v\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140=*\u00020!2\u0006\u0010u\u001a\u00020.H\u0016J&\u0010w\u001a\b\u0012\u0004\u0012\u00020y0x*\u0006\u0012\u0002\b\u00030z2\u0006\u0010{\u001a\u00020\u00062\u0006\u0010|\u001a\u00020\u0004H\u0016J#\u0010}\u001a\u00020~2\u000f\u0010\u007f\u001a\u000b\u0012\u0006\b\u0000\u0012\u00020y0\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0082\u0001H\u0002J+\u0010\u0083\u0001\u001a\u00020~*\u00030\u0084\u00012\u0013\u0010\u007f\u001a\u000f\u0012\n\b\u0000\u0012\u0006\u0012\u0002\b\u00030\u00140\u0080\u00012\u0006\u0010u\u001a\u00020.H\u0002J\u0013\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020.0=*\u00020!H\u0016J\u0013\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130=*\u00020!H\u0016J\u001e\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150=*\u00020\u00172\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010!H\u0016J\r\u0010£\u0001\u001a\u00020\b*\u00020\u0015H\u0016J1\u0010¯\u0001\u001a\u00020\b2\t\u0010°\u0001\u001a\u0004\u0018\u00010h2\t\u0010±\u0001\u001a\u0004\u0018\u00010h2\u0007\u0010²\u0001\u001a\u00020\b2\u0007\u0010³\u0001\u001a\u00020\bH\u0016J\u000f\u0010´\u0001\u001a\u00030µ\u0001*\u00030µ\u0001H\u0002J\u001b\u0010¶\u0001\u001a\u00020\b2\u0007\u0010·\u0001\u001a\u00020h2\u0007\u0010¸\u0001\u001a\u00020hH\u0016J\u000f\u0010¹\u0001\u001a\u00030º\u0001*\u00030º\u0001H\u0002J\u000f\u0010»\u0001\u001a\u00030º\u0001*\u00030º\u0001H\u0002J\u001f\u0010¼\u0001\u001a\u0005\u0018\u00010µ\u00012\b\u0010½\u0001\u001a\u00030µ\u00012\u0007\u0010¾\u0001\u001a\u00020 H\u0002J\n\u0010¿\u0001\u001a\u00030À\u0001H\u0002J\r\u0010Á\u0001\u001a\u00020\b*\u00020!H\u0016J\u0018\u0010Â\u0001\u001a\u00020\b*\u00020\u00152\t\u0010Ã\u0001\u001a\u0004\u0018\u00010!H\u0016J'\u0010Ð\u0001\u001a\u00020\b2\b\u0010Ñ\u0001\u001a\u00030Í\u00012\b\u0010Ò\u0001\u001a\u00030Í\u00012\b\u0010Ó\u0001\u001a\u00030Ô\u0001H\u0016J1\u0010Ø\u0001\u001a\u00020~2\u0007\u0010Ù\u0001\u001a\u00020\u00132\u0007\u0010Ú\u0001\u001a\u00020\u00132\t\u0010Û\u0001\u001a\u0004\u0018\u00010!2\t\u0010Ü\u0001\u001a\u0004\u0018\u00010!H\u0016JE\u0010Ý\u0001\u001a\u00020~2\u0007\u0010Ù\u0001\u001a\u00020\u00132\u001b\u0010Þ\u0001\u001a\u0016\u0012\u0005\u0012\u00030à\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130=0ß\u00012\t\u0010Û\u0001\u001a\u0004\u0018\u00010!2\t\u0010Ü\u0001\u001a\u0004\u0018\u00010!H\u0016J:\u0010á\u0001\u001a\u00020~*\u00020 2\u000b\u0010â\u0001\u001a\u0006\u0012\u0002\b\u00030\u00122\u000b\u0010ã\u0001\u001a\u0006\u0012\u0002\b\u00030\u00122\u0007\u0010ä\u0001\u001a\u00020 2\b\u0010å\u0001\u001a\u00030æ\u0001H\u0002J\u0012\u0010é\u0001\u001a\u00020\b2\u0007\u0010ã\u0001\u001a\u00020\u0013H\u0016J6\u0010ê\u0001\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0005\u0012\u00030æ\u00010ß\u00012\u0007\u0010ë\u0001\u001a\u00020!2\u0007\u0010ì\u0001\u001a\u00020!2\u0007\u0010ã\u0001\u001a\u00020\u0013H\u0016J\u000e\u0010í\u0001\u001a\u00030î\u0001*\u00020\u0013H\u0016J\u000e\u0010&\u001a\u0004\u0018\u00010%*\u00020lH\u0016J7\u0010ï\u0001\u001a\u00020~2\u0007\u0010ð\u0001\u001a\u00020\u00132\u0007\u0010ñ\u0001\u001a\u00020\u00132\u0007\u0010ò\u0001\u001a\u00020l2\u0007\u0010ó\u0001\u001a\u00020l2\b\u0010ô\u0001\u001a\u00030õ\u0001H\u0016JE\u0010ö\u0001\u001a\u00020~2\u000b\u0010ð\u0001\u001a\u0006\u0012\u0002\b\u00030\u00122\u000b\u0010ñ\u0001\u001a\u0006\u0012\u0002\b\u00030\u00122\n\u0010ò\u0001\u001a\u0005\u0018\u00010÷\u00012\n\u0010ó\u0001\u001a\u0005\u0018\u00010÷\u00012\b\u0010ô\u0001\u001a\u00030õ\u0001H\u0002J\u001e\u0010ø\u0001\u001a\u00020\b2\b\u0010ù\u0001\u001a\u00030ú\u00012\b\u0010û\u0001\u001a\u00030ú\u0001H\u0096\u0001J\u000f\u0010ü\u0001\u001a\u00030ý\u0001*\u00020hH\u0096\u0001J\u0010\u0010þ\u0001\u001a\u00030ÿ\u0001*\u00030\u0080\u0002H\u0096\u0001J\u0012\u0010\u0081\u0002\u001a\u0005\u0018\u00010\u0082\u0002*\u00030\u0088\u0001H\u0096\u0001J0\u0010\u0081\u0002\u001a\u00030\u0082\u0002*\u00030\u0082\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0012\u0010\u0089\u0002\u001a\u0005\u0018\u00010\u0082\u0002*\u00030\u0080\u0002H\u0096\u0001J0\u0010\u0089\u0002\u001a\u00030\u0082\u0002*\u00030\u0082\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0012\u0010\u008a\u0002\u001a\u0005\u0018\u00010\u008b\u0002*\u00030\u0080\u0002H\u0096\u0001J0\u0010\u008a\u0002\u001a\u00030\u008b\u0002*\u00030\u008b\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0012\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u008d\u0002*\u00030\u008e\u0002H\u0096\u0001J0\u0010\u008c\u0002\u001a\u00030\u008d\u0002*\u00030\u008d\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0011\u0010\u008f\u0002\u001a\u0005\u0018\u00010\u008e\u0002*\u00020hH\u0096\u0001J0\u0010\u008f\u0002\u001a\u00030\u008e\u0002*\u00030\u008e\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0011\u0010\u0090\u0002\u001a\u0005\u0018\u00010\u0080\u0002*\u00020hH\u0096\u0001J0\u0010\u0090\u0002\u001a\u00030\u0080\u0002*\u00030\u0080\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u000f\u0010\u0091\u0002\u001a\u00030\u0092\u0002*\u00020hH\u0096\u0001J!\u0010\u0093\u0002\u001a\u0005\u0018\u00010\u0080\u00022\b\u0010\u0094\u0002\u001a\u00030\u0080\u00022\b\u0010\u0095\u0002\u001a\u00030\u0096\u0002H\u0096\u0001J\u0015\u0010\u0097\u0002\u001a\u0004\u0018\u00010h2\u0007\u0010\u0094\u0002\u001a\u00020hH\u0096\u0001J\u0010\u0010\u0098\u0002\u001a\u00030\u0096\u0002*\u00030\u0082\u0002H\u0096\u0001J\n\u0010\u0099\u0002\u001a\u00020cH\u0096\u0001J\"\u0010\u009a\u0002\u001a\u000b\u0012\u0005\u0012\u00030\u0088\u0001\u0018\u00010=*\u00030\u0080\u00022\b\u0010\u009b\u0002\u001a\u00030ú\u0001H\u0096\u0001J\u001a\u0010\u009c\u0002\u001a\u00030\u0092\u0002*\u00030ÿ\u00012\b\u0010\u009d\u0002\u001a\u00030ý\u0001H\u0096\u0003J\u0019\u0010\u009e\u0002\u001a\u00030\u0092\u0002*\u00020h2\b\u0010\u009d\u0002\u001a\u00030ý\u0001H\u0096\u0001J\u001c\u0010\u009f\u0002\u001a\u0005\u0018\u00010\u0092\u0002*\u00030\u0080\u00022\b\u0010\u009d\u0002\u001a\u00030ý\u0001H\u0096\u0001J\u0015\u0010 \u0002\u001a\t\u0012\u0005\u0012\u00030\u0092\u00020=*\u00020hH\u0096\u0001J\u0015\u0010¡\u0002\u001a\t\u0012\u0005\u0012\u00030¢\u00020=*\u00020hH\u0096\u0001J\u001a\u0010£\u0002\u001a\u00030¤\u0002*\u00030ú\u00012\b\u0010\u009d\u0002\u001a\u00030ý\u0001H\u0096\u0001J\u0016\u0010¥\u0002\u001a\t\u0012\u0005\u0012\u00030¤\u00020=*\u00030ú\u0001H\u0096\u0001J\u0011\u0010¦\u0002\u001a\u0004\u0018\u00010h*\u00030\u0092\u0002H\u0096\u0001J\u0010\u0010§\u0002\u001a\u00030ú\u0001*\u00030¤\u0002H\u0096\u0001J\u0012\u0010¨\u0002\u001a\u0005\u0018\u00010¤\u0002*\u00030ú\u0001H\u0096\u0001J\u0019\u0010©\u0002\u001a\u00020h*\u00030¤\u00022\b\u0010\u009d\u0002\u001a\u00030ý\u0001H\u0096\u0001J\u0015\u0010ª\u0002\u001a\b\u0012\u0004\u0012\u00020h0=*\u00030¤\u0002H\u0096\u0001J\u0010\u0010«\u0001\u001a\u00030«\u0002*\u00030\u0092\u0002H\u0096\u0001J\u0010\u0010«\u0001\u001a\u00030«\u0002*\u00030¤\u0002H\u0096\u0001J\u000e\u0010¬\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u001b\u0010\u00ad\u0002\u001a\u00020\b*\u00030¤\u00022\n\u0010®\u0002\u001a\u0005\u0018\u00010ú\u0001H\u0096\u0001J\u001e\u0010¯\u0002\u001a\u00020\b2\b\u0010°\u0002\u001a\u00030\u0080\u00022\b\u0010±\u0002\u001a\u00030\u0080\u0002H\u0096\u0001J\u0019\u0010²\u0002\u001a\u00020h2\r\u0010³\u0002\u001a\b\u0012\u0004\u0012\u00020h0xH\u0096\u0001J\u001b\u0010²\u0002\u001a\u00030\u0088\u00012\u000e\u0010³\u0002\u001a\t\u0012\u0005\u0012\u00030\u0088\u00010xH\u0096\u0001J\u000f\u0010´\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010µ\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010¶\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010·\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010¸\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010¹\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010º\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010»\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010¼\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010¼\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010½\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010¾\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010¿\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010¿\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010À\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010Á\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010Â\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010Ã\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010Ä\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010Å\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Æ\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010Ç\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010È\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000f\u0010É\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010Ê\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u0013\u0010Ë\u0002\u001a\u00020\b*\u00020hH\u0097\u0001b\u0003\bÌ\u0002J\u000e\u0010Í\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010Î\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010Ï\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010Ð\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010Ñ\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u0017\u0010Ñ\u0002\u001a\u00020\b*\u00020h2\u0007\u0010Ò\u0002\u001a\u00020\bH\u0096\u0001J\u000f\u0010Ó\u0002\u001a\u00020\b*\u00030\u0082\u0002H\u0096\u0001J\u000f\u0010Ô\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Ô\u0002\u001a\u00020\b*\u00030\u0088\u0001H\u0096\u0001J\u0014\u0010Õ\u0002\u001a\u00020\b*\u00030\u0082\u0002H\u0097\u0001b\u0003\bÖ\u0002J\u000e\u0010×\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010Ø\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000f\u0010Ù\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Ú\u0002\u001a\u00020\b*\u00030\u0092\u0002H\u0096\u0001J\u000f\u0010Û\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Ü\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Ý\u0002\u001a\u00020\b*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010Þ\u0002\u001a\u00020\b*\u00030ú\u0001H\u0096\u0001J\u000e\u0010ß\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u000e\u0010à\u0002\u001a\u00020\b*\u00020hH\u0096\u0001J\u0017\u0010á\u0002\u001a\n\u0012\u0005\u0012\u00030\u0092\u00020â\u0002*\u00030ÿ\u0001H\u0096\u0003J\u0010\u0010ã\u0002\u001a\u00030\u0080\u0002*\u00030\u008e\u0002H\u0096\u0001J0\u0010ä\u0002\u001a\u00030\u0080\u0002*\u00030\u0080\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u000f\u0010ä\u0002\u001a\u00030\u0080\u0002*\u00020hH\u0096\u0001J\u0011\u0010å\u0002\u001a\u0004\u0018\u00010h*\u00030\u0082\u0002H\u0096\u0001J0\u0010æ\u0002\u001a\u00030\u008b\u0002*\u00030\u008b\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u000e\u0010æ\u0002\u001a\u00020h*\u00020hH\u0096\u0001J\u0017\u0010æ\u0002\u001a\u00020h*\u00020h2\u0007\u0010ç\u0002\u001a\u00020\bH\u0096\u0001J\u0010\u0010æ\u0002\u001a\u00030\u0080\u0002*\u00030\u0080\u0002H\u0096\u0001J\u0010\u0010è\u0002\u001a\u00030\u0088\u0001*\u00030\u008b\u0002H\u0096\u0001J0\u0010é\u0002\u001a\u00030\u0088\u0001*\u00030\u0088\u0001H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u0010\u0010é\u0002\u001a\u00030\u0088\u0001*\u00030\u0080\u0002H\u0096\u0001J\u0010\u0010ê\u0002\u001a\u00030ý\u0001*\u00030ú\u0001H\u0096\u0001J\u0015\u0010ë\u0002\u001a\b\u0012\u0004\u0012\u00020h0x*\u00030\u0080\u0002H\u0096\u0001J\u0010\u0010ì\u0002\u001a\u00030\u0092\u0002*\u00030í\u0002H\u0096\u0001J\u0019\u0010î\u0002\u001a\u00030\u0092\u0002*\u00030\u0092\u00022\u0007\u0010ï\u0002\u001a\u00020hH\u0096\u0001J\u0017\u0010ð\u0002\u001a\u00020h*\u00020c2\u0007\u0010\u0094\u0002\u001a\u00020hH\u0096\u0001J\u0010\u0010ñ\u0002\u001a\u00030ý\u0001*\u00030ÿ\u0001H\u0096\u0001J\u0015\u0010ò\u0002\u001a\u00030ó\u00022\b\u0010\u0094\u0002\u001a\u00030\u0080\u0002H\u0096\u0001J\u0015\u0010ô\u0002\u001a\b\u0012\u0004\u0012\u00020h0x*\u00030ú\u0001H\u0096\u0001J\u0010\u0010õ\u0002\u001a\u00030í\u0002*\u00030\u0082\u0002H\u0096\u0001J\u0010\u0010õ\u0002\u001a\u00030ú\u0001*\u00030\u0080\u0002H\u0096\u0001J\u000f\u0010õ\u0002\u001a\u00030ú\u0001*\u00020hH\u0096\u0001J!\u0010ö\u0002\u001a\u00020c2\u0015\u0010÷\u0002\u001a\u0010\u0012\u0005\u0012\u00030ú\u0001\u0012\u0004\u0012\u00020h0ß\u0001H\u0096\u0001J\u0010\u0010ø\u0002\u001a\u00030ú\u0001*\u00030ú\u0001H\u0096\u0001J\u0010\u0010ù\u0002\u001a\u00030\u0080\u0002*\u00030\u008e\u0002H\u0096\u0001J\u0010\u0010ú\u0002\u001a\u00030ý\u0001*\u00030¤\u0002H\u0096\u0001J0\u0010û\u0002\u001a\u00030\u0080\u0002*\u00030\u0080\u0002H\u0097\u0001b\u001e\b\u0083\u0002\u0012\n\b\u0084\u0002\u0012\u0005\b\b(\u0085\u0002\u0012\r\b\u0086\u0002\u0012\b\b\n0\u0087\u00028\u0088\u0002J\u000f\u0010û\u0002\u001a\u00030\u0080\u0002*\u00020hH\u0096\u0001J!\u0010ü\u0002\u001a\u00020h*\u00020h2\b\u0010ý\u0002\u001a\u00030þ\u00022\u0007\u0010ï\u0002\u001a\u00020hH\u0096\u0001J\u0017\u0010ÿ\u0002\u001a\u00020h*\u00020h2\u0007\u0010\u0080\u0003\u001a\u00020\bH\u0096\u0001J\u0019\u0010ÿ\u0002\u001a\u00030\u0080\u0002*\u00030\u0080\u00022\u0007\u0010\u0080\u0003\u001a\u00020\bH\u0096\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010$\u001a\u00020%*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0018\u0010$\u001a\u00020%*\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010(R\u0018\u0010)\u001a\u00020**\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0018\u0010-\u001a\u00020.*\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0018\u0010-\u001a\u00020.*\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00101R\u0018\u00103\u001a\u000204*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0018\u00107\u001a\u00020\b*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0018\u00109\u001a\u00020\b*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u00108R\u0018\u0010:\u001a\u00020\b*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u00108R\u0018\u0010;\u001a\u00020\b*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00108R\u001e\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001d0=*\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0018\u0010@\u001a\u00020A*\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0018\u0010D\u001a\u00020E*\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0018\u0010@\u001a\u00020A*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010HR\u0018\u0010D\u001a\u00020E*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010IR\u0014\u0010J\u001a\u00020KX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u0018\u0010N\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0018\u0010P\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bP\u0010OR\u0018\u0010Q\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010OR\u0018\u0010R\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bR\u0010OR\u0018\u0010S\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010OR\u0018\u0010T\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010OR\u0018\u0010U\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010OR\u0018\u0010V\u001a\u00020\b*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0018\u0010X\u001a\u00020\b*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010WR\u0018\u0010Y\u001a\u00020\b*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010WR\u001a\u0010Z\u001a\u0004\u0018\u00010\u0017*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u001a\u0010]\u001a\u0004\u0018\u00010\u0017*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b^\u0010\\R\u001e\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001b0=*\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001e\u0010g\u001a\b\u0012\u0004\u0012\u00020h0=*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u001e\u0010k\u001a\b\u0012\u0004\u0012\u00020l0=*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bm\u0010jR\u0018\u0010n\u001a\u00020h*\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010pR\u001e\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001*\u00020\u00158VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001R\u001d\u0010\u008b\u0001\u001a\u0004\u0018\u00010h*\u00020\u00158VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001d\u0010\u008e\u0001\u001a\u0004\u0018\u00010l*\u00020\u00158VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001b\u0010\u0091\u0001\u001a\u00020h*\u00020\u00158VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u008d\u0001R\u001b\u0010\u0093\u0001\u001a\u00020l*\u00020\u00158VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0090\u0001R\u001f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001d0=*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b>\u0010\u0095\u0001R!\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0=*\u00020\u00178VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001b0=*\u00020\u00178VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b`\u0010\u009a\u0001R\u001b\u0010\u009b\u0001\u001a\u00020\b*\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u001b\u0010\u009d\u0001\u001a\u00020\b*\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009c\u0001R\u001b\u0010\u009e\u0001\u001a\u00020\b*\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009c\u0001R\u001b\u0010\u009f\u0001\u001a\u00020\b*\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b \u0001\u0010\u009c\u0001R\u001b\u0010¡\u0001\u001a\u00020\b*\u00020\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¢\u0001\u0010\u009c\u0001R!\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020h0=*\u00020\u001d8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¥\u0001\u0010¦\u0001R!\u0010§\u0001\u001a\b\u0012\u0004\u0012\u00020l0=*\u00020\u001d8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¨\u0001\u0010¦\u0001R\u001c\u0010©\u0001\u001a\u00030ª\u0001*\u00020\u001d8VX\u0096\u0004¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u001b\u0010\u00ad\u0001\u001a\u00020\b*\u00020\u001d8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u001a\u0010Ä\u0001\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010OR\u001a\u0010Å\u0001\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÆ\u0001\u0010OR\u001a\u0010Ç\u0001\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÈ\u0001\u0010OR\u001a\u0010É\u0001\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÉ\u0001\u0010OR\u001a\u0010Ê\u0001\u001a\u00020\b*\u00020\u00158VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010OR\"\u0010Ì\u0001\u001a\t\u0012\u0005\u0012\u00030Í\u00010=*\u00020\u00138VX\u0096\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ï\u0001R\u001b\u0010Õ\u0001\u001a\u00020\b*\u00020\u00138VX\u0096\u0004¢\u0006\b\u001a\u0006\bÖ\u0001\u0010×\u0001R\u0016\u0010ç\u0001\u001a\u00020\bX\u0096D¢\u0006\t\n\u0000\u001a\u0005\bè\u0001\u0010\rRW\u0010\u0081\u0003\u001aB\u0012\u0015\u0012\u00130h¢\u0006\u000e\b\u0083\u0003\u0012\t\bu\u0012\u0005\b\b(¸\u0001\u0012\u0015\u0012\u00130h¢\u0006\u000e\b\u0083\u0003\u0012\t\bu\u0012\u0005\b\b(·\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0082\u0003j\u0005\u0018\u0001`\u0084\u00038VX\u0096\u0005¢\u0006\b\u001a\u0006\b\u0085\u0003\u0010\u0086\u0003R\u001d\u0010\u0087\u0003\u001a\u0005\u0018\u00010¤\u0002*\u00030\u0088\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0089\u0003\u0010\u008a\u0003¨\u0006\u008d\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatchingContextImpl;", "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "Lorg/jetbrains/kotlin/types/model/TypeSystemContext;", "actualSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "actualScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "allowedWritingMemberExpectForActualMapping", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Z)V", "shouldCheckDefaultParams", "getShouldCheckDefaultParams", "()Z", "expectScopeSession", "getExpectScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "asSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/mpp/FunctionSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/mpp/PropertySymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "Lorg/jetbrains/kotlin/mpp/ValueParameterSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/mpp/ClassLikeSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/mpp/TypeAliasSymbolMarker;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "(Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;)Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/mpp/TypeAliasSymbolMarker;)Lorg/jetbrains/kotlin/name/ClassId;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getCallableId", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/name/CallableId;", "parameterName", "Lorg/jetbrains/kotlin/name/Name;", "getParameterName", "(Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;)Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/mpp/ValueParameterSymbolMarker;)Lorg/jetbrains/kotlin/name/Name;", "expandToRegularClass", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "(Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;)Lorg/jetbrains/kotlin/descriptors/ClassKind;", "isCompanion", "(Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;)Z", "isInner", "isInlineOrValue", "isFun", "typeParameters", Argument.Delimiters.none, "getTypeParameters", "(Lorg/jetbrains/kotlin/mpp/ClassLikeSymbolMarker;)Ljava/util/List;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality", "(Lorg/jetbrains/kotlin/mpp/ClassLikeSymbolMarker;)Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "(Lorg/jetbrains/kotlin/mpp/ClassLikeSymbolMarker;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/descriptors/Modality;", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "mustUseMatcher", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext$MustUseMatcher;", "getMustUseMatcher", "()Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext$MustUseMatcher;", "isExpect", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Z", "isInline", "isSuspend", "isExternal", "isInfix", "isOperator", "isTailrec", "isVar", "(Lorg/jetbrains/kotlin/mpp/PropertySymbolMarker;)Z", "isLateinit", "isConst", "getter", "getGetter", "(Lorg/jetbrains/kotlin/mpp/PropertySymbolMarker;)Lorg/jetbrains/kotlin/mpp/FunctionSymbolMarker;", "setter", "getSetter", "contextParameters", "getContextParameters", "(Lorg/jetbrains/kotlin/mpp/PropertySymbolMarker;)Ljava/util/List;", "createExpectActualTypeParameterSubstitutor", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "expectActualTypeParameters", "Lkotlin/Pair;", "parentSubstitutor", "superTypes", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getSuperTypes", "(Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;)Ljava/util/List;", "superTypesRefs", "Lorg/jetbrains/kotlin/mpp/TypeRefMarker;", "getSuperTypesRefs", "defaultType", "getDefaultType", "(Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;)Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "collectAllMembers", "isActualDeclaration", "collectAllStaticCallables", "getCallablesForExpectClass", ModuleXmlParser.NAME, "getStaticCallablesForExpectClass", "getConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "scopeSession", "session", "getConstructorsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "getMembersTo", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "collectEnumEntryNames", "collectEnumEntries", "dispatchReceiverType", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "getDispatchReceiverType", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "extensionReceiverType", "getExtensionReceiverType", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "extensionReceiverTypeRef", "getExtensionReceiverTypeRef", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Lorg/jetbrains/kotlin/mpp/TypeRefMarker;", "returnType", "getReturnType", "returnTypeRef", "getReturnTypeRef", "(Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;)Ljava/util/List;", "allRecursivelyOverriddenDeclarationsIncludingSelf", "containingClass", "valueParameters", "getValueParameters", "(Lorg/jetbrains/kotlin/mpp/FunctionSymbolMarker;)Ljava/util/List;", "isVararg", "(Lorg/jetbrains/kotlin/mpp/ValueParameterSymbolMarker;)Z", "isNoinline", "isCrossinline", "hasDefaultValue", "getHasDefaultValue", "hasDefaultValueNonRecursive", "getHasDefaultValueNonRecursive", "isAnnotationConstructor", "bounds", "getBounds", "(Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;)Ljava/util/List;", "boundsTypeRefs", "getBoundsTypeRefs", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance", "(Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;)Lorg/jetbrains/kotlin/types/Variance;", "isReified", "(Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;)Z", "areCompatibleExpectActualTypes", "expectType", "actualType", "parameterOfAnnotationComparisonMode", "dynamicTypesEqualToAnything", "convertToArrayWithOutProjections", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "isSubtypeOf", "superType", "subType", "actualize", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "actualizeTypeArguments", "tryExpandExpectNestedClassActualizedViaTypealias", "expectNestedClassType", "expectNestedClassSymbol", "createTypeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "isSamInterface", "isFakeOverride", "containingExpectClass", "isDelegatedMember", "hasStableParameterNames", "getHasStableParameterNames", "shouldMatchByParameterNames", "getShouldMatchByParameterNames", "isJavaField", "canBeActualizedByJavaField", "getCanBeActualizedByJavaField", "annotations", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext$AnnotationCallInfo;", "getAnnotations", "(Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;)Ljava/util/List;", "areAnnotationArgumentsEqual", "expectAnnotation", "actualAnnotation", "collectionArgumentsCompatibilityCheckStrategy", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy;", "hasSourceAnnotationsErased", "getHasSourceAnnotationsErased", "(Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;)Z", "onMatchedMembers", "expectSymbol", "actualSymbol", "containingExpectClassSymbol", "containingActualClassSymbol", "onMismatchedMembersFromClassScope", "actualSymbolsByIncompatibility", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility$Mismatch;", "addMemberExpectForActualMapping", "expectMember", "actualMember", "expectClassSymbol", "compatibility", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", "checkClassScopesForAnnotationCompatibility", "getCheckClassScopesForAnnotationCompatibility", "skipCheckingAnnotationsOfActualClassMember", "findPotentialExpectClassMembersForActual", "expectClass", "actualClass", "getSourceElement", "Lorg/jetbrains/kotlin/mpp/SourceElementMarker;", "checkAnnotationsOnTypeRefAndArguments", "expectContainingSymbol", "actualContainingSymbol", "expectTypeRef", "actualTypeRef", "checker", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext$AnnotationsCheckerCallback;", "checkAnnotationsOnTypeRefAndArgumentsImpl", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "areEqualTypeConstructors", "c1", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "c2", "argumentsCount", Argument.Delimiters.none, "asArgumentList", "Lorg/jetbrains/kotlin/types/model/TypeArgumentListMarker;", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "asCapturedType", "Lorg/jetbrains/kotlin/types/model/CapturedTypeMarker;", "Lkotlin/Deprecated;", "message", "This call does effectively nothing, please drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", "asCapturedTypeUnwrappingDnn", "asDefinitelyNotNullType", "Lorg/jetbrains/kotlin/types/model/DefinitelyNotNullTypeMarker;", "asDynamicType", "Lorg/jetbrains/kotlin/types/model/DynamicTypeMarker;", "Lorg/jetbrains/kotlin/types/model/FlexibleTypeMarker;", "asFlexibleType", "asRigidType", "asTypeArgument", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "captureFromArguments", ModuleXmlParser.TYPE, "status", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "captureFromExpression", "captureStatus", "createEmptySubstitutor", "fastCorrespondingSupertypes", "constructor", "get", "index", "getArgument", "getArgumentOrNull", "getArguments", "getAttributes", "Lorg/jetbrains/kotlin/types/model/AnnotationMarker;", "getParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "getParameters", "getType", "getTypeConstructor", "getTypeParameterClassifier", "getUpperBound", "getUpperBounds", "Lorg/jetbrains/kotlin/types/model/TypeVariance;", "hasFlexibleNullability", "hasRecursiveBounds", "selfConstructor", "identicalArguments", "a", "b", "intersectTypes", "types", "isAnonymous", "isAnyConstructor", "isArrayConstructor", "isCapturedDynamic", "isCapturedType", "isClassType", "isClassTypeConstructor", "isCommonFinalClassConstructor", "isDefinitelyNotNullType", "isDenotable", "isDynamic", "isError", "isFlexible", "isFlexibleNothing", "isFlexibleWithDifferentTypeConstructors", "isIntegerConstantOperatorTypeConstructor", "isIntegerLiteralConstantTypeConstructor", "isIntegerLiteralType", "isIntegerLiteralTypeConstructor", "isInterface", "isIntersection", "isLocalType", "isMarkedNullable", "isNotNullTypeParameter", "Lorg/jetbrains/kotlin/types/model/ObsoleteTypeKind;", "isNothing", "isNothingConstructor", "isNullableAny", "isNullableNothing", "isNullableType", "considerTypeVariableBounds", "isOldCapturedType", "isPrimitiveType", "isProjectionNotNull", "Lorg/jetbrains/kotlin/builtins/functions/AllowedToUsedOnlyInK1;", "isRawType", "isRigidType", "isSingleClassifierType", "isStarProjection", "isStubType", "isStubTypeForBuilderInference", "isStubTypeForVariableInSubtyping", "isTypeParameterTypeConstructor", "isTypeVariableType", "isUninferredParameter", "iterator", Argument.Delimiters.none, "lowerBound", "lowerBoundIfFlexible", "lowerType", "makeDefinitelyNotNullOrNotNull", "preserveAttributes", "original", "originalIfDefinitelyNotNullable", "parametersCount", "possibleIntegerTypes", "projection", "Lorg/jetbrains/kotlin/types/model/CapturedTypeConstructorMarker;", "replaceType", "newType", "safeSubstitute", "size", "substitutionSupertypePolicy", "Lorg/jetbrains/kotlin/types/TypeCheckerState$SupertypesPolicy;", "supertypes", "typeConstructor", "typeSubstitutorByTypeConstructor", "map", "unwrapStubTypeVariableConstructor", "upperBound", "upperBoundCount", "upperBoundIfFlexible", "withNewTypeSince", "languageFeature", Argument.Delimiters.none, "withNullability", "nullable", "customSubtypingCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "Lorg/jetbrains/kotlin/types/model/CustomSubtypingCallback;", "getCustomSubtypingCallback", "()Lkotlin/jvm/functions/Function2;", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;", "getTypeParameter", "(Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;)Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "AnnotationCallInfoImpl", "Factory", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualMatchingContextImpl implements FirExpectActualMatchingContext, TypeSystemContext {
    private final /* synthetic */ ConeInferenceContext $$delegate_0;
    private final ScopeSession actualScopeSession;
    private final FirSession actualSession;
    private final boolean allowedWritingMemberExpectForActualMapping;
    private final boolean checkClassScopesForAnnotationCompatibility;
    private final ExpectActualMatchingContext.MustUseMatcher mustUseMatcher;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatchingContextImpl$Factory;", "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContextFactory;", "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatchingContextImpl;", "actualSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "actualScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "allowedWritingMemberExpectForActualMapping", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Factory implements FirExpectActualMatchingContextFactory {
        public static final Factory INSTANCE = new Factory();

        private Factory() {
        }

        @Override // org.jetbrains.kotlin.fir.FirExpectActualMatchingContextFactory
        public FirExpectActualMatchingContextImpl create(FirSession actualSession, ScopeSession actualScopeSession, boolean allowedWritingMemberExpectForActualMapping) {
            actualSession.getClass();
            actualScopeSession.getClass();
            return new FirExpectActualMatchingContextImpl(actualSession, actualScopeSession, allowedWritingMemberExpectForActualMapping, null);
        }
    }

    private FirExpectActualMatchingContextImpl(FirSession firSession, ScopeSession scopeSession, boolean z) {
        this.$$delegate_0 = TypeComponentsKt.getTypeContext(firSession);
        this.actualSession = firSession;
        this.actualScopeSession = scopeSession;
        this.allowedWritingMemberExpectForActualMapping = z;
        this.mustUseMatcher = new ExpectActualMatchingContext.MustUseMatcher() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualMatchingContextImpl$mustUseMatcher$1
            public boolean matches(CallableSymbolMarker expectCallable, CallableSymbolMarker actualCallable, RegularClassSymbolMarker containingExpectClass) {
                expectCallable.getClass();
                actualCallable.getClass();
                return FirMustUseReturnValueStatusComponentKt.getMustUseReturnValueStatusComponent(this.this$0.actualSession).isExpectActualIgnorabilityCompatible(this.this$0.actualSession, this.this$0.asSymbol(expectCallable), this.this$0.asSymbol(actualCallable), containingExpectClass != null ? this.this$0.asSymbol(containingExpectClass) : null);
            }
        };
        this.checkClassScopesForAnnotationCompatibility = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConeKotlinType actualize(ConeKotlinType coneKotlinType) {
        ConeClassLikeType coneClassLikeTypeTryExpandExpectNestedClassActualizedViaTypealias;
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinType);
        if ((coneKotlinType instanceof ConeClassLikeType) && classId != null && classId.isNestedClass()) {
            FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(classId, this.actualSession);
            if ((symbol instanceof FirRegularClassSymbol) && ((FirClassLikeSymbol) symbol).getRawStatus().isExpect() && (coneClassLikeTypeTryExpandExpectNestedClassActualizedViaTypealias = tryExpandExpectNestedClassActualizedViaTypealias((ConeClassLikeType) coneKotlinType, (FirRegularClassSymbol) symbol)) != null) {
                return actualizeTypeArguments(coneClassLikeTypeTryExpandExpectNestedClassActualizedViaTypealias);
            }
        }
        return actualizeTypeArguments(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, this.actualSession, (Function1) null, 2, (Object) null));
    }

    private final ConeKotlinType actualizeTypeArguments(ConeKotlinType coneKotlinType) {
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            return coneKotlinType;
        }
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        if (typeArguments.length == 0) {
            return coneKotlinType;
        }
        int length = typeArguments.length;
        ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
        for (int i = 0; i < length; i++) {
            ConeTypeProjection coneTypeProjection = typeArguments[i];
            if (coneTypeProjection instanceof ConeKotlinTypeProjection) {
                ConeTypeProjection coneTypeProjectionReplaceType = replaceType(coneTypeProjection, actualize(((ConeKotlinTypeProjection) coneTypeProjection).getType()));
                coneTypeProjectionReplaceType.getClass();
                coneTypeProjection = coneTypeProjectionReplaceType;
            }
            coneTypeProjectionArr[i] = coneTypeProjection;
        }
        return TypeUtilsKt.withArguments(coneKotlinType, coneTypeProjectionArr);
    }

    private final void addMemberExpectForActualMapping(FirRegularClassSymbol firRegularClassSymbol, FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2, FirRegularClassSymbol firRegularClassSymbol2, ExpectActualMatchingCompatibility expectActualMatchingCompatibility) {
        if (!this.allowedWritingMemberExpectForActualMapping) {
            k2d.a("Writing memberExpectForActual is not allowed in this context");
            return;
        }
        ConcurrentMap concurrentMap = (ConcurrentMap) ExpectActualAttributesKt.getExpectActualMappingStorage(this.actualSession).getCache().getValue(firRegularClassSymbol, null);
        Pair pair = TuplesKt.to(firBasedSymbol2, firRegularClassSymbol2);
        final Function1 function1 = new Function1() { // from class: e65
            public final Object invoke(Object obj) {
                return FirExpectActualMatchingContextImpl.n((Pair) obj);
            }
        };
        ConcurrentMap concurrentMap2 = (ConcurrentMap) concurrentMap.computeIfAbsent(pair, new Function() { // from class: f65
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FirExpectActualMatchingContextImpl.q(function1, obj);
            }
        });
        concurrentMap2.getClass();
        concurrentMap2.put(firBasedSymbol, expectActualMatchingCompatibility);
    }

    private static final FirAnnotation areAnnotationArgumentsEqual$getFirAnnotation(ExpectActualMatchingContext.AnnotationCallInfo annotationCallInfo) {
        annotationCallInfo.getClass();
        return ((AnnotationCallInfoImpl) annotationCallInfo).getAnnotation();
    }

    private final FirBasedSymbol<?> asSymbol(DeclarationSymbolMarker declarationSymbolMarker) {
        declarationSymbolMarker.getClass();
        return (FirBasedSymbol) declarationSymbolMarker;
    }

    private final void checkAnnotationsOnTypeRefAndArgumentsImpl(FirBasedSymbol<?> expectContainingSymbol, FirBasedSymbol<?> actualContainingSymbol, FirTypeRef expectTypeRef, FirTypeRef actualTypeRef, ExpectActualMatchingContext.AnnotationsCheckerCallback checker) {
        FirUserTypeRef delegatedTypeRef;
        FirUserTypeRef delegatedTypeRef2;
        if (expectTypeRef == null || actualTypeRef == null || (expectTypeRef instanceof FirErrorTypeRef) || (actualTypeRef instanceof FirErrorTypeRef)) {
            return;
        }
        checker.check(checkAnnotationsOnTypeRefAndArgumentsImpl$getAnnotations(expectTypeRef, this, expectContainingSymbol), checkAnnotationsOnTypeRefAndArgumentsImpl$getAnnotations(actualTypeRef, this, actualContainingSymbol), new FirSourceElement(actualTypeRef.getSource()));
        FirResolvedTypeRef firResolvedTypeRef = expectTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) expectTypeRef : null;
        if (firResolvedTypeRef == null || (delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef()) == null) {
            return;
        }
        FirResolvedTypeRef firResolvedTypeRef2 = actualTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) actualTypeRef : null;
        if (firResolvedTypeRef2 == null || (delegatedTypeRef2 = firResolvedTypeRef2.getDelegatedTypeRef()) == null) {
            return;
        }
        if ((delegatedTypeRef instanceof FirUserTypeRef) && (delegatedTypeRef2 instanceof FirUserTypeRef)) {
            List<Pair> listZipIfSizesAreEqual = CollectionsKt.zipIfSizesAreEqual(delegatedTypeRef.getQualifier(), delegatedTypeRef2.getQualifier());
            if (listZipIfSizesAreEqual == null) {
                listZipIfSizesAreEqual = kotlin.collections.CollectionsKt.emptyList();
            }
            for (Pair pair : listZipIfSizesAreEqual) {
                List<Pair> listZipIfSizesAreEqual2 = CollectionsKt.zipIfSizesAreEqual(((FirQualifierPart) pair.component1()).getTypeArgumentList().getTypeArguments(), ((FirQualifierPart) pair.component2()).getTypeArgumentList().getTypeArguments());
                if (listZipIfSizesAreEqual2 == null) {
                    listZipIfSizesAreEqual2 = kotlin.collections.CollectionsKt.emptyList();
                }
                for (Pair pair2 : listZipIfSizesAreEqual2) {
                    FirTypeProjection firTypeProjection = (FirTypeProjection) pair2.component1();
                    FirTypeProjection firTypeProjection2 = (FirTypeProjection) pair2.component2();
                    if ((firTypeProjection instanceof FirTypeProjectionWithVariance) && (firTypeProjection2 instanceof FirTypeProjectionWithVariance)) {
                        checkAnnotationsOnTypeRefAndArgumentsImpl(expectContainingSymbol, actualContainingSymbol, ((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef(), ((FirTypeProjectionWithVariance) firTypeProjection2).getTypeRef(), checker);
                    }
                }
            }
            return;
        }
        if ((delegatedTypeRef instanceof FirFunctionTypeRef) && (delegatedTypeRef2 instanceof FirFunctionTypeRef)) {
            FirFunctionTypeRef firFunctionTypeRef = (FirFunctionTypeRef) delegatedTypeRef;
            FirFunctionTypeRef firFunctionTypeRef2 = (FirFunctionTypeRef) delegatedTypeRef2;
            checkAnnotationsOnTypeRefAndArgumentsImpl(expectContainingSymbol, actualContainingSymbol, firFunctionTypeRef.getReceiverTypeRef(), firFunctionTypeRef2.getReceiverTypeRef(), checker);
            checkAnnotationsOnTypeRefAndArgumentsImpl(expectContainingSymbol, actualContainingSymbol, firFunctionTypeRef.getReturnTypeRef(), firFunctionTypeRef2.getReturnTypeRef(), checker);
            List<Pair> listZipIfSizesAreEqual3 = CollectionsKt.zipIfSizesAreEqual(firFunctionTypeRef.getParameters(), firFunctionTypeRef2.getParameters());
            if (listZipIfSizesAreEqual3 == null) {
                listZipIfSizesAreEqual3 = kotlin.collections.CollectionsKt.emptyList();
            }
            for (Pair pair3 : listZipIfSizesAreEqual3) {
                checkAnnotationsOnTypeRefAndArgumentsImpl(expectContainingSymbol, actualContainingSymbol, ((FirFunctionTypeParameter) pair3.component1()).getReturnTypeRef(), ((FirFunctionTypeParameter) pair3.component2()).getReturnTypeRef(), checker);
            }
            List<Pair> listZipIfSizesAreEqual4 = CollectionsKt.zipIfSizesAreEqual(firFunctionTypeRef.getContextParameterTypeRefs(), firFunctionTypeRef2.getContextParameterTypeRefs());
            if (listZipIfSizesAreEqual4 == null) {
                listZipIfSizesAreEqual4 = kotlin.collections.CollectionsKt.emptyList();
            }
            for (Pair pair4 : listZipIfSizesAreEqual4) {
                checkAnnotationsOnTypeRefAndArgumentsImpl(expectContainingSymbol, actualContainingSymbol, (FirTypeRef) pair4.component1(), (FirTypeRef) pair4.component2(), checker);
            }
        }
    }

    private static final List<AnnotationCallInfoImpl> checkAnnotationsOnTypeRefAndArgumentsImpl$getAnnotations(FirAnnotationContainer firAnnotationContainer, FirExpectActualMatchingContextImpl firExpectActualMatchingContextImpl, FirBasedSymbol<?> firBasedSymbol) {
        List<FirAnnotation> listResolvedAnnotationsWithArguments = FirBasedSymbolKt.resolvedAnnotationsWithArguments(firAnnotationContainer, firBasedSymbol);
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(listResolvedAnnotationsWithArguments, 10));
        Iterator<T> it = listResolvedAnnotationsWithArguments.iterator();
        while (it.hasNext()) {
            arrayList.add(new AnnotationCallInfoImpl(firExpectActualMatchingContextImpl, (FirAnnotation) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectAllMembers$lambda$0$0(FirRegularClassSymbol firRegularClassSymbol, List list, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if ((firClassifierSymbol instanceof FirClassLikeSymbol) && Intrinsics.areEqual(((FirClassLikeSymbol) firClassifierSymbol).getClassId().getOuterClassId(), firRegularClassSymbol.getClassId())) {
            list.add(firClassifierSymbol);
        }
        return Unit.INSTANCE;
    }

    private final ConeClassLikeType convertToArrayWithOutProjections(ConeClassLikeType coneClassLikeType) {
        int length = coneClassLikeType.getTypeArguments().length;
        ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
        for (int i = 0; i < length; i++) {
            ConeKotlinType coneKotlinTypeProjectionOut = coneClassLikeType.getTypeArguments()[i];
            if (coneKotlinTypeProjectionOut instanceof ConeKotlinType) {
                coneKotlinTypeProjectionOut = new ConeKotlinTypeProjectionOut(coneKotlinTypeProjectionOut);
            }
            coneTypeProjectionArr[i] = coneKotlinTypeProjectionOut;
        }
        return new ConeClassLikeTypeImpl(coneClassLikeType.getLookupTag(), coneTypeProjectionArr, coneClassLikeType.getIsMarkedNullable(), null, 8, null);
    }

    private final TypeCheckerState createTypeCheckerState() {
        return TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(this.actualSession), true, false, false, 4, (Object) null);
    }

    private final void getConstructorsTo(List<? super FirConstructorSymbol> destination, FirTypeScope scope) {
        List<? super FirConstructorSymbol> list = destination;
        Iterator<T> it = FirScopeKt.getDeclaredConstructors(scope).iterator();
        while (it.hasNext()) {
            list.add((FirConstructorSymbol) it.next());
        }
    }

    private final void getMembersTo(FirScope firScope, final List<? super FirCallableSymbol<?>> list, Name name) {
        firScope.processFunctionsByName(name, new Function1() { // from class: h65
            public final Object invoke(Object obj) {
                return FirExpectActualMatchingContextImpl.p(list, (FirNamedFunctionSymbol) obj);
            }
        });
        firScope.processPropertiesByName(name, new Function1() { // from class: i65
            public final Object invoke(Object obj) {
                return FirExpectActualMatchingContextImpl.m(list, (FirVariableSymbol) obj);
            }
        });
    }

    public static Unit m(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        list.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    public static ConcurrentMap n(Pair pair) {
        return new ConcurrentHashMap();
    }

    public static Unit p(List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        list.add(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    public static ConcurrentMap q(Function1 function1, Object obj) {
        return (ConcurrentMap) function1.invoke(obj);
    }

    private final ConeClassLikeType tryExpandExpectNestedClassActualizedViaTypealias(ConeClassLikeType expectNestedClassType, FirRegularClassSymbol expectNestedClassSymbol) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        ClassId classId;
        ClassId classId2 = expectNestedClassSymbol.getClassId();
        ClassId outermostClassId = classId2.getOutermostClassId();
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(outermostClassId, this.actualSession);
        FirTypeAliasSymbol firTypeAliasSymbol = symbol instanceof FirTypeAliasSymbol ? (FirTypeAliasSymbol) symbol : null;
        if (firTypeAliasSymbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(firTypeAliasSymbol, this.actualSession)) == null || (classId = firRegularClassSymbolFullyExpandedClass.getClassId()) == null) {
            return null;
        }
        return TypeConstructionUtilsKt.constructClassLikeType(ClassId.Companion.fromString$default(ClassId.Companion, StringsKt.replaceFirst$default(classId2.asString(), outermostClassId.asString(), classId.asString(), false, 4, (Object) null), false, 2, (Object) null), expectNestedClassType.getTypeArguments(), expectNestedClassType.getIsMarkedNullable(), expectNestedClassType.getAttributes());
    }

    public List<CallableSymbolMarker> allRecursivelyOverriddenDeclarationsIncludingSelf(FunctionSymbolMarker functionSymbolMarker, RegularClassSymbolMarker regularClassSymbolMarker) {
        functionSymbolMarker.getClass();
        FirFunctionSymbol<?> firFunctionSymbolAsSymbol = asSymbol(functionSymbolMarker);
        if ((firFunctionSymbolAsSymbol instanceof FirConstructorSymbol) || (firFunctionSymbolAsSymbol instanceof FirFunctionWithoutNameSymbol)) {
            return kotlin.collections.CollectionsKt.listOf(firFunctionSymbolAsSymbol);
        }
        if (!(firFunctionSymbolAsSymbol instanceof FirNamedFunctionSymbol)) {
            bu8.a();
            return null;
        }
        if (regularClassSymbolMarker == null) {
            return kotlin.collections.CollectionsKt.listOf(firFunctionSymbolAsSymbol);
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firFunctionSymbolAsSymbol;
        List listPlus = kotlin.collections.CollectionsKt.plus(kotlin.collections.CollectionsKt.listOf(firFunctionSymbolAsSymbol), kotlin.collections.CollectionsKt.asSequence(ScopesKt.overriddenFunctions(firNamedFunctionSymbol, asSymbol(regularClassSymbolMarker), firNamedFunctionSymbol.getModuleData().getSession(), this.actualScopeSession)));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            FirFunctionSymbol firFunctionSymbol = (FirFunctionSymbol) obj;
            if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(firFunctionSymbol) && !Intrinsics.areEqual(firFunctionSymbol.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public boolean areAnnotationArgumentsEqual(ExpectActualMatchingContext.AnnotationCallInfo expectAnnotation, ExpectActualMatchingContext.AnnotationCallInfo actualAnnotation, ExpectActualCollectionArgumentsCompatibilityCheckStrategy collectionArgumentsCompatibilityCheckStrategy) {
        expectAnnotation.getClass();
        actualAnnotation.getClass();
        collectionArgumentsCompatibilityCheckStrategy.getClass();
        return AnnotationCompareUtilsKt.areFirAnnotationsEqual(this, areAnnotationArgumentsEqual$getFirAnnotation(expectAnnotation), areAnnotationArgumentsEqual$getFirAnnotation(actualAnnotation), collectionArgumentsCompatibilityCheckStrategy);
    }

    public boolean areCompatibleExpectActualTypes(KotlinTypeMarker expectType, KotlinTypeMarker actualType, boolean parameterOfAnnotationComparisonMode, boolean dynamicTypesEqualToAnything) {
        if (expectType == null) {
            return actualType == null;
        }
        if (actualType == null) {
            return false;
        }
        if (!dynamicTypesEqualToAnything) {
            boolean z = expectType instanceof ConeDynamicType;
            boolean z2 = actualType instanceof ConeDynamicType;
            if ((z && !z2) || (!z && z2)) {
                return false;
            }
        }
        ConeKotlinType coneKotlinTypeActualize = actualize((ConeKotlinType) expectType);
        ConeKotlinType coneKotlinTypeActualize2 = actualize((ConeKotlinType) actualType);
        return (parameterOfAnnotationComparisonMode && (coneKotlinTypeActualize instanceof ConeClassLikeType) && ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinTypeActualize) && (coneKotlinTypeActualize2 instanceof ConeClassLikeType) && ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinTypeActualize2)) ? AbstractTypeChecker.INSTANCE.equalTypes(createTypeCheckerState(), convertToArrayWithOutProjections((ConeClassLikeType) coneKotlinTypeActualize), convertToArrayWithOutProjections((ConeClassLikeType) coneKotlinTypeActualize2)) : AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(this.actualSession), coneKotlinTypeActualize, coneKotlinTypeActualize2, false, false, 24, (Object) null);
    }

    public boolean areEqualTypeConstructors(TypeConstructorMarker c1, TypeConstructorMarker c2) {
        c1.getClass();
        c2.getClass();
        return this.$$delegate_0.areEqualTypeConstructors(c1, c2);
    }

    public int argumentsCount(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.argumentsCount(kotlinTypeMarker);
    }

    public TypeArgumentListMarker asArgumentList(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.m665asArgumentList(rigidTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public CapturedTypeMarker asCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.asCapturedType(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public CapturedTypeMarker asCapturedTypeUnwrappingDnn(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.asCapturedTypeUnwrappingDnn(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.$$delegate_0.asDefinitelyNotNullType(definitelyNotNullTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DynamicTypeMarker asDynamicType(DynamicTypeMarker dynamicTypeMarker) {
        dynamicTypeMarker.getClass();
        return this.$$delegate_0.asDynamicType(dynamicTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public FlexibleTypeMarker asFlexibleType(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.$$delegate_0.asFlexibleType(flexibleTypeMarker);
    }

    public RigidTypeMarker asRigidType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m670asRigidType(kotlinTypeMarker);
    }

    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m671asTypeArgument(kotlinTypeMarker);
    }

    public RigidTypeMarker captureFromArguments(RigidTypeMarker type, CaptureStatus status) {
        type.getClass();
        status.getClass();
        return this.$$delegate_0.m672captureFromArguments(type, status);
    }

    public KotlinTypeMarker captureFromExpression(KotlinTypeMarker type) {
        type.getClass();
        return this.$$delegate_0.m673captureFromExpression(type);
    }

    public CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.captureStatus(capturedTypeMarker);
    }

    public void checkAnnotationsOnTypeRefAndArguments(DeclarationSymbolMarker expectContainingSymbol, DeclarationSymbolMarker actualContainingSymbol, TypeRefMarker expectTypeRef, TypeRefMarker actualTypeRef, ExpectActualMatchingContext.AnnotationsCheckerCallback checker) {
        expectContainingSymbol.getClass();
        actualContainingSymbol.getClass();
        expectTypeRef.getClass();
        actualTypeRef.getClass();
        checker.getClass();
        if ((expectTypeRef instanceof FirResolvedTypeRef) && (actualTypeRef instanceof FirResolvedTypeRef)) {
            checkAnnotationsOnTypeRefAndArgumentsImpl(asSymbol(expectContainingSymbol), asSymbol(actualContainingSymbol), (FirTypeRef) expectTypeRef, (FirTypeRef) actualTypeRef, checker);
        } else {
            k2d.a("Check failed.");
        }
    }

    public List<FirBasedSymbol<?>> collectAllMembers(RegularClassSymbolMarker regularClassSymbolMarker, boolean z) {
        FirSession session;
        regularClassSymbolMarker.getClass();
        final FirRegularClassSymbol firRegularClassSymbolAsSymbol = asSymbol(regularClassSymbolMarker);
        if (z) {
            session = this.actualSession;
        } else {
            if (z) {
                bu8.a();
                return null;
            }
            session = firRegularClassSymbolAsSymbol.getModuleData().getSession();
        }
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(ScopeUtilsKt.defaultType(firRegularClassSymbolAsSymbol), session, z ? this.actualScopeSession : getExpectScopeSession(), CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        if (firTypeScopeScope == null) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        final ArrayList arrayList = new ArrayList();
        Iterator<Name> it = firTypeScopeScope.getCallableNames().iterator();
        while (it.hasNext()) {
            getMembersTo(firTypeScopeScope, arrayList, it.next());
        }
        for (Name name : firTypeScopeScope.getClassifierNames()) {
            final Function1 function1 = new Function1() { // from class: g65
                public final Object invoke(Object obj) {
                    return FirExpectActualMatchingContextImpl.collectAllMembers$lambda$0$0(firRegularClassSymbolAsSymbol, arrayList, (FirClassifierSymbol) obj);
                }
            };
            firTypeScopeScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualMatchingContextImpl$collectAllMembers$lambda$0$$inlined$processClassifiersByName$1
                public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                    firClassifierSymbol.getClass();
                    coneSubstitutor.getClass();
                    function1.invoke(firClassifierSymbol);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                    return Unit.INSTANCE;
                }
            });
        }
        getConstructorsTo(arrayList, firTypeScopeScope);
        return arrayList;
    }

    public List<FirCallableSymbol<?>> collectAllStaticCallables(RegularClassSymbolMarker regularClassSymbolMarker, boolean z) {
        regularClassSymbolMarker.getClass();
        FirRegularClassSymbol firRegularClassSymbolAsSymbol = asSymbol(regularClassSymbolMarker);
        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = ImplicitReceiverUtilsKt.staticScope(firRegularClassSymbolAsSymbol, new SessionHolderImpl(z ? this.actualSession : firRegularClassSymbolAsSymbol.getModuleData().getSession(), this.actualScopeSession));
        if (firContainingNamesAwareScopeStaticScope == null) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Name> it = firContainingNamesAwareScopeStaticScope.getCallableNames().iterator();
        while (it.hasNext()) {
            getMembersTo(firContainingNamesAwareScopeStaticScope, arrayList, it.next());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<DeclarationSymbolMarker> collectEnumEntries(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        List<FirEnumEntry> listCollectEnumEntries = DeclarationUtilsKt.collectEnumEntries((FirClass) asSymbol(regularClassSymbolMarker).getFir(), this.actualSession);
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(listCollectEnumEntries, 10));
        Iterator<T> it = listCollectEnumEntries.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirEnumEntry) it.next()).getSymbol());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Name> collectEnumEntryNames(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        List<FirEnumEntry> listCollectEnumEntries = DeclarationUtilsKt.collectEnumEntries((FirClass) asSymbol(regularClassSymbolMarker).getFir(), this.actualSession);
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(listCollectEnumEntries, 10));
        Iterator<T> it = listCollectEnumEntries.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirEnumEntry) it.next()).getName());
        }
        return arrayList;
    }

    public TypeSubstitutorMarker createEmptySubstitutor() {
        return this.$$delegate_0.m624createEmptySubstitutor();
    }

    public TypeSubstitutorMarker createExpectActualTypeParameterSubstitutor(List<? extends Pair<? extends TypeParameterSymbolMarker, ? extends TypeParameterSymbolMarker>> expectActualTypeParameters, TypeSubstitutorMarker parentSubstitutor) {
        expectActualTypeParameters.getClass();
        return ExpectActualUtilsKt.createExpectActualTypeParameterSubstitutor(expectActualTypeParameters, this.actualSession, parentSubstitutor != null ? (ConeSubstitutor) parentSubstitutor : null);
    }

    public RegularClassSymbolMarker expandToRegularClass(TypeAliasSymbolMarker typeAliasSymbolMarker) {
        typeAliasSymbolMarker.getClass();
        return ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(asSymbol(typeAliasSymbolMarker).getResolvedExpandedTypeRef().getConeType(), this.actualSession, (Function1) null, 2, (Object) null), this.actualSession);
    }

    public List<SimpleTypeMarker> fastCorrespondingSupertypes(RigidTypeMarker rigidTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        rigidTypeMarker.getClass();
        typeConstructorMarker.getClass();
        return this.$$delegate_0.fastCorrespondingSupertypes(rigidTypeMarker, typeConstructorMarker);
    }

    public Map<FirBasedSymbol<?>, ExpectActualMatchingCompatibility> findPotentialExpectClassMembersForActual(RegularClassSymbolMarker expectClass, RegularClassSymbolMarker actualClass, DeclarationSymbolMarker actualMember) {
        ConcurrentMap concurrentMap;
        expectClass.getClass();
        actualClass.getClass();
        actualMember.getClass();
        ConcurrentMap concurrentMap2 = (ConcurrentMap) ExpectActualAttributesKt.getExpectActualMappingStorage(this.actualSession).getCache().getValueIfComputed(asSymbol(actualClass));
        return (concurrentMap2 == null || (concurrentMap = (ConcurrentMap) concurrentMap2.get(TuplesKt.to(actualMember, expectClass))) == null) ? MapsKt.emptyMap() : concurrentMap;
    }

    public TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i) {
        typeArgumentListMarker.getClass();
        return this.$$delegate_0.get(typeArgumentListMarker, i);
    }

    public List<ExpectActualMatchingContext.AnnotationCallInfo> getAnnotations(DeclarationSymbolMarker declarationSymbolMarker) {
        declarationSymbolMarker.getClass();
        List<FirAnnotation> resolvedAnnotationsWithArguments = asSymbol(declarationSymbolMarker).getResolvedAnnotationsWithArguments();
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(resolvedAnnotationsWithArguments, 10));
        Iterator<T> it = resolvedAnnotationsWithArguments.iterator();
        while (it.hasNext()) {
            arrayList.add(new AnnotationCallInfoImpl(this, (FirAnnotation) it.next()));
        }
        return arrayList;
    }

    public TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m674getArgument(kotlinTypeMarker, i);
    }

    public TypeArgumentMarker getArgumentOrNull(RigidTypeMarker rigidTypeMarker, int i) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.getArgumentOrNull(rigidTypeMarker, i);
    }

    public List<TypeArgumentMarker> getArguments(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.getArguments(kotlinTypeMarker);
    }

    public List<AnnotationMarker> getAttributes(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.getAttributes(kotlinTypeMarker);
    }

    public List<KotlinTypeMarker> getBounds(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        List<FirResolvedTypeRef> resolvedBounds = asSymbol(typeParameterSymbolMarker).getResolvedBounds();
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        return arrayList;
    }

    public List<TypeRefMarker> getBoundsTypeRefs(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        return asSymbol(typeParameterSymbolMarker).getResolvedBounds();
    }

    public CallableId getCallableId(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        CallableId callableId = asSymbol(callableSymbolMarker).getCallableId();
        callableId.getClass();
        return callableId;
    }

    @Override // org.jetbrains.kotlin.fir.FirExpectActualMatchingContext
    public List<FirCallableSymbol<?>> getCallablesForExpectClass(RegularClassSymbolMarker regularClassSymbolMarker, Name name) {
        regularClassSymbolMarker.getClass();
        name.getClass();
        FirRegularClassSymbol firRegularClassSymbolAsSymbol = asSymbol(regularClassSymbolMarker);
        FirScope firScopeScope = ScopeUtilsKt.scope(ScopeUtilsKt.defaultType(firRegularClassSymbolAsSymbol), firRegularClassSymbolAsSymbol.getModuleData().getSession(), getExpectScopeSession(), CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        if (firScopeScope == null) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        getMembersTo(firScopeScope, arrayList, name);
        return arrayList;
    }

    public boolean getCanBeActualizedByJavaField(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        if (isJavaField(callableSymbolMarker)) {
            return true;
        }
        return (callableSymbolMarker instanceof FirPropertySymbol) && Intrinsics.areEqual(((FirPropertySymbol) callableSymbolMarker).getCallableId(), ExpectActualMatchingContext.Companion.getAbstractMutableListModCountCallableId());
    }

    public boolean getCheckClassScopesForAnnotationCompatibility() {
        return this.checkClassScopesForAnnotationCompatibility;
    }

    public ClassId getClassId(TypeRefMarker typeRefMarker) {
        typeRefMarker.getClass();
        return ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(((FirResolvedTypeRef) typeRefMarker).getConeType(), this.actualSession, (Function1) null, 2, (Object) null));
    }

    public ClassKind getClassKind(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getClassKind();
    }

    @Override // org.jetbrains.kotlin.fir.FirExpectActualMatchingContext
    public Collection<FirConstructorSymbol> getConstructors(FirClassSymbol<?> firClassSymbol, ScopeSession scopeSession, FirSession firSession) {
        firClassSymbol.getClass();
        scopeSession.getClass();
        firSession.getClass();
        ArrayList arrayList = new ArrayList();
        getConstructorsTo(arrayList, FirKotlinScopeProviderKt.unsubstitutedScope(firClassSymbol, firSession, scopeSession, false, FirResolvePhase.STATUS));
        return arrayList;
    }

    public List<ValueParameterSymbolMarker> getContextParameters(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).getContextParameterSymbols();
    }

    public Function2<KotlinTypeMarker, KotlinTypeMarker, Boolean> getCustomSubtypingCallback() {
        return this.$$delegate_0.getCustomSubtypingCallback();
    }

    public KotlinTypeMarker getDefaultType(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return ScopeUtilsKt.defaultType(asSymbol(regularClassSymbolMarker));
    }

    public SimpleTypeMarker getDispatchReceiverType(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getDispatchReceiverType();
    }

    @Override // org.jetbrains.kotlin.fir.FirExpectActualMatchingContext
    public ScopeSession getExpectScopeSession() {
        return new ScopeSession();
    }

    public KotlinTypeMarker getExtensionReceiverType(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedReceiverType();
    }

    public TypeRefMarker getExtensionReceiverTypeRef(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedReceiverTypeRef();
    }

    public FunctionSymbolMarker getGetter(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).getGetterSymbol();
    }

    public boolean getHasDefaultValue(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).getHasDefaultValue();
    }

    public boolean getHasDefaultValueNonRecursive(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).getHasDefaultValue();
    }

    public boolean getHasSourceAnnotationsErased(DeclarationSymbolMarker declarationSymbolMarker) {
        declarationSymbolMarker.getClass();
        FirBasedSymbol<?> firBasedSymbolAsSymbol = asSymbol(declarationSymbolMarker);
        return firBasedSymbolAsSymbol.getSource() == null && !(firBasedSymbolAsSymbol.getOrigin() instanceof FirDeclarationOrigin.Plugin);
    }

    public boolean getHasStableParameterNames(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getRawStatus().getHasStableParameterNames();
    }

    public Modality getModality(ClassLikeSymbolMarker classLikeSymbolMarker) {
        classLikeSymbolMarker.getClass();
        return asSymbol(classLikeSymbolMarker).getResolvedStatus().getModality();
    }

    public ExpectActualMatchingContext.MustUseMatcher getMustUseMatcher() {
        return this.mustUseMatcher;
    }

    public TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.m675getParameter(typeConstructorMarker, i);
    }

    public Name getParameterName(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        return asSymbol(typeParameterSymbolMarker).getName();
    }

    public List<TypeParameterMarker> getParameters(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.getParameters(typeConstructorMarker);
    }

    public KotlinTypeMarker getReturnType(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedReturnType();
    }

    public TypeRefMarker getReturnTypeRef(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedReturnTypeRef();
    }

    public FunctionSymbolMarker getSetter(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).getSetterSymbol();
    }

    public boolean getShouldCheckDefaultParams() {
        return true;
    }

    public boolean getShouldMatchByParameterNames(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return false;
    }

    public SourceElementMarker getSourceElement(DeclarationSymbolMarker declarationSymbolMarker) {
        declarationSymbolMarker.getClass();
        return new FirSourceElement(asSymbol(declarationSymbolMarker).getSource());
    }

    @Override // org.jetbrains.kotlin.fir.FirExpectActualMatchingContext
    public List<FirCallableSymbol<?>> getStaticCallablesForExpectClass(RegularClassSymbolMarker regularClassSymbolMarker, Name name) {
        regularClassSymbolMarker.getClass();
        name.getClass();
        FirRegularClassSymbol firRegularClassSymbolAsSymbol = asSymbol(regularClassSymbolMarker);
        FirScope firScopeStaticScope = ImplicitReceiverUtilsKt.staticScope(firRegularClassSymbolAsSymbol, new SessionHolderImpl(firRegularClassSymbolAsSymbol.getModuleData().getSession(), this.actualScopeSession));
        if (firScopeStaticScope == null) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        getMembersTo(firScopeStaticScope, arrayList, name);
        return arrayList;
    }

    public List<KotlinTypeMarker> getSuperTypes(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getResolvedSuperTypes();
    }

    public List<TypeRefMarker> getSuperTypesRefs(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getResolvedSuperTypeRefs();
    }

    public KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.$$delegate_0.m677getType(typeArgumentMarker);
    }

    public TypeConstructorMarker getTypeConstructor(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.m678getTypeConstructor(typeParameterMarker);
    }

    public TypeParameterMarker getTypeParameter(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        typeVariableTypeConstructorMarker.getClass();
        return this.$$delegate_0.m679getTypeParameter(typeVariableTypeConstructorMarker);
    }

    public TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.m680getTypeParameterClassifier(typeConstructorMarker);
    }

    public List<TypeParameterSymbolMarker> getTypeParameters(ClassLikeSymbolMarker classLikeSymbolMarker) {
        classLikeSymbolMarker.getClass();
        return asSymbol(classLikeSymbolMarker).getTypeParameterSymbols();
    }

    public KotlinTypeMarker getUpperBound(TypeParameterMarker typeParameterMarker, int i) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.m682getUpperBound(typeParameterMarker, i);
    }

    public List<KotlinTypeMarker> getUpperBounds(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.getUpperBounds(typeParameterMarker);
    }

    public List<ValueParameterSymbolMarker> getValueParameters(FunctionSymbolMarker functionSymbolMarker) {
        functionSymbolMarker.getClass();
        return asSymbol(functionSymbolMarker).getValueParameterSymbols();
    }

    public Variance getVariance(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        return asSymbol(typeParameterSymbolMarker).getVariance();
    }

    public Visibility getVisibility(ClassLikeSymbolMarker classLikeSymbolMarker) {
        classLikeSymbolMarker.getClass();
        return asSymbol(classLikeSymbolMarker).getResolvedStatus().getVisibility();
    }

    public boolean hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.hasFlexibleNullability(kotlinTypeMarker);
    }

    public boolean hasRecursiveBounds(TypeParameterMarker typeParameterMarker, TypeConstructorMarker typeConstructorMarker) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.hasRecursiveBounds(typeParameterMarker, typeConstructorMarker);
    }

    public boolean identicalArguments(RigidTypeMarker a, RigidTypeMarker b) {
        a.getClass();
        b.getClass();
        return this.$$delegate_0.identicalArguments(a, b);
    }

    public KotlinTypeMarker intersectTypes(Collection<? extends KotlinTypeMarker> types) {
        types.getClass();
        return this.$$delegate_0.m684intersectTypes((Collection) types);
    }

    public boolean isAnnotationConstructor(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        FirCallableSymbol<?> firCallableSymbolAsSymbol = asSymbol(callableSymbolMarker);
        return DeclarationUtilsKt.isAnnotationConstructor(firCallableSymbolAsSymbol, firCallableSymbolAsSymbol.getModuleData().getSession());
    }

    public boolean isAnonymous(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isAnonymous(typeConstructorMarker);
    }

    public boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isAnyConstructor(typeConstructorMarker);
    }

    public boolean isArrayConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isArrayConstructor(typeConstructorMarker);
    }

    public boolean isCapturedDynamic(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isCapturedDynamic(kotlinTypeMarker);
    }

    public boolean isCapturedType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isCapturedType(kotlinTypeMarker);
    }

    public boolean isClassType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isClassType(rigidTypeMarker);
    }

    public boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isClassTypeConstructor(typeConstructorMarker);
    }

    public boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isCommonFinalClassConstructor(typeConstructorMarker);
    }

    public boolean isCompanion(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getResolvedStatus().isCompanion();
    }

    public boolean isConst(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).getResolvedStatus().isConst();
    }

    public boolean isCrossinline(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).isCrossinline();
    }

    public boolean isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isDefinitelyNotNullType(kotlinTypeMarker);
    }

    public boolean isDelegatedMember(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return ClassMembersKt.isDelegated(asSymbol(callableSymbolMarker));
    }

    public boolean isDenotable(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isDenotable(typeConstructorMarker);
    }

    public boolean isDynamic(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isDynamic(kotlinTypeMarker);
    }

    public boolean isError(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isError(kotlinTypeMarker);
    }

    public boolean isExpect(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isExpect();
    }

    public boolean isExternal(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isExternal();
    }

    public boolean isFakeOverride(CallableSymbolMarker callableSymbolMarker, RegularClassSymbolMarker regularClassSymbolMarker) {
        ConeSimpleKotlinType dispatchReceiverType;
        callableSymbolMarker.getClass();
        if (regularClassSymbolMarker == null) {
            return false;
        }
        FirCallableSymbol<?> firCallableSymbolAsSymbol = asSymbol(callableSymbolMarker);
        FirRegularClassSymbol firRegularClassSymbolAsSymbol = asSymbol(regularClassSymbolMarker);
        if ((firCallableSymbolAsSymbol instanceof FirConstructorSymbol) || (dispatchReceiverType = firCallableSymbolAsSymbol.getDispatchReceiverType()) == null || Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(dispatchReceiverType), firRegularClassSymbolAsSymbol.getClassId())) {
            return ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableSymbolAsSymbol);
        }
        return true;
    }

    public boolean isFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isFlexible(kotlinTypeMarker);
    }

    public boolean isFlexibleNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isFlexibleNothing(kotlinTypeMarker);
    }

    public boolean isFlexibleWithDifferentTypeConstructors(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isFlexibleWithDifferentTypeConstructors(kotlinTypeMarker);
    }

    public boolean isFun(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getResolvedStatus().isFun();
    }

    public boolean isInfix(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isInfix();
    }

    public boolean isInline(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isInline();
    }

    public boolean isInlineOrValue(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        FirResolvedDeclarationStatus resolvedStatus = asSymbol(regularClassSymbolMarker).getResolvedStatus();
        return resolvedStatus.isInline() || resolvedStatus.isValue();
    }

    public boolean isInner(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getResolvedStatus().isInner();
    }

    public boolean isIntegerConstantOperatorTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isIntegerConstantOperatorTypeConstructor(typeConstructorMarker);
    }

    public boolean isIntegerLiteralConstantTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isIntegerLiteralConstantTypeConstructor(typeConstructorMarker);
    }

    public boolean isIntegerLiteralType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isIntegerLiteralType(rigidTypeMarker);
    }

    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isIntegerLiteralTypeConstructor(typeConstructorMarker);
    }

    public boolean isInterface(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isInterface(typeConstructorMarker);
    }

    public boolean isIntersection(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isIntersection(typeConstructorMarker);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isJavaField(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        if (!(callableSymbolMarker instanceof FirFieldSymbol)) {
            return false;
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirFieldSymbol) callableSymbolMarker).getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        return firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Java;
    }

    public boolean isLateinit(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).getResolvedStatus().isLateInit();
    }

    public boolean isLocalType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isLocalType(typeConstructorMarker);
    }

    public boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isMarkedNullable(kotlinTypeMarker);
    }

    public boolean isNoinline(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).isNoinline();
    }

    @ObsoleteTypeKind
    public boolean isNotNullTypeParameter(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNotNullTypeParameter(kotlinTypeMarker);
    }

    public boolean isNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNothing(kotlinTypeMarker);
    }

    public boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isNothingConstructor(typeConstructorMarker);
    }

    public boolean isNullableAny(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNullableAny(kotlinTypeMarker);
    }

    public boolean isNullableNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNullableNothing(kotlinTypeMarker);
    }

    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNullableType(kotlinTypeMarker);
    }

    public boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.isOldCapturedType(capturedTypeMarker);
    }

    public boolean isOperator(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isOperator();
    }

    public boolean isPrimitiveType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isPrimitiveType(rigidTypeMarker);
    }

    @AllowedToUsedOnlyInK1
    public boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.isProjectionNotNull(capturedTypeMarker);
    }

    public boolean isRawType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isRawType(kotlinTypeMarker);
    }

    public boolean isReified(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        return asSymbol(typeParameterSymbolMarker).isReified();
    }

    public boolean isRigidType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isRigidType(kotlinTypeMarker);
    }

    public boolean isSamInterface(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return new FirSamResolver(this.actualSession, this.actualScopeSession, null, 4, null).isSamType(ScopeUtilsKt.defaultType(asSymbol(regularClassSymbolMarker)));
    }

    public boolean isSingleClassifierType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isSingleClassifierType(rigidTypeMarker);
    }

    public boolean isStarProjection(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.$$delegate_0.isStarProjection(typeArgumentMarker);
    }

    public boolean isStubType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isStubType(rigidTypeMarker);
    }

    public boolean isStubTypeForBuilderInference(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isStubTypeForBuilderInference(rigidTypeMarker);
    }

    public boolean isStubTypeForVariableInSubtyping(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isStubTypeForVariableInSubtyping(rigidTypeMarker);
    }

    public boolean isSubtypeOf(KotlinTypeMarker superType, KotlinTypeMarker subType) {
        superType.getClass();
        subType.getClass();
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, createTypeCheckerState(), subType, superType, false, 8, (Object) null);
    }

    public boolean isSuspend(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isSuspend();
    }

    public boolean isTailrec(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().isTailRec();
    }

    public boolean isTypeParameterTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isTypeParameterTypeConstructor(typeConstructorMarker);
    }

    public boolean isTypeVariableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isTypeVariableType(kotlinTypeMarker);
    }

    public boolean isUninferredParameter(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isUninferredParameter(kotlinTypeMarker);
    }

    public boolean isVar(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return asSymbol(propertySymbolMarker).isVar();
    }

    public boolean isVararg(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).isVararg();
    }

    public Iterator<TypeArgumentMarker> iterator(TypeArgumentListMarker typeArgumentListMarker) {
        typeArgumentListMarker.getClass();
        return this.$$delegate_0.iterator(typeArgumentListMarker);
    }

    public RigidTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.$$delegate_0.m686lowerBound(flexibleTypeMarker);
    }

    public RigidTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.lowerBoundIfFlexible(kotlinTypeMarker);
    }

    public KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.m687lowerType(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DefinitelyNotNullTypeMarker makeDefinitelyNotNullOrNotNull(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.$$delegate_0.makeDefinitelyNotNullOrNotNull(definitelyNotNullTypeMarker);
    }

    public void onMatchedMembers(DeclarationSymbolMarker expectSymbol, DeclarationSymbolMarker actualSymbol, RegularClassSymbolMarker containingExpectClassSymbol, RegularClassSymbolMarker containingActualClassSymbol) {
        expectSymbol.getClass();
        actualSymbol.getClass();
        if (containingActualClassSymbol == null || containingExpectClassSymbol == null) {
            return;
        }
        addMemberExpectForActualMapping(asSymbol(containingActualClassSymbol), asSymbol(expectSymbol), asSymbol(actualSymbol), asSymbol(containingExpectClassSymbol), ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE);
    }

    public void onMismatchedMembersFromClassScope(DeclarationSymbolMarker expectSymbol, Map<ExpectActualMatchingCompatibility.Mismatch, ? extends List<? extends DeclarationSymbolMarker>> actualSymbolsByIncompatibility, RegularClassSymbolMarker containingExpectClassSymbol, RegularClassSymbolMarker containingActualClassSymbol) {
        expectSymbol.getClass();
        actualSymbolsByIncompatibility.getClass();
        if (containingExpectClassSymbol == null || containingActualClassSymbol == null) {
            return;
        }
        for (Map.Entry<ExpectActualMatchingCompatibility.Mismatch, ? extends List<? extends DeclarationSymbolMarker>> entry : actualSymbolsByIncompatibility.entrySet()) {
            ExpectActualMatchingCompatibility.Mismatch key = entry.getKey();
            Iterator<? extends DeclarationSymbolMarker> it = entry.getValue().iterator();
            while (it.hasNext()) {
                addMemberExpectForActualMapping(asSymbol(containingActualClassSymbol), asSymbol(expectSymbol), asSymbol(it.next()), asSymbol(containingExpectClassSymbol), key);
            }
        }
    }

    public SimpleTypeMarker original(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.$$delegate_0.m651original(definitelyNotNullTypeMarker);
    }

    public SimpleTypeMarker originalIfDefinitelyNotNullable(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.originalIfDefinitelyNotNullable(rigidTypeMarker);
    }

    public int parametersCount(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.parametersCount(typeConstructorMarker);
    }

    public Collection<KotlinTypeMarker> possibleIntegerTypes(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.possibleIntegerTypes(rigidTypeMarker);
    }

    public TypeArgumentMarker projection(CapturedTypeConstructorMarker capturedTypeConstructorMarker) {
        capturedTypeConstructorMarker.getClass();
        return this.$$delegate_0.m688projection(capturedTypeConstructorMarker);
    }

    public TypeArgumentMarker replaceType(TypeArgumentMarker typeArgumentMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeArgumentMarker.getClass();
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m689replaceType(typeArgumentMarker, kotlinTypeMarker);
    }

    public KotlinTypeMarker safeSubstitute(TypeSubstitutorMarker typeSubstitutorMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeSubstitutorMarker.getClass();
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m657safeSubstitute(typeSubstitutorMarker, kotlinTypeMarker);
    }

    public int size(TypeArgumentListMarker typeArgumentListMarker) {
        typeArgumentListMarker.getClass();
        return this.$$delegate_0.size(typeArgumentListMarker);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public boolean skipCheckingAnnotationsOfActualClassMember(DeclarationSymbolMarker actualMember) throws KotlinIllegalArgumentExceptionWithAttachments {
        actualMember.getClass();
        FirDeclaration fir = asSymbol(actualMember).getFir();
        FirMemberDeclaration firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
        return firMemberDeclaration != null && firMemberDeclaration.getStatus().isActual();
    }

    public TypeCheckerState.SupertypesPolicy substitutionSupertypePolicy(RigidTypeMarker type) {
        type.getClass();
        return this.$$delegate_0.substitutionSupertypePolicy(type);
    }

    public Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.supertypes(typeConstructorMarker);
    }

    public CapturedTypeConstructorMarker typeConstructor(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.$$delegate_0.m690typeConstructor(capturedTypeMarker);
    }

    public TypeSubstitutorMarker typeSubstitutorByTypeConstructor(Map<TypeConstructorMarker, ? extends KotlinTypeMarker> map) {
        map.getClass();
        return this.$$delegate_0.typeSubstitutorByTypeConstructor(map);
    }

    public TypeConstructorMarker unwrapStubTypeVariableConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.m692unwrapStubTypeVariableConstructor(typeConstructorMarker);
    }

    public RigidTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.$$delegate_0.m693upperBound(flexibleTypeMarker);
    }

    public int upperBoundCount(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.upperBoundCount(typeParameterMarker);
    }

    public RigidTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.upperBoundIfFlexible(kotlinTypeMarker);
    }

    public KotlinTypeMarker withNewTypeSince(KotlinTypeMarker kotlinTypeMarker, Object obj, KotlinTypeMarker kotlinTypeMarker2) {
        kotlinTypeMarker.getClass();
        obj.getClass();
        kotlinTypeMarker2.getClass();
        return this.$$delegate_0.m694withNewTypeSince(kotlinTypeMarker, obj, kotlinTypeMarker2);
    }

    public KotlinTypeMarker withNullability(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m662withNullability(kotlinTypeMarker, z);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatchingContextImpl$AnnotationCallInfoImpl;", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualMatchingContext$AnnotationCallInfo;", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatchingContextImpl;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "getAnnotation", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotationSymbol", "getAnnotationSymbol", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "isRetentionSource", Argument.Delimiters.none, "()Z", "isOptIn", "isOptionalExpectation", "getAnnotationClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getAnnotationConeType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class AnnotationCallInfoImpl implements ExpectActualMatchingContext.AnnotationCallInfo {
        private final FirAnnotation annotation;
        private final FirAnnotation annotationSymbol;
        final /* synthetic */ FirExpectActualMatchingContextImpl this$0;

        public AnnotationCallInfoImpl(FirExpectActualMatchingContextImpl firExpectActualMatchingContextImpl, FirAnnotation firAnnotation) {
            firAnnotation.getClass();
            this.this$0 = firExpectActualMatchingContextImpl;
            this.annotation = firAnnotation;
            this.annotationSymbol = firAnnotation;
        }

        private final FirRegularClassSymbol getAnnotationClass() {
            ConeClassLikeType annotationConeType = getAnnotationConeType();
            if (annotationConeType != null) {
                return ToSymbolUtilsKt.toRegularClassSymbol(annotationConeType, this.this$0.actualSession);
            }
            return null;
        }

        private final ConeClassLikeType getAnnotationConeType() {
            ConeClassLikeType annotationClassLikeType = FirAnnotationUtilsKt.toAnnotationClassLikeType(this.annotation, this.this$0.actualSession);
            ConeKotlinType coneKotlinTypeActualize = annotationClassLikeType != null ? this.this$0.actualize(annotationClassLikeType) : null;
            ConeClassLikeType coneClassLikeType = coneKotlinTypeActualize instanceof ConeClassLikeType ? (ConeClassLikeType) coneKotlinTypeActualize : null;
            if (coneClassLikeType instanceof ConeErrorType) {
                return null;
            }
            return coneClassLikeType;
        }

        public final FirAnnotation getAnnotation() {
            return this.annotation;
        }

        public ClassId getClassId() {
            ConeClassLikeLookupTag lookupTag;
            ConeClassLikeType annotationConeType = getAnnotationConeType();
            if (annotationConeType == null || (lookupTag = annotationConeType.getLookupTag()) == null) {
                return null;
            }
            return lookupTag.getClassId();
        }

        public boolean isOptIn() {
            FirRegularClassSymbol annotationClass = getAnnotationClass();
            if (annotationClass != null) {
                return FirAnnotationUtilsKt.hasAnnotation(annotationClass, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), this.this$0.actualSession);
            }
            return false;
        }

        public boolean isOptionalExpectation() {
            FirRegularClassSymbol annotationClass = getAnnotationClass();
            if (annotationClass != null) {
                return FirAnnotationUtilsKt.hasAnnotation(annotationClass, StandardClassIds$Annotations.INSTANCE.getOptionalExpectation(), this.this$0.actualSession);
            }
            return false;
        }

        public boolean isRetentionSource() {
            FirRegularClassSymbol annotationClass = getAnnotationClass();
            return (annotationClass != null ? FirRetentionAnnotationHelpersKt.getRetention(annotationClass, this.this$0.actualSession) : null) == AnnotationRetention.SOURCE;
        }

        public FirAnnotation getAnnotationSymbol() {
            return this.annotationSymbol;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirCallableSymbol<?> asSymbol(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return (FirCallableSymbol) callableSymbolMarker;
    }

    private final FirFunctionSymbol<?> asSymbol(FunctionSymbolMarker functionSymbolMarker) {
        functionSymbolMarker.getClass();
        return (FirFunctionSymbol) functionSymbolMarker;
    }

    private final FirPropertySymbol asSymbol(PropertySymbolMarker propertySymbolMarker) {
        propertySymbolMarker.getClass();
        return (FirPropertySymbol) propertySymbolMarker;
    }

    private final FirValueParameterSymbol asSymbol(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return (FirValueParameterSymbol) valueParameterSymbolMarker;
    }

    private final FirTypeParameterSymbol asSymbol(TypeParameterSymbolMarker typeParameterSymbolMarker) {
        typeParameterSymbolMarker.getClass();
        return (FirTypeParameterSymbol) typeParameterSymbolMarker;
    }

    public CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.$$delegate_0.m666asCapturedType(simpleTypeMarker);
    }

    public CapturedTypeMarker asCapturedTypeUnwrappingDnn(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.asCapturedTypeUnwrappingDnn(rigidTypeMarker);
    }

    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.m667asDefinitelyNotNullType(rigidTypeMarker);
    }

    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.$$delegate_0.m668asDynamicType(flexibleTypeMarker);
    }

    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m669asFlexibleType(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker asRigidType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.asRigidType(rigidTypeMarker);
    }

    /* JADX INFO: renamed from: intersectTypes, reason: collision with other method in class */
    public SimpleTypeMarker m613intersectTypes(Collection<? extends SimpleTypeMarker> types) {
        types.getClass();
        return this.$$delegate_0.m685intersectTypes((Collection) types);
    }

    public boolean isDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.isDefinitelyNotNullType(rigidTypeMarker);
    }

    public boolean isError(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.$$delegate_0.isError(typeConstructorMarker);
    }

    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.isNullableType(kotlinTypeMarker, z);
    }

    public boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.$$delegate_0.isPrimitiveType(simpleTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker lowerBoundIfFlexible(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.lowerBoundIfFlexible(rigidTypeMarker);
    }

    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.makeDefinitelyNotNullOrNotNull(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public SimpleTypeMarker originalIfDefinitelyNotNullable(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.$$delegate_0.originalIfDefinitelyNotNullable(simpleTypeMarker);
    }

    public TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.typeConstructor(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker upperBoundIfFlexible(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.upperBoundIfFlexible(rigidTypeMarker);
    }

    public RigidTypeMarker withNullability(RigidTypeMarker rigidTypeMarker, boolean z) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.m695withNullability(rigidTypeMarker, z);
    }

    private final FirClassLikeSymbol<?> asSymbol(ClassLikeSymbolMarker classLikeSymbolMarker) {
        classLikeSymbolMarker.getClass();
        return (FirClassLikeSymbol) classLikeSymbolMarker;
    }

    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.$$delegate_0.m645makeDefinitelyNotNullOrNotNull(kotlinTypeMarker, z);
    }

    public TypeConstructorMarker typeConstructor(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.m691typeConstructor(rigidTypeMarker);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirRegularClassSymbol asSymbol(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return (FirRegularClassSymbol) regularClassSymbolMarker;
    }

    public List<ValueParameterSymbolMarker> getContextParameters(FunctionSymbolMarker functionSymbolMarker) {
        functionSymbolMarker.getClass();
        return asSymbol(functionSymbolMarker).getContextParameterSymbols();
    }

    public Name getParameterName(ValueParameterSymbolMarker valueParameterSymbolMarker) {
        valueParameterSymbolMarker.getClass();
        return asSymbol(valueParameterSymbolMarker).getName();
    }

    public List<TypeParameterSymbolMarker> getTypeParameters(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getTypeParameterSymbols();
    }

    public TypeVariance getVariance(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.$$delegate_0.getVariance(typeParameterMarker);
    }

    public RigidTypeMarker makeDefinitelyNotNullOrNotNull(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.$$delegate_0.m646makeDefinitelyNotNullOrNotNull(rigidTypeMarker);
    }

    private final FirTypeAliasSymbol asSymbol(TypeAliasSymbolMarker typeAliasSymbolMarker) {
        typeAliasSymbolMarker.getClass();
        return (FirTypeAliasSymbol) typeAliasSymbolMarker;
    }

    public TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.$$delegate_0.getVariance(typeArgumentMarker);
    }

    public Modality getModality(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().getModality();
    }

    public Visibility getVisibility(CallableSymbolMarker callableSymbolMarker) {
        callableSymbolMarker.getClass();
        return asSymbol(callableSymbolMarker).getResolvedStatus().getVisibility();
    }

    public ClassId getClassId(TypeAliasSymbolMarker typeAliasSymbolMarker) {
        typeAliasSymbolMarker.getClass();
        return asSymbol(typeAliasSymbolMarker).getClassId();
    }

    public ClassId getClassId(RegularClassSymbolMarker regularClassSymbolMarker) {
        regularClassSymbolMarker.getClass();
        return asSymbol(regularClassSymbolMarker).getClassId();
    }

    public /* synthetic */ FirExpectActualMatchingContextImpl(FirSession firSession, ScopeSession scopeSession, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, z);
    }
}
