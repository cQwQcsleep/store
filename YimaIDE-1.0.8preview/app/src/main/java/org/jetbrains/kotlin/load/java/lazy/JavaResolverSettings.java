package org.jetbrains.kotlin.load.java.lazy;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\bf\u0018\u0000 \r2\u00020\u0001:\u0002\f\rR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/lazy/JavaResolverSettings;", "", "correctNullabilityForNotNullTypeParameter", "", "getCorrectNullabilityForNotNullTypeParameter", "()Z", "typeEnhancementImprovementsInStrictMode", "getTypeEnhancementImprovementsInStrictMode", "ignoreNullabilityForErasedValueParameters", "getIgnoreNullabilityForErasedValueParameters", "enhancePrimitiveArrays", "getEnhancePrimitiveArrays", "Default", "Companion", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface JavaResolverSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/load/java/lazy/JavaResolverSettings$Companion;", "", "<init>", "()V", "create", "Lorg/jetbrains/kotlin/load/java/lazy/JavaResolverSettings;", "correctNullabilityForNotNullTypeParameter", "", "typeEnhancementImprovementsInStrictMode", "ignoreNullabilityForErasedValueParameters", "enhancePrimitiveArrays", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final JavaResolverSettings create(final boolean correctNullabilityForNotNullTypeParameter, final boolean typeEnhancementImprovementsInStrictMode, final boolean ignoreNullabilityForErasedValueParameters, final boolean enhancePrimitiveArrays) {
            return new JavaResolverSettings() { // from class: org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings$Companion$create$1
                @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
                /* JADX INFO: renamed from: getCorrectNullabilityForNotNullTypeParameter, reason: from getter */
                public boolean get$correctNullabilityForNotNullTypeParameter() {
                    return correctNullabilityForNotNullTypeParameter;
                }

                @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
                /* JADX INFO: renamed from: getEnhancePrimitiveArrays, reason: from getter */
                public boolean get$enhancePrimitiveArrays() {
                    return enhancePrimitiveArrays;
                }

                @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
                /* JADX INFO: renamed from: getIgnoreNullabilityForErasedValueParameters, reason: from getter */
                public boolean get$ignoreNullabilityForErasedValueParameters() {
                    return ignoreNullabilityForErasedValueParameters;
                }

                @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
                /* JADX INFO: renamed from: getTypeEnhancementImprovementsInStrictMode, reason: from getter */
                public boolean get$typeEnhancementImprovementsInStrictMode() {
                    return typeEnhancementImprovementsInStrictMode;
                }
            };
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/load/java/lazy/JavaResolverSettings$Default;", "Lorg/jetbrains/kotlin/load/java/lazy/JavaResolverSettings;", "<init>", "()V", "correctNullabilityForNotNullTypeParameter", "", "getCorrectNullabilityForNotNullTypeParameter", "()Z", "typeEnhancementImprovementsInStrictMode", "getTypeEnhancementImprovementsInStrictMode", "ignoreNullabilityForErasedValueParameters", "getIgnoreNullabilityForErasedValueParameters", "enhancePrimitiveArrays", "getEnhancePrimitiveArrays", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Default implements JavaResolverSettings {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
        /* JADX INFO: renamed from: getCorrectNullabilityForNotNullTypeParameter */
        public boolean get$correctNullabilityForNotNullTypeParameter() {
            return false;
        }

        @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
        /* JADX INFO: renamed from: getEnhancePrimitiveArrays */
        public boolean get$enhancePrimitiveArrays() {
            return false;
        }

        @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
        /* JADX INFO: renamed from: getIgnoreNullabilityForErasedValueParameters */
        public boolean get$ignoreNullabilityForErasedValueParameters() {
            return false;
        }

        @Override // org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings
        /* JADX INFO: renamed from: getTypeEnhancementImprovementsInStrictMode */
        public boolean get$typeEnhancementImprovementsInStrictMode() {
            return false;
        }
    }

    /* JADX INFO: renamed from: getCorrectNullabilityForNotNullTypeParameter */
    boolean get$correctNullabilityForNotNullTypeParameter();

    /* JADX INFO: renamed from: getEnhancePrimitiveArrays */
    boolean get$enhancePrimitiveArrays();

    /* JADX INFO: renamed from: getIgnoreNullabilityForErasedValueParameters */
    boolean get$ignoreNullabilityForErasedValueParameters();

    /* JADX INFO: renamed from: getTypeEnhancementImprovementsInStrictMode */
    boolean get$typeEnhancementImprovementsInStrictMode();
}
