package org.jetbrains.kotlin.fir.types;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"f\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t8GX\u0087\u0004r6\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u001c\b\u0013\u0012\u0018\b\u000bB\u0014\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0006\b\u0017\u0012\u0002\b\f\u0012\n\b\u0018\u0012\u0006\b\n0\u00198\u001az\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\f¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"parameterNameAttribute", "Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getParameterNameAttribute", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute;", "parameterNameAttribute$delegate", "Lkotlin/properties/ReadOnlyProperty;", "parameterName", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "parameterNameDeprecated$annotations", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "parameterNameDeprecated", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "Lkotlin/Deprecated;", "message", "Parameter name access without a session is not supported.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "this.valueParameterName(session)", "imports", "level", "Lkotlin/DeprecationLevel;", "ERROR", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ParameterNameTypeAttributeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ParameterNameTypeAttributeKt.class, "parameterNameAttribute", "getParameterNameAttribute(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ParameterNameTypeAttribute;", 1)};
    private static final ReadOnlyProperty parameterNameAttribute$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(ParameterNameTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        parameterNameAttribute$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    public static final ParameterNameTypeAttribute getParameterNameAttribute(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (ParameterNameTypeAttribute) parameterNameAttribute$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    public static final Name parameterNameDeprecated(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        throw new UnsupportedOperationException("Parameter name access without a session is not supported.");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Parameter name access without a session is not supported.", replaceWith = @ReplaceWith(expression = "this.valueParameterName(session)", imports = {}))
    public static /* synthetic */ void parameterNameDeprecated$annotations(ConeKotlinType coneKotlinType) {
    }
}
