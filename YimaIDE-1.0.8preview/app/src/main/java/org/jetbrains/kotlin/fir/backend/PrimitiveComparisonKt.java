package org.jetbrains.kotlin.fir.backend;

import defpackage.f2f;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.PrimitivesKt;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000b\u001a)\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\f\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u001a\f\u0010\u0012\u001a\u00020\u0010*\u00020\u0010H\u0002\u001a\u001f\u0010\u0013\u001a\u0004\u0018\u00010\u0010*\u00020\u0010H\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u0014\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0015"}, d2 = {"left", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "getLeft", "(Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "right", "getRight", "inferPrimitiveNumericComparisonInfo", "Lorg/jetbrains/kotlin/fir/backend/PrimitiveConeNumericComparisonInfo;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;)Lorg/jetbrains/kotlin/fir/backend/PrimitiveConeNumericComparisonInfo;", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/backend/PrimitiveConeNumericComparisonInfo;", "leastCommonPrimitiveNumericType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "t1", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "t2", "promoteIntegerTypeToIntIfRequired", "getPrimitiveTypeOrSupertype", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PrimitiveComparisonKt {
    public static final FirExpression getLeft(FirComparisonExpression firComparisonExpression) {
        firComparisonExpression.getClass();
        FirExpression explicitReceiver = firComparisonExpression.getCompareToCall().getExplicitReceiver();
        if (explicitReceiver != null) {
            return explicitReceiver;
        }
        f2f.a("There should be an explicit receiver for ", UtilsKt.render(firComparisonExpression.getCompareToCall()));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeKotlinType getPrimitiveTypeOrSupertype(Fir2IrComponents fir2IrComponents, ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeTypeParameterType) {
            Iterator<T> it = ((FirTypeParameter) ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getFir()).getBounds().iterator();
            while (it.hasNext()) {
                ConeKotlinType primitiveTypeOrSupertype = getPrimitiveTypeOrSupertype(fir2IrComponents, FirTypeUtilsKt.getConeType((FirTypeRef) it.next()));
                if (primitiveTypeOrSupertype != null) {
                    return primitiveTypeOrSupertype;
                }
            }
            return null;
        }
        if ((coneKotlinType instanceof ConeClassLikeType) && PrimitivesKt.isPrimitiveNumberType((ConeClassLikeType) coneKotlinType)) {
            return coneKotlinType;
        }
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            if (coneKotlinType instanceof ConeCapturedType) {
                return getPrimitiveTypeOrSupertype(fir2IrComponents, Fir2IrTypeConverterKt.approximateForIrOrSelf(fir2IrComponents, coneKotlinType));
            }
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        ConeRigidType lowerBound = coneFlexibleType.getLowerBound();
        ConeClassLikeType coneClassLikeType = lowerBound instanceof ConeClassLikeType ? (ConeClassLikeType) lowerBound : null;
        return (coneClassLikeType == null || !PrimitivesKt.isPrimitiveNumberType(coneClassLikeType)) ? getPrimitiveTypeOrSupertype(fir2IrComponents, coneFlexibleType.getLowerBound()) : coneKotlinType;
    }

    public static final FirExpression getRight(FirComparisonExpression firComparisonExpression) {
        firComparisonExpression.getClass();
        FirExpression firExpression = (FirExpression) CollectionsKt.getOrNull(firComparisonExpression.getCompareToCall().getArgumentList().getArguments(), 0);
        if (firExpression != null) {
            return firExpression;
        }
        f2f.a("There should be a first arg for ", UtilsKt.render(firComparisonExpression.getCompareToCall()));
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final PrimitiveConeNumericComparisonInfo inferPrimitiveNumericComparisonInfo(Fir2IrComponents fir2IrComponents, FirExpression firExpression, FirExpression firExpression2) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType primitiveTypeOrSupertype;
        fir2IrComponents.getClass();
        firExpression.getClass();
        firExpression2.getClass();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        ConeKotlinType resolvedType2 = FirTypeUtilsKt.getResolvedType(firExpression2);
        ConeKotlinType primitiveTypeOrSupertype2 = getPrimitiveTypeOrSupertype(fir2IrComponents, resolvedType);
        if (primitiveTypeOrSupertype2 == null || (primitiveTypeOrSupertype = getPrimitiveTypeOrSupertype(fir2IrComponents, resolvedType2)) == null) {
            return null;
        }
        return new PrimitiveConeNumericComparisonInfo(leastCommonPrimitiveNumericType(primitiveTypeOrSupertype2, primitiveTypeOrSupertype), primitiveTypeOrSupertype2, primitiveTypeOrSupertype);
    }

    private static final ConeClassLikeType leastCommonPrimitiveNumericType(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(promoteIntegerTypeToIntIfRequired(coneKotlinType));
        ConeRigidType coneRigidTypeLowerBoundIfFlexible2 = ConeTypeUtilsKt.lowerBoundIfFlexible(promoteIntegerTypeToIntIfRequired(coneKotlinType2));
        if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType) || !(coneRigidTypeLowerBoundIfFlexible2 instanceof ConeClassLikeType)) {
            a11.a("Unexpected types: t1=", coneKotlinType, ", t2=", coneKotlinType2);
            return null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible;
        if (!PrimitivesKt.isDouble(coneClassLikeType)) {
            ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible2;
            if (!PrimitivesKt.isDouble(coneClassLikeType2)) {
                if (PrimitivesKt.isFloat(coneClassLikeType) || PrimitivesKt.isFloat(coneClassLikeType2)) {
                    return StandardTypes.INSTANCE.getFloat();
                }
                if (PrimitivesKt.isLong(coneClassLikeType) || PrimitivesKt.isLong(coneClassLikeType2)) {
                    return StandardTypes.INSTANCE.getLong();
                }
                if (PrimitivesKt.isInt(coneClassLikeType) || PrimitivesKt.isInt(coneClassLikeType2)) {
                    return StandardTypes.INSTANCE.getInt();
                }
                a11.a("Unexpected types: t1=", coneKotlinType, ", t2=", coneKotlinType2);
                return null;
            }
        }
        return StandardTypes.INSTANCE.getDouble();
    }

    private static final ConeKotlinType promoteIntegerTypeToIntIfRequired(ConeKotlinType coneKotlinType) {
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinType);
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getByte()) || Intrinsics.areEqual(classId, standardClassIds.getShort())) {
            return StandardTypes.INSTANCE.getInt();
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getLong()) || Intrinsics.areEqual(classId, standardClassIds.getInt()) || Intrinsics.areEqual(classId, standardClassIds.getFloat()) || Intrinsics.areEqual(classId, standardClassIds.getDouble()) || Intrinsics.areEqual(classId, standardClassIds.getChar())) {
            return coneKotlinType;
        }
        w04.a("Primitive number type expected: ", coneKotlinType);
        return null;
    }

    public static final PrimitiveConeNumericComparisonInfo inferPrimitiveNumericComparisonInfo(Fir2IrComponents fir2IrComponents, FirComparisonExpression firComparisonExpression) {
        fir2IrComponents.getClass();
        firComparisonExpression.getClass();
        return inferPrimitiveNumericComparisonInfo(fir2IrComponents, getLeft(firComparisonExpression), getRight(firComparisonExpression));
    }
}
