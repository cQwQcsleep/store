package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0001\u001a\u001e\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001\u001a\u001e\u0010\b\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u0001\u001a\n\u0010\f\u001a\u00020\u0002*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\r"}, d2 = {"isArrayOrPrimitiveArray", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "checkUnsignedArrays", "createOutArrayType", "nullable", "createPrimitiveArrayType", "createArrayType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "createPrimitiveArrayTypeIfPossible", "varargElementType", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArrayUtilsKt {
    public static final ConeClassLikeType createArrayType(ConeTypeProjection coneTypeProjection, boolean z, boolean z2) {
        coneTypeProjection.getClass();
        if ((coneTypeProjection instanceof ConeKotlinTypeProjection) && z2) {
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(((ConeKotlinTypeProjection) coneTypeProjection).getType());
            if (coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType) {
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible;
                if (!coneClassLikeType.getIsMarkedNullable()) {
                    ClassId classId = coneClassLikeType.getLookupTag().getClassId();
                    StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                    ClassId classId2 = (ClassId) standardClassIds.getPrimitiveArrayTypeByElementType().get(classId);
                    if (classId2 == null) {
                        classId2 = (ClassId) standardClassIds.getUnsignedArrayTypeByElementType().get(classId);
                    }
                    ClassId classId3 = classId2;
                    if (classId3 != null) {
                        return TypeConstructionUtilsKt.constructClassLikeType$default(classId3, null, z, null, 5, null);
                    }
                }
            }
        }
        return TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getArray(), new ConeTypeProjection[]{coneTypeProjection}, z, null, 4, null);
    }

    public static /* synthetic */ ConeClassLikeType createArrayType$default(ConeTypeProjection coneTypeProjection, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return createArrayType(coneTypeProjection, z, z2);
    }

    public static final ConeKotlinType createOutArrayType(ConeKotlinType coneKotlinType, boolean z, boolean z2) {
        coneKotlinType.getClass();
        return createArrayType(new ConeKotlinTypeProjectionOut(coneKotlinType), z, z2);
    }

    public static /* synthetic */ ConeKotlinType createOutArrayType$default(ConeKotlinType coneKotlinType, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return createOutArrayType(coneKotlinType, z, z2);
    }

    public static final boolean isArrayOrPrimitiveArray(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return FirTypeUtilsKt.arrayElementTypeArgument$default(coneKotlinType, false, 1, null) != null;
    }

    public static /* synthetic */ boolean isArrayOrPrimitiveArray$default(ConeKotlinType coneKotlinType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return isArrayOrPrimitiveArray(coneKotlinType, z);
    }

    public static final ConeKotlinType varargElementType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinType, false, 1, null);
        return coneKotlinTypeArrayElementType$default == null ? coneKotlinType : coneKotlinTypeArrayElementType$default;
    }

    public static final boolean isArrayOrPrimitiveArray(ConeKotlinType coneKotlinType, boolean z) {
        coneKotlinType.getClass();
        return FirTypeUtilsKt.arrayElementTypeArgument(coneKotlinType, z) != null;
    }
}
