package org.jetbrains.kotlin.ir.backend.js.dce;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/dce/ReachabilityInfo;", "", "source", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "target", "description", "", "isTargetContagious", "", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;Ljava/lang/String;Z)V", "getSource", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "getTarget", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
final /* data */ class ReachabilityInfo {
    private final String description;
    private final boolean isTargetContagious;
    private final IrDeclaration source;
    private final IrDeclaration target;

    public ReachabilityInfo(IrDeclaration irDeclaration, IrDeclaration irDeclaration2, String str, boolean z) {
        irDeclaration.getClass();
        irDeclaration2.getClass();
        str.getClass();
        this.source = irDeclaration;
        this.target = irDeclaration2;
        this.description = str;
        this.isTargetContagious = z;
    }

    public static /* synthetic */ ReachabilityInfo copy$default(ReachabilityInfo reachabilityInfo, IrDeclaration irDeclaration, IrDeclaration irDeclaration2, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            irDeclaration = reachabilityInfo.source;
        }
        if ((i & 2) != 0) {
            irDeclaration2 = reachabilityInfo.target;
        }
        if ((i & 4) != 0) {
            str = reachabilityInfo.description;
        }
        if ((i & 8) != 0) {
            z = reachabilityInfo.isTargetContagious;
        }
        return reachabilityInfo.copy(irDeclaration, irDeclaration2, str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrDeclaration getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IrDeclaration getTarget() {
        return this.target;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsTargetContagious() {
        return this.isTargetContagious;
    }

    public final ReachabilityInfo copy(IrDeclaration source, IrDeclaration target, String description, boolean isTargetContagious) {
        source.getClass();
        target.getClass();
        description.getClass();
        return new ReachabilityInfo(source, target, description, isTargetContagious);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReachabilityInfo)) {
            return false;
        }
        ReachabilityInfo reachabilityInfo = (ReachabilityInfo) other;
        return Intrinsics.areEqual(this.source, reachabilityInfo.source) && Intrinsics.areEqual(this.target, reachabilityInfo.target) && Intrinsics.areEqual(this.description, reachabilityInfo.description) && this.isTargetContagious == reachabilityInfo.isTargetContagious;
    }

    public final String getDescription() {
        return this.description;
    }

    public final IrDeclaration getSource() {
        return this.source;
    }

    public final IrDeclaration getTarget() {
        return this.target;
    }

    public int hashCode() {
        return (((((this.source.hashCode() * 31) + this.target.hashCode()) * 31) + this.description.hashCode()) * 31) + Boolean.hashCode(this.isTargetContagious);
    }

    public final boolean isTargetContagious() {
        return this.isTargetContagious;
    }

    public String toString() {
        return "ReachabilityInfo(source=" + this.source + ", target=" + this.target + ", description=" + this.description + ", isTargetContagious=" + this.isTargetContagious + ')';
    }
}
