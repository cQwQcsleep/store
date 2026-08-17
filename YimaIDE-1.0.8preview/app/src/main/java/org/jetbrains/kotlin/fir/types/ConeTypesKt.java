package org.jetbrains.kotlin.fir.types;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007b\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u001a\r\u0010\u0000\u001a\u00020\t*\u00020\nH\u0086\b\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\tH\u0002\u001a\n\u0010\r\u001a\u00020\f*\u00020\u0001¨\u0006\u000e"}, d2 = {"asCone", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lkotlin/Deprecated;", "message", "This call is redundant, please just drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "unwrapDefinitelyNotNull", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "unwrapToSimpleTypeUsingLowerBound", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypesKt {
    public static final ConeKotlinType asCone(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return (ConeKotlinType) kotlinTypeMarker;
    }

    private static final ConeSimpleKotlinType unwrapDefinitelyNotNull(ConeRigidType coneRigidType) {
        if (coneRigidType instanceof ConeDefinitelyNotNullType) {
            return ((ConeDefinitelyNotNullType) coneRigidType).getOriginal();
        }
        if (coneRigidType instanceof ConeSimpleKotlinType) {
            return (ConeSimpleKotlinType) coneRigidType;
        }
        bu8.a();
        return null;
    }

    public static final ConeSimpleKotlinType unwrapToSimpleTypeUsingLowerBound(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return unwrapDefinitelyNotNull(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call is redundant, please just drop it")
    public static final ConeKotlinType asCone(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType;
    }

    public static final ConeRigidType asCone(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return (ConeRigidType) rigidTypeMarker;
    }
}
