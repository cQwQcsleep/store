package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"extractInputOutputTypesFromCallableReferenceExpectedType", "Lorg/jetbrains/kotlin/fir/resolve/calls/InputOutputTypes;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolutionAtomsKt {
    public static final InputOutputTypes extractInputOutputTypesFromCallableReferenceExpectedType(ConeKotlinType coneKotlinType, FirSession firSession) {
        firSession.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = coneKotlinType != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType) : null;
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType != null && FunctionalTypeUtilsKt.isSomeFunctionType(coneClassLikeType, firSession)) {
            return new InputOutputTypes(FunctionalTypeUtilsKt.valueParameterTypesIncludingReceiver(coneClassLikeType, firSession), FunctionalTypeUtilsKt.returnType(coneClassLikeType, firSession));
        }
        return null;
    }
}
