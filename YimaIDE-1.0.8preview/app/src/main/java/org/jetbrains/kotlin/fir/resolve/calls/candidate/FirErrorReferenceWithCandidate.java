package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirErrorReferenceWithCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "isError", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorReferenceWithCandidate extends FirNamedReferenceWithCandidate {
    private final ConeDiagnostic diagnostic;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirErrorReferenceWithCandidate(KtSourceElement ktSourceElement, Name name, Candidate candidate, ConeDiagnostic coneDiagnostic) {
        super(ktSourceElement, name, candidate);
        name.getClass();
        candidate.getClass();
        coneDiagnostic.getClass();
        this.diagnostic = coneDiagnostic;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate
    public boolean isError() {
        return true;
    }
}
