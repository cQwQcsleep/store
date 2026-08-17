package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.load.java.JavaTypeEnhancementState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R'\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR)\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\tR)\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u000b\u0012\u0004\b\u0013\u0010\u0003\u001a\u0004\b\u0014\u0010\tR'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u000b\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0018\u0010\tR'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u000b\u0012\u0004\b\u001b\u0010\u0003\u001a\u0004\b\u001c\u0010\tR'\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b!\u0010\u000b\u0012\u0004\b\u001f\u0010\u0003\u001a\u0004\b \u0010\tR'\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b%\u0010\u000b\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\tR'\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b)\u0010\u000b\u0012\u0004\b'\u0010\u0003\u001a\u0004\b(\u0010\tR'\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b-\u0010\u000b\u0012\u0004\b+\u0010\u0003\u001a\u0004\b,\u0010\t¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmAnalysisFlags;", Argument.Delimiters.none, "<init>", "()V", "strictMetadataVersionSemantics", "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "getStrictMetadataVersionSemantics$annotations", "getStrictMetadataVersionSemantics", "()Lorg/jetbrains/kotlin/config/AnalysisFlag;", "strictMetadataVersionSemantics$delegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "javaTypeEnhancementState", "Lorg/jetbrains/kotlin/load/java/JavaTypeEnhancementState;", "getJavaTypeEnhancementState$annotations", "getJavaTypeEnhancementState", "javaTypeEnhancementState$delegate", "jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "getJvmDefaultMode$annotations", "getJvmDefaultMode", "jvmDefaultMode$delegate", "inheritMultifileParts", "getInheritMultifileParts$annotations", "getInheritMultifileParts", "inheritMultifileParts$delegate", "sanitizeParentheses", "getSanitizeParentheses$annotations", "getSanitizeParentheses", "sanitizeParentheses$delegate", "suppressMissingBuiltinsError", "getSuppressMissingBuiltinsError$annotations", "getSuppressMissingBuiltinsError", "suppressMissingBuiltinsError$delegate", "enableJvmPreview", "getEnableJvmPreview$annotations", "getEnableJvmPreview", "enableJvmPreview$delegate", "outputBuiltinsMetadata", "getOutputBuiltinsMetadata$annotations", "getOutputBuiltinsMetadata", "outputBuiltinsMetadata$delegate", "implicitJvmExposeBoxed", "getImplicitJvmExposeBoxed$annotations", "getImplicitJvmExposeBoxed", "implicitJvmExposeBoxed$delegate", "Delegates", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmAnalysisFlags {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    public static final JvmAnalysisFlags INSTANCE;

    /* JADX INFO: renamed from: enableJvmPreview$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate enableJvmPreview;

    /* JADX INFO: renamed from: implicitJvmExposeBoxed$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate implicitJvmExposeBoxed;

    /* JADX INFO: renamed from: inheritMultifileParts$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate inheritMultifileParts;

    /* JADX INFO: renamed from: javaTypeEnhancementState$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate javaTypeEnhancementState;

    /* JADX INFO: renamed from: jvmDefaultMode$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate jvmDefaultMode;

    /* JADX INFO: renamed from: outputBuiltinsMetadata$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate outputBuiltinsMetadata;

    /* JADX INFO: renamed from: sanitizeParentheses$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate sanitizeParentheses;

    /* JADX INFO: renamed from: strictMetadataVersionSemantics$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate strictMetadataVersionSemantics;

    /* JADX INFO: renamed from: suppressMissingBuiltinsError$delegate, reason: from kotlin metadata */
    private static final AnalysisFlag.Delegate suppressMissingBuiltinsError;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmAnalysisFlags$Delegates;", Argument.Delimiters.none, "<init>", "()V", "JavaTypeEnhancementStateNullByDefault", "JvmDefaultModeNullByDefault", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Delegates {
        public static final Delegates INSTANCE = new Delegates();

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmAnalysisFlags$Delegates$JavaTypeEnhancementStateNullByDefault;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "Lorg/jetbrains/kotlin/load/java/JavaTypeEnhancementState;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class JavaTypeEnhancementStateNullByDefault {
            public static final JavaTypeEnhancementStateNullByDefault INSTANCE = new JavaTypeEnhancementStateNullByDefault();

            private JavaTypeEnhancementStateNullByDefault() {
            }

            public final AnalysisFlag.Delegate<JavaTypeEnhancementState> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new AnalysisFlag.Delegate<>(property.getName(), null);
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0086\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/JvmAnalysisFlags$Delegates$JvmDefaultModeNullByDefault;", Argument.Delimiters.none, "<init>", "()V", "provideDelegate", "Lorg/jetbrains/kotlin/config/AnalysisFlag$Delegate;", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "instance", "property", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:config.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class JvmDefaultModeNullByDefault {
            public static final JvmDefaultModeNullByDefault INSTANCE = new JvmDefaultModeNullByDefault();

            private JvmDefaultModeNullByDefault() {
            }

            public final AnalysisFlag.Delegate<JvmDefaultMode> provideDelegate(Object instance, KProperty<?> property) {
                property.getClass();
                return new AnalysisFlag.Delegate<>(property.getName(), null);
            }
        }

        private Delegates() {
        }
    }

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(JvmAnalysisFlags.class, "strictMetadataVersionSemantics", "getStrictMetadataVersionSemantics()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "javaTypeEnhancementState", "getJavaTypeEnhancementState()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "jvmDefaultMode", "getJvmDefaultMode()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "inheritMultifileParts", "getInheritMultifileParts()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "sanitizeParentheses", "getSanitizeParentheses()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "suppressMissingBuiltinsError", "getSuppressMissingBuiltinsError()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "enableJvmPreview", "getEnableJvmPreview()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "outputBuiltinsMetadata", "getOutputBuiltinsMetadata()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0), new PropertyReference1Impl<>(JvmAnalysisFlags.class, "implicitJvmExposeBoxed", "getImplicitJvmExposeBoxed()Lorg/jetbrains/kotlin/config/AnalysisFlag;", 0)};
        $$delegatedProperties = kPropertyArr;
        JvmAnalysisFlags jvmAnalysisFlags = new JvmAnalysisFlags();
        INSTANCE = jvmAnalysisFlags;
        AnalysisFlag.Delegates.Boolean.Companion companion = AnalysisFlag.Delegates.Boolean.INSTANCE;
        strictMetadataVersionSemantics = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[0]);
        javaTypeEnhancementState = Delegates.JavaTypeEnhancementStateNullByDefault.INSTANCE.provideDelegate(jvmAnalysisFlags, kPropertyArr[1]);
        jvmDefaultMode = Delegates.JvmDefaultModeNullByDefault.INSTANCE.provideDelegate(jvmAnalysisFlags, kPropertyArr[2]);
        inheritMultifileParts = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[3]);
        sanitizeParentheses = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[4]);
        suppressMissingBuiltinsError = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[5]);
        enableJvmPreview = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[6]);
        outputBuiltinsMetadata = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[7]);
        implicitJvmExposeBoxed = companion.provideDelegate(jvmAnalysisFlags, kPropertyArr[8]);
    }

    private JvmAnalysisFlags() {
    }

    public static final AnalysisFlag<Boolean> getEnableJvmPreview() {
        return enableJvmPreview.getValue((Object) INSTANCE, $$delegatedProperties[6]);
    }

    @JvmStatic
    public static /* synthetic */ void getEnableJvmPreview$annotations() {
    }

    public static final AnalysisFlag<Boolean> getImplicitJvmExposeBoxed() {
        return implicitJvmExposeBoxed.getValue((Object) INSTANCE, $$delegatedProperties[8]);
    }

    @JvmStatic
    public static /* synthetic */ void getImplicitJvmExposeBoxed$annotations() {
    }

    public static final AnalysisFlag<Boolean> getInheritMultifileParts() {
        return inheritMultifileParts.getValue((Object) INSTANCE, $$delegatedProperties[3]);
    }

    @JvmStatic
    public static /* synthetic */ void getInheritMultifileParts$annotations() {
    }

    public static final AnalysisFlag<JavaTypeEnhancementState> getJavaTypeEnhancementState() {
        return javaTypeEnhancementState.getValue((Object) INSTANCE, $$delegatedProperties[1]);
    }

    @JvmStatic
    public static /* synthetic */ void getJavaTypeEnhancementState$annotations() {
    }

    public static final AnalysisFlag<JvmDefaultMode> getJvmDefaultMode() {
        return jvmDefaultMode.getValue((Object) INSTANCE, $$delegatedProperties[2]);
    }

    @JvmStatic
    public static /* synthetic */ void getJvmDefaultMode$annotations() {
    }

    public static final AnalysisFlag<Boolean> getOutputBuiltinsMetadata() {
        return outputBuiltinsMetadata.getValue((Object) INSTANCE, $$delegatedProperties[7]);
    }

    @JvmStatic
    public static /* synthetic */ void getOutputBuiltinsMetadata$annotations() {
    }

    public static final AnalysisFlag<Boolean> getSanitizeParentheses() {
        return sanitizeParentheses.getValue((Object) INSTANCE, $$delegatedProperties[4]);
    }

    @JvmStatic
    public static /* synthetic */ void getSanitizeParentheses$annotations() {
    }

    public static final AnalysisFlag<Boolean> getStrictMetadataVersionSemantics() {
        return strictMetadataVersionSemantics.getValue((Object) INSTANCE, $$delegatedProperties[0]);
    }

    @JvmStatic
    public static /* synthetic */ void getStrictMetadataVersionSemantics$annotations() {
    }

    public static final AnalysisFlag<Boolean> getSuppressMissingBuiltinsError() {
        return suppressMissingBuiltinsError.getValue((Object) INSTANCE, $$delegatedProperties[5]);
    }

    @JvmStatic
    public static /* synthetic */ void getSuppressMissingBuiltinsError$annotations() {
    }
}
