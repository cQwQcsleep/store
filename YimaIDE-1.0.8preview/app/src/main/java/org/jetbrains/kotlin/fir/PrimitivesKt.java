package org.jetbrains.kotlin.fir;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0006\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\b\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\t\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\n\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\f\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\r\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0010\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u0006\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u0007\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\b\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\t\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\n\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u000b\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\f\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u000e\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u000f\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u0010\u001a\u00020\u0004*\u00020\u0012\u001a\n\u0010\u0011\u001a\u00020\u0004*\u00020\u0012\u001a\u000e\u0010\u0005\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u0006\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u0007\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\t\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\n\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u000b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\f\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u000e\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u000f\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u0010\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\u001a\u000e\u0010\u0011\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0013\"\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"createType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/name/ClassId;", "isNullable", Argument.Delimiters.none, "isDouble", "isFloat", "isLong", "isInt", "isShort", "isByte", "isBoolean", "isChar", "isULong", "isPrimitiveType", "isPrimitiveNumberType", "isPrimitiveUnsignedNumberType", "isPrimitiveNumberOrUnsignedNumberType", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "PRIMITIVE_NUMBER_CLASS_IDS", Argument.Delimiters.none, "PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PrimitivesKt {
    private static final Set<ClassId> PRIMITIVE_NUMBER_CLASS_IDS;
    private static final Set<ClassId> PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS;

    static {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        PRIMITIVE_NUMBER_CLASS_IDS = SetsKt.setOf(new ClassId[]{standardClassIds.getDouble(), standardClassIds.getFloat(), standardClassIds.getLong(), standardClassIds.getInt(), standardClassIds.getShort(), standardClassIds.getByte()});
        PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS = SetsKt.setOf(new ClassId[]{standardClassIds.getULong(), standardClassIds.getUInt(), standardClassIds.getUShort(), standardClassIds.getUByte()});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeClassLikeType createType(ClassId classId, boolean z) {
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(classId), ConeTypeProjection.Companion.getEMPTY_ARRAY(), z, null, 8, null);
    }

    public static /* synthetic */ ConeClassLikeType createType$default(ClassId classId, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return createType(classId, z);
    }

    public static final boolean isBoolean(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getBoolean());
    }

    public static final boolean isByte(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getByte());
    }

    public static final boolean isChar(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getChar());
    }

    public static final boolean isDouble(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getDouble());
    }

    public static final boolean isFloat(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getFloat());
    }

    public static final boolean isInt(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getInt());
    }

    public static final boolean isLong(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getLong());
    }

    public static final boolean isPrimitiveNumberOrUnsignedNumberType(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return isPrimitiveNumberType(coneClassLikeType) || isPrimitiveUnsignedNumberType(coneClassLikeType);
    }

    public static final boolean isPrimitiveNumberType(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return PRIMITIVE_NUMBER_CLASS_IDS.contains(coneClassLikeType.getLookupTag().getClassId());
    }

    public static final boolean isPrimitiveType(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return isPrimitiveNumberOrUnsignedNumberType(coneClassLikeType) || isBoolean(coneClassLikeType) || isByte(coneClassLikeType) || isShort(coneClassLikeType) || isChar(coneClassLikeType);
    }

    public static final boolean isPrimitiveUnsignedNumberType(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS.contains(coneClassLikeType.getLookupTag().getClassId());
    }

    public static final boolean isShort(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getShort());
    }

    public static final boolean isULong(ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        return Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getULong());
    }

    public static final boolean isPrimitiveNumberType(FirClass firClass) {
        firClass.getClass();
        return PRIMITIVE_NUMBER_CLASS_IDS.contains(FirDeclarationUtilKt.getClassId(firClass));
    }

    public static final boolean isPrimitiveUnsignedNumberType(FirClass firClass) {
        firClass.getClass();
        return PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS.contains(FirDeclarationUtilKt.getClassId(firClass));
    }

    public static final boolean isPrimitiveNumberType(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return PRIMITIVE_NUMBER_CLASS_IDS.contains(firClassSymbol.getClassId());
    }

    public static final boolean isPrimitiveUnsignedNumberType(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return PRIMITIVE_UNSIGNED_NUMBER_CLASS_IDS.contains(firClassSymbol.getClassId());
    }

    public static final boolean isPrimitiveNumberOrUnsignedNumberType(FirClass firClass) {
        firClass.getClass();
        return isPrimitiveNumberType(firClass) || isPrimitiveUnsignedNumberType(firClass);
    }

    public static final boolean isPrimitiveNumberOrUnsignedNumberType(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return isPrimitiveNumberType(firClassSymbol) || isPrimitiveUnsignedNumberType(firClassSymbol);
    }

    public static final boolean isBoolean(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getBoolean());
    }

    public static final boolean isByte(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getByte());
    }

    public static final boolean isChar(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getChar());
    }

    public static final boolean isDouble(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getDouble());
    }

    public static final boolean isFloat(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getFloat());
    }

    public static final boolean isInt(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getInt());
    }

    public static final boolean isLong(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getLong());
    }

    public static final boolean isShort(FirClass firClass) {
        firClass.getClass();
        return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(firClass), StandardClassIds.INSTANCE.getShort());
    }

    public static final boolean isBoolean(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getBoolean());
    }

    public static final boolean isByte(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getByte());
    }

    public static final boolean isChar(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getChar());
    }

    public static final boolean isDouble(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getDouble());
    }

    public static final boolean isFloat(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getFloat());
    }

    public static final boolean isInt(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getInt());
    }

    public static final boolean isLong(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getLong());
    }

    public static final boolean isShort(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return Intrinsics.areEqual(firClassSymbol.getClassId(), StandardClassIds.INSTANCE.getShort());
    }

    public static final boolean isPrimitiveType(FirClass firClass) {
        firClass.getClass();
        return isPrimitiveNumberOrUnsignedNumberType(firClass) || isBoolean(firClass) || isByte(firClass) || isShort(firClass) || isChar(firClass);
    }

    public static final boolean isPrimitiveType(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return isPrimitiveNumberOrUnsignedNumberType(firClassSymbol) || isBoolean(firClassSymbol) || isByte(firClassSymbol) || isShort(firClassSymbol) || isChar(firClassSymbol);
    }
}
