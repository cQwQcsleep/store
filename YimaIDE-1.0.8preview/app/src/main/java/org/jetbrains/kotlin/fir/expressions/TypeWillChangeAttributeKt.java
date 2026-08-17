package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a)\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\b*\u0002H\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u0002H\f¢\u0006\u0002\u0010\u000e\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000f"}, d2 = {"typeWillChangeAttribute", "Lorg/jetbrains/kotlin/fir/expressions/TypeWillChangeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getTypeWillChangeAttribute", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/expressions/TypeWillChangeAttribute;", "typeWillChangeAttribute$delegate", "Lkotlin/properties/ReadOnlyProperty;", "typeChangeRelatedTo", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "withNewTypeSince", "T", "newType", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeWillChangeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(TypeWillChangeAttributeKt.class, "typeWillChangeAttribute", "getTypeWillChangeAttribute(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/expressions/TypeWillChangeAttribute;", 1)};
    private static final ReadOnlyProperty typeWillChangeAttribute$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(TypeWillChangeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        typeWillChangeAttribute$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final TypeWillChangeAttribute getTypeWillChangeAttribute(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (TypeWillChangeAttribute) typeWillChangeAttribute$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    public static final TypeWillChangeAttribute typeChangeRelatedTo(ConeKotlinType coneKotlinType, LanguageFeature languageFeature) {
        coneKotlinType.getClass();
        languageFeature.getClass();
        TypeWillChangeAttribute typeWillChangeAttribute = getTypeWillChangeAttribute(coneKotlinType.getAttributes());
        if (typeWillChangeAttribute == null || typeWillChangeAttribute.getLanguageFeature() != languageFeature) {
            return null;
        }
        return typeWillChangeAttribute;
    }

    public static final <T extends ConeKotlinType> T withNewTypeSince(T t, LanguageFeature languageFeature, T t2) {
        t.getClass();
        languageFeature.getClass();
        t2.getClass();
        return (T) TypeUtilsKt.withAttributes(t, t.getAttributes().add(new TypeWillChangeAttribute(t2, languageFeature)));
    }
}
