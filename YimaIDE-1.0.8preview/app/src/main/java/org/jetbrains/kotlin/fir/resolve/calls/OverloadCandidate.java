package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/OverloadCandidate;", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "isInBestCandidates", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Z)V", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class OverloadCandidate {
    private final Candidate candidate;
    private final boolean isInBestCandidates;

    public OverloadCandidate(Candidate candidate, boolean z) {
        candidate.getClass();
        this.candidate = candidate;
        this.isInBestCandidates = z;
    }

    public static /* synthetic */ OverloadCandidate copy$default(OverloadCandidate overloadCandidate, Candidate candidate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            candidate = overloadCandidate.candidate;
        }
        if ((i & 2) != 0) {
            z = overloadCandidate.isInBestCandidates;
        }
        return overloadCandidate.copy(candidate, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Candidate getCandidate() {
        return this.candidate;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsInBestCandidates() {
        return this.isInBestCandidates;
    }

    public final OverloadCandidate copy(Candidate candidate, boolean isInBestCandidates) {
        candidate.getClass();
        return new OverloadCandidate(candidate, isInBestCandidates);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OverloadCandidate)) {
            return false;
        }
        OverloadCandidate overloadCandidate = (OverloadCandidate) other;
        return Intrinsics.areEqual(this.candidate, overloadCandidate.candidate) && this.isInBestCandidates == overloadCandidate.isInBestCandidates;
    }

    public final Candidate getCandidate() {
        return this.candidate;
    }

    public int hashCode() {
        return (this.candidate.hashCode() * 31) + Boolean.hashCode(this.isInBestCandidates);
    }

    public final boolean isInBestCandidates() {
        return this.isInBestCandidates;
    }

    public String toString() {
        return "OverloadCandidate(candidate=" + this.candidate + ", isInBestCandidates=" + this.isInBestCandidates + ')';
    }
}
