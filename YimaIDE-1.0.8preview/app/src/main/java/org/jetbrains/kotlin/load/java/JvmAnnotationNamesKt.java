package org.jetbrains.kotlin.load.java;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\"\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u0005\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0003\"\u0011\u0010\u0012\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0003\"\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0003\"\u0011\u0010\u0016\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0003\"\u0011\u0010\u0018\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0003\"\u0011\u0010\u001a\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0003\"\u0011\u0010\u001c\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0003\"\u0011\u0010\u001e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0003\"\u0011\u0010 \u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0003\"\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%\"\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%\"\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%\"\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%\"\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%\"\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b/\u0010%\"\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\b\n\u0000\u001a\u0004\b1\u0010%\"\u001d\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000103¢\u0006\b\n\u0000\u001a\u0004\b4\u00105\"\u0011\u00106\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0003¨\u00068"}, d2 = {"JSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getJSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "JSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME", "getJSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME", "JSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME", "getJSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME", "JSPECIFY_NON_NULL_ANNOTATION_FQ_NAME", "getJSPECIFY_NON_NULL_ANNOTATION_FQ_NAME", "JSPECIFY_NULLABLE_ANNOTATION_FQ_NAME", "getJSPECIFY_NULLABLE_ANNOTATION_FQ_NAME", "JSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME", "getJSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME", "JSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME", "getJSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME", "JSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME", "getJSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME", "JAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME", "getJAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME", "JAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME", "getJAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME", "JAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME", "getJAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME", "JAVAX_NONNULL_ANNOTATION_FQ_NAME", "getJAVAX_NONNULL_ANNOTATION_FQ_NAME", "JAVAX_NULLABLE_ANNOTATION_FQ_NAME", "getJAVAX_NULLABLE_ANNOTATION_FQ_NAME", "JAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME", "getJAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME", "JAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME", "getJAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME", "JAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME", "getJAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME", "BUILT_IN_TYPE_QUALIFIER_ANNOTATIONS", "", "getBUILT_IN_TYPE_QUALIFIER_ANNOTATIONS", "()Ljava/util/Set;", "NOT_NULL_ANNOTATIONS", "getNOT_NULL_ANNOTATIONS", "NULLABLE_ANNOTATIONS", "getNULLABLE_ANNOTATIONS", "FORCE_FLEXIBILITY_ANNOTATIONS", "getFORCE_FLEXIBILITY_ANNOTATIONS", "NULLABILITY_ANNOTATIONS", "getNULLABILITY_ANNOTATIONS", "READ_ONLY_ANNOTATIONS", "getREAD_ONLY_ANNOTATIONS", "MUTABLE_ANNOTATIONS", "getMUTABLE_ANNOTATIONS", "javaToKotlinNameMap", "", "getJavaToKotlinNameMap", "()Ljava/util/Map;", "UNDER_MIGRATION_ANNOTATION_FQ_NAME", "getUNDER_MIGRATION_ANNOTATION_FQ_NAME", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class JvmAnnotationNamesKt {
    private static final Set<FqName> BUILT_IN_TYPE_QUALIFIER_ANNOTATIONS;
    private static final Set<FqName> FORCE_FLEXIBILITY_ANNOTATIONS;
    private static final FqName JAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_NONNULL_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_NULLABLE_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME;
    private static final FqName JAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_NON_NULL_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_NULLABLE_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME;
    private static final FqName JSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME;
    private static final Set<FqName> MUTABLE_ANNOTATIONS;
    private static final Set<FqName> NOT_NULL_ANNOTATIONS;
    private static final Set<FqName> NULLABILITY_ANNOTATIONS;
    private static final Set<FqName> NULLABLE_ANNOTATIONS;
    private static final Set<FqName> READ_ONLY_ANNOTATIONS;
    private static final FqName UNDER_MIGRATION_ANNOTATION_FQ_NAME;
    private static final Map<FqName, FqName> javaToKotlinNameMap;

    static {
        FqName fqName = new FqName("org.jspecify.nullness.Nullable");
        JSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME = fqName;
        FqName fqName2 = new FqName("org.jspecify.nullness.NullMarked");
        JSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME = fqName2;
        FqName fqName3 = new FqName("org.jspecify.nullness.NullnessUnspecified");
        JSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME = fqName3;
        FqName fqName4 = new FqName("org.jspecify.annotations.NonNull");
        JSPECIFY_NON_NULL_ANNOTATION_FQ_NAME = fqName4;
        FqName fqName5 = new FqName("org.jspecify.annotations.Nullable");
        JSPECIFY_NULLABLE_ANNOTATION_FQ_NAME = fqName5;
        FqName fqName6 = new FqName("org.jspecify.annotations.NullMarked");
        JSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME = fqName6;
        FqName fqName7 = new FqName("org.jspecify.annotations.NullnessUnspecified");
        JSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME = fqName7;
        FqName fqName8 = new FqName("org.jspecify.annotations.NullUnmarked");
        JSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME = fqName8;
        JAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME = new FqName("javax.annotation.meta.TypeQualifier");
        JAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME = new FqName("javax.annotation.meta.TypeQualifierNickname");
        JAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME = new FqName("javax.annotation.meta.TypeQualifierDefault");
        FqName fqName9 = new FqName("javax.annotation.Nonnull");
        JAVAX_NONNULL_ANNOTATION_FQ_NAME = fqName9;
        FqName fqName10 = new FqName("javax.annotation.Nullable");
        JAVAX_NULLABLE_ANNOTATION_FQ_NAME = fqName10;
        FqName fqName11 = new FqName("javax.annotation.CheckForNull");
        JAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME = fqName11;
        JAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME = new FqName("javax.annotation.ParametersAreNonnullByDefault");
        JAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME = new FqName("javax.annotation.ParametersAreNullableByDefault");
        BUILT_IN_TYPE_QUALIFIER_ANNOTATIONS = SetsKt.setOf(new FqName[]{fqName9, fqName11});
        FqName fqName12 = JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION;
        fqName12.getClass();
        Set<FqName> of = SetsKt.setOf(new FqName[]{fqName12, fqName4, new FqName("android.annotation.NonNull"), new FqName("androidx.annotation.NonNull"), new FqName("androidx.annotation.RecentlyNonNull"), new FqName("android.support.annotation.NonNull"), new FqName("com.android.annotations.NonNull"), new FqName("org.checkerframework.checker.nullness.compatqual.NonNullDecl"), new FqName("org.checkerframework.checker.nullness.qual.NonNull"), new FqName("edu.umd.cs.findbugs.annotations.NonNull"), new FqName("io.reactivex.annotations.NonNull"), new FqName("io.reactivex.rxjava3.annotations.NonNull"), new FqName("org.eclipse.jdt.annotation.NonNull"), new FqName("lombok.NonNull"), new FqName("jakarta.annotation.Nonnull")});
        NOT_NULL_ANNOTATIONS = of;
        FqName fqName13 = JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION;
        fqName13.getClass();
        Set<FqName> of2 = SetsKt.setOf(new FqName[]{fqName13, fqName, fqName5, fqName10, fqName11, new FqName("android.annotation.Nullable"), new FqName("androidx.annotation.Nullable"), new FqName("androidx.annotation.RecentlyNullable"), new FqName("android.support.annotation.Nullable"), new FqName("com.android.annotations.Nullable"), new FqName("org.checkerframework.checker.nullness.compatqual.NullableDecl"), new FqName("org.checkerframework.checker.nullness.qual.Nullable"), new FqName("edu.umd.cs.findbugs.annotations.Nullable"), new FqName("edu.umd.cs.findbugs.annotations.PossiblyNull"), new FqName("edu.umd.cs.findbugs.annotations.CheckForNull"), new FqName("io.reactivex.annotations.Nullable"), new FqName("io.reactivex.rxjava3.annotations.Nullable"), new FqName("org.eclipse.jdt.annotation.Nullable"), new FqName("jakarta.annotation.Nullable"), new FqName("io.vertx.codegen.annotations.Nullable")});
        NULLABLE_ANNOTATIONS = of2;
        FORCE_FLEXIBILITY_ANNOTATIONS = SetsKt.setOf(new FqName[]{fqName3, fqName7});
        NULLABILITY_ANNOTATIONS = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(new LinkedHashSet(), of), of2), fqName9), fqName2), fqName6), fqName8);
        READ_ONLY_ANNOTATIONS = SetsKt.setOf(new FqName[]{JvmAnnotationNames.JETBRAINS_READONLY_ANNOTATION, JvmAnnotationNames.READONLY_ANNOTATION, JvmAnnotationNames.JETBRAINS_UNMODIFIABLE_ANNOTATION, JvmAnnotationNames.JETBRAINS_UNMODIFIABLE_VIEW_ANNOTATION});
        MUTABLE_ANNOTATIONS = SetsKt.setOf(new FqName[]{JvmAnnotationNames.JETBRAINS_MUTABLE_ANNOTATION, JvmAnnotationNames.MUTABLE_ANNOTATION});
        javaToKotlinNameMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(JvmAnnotationNames.TARGET_ANNOTATION, StandardNames.FqNames.target), TuplesKt.to(JvmAnnotationNames.RETENTION_ANNOTATION, StandardNames.FqNames.retention), TuplesKt.to(JvmAnnotationNames.DEPRECATED_ANNOTATION, StandardNames.FqNames.deprecated), TuplesKt.to(JvmAnnotationNames.DOCUMENTED_ANNOTATION, StandardNames.FqNames.mustBeDocumented)});
        UNDER_MIGRATION_ANNOTATION_FQ_NAME = new FqName("kotlin.annotations.jvm.UnderMigration");
    }

    public static final Set<FqName> getBUILT_IN_TYPE_QUALIFIER_ANNOTATIONS() {
        return BUILT_IN_TYPE_QUALIFIER_ANNOTATIONS;
    }

    public static final Set<FqName> getFORCE_FLEXIBILITY_ANNOTATIONS() {
        return FORCE_FLEXIBILITY_ANNOTATIONS;
    }

    public static final FqName getJAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME() {
        return JAVAX_CHECK_FOR_NULL_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_NONNULL_ANNOTATION_FQ_NAME() {
        return JAVAX_NONNULL_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_NULLABLE_ANNOTATION_FQ_NAME() {
        return JAVAX_NULLABLE_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME() {
        return JAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME() {
        return JAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME() {
        return JAVAX_TYPE_QUALIFIER_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME() {
        return JAVAX_TYPE_QUALIFIER_DEFAULT_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME() {
        return JAVAX_TYPE_QUALIFIER_NICKNAME_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_NON_NULL_ANNOTATION_FQ_NAME() {
        return JSPECIFY_NON_NULL_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_NULLABLE_ANNOTATION_FQ_NAME() {
        return JSPECIFY_NULLABLE_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME() {
        return JSPECIFY_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME() {
        return JSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME() {
        return JSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME() {
        return JSPECIFY_OLD_NULLABLE_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME() {
        return JSPECIFY_OLD_NULLNESS_UNSPECIFIED_ANNOTATION_FQ_NAME;
    }

    public static final FqName getJSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME() {
        return JSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME;
    }

    public static final Map<FqName, FqName> getJavaToKotlinNameMap() {
        return javaToKotlinNameMap;
    }

    public static final Set<FqName> getMUTABLE_ANNOTATIONS() {
        return MUTABLE_ANNOTATIONS;
    }

    public static final Set<FqName> getNOT_NULL_ANNOTATIONS() {
        return NOT_NULL_ANNOTATIONS;
    }

    public static final Set<FqName> getNULLABILITY_ANNOTATIONS() {
        return NULLABILITY_ANNOTATIONS;
    }

    public static final Set<FqName> getNULLABLE_ANNOTATIONS() {
        return NULLABLE_ANNOTATIONS;
    }

    public static final Set<FqName> getREAD_ONLY_ANNOTATIONS() {
        return READ_ONLY_ANNOTATIONS;
    }

    public static final FqName getUNDER_MIGRATION_ANNOTATION_FQ_NAME() {
        return UNDER_MIGRATION_ANNOTATION_FQ_NAME;
    }
}
