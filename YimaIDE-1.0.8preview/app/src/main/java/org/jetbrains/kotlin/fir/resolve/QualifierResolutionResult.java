package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/QualifierResolutionResult;", Argument.Delimiters.none, "qualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "applicability", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;)V", "getQualifier", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "getApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class QualifierResolutionResult {
    private final CandidateApplicability applicability;
    private final FirResolvedQualifier qualifier;

    public QualifierResolutionResult(FirResolvedQualifier firResolvedQualifier, CandidateApplicability candidateApplicability) {
        firResolvedQualifier.getClass();
        candidateApplicability.getClass();
        this.qualifier = firResolvedQualifier;
        this.applicability = candidateApplicability;
    }

    public static /* synthetic */ QualifierResolutionResult copy$default(QualifierResolutionResult qualifierResolutionResult, FirResolvedQualifier firResolvedQualifier, CandidateApplicability candidateApplicability, int i, Object obj) {
        if ((i & 1) != 0) {
            firResolvedQualifier = qualifierResolutionResult.qualifier;
        }
        if ((i & 2) != 0) {
            candidateApplicability = qualifierResolutionResult.applicability;
        }
        return qualifierResolutionResult.copy(firResolvedQualifier, candidateApplicability);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirResolvedQualifier getQualifier() {
        return this.qualifier;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CandidateApplicability getApplicability() {
        return this.applicability;
    }

    public final QualifierResolutionResult copy(FirResolvedQualifier qualifier, CandidateApplicability applicability) {
        qualifier.getClass();
        applicability.getClass();
        return new QualifierResolutionResult(qualifier, applicability);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QualifierResolutionResult)) {
            return false;
        }
        QualifierResolutionResult qualifierResolutionResult = (QualifierResolutionResult) other;
        return Intrinsics.areEqual(this.qualifier, qualifierResolutionResult.qualifier) && this.applicability == qualifierResolutionResult.applicability;
    }

    public final CandidateApplicability getApplicability() {
        return this.applicability;
    }

    public final FirResolvedQualifier getQualifier() {
        return this.qualifier;
    }

    public int hashCode() {
        return (this.qualifier.hashCode() * 31) + this.applicability.hashCode();
    }

    public String toString() {
        return "QualifierResolutionResult(qualifier=" + this.qualifier + ", applicability=" + this.applicability + ')';
    }
}
