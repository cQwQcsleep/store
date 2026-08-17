package org.jetbrains.kotlin.fir.resolve.substitution;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeCapturedTypeConstructor;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\b&\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH&J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\t*\u00020\t2\u0006\u0010\u0011\u001a\u00020\tJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u0013\u001a\u0004\u0018\u00010\t*\u00020\tH\u0002J\u000e\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u0004\u0018\u00010\t*\u00020\u0017H\u0004J\u000e\u0010\u0018\u001a\u0004\u0018\u00010\t*\u00020\u0019H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001b²\u0006\u0012\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001dX\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;)V", "getTypeContext", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "substituteArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "projection", "index", Argument.Delimiters.none, "updateNullabilityIfNeeded", "originalType", "substituteOrNull", "substituteRecursive", "substituteIntersectedTypes", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "substituteOriginal", "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "substituteArguments", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "Companion", "org.jetbrains.kotlin:providers", "newArguments", Argument.Delimiters.none}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractConeSubstitutor extends ConeSubstitutor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConeTypeContext typeContext;

    public AbstractConeSubstitutor(ConeTypeContext coneTypeContext) {
        coneTypeContext.getClass();
        this.typeContext = coneTypeContext;
    }

    public static ConeTypeProjection[] a(ConeSimpleKotlinType coneSimpleKotlinType) {
        return new ConeTypeProjection[coneSimpleKotlinType.getTypeArguments().length];
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ConeKotlinType substituteArguments(final ConeSimpleKotlinType coneSimpleKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: xl
            public final Object invoke() {
                return AbstractConeSubstitutor.a(coneSimpleKotlinType);
            }
        });
        ConeTypeProjection[] typeArguments = coneSimpleKotlinType.getTypeArguments();
        int length = typeArguments.length;
        int i = 0;
        boolean z = false;
        while (true) {
            ConeTypeProjection coneTypeProjection = null;
            if (i >= length) {
                break;
            }
            ConeTypeProjection coneTypeProjection2 = typeArguments[i];
            ConeTypeProjection[] coneTypeProjectionArrSubstituteArguments$lambda$1 = substituteArguments$lambda$1(lazy);
            ConeTypeProjection coneTypeProjectionSubstituteArgument = substituteArgument(coneTypeProjection2, i);
            if (coneTypeProjectionSubstituteArgument != null) {
                Unit unit = Unit.INSTANCE;
                z = true;
                coneTypeProjection = coneTypeProjectionSubstituteArgument;
            }
            coneTypeProjectionArrSubstituteArguments$lambda$1[i] = coneTypeProjection;
            i++;
        }
        if (!z) {
            return null;
        }
        ConeTypeProjection[] typeArguments2 = coneSimpleKotlinType.getTypeArguments();
        int length2 = typeArguments2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            ConeTypeProjection coneTypeProjection3 = typeArguments2[i2];
            if (substituteArguments$lambda$1(lazy)[i2] == null) {
                substituteArguments$lambda$1(lazy)[i2] = coneTypeProjection3;
            }
        }
        if (coneSimpleKotlinType instanceof ConeClassLikeTypeImpl) {
            ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) coneSimpleKotlinType;
            return new ConeClassLikeTypeImpl(coneClassLikeTypeImpl.getLookupTag(), substituteArguments$lambda$1(lazy), coneClassLikeTypeImpl.getIsMarkedNullable(), coneClassLikeTypeImpl.getAttributes());
        }
        if (coneSimpleKotlinType instanceof ConeErrorType) {
            ConeErrorType coneErrorType = (ConeErrorType) coneSimpleKotlinType;
            return new ConeErrorType(coneErrorType.getDiagnostic(), coneErrorType.getIsUninferredParameter(), null, substituteArguments$lambda$1(lazy), coneErrorType.getAttributes(), null, null, 100, null);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown class-like type to substitute, " + Reflection.getOrCreateKotlinClass(coneSimpleKotlinType.getClass()), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, coneSimpleKotlinType);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private static final ConeTypeProjection[] substituteArguments$lambda$1(Lazy<ConeTypeProjection[]> lazy) {
        return (ConeTypeProjection[]) lazy.getValue();
    }

    private final ConeIntersectionType substituteIntersectedTypes(ConeIntersectionType coneIntersectionType) {
        ArrayList arrayList = new ArrayList(coneIntersectionType.getIntersectedTypes().size());
        boolean z = false;
        for (ConeKotlinType coneKotlinType : coneIntersectionType.getIntersectedTypes()) {
            ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(coneKotlinType);
            if (coneKotlinTypeSubstituteOrNull != null) {
                z = true;
                coneKotlinType = coneKotlinTypeSubstituteOrNull;
            }
            arrayList.add(coneKotlinType);
        }
        if (z) {
            return new ConeIntersectionType(arrayList, null, 2, null);
        }
        return null;
    }

    private final ConeKotlinType substituteRecursive(ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        ArrayList arrayList;
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        if (coneKotlinType instanceof ConeClassLikeType) {
            return substituteArguments((ConeSimpleKotlinType) coneKotlinType);
        }
        if (!(coneKotlinType instanceof ConeLookupTagBasedType) && !(coneKotlinType instanceof ConeTypeVariableType)) {
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                ConeTypeContext coneTypeContext = this.typeContext;
                ConeKotlinType coneKotlinTypeSubstituteOrNull2 = substituteOrNull(coneFlexibleType.getLowerBound());
                if (coneFlexibleType.getIsTrivial()) {
                    if (coneKotlinTypeSubstituteOrNull2 == null) {
                        return null;
                    }
                    if (coneKotlinTypeSubstituteOrNull2 instanceof ConeRigidType) {
                        return TypeUtilsKt.coneFlexibleOrSimpleType(coneTypeContext, coneKotlinTypeSubstituteOrNull2, TypeUtilsKt.withNullability$default(coneKotlinTypeSubstituteOrNull2, true, coneTypeContext, null, true, 4, null), true);
                    }
                    if (!(coneKotlinTypeSubstituteOrNull2 instanceof ConeFlexibleType)) {
                        bu8.a();
                        return null;
                    }
                    if (((ConeFlexibleType) coneKotlinTypeSubstituteOrNull2).getAttributes().isEmpty()) {
                        return coneKotlinTypeSubstituteOrNull2;
                    }
                }
                ConeKotlinType coneKotlinTypeSubstituteOrNull3 = substituteOrNull(coneFlexibleType.getUpperBound());
                if (coneKotlinTypeSubstituteOrNull2 == null && coneKotlinTypeSubstituteOrNull3 == null) {
                    return null;
                }
                if (!(coneFlexibleType instanceof ConeRawType)) {
                    if (coneKotlinTypeSubstituteOrNull2 == null) {
                        coneKotlinTypeSubstituteOrNull2 = coneFlexibleType.getLowerBound();
                    }
                    if (coneKotlinTypeSubstituteOrNull3 == null) {
                        coneKotlinTypeSubstituteOrNull3 = coneFlexibleType.getUpperBound();
                    }
                    return TypeUtilsKt.coneFlexibleOrSimpleType(coneTypeContext, coneKotlinTypeSubstituteOrNull2, coneKotlinTypeSubstituteOrNull3, false);
                }
                ConeRawType.Companion companion = ConeRawType.INSTANCE;
                if (coneKotlinTypeSubstituteOrNull2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeSubstituteOrNull2)) == null) {
                    lowerBound = coneFlexibleType.getLowerBound();
                }
                if (coneKotlinTypeSubstituteOrNull3 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinTypeSubstituteOrNull3)) == null) {
                    upperBound = coneFlexibleType.getUpperBound();
                }
                return companion.create(lowerBound, upperBound);
            }
            if (coneKotlinType instanceof ConeCapturedType) {
                ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
                ConeKotlinType lowerType = coneCapturedType.getConstructor().getLowerType();
                if (lowerType == null) {
                    lowerType = ConeTypeProjectionKt.getType(coneCapturedType.getConstructor().getProjection());
                }
                if (lowerType == null || (coneKotlinTypeSubstituteOrNull = substituteOrNull(lowerType)) == null) {
                    return null;
                }
                if (coneKotlinTypeSubstituteOrNull instanceof ConeCapturedType) {
                    return (ConeCapturedType) coneKotlinTypeSubstituteOrNull;
                }
                List<ConeKotlinType> supertypes = coneCapturedType.getConstructor().getSupertypes();
                if (supertypes != null) {
                    List<ConeKotlinType> list = supertypes;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (ConeKotlinType coneKotlinType2 : list) {
                        ConeKotlinType coneKotlinTypeSubstituteOrNull4 = substituteOrNull(coneKotlinType2);
                        if (coneKotlinTypeSubstituteOrNull4 != null) {
                            coneKotlinType2 = coneKotlinTypeSubstituteOrNull4;
                        }
                        arrayList2.add(coneKotlinType2);
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = null;
                }
                return ConeCapturedType.copy$default(coneCapturedType, false, new ConeCapturedTypeConstructor(SubstitutionUtilitiesKt.wrapProjection(coneCapturedType.getConstructor().getProjection(), coneKotlinTypeSubstituteOrNull), coneCapturedType.getConstructor().getLowerType() != null ? coneKotlinTypeSubstituteOrNull : null, coneCapturedType.getConstructor().getCaptureStatus(), arrayList, coneCapturedType.getConstructor().getTypeParameterMarker()), null, 5, null);
            }
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                return substituteOriginal((ConeDefinitelyNotNullType) coneKotlinType);
            }
            if (coneKotlinType instanceof ConeIntersectionType) {
                return substituteIntersectedTypes((ConeIntersectionType) coneKotlinType);
            }
            if ((coneKotlinType instanceof ConeStubType) || (coneKotlinType instanceof ConeIntegerLiteralType)) {
                return null;
            }
            bu8.a();
        }
        return null;
    }

    public final ConeTypeContext getTypeContext() {
        return this.typeContext;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
    public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) {
        ConeKotlinType type;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        projection.getClass();
        ConeKotlinTypeProjection coneKotlinTypeProjection = projection instanceof ConeKotlinTypeProjection ? (ConeKotlinTypeProjection) projection : null;
        if (coneKotlinTypeProjection == null || (type = coneKotlinTypeProjection.getType()) == null || (coneKotlinTypeSubstituteOrNull = substituteOrNull(type)) == null) {
            return null;
        }
        return SubstitutionUtilitiesKt.wrapProjection(projection, coneKotlinTypeSubstituteOrNull);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00a8  */
    @Override // org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
    public ConeKotlinType substituteOrNull(ConeKotlinType type) {
        ConeAttributes attributes;
        ConeAttributes attributes2;
        ConeAttributes coneAttributesAdd;
        ConeKotlinType coneType;
        type.getClass();
        ConeKotlinType coneKotlinTypeSubstituteType = substituteType(type);
        if (coneKotlinTypeSubstituteType != null && (type instanceof ConeDefinitelyNotNullType)) {
            return TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(coneKotlinTypeSubstituteType, this.typeContext, false, false, 4, (Object) null);
        }
        if (coneKotlinTypeSubstituteType == null) {
            coneKotlinTypeSubstituteType = substituteRecursive(type);
        }
        if (type instanceof ConeFlexibleType) {
            attributes = null;
        } else {
            attributes = type.getAttributes();
            if (attributes.isEmpty()) {
                attributes = null;
            } else {
                List<? extends ConeAttribute<?>> mutableList = null;
                int i = 0;
                boolean z = false;
                for (ConeAttribute<?> coneAttribute : attributes) {
                    int i2 = i + 1;
                    if (coneAttribute instanceof ConeAttributeWithConeType) {
                        ConeAttributeWithConeType coneAttributeWithConeTypeCopyWith = (ConeAttributeWithConeType) coneAttribute;
                        ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(coneAttributeWithConeTypeCopyWith.getConeType());
                        if (coneKotlinTypeSubstituteOrNull == null) {
                            coneAttributeWithConeTypeCopyWith = null;
                        } else if (!Intrinsics.areEqual(coneKotlinTypeSubstituteOrNull, coneAttributeWithConeTypeCopyWith.getConeType())) {
                            ConeAttributeWithConeType coneAttributeWithConeType = (ConeAttributeWithConeType) coneKotlinTypeSubstituteOrNull.getAttributes().get(coneAttributeWithConeTypeCopyWith.getKey());
                            if (coneAttributeWithConeType != null && (coneType = coneAttributeWithConeType.getConeType()) != null) {
                                coneKotlinTypeSubstituteOrNull = coneType;
                            }
                            coneAttributeWithConeTypeCopyWith = coneAttributeWithConeTypeCopyWith.copyWith(coneKotlinTypeSubstituteOrNull);
                        }
                        if (coneAttributeWithConeTypeCopyWith != null) {
                            if (mutableList == null) {
                                mutableList = CollectionsKt.toMutableList(attributes);
                            }
                            mutableList.set(i, coneAttributeWithConeTypeCopyWith);
                            z = z || !Intrinsics.areEqual(coneAttributeWithConeTypeCopyWith, coneAttribute);
                        }
                    }
                    i = i2;
                }
                if (mutableList == null || z) {
                    if (mutableList != null) {
                        attributes = ConeAttributes.INSTANCE.create(mutableList);
                    } else {
                        attributes = null;
                    }
                }
            }
        }
        if (attributes == null) {
            attributes = null;
        } else if (coneKotlinTypeSubstituteType != null && (attributes2 = coneKotlinTypeSubstituteType.getAttributes()) != null && (coneAttributesAdd = attributes2.add(attributes)) != null) {
            attributes = coneAttributesAdd;
        }
        if (coneKotlinTypeSubstituteType == null && attributes == null) {
            return null;
        }
        if (coneKotlinTypeSubstituteType != null) {
            type = coneKotlinTypeSubstituteType;
        }
        return attributes != null ? TypeUtilsKt.withAttributes(type, attributes) : type;
    }

    public final ConeKotlinType substituteOriginal(ConeDefinitelyNotNullType coneDefinitelyNotNullType) {
        coneDefinitelyNotNullType.getClass();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(coneDefinitelyNotNullType.getOriginal());
        if (coneKotlinTypeSubstituteOrNull == null) {
            return null;
        }
        ConeKotlinType coneKotlinTypeWithNullability = TypeUtilsKt.withNullability(coneKotlinTypeSubstituteOrNull, false, this.typeContext, coneKotlinTypeSubstituteOrNull.getAttributes().add(coneDefinitelyNotNullType.getOriginal().getAttributes()), true);
        ConeDefinitelyNotNullType coneDefinitelyNotNullTypeCreate = TypeUtilsKt.create(ConeDefinitelyNotNullType.INSTANCE, coneKotlinTypeWithNullability, this.typeContext, true);
        return coneDefinitelyNotNullTypeCreate != null ? coneDefinitelyNotNullTypeCreate : coneKotlinTypeWithNullability;
    }

    public abstract ConeKotlinType substituteType(ConeKotlinType type);

    public final ConeKotlinType updateNullabilityIfNeeded(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return INSTANCE.updateNullabilityIfNeeded(coneKotlinType, coneKotlinType2, this.typeContext);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor$Companion;", Argument.Delimiters.none, "<init>", "()V", "updateNullabilityIfNeeded", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConeKotlinType updateNullabilityIfNeeded(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, ConeTypeContext coneTypeContext) {
            coneKotlinType.getClass();
            coneKotlinType2.getClass();
            coneTypeContext.getClass();
            if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                return TypeUtilsKt.withNullability$default(coneKotlinType, false, coneTypeContext, null, false, 12, null);
            }
            return ConeTypeUtilsKt.isMarkedNullable(coneKotlinType2) ? TypeUtilsKt.withNullability$default(coneKotlinType, true, coneTypeContext, null, false, 12, null) : coneKotlinType;
        }

        private Companion() {
        }
    }
}
