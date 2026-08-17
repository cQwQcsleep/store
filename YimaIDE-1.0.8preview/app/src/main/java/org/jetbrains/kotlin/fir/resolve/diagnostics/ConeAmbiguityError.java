package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0006\b\u0001\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0006\u001a\u0012\u0012\u0006\b\u0001\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeAmbiguityError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithCandidates;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "applicability", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "candidatesWithErrors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCandidate;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;Ljava/util/Map;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "getCandidatesWithErrors", "()Ljava/util/Map;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "candidates", Argument.Delimiters.none, "getCandidates", "()Ljava/util/Collection;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAmbiguityError implements ConeDiagnosticWithCandidates {
    private final CandidateApplicability applicability;
    private final Map<? extends AbstractCandidate, ConeDiagnostic> candidatesWithErrors;
    private final Name name;

    /* JADX WARN: Multi-variable type inference failed */
    public ConeAmbiguityError(Name name, CandidateApplicability candidateApplicability, Map<? extends AbstractCandidate, ? extends ConeDiagnostic> map) {
        name.getClass();
        candidateApplicability.getClass();
        map.getClass();
        this.name = name;
        this.applicability = candidateApplicability;
        this.candidatesWithErrors = map;
    }

    public final CandidateApplicability getApplicability() {
        return this.applicability;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates
    public Collection<AbstractCandidate> getCandidates() {
        return this.candidatesWithErrors.keySet();
    }

    public final Map<? extends AbstractCandidate, ConeDiagnostic> getCandidatesWithErrors() {
        return this.candidatesWithErrors;
    }

    public final Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        StringBuilder sb = new StringBuilder("Ambiguity: ");
        sb.append(this.name);
        sb.append(", ");
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
