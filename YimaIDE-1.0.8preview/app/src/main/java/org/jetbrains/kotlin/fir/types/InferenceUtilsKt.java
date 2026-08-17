package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u0082\u0002\n\n\b\b\u0002\u001a\u0004\u0010\u0000(\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\t\u001a\u00020\u0007*\u00020\u0002\u001a\u001a\u0010\u0010\u001a\u00020\u0007*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002\"\u0017\u0010\n\u001a\u0004\u0018\u00010\u0002*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u0002*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rò\u0001\u0004\n\u00020\u0005¨\u0006\u0014"}, d2 = {"classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "isKProperty", Argument.Delimiters.none, "isKMutableProperty", "isKClassType", "returnType", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getReturnType", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "receiverType", "getReceiverType", "isTypeMismatchDueToNullability", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "actualType", "expectedType", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InferenceUtilsKt {
    private static final ClassId classId(ConeKotlinType coneKotlinType, FirSession firSession) {
        if (coneKotlinType instanceof ConeClassLikeType) {
            return TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) coneKotlinType, firSession, (Function1) null, 2, (Object) null).getLookupTag().getClassId();
        }
        return null;
    }

    public static final ConeKotlinType getReceiverType(FirAnonymousFunction firAnonymousFunction) {
        FirResolvedTypeRef typeRef;
        firAnonymousFunction.getClass();
        FirReceiverParameter receiverParameter = firAnonymousFunction.getReceiverParameter();
        if (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) {
            return null;
        }
        FirResolvedTypeRef firResolvedTypeRef = typeRef instanceof FirResolvedTypeRef ? typeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            return null;
        }
        return coneType;
    }

    public static final ConeKotlinType getReturnType(FirAnonymousFunction firAnonymousFunction) {
        firAnonymousFunction.getClass();
        FirResolvedTypeRef returnTypeRef = firAnonymousFunction.getReturnTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (coneType == null) {
            return null;
        }
        return coneType;
    }

    public static final boolean isKClassType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds.INSTANCE.getKClass());
    }

    public static final boolean isKMutableProperty(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ClassId classId = classId(coneKotlinType, firSession);
        if (classId != null && Intrinsics.areEqual(classId.getPackageFqName(), StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE())) {
            String identifier = classId.getShortClassName().getIdentifier();
            identifier.getClass();
            if (StringsKt.startsWith$default(identifier, "KMutableProperty", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isKProperty(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ClassId classId = classId(coneKotlinType, firSession);
        if (classId != null && Intrinsics.areEqual(classId.getPackageFqName(), StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE())) {
            String identifier = classId.getShortClassName().getIdentifier();
            identifier.getClass();
            if (StringsKt.startsWith$default(identifier, "KProperty", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isTypeMismatchDueToNullability(ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneTypeContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return coneTypeContext.isNullableType(coneKotlinType) && !coneTypeContext.isNullableType(coneKotlinType2) && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, coneTypeContext, coneKotlinType, TypeUtilsKt.withNullability$default(coneKotlinType2, true, coneTypeContext, null, false, 12, null), false, 8, (Object) null);
    }
}
