package org.jetbrains.kotlin.resolve.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J3\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription;", "", "annotationFqName", "Lorg/jetbrains/kotlin/name/FqName;", "severity", "Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;", "message", "", "subclassesOnly", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;Ljava/lang/String;Z)V", "getAnnotationFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getSeverity", "()Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;", "getMessage", "()Ljava/lang/String;", "getSubclassesOnly", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "Severity", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class OptInDescription {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Severity DEFAULT_SEVERITY = Severity.ERROR;
    private static final Set<KotlinTarget> WRONG_TARGETS_FOR_MARKER = SetsKt.setOf(new KotlinTarget[]{KotlinTarget.EXPRESSION, KotlinTarget.FILE, KotlinTarget.TYPE, KotlinTarget.TYPE_PARAMETER});
    private final FqName annotationFqName;
    private final String message;
    private final Severity severity;
    private final boolean subclassesOnly;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;", "", "<init>", "(Ljava/lang/String;I)V", "WARNING", "ERROR", "FUTURE_ERROR", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public enum Severity {
        WARNING,
        ERROR,
        FUTURE_ERROR;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Severity> getEntries() {
            return $ENTRIES;
        }
    }

    public OptInDescription(FqName fqName, Severity severity, String str, boolean z) {
        fqName.getClass();
        severity.getClass();
        this.annotationFqName = fqName;
        this.severity = severity;
        this.message = str;
        this.subclassesOnly = z;
    }

    public static /* synthetic */ OptInDescription copy$default(OptInDescription optInDescription, FqName fqName, Severity severity, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            fqName = optInDescription.annotationFqName;
        }
        if ((i & 2) != 0) {
            severity = optInDescription.severity;
        }
        if ((i & 4) != 0) {
            str = optInDescription.message;
        }
        if ((i & 8) != 0) {
            z = optInDescription.subclassesOnly;
        }
        return optInDescription.copy(fqName, severity, str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FqName getAnnotationFqName() {
        return this.annotationFqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Severity getSeverity() {
        return this.severity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSubclassesOnly() {
        return this.subclassesOnly;
    }

    public final OptInDescription copy(FqName annotationFqName, Severity severity, String message, boolean subclassesOnly) {
        annotationFqName.getClass();
        severity.getClass();
        return new OptInDescription(annotationFqName, severity, message, subclassesOnly);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OptInDescription)) {
            return false;
        }
        OptInDescription optInDescription = (OptInDescription) other;
        return Intrinsics.areEqual(this.annotationFqName, optInDescription.annotationFqName) && this.severity == optInDescription.severity && Intrinsics.areEqual(this.message, optInDescription.message) && this.subclassesOnly == optInDescription.subclassesOnly;
    }

    public final FqName getAnnotationFqName() {
        return this.annotationFqName;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Severity getSeverity() {
        return this.severity;
    }

    public final boolean getSubclassesOnly() {
        return this.subclassesOnly;
    }

    public int hashCode() {
        int iHashCode = ((this.annotationFqName.hashCode() * 31) + this.severity.hashCode()) * 31;
        String str = this.message;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.subclassesOnly);
    }

    public String toString() {
        return "OptInDescription(annotationFqName=" + this.annotationFqName + ", severity=" + this.severity + ", message=" + this.message + ", subclassesOnly=" + this.subclassesOnly + ')';
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Companion;", "", "<init>", "()V", "DEFAULT_SEVERITY", "Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;", "getDEFAULT_SEVERITY", "()Lorg/jetbrains/kotlin/resolve/checkers/OptInDescription$Severity;", "WRONG_TARGETS_FOR_MARKER", "", "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "getWRONG_TARGETS_FOR_MARKER", "()Ljava/util/Set;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Severity getDEFAULT_SEVERITY() {
            return OptInDescription.DEFAULT_SEVERITY;
        }

        public final Set<KotlinTarget> getWRONG_TARGETS_FOR_MARKER() {
            return OptInDescription.WRONG_TARGETS_FOR_MARKER;
        }

        private Companion() {
        }
    }
}
