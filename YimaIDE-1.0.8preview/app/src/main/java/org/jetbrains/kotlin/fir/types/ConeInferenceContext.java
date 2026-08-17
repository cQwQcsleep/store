package org.jetbrains.kotlin.fir.types;

import defpackage.dwe;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.AllowedToUsedOnlyInK1;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.PrimitivesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeIntermediateDiagnostic;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeTypeSubstitutorByTypeConstructorKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.AbstractTypeRefiner;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.model.AnnotationMarker;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;
import org.jetbrains.kotlin.types.model.DefinitelyNotNullTypeMarker;
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker;
import org.jetbrains.kotlin.types.model.K2Only;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.StubTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContext;
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ò\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0016JF\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u001eH\u0016J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J(\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u0014H\u0016J\f\u00106\u001a\u00020\u0014*\u00020\u0012H\u0016J\f\u0010!\u001a\u00020\u0014*\u00020\u000fH\u0016J\f\u00107\u001a\u000208*\u000209H\u0016J\f\u0010:\u001a\u00020#*\u00020\u0012H\u0016J\f\u0010;\u001a\u00020#*\u00020\u0012H\u0016J\f\u0010:\u001a\u00020#*\u00020\u000fH\u0016J \u0010<\u001a\u00020\u0014*\u00020\u00122\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00140>H\u0016J\"\u0010?\u001a\u00020\u0014*\u0004\u0018\u00010\u00122\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00140>H\u0002J\f\u0010@\u001a\u00020\u0014*\u00020\u001cH\u0002J\u0014\u0010A\u001a\u0004\u0018\u00010\u0012*\b\u0012\u0004\u0012\u00020\u00120BH\u0016J\f\u0010C\u001a\u00020\u0014*\u00020\u0012H\u0016J\f\u0010D\u001a\u00020\u0014*\u00020\u0012H\u0016J\u0014\u0010E\u001a\u00020\r*\u00020\u00122\u0006\u0010 \u001a\u00020\u0014H\u0016J\u0014\u0010F\u001a\u00020\r*\u00020\u00122\u0006\u0010G\u001a\u00020\u0014H\u0016J\f\u0010F\u001a\u00020H*\u00020\u000fH\u0016J0\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u001f2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00120\u001e2\b\u0010M\u001a\u0004\u0018\u00010\u00122\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u0002092\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u00020T2\u0006\u0010Q\u001a\u00020RH\u0016J\f\u0010U\u001a\u00020\r*\u00020\u0012H\u0016J\u001a\u0010V\u001a\u00020H*\u00020\u000f2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J \u0010V\u001a\u00020H*\u00020\u000f2\u0012\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f0>H\u0016J\f\u0010Y\u001a\u00020\u0014*\u00020\u0012H\u0016J\f\u0010Z\u001a\u00020\u0014*\u00020\u0012H\u0016J\f\u0010[\u001a\u00020\u0014*\u00020\u001cH\u0016J\f\u0010\\\u001a\u000208*\u00020RH\u0016J\f\u0010]\u001a\u00020^*\u00020_H\u0016J\u000e\u0010-\u001a\u0004\u0018\u00010`*\u00020_H\u0016J\u0010\u0010a\u001a\u00020\u0012*\u00020_H\u0017b\u0002\bbJ\u0010\u0010c\u001a\u00020\u0014*\u00020_H\u0017b\u0002\bbJ\u0010\u0010d\u001a\u00020\u0014*\u00020_H\u0017b\u0002\beJ$\u0010f\u001a\u00020\u0014*\u00020J2\u0016\u0010g\u001a\u0012\u0012\u0004\u0012\u00020J0hj\b\u0012\u0004\u0012\u00020J`iH\u0002J\f\u0010j\u001a\u00020\u001a*\u00020kH\u0016J\u001c\u0010l\u001a\u00020m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00120oH\u0016J\b\u0010p\u001a\u00020mH\u0016J\b\u0010q\u001a\u00020rH\u0016J\u0014\u0010s\u001a\u00020\r*\u00020t2\u0006\u0010(\u001a\u00020\u0012H\u0016J\f\u0010u\u001a\u00020v*\u00020RH\u0016J\f\u0010w\u001a\u00020\u0014*\u00020\u0012H\u0016J\u0010\u0010x\u001a\u00020\u0014*\u00020\u0012H\u0017b\u0002\beJ\f\u0010y\u001a\u00020\u0014*\u00020\u001cH\u0016J\f\u0010z\u001a\u00020\u0014*\u00020{H\u0016J\u001b\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u007f2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0011\u0010\u0081\u0001\u001a\u00020}2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\f\u0010N\u001a\u00020O*\u00020_H\u0016J\r\u0010\u0082\u0001\u001a\u00020\u0014*\u00020_H\u0016J\r\u0010\u0083\u0001\u001a\u00020\u0014*\u00020\u001cH\u0016J\r\u0010\u0084\u0001\u001a\u00020\u0012*\u00020\u0012H\u0016J\r\u0010\u0085\u0001\u001a\u00020\u0014*\u00020\u001cH\u0016J\r\u0010\u0086\u0001\u001a\u00020\r*\u00020\u0012H\u0016J\r\u0010\u0087\u0001\u001a\u00020}*\u00020\u001cH\u0016J\u001a\u0010\u0088\u0001\u001a\u0004\u0018\u00010H2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001eH\u0016J#\u0010\u008a\u0001\u001a\r\u0012\t\u0012\u0007\u0012\u0002\b\u00030\u008b\u00010\u001e2\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00120\u001eH\u0016J\r\u0010\u008d\u0001\u001a\u00020\u0014*\u00020%H\u0002J\u001c\u0010\u008e\u0001\u001a\u00020\r*\u00020\u00122\r\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020%0\u001eH\u0016J\u0018\u0010\u0090\u0001\u001a\u00020\b*\u00020\u001c2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0012H\u0016J\r\u0010\u0092\u0001\u001a\u00020\u0014*\u00020\u0012H\u0016J\r\u0010\u0093\u0001\u001a\u00020\u0014*\u00020\u0012H\u0016J!\u0010\u0094\u0001\u001a\u00020\u0014*\u00020\r2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00140>H\u0016J\r\u0010\u0095\u0001\u001a\u00020\u0014*\u00020\u0012H\u0016J\f\u0010\"\u001a\u00020#*\u00020\u0012H\u0016J\u0013\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\r0\u001e*\u00020\u0012H\u0016J\r\u0010\u0097\u0001\u001a\u00020\r*\u00020\u0012H\u0016J\u000f\u0010\u0098\u0001\u001a\u0004\u0018\u00010\r*\u00020\u0012H\u0002J\u0010\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u0001*\u00020\u0012H\u0016J\u001d\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0007\u0010\u009d\u0001\u001a\u00020#2\b\u0010\u009e\u0001\u001a\u00030\u009a\u0001H\u0016J\u001d\u0010\u009f\u0001\u001a\u00030\u009c\u00012\u0007\u0010\u009d\u0001\u001a\u00020#2\b\u0010\u009e\u0001\u001a\u00030\u009a\u0001H\u0016J\u001c\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u00020\u00122\u0007\u0010£\u0001\u001a\u00020\u0012H\u0016J\u000f\u0010¤\u0001\u001a\u0004\u0018\u00010\r*\u00020\u000fH\u0016J\t\u0010¥\u0001\u001a\u00020\u0014H\u0016J\t\u0010¦\u0001\u001a\u00020\u0014H\u0016J\r\u0010§\u0001\u001a\u00020\r*\u00020\u0012H\u0016J\u0014\u0010¨\u0001\u001a\u0004\u0018\u00010m2\u0007\u0010©\u0001\u001a\u00020\u0012H\u0016J\t\u0010ª\u0001\u001a\u00020\u0014H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010«\u0001\u001a\u00020\u00148VX\u0096\u0004¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u0017\u0010\u00ad\u0001\u001a\u00020\u00148VX\u0096\u0004¢\u0006\b\u001a\u0006\b®\u0001\u0010¬\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006¯\u0001²\u0006\u000b\u0010°\u0001\u001a\u00020mX\u008a\u0084\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "Lorg/jetbrains/kotlin/types/model/TypeSystemInferenceExtensionContext;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "nullableNothingType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "nullableAnyType", "nothingType", "anyType", "createFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lowerBound", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "upperBound", "createTrivialFlexibleTypeOrSelf", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "isTriviallyFlexible", Argument.Delimiters.none, "flexibleType", "Lorg/jetbrains/kotlin/types/model/FlexibleTypeMarker;", "makeLowerBoundDefinitelyNotNullOrNotNull", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "createSimpleType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "constructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "nullable", "isExtensionFunction", "contextParameterCount", Argument.Delimiters.none, "attributes", "Lorg/jetbrains/kotlin/types/model/AnnotationMarker;", "createTypeArgument", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjection;", ModuleXmlParser.TYPE, "variance", "Lorg/jetbrains/kotlin/types/model/TypeVariance;", "createStarProjection", "Lorg/jetbrains/kotlin/fir/types/ConeStarProjection;", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "newTypeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "typeSystemContext", "Lorg/jetbrains/kotlin/types/model/TypeSystemContext;", "errorTypesEqualToAnything", "stubTypesEqualToAnything", "dnnTypesEqualToFlexible", "canHaveUndefinedNullability", "getOriginalTypeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableTypeConstructor;", "Lorg/jetbrains/kotlin/types/model/StubTypeMarker;", "typeDepth", "typeDepthForApproximation", "contains", "predicate", "Lkotlin/Function1;", "containsInternal", "isUnitTypeConstructor", "singleBestRepresentative", Argument.Delimiters.none, "isUnit", "isBuiltinFunctionTypeOrSubtype", "withNullability", "makeDefinitelyNotNullOrNotNull", "preserveAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "createCapturedType", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "constructorProjection", "constructorSupertypes", "lowerType", "captureStatus", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "createStubTypeForBuilderInference", "typeVariable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "createStubTypeForTypeVariablesInSubtyping", "Lorg/jetbrains/kotlin/fir/types/ConeStubTypeForTypeVariableInSubtyping;", "removeAnnotations", "replaceArguments", "newArguments", "replacement", "hasExactAnnotation", "hasNoInferAnnotation", "isFinalClassConstructor", "freshTypeConstructor", "typeConstructorProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/types/model/CapturedTypeMarker;", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "withNotNullProjection", "Lorg/jetbrains/kotlin/builtins/functions/AllowedToUsedOnlyInK1;", "isProjectionNotNull", "hasRawSuperTypeRecursive", "Lorg/jetbrains/kotlin/types/model/K2Only;", "hasRawSuperTypeInternal", "seen", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "original", "Lorg/jetbrains/kotlin/types/model/DefinitelyNotNullTypeMarker;", "typeSubstitutorByTypeConstructor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "map", Argument.Delimiters.none, "createEmptySubstitutor", "createSubstitutionFromSubtypingStubTypesToTypeVariables", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "safeSubstitute", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "defaultType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariableType;", "isSpecial", "hasEnhancedNullability", "isTypeVariable", "isContainedInInvariantOrContravariantPositions", "Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;", "createErrorType", "Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "debugName", Argument.Delimiters.none, "delegatedType", "createUninferredType", "isOldCapturedType", "isCapturedTypeConstructor", "eraseContainingTypeParameters", "isTypeParameterTypeConstructor", "removeExactAnnotation", "toErrorType", "findCommonIntegerLiteralTypesSuperType", "explicitSupertypes", "unionTypeAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "types", "isCustomAttribute", "replaceCustomAttributes", "newAttributes", "getApproximatedIntegerLiteralType", "expectedType", "isSignedOrUnsignedNumberType", "isFunctionOrKFunctionWithAnySuspendability", "isTypeOrSubtypeOf", "isExtensionFunctionType", "extractArgumentsForFunctionTypeOrSubtype", "getFunctionTypeFromSupertypes", "getFunctionTypeFromSupertypesOrNull", "functionTypeKind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getNonReflectFunctionTypeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "parametersNumber", "kind", "getReflectFunctionTypeConstructor", "createTypeWithUpperBoundForIntersectionResult", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "firstCandidate", "secondCandidate", "getUpperBoundForApproximationOfIntersectionType", "usePreciseSimplificationToFlexibleLowerConstraint", "simplifyFlexibleUpperConstraintWithDnnBoundToNullable", "convertToNonRaw", "createSubstitutorForSuperTypes", "baseType", "supportsImprovedVarianceInCst", "isK2", "()Z", "lexicographicVariableReadinessCalculation", "getLexicographicVariableReadinessCalculation", "org.jetbrains.kotlin:providers", "substitutor"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ConeInferenceContext extends ConeTypeContext, TypeSystemInferenceExtensionContext {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            try {
                iArr[TypeVariance.INV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TypeVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static ConeSubstitutor a(Map map, ConeInferenceContext coneInferenceContext) {
        return ConeSubstitutorByMapKt.substitutorByMap$default(map, coneInferenceContext.getSession(), false, 4, null);
    }

    static String c(KotlinTypeMarker kotlinTypeMarker) {
        return "Expected type is intersection, found " + kotlinTypeMarker;
    }

    private default boolean containsInternal(KotlinTypeMarker kotlinTypeMarker, Function1<? super KotlinTypeMarker, Boolean> function1) {
        if (kotlinTypeMarker == null) {
            return false;
        }
        if (((Boolean) function1.invoke(kotlinTypeMarker)).booleanValue()) {
            return true;
        }
        ConeFlexibleType coneFlexibleType = kotlinTypeMarker instanceof ConeFlexibleType ? (ConeFlexibleType) kotlinTypeMarker : null;
        if (coneFlexibleType != null) {
            if (containsInternal(coneFlexibleType.getLowerBound(), function1)) {
                return true;
            }
            if (!coneFlexibleType.getIsTrivial() && containsInternal(coneFlexibleType.getUpperBound(), function1)) {
                return true;
            }
        }
        if ((kotlinTypeMarker instanceof ConeDefinitelyNotNullType) && containsInternal(((ConeDefinitelyNotNullType) kotlinTypeMarker).getOriginal(), function1)) {
            return true;
        }
        if (kotlinTypeMarker instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) kotlinTypeMarker).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return false;
            }
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                if (containsInternal((ConeKotlinType) it.next(), function1)) {
                    return true;
                }
            }
            return false;
        }
        if (kotlinTypeMarker instanceof ConeCapturedType) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(((ConeCapturedType) kotlinTypeMarker).getConstructor().getProjection());
            return type != null && containsInternal(type, function1);
        }
        int iArgumentsCount = argumentsCount(kotlinTypeMarker);
        for (int i = 0; i < iArgumentsCount; i++) {
            ConeTypeProjection coneTypeProjectionM674getArgument = m674getArgument(kotlinTypeMarker, i);
            if (!isStarProjection(coneTypeProjectionM674getArgument) && containsInternal(m677getType((TypeArgumentMarker) coneTypeProjectionM674getArgument), function1)) {
                return true;
            }
        }
        return false;
    }

    static Iterable e(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneInferenceContext.supertypes(coneInferenceContext.typeConstructor(coneKotlinType));
    }

    private static ConeSubstitutor eraseContainingTypeParameters$lambda$2(Lazy<? extends ConeSubstitutor> lazy) {
        return (ConeSubstitutor) lazy.getValue();
    }

    static boolean g(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return FunctionalTypeUtilsKt.isSomeFunctionType((ConeRigidType) coneInferenceContext.lowerBoundIfFlexible(coneKotlinType), coneInferenceContext.getSession());
    }

    private default ConeKotlinType getFunctionTypeFromSupertypesOrNull(KotlinTypeMarker kotlinTypeMarker) {
        if (isBuiltinFunctionTypeOrSubtype(kotlinTypeMarker)) {
            return m640getFunctionTypeFromSupertypes(kotlinTypeMarker);
        }
        return null;
    }

    static TypeArgumentMarker h(ConeInferenceContext coneInferenceContext, Lazy lazy, TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        ConeKotlinType coneKotlinTypeM677getType = coneInferenceContext.m677getType(typeArgumentMarker);
        if (coneKotlinTypeM677getType != null) {
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = (ConeTypeParameterLookupTag) coneInferenceContext.m680getTypeParameterClassifier(coneInferenceContext.typeConstructor(coneKotlinTypeM677getType));
            if ((coneTypeParameterLookupTag != null ? coneTypeParameterLookupTag.getTypeParameterSymbol() : null) != null) {
                return coneInferenceContext.m633createTypeArgument((KotlinTypeMarker) coneInferenceContext.m657safeSubstitute((TypeSubstitutorMarker) eraseContainingTypeParameters$lambda$2(lazy), (KotlinTypeMarker) coneKotlinTypeM677getType), TypeVariance.OUT);
            }
        }
        return typeArgumentMarker;
    }

    private default boolean hasRawSuperTypeInternal(ConeCapturedType coneCapturedType, final HashSet<ConeCapturedType> hashSet) {
        List<ConeKotlinType> supertypes = coneCapturedType.getConstructor().getSupertypes();
        if (supertypes != null) {
            List<ConeKotlinType> list = supertypes;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                for (ConeKotlinType coneKotlinType : list) {
                    if (TypeUtilsKt.isRaw(coneKotlinType) || contains(coneKotlinType, new Function1() { // from class: wp2
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(ConeInferenceContext.hasRawSuperTypeInternal$lambda$0$0(hashSet, this, (KotlinTypeMarker) obj));
                        }
                    })) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static boolean hasRawSuperTypeInternal$lambda$0$0(HashSet hashSet, ConeInferenceContext coneInferenceContext, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return (kotlinTypeMarker instanceof ConeCapturedType) && hashSet.add(kotlinTypeMarker) && coneInferenceContext.hasRawSuperTypeInternal((ConeCapturedType) kotlinTypeMarker, hashSet);
    }

    private default boolean isCustomAttribute(AnnotationMarker annotationMarker) {
        Map classIdByCompilerAttributeKey = CompilerConeAttributes.INSTANCE.getClassIdByCompilerAttributeKey();
        ConeAttribute coneAttribute = annotationMarker instanceof ConeAttribute ? (ConeAttribute) annotationMarker : null;
        return (classIdByCompilerAttributeKey.containsKey(coneAttribute != null ? coneAttribute.getKey() : null) || (annotationMarker instanceof ParameterNameTypeAttribute) || (annotationMarker instanceof CustomAnnotationTypeAttribute)) ? false : true;
    }

    private default boolean isUnitTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) && Intrinsics.areEqual(((ConeClassLikeLookupTag) typeConstructorMarker).getClassId(), StandardClassIds.INSTANCE.getUnit());
    }

    static boolean l(ConeInferenceContext coneInferenceContext, ConeRigidType coneRigidType, Ref.ObjectRef objectRef, RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        List<ConeClassLikeType> listFastCorrespondingSupertypes = coneInferenceContext.fastCorrespondingSupertypes(coneRigidType, coneInferenceContext.m691typeConstructor(rigidTypeMarker));
        if (listFastCorrespondingSupertypes == null) {
            return false;
        }
        List<ConeClassLikeType> list = listFastCorrespondingSupertypes;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (ConeClassLikeType coneClassLikeType : list) {
            boolean zIsSomeFunctionType = FunctionalTypeUtilsKt.isSomeFunctionType(coneClassLikeType, coneInferenceContext.getSession());
            if (zIsSomeFunctionType) {
                objectRef.element = coneClassLikeType;
            }
            if (zIsSomeFunctionType) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: anyType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType m621anyType() {
        return getSession().getBuiltinTypes().getAnyType().getConeType();
    }

    default boolean canHaveUndefinedNullability(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return (kotlinTypeMarker instanceof ConeCapturedType) || (kotlinTypeMarker instanceof ConeTypeVariableType) || (kotlinTypeMarker instanceof ConeTypeParameterType);
        }
        w01.a("Failed requirement.");
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeTypeContext
    default CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return ((ConeCapturedType) capturedTypeMarker).getConstructor().getCaptureStatus();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default boolean contains(KotlinTypeMarker kotlinTypeMarker, Function1<? super KotlinTypeMarker, Boolean> function1) {
        kotlinTypeMarker.getClass();
        function1.getClass();
        return containsInternal(kotlinTypeMarker, function1);
    }

    default int contextParameterCount(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return FunctionalTypeUtilsKt.contextParameterTypes(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound((ConeKotlinType) kotlinTypeMarker), getSession()).size();
        }
        w01.a("Failed requirement.");
        return 0;
    }

    /* JADX INFO: renamed from: convertToNonRaw, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m622convertToNonRaw(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.convertToNonRawVersion((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return null;
    }

    default ConeCapturedType createCapturedType(TypeArgumentMarker constructorProjection, List<? extends KotlinTypeMarker> constructorSupertypes, KotlinTypeMarker lowerType, CaptureStatus captureStatus) {
        constructorProjection.getClass();
        constructorSupertypes.getClass();
        captureStatus.getClass();
        if (!(lowerType == null ? true : lowerType instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (constructorProjection instanceof ConeTypeProjection) {
            return new ConeCapturedType(false, new ConeCapturedTypeConstructor((ConeTypeProjection) constructorProjection, (ConeKotlinType) lowerType, captureStatus, constructorSupertypes, null, 16, null), null, 5, null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: createErrorType, reason: merged with bridge method [inline-methods] */
    default ConeErrorType m625createErrorType(String debugName, RigidTypeMarker delegatedType) {
        debugName.getClass();
        return new ConeErrorType(new ConeIntermediateDiagnostic(debugName), false, delegatedType != null ? (ConeRigidType) delegatedType : null, null, null, null, null, 122, null);
    }

    /* JADX INFO: renamed from: createFlexibleType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m626createFlexibleType(RigidTypeMarker lowerBound, RigidTypeMarker upperBound) {
        lowerBound.getClass();
        upperBound.getClass();
        if (!(lowerBound instanceof ConeRigidType)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (upperBound instanceof ConeRigidType) {
            return TypeUtilsKt.coneFlexibleOrSimpleType(this, (ConeKotlinType) lowerBound, (ConeKotlinType) upperBound, false);
        }
        w01.a("Failed requirement.");
        return null;
    }

    default ConeSimpleKotlinType createSimpleType(TypeConstructorMarker constructor, List<? extends TypeArgumentMarker> arguments, boolean nullable, boolean isExtensionFunction, int contextParameterCount, List<? extends AnnotationMarker> attributes) {
        ArrayList arrayList;
        ConeAttributes withExtensionFunctionType;
        constructor.getClass();
        arguments.getClass();
        if (!(constructor instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (attributes != null) {
            arrayList = new ArrayList();
            for (Object obj : attributes) {
                if (obj instanceof ConeAttribute) {
                    arrayList.add(obj);
                }
            }
        } else {
            arrayList = null;
        }
        if (isExtensionFunction || contextParameterCount > 0) {
            if (!(constructor instanceof ConeClassLikeLookupTag)) {
                w01.a("Failed requirement.");
                return null;
            }
            if ((arrayList == null || arrayList.isEmpty()) && contextParameterCount == 0) {
                withExtensionFunctionType = ConeAttributes.INSTANCE.getWithExtensionFunctionType();
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                if (isExtensionFunction) {
                    arrayList.add(CompilerConeAttributes.ExtensionFunctionType.INSTANCE);
                }
                if (contextParameterCount > 0) {
                    arrayList.add(new CompilerConeAttributes.ContextFunctionTypeParams(contextParameterCount));
                }
                withExtensionFunctionType = ConeAttributes.INSTANCE.create(arrayList);
            }
        } else if (arrayList == null || (withExtensionFunctionType = ConeAttributes.INSTANCE.create(arrayList)) == null) {
            withExtensionFunctionType = ConeAttributes.INSTANCE.getEmpty();
        }
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) constructor;
        if (coneTypeConstructorMarker instanceof ConeClassLikeLookupTag) {
            return new ConeClassLikeTypeImpl((ConeClassLikeLookupTag) constructor, (ConeTypeProjection[]) arguments.toArray(new ConeTypeProjection[0]), nullable, withExtensionFunctionType);
        }
        if (coneTypeConstructorMarker instanceof ConeTypeParameterLookupTag) {
            return new ConeTypeParameterTypeImpl((ConeTypeParameterLookupTag) constructor, nullable, withExtensionFunctionType);
        }
        if (coneTypeConstructorMarker instanceof ConeIntersectionType) {
            ConeIntersectionType coneIntersectionType = (ConeIntersectionType) constructor;
            if (withExtensionFunctionType == coneIntersectionType.getAttributes()) {
                return coneIntersectionType;
            }
            Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                arrayList2.add(TypeUtilsKt.withAttributes((ConeKotlinType) it.next(), withExtensionFunctionType));
            }
            ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
            return new ConeIntersectionType(arrayList2, upperBoundForApproximation != null ? TypeUtilsKt.withAttributes(upperBoundForApproximation, withExtensionFunctionType) : null);
        }
        if ((coneTypeConstructorMarker instanceof ConeCapturedTypeConstructor) || (coneTypeConstructorMarker instanceof ConeIntegerLiteralType) || (coneTypeConstructorMarker instanceof ConeStubTypeConstructor) || (coneTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor)) {
            f2f.a("Unsupported type constructor: ", Reflection.getOrCreateKotlinClass(constructor.getClass()));
            return null;
        }
        if (coneTypeConstructorMarker instanceof ConeClassifierLookupTag) {
            f2f.a("Unexpected /* sealed */ ConeClassifierLookupTag inheritor: ", Reflection.getOrCreateKotlinClass(constructor.getClass()));
            return null;
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: renamed from: createStarProjection, reason: merged with bridge method [inline-methods] */
    default ConeStarProjection m628createStarProjection(TypeParameterMarker typeParameter) {
        typeParameter.getClass();
        return ConeStarProjection.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    default StubTypeMarker createStubTypeForBuilderInference(TypeVariableMarker typeVariable) throws KotlinNothingValueException {
        typeVariable.getClass();
        AddToStdlibKt.shouldNotBeCalled("PCLA does not use stub types for builder inference");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: createStubTypeForTypeVariablesInSubtyping, reason: merged with bridge method [inline-methods] */
    default ConeStubTypeForTypeVariableInSubtyping m629createStubTypeForTypeVariablesInSubtyping(TypeVariableMarker typeVariable) {
        typeVariable.getClass();
        if (typeVariable instanceof ConeTypeVariable) {
            return new ConeStubTypeForTypeVariableInSubtyping((ConeTypeVariable) typeVariable, isMarkedNullable(m636defaultType(typeVariable)));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(typeVariable);
        ywd.a(sb, " should subtype of ", Reflection.getOrCreateKotlinClass(ConeTypeVariable.class).getQualifiedName());
        return null;
    }

    /* JADX INFO: renamed from: createSubstitutionFromSubtypingStubTypesToTypeVariables, reason: merged with bridge method [inline-methods] */
    default AbstractConeSubstitutor m630createSubstitutionFromSubtypingStubTypesToTypeVariables() {
        return new AbstractConeSubstitutor() { // from class: org.jetbrains.kotlin.fir.types.ConeInferenceContext.createSubstitutionFromSubtypingStubTypesToTypeVariables.1
            {
                super(ConeInferenceContext.this);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
            public ConeKotlinType substituteType(ConeKotlinType type) {
                ConeStubTypeConstructor constructor;
                ConeTypeVariable variable;
                ConeTypeVariableType defaultType;
                type.getClass();
                ConeStubTypeForTypeVariableInSubtyping coneStubTypeForTypeVariableInSubtyping = type instanceof ConeStubTypeForTypeVariableInSubtyping ? (ConeStubTypeForTypeVariableInSubtyping) type : null;
                if (coneStubTypeForTypeVariableInSubtyping == null || (constructor = coneStubTypeForTypeVariableInSubtyping.getConstructor()) == null || (variable = constructor.getVariable()) == null || (defaultType = variable.getDefaultType()) == null) {
                    return null;
                }
                return (ConeTypeVariableType) TypeUtilsKt.withNullabilityOf(defaultType, type, ConeInferenceContext.this);
            }
        };
    }

    /* JADX INFO: renamed from: createSubstitutorForSuperTypes, reason: merged with bridge method [inline-methods] */
    default ConeSubstitutor m631createSubstitutorForSuperTypes(KotlinTypeMarker baseType) {
        baseType.getClass();
        if (baseType instanceof ConeLookupTagBasedType) {
            return SupertypeUtilsKt.createSubstitutionForSupertype((ConeLookupTagBasedType) baseType, getSession());
        }
        return null;
    }

    /* JADX INFO: renamed from: createTrivialFlexibleTypeOrSelf, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m632createTrivialFlexibleTypeOrSelf(KotlinTypeMarker lowerBound) {
        lowerBound.getClass();
        if (lowerBound instanceof ConeKotlinType) {
            return (!(lowerBound instanceof ConeRigidType) || ConeTypeUtilsKt.isMarkedNullable((ConeKotlinType) lowerBound) || (lowerBound instanceof ConeErrorType)) ? (ConeKotlinType) lowerBound : TypeUtilsKt.toTrivialFlexibleType((ConeRigidType) lowerBound, this);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: createTypeArgument, reason: merged with bridge method [inline-methods] */
    default ConeKotlinTypeProjection m633createTypeArgument(KotlinTypeMarker type, TypeVariance variance) {
        type.getClass();
        variance.getClass();
        if (!(type instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return (ConeKotlinTypeProjection) type;
        }
        if (i == 2) {
            return new ConeKotlinTypeProjectionIn((ConeKotlinType) type);
        }
        if (i == 3) {
            return new ConeKotlinTypeProjectionOut((ConeKotlinType) type);
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: renamed from: createTypeWithUpperBoundForIntersectionResult, reason: merged with bridge method [inline-methods] */
    default ConeIntersectionType m634createTypeWithUpperBoundForIntersectionResult(final KotlinTypeMarker firstCandidate, KotlinTypeMarker secondCandidate) {
        firstCandidate.getClass();
        secondCandidate.getClass();
        if (!(firstCandidate instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (!(secondCandidate instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        RigidTypeMarker rigidTypeMarkerLowerBoundIfFlexible = lowerBoundIfFlexible(firstCandidate);
        ConeIntersectionType coneIntersectionType = rigidTypeMarkerLowerBoundIfFlexible instanceof ConeIntersectionType ? (ConeIntersectionType) rigidTypeMarkerLowerBoundIfFlexible : null;
        if (coneIntersectionType != null) {
            return ConeTypeUtilsKt.withUpperBound(coneIntersectionType, (ConeKotlinType) secondCandidate);
        }
        dwe.a(new Function0() { // from class: yp2
            public final Object invoke() {
                return ConeInferenceContext.c(firstCandidate);
            }
        });
        return null;
    }

    /* JADX INFO: renamed from: createUninferredType, reason: merged with bridge method [inline-methods] */
    default ConeErrorType m635createUninferredType(TypeConstructorMarker constructor) {
        constructor.getClass();
        return new ConeErrorType(new ConeIntermediateDiagnostic("Uninferred type c: " + constructor), false, null, null, null, null, null, 126, null);
    }

    /* JADX INFO: renamed from: defaultType, reason: merged with bridge method [inline-methods] */
    default ConeTypeVariableType m636defaultType(TypeVariableMarker typeVariableMarker) {
        typeVariableMarker.getClass();
        if (typeVariableMarker instanceof ConeTypeVariable) {
            return ((ConeTypeVariable) typeVariableMarker).getDefaultType();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default KotlinTypeMarker eraseContainingTypeParameters(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        Set setExtractTypeParameters = extractTypeParameters(kotlinTypeMarker);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setExtractTypeParameters, 10));
        Iterator it = setExtractTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((ConeTypeParameterLookupTag) ((TypeParameterMarker) it.next())).getTypeParameterSymbol());
        }
        final Map<FirTypeParameterSymbol, ConeKotlinType> mapEraseToUpperBoundsAssociated = TypeUtilsKt.eraseToUpperBoundsAssociated(arrayList, getSession());
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: zp2
            public final Object invoke() {
                return ConeInferenceContext.a(mapEraseToUpperBoundsAssociated, this);
            }
        });
        if (argumentsCount(kotlinTypeMarker) != 0) {
            return replaceArgumentsDeeply(kotlinTypeMarker, new Function1() { // from class: aq2
                public final Object invoke(Object obj) {
                    return ConeInferenceContext.h(this.b, lazy, (TypeArgumentMarker) obj);
                }
            });
        }
        return isTypeParameterTypeConstructor(typeConstructor(kotlinTypeMarker)) ? m657safeSubstitute((TypeSubstitutorMarker) eraseContainingTypeParameters$lambda$2(lazy), kotlinTypeMarker) : kotlinTypeMarker;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    default List<ConeKotlinType> extractArgumentsForFunctionTypeOrSubtype(KotlinTypeMarker kotlinTypeMarker) throws KotlinIllegalArgumentExceptionWithAttachments {
        kotlinTypeMarker.getClass();
        ConeKotlinType coneKotlinTypeM640getFunctionTypeFromSupertypes = m640getFunctionTypeFromSupertypes(kotlinTypeMarker);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        int iArgumentsCount = argumentsCount(coneKotlinTypeM640getFunctionTypeFromSupertypes) - 1;
        for (int i = 0; i < iArgumentsCount; i++) {
            ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeTypeProjection) m674getArgument((KotlinTypeMarker) coneKotlinTypeM640getFunctionTypeFromSupertypes, i);
            listCreateListBuilder.add(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection ? coneKotlinTypeProjection.getType() : TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getAny(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), true, null, 4, null));
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    default ConeRigidType findCommonIntegerLiteralTypesSuperType(List<? extends RigidTypeMarker> explicitSupertypes) {
        explicitSupertypes.getClass();
        return ConeIntegerLiteralTypeImplKt.findCommonSuperType(ConeIntegerLiteralType.Companion, explicitSupertypes);
    }

    /* JADX INFO: renamed from: freshTypeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeTypeVariableTypeConstructor m638freshTypeConstructor(TypeVariableMarker typeVariableMarker) {
        typeVariableMarker.getClass();
        if (typeVariableMarker instanceof ConeTypeVariable) {
            return ((ConeTypeVariable) typeVariableMarker).getTypeConstructor();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default FunctionTypeKind functionTypeKind(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return FunctionalTypeUtilsKt.functionTypeKind$default((ConeRigidType) lowerBoundIfFlexible(kotlinTypeMarker), getSession(), false, 2, (Object) null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: getApproximatedIntegerLiteralType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType m639getApproximatedIntegerLiteralType(TypeConstructorMarker typeConstructorMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeConstructorMarker.getClass();
        if (typeConstructorMarker instanceof ConeIntegerLiteralType) {
            if (kotlinTypeMarker == null ? true : kotlinTypeMarker instanceof ConeKotlinType) {
                return ((ConeIntegerLiteralType) typeConstructorMarker).getApproximatedType((ConeKotlinType) kotlinTypeMarker);
            }
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: getFunctionTypeFromSupertypes, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m640getFunctionTypeFromSupertypes(KotlinTypeMarker kotlinTypeMarker) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType functionTypeFromSupertypesOrNull;
        kotlinTypeMarker.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        isBuiltinFunctionTypeOrSubtype(kotlinTypeMarker);
        ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
        final ConeRigidType coneRigidType = (ConeRigidType) lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, getSession(), (Function1) null, 2, (Object) null));
        if (!FunctionalTypeUtilsKt.isSomeFunctionType(coneRigidType, getSession())) {
            if (coneRigidType instanceof ConeCapturedType) {
                List<ConeKotlinType> supertypes = ((ConeCapturedType) coneRigidType).getConstructor().getSupertypes();
                if (supertypes != null) {
                    Iterator<T> it = supertypes.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            functionTypeFromSupertypesOrNull = getFunctionTypeFromSupertypesOrNull((ConeKotlinType) it.next());
                            if (functionTypeFromSupertypesOrNull != null) {
                                coneKotlinType = functionTypeFromSupertypesOrNull;
                            }
                        }
                    }
                }
                coneKotlinType = null;
            } else if (coneRigidType instanceof ConeTypeParameterType) {
                Iterator<T> it2 = ((ConeTypeParameterType) coneRigidType).getLookupTag().getTypeParameterSymbol().getResolvedBounds().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        functionTypeFromSupertypesOrNull = getFunctionTypeFromSupertypesOrNull(((FirResolvedTypeRef) it2.next()).getConeType());
                        if (functionTypeFromSupertypesOrNull != null) {
                            coneKotlinType = functionTypeFromSupertypesOrNull;
                        }
                    } else {
                        coneKotlinType = null;
                    }
                }
            } else if (coneRigidType instanceof ConeIntersectionType) {
                Iterator<T> it3 = ((ConeIntersectionType) coneRigidType).getIntersectedTypes().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        functionTypeFromSupertypesOrNull = getFunctionTypeFromSupertypesOrNull((ConeKotlinType) it3.next());
                        if (functionTypeFromSupertypesOrNull != null) {
                            coneKotlinType = functionTypeFromSupertypesOrNull;
                        }
                    } else {
                        coneKotlinType = null;
                    }
                }
            } else {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                anySuperTypeConstructor(coneRigidType, new Function1() { // from class: up2
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(ConeInferenceContext.l(this.b, coneRigidType, objectRef, (RigidTypeMarker) obj));
                    }
                });
                coneKotlinType = (ConeKotlinType) objectRef.element;
            }
        }
        if (coneKotlinType != null) {
            return coneKotlinType;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(StringsKt.trimIndent("\n            Failed to find functional supertype for " + coneRigidType.getClass() + ".\n            The contract of this function is that it returns a non-null value iff `isBuiltinFunctionTypeOrSubtype` returns `true`.\n            "), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, coneRigidType);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    default boolean getLexicographicVariableReadinessCalculation() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.LexicographicVariableReadinessCalculation);
    }

    /* JADX INFO: renamed from: getNonReflectFunctionTypeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeLookupTag m641getNonReflectFunctionTypeConstructor(int parametersNumber, FunctionTypeKind kind) {
        kind.getClass();
        return TypeConstructionUtilsKt.toLookupTag(kind.nonReflectKind().numberedClassId(parametersNumber));
    }

    /* JADX INFO: renamed from: getOriginalTypeVariable, reason: merged with bridge method [inline-methods] */
    default ConeTypeVariableTypeConstructor m642getOriginalTypeVariable(StubTypeMarker stubTypeMarker) {
        stubTypeMarker.getClass();
        if (stubTypeMarker instanceof ConeStubType) {
            return ((ConeStubType) stubTypeMarker).getConstructor().getVariable().getTypeConstructor();
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: getReflectFunctionTypeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeLookupTag m643getReflectFunctionTypeConstructor(int parametersNumber, FunctionTypeKind kind) {
        kind.getClass();
        return TypeConstructionUtilsKt.toLookupTag(kind.reflectKind().numberedClassId(parametersNumber));
    }

    default FirSymbolProvider getSymbolProvider() {
        return FirSymbolProviderKt.getSymbolProvider(getSession());
    }

    /* JADX INFO: renamed from: getUpperBoundForApproximationOfIntersectionType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m644getUpperBoundForApproximationOfIntersectionType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        ConeIntersectionType coneIntersectionType = rigidTypeMarker instanceof ConeIntersectionType ? (ConeIntersectionType) rigidTypeMarker : null;
        if (coneIntersectionType != null) {
            return coneIntersectionType.getUpperBoundForApproximation();
        }
        return null;
    }

    @K2Only
    default boolean hasEnhancedNullability(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return CompilerConeAttributesKt.getHasEnhancedNullability((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean hasExactAnnotation(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return CompilerConeAttributesKt.getExact(((ConeKotlinType) kotlinTypeMarker).getAttributes()) != null;
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean hasNoInferAnnotation(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return CompilerConeAttributesKt.getHasNoInfer((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    @K2Only
    default boolean hasRawSuperTypeRecursive(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return hasRawSuperTypeInternal((ConeCapturedType) capturedTypeMarker, new HashSet<>());
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isBuiltinFunctionTypeOrSubtype(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return isTypeOrSubtypeOf((ConeKotlinType) kotlinTypeMarker, new Function1() { // from class: vp2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ConeInferenceContext.g(this.b, (ConeKotlinType) obj));
                }
            });
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isCapturedTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeCapturedTypeConstructor;
    }

    default boolean isContainedInInvariantOrContravariantPositions(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        typeVariableTypeConstructorMarker.getClass();
        if (typeVariableTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor) {
            return ((ConeTypeVariableTypeConstructor) typeVariableTypeConstructorMarker).getIsContainedInInvariantOrContravariantPositions();
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isExtensionFunction(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            return CompilerConeAttributesKt.isExtensionFunctionType((ConeKotlinType) rigidTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isExtensionFunctionType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.isExtensionFunctionType((ConeKotlinType) kotlinTypeMarker, getSession());
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        if (classLikeSymbol == null) {
            return false;
        }
        if (classLikeSymbol instanceof FirAnonymousObjectSymbol) {
            return true;
        }
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbol : null;
        return firRegularClassSymbol != null && firRegularClassSymbol.getResolvedStatus().getModality() == Modality.FINAL;
    }

    default boolean isFunctionOrKFunctionWithAnySuspendability(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return FunctionalTypeUtilsKt.isSomeFunctionType((ConeKotlinType) kotlinTypeMarker, getSession());
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isK2() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeTypeContext
    default boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return false;
    }

    @AllowedToUsedOnlyInK1
    default boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return false;
    }

    default boolean isSignedOrUnsignedNumberType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return false;
        }
        if (kotlinTypeMarker instanceof ConeIntegerLiteralType) {
            return true;
        }
        if (kotlinTypeMarker instanceof ConeClassLikeType) {
            return PrimitivesKt.isPrimitiveNumberOrUnsignedNumberType((ConeClassLikeType) kotlinTypeMarker);
        }
        return false;
    }

    default boolean isSpecial(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return false;
    }

    default boolean isTriviallyFlexible(FlexibleTypeMarker flexibleType) {
        flexibleType.getClass();
        if (flexibleType instanceof ConeFlexibleType) {
            return ((ConeFlexibleType) flexibleType).getIsTrivial();
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isTypeOrSubtypeOf(ConeKotlinType coneKotlinType, final Function1<? super ConeKotlinType, Boolean> function1) {
        coneKotlinType.getClass();
        function1.getClass();
        return ((Boolean) function1.invoke(coneKotlinType)).booleanValue() || ((Boolean) DFS.dfsFromNode(coneKotlinType, new DFS.Neighbors() { // from class: xp2
            public final Iterable getNeighbors(Object obj) {
                return ConeInferenceContext.e(this.a, (ConeKotlinType) obj);
            }
        }, new DFS.VisitedWithSet(), new DFS.AbstractNodeHandler<ConeKotlinType, Boolean>() { // from class: org.jetbrains.kotlin.fir.types.ConeInferenceContext.isTypeOrSubtypeOf.2
            private boolean result;

            public boolean beforeChildren(ConeKotlinType current) {
                current.getClass();
                if (((Boolean) function1.invoke(current)).booleanValue()) {
                    this.result = true;
                }
                return !this.result;
            }

            /* JADX INFO: renamed from: result, reason: merged with bridge method [inline-methods] */
            public Boolean m663result() {
                return Boolean.valueOf(this.result);
            }
        })).booleanValue();
    }

    default boolean isTypeParameterTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return m680getTypeParameterClassifier(typeConstructorMarker) != null;
    }

    default boolean isTypeVariable(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeTypeVariableTypeConstructor;
    }

    default boolean isUnit(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return isUnitTypeConstructor(typeConstructor(kotlinTypeMarker)) && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    /* JADX INFO: renamed from: makeDefinitelyNotNullOrNotNull, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m645makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default((ConeKotlinType) kotlinTypeMarker, (ConeTypeContext) this, false, z, 2, (Object) null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: makeLowerBoundDefinitelyNotNullOrNotNull, reason: merged with bridge method [inline-methods] */
    default ConeFlexibleType m647makeLowerBoundDefinitelyNotNullOrNotNull(FlexibleTypeMarker flexibleType) {
        flexibleType.getClass();
        if (!(flexibleType instanceof ConeFlexibleType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) flexibleType;
        ConeRigidType lowerBound = coneFlexibleType.getLowerBound();
        lowerBound.getClass();
        ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) lowerBound, (ConeTypeContext) this, false, true);
        coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull.getClass();
        return new ConeFlexibleType((ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull, coneFlexibleType.getUpperBound(), coneFlexibleType.getIsTrivial());
    }

    default TypeCheckerState newTypeCheckerState(TypeSystemContext typeSystemContext, boolean errorTypesEqualToAnything, boolean stubTypesEqualToAnything, boolean dnnTypesEqualToFlexible) {
        typeSystemContext.getClass();
        return new TypeCheckerState(errorTypesEqualToAnything, stubTypesEqualToAnything, dnnTypesEqualToFlexible, true, typeSystemContext, new ConeTypePreparator(getSession()), AbstractTypeRefiner.Default.INSTANCE);
    }

    /* JADX INFO: renamed from: nothingType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType m648nothingType() {
        return getSession().getBuiltinTypes().getNothingType().getConeType();
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeTypeContext
    /* JADX INFO: renamed from: nullableAnyType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType mo649nullableAnyType() {
        return getSession().getBuiltinTypes().getNullableAnyType().getConeType();
    }

    /* JADX INFO: renamed from: nullableNothingType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType m650nullableNothingType() {
        return getSession().getBuiltinTypes().getNullableNothingType().getConeType();
    }

    /* JADX INFO: renamed from: original, reason: merged with bridge method [inline-methods] */
    default ConeSimpleKotlinType m651original(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        if (definitelyNotNullTypeMarker instanceof ConeDefinitelyNotNullType) {
            return ((ConeDefinitelyNotNullType) definitelyNotNullTypeMarker).getOriginal();
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: removeAnnotations, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m652removeAnnotations(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.withAttributes((ConeKotlinType) kotlinTypeMarker, ConeAttributes.INSTANCE.getEmpty());
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: removeExactAnnotation, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m653removeExactAnnotation(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
            return TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().remove((ConeAttribute<?>) CompilerConeAttributes.Exact.INSTANCE));
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    default ConeRigidType replaceArguments(RigidTypeMarker rigidTypeMarker, Function1<? super TypeArgumentMarker, ? extends TypeArgumentMarker> function1) throws KotlinIllegalArgumentExceptionWithAttachments {
        rigidTypeMarker.getClass();
        function1.getClass();
        if (!(rigidTypeMarker instanceof ConeRigidType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeKotlinType coneKotlinTypeWithArguments = (ConeKotlinType) rigidTypeMarker;
        ConeTypeProjection[] typeArguments = coneKotlinTypeWithArguments.getTypeArguments();
        if (typeArguments.length != 0) {
            int length = typeArguments.length;
            ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
            for (int i = 0; i < length; i++) {
                coneTypeProjectionArr[i] = (ConeTypeProjection) ((TypeArgumentMarker) function1.invoke(typeArguments[i]));
            }
            coneKotlinTypeWithArguments = TypeUtilsKt.withArguments(coneKotlinTypeWithArguments, coneTypeProjectionArr);
        }
        return (ConeRigidType) coneKotlinTypeWithArguments;
    }

    default ConeKotlinType replaceCustomAttributes(KotlinTypeMarker kotlinTypeMarker, List<? extends AnnotationMarker> list) {
        kotlinTypeMarker.getClass();
        list.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (isCustomAttribute((ConeAttribute) obj)) {
                arrayList.add(obj);
            }
        }
        ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
        ConeAttributes attributes = coneKotlinType.getAttributes();
        ArrayList arrayList2 = new ArrayList();
        for (ConeAttribute<?> coneAttribute : attributes) {
            if (!isCustomAttribute(coneAttribute)) {
                arrayList2.add(coneAttribute);
            }
        }
        return TypeUtilsKt.withAttributes(coneKotlinType, ConeAttributes.INSTANCE.create(CollectionsKt.plus(arrayList, arrayList2)));
    }

    /* JADX INFO: renamed from: safeSubstitute, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m657safeSubstitute(TypeSubstitutorMarker typeSubstitutorMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeSubstitutorMarker.getClass();
        kotlinTypeMarker.getClass();
        if (!(typeSubstitutorMarker instanceof ConeSubstitutor)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return ((ConeSubstitutor) typeSubstitutorMarker).substituteOrSelf((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return null;
    }

    default boolean simplifyFlexibleUpperConstraintWithDnnBoundToNullable() {
        return !FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound);
    }

    default KotlinTypeMarker singleBestRepresentative(Collection<? extends KotlinTypeMarker> collection) {
        Object next;
        collection.getClass();
        if (collection.size() == 1) {
            return (KotlinTypeMarker) CollectionsKt.first(collection);
        }
        TypeCheckerState typeCheckerStateNewTypeCheckerState$default = TypeCheckerProviderContext.newTypeCheckerState$default(this, true, true, false, 4, (Object) null);
        Collection<? extends KotlinTypeMarker> collection2 = collection;
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            next = it.next();
            KotlinTypeMarker kotlinTypeMarker = (KotlinTypeMarker) next;
            if (!collection2.isEmpty()) {
                Iterator<T> it2 = collection2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        KotlinTypeMarker kotlinTypeMarker2 = (KotlinTypeMarker) it2.next();
                        if (Intrinsics.areEqual(kotlinTypeMarker, kotlinTypeMarker2) || AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerStateNewTypeCheckerState$default, kotlinTypeMarker, kotlinTypeMarker2)) {
                        }
                    }
                }
            }
            return (KotlinTypeMarker) next;
        }
        next = null;
        return (KotlinTypeMarker) next;
    }

    default boolean supportsImprovedVarianceInCst() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.ImprovedVarianceInCst);
    }

    /* JADX INFO: renamed from: toErrorType, reason: merged with bridge method [inline-methods] */
    default ConeErrorType m658toErrorType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeClassLikeLookupTag)) {
            return m625createErrorType("Unknown reason", (RigidTypeMarker) null);
        }
        return m625createErrorType("Not found classifier: " + ((ConeClassLikeLookupTag) typeConstructorMarker).getClassId(), (RigidTypeMarker) null);
    }

    /* JADX INFO: renamed from: typeConstructorProjection, reason: merged with bridge method [inline-methods] */
    default ConeTypeProjection m659typeConstructorProjection(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return ((ConeCapturedType) capturedTypeMarker).getConstructor().getProjection();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default int typeDepth(RigidTypeMarker rigidTypeMarker) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default;
        rigidTypeMarker.getClass();
        if (!(rigidTypeMarker instanceof ConeRigidType)) {
            w01.a("Failed requirement.");
            return 0;
        }
        if ((rigidTypeMarker instanceof ConeClassLikeType) && rigidTypeMarker != (coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) rigidTypeMarker, getSession(), (Function1) null, 2, (Object) null))) {
            return typeDepth(coneClassLikeTypeFullyExpandedType$default);
        }
        ConeKotlinTypeProjection[] typeArguments = ((ConeRigidType) rigidTypeMarker).getTypeArguments();
        int length = typeArguments.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            int iTypeDepth = 1;
            if (i >= length) {
                return i2 + 1;
            }
            ConeKotlinTypeProjection coneKotlinTypeProjection = typeArguments[i];
            if (!(coneKotlinTypeProjection instanceof ConeStarProjection)) {
                if (!(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection)) {
                    bu8.a();
                    return 0;
                }
                iTypeDepth = typeDepth((KotlinTypeMarker) coneKotlinTypeProjection.getType());
            }
            if (iTypeDepth > i2) {
                i2 = iTypeDepth;
            }
            i++;
        }
    }

    default int typeDepthForApproximation(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (!(kotlinTypeMarker instanceof ConeCapturedType)) {
            return typeDepth(kotlinTypeMarker);
        }
        ConeKotlinType type = ConeTypeProjectionKt.getType(((ConeCapturedType) kotlinTypeMarker).getConstructor().getProjection());
        if (type != null) {
            return typeDepth((KotlinTypeMarker) type);
        }
        return 1;
    }

    /* JADX INFO: renamed from: typeParameter, reason: merged with bridge method [inline-methods] */
    default ConeTypeParameterLookupTag m660typeParameter(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (!(capturedTypeMarker instanceof ConeCapturedType)) {
            w01.a("Failed requirement.");
            return null;
        }
        TypeParameterMarker typeParameterMarker = ((ConeCapturedType) capturedTypeMarker).getConstructor().getTypeParameterMarker();
        if (typeParameterMarker != null) {
            return (ConeTypeParameterLookupTag) typeParameterMarker;
        }
        return null;
    }

    default ConeSubstitutor typeSubstitutorByTypeConstructor(Map<TypeConstructorMarker, ? extends KotlinTypeMarker> map) {
        map.getClass();
        return ConeTypeSubstitutorByTypeConstructorKt.createTypeSubstitutorByTypeConstructor(map, this, false);
    }

    default List<ConeAttribute<?>> unionTypeAttributes(List<? extends KotlinTypeMarker> types) {
        types.getClass();
        List<? extends KotlinTypeMarker> list = types;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ConeKotlinType) it.next()).getAttributes());
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = ((ConeAttributes) next).union((ConeAttributes) it2.next());
        }
        return CollectionsKt.toList((Iterable) next);
    }

    default boolean usePreciseSimplificationToFlexibleLowerConstraint() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.PreciseSimplificationToFlexibleLowerConstraint);
    }

    @AllowedToUsedOnlyInK1
    default KotlinTypeMarker withNotNullProjection(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        throw new IllegalStateException("AllowedToUsedOnlyInK1");
    }

    /* JADX INFO: renamed from: withNullability, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m662withNullability(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.withNullability$default((ConeKotlinType) kotlinTypeMarker, z, this, null, false, 12, null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: createEmptySubstitutor, reason: merged with bridge method [inline-methods] */
    default ConeSubstitutor m624createEmptySubstitutor() {
        return ConeSubstitutor.Empty.INSTANCE;
    }

    /* JADX INFO: renamed from: typeSubstitutorByTypeConstructor, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default TypeSubstitutorMarker m661typeSubstitutorByTypeConstructor(Map map) {
        return typeSubstitutorByTypeConstructor((Map<TypeConstructorMarker, ? extends KotlinTypeMarker>) map);
    }

    /* JADX INFO: renamed from: findCommonIntegerLiteralTypesSuperType, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default RigidTypeMarker m637findCommonIntegerLiteralTypesSuperType(List list) {
        return findCommonIntegerLiteralTypesSuperType((List<? extends RigidTypeMarker>) list);
    }

    /* JADX INFO: renamed from: makeDefinitelyNotNullOrNotNull, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m646makeDefinitelyNotNullOrNotNull(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) rigidTypeMarker, (ConeTypeContext) this, false, false);
            coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull.getClass();
            return (ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull;
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: replaceArguments, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default RigidTypeMarker m655replaceArguments(RigidTypeMarker rigidTypeMarker, Function1 function1) {
        return replaceArguments(rigidTypeMarker, (Function1<? super TypeArgumentMarker, ? extends TypeArgumentMarker>) function1);
    }

    default ConeRigidType replaceArguments(RigidTypeMarker rigidTypeMarker, List<? extends TypeArgumentMarker> list) {
        rigidTypeMarker.getClass();
        list.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            return (ConeRigidType) TypeUtilsKt.withArguments((ConeKotlinType) rigidTypeMarker, (ConeTypeProjection[]) list.toArray(new ConeTypeProjection[0]));
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: createCapturedType, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default CapturedTypeMarker m623createCapturedType(TypeArgumentMarker typeArgumentMarker, List list, KotlinTypeMarker kotlinTypeMarker, CaptureStatus captureStatus) {
        return createCapturedType(typeArgumentMarker, (List<? extends KotlinTypeMarker>) list, kotlinTypeMarker, captureStatus);
    }

    /* JADX INFO: renamed from: replaceArguments, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default RigidTypeMarker m654replaceArguments(RigidTypeMarker rigidTypeMarker, List list) {
        return replaceArguments(rigidTypeMarker, (List<? extends TypeArgumentMarker>) list);
    }

    default int typeDepth(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
            if (coneKotlinType instanceof ConeSimpleKotlinType) {
                return typeDepth((RigidTypeMarker) kotlinTypeMarker);
            }
            if (coneKotlinType instanceof ConeFlexibleType) {
                FlexibleTypeMarker flexibleTypeMarker = (FlexibleTypeMarker) kotlinTypeMarker;
                return Math.max(typeDepth(m686lowerBound(flexibleTypeMarker)), typeDepth(m693upperBound(flexibleTypeMarker)));
            }
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                return typeDepth(((ConeDefinitelyNotNullType) kotlinTypeMarker).getOriginal());
            }
            bu8.a();
            return 0;
        }
        w01.a("Failed requirement.");
        return 0;
    }

    /* JADX INFO: renamed from: replaceCustomAttributes, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default KotlinTypeMarker m656replaceCustomAttributes(KotlinTypeMarker kotlinTypeMarker, List list) {
        return replaceCustomAttributes(kotlinTypeMarker, (List<? extends AnnotationMarker>) list);
    }

    /* JADX INFO: renamed from: createSimpleType, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default SimpleTypeMarker m627createSimpleType(TypeConstructorMarker typeConstructorMarker, List list, boolean z, boolean z2, int i, List list2) {
        return createSimpleType(typeConstructorMarker, (List<? extends TypeArgumentMarker>) list, z, z2, i, (List<? extends AnnotationMarker>) list2);
    }
}
