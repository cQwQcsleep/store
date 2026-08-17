package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeResolutionToClassifierError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithSingleCandidate;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "candidateSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "getCandidateSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeResolutionToClassifierError implements ConeDiagnosticWithSingleCandidate {
    private final AbstractCallCandidate<?> candidate;
    private final FirRegularClassSymbol candidateSymbol;

    public ConeResolutionToClassifierError(AbstractCallCandidate<?> abstractCallCandidate, FirRegularClassSymbol firRegularClassSymbol) {
        abstractCallCandidate.getClass();
        firRegularClassSymbol.getClass();
        this.candidate = abstractCallCandidate;
        this.candidateSymbol = firRegularClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSingleCandidate
    public AbstractCallCandidate<?> getCandidate() {
        return this.candidate;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Resolution to classifier";
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSingleCandidate
    public FirRegularClassSymbol getCandidateSymbol() {
        return this.candidateSymbol;
    }
}
