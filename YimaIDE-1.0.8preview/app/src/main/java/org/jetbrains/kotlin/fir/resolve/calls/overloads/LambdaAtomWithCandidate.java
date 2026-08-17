package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u0086\u0002J\t\u0010\r\u001a\u00020\u0005H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/LambdaAtomWithCandidate;", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;)V", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getAtom", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "component1", "component2", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class LambdaAtomWithCandidate {
    private final ConeResolvedLambdaAtom atom;
    private final Candidate candidate;

    public LambdaAtomWithCandidate(Candidate candidate, ConeResolvedLambdaAtom coneResolvedLambdaAtom) {
        candidate.getClass();
        coneResolvedLambdaAtom.getClass();
        this.candidate = candidate;
        this.atom = coneResolvedLambdaAtom;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Candidate getCandidate() {
        return this.candidate;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeResolvedLambdaAtom getAtom() {
        return this.atom;
    }

    public final ConeResolvedLambdaAtom getAtom() {
        return this.atom;
    }

    public final Candidate getCandidate() {
        return this.candidate;
    }
}
