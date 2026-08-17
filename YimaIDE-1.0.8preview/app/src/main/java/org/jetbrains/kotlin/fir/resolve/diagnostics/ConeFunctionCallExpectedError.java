package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeFunctionCallExpectedError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeDiagnosticWithCandidates;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "hasValueParameters", Argument.Delimiters.none, "candidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;ZLjava/util/Collection;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getHasValueParameters", "()Z", "getCandidates", "()Ljava/util/Collection;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeFunctionCallExpectedError implements ConeDiagnosticWithCandidates {
    private final Collection<AbstractCallCandidate<?>> candidates;
    private final boolean hasValueParameters;
    private final Name name;

    /* JADX WARN: Multi-variable type inference failed */
    public ConeFunctionCallExpectedError(Name name, boolean z, Collection<? extends AbstractCallCandidate<?>> collection) {
        name.getClass();
        collection.getClass();
        this.name = name;
        this.hasValueParameters = z;
        this.candidates = collection;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates
    public Collection<AbstractCallCandidate<?>> getCandidates() {
        return this.candidates;
    }

    public final boolean getHasValueParameters() {
        return this.hasValueParameters;
    }

    public final Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        StringBuilder sb = new StringBuilder("Function call expected: ");
        sb.append(this.name);
        sb.append('(');
        sb.append(this.hasValueParameters ? "..." : Argument.Delimiters.none);
        sb.append(')');
        return sb.toString();
    }
}
