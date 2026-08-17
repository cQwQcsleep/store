package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/Unsupported;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "message", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getMessage", "()Ljava/lang/String;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Unsupported extends ResolutionDiagnostic {
    private final String message;
    private final KtSourceElement source;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Unsupported(String str, KtSourceElement ktSourceElement) {
        super(CandidateApplicability.K2_UNSUPPORTED);
        str.getClass();
        this.message = str;
        this.source = ktSourceElement;
    }

    public final String getMessage() {
        return this.message;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }
}
