package org.jetbrains.kotlin.fir.types;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007b\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u001a\u0014\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\n\u001a\u0012\u0010\u0010\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0011\u001a\u00020\n\"\u0017\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f\"\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"asCone", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "Lkotlin/Deprecated;", "message", "This call is redundant, please just drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getType", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isStarProjection", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Z", "replaceType", "newType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjection;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Lorg/jetbrains/kotlin/types/Variance;", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeProjectionKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProjectionKind.INVARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ConeTypeProjection asCone(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return (ConeTypeProjection) typeArgumentMarker;
    }

    public static final ConeKotlinType getType(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        if (Intrinsics.areEqual(coneTypeProjection, ConeStarProjection.INSTANCE)) {
            return null;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjection) {
            return ((ConeKotlinTypeProjection) coneTypeProjection).getType();
        }
        bu8.a();
        return null;
    }

    public static final Variance getVariance(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[coneTypeProjection.getKind().ordinal()];
        if (i == 1) {
            return Variance.OUT_VARIANCE;
        }
        if (i == 2) {
            return Variance.IN_VARIANCE;
        }
        if (i == 3) {
            return Variance.OUT_VARIANCE;
        }
        if (i == 4) {
            return Variance.INVARIANT;
        }
        bu8.a();
        return null;
    }

    public static final boolean isStarProjection(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        return Intrinsics.areEqual(coneTypeProjection, ConeStarProjection.INSTANCE);
    }

    public static final ConeKotlinTypeProjection replaceType(ConeKotlinTypeProjection coneKotlinTypeProjection, ConeKotlinType coneKotlinType) {
        coneKotlinTypeProjection.getClass();
        coneKotlinType.getClass();
        if (coneKotlinType == coneKotlinTypeProjection.getType()) {
            return coneKotlinTypeProjection;
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinType) {
            return coneKotlinType;
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            return new ConeKotlinTypeProjectionIn(coneKotlinType);
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            return new ConeKotlinTypeProjectionOut(coneKotlinType);
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeConflictingProjection) {
            return new ConeKotlinTypeConflictingProjection(coneKotlinType);
        }
        bu8.a();
        return null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call is redundant, please just drop it")
    public static final ConeTypeProjection asCone(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        return coneTypeProjection;
    }

    public static final ConeTypeProjection replaceType(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType) {
        coneTypeProjection.getClass();
        if (coneTypeProjection instanceof ConeStarProjection) {
            return coneTypeProjection;
        }
        if (!(coneTypeProjection instanceof ConeKotlinTypeProjection)) {
            bu8.a();
            return null;
        }
        if (coneKotlinType != null) {
            return replaceType((ConeKotlinTypeProjection) coneTypeProjection, coneKotlinType);
        }
        w01.a("Type for non star projection should be not null");
        return null;
    }
}
