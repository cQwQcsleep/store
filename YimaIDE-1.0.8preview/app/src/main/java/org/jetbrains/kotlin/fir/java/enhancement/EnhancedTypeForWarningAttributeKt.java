package org.jetbrains.kotlin.fir.java.enhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\b\"\u0015\u0010\t\u001a\u00020\u0007*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u000e"}, d2 = {"enhancedTypeForWarning", "Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedTypeForWarningAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getEnhancedTypeForWarning", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedTypeForWarningAttribute;", "enhancedTypeForWarning$delegate", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "enhancedTypeForWarningOrSelf", "getEnhancedTypeForWarningOrSelf", "isEnhancedTypeForWarningDeprecation", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EnhancedTypeForWarningAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(EnhancedTypeForWarningAttributeKt.class, "enhancedTypeForWarning", "getEnhancedTypeForWarning(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedTypeForWarningAttribute;", 1)};
    private static final ReadOnlyProperty enhancedTypeForWarning$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(EnhancedTypeForWarningAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        enhancedTypeForWarning$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final ConeKotlinType getEnhancedTypeForWarning(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        EnhancedTypeForWarningAttribute enhancedTypeForWarning = getEnhancedTypeForWarning(coneKotlinType.getAttributes());
        if (enhancedTypeForWarning != null) {
            return enhancedTypeForWarning.getConeType();
        }
        return null;
    }

    public static final ConeKotlinType getEnhancedTypeForWarningOrSelf(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeKotlinType enhancedTypeForWarning = getEnhancedTypeForWarning(coneKotlinType);
        return enhancedTypeForWarning == null ? coneKotlinType : enhancedTypeForWarning;
    }

    public static final boolean isEnhancedTypeForWarningDeprecation(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        EnhancedTypeForWarningAttribute enhancedTypeForWarning = getEnhancedTypeForWarning(coneKotlinType.getAttributes());
        return enhancedTypeForWarning != null && enhancedTypeForWarning.isDeprecation();
    }

    public static final EnhancedTypeForWarningAttribute getEnhancedTypeForWarning(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (EnhancedTypeForWarningAttribute) enhancedTypeForWarning$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }
}
