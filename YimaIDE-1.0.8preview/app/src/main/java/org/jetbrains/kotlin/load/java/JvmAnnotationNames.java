package org.jetbrains.kotlin.load.java;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class JvmAnnotationNames {
    public static final Name DEFAULT_ANNOTATION_MEMBER_NAME;
    public static final FqName DEPRECATED_ANNOTATION;
    public static final FqName DOCUMENTED_ANNOTATION;
    public static final FqName ELEMENT_TYPE_ENUM;
    public static final FqName ENHANCED_MUTABILITY_ANNOTATION;
    public static final FqName ENHANCED_NULLABILITY_ANNOTATION;
    public static final FqName INHERITED_ANNOTATION;
    public static final FqName JETBRAINS_MUTABLE_ANNOTATION;
    public static final FqName JETBRAINS_NOT_NULL_ANNOTATION;
    public static final FqName JETBRAINS_NULLABLE_ANNOTATION;
    public static final FqName JETBRAINS_READONLY_ANNOTATION;
    public static final FqName JETBRAINS_UNMODIFIABLE_ANNOTATION;
    public static final FqName JETBRAINS_UNMODIFIABLE_VIEW_ANNOTATION;
    public static final String KIND_FIELD_NAME = "k";
    public static final FqName KOTLIN_JVM_INTERNAL;
    public static final String METADATA_DATA_FIELD_NAME = "d1";
    public static final String METADATA_DESC;
    public static final String METADATA_EXTRA_INT_FIELD_NAME = "xi";
    public static final String METADATA_EXTRA_STRING_FIELD_NAME = "xs";
    public static final int METADATA_FIR_FLAG = 64;
    public static final FqName METADATA_FQ_NAME;
    public static final int METADATA_JVM_IR_FLAG = 16;
    public static final int METADATA_JVM_IR_STABLE_ABI_FLAG = 32;
    public static final String METADATA_MULTIFILE_CLASS_NAME_FIELD_NAME = "xs";
    public static final int METADATA_MULTIFILE_PARTS_INHERIT_FLAG = 1;
    public static final String METADATA_PACKAGE_NAME_FIELD_NAME = "pn";
    public static final int METADATA_PRE_RELEASE_FLAG = 2;
    public static final int METADATA_PUBLIC_ABI_FLAG = 128;
    public static final int METADATA_SCRIPT_FLAG = 4;
    public static final int METADATA_STRICT_VERSION_SEMANTICS_FLAG = 8;
    public static final String METADATA_STRINGS_FIELD_NAME = "d2";
    public static final String METADATA_VERSION_FIELD_NAME = "mv";
    public static final FqName MUTABLE_ANNOTATION;
    public static final FqName OVERRIDE_ANNOTATION;
    public static final FqName PURELY_IMPLEMENTS_ANNOTATION;
    public static final FqName READONLY_ANNOTATION;
    public static final FqName REPEATABLE_ANNOTATION;
    public static final FqName RETENTION_ANNOTATION;
    public static final FqName RETENTION_POLICY_ENUM;
    public static final String SOURCE_DEBUG_EXTENSION_DESC = "Lkotlin/jvm/internal/SourceDebugExtension;";
    public static final FqName TARGET_ANNOTATION;

    static {
        FqName fqName = new FqName("kotlin.Metadata");
        METADATA_FQ_NAME = fqName;
        METADATA_DESC = "L" + JvmClassName.byFqNameWithoutInnerClasses(fqName).getInternalName() + ";";
        DEFAULT_ANNOTATION_MEMBER_NAME = Name.identifier("value");
        TARGET_ANNOTATION = new FqName(Target.class.getName());
        ELEMENT_TYPE_ENUM = new FqName(ElementType.class.getName());
        RETENTION_ANNOTATION = new FqName(Retention.class.getName());
        RETENTION_POLICY_ENUM = new FqName(RetentionPolicy.class.getName());
        DEPRECATED_ANNOTATION = new FqName(Deprecated.class.getName());
        DOCUMENTED_ANNOTATION = new FqName(Documented.class.getName());
        REPEATABLE_ANNOTATION = new FqName("java.lang.annotation.Repeatable");
        INHERITED_ANNOTATION = new FqName("java.lang.annotation.Inherited");
        OVERRIDE_ANNOTATION = new FqName(Override.class.getName());
        JETBRAINS_NOT_NULL_ANNOTATION = new FqName("org.jetbrains.annotations.NotNull");
        JETBRAINS_NULLABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Nullable");
        JETBRAINS_MUTABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Mutable");
        JETBRAINS_READONLY_ANNOTATION = new FqName("org.jetbrains.annotations.ReadOnly");
        JETBRAINS_UNMODIFIABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Unmodifiable");
        JETBRAINS_UNMODIFIABLE_VIEW_ANNOTATION = new FqName("org.jetbrains.annotations.UnmodifiableView");
        READONLY_ANNOTATION = new FqName("kotlin.annotations.jvm.ReadOnly");
        MUTABLE_ANNOTATION = new FqName("kotlin.annotations.jvm.Mutable");
        PURELY_IMPLEMENTS_ANNOTATION = new FqName("kotlin.jvm.PurelyImplements");
        KOTLIN_JVM_INTERNAL = new FqName("kotlin.jvm.internal");
        ENHANCED_NULLABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedNullability");
        ENHANCED_MUTABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedMutability");
    }

    private JvmAnnotationNames() {
    }
}
