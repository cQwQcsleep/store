package org.jetbrains.kotlin.load.java;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaNullabilityAnnotationsStatus;", "", "reportLevelBefore", "Lorg/jetbrains/kotlin/load/java/ReportLevel;", "sinceVersion", "Lkotlin/KotlinVersion;", "reportLevelAfter", "<init>", "(Lorg/jetbrains/kotlin/load/java/ReportLevel;Lkotlin/KotlinVersion;Lorg/jetbrains/kotlin/load/java/ReportLevel;)V", "getReportLevelBefore", "()Lorg/jetbrains/kotlin/load/java/ReportLevel;", "getSinceVersion", "()Lkotlin/KotlinVersion;", "getReportLevelAfter", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class JavaNullabilityAnnotationsStatus {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JavaNullabilityAnnotationsStatus DEFAULT = new JavaNullabilityAnnotationsStatus(ReportLevel.STRICT, null, null, 6, null);
    private final ReportLevel reportLevelAfter;
    private final ReportLevel reportLevelBefore;
    private final KotlinVersion sinceVersion;

    public /* synthetic */ JavaNullabilityAnnotationsStatus(ReportLevel reportLevel, KotlinVersion kotlinVersion, ReportLevel reportLevel2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, (i & 2) != 0 ? new KotlinVersion(1, 0) : kotlinVersion, (i & 4) != 0 ? reportLevel : reportLevel2);
    }

    public static /* synthetic */ JavaNullabilityAnnotationsStatus copy$default(JavaNullabilityAnnotationsStatus javaNullabilityAnnotationsStatus, ReportLevel reportLevel, KotlinVersion kotlinVersion, ReportLevel reportLevel2, int i, Object obj) {
        if ((i & 1) != 0) {
            reportLevel = javaNullabilityAnnotationsStatus.reportLevelBefore;
        }
        if ((i & 2) != 0) {
            kotlinVersion = javaNullabilityAnnotationsStatus.sinceVersion;
        }
        if ((i & 4) != 0) {
            reportLevel2 = javaNullabilityAnnotationsStatus.reportLevelAfter;
        }
        return javaNullabilityAnnotationsStatus.copy(reportLevel, kotlinVersion, reportLevel2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ReportLevel getReportLevelBefore() {
        return this.reportLevelBefore;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final KotlinVersion getSinceVersion() {
        return this.sinceVersion;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ReportLevel getReportLevelAfter() {
        return this.reportLevelAfter;
    }

    public final JavaNullabilityAnnotationsStatus copy(ReportLevel reportLevelBefore, KotlinVersion sinceVersion, ReportLevel reportLevelAfter) {
        reportLevelBefore.getClass();
        reportLevelAfter.getClass();
        return new JavaNullabilityAnnotationsStatus(reportLevelBefore, sinceVersion, reportLevelAfter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaNullabilityAnnotationsStatus)) {
            return false;
        }
        JavaNullabilityAnnotationsStatus javaNullabilityAnnotationsStatus = (JavaNullabilityAnnotationsStatus) other;
        return this.reportLevelBefore == javaNullabilityAnnotationsStatus.reportLevelBefore && Intrinsics.areEqual(this.sinceVersion, javaNullabilityAnnotationsStatus.sinceVersion) && this.reportLevelAfter == javaNullabilityAnnotationsStatus.reportLevelAfter;
    }

    public final ReportLevel getReportLevelAfter() {
        return this.reportLevelAfter;
    }

    public final ReportLevel getReportLevelBefore() {
        return this.reportLevelBefore;
    }

    public final KotlinVersion getSinceVersion() {
        return this.sinceVersion;
    }

    public int hashCode() {
        int iHashCode = this.reportLevelBefore.hashCode() * 31;
        KotlinVersion kotlinVersion = this.sinceVersion;
        return ((iHashCode + (kotlinVersion == null ? 0 : kotlinVersion.hashCode())) * 31) + this.reportLevelAfter.hashCode();
    }

    public String toString() {
        return "JavaNullabilityAnnotationsStatus(reportLevelBefore=" + this.reportLevelBefore + ", sinceVersion=" + this.sinceVersion + ", reportLevelAfter=" + this.reportLevelAfter + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaNullabilityAnnotationsStatus$Companion;", "", "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/load/java/JavaNullabilityAnnotationsStatus;", "getDEFAULT", "()Lorg/jetbrains/kotlin/load/java/JavaNullabilityAnnotationsStatus;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JavaNullabilityAnnotationsStatus getDEFAULT() {
            return JavaNullabilityAnnotationsStatus.DEFAULT;
        }

        private Companion() {
        }
    }

    public JavaNullabilityAnnotationsStatus(ReportLevel reportLevel, KotlinVersion kotlinVersion, ReportLevel reportLevel2) {
        reportLevel.getClass();
        reportLevel2.getClass();
        this.reportLevelBefore = reportLevel;
        this.sinceVersion = kotlinVersion;
        this.reportLevelAfter = reportLevel2;
    }
}
