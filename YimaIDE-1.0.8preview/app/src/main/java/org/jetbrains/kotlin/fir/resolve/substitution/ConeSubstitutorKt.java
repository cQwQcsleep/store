package org.jetbrains.kotlin.fir.resolve.substitution;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007b\u0018\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¨\u0006\f"}, d2 = {"asCone", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "Lkotlin/Deprecated;", "message", "This call is redundant, please just drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", "substituteOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSubstitutorKt {
    public static final ConeSubstitutor asCone(TypeSubstitutorMarker typeSubstitutorMarker) {
        typeSubstitutorMarker.getClass();
        return (ConeSubstitutor) typeSubstitutorMarker;
    }

    public static final ConeKotlinType substituteOrNull(ConeSubstitutor coneSubstitutor, ConeKotlinType coneKotlinType) {
        coneSubstitutor.getClass();
        if (coneKotlinType != null) {
            return coneSubstitutor.substituteOrNull(coneKotlinType);
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call is redundant, please just drop it")
    public static final ConeSubstitutor asCone(ConeSubstitutor coneSubstitutor) {
        coneSubstitutor.getClass();
        return coneSubstitutor;
    }
}
