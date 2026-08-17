package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.SymbolConversionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitAnyTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBooleanTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitIntTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNothingTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNullableAnyTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNullableNothingTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitStringTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitUnitTypeRef;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrDynamicTypeImpl;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.types.impl.IrStarProjectionImpl;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.CommonFlexibleTypeBoundsChecker;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Þ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u001b\u001a\u00020\u0010*\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJL\u0010\u001b\u001a\u00020\u0010*\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$2\b\b\u0002\u0010'\u001a\u00020$J\f\u0010&\u001a\u00020$*\u00020(H\u0002J\u0016\u0010)\u001a\u00020\u00102\f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0!H\u0002J\f\u0010,\u001a\u00020$*\u00020(H\u0002J\u0014\u0010-\u001a\u00020.*\u00020/2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001a\u00100\u001a\u00020$*\u00020\u001f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001502H\u0002J\u0014\u00103\u001a\u0004\u0018\u00010\n2\b\u00104\u001a\u0004\u0018\u00010\tH\u0002J\u0014\u00105\u001a\u0004\u0018\u00010\n2\b\u00104\u001a\u0004\u0018\u00010\tH\u0002J\u0016\u00106\u001a\u0004\u0018\u00010\u00102\n\u00107\u001a\u0006\u0012\u0002\b\u000308H\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR'\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00100\b8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019R\u0012\u00109\u001a\u00020:X\u0096\u0005¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0012\u0010=\u001a\u00020>X\u0096\u0005¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0012\u0010A\u001a\u00020BX\u0096\u0005¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0012\u0010E\u001a\u00020FX\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0012\u0010I\u001a\u00020JX\u0096\u0005¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0012\u0010M\u001a\u00020NX\u0096\u0005¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0012\u0010Q\u001a\u00020RX\u0096\u0005¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0012\u0010U\u001a\u00020VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0012\u0010Y\u001a\u00020ZX\u0096\u0005¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0012\u0010]\u001a\u00020^X\u0096\u0005¢\u0006\u0006\u001a\u0004\b_\u0010`R\u0012\u0010a\u001a\u00020bX\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0012\u0010e\u001a\u00020fX\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010lR\u001a\u0010m\u001a\n\u0012\u0004\u0012\u00020o\u0018\u00010nX\u0096\u0005¢\u0006\u0006\u001a\u0004\bp\u0010qR\u0012\u0010r\u001a\u00020sX\u0096\u0005¢\u0006\u0006\u001a\u0004\bt\u0010uR\u0012\u0010v\u001a\u00020wX\u0096\u0005¢\u0006\u0006\u001a\u0004\bx\u0010yR\u0012\u0010z\u001a\u00020{X\u0096\u0005¢\u0006\u0006\u001a\u0004\b|\u0010}R\u001a\u0010~\u001a\b\u0012\u0004\u0012\u00020\u007f0!X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0016\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0016\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0016\u0010\u008a\u0001\u001a\u00030\u008b\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0016\u0010\u008e\u0001\u001a\u00030\u008f\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0016\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0018\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0016\u0010\u009a\u0001\u001a\u00030\u009b\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0015\u0010\u009e\u0001\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001¨\u0006¥\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "classIdToSymbolMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getClassIdToSymbolMap$org_jetbrains_kotlin_fir2ir", "()Ljava/util/Map;", "classIdToSymbolMap$delegate", "Lkotlin/Lazy;", "classIdToTypeMap", "Lorg/jetbrains/kotlin/ir/types/IrType;", "getClassIdToTypeMap$org_jetbrains_kotlin_fir2ir", "classIdToTypeMap$delegate", "capturedTypeCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "errorTypeForCapturedTypeStub", "Lorg/jetbrains/kotlin/ir/types/IrErrorType;", "getErrorTypeForCapturedTypeStub", "()Lorg/jetbrains/kotlin/ir/types/IrErrorType;", "errorTypeForCapturedTypeStub$delegate", "toIrType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "hasFlexibleNullability", Argument.Delimiters.none, "hasFlexibleMutability", "hasFlexibleArrayElementVariance", "addRawTypeAnnotation", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "approximateUpperBounds", "resolvedBounds", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "isMutabilityFlexible", "toIrTypeArgument", "Lorg/jetbrains/kotlin/ir/types/IrTypeArgument;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isRecursive", "visited", Argument.Delimiters.none, "getArrayClassSymbol", "classId", "getBuiltInClassSymbol", "approximateTypeForLocalClassIfNeeded", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrTypeConverter implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Map<ConeCapturedType, IrType> capturedTypeCache;

    /* JADX INFO: renamed from: classIdToSymbolMap$delegate, reason: from kotlin metadata */
    private final Lazy classIdToSymbolMap;

    /* JADX INFO: renamed from: classIdToTypeMap$delegate, reason: from kotlin metadata */
    private final Lazy classIdToTypeMap;
    private final Fir2IrConversionScope conversionScope;

    /* JADX INFO: renamed from: errorTypeForCapturedTypeStub$delegate, reason: from kotlin metadata */
    private final Lazy errorTypeForCapturedTypeStub;

    public Fir2IrTypeConverter(Fir2IrComponents fir2IrComponents, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.conversionScope = fir2IrConversionScope;
        this.classIdToSymbolMap = LazyKt.lazy(new Function0() { // from class: qw4
            public final Object invoke() {
                return Fir2IrTypeConverter.c(this.b);
            }
        });
        this.classIdToTypeMap = LazyKt.lazy(new Function0() { // from class: rw4
            public final Object invoke() {
                return Fir2IrTypeConverter.e(this.b);
            }
        });
        this.capturedTypeCache = new LinkedHashMap();
        this.errorTypeForCapturedTypeStub = LazyKt.lazy(new Function0() { // from class: sw4
            public final Object invoke() {
                return Fir2IrTypeConverter.d();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final IrType approximateTypeForLocalClassIfNeeded(FirClassifierSymbol<?> symbol) {
        Object next;
        IrType irType$default;
        ConeClassLikeLookupTag coneClassLikeLookupTag;
        if (!getConfiguration().getSkipBodies() || !(symbol instanceof FirClassSymbol)) {
            return null;
        }
        FirClass firClass = (FirClass) ((FirClassSymbol) symbol).getFir();
        if (!firClass.getIsLocal()) {
            return null;
        }
        Iterator<T> it = firClass.getSuperTypeRefs().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ConeClassifierLookupTag lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny(FirTypeUtilsKt.getConeType((FirTypeRef) next));
            coneClassLikeLookupTag = lookupTagIfAny instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) lookupTagIfAny : null;
        } while (Intrinsics.areEqual(coneClassLikeLookupTag != null ? coneClassLikeLookupTag.getClassId() : null, StandardClassIds.INSTANCE.getEnum()));
        FirTypeRef firTypeRef = (FirTypeRef) next;
        return (firTypeRef == null || (irType$default = toIrType$default(this, firTypeRef, null, 1, null)) == null) ? getBuiltins().getAnyType() : irType$default;
    }

    private final IrType approximateUpperBounds(List<? extends FirResolvedTypeRef> resolvedBounds) {
        ConeRigidType coneRigidTypeReplaceArgumentsWithStarProjections;
        ConeKotlinType coneKotlinTypeApproximateForIrOrSelf;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        List<? extends FirResolvedTypeRef> list = resolvedBounds;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        ConeKotlinType coneKotlinTypeCommonSuperTypeOrNull = TypeUtilsKt.commonSuperTypeOrNull(typeContext, arrayList);
        coneKotlinTypeCommonSuperTypeOrNull.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeCommonSuperTypeOrNull);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null || (coneRigidTypeReplaceArgumentsWithStarProjections = ConeTypeUtilsKt.replaceArgumentsWithStarProjections(coneClassLikeType)) == null) {
            coneRigidTypeReplaceArgumentsWithStarProjections = coneRigidTypeLowerBoundIfFlexible;
        }
        ConeSimpleKotlinType coneSimpleKotlinType = coneRigidTypeLowerBoundIfFlexible instanceof ConeSimpleKotlinType ? (ConeSimpleKotlinType) coneRigidTypeLowerBoundIfFlexible : null;
        return toIrType$default(this, (coneSimpleKotlinType == null || (coneKotlinTypeApproximateForIrOrSelf = Fir2IrTypeConverterKt.approximateForIrOrSelf(this, coneSimpleKotlinType)) == null) ? coneRigidTypeReplaceArgumentsWithStarProjections : coneKotlinTypeApproximateForIrOrSelf, null, null, false, false, false, false, 63, null);
    }

    public static Map c(Fir2IrTypeConverter fir2IrTypeConverter) {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(standardClassIds.getNothing(), fir2IrTypeConverter.getBuiltins().getNothingClass()), TuplesKt.to(standardClassIds.getAny(), fir2IrTypeConverter.getBuiltins().getAnyClass()), TuplesKt.to(standardClassIds.getUnit(), fir2IrTypeConverter.getBuiltins().getUnitClass()), TuplesKt.to(standardClassIds.getBoolean(), fir2IrTypeConverter.getBuiltins().getBooleanClass()), TuplesKt.to(standardClassIds.getCharSequence(), fir2IrTypeConverter.getBuiltins().getCharSequenceClass()), TuplesKt.to(standardClassIds.getString(), fir2IrTypeConverter.getBuiltins().getStringClass()), TuplesKt.to(standardClassIds.getNumber(), fir2IrTypeConverter.getBuiltins().getNumberClass()), TuplesKt.to(standardClassIds.getLong(), fir2IrTypeConverter.getBuiltins().getLongClass()), TuplesKt.to(standardClassIds.getInt(), fir2IrTypeConverter.getBuiltins().getIntClass()), TuplesKt.to(standardClassIds.getShort(), fir2IrTypeConverter.getBuiltins().getShortClass()), TuplesKt.to(standardClassIds.getByte(), fir2IrTypeConverter.getBuiltins().getByteClass()), TuplesKt.to(standardClassIds.getFloat(), fir2IrTypeConverter.getBuiltins().getFloatClass()), TuplesKt.to(standardClassIds.getDouble(), fir2IrTypeConverter.getBuiltins().getDoubleClass()), TuplesKt.to(standardClassIds.getChar(), fir2IrTypeConverter.getBuiltins().getCharClass()), TuplesKt.to(standardClassIds.getArray(), fir2IrTypeConverter.getBuiltins().getArrayClass())});
    }

    public static IrErrorType d() {
        return Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null);
    }

    public static Map e(Fir2IrTypeConverter fir2IrTypeConverter) {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(standardClassIds.getNothing(), fir2IrTypeConverter.getBuiltins().getNothingType()), TuplesKt.to(standardClassIds.getUnit(), fir2IrTypeConverter.getBuiltins().getUnitType()), TuplesKt.to(standardClassIds.getBoolean(), fir2IrTypeConverter.getBuiltins().getBooleanType()), TuplesKt.to(standardClassIds.getString(), fir2IrTypeConverter.getBuiltins().getStringType()), TuplesKt.to(standardClassIds.getAny(), fir2IrTypeConverter.getBuiltins().getAnyType()), TuplesKt.to(standardClassIds.getLong(), fir2IrTypeConverter.getBuiltins().getLongType()), TuplesKt.to(standardClassIds.getInt(), fir2IrTypeConverter.getBuiltins().getIntType()), TuplesKt.to(standardClassIds.getShort(), fir2IrTypeConverter.getBuiltins().getShortType()), TuplesKt.to(standardClassIds.getByte(), fir2IrTypeConverter.getBuiltins().getByteType()), TuplesKt.to(standardClassIds.getFloat(), fir2IrTypeConverter.getBuiltins().getFloatType()), TuplesKt.to(standardClassIds.getDouble(), fir2IrTypeConverter.getBuiltins().getDoubleType()), TuplesKt.to(standardClassIds.getChar(), fir2IrTypeConverter.getBuiltins().getCharType())});
    }

    private final IrClassSymbol getArrayClassSymbol(ClassId classId) {
        ClassId classId2 = (ClassId) StandardClassIds.INSTANCE.getElementTypeByPrimitiveArrayType().get(classId);
        if (classId2 == null) {
            return null;
        }
        IrClassSymbol irClassSymbol = getBuiltins().getPrimitiveArrayForType().get(getClassIdToTypeMap$org_jetbrains_kotlin_fir2ir().get(classId2));
        if (irClassSymbol != null) {
            return irClassSymbol;
        }
        a11.a("Strange primitiveId ", classId2, " from array: ", classId);
        return null;
    }

    private final IrClassSymbol getBuiltInClassSymbol(ClassId classId) {
        IrClassSymbol irClassSymbol = getClassIdToSymbolMap$org_jetbrains_kotlin_fir2ir().get(classId);
        return irClassSymbol == null ? getArrayClassSymbol(classId) : irClassSymbol;
    }

    private final IrErrorType getErrorTypeForCapturedTypeStub() {
        return (IrErrorType) this.errorTypeForCapturedTypeStub.getValue();
    }

    private final boolean hasFlexibleArrayElementVariance(ConeFlexibleType coneFlexibleType) {
        ConeRigidType lowerBound = coneFlexibleType.getLowerBound();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(lowerBound);
        ClassId classId = classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null;
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (!Intrinsics.areEqual(classId, standardClassIds.getArray()) || ((ConeTypeProjection) ArraysKt.single(lowerBound.getTypeArguments())).getKind() != ProjectionKind.INVARIANT) {
            return false;
        }
        ConeRigidType upperBound = coneFlexibleType.getUpperBound();
        ConeClassLikeLookupTag classLikeLookupTagIfAny2 = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(upperBound);
        return Intrinsics.areEqual(classLikeLookupTagIfAny2 != null ? classLikeLookupTagIfAny2.getClassId() : null, standardClassIds.getArray()) && ((ConeTypeProjection) ArraysKt.single(upperBound.getTypeArguments())).getKind() == ProjectionKind.OUT;
    }

    private final boolean isMutabilityFlexible(ConeFlexibleType coneFlexibleType) {
        FqName fqNameAsSingleFqName;
        ClassId classId;
        FqName fqNameAsSingleFqName2;
        ClassId classId2 = ConeTypeUtilsKt.getClassId(coneFlexibleType.getLowerBound());
        if (classId2 == null || (fqNameAsSingleFqName = classId2.asSingleFqName()) == null || (classId = ConeTypeUtilsKt.getClassId(coneFlexibleType.getUpperBound())) == null || (fqNameAsSingleFqName2 = classId.asSingleFqName()) == null || Intrinsics.areEqual(fqNameAsSingleFqName, fqNameAsSingleFqName2)) {
            return false;
        }
        CommonFlexibleTypeBoundsChecker commonFlexibleTypeBoundsChecker = CommonFlexibleTypeBoundsChecker.INSTANCE;
        return Intrinsics.areEqual(commonFlexibleTypeBoundsChecker.getBaseBoundFqNameByMutability(fqNameAsSingleFqName), commonFlexibleTypeBoundsChecker.getBaseBoundFqNameByMutability(fqNameAsSingleFqName2));
    }

    private final boolean isRecursive(ConeKotlinType coneKotlinType, Set<ConeCapturedType> set) {
        boolean zIsRecursive;
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            for (ConeKotlinTypeProjectionIn coneKotlinTypeProjectionIn : coneKotlinType.getTypeArguments()) {
                if (coneKotlinTypeProjectionIn instanceof ConeKotlinType) {
                    zIsRecursive = isRecursive((ConeKotlinType) coneKotlinTypeProjectionIn, set);
                } else if (coneKotlinTypeProjectionIn instanceof ConeKotlinTypeProjectionIn) {
                    zIsRecursive = isRecursive(coneKotlinTypeProjectionIn.getType(), set);
                } else {
                    zIsRecursive = coneKotlinTypeProjectionIn instanceof ConeKotlinTypeProjectionOut ? isRecursive(((ConeKotlinTypeProjectionOut) coneKotlinTypeProjectionIn).getType(), set) : false;
                }
                if (zIsRecursive) {
                    return true;
                }
            }
            return false;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            return isRecursive(coneFlexibleType.getLowerBound(), set) || isRecursive(coneFlexibleType.getUpperBound(), set);
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            if (!set.add(coneKotlinType)) {
                return true;
            }
            List<ConeKotlinType> supertypes = ((ConeCapturedType) coneKotlinType).getConstructor().getSupertypes();
            if (supertypes != null) {
                List<ConeKotlinType> list = supertypes;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if (isRecursive((ConeKotlinType) it.next(), set)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return isRecursive(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), set);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return false;
            }
            Iterator<T> it2 = intersectedTypes.iterator();
            while (it2.hasNext()) {
                if (isRecursive((ConeKotlinType) it2.next(), set)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrType toIrType$default(Fir2IrTypeConverter fir2IrTypeConverter, ConeKotlinType coneKotlinType, ConversionTypeOrigin conversionTypeOrigin, List list, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        return fir2IrTypeConverter.toIrType(coneKotlinType, (i & 1) != 0 ? ConversionTypeOrigin.DEFAULT : conversionTypeOrigin, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3, (i & 32) != 0 ? false : z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toIrType$lambda$1$1(List list, Fir2IrTypeConverter fir2IrTypeConverter, List list2) {
        list2.getClass();
        CollectionsKt.addAll(list, fir2IrTypeConverter.getAnnotationGenerator().toIrAnnotations(list2));
        return Unit.INSTANCE;
    }

    private final IrTypeArgument toIrTypeArgument(ConeTypeProjection coneTypeProjection, ConversionTypeOrigin conversionTypeOrigin) {
        if (Intrinsics.areEqual(coneTypeProjection, ConeStarProjection.INSTANCE)) {
            return IrStarProjectionImpl.INSTANCE;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            return toIrTypeArgument$toIrTypeArgument(this, conversionTypeOrigin, ((ConeKotlinTypeProjectionIn) coneTypeProjection).getType(), Variance.IN_VARIANCE);
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            return toIrTypeArgument$toIrTypeArgument(this, conversionTypeOrigin, ((ConeKotlinTypeProjectionOut) coneTypeProjection).getType(), Variance.OUT_VARIANCE);
        }
        if (coneTypeProjection instanceof ConeKotlinTypeConflictingProjection) {
            return toIrTypeArgument$toIrTypeArgument(this, conversionTypeOrigin, ((ConeKotlinTypeConflictingProjection) coneTypeProjection).getType(), Variance.INVARIANT);
        }
        if (coneTypeProjection instanceof ConeKotlinType) {
            return ((coneTypeProjection instanceof ConeCapturedType) && this.capturedTypeCache.containsKey(coneTypeProjection) && isRecursive((ConeKotlinType) coneTypeProjection, new LinkedHashSet())) ? IrStarProjectionImpl.INSTANCE : IrSimpleTypeImplKt.makeTypeProjection(toIrType$default(this, (ConeKotlinType) coneTypeProjection, conversionTypeOrigin, null, false, false, false, false, 62, null), Variance.INVARIANT);
        }
        bu8.a();
        return null;
    }

    private static final IrTypeProjection toIrTypeArgument$toIrTypeArgument(Fir2IrTypeConverter fir2IrTypeConverter, ConversionTypeOrigin conversionTypeOrigin, ConeKotlinType coneKotlinType, Variance variance) {
        return IrSimpleTypeImplKt.makeTypeProjection(toIrType$default(fir2IrTypeConverter, coneKotlinType, conversionTypeOrigin, null, false, false, false, false, 62, null), variance);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    public final Map<ClassId, IrClassSymbol> getClassIdToSymbolMap$org_jetbrains_kotlin_fir2ir() {
        return (Map) this.classIdToSymbolMap.getValue();
    }

    public final Map<ClassId, IrType> getClassIdToTypeMap$org_jetbrains_kotlin_fir2ir() {
        return (Map) this.classIdToTypeMap.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public final IrType toIrType(ConeKotlinType coneKotlinType, ConversionTypeOrigin conversionTypeOrigin, List<? extends FirAnnotation> list, boolean z, boolean z2, boolean z3, boolean z4) {
        IrAnnotation noInferAnnotation;
        IrAnnotation extensionFunctionTypeAnnotation;
        IrClassifierSymbol irSymbol;
        IrClass irClassForNotFoundClass;
        coneKotlinType.getClass();
        conversionTypeOrigin.getClass();
        list.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, coneKotlinType);
        if (coneKotlinTypeFullyExpandedType instanceof ConeErrorType) {
            ConeErrorType coneErrorType = (ConeErrorType) coneKotlinTypeFullyExpandedType;
            ConeDiagnostic diagnostic = coneErrorType.getDiagnostic();
            return diagnostic instanceof ConeUnresolvedError ? Fir2IrTypeConverterKt.createErrorType(((ConeUnresolvedError) diagnostic).getQualifier(), coneErrorType.getIsMarkedNullable()) : Fir2IrTypeConverterKt.createErrorType(diagnostic.getReason(), coneErrorType.getIsMarkedNullable());
        }
        if (!(coneKotlinTypeFullyExpandedType instanceof ConeLookupTagBasedType)) {
            if (coneKotlinTypeFullyExpandedType instanceof ConeRawType) {
                ConeRawType coneRawType = (ConeRawType) coneKotlinTypeFullyExpandedType;
                return toIrType(TypeUtilsKt.withNullabilityOf(coneRawType.getLowerBound(), coneRawType.getUpperBound(), TypeComponentsKt.getTypeContext(getSession())), conversionTypeOrigin, list, ConeTypeUtilsKt.isMarkedNullable(coneRawType.getLowerBound()) != ConeTypeUtilsKt.isMarkedNullable(coneRawType.getUpperBound()), isMutabilityFlexible((ConeFlexibleType) coneKotlinTypeFullyExpandedType), false, true);
            }
            if (coneKotlinTypeFullyExpandedType instanceof ConeDynamicType) {
                return new IrDynamicTypeImpl(getAnnotationGenerator().toIrAnnotations(list), Variance.INVARIANT);
            }
            if (coneKotlinTypeFullyExpandedType instanceof ConeFlexibleType) {
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinTypeFullyExpandedType;
                ConeRigidType upperBound = coneFlexibleType.getUpperBound();
                if (!(upperBound instanceof ConeClassLikeType)) {
                    return toIrType(upperBound, conversionTypeOrigin, list, ConeTypeUtilsKt.isMarkedNullable(coneFlexibleType.getLowerBound()) != ConeTypeUtilsKt.isMarkedNullable(coneFlexibleType.getUpperBound()), isMutabilityFlexible(coneFlexibleType), false, false);
                }
                ConeRigidType lowerBound = coneFlexibleType.getLowerBound();
                boolean zContains = coneFlexibleType.getAttributes().contains((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE);
                ConeRigidType coneRigidTypeReplaceArguments = ((lowerBound instanceof ConeClassLikeType) && Intrinsics.areEqual(((ConeClassLikeType) lowerBound).getLookupTag(), ((ConeClassLikeType) upperBound).getLookupTag()) && !zContains) ? typeContext.replaceArguments((RigidTypeMarker) lowerBound, (List<? extends TypeArgumentMarker>) typeContext.getArguments(upperBound)) : lowerBound;
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) upperBound;
                return toIrType(TypeUtilsKt.withAttributes(typeContext.m695withNullability((RigidTypeMarker) coneRigidTypeReplaceArguments, coneClassLikeType.getIsMarkedNullable()), coneFlexibleType.getAttributes()), conversionTypeOrigin, list, ConeTypeUtilsKt.isMarkedNullable(lowerBound) != coneClassLikeType.getIsMarkedNullable(), isMutabilityFlexible(coneFlexibleType), hasFlexibleArrayElementVariance(coneFlexibleType), zContains);
            }
            if (coneKotlinTypeFullyExpandedType instanceof ConeCapturedType) {
                IrType irType = this.capturedTypeCache.get(coneKotlinTypeFullyExpandedType);
                if (irType != null) {
                    return irType;
                }
                this.capturedTypeCache.put((ConeCapturedType) coneKotlinTypeFullyExpandedType, (IrType) getErrorTypeForCapturedTypeStub());
                IrType irType$default = toIrType$default(this, Fir2IrTypeConverterKt.approximateForIrOrSelf(this, coneKotlinTypeFullyExpandedType), conversionTypeOrigin, list, z, z2, false, z4, 16, null);
                this.capturedTypeCache.put((ConeCapturedType) coneKotlinTypeFullyExpandedType, irType$default);
                return irType$default;
            }
            if (coneKotlinTypeFullyExpandedType instanceof ConeDefinitelyNotNullType) {
                return IrTypesKt.makeNotNull(toIrType$default(this, ((ConeDefinitelyNotNullType) coneKotlinTypeFullyExpandedType).getOriginal(), conversionTypeOrigin, null, false, false, false, false, 62, null));
            }
            if (coneKotlinTypeFullyExpandedType instanceof ConeIntersectionType) {
                ConeKotlinType coneKotlinTypeApproximateForIrOrNull = Fir2IrTypeConverterKt.approximateForIrOrNull(this, coneKotlinTypeFullyExpandedType);
                coneKotlinTypeApproximateForIrOrNull.getClass();
                return toIrType$default(this, coneKotlinTypeApproximateForIrOrNull, conversionTypeOrigin, null, false, false, false, false, 62, null);
            }
            if ((coneKotlinTypeFullyExpandedType instanceof ConeStubType) || (coneKotlinTypeFullyExpandedType instanceof ConeIntegerLiteralType) || (coneKotlinTypeFullyExpandedType instanceof ConeTypeVariableType)) {
                return Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null);
            }
            bu8.a();
            return null;
        }
        final ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, getAnnotationGenerator().toIrAnnotations(list));
        IrClassifierSymbol builtInClassSymbol = getBuiltInClassSymbol(ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType));
        if (builtInClassSymbol == null) {
            ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) coneKotlinTypeFullyExpandedType;
            FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(this, coneLookupTagBasedType.getLookupTag());
            if (symbol != null) {
                IrType irTypeApproximateTypeForLocalClassIfNeeded = approximateTypeForLocalClassIfNeeded(symbol);
                if (irTypeApproximateTypeForLocalClassIfNeeded != null) {
                    return irTypeApproximateTypeForLocalClassIfNeeded;
                }
                irSymbol = SymbolConversionUtilsKt.toIrSymbol(this, symbol, conversionTypeOrigin, new Function1() { // from class: tw4
                    public final Object invoke(Object obj) {
                        return Fir2IrTypeConverter.toIrType$lambda$1$1(arrayList, this, (List) obj);
                    }
                });
            } else {
                irSymbol = null;
            }
            if (irSymbol == null) {
                ConeClassifierLookupTag lookupTag = coneLookupTagBasedType.getLookupTag();
                ConeClassLikeLookupTag coneClassLikeLookupTag = lookupTag instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) lookupTag : null;
                builtInClassSymbol = (coneClassLikeLookupTag == null || (irClassForNotFoundClass = getClassifierStorage().getIrClassForNotFoundClass(coneClassLikeLookupTag)) == null) ? null : irClassForNotFoundClass.getSymbol();
                if (builtInClassSymbol == null) {
                    return Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null);
                }
            } else {
                builtInClassSymbol = irSymbol;
            }
        }
        IrSpecialAnnotationsProvider specialAnnotationsProvider = getSpecialAnnotationsProvider();
        if (specialAnnotationsProvider != null) {
            if (CompilerConeAttributesKt.getHasEnhancedNullability(coneKotlinTypeFullyExpandedType)) {
                arrayList.add(specialAnnotationsProvider.generateEnhancedNullabilityAnnotation());
            }
            if (z) {
                arrayList.add(specialAnnotationsProvider.generateFlexibleNullabilityAnnotation());
            }
            if (z2) {
                arrayList.add(specialAnnotationsProvider.generateFlexibleMutabilityAnnotation());
            }
            if (z3) {
                arrayList.add(specialAnnotationsProvider.generateFlexibleArrayElementVarianceAnnotation());
            }
            if (z4) {
                arrayList.add(specialAnnotationsProvider.generateRawTypeAnnotation());
            }
        }
        if (CompilerConeAttributesKt.isExtensionFunctionType(coneKotlinTypeFullyExpandedType) && FirAnnotationUtilsKt.getAnnotationsByClassId(list, StandardClassIds$Annotations.INSTANCE.getExtensionFunctionType(), getSession()).isEmpty() && (extensionFunctionTypeAnnotation = getBuiltins().getExtensionFunctionTypeAnnotation()) != null) {
            arrayList.add(extensionFunctionTypeAnnotation);
        }
        if (CompilerConeAttributesKt.getHasNoInfer(coneKotlinTypeFullyExpandedType) && FirAnnotationUtilsKt.getAnnotationsByClassId(list, StandardClassIds$Annotations.INSTANCE.getNoInfer(), getSession()).isEmpty() && (noInferAnnotation = getBuiltins().getNoInferAnnotation()) != null) {
            arrayList.add(noInferAnnotation);
        }
        for (FirAnnotation firAnnotation : CustomAnnotationTypeAttributeKt.getTypeAnnotations(coneKotlinTypeFullyExpandedType)) {
            List<? extends FirAnnotation> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) it.next()).getAnnotationTypeRef();
                        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
                        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                        ConeClassLikeType coneClassLikeType2 = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
                        FirResolvedTypeRef annotationTypeRef2 = firAnnotation.getAnnotationTypeRef();
                        FirResolvedTypeRef firResolvedTypeRef2 = annotationTypeRef2 instanceof FirResolvedTypeRef ? annotationTypeRef2 : null;
                        ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
                        if (Intrinsics.areEqual(coneClassLikeType2, coneType2 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType2 : null)) {
                        }
                    }
                }
            }
            IrAnnotation irAnnotationConvertToIrAnnotation = getCallGenerator().convertToIrAnnotation(firAnnotation);
            IrAnnotation irAnnotation = irAnnotationConvertToIrAnnotation instanceof IrAnnotation ? irAnnotationConvertToIrAnnotation : null;
            if (irAnnotation != null) {
                arrayList.add(irAnnotation);
            }
        }
        ConeKotlinType coneKotlinTypeApproximateForIrOrSelf = Fir2IrTypeConverterKt.approximateForIrOrSelf(this, coneKotlinTypeFullyExpandedType);
        if (coneKotlinTypeApproximateForIrOrSelf instanceof ConeTypeParameterType) {
            ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneKotlinTypeApproximateForIrOrSelf;
            if (this.conversionScope.shouldEraseType(coneTypeParameterType)) {
                return approximateUpperBounds(coneTypeParameterType.getLookupTag().getTypeParameterSymbol().getResolvedBounds());
            }
        }
        boolean zIsMarkedNullable = ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeApproximateForIrOrSelf);
        ConeTypeProjection[] typeArguments = coneKotlinTypeApproximateForIrOrSelf.getTypeArguments();
        ArrayList arrayList2 = new ArrayList(typeArguments.length);
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            arrayList2.add(toIrTypeArgument(coneTypeProjection, conversionTypeOrigin));
        }
        return IrSimpleTypeImplKt.IrSimpleTypeImpl(builtInClassSymbol, zIsMarkedNullable, arrayList2, arrayList);
    }

    public static /* synthetic */ IrType toIrType$default(Fir2IrTypeConverter fir2IrTypeConverter, FirTypeRef firTypeRef, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 1) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrTypeConverter.toIrType(firTypeRef, conversionTypeOrigin);
    }

    public final IrType toIrType(FirTypeRef firTypeRef, ConversionTypeOrigin conversionTypeOrigin) {
        firTypeRef.getClass();
        conversionTypeOrigin.getClass();
        this.capturedTypeCache.clear();
        if (!(firTypeRef instanceof FirResolvedTypeRef)) {
            return Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null);
        }
        if (!(firTypeRef instanceof FirImplicitBuiltinTypeRef)) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) firTypeRef;
            return toIrType$default(this, firResolvedTypeRef.getConeType(), conversionTypeOrigin, firResolvedTypeRef.getAnnotations(), false, false, false, false, 60, null);
        }
        if (firTypeRef instanceof FirImplicitNothingTypeRef) {
            return getBuiltins().getNothingType();
        }
        if (firTypeRef instanceof FirImplicitUnitTypeRef) {
            return getBuiltins().getUnitType();
        }
        if (firTypeRef instanceof FirImplicitBooleanTypeRef) {
            return getBuiltins().getBooleanType();
        }
        if (firTypeRef instanceof FirImplicitStringTypeRef) {
            return getBuiltins().getStringType();
        }
        if (firTypeRef instanceof FirImplicitAnyTypeRef) {
            return getBuiltins().getAnyType();
        }
        if (firTypeRef instanceof FirImplicitIntTypeRef) {
            return getBuiltins().getIntType();
        }
        if (firTypeRef instanceof FirImplicitNullableAnyTypeRef) {
            return getBuiltins().getAnyNType();
        }
        if (firTypeRef instanceof FirImplicitNullableNothingTypeRef) {
            return getBuiltins().getNothingNType();
        }
        FirImplicitBuiltinTypeRef firImplicitBuiltinTypeRef = (FirImplicitBuiltinTypeRef) firTypeRef;
        return toIrType$default(this, firImplicitBuiltinTypeRef.getConeType(), conversionTypeOrigin, firImplicitBuiltinTypeRef.getAnnotations(), false, false, false, false, 60, null);
    }
}
