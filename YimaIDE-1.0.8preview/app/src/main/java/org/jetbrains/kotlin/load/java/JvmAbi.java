package org.jetbrains.kotlin.load.java;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0007J\u0010\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0007J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0007J\u0010\u0010)\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0007J\u0010\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0007J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0007J\u0010\u0010-\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0007J\u0010\u0010.\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001a¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JvmAbi;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DEFAULT_IMPLS_CLASS_NAME", "", "ERASED_INLINE_CONSTRUCTOR_NAME", "JVM_FIELD_ANNOTATION_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "JVM_FIELD_ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "DEFAULT_IMPLS_SUFFIX", "DEFAULT_PARAMS_IMPL_SUFFIX", "GET_PREFIX", "IS_PREFIX", "SET_PREFIX", "DELEGATED_PROPERTY_NAME_SUFFIX", "DELEGATED_PROPERTIES_ARRAY_NAME", "DELEGATE_SUPER_FIELD_PREFIX", "ANNOTATIONS_SUFFIX", "ANNOTATED_PROPERTY_METHOD_NAME_SUFFIX", "ANNOTATED_TYPEALIAS_METHOD_NAME_SUFFIX", "INSTANCE_FIELD", "HIDDEN_INSTANCE_FIELD", "REFLECTION_FACTORY_IMPL", "getREFLECTION_FACTORY_IMPL", "()Lorg/jetbrains/kotlin/name/ClassId;", "LOCAL_VARIABLE_NAME_PREFIX_INLINE_ARGUMENT", "LOCAL_VARIABLE_NAME_PREFIX_INLINE_FUNCTION", "IMPL_SUFFIX_FOR_INLINE_CLASS_MEMBERS", "REPEATABLE_ANNOTATION_CONTAINER_NAME", "REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION", "getREPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION", "getSyntheticMethodNameForAnnotatedProperty", "baseName", "getSyntheticMethodNameForAnnotatedTypeAlias", "typeAliasName", "Lorg/jetbrains/kotlin/name/Name;", "isGetterName", "", "name", "isSetterName", "getterName", "propertyName", "setterName", "startsWithIsPrefix", "isFakeLocalVariableForInline", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JvmAbi {
    public static final String ANNOTATED_PROPERTY_METHOD_NAME_SUFFIX = "$annotations";
    public static final String DEFAULT_IMPLS_CLASS_NAME = "DefaultImpls";
    public static final String DEFAULT_IMPLS_SUFFIX = "$DefaultImpls";
    public static final String DEFAULT_PARAMS_IMPL_SUFFIX = "$default";
    public static final String DELEGATED_PROPERTIES_ARRAY_NAME = "$$delegatedProperties";
    public static final String DELEGATED_PROPERTY_NAME_SUFFIX = "$delegate";
    public static final String DELEGATE_SUPER_FIELD_PREFIX = "$$delegate_";
    public static final String ERASED_INLINE_CONSTRUCTOR_NAME = "constructor";
    public static final String HIDDEN_INSTANCE_FIELD = "$$INSTANCE";
    public static final String IMPL_SUFFIX_FOR_INLINE_CLASS_MEMBERS = "-impl";
    public static final JvmAbi INSTANCE = new JvmAbi();
    public static final String INSTANCE_FIELD = "INSTANCE";
    public static final ClassId JVM_FIELD_ANNOTATION_CLASS_ID;
    public static final FqName JVM_FIELD_ANNOTATION_FQ_NAME;
    public static final String LOCAL_VARIABLE_NAME_PREFIX_INLINE_ARGUMENT = "$i$a$";
    public static final String LOCAL_VARIABLE_NAME_PREFIX_INLINE_FUNCTION = "$i$f$";
    private static final ClassId REFLECTION_FACTORY_IMPL;
    private static final ClassId REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION;
    public static final String REPEATABLE_ANNOTATION_CONTAINER_NAME = "Container";

    static {
        FqName fqName = new FqName("kotlin.jvm.JvmField");
        JVM_FIELD_ANNOTATION_FQ_NAME = fqName;
        ClassId.Companion companion = ClassId.Companion;
        JVM_FIELD_ANNOTATION_CLASS_ID = companion.topLevel(fqName);
        REFLECTION_FACTORY_IMPL = companion.topLevel(new FqName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
        REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION = ClassId.Companion.fromString$default(companion, "kotlin/jvm/internal/RepeatableContainer", false, 2, (Object) null);
    }

    private JvmAbi() {
    }

    @JvmStatic
    public static final String getSyntheticMethodNameForAnnotatedProperty(String baseName) {
        baseName.getClass();
        return baseName + ANNOTATED_PROPERTY_METHOD_NAME_SUFFIX;
    }

    @JvmStatic
    public static final String getSyntheticMethodNameForAnnotatedTypeAlias(Name typeAliasName) {
        typeAliasName.getClass();
        return typeAliasName.asString() + ANNOTATED_PROPERTY_METHOD_NAME_SUFFIX;
    }

    @JvmStatic
    public static final String getterName(String propertyName) {
        propertyName.getClass();
        if (startsWithIsPrefix(propertyName)) {
            return propertyName;
        }
        return "get" + CapitalizeDecapitalizeKt.capitalizeAsciiOnly(propertyName);
    }

    @JvmStatic
    public static final boolean isFakeLocalVariableForInline(String name) {
        name.getClass();
        return StringsKt.startsWith$default(name, LOCAL_VARIABLE_NAME_PREFIX_INLINE_FUNCTION, false, 2, (Object) null) || StringsKt.startsWith$default(name, LOCAL_VARIABLE_NAME_PREFIX_INLINE_ARGUMENT, false, 2, (Object) null);
    }

    @JvmStatic
    public static final boolean isGetterName(String name) {
        name.getClass();
        return StringsKt.startsWith$default(name, "get", false, 2, (Object) null) || StringsKt.startsWith$default(name, "is", false, 2, (Object) null);
    }

    @JvmStatic
    public static final boolean isSetterName(String name) {
        name.getClass();
        return StringsKt.startsWith$default(name, "set", false, 2, (Object) null);
    }

    @JvmStatic
    public static final String setterName(String propertyName) {
        propertyName.getClass();
        StringBuilder sb = new StringBuilder("set");
        sb.append(startsWithIsPrefix(propertyName) ? propertyName.substring(2) : CapitalizeDecapitalizeKt.capitalizeAsciiOnly(propertyName));
        return sb.toString();
    }

    @JvmStatic
    public static final boolean startsWithIsPrefix(String name) {
        name.getClass();
        if (!StringsKt.startsWith$default(name, "is", false, 2, (Object) null) || name.length() == 2) {
            return false;
        }
        char cCharAt = name.charAt(2);
        return 'a' > cCharAt || cCharAt > 'z';
    }

    public final ClassId getREFLECTION_FACTORY_IMPL() {
        return REFLECTION_FACTORY_IMPL;
    }

    public final ClassId getREPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION() {
        return REPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION;
    }
}
