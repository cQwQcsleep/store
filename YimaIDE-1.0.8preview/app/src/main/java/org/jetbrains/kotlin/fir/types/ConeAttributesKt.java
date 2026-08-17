package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0086\bø\u0001\u0000*\"\u0010\u0006\"\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u00072\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\b0\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"transformOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeAttributeWithConeType;", "T", "transform", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "ConeAttributeKey", "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAttributesKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends ConeAttributeWithConeType<? extends T>> ConeAttributeWithConeType<T> transformOrNull(ConeAttributeWithConeType<? extends T> coneAttributeWithConeType, Function1<? super ConeKotlinType, ? extends ConeKotlinType> function1) {
        ConeKotlinType coneType;
        coneAttributeWithConeType.getClass();
        function1.getClass();
        ConeKotlinType coneKotlinType = (ConeKotlinType) function1.invoke(coneAttributeWithConeType.getConeType());
        if (coneKotlinType == null) {
            return null;
        }
        if (Intrinsics.areEqual(coneKotlinType, coneAttributeWithConeType.getConeType())) {
            return coneAttributeWithConeType;
        }
        ConeAttributeWithConeType coneAttributeWithConeType2 = (ConeAttributeWithConeType) coneKotlinType.getAttributes().get(coneAttributeWithConeType.getKey());
        if (coneAttributeWithConeType2 != null && (coneType = coneAttributeWithConeType2.getConeType()) != null) {
            coneKotlinType = coneType;
        }
        return coneAttributeWithConeType.copyWith(coneKotlinType);
    }
}
