package org.jetbrains.kotlin.fir.types;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007b\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b¨\u0006\t"}, d2 = {"asCone", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "Lkotlin/Deprecated;", "message", "This call is redundant, please just drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeVariableAndStubTypesKt {
    public static final ConeTypeVariable asCone(TypeVariableMarker typeVariableMarker) {
        typeVariableMarker.getClass();
        return (ConeTypeVariable) typeVariableMarker;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call is redundant, please just drop it")
    public static final ConeTypeVariable asCone(ConeTypeVariable coneTypeVariable) {
        coneTypeVariable.getClass();
        return coneTypeVariable;
    }
}
