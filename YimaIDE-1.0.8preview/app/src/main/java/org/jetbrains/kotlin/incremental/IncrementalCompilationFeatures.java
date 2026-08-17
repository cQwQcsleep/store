package org.jetbrains.kotlin.incremental;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures;", "Ljava/io/Serializable;", "usePreciseJavaTracking", "", "withAbiSnapshot", "preciseCompilationResultsBackup", "keepIncrementalCompilationCachesInMemory", "enableUnsafeIncrementalCompilationForMultiplatform", "enableMonotonousIncrementalCompileSetExpansion", "<init>", "(ZZZZZZ)V", "getUsePreciseJavaTracking", "()Z", "getWithAbiSnapshot", "getPreciseCompilationResultsBackup", "getKeepIncrementalCompilationCachesInMemory", "getEnableUnsafeIncrementalCompilationForMultiplatform", "getEnableMonotonousIncrementalCompileSetExpansion", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IncrementalCompilationFeatures implements Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final IncrementalCompilationFeatures DEFAULT_CONFIGURATION = new IncrementalCompilationFeatures(false, false, false, false, false, false, 63, null);
    public static final long serialVersionUID = 3;
    private final boolean enableMonotonousIncrementalCompileSetExpansion;
    private final boolean enableUnsafeIncrementalCompilationForMultiplatform;
    private final boolean keepIncrementalCompilationCachesInMemory;
    private final boolean preciseCompilationResultsBackup;
    private final boolean usePreciseJavaTracking;
    private final boolean withAbiSnapshot;

    public /* synthetic */ IncrementalCompilationFeatures(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? true : z6);
    }

    public static /* synthetic */ IncrementalCompilationFeatures copy$default(IncrementalCompilationFeatures incrementalCompilationFeatures, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, Object obj) {
        if ((i & 1) != 0) {
            z = incrementalCompilationFeatures.usePreciseJavaTracking;
        }
        if ((i & 2) != 0) {
            z2 = incrementalCompilationFeatures.withAbiSnapshot;
        }
        if ((i & 4) != 0) {
            z3 = incrementalCompilationFeatures.preciseCompilationResultsBackup;
        }
        if ((i & 8) != 0) {
            z4 = incrementalCompilationFeatures.keepIncrementalCompilationCachesInMemory;
        }
        if ((i & 16) != 0) {
            z5 = incrementalCompilationFeatures.enableUnsafeIncrementalCompilationForMultiplatform;
        }
        if ((i & 32) != 0) {
            z6 = incrementalCompilationFeatures.enableMonotonousIncrementalCompileSetExpansion;
        }
        boolean z7 = z5;
        boolean z8 = z6;
        return incrementalCompilationFeatures.copy(z, z2, z3, z4, z7, z8);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getUsePreciseJavaTracking() {
        return this.usePreciseJavaTracking;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getWithAbiSnapshot() {
        return this.withAbiSnapshot;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPreciseCompilationResultsBackup() {
        return this.preciseCompilationResultsBackup;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getKeepIncrementalCompilationCachesInMemory() {
        return this.keepIncrementalCompilationCachesInMemory;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getEnableUnsafeIncrementalCompilationForMultiplatform() {
        return this.enableUnsafeIncrementalCompilationForMultiplatform;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getEnableMonotonousIncrementalCompileSetExpansion() {
        return this.enableMonotonousIncrementalCompileSetExpansion;
    }

    public final IncrementalCompilationFeatures copy(boolean usePreciseJavaTracking, boolean withAbiSnapshot, boolean preciseCompilationResultsBackup, boolean keepIncrementalCompilationCachesInMemory, boolean enableUnsafeIncrementalCompilationForMultiplatform, boolean enableMonotonousIncrementalCompileSetExpansion) {
        return new IncrementalCompilationFeatures(usePreciseJavaTracking, withAbiSnapshot, preciseCompilationResultsBackup, keepIncrementalCompilationCachesInMemory, enableUnsafeIncrementalCompilationForMultiplatform, enableMonotonousIncrementalCompileSetExpansion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncrementalCompilationFeatures)) {
            return false;
        }
        IncrementalCompilationFeatures incrementalCompilationFeatures = (IncrementalCompilationFeatures) other;
        return this.usePreciseJavaTracking == incrementalCompilationFeatures.usePreciseJavaTracking && this.withAbiSnapshot == incrementalCompilationFeatures.withAbiSnapshot && this.preciseCompilationResultsBackup == incrementalCompilationFeatures.preciseCompilationResultsBackup && this.keepIncrementalCompilationCachesInMemory == incrementalCompilationFeatures.keepIncrementalCompilationCachesInMemory && this.enableUnsafeIncrementalCompilationForMultiplatform == incrementalCompilationFeatures.enableUnsafeIncrementalCompilationForMultiplatform && this.enableMonotonousIncrementalCompileSetExpansion == incrementalCompilationFeatures.enableMonotonousIncrementalCompileSetExpansion;
    }

    public final boolean getEnableMonotonousIncrementalCompileSetExpansion() {
        return this.enableMonotonousIncrementalCompileSetExpansion;
    }

    public final boolean getEnableUnsafeIncrementalCompilationForMultiplatform() {
        return this.enableUnsafeIncrementalCompilationForMultiplatform;
    }

    public final boolean getKeepIncrementalCompilationCachesInMemory() {
        return this.keepIncrementalCompilationCachesInMemory;
    }

    public final boolean getPreciseCompilationResultsBackup() {
        return this.preciseCompilationResultsBackup;
    }

    public final boolean getUsePreciseJavaTracking() {
        return this.usePreciseJavaTracking;
    }

    public final boolean getWithAbiSnapshot() {
        return this.withAbiSnapshot;
    }

    public int hashCode() {
        return (((((((((Boolean.hashCode(this.usePreciseJavaTracking) * 31) + Boolean.hashCode(this.withAbiSnapshot)) * 31) + Boolean.hashCode(this.preciseCompilationResultsBackup)) * 31) + Boolean.hashCode(this.keepIncrementalCompilationCachesInMemory)) * 31) + Boolean.hashCode(this.enableUnsafeIncrementalCompilationForMultiplatform)) * 31) + Boolean.hashCode(this.enableMonotonousIncrementalCompileSetExpansion);
    }

    public String toString() {
        return "IncrementalCompilationFeatures(usePreciseJavaTracking=" + this.usePreciseJavaTracking + ", withAbiSnapshot=" + this.withAbiSnapshot + ", preciseCompilationResultsBackup=" + this.preciseCompilationResultsBackup + ", keepIncrementalCompilationCachesInMemory=" + this.keepIncrementalCompilationCachesInMemory + ", enableUnsafeIncrementalCompilationForMultiplatform=" + this.enableUnsafeIncrementalCompilationForMultiplatform + ", enableMonotonousIncrementalCompileSetExpansion=" + this.enableMonotonousIncrementalCompileSetExpansion + ')';
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures$Companion;", "", "<init>", "()V", "DEFAULT_CONFIGURATION", "Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures;", "getDEFAULT_CONFIGURATION", "()Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures;", "serialVersionUID", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IncrementalCompilationFeatures getDEFAULT_CONFIGURATION() {
            return IncrementalCompilationFeatures.DEFAULT_CONFIGURATION;
        }

        private Companion() {
        }
    }

    public IncrementalCompilationFeatures(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.usePreciseJavaTracking = z;
        this.withAbiSnapshot = z2;
        this.preciseCompilationResultsBackup = z3;
        this.keepIncrementalCompilationCachesInMemory = z4;
        this.enableUnsafeIncrementalCompilationForMultiplatform = z5;
        this.enableMonotonousIncrementalCompileSetExpansion = z6;
    }

    public IncrementalCompilationFeatures() {
        this(false, false, false, false, false, false, 63, null);
    }
}
