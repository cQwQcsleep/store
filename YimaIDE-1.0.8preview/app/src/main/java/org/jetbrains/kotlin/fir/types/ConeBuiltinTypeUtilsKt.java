package org.jetbrains.kotlin.fir.types;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\u001a#\u00100\u001a\u00020\u0001*\u00020\u00022\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u00104\u001a\u001a\u00105\u001a\u00020\u0001*\u00020\u00022\f\u00106\u001a\b\u0012\u0004\u0012\u00020207H\u0002\u001a\u001b\u0010(\u001a\u00020\u0001*\u00020\u00022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u00108\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0003\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0003\"\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0003\"\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0003\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0003\"\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0003\"\u0015\u0010\r\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0003\"\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0003\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0003\"\u0015\u0010\u0010\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0003\"\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0003\"\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0003\"\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0003\"\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0003\"\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0003\"\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0003\"\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0003\"\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0003\"\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0003\"\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0003\"\u0015\u0010\u001b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0003\"\u0015\u0010\u001c\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0003\"\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0003\"\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0003\"\u0015\u0010\u001f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0003\"\u0015\u0010 \u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b \u0010\u0003\"\u0015\u0010!\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b!\u0010\u0003\"\u0015\u0010\"\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0003\"\u0015\u0010#\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b#\u0010\u0003\"\u0015\u0010$\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b$\u0010\u0003\"\u0015\u0010%\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b%\u0010\u0003\"\u0015\u0010&\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b&\u0010\u0003\"\u0015\u0010'\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010\u0003\"\u0015\u0010(\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b(\u0010\u0003\"\u0015\u0010)\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b)\u0010\u0003\"\u0015\u0010*\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b*\u0010\u0003\"\u0015\u0010+\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010\u0003\"\u0015\u0010,\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b,\u0010\u0003\"\u0015\u0010-\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010\u0003\"\u0015\u0010.\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b.\u0010\u0003\"\u0015\u0010/\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b/\u0010\u0003¨\u00069"}, d2 = {"isByte", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isShort", "isInt", "isLong", "isFloat", "isDouble", "isAny", "isNullableAny", "isAnyOrNullableAny", "isNothing", "isNullableNothing", "isNothingOrNullableNothing", "isUnit", "isUnitOrNullableUnit", "isBoolean", "isNullableBoolean", "isBooleanOrNullableBoolean", "isThrowableOrNullableThrowable", "isChar", "isCharOrNullableChar", "isString", "isNullableString", "isEnum", "isList", "isMutableList", "isSet", "isMutableSet", "isMap", "isMutableMap", "isSequence", "isUByte", "isUShort", "isUInt", "isULong", "isPrimitiveOrNullablePrimitive", "isPrimitive", "isPrimitiveNumberOrNullableType", "isArrayType", "isArrayTypeOrNullableArrayType", "isNonPrimitiveArray", "isPrimitiveArray", "isUnsignedArray", "isPrimitiveOrUnsignedArray", "isUnsignedTypeOrNullableUnsignedType", "isUnsignedType", "isBuiltinType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "isNullable", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/name/ClassId;Ljava/lang/Boolean;)Z", "isAnyOfBuiltinType", "classIds", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/lang/Boolean;)Z", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeBuiltinTypeUtilsKt {
    public static final boolean isAny(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getAny(), Boolean.FALSE);
    }

    private static final boolean isAnyOfBuiltinType(ConeKotlinType coneKotlinType, Set<ClassId> set) {
        if (coneKotlinType instanceof ConeClassLikeType) {
            return set.contains(((ConeClassLikeType) coneKotlinType).getLookupTag().getClassId());
        }
        return false;
    }

    public static final boolean isAnyOrNullableAny(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getAny(), null);
    }

    private static final boolean isArrayType(ConeKotlinType coneKotlinType, Boolean bool) {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (isBuiltinType(coneKotlinType, standardClassIds.getArray(), bool)) {
            return true;
        }
        Collection collectionValues = standardClassIds.getPrimitiveArrayTypeByElementType().values();
        if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
            Iterator it = collectionValues.iterator();
            while (it.hasNext()) {
                if (isBuiltinType(coneKotlinType, (ClassId) it.next(), bool)) {
                    return true;
                }
            }
        }
        Collection collectionValues2 = StandardClassIds.INSTANCE.getUnsignedArrayTypeByElementType().values();
        if ((collectionValues2 instanceof Collection) && collectionValues2.isEmpty()) {
            return false;
        }
        Iterator it2 = collectionValues2.iterator();
        while (it2.hasNext()) {
            if (isBuiltinType(coneKotlinType, (ClassId) it2.next(), bool)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isArrayTypeOrNullableArrayType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isArrayType(coneKotlinType, null);
    }

    public static final boolean isBoolean(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getBoolean(), Boolean.FALSE);
    }

    public static final boolean isBooleanOrNullableBoolean(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getBoolean(), null);
    }

    private static final boolean isBuiltinType(ConeKotlinType coneKotlinType, ClassId classId, Boolean bool) {
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            return false;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), classId) && (bool == null || Intrinsics.areEqual(Boolean.valueOf(coneClassLikeType.isMarkedNullable()), bool));
    }

    public static final boolean isByte(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getByte(), Boolean.FALSE);
    }

    public static final boolean isChar(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getChar(), Boolean.FALSE);
    }

    public static final boolean isCharOrNullableChar(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isAnyOfBuiltinType(coneKotlinType, SetsKt.setOf(StandardClassIds.INSTANCE.getChar()));
    }

    public static final boolean isDouble(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getDouble(), Boolean.FALSE);
    }

    public static final boolean isEnum(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getEnum(), Boolean.FALSE);
    }

    public static final boolean isFloat(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getFloat(), Boolean.FALSE);
    }

    public static final boolean isInt(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getInt(), Boolean.FALSE);
    }

    public static final boolean isList(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getList(), Boolean.FALSE);
    }

    public static final boolean isLong(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getLong(), Boolean.FALSE);
    }

    public static final boolean isMap(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getMap(), Boolean.FALSE);
    }

    public static final boolean isMutableList(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getMutableList(), Boolean.FALSE);
    }

    public static final boolean isMutableMap(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getMutableMap(), Boolean.FALSE);
    }

    public static final boolean isMutableSet(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getMutableSet(), Boolean.FALSE);
    }

    public static final boolean isNonPrimitiveArray(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinType);
        return Intrinsics.areEqual(classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null, StandardClassIds.INSTANCE.getArray());
    }

    public static final boolean isNothing(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getNothing(), Boolean.FALSE);
    }

    public static final boolean isNothingOrNullableNothing(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getNothing(), null);
    }

    public static final boolean isNullableAny(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getAny(), Boolean.TRUE);
    }

    public static final boolean isNullableBoolean(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getBoolean(), Boolean.TRUE);
    }

    public static final boolean isNullableNothing(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getNothing(), Boolean.TRUE);
    }

    public static final boolean isNullableString(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getString(), Boolean.TRUE);
    }

    public static final boolean isPrimitive(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isPrimitiveOrNullablePrimitive(coneKotlinType) && !ConeTypeUtilsKt.isMarkedNullable(coneKotlinType);
    }

    public static final boolean isPrimitiveArray(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        Collection collectionValues = StandardClassIds.INSTANCE.getPrimitiveArrayTypeByElementType().values();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinType);
        return CollectionsKt.contains(collectionValues, classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null);
    }

    public static final boolean isPrimitiveNumberOrNullableType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return (!isPrimitiveOrNullablePrimitive(coneKotlinType) || isBooleanOrNullableBoolean(coneKotlinType) || isCharOrNullableChar(coneKotlinType)) ? false : true;
    }

    public static final boolean isPrimitiveOrNullablePrimitive(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isAnyOfBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getPrimitiveTypes());
    }

    public static final boolean isPrimitiveOrUnsignedArray(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isPrimitiveArray(coneKotlinType) || isUnsignedArray(coneKotlinType);
    }

    public static final boolean isSequence(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getSequence(), Boolean.FALSE);
    }

    public static final boolean isSet(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getSet(), Boolean.FALSE);
    }

    public static final boolean isShort(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getShort(), Boolean.FALSE);
    }

    public static final boolean isString(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getString(), Boolean.FALSE);
    }

    public static final boolean isThrowableOrNullableThrowable(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isAnyOfBuiltinType(coneKotlinType, SetsKt.setOf(StandardClassIds.INSTANCE.getThrowable()));
    }

    public static final boolean isUByte(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUByte(), Boolean.FALSE);
    }

    public static final boolean isUInt(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUInt(), Boolean.FALSE);
    }

    public static final boolean isULong(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getULong(), Boolean.FALSE);
    }

    public static final boolean isUShort(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUShort(), Boolean.FALSE);
    }

    public static final boolean isUnit(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUnit(), Boolean.FALSE);
    }

    public static final boolean isUnitOrNullableUnit(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUnit(), null);
    }

    public static final boolean isUnsignedArray(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        Collection collectionValues = StandardClassIds.INSTANCE.getUnsignedArrayTypeByElementType().values();
        ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinType);
        return CollectionsKt.contains(collectionValues, classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null);
    }

    public static final boolean isUnsignedType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isUnsignedTypeOrNullableUnsignedType(coneKotlinType) && !ConeTypeUtilsKt.isMarkedNullable(coneKotlinType);
    }

    public static final boolean isUnsignedTypeOrNullableUnsignedType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isAnyOfBuiltinType(coneKotlinType, StandardClassIds.INSTANCE.getUnsignedTypes());
    }

    public static final boolean isArrayType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return isArrayType(coneKotlinType, Boolean.FALSE);
    }
}
