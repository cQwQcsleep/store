package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralOuterCandidateContext;", Argument.Delimiters.none, "containingCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "checkerSink", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;)V", "getContainingCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getCheckerSink", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectionLiteralOuterCandidateContext {
    private final CheckerSink checkerSink;
    private final Candidate containingCandidate;

    public CollectionLiteralOuterCandidateContext(Candidate candidate, CheckerSink checkerSink) {
        candidate.getClass();
        this.containingCandidate = candidate;
        this.checkerSink = checkerSink;
    }

    public final CheckerSink getCheckerSink() {
        return this.checkerSink;
    }

    public final Candidate getContainingCandidate() {
        return this.containingCandidate;
    }

    public /* synthetic */ CollectionLiteralOuterCandidateContext(Candidate candidate, CheckerSink checkerSink, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(candidate, (i & 2) != 0 ? null : checkerSink);
    }
}
