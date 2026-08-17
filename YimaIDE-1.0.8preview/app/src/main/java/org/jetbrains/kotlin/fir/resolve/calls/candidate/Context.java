package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Context;", Argument.Delimiters.none, "candidateProcessor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", Argument.Delimiters.none, "postponedAtomsProcessor", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getCandidateProcessor", "()Lkotlin/jvm/functions/Function1;", "getPostponedAtomsProcessor", "visited", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "getVisited", "()Ljava/util/Set;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class Context {
    private final Function1<Candidate, Unit> candidateProcessor;
    private final Function1<ConePostponedResolvedAtom, Unit> postponedAtomsProcessor;
    private final Set<ConeResolutionAtom> visited;

    /* JADX WARN: Multi-variable type inference failed */
    public Context(Function1<? super Candidate, Unit> function1, Function1<? super ConePostponedResolvedAtom, Unit> function2) {
        function1.getClass();
        function2.getClass();
        this.candidateProcessor = function1;
        this.postponedAtomsProcessor = function2;
        this.visited = new LinkedHashSet();
    }

    public final Function1<Candidate, Unit> getCandidateProcessor() {
        return this.candidateProcessor;
    }

    public final Function1<ConePostponedResolvedAtom, Unit> getPostponedAtomsProcessor() {
        return this.postponedAtomsProcessor;
    }

    public final Set<ConeResolutionAtom> getVisited() {
        return this.visited;
    }
}
