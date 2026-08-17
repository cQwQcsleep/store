package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithSingleCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithCandidates;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "candidateSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getCandidateSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "candidates", Argument.Delimiters.none, "getCandidates", "()Ljava/util/Collection;", "candidateSymbols", "getCandidateSymbols", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ConeDiagnosticWithSingleCandidate extends ConeDiagnosticWithCandidates {
    AbstractCallCandidate<?> getCandidate();

    default FirBasedSymbol<?> getCandidateSymbol() {
        return getCandidate().getSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates
    default Collection<FirBasedSymbol<?>> getCandidateSymbols() {
        return CollectionsKt.listOf(getCandidateSymbol());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates
    default Collection<AbstractCallCandidate<?>> getCandidates() {
        return CollectionsKt.listOf(getCandidate());
    }
}
