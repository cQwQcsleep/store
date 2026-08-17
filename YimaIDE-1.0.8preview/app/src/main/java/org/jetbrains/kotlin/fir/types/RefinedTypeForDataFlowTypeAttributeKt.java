package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"refinedTypeForDataFlow", "Lorg/jetbrains/kotlin/fir/types/RefinedTypeForDataFlowTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getRefinedTypeForDataFlow", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/RefinedTypeForDataFlowTypeAttribute;", "refinedTypeForDataFlow$delegate", "Lkotlin/properties/ReadOnlyProperty;", "refinedTypeForDataFlowOrSelf", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getRefinedTypeForDataFlowOrSelf", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RefinedTypeForDataFlowTypeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(RefinedTypeForDataFlowTypeAttributeKt.class, "refinedTypeForDataFlow", "getRefinedTypeForDataFlow(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/RefinedTypeForDataFlowTypeAttribute;", 1)};
    private static final ReadOnlyProperty refinedTypeForDataFlow$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(RefinedTypeForDataFlowTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        refinedTypeForDataFlow$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final RefinedTypeForDataFlowTypeAttribute getRefinedTypeForDataFlow(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (RefinedTypeForDataFlowTypeAttribute) refinedTypeForDataFlow$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    public static final ConeKotlinType getRefinedTypeForDataFlowOrSelf(ConeKotlinType coneKotlinType) {
        ConeKotlinType coneType;
        coneKotlinType.getClass();
        RefinedTypeForDataFlowTypeAttribute refinedTypeForDataFlow = getRefinedTypeForDataFlow(coneKotlinType.getAttributes());
        return (refinedTypeForDataFlow == null || (coneType = refinedTypeForDataFlow.getConeType()) == null) ? coneKotlinType : coneType;
    }
}
