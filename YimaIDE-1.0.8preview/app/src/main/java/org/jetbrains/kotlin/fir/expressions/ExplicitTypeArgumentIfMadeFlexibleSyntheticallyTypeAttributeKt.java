package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"explicitTypeArgumentIfMadeFlexibleSynthetically", "Lorg/jetbrains/kotlin/fir/expressions/ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getExplicitTypeArgumentIfMadeFlexibleSynthetically", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/expressions/ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;", "explicitTypeArgumentIfMadeFlexibleSynthetically$delegate", "Lkotlin/properties/ReadOnlyProperty;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt.class, "explicitTypeArgumentIfMadeFlexibleSynthetically", "getExplicitTypeArgumentIfMadeFlexibleSynthetically(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/expressions/ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;", 1)};
    private static final ReadOnlyProperty explicitTypeArgumentIfMadeFlexibleSynthetically$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        explicitTypeArgumentIfMadeFlexibleSynthetically$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute getExplicitTypeArgumentIfMadeFlexibleSynthetically(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute) explicitTypeArgumentIfMadeFlexibleSynthetically$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }
}
