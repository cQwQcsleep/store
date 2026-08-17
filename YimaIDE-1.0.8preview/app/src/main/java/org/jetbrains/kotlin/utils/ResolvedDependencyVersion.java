package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\n\u0010\n\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/utils/ResolvedDependencyVersion;", "", "version", "", "<init>", "(Ljava/lang/String;)V", "getVersion", "()Ljava/lang/String;", "isEmpty", "", "toString", "component1", "copy", "equals", "other", "hashCode", "", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class ResolvedDependencyVersion {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ResolvedDependencyVersion EMPTY = new ResolvedDependencyVersion("");
    private final String version;

    public ResolvedDependencyVersion(String str) {
        str.getClass();
        this.version = str;
    }

    public static /* synthetic */ ResolvedDependencyVersion copy$default(ResolvedDependencyVersion resolvedDependencyVersion, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = resolvedDependencyVersion.version;
        }
        return resolvedDependencyVersion.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    public final ResolvedDependencyVersion copy(String version) {
        version.getClass();
        return new ResolvedDependencyVersion(version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ResolvedDependencyVersion) && Intrinsics.areEqual(this.version, ((ResolvedDependencyVersion) other).version);
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return this.version.hashCode();
    }

    public final boolean isEmpty() {
        return this.version.length() == 0;
    }

    public String toString() {
        return this.version;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/utils/ResolvedDependencyVersion$Companion;", "", "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/utils/ResolvedDependencyVersion;", "getEMPTY", "()Lorg/jetbrains/kotlin/utils/ResolvedDependencyVersion;", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ResolvedDependencyVersion getEMPTY() {
            return ResolvedDependencyVersion.EMPTY;
        }

        private Companion() {
        }
    }
}
