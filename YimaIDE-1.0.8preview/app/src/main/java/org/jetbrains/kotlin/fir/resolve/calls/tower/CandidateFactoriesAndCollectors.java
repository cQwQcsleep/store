package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/CandidateFactoriesAndCollectors;", Argument.Delimiters.none, "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "resultCollector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;)V", "getCandidateFactory", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "getResultCollector", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CandidateFactoriesAndCollectors {
    private final CandidateFactory candidateFactory;
    private final CandidateCollector resultCollector;

    public CandidateFactoriesAndCollectors(CandidateFactory candidateFactory, CandidateCollector candidateCollector) {
        candidateFactory.getClass();
        candidateCollector.getClass();
        this.candidateFactory = candidateFactory;
        this.resultCollector = candidateCollector;
    }

    public final CandidateFactory getCandidateFactory() {
        return this.candidateFactory;
    }

    public final CandidateCollector getResultCollector() {
        return this.resultCollector;
    }
}
