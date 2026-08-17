package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\b\"\u0015\u0010\t\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u000e"}, d2 = {"abbreviatedType", "Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getAbbreviatedType", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;", "abbreviatedType$delegate", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "abbreviatedTypeOrSelf", "getAbbreviatedTypeOrSelf", "isTypealiasExpansion", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AbbreviatedTypeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(AbbreviatedTypeAttributeKt.class, "abbreviatedType", "getAbbreviatedType(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;", 1)};
    private static final ReadOnlyProperty abbreviatedType$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(AbbreviatedTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        abbreviatedType$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final ConeKotlinType getAbbreviatedType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        AbbreviatedTypeAttribute abbreviatedType = getAbbreviatedType(coneKotlinType.getAttributes());
        if (abbreviatedType != null) {
            return abbreviatedType.getConeType();
        }
        return null;
    }

    public static final ConeKotlinType getAbbreviatedTypeOrSelf(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeKotlinType abbreviatedType = getAbbreviatedType(coneKotlinType);
        return abbreviatedType == null ? coneKotlinType : abbreviatedType;
    }

    public static final boolean isTypealiasExpansion(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return !Intrinsics.areEqual(coneKotlinType, getAbbreviatedTypeOrSelf(coneKotlinType));
    }

    public static final AbbreviatedTypeAttribute getAbbreviatedType(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (AbbreviatedTypeAttribute) abbreviatedType$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }
}
