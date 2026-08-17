package org.jetbrains.kotlin.fir.java;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.java.JavaAnnotationsMappingKt;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.enhancement.JavaTypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttribute;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.jvm.FirJavaTypeRef;
import org.jetbrains.kotlin.fir.types.jvm.FirJavaTypeRefBuilder;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationOwner;
import org.jetbrains.kotlin.load.java.structure.JavaArrayType;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClassifier;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaElementsKt;
import org.jetbrains.kotlin.load.java.structure.JavaPrimitiveType;
import org.jetbrains.kotlin.load.java.structure.JavaType;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;
import org.jetbrains.kotlin.load.java.structure.JavaTypesKt;
import org.jetbrains.kotlin.load.java.structure.JavaWildcardType;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a9\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\t\u001a.\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u001a0\u0010\u0014\u001a\u00020\u0015*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u001e\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000\u001a2\u0010\u0019\u001a\u00020\u001a*\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0000\u001aB\u0010\u001b\u001a\u00020\u0015*\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dH\u0002\u001aJ\u0010\u001f\u001a\u00020\u0005*\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dH\u0002\u001a\f\u0010%\u001a\u00020&*\u00020'H\u0002\u001aB\u0010(\u001a\u00020)*\u00020*2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010+\u001a\u0004\u0018\u00010)H\u0002\u001a\f\u0010,\u001a\u00020-*\u00020.H\u0002\u001a\u001c\u0010/\u001a\u00020&*\u00020*2\u0006\u00100\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0002\"\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"toConeFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "Lorg/jetbrains/kotlin/name/ClassId;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "typeArgumentsForUpper", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "(Lorg/jetbrains/kotlin/name/ClassId;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "resolveIfJavaType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "mode", "Lorg/jetbrains/kotlin/fir/java/FirJavaTypeConversionMode;", "toConeKotlinTypeProbablyFlexible", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toFirJavaTypeRef", "Lorg/jetbrains/kotlin/fir/types/jvm/FirJavaTypeRef;", "Lorg/jetbrains/kotlin/load/java/structure/JavaType;", "toFirResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "toConeKotlinType", "additionalAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "toConeTypeProjection", "parameterVariance", "Lorg/jetbrains/kotlin/types/Variance;", "javaReadOnlyFqNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "isTriviallyFlexible", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifier;", "toConeKotlinTypeForFlexibleBound", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifierType;", "lowerBound", "allTypeParametersNumber", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "argumentsMakeSenseOnlyForMutableContainer", "classId", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaTypeConversionKt {
    private static final Set<FqName> javaReadOnlyFqNames = JavaToKotlinClassMap.INSTANCE.getReadOnlyAsJava();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirJavaTypeConversionMode.values().length];
            try {
                iArr[FirJavaTypeConversionMode.ANNOTATION_CONSTRUCTOR_PARAMETER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirJavaTypeConversionMode.ANNOTATION_MEMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final int allTypeParametersNumber(JavaClass javaClass) {
        int size = 0;
        while (javaClass != null) {
            size += javaClass.getTypeParameters().size();
            javaClass = javaClass.isStatic() ? null : javaClass.getOuterClass();
        }
        return size;
    }

    private static final boolean argumentsMakeSenseOnlyForMutableContainer(JavaClassifierType javaClassifierType, ClassId classId, FirSession firSession) {
        ClassId onlyToMutable;
        FirRegularClassSymbol regularClassSymbol;
        List<FirTypeParameterSymbol> typeParameterSymbols;
        FirTypeParameterSymbol firTypeParameterSymbol;
        Variance variance;
        return (!JavaToKotlinClassMap.INSTANCE.isReadOnly(classId.asSingleFqName().toUnsafe()) || (onlyToMutable = JavaTypeUtilsKt.readOnlyToMutable(classId)) == null || !JavaTypesKt.isSuperWildcard((JavaType) CollectionsKt.lastOrNull(javaClassifierType.getTypeArguments())) || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeClassLikeLookupTag) TypeConstructionUtilsKt.toLookupTag(onlyToMutable), firSession)) == null || (typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols()) == null || (firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.lastOrNull(typeParameterSymbols)) == null || (variance = firTypeParameterSymbol.getVariance()) == null || variance == Variance.OUT_VARIANCE) ? false : true;
    }

    private static final boolean isTriviallyFlexible(JavaClassifier javaClassifier) {
        return ((javaClassifier instanceof JavaClass) && !CollectionsKt.contains(javaReadOnlyFqNames, ((JavaClass) javaClassifier).getFqName())) || (javaClassifier instanceof JavaTypeParameter);
    }

    public static final FirTypeRef resolveIfJavaType(FirTypeRef firTypeRef, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode) {
        firTypeRef.getClass();
        firSession.getClass();
        javaTypeParameterStack.getClass();
        firJavaTypeConversionMode.getClass();
        return (!(firTypeRef instanceof FirResolvedTypeRef) && (firTypeRef instanceof FirJavaTypeRef)) ? toFirResolvedTypeRef(((FirJavaTypeRef) firTypeRef).getType(), firSession, javaTypeParameterStack, ktSourceElement, firJavaTypeConversionMode) : firTypeRef;
    }

    public static /* synthetic */ FirTypeRef resolveIfJavaType$default(FirTypeRef firTypeRef, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode, int i, Object obj) {
        if ((i & 8) != 0) {
            firJavaTypeConversionMode = FirJavaTypeConversionMode.DEFAULT;
        }
        return resolveIfJavaType(firTypeRef, firSession, javaTypeParameterStack, ktSourceElement, firJavaTypeConversionMode);
    }

    private static final ConeFlexibleType toConeFlexibleType(ClassId classId, ConeTypeProjection[] coneTypeProjectionArr, ConeTypeProjection[] coneTypeProjectionArr2, ConeAttributes coneAttributes) {
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(classId);
        return new ConeFlexibleType(TypeConstructionUtilsKt.constructClassType(lookupTag, coneTypeProjectionArr, false, coneAttributes), TypeConstructionUtilsKt.constructClassType(lookupTag, coneTypeProjectionArr2, true, coneAttributes), false);
    }

    private static final ConeKotlinType toConeKotlinType(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, FirJavaTypeConversionMode firJavaTypeConversionMode, KtSourceElement ktSourceElement, Collection<? extends JavaAnnotation> collection) {
        ConeKotlinType type = ConeTypeProjectionKt.getType(toConeTypeProjection(javaType, firSession, javaTypeParameterStack, Variance.INVARIANT, firJavaTypeConversionMode, ktSourceElement, collection));
        return type == null ? new ConeFlexibleType(firSession.getBuiltinTypes().getAnyType().getConeType(), firSession.getBuiltinTypes().getNullableAnyType().getConeType(), true) : type;
    }

    public static /* synthetic */ ConeKotlinType toConeKotlinType$default(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, FirJavaTypeConversionMode firJavaTypeConversionMode, KtSourceElement ktSourceElement, Collection collection, int i, Object obj) {
        if ((i & 16) != 0) {
            collection = null;
        }
        return toConeKotlinType(javaType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, ktSourceElement, collection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeLookupTagBasedType toConeKotlinTypeForFlexibleBound(JavaClassifierType javaClassifierType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, FirJavaTypeConversionMode firJavaTypeConversionMode, ConeAttributes coneAttributes, KtSourceElement ktSourceElement, ConeLookupTagBasedType coneLookupTagBasedType) {
        ClassId classIdMapJavaToKotlin;
        ClassId onlyToMutable;
        Variance variance;
        FirTypeParameterSymbol firTypeParameterSymbol;
        FirTypeParameter firTypeParameter;
        FirRegularClassSymbol regularClassSymbol;
        FirRegularClassSymbol regularClassSymbol2;
        FirSession firSession2 = firSession;
        JavaClass classifier = javaClassifierType.getClassifier();
        if (!(classifier instanceof JavaClass)) {
            if (classifier instanceof JavaTypeParameter) {
                JavaTypeParameter javaTypeParameter = (JavaTypeParameter) classifier;
                FirTypeParameterSymbol firTypeParameterSymbol2 = javaTypeParameterStack.get(javaTypeParameter);
                if (firTypeParameterSymbol2 != null) {
                    return new ConeTypeParameterTypeImpl(firTypeParameterSymbol2.getLookupTag(), coneLookupTagBasedType != null, coneAttributes);
                }
                return new ConeErrorType(new ConeUnresolvedNameError(javaTypeParameter.getName(), null, null, 6, null), false, null, null, null, null, null, 126, null);
            }
            if (classifier == null) {
                return TypeConstructionUtilsKt.constructClassLikeType$default(ClassId.Companion.topLevel(new FqName(javaClassifierType.getClassifierQualifiedName())), null, coneLookupTagBasedType != null, coneAttributes, 1, null);
            }
            return new ConeErrorType(new ConeSimpleDiagnostic("Unexpected classifier: " + classifier, DiagnosticKind.Java), false, null, null, null, null, null, 126, null);
        }
        if (firJavaTypeConversionMode.getInsideAnnotation()) {
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            FqName fqName = classifier.getFqName();
            fqName.getClass();
            classIdMapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlinIncludingClassMapping(fqName);
        } else {
            JavaToKotlinClassMap javaToKotlinClassMap2 = JavaToKotlinClassMap.INSTANCE;
            FqName fqName2 = classifier.getFqName();
            fqName2.getClass();
            classIdMapJavaToKotlin = javaToKotlinClassMap2.mapJavaToKotlin(fqName2);
        }
        if (classIdMapJavaToKotlin == null) {
            classIdMapJavaToKotlin = JavaElementsKt.getClassId(classifier);
            classIdMapJavaToKotlin.getClass();
        }
        if ((coneLookupTagBasedType == null || argumentsMakeSenseOnlyForMutableContainer(javaClassifierType, classIdMapJavaToKotlin, firSession2)) && (onlyToMutable = JavaTypeUtilsKt.readOnlyToMutable(classIdMapJavaToKotlin)) != null) {
            classIdMapJavaToKotlin = onlyToMutable;
        }
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(classIdMapJavaToKotlin);
        ConeStarProjection[] typeArguments = null;
        typeParameterSymbols = null;
        List<FirTypeParameterSymbol> typeParameterSymbols = null;
        if (javaClassifierType.isRaw()) {
            ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl = (coneLookupTagBasedType != null || firJavaTypeConversionMode == FirJavaTypeConversionMode.TYPE_PARAMETER_BOUND_FIRST_ROUND) ? null : lookupTag;
            List<FirTypeParameterSymbol> typeParameterSymbols2 = (coneClassLikeLookupTagImpl == null || (regularClassSymbol2 = ToSymbolUtilsKt.toRegularClassSymbol((ConeClassLikeLookupTag) coneClassLikeLookupTagImpl, firSession2)) == null) ? null : regularClassSymbol2.getTypeParameterSymbols();
            if (firJavaTypeConversionMode.getInsideAnnotation()) {
                int iAllTypeParametersNumber = allTypeParametersNumber(classifier);
                typeArguments = new ConeStarProjection[iAllTypeParametersNumber];
                for (int i = 0; i < iAllTypeParametersNumber; i++) {
                    typeArguments[i] = ConeStarProjection.INSTANCE;
                }
            } else if (typeParameterSymbols2 == null || (typeArguments = TypeUtilsKt.getProjectionsForRawType(typeParameterSymbols2, firSession2, null)) == null) {
                int iAllTypeParametersNumber2 = allTypeParametersNumber(classifier);
                typeArguments = new ConeStarProjection[iAllTypeParametersNumber2];
                for (int i2 = 0; i2 < iAllTypeParametersNumber2; i2++) {
                    typeArguments[i2] = ConeStarProjection.INSTANCE;
                }
            }
        } else if (!Intrinsics.areEqual(lookupTag, coneLookupTagBasedType != null ? coneLookupTagBasedType.getLookupTag() : null) && !javaClassifierType.getTypeArguments().isEmpty()) {
            ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl2 = firJavaTypeConversionMode != FirJavaTypeConversionMode.TYPE_PARAMETER_BOUND_FIRST_ROUND ? lookupTag : null;
            if (coneClassLikeLookupTagImpl2 != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeClassLikeLookupTag) coneClassLikeLookupTagImpl2, firSession2)) != null) {
                typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols();
            }
            List<FirTypeParameterSymbol> list = typeParameterSymbols;
            int size = javaClassifierType.getTypeArguments().size();
            ConeStarProjection[] coneStarProjectionArr = new ConeTypeProjection[size];
            int i3 = 0;
            while (i3 < size) {
                FirJavaTypeConversionMode firJavaTypeConversionMode2 = firJavaTypeConversionMode.getInsideAnnotation() ? FirJavaTypeConversionMode.DEFAULT : firJavaTypeConversionMode;
                JavaType javaType = (JavaType) javaClassifierType.getTypeArguments().get(i3);
                if (list == null || (firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.getOrNull(list, i3)) == null || (firTypeParameter = (FirTypeParameter) firTypeParameterSymbol.getFir()) == null || (variance = firTypeParameter.getVariance()) == null) {
                    variance = Variance.INVARIANT;
                }
                int i4 = i3;
                ConeStarProjection[] coneStarProjectionArr2 = coneStarProjectionArr;
                coneStarProjectionArr2[i4] = toConeTypeProjection$default(javaType, firSession2, javaTypeParameterStack, variance, firJavaTypeConversionMode2, ktSourceElement, null, 32, null);
                i3 = i4 + 1;
                firSession2 = firSession;
                size = size;
                coneStarProjectionArr = coneStarProjectionArr2;
            }
            typeArguments = coneStarProjectionArr;
        } else if (coneLookupTagBasedType != null) {
            typeArguments = coneLookupTagBasedType.getTypeArguments();
        }
        if (typeArguments == null) {
            typeArguments = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        return TypeConstructionUtilsKt.constructClassType(lookupTag, typeArguments, coneLookupTagBasedType != null, coneAttributes);
    }

    public static /* synthetic */ ConeLookupTagBasedType toConeKotlinTypeForFlexibleBound$default(JavaClassifierType javaClassifierType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, FirJavaTypeConversionMode firJavaTypeConversionMode, ConeAttributes coneAttributes, KtSourceElement ktSourceElement, ConeLookupTagBasedType coneLookupTagBasedType, int i, Object obj) {
        if ((i & 32) != 0) {
            coneLookupTagBasedType = null;
        }
        return toConeKotlinTypeForFlexibleBound(javaClassifierType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, coneAttributes, ktSourceElement, coneLookupTagBasedType);
    }

    public static final ConeKotlinType toConeKotlinTypeProbablyFlexible(FirTypeRef firTypeRef, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode) {
        ConeKotlinType coneType;
        firTypeRef.getClass();
        firSession.getClass();
        javaTypeParameterStack.getClass();
        firJavaTypeConversionMode.getClass();
        FirResolvedTypeRef firResolvedTypeRefResolveIfJavaType = resolveIfJavaType(firTypeRef, firSession, javaTypeParameterStack, ktSourceElement, firJavaTypeConversionMode);
        FirResolvedTypeRef firResolvedTypeRef = firResolvedTypeRefResolveIfJavaType instanceof FirResolvedTypeRef ? firResolvedTypeRefResolveIfJavaType : null;
        if (firResolvedTypeRef != null && (coneType = firResolvedTypeRef.getConeType()) != null) {
            return coneType;
        }
        return new ConeErrorType(new ConeSimpleDiagnostic("Type reference in Java not resolved: " + firTypeRef.getClass(), DiagnosticKind.Java), false, null, null, null, null, null, 126, null);
    }

    public static /* synthetic */ ConeKotlinType toConeKotlinTypeProbablyFlexible$default(FirTypeRef firTypeRef, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode, int i, Object obj) {
        if ((i & 8) != 0) {
            firJavaTypeConversionMode = FirJavaTypeConversionMode.DEFAULT;
        }
        return toConeKotlinTypeProbablyFlexible(firTypeRef, firSession, javaTypeParameterStack, ktSourceElement, firJavaTypeConversionMode);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final ConeTypeProjection toConeTypeProjection(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, Variance variance, FirJavaTypeConversionMode firJavaTypeConversionMode, KtSourceElement ktSourceElement, Collection<? extends JavaAnnotation> collection) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeAttributes empty;
        String identifier;
        Name typeName;
        Pair pair;
        JavaClassifier classifier;
        if (javaType == null || (javaType.getAnnotations().isEmpty() && collection == null)) {
            empty = ConeAttributes.INSTANCE.getEmpty();
        } else {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            if (!javaType.getAnnotations().isEmpty()) {
                listCreateListBuilder.addAll(JavaAnnotationsMappingKt.convertAnnotationsToFir((JavaAnnotationOwner) javaType, firSession, ktSourceElement));
            }
            if (collection != null) {
                listCreateListBuilder.addAll(JavaAnnotationsMappingKt.convertAnnotationsToFir(collection, firSession, ktSourceElement));
            }
            empty = ConeAttributes.INSTANCE.create(CollectionsKt.listOf(new CustomAnnotationTypeAttribute(CollectionsKt.build(listCreateListBuilder))));
        }
        if (javaType instanceof JavaClassifierType) {
            JavaClassifierType javaClassifierType = (JavaClassifierType) javaType;
            ConeAttributes coneAttributes = empty;
            ConeLookupTagBasedType coneKotlinTypeForFlexibleBound$default = toConeKotlinTypeForFlexibleBound$default(javaClassifierType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, coneAttributes, ktSourceElement, null, 32, null);
            if (firJavaTypeConversionMode.getInsideAnnotation()) {
                return coneKotlinTypeForFlexibleBound$default;
            }
            if (!javaClassifierType.isRaw() && (classifier = javaClassifierType.getClassifier()) != null && isTriviallyFlexible(classifier)) {
                return TypeUtilsKt.toTrivialFlexibleType(coneKotlinTypeForFlexibleBound$default, TypeComponentsKt.getTypeContext(firSession));
            }
            ConeLookupTagBasedType coneKotlinTypeForFlexibleBound = toConeKotlinTypeForFlexibleBound(javaClassifierType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, coneAttributes, ktSourceElement, coneKotlinTypeForFlexibleBound$default);
            return javaClassifierType.isRaw() ? ConeRawType.INSTANCE.create(coneKotlinTypeForFlexibleBound$default, coneKotlinTypeForFlexibleBound) : new ConeFlexibleType(coneKotlinTypeForFlexibleBound$default, coneKotlinTypeForFlexibleBound, false);
        }
        ConeAttributes coneAttributes2 = empty;
        if (javaType instanceof JavaArrayType) {
            JavaPrimitiveType componentType = ((JavaArrayType) javaType).getComponentType();
            if (componentType instanceof JavaPrimitiveType) {
                StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                PrimitiveType type = componentType.getType();
                type.getClass();
                String identifier2 = type.getArrayTypeName().getIdentifier();
                identifier2.getClass();
                pair = TuplesKt.to(standardClassIds.byName(identifier2), new ConeKotlinType[0]);
            } else {
                pair = TuplesKt.to(StandardClassIds.INSTANCE.getArray(), new ConeKotlinType[]{toConeKotlinType$default(componentType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, ktSourceElement, null, 16, null)});
            }
            ClassId classId = (ClassId) pair.component1();
            ConeKotlinType[] coneKotlinTypeArr = (ConeKotlinType[]) pair.component2();
            int length = coneKotlinTypeArr.length;
            ConeKotlinTypeProjectionOut[] coneKotlinTypeProjectionOutArr = new ConeKotlinTypeProjectionOut[length];
            for (int i = 0; i < length; i++) {
                coneKotlinTypeProjectionOutArr[i] = new ConeKotlinTypeProjectionOut(coneKotlinTypeArr[i]);
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[firJavaTypeConversionMode.ordinal()];
            if (i2 != 1) {
                return i2 != 2 ? toConeFlexibleType(classId, coneKotlinTypeArr, coneKotlinTypeProjectionOutArr, coneAttributes2) : TypeConstructionUtilsKt.constructClassLikeType(classId, coneKotlinTypeArr, false, coneAttributes2);
            }
            return TypeConstructionUtilsKt.constructClassLikeType(classId, coneKotlinTypeProjectionOutArr, false, coneAttributes2);
        }
        if (javaType instanceof JavaPrimitiveType) {
            StandardClassIds standardClassIds2 = StandardClassIds.INSTANCE;
            PrimitiveType type2 = ((JavaPrimitiveType) javaType).getType();
            if (type2 == null || (typeName = type2.getTypeName()) == null || (identifier = typeName.getIdentifier()) == null) {
                identifier = "Unit";
            }
            return TypeConstructionUtilsKt.constructClassLikeType$default(standardClassIds2.byName(identifier), null, false, coneAttributes2, 3, null);
        }
        if (!(javaType instanceof JavaWildcardType)) {
            if (javaType == null) {
                return ConeStarProjection.INSTANCE;
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Strange JavaType: " + javaType.getClass(), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            exceptionAttachmentBuilder.withEntry(ModuleXmlParser.TYPE, javaType, new Function1() { // from class: ol7
                public final Object invoke(Object obj) {
                    return JavaTypeConversionKt.toConeTypeProjection$lambda$1$0((JavaType) obj);
                }
            });
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
        JavaType bound = javaWildcardType.getBound();
        Variance variance2 = javaWildcardType.isExtends() ? Variance.OUT_VARIANCE : Variance.IN_VARIANCE;
        if (bound == null || !(variance == Variance.INVARIANT || variance == variance2)) {
            return ConeStarProjection.INSTANCE;
        }
        JavaAnnotation javaAnnotationExtractNullabilityAnnotationOnBoundedWildcard = JavaUtilsKt.extractNullabilityAnnotationOnBoundedWildcard(javaWildcardType);
        ConeKotlinType coneKotlinType = toConeKotlinType(bound, firSession, javaTypeParameterStack, firJavaTypeConversionMode, ktSourceElement, javaAnnotationExtractNullabilityAnnotationOnBoundedWildcard != null ? CollectionsKt.listOf(javaAnnotationExtractNullabilityAnnotationOnBoundedWildcard) : null);
        return javaWildcardType.isExtends() ? new ConeKotlinTypeProjectionOut(coneKotlinType) : new ConeKotlinTypeProjectionIn(coneKotlinType);
    }

    public static /* synthetic */ ConeTypeProjection toConeTypeProjection$default(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, Variance variance, FirJavaTypeConversionMode firJavaTypeConversionMode, KtSourceElement ktSourceElement, Collection collection, int i, Object obj) {
        if ((i & 32) != 0) {
            collection = null;
        }
        return toConeTypeProjection(javaType, firSession, javaTypeParameterStack, variance, firJavaTypeConversionMode, ktSourceElement, collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toConeTypeProjection$lambda$1$0(JavaType javaType) {
        javaType.getClass();
        return javaType.toString();
    }

    public static final FirJavaTypeRef toFirJavaTypeRef(final JavaType javaType, final FirSession firSession, final KtSourceElement ktSourceElement) {
        javaType.getClass();
        firSession.getClass();
        FirJavaTypeRefBuilder firJavaTypeRefBuilder = new FirJavaTypeRefBuilder();
        firJavaTypeRefBuilder.setAnnotationBuilder(new Function0() { // from class: pl7
            public final Object invoke() {
                return JavaAnnotationsMappingKt.convertAnnotationsToFir((JavaAnnotationOwner) javaType, firSession, ktSourceElement);
            }
        });
        firJavaTypeRefBuilder.setType(javaType);
        firJavaTypeRefBuilder.setSource(ktSourceElement);
        return firJavaTypeRefBuilder.build();
    }

    public static final FirResolvedTypeRef toFirResolvedTypeRef(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode) {
        firSession.getClass();
        javaTypeParameterStack.getClass();
        firJavaTypeConversionMode.getClass();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        ConeKotlinType coneKotlinType$default = toConeKotlinType$default(javaType, firSession, javaTypeParameterStack, firJavaTypeConversionMode, ktSourceElement, null, 16, null);
        if (firJavaTypeConversionMode == FirJavaTypeConversionMode.SUPERTYPE) {
            coneKotlinType$default = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType$default);
        }
        firResolvedTypeRefBuilder.setConeType(coneKotlinType$default);
        CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), CustomAnnotationTypeAttributeKt.getTypeAnnotations(firResolvedTypeRefBuilder.getConeType()));
        firResolvedTypeRefBuilder.setSource(ktSourceElement);
        return firResolvedTypeRefBuilder.build();
    }

    public static /* synthetic */ FirResolvedTypeRef toFirResolvedTypeRef$default(JavaType javaType, FirSession firSession, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement, FirJavaTypeConversionMode firJavaTypeConversionMode, int i, Object obj) {
        if ((i & 8) != 0) {
            firJavaTypeConversionMode = FirJavaTypeConversionMode.DEFAULT;
        }
        return toFirResolvedTypeRef(javaType, firSession, javaTypeParameterStack, ktSourceElement, firJavaTypeConversionMode);
    }
}
