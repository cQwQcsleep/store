package org.jetbrains.kotlin.fir.resolve.substitution;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeCapturedTypeConstructor;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0086\bø\u0001\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"substitute", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "f", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "wrapProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "old", "newType", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SubstitutionUtilitiesKt {
    public static final ConeCapturedType substitute(ConeCapturedType coneCapturedType, Function1<? super ConeKotlinType, ? extends ConeKotlinType> function1) {
        ConeKotlinType coneKotlinType;
        ArrayList arrayList;
        coneCapturedType.getClass();
        function1.getClass();
        ConeKotlinType lowerType = coneCapturedType.getConstructor().getLowerType();
        if (lowerType == null) {
            lowerType = ConeTypeProjectionKt.getType(coneCapturedType.getConstructor().getProjection());
        }
        if (lowerType == null || (coneKotlinType = (ConeKotlinType) function1.invoke(lowerType)) == null) {
            return null;
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            return (ConeCapturedType) coneKotlinType;
        }
        List<ConeKotlinType> supertypes = coneCapturedType.getConstructor().getSupertypes();
        if (supertypes != null) {
            List<ConeKotlinType> list = supertypes;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (ConeKotlinType coneKotlinType2 : list) {
                ConeKotlinType coneKotlinType3 = (ConeKotlinType) function1.invoke(coneKotlinType2);
                if (coneKotlinType3 != null) {
                    coneKotlinType2 = coneKotlinType3;
                }
                arrayList2.add(coneKotlinType2);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return ConeCapturedType.copy$default(coneCapturedType, false, new ConeCapturedTypeConstructor(wrapProjection(coneCapturedType.getConstructor().getProjection(), coneKotlinType), coneCapturedType.getConstructor().getLowerType() != null ? coneKotlinType : null, coneCapturedType.getConstructor().getCaptureStatus(), arrayList, coneCapturedType.getConstructor().getTypeParameterMarker()), null, 5, null);
    }

    public static final ConeTypeProjection wrapProjection(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType) {
        coneTypeProjection.getClass();
        coneKotlinType.getClass();
        if (coneTypeProjection instanceof ConeStarProjection) {
            return coneTypeProjection;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            return new ConeKotlinTypeProjectionIn(coneKotlinType);
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            return new ConeKotlinTypeProjectionOut(coneKotlinType);
        }
        if (coneTypeProjection instanceof ConeKotlinTypeConflictingProjection) {
            return new ConeKotlinTypeConflictingProjection(coneKotlinType);
        }
        if (coneTypeProjection instanceof ConeKotlinType) {
            return coneKotlinType;
        }
        bu8.a();
        return null;
    }
}
