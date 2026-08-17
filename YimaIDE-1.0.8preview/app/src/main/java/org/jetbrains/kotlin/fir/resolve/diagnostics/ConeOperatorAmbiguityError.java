package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001e\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeOperatorAmbiguityError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithCandidates;", "candidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "<init>", "(Ljava/util/Collection;)V", "getCandidates", "()Ljava/util/Collection;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeOperatorAmbiguityError implements ConeDiagnosticWithCandidates {
    private final Collection<AbstractCallCandidate<?>> candidates;

    /* JADX WARN: Multi-variable type inference failed */
    public ConeOperatorAmbiguityError(Collection<? extends AbstractCallCandidate<?>> collection) {
        collection.getClass();
        this.candidates = collection;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates
    public Collection<AbstractCallCandidate<?>> getCandidates() {
        return this.candidates;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        StringBuilder sb = new StringBuilder("Operator overload ambiguity. Compatible candidates: ");
        Collection<FirBasedSymbol<?>> candidateSymbols = getCandidateSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidateSymbols, 10));
        Iterator<T> it = candidateSymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(ConeDiagnosticsKt.describeSymbol((FirBasedSymbol) it.next()));
        }
        sb.append(arrayList);
        return sb.toString();
    }
}
