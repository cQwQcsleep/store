package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\n\u0010\r\"\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"custom", "Lorg/jetbrains/kotlin/fir/types/CustomAnnotationTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getCustom", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CustomAnnotationTypeAttribute;", "custom$delegate", "Lkotlin/properties/ReadOnlyProperty;", "customAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getCustomAnnotations", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/util/List;", "typeAnnotations", "getTypeAnnotations", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CustomAnnotationTypeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(CustomAnnotationTypeAttributeKt.class, "custom", "getCustom(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CustomAnnotationTypeAttribute;", 1)};
    private static final ReadOnlyProperty custom$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CustomAnnotationTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        custom$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final CustomAnnotationTypeAttribute getCustom(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (CustomAnnotationTypeAttribute) custom$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    public static final List<FirAnnotation> getCustomAnnotations(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        CustomAnnotationTypeAttribute custom = getCustom(coneAttributes);
        List<FirAnnotation> annotations = custom != null ? custom.getAnnotations() : null;
        return annotations == null ? CollectionsKt.emptyList() : annotations;
    }

    public static final List<FirAnnotation> getTypeAnnotations(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        List<FirAnnotation> customAnnotations = getCustomAnnotations(coneKotlinType);
        ParameterNameTypeAttribute parameterNameAttribute = ParameterNameTypeAttributeKt.getParameterNameAttribute(coneKotlinType.getAttributes());
        if (parameterNameAttribute == null) {
            return customAnnotations;
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(parameterNameAttribute.getAnnotations());
        listCreateListBuilder.addAll(customAnnotations);
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static final List<FirAnnotation> getCustomAnnotations(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getCustomAnnotations(coneKotlinType.getAttributes());
    }
}
