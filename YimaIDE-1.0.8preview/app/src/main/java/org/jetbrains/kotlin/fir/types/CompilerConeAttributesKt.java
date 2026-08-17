package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\t\u0010\n\"!\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0006\u001a\u0004\b\u000e\u0010\u000f\"!\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0013\u0010\u0014\"!\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0015\u0010\u001b\u001a\u00020\u001c*\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0015\u0010 \u001a\u00020\u001c*\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b \u0010\u001f\"\u0015\u0010!\u001a\u00020\u001c*\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001f\"\u0015\u0010#\u001a\u00020\u001c*\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b$\u0010\u001f\"\u0015\u0010%\u001a\u00020&*\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0015\u0010%\u001a\u00020&*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010)¨\u0006*"}, d2 = {"exact", "Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$Exact;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getExact", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$Exact;", "exact$delegate", "Lkotlin/properties/ReadOnlyProperty;", "noInfer", "Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$NoInfer;", "getNoInfer", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$NoInfer;", "noInfer$delegate", "enhancedNullability", "Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$EnhancedNullability;", "getEnhancedNullability", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$EnhancedNullability;", "enhancedNullability$delegate", "extensionFunctionType", "Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ExtensionFunctionType;", "getExtensionFunctionType", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ExtensionFunctionType;", "extensionFunctionType$delegate", "contextFunctionTypeParams", "Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ContextFunctionTypeParams;", "getContextFunctionTypeParams", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ContextFunctionTypeParams;", "contextFunctionTypeParams$delegate", "hasEnhancedNullability", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getHasEnhancedNullability", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isExtensionFunctionType", "hasNoInfer", "getHasNoInfer", "hasContextParameters", "getHasContextParameters", "contextParameterNumberForFunctionType", Argument.Delimiters.none, "getContextParameterNumberForFunctionType", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)I", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)I", "org.jetbrains.kotlin:cones"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerConeAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(CompilerConeAttributesKt.class, "exact", "getExact(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$Exact;", 1), new PropertyReference1Impl<>(CompilerConeAttributesKt.class, "noInfer", "getNoInfer(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$NoInfer;", 1), new PropertyReference1Impl<>(CompilerConeAttributesKt.class, "enhancedNullability", "getEnhancedNullability(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$EnhancedNullability;", 1), new PropertyReference1Impl<>(CompilerConeAttributesKt.class, "extensionFunctionType", "getExtensionFunctionType(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ExtensionFunctionType;", 1), new PropertyReference1Impl<>(CompilerConeAttributesKt.class, "contextFunctionTypeParams", "getContextFunctionTypeParams(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/CompilerConeAttributes$ContextFunctionTypeParams;", 1)};
    private static final ReadOnlyProperty contextFunctionTypeParams$delegate;
    private static final ReadOnlyProperty enhancedNullability$delegate;
    private static final ReadOnlyProperty exact$delegate;
    private static final ReadOnlyProperty extensionFunctionType$delegate;
    private static final ReadOnlyProperty noInfer$delegate;

    static {
        ConeAttributes.Companion companion = ConeAttributes.INSTANCE;
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.Exact.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        exact$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor2 = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.NoInfer.class));
        nullableArrayMapAccessorGenerateNullableAccessor2.getClass();
        noInfer$delegate = nullableArrayMapAccessorGenerateNullableAccessor2;
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor3 = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.EnhancedNullability.class));
        nullableArrayMapAccessorGenerateNullableAccessor3.getClass();
        enhancedNullability$delegate = nullableArrayMapAccessorGenerateNullableAccessor3;
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor4 = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.ExtensionFunctionType.class));
        nullableArrayMapAccessorGenerateNullableAccessor4.getClass();
        extensionFunctionType$delegate = nullableArrayMapAccessorGenerateNullableAccessor4;
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor5 = companion.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.ContextFunctionTypeParams.class));
        nullableArrayMapAccessorGenerateNullableAccessor5.getClass();
        contextFunctionTypeParams$delegate = nullableArrayMapAccessorGenerateNullableAccessor5;
    }

    private static final CompilerConeAttributes.ContextFunctionTypeParams getContextFunctionTypeParams(ConeAttributes coneAttributes) {
        return (CompilerConeAttributes.ContextFunctionTypeParams) contextFunctionTypeParams$delegate.getValue(coneAttributes, $$delegatedProperties[4]);
    }

    public static final int getContextParameterNumberForFunctionType(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        CompilerConeAttributes.ContextFunctionTypeParams contextFunctionTypeParams = getContextFunctionTypeParams(coneAttributes);
        if (contextFunctionTypeParams != null) {
            return contextFunctionTypeParams.getContextParameterNumber();
        }
        return 0;
    }

    public static final CompilerConeAttributes.EnhancedNullability getEnhancedNullability(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (CompilerConeAttributes.EnhancedNullability) enhancedNullability$delegate.getValue(coneAttributes, $$delegatedProperties[2]);
    }

    public static final CompilerConeAttributes.Exact getExact(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (CompilerConeAttributes.Exact) exact$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }

    public static final CompilerConeAttributes.ExtensionFunctionType getExtensionFunctionType(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (CompilerConeAttributes.ExtensionFunctionType) extensionFunctionType$delegate.getValue(coneAttributes, $$delegatedProperties[3]);
    }

    public static final boolean getHasContextParameters(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getContextParameterNumberForFunctionType(coneKotlinType.getAttributes()) > 0;
    }

    public static final boolean getHasEnhancedNullability(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getEnhancedNullability(coneKotlinType.getAttributes()) != null;
    }

    public static final boolean getHasNoInfer(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getNoInfer(coneKotlinType.getAttributes()) != null;
    }

    public static final CompilerConeAttributes.NoInfer getNoInfer(ConeAttributes coneAttributes) {
        coneAttributes.getClass();
        return (CompilerConeAttributes.NoInfer) noInfer$delegate.getValue(coneAttributes, $$delegatedProperties[1]);
    }

    public static final boolean isExtensionFunctionType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getExtensionFunctionType(coneKotlinType.getAttributes()) != null;
    }

    public static final int getContextParameterNumberForFunctionType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return getContextParameterNumberForFunctionType(coneKotlinType.getAttributes());
    }
}
