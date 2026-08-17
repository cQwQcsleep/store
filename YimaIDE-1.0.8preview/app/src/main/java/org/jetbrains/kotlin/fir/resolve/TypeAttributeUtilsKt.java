package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttribute;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"removeParameterNameAnnotation", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeAttributeUtilsKt {
    public static final ConeKotlinType removeParameterNameAnnotation(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().remove(ParameterNameTypeAttribute.Companion.getKEY()));
    }
}
